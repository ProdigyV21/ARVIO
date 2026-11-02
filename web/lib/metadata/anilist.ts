import type { MediaItem } from "../types";
import type { MetadataMediaType, MetadataResolver } from "./types";

const ANILIST_GRAPHQL_ENDPOINT = "https://graphql.anilist.co";

// AniList allows ~90 requests a minute and answers a burst with 429 without
// CORS headers (the browser reports a CORS error). Home rows full of anime
// used to fire dozens at once. Requests are now cached, deduplicated, sent
// one after another with a small gap, and paused for a minute after a refusal.
const MIN_GAP_MS = 700;
const COOLDOWN_MS = 60_000;
const responses = new Map<string, Promise<any | null>>();
let queue: Promise<unknown> = Promise.resolve();
let lastSent = 0;
let pausedUntil = 0;

function anilistQuery(query: string, variables: Record<string, unknown>): Promise<any | null> {
  const key = JSON.stringify([query, variables]);
  const cached = responses.get(key);
  if (cached) return cached;
  if (Date.now() < pausedUntil) return Promise.resolve(null);
  const run = queue.then(async () => {
    if (Date.now() < pausedUntil) return null;
    const wait = lastSent + MIN_GAP_MS - Date.now();
    if (wait > 0) await new Promise((resolve) => setTimeout(resolve, wait));
    lastSent = Date.now();
    try {
      const res = await fetch(ANILIST_GRAPHQL_ENDPOINT, {
        method: "POST",
        headers: { "Content-Type": "application/json", Accept: "application/json" },
        body: JSON.stringify({ query, variables })
      });
      if (res.status === 429 || res.status >= 500) { pausedUntil = Date.now() + COOLDOWN_MS; return null; }
      if (!res.ok) return null;
      return await res.json();
    } catch {
      // A rate-limit refusal surfaces as a CORS/network error.
      pausedUntil = Date.now() + COOLDOWN_MS;
      return null;
    }
  });
  queue = run.catch(() => null);
  const result = run.then((json) => { if (json === null) responses.delete(key); return json; });
  responses.set(key, result);
  if (responses.size > 400) responses.delete(responses.keys().next().value!);
  return result;
}

const MEDIA_QUERY = `
query ($id: Int, $search: String) {
  Media(id: $id, search: $search, type: ANIME) {
    id
    idMal
    title {
      romaji
      english
      native
    }
    description
    bannerImage
    coverImage {
      extraLarge
      large
      medium
      color
    }
    format
    status
    episodes
    duration
    averageScore
    popularity
    genres
    season
    seasonYear
    studios(isMain: true) {
      nodes {
        id
        name
      }
    }
  }
}
`;

const SEARCH_QUERY = `
query ($search: String) {
  Page(page: 1, perPage: 20) {
    media(search: $search, type: ANIME) {
      id
      idMal
      title {
        romaji
        english
        native
      }
      description
      bannerImage
      coverImage {
        large
        medium
      }
      averageScore
      seasonYear
      episodes
    }
  }
}
`;

function mapAniListToMediaItem(media: any): MediaItem {
  const title = media.title?.english || media.title?.romaji || media.title?.native || "Untitled Anime";
  const poster = media.coverImage?.extraLarge || media.coverImage?.large || media.coverImage?.medium || null;
  const rating = media.averageScore ? (media.averageScore / 10).toFixed(1) : undefined;

  return {
    id: media.id,
    anilistId: media.id,
    title,
    subtitle: media.title?.romaji !== title ? media.title?.romaji : undefined,
    overview: media.description ? media.description.replace(/<[^>]*>?/gm, "") : "",
    year: media.seasonYear ? String(media.seasonYear) : undefined,
    rating,
    duration: media.duration ? `${media.duration}m` : undefined,
    mediaType: "tv",
    isAnime: true,
    image: poster ?? undefined,
    backdrop: media.bannerImage ?? poster ?? null,
    badge: media.format ?? "ANIME",
    genres: media.genres ?? [],
    status: media.status,
    numberOfEpisodes: media.episodes ?? null
  };
}

export const aniListResolver: MetadataResolver = {
  id: "anilist",
  name: "AniList",
  supportedTypes: ["anime"],

  async getDetails(id: string | number, _mediaType?: MetadataMediaType): Promise<MediaItem | null> {
    try {
      const isNumeric = !isNaN(Number(id));
      const variables = isNumeric ? { id: Number(id) } : { search: String(id) };

      const json = await anilistQuery(MEDIA_QUERY, variables);
      if (!json?.data?.Media) return null;

      return mapAniListToMediaItem(json.data.Media);
    } catch {
      return null;
    }
  },

  async search(query: string, _mediaType?: MetadataMediaType): Promise<MediaItem[]> {
    try {
      const json = await anilistQuery(SEARCH_QUERY, { search: query });
      const items = json?.data?.Page?.media ?? [];
      return items.map(mapAniListToMediaItem);
    } catch {
      return [];
    }
  }
};
