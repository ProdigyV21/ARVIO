// IPTV panels (XC_VM, Xtream Codes) count every open stream request as a
// connection, keep counting a closed one for several seconds, and close the
// oldest viewer's stream when a shared line goes over its limit (#849). These
// rules keep the browser from being the one that pushes a line over.
import { fetchXtreamAccountUsage, playlistProxyHeaders, type XtreamInfo } from "./iptv";
import type { StreamSource } from "./types";

/** Live TV comes with the player's liveTv flag; these are the IPTV VOD and catch-up sources. */
export function isIptvSource(stream: Pick<StreamSource, "addonId" | "addonName">) {
  return /^(iptv|stalker|xtream)/i.test(stream.addonId ?? "") || stream.addonName === "Catch-up";
}

const STREAM_PATHS = new Set(["live", "movie", "series", "timeshift"]);

/**
 * The Xtream account a stream plays on: `/live|movie|series|timeshift/<user>/<pass>/…`,
 * or a live channel's short `/<user>/<pass>/<id>` form. Live channels count
 * only on their playlist's own Xtream server; anything else (M3U, Stalker,
 * other add-ons) has no account to ask.
 */
export function iptvAccountOf(stream: Pick<StreamSource, "url" | "originalUrl" | "addonId" | "addonName">, options: { liveTv: boolean; xtreamOrigin?: string | null }): XtreamInfo | null {
  if (!options.liveTv && !isIptvSource(stream)) return null;
  let url: URL;
  try { url = new URL(options.liveTv ? stream.url ?? "" : stream.originalUrl ?? stream.url ?? ""); } catch { return null; }
  if (!/^https?:$/.test(url.protocol)) return null;
  if (options.liveTv) {
    try { if (!options.xtreamOrigin || new URL(options.xtreamOrigin).origin !== url.origin) return null; } catch { return null; }
  }
  const decode = (value: string) => { try { return decodeURIComponent(value); } catch { return value; } };
  const segments = url.pathname.split("/").filter(Boolean);
  const at = segments.findIndex((segment) => STREAM_PATHS.has(segment.toLowerCase()));
  if (at >= 0 && segments.length >= at + 4) {
    return { baseUrl: `${url.origin}${segments.slice(0, at).map((segment) => `/${segment}`).join("")}`, username: decode(segments[at + 1]), password: decode(segments[at + 2]) };
  }
  if (options.liveTv && segments.length === 3 && /^\d+(\.\w+)?$/.test(segments[2])) return { baseUrl: url.origin, username: decode(segments[0]), password: decode(segments[1]) };
  return null;
}

/** A channel change or reconnect this soon after this device's own stream on the account is not checked: the panel may still count that stream. */
export const ACCOUNT_IN_USE_WINDOW_MS = 60_000;
/** The panel's first answer is awaited this long; silence means tune anyway. */
export const ACCOUNT_CHECK_TIMEOUT_MS = 2_500;

const inUse = new Map<string, number>();
const accountKey = (account: XtreamInfo) => { try { return `${new URL(account.baseUrl).host.toLowerCase()}|${account.username}`; } catch { return `${account.baseUrl}|${account.username}`; } };

/** This device opens (or just released) a stream on the account. */
export function noteIptvAccountInUse(account: XtreamInfo, now = Date.now()) {
  const key = accountKey(account);
  inUse.delete(key);
  inUse.set(key, now);
  while (inUse.size > 16) inUse.delete(inUse.keys().next().value!);
}

/** Whether to ask the panel before tuning: not within ACCOUNT_IN_USE_WINDOW_MS of this device's own stream on the account. */
export function shouldCheckIptvAccount(account: XtreamInfo, now = Date.now()) {
  const last = inUse.get(accountKey(account));
  return last === undefined || now - last >= ACCOUNT_IN_USE_WINDOW_MS;
}

export type IptvAccountVerdict = "full" | "free" | "unknown";

/** Only a clear "every connection is in use" counts; a missing or unlimited (0) allowance is unknown. */
export function accountVerdict(usage: { active: number; max: number } | null | undefined): IptvAccountVerdict {
  if (!usage || !(usage.max > 0) || !(usage.active >= 0)) return "unknown";
  return usage.active >= usage.max ? "full" : "free";
}

/**
 * One best-effort look at active_cons / max_connections before a tune. The
 * counts lag and another device can connect right after, so only "full"
 * stops the tune; an error, a paused request guard or no answer within
 * `timeoutMs` lets it proceed. One request per tune, never a polling loop.
 */
export async function checkIptvAccount(account: XtreamInfo, headers?: Record<string, string>, timeoutMs = ACCOUNT_CHECK_TIMEOUT_MS): Promise<IptvAccountVerdict> {
  let timer: ReturnType<typeof setTimeout> | undefined;
  const silence = new Promise<null>((resolve) => { timer = setTimeout(() => resolve(null), timeoutMs); });
  try {
    const request = fetchXtreamAccountUsage(account, headers && Object.keys(headers).length ? headers : playlistProxyHeaders()).catch(() => null);
    return accountVerdict(await Promise.race([request, silence]));
  } finally {
    clearTimeout(timer);
  }
}
