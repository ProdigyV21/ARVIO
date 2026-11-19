// "View on Simkl" / "View on Trakt" from a details page: open the title's own
// page, not a search.
import { config } from "./config";
import type { MediaItem } from "./types";

type Ids = Pick<MediaItem, "id" | "imdbId" | "mediaType">;

/** Simkl's public redirect turns an IMDb/TMDB id into the title page (301). */
export function simklRedirectUrl(item: Ids): string {
  const params = new URLSearchParams({ to: "Simkl", type: item.mediaType === "movie" ? "movie" : "show" });
  if (item.imdbId) params.set("imdb", item.imdbId);
  else params.set("tmdb", String(item.id));
  if (config.simklClientId) params.set("client_id", config.simklClientId);
  return `https://api.simkl.com/redirect?${params.toString()}`;
}

export function traktSearchUrl(title: string): string {
  return `https://trakt.tv/search?query=${encodeURIComponent(title)}`;
}

/**
 * The title's Trakt page. The relay looks it up by IMDb/TMDB id and redirects
 * (the browser build has no Trakt key and Trakt blocks the app backend);
 * without a relay, or when Trakt doesn't know the id, it opens a title search.
 */
export function traktLinkUrl(item: Ids & { title: string }, relayHasLink: boolean): string {
  const relay = (config.resolverUrl || "").replace(/\/+$/, "");
  if (!relayHasLink || !/^https?:/.test(relay)) return traktSearchUrl(item.title);
  const params = new URLSearchParams({ type: item.mediaType === "movie" ? "movie" : "show", title: item.title });
  if (item.imdbId) params.set("imdb", item.imdbId);
  else params.set("tmdb", String(item.id));
  return `${relay}/trakt/link?${params.toString()}`;
}

let traktLinkSupport: Promise<boolean> | null = null;
/** Whether the deployed relay has the /trakt/link redirect (its /health lists it). */
export function relaySupportsTraktLink(): Promise<boolean> {
  const relay = (config.resolverUrl || "").replace(/\/+$/, "");
  if (!/^https?:/.test(relay)) return Promise.resolve(false);
  traktLinkSupport ??= fetch(`${relay}/health`, { cache: "no-store" })
    .then((response) => response.ok ? response.json() as Promise<{ features?: string[] }> : null)
    .then((health) => Boolean(health?.features?.includes("trakt-link")))
    .catch(() => false);
  return traktLinkSupport;
}

const traktViaSimkl = new Map<string, Promise<string | null>>();
/**
 * The title's Trakt page from Simkl's id mapping (Simkl records Trakt's slug).
 * Runs in the browser: Simkl answers CORS with the public client id, while
 * Trakt itself rate-limits Cloudflare egress and blocks the app backend.
 */
export function traktPageViaSimkl(item: Ids): Promise<string | null> {
  if (!config.simklClientId) return Promise.resolve(null);
  const key = `${item.mediaType}:${item.imdbId ?? `tmdb${item.id}`}`;
  const known = traktViaSimkl.get(key);
  if (known) return known;
  const client = encodeURIComponent(config.simklClientId);
  const lookup = (async () => {
    try {
      const query = item.imdbId ? `imdb=${encodeURIComponent(item.imdbId)}` : `tmdb=${item.id}&type=${item.mediaType === "movie" ? "movie" : "show"}`;
      const found = await fetch(`https://api.simkl.com/search/id?${query}&client_id=${client}`).then((r) => r.ok ? r.json() : null) as Array<{ type?: string; ids?: { simkl?: number } }> | null;
      const hit = found?.find((entry) => entry.ids?.simkl) ?? null;
      if (!hit?.ids?.simkl) return null;
      const section = hit.type === "movie" ? "movies" : hit.type === "anime" ? "anime" : "tv";
      const full = await fetch(`https://api.simkl.com/${section}/${hit.ids.simkl}?extended=full&client_id=${client}`).then((r) => r.ok ? r.json() : null) as { ids?: { traktslug?: string } } | null;
      const slug = full?.ids?.traktslug;
      return slug ? `https://trakt.tv/${section === "movies" ? "movies" : "shows"}/${encodeURIComponent(slug)}` : null;
    } catch {
      return null;
    }
  })();
  traktViaSimkl.set(key, lookup);
  void lookup.then((url) => { if (!url) traktViaSimkl.delete(key); });
  return lookup;
}
