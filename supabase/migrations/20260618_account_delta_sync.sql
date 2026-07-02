-- Nuvio-style cloud sync foundation for ARVIO.
--
-- Keep the legacy account_sync_state full snapshot for old clients, but add
-- small row-based current state + append-only delta events so newer clients can
-- pull only the changes since their last cursor.

create table if not exists public.account_sync_items (
  user_id uuid not null references auth.users(id) on delete cascade,
  scope text not null,
  profile_id text not null default '',
  entity_key text not null,
  payload jsonb not null default '{}'::jsonb,
  deleted_at timestamptz,
  version bigint not null default 1,
  created_at timestamptz not null default now(),
  updated_at timestamptz not null default now(),
  primary key (user_id, scope, profile_id, entity_key)
);

create index if not exists idx_account_sync_items_user_scope_updated
  on public.account_sync_items(user_id, scope, updated_at desc);

create table if not exists public.account_sync_delta_events (
  event_id bigserial primary key,
  user_id uuid not null references auth.users(id) on delete cascade,
  scope text not null,
  profile_id text not null default '',
  entity_key text not null,
  operation text not null check (operation in ('upsert', 'delete')),
  payload jsonb,
  item_version bigint not null default 1,
  created_at timestamptz not null default now()
);

create index if not exists idx_account_sync_delta_events_user_event
  on public.account_sync_delta_events(user_id, event_id);

create index if not exists idx_account_sync_delta_events_user_scope_event
  on public.account_sync_delta_events(user_id, scope, event_id);

alter table public.account_sync_items enable row level security;
alter table public.account_sync_items force row level security;

alter table public.account_sync_delta_events enable row level security;
alter table public.account_sync_delta_events force row level security;

do $$
begin
  if not exists (
    select 1 from pg_policies
    where schemaname = 'public'
      and tablename = 'account_sync_items'
      and policyname = 'users_manage_own_account_sync_items'
  ) then
    create policy users_manage_own_account_sync_items
      on public.account_sync_items
      for all
      to authenticated
      using ((select auth.uid()) = user_id)
      with check ((select auth.uid()) = user_id);
  end if;

  if not exists (
    select 1 from pg_policies
    where schemaname = 'public'
      and tablename = 'account_sync_delta_events'
      and policyname = 'users_read_own_account_sync_delta_events'
  ) then
    create policy users_read_own_account_sync_delta_events
      on public.account_sync_delta_events
      for select
      to authenticated
      using ((select auth.uid()) = user_id);
  end if;
end $$;

create or replace function public.account_sync_profile_count(p_payload jsonb)
returns integer
language sql
immutable
as $$
  select case
    when jsonb_typeof(p_payload -> 'profiles') = 'array'
      then jsonb_array_length(p_payload -> 'profiles')
    else null
  end;
$$;

create or replace function public.account_sync_scoped_coverage(p_payload jsonb)
returns integer
language plpgsql
immutable
as $$
declare
  v_profile_ids text[] := array[]::text[];
  v_key text;
  v_count integer := 0;
  v_profile_id text;
  v_obj jsonb;
begin
  if jsonb_typeof(p_payload -> 'profiles') <> 'array' then
    return 0;
  end if;

  select coalesce(array_agg(profile ->> 'id'), array[]::text[])
    into v_profile_ids
  from jsonb_array_elements(p_payload -> 'profiles') as profile
  where coalesce(profile ->> 'id', '') <> '';

  if coalesce(array_length(v_profile_ids, 1), 0) = 0 then
    return 0;
  end if;

  foreach v_key in array array[
    'profileSettingsById',
    'addonsByProfile',
    'catalogsByProfile',
    'hiddenPreinstalledByProfile',
    'hiddenAddonByProfile',
    'hiddenHomeServerByProfile',
    'iptvByProfile',
    'watchlistByProfile'
  ] loop
    v_obj := p_payload -> v_key;
    if jsonb_typeof(v_obj) = 'object' then
      foreach v_profile_id in array v_profile_ids loop
        if v_obj ? v_profile_id then
          v_count := v_count + 1;
        end if;
      end loop;
    end if;
  end loop;

  return v_count;
end;
$$;

create or replace function public.account_sync_restore_rank(p_payload jsonb)
returns integer
language plpgsql
immutable
as $$
declare
  v_profile_count integer := public.account_sync_profile_count(p_payload);
  v_has_full_shape boolean := false;
  v_has_configured_state boolean := false;
