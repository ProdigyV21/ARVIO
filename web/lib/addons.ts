import { addonServesOwnMeta, nativeStreamTarget } from "./addonNative";
import { jsonRequest, proxiedUrl } from "./http";
import { hasResolverConfig } from "./config";
import { getResolverStreamsProgressive } from "./resolver";
import { loadStored, saveStored } from "./storage";
import { isBrowserPlayableStream, isIosPlayableStream } from "./streamCompatibility";
import { isInformationalAddonStream } from "./addonStreamInfo";
import type { AddonCatalog, InstalledAddon, MediaItem, StreamSource, SubtitleTrack } from "./types";

const ADDON_KEY = "arvio.web.installed.addons";

type RawManifest = {
  id?: string;
  name?: string;
  version?: string;
  description?: string;
  logo?: string;
  background?: string;
  types?: string[];
  idPrefixes?: string[];
  catalogs?: AddonCatalog[] | Record<string, unknown>;
  resources?: Array<string | { name?: string; types?: string[]; idPrefixes?: string[] }>;
};

type RawStream = {
  name?: string;
  title?: string;
  url?: string;
  externalUrl?: string;
  ytId?: string;
  infoHash?: string;
  fileIdx?: number;
  behaviorHints?: StreamSource["behaviorHints"];
  subtitles?: SubtitleTrack[];
  sources?: string[];
  description?: string;
  size?: string;
  sizeBytes?: number;
};

type RawSubtitle = {
  id?: string;
  url?: string;
  lang?: string;
  label?: string;
  name?: string;
};

function normalizeManifestUrl(raw: string) {
  const trimmed = raw.trim();
  try {
    const url = new URL(trimmed);
    if (!url.pathname.endsWith("/manifest.json")) {
      url.pathname = `${url.pathname.replace(/\/+$/, "")}/manifest.json`;
    }
    return url.toString();
  } catch {
    if (trimmed.endsWith("/manifest.json")) return trimmed;
    return `${trimmed.replace(/\/+$/, "")}/manifest.json`;
  }
}

function baseFromManifest(manifestUrl: string) {
  return addonBaseUrl(manifestUrl).base;
}

function addonBaseUrl(manifestUrl: string) {
  try {
    const url = new URL(manifestUrl);
    const query = url.searchParams.toString();
    url.search = "";
    url.pathname = url.pathname.replace(/\/manifest\.json$/, "").replace(/\/+$/, "");
    return { base: url.toString().replace(/\/+$/, ""), query };
  } catch {
    const [path, query = ""] = manifestUrl.split("?");
    return { base: path.replace(/\/manifest\.json$/, "").replace(/\/+$/, ""), query };
  }
}

function manifestUrlFor(addon: InstalledAddon) {
  const compatible = addon as InstalledAddon & { url?: string; transportUrl?: string };
  const raw = compatible.manifestUrl || compatible.url || compatible.transportUrl || "";
  return raw ? normalizeManifestUrl(raw) : "";
}

function normalizeResources(resources: unknown) {
  if (!Array.isArray(resources)) return [];
  const normalized = resources.map((resource) => {
    if (typeof resource === "string") return resource;
    if (resource && typeof resource === "object") {
      const name = (resource as { name?: unknown }).name;
      if (typeof name !== "string" || !name) return "";
      const raw = resource as { types?: unknown; idPrefixes?: unknown };
      return {
        name,
        types: Array.isArray(raw.types) ? raw.types.filter((type): type is string => typeof type === "string") : undefined,
        idPrefixes: Array.isArray(raw.idPrefixes) ? raw.idPrefixes.filter((prefix): prefix is string => typeof prefix === "string") : undefined
      };
    }
    return "";
  }).filter(Boolean);
  const seen = new Set<string>();
  return normalized.filter((resource) => {
    const key = typeof resource === "string"
      ? resource
      : `${resource.name}|${resource.types?.join(",") ?? ""}|${resource.idPrefixes?.join(",") ?? ""}`;
    if (seen.has(key)) return false;
    seen.add(key);
    return true;
  });
}

function stringList(values: unknown) {
  return Array.isArray(values) ? values.filter((value): value is string => typeof value === "string") : undefined;
}

