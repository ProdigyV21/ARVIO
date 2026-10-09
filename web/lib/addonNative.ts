import { jsonRequest, proxiedUrl } from "./http";
import { loadStored, saveStored } from "./storage";
import type { EpisodeInfo, InstalledAddon, MediaItem, MediaType } from "./types";

/**
 * Native addon items, ported from the Android app's AddonNativeCatalog: addon catalog
 * entries with no TMDB/IMDb identity whose addon serves its own metadata, such as a
 * broadcaster's VOD catalogue. Matching those to TMDB would hide everything TMDB doesn't
 * list, so they keep a stable negative id, take details and episodes from the addon's
 * /meta, and request streams by the addon's own video ids.
 *
 * Details, the player and watch history only carry the numeric id, so a registry in
 * localStorage maps it back to the addon. The id formula matches Android's, so a cloud
 * watch-history entry written by either app points at the same show.
 */

// `type` is how the item is shown; `addonType` is the addon's own type ("Podcasts", "tv") that
// its /meta and /stream answer to, when it differs.
type Entry = { addonId: string; manifestUrl: string; metaId: string; type: "movie" | "series"; addonType?: string; title: string; poster?: string };

export type AddonMetaPreview = { id?: string; name?: string; title?: string; poster?: string; background?: string; description?: string };

type AddonMetaVideo = {
  id?: string;
  title?: string;
  name?: string;
  season?: number;
  episode?: number;
  released?: string;
  thumbnail?: string;
  overview?: string;
  description?: string;
};

type AddonMeta = AddonMetaPreview & { releaseInfo?: string; year?: string | number; released?: string; videos?: AddonMetaVideo[] };

const REGISTRY_KEY = "arvio.web.addonNativeItems.v1";
const MAX_ENTRIES = 2000;
const META_TTL_MS = 30 * 60 * 1000;
// Ids TMDB lookups understand and the anime id families keep their existing handling.
const NON_NATIVE_ID_FAMILIES = ["tt", "tmdb:", "imdb:", "kitsu:", "mal:", "anilist:", "anidb:", "tvdb:"];

let registry: Map<number, Entry> | null = null;
let saveTimer: ReturnType<typeof setTimeout> | null = null;
const metas = new Map<number, { at: number; meta: Promise<AddonMeta | null> }>();

// Java's String.hashCode, so ids agree with the Android app.
function javaHash(value: string) {
  let hash = 0;
  for (let index = 0; index < value.length; index += 1) hash = (Math.imul(31, hash) + value.charCodeAt(index)) | 0;
  return hash;
}

export function nativeItemId(addonId: string, metaId: string) {
  return -Math.max(1, javaHash(`native:${addonId}:${metaId}`) & 0x7fffffff);
}

function entries() {
  if (registry) return registry;
  registry = new Map();
  const saved = loadStored<Record<string, Entry>>(REGISTRY_KEY, {});
  for (const [key, entry] of Object.entries(saved)) {
    const id = Number(key);
    if (id < 0 && entry?.metaId && entry.manifestUrl) registry.set(id, entry);
  }
  return registry;
}

function persistSoon() {
  if (saveTimer || typeof window === "undefined") return;
  saveTimer = setTimeout(() => {
    saveTimer = null;
    const map = entries();
    // Re-registered items move to the end, so the least recently seen go first.
    while (map.size > MAX_ENTRIES) map.delete(map.keys().next().value!);
    saveStored(REGISTRY_KEY, Object.fromEntries(map));
  }, 500);
}

/**
 * True when [addon] serves its own metadata for [contentId]: its manifest offers a `meta`
 * resource whose id prefixes cover that id.
 */
export function addonServesOwnMeta(addon: InstalledAddon, type: string, contentId: string) {
  const id = contentId.trim().toLowerCase();
  if (!id || addon.enabled === false || NON_NATIVE_ID_FAMILIES.some((family) => id.startsWith(family))) return false;
  const lower = type.trim().toLowerCase();
  const aliases = lower === "movie" || lower === "film" ? ["movie", "film"]
    : lower === "series" || lower === "tv" || lower === "show" ? ["series", "tv", "show"]
    : [lower];
  return (addon.resources ?? []).some((resource) => {
    const name = typeof resource === "string" ? resource : resource.name;
    if (name?.toLowerCase() !== "meta") return false;
    const types = typeof resource === "string" ? undefined : resource.types;
    if (types?.length && !types.some((value) => aliases.includes(value.trim().toLowerCase()))) return false;
    // An addon that names no prefix at all can't be said to own any id.
    const prefixes = (typeof resource === "string" ? undefined : resource.idPrefixes) ?? addon.idPrefixes ?? [];
    return prefixes.some((prefix) => prefix.trim() && id.startsWith(prefix.trim().toLowerCase()));
  });
}

/**
 * The addon in [addons] (other than [excludeAddonId]) that serves its own metadata for
 * [contentId]. Metadata addons such as AIOMetadata re-list another addon's catalog (a channel
 * addon's own ids) without serving its metadata or streams; the owner does.
 */
export function findAddonServingOwnMeta(addons: InstalledAddon[], type: string, contentId: string, excludeAddonId?: string) {
  return addons.find((addon) => addon.id !== excludeAddonId && addonServesOwnMeta(addon, type, contentId)) ?? null;
}

function cardFor(id: number, entry: Entry): MediaItem {
  return {
    id,
    title: entry.title,
    overview: "",
    year: "",
    mediaType: entry.type === "series" ? "tv" : "movie",
    image: entry.poster ?? "",
    backdrop: null,
    rating: "",
    duration: "",
    addonNativeId: entry.metaId,
    addonNativeAddonId: entry.addonId
  };
}

