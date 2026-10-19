"use client";
import { useTranslation } from "@/lib/i18n";


import { BadgeCheck, Check, ExternalLink, Loader2, LogOut, RefreshCw, Sparkles } from "lucide-react";
import { useCallback, useEffect, useRef, useState } from "react";
import { config } from "@/lib/config";
import { HttpError } from "@/lib/http";
import {
  cachedEntitlement,
  fetchEntitlement,
  startTrial,
  type EntitlementState
} from "@/lib/entitlement";
import { authClient, useApp } from "@/lib/store";
import { capturePremiumAttribution, trackPremiumEvent, trackPremiumMilestone, TRIAL_INTENT_KEY } from "@/lib/premiumAnalytics";
import { currentEntitlement, entitlementCheckDelay } from "@/lib/entitlementPolicy";
import { EntitlementContext } from "@/lib/entitlementContext";
import { ENTITLEMENT_REFRESH_EVENT } from "@/lib/entitlement";
import { MembershipCheckout } from "./MembershipCheckout";

// Three-day free trial: enabled — enough time to use ARVIO Web on normal days,
// blind $2.99 ask. One trial per account (trialUsed is stamped server-side).
const SHOW_TRIAL = true;

// A still-valid cached membership survives transient backend errors. Unknown
// access is retryable, not a free membership or a request to pay again.
export function EntitlementGate({ children }: { children: React.ReactNode }) {
  const translateUi = useTranslation();
  const { auth, signOut, goToLogin } = useApp();
  const accountId = auth?.userId ?? null;
  const [state, setState] = useState<EntitlementState | null>(() => cachedEntitlement(authClient));
  const [status, setStatus] = useState<"loading" | "ready" | "error">(state ? "ready" : "loading");
  const [retry, setRetry] = useState(0);
  const [stateAccountId, setStateAccountId] = useState(accountId);
  const lastVerifiedAt = useRef(Date.now());
  const lastRefreshAt = useRef(0);

  useEffect(() => {
    if (!config.paywallEnabled) return;
    const cached = cachedEntitlement(authClient);
    setStateAccountId(accountId);
    setState(cached);
    if (!accountId) {
      setStatus("ready");
      return;
    }
    setStatus(cached ? "ready" : "loading");
    let active = true;
    void fetchEntitlement(authClient)
      .then((next) => { if (active) { lastVerifiedAt.current = Date.now(); setState(next); setStatus("ready"); } })
      .catch(() => { if (active) setStatus("error"); });
    return () => { active = false; };
  }, [accountId, retry]);

  useEffect(() => {
    if (!config.paywallEnabled || !accountId) return;
    let active = true;
    let refreshing = false;
    let retryTimer: ReturnType<typeof setTimeout> | null = null;
    let expiryTimer: ReturnType<typeof setTimeout> | null = null;

    const refreshAccess = (scheduleRetry = false) => {
      if (!active || refreshing || document.visibilityState === "hidden") return;
      if (scheduleRetry && Date.now() - lastRefreshAt.current < 1000) return;
      refreshing = true;
      lastRefreshAt.current = Date.now();
      let refreshedState = state;
      let failed = false;
      void fetchEntitlement(authClient)
        .then((next) => {
          if (!active) return;
          lastVerifiedAt.current = Date.now();
          refreshedState = next;
          setState(next);
          setStatus("ready");
          if ((!next.entitled || next.reason === "trial") && scheduleRetry) {
            if (retryTimer) clearTimeout(retryTimer);
            retryTimer = setTimeout(() => refreshAccess(false), 3000);
          }
        })
        .catch(() => { failed = true; if (active) setStatus("error"); })
        .finally(() => {
          refreshing = false;
          if (active) {
            if (expiryTimer) clearTimeout(expiryTimer);
            const nextDelay = failed ? 15 * 60_000 : entitlementCheckDelay(refreshedState);
            if (nextDelay !== null) expiryTimer = setTimeout(() => refreshAccess(false), nextDelay);
          }
        });
    };

    const delay = entitlementCheckDelay(state);
    if (delay !== null) expiryTimer = setTimeout(() => refreshAccess(false), delay);

    const onFocus = () => refreshAccess(true);
    const onVisibilityChange = () => {
      if (document.visibilityState === "visible") refreshAccess(true);
    };
    window.addEventListener("focus", onFocus);
    window.addEventListener(ENTITLEMENT_REFRESH_EVENT, onFocus);
    document.addEventListener("visibilitychange", onVisibilityChange);
    return () => {
      active = false;
      if (retryTimer) clearTimeout(retryTimer);
      if (expiryTimer) clearTimeout(expiryTimer);
      window.removeEventListener("focus", onFocus);
      window.removeEventListener(ENTITLEMENT_REFRESH_EVENT, onFocus);
      document.removeEventListener("visibilitychange", onVisibilityChange);
    };
  }, [accountId, state?.entitled, state?.reason, state?.expiresAt, retry]);

  if (!config.paywallEnabled) return <>{children}</>;
  const access = stateAccountId === accountId ? currentEntitlement(state) : null;
  if (access?.entitled && (status !== "error" || Date.now() - lastVerifiedAt.current < 6 * 60 * 60_000)) return <EntitlementContext.Provider value={access}>{children}</EntitlementContext.Provider>;
  if (status === "loading" || stateAccountId !== accountId) {
    return (
      <main className="paywall-boot">
        <Loader2 className="paywall-spinner" size={40} />
      </main>
    );
  }
  if (status === "error") {
    return (
      <main className="paywall">
        <div className="paywall-card" role="alert">
          <h1>{translateUi("We could not check your membership")}</h1>
          <p className="paywall-sub">{translateUi("Your payment status has not changed. Please retry before subscribing again.")}</p>
          <button className="paywall-trial" onClick={() => setRetry((value) => value + 1)}><RefreshCw size={16} /> {translateUi(" Check access again")}</button>
          <button className="paywall-link-toggle" onClick={goToLogin}>{translateUi("Reconnect to Cloud")}</button>
        </div>
      </main>
    );
  }

  return (
    <PaywallScreen
      state={access}
      accountId={accountId}
      isSignedIn={Boolean(auth)}
      onEntitled={(next) => {
        if (authClient.session?.userId !== accountId) return;
        lastVerifiedAt.current = Date.now(); setStateAccountId(accountId); setState(next); setStatus("ready");
      }}
      onConnect={goToLogin}
      onSignOut={signOut}
    />
  );
}