begin
  v_has_full_shape :=
    p_payload ? 'profileSettingsById' or
    p_payload ? 'addonsByProfile' or
    p_payload ? 'catalogsByProfile' or
    p_payload ? 'iptvByProfile' or
    p_payload ? 'watchlistByProfile';

  v_has_configured_state :=
    (
      case
        when jsonb_typeof(p_payload -> 'addons') = 'array'
          then jsonb_array_length(p_payload -> 'addons') > 0
        else false
      end
    ) or
    coalesce(p_payload ->> 'iptvM3uUrl', '') <> '' or
    public.account_sync_scoped_coverage(p_payload) > 0;

  if v_profile_count is not null and v_profile_count <= 0 then
    return 0;
  elsif v_profile_count is not null and v_profile_count > 1 and v_has_full_shape then
    return 80;
  elsif v_profile_count is not null and v_profile_count > 1 then
    return 70;
  elsif v_has_configured_state and v_has_full_shape then
    return 50;
  elsif v_has_configured_state then
    return 40;
  elsif v_profile_count is null and v_has_full_shape then
    return 30;
  elsif v_profile_count is null then
    return 20;
  else
    return 10;
  end if;
end;
$$;

create or replace function public.account_sync_event_cursor()
returns bigint
language sql
security definer
set search_path = public, pg_catalog
as $$
  select coalesce(max(event_id), 0)
  from public.account_sync_delta_events
  where user_id = auth.uid();
$$;

grant execute on function public.account_sync_event_cursor() to authenticated;

create or replace function public.push_account_sync_items(p_items jsonb)
returns jsonb
language plpgsql
security definer
set search_path = public, pg_catalog
as $$
declare
  v_user_id uuid := auth.uid();
  v_now timestamptz := now();
  v_item jsonb;
  v_scope text;
  v_profile_id text;
  v_entity_key text;
  v_payload jsonb;
  v_deleted boolean;
  v_operation text;
  v_version bigint;
  v_count integer := 0;
begin
  if v_user_id is null then
    raise exception 'not_authenticated' using errcode = '28000';
  end if;

  if jsonb_typeof(p_items) <> 'array' then
    raise exception 'items_must_be_array' using errcode = '22023';
  end if;

  for v_item in select * from jsonb_array_elements(p_items) loop
    v_scope := nullif(trim(coalesce(v_item ->> 'scope', '')), '');
    v_profile_id := trim(coalesce(v_item ->> 'profile_id', v_item ->> 'profileId', ''));
    v_entity_key := nullif(trim(coalesce(v_item ->> 'entity_key', v_item ->> 'entityKey', '')), '');
    v_payload := coalesce(v_item -> 'payload', '{}'::jsonb);
    v_deleted := coalesce((v_item ->> 'deleted')::boolean, false);

    if v_scope is null or v_entity_key is null then
      raise exception 'missing_scope_or_entity_key' using errcode = '22023';
    end if;

    insert into public.account_sync_items (
      user_id,
      scope,
      profile_id,
      entity_key,
      payload,
      deleted_at,
      version,
      created_at,
      updated_at
    )
    values (
      v_user_id,
      v_scope,
      v_profile_id,
      v_entity_key,
      v_payload,
      case when v_deleted then v_now else null end,
      1,
      v_now,
      v_now
    )
    on conflict (user_id, scope, profile_id, entity_key) do update
      set payload = excluded.payload,
          deleted_at = excluded.deleted_at,
          version = public.account_sync_items.version + 1,
          updated_at = excluded.updated_at
    returning version into v_version;

    v_operation := case when v_deleted then 'delete' else 'upsert' end;

    insert into public.account_sync_delta_events (
      user_id,
      scope,
      profile_id,
      entity_key,
      operation,
      payload,
      item_version,
      created_at
    )
    values (
      v_user_id,
      v_scope,
      v_profile_id,
      v_entity_key,
      v_operation,
      case when v_deleted then null else v_payload end,
      v_version,
      v_now
    );

    v_count := v_count + 1;
  end loop;

  return jsonb_build_object(
    'updated_count', v_count,
    'cursor', public.account_sync_event_cursor()
  );
end;
$$;

grant execute on function public.push_account_sync_items(jsonb) to authenticated;

create or replace function public.pull_account_sync_delta(
  p_since_event_id bigint default 0,
  p_limit integer default 500
)
returns table (
  event_id bigint,
  scope text,
  profile_id text,
  entity_key text,
  operation text,
  payload jsonb,
  item_version bigint,
  created_at timestamptz
)
language sql
security definer
set search_path = public, pg_catalog
as $$
  select
    e.event_id,
    e.scope,
    e.profile_id,
    e.entity_key,
    e.operation,
    e.payload,
    e.item_version,
    e.created_at
  from public.account_sync_delta_events e
  where e.user_id = auth.uid()
    and e.event_id > coalesce(p_since_event_id, 0)
  order by e.event_id asc
  limit least(greatest(coalesce(p_limit, 500), 1), 1000);
