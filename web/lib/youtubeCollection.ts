import { nativeItemId } from "./addonNative";
import { loadStored, saveStored } from "./storage";
import type { CatalogConfig, MediaItem, StreamSource } from "./types";

/**
 * The profile's own YouTube row, ported from the Android app's YouTubeCollection. The
 * row is a "youtube" catalog whose sourceRef is `youtube:` + a JSON array of { id, title }
 * in Home order. Its videos are cards with the same negative ids Android gives them, so a
 * cloud watch-history entry from either app points at the same video. They play through
 * the player's YouTube embed: their one source is the video's YouTube page.
 */

export const YOUTUBE_SOURCE_REF_PREFIX = "youtube:";
const ADDON_ID = "arvio_youtube";
const META_ID_PREFIX = "ytv:";
const VIDEO_ID = /^[A-Za-z0-9_-]{11}$/;
const REGISTRY_KEY = "arvio.web.youtubeItems.v1";
const MAX_ENTRIES = 2000;

type Video = { id: string; title: string };

let registry: Map<number, Video> | null = null;
let saveTimer: ReturnType<typeof setTimeout> | null = null;

function entries() {
  if (registry) return registry;
  registry = new Map();
  const saved = loadStored<Record<string, Video>>(REGISTRY_KEY, {});
  for (const [key, video] of Object.entries(saved)) {
    const id = Number(key);
    if (id < 0 && video && VIDEO_ID.test(String(video.id))) registry.set(id, video);
  }
  return registry;
}

function persistSoon() {
  if (saveTimer || typeof window === "undefined") return;
  saveTimer = setTimeout(() => {
    saveTimer = null;
    const map = entries();
    while (map.size > MAX_ENTRIES) map.delete(map.keys().next().value!);
    saveStored(REGISTRY_KEY, Object.fromEntries(map));
  }, 500);
}

export function isYouTubeCatalogRef(sourceRef: unknown) {
  return typeof sourceRef === "string" && sourceRef.startsWith(YOUTUBE_SOURCE_REF_PREFIX);
}

export function decodeYouTubeVideos(sourceRef: string | null | undefined): Video[] {
  if (!isYouTubeCatalogRef(sourceRef)) return [];
  let parsed: unknown;
  try { parsed = JSON.parse(sourceRef!.slice(YOUTUBE_SOURCE_REF_PREFIX.length)); } catch { return []; }
  if (!Array.isArray(parsed)) return [];
  const seen = new Set<string>();
  const videos: Video[] = [];
  for (const raw of parsed) {
    const id = raw && typeof raw === "object" ? (raw as { id?: unknown }).id : undefined;
    if (typeof id !== "string" || !VIDEO_ID.test(id) || seen.has(id)) continue;
    seen.add(id);
    const title = (raw as { title?: unknown }).title;
    videos.push({ id, title: typeof title === "string" && title.trim() ? title : id });
  }
  return videos;
}

export function youTubeItemId(videoId: string) {
  return nativeItemId(ADDON_ID, META_ID_PREFIX + videoId);
}

function cardFor(id: number, video: Video): MediaItem {
  return {
    id,
    title: video.title,
    mediaType: "movie",
    addonNativeId: META_ID_PREFIX + video.id,
    addonNativeAddonId: ADDON_ID,
    image: `https://i.ytimg.com/vi/${video.id}/hqdefault.jpg`,
    backdrop: `https://i.ytimg.com/vi/${video.id}/maxresdefault.jpg`,
    overview: ""
  };
}

/** The row's videos as Home cards, in the user's order. */
export function youTubeCatalogItems(catalog: CatalogConfig): MediaItem[] {
  const map = entries();
  return decodeYouTubeVideos(catalog.sourceRef).map((video) => {
    const id = youTubeItemId(video.id);
    if (JSON.stringify(map.get(id)) !== JSON.stringify(video)) {
      map.delete(id);
      map.set(id, video);
      persistSoon();
    }
    return cardFor(id, video);
  });
}

export function isYouTubeItem(item: { id: number; addonNativeId?: string | null; addonNativeAddonId?: string | null }) {
  if (item.id >= 0) return false;
  if (entries().has(item.id)) return true;
  const videoId = item.addonNativeId?.startsWith(META_ID_PREFIX) ? item.addonNativeId.slice(META_ID_PREFIX.length) : "";
  if (item.addonNativeAddonId !== ADDON_ID || !VIDEO_ID.test(videoId) || youTubeItemId(videoId) !== item.id) return false;
  entries().set(item.id, { id: videoId, title: videoId });
  persistSoon();
  return true;
}

/** The video's card; YouTube videos have no other metadata to fetch. */
export function youTubeDetails(item: MediaItem): MediaItem | null {
  const video = entries().get(item.id);
  return video ? { ...cardFor(item.id, video), ...item, title: item.title || video.title } : null;
}

/** The video's single source: its YouTube page, which the player shows through YouTube's embed. */
export function youTubeStreams(item: MediaItem): StreamSource[] {
  const video = entries().get(item.id);
  if (!video) return [];
  return [{
    source: "YouTube",
    addonName: "YouTube",
    addonId: ADDON_ID,
    quality: "YouTube",
    url: `https://www.youtube.com/watch?v=${video.id}`
  }];
}