function normalizeCatalogs(catalogs: unknown) {
  return Array.isArray(catalogs) ? catalogs.filter((catalog): catalog is AddonCatalog => Boolean(catalog && typeof catalog === "object")) : [];
}

export function normalizeAddon(addon: unknown): InstalledAddon | null {
  if (!addon || typeof addon !== "object") return null;
  const raw = addon as Partial<InstalledAddon> & {
    url?: string;
    transportUrl?: string;
    description?: string | null;
    logo?: string | null;
    background?: string | null;
    isEnabled?: boolean;
    manifest?: RawManifest | null;
  };
  const manifestUrl = manifestUrlFor(raw as InstalledAddon);
  if (!manifestUrl) return null;
  const id = typeof raw.id === "string" && raw.id ? raw.id : manifestUrl;
  const enabled = raw.enabled !== false && raw.isEnabled !== false;
  // Android-installed entries carry catalogs/resources/types/idPrefixes only
  // inside `manifest`; without this fallback their collections and catalog
  // rows cannot find the addon on web.
  const manifest = raw.manifest && typeof raw.manifest === "object" ? raw.manifest : undefined;
  const pick = <T,>(own: T[] | undefined, fallback: unknown) =>
    Array.isArray(own) && own.length ? own : Array.isArray(fallback) ? fallback : own;
  return {
    // CRITICAL: preserve every field we don't understand. The Android app's
    // addon entries carry `type`/`url`/`transportUrl`/`isInstalled`/`manifest`
    // — rebuilding the object from a fixed field list stripped those on every
    // web push, leaving the APK unable to parse the cloud addons (its enum
    // `type` became null), which is what kept desyncing addon lists between
    // web and Android.
    ...(raw as Record<string, unknown>),
    id,
    name: typeof raw.name === "string" && raw.name ? raw.name : "Unnamed addon",
    version: typeof raw.version === "string" && raw.version ? raw.version : "1.0.0",
    manifestUrl,
    description: raw.description ?? null,
    catalogs: normalizeCatalogs(pick(raw.catalogs, manifest?.catalogs)),
    resources: normalizeResources(pick(raw.resources, manifest?.resources)),
    types: stringList(pick(raw.types, manifest?.types)),
    idPrefixes: stringList(pick(raw.idPrefixes, manifest?.idPrefixes)),
    logo: raw.logo ?? null,
    background: raw.background ?? null,
    enabled,
    // Mirror the Android flag so a web-side enable/disable round-trips.
    isEnabled: enabled
  } as InstalledAddon;
}

export function normalizeAddons(addons: unknown) {
  if (!Array.isArray(addons)) return [];
  const seen = new Set<string>();
  return addons.map(normalizeAddon).filter((addon): addon is InstalledAddon => {
    if (!addon) return false;
    const key = addon.manifestUrl || addon.id;
    if (seen.has(key)) return false;
    seen.add(key);
    return true;
  });
}

function supportsResource(addon: InstalledAddon, resource: string) {
  if (!Array.isArray(addon.resources) || addon.resources.length === 0) return true;
  return addon.resources
    .map((item) => typeof item === "string" ? item : (item as { name?: string } | null)?.name)
    .filter(Boolean)
    .includes(resource);
}

export function loadLocalAddons() {
  return normalizeAddons(loadStored<unknown[]>(ADDON_KEY, []));
}

export function saveLocalAddons(addons: InstalledAddon[]) {
  saveStored(ADDON_KEY, normalizeAddons(addons));
}

