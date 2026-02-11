-- Security hardening for exposed public table.
-- Fixes Supabase warning: "RLS Disabled in Public" on public.auth_audit_log.

alter table if exists public.auth_audit_log enable row level security;
alter table if exists public.auth_audit_log force row level security;

-- Prevent client roles from reading/writing this table through PostgREST.
revoke all on table public.auth_audit_log from anon;
revoke all on table public.auth_audit_log from authenticated;
