import { jsonRequest, proxiedUrl } from "./http";
import { loadStored, saveStored } from "./storage";
import type { AddonCatalog, InstalledAddon, MediaItem, StreamSource, SubtitleTrack } from "./types";

const ADDON_KEY = "arvio.web.installed.addons";

type RawManifest = {
  id?: string;
  name?: string;
  version?: string;
  description?: string;
  logo?: string;
  background?: string;
  catalogs?: AddonCatalog[];
  resources?: Array<string | { name?: string }>;
};

type RawStream = {
  name?: string;
  title?: string;
  url?: string;
  externalUrl?: string;
  infoHash?: string;
  fileIdx?: number;
  behaviorHints?: StreamSource["behaviorHints"];
  subtitles?: SubtitleTrack[];
  sources?: string[];
  description?: string;
};

function normalizeManifestUrl(raw: string) {
  const trimmed = raw.trim();
  if (trimmed.endsWith("/manifest.json")) return trimmed;
  return `${trimmed.replace(/\/+$/, "")}/manifest.json`;
}

function baseFromManifest(manifestUrl: string) {
  return manifestUrl.replace(/\/manifest\.json$/, "");
}

export function loadLocalAddons() {
  return loadStored<InstalledAddon[]>(ADDON_KEY, []);
}

export function saveLocalAddons(addons: InstalledAddon[]) {
  saveStored(ADDON_KEY, addons);
}

export async function installAddon(rawUrl: string) {
  const manifestUrl = normalizeManifestUrl(rawUrl);
  const manifest = await jsonRequest<RawManifest>(proxiedUrl(manifestUrl));
  const resources = (manifest.resources ?? []).map((resource) =>
    typeof resource === "string" ? resource : resource.name ?? "resource"
  );
  return {
    id: manifest.id ?? manifestUrl,
    name: manifest.name ?? "Unnamed addon",
    version: manifest.version ?? "1.0.0",
    manifestUrl,
    description: manifest.description ?? null,
    catalogs: manifest.catalogs ?? [],
    resources,
    logo: manifest.logo ?? null,
    background: manifest.background ?? null,
    enabled: true
  } satisfies InstalledAddon;
}

export async function getStreams(addons: InstalledAddon[], item: MediaItem, season?: number, episode?: number) {
  const type = item.mediaType === "tv" ? "series" : "movie";
  const id = item.mediaType === "tv" && season && episode ? `tmdb:${item.id}:${season}:${episode}` : `tmdb:${item.id}`;
  const requests = addons
    .filter((addon) => addon.enabled !== false && addon.resources.includes("stream"))
    .map(async (addon) => {
      const url = `${baseFromManifest(addon.manifestUrl)}/stream/${type}/${encodeURIComponent(id)}.json`;
      try {
        const payload = await jsonRequest<{ streams?: RawStream[] }>(proxiedUrl(url));
        return (payload.streams ?? []).map((stream) => ({
          source: stream.name ?? stream.title ?? addon.name,
          addonName: addon.name,
          addonId: addon.id,
          quality: detectQuality(stream.title ?? stream.name ?? ""),
          size: "",
          url: stream.url ?? stream.externalUrl ?? null,
          infoHash: stream.infoHash ?? null,
          fileIdx: stream.fileIdx ?? null,
          behaviorHints: stream.behaviorHints ?? null,
          subtitles: stream.subtitles ?? [],
          sources: stream.sources ?? [],
          description: stream.description ?? stream.title ?? null
        }));
      } catch {
        return [] as StreamSource[];
      }
    });
  return sortStreams((await Promise.all(requests)).flat().filter((stream) => stream.url || stream.infoHash));
}

function detectQuality(value: string) {
  const text = value.toLowerCase();
  if (text.includes("2160") || text.includes("4k")) return "4K";
  if (text.includes("1080")) return "1080p";
  if (text.includes("720")) return "720p";
  if (text.includes("hdr")) return "HDR";
  return "HD";
}

function sortStreams(streams: StreamSource[]) {
  return streams.sort((a, b) => streamScore(b) - streamScore(a));
}

function streamScore(stream: StreamSource) {
  const text = `${stream.quality ?? ""} ${stream.source} ${stream.description ?? ""}`.toLowerCase();
  let score = 0;
  if (stream.url) score += 100;
  if (stream.behaviorHints?.cached) score += 80;
  if (text.includes("2160") || text.includes("4k")) score += 60;
  if (text.includes("1080")) score += 45;
  if (text.includes("720")) score += 25;
  if (text.includes("hdr")) score += 12;
  if (text.includes("cam")) score -= 80;
  if (stream.infoHash && !stream.url) score -= 50;
  if (stream.sizeBytes) score += Math.min(25, stream.sizeBytes / 1_000_000_000);
  return score;
}
