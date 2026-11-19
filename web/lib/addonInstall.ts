// Installing an addon that is already there (same manifest id) asks first,
// like Android's AddonSetupDialogs: replace the existing install (keeping its
// place in the list) or keep both, each with its own installation id.
import type { InstalledAddon } from "./types";

export type AddonInstallMode = "replace" | "keep-both";

type WithManifest = InstalledAddon & { manifest?: { id?: string; behaviorHints?: Record<string, unknown> } | null; transportUrl?: string; configureUrl?: string | null };

export function addonManifestId(addon: InstalledAddon): string {
  return (addon as WithManifest).manifest?.id || addon.id;
}

const sameUrl = (a: string, b: string) => a.trim().toLowerCase() === b.trim().toLowerCase();

/** Installed addons the candidate would collide with (same manifest id or same link). */
export function duplicateAddons(addons: InstalledAddon[], candidate: InstalledAddon): InstalledAddon[] {
  const manifestId = addonManifestId(candidate);
  return addons.filter((addon) => addon.id === candidate.id || addonManifestId(addon) === manifestId || sameUrl(addon.manifestUrl, candidate.manifestUrl));
}

/** Same link as an installed addon: "keep both" would be the same install twice. */
export function isSameInstall(addons: InstalledAddon[], candidate: InstalledAddon) {
  return addons.some((addon) => sameUrl(addon.manifestUrl, candidate.manifestUrl));
}

/** Android's buildAddonInstanceId: `${manifestId}_${first 6 bytes of sha256(url) as hex}`. */
export async function addonInstanceId(manifestId: string, url: string): Promise<string> {
  const digest = new Uint8Array(await crypto.subtle.digest("SHA-256", new TextEncoder().encode(url.trim().toLowerCase())));
  return `${manifestId}_${Array.from(digest.slice(0, 6), (byte) => byte.toString(16).padStart(2, "0")).join("")}`;
}

/**
 * The addon list after installing `candidate`. Replacing keeps the first
 * replaced addon's position; a new or kept-both addon goes first (web order).
 */
export function applyAddonInstall(addons: InstalledAddon[], candidate: InstalledAddon, mode: AddonInstallMode | "new"): { next: InstalledAddon[]; removedIds: string[] } {
  if (mode === "replace") {
    const replaced = duplicateAddons(addons, candidate);
    const ids = new Set(replaced.map((addon) => addon.id));
    const index = addons.findIndex((addon) => ids.has(addon.id));
    const kept = addons.filter((addon) => !ids.has(addon.id));
    const keptBefore = addons.slice(0, Math.max(0, index)).filter((addon) => !ids.has(addon.id)).length;
    const previous = replaced[0];
    // A replacement keeps the user's on/off choice.
    const placed = { ...candidate, enabled: previous ? previous.enabled !== false : true };
    const next = index < 0 ? [placed, ...kept] : [...kept.slice(0, keptBefore), placed, ...kept.slice(keptBefore)];
    return { next, removedIds: replaced.map((addon) => addon.id).filter((id) => id !== candidate.id) };
  }
  return { next: [candidate, ...addons.filter((addon) => addon.id !== candidate.id)], removedIds: [] };
}

/** The addon's own settings page, when its manifest says it can be configured (Android AddonSetup). */
export function addonConfigureUrl(addon: InstalledAddon): string | null {
  const raw = addon as WithManifest;
  if (raw.configureUrl) return raw.configureUrl;
  const hints = raw.manifest?.behaviorHints;
  if (!hints?.configurable && !hints?.configurationRequired) return null;
  try {
    const base = new URL(raw.transportUrl || addon.manifestUrl);
    base.pathname = `${base.pathname.replace(/\/+$/, "").replace(/\/manifest\.json$/, "")}/configure`;
    base.hash = "";
    return base.toString();
  } catch { return null; }
}

/** What an addon provides, for the install preview and the list. */
export function addonCapabilities(addon: InstalledAddon): { catalogs: number; streams: boolean; subtitles: boolean; meta: boolean } {
  const names = (addon.resources ?? []).map((resource) => typeof resource === "string" ? resource : resource?.name ?? "");
  const any = names.length === 0;
  return {
    catalogs: Array.isArray(addon.catalogs) ? addon.catalogs.length : 0,
    streams: any || names.includes("stream"),
    subtitles: names.includes("subtitles"),
    meta: names.includes("meta")
  };
}

export class AddonAlreadyInstalledError extends Error {
  readonly candidate: InstalledAddon;
  readonly existing: InstalledAddon[];
  constructor(candidate: InstalledAddon, existing: InstalledAddon[]) {
    super(`${candidate.name} is already installed. Open Addons to replace it or keep both.`);
    this.name = "AddonAlreadyInstalledError";
    this.candidate = candidate;
    this.existing = existing;
  }
}
