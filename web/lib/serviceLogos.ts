// Streaming-service clearlogos bundled from the Android app (same SVGs) —
// shown without backgrounds, exactly like the app. Mapping mirrors
// MediaRepository.canonicalStreamingServiceName / bundledStreamingLogoUri.

export function canonicalServiceName(raw: unknown): string {
  // Provider metadata can come from add-ons or older cloud snapshots, not only TMDB.
  const name = typeof raw === "string" ? raw.trim() : "";
  if (!name) return "";
  const n = name.toLowerCase();
  if (n === "max" || n.includes("hbo")) return "HBO Max";
  if (n.includes("netflix")) return "Netflix";
  if (n.includes("prime") || n.includes("amazon")) return "Prime Video";
  if (n.includes("disney")) return "Disney+";
  if (n.includes("apple tv")) return "Apple TV+";
  if (n.includes("paramount")) return "Paramount+";
  if (n.includes("hulu")) return "Hulu";
  if (n.includes("peacock")) return "Peacock";
  if (n.includes("crunchyroll")) return "Crunchyroll";
  if (n.includes("discovery")) return "Discovery+";
  if (n.includes("mgm")) return "MGM+";
  if (n.includes("shudder")) return "Shudder";
  if (n.includes("starz")) return "Starz";
  if (n.includes("youtube")) return "YouTube";
  return name;
}

const LOGO_BY_SERVICE: Record<string, string> = {
  "netflix": "/logos/logo_netflix.svg",
  "hbo max": "/logos/logo_hbo_max.svg",
  "hulu": "/logos/logo_hulu.svg",
  "prime video": "/logos/logo_prime_video.svg",
  "disney+": "/logos/logo_disney_plus.svg",
  "paramount+": "/logos/logo_paramount_plus.svg",
  "peacock": "/logos/logo_peacock.svg",
  "crunchyroll": "/logos/logo_crunchyroll.svg",
  "discovery+": "/logos/logo_discovery_plus.svg",
  "mgm+": "/logos/logo_mgm_plus.svg",
  "shudder": "/logos/logo_shudder.svg",
  "starz": "/logos/logo_starz.svg",
  "apple tv+": "/logos/logo_apple_tv_plus.svg"
};

/** Clearlogo path for a provider name, or null when we have no bundled logo. */
export function serviceClearLogo(providerName: string | null | undefined): string | null {
  const canonical = canonicalServiceName(providerName).toLowerCase();
  return LOGO_BY_SERVICE[canonical] ?? null;
}

export const IMDB_LOGO = "/logos/logo_imdb_rectangle.svg";
// Card/hero/episode scores come from TMDB (vote_average), NOT IMDb — badge them
// accurately. Real IMDb ratings need an imdb id + a Cinemeta lookup per title.
export const TMDB_LOGO = "/logos/logo_tmdb_rectangle.svg";
