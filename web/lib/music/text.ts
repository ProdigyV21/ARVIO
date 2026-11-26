// Matching and ranking of songs by name: finding a chart song in the user's own sources, and
// putting what the user most likely wants first in search. Same rules as the Android app.
import type { MusicAlbum, MusicArtist, MusicSearchResult, MusicShelf, MusicTrack } from "./types";

export function normalizeMusicText(text: string): string {
  return text.toLowerCase().normalize("NFD").replace(/\p{Mn}+/gu, "")
    .replace(/[([].*?[)\]]/g, " ")
    .replace(/ - .*(remaster|version|live|edit|mix|mono|stereo).*$/, " ")
    .replace(/[^\p{L}\p{N}]+/gu, " ")
    .trim();
}

export const MATCH_THRESHOLD = 0.7;

/** How well a found track fits the wanted song, 0..1; below MATCH_THRESHOLD it is not the same song. */
export function matchScore(wanted: { title: string; artist: string; durationSec?: number | null }, found: MusicTrack): number {
  const wantTitle = normalizeMusicText(wanted.title);
  const gotTitle = normalizeMusicText(found.title);
  const title = !wantTitle || !gotTitle ? 0 : wantTitle === gotTitle ? 1 : gotTitle.includes(wantTitle) || wantTitle.includes(gotTitle) ? 0.75 : 0;
  if (title === 0) return 0;
  const wantArtist = normalizeMusicText(wanted.artist);
  const gotArtist = normalizeMusicText(found.artist);
  const artist = !wantArtist ? 0.5
    : gotArtist === wantArtist ? 1
      : gotArtist && (gotArtist.includes(wantArtist) || wantArtist.includes(gotArtist)) ? 0.8
        // Addons often fold the artist into the title ("Artist - Song").
        : gotTitle.includes(wantArtist) ? 0.7 : 0;
  const wantSec = wanted.durationSec && wanted.durationSec > 0 ? wanted.durationSec : null;
  const diff = wantSec === null || found.durationSec <= 0 ? null : Math.abs(wantSec - found.durationSec);
  const duration = diff === null ? 0.5 : diff <= 5 ? 1 : diff <= 20 ? 0.5 : 0;
  const exact = wanted.title.trim().toLowerCase() === found.title.trim().toLowerCase() ? 0.05 : 0;
  return title * 0.5 + artist * 0.35 + duration * 0.15 + exact;
}

export const MATCH_SCHEME = "arvio-music-match";
export const ADDON_STREAM_SCHEME = "arvio-music-addon";

export function matchUri(title: string, artist: string, durationSec: number) {
  const enc = (value: string) => encodeURIComponent(value);
  return `${MATCH_SCHEME}://track?t=${enc(title)}&a=${enc(artist)}&d=${durationSec}`;
}

/** The query of a placeholder URI ("scheme://host?a=b"), read without relying on URL's handling of unknown schemes. */
export function placeholderParams(uri: string): URLSearchParams {
  const query = uri.includes("?") ? uri.slice(uri.indexOf("?") + 1) : "";
  return new URLSearchParams(query.replace(/\+/g, "%20"));
}

export function addonStreamUri(baseUrl: string, trackId: string, protocol?: string | null) {
  return `${ADDON_STREAM_SCHEME}://stream?base=${encodeURIComponent(baseUrl)}&id=${encodeURIComponent(trackId)}${protocol ? `&p=${encodeURIComponent(protocol)}` : ""}`;
}

