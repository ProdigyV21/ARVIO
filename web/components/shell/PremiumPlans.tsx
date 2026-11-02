"use client";
import "./premium-plans.css";

import { useEffect, useState, type ReactNode } from "react";
import { Smartphone } from "lucide-react";
import { useTranslation } from "@/lib/i18n";
import { kofiSubscribeUrl, kofiYearlyUrl } from "@/lib/entitlement";
import { isTvBrowser } from "@/lib/tvNav";

export const DISCORD_URL = "https://discord.gg/UavuEYMfQ4";
export const ANDROID_APP_URL = "https://arvio.tv/";

export type PremiumPlanId = "yearly" | "monthly";

/**
 * The one two-plan card used by the landing page and the Premium offer:
 * the 12-month pass and the monthly membership, with the refund promise.
 * `action` renders each plan's button (the offer's Ko-fi checkout); without
 * it the card is informational and the caller shows one shared button.
 */
export function PremiumPlans({ action, compact = false }: { action?: (plan: PremiumPlanId) => ReactNode; compact?: boolean }) {
  const translateUi = useTranslation();
  const yearly = Boolean(kofiYearlyUrl());
  return <div className={`premium-plans${compact ? " is-compact" : ""}`}>
    <div className={`premium-offer-plans${yearly ? "" : " single"}`}>
      {yearly && <div className="premium-offer-plan best">
        <p className="premium-offer-badge">{translateUi("Best value · save 44%")}</p>
        <p className="premium-offer-price"><strong>$20</strong> <span>{translateUi("/ 12 months")}</span></p>
        <p className="premium-offer-terms">{translateUi("One payment, no automatic renewal. About $1.67 a month.")}</p>
        {action?.("yearly")}
      </div>}
      <div className="premium-offer-plan">
        <p className="premium-offer-price"><strong>$2.99</strong> <span>{translateUi("/ month")}</span></p>
        <p className="premium-offer-terms">{translateUi("Monthly membership. Cancel anytime through Ko-fi.")}</p>
        {action?.("monthly")}
      </div>
    </div>
    <p className="premium-offer-refund">{translateUi("Doesn't work on your device? Message us within 7 days for a full refund.")} <a href={DISCORD_URL} target="_blank" rel="noopener noreferrer">Discord</a></p>
  </div>;
}

/** Smart-TV browsers: a remote can't type card details, so pay on a phone. */
export function isTvClassBrowser(): boolean {
  if (typeof window === "undefined") return false;
  if (isTvBrowser()) return true;
  try {
    return window.matchMedia("(hover: none)").matches && navigator.maxTouchPoints === 0 && window.innerWidth >= 960;
  } catch { return false; }
}

export function isAndroidBrowser(): boolean {
  return typeof navigator !== "undefined" && /Android/i.test(navigator.userAgent) && !isTvBrowser();
}

/** A QR code that opens the same Ko-fi checkout on a phone. */
export function PayOnPhone({ email }: { email?: string | null }) {
  const translateUi = useTranslation();
  const yearly = kofiYearlyUrl();
  const [plan, setPlan] = useState<PremiumPlanId>(yearly ? "yearly" : "monthly");
  const [qr, setQr] = useState("");
  const url = plan === "yearly" && yearly ? yearly : kofiSubscribeUrl();
  useEffect(() => {
    let active = true;
    void import("qrcode").then((QRCode) => QRCode.toDataURL(url, { margin: 1, width: 440 }))
      .then((dataUrl) => { if (active) setQr(dataUrl); }).catch(() => { if (active) setQr(""); });
    return () => { active = false; };
  }, [url]);
  return <section className="premium-pay-on-phone" aria-label={translateUi("Pay on your phone")}>
    <div className="premium-qr">{qr ? <img src={qr} width={220} height={220} alt={translateUi("QR code for the Ko-fi checkout")} /> : <span>{url.replace(/^https?:\/\//, "")}</span>}</div>
    <div>
      <p className="premium-pay-title"><Smartphone size={17} /> {translateUi("Pay on your phone")}</p>
      <p>{translateUi("Scan with your phone camera to open Ko-fi. This TV unlocks by itself after you pay.")}</p>
      {email && <p className="premium-pay-email">{translateUi("Use this email at checkout")} <strong>{email}</strong></p>}
      <p className="premium-pay-currency">{translateUi("Ko-fi charges in US dollars; your bank converts the amount automatically.")}</p>
      {yearly && <div className="premium-pay-plans" role="group" aria-label={translateUi("Plan")}>
        <button type="button" aria-pressed={plan === "yearly"} onClick={() => setPlan("yearly")}>{translateUi("12-month pass · $20")}</button>
        <button type="button" aria-pressed={plan === "monthly"} onClick={() => setPlan("monthly")}>{translateUi("Monthly · $2.99")}</button>
      </div>}
    </div>
  </section>;
}

/** Android visitors: the free Android app is the better fit on that device. */
export function AndroidAppNote() {
  const translateUi = useTranslation();
  const [android, setAndroid] = useState(false);
  useEffect(() => setAndroid(isAndroidBrowser()), []);
  if (!android) return null;
  return <p className="premium-android-note"><Smartphone size={16} />
    <span>{translateUi("On Android, the ARVIO app is free.")} <a href={ANDROID_APP_URL} target="_blank" rel="noopener noreferrer">{translateUi("Get the Android app")}</a></span>
  </p>;
}
