import type { PlaybackPlan } from "@/lib/streamCompatibility";
import type { StreamSource } from "@/lib/types";

/**
 * Three tiers, decided from the shared playback plan for THIS browser:
 * - "here": the browser plays the file as it is (start is quick);
 * - "conversion": it plays here after repackaging or provider conversion,
 *   which takes longer to start;
 * - "external": no browser route; hand it to VLC / Infuse / another player.
 * No tier claims verified playback: the plan is a prediction from metadata.
 */
export type SourceTier = "here" | "conversion" | "external";

export type SourcePlaybackPresentation = {
  tier: SourceTier;
  /** Kept for styling hooks and older callers. */
  state: "blocked" | "conversion" | "preparation" | "likely";
  label: string;
  detail: string;
  className: string;
  canTryBrowser: boolean;
};

export const SOURCE_TIER_LABELS: Record<SourceTier, string> = {
  here: "Plays here",
  conversion: "Needs conversion · slower start",
  external: "External player only"
};

export function sourcePlaybackPresentation(
  stream: Pick<StreamSource, "url">,
  plan: Pick<PlaybackPlan, "route" | "method" | "detail">,
  uncached: boolean
): SourcePlaybackPresentation {
  const cacheDetail = uncached ? "Not cached: the provider must download this source first." : "";
  const detail = (fallback: string) => [plan.detail || fallback, cacheDetail].filter(Boolean).join(" ");
  if (!stream.url || plan.route !== "here") {
    return {
      tier: "external",
      state: "blocked",
      label: SOURCE_TIER_LABELS.external,
      detail: detail(!stream.url ? "No resolved playback URL is available." : plan.route === "vlc"
        ? "This browser cannot decode it. Use VLC, Infuse or another source."
        : "No browser playback route is available for this source."),
      className: "recommend-external",
      canTryBrowser: false
    };
  }
  if (plan.method === "transcode") {
    return {
      tier: "conversion",
      state: "conversion",
      label: SOURCE_TIER_LABELS.conversion,
      detail: detail("Conversion availability depends on provider support and permission."),
      className: "is-transcode",
      canTryBrowser: !uncached
    };
  }
  if (plan.method === "remux") {
    return {
      tier: "conversion",
      state: "preparation",
      label: SOURCE_TIER_LABELS.conversion,
      detail: detail("The browser repackages this file before it starts."),
      className: "is-remux",
      canTryBrowser: !uncached
    };
  }
  return {
    tier: "here",
    state: "likely",
    label: SOURCE_TIER_LABELS.here,
    detail: detail(""),
    className: "is-here",
    canTryBrowser: !uncached
  };
}

export type SourceBadge = { label: string; tone?: "gold" | "ok" | "warn" | "" };

function detectQuality(text: string) {
  if (/\b(?:4320p|8k)\b/.test(text)) return "8K";
  if (/2160|\b4k\b|\buhd\b/.test(text)) return "4K";
  if (/1440/.test(text)) return "1440p";
  if (/1080/.test(text)) return "1080p";
  if (/720/.test(text)) return "720p";
  if (/480|\bsd\b/.test(text)) return "480p";
  return "";
}

/** Normalised resolution bucket for the quality filters. */
export function sourceQuality(stream: Pick<StreamSource, "quality" | "source" | "description">) {
  return detectQuality(`${stream.quality ?? ""} ${stream.source ?? ""} ${stream.description ?? ""}`.toLowerCase());
}

/** Cached on a debrid service (plays without a provider-side download). */
export function isCachedSource(stream: Pick<StreamSource, "url" | "source" | "description" | "addonName" | "behaviorHints">, isDebrid: boolean, uncached: boolean) {
  if (uncached) return false;
  if (stream.behaviorHints?.cached) return true;
  const text = `${stream.source ?? ""} ${stream.addonName ?? ""} ${stream.description ?? ""}`;
  return /\[\s*(?:tb|rd|ad|pm|dl|oc|ed)\s*\+\s*\]|⚡/i.test(text) || (isDebrid && /\bcached\b/i.test(text));
}

/**
 * Compact facts for one source row: resolution, HDR, audio, size and the
 * debrid/cache state. Nothing that merely restates "it has a URL".
 */
export function sourceBadges(stream: StreamSource, info: { debrid: boolean; uncached: boolean }): SourceBadge[] {
  const text = `${stream.source} ${stream.description ?? ""} ${stream.size ?? ""} ${stream.behaviorHints?.filename ?? ""}`.toLowerCase();
  const badges: SourceBadge[] = [];
  const quality = sourceQuality(stream) || stream.quality || "";
  if (quality) badges.push({ label: quality, tone: quality === "4K" || quality === "8K" ? "gold" : "" });
  if (text.includes("dolby vision") || /\b(?:dv|dovi)\b/.test(text)) badges.push({ label: "DV" });
  if (/hdr10\+|hdr10plus/.test(text)) badges.push({ label: "HDR10+" });
  else if (/\bhdr/.test(text)) badges.push({ label: "HDR" });
  if (text.includes("atmos")) badges.push({ label: "Atmos" });
  else if (/\b7[ .]1\b/.test(text)) badges.push({ label: "7.1" });
  else if (/\b5[ .]1\b/.test(text)) badges.push({ label: "5.1" });
  const size = stream.size || text.match(/\b\d+(?:\.\d+)?\s?(?:gb|mb|tb)\b/i)?.[0]?.toUpperCase();
  if (size) badges.push({ label: size });
  if (info.uncached) badges.push({ label: "Not cached", tone: "warn" });
  else if (isCachedSource(stream, info.debrid, info.uncached)) badges.push({ label: "Cached", tone: "ok" });
  else if (info.debrid) badges.push({ label: "Debrid", tone: "ok" });
  if (!stream.url) badges.push({ label: "No URL", tone: "warn" });
  const seen = new Set<string>();
  return badges.filter((badge) => {
    if (seen.has(badge.label)) return false;
    seen.add(badge.label);
    return true;
  }).slice(0, 6);
}

/**
 * The row's headline: the release/file name when the addon gives one (that is
 * what tells two "Torrentio 1080p" rows apart), else the addon's own label.
 */
export function sourceTitle(stream: Pick<StreamSource, "source" | "addonName" | "description" | "behaviorHints">) {
  const rawFilename = stream.behaviorHints?.filename;
  const file = typeof rawFilename === "string" ? rawFilename.trim() : "";
  if (file) return file;

  const description = typeof stream.description === "string" ? stream.description : "";
  const firstLine = description.split("\n").map((line) => line.trim()).find(Boolean);
  // Stremio addons put the release name on the first description line and
  // decorations (👤 seeders, 💾 size, ⚙️ provider) on the following ones.
  if (firstLine && /[._ -]/.test(firstLine) && firstLine.length > 8 && !/^[👤💾⚙️🔗📦]/u.test(firstLine)) {
    return firstLine.replace(/\s*💾.*$/u, "").trim() || firstLine;
  }

  if (typeof stream.source === "string" && stream.source) return stream.source;
  if (typeof stream.addonName === "string" && stream.addonName) return stream.addonName;
  return "Source";
}

export type SourceFilter = "all" | "here" | "4k" | "1080p" | "cached";

export function matchesSourceFilter(filter: SourceFilter, facts: { tier: SourceTier; quality: string; cached: boolean }) {
  switch (filter) {
    case "here": return facts.tier !== "external";
    case "4k": return facts.quality === "4K" || facts.quality === "8K";
    case "1080p": return facts.quality === "1080p";
    case "cached": return facts.cached;
    default: return true;
  }
}
