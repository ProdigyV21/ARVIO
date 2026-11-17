"use client";

import { useEffect, useId, useRef, useState } from "react";
import { ArrowRight, BadgeCheck, Check, Loader2, ShieldCheck, X } from "lucide-react";
import { authClient, useAppPick } from "@/lib/store";
import { config } from "@/lib/config";
import { cachedEntitlement, fetchEntitlement, kofiYearlyUrl, type EntitlementState } from "@/lib/entitlement";
import { capturePremiumAttribution } from "@/lib/premiumAnalytics";
import { capturePremiumPurchaseIntent, clearPremiumPurchaseIntent, consumePremiumPurchaseIntent, type PremiumPurchasePlan } from "@/lib/premiumPurchaseIntent";
import { premiumPurchaseOffer } from "@/lib/premiumPurchaseOffer";
import { loadVisitorCountry, useRegionalYearly } from "@/lib/regionalPricing";
import { useTranslation } from "@/lib/i18n";
import { MembershipCheckout } from "./MembershipCheckout";
import { DISCORD_URL } from "./PremiumPlans";
import "./premium-purchase-intent.css";

/** Marketing plan links finish account pairing before opening any payment page. */
export function PremiumPurchaseIntent() {
  const { auth } = useAppPick("auth");
  return <PurchaseIntentSession key={auth?.userId ?? "signed-out"} />;
}

function PurchaseIntentSession() {
  const { auth, goToLogin } = useAppPick("auth", "goToLogin");
  const [plan, setPlan] = useState<PremiumPurchasePlan | null>(null);
  const captured = useRef(false);
  useEffect(() => {
    if (captured.current || !config.paywallEnabled || config.selfHosted) return;
    captured.current = true;
    // Save acquisition before either the sign-in redirect or intent URL cleanup.
    capturePremiumAttribution();
    const pending = capturePremiumPurchaseIntent();
    if (!pending) return;
    if (auth) { setPlan(consumePremiumPurchaseIntent()); return; }
    // The website already showed this plan and the sign-in next step. Keep the
    // selection for the auth round trip instead of requesting that click again.
    try { goToLogin(); }
    catch { setPlan(pending.plan); } // A blocked redirect keeps a manual retry.
  }, [auth, goToLogin]);
  if (!plan) return null;
  return <PremiumPurchaseConfirmation key={plan} plan={plan} onSignIn={goToLogin} onClose={() => {
    clearPremiumPurchaseIntent(); setPlan(null);
  }} />;
}

