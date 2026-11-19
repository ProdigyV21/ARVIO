"use client";
import "@/app/premium.css";
import { useTranslation } from "@/lib/i18n";


import { Loader2 } from "lucide-react";
import { useId, useRef, useState } from "react";
import { linkKofiEmail, type EntitlementState } from "@/lib/entitlement";
import { HttpError } from "@/lib/http";
import { authClient, useAppPick } from "@/lib/store";
import { trackPremiumEvent } from "@/lib/premiumAnalytics";

/** The emailed code: 8 digits ("1234 5678"); older emails had 16 hex characters. */
export function normalizeBillingCode(value: string): string | null {
  const raw = value.trim().toLowerCase().replace(/[\s-]/g, "");
  return /^\d{8}$/.test(raw) || /^[a-f0-9]{16}$/.test(raw) ? raw : null;
}

export function BillingEmailForm({ onEntitled, initiallyOpen = false }: { onEntitled: (state: EntitlementState) => void; initiallyOpen?: boolean }) {
  const translateUi = useTranslation();
  const { auth, goToLogin } = useAppPick("auth", "goToLogin");
  const [open, setOpen] = useState(initiallyOpen);
  const [email, setEmail] = useState("");
  const [code, setCode] = useState("");
  const [verification, setVerification] = useState(false);
  const [busy, setBusy] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const pending = useRef(false);
  const id = useId();

  const submit = async (event: React.FormEvent) => {
    event.preventDefault();
    if (pending.current) return;
    if (!auth) { goToLogin(); return; }
    const accountId = auth.userId;
    pending.current = true;
    setBusy(true); setError(null);
    void trackPremiumEvent(authClient, "membership_link_started");
    try {
      const next = await linkKofiEmail(authClient, email.trim(), verification ? normalizeBillingCode(code) ?? code.trim() : undefined);
      if (authClient.session?.userId !== accountId) return;
      if (next.verificationRequired) setVerification(true);
      else if (next.entitled && next.reason === "subscription") {
        void trackPremiumEvent(authClient, "membership_linked");
        onEntitled(next);
        setOpen(false); setCode(""); setVerification(false);
      } else setError("No active membership was found for that email. Check the email on your Ko-fi payment receipt.");
    } catch (err) {
      if (authClient.session?.userId !== accountId) return;
      const status = err instanceof HttpError ? err.status : 0;
      void trackPremiumEvent(authClient, "membership_link_failed", { status });
      if (status === 401) goToLogin();
      else setError(status === 404 ? "No active membership was found for that email. Check your Ko-fi payment receipt." : err instanceof Error ? err.message : "Could not link your membership. Please try again.");
    } finally { pending.current = false; setBusy(false); }
  };

  return <>
    <button type="button" className="paywall-link-toggle" aria-expanded={open} aria-controls={id} onClick={() => setOpen(!open)}>
      {translateUi("Paid with a different email? Link your Ko-fi email")}</button>
    {open && <form id={id} className="premium-billing-form" onSubmit={submit}>
      <label>{translateUi("Ko-fi / PayPal email")}<input type="email" autoComplete="email" value={email} required disabled={busy} maxLength={254}
          onChange={event => { setEmail(event.target.value); setCode(""); setVerification(false); setError(null); }} />
      </label>
      {verification && <label>{translateUi("8-digit code sent to your billing email")}<input value={code} onChange={event => setCode(event.target.value)} autoComplete="one-time-code" inputMode="numeric" autoCapitalize="none" spellCheck={false} required disabled={busy} maxLength={19} placeholder="1234 5678" autoFocus />
      </label>}
      <button type="submit" className="paywall-trial" disabled={busy || !email.trim() || (verification && !normalizeBillingCode(code))}>
        {busy ? <Loader2 className="paywall-spinner" size={16} /> : verification ? translateUi("Verify email") : translateUi("Send verification code")}
      </button>
      {error && <p className="paywall-error" role="alert">{translateUi(error ?? "")}</p>}
    </form>}
  </>;
}
