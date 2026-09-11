import { jsonRequest, proxiedUrl } from "./http";
import type { CatalogConfig, Category, HomeServerCollectionConfig, HomeServerConfig, MediaItem, MediaType, StreamSource } from "./types";

// ── Cloud sync shape mapping (APK parity) ───────────────────────────────────
// The Android app persists home servers as a JSON string:
//   { "connections": [ HomeServerConnection ] }
// with fields: serverKind (UNKNOWN|JELLYFIN|EMBY|PLEX), serverUrl, accessToken,
// accountToken, userId, userName, serverName, serverId, collections, enabled…
// The web app uses HomeServerConfig (type/url/token/…). These helpers translate
// both directions so a server configured on the TV works here and vice-versa.

function apkKindToWebType(kind: unknown): HomeServerConfig["type"] {
  const k = String(kind ?? "").toUpperCase();
  if (k === "PLEX") return "plex";
  if (k === "EMBY") return "emby";
  return "jellyfin";
}

function webTypeToApkKind(type: HomeServerConfig["type"]): string {
  if (type === "plex") return "PLEX";
  if (type === "emby") return "EMBY";
  return "JELLYFIN";
}

function toStr(value: unknown): string {
  return typeof value === "string" ? value : value == null ? "" : String(value);
}

// Parse a cloud homeServerConnectionJson value into web HomeServerConfig[].
// Accepts the APK object shape { connections: [...] }, a bare array (legacy web
// writes), or a single object.
export function parseHomeServerConnectionJson(json: string | null | undefined): HomeServerConfig[] {
  if (!json || !json.trim()) return [];
  let root: unknown;
  try {
    root = JSON.parse(json);
  } catch {
    return [];
  }
  const rawList: unknown[] = Array.isArray(root)
    ? root
    : root && typeof root === "object" && Array.isArray((root as { connections?: unknown[] }).connections)
      ? (root as { connections: unknown[] }).connections
      : root && typeof root === "object"
        ? [root]
        : [];

  return rawList
    .map((raw): HomeServerConfig | null => {
      if (!raw || typeof raw !== "object") return null;
      const r = raw as Record<string, unknown>;
      // Support both APK field names and prior web field names.
      const url = trimUrl(toStr(r.serverUrl ?? r.url));
      const type = "type" in r && !("serverKind" in r)
        ? (String(r.type).toLowerCase() as HomeServerConfig["type"])
        : apkKindToWebType(r.serverKind);
      const token = toStr(r.accessToken ?? r.token);
      if (!url && !token) return null;
      const collections: HomeServerCollectionConfig[] = Array.isArray(r.collections)
        ? (r.collections as unknown[])
            .map((c) => (c && typeof c === "object" ? (c as Record<string, unknown>) : null))
            .filter((c): c is Record<string, unknown> => Boolean(c))
            .map((c) => ({
              id: toStr(c.id),
              name: toStr(c.name),
              type: toStr(c.type),
              enabled: c.enabled !== false
            }))
        : [];
      return {
        id: toStr(r.connectionId ?? r.id) || `${type}:${url}`,
        type,
        name: toStr(r.displayName ?? r.name ?? r.serverName) || "Home Server",
        url,
        token: token || undefined,
        username: toStr(r.userName ?? r.username) || undefined,
        password: toStr(r.password) || undefined,
        enabled: r.enabled !== false,
        serverId: toStr(r.serverId) || undefined,
        userId: toStr(r.userId) || undefined,
        userName: toStr(r.userName) || undefined,
        accountToken: toStr(r.accountToken) || undefined,
        collections: collections.length ? collections : undefined,
        lastConnectedAt: typeof r.lastConnectedAt === "number" ? r.lastConnectedAt : undefined
      };
    })
    .filter((s): s is HomeServerConfig => Boolean(s));
}

// Serialize web HomeServerConfig[] back into the APK { connections: [...] }
// shape so the Android app reads it after a web-side change.
export function serializeHomeServerConnectionJson(servers: HomeServerConfig[] | undefined): string {
  const list = (servers ?? []).filter((s) => s && (s.url || s.token));
  if (!list.length) return "";
  return JSON.stringify({
    connections: list.map((s) => ({
      enabled: s.enabled !== false,
      connectionId: s.id || `${webTypeToApkKind(s.type)}:${s.url}`,
      serverUrl: s.url,
      displayName: s.name || "",
      serverName: s.name || "",
      serverKind: webTypeToApkKind(s.type),
      serverId: s.serverId || "",
      userId: s.userId || "",
      userName: s.userName || s.username || "",
      accessToken: s.token || "",
      accountToken: s.accountToken || "",
      collections: (s.collections ?? []).map((c) => ({
        id: c.id,
        name: c.name,
        type: c.type,
        enabled: c.enabled !== false
      })),
      lastConnectedAt: s.lastConnectedAt || 0
    }))
  });
}

/**
 * Home-server clients. Jellyfin / Emby share an API surface. Plex uses its own
 * JSON API but is normalized to the same MediaItem rows.
 *
 * All requests go through /api/proxy so the browser avoids CORS with the user's
 * server and we can attach the auth header.
 */

const AUTH_HEADER = 'MediaBrowser Client="ARVIO Web", Device="Web", DeviceId="arvio-web", Version="1.0.0"';

const sessionCache = new Map<string, { token: string; userId: string }>();

function trimUrl(url: string) {
  return url.replace(/\/+$/, "");
}

