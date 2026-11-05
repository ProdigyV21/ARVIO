"use client";
import "@/app/premium.css";
import { useRegionalYearly } from "@/lib/regionalPricing";

import { useEffect, useId, useRef, useState } from "react";
import { BadgeCheck, ExternalLink, Loader2, RefreshCw, X } from "lucide-react";
import { useTranslation } from "@/lib/i18n";
import { authClient, useAppPick } from "@/lib/store";
import { EXIT_REASONS, fetchEntitlement, kofiYearlyUrl, submitExitSurvey, type EntitlementState, type ExitReason } from "@/lib/entitlement";
import type { PremiumIntent } from "@/lib/premiumGate";
import { config } from "@/lib/config";
import { MembershipCheckout } from "./MembershipCheckout";
import { AndroidAppNote, isTvClassBrowser, PayOnPhone, PremiumPlans } from "./PremiumPlans";

export function deviceName(userAgent = typeof navigator === "undefined" ? "" : navigator.userAgent, touchPoints = typeof navigator === "undefined" ? 0 : navigator.maxTouchPoints): string {
  if (/\b(tizen|web0s|webos|smart-?tv|netcast|hbbtv)\b/i.test(userAgent)) return "TV";
  if (/iPhone|iPod/i.test(userAgent)) return "iPhone";
  if (/iPad/i.test(userAgent) || (/Macintosh/i.test(userAgent) && touchPoints > 1)) return "iPad";
  if (/Macintosh|Mac OS X/i.test(userAgent)) return "Mac";
  if (/Android/i.test(userAgent)) return "phone";
  if (/Windows|Linux|CrOS/i.test(userAgent)) return "computer";
  return "device";
}

// Literal phrases so the translation tooling can find them.
function headline(intent: PremiumIntent): string {
  if (intent === "live") return "Watch Live TV on your {device}";
  if (intent === "download") return "Download with ARVIO Premium";
  if (intent === "external" || intent === "copy") return "Open streams with ARVIO Premium";
  return "Watch this on your {device}";
}

const DEVICE_LABELS: Record<string, string> = { TV: "TV", iPhone: "iPhone", iPad: "iPad", Mac: "Mac", phone: "phone", computer: "computer", device: "device" };

const REASON_LABELS: Record<ExitReason, string> = {
  too_expensive: "Too expensive",
  playback_problems: "Playback didn't work well",
  missing_feature: "A feature I needed was missing",
  only_needed_briefly: "I only needed it for a while",
  use_android_instead: "I use the Android app instead",
  other: "Something else"
};

function surveyKey(state: EntitlementState | null, account: string | null) {
  return account ? `arvio.exitsurvey:${account}:${state?.expiresAt ?? ""}` : null;
}

// Asked once per lapsed membership: why did you stop? Shown with the member
// notices, never inside the offer that blocks playback.
export function ExitSurvey({ state, account, notice = false }: { state: EntitlementState | null; account: string | null; notice?: boolean }) {
  const translateUi = useTranslation();
  const key = surveyKey(state, account);
  const [done, setDone] = useState(() => { try { return !key || Boolean(localStorage.getItem(key)); } catch { return true; } });
  const [reason, setReason] = useState<ExitReason | null>(null);
  const [detail, setDetail] = useState("");
  const [sending, setSending] = useState(false);
  const id = useId();
  if (done) return null;
  const finish = () => { try { if (key) localStorage.setItem(key, "1"); } catch { /* optional */ } setDone(true); };
  return <form className={`premium-exit-survey${notice ? " member-notice" : ""}`} aria-labelledby={`${id}-q`} onSubmit={async (event) => {
    event.preventDefault();
    if (!reason) return;
    setSending(true);
    await submitExitSurvey(authClient, reason, detail).catch(() => null);
    setSending(false);
    finish();
  }}>
    {notice && <button type="button" className="member-notice-close" aria-label={translateUi("Close")} onClick={finish}><X size={16} /></button>}
    <p id={`${id}-q`} className="premium-exit-question">{translateUi("Your membership ended. What made you stop?")}</p>
    <div className="premium-exit-reasons" role="radiogroup" aria-labelledby={`${id}-q`}>
      {EXIT_REASONS.map((value) => <button type="button" key={value} role="radio" aria-checked={reason === value} onClick={() => setReason(value)}>{translateUi(REASON_LABELS[value])}</button>)}
    </div>
    {reason && <>
      <label className="premium-exit-detail"><span>{translateUi("Anything else? (optional)")}</span>
        <textarea value={detail} maxLength={300} rows={2} onChange={(event) => setDetail(event.target.value)} />
      </label>
      <div className="premium-exit-actions">
        <button type="submit" className="paywall-trial" disabled={sending}>{sending ? <Loader2 size={15} className="paywall-spinner" /> : null}{translateUi("Send")}</button>
        <button type="button" className="paywall-link-toggle" onClick={finish}>{translateUi("Skip")}</button>
      </div>
    </>}
  </form>;
}