$$;

grant execute on function public.pull_account_sync_delta(bigint, integer) to authenticated;

create or replace function public.pull_account_sync_items(
  p_scope text default null,
  p_profile_id text default null,
  p_limit integer default 1000
)
returns table (
  scope text,
  profile_id text,
  entity_key text,
  payload jsonb,
  deleted_at timestamptz,
  version bigint,
  updated_at timestamptz
)
language sql
security definer
set search_path = public, pg_catalog
as $$
  select
    i.scope,
    i.profile_id,
    i.entity_key,
    i.payload,
    i.deleted_at,
    i.version,
    i.updated_at
  from public.account_sync_items i
  where i.user_id = auth.uid()
    and (p_scope is null or i.scope = p_scope)
    and (p_profile_id is null or i.profile_id = p_profile_id)
  order by i.updated_at desc
  limit least(greatest(coalesce(p_limit, 1000), 1), 5000);
$$;

grant execute on function public.pull_account_sync_items(text, text, integer) to authenticated;

-- Replace the legacy full-snapshot writer with a guarded version. It still
-- writes account_sync_state/user_settings for old clients, but refuses to let
-- an obviously poorer snapshot overwrite a richer cloud backup.
create or replace function public.save_account_sync_payload(p_payload text)
returns jsonb
language plpgsql
security definer
set search_path = public, pg_catalog
as $$
declare
  v_user_id uuid := auth.uid();
  v_now timestamptz := now();
  v_payload_json jsonb;
  v_existing_payload_json jsonb;
  v_existing_payload text;
  v_incoming_profile_count integer := 0;
  v_existing_profile_count integer := 0;
  v_incoming_rank integer := 0;
  v_existing_rank integer := 0;
  v_incoming_coverage integer := 0;
  v_existing_coverage integer := 0;
  v_keep_existing boolean := false;
begin
  if v_user_id is null then
    raise exception 'not_authenticated' using errcode = '28000';
  end if;

  if p_payload is null or length(trim(p_payload)) = 0 then
    raise exception 'empty_account_sync_payload' using errcode = '22023';
  end if;

  v_payload_json := p_payload::jsonb;
  v_incoming_profile_count := coalesce(public.account_sync_profile_count(v_payload_json), 0);
  v_incoming_rank := public.account_sync_restore_rank(v_payload_json);
  v_incoming_coverage := public.account_sync_scoped_coverage(v_payload_json);

  select payload
    into v_existing_payload
  from public.account_sync_state
  where user_id = v_user_id;

  if v_existing_payload is not null and length(trim(v_existing_payload)) > 0 then
    v_existing_payload_json := v_existing_payload::jsonb;
    v_existing_profile_count := coalesce(public.account_sync_profile_count(v_existing_payload_json), 0);
    v_existing_rank := public.account_sync_restore_rank(v_existing_payload_json);
    v_existing_coverage := public.account_sync_scoped_coverage(v_existing_payload_json);

    v_keep_existing :=
      v_incoming_rank < 40 and v_existing_rank >= 40;
  end if;

  if v_keep_existing then
    return jsonb_build_object(
      'accepted', false,
      'reason', 'existing_snapshot_is_richer',
      'user_id', v_user_id::text,
      'profile_count', v_existing_profile_count,
      'incoming_profile_count', v_incoming_profile_count,
      'rank', v_existing_rank,
      'incoming_rank', v_incoming_rank,
      'coverage', v_existing_coverage,
      'incoming_coverage', v_incoming_coverage
    );
  end if;

  insert into public.account_sync_state (user_id, payload, updated_at)
  values (v_user_id, p_payload, v_now)
  on conflict (user_id) do update
    set payload = excluded.payload,
        updated_at = excluded.updated_at;

  insert into public.user_settings (user_id, settings, created_at, updated_at)
  values (
    v_user_id,
    jsonb_build_object(
      'accountSyncPayload', p_payload,
      'accountSyncUpdatedAt', v_now::text
    ),
    v_now,
    v_now
  )
  on conflict (user_id) do update
    set settings = coalesce(public.user_settings.settings, '{}'::jsonb)
      || jsonb_build_object(
        'accountSyncPayload', p_payload,
        'accountSyncUpdatedAt', v_now::text
      ),
        updated_at = v_now;

  return jsonb_build_object(
    'accepted', true,
    'user_id', v_user_id::text,
    'updated_at', v_now::text,
    'profile_count', v_incoming_profile_count,
    'rank', v_incoming_rank,
    'coverage', v_incoming_coverage,
    'cursor', public.account_sync_event_cursor()
  );
end;
$$;

grant execute on function public.save_account_sync_payload(text) to authenticated;