export async function installAddon(rawUrl: string) {
  const manifestUrl = normalizeManifestUrl(rawUrl);
  const manifest = await addonJsonRequest<RawManifest>(manifestUrl);
  const resourceNames = (manifest.resources ?? [])
    .map((resource) => typeof resource === "string" ? resource : (resource as { name?: string } | null)?.name ?? "")
    .filter(Boolean);
  const subtitleOnly = resourceNames.includes("subtitles") && !resourceNames.includes("stream");
  return normalizeAddon({
    id: manifest.id ?? manifestUrl,
    name: manifest.name ?? "Unnamed addon",
    version: manifest.version ?? "1.0.0",
    manifestUrl,
    description: manifest.description ?? null,
    catalogs: manifest.catalogs,
    resources: manifest.resources,
    types: manifest.types,
    idPrefixes: manifest.idPrefixes,
    logo: manifest.logo ?? null,
    background: manifest.background ?? null,
    enabled: true,
    // Android-app compatibility: its Addon model needs these to parse and use
    // a cloud-synced entry (see normalizeAddon comment).
    isInstalled: true,
    isEnabled: true,
    type: subtitleOnly ? "SUBTITLE" : "COMMUNITY",
    runtimeKind: "STREMIO",
    installSource: "DIRECT_URL",
    url: manifestUrl,
    transportUrl: manifestUrl.replace(/\/manifest\.json$/, ""),
    manifest: {
      id: manifest.id ?? manifestUrl,
      name: manifest.name ?? "Unnamed addon",
      version: manifest.version ?? "1.0.0",
      description: manifest.description ?? "",
      logo: manifest.logo ?? null,
      // The Android app's Gson models require OBJECT resources/catalogs — a
      // single plain-string resource (e.g. "stream") makes its entire addon
      // apply throw, which is how addon lists desynced. Normalize here.
      resources: (manifest.resources ?? [])
        .map((resource) => typeof resource === "string"
          ? { name: resource, types: [], idPrefixes: null }
          : { name: (resource as { name?: string })?.name ?? "", types: (resource as { types?: string[] })?.types ?? [], idPrefixes: (resource as { idPrefixes?: string[] })?.idPrefixes ?? null })
        .filter((resource) => resource.name),
      types: manifest.types ?? [],
      idPrefixes: manifest.idPrefixes ?? [],
      catalogs: (Array.isArray(manifest.catalogs) ? manifest.catalogs : []).map((catalog) => ({
        type: String((catalog as { type?: string })?.type ?? "movie"),
        id: String((catalog as { id?: string })?.id ?? ""),
        name: String((catalog as { name?: string })?.name ?? (catalog as { id?: string })?.id ?? "")
      })),
      behaviorHints: {}
    }
  })!;
}

export async function getStreams(addons: InstalledAddon[], item: MediaItem, season?: number, episode?: number) {
  return getStreamsProgressive(addons, item, season, episode);
}

export async function getStreamsProgressive(
  addons: InstalledAddon[],
  item: MediaItem,
  season?: number,
  episode?: number,
  onUpdate?: (streams: StreamSource[], batch: StreamSource[]) => void
) {
  const update = onUpdate ? (streams: StreamSource[], batch: StreamSource[]) =>
    onUpdate(streams.filter(stream => !isInformationalAddonStream(stream)), batch.filter(stream => !isInformationalAddonStream(stream))) : undefined;
  // Native addon items are only known to the addon that serves them, by its own ids.
  const native = await nativeStreamTarget(item, season, episode);
  if (native) {
    if (!native.ids.length) return [];
    const owners = addons.filter((addon) => addonServesOwnMeta(addon, native.requestType, native.metaId));
    return getBrowserStreamsProgressive(owners, item, season, episode, onUpdate, native.ids, native.requestType);
  }
  if (hasResolverConfig()) {
    const browserPromise = getBrowserStreamsProgressive(addons, item, season, episode, update)
      .catch((error) => {
        console.warn("ARVIO browser addon lookup failed.", error);
        return [] as StreamSource[];
      });
    const resolverPromise = getResolverStreamsProgressive(addons, item, season, episode, update)
      .catch((error) => {
        console.warn("ARVIO resolver unavailable, using browser addon lookup.", error);
        return [] as StreamSource[];
      });
    const [browserResolved, resolved] = await Promise.all([browserPromise, resolverPromise]);
    const merged = sortStreams(dedupeStreams([...browserResolved, ...resolved])
      .filter((stream) => !isInformationalAddonStream(stream) && (stream.url || stream.infoHash || stream.description || stream.source)));
    if (merged.length) onUpdate?.(merged, browserResolved);
    return merged;
  }

  return getBrowserStreamsProgressive(addons, item, season, episode, onUpdate);
}

