"use client";
import { useTranslation } from "@/lib/i18n";


import { BadgeCheck, ExternalLink } from "lucide-react";
import { useEffect, useState } from "react";
import { useApp } from "@/lib/store";
import { useEntitlement } from "@/lib/entitlementContext";
import { ENTITLEMENT_REFRESH_EVENT, type EntitlementState } from "@/lib/entitlement";
import { config } from "@/lib/config";
import { requestSourceSettings, sourceSetupState } from "@/lib/sourceSetup";
import { MembershipCheckout } from "./MembershipCheckout";

export function PremiumAccount() {
  const translateUi = useTranslation();
  const { auth, addons, addonsReady, settings, setSection } = useApp();
  const entitlement = useEntitlement();
  const [state, setState] = useState(entitlement);
  const [message, setMessage] = useState("");
  useEffect(() => { setState(entitlement); setMessage(""); }, [auth?.userId, entitlement]);
  if (!config.paywallEnabled || !auth) return null;
  const trial = state?.reason === "trial";
  const paid = state?.entitled && state.reason === "subscription";
  const applied = (next: EntitlementState) => {
    setState(next);
    setMessage(next.reason === "subscription" ? "Your Premium membership is active." : "Your trial is active. If you have paid, check your billing email below; do not pay again.");
    window.dispatchEvent(new Event(ENTITLEMENT_REFRESH_EVENT));
  };
  return <section className="premium-account" aria-labelledby="premium-account-title">
    <h2 id="premium-account-title"><BadgeCheck size={20} /> {translateUi(" ARVIO Web Premium")}</h2>
    <p className="premium-account-status">{paid ? translateUi("Membership active") : trial ? translateUi("Free trial active") : translateUi("Check membership")}</p>
    <p className="premium-account-email">{auth.email}</p>
    {state?.expiresAt && <p>{trial ? translateUi("Trial ends") : translateUi("Access until")}: {new Date(state.expiresAt).toLocaleString()}</p>}
    <p>{translateUi("Browser access on iPhone, iPad, Windows, Mac and Linux. The Android app and Cloud dashboard remain free.")}</p>
    {trial && <div className="premium-trial-next">
      <h3>{translateUi("Make the most of your trial")}</h3>
      <p>{translateUi(addonsReady && sourceSetupState(addons, settings).hasAny ? "A media source is configured. Try a film or episode, including audio and subtitles, before subscribing." : "First connect your own media source. Catalogs alone do not provide playback.")}</p>
      <div className="premium-account-actions">
        <button type="button" className="paywall-trial" onClick={() => setSection("addons")}>{translateUi("Addons")}</button>
        <button type="button" className="paywall-trial" onClick={() => { requestSourceSettings("homeserver"); setSection("settings"); }}>{translateUi("Home Server")}</button>
      </div>
      <p>{translateUi("Your trial ends automatically. You will not be charged unless you subscribe on Ko-fi.")}</p>
    </div>}
    <div className="premium-account-actions">
      {!paid && <MembershipCheckout className="paywall-primary" entry="account_settings" onEntitled={applied} showEmailHint>
        {translateUi("Keep Premium - $2.99/month ")}<ExternalLink size={15} />
      </MembershipCheckout>}
    </div>
    {message && <p role="status">{translateUi(message)}</p>}
  </section>;
}
