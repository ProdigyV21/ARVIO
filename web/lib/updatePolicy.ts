const BUILD_STAMP = /^\d{10,16}$/;

/** A newer deploy found right after page load still counts as "app start". */
export const BOOT_WINDOW_MS = 10_000;
/** Never navigate for updates more often than this (stale-CDN loop guard). */
export const RELOAD_COOLDOWN_MS = 4 * 60 * 1000;

export function isNewerBuild(remote: unknown, current: string): remote is string | number {
  // Older manifests may be stale CDN responses or an intentional rollback.
  // A rollback requires a manual refresh; never repeatedly navigate backwards.
  const candidate = String(remote ?? "");
  if (!BUILD_STAMP.test(candidate) || !BUILD_STAMP.test(current)) return false;
  const next = Number(candidate), previous = Number(current);
  return Number.isSafeInteger(next) && Number.isSafeInteger(previous) && next > previous;
}

/** Should the "Update ready" prompt appear for this manifest? */
export function shouldOfferUpdate({ current, remote, attempted }: { current: string; remote: unknown; attempted: string }) {
  // `attempted` is the version a previous update navigation asked for: if this
  // bundle is still older, the CDN served stale HTML — offering the same
  // version again would loop.
  return isNewerBuild(remote, current) && String(remote) !== attempted;
}

export type UpdateTrigger = "boot" | "navigation" | "manual";

/**
 * May an offered update apply itself now? Only at a safe moment: app start
 * (a check that resolved within BOOT_WINDOW_MS of load) or a navigation
 * between top-level sections — and never while the screen holds something a
 * reload would destroy (`blocked`: player, focused field, open dialog). A
 * manual "Reload" press always applies.
 */
export function shouldAutoApply({ trigger, sinceBootMs, blocked, now, lastReloadAt }: {
  trigger: UpdateTrigger; sinceBootMs: number; blocked: boolean; now: number; lastReloadAt: number;
}) {
  if (trigger === "manual") return true;
  if (blocked) return false;
  if (lastReloadAt && now - lastReloadAt < RELOAD_COOLDOWN_MS) return false;
  return trigger === "navigation" || sinceBootMs <= BOOT_WINDOW_MS;
}

type DocLike = Pick<Document, "querySelector"> & { activeElement: Element | null };

/** Why reloading right now would lose something, or null when it is safe. */
export function updateBlocker(doc: DocLike): "player" | "text" | "dialog" | null {
  // A paused/buffering player or an embedded trailer must not be closed.
  if (doc.querySelector('video, iframe[src*="youtube.com"], iframe[src*="youtube-nocookie.com"]')) return "player";
  const active = doc.activeElement as (Element & { isContentEditable?: boolean }) | null;
  if (active && (/^(INPUT|TEXTAREA|SELECT)$/.test(active.tagName) || active.isContentEditable)) return "text";
  if (doc.querySelector('dialog[open], [role="dialog"], [aria-modal="true"]')) return "dialog";
  return null;
}
