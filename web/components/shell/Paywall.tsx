"use client";
import "@/app/premium.css";
import { useTranslation } from "@/lib/i18n";


import { ArrowRight } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { config } from "@/lib/config";
import {
  cachedEntitlement,
  fetchEntitlement,
  type EntitlementState
} from "@/lib/entitlement";
import { authClient, useAppPick } from "@/lib/store";
import { capturePremiumAttribution, trackPremiumEvent, trackPremiumMilestone, TRIAL_INTENT_KEY } from "@/lib/premiumAnalytics";
import { currentEntitlement, entitlementCheckDelay } from "@/lib/entitlementPolicy";
import { EntitlementContext } from "@/lib/entitlementContext";
import { ENTITLEMENT_REFRESH_EVENT } from "@/lib/entitlement";
import { MembershipCheckout } from "./MembershipCheckout";
import { WebFeaturePreview } from "./WebFeaturePreview";
import { PremiumOffer } from "./PremiumOffer";
import { MemberNotices } from "./MemberNotices";
import { setPremiumGate, type PremiumIntent } from "@/lib/premiumGate";
import { clearStreamTicket, refreshStreamTicket } from "@/lib/streamTicket";
import { captureReferralCode, claimPendingReferral } from "@/lib/referral";

// Explore mode. Signed-in accounts use ARVIO Web freely; Premium is asked for
// only when they start something that plays (see lib/premiumGate.ts). A
// still-valid cached membership survives transient backend errors, and access
// ending mid-film never stops the film: the next play asks again.
export function EntitlementGate({ children }: { children: React.ReactNode }) {
  const { auth } = useAppPick("auth");
  const accountId = auth?.userId ?? null;
  const [state, setState] = useState<EntitlementState | null>(() => cachedEntitlement(authClient));
  const [status, setStatus] = useState<"loading" | "ready" | "error">(state ? "ready" : "loading");
  const [retry, setRetry] = useState(0);
  const [stateAccountId, setStateAccountId] = useState(accountId);
  const [offer, setOffer] = useState<{ intent: PremiumIntent; resume?: () => void } | null>(null);
  const lastVerifiedAt = useRef(Date.now());
  const lastRefreshAt = useRef(0);

  // Invite links opened while already signed in.
  useEffect(() => { if (config.paywallEnabled) captureReferralCode(); }, []);

  useEffect(() => {
    if (!config.paywallEnabled) return;
    const cached = cachedEntitlement(authClient);
    setStateAccountId(accountId);
    setState(cached);
    setOffer(null);
    clearStreamTicket();
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
          if (!next.entitled && scheduleRetry) {
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

  const access = stateAccountId === accountId ? currentEntitlement(state) : null;
  const entitledNow = Boolean(access?.entitled) && (status !== "error" || Date.now() - lastVerifiedAt.current < 6 * 60 * 60_000);
  const gate = useRef({ entitledNow, accountId });
  gate.current = { entitledNow, accountId };

  // Register the gate the store and details screen ask before anything plays.
  useEffect(() => {
    if (!config.paywallEnabled) return;
    setPremiumGate((intent, resume) => {
      if (gate.current.entitledNow) return true;
      setOffer({ intent, resume });
      if (gate.current.accountId) void trackPremiumEvent(authClient, "paywall_view", { entry: `explore_${intent}`, flow_version: "explore_v1" }, true);
      return false;
    });
    return () => setPremiumGate(null);
  }, []);

  // Paid while the offer was open (or the check finished): continue where the
  // member left off.
  useEffect(() => {
    if (!offer || !entitledNow) return;
    const resume = offer.resume;
    setOffer(null);
    // Fetch the stream ticket first (bounded), so the resumed playback's relay
    // URLs carry it.
    let done = false;
    const go = () => { if (!done) { done = true; resume?.(); } };
    const timer = setTimeout(go, 2500);
    void refreshStreamTicket(authClient).finally(() => { clearTimeout(timer); go(); });
  }, [entitledNow, offer]);

  // Premium stream tickets for the media relay, and invites to claim.
  useEffect(() => {
    if (!config.paywallEnabled || !accountId) return;
    if (!entitledNow) { clearStreamTicket(); return; }
    void refreshStreamTicket(authClient);
    // Tabs and home-screen apps stay open for days: keep the ticket fresh.
    const timer = window.setInterval(() => { void refreshStreamTicket(authClient); }, 6 * 60 * 60 * 1000);
    return () => window.clearInterval(timer);
  }, [accountId, entitledNow]);
  useEffect(() => {
    if (!config.paywallEnabled || !accountId || status !== "ready" || !access) return;
    void claimPendingReferral(authClient, Boolean(access.everPaid || access.entitled));
  }, [accountId, status, access?.everPaid, access?.entitled]);

  if (!config.paywallEnabled) return <>{children}</>;
  return <EntitlementContext.Provider value={access}>
    {children}
    <MemberNotices state={access} account={accountId} />
    {offer && accountId && <PremiumOffer
      intent={offer.intent}
      state={access}
      status={status}
      account={accountId}
      onClose={() => setOffer(null)}
      onRetry={() => setRetry((value) => value + 1)}
      onEntitled={(next) => {
        if (authClient.session?.userId !== accountId) return;
        lastVerifiedAt.current = Date.now(); setStateAccountId(accountId); setState(next); setStatus("ready");
      }}
    />}
  </EntitlementContext.Provider>;
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
  const expired = state?.source !== "trial" && (state?.reason === "expired" || state?.status === "cancelled");

  useEffect(() => {
    capturePremiumAttribution();
    captureReferralCode();
    // Old bookmarks and stored intents must never create a free entitlement.
    try { localStorage.removeItem(TRIAL_INTENT_KEY); } catch { /* storage is optional */ }
    if (!isSignedIn) return;
    void trackPremiumEvent(authClient, "paywall_view", { flow_version: "paid_only_v1" }, true);
    void trackPremiumMilestone(authClient, "account_connected");
  }, [isSignedIn, accountId]);

  // Shown to signed-out visitors only: signing in opens ARVIO Web in explore
  // mode, and Premium is offered when they press Play.
  return (
    <WebFeaturePreview isSignedIn={isSignedIn} expired={expired} onConnect={onConnect} onSignOut={onSignOut} checkout={
        isSignedIn ? <MembershipCheckout className="paywall-primary" entry="feature_preview" onEntitled={onEntitled} showEmailHint>
          {translateUi("Subscribe on Ko-fi")}
        </MembershipCheckout> : <button type="button" className="paywall-primary" onClick={onConnect}>
          {translateUi("Sign in to look around")} <ArrowRight size={15} />
        </button>
    } />
  );
}