async function getBrowserStreamsProgressive(
  addons: InstalledAddon[],
  item: MediaItem,
  season?: number,
  episode?: number,
  onUpdate?: (streams: StreamSource[], batch: StreamSource[]) => void,
  nativeIds?: string[],
  // A native item's own type ("tv", "Podcasts"); strict addons route /stream by it.
  nativeType?: string
) {
  const type = item.mediaType === "tv" ? "series" : "movie";
  const ids = nativeIds ?? streamIds(item, season, episode);
  const enabled = addons.filter((addon) => addon.enabled !== false && manifestUrlFor(addon) && supportsResource(addon, "stream"));
  const subtitleAddons = addons.filter((addon) => addon.enabled !== false && manifestUrlFor(addon) && supportsResource(addon, "subtitles"));
  // OpenSubtitles is always available as a built-in subtitle source (like the
  // Android app) — most stream addons don't provide subtitles at all. Exact
  // manifest match only: some users carry an OpenSubtitles entry with a broken
  // base path (…/subtitles/manifest.json) that returns empty results. It only
  // knows IMDb/TMDB ids, so native addon items skip it.
  if (!nativeIds && !subtitleAddons.some((addon) => manifestUrlFor(addon) === BUILTIN_OPENSUBTITLES.manifestUrl)) {
    subtitleAddons.push(BUILTIN_OPENSUBTITLES);
  }
  let aggregate: StreamSource[] = [];
  let sharedSubtitles: SubtitleTrack[] = [];
  const subtitlePromise = queryAllAddonSubtitles(subtitleAddons, type, ids)
    .then((subtitles) => {
      sharedSubtitles = subtitles;
      if (subtitles.length && aggregate.length) {
        aggregate = aggregate.map((stream) => attachSubtitles(stream, subtitles));
        onUpdate?.(aggregate, []);
      }
      return subtitles;
    })
    .catch(() => [] as SubtitleTrack[]);

  await Promise.all(enabled.map(async (addon) => {
    const batch = await queryAddonStreams(addon, type, ids, nativeType);
    if (!batch.length) return;
    const withSubtitles = batch.map((stream) => attachSubtitles(stream, sharedSubtitles));
    aggregate = sortStreams(dedupeStreams([...aggregate, ...withSubtitles])
      .filter((stream) => stream.url || stream.infoHash || stream.description || stream.source));
    onUpdate?.(aggregate, withSubtitles);
  }));
  const finalSubtitles = await subtitlePromise;
  if (finalSubtitles.length) aggregate = aggregate.map((stream) => attachSubtitles(stream, finalSubtitles));
  return aggregate;
}

async function queryAddonStreams(addon: InstalledAddon, type: "movie" | "series", ids: string[], preferredType?: string) {
  const { base, query } = addonBaseUrl(manifestUrlFor(addon));
  for (const id of ids) {
    if (!addonSupportsId(addon, id)) continue;
    for (const requestType of streamRequestTypes(addon, type, preferredType)) {
      const url = `${base}/stream/${encodeURIComponent(requestType)}/${encodeURIComponent(id)}.json${query ? `?${query}` : ""}`;
      try {
        const payload = await addonJsonRequest<{ streams?: RawStream[] }>(url);
        const streams = (payload.streams ?? []).map((stream) => normalizeStream(stream, addon))
          .filter(stream => !isInformationalAddonStream(stream));
        if (streams.length > 0) return streams;
      } catch {
        // Try the next compatible type/ID form. Torrentio and similar addons often
        // prefer IMDb IDs while some catalog sources only have TMDB IDs.
      }
    }
  }
  return [] as StreamSource[];
}

// Public OpenSubtitles v3 Stremio addon — keyless, used as the built-in
// subtitle source when no installed addon provides subtitles.
const BUILTIN_OPENSUBTITLES: InstalledAddon = {
  id: "org.stremio.opensubtitlesv3",
  name: "OpenSubtitles",
  version: "1.0.0",
  manifestUrl: "https://opensubtitles-v3.strem.io/manifest.json",
  catalogs: [],
  resources: ["subtitles"],
  types: ["movie", "series"],
  enabled: true
};