/** Builds (and remembers) the card for a catalog entry, or null when it has no id or title. */
export function registerNativeItem(addon: InstalledAddon, meta: AddonMetaPreview, mediaType: MediaType, addonType?: string): MediaItem | null {
  const metaId = meta.id?.trim();
  const title = (meta.name ?? meta.title)?.trim();
  if (!metaId || !title) return null;
  const id = nativeItemId(addon.id, metaId);
  const entry: Entry = {
    addonId: addon.id,
    manifestUrl: addon.manifestUrl,
    metaId,
    type: mediaType === "tv" ? "series" : "movie",
    ...(addonType && addonType !== (mediaType === "tv" ? "series" : "movie") ? { addonType } : {}),
    title,
    ...(meta.poster ? { poster: meta.poster } : {})
  };
  const map = entries();
  if (JSON.stringify(map.get(id)) !== JSON.stringify(entry)) {
    map.delete(id);
    map.set(id, entry);
    persistSoon();
  }
  return { ...cardFor(id, entry), overview: meta.description ?? "", backdrop: meta.background ?? null };
}

export function isAddonNative(item: { id: number }) {
  return item.id < 0 && entries().has(item.id);
}

function manifestBase(manifestUrl: string) {
  const [path, query = ""] = manifestUrl.split("?");
  return { base: path.replace(/\/manifest\.json$/, "").replace(/\/+$/, ""), query };
}

async function fetchMeta(entry: Entry) {
  const { base, query } = manifestBase(entry.manifestUrl);
  const url = `${base}/meta/${encodeURIComponent(entry.addonType || entry.type)}/${encodeURIComponent(entry.metaId)}.json${query ? `?${query}` : ""}`;
  const payload = await jsonRequest<{ meta?: AddonMeta }>(url)
    .catch(() => jsonRequest<{ meta?: AddonMeta }>(proxiedUrl(url)));
  return payload?.meta ?? null;
}

function loadMeta(id: number, entry: Entry) {
  const cached = metas.get(id);
  if (cached && Date.now() - cached.at < META_TTL_MS) return cached.meta;
  const meta = fetchMeta(entry).catch(() => null);
  metas.set(id, { at: Date.now(), meta });
  // A failure is not remembered, so the next open retries.
  void meta.then((value) => { if (!value && metas.get(id)?.meta === meta) metas.delete(id); });
  return meta;
}

function yearOf(value: unknown) {
  return /(19|20)\d{2}/.exec(String(value ?? ""))?.[0];
}

/** Full details from the addon's /meta; the catalog card when that fails. Null for other items. */
export async function getNativeDetails(item: MediaItem): Promise<MediaItem | null> {
  const entry = entries().get(item.id);
  if (!entry) return null;
  const base = { ...cardFor(item.id, entry), ...item };
  const meta = await loadMeta(item.id, entry);
  if (!meta) return base;
  const videos = meta.videos ?? [];
  const seasonNumbers = [...new Set(videos.map((video) => video.season).filter((season): season is number => typeof season === "number" && season > 0))]
    .sort((a, b) => a - b);
  const firstRelease = videos.map((video) => video.released).filter(Boolean).sort()[0];
  return {
    ...base,
    title: meta.name?.trim() || base.title,
    overview: meta.description?.trim() || base.overview,
    image: meta.poster || base.image,
    backdrop: meta.background || base.backdrop,
    year: yearOf(meta.releaseInfo ?? meta.year ?? meta.released) ?? yearOf(firstRelease) ?? base.year,
    seasons: base.mediaType === "tv" ? seasonNumbers.map((seasonNumber) => ({
      id: seasonNumber,
      seasonNumber,
      name: `Season ${seasonNumber}`,
      episodeCount: videos.filter((video) => video.season === seasonNumber).length
    })) : [],
    numberOfSeasons: base.mediaType === "tv" ? seasonNumbers.length : null,
    numberOfEpisodes: base.mediaType === "tv" ? videos.length : null
  };
}

export async function getNativeSeasonEpisodes(id: number, season: number): Promise<EpisodeInfo[]> {
  const entry = entries().get(id);
  if (!entry) return [];
  const meta = await loadMeta(id, entry);
  return (meta?.videos ?? [])
    .filter((video): video is AddonMetaVideo & { episode: number } => video.season === season && typeof video.episode === "number")
    .sort((a, b) => a.episode - b.episode)
    .map((video) => ({
      // Episode ids only need to be unique within the show for the UI.
      id: javaHash(video.id ?? `${season}:${video.episode}`),
      episodeNumber: video.episode,
      seasonNumber: season,
      name: video.title?.trim() || video.name?.trim() || `Episode ${video.episode}`,
      overview: video.overview ?? video.description ?? "",
      still: video.thumbnail || undefined,
      airDate: video.released?.slice(0, 10) ?? ""
    }));
}

/**
 * The addon's own ids to request streams with: the show id for a movie, the matching
 * video's id for an episode. Null when [item] isn't a native addon item.
 */
export async function nativeStreamTarget(item: MediaItem, season?: number, episode?: number) {
  const entry = entries().get(item.id);
  if (!entry) return null;
  // requestType: what the owning addon's /stream answers to ("tv" for a live channel).
  const target = { metaId: entry.metaId, type: entry.type, requestType: entry.addonType || entry.type };
  if (entry.type === "movie") return { ...target, ids: [entry.metaId] };
  if (!season || !episode) return { ...target, ids: [] as string[] };
  const meta = await loadMeta(item.id, entry);
  const video = meta?.videos?.find((candidate) => candidate.season === season && candidate.episode === episode);
  return { ...target, ids: video?.id ? [video.id] : [] };
}