export function PaywallScreen({
  state,
  accountId,
  isSignedIn,
  onEntitled,
  onConnect,
  onSignOut
}: {
  state: EntitlementState | null;
  accountId: string | null;
  isSignedIn: boolean;
  onEntitled: (next: EntitlementState) => void;
  onConnect: () => void;
  onSignOut: () => void;
}) {
  const translateUi = useTranslation();
  const [busy, setBusy] = useState<"trial" | null>(null);
  const [error, setError] = useState<string | null>(null);
  const trialAvailable = state?.trialAvailable ?? true;
  const trialDays = state?.trialDurationDays ?? 3;
  const expired = state?.reason === "expired" || state?.status === "cancelled";

  useEffect(() => {
    capturePremiumAttribution();
    if (!isSignedIn) return;
    void trackPremiumEvent(authClient, "paywall_view", {}, true);
    void trackPremiumMilestone(authClient, "account_connected");
  }, [isSignedIn, accountId]);

  const beginTrial = useCallback(async () => {
    if (!isSignedIn) {
      capturePremiumAttribution();
      try { localStorage.setItem(TRIAL_INTENT_KEY, "1"); } catch { /* storage is optional */ }
      onConnect();
      return;
    }
    void trackPremiumEvent(authClient, "trial_requested");
    setBusy("trial"); setError(null);
    try {
      const next = await startTrial(authClient);
      if (next.entitled) {
        try { localStorage.removeItem(TRIAL_INTENT_KEY); } catch { /* storage is optional */ }
        onEntitled(next);
      }
      else setError("Your free trial has already been used.");
    } catch (err) {
      // startTrial already refreshed + retried on a stale token; reaching this
      // catch means the session is genuinely dead, the trial was consumed, or
      // the backend hiccuped — say which, and give the dead-session case a way
      // out (the generic message left users stuck with no next step).
      const status = err instanceof HttpError ? err.status : null;
      if (status === 401) {
        onConnect();
        return;
      } else if (status === 409) {
        try { localStorage.removeItem(TRIAL_INTENT_KEY); } catch { /* storage is optional */ }
        setError("Your free trial has already been used.");
      } else setError("Could not start the trial — please try again in a moment.");
      void trackPremiumEvent(authClient, "trial_start_failed", {
        status: status || 0,
        error: err instanceof Error ? err.message : "unknown"
      });
    } finally {
      setBusy(null);
    }
  }, [isSignedIn, onConnect, onEntitled]);

  useEffect(() => {
    if (!isSignedIn || busy !== null || !trialAvailable || expired) return;
    let pending = false;
    try { pending = localStorage.getItem(TRIAL_INTENT_KEY) === "1"; } catch { pending = false; }
    if (pending) {
      try { localStorage.removeItem(TRIAL_INTENT_KEY); } catch { /* storage is optional */ }
      void beginTrial();
    }
  }, [beginTrial, busy, expired, isSignedIn, trialAvailable]);

  return (
    <main className="paywall">
      <div className="paywall-card">
        <div className="paywall-brand">
          <img src="/arvio-logo.svg" alt="" className="paywall-logo" />
          <img src="/arvio-wordmark.svg" alt="ARVIO" className="paywall-wordmark" />
        </div>

        <h1>{expired ? translateUi("Your ARVIO Web membership has ended") : translateUi("ARVIO Web is a members feature")}</h1>
        <p className="paywall-sub">
          {translateUi("Take your existing ARVIO setup to Windows, Mac, iPhone, iPad and smart-TV browsers. Your profiles, libraries, addons and progress stay connected through ARVIO Cloud.")}</p>

        <div className="paywall-benefits" aria-label={translateUi("ARVIO Web benefits")}>
          <span><Check size={15} /> {translateUi(" Same profiles, libraries and watch progress")}</span>
          <span><Check size={15} /> {translateUi(" Watch or download directly on Windows, Mac and mobile")}</span>
          <span><Check size={15} /> {translateUi(" Browser playback and one-click VLC")}</span>
          <span><Check size={15} /> {translateUi(" Android and TV app remains completely free")}</span>
        </div>

        {SHOW_TRIAL && trialAvailable && !expired && (
          <button type="button" className="paywall-primary" onClick={() => void beginTrial()} disabled={busy !== null}>
            {busy === "trial" ? <Loader2 className="paywall-spinner" size={16} /> : <Sparkles size={16} />}
            {isSignedIn ? translateUi("Start {value0}-day free trial", {value0: trialDays}) : translateUi("Connect to Cloud for {value0}-day trial", {value0: trialDays})}
          </button>
        )}
        {SHOW_TRIAL && trialAvailable && !expired && <p className="paywall-disclaimer">{translateUi("Your trial ends automatically. You will not be charged unless you subscribe on Ko-fi.")}</p>}

        <div className="paywall-price">
          <span className="paywall-amount">$2.99</span>
          <span className="paywall-period">{translateUi("/ month")}</span>
        </div>

        <MembershipCheckout
          className={SHOW_TRIAL && trialAvailable && !expired ? "paywall-trial" : "paywall-primary"}
          entry="paywall"
          onEntitled={onEntitled}
          showEmailHint
        >
          <BadgeCheck size={18} /> {translateUi(" Subscribe on Ko-fi ")}<ExternalLink size={15} />
        </MembershipCheckout>
        <p className="paywall-disclaimer">{translateUi("Use the email on your ARVIO Cloud account at checkout, or link your billing email below. Membership does not include media or subscriptions to other services.")}</p>

        {error && <p className="paywall-error">{translateUi(error ?? "")}</p>}

        <p className="paywall-proof">{translateUi("10,000+ users · 10+ contributors · open source")}</p>

        {isSignedIn && (
          <button type="button" className="paywall-signout" onClick={onSignOut}>
            <LogOut size={15} /> {translateUi(" Sign out")}</button>
        )}
      </div>
    </main>
  );
}
