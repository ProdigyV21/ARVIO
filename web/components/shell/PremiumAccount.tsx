"use client";
import { useTranslation } from "@/lib/i18n";
import { REGIONAL_YEARLY_PRICE, STANDARD_YEARLY_PRICE, useRegionalYearly } from "@/lib/regionalPricing";

import { BadgeCheck, Copy, ExternalLink, Gift } from "lucide-react";
import { useEffect, useState } from "react";
import { authClient, useAppPick } from "@/lib/store";
import { useEntitlement } from "@/lib/entitlementContext";
import { ENTITLEMENT_REFRESH_EVENT, fetchReferralStatus, kofiYearlyUrl, type EntitlementState, type ReferralStatus } from "@/lib/entitlement";
import { accessEndsAt } from "@/lib/entitlementPolicy";
import { config } from "@/lib/config";
import { WHATS_NEW } from "@/lib/whatsNew";
import { MembershipCheckout } from "./MembershipCheckout";

// "Give a month, get a month": the account's invite link.
function InviteFriends() {
  const translateUi = useTranslation();
  const { auth } = useAppPick("auth");
  const [status, setStatus] = useState<ReferralStatus | null>(null);
  const [failed, setFailed] = useState(false);
  const [copied, setCopied] = useState(false);
  useEffect(() => {
    if (!auth) return;
    let active = true;
    setStatus(null); setFailed(false);
    fetchReferralStatus(authClient).then((next) => { if (active) setStatus(next); }).catch(() => { if (active) setFailed(true); });
    return () => { active = false; };
  }, [auth?.userId]);
  if (failed) return null;
  return <div className="premium-invite">
    <h3><Gift size={17} /> {translateUi("Give a month, get a month")}</h3>
    <p>{translateUi("Share your link. When a friend joins Premium, you both get 31 extra days.")}</p>
    {status ? <>
      <div className="premium-invite-link">
        <input readOnly value={status.link} aria-label={translateUi("Your invite link")} onFocus={(event) => event.currentTarget.select()} />
        <button type="button" className="paywall-trial" onClick={async () => {
          try {
            if (navigator.share && /iPhone|iPad|Android/i.test(navigator.userAgent)) await navigator.share({ title: "ARVIO", url: status.link });
            else { await navigator.clipboard.writeText(status.link); setCopied(true); }
          } catch { /* share sheet closed */ }
        }}><Copy size={15} /> {translateUi(copied ? "Copied" : "Share link")}</button>
      </div>
      <p className="premium-invite-stats">{translateUi("{joined} joined · {left} free months left this year", { joined: status.joined, left: status.rewardsLeftThisYear })}</p>
    </> : <p className="premium-invite-stats">{translateUi("Loading your link…")}</p>}
  </div>;
}

export function PremiumAccount() {
  const translateUi = useTranslation();
  const { auth } = useAppPick("auth");
  const entitlement = useEntitlement();
  const [state, setState] = useState(entitlement);
  const [message, setMessage] = useState("");
  useEffect(() => { setState(entitlement); setMessage(""); }, [auth?.userId, entitlement]);
  const regional = useRegionalYearly();
  if (!config.paywallEnabled || !auth) return null;
  const paid = Boolean(state?.entitled);
  const yearly = kofiYearlyUrl();
  const yearlyPrice = regional ? REGIONAL_YEARLY_PRICE : STANDARD_YEARLY_PRICE;
  const end = accessEndsAt(state);
  const applied = (next: EntitlementState) => {
    if (!next.entitled || next.reason !== "subscription" || next.source === "trial") return;
    setState(next);
    setMessage("Your Premium membership is active.");
    window.dispatchEvent(new Event(ENTITLEMENT_REFRESH_EVENT));
  };
  return <section className="premium-account" aria-labelledby="premium-account-title">
    <h2 id="premium-account-title"><BadgeCheck size={20} /> {translateUi(" ARVIO Web Premium")}</h2>
    <p className="premium-account-status">{paid ? translateUi(state?.plan === "yearly" ? "12-month pass active" : "Membership active") : translateUi("Not a member yet")}</p>
    <p className="premium-account-email">{auth.email}</p>
    {state?.reason === "grace" && state.graceEndsAt && <p>{translateUi("Your renewal hasn't come through yet. Access continues until {date}.", { date: new Date(state.graceEndsAt).toLocaleDateString() })}</p>}
    {paid && end && <p>{translateUi("Access until")}: {new Date(end).toLocaleString()}</p>}
    <p>{translateUi("Browser access on iPhone, iPad, Windows, Mac and Linux. The Android app and Cloud dashboard remain free.")}</p>
    <div className="premium-account-actions">
      {yearly && state?.plan !== "yearly" && <MembershipCheckout className="paywall-primary" entry="account_settings_yearly" href={yearly} onEntitled={applied} activeWhen={paid ? (next) => next.plan === "yearly" : undefined}>
        {translateUi("Get the 12-month pass")} · {yearlyPrice} <ExternalLink size={15} />
      </MembershipCheckout>}
      {!paid && <MembershipCheckout className={yearly ? "paywall-trial" : "paywall-primary"} entry="account_settings" onEntitled={applied} showEmailHint>
        {translateUi("Keep Premium - $2.99/month ")}<ExternalLink size={15} />
      </MembershipCheckout>}
    </div>
    {paid && yearly && state?.plan !== "yearly" && <p className="premium-account-note">{translateUi("Your remaining time is added on top. After buying the pass, cancel your monthly membership on Ko-fi.")}</p>}
    {message && <p role="status">{translateUi(message)}</p>}
    <InviteFriends />
    <div className="premium-whats-new">
      <h3>{translateUi("What's new in Premium")}</h3>
      <ul>{WHATS_NEW.slice(0, 3).map((entry) => <li key={entry.id}><strong>{translateUi(entry.title)}</strong> {translateUi(entry.body)}</li>)}</ul>
    </div>
  </section>;
}
