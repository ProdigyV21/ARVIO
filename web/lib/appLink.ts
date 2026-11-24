// "Open in the ARVIO app" for Android phones and tablets. The Android app
// registers arvio://open?type=movie|tv&id=<tmdb>[&season=&episode=] (title
// only, never a source URL), so the app plays with its own player and codecs:
// no browser limits on MKV, DTS or HDR. Chrome launches it through an intent://
// URL; when the app is not installed it falls back to arvio.tv, where it can be
// downloaded.
import type { MediaType } from "./types";

const PACKAGE = "com.arvio.tv";
const FALLBACK = "https://arvio.tv/";

export function arvioAppLink(item: { id: number; mediaType: MediaType }, episode?: { season: number; episode: number } | null) {
  if (!Number.isInteger(item.id) || item.id <= 0 || (item.mediaType !== "movie" && item.mediaType !== "tv")) return null;
  const query = new URLSearchParams({ type: item.mediaType, id: String(item.id) });
  if (item.mediaType === "tv" && episode && episode.season >= 0 && episode.episode > 0) {
    query.set("season", String(episode.season));
    query.set("episode", String(episode.episode));
  }
  return `intent://open?${query.toString()}#Intent;scheme=arvio;package=${PACKAGE};S.browser_fallback_url=${encodeURIComponent(FALLBACK)};end`;
}