/** Words that ask for a playlist rather than one song, in the app's languages. */
const PLAYLIST_WORDS = new Set([
  "playlist", "playlists", "hits", "top", "mix", "best", "charts", "chart", "songs", "music", "radio",
  "gym", "workout", "running", "chill", "party", "throwback", "classics", "essentials", "vibes",
  "focus", "study", "sleep", "relax", "lofi", "christmas", "summer", "love", "sad", "roadtrip",
  "decade", "oldies", "anthems", "hot", "new", "fitness", "training", "cardio", "dinner", "morning",
  "afspeellijst", "liedjes", "muziek", "nummers", "feest", "sportschool", "hardlopen", "kerst", "zomer",
  "nederlandstalig", "nederlandse", "beste",
  "lieder", "musik", "fitnessstudio", "laufen", "weihnachten", "sommer", "deutsche", "schlager",
  "musique", "chansons", "tubes", "musica", "canciones", "exitos", "canzoni", "musicas", "sucessos"
]);
const DECADE = /\b(19|20)?\d0s\b|\b(19|20)\d\d\b/;

export function wantsPlaylists(query: string): boolean {
  const q = normalizeMusicText(query);
  if (!q) return false;
  return q.split(" ").some((word) => PLAYLIST_WORDS.has(word)) || DECADE.test(query.toLowerCase());
}

/** How well a song fits the query: its title typed exactly beats a title that only contains the words. */
export function trackScore(query: string, track: MusicTrack): number {
  const q = normalizeMusicText(query);
  const title = normalizeMusicText(track.title);
  const artist = normalizeMusicText(track.artist);
  if (!q || !title) return 0;
  const words = q.split(" ");
  let score = title === q ? 6
    : artist && (q === `${artist} ${title}` || q === `${title} ${artist}`) ? 6
      : q.includes(title) && title.length * 2 >= q.length ? 4
        : q.includes(title) ? 2
          : title.includes(q) ? 3
            : title.split(" ").some((word) => word.length > 2 && words.includes(word)) ? 1 : 0;
  if (artist && (q.includes(artist) || artist.includes(q))) score += 2;
  if (score > 0 && query.trim().toLowerCase().includes(track.title.trim().toLowerCase())) score += 1;
  return score;
}

export function rankTracks(query: string, tracks: MusicTrack[]): MusicTrack[] {
  return tracks.map((track, index) => ({ track, index, score: trackScore(query, track) }))
    .sort((a, b) => b.score - a.score || a.index - b.index)
    .map((entry) => entry.track);
}

export type SearchSection = "tracks" | "artists" | "albums" | "playlists";

export function searchOrder(query: string, result: MusicSearchResult): SearchSection[] {
  const q = normalizeMusicText(query);
  const artistExact = result.artists.some((artist: MusicArtist) => normalizeMusicText(artist.name) === q);
  const songTyped = result.tracks.some((track) => trackScore(query, track) >= 6);
  if (result.playlists.length && wantsPlaylists(query) && !songTyped) return ["playlists", "tracks", "albums", "artists"];
  if (artistExact && !result.tracks.some((track) => normalizeMusicText(track.title) === q)) return ["artists", "tracks", "albums", "playlists"];
  return ["tracks", "albums", "artists", "playlists"];
}

export const CHART_SHELF_PREFIX = "charts:";
export const isChartShelf = (key: string) => key.slice(key.indexOf("/") + 1).startsWith(CHART_SHELF_PREFIX);

/** The home rows: the country's charts lead, then the user's own sources' rows, then the rest of the charts. */
export function arrangeHome(shelves: MusicShelf[], lead = 4): MusicShelf[] {
  const charts = shelves.filter((shelf) => isChartShelf(shelf.key));
  const own = shelves.filter((shelf) => !isChartShelf(shelf.key));
  return [...charts.slice(0, lead), ...own, ...charts.slice(lead)];
}

export function formatDuration(seconds: number) {
  if (!Number.isFinite(seconds) || seconds <= 0) return "";
  const s = Math.round(seconds);
  const h = Math.floor(s / 3600), m = Math.floor((s % 3600) / 60), sec = s % 60;
  return h ? `${h}:${String(m).padStart(2, "0")}:${String(sec).padStart(2, "0")}` : `${m}:${String(sec).padStart(2, "0")}`;
}

export const albumKey = (album: MusicAlbum) => album.id;
