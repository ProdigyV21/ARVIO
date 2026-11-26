// Charts and playlists for the Music home, from Deezer's public catalogue API (names, covers and
// track lists only). Songs never stream from here: each one carries a match placeholder that the
// player resolves in the user's own sources when it starts. Used by app/api/music/deezer.
import { chartRows } from "./chartCatalog";
import { CHART_SHELF_PREFIX, matchUri } from "./text";
import type { MusicAlbum, MusicShelf, MusicTrack } from "./types";

export const DEEZER_API = "https://api.deezer.com";
const QUOTA_ERROR = 4;
const MIN_TRACKS = 8;
const MAX_PER_ROW = 20;
const MAX_TRACKS = 300;
/** Rows built per language per day; the rest of the catalogue is reached through search. */
const MAX_ROWS = 30;

type Json = Record<string, unknown>;
const obj = (value: unknown): Json => (value && typeof value === "object" && !Array.isArray(value) ? value as Json : {});
const list = (value: unknown): Json[] => (Array.isArray(value) ? value.filter((v) => v && typeof v === "object") as Json[] : []);
const str = (value: unknown) => (typeof value === "string" && value ? value : typeof value === "number" ? String(value) : null);
const num = (value: unknown) => (typeof value === "number" && Number.isFinite(value) ? value : typeof value === "string" && value.trim() && Number.isFinite(Number(value)) ? Number(value) : null);

export function parsePlaylist(o: Json): MusicAlbum {
  return {
    id: `playlist:${str(o.id) ?? ""}`,
    name: str(o.title) ?? "",
    artist: str(obj(o.user).name) ?? str(obj(o.creator).name) ?? "",
    artistId: null,
    coverUrl: str(o.picture_xl) ?? str(o.picture_big) ?? str(o.picture_medium),
    trackCount: num(o.nb_tracks)
  };
}

export function parseTrack(o: Json): MusicTrack {
  const album = obj(o.album);
  const title = str(o.title_short) ?? str(o.title) ?? "";
  const artist = str(obj(o.artist).name) ?? "";
  const seconds = num(o.duration) ?? 0;
  return {
    id: `deezer:${str(o.id) ?? ""}`,
    title,
    artist,
    album: str(album.title) ?? "",
    albumId: null,
    durationSec: seconds,
    coverUrl: str(album.cover_xl) ?? str(album.cover_big) ?? str(album.cover_medium),
    streamUrl: matchUri(title, artist, seconds)
  };
}

const sleep = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms));

/** Deezer answers errors with HTTP 200 and an "error" object; a quota error is worth one retry. */
async function get(path: string, query: Record<string, string>, revalidate: number): Promise<Json> {
  const url = new URL(`${DEEZER_API}/${path}`);
  for (const [k, v] of Object.entries(query)) url.searchParams.set(k, v);
  for (let attempt = 0; attempt < 2; attempt++) {
    const response = await fetch(url, { headers: { accept: "application/json" }, next: { revalidate }, signal: AbortSignal.timeout(8000) } as RequestInit);
    if (!response.ok) throw new Error(`HTTP ${response.status}`);
    const body = obj(await response.json());
    const error = body.error ? obj(body.error) : null;
    if (!error) return body;
    if (attempt === 0 && num(error.code) === QUOTA_ERROR) { await sleep(1500); continue; }
    throw new Error(str(error.message) ?? "Charts unavailable");
  }
  throw new Error("Charts unavailable");
}

async function pool<T, R>(items: T[], size: number, run: (item: T) => Promise<R>): Promise<R[]> {
  const out: R[] = new Array(items.length);
  let next = 0;
  await Promise.all(Array.from({ length: Math.min(size, items.length) }, async () => {
    while (next < items.length) {
      const index = next++;
      out[index] = await run(items[index]);
    }
  }));
  return out;
}

export async function searchPlaylists(query: string, revalidate = 3600): Promise<MusicAlbum[]> {
  const body = await get("search/playlist", { q: query, limit: "25" }, revalidate);
  return list(body.data).filter((p) => (num(p.nb_tracks) ?? 0) >= MIN_TRACKS).map(parsePlaylist);
}

export async function homeShelves(language: string, date: string): Promise<MusicShelf[]> {
  const rows = chartRows(language, date).slice(0, MAX_ROWS);
  const found = await pool(rows, 5, async (row) => {
    try { return { row, playlists: await searchPlaylists(row.query, 21600) }; }
    catch { return null; }
  });
  // A playlist shows once, in the first row that has it.
  const seen = new Set<string>();
  const shelves: MusicShelf[] = [];
  for (const entry of found) {
    if (!entry) continue;
    const fresh = entry.playlists.filter((p) => !seen.has(p.id) && seen.add(p.id)).slice(0, MAX_PER_ROW);
    if (fresh.length >= 3) shelves.push({ key: CHART_SHELF_PREFIX + entry.row.key, title: entry.row.title, albums: fresh });
  }
  if (!shelves.length) throw new Error("Charts unavailable");
  return shelves;
}

export async function playlistTracks(id: string): Promise<MusicTrack[]> {
  const tracks: MusicTrack[] = [];
  let index = 0;
  while (tracks.length < MAX_TRACKS) {
    const body = await get(`playlist/${id}/tracks`, { limit: "100", index: String(index) }, 21600);
    const page = list(body.data);
    tracks.push(...page.map(parseTrack));
    index += page.length;
    if (!page.length || !str(body.next)) break;
  }
  const seen = new Set<string>();
  return tracks.filter((t) => !seen.has(t.id) && seen.add(t.id));
}