/** Also used by the isolated payment fixture: identity and transport stay mocked. */
export function PremiumPurchaseConfirmation({ plan, onSignIn, onClose }: {
  plan: PremiumPurchasePlan; onSignIn: () => void; onClose: () => void;
}) {
  const translateUi = useTranslation();
  const { auth } = useAppPick("auth");
  const accountId = auth?.userId;
  const regional = useRegionalYearly();
  const [priceReady, setPriceReady] = useState(plan === "monthly");
  const [state, setState] = useState<EntitlementState | null>(null);
  const [status, setStatus] = useState<"loading" | "ready" | "error">(accountId ? "loading" : "ready");
  const [attempt, setAttempt] = useState(0);
  const dialog = useRef<HTMLDialogElement>(null);
  const titleId = useId();
  const offer = premiumPurchaseOffer(plan, regional, state);
  const yearlyUrl = kofiYearlyUrl();
  const unavailable = plan === "yearly" && !yearlyUrl;

  useEffect(() => {
    if (plan !== "yearly") return;
    let active = true;
    // A failed or unusually slow geo lookup falls back to the standard offer.
    // The displayed total and destination always re-render together if it arrives later.
    const timer = window.setTimeout(() => { if (active) setPriceReady(true); }, 6000);
    void loadVisitorCountry().then(() => { if (active) setPriceReady(true); });
    return () => { active = false; window.clearTimeout(timer); };
  }, [plan]);

  useEffect(() => {
    const element = dialog.current;
    const previousFocus = document.activeElement;
    element?.showModal();
    element?.querySelector<HTMLElement>("[data-purchase-focus]")?.focus({ preventScroll: true });
    return () => {
      element?.close();
      if (previousFocus instanceof HTMLElement && previousFocus.isConnected) previousFocus.focus({ preventScroll: true });
    };
  }, []);

  useEffect(() => {
    if (status !== "ready" || (!offer.active && !priceReady)) return;
    // The payment action mounts after the access/price checks, so a keyboard
    // or TV remote lands on the next step rather than the close button.
    dialog.current?.querySelector<HTMLElement>(".paywall-primary")?.focus({ preventScroll: true });
  }, [status, priceReady, offer.active]);

  useEffect(() => {
    if (!accountId) { setState(null); setStatus("ready"); return; }
    let current = true;
    const cached = cachedEntitlement(authClient);
    setState(cached);
    setStatus(cached?.entitled ? "ready" : "loading");
    void fetchEntitlement(authClient).then(next => {
      if (!current || authClient.session?.userId !== accountId) return;
      setState(next); setStatus("ready");
    }).catch(() => {
      if (current && authClient.session?.userId === accountId && !cached?.entitled) setStatus("error");
    });
    return () => { current = false; };
  }, [accountId, attempt]);

  return <dialog ref={dialog} className="premium-purchase" aria-labelledby={titleId}
    onCancel={event => { event.preventDefault(); onClose(); }}
    onKeyDown={event => { if (event.key === "Escape") { event.preventDefault(); event.stopPropagation(); onClose(); } }}>
    <button type="button" className="premium-purchase-close" aria-label={translateUi("Close")} onClick={onClose}><X size={20} /></button>
    <div className="premium-purchase-brand"><img src="/arvio-logo.svg" alt="" width={32} height={32} /><span>ARVIO <strong>Premium</strong></span></div>
    {offer.active ? <>
      <div className="premium-purchase-success"><BadgeCheck size={30} /></div>
      <h2 id={titleId}>{translateUi("Your Premium membership is active.")}</h2>
      <p className="premium-purchase-lead">{translateUi("You already have access. There is no need to pay again.")}</p>
      <div className="premium-purchase-account"><span>{translateUi("Your ARVIO account")}</span><strong>{auth?.email}</strong></div>
      <button type="button" className="paywall-primary" data-purchase-focus onClick={onClose}>{translateUi("Open ARVIO Web")} <ArrowRight size={17} /></button>
    </> : <>
      <ol className="premium-purchase-steps" aria-label={translateUi("Membership steps")}>
        <li aria-current={!auth ? "step" : undefined}>{auth ? <Check size={13} /> : <span>1</span>}{translateUi("Account")}</li>
        <li aria-current={auth ? "step" : undefined}><span>2</span>{translateUi("Payment")}</li>
        <li><span>3</span>{translateUi("Ready")}</li>
      </ol>
      <h2 id={titleId}>{translateUi(auth ? "Finish your membership" : "Your selected Premium plan")}</h2>
      <p className="premium-purchase-lead">{translateUi(auth ? "Your plan is ready. Complete payment securely on Ko-fi, then return here." : "Sign in or create your ARVIO account, then complete payment on Ko-fi.")}</p>
      <section className="premium-purchase-plan" aria-label={translateUi("Your selected plan")}>
        <span className="premium-purchase-plan-name">{translateUi(plan === "yearly" ? "12-month pass" : "Monthly membership")}</span>
        {!priceReady ? <p className="premium-purchase-checking" role="status"><Loader2 size={17} className="paywall-spinner" />{translateUi("Checking your price…")}</p> : <>
          <p className="premium-purchase-total"><strong>{offer.total}</strong><span>{translateUi(offer.period)}</span><small>USD</small></p>
          <p className="premium-purchase-terms">{translateUi(plan === "yearly" ? "One payment, no automatic renewal. About {value0} a month." : "Monthly membership. Cancel anytime through Ko-fi.", { value0: offer.monthlyEquivalent ?? "" })}</p>
          {offer.saving && <p className="premium-purchase-saving">{translateUi("Save {value0} vs. 12 monthly payments of $2.99 ($35.88).", { value0: offer.saving })}</p>}
        </>}
        <p className="premium-purchase-benefits">{translateUi("24/7 hosted ARVIO Web for your supported browsers.")}</p>
      </section>
      {auth && <div className="premium-purchase-account"><span>{translateUi("Your ARVIO account")}</span><strong>{auth.email}</strong></div>}
      {status === "loading" && <p className="premium-purchase-checking" role="status"><Loader2 size={17} className="paywall-spinner" />{translateUi("Checking your membership…")}</p>}
      {status === "error" ? <>
        <p className="premium-purchase-error" role="status">{translateUi("Could not check access. Your payment status has not changed; please retry.")}</p>
        <button type="button" className="paywall-primary" data-purchase-focus onClick={() => { setStatus("loading"); setAttempt(value => value + 1); }}>{translateUi("Check payment status")}</button>
      </> : unavailable ? <p role="status">{translateUi("This plan is unavailable right now. Please try again shortly.")}</p>
        : !auth ? <button type="button" className="paywall-primary" data-purchase-focus disabled={!priceReady} onClick={onSignIn}>{translateUi("Sign in to continue")} <ArrowRight size={17} /></button>
          : status === "ready" && priceReady && <MembershipCheckout entry={`website_${plan}`} className="paywall-primary" href={plan === "yearly" ? yearlyUrl : undefined} showEmailHint
            onEntitled={next => { if (authClient.session?.userId === accountId) setState(next); }}>
            {translateUi("Continue to Ko-fi · {value0}", { value0: offer.total })} <ArrowRight size={17} />
          </MembershipCheckout>}
      <p className="premium-purchase-refund"><ShieldCheck size={15} /><span>{translateUi("Doesn't work on your device? Message us within 7 days for a full refund.")} <a href={DISCORD_URL} target="_blank" rel="noopener noreferrer">Discord</a></span></p>
      <button type="button" className="premium-purchase-later" onClick={onClose}>{translateUi("Keep exploring")}</button>
    </>}
  </dialog>;
}
