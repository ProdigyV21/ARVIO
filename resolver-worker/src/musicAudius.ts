// ARVIO music addon for Audius (audius.co): music that artists upload and share for free
// streaming through Audius' public API. It speaks the music addon protocol the app expects
// (Stremio-style: manifest, catalog, meta, stream) under /music/audius/.
//
// Audius API: https://docs.audius.org/developers/api — requests carry app_name.

const PREFIX = "/music/audius";
const APP_NAME = "ARVIO";
const API_ROOT = "https://api.audius.co";
const CACHE_SECONDS = 600;

const GENRES: Array<[string, string]> = [
  ["", "Top 100"],
  ["Electronic", "Electronic"],
  ["Hip-Hop/Rap", "Hip-hop & rap"],
  ["Pop", "Pop"],
  ["R&B/Soul", "R&B & soul"],
  ["Rock", "Rock"],
  ["Alternative", "Alternative"],
  ["Lo-Fi", "Lo-fi"],
  ["Jazz", "Jazz"],
  ["Classical", "Classical"],
  ["Ambient", "Ambient"],
  ["Latin", "Latin"],
];

type Json = Record<string, any>;

const manifest = {
  id: "tv.arvio.music.audius",
  version: "1.0.0",
  name: "Audius",
  description: "Music that artists share for free on Audius. Trending charts, albums, playlists and search.",
  logo: "https://audius.co/favicons/favicon-196x196.png",
  types: ["music"],
  resources: ["catalog", "meta", "stream"],
  idPrefixes: [""],
  catalogs: [
    { type: "music", id: "trending", name: "Audius: trending this week" },
    { type: "music", id: "playlists", name: "Audius: popular albums & playlists" },
    { type: "music", id: "search", name: "Audius", extra: [{ name: "search", isRequired: true }] },
  ],
};

let apiBase: string | null = null;

async function base(): Promise<string> {
  if (apiBase) return apiBase;
  // api.audius.co answers /v1 itself; it also lists discovery nodes as a fallback.
  try {
    const probe = await fetch(`${API_ROOT}/v1/tracks/trending?limit=1&app_name=${APP_NAME}`, {
      cf: { cacheTtl: CACHE_SECONDS, cacheEverything: true },
    } as RequestInit);
    if (probe.ok) return (apiBase = `${API_ROOT}/v1`);
  } catch {
    // fall through to the node list
  }
  const nodes = (await (await fetch(API_ROOT)).json()) as { data?: string[] };
  const node = nodes.data?.[0];
  if (!node) throw new Error("No Audius node available");
  return (apiBase = `${node.replace(/\/+$/, "")}/v1`);
}

async function api(path: string, params: Record<string, string> = {}): Promise<any> {
  const url = new URL(`${await base()}${path}`);
  for (const [key, value] of Object.entries(params)) if (value) url.searchParams.set(key, value);
  url.searchParams.set("app_name", APP_NAME);
  const response = await fetch(url.toString(), {
    headers: { Accept: "application/json" },
    cf: { cacheTtl: CACHE_SECONDS, cacheEverything: true },
  } as RequestInit);
  if (!response.ok) throw new Error(`Audius ${response.status}`);
  const body = (await response.json()) as { data?: unknown };
  return body.data;
}

function art(item: Json | undefined, size = "480x480"): string | undefined {
  const artwork = item?.artwork ?? item?.profile_picture;
  if (!artwork) return undefined;
  return artwork[size] ?? artwork["1000x1000"] ?? artwork["150x150"];
}

function playable(track: Json): boolean {
  return track && track.is_streamable !== false && !track.stream_conditions && !track.is_delete;
}

async function streamUrl(trackId: string): Promise<string> {
  return `${await base()}/tracks/${encodeURIComponent(trackId)}/stream?app_name=${APP_NAME}`;
}

async function trackVideo(track: Json, index: number): Promise<Json> {
  return {
    id: String(track.id),
    title: track.title,
    artist: track.user?.name,
    duration: track.duration,
    number: index + 1,
    thumbnail: art(track),
    streams: [{ url: await streamUrl(String(track.id)), title: "Audius" }],
  };
}

function trackMeta(track: Json): Json {
  return {
    id: String(track.id),
    type: "track",
    name: track.title,
    title: track.title,
    artist: track.user?.name,
    duration: track.duration,
    poster: art(track),
  };
}

function playlistMeta(playlist: Json): Json {
  return {
    id: `playlist:${playlist.id}`,
    type: "album",
    name: playlist.playlist_name,
    artist: playlist.user?.name,
    artistId: playlist.user?.id ? String(playlist.user.id) : undefined,
    poster: art(playlist),
    trackCount: playlist.track_count,
  };
}

function artistMeta(user: Json): Json {
  return {
    id: String(user.id),
    type: "artist",
    name: user.name,
    poster: art(user),
    albumCount: user.album_count,
  };
}

async function trendingTracks(genre: string): Promise<Json[]> {
  const tracks = ((await api("/tracks/trending", { time: "week", genre, limit: "100" })) ?? []) as Json[];
  return tracks.filter(playable);
}

