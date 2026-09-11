"use client";

import { BadgeCheck, ExternalLink, Loader2, RefreshCw } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { authClient, useApp } from "@/lib/store";
import { useEntitlement } from "@/lib/entitlementContext";
import { ENTITLEMENT_REFRESH_EVENT, fetchEntitlement, kofiSubscribeUrl, type EntitlementState } from "@/lib/entitlement";
import { config } from "@/lib/config";
import { HttpError } from "@/lib/http";
import { trackPremiumEvent } from "@/lib/premiumAnalytics";
import { BillingEmailForm } from "./BillingEmailForm";

export function PremiumAccount() {
  const { auth, goToLogin } = useApp();
  const entitlement = useEntitlement();
  const [state, setState] = useState(entitlement);
  const [busy, setBusy] = useState(false);
  const [message, setMessage] = useState("");
  const pending = useRef(false);
  useEffect(() => { setState(entitlement); setMessage(""); }, [auth?.userId, entitlement]);
  if (!config.paywallEnabled || !auth) return null;
  const trial = state?.reason === "trial";
  const paid = state?.entitled && state.reason === "subscription";
  const applied = (next: EntitlementState) => {
    setState(next);
    setMessage(next.reason === "subscription" ? "Your Premium membership is active." : "Your trial is active. If you have paid, check your billing email below; do not pay again.");
    window.dispatchEvent(new Event(ENTITLEMENT_REFRESH_EVENT));
  };
  const check = async () => {
    if (pending.current) return;
    pending.current = true; setBusy(true); setMessage("");
    const account = auth.userId;
    try {
      const next = await fetchEntitlement(authClient);
      if (authClient.session?.userId !== account) return;
      if (next.entitled) applied(next);
      else setMessage("Payment has not arrived yet. Try again shortly or link the email on your Ko-fi receipt. Do not pay again.");
    } catch (error) {
      if (authClient.session?.userId !== account) return;
      if (error instanceof HttpError && error.status === 401) goToLogin();
      else setMessage("Could not check access. Your payment status has not changed; please retry.");
    } finally { pending.current = false; setBusy(false); }
  };
  return <section className="premium-account" aria-labelledby="premium-account-title">
    <h2 id="premium-account-title"><BadgeCheck size={20} /> ARVIO Web Premium</h2>
    <p className="premium-account-status">{paid ? "Membership active" : trial ? "Free trial active" : "Check membership"}</p>
    <p className="premium-account-email">{auth.email}</p>
    {state?.expiresAt && <p>{trial ? "Trial ends" : "Access until"}: {new Date(state.expiresAt).toLocaleString()}</p>}
    <p>Browser access on iPhone, iPad, Windows, Mac and Linux. The Android app and Cloud dashboard remain free.</p>
    <div className="premium-account-actions">
      {!paid && <a className="paywall-primary" href={kofiSubscribeUrl()} target="_blank" rel="noopener noreferrer" onClick={() => { void trackPremiumEvent(authClient, "checkout_opened", { content: "account_settings" }); }}>
        Keep Premium - $2.99/month <ExternalLink size={15} />
      </a>}
      <button type="button" className="paywall-trial" disabled={busy} onClick={() => void check()}>
        {busy ? <Loader2 size={16} className="paywall-spinner" /> : <RefreshCw size={16} />} Check payment status
      </button>
    </div>
    <BillingEmailForm key={auth.userId} onEntitled={applied} />
    {message && <p role="status">{message}</p>}
  </section>;
}
