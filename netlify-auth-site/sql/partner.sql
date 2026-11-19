BEGIN;

CREATE TABLE IF NOT EXISTS public.partner_organizations (
  id uuid PRIMARY KEY,
  owner_id text NOT NULL UNIQUE,
  billing_email_hash text NOT NULL UNIQUE,
  revision integer NOT NULL DEFAULT 1 CHECK (revision > 0),
  state jsonb NOT NULL,
  created_at timestamptz NOT NULL DEFAULT now(),
  updated_at timestamptz NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS public.partner_memberships (
  account_id text PRIMARY KEY,
  organization_id uuid NOT NULL REFERENCES public.partner_organizations(id) ON DELETE CASCADE,
  customer_id uuid NOT NULL,
  accepted_at timestamptz NOT NULL DEFAULT now(),
  UNIQUE (organization_id, customer_id)
);

CREATE TABLE IF NOT EXISTS public.partner_invitations (
  token_hash text PRIMARY KEY,
  organization_id uuid NOT NULL REFERENCES public.partner_organizations(id) ON DELETE CASCADE,
  customer_id uuid NOT NULL,
  email_hash text NOT NULL,
  expires_at timestamptz NOT NULL,
  UNIQUE (organization_id, customer_id)
);

CREATE TABLE IF NOT EXISTS public.partner_challenges (
  account_id text PRIMARY KEY,
  code_hash text NOT NULL,
  attempts integer NOT NULL DEFAULT 0,
  sends integer NOT NULL DEFAULT 1,
  window_start timestamptz NOT NULL DEFAULT now(),
  sent_at timestamptz NOT NULL DEFAULT now(),
  expires_at timestamptz NOT NULL,
  used boolean NOT NULL DEFAULT false
);

CREATE TABLE IF NOT EXISTS public.partner_admin_sessions (
  token_hash text PRIMARY KEY,
  account_id text NOT NULL,
  expires_at timestamptz NOT NULL
);
CREATE INDEX IF NOT EXISTS partner_admin_sessions_expiry ON public.partner_admin_sessions(expires_at);

CREATE TABLE IF NOT EXISTS public.partner_payments (
  transaction_id text PRIMARY KEY,
  email_hash text NOT NULL,
  tier text NOT NULL,
  amount_cents integer NOT NULL CHECK (amount_cents > 0),
  paid_at timestamptz NOT NULL,
  paid_until timestamptz NOT NULL,
  revoked_at timestamptz,
  reconciliation_reason text,
  created_at timestamptz NOT NULL DEFAULT now()
);
CREATE INDEX IF NOT EXISTS partner_payments_customer ON public.partner_payments(email_hash, paid_until);

-- Complimentary pilots are explicit operator grants, never synthetic payments.
CREATE TABLE IF NOT EXISTS public.partner_pilot_grants (
  account_id text PRIMARY KEY,
  email_hash text NOT NULL,
  seats integer NOT NULL CHECK (seats BETWEEN 1 AND 200),
  expires_at timestamptz NOT NULL,
  granted_by text NOT NULL,
  reason text NOT NULL,
  revoked_at timestamptz,
  created_at timestamptz NOT NULL DEFAULT now()
);

-- These are server-only tables. No Supabase/PostgREST access, even if this
-- migration is installed in a database that exposes the public schema.
ALTER TABLE public.partner_organizations ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.partner_memberships ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.partner_invitations ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.partner_challenges ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.partner_admin_sessions ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.partner_payments ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.partner_pilot_grants ENABLE ROW LEVEL SECURITY;
REVOKE ALL ON public.partner_organizations, public.partner_memberships,
  public.partner_invitations, public.partner_challenges,
  public.partner_admin_sessions, public.partner_payments,
  public.partner_pilot_grants FROM PUBLIC;

COMMIT;