async function catalog(id: string, search?: string): Promise<Json[]> {
  if (id === "search") {
    if (!search) return [];
    const [tracks, playlists, users] = await Promise.all([
      api("/tracks/search", { query: search, limit: "30" }).catch(() => []),
      api("/playlists/search", { query: search, limit: "20" }).catch(() => []),
      api("/users/search", { query: search, limit: "12" }).catch(() => []),
    ]);
    return [
      ...((users ?? []) as Json[]).map(artistMeta),
      ...((playlists ?? []) as Json[]).map(playlistMeta),
      ...((tracks ?? []) as Json[]).filter(playable).map(trackMeta),
    ];
  }
  if (id === "trending") {
    const charts = await Promise.all(
      GENRES.map(async ([genre, label]) => {
        const tracks = await trendingTracks(genre).catch(() => []);
        if (!tracks.length) return null;
        return {
          id: `trending:${genre || "all"}`,
          type: "album",
          name: `${label}: trending`,
          artist: "Audius",
          poster: art(tracks[0]),
          trackCount: tracks.length,
        };
      }),
    );
    return charts.filter(Boolean) as Json[];
  }
  if (id === "playlists") {
    const playlists = ((await api("/playlists/trending", { time: "week", limit: "40" })) ?? []) as Json[];
    return playlists.map(playlistMeta);
  }
  return [];
}

async function albumMeta(id: string): Promise<Json | null> {
  if (id.startsWith("trending:")) {
    const key = id.slice("trending:".length);
    const genre = key === "all" ? "" : key;
    const label = GENRES.find(([g]) => g === genre)?.[1] ?? genre;
    const tracks = await trendingTracks(genre);
    return {
      id,
      type: "album",
      name: `${label}: trending`,
      artist: "Audius",
      poster: art(tracks[0]),
      videos: await Promise.all(tracks.map(trackVideo)),
    };
  }
  if (id.startsWith("playlist:")) {
    const playlistId = id.slice("playlist:".length);
    const [info, tracks] = await Promise.all([
      api(`/playlists/${encodeURIComponent(playlistId)}`),
      api(`/playlists/${encodeURIComponent(playlistId)}/tracks`),
    ]);
    const playlist = (Array.isArray(info) ? info[0] : info) as Json | undefined;
    if (!playlist) return null;
    const list = ((tracks ?? []) as Json[]).filter(playable);
    return { ...playlistMeta(playlist), videos: await Promise.all(list.map(trackVideo)) };
  }
  if (id.startsWith("user-tracks:")) {
    const userId = id.slice("user-tracks:".length);
    const [user, tracks] = await Promise.all([
      api(`/users/${encodeURIComponent(userId)}`),
      api(`/users/${encodeURIComponent(userId)}/tracks`, { limit: "100", sort: "date" }),
    ]);
    const list = ((tracks ?? []) as Json[]).filter(playable);
    return {
      id,
      type: "album",
      name: `${user?.name ?? "Artist"}: tracks`,
      artist: user?.name,
      artistId: userId,
      poster: art(user) ?? art(list[0]),
      videos: await Promise.all(list.map(trackVideo)),
    };
  }
  return null;
}

async function artistPage(userId: string): Promise<Json | null> {
  const user = (await api(`/users/${encodeURIComponent(userId)}`)) as Json | undefined;
  if (!user) return null;
  const albums = ((await api(`/users/${encodeURIComponent(userId)}/albums`, { limit: "50" }).catch(() => [])) ?? []) as Json[];
  const playlists = albums.length
    ? albums
    : (((await api(`/users/${encodeURIComponent(userId)}/playlists`, { limit: "50" }).catch(() => [])) ?? []) as Json[]);
  return {
    ...artistMeta(user),
    albums: [
      { id: `user-tracks:${userId}`, type: "album", name: `${user.name}: tracks`, artist: user.name, artistId: String(user.id), poster: art(user), trackCount: user.track_count },
      ...playlists.map(playlistMeta),
    ],
  };
}

function reply(body: unknown, status = 200): Response {
  return new Response(JSON.stringify(body), {
    status,
    headers: {
      "Content-Type": "application/json; charset=utf-8",
      "Access-Control-Allow-Origin": "*",
      "Cache-Control": `public, max-age=${CACHE_SECONDS}`,
    },
  });
}

/** Strips ".json" and decodes one path segment. */
function segment(value: string): string {
  return decodeURIComponent(value.replace(/\.json$/, ""));
}

export function isAudiusAddonPath(pathname: string): boolean {
  return pathname === PREFIX || pathname.startsWith(`${PREFIX}/`);
}

export async function audiusAddon(request: Request): Promise<Response> {
  const url = new URL(request.url);
  const parts = url.pathname.slice(PREFIX.length).split("/").filter(Boolean);
  try {
    if (parts.length === 0 || (parts.length === 1 && parts[0] === "manifest.json")) return reply(manifest);
    const [resource, type, id, extra] = parts;
    if (resource === "catalog" && type === "music" && id) {
      const extraValue = extra ? segment(extra) : "";
      const search = extraValue.startsWith("search=") ? extraValue.slice("search=".length) : undefined;
      return reply({ metas: await catalog(segment(id), search) });
    }
    if (resource === "meta" && id) {
      const metaId = segment(id);
      const meta = type === "artist" ? await artistPage(metaId) : type === "album" ? await albumMeta(metaId) : null;
      return meta ? reply({ meta }) : reply({ meta: null }, 404);
    }
    if (resource === "stream" && type === "track" && id) {
      return reply({ streams: [{ url: await streamUrl(segment(id)), title: "Audius" }] });
    }
    return reply({ error: "Not found" }, 404);
  } catch (error) {
    return reply({ error: error instanceof Error ? error.message : "Audius request failed" }, 502);
  }
}
