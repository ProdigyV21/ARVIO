"use client";
import "./sportsReminder.css";
import { useEffect, useRef, useState } from "react";
import { BellRing } from "lucide-react";
import { authClient, useAppPick } from "@/lib/store";
import { useTranslation } from "@/lib/i18n";
import { useSportsPreferences } from "@/lib/sportsPreferences";
import { dueReminder, nextReminderAt, SPORTS_REMINDER_COUNTDOWN_S, type SportsReminder } from "@/lib/sportsFollows";

/**
 * A minute before a reminded game starts: a 10 s countdown, then the game's
 * channel plays. Cancel (or Escape/Back) stops it. Focus starts on Cancel, so
 * a stray OK on a TV remote never switches early.
 */
export function SportsReminderHost() {
  const translateUi = useTranslation();
  const { iptvSnapshot, playChannel, setSection, activeProfile } = useAppPick("iptvSnapshot", "playChannel", "setSection", "activeProfile");
  const { reminders, updateReminders } = useSportsPreferences(authClient, activeProfile?.id);
  const [handled, setHandled] = useState<ReadonlySet<string>>(() => new Set());
  const [due, setDue] = useState<SportsReminder | undefined>();
  useEffect(() => {
    let timer: number | undefined;
    const check = () => {
      const now = Date.now();
      const reminder = dueReminder(reminders, now, handled);
      setDue(current => current?.eventId === reminder?.eventId ? current : reminder);
      if (reminder) return;
      const next = nextReminderAt(reminders, now, handled);
      if (next !== undefined) timer = window.setTimeout(check, Math.min(30_000, Math.max(500, next - now)));
    };
    check();
    return () => window.clearTimeout(timer);
  }, [reminders, handled]);
  const [remaining, setRemaining] = useState(SPORTS_REMINDER_COUNTDOWN_S);
  const cancelButton = useRef<HTMLButtonElement>(null);
  const finish = (reminder: SportsReminder) => {
    setHandled(current => new Set([...current, reminder.eventId]));
    setDue(undefined);
    updateReminders(current => current.map(r => r.eventId === reminder.eventId ? { ...r, dismissed: true } : r));
  };
  const switchTo = (reminder: SportsReminder) => {
    const channels = iptvSnapshot.allChannels ?? iptvSnapshot.channels;
    const channel = reminder.channelIds.map(id => channels.find(ch => ch.id === id)).find(Boolean);
    if (channel) playChannel(channel);
    else setSection("tv");
    finish(reminder);
  };
  const hasChannel = Boolean(due?.channelIds.length);
  useEffect(() => {
    if (!due) return;
    setRemaining(SPORTS_REMINDER_COUNTDOWN_S);
    const active = document.activeElement;
    // Do not take focus from someone typing.
    if (!active || active === document.body || active instanceof HTMLButtonElement) cancelButton.current?.focus({ preventScroll: true });
    const onKey = (event: KeyboardEvent) => { if (event.key === "Escape" || event.key === "GoBack" || event.key === "BrowserBack") { event.preventDefault(); event.stopPropagation(); finish(due); } };
    window.addEventListener("keydown", onKey, true);
    if (!hasChannel) {
      const timeout = window.setTimeout(() => finish(due), 20_000);
      return () => { window.clearTimeout(timeout); window.removeEventListener("keydown", onKey, true); };
    }
    const started = Date.now();
    const tick = window.setInterval(() => {
      const left = SPORTS_REMINDER_COUNTDOWN_S - Math.floor((Date.now() - started) / 1000);
      if (left <= 0) { window.clearInterval(tick); switchTo(due); } else setRemaining(left);
    }, 250);
    return () => { window.clearInterval(tick); window.removeEventListener("keydown", onKey, true); };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [due?.eventId]);
  if (!due) return null;
  const started = Date.now() >= due.startsAt;
  return <aside className="sports-reminder" role="alertdialog" aria-live="assertive" aria-label={due.title}>
    <p><BellRing size={16} />{started ? translateUi("Has started") : translateUi("Starts in a minute")}</p>
    <strong>{due.title}</strong>
    <small>{hasChannel ? translateUi("Switching in {seconds} s", { seconds: remaining }) : translateUi("No channel found for this game yet.")}</small>
    <div>
      <button type="button" className="primary" onClick={() => switchTo(due)}>{hasChannel ? translateUi("Watch now") : translateUi("Open Sports")}</button>
      <button type="button" className="secondary" ref={cancelButton} onClick={() => finish(due)}>{translateUi("Cancel")}</button>
    </div>
  </aside>;
}