export function PremiumOffer({ intent, state, status, account, onClose, onRetry, onEntitled }: {
  intent: PremiumIntent;
  state: EntitlementState | null;
  status: "loading" | "ready" | "error";
  account: string | null;
  onClose: () => void;
  onRetry: () => void;
  onEntitled: (next: EntitlementState) => void;
}) {
  const translateUi = useTranslation();
  const { auth } = useAppPick("auth");
  const dialog = useRef<HTMLDialogElement>(null);
  const id = useId();
  useRegionalYearly(); // re-render with the regional link once the country is known
  const yearly = kofiYearlyUrl();
  const device = deviceName();
  const lapsed = Boolean(state?.everPaid) && !state?.entitled;
  const [tv] = useState(() => isTvClassBrowser());
  const entitled = useRef(onEntitled);
  entitled.current = onEntitled;
  const ready = !(status === "loading" && !state) && status !== "error";

  useEffect(() => {
    const element = dialog.current;
    if (element && !element.open) element.showModal();
    return () => element?.close();
  }, []);
  // Remote users start on the main button, not the close button.
  useEffect(() => {
    const focusPrimary = () => dialog.current?.querySelector<HTMLElement>(".premium-pay-plans button, .premium-offer-plan .paywall-primary, [role='alert'] .paywall-primary")?.focus({ preventScroll: true });
    focusPrimary();
    // showModal() may move focus to the dialog itself after this effect.
    const frame = requestAnimationFrame(focusPrimary);
    return () => cancelAnimationFrame(frame);
  }, [status, ready]);
  // Paying on a phone (QR on a TV): this screen unlocks by itself.
  useEffect(() => {
    if (!tv || !account) return undefined;
    let stopped = false;
    const started = Date.now();
    const timer = window.setInterval(() => {
      if (stopped || Date.now() - started > 20 * 60_000) { window.clearInterval(timer); return; }
      void fetchEntitlement(authClient).then((next) => {
        if (!stopped && authClient.session?.userId === account && next.entitled && next.reason === "subscription") entitled.current(next);
      }).catch(() => undefined);
    }, 10_000);
    return () => { stopped = true; window.clearInterval(timer); };
  }, [tv, account]);

  return <dialog ref={dialog} className="premium-offer" aria-labelledby={`${id}-title`}
    onCancel={(event) => { event.preventDefault(); onClose(); }}
    // TV Back arrives as a synthetic Escape, which never fires "cancel".
    onKeyDown={(event) => { if (event.key === "Escape" && !event.defaultPrevented && !(event.target as HTMLElement).closest(".membership-checkout")) { event.preventDefault(); onClose(); } }}>
    <button type="button" className="membership-close" aria-label={translateUi("Close")} onClick={onClose}><X size={20} /></button>
    {status === "loading" && !state ? <div className="premium-offer-checking" role="status">
      <Loader2 size={28} className="paywall-spinner" />
      <p>{translateUi("Checking your membership…")}</p>
    </div> : status === "error" ? <div role="alert">
      <h2 id={`${id}-title`}>{translateUi("We could not check your membership")}</h2>
      <p>{translateUi("Your payment status has not changed. Please retry before subscribing again.")}</p>
      <button type="button" className="paywall-primary" onClick={onRetry}><RefreshCw size={16} /> {translateUi("Check access again")}</button>
    </div> : <>
      <p className="premium-offer-eyebrow"><BadgeCheck size={15} /> ARVIO PREMIUM</p>
      <h2 id={`${id}-title`}>{translateUi(headline(intent), { device: translateUi(DEVICE_LABELS[device] ?? "device") })}</h2>
      <p className="premium-offer-lead">{lapsed ? translateUi("Welcome back. Everything you set up is still here.") : translateUi("Everything you set up stays. Premium unlocks playback, Live TV and downloads in ARVIO Web on every browser you sign in to.")}</p>
      {tv && <PayOnPhone email={auth?.email} />}
      <PremiumPlans action={(plan) => plan === "yearly"
        ? <MembershipCheckout className="paywall-primary" entry={`explore_${intent}_yearly`} href={yearly} onEntitled={onEntitled} showEmailHint>
            {translateUi("Get the 12-month pass")} <ExternalLink size={15} />
          </MembershipCheckout>
        : <MembershipCheckout className={yearly ? "paywall-trial" : "paywall-primary"} entry={`explore_${intent}_monthly`} onEntitled={onEntitled} showEmailHint>
            {translateUi("Subscribe monthly")} <ExternalLink size={15} />
          </MembershipCheckout>} />
      <AndroidAppNote />
      {/* Members who left already know the app: the video is for newcomers. */}
      {!lapsed && !tv && config.promoVideoUrl && <video className="premium-offer-video" src={config.promoVideoUrl} poster={config.promoPosterUrl || undefined} muted playsInline controls preload="none" />}
      <p className="premium-offer-note">{translateUi("The Android and TV app stays free. Membership does not include media or subscriptions to other services.")}</p>
    </>}
  </dialog>;
}