// Fetch subtitles for one title on demand (used before handing a source to an
// external player). Mirrors the source-loader's subtitle-addon selection,
// including the built-in OpenSubtitles fallback.
export async function fetchSubtitlesForItem(
  addons: InstalledAddon[],
  item: MediaItem,
  season?: number,
  episode?: number
): Promise<SubtitleTrack[]> {
  const type = item.mediaType === "tv" ? "series" : "movie";
  const ids = streamIds(item, season, episode);
  const subtitleAddons = addons.filter((addon) => addon.enabled !== false && manifestUrlFor(addon) && supportsResource(addon, "subtitles"));
  if (!subtitleAddons.some((addon) => manifestUrlFor(addon) === BUILTIN_OPENSUBTITLES.manifestUrl)) {
    subtitleAddons.push(BUILTIN_OPENSUBTITLES);
  }
  return queryAllAddonSubtitles(subtitleAddons, type, ids).catch(() => []);
}

async function queryAllAddonSubtitles(addons: InstalledAddon[], type: "movie" | "series", ids: string[]) {
  const batches = await Promise.all(addons.map((addon) => queryAddonSubtitles(addon, type, ids)));
  const seen = new Set<string>();
  return batches.flat().filter((subtitle) => {
    const key = `${subtitle.lang}|${subtitle.url}`;
    if (seen.has(key)) return false;
    seen.add(key);
    return true;
  }).slice(0, 80);
}

async function queryAddonSubtitles(addon: InstalledAddon, type: "movie" | "series", ids: string[]) {
  const { base, query } = addonBaseUrl(manifestUrlFor(addon));
  for (const id of ids) {
    const url = `${base}/subtitles/${type}/${encodeURIComponent(id)}.json${query ? `?${query}` : ""}`;
    try {
      const payload = await addonJsonRequest<{ subtitles?: RawSubtitle[] }>(url);
      const subtitles = (payload.subtitles ?? [])
        .map((subtitle, index) => normalizeSubtitle(subtitle, addon, index))
        .filter((subtitle): subtitle is SubtitleTrack => Boolean(subtitle));
      if (subtitles.length > 0) return subtitles;
    } catch {
      // Try the next compatible ID form.
    }
  }
  return [] as SubtitleTrack[];
}

async function addonJsonRequest<T>(url: string) {
  try {
    return await jsonRequest<T>(url);
  } catch {
    return jsonRequest<T>(proxiedUrl(url));
  }
}

function streamIds(item: MediaItem, season?: number, episode?: number) {
  const ids: string[] = [];
  const hasEpisode = item.mediaType === "tv" && season && episode;
  if (item.imdbId?.startsWith("tt")) {
    ids.push(hasEpisode ? `${item.imdbId}:${season}:${episode}` : item.imdbId);
  }
  ids.push(hasEpisode ? `tmdb:${item.id}:${season}:${episode}` : `tmdb:${item.id}`);
  return Array.from(new Set(ids));
}

function streamRequestTypes(addon: InstalledAddon, requestedType: "movie" | "series", preferredType?: string) {
  const aliases = requestedType === "series" ? ["series", "tv", "show"] : ["movie", "film"];
  // A native item is asked for by its own type first: a live channel ("tv") is shown as a
  // movie, but its addon may declare no movie type at all.
  return [...new Set([...(preferredType ? [preferredType] : []), ...aliases.filter((type) => addonSupportsType(addon, type))])];
}

function addonSupportsType(addon: InstalledAddon, requestedType: string) {
  const declared = new Set<string>();
  const add = (value: unknown) => {
    if (typeof value === "string" && value.trim()) declared.add(value.trim().toLowerCase());
  };
  addon.types?.forEach(add);
  addon.resources?.forEach((resource) => {
    if (typeof resource !== "string") resource.types?.forEach(add);
  });
  if (!declared.size) return true;
  const aliases = requestedType === "series" || requestedType === "tv" || requestedType === "show"
    ? ["series", "tv", "show"]
    : requestedType === "movie" || requestedType === "film"
      ? ["movie", "film"]
      : [requestedType];
  return aliases.some((alias) => declared.has(alias));
}

