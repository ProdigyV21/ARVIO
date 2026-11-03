CREATE TABLE IF NOT EXISTS public.discord_premium_links (
  account_id text PRIMARY KEY,
  email_hash text NOT NULL UNIQUE CHECK (email_hash ~ '^[0-9a-f]{64}$'),
  discord_id text NOT NULL UNIQUE CHECK (discord_id ~ '^[0-9]{17,20}$'),
  next_check_at timestamptz NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS discord_premium_links_due
  ON public.discord_premium_links (next_check_at, account_id);

ALTER TABLE public.discord_premium_links ENABLE ROW LEVEL SECURITY;
REVOKE ALL ON public.discord_premium_links FROM PUBLIC;
