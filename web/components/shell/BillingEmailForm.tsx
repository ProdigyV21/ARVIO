"use client";

import { Loader2 } from "lucide-react";
import { useId, useRef, useState } from "react";
import { linkKofiEmail, type EntitlementState } from "@/lib/entitlement";
import { HttpError } from "@/lib/http";
import { authClient, useApp } from "@/lib/store";
import { trackPremiumEvent } from "@/lib/premiumAnalytics";

export function BillingEmailForm({ onEntitled }: { onEntitled: (state: EntitlementState) => void }) {
  const { auth, goToLogin } = useApp();
  const [open, setOpen] = useState(false);
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
      const next = await linkKofiEmail(authClient, email.trim(), verification ? code.trim() : undefined);
      if (authClient.session?.userId !== accountId) return;
      if (next.verificationRequired) setVerification(true);
      else if (next.entitled) {
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
      Paid with a different email? Link your Ko-fi email
    </button>
    {open && <form id={id} className="premium-billing-form" onSubmit={submit}>
      <label>Ko-fi / PayPal email
        <input type="email" autoComplete="email" value={email} required disabled={busy} maxLength={254}
          onChange={event => { setEmail(event.target.value); setCode(""); setVerification(false); setError(null); }} />
      </label>
      {verification && <label>Code sent to your billing email
        <input value={code} onChange={event => setCode(event.target.value)} autoComplete="one-time-code" autoCapitalize="none" spellCheck={false} required disabled={busy} maxLength={16} pattern="[a-fA-F0-9]{16}" />
      </label>}
      <button type="submit" className="paywall-trial" disabled={busy || !email.trim() || (verification && !/^[a-f0-9]{16}$/i.test(code.trim()))}>
        {busy ? <Loader2 className="paywall-spinner" size={16} /> : verification ? "Verify email" : "Send verification code"}
      </button>
      {error && <p className="paywall-error" role="alert">{error}</p>}
    </form>}
  </>;
}
