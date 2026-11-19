import type { MediaType } from "./types";

/**
 * Everything a card shows beyond its list data — title logo, streaming-service
 * names, runtime, artwork fallback and the imdb id for the IMDb rating — from
 * ONE TMDB details call (append_to_response=images,external_ids,watch/providers)
 * instead of three separate requests per card. Cached in memory and in
 * localStorage, so a warm Home load makes no decoration requests at all.
 */
export type CardExtras = {
  logo: string | null;
  providers: string[];
  runtime: number;
  image: string;
  backdrop: string | null;
  imdbId: string | null;
};

type TmdbImage = { file_path?: string | null; iso_639_1?: string | null; vote_average?: number };
export type CardExtrasPayload = {
  runtime?: number;
  episode_run_time?: number[];
  poster_path?: string | null;
  backdrop_path?: string | null;
  external_ids?: { imdb_id?: string | null };
  images?: { logos?: TmdbImage[] };
  "watch/providers"?: { results?: Record<string, { flatrate?: Array<{ provider_name?: string }> }> };
};

type Stored = { l: string | null; p: string[]; r: number; i: string; b: string | null; m: string | null; at: number };

export const CARD_EXTRAS_KEY = "arvio.web.cardExtras.v1";
const MAX_ENTRIES = 900;
const TTL_MS = 7 * 24 * 60 * 60 * 1000;
// A failed lookup is remembered briefly so a broken title isn't retried on
// every render, but recovers within the session.
const FAILURE_TTL_MS = 2 * 60 * 1000;

export const CARD_EXTRAS_APPEND = "images,external_ids,watch/providers";
export const CARD_EXTRAS_IMAGE_LANGUAGES = "en,null";

/** Same pick as MediaRepository.getImages on Android: best-voted English logo, else language-less, else any. */
export function pickLogoPath(logos: TmdbImage[] | undefined): string | null {
  const list = logos ?? [];
  const pick =
    list.filter((logo) => logo.iso_639_1 === "en").sort((a, b) => (b.vote_average ?? 0) - (a.vote_average ?? 0))[0] ??
    list.find((logo) => logo.iso_639_1 === null) ??
    list[0];
  return pick?.file_path || null;
}

export function cardExtrasFromPayload(payload: CardExtrasPayload, imageUrl: (kind: "poster" | "backdrop" | "logo", path?: string | null) => string, region = "US"): CardExtras {
  const logoPath = pickLogoPath(payload.images?.logos);
  const providers = (payload["watch/providers"]?.results?.[region]?.flatrate ?? [])
    .map((provider) => provider.provider_name ?? "")
    .filter(Boolean)
    .slice(0, 3);
  return {
    logo: logoPath ? imageUrl("logo", logoPath) || null : null,
    providers,
    runtime: payload.runtime ?? payload.episode_run_time?.[0] ?? 0,
    image: imageUrl("poster", payload.poster_path),
    backdrop: imageUrl("backdrop", payload.backdrop_path) || null,
    imdbId: payload.external_ids?.imdb_id ?? null
  };
}

export function createCardExtrasCache({ request, imageUrl, load, save, now = () => Date.now(), persistDelayMs = 1200, setTimer = setTimeout }: {
  request: (mediaType: MediaType, id: number) => Promise<CardExtrasPayload>;
  imageUrl: (kind: "poster" | "backdrop" | "logo", path?: string | null) => string;
  load: () => Record<string, Stored> | null;
  save: (value: Record<string, Stored>) => void;
  now?: () => number;
  persistDelayMs?: number;
  setTimer?: (fn: () => void, ms: number) => unknown;
}) {
  const memory = new Map<string, Stored>();
  const failures = new Map<string, number>();
  const inflight = new Map<string, Promise<CardExtras | null>>();
  let restored = false;
  let persistPending = false;

  const restore = () => {
    if (restored) return;
    restored = true;
    const stored = load() ?? {};
    const cutoff = now() - TTL_MS;
    Object.entries(stored).forEach(([key, value]) => {
      if (value && typeof value === "object" && value.at > cutoff && !memory.has(key)) memory.set(key, value);
    });
  };
  const persist = () => {
    if (persistPending) return;
    persistPending = true;
    setTimer(() => {
      persistPending = false;
      const entries = [...memory.entries()].sort((a, b) => b[1].at - a[1].at).slice(0, MAX_ENTRIES);
      save(Object.fromEntries(entries));
    }, persistDelayMs);
  };
  const toExtras = (value: Stored): CardExtras => ({ logo: value.l, providers: value.p ?? [], runtime: value.r ?? 0, image: value.i ?? "", backdrop: value.b ?? null, imdbId: value.m ?? null });
  const keyOf = (mediaType: MediaType, id: number) => `${mediaType}:${id}`;

  return {
    /** Cached extras, synchronously (null = not fetched yet). */
    peek(mediaType: MediaType, id: number): CardExtras | null {
      if (!(id > 0)) return null;
      restore();
      const hit = memory.get(keyOf(mediaType, id));
      return hit ? toExtras(hit) : null;
    },
    /** Extras for one title: cache first, else exactly one request (deduplicated). */
    get(mediaType: MediaType, id: number): Promise<CardExtras | null> {
      if (!(id > 0)) return Promise.resolve(null);
      restore();
      const key = keyOf(mediaType, id);
      const hit = memory.get(key);
      if (hit) return Promise.resolve(toExtras(hit));
      const failedAt = failures.get(key);
      if (failedAt && now() - failedAt < FAILURE_TTL_MS) return Promise.resolve(null);
      const existing = inflight.get(key);
      if (existing) return existing;
      const task = request(mediaType, id).then((payload) => {
        const extras = cardExtrasFromPayload(payload, imageUrl);
        memory.set(key, { l: extras.logo, p: extras.providers, r: extras.runtime, i: extras.image, b: extras.backdrop, m: extras.imdbId, at: now() });
        failures.delete(key);
        persist();
        return extras;
      }, () => {
        failures.set(key, now());
        return null;
      }).finally(() => { inflight.delete(key); });
      inflight.set(key, task);
      return task;
    },
    size: () => memory.size
  };
}
