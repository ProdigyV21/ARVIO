"use client";
import "@/app/premium.css";

import { useEffect, useState } from "react";
import { AlertTriangle, Check, Sparkles, X } from "lucide-react";
import { useTranslation } from "@/lib/i18n";
import { useAppPick } from "@/lib/store";
import type { EntitlementState } from "@/lib/entitlement";
import { kofiSubscribeUrl } from "@/lib/entitlement";
import { markWhatsNewSeen, unseenWhatsNew } from "@/lib/whatsNew";
import { ExitSurvey } from "./PremiumOffer";

const CHECKLIST_DAYS = 14;

function stored(key: string) { try { return localStorage.getItem(key); } catch { return null; } }
function remember(key: string, value = "1") { try { localStorage.setItem(key, value); } catch { /* optional */ } }

function isAppleMobileSafari() {
  if (typeof navigator === "undefined") return false;
  return /iPhone|iPad|iPod/i.test(navigator.userAgent) || (/Macintosh/i.test(navigator.userAgent) && navigator.maxTouchPoints > 1);
}

function isStandalone() {
  if (typeof window === "undefined") return false;
  return window.matchMedia?.("(display-mode: standalone)").matches || (navigator as Navigator & { standalone?: boolean }).standalone === true;
}

// The first days after paying: three steps that make ARVIO Web work well.
function MemberChecklist({ state, account }: { state: EntitlementState; account: string }) {
  const translateUi = useTranslation();
  const { addons, settings, setSection } = useAppPick("addons", "settings", "setSection");
  const key = `arvio.member.checklist:${account}`;
  const [hidden, setHidden] = useState(() => Boolean(stored(key)));
  const started = Date.parse(state.startedAt ?? "");
  const recent = Number.isFinite(started) && Date.now() - started < CHECKLIST_DAYS * 24 * 60 * 60 * 1000;
  const steps = [
    { id: "source", label: "Connect a source", done: addons.length > 0 || (settings.homeServers?.length ?? 0) > 0 || (settings.iptvPlaylists?.length ?? 0) > 0, action: () => setSection("settings") },
    ...(isAppleMobileSafari() ? [{ id: "home", label: "Add ARVIO to your Home Screen (Share, then Add to Home Screen)", done: isStandalone(), action: undefined }] : []),
    { id: "subtitles", label: "Pick your subtitle language", done: Boolean(settings.defaultSubtitle && settings.defaultSubtitle !== "Off"), action: () => setSection("settings") }
  ];
  if (hidden || !recent || steps.every((step) => step.done)) return null;
  return <aside className="member-notice" aria-labelledby="member-checklist-title">
    <button type="button" className="member-notice-close" aria-label={translateUi("Close")} onClick={() => { remember(key); setHidden(true); }}><X size={16} /></button>
    <p id="member-checklist-title" className="member-notice-title"><Sparkles size={15} /> {translateUi("Welcome to ARVIO Premium")}</p>
    <ul className="member-checklist">
      {steps.map((step) => <li key={step.id} data-done={step.done}>
        <span className="member-check" aria-hidden="true">{step.done ? <Check size={13} /> : null}</span>
        {step.action && !step.done ? <button type="button" onClick={step.action}>{translateUi(step.label)}</button> : <span>{translateUi(step.label)}</span>}
      </li>)}
    </ul>
  </aside>;
}

function WhatsNewNote() {
  const translateUi = useTranslation();
  const [entry, setEntry] = useState(() => unseenWhatsNew());
  if (!entry) return null;
  return <aside className="member-notice" aria-labelledby="whats-new-title">
    <button type="button" className="member-notice-close" aria-label={translateUi("Close")} onClick={() => { markWhatsNewSeen(entry.id); setEntry(null); }}><X size={16} /></button>
    <p id="whats-new-title" className="member-notice-title"><Sparkles size={15} /> {translateUi("New in Premium")}: {translateUi(entry.title)}</p>
    <p className="member-notice-body">{translateUi(entry.body)}</p>
  </aside>;
}

// A renewal that has not come through yet: access continues, but say so.
function GraceBanner({ state }: { state: EntitlementState }) {
  const translateUi = useTranslation();
  const [hidden, setHidden] = useState(false);
  if (hidden || state.reason !== "grace" || !state.graceEndsAt) return null;
  const until = new Date(state.graceEndsAt).toLocaleDateString(undefined, { day: "numeric", month: "long" });
  return <aside className="member-notice member-grace" role="status">
    <button type="button" className="member-notice-close" aria-label={translateUi("Close")} onClick={() => setHidden(true)}><X size={16} /></button>
    <p className="member-notice-title"><AlertTriangle size={15} /> {translateUi("Payment not received yet")}</p>
    <p className="member-notice-body">{translateUi("Your renewal hasn't come through yet. Access continues until {date}.", { date: until })} <a href={kofiSubscribeUrl()} target="_blank" rel="noopener noreferrer">{translateUi("Check Ko-fi")}</a></p>
  </aside>;
}

/** Notices for members: the grace banner, the first-days checklist, or
 *  otherwise a short "What's new in Premium" note. Never shown while a video
 *  plays. */
export function MemberNotices({ state, account }: { state: EntitlementState | null; account: string | null }) {
  const { activeStream } = useAppPick("activeStream");
  const [mounted, setMounted] = useState(false);
  useEffect(() => setMounted(true), []);
  if (!mounted || !state || !account || activeStream) return null;
  // A lapsed member is asked once why they stopped — here, not in the offer
  // that appears when they press Play.
  if (!state.entitled) return state.everPaid ? <ExitSurvey state={state} account={account} notice /> : null;
  const started = Date.parse(state.startedAt ?? "");
  const newMember = Number.isFinite(started) && Date.now() - started < CHECKLIST_DAYS * 24 * 60 * 60 * 1000;
  // One card at a time: a missing payment first, then the welcome checklist,
  // otherwise what's new.
  if (state.reason === "grace") return <GraceBanner state={state} />;
  return newMember ? <MemberChecklist state={state} account={account} /> : <WhatsNewNote />;
}
