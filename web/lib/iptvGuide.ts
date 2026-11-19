// Pure helpers for the Live TV guide: clock formatting, D-pad focus time,
// search matching and readable playlist errors. Kept free of React so the
// guide, Settings and tests can share them.
import type { IptvChannel, IptvNowNext, IptvProgram } from "./types";

export type ClockFormat = "12h" | "24h" | undefined;

// Intl.DateTimeFormat construction is expensive (it was ~70% of a guide row
// render with 55k channels). Build each formatter once per clock format.
const timeFormatters = new Map<string, Intl.DateTimeFormat>();
function timeFormatter(clockFormat: ClockFormat) {
  const key = clockFormat ?? "auto";
  let formatter = timeFormatters.get(key);
  if (!formatter) {
    try {
      formatter = new Intl.DateTimeFormat([], clockFormat === "24h"
        ? { hour: "2-digit", minute: "2-digit", hourCycle: "h23" }
        // 12h and locale default: "9:00 PM", never "09:00 PM".
        : { hour: "numeric", minute: "2-digit", ...(clockFormat === "12h" ? { hour12: true } : {}) });
    } catch {
      formatter = new Intl.DateTimeFormat();
    }
    timeFormatters.set(key, formatter);
  }
  return formatter;
}

export function formatGuideTime(ms: number, clockFormat?: ClockFormat): string {
  if (!Number.isFinite(ms)) return "";
  try { return timeFormatter(clockFormat).format(new Date(ms)); } catch { return ""; }
}

const dayFormatters = new Map<string, Intl.DateTimeFormat>();
export function formatGuideDay(ms: number, withTime = false, clockFormat?: ClockFormat): string {
  const key = `${withTime}:${clockFormat ?? "auto"}`;
  let formatter = dayFormatters.get(key);
  if (!formatter) {
    formatter = new Intl.DateTimeFormat([], withTime
      ? { weekday: "short", hour: clockFormat === "24h" ? "2-digit" : "numeric", minute: "2-digit", ...(clockFormat ? { hourCycle: clockFormat === "24h" ? "h23" : "h12" } : {}) }
      : { weekday: "short", day: "numeric", month: "short" });
    dayFormatters.set(key, formatter);
  }
  try { return formatter.format(new Date(ms)); } catch { return ""; }
}

/** Every programme a guide entry knows, oldest first, without duplicates. */
export function guidePrograms(guide?: IptvNowNext): IptvProgram[] {
  if (!guide) return [];
  const seen = new Set<number>();
  return [...(guide.recent ?? []), guide.now, guide.next, ...(guide.upcoming ?? [])]
    .filter((program): program is IptvProgram => Boolean(program))
    .filter((program) => (seen.has(program.startUtcMillis) ? false : (seen.add(program.startUtcMillis), true)))
    .sort((a, b) => a.startUtcMillis - b.startUtcMillis);
}

export type GuideSlot = { start: number; end: number };

/**
 * Which programme Up/Down should land on: the one airing at the remembered
 * focus time, else the nearest one that starts before it, else the first.
 * Returns -1 when the row has no programmes (focus the channel instead).
 */
export function slotAtFocusTime(slots: GuideSlot[], focusTime: number): number {
  if (!slots.length) return -1;
  const containing = slots.findIndex((slot) => slot.start <= focusTime && focusTime < slot.end);
  if (containing >= 0) return containing;
  let best = -1;
  for (let index = 0; index < slots.length; index++) if (slots[index].start <= focusTime) best = index;
  return best >= 0 ? best : 0;
}

/** The focus time a programme sets when the user moves onto it sideways. */
export function focusTimeForSlot(slot: GuideSlot, windowStart: number): number {
  return Math.max(slot.start, windowStart);
}

/**
 * Channel search: name, category (the playlist's name and the name the user
 * gave it), guide id and the titles of loaded programmes.
 */
export function channelMatchesQuery(channel: IptvChannel, needle: string, guide?: IptvNowNext, groupName?: string): boolean {
  if (!needle) return true;
  if (channel.name.toLowerCase().includes(needle) || channel.group.toLowerCase().includes(needle)
    || Boolean(groupName && groupName.toLowerCase().includes(needle))
    || channel.tvgId?.toLowerCase().includes(needle) || channel.number === needle) return true;
  if (!guide) return false;
  return [guide.now, guide.next, guide.later, ...(guide.upcoming ?? [])]
    .some((program) => Boolean(program?.title && program.title.toLowerCase().includes(needle)));
}

export type PlaylistErrorCause = "not-found" | "html" | "unreachable" | "blocked" | "auth" | "paused" | "empty" | "other";

/** Map the loader's technical message to a plain cause the user can act on. */
export function playlistErrorCause(message: string): PlaylistErrorCause {
  const text = message.toLowerCase();
  if (/temporarily paused|429|too many/.test(text)) return "paused";
  if (/html page|<html|text\/html/.test(text)) return "html";
  if (/\b(401|403)\b|unauthori[sz]ed|forbidden|expired|auth/.test(text)) return "auth";
  if (/\b404\b|not found/.test(text)) return "not-found";
  if (/blocked proxy target|blocked|mixed content|cors/.test(text)) return "blocked";
  if (/failed to fetch|networkerror|network error|load failed|timed? ?out|took too long|enotfound|econnrefused|unreachable|dns/.test(text)) return "unreachable";
  if (/did not contain any channels|not an m3u|no channels/.test(text)) return "empty";
  return "other";
}

/** "Name: message" warnings from older snapshots → name and message. */
export function splitPlaylistWarning(warning: string): { name: string; message: string } {
  const index = warning.indexOf(": ");
  return index > 0 ? { name: warning.slice(0, index), message: warning.slice(index + 2) } : { name: "", message: warning };
}

/** Xtream Codes login → the M3U and XMLTV links ARVIO stores (same as Android). */
export function xtreamPlaylistUrls(host: string, username: string, password: string): { m3uUrl: string; epgUrl: string } | null {
  let base = host.trim();
  if (!base || !username.trim() || !password.trim()) return null;
  if (!/^https?:\/\//i.test(base)) base = `http://${base}`;
  let parsed: URL;
  try { parsed = new URL(base); } catch { return null; }
  const root = `${parsed.protocol}//${parsed.host}${parsed.pathname.replace(/\/(get|player_api|xmltv)\.php$/i, "").replace(/\/+$/, "")}`;
  const query = (path: string, extra: Record<string, string> = {}) => {
    const target = new URL(`${root}/${path}`);
    target.searchParams.set("username", username.trim());
    target.searchParams.set("password", password.trim());
    for (const [key, value] of Object.entries(extra)) target.searchParams.set(key, value);
    return target.toString();
  };
  return { m3uUrl: query("get.php", { type: "m3u_plus", output: "m3u8" }), epgUrl: query("xmltv.php") };
}
