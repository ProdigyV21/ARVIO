create extension if not exists pgcrypto;

create table if not exists public.app_usage_events (
  id uuid primary key default gen_random_uuid(),
  event_name text not null,
  event_date date not null default current_date,
  install_id text not null,
  user_id uuid references auth.users(id) on delete set null,
  profile_id text,
  platform text not null default 'android',
  device_type text,
  app_version text,
  app_version_code integer,
  distribution text,
  metadata jsonb not null default '{}'::jsonb,
  created_at timestamptz not null default now(),
  updated_at timestamptz not null default now(),
  constraint app_usage_events_event_name_check check (event_name in ('app_open')),
  constraint app_usage_events_install_id_check check (char_length(install_id) between 8 and 128),
  constraint app_usage_events_event_day_install_unique unique (event_name, event_date, install_id)
);

create index if not exists app_usage_events_event_date_idx
  on public.app_usage_events (event_date desc);

create index if not exists app_usage_events_user_event_date_idx
  on public.app_usage_events (user_id, event_date desc)
  where user_id is not null;

create index if not exists app_usage_events_version_idx
  on public.app_usage_events (app_version, app_version_code, distribution, device_type);

alter table public.app_usage_events enable row level security;
alter table public.app_usage_events force row level security;

revoke all on public.app_usage_events from anon, authenticated;

create or replace view public.app_usage_daily as
select
  event_date as day,
  count(*) as active_installs,
  count(distinct user_id) filter (where user_id is not null) as active_accounts,
  count(*) filter (where user_id is not null) as signed_in_installs,
  count(*) filter (where user_id is null) as anonymous_installs
from public.app_usage_events
where event_name = 'app_open'
group by event_date;

create or replace view public.app_usage_weekly as
select
  date_trunc('week', event_date::timestamp)::date as week_start,
  count(distinct install_id) as active_installs,
  count(distinct user_id) filter (where user_id is not null) as active_accounts,
  count(distinct install_id) filter (where user_id is not null) as signed_in_installs,
  count(distinct install_id) filter (where user_id is null) as anonymous_installs
from public.app_usage_events
where event_name = 'app_open'
group by date_trunc('week', event_date::timestamp)::date;

create or replace view public.app_usage_monthly as
select
  date_trunc('month', event_date::timestamp)::date as month_start,
  count(distinct install_id) as active_installs,
  count(distinct user_id) filter (where user_id is not null) as active_accounts,
  count(distinct install_id) filter (where user_id is not null) as signed_in_installs,
  count(distinct install_id) filter (where user_id is null) as anonymous_installs
from public.app_usage_events
where event_name = 'app_open'
group by date_trunc('month', event_date::timestamp)::date;

create or replace view public.app_usage_versions_30d as
select
  coalesce(nullif(app_version, ''), 'unknown') as app_version,
  app_version_code,
  coalesce(nullif(distribution, ''), 'unknown') as distribution,
  coalesce(nullif(device_type, ''), 'unknown') as device_type,
  count(distinct install_id) as active_installs,
  count(distinct user_id) filter (where user_id is not null) as active_accounts
from public.app_usage_events
where event_name = 'app_open'
  and event_date >= current_date - interval '30 days'
group by app_version, app_version_code, distribution, device_type;

revoke all on public.app_usage_daily from anon, authenticated;
revoke all on public.app_usage_weekly from anon, authenticated;
revoke all on public.app_usage_monthly from anon, authenticated;
revoke all on public.app_usage_versions_30d from anon, authenticated;