function addonSupportsId(addon: InstalledAddon, id: string) {
  const prefixes = [
    ...(addon.idPrefixes ?? []),
    ...(addon.resources ?? []).flatMap((resource) => typeof resource === "string" ? [] : resource.idPrefixes ?? [])
  ].map((prefix) => prefix.trim()).filter(Boolean);
  if (!prefixes.length) return true;
  return prefixes.some((prefix) =>
    id.toLowerCase().startsWith(prefix.toLowerCase()) ||
    (!prefix.endsWith(":") && id.toLowerCase().startsWith(`${prefix.toLowerCase()}:`))
  );
}

function normalizeStream(stream: RawStream, addon: InstalledAddon): StreamSource {
  const text = [stream.title, stream.name, stream.description, stream.size].filter(Boolean).join(" ");
  const url = streamUrl(stream);
  const normalized: StreamSource = {
    source: stream.name ?? stream.title ?? addon.name,
    addonName: addon.name,
    addonId: addon.id,
    quality: detectQuality(text),
    size: stream.size ?? sizeLabel(stream.sizeBytes ?? stream.behaviorHints?.videoSize ?? null),
    sizeBytes: stream.sizeBytes ?? stream.behaviorHints?.videoSize ?? null,
    url,
    infoHash: stream.infoHash ?? null,
    fileIdx: stream.fileIdx ?? null,
    behaviorHints: {
      ...(stream.behaviorHints ?? {}),
      notWebReady: !url && Boolean(stream.infoHash || stream.ytId || stream.behaviorHints?.videoHash)
    },
    subtitles: stream.subtitles ?? [],
    sources: stream.sources ?? [],
    description: stream.description ?? stream.title ?? null
  };
  normalized.behaviorHints = {
    ...(normalized.behaviorHints ?? {}),
    browserPlayable: isBrowserPlayableStream(normalized),
    iosPlayable: isIosPlayableStream(normalized)
  };
  return normalized;
}

function normalizeSubtitle(subtitle: RawSubtitle, addon: InstalledAddon, index: number): SubtitleTrack | null {
  const url = typeof subtitle.url === "string" ? subtitle.url.trim() : "";
  if (!/^https?:\/\//i.test(url)) return null;
  const lang = subtitle.lang?.trim() || "en";
  return {
    id: subtitle.id || `${addon.id}-${lang}-${index}`,
    url,
    lang,
    label: subtitle.label || subtitle.name || lang.toUpperCase(),
    provider: addon.name,
    isEmbedded: false
  };
}

function attachSubtitles(stream: StreamSource, sharedSubtitles: SubtitleTrack[]) {
  if (!sharedSubtitles.length) return stream;
  const existing = stream.subtitles ?? [];
  const seen = new Set(existing.map((subtitle) => subtitle.url));
  const merged = [...existing, ...sharedSubtitles.filter((subtitle) => !seen.has(subtitle.url))];
  return merged.length === existing.length ? stream : { ...stream, subtitles: merged };
}

function streamUrl(stream: RawStream) {
  const candidates = [
    stream.url,
    stream.externalUrl,
    stream.behaviorHints?.directUrl,
    stream.behaviorHints?.url,
    stream.behaviorHints?.externalUrl
  ];
  for (const candidate of candidates) {
    const value = typeof candidate === "string" ? candidate.trim() : "";
    if (!value) continue;
    if (value.startsWith("//")) return `https:${value}`;
    if (/^https?:\/\//i.test(value)) return value;
  }
  return null;
}

function sizeLabel(bytes?: number | null) {
  if (!bytes || !Number.isFinite(bytes)) return "";
  if (bytes >= 1_000_000_000) return `${(bytes / 1_000_000_000).toFixed(1)} GB`;
  if (bytes >= 1_000_000) return `${Math.round(bytes / 1_000_000)} MB`;
  return "";
}

function dedupeStreams(streams: StreamSource[]) {
  const seen = new Set<string>();
  return streams.filter((stream) => {
    const key = [
      stream.addonId,
      stream.url ?? "",
      stream.infoHash ?? "",
      stream.fileIdx ?? "",
      stream.source,
      stream.description ?? ""
    ].join("|");
    if (seen.has(key)) return false;
    seen.add(key);
    return true;
  });
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
  if (isBrowserPlayableStream(stream)) score += 180;
  else if (isIosPlayableStream(stream)) score += 110;
  else if (stream.url) score += 25;
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