function javaUrlEncode(value: string): string {
  return encodeURIComponent(value)
    .replace(/%20/g, "+")
    .replace(/[!'()~]/g, (character) => `%${character.charCodeAt(0).toString(16).toUpperCase()}`);
}

function homeServerKey(server: HomeServerConfig): string {
  return server.serverId?.trim() || server.id.trim() || `${webTypeToApkKind(server.type)}:${trimUrl(server.url)}`;
}

export function buildHomeServerCatalogSourceRef(
  server: HomeServerConfig,
  collection: Pick<HomeServerCollectionConfig, "id" | "type">
): string {
  return `home_server_catalog|${[
    homeServerKey(server),
    collection.id,
    collection.type
  ].map(javaUrlEncode).join("|")}`;
}

async function sha256Short(value: string): Promise<string> {
  const digest = await globalThis.crypto.subtle.digest("SHA-256", new TextEncoder().encode(value));
  return Array.from(new Uint8Array(digest).slice(0, 8))
    .map((byte) => byte.toString(16).padStart(2, "0"))
    .join("");
}

export async function homeServerCatalogIdentity(
  server: HomeServerConfig,
  collection: Pick<HomeServerCollectionConfig, "id" | "type">
): Promise<{ id: string; sourceRef: string }> {
  const sourceRef = buildHomeServerCatalogSourceRef(server, collection);
  return { id: `home_server_${await sha256Short(sourceRef)}`, sourceRef };
}

export async function buildHomeServerCatalogConfigs(
  servers: HomeServerConfig[],
  savedCatalogs: CatalogConfig[] = [],
  hiddenCatalogIds: string[] = []
): Promise<CatalogConfig[]> {
  const hidden = new Set(hiddenCatalogIds);
  const savedHomeCatalogs = savedCatalogs.filter((catalog) => catalog.sourceType === "home-server");
  const savedById = new Map(savedHomeCatalogs.map((catalog) => [catalog.id, catalog]));
  const savedBySourceRef = new Map(
    savedHomeCatalogs
      .filter((catalog) => Boolean(catalog.sourceRef))
      .map((catalog) => [catalog.sourceRef as string, catalog])
  );
  const generated: CatalogConfig[] = [];

  for (const server of servers) {
    for (const collection of server.collections ?? []) {
      if (!collection.id || !isVideoCollectionType(collection.type)) continue;
      const identity = await homeServerCatalogIdentity(server, collection);
      const existing = savedById.get(identity.id) ?? savedBySourceRef.get(identity.sourceRef);
      const title = existing?.name || existing?.title || `${server.name} - ${collection.name}`;
      const normalizedType = collection.type.toLowerCase();
      generated.push({
        ...existing,
        id: identity.id,
        name: title,
        title,
        sourceType: "home-server",
        sourceRef: identity.sourceRef,
        mediaType: normalizedType.includes("movie") ? "movie" : normalizedType.includes("tv") ? "tv" : "all",
        enabled: !hidden.has(identity.id),
        isPreinstalled: false,
        layout: existing?.layout ?? "landscape"
      });
    }
  }

  const generatedById = new Map(generated.map((catalog) => [catalog.id, catalog]));
  const ordered = savedHomeCatalogs
    .map((catalog) => generatedById.get(catalog.id))
    .filter((catalog): catalog is CatalogConfig => Boolean(catalog));
  const orderedIds = new Set(ordered.map((catalog) => catalog.id));
  ordered.push(...generated.filter((catalog) => !orderedIds.has(catalog.id)));

  // Keep a saved catalog manageable if an older cloud record has not yet
  // populated the connection's collection metadata.
  const generatedIds = new Set(generated.map((catalog) => catalog.id));
  ordered.push(...savedHomeCatalogs
    .filter((catalog) => !generatedIds.has(catalog.id))
    .map((catalog) => ({ ...catalog, enabled: catalog.enabled !== false && !hidden.has(catalog.id) })));
  return ordered;
}

function directUrl(url: string) {
  return url;
}

function hashId(value: string): number {
  let hash = 0;
  for (let i = 0; i < value.length; i += 1) {
    hash = (hash * 31 + value.charCodeAt(i)) | 0;
  }
  return Math.abs(hash) || 1;
}

async function proxiedGet<T>(url: string, headers?: Record<string, string>, signal?: AbortSignal): Promise<T> {
  return jsonRequest<T>(proxiedUrl(url, headers), { signal });
}

async function proxiedPost<T>(url: string, body: unknown, headers?: Record<string, string>, signal?: AbortSignal): Promise<T> {
  const target = new URL("/api/proxy", window.location.origin);
  target.searchParams.set("url", url);
  if (headers && Object.keys(headers).length) target.searchParams.set("headers", btoa(JSON.stringify(headers)));
  return jsonRequest<T>(target.toString(), { method: "POST", body: JSON.stringify(body), signal });
}

export { ensureSession as ensureHomeServerSession };

async function ensureSession(server: HomeServerConfig, signal?: AbortSignal): Promise<{ token: string; userId: string } | null> {
  signal?.throwIfAborted();
  const cacheKey = JSON.stringify([server.id, server.url, server.token, server.userId, server.username, server.password]);
  const cached = sessionCache.get(cacheKey);
  if (cached) return cached;
  const base = trimUrl(server.url);

  // Direct session if both token and userId are already present on the config
  if (server.token && server.userId) {
    const session = { token: server.token, userId: server.userId };
    sessionCache.set(cacheKey, session);
    return session;
  }

  // API-key path: resolve the user id from the token.
  if (server.token) {
    try {
      const me = await proxiedGet<{ Id: string }>(`${base}/Users/Me?api_key=${encodeURIComponent(server.token)}`, {
        "X-Emby-Token": server.token,
        "X-MediaBrowser-Token": server.token
      }, signal);
      signal?.throwIfAborted();
      if (me?.Id) {
        const session = { token: server.token, userId: me.Id };
        sessionCache.set(cacheKey, session);
        return session;
      }
    } catch {
      signal?.throwIfAborted();
      /* fall through to username auth */
    }

    try {
      const users = await proxiedGet<Array<{ Id: string }>>(`${base}/Users?api_key=${encodeURIComponent(server.token)}`, {
        "X-Emby-Token": server.token
      }, signal);
      signal?.throwIfAborted();
      if (Array.isArray(users) && users.length > 0 && users[0]?.Id) {
        const session = { token: server.token, userId: users[0].Id };
        sessionCache.set(cacheKey, session);
        return session;
      }
    } catch {
      signal?.throwIfAborted();
      /* fall through to username auth */
    }
  }

  // Username/password path (AuthenticateByName).
  if (server.username) {
    try {
      const auth = await proxiedPost<{ AccessToken: string; User: { Id: string } }>(
        `${base}/Users/AuthenticateByName`,
        { Username: server.username, Pw: server.password ?? "" },
        { "X-Emby-Authorization": AUTH_HEADER },
        signal
      );
      signal?.throwIfAborted();
      if (auth?.AccessToken && auth.User?.Id) {
        const session = { token: auth.AccessToken, userId: auth.User.Id };
        sessionCache.set(cacheKey, session);
        return session;
      }
    } catch {
      signal?.throwIfAborted();
      return null;
    }
  }
  return null;
}

interface JellyfinItem {
  Id: string;
  Name: string;
  Type: string;
  ProductionYear?: number;
  Overview?: string;
  CommunityRating?: number;
  ImageTags?: { Primary?: string };
  BackdropImageTags?: string[];
  PrimaryImageTag?: string;
  ProviderIds?: { Tmdb?: string; Imdb?: string; Tvdb?: string };
  DateCreated?: string;
  PremiereDate?: string;
}

interface PlexSection {
  key: string;
  title: string;
  type: string;
}

interface PlexItem {
  ratingKey: string;
  title: string;
  type: string;
  year?: number;
  summary?: string;
  rating?: number;
  thumb?: string;
  art?: string;
  Media?: Array<{ Part?: Array<{ key?: string }> }>;
  Guid?: Array<{ id?: string }>;
  addedAt?: number;
  originallyAvailableAt?: string;
}

const NON_VIDEO_COLLECTION_TYPES = new Set(["music", "photos", "homevideos", "books", "podcasts", "audiobooks"]);
const BROWSABLE_LIBRARY_TYPES = new Set(["movies", "tvshows", "mixed"]);
function isVideoCollectionType(type?: string | null): boolean {
  if (!type) return true;
  return !NON_VIDEO_COLLECTION_TYPES.has(type.toLowerCase().trim());
}

function mapItem(base: string, token: string, item: JellyfinItem, server?: HomeServerConfig): MediaItem {
  const mediaType: MediaType = item.Type === "Series" ? "tv" : "movie";
  // Leave these empty when the server has no artwork rather than pointing at an
  // image route that will 404: MediaCard renders a placeholder for empty values
  // but a broken <img> for a failing URL, and it skips the TMDB artwork
  // back-fill for home-server items, so there is nothing to recover with.
  const primaryTag = item.ImageTags?.Primary ?? item.PrimaryImageTag;
  const image = primaryTag
    ? directUrl(`${base}/Items/${item.Id}/Images/Primary?maxWidth=500&tag=${primaryTag}&api_key=${token}`)
    : "";
  const backdropTag = item.BackdropImageTags?.[0];
  const backdrop = backdropTag
    ? directUrl(`${base}/Items/${item.Id}/Images/Backdrop/0?maxWidth=1280&tag=${backdropTag}&api_key=${token}`)
    : null;
  const tmdbId = Number(item.ProviderIds?.Tmdb) || null;
  return {
    id: tmdbId ?? -hashId(`${server?.id ?? base}:${item.Id}`),
    title: item.Name,
    overview: item.Overview ?? "",
    year: item.ProductionYear ? String(item.ProductionYear) : "",
    releaseDate: item.PremiereDate ?? null,
    rating: item.CommunityRating ? item.CommunityRating.toFixed(1) : "",
    mediaType,
    image,
    backdrop,
    isHomeServer: true,
    tmdbId,
    imdbId: item.ProviderIds?.Imdb ?? null,
    tvdbId: Number(item.ProviderIds?.Tvdb) || null,
    activityAt: item.DateCreated ? Date.parse(item.DateCreated) || 0 : 0,
    homeServerItemId: item.Id,
    homeServerId: server?.id ?? null,
    homeServerType: server?.type ?? null,
    // Movies stream directly; series would need episode browsing (future).
    homeServerUrl: mediaType === "movie" ? directUrl(`${base}/Videos/${item.Id}/stream?static=true&api_key=${token}`) : null
  };
}

function plexImage(base: string, token: string, path?: string) {
  return path ? directUrl(`${base}${path}?X-Plex-Token=${encodeURIComponent(token)}`) : "";
}

function plexStreamUrl(base: string, token: string, item: PlexItem) {
  const part = item.Media?.[0]?.Part?.[0]?.key;
  return part ? directUrl(`${base}${part}?X-Plex-Token=${encodeURIComponent(token)}`) : null;
}

function mapPlexItem(base: string, token: string, item: PlexItem, server?: HomeServerConfig): MediaItem {
  const mediaType: MediaType = item.type === "show" ? "tv" : "movie";
  const providerIds = Object.fromEntries((item.Guid ?? []).map((entry) => {
    const value = entry.id ?? "";
    return [value.split("://")[0]?.toLowerCase(), value.split("://")[1]?.split("?")[0]];
  }).filter(([provider, id]) => Boolean(provider && id)));
  const tmdbId = Number(providerIds.tmdb) || null;
  return {
    id: tmdbId ?? -hashId(`${server?.id ?? base}:${item.ratingKey}`),
    title: item.title,
    overview: item.summary ?? "",
    year: item.year ? String(item.year) : "",
    releaseDate: item.originallyAvailableAt ?? null,
    rating: item.rating ? item.rating.toFixed(1) : "",
    mediaType,
    image: plexImage(base, token, item.thumb),
    backdrop: item.art ? plexImage(base, token, item.art) : null,
    isHomeServer: true,
    tmdbId,
    imdbId: providerIds.imdb ?? null,
    tvdbId: Number(providerIds.tvdb) || null,
    activityAt: item.addedAt ? item.addedAt * 1000 : 0,
    homeServerItemId: item.ratingKey,
    homeServerId: server?.id ?? null,
    homeServerType: server?.type ?? "plex",
    homeServerUrl: mediaType === "movie" ? plexStreamUrl(base, token, item) : null
  };
}

async function loadPlexRows(server: HomeServerConfig, hiddenCatalogIds: Set<string>): Promise<Category[]> {
  if (!server.token) return [];
  const base = trimUrl(server.url);
  const token = server.token;
  const headers = { Accept: "application/json", "X-Plex-Token": token };
  const sections = await proxiedGet<{ MediaContainer?: { Directory?: PlexSection[] } }>(
    `${base}/library/sections?X-Plex-Token=${encodeURIComponent(token)}`,
    headers
  ).catch(() => null);
  const libraries = (sections?.MediaContainer?.Directory ?? [])
    .filter((section) => section.type === "movie" || section.type === "show")
    .filter((section) => {
      if (!server.collections?.length) return true;
      return server.collections.some((collection) => collection.id === section.key && collection.enabled !== false);
    })
    .slice(0, 6);
  const rows = await Promise.all(libraries.map(async (library) => {
    const configured = server.collections?.find((collection) => collection.id === library.key);
    if (server.collections?.length && !configured) return null;
    if (configured?.enabled === false) return null;
    const identity = await homeServerCatalogIdentity(server, {
      id: library.key,
      type: configured?.type || library.type
    });
    if (hiddenCatalogIds.has(identity.id)) return null;
    const payload = await proxiedGet<{ MediaContainer?: { Metadata?: PlexItem[] } }>(
      `${base}/library/sections/${library.key}/all?X-Plex-Token=${encodeURIComponent(token)}&sort=addedAt:desc`,
      headers
    ).catch(() => null);
    const mapped = (payload?.MediaContainer?.Metadata ?? [])
      .slice(0, 24)
      .map((item) => mapPlexItem(base, token, item, server))
      .filter((item) => Boolean(item && item.title));
    return mapped.length ? { id: identity.id, title: `${server.name} - ${library.title}`, items: mapped } : null;
  }));
  return rows.filter((row): row is Category => Boolean(row));
}

export async function loadHomeServerRows(
  servers: HomeServerConfig[],
  hiddenCatalogIds: string[] = [],
  catalogOrder: CatalogConfig[] = []
): Promise<Category[]> {
  const hidden = new Set(hiddenCatalogIds);
  const plexRows = await Promise.all(
    servers
      .filter((server) => server.enabled && server.url && server.type === "plex")
      .map((server) => loadPlexRows(server, hidden).catch(() => [] as Category[]))
  );
  const active = servers.filter((server) => server.enabled && server.url && (server.type === "jellyfin" || server.type === "emby"));
  const rowsPerServer = await Promise.all(active.map(async (server) => {
    const session = await ensureSession(server).catch(() => null);
    if (!session) return [] as Category[];
    const base = trimUrl(server.url);
    const { token, userId } = session;
    try {
      const views = await proxiedGet<{ Items?: Array<{ Id: string; Name: string; CollectionType?: string }> }>(
        `${base}/Users/${userId}/Views?api_key=${token}`
      );
      const libraries = (views.Items ?? [])
        .filter((view) => isVideoCollectionType(view.CollectionType))
        .filter((view) => {
          if (!server.collections?.length) return true;
          return server.collections.some((collection) => collection.id === view.Id && collection.enabled !== false);
        })
        .slice(0, 6);
      const rows = await Promise.all(libraries.map(async (library) => {
        const configured = server.collections?.find((collection) => collection.id === library.Id);
        if (server.collections?.length && !configured) return null;
        if (configured?.enabled === false) return null;
        const identity = await homeServerCatalogIdentity(server, {
          id: library.Id,
          type: configured?.type || library.CollectionType || ""
        });
        if (hidden.has(identity.id)) return null;
        const items = await proxiedGet<{ Items?: JellyfinItem[] }>(
          `${base}/Users/${userId}/Items?ParentId=${library.Id}&Recursive=true&IncludeItemTypes=Movie,Series&SortBy=DateCreated&SortOrder=Descending&Limit=24&Fields=Overview,PrimaryImageAspectRatio,BasicSyncInfo,ImageTags,BackdropImageTags,ProductionYear,CommunityRating&api_key=${token}`
        ).catch(() => ({ Items: [] as JellyfinItem[] }));
        const mapped = (items.Items ?? []).map((item) => mapItem(base, token, item, server)).filter((m) => Boolean(m && m.title));
        return mapped.length ? { id: identity.id, title: `${server.name} - ${library.Name}`, items: mapped } : null;
      }));
      return rows.filter((row): row is Category => Boolean(row));
    } catch {
      return [] as Category[];
    }
  }));
  const order = new Map(catalogOrder.map((catalog, index) => [catalog.id, index]));
  return [...plexRows.flat(), ...rowsPerServer.flat()]
    .sort((left, right) => (order.get(left.id) ?? Number.MAX_SAFE_INTEGER) - (order.get(right.id) ?? Number.MAX_SAFE_INTEGER));
}

export function clearHomeServerSessions() {
  sessionCache.clear();
}

// Verify a home-server config works: authenticate (Jellyfin/Emby) or validate
// the token (Plex), and count libraries. Used by the web settings "Test" button
// so users adding a server directly (no Android app) get confirmation.
export async function testHomeServerConnection(
  server: HomeServerConfig
): Promise<{
  ok: boolean;
  serverName?: string;
  libraryCount?: number;
  connection?: HomeServerConfig;
  error?: string;
}> {
  const base = trimUrl(server.url);
  if (!base) return { ok: false, error: "Missing server URL" };
  try {
    if (server.type === "plex") {
      const token = server.token ?? "";
      if (!token) return { ok: false, error: "Plex needs an access token" };
      const sections = await proxiedGet<{ MediaContainer?: { Directory?: PlexSection[]; friendlyName?: string } }>(
        `${base}/library/sections?X-Plex-Token=${encodeURIComponent(token)}`,
        { Accept: "application/json", "X-Plex-Token": token }
      );
      const libs = sections?.MediaContainer?.Directory ?? [];
      const serverName = sections?.MediaContainer?.friendlyName;
      return {
        ok: true,
        serverName,
        libraryCount: libs.length,
        connection: {
          ...server,
          name: server.name || serverName || "Plex",
          url: base,
          password: undefined,
          collections: libs.map((library) => ({
            id: library.key,
            name: library.title,
            type: library.type,
            enabled: true
          })),
          lastConnectedAt: Date.now()
        }
      };
    }
    // Reset any cached (possibly stale) session so the test really re-auths.
    sessionCache.delete(server.id);
    const session = await ensureSession(server);
    if (!session) return { ok: false, error: "Authentication failed — check token or username/password" };
    const views = await proxiedGet<{ Items?: Array<{ Id: string; Name: string; CollectionType?: string }> }>(
      `${base}/Users/${session.userId}/Views?api_key=${session.token}`
    ).catch(() => null);
    const info = await proxiedGet<{ Id?: string; ServerName?: string }>(
      `${base}/System/Info?api_key=${session.token}`
    ).catch(() => null);
    const libraries = (views?.Items ?? [])
      .filter((view) => isBrowsableLibraryType(view.CollectionType))
      .map((view) => ({
        id: view.Id,
        name: view.Name,
        type: view.CollectionType ?? "mixed",
        enabled: true
      }));
    return {
      ok: true,
      serverName: info?.ServerName,
      libraryCount: libraries.length,
      connection: {
        ...server,
        name: server.name || info?.ServerName || (server.type === "emby" ? "Emby" : "Jellyfin"),
        url: base,
        token: session.token,
        userId: session.userId,
        userName: server.username,
        password: undefined,
        serverId: info?.Id,
        collections: libraries,
        lastConnectedAt: Date.now()
      }
    };
  } catch (error) {
    return { ok: false, error: error instanceof Error ? error.message : "Connection failed" };
  }
}

// ── Source resolution (APK parity) ──────────────────────────────────────────
// Match the opened title on each usable home server and emit playable
// StreamSources (addonId "home_server"), for movies and episodes, across
// Jellyfin/Emby (PlaybackInfo + /Videos/{id}/stream) and Plex (media parts).

export const HOME_SERVER_ADDON_ID = "home_server";

function normalizeTitle(title: string): string {
  return title
    .normalize("NFD")
    .replace(/[̀-ͯ]/g, "")
    .toLowerCase()
    .replace(/&/g, " and ")
    .replace(/[^a-z0-9]+/g, " ")
    .replace(/\b(the|a|an)\b/g, " ")
    .trim()
    .replace(/\s+/g, " ");
}

interface MatchTarget {
  title: string;
  year?: number;
  imdbId?: string;
  tmdbId?: number;
}

const SOURCE_CACHE_TTL_MS = 30 * 60 * 1000;
const EMPTY_SOURCE_CACHE_TTL_MS = 30 * 1000;
const SOURCE_CACHE_MAX_ENTRIES = 128;
const sourceCache = new Map<string, { expiresAt: number; sources: StreamSource[] }>();
const sourceRequests = new Map<string, Promise<StreamSource[]>>();

function sourceContentIdentity(target: MatchTarget): string {
  const imdb = target.imdbId?.trim().toLowerCase();
  if (imdb) return `imdb:${imdb}`;
  if (target.tmdbId && target.tmdbId > 0) return `tmdb:${target.tmdbId}`;
  return `title:${normalizeTitle(target.title)}:${target.year ?? ""}`;
}

function sourceServerSignature(servers: HomeServerConfig[]): string {
  return servers.map((server) => [
    server.id,
    server.type,
    trimUrl(server.url),
    server.userId ?? "",
    server.lastConnectedAt ?? 0,
    `${server.token?.length ?? 0}:${hashId(server.token ?? "")}`,
    (server.collections ?? []).filter((collection) => collection.enabled !== false).map((collection) => collection.id).join(",")
  ].join(":"))
    .join("|");
}

function homeServerSourceCacheKey(
  type: "movie" | "episode",
  servers: HomeServerConfig[],
  target: MatchTarget,
  season?: number,
  episode?: number
): string {
  return [type, sourceServerSignature(servers), sourceContentIdentity(target), season ?? "", episode ?? ""].join("|");
}

async function resolveCachedHomeServerSources(
  key: string,
  loader: () => Promise<StreamSource[]>
): Promise<StreamSource[]> {
  const cached = sourceCache.get(key);
  if (cached && cached.expiresAt > Date.now()) return cached.sources;
  if (cached) sourceCache.delete(key);

  const inFlight = sourceRequests.get(key);
  if (inFlight) return inFlight;

  let request: Promise<StreamSource[]>;
  request = loader()
    .then((sources) => {
      if (sourceCache.size >= SOURCE_CACHE_MAX_ENTRIES) {
        const oldestKey = sourceCache.keys().next().value as string | undefined;
        if (oldestKey) sourceCache.delete(oldestKey);
      }
      sourceCache.set(key, {
        sources,
        expiresAt: Date.now() + (sources.length ? SOURCE_CACHE_TTL_MS : EMPTY_SOURCE_CACHE_TTL_MS)
      });
      return sources;
    })
    .finally(() => {
      if (sourceRequests.get(key) === request) sourceRequests.delete(key);
    });
  sourceRequests.set(key, request);
  return request;
}

interface Candidate {
  title: string;
  year?: number;
  providerIds: Record<string, string>;
}

// Mirrors HomeServerMatcher.score in the APK.
function scoreCandidate(target: MatchTarget, c: Candidate): number {
  let score = 0;
  const providers = Object.fromEntries(Object.entries(c.providerIds).map(([k, v]) => [k.toLowerCase(), v]));
  const imdb = target.imdbId?.trim().toLowerCase();
  if (imdb && providers.imdb?.toLowerCase() === imdb) score += 1000;
  if (target.tmdbId != null && Number(providers.tmdb) === target.tmdbId) score += 900;

  const reqN = normalizeTitle(target.title);
  const candN = normalizeTitle(c.title);
  if (reqN && candN) {
    if (reqN === candN) score += 140;
    else if (candN.includes(reqN) || reqN.includes(candN)) score += 65;
  }
  if (target.year != null && c.year != null) {
    const delta = Math.abs(target.year - c.year);
    if (delta === 0) score += 90;
    else if (delta === 1) score += 45;
    else if (delta <= 2) score += 15;
    else score -= 120;
  }
  return score;
}

function isAcceptable(score: number): boolean {
  return score >= 150 || score >= 900;
}

function isLikelySameVersion(target: MatchTarget, candidate: Candidate): boolean {
  if (!normalizeTitle(target.title) || normalizeTitle(target.title) !== normalizeTitle(candidate.title)) return false;
  if (target.year == null || candidate.year == null) return true;
  return Math.abs(target.year - candidate.year) <= 1;
}

function formatBytes(bytes: number): string {
  if (!bytes || bytes <= 0) return "";
  const gb = bytes / 1_000_000_000;
  if (gb >= 1) return `${gb.toFixed(1)} GB`;
  return `${Math.round(bytes / 1_000_000)} MB`;
}

function qualityLabel(width?: number, height?: number): string {
  const h = height ?? 0;
  const w = width ?? 0;
  if (h >= 2000 || w >= 3800) return "4K";
  if (h >= 1000 || w >= 1900) return "1080p";
  if (h >= 700 || w >= 1200) return "720p";
  if (h > 0) return `${h}p`;
  return "";
}

// ---- Jellyfin / Emby ----

export interface JellyfinMediaSource {
  Id?: string;
  Path?: string;
  Container?: string;
  Size?: number;
  ETag?: string;
  DefaultAudioStreamIndex?: number;
  SupportsDirectPlay?: boolean;
  SupportsDirectStream?: boolean;
  SupportsTranscoding?: boolean;
  DirectStreamUrl?: string;
  TranscodingUrl?: string;
  TranscodingSubProtocol?: string;
  TranscodingContainer?: string;
  RequiresOpening?: boolean;
  MediaStreams?: Array<{
    Type?: string; Index?: number; IsDefault?: boolean; Codec?: string; Profile?: string;
    Width?: number; Height?: number; BitDepth?: number; Level?: number; IsInterlaced?: boolean;
    VideoRange?: string; VideoRangeType?: string; ColorTransfer?: string;
    DvProfile?: number; DolbyVisionProfile?: number;
  }>;
}

export function jellyfinSourceMedia(source: JellyfinMediaSource): NonNullable<StreamSource["media"]> {
  const streams = source.MediaStreams ?? [];
  const video = streams.find((s) => s.Type === "Video");
  const audio = streams.find((s) => s.Type === "Audio" && source.DefaultAudioStreamIndex != null && s.Index === source.DefaultAudioStreamIndex)
    ?? streams.find((s) => s.Type === "Audio" && s.IsDefault) ?? streams.find((s) => s.Type === "Audio");
  const range = video?.VideoRangeType || video?.VideoRange;
  return {
    container: source.Container?.toLowerCase(),
    videoCodec: video?.Codec?.toLowerCase(),
    audioCodec: audio?.Codec?.toLowerCase(),
    hdr: video?.DvProfile || video?.DolbyVisionProfile ? "Dolby Vision"
      : range && range !== "Unknown" ? range
      : video?.ColorTransfer === "smpte2084" ? "HDR10" : video?.ColorTransfer === "arib-std-b67" ? "HLG" : undefined
  };
}

interface JellyfinFullItem {
  Id: string;
  Name: string;
  ProductionYear?: number;
  ProviderIds?: Record<string, string>;
  MediaSources?: JellyfinMediaSource[];
}

async function jellyfinFindItems(
  server: HomeServerConfig,
  session: { token: string; userId: string },
  target: MatchTarget,
  itemTypes: string
): Promise<JellyfinFullItem[]> {
  const base = trimUrl(server.url);
  const { token, userId } = session;
  const params = new URLSearchParams({
    Recursive: "true",
    IncludeItemTypes: itemTypes,
    SearchTerm: target.title,
    Fields: "ProviderIds,MediaSources,MediaStreams,ProductionYear,Path",
    Limit: "12",
    api_key: token
  });
  const res = await proxiedGet<{ Items?: JellyfinFullItem[] }>(
    `${base}/Users/${userId}/Items?${params.toString()}`
  ).catch(() => null);
  const items = res?.Items ?? [];
  const scored = items.map((item) => {
    const candidate = {
      title: item.Name,
      year: item.ProductionYear,
      providerIds: item.ProviderIds ?? {}
    };
    return { item, candidate, score: scoreCandidate(target, candidate) };
  }).filter(({ score }) => isAcceptable(score));
  const bestScore = Math.max(...scored.map(({ score }) => score), -Infinity);
  return scored
    .filter(({ candidate, score }) => score === bestScore || isLikelySameVersion(target, candidate))
    .sort((a, b) => b.score - a.score)
    .filter(({ item }, index, all) => all.findIndex((entry) => entry.item.Id === item.Id) === index)
    .map(({ item }) => item);
}

async function jellyfinFindItem(
  server: HomeServerConfig,
  session: { token: string; userId: string },
  target: MatchTarget,
  itemTypes: string
): Promise<JellyfinFullItem | null> {
  return (await jellyfinFindItems(server, session, target, itemTypes))[0] ?? null;
}

async function jellyfinItemSources(
  server: HomeServerConfig,
  session: { token: string; userId: string },
  item: JellyfinFullItem
): Promise<StreamSource[]> {
  const base = trimUrl(server.url);
  const { token, userId } = session;
  // PlaybackInfo yields the authoritative MediaSources with container/size.
  const playbackInfo = await proxiedPost<{ MediaSources?: JellyfinFullItem["MediaSources"] }>(
    `${base}/Items/${item.Id}/PlaybackInfo?UserId=${userId}&IsPlayback=false&AutoOpenLiveStream=false&MaxStreamingBitrate=2147483647&api_key=${token}`,
    {},
    { "X-Emby-Token": token }
  ).catch(() => null);
  const mediaSources = (playbackInfo?.MediaSources ?? item.MediaSources ?? []).filter(Boolean);
  const label = server.name || "Home Server";
  const seen = new Set<string>();
  const out: StreamSource[] = [];
  for (const [mediaIndex, ms] of mediaSources.entries()) {
    const videoStream = (ms.MediaStreams ?? []).find((s) => s.Type === "Video");
    const quality = qualityLabel(videoStream?.Width, videoStream?.Height) || "Direct";
    const container = (ms.Container ?? "").toLowerCase();
    const ext = container ? `.${container}` : "";
    // Direct static stream — playable in-browser (mp4) or via remux/external.
    const params = new URLSearchParams({ Static: "true", MediaSourceId: ms.Id ?? "", api_key: token });
    if (ms.ETag) params.set("Tag", ms.ETag);
    const url = `${base}/Videos/${encodeURIComponent(item.Id)}/stream${ext}?${params}`;
    if (seen.has(url)) continue;
    seen.add(url);
    out.push({
      source: [label, quality, container.toUpperCase()].filter(Boolean).join(" "),
      addonName: label,
      addonId: HOME_SERVER_ADDON_ID,
      quality,
      size: formatBytes(ms.Size ?? 0),
      sizeBytes: ms.Size && ms.Size > 0 ? ms.Size : null,
      url,
      transport: "file",
      media: jellyfinSourceMedia(ms),
      homeServer: { serverId: server.id, itemId: item.Id, mediaSourceId: ms.Id, mediaIndex },
      behaviorHints: {
        cached: true,
        filename: item.Name,
        videoSize: ms.Size && ms.Size > 0 ? ms.Size : null
      },
      description: `${item.Name} · ${label}`
    });
  }
  return out;
}

// ---- Plex ----

export interface PlexMedia {
  id?: string | number;
  container?: string;
  videoCodec?: string;
  audioCodec?: string;
  videoResolution?: string;
  Part?: Array<{
    id?: string | number; key?: string; size?: number; container?: string; file?: string;
    decision?: string;
    Stream?: Array<{
      streamType?: number; codec?: string; selected?: boolean | number; default?: boolean | number;
      DOVIPresent?: boolean | number; DOVIProfile?: number; colorTrc?: string;
      videoRange?: string; bitDepth?: number; profile?: string; decision?: string;
    }>;
  }>;
}

export function plexSourceMedia(media: PlexMedia, part: NonNullable<PlexMedia["Part"]>[number]): NonNullable<StreamSource["media"]> {
  const streams = part.Stream ?? [];
  const video = streams.find((s) => s.streamType === 1);
  const audio = streams.find((s) => s.streamType === 2 && s.selected)
    ?? streams.find((s) => s.streamType === 2 && s.default) ?? streams.find((s) => s.streamType === 2);
  return {
    container: (part.container ?? media.container)?.toLowerCase(),
    videoCodec: (video?.codec ?? media.videoCodec)?.toLowerCase(),
    audioCodec: (audio?.codec ?? media.audioCodec)?.toLowerCase(),
    hdr: video?.DOVIPresent || video?.DOVIProfile ? "Dolby Vision"
      : video?.videoRange || (video?.colorTrc === "smpte2084" ? "HDR10" : video?.colorTrc === "arib-std-b67" ? "HLG" : undefined)
  };
}

export interface PlexMetadata {
  ratingKey: string;
  title: string;
  year?: number;
  Guid?: Array<{ id: string }>;
  Media?: PlexMedia[];
}

function plexProviderIds(meta: PlexMetadata): Record<string, string> {
  const ids: Record<string, string> = {};
  for (const g of meta.Guid ?? []) {
    const m = g.id.match(/^(imdb|tmdb|tvdb):\/\/(.+)$/i);
    if (m) ids[m[1].toLowerCase()] = m[2];
  }
  return ids;
}

async function plexFindMetadataMatches(
  server: HomeServerConfig,
  target: MatchTarget,
  plexTypes: number[]
): Promise<PlexMetadata[]> {
  const base = trimUrl(server.url);
  const token = server.token ?? "";
  const headers = { Accept: "application/json", "X-Plex-Token": token };
  const responses = await Promise.all(plexTypes.map(async (plexType) => {
    const res = await proxiedGet<{ MediaContainer?: { Metadata?: PlexMetadata[] } }>(
      `${base}/library/all?title=${encodeURIComponent(target.title)}&type=${plexType}&includeGuids=1&X-Plex-Token=${encodeURIComponent(token)}`,
      headers
    ).catch(() => null);
    return res?.MediaContainer?.Metadata ?? [];
  }));
  const scored = responses.flat().map((meta) => {
    const candidate = { title: meta.title, year: meta.year, providerIds: plexProviderIds(meta) };
    return { meta, candidate, score: scoreCandidate(target, candidate) };
  }).filter(({ score }) => isAcceptable(score));
  const bestScore = Math.max(...scored.map(({ score }) => score), -Infinity);
  return scored
    .filter(({ candidate, score }) => score === bestScore || isLikelySameVersion(target, candidate))
    .sort((a, b) => b.score - a.score)
    .filter(({ meta }, index, all) => all.findIndex((entry) => entry.meta.ratingKey === meta.ratingKey) === index)
    .map(({ meta }) => meta);
}

async function plexFindMetadata(
  server: HomeServerConfig,
  target: MatchTarget,
  plexTypes: number[]
): Promise<PlexMetadata | null> {
  return (await plexFindMetadataMatches(server, target, plexTypes))[0] ?? null;
}

async function plexMetadataSources(server: HomeServerConfig, meta: PlexMetadata): Promise<StreamSource[]> {
  const base = trimUrl(server.url);
  const token = server.token ?? "";
  const headers = { Accept: "application/json", "X-Plex-Token": token };
  // Search responses can contain only one Media entry. Always hydrate the full
  // item so every Plex version/part is represented in the source selector.
  const hydrated = (await proxiedGet<{ MediaContainer?: { Metadata?: PlexMetadata[] } }>(
    `${base}/library/metadata/${meta.ratingKey}?includeGuids=1&includeMedia=1&X-Plex-Token=${encodeURIComponent(token)}`,
    headers
  ).catch(() => null))?.MediaContainer?.Metadata?.[0] ?? meta;
  const label = server.name || "Home Server";
  const out: StreamSource[] = [];
  const seen = new Set<string>();
  for (const [mediaIndex, media] of (hydrated.Media ?? []).entries()) {
    for (const [partIndex, part] of (media.Part ?? []).entries()) {
      if (!part.key) continue;
      const url = `${base}${part.key}?X-Plex-Token=${encodeURIComponent(token)}`;
      if (seen.has(url)) continue;
      seen.add(url);
      const container = (part.container ?? media.container ?? "").toLowerCase();
      const quality = media.videoResolution
        ? (media.videoResolution === "4k" ? "4K" : `${media.videoResolution}p`.replace("pp", "p"))
        : "Direct";
      out.push({
        source: [label, quality, container.toUpperCase()].filter(Boolean).join(" "),
        addonName: label,
        addonId: HOME_SERVER_ADDON_ID,
        quality,
        size: formatBytes(part.size ?? 0),
        sizeBytes: part.size && part.size > 0 ? part.size : null,
        url,
        transport: "file",
        media: plexSourceMedia(media, part),
        homeServer: {
          serverId: server.id, itemId: hydrated.ratingKey,
          mediaSourceId: media.id == null ? undefined : String(media.id), mediaIndex, partIndex
        },
        behaviorHints: { cached: true, filename: hydrated.title, videoSize: part.size && part.size > 0 ? part.size : null },
        description: `${hydrated.title} · ${label}`
      });
    }
  }
  return out;
}

function usableServers(servers: HomeServerConfig[]): HomeServerConfig[] {
  return servers.filter((s) => s.enabled && s.url && (s.type === "plex" ? Boolean(s.token) : true));
}

export async function resolveHomeServerMovieSources(
  servers: HomeServerConfig[],
  target: MatchTarget
): Promise<StreamSource[]> {
  const active = usableServers(servers);
  if (!active.length) return [];
  const cacheKey = homeServerSourceCacheKey("movie", active, target);
  return resolveCachedHomeServerSources(cacheKey, async () => {
    const perServer = await Promise.all(active.map(async (server) => {
      try {
        if (server.type === "plex") {
          const matches = await plexFindMetadataMatches(server, target, [1]); // 1 = movie
          return (await Promise.all(matches.map((meta) => plexMetadataSources(server, meta)))).flat();
        }
        const session = await ensureSession(server);
        if (!session) return [];
        const items = await jellyfinFindItems(server, session, target, "Movie");
        return (await Promise.all(items.map((item) => jellyfinItemSources(server, session, item)))).flat();
      } catch {
        return [];
      }
    }));
    return dedupeSources(perServer.flat());
  });
}

export async function resolveHomeServerEpisodeSources(
  servers: HomeServerConfig[],
  target: MatchTarget,
  season: number,
  episode: number
): Promise<StreamSource[]> {
  const active = usableServers(servers);
  if (!active.length) return [];
  const cacheKey = homeServerSourceCacheKey("episode", active, target, season, episode);
  return resolveCachedHomeServerSources(cacheKey, async () => {
    const perServer = await Promise.all(active.map(async (server) => {
      try {
        if (server.type === "plex") {
          return plexEpisodeSources(server, target, season, episode);
        }
        const session = await ensureSession(server);
        if (!session) return [];
        const series = await jellyfinFindItem(server, session, target, "Series");
        if (!series) return [];
        const base = trimUrl(server.url);
        const { token, userId } = session;
        // Keep separately stored episode versions instead of selecting the first one.
        const epRes = await proxiedGet<{ Items?: Array<JellyfinFullItem & { IndexNumber?: number; ParentIndexNumber?: number }> }>(
          `${base}/Shows/${series.Id}/Episodes?userId=${userId}&Fields=MediaSources,Path&api_key=${token}`
        ).catch(() => null);
        const episodes = (epRes?.Items ?? []).filter((item) =>
          item.ParentIndexNumber === season && item.IndexNumber === episode
        );
        return (await Promise.all(episodes.map((item) => jellyfinItemSources(server, session, item)))).flat();
      } catch {
        return [];
      }
    }));
    return dedupeSources(perServer.flat());
  });
}

async function plexEpisodeSources(
  server: HomeServerConfig,
  target: MatchTarget,
  season: number,
  episode: number
): Promise<StreamSource[]> {
  const base = trimUrl(server.url);
  const token = server.token ?? "";
  const headers = { Accept: "application/json", "X-Plex-Token": token };
  const series = await plexFindMetadata(server, target, [2]); // 2 = show
  if (!series) return [];
  // Grandchildren query gets episodes directly with season/episode indices.
  const res = await proxiedGet<{ MediaContainer?: { Metadata?: Array<PlexMetadata & { index?: number; parentIndex?: number }> } }>(
    `${base}/library/metadata/${series.ratingKey}/allLeaves?X-Plex-Token=${encodeURIComponent(token)}`,
    headers
  ).catch(() => null);
  const episodes = (res?.MediaContainer?.Metadata ?? []).filter(
    (item) => item.parentIndex === season && item.index === episode
  );
  return (await Promise.all(episodes.map((item) => plexMetadataSources(server, item)))).flat();
}

function dedupeSources(sources: StreamSource[]): StreamSource[] {
  const seen = new Set<string>();
  return sources.filter((s) => {
    const key = `${s.url ?? ""}|${s.source}`;
    if (seen.has(key)) return false;
    seen.add(key);
    return true;
  });
}

// ── Browsable libraries (for the watchlist list-source selector) ─────────────

export interface HomeServerLibraryOption {
  // Encoded source id: "hslib:<serverId>:<libraryKey>". The UI passes it back
  // to loadHomeServerLibraryItems.
  value: string;
  label: string;
  serverId: string;
  serverName: string;
  serverType: HomeServerConfig["type"];
  libraryId: string;
  libraryName: string;
  mediaType: "movie" | "tv" | "mixed";
}

function isBrowsableLibraryType(type?: string | null): boolean {
  return !type || BROWSABLE_LIBRARY_TYPES.has(type.toLowerCase().trim());
}

export type HomeServerLibrarySort = import("./librarySort").LibrarySort;
export interface HomeServerLibraryPage {
  items: MediaItem[];
  hasMore: boolean;
  total: number;
}

function libraryValue(serverId: string, libraryId: string): string {
  return `hslib:${encodeURIComponent(serverId)}:${encodeURIComponent(libraryId)}`;
}

export function homeServerLibraryMediaFilter(mediaType: HomeServerLibraryOption["mediaType"] | string | undefined): "movie" | "tv" | "all" {
  const normalized = mediaType?.trim().toLowerCase();
  if (["movie", "movies", "film", "films"].includes(normalized ?? "")) return "movie";
  if (["tv", "show", "shows", "series", "tvshow", "tvshows"].includes(normalized ?? "")) return "tv";
  return "all";
}

function parseLibraryValue(value: string): { serverId: string; libraryId: string } | null {
  const match = value.match(/^hslib:([^:]+):(.+)$/);
  if (!match) return null;
  try {
    return { serverId: decodeURIComponent(match[1]), libraryId: decodeURIComponent(match[2]) };
  } catch {
    return { serverId: match[1], libraryId: match[2] };
  }
}

// List each enabled server's movie/show libraries as selectable options.
export async function listHomeServerLibraries(
  servers: HomeServerConfig[]
): Promise<HomeServerLibraryOption[]> {
  const active = usableServers(servers);
  const perServer = await Promise.all(active.map(async (server) => {
    try {
      if (server.type === "plex") {
        const base = trimUrl(server.url);
        const token = server.token ?? "";
        const res = await proxiedGet<{ MediaContainer?: { Directory?: PlexSection[] } }>(
          `${base}/library/sections?X-Plex-Token=${encodeURIComponent(token)}`,
          { Accept: "application/json", "X-Plex-Token": token }
        );
        return (res?.MediaContainer?.Directory ?? [])
          .filter((s) => s.type === "movie" || s.type === "show")
          .map((s) => ({
            value: libraryValue(server.id, s.key),
            label: `${server.name} · ${s.title}`,
            serverId: server.id,
            serverName: server.name,
            serverType: server.type,
            libraryId: s.key,
            libraryName: s.title,
            mediaType: s.type === "movie" ? "movie" as const : "tv" as const
          }));
      }
      const session = await ensureSession(server);
      if (!session) return [];
      const base = trimUrl(server.url);
      const views = await proxiedGet<{ Items?: Array<{ Id: string; Name: string; CollectionType?: string }> }>(
        `${base}/Users/${session.userId}/Views?api_key=${session.token}`
      );
      return (views.Items ?? [])
        .filter((v) => isBrowsableLibraryType(v.CollectionType))
        .map((v) => ({
          value: libraryValue(server.id, v.Id),
          label: `${server.name} · ${v.Name}`,
          serverId: server.id,
          serverName: server.name,
          serverType: server.type,
          libraryId: v.Id,
          libraryName: v.Name,
          mediaType: v.CollectionType === "movies" ? "movie" as const : v.CollectionType === "tvshows" ? "tv" as const : "mixed" as const
        }));
    } catch {
      return [];
    }
  }));
  return perServer.flat();
}

// Load the items of a chosen library (recently-added first) as MediaItems.
export async function loadHomeServerLibraryItems(
  servers: HomeServerConfig[],
  source: string,
  limit = 60
): Promise<MediaItem[]> {
  return (await loadHomeServerLibraryPage(servers, source, { limit })).items;
}

export async function loadHomeServerLibraryPage(
  servers: HomeServerConfig[],
  source: string,
  options: {
    offset?: number;
    limit?: number;
    sort?: HomeServerLibrarySort;
    filter?: "all" | "movie" | "tv";
    libraryMediaType?: HomeServerLibraryOption["mediaType"];
    search?: string;
    throwOnError?: boolean;
  } = {}
): Promise<HomeServerLibraryPage> {
  const parsed = parseLibraryValue(source);
  if (!parsed) return { items: [], hasMore: false, total: 0 };
  const { serverId, libraryId: libraryKey } = parsed;
  const offset = Math.max(0, options.offset ?? 0);
  const limit = Math.min(100, Math.max(1, options.limit ?? 60));
  const sort = options.sort ?? "added";
  const filter = options.filter ?? "all";
  const search = options.search?.trim() ?? "";
  const server = servers.find((s) => s.id === serverId && s.enabled && s.url);
  if (!server) return { items: [], hasMore: false, total: 0 };
  const effectiveFilter = filter === "all" ? homeServerLibraryMediaFilter(options.libraryMediaType) : filter;
  try {
    if (server.type === "plex") {
      const base = trimUrl(server.url);
      const token = server.token ?? "";
      const params = new URLSearchParams({
        "X-Plex-Token": token,
        sort: sort === "release-newest" ? "originallyAvailableAt:desc" : sort === "release-oldest" ? "originallyAvailableAt:asc" : sort === "title" ? "titleSort:asc" : sort === "rating" ? "rating:desc" : "addedAt:desc",
        "X-Plex-Container-Start": String(offset),
        "X-Plex-Container-Size": String(limit),
        includeGuids: "1"
      });
      if (effectiveFilter !== "all") params.set("type", effectiveFilter === "movie" ? "1" : "2");
      if (search) params.set("title", search);
      const res = await proxiedGet<{ MediaContainer?: { Metadata?: PlexItem[]; totalSize?: number; size?: number } }>(
        `${base}/library/sections/${libraryKey}/all?${params.toString()}`,
        { Accept: "application/json", "X-Plex-Token": token }
      );
      const items = (res?.MediaContainer?.Metadata ?? [])
        .map((item) => mapPlexItem(base, token, item, server))
        .filter((m) => Boolean(m && m.title));
      const total = res?.MediaContainer?.totalSize ?? res?.MediaContainer?.size ?? items.length;
      return { items, total, hasMore: offset + items.length < total };
    }
    const session = await ensureSession(server);
    if (!session) return { items: [], hasMore: false, total: 0 };
    const base = trimUrl(server.url);
    const params = new URLSearchParams({
      ParentId: libraryKey,
      Recursive: "true",
      IncludeItemTypes: effectiveFilter === "movie" ? "Movie" : effectiveFilter === "tv" ? "Series" : "Movie,Series",
      SortBy: sort === "release-newest" || sort === "release-oldest" ? "PremiereDate" : sort === "title" ? "SortName" : sort === "rating" ? "CommunityRating" : "DateCreated",
      SortOrder: sort === "title" || sort === "release-oldest" ? "Ascending" : "Descending",
      StartIndex: String(offset),
      Limit: String(limit),
      Fields: "Overview,PrimaryImageAspectRatio,BasicSyncInfo,ImageTags,BackdropImageTags,ProductionYear,CommunityRating,ProviderIds,DateCreated",
      api_key: session.token
    });
    if (search) params.set("SearchTerm", search);
    const payload = await proxiedGet<{ Items?: JellyfinItem[]; TotalRecordCount?: number }>(
      `${base}/Users/${session.userId}/Items?${params.toString()}`
    );
    const items = (payload.Items ?? []).map((item) => mapItem(base, session.token, item, server)).filter((m) => Boolean(m && m.title));
    const total = payload.TotalRecordCount ?? items.length;
    return { items, total, hasMore: offset + items.length < total };
  } catch (error) {
    if (options.throwOnError) throw error;
    return { items: [], hasMore: false, total: 0 };
  }
}
