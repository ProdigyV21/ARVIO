CREATE TABLE IF NOT EXISTS public.arvio_accounts (
  id uuid PRIMARY KEY DEFAULT (md5(random()::text || clock_timestamp()::text)::uuid),
  email text NOT NULL,
  email_normalized text NOT NULL UNIQUE,
  supabase_user_id uuid UNIQUE,
  created_at timestamptz NOT NULL DEFAULT now(),
  updated_at timestamptz NOT NULL DEFAULT now(),
  last_seen_at timestamptz
);

CREATE INDEX IF NOT EXISTS idx_arvio_accounts_supabase_user_id
  ON public.arvio_accounts (supabase_user_id);

CREATE TABLE IF NOT EXISTS public.account_sync_snapshots (
  account_id uuid PRIMARY KEY REFERENCES public.arvio_accounts(id) ON DELETE CASCADE,
  payload jsonb NOT NULL,
  payload_version integer NOT NULL DEFAULT 1,
  restore_rank integer NOT NULL DEFAULT 0,
  profile_count integer,
  scoped_coverage integer NOT NULL DEFAULT 0,
  payload_updated_at timestamptz,
  source text NOT NULL DEFAULT 'netlify',
  created_at timestamptz NOT NULL DEFAULT now(),
  updated_at timestamptz NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_account_sync_snapshots_updated_at
  ON public.account_sync_snapshots (updated_at DESC);

CREATE TABLE IF NOT EXISTS public.account_sync_items (
  account_id uuid NOT NULL REFERENCES public.arvio_accounts(id) ON DELETE CASCADE,
  scope text NOT NULL,
  profile_id text NOT NULL DEFAULT '',
  entity_key text NOT NULL,
  payload jsonb,
  deleted_at timestamptz,
  version bigint NOT NULL DEFAULT 1,
  updated_at timestamptz NOT NULL DEFAULT now(),
  PRIMARY KEY (account_id, scope, profile_id, entity_key)
);

CREATE INDEX IF NOT EXISTS idx_account_sync_items_account_scope
  ON public.account_sync_items (account_id, scope, profile_id);

CREATE TABLE IF NOT EXISTS public.account_sync_delta_events (
  event_id bigserial PRIMARY KEY,
  account_id uuid NOT NULL REFERENCES public.arvio_accounts(id) ON DELETE CASCADE,
  scope text NOT NULL,
  profile_id text NOT NULL DEFAULT '',
  entity_key text NOT NULL,
  operation text NOT NULL CHECK (operation IN ('upsert', 'delete')),
  payload jsonb,
  item_version bigint NOT NULL,
  created_at timestamptz NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_account_sync_delta_events_account_event
  ON public.account_sync_delta_events (account_id, event_id);

CREATE TABLE IF NOT EXISTS public.legacy_supabase_users (
  supabase_user_id uuid PRIMARY KEY,
  email text NOT NULL,
  email_normalized text NOT NULL,
  created_at timestamptz,
  last_sign_in_at timestamptz,
  imported_at timestamptz NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_legacy_supabase_users_email
  ON public.legacy_supabase_users (email_normalized);

CREATE TABLE IF NOT EXISTS public.legacy_supabase_snapshots (
  supabase_user_id uuid PRIMARY KEY,
  email text,
  email_normalized text,
  payload jsonb NOT NULL,
  payload_version integer NOT NULL DEFAULT 1,
  restore_rank integer NOT NULL DEFAULT 0,
  profile_count integer,
  scoped_coverage integer NOT NULL DEFAULT 0,
  payload_updated_at timestamptz,
  source text NOT NULL DEFAULT 'supabase_export',
  imported_at timestamptz NOT NULL DEFAULT now(),
  claimed_account_id uuid REFERENCES public.arvio_accounts(id) ON DELETE SET NULL,
  claimed_at timestamptz
);

CREATE INDEX IF NOT EXISTS idx_legacy_supabase_snapshots_email
  ON public.legacy_supabase_snapshots (email_normalized);

CREATE TABLE IF NOT EXISTS public.app_usage_daily (
  usage_date date NOT NULL,
  account_id uuid NOT NULL DEFAULT '00000000-0000-0000-0000-000000000000',
  install_id text NOT NULL DEFAULT '',
  event_name text NOT NULL,
  event_count integer NOT NULL DEFAULT 0,
  updated_at timestamptz NOT NULL DEFAULT now(),
  PRIMARY KEY (usage_date, account_id, install_id, event_name)
);

CREATE TABLE IF NOT EXISTS public.legacy_supabase_rows (
  table_name text NOT NULL,
  supabase_user_id uuid,
  row_key text NOT NULL,
  row_data jsonb NOT NULL,
  imported_at timestamptz NOT NULL DEFAULT now(),
  PRIMARY KEY (table_name, row_key)
);

CREATE INDEX IF NOT EXISTS idx_legacy_supabase_rows_user_table
  ON public.legacy_supabase_rows (supabase_user_id, table_name);
