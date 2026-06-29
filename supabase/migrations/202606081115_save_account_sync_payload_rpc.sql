-- Canonical cloud-sync writer for app clients.
-- The client sends one full account snapshot; the database stores it in the
-- primary account_sync_state row and mirrors it into user_settings for older
-- app versions/realtime listeners.

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
  v_profile_count integer := 0;
begin
  if v_user_id is null then
    raise exception 'not_authenticated' using errcode = '28000';
  end if;

  if p_payload is null or length(trim(p_payload)) = 0 then
    raise exception 'empty_account_sync_payload' using errcode = '22023';
  end if;

  v_payload_json := p_payload::jsonb;

  if jsonb_typeof(v_payload_json -> 'profiles') = 'array' then
    v_profile_count := jsonb_array_length(v_payload_json -> 'profiles');
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
    'user_id', v_user_id::text,
    'updated_at', v_now::text,
    'profile_count', v_profile_count
  );
end;
$$;

grant execute on function public.save_account_sync_payload(text) to authenticated;
