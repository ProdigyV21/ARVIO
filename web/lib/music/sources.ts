// The Music tab's sources in the browser: the user's own server (Navidrome/Subsonic or Jellyfin),
// music addons (Stremio-style with music types, or the Eclipse format) and the charts. Written from
// the public API descriptions, mirroring the Android app's sources so ids and favourites match.
import { md5Hex } from "./md5";
import { addonStreamUri, matchScore, MATCH_SCHEME, MATCH_THRESHOLD, placeholderParams } from "./text";
import {
  emptySearchResult, MusicError,
  type AlbumShelf, type MusicAddon, type MusicAddonCatalog, type MusicAlbum, type MusicArtist,
  type MusicSearchResult, type MusicServerConfig, type MusicShelf, type MusicSource, type MusicTrack
} from "./types";

type Json = Record<string, unknown>;
const obj = (value: unknown): Json => (value && typeof value === "object" && !Array.isArray(value) ? value as Json : {});
/** Some servers send a lone object where a one-item list is meant; both read as a list. */
const list = (value: unknown): Json[] => Array.isArray(value) ? value.filter((v) => v && typeof v === "object") as Json[] : value && typeof value === "object" ? [value as Json] : [];
const str = (value: unknown) => (typeof value === "string" && value ? value : typeof value === "number" ? String(value) : null);
const num = (value: unknown) => (typeof value === "number" && Number.isFinite(value) ? Math.trunc(value) : typeof value === "string" && value.trim() && Number.isFinite(Number(value)) ? Math.trunc(Number(value)) : null);
const strings = (value: unknown) => (Array.isArray(value) ? value.filter((v): v is string => typeof v === "string") : []);

const SHELVES: AlbumShelf[] = ["NEWEST", "RECENT", "FREQUENT", "RANDOM"];

/** A secure page cannot load an http:// address (mixed content), except the browser's own machine. */
export function blockedOnThisPage(url: string): boolean {
  if (typeof window === "undefined" || window.location.protocol !== "https:") return false;
  try {
    const target = new URL(url);
    return target.protocol === "http:" && !["localhost", "127.0.0.1", "[::1]"].includes(target.hostname);
  } catch {
    return false;
  }
}

export class InsecureServerError extends MusicError {
  constructor() {
    super("This address starts with http://, which browsers block on a secure (https) page. Use the server's https address.");
  }
}

/** Accepts "music.example.com", "192.168.1.5:4533/" and full URLs alike. */
export function normalizeServerUrl(input: string): string {
  const trimmed = input.trim().replace(/\/+$/, "");
  if (!trimmed) return trimmed;
  if (/^https?:\/\//i.test(trimmed)) return trimmed;
  const host = trimmed.split("/")[0];
  const name = host.split(":")[0];
  // A LAN address or an explicit port is almost always a plain-HTTP home server; a bare domain is HTTPS.
  const local = host.includes(":") || /^[\d.]+$/.test(name) || name.endsWith(".local") || name.endsWith(".lan") || !name.includes(".");
  return (local ? "http://" : "https://") + trimmed;
}

async function fetchJson(url: string, init?: RequestInit, viaProxy = false): Promise<Json> {
  if (blockedOnThisPage(url)) throw new InsecureServerError();
  let response: Response;
  try {
    response = await fetch(url, { ...init, headers: { accept: "application/json", ...(init?.headers ?? {}) }, signal: init?.signal ?? AbortSignal.timeout(20000) });
  } catch (error) {
    // An addon that sends no CORS headers is still readable through ARVIO's proxy (JSON only, never audio).
    if (viaProxy && !(init?.method && init.method !== "GET")) {
      const proxied = await fetch(`/api/proxy?url=${encodeURIComponent(url)}`, { headers: { accept: "application/json" }, signal: AbortSignal.timeout(20000) }).catch(() => null);
      if (proxied?.ok) return obj(await proxied.json().catch(() => ({})));
    }
    throw new MusicError(error instanceof DOMException && error.name === "TimeoutError" ? "The server took too long to answer" : "Could not reach the server from this browser");
  }
  if (response.status === 401) throw new MusicError("Wrong username or password");
  if (!response.ok) throw new MusicError(`HTTP ${response.status}`);
  try {
    return obj(await response.json());
  } catch {
    throw new MusicError("Unexpected server response");
  }
}

// ── Subsonic / OpenSubsonic (Navidrome, Gonic, Airsonic, Ampache, LMS …) ──

export function subsonicCredentials(password: string) {
  const bytes = crypto.getRandomValues(new Uint8Array(8));
  const salt = Array.from(bytes, (b) => b.toString(16).padStart(2, "0")).join("");
  return { token: md5Hex(password + salt), salt };
}

export class SubsonicSource implements MusicSource {
  constructor(private config: MusicServerConfig) {}

  url(method: string, params: Record<string, string> = {}) {
    const url = new URL(`${this.config.baseUrl.replace(/\/+$/, "")}/rest/${method}`);
    const all = { u: this.config.username, t: this.config.token, s: this.config.salt ?? "", v: "1.16.1", c: "ARVIO", f: "json", ...params };
    for (const [k, v] of Object.entries(all)) url.searchParams.set(k, v);
    return url.toString();
  }

  private async call(method: string, params: Record<string, string> = {}) {
    const root = await fetchJson(this.url(method, params));
    const response = obj(root["subsonic-response"]);
    if (!("status" in response)) throw new MusicError("Not a Subsonic server");
    if (response.status !== "ok") {
      const error = obj(response.error);
      throw new MusicError(str(error.message) ?? `Subsonic error ${str(error.code) ?? ""}`.trim());
    }
    return response;
  }

  ping() { return this.call("ping"); }

  async shelves(): Promise<MusicShelf[]> {
    const rows = await Promise.all(SHELVES.map(async (kind) => {
      const albums = kind === "NEWEST" ? await this.albums(kind) : await this.albums(kind).catch(() => []);
      return { key: kind, albums, kind };
    }));
    return rows.filter((row) => row.albums.length);
  }

  async albums(shelf: AlbumShelf, size = 24): Promise<MusicAlbum[]> {
    const type = { NEWEST: "newest", RECENT: "recent", FREQUENT: "frequent", RANDOM: "random", ALPHABETICAL: "alphabeticalByName" }[shelf];
    const body = await this.call("getAlbumList2", { type, size: String(size) });
    return list(obj(body.albumList2).album).map((o) => this.parseAlbum(o));
  }

  async artists() {
    const body = await this.call("getArtists");
    return list(obj(body.artists).index).flatMap((i) => list(i.artist)).map((o) => this.parseArtist(o));
  }

  async artistAlbums(artistId: string) {
    const body = await this.call("getArtist", { id: artistId });
    return list(obj(body.artist).album).map((o) => this.parseAlbum(o)).sort((a, b) => (b.year ?? 0) - (a.year ?? 0));
  }

  async albumTracks(albumId: string) {
    const body = await this.call("getAlbum", { id: albumId });
    return list(obj(body.album).song).map((o) => this.parseTrack(o));
  }

  async search(query: string): Promise<MusicSearchResult> {
    const result = obj((await this.call("search3", { query, artistCount: "12", albumCount: "24", songCount: "40" })).searchResult3);
    return {
      artists: list(result.artist).map((o) => this.parseArtist(o)),
      albums: list(result.album).map((o) => this.parseAlbum(o)),
      tracks: list(result.song).map((o) => this.parseTrack(o)),
      playlists: []
    };
  }

  async reportPlayed(trackId: string) { await this.call("scrobble", { id: trackId, submission: "true" }); }

  streamUrl(trackId: string) { return this.url("stream", { id: trackId }); }

  private cover(coverArt: string | null) { return coverArt ? this.url("getCoverArt", { id: coverArt, size: "600" }) : null; }

  private parseAlbum(o: Json): MusicAlbum {
    return { id: str(o.id) ?? "", name: str(o.name) ?? str(o.title) ?? str(o.album) ?? "", artist: str(o.artist) ?? "", artistId: str(o.artistId), coverUrl: this.cover(str(o.coverArt)), year: num(o.year), trackCount: num(o.songCount) };
  }

  private parseArtist(o: Json): MusicArtist {
    const image = str(o.artistImageUrl);
    return { id: str(o.id) ?? "", name: str(o.name) ?? "", coverUrl: image?.startsWith("http") ? image : this.cover(str(o.coverArt)), albumCount: num(o.albumCount) };
  }

  private parseTrack(o: Json): MusicTrack {
    const id = str(o.id) ?? "";
    return { id, title: str(o.title) ?? "", artist: str(o.artist) ?? "", album: str(o.album) ?? "", albumId: str(o.albumId), durationSec: num(o.duration) ?? 0, coverUrl: this.cover(str(o.coverArt) ?? str(o.albumId)), streamUrl: this.streamUrl(id), trackNumber: num(o.track) };
  }
}

// ── Jellyfin (also Emby, whose music API is the same) ──

export function jellyfinAuthorization(deviceId: string, token?: string) {
  return `MediaBrowser Client="ARVIO", Device="Web", DeviceId="${deviceId}", Version="1.0"${token ? `, Token="${token}"` : ""}`;
}

export async function jellyfinSignIn(baseUrl: string, username: string, password: string, deviceId: string) {
  const body = await fetchJson(`${baseUrl.replace(/\/+$/, "")}/Users/AuthenticateByName`, {
    method: "POST",
    headers: { "content-type": "application/json", "x-emby-authorization": jellyfinAuthorization(deviceId) },
    body: JSON.stringify({ Username: username, Pw: password })
  });
  const token = str(body.AccessToken);
  const userId = str(obj(body.User).Id);
  if (!token || !userId) throw new MusicError("Not a Jellyfin server");
  return { token, userId };
}

export class JellyfinMusicSource implements MusicSource {
  constructor(private config: MusicServerConfig) {}

  private url(path: string, params: Record<string, string> = {}) {
    const url = new URL(`${this.config.baseUrl.replace(/\/+$/, "")}/${path}`);
    for (const [k, v] of Object.entries(params)) url.searchParams.set(k, v);
    // The token as a query parameter keeps every call a simple request (no CORS preflight).
    url.searchParams.set("api_key", this.config.token);
    return url.toString();
  }

  private items(params: Record<string, string>) {
    return fetchJson(this.url(`Users/${this.config.userId}/Items`, { Recursive: "true", ...params }));
  }

  async shelves(): Promise<MusicShelf[]> {
    const rows = await Promise.all(SHELVES.map(async (kind) => {
      const albums = kind === "NEWEST" ? await this.albums(kind) : await this.albums(kind).catch(() => []);
      return { key: kind, albums, kind };
    }));
    return rows.filter((row) => row.albums.length);
  }

  async albums(shelf: AlbumShelf, size = 24) {
    const [sortBy, order] = { NEWEST: ["DateCreated,SortName", "Descending"], RECENT: ["DatePlayed,SortName", "Descending"], FREQUENT: ["PlayCount,SortName", "Descending"], RANDOM: ["Random", "Ascending"], ALPHABETICAL: ["SortName", "Ascending"] }[shelf];
    const body = await this.items({ IncludeItemTypes: "MusicAlbum", SortBy: sortBy, SortOrder: order, Limit: String(size), Fields: "ChildCount", ...(shelf === "RECENT" || shelf === "FREQUENT" ? { Filters: "IsPlayed" } : {}) });
    return list(body.Items).map((o) => this.parseAlbum(o));
  }

  async artists() {
    const body = await fetchJson(this.url("Artists/AlbumArtists", { UserId: this.config.userId ?? "", SortBy: "SortName", SortOrder: "Ascending", Limit: "1000" }));
    return list(body.Items).map((o) => this.parseArtist(o));
  }

  async artistAlbums(artistId: string) {
    const body = await this.items({ IncludeItemTypes: "MusicAlbum", AlbumArtistIds: artistId, SortBy: "ProductionYear,SortName", SortOrder: "Descending", Fields: "ChildCount" });
    return list(body.Items).map((o) => this.parseAlbum(o));
  }

  async albumTracks(albumId: string) {
    const body = await this.items({ ParentId: albumId, IncludeItemTypes: "Audio", SortBy: "ParentIndexNumber,IndexNumber,SortName", SortOrder: "Ascending" });
    return list(body.Items).map((o) => this.parseTrack(o));
  }

  async search(query: string): Promise<MusicSearchResult> {
    const items = list((await this.items({ SearchTerm: query, IncludeItemTypes: "MusicArtist,MusicAlbum,Audio", Limit: "80", Fields: "ChildCount" })).Items);
    return {
      artists: items.filter((o) => o.Type === "MusicArtist").map((o) => this.parseArtist(o)),
      albums: items.filter((o) => o.Type === "MusicAlbum").map((o) => this.parseAlbum(o)),
      tracks: items.filter((o) => o.Type === "Audio").map((o) => this.parseTrack(o)),
      playlists: []
    };
  }

  async reportPlayed(trackId: string) {
    await fetch(this.url(`Users/${this.config.userId}/PlayedItems/${trackId}`), { method: "POST" }).catch(() => undefined);
  }

  streamUrl(trackId: string) { return this.url(`Audio/${trackId}/stream`, { static: "true" }); }

  private image(itemId: string | null) {
    if (!itemId) return null;
    const url = new URL(`${this.config.baseUrl.replace(/\/+$/, "")}/Items/${itemId}/Images/Primary`);
    url.searchParams.set("fillHeight", "600");
    url.searchParams.set("quality", "90");
    return url.toString();
  }

  private hasImage(o: Json) { return "Primary" in obj(o.ImageTags); }

  private parseAlbum(o: Json): MusicAlbum {
    const id = str(o.Id) ?? "";
    const albumArtist = list(o.AlbumArtists)[0];
    return { id, name: str(o.Name) ?? "", artist: str(o.AlbumArtist) ?? str(albumArtist?.Name) ?? "", artistId: str(albumArtist?.Id), coverUrl: this.hasImage(o) ? this.image(id) : null, year: num(o.ProductionYear), trackCount: num(o.ChildCount) };
  }

  private parseArtist(o: Json): MusicArtist {
    const id = str(o.Id) ?? "";
    return { id, name: str(o.Name) ?? "", coverUrl: this.hasImage(o) ? this.image(id) : null, albumCount: num(o.AlbumCount) };
  }

  private parseTrack(o: Json): MusicTrack {
    const id = str(o.Id) ?? "";
    const albumId = str(o.AlbumId);
    const artists = list(o.ArtistItems).map((a) => str(a.Name)).filter(Boolean).join(", ");
    return {
      id, title: str(o.Name) ?? "", artist: artists || str(o.AlbumArtist) || "", album: str(o.Album) ?? "", albumId,
      durationSec: Math.floor((num(o.RunTimeTicks) ?? 0) / 10_000_000),
      coverUrl: this.hasImage(o) ? this.image(id) : str(o.AlbumPrimaryImageTag) ? this.image(albumId) : null,
      streamUrl: this.streamUrl(id), trackNumber: num(o.IndexNumber)
    };
  }
}

// ── Music addons ──

export const ECLIPSE = "eclipse";
const ECLIPSE_TYPES = new Set(["track", "album", "artist", "playlist", "file"]);

/** Accepts a manifest URL, a base URL, or a stremio:// link. */
export function normalizeAddonUrl(input: string) {
  let value = input.trim();
  if (/^stremio:\/\//i.test(value)) value = "https://" + value.slice("stremio://".length);
  value = value.replace(/\/+$/, "").replace(/\/manifest\.json$/, "").replace(/\/+$/, "");
  return normalizeServerUrl(value);
}

export async function installMusicAddon(input: string): Promise<MusicAddon> {
  const base = normalizeAddonUrl(input);
  if (!base) throw new MusicError("Enter the addon address");
  const manifest = await fetchJson(`${base}/manifest.json`, undefined, true);
  const types = strings(manifest.types);
  const resources = strings(manifest.resources);
  const eclipse = !types.includes("music") && (resources.includes("search") || resources.includes("stream")) && types.every((t) => ECLIPSE_TYPES.has(t));
  if (eclipse) {
    return {
      baseUrl: base, id: str(manifest.id) ?? base, name: str(manifest.name) ?? base.replace(/^https?:\/\//, "").split("/")[0], protocol: ECLIPSE,
      catalogs: list(manifest.catalogs).filter((c) => str(c.id)).map((c) => ({ id: str(c.id)!, name: str(c.name) ?? str(c.id)!, searchOnly: false, searchable: false, type: str(c.type) ?? "album" }))
    };
  }
  const catalogs: MusicAddonCatalog[] = list(manifest.catalogs).filter((c) => c.type === "music" && str(c.id)).map((c) => {
    const search = list(c.extra).find((e) => e.name === "search");
    return { id: str(c.id)!, name: str(c.name) ?? str(c.id)!, searchOnly: search?.isRequired === true, searchable: Boolean(search) };
  });
  if (!types.includes("music") && !catalogs.length) throw new MusicError("This is not a music addon");
  return { baseUrl: base, id: str(manifest.id) ?? base, name: str(manifest.name) ?? base.replace(/^https?:\/\//, ""), catalogs, protocol: null };
}

// Path segments are encoded like Android's addPathSegment: "=" stays, so "search=…" reaches the addon as written.
const path = (...segments: string[]) => segments.map((s) => encodeURIComponent(s).replace(/%3D/gi, "=")).join("/");

export class MusicAddonSource implements MusicSource {
  constructor(public addon: MusicAddon) {}
  private get(...segments: string[]) { return fetchJson(`${this.addon.baseUrl}/${path(...segments)}`, undefined, true); }

  async shelves(): Promise<MusicShelf[]> {
    const rows = await Promise.all(this.addon.catalogs.filter((c) => !c.searchOnly).map(async (catalog): Promise<MusicShelf | null> => {
      try {
        const albums = list((await this.get("catalog", "music", `${catalog.id}.json`)).metas).filter((m) => m.type === "album").map((m) => this.parseAlbum(m));
        return { key: `${this.addon.id}/${catalog.id}`, title: catalog.name, albums };
      } catch { return null; }
    }));
    return rows.filter((row): row is MusicShelf => Boolean(row?.albums.length));
  }

  async artists() {
    const parts = await Promise.all(this.addon.catalogs.filter((c) => !c.searchOnly).map((c) => this.get("catalog", "music", `${c.id}.json`).then((b) => list(b.metas)).catch(() => [] as Json[])));
    return dedupe(parts.flat().filter((m) => m.type === "artist").map((m) => this.parseArtist(m)));
  }

  async artistAlbums(artistId: string) {
    return list(obj((await this.get("meta", "artist", `${artistId}.json`)).meta).albums).map((m) => this.parseAlbum(m));
  }

  async albumTracks(albumId: string) {
    const meta = obj((await this.get("meta", "album", `${albumId}.json`)).meta);
    const album = this.parseAlbum(meta);
    return list(meta.videos).map((v, i) => this.parseTrack(v, album, i));
  }

  async search(query: string): Promise<MusicSearchResult> {
    const parts = (await Promise.all(this.addon.catalogs.filter((c) => c.searchable).map((c) =>
      this.get("catalog", "music", c.id, `search=${query}.json`).then((b) => list(b.metas)).catch(() => [] as Json[])))).flat();
    return {
      artists: dedupe(parts.filter((m) => m.type === "artist").map((m) => this.parseArtist(m))),
      albums: dedupe(parts.filter((m) => m.type === "album").map((m) => this.parseAlbum(m))),
      tracks: dedupe(parts.filter((m) => m.type === "track").map((m) => this.parseTrack(m, null, null))),
      playlists: []
    };
  }

  async reportPlayed() {}

  private parseAlbum(o: Json): MusicAlbum {
    return { id: str(o.id) ?? "", name: str(o.name) ?? "", artist: str(o.artist) ?? "", artistId: str(o.artistId), coverUrl: str(o.poster), year: num(str(o.releaseInfo)?.slice(0, 4)) ?? num(o.year), trackCount: num(o.trackCount) };
  }

  private parseArtist(o: Json): MusicArtist {
    return { id: str(o.id) ?? "", name: str(o.name) ?? "", coverUrl: str(o.poster), albumCount: num(o.albumCount) };
  }

  private parseTrack(o: Json, album: MusicAlbum | null, index: number | null): MusicTrack {
    const id = str(o.id) ?? "";
    const direct = list(o.streams).map((s) => str(s.url)).find(Boolean);
    return {
      id, title: str(o.title) ?? str(o.name) ?? "", artist: str(o.artist) ?? album?.artist ?? "", album: str(o.album) ?? album?.name ?? "",
      albumId: str(o.albumId) ?? album?.id ?? null, durationSec: num(o.duration) ?? 0, coverUrl: str(o.thumbnail) ?? str(o.poster) ?? album?.coverUrl ?? null,
      streamUrl: direct ?? addonStreamUri(this.addon.baseUrl, id), trackNumber: num(o.number) ?? (index === null ? null : index + 1)
    };
  }
}

const ALBUM = "album:", PLAYLIST = "playlist:", CATALOG = "catalog:";

export class EclipseAddonSource implements MusicSource {
  constructor(public addon: MusicAddon) {}
  private get(segments: string[], query: Record<string, string> = {}) {
    const url = new URL(`${this.addon.baseUrl}/${path(...segments)}`);
    for (const [k, v] of Object.entries(query)) url.searchParams.set(k, v);
    return fetchJson(url.toString(), undefined, true);
  }
  private catalogItems(id: string) { return this.get(["catalog", id], { skip: "0" }).then((b) => list(b.items)); }

  async shelves(): Promise<MusicShelf[]> {
    const rows = await Promise.all(this.addon.catalogs.filter((c) => !c.searchOnly && c.type !== "artist").map(async (catalog): Promise<MusicShelf | null> => {
      try {
        const items = await this.catalogItems(catalog.id);
        // A row of songs becomes one card that opens (and plays) the whole row.
        const albums: MusicAlbum[] = catalog.type === "track"
          ? items.slice(0, 1).map((first) => ({ id: CATALOG + catalog.id, name: catalog.name, artist: this.addon.name, artistId: null, coverUrl: str(first.artworkURL), trackCount: items.length }))
          : items.filter((i) => i.type === "album" || i.type === "playlist").map((i) => this.parseAlbum(i, str(i.type)));
        return { key: `${this.addon.id}/${catalog.id}`, title: catalog.name, albums };
      } catch { return null; }
    }));
    return rows.filter((row): row is MusicShelf => Boolean(row?.albums.length));
  }

  async artists() {
    const parts = await Promise.all(this.addon.catalogs.filter((c) => c.type === "artist").map((c) => this.catalogItems(c.id).then((items) => items.map((i) => this.parseArtist(i))).catch(() => [])));
    return dedupe(parts.flat());
  }

  async artistAlbums(artistId: string) {
    return list((await this.get(["artist", artistId])).albums).map((a) => this.parseAlbum(a, "album"));
  }

  async albumTracks(albumId: string) {
    if (albumId.startsWith(CATALOG)) {
      const id = albumId.slice(CATALOG.length);
      const name = this.addon.catalogs.find((c) => c.id === id)?.name ?? "";
      return (await this.catalogItems(id)).filter((o) => !o.type || o.type === "track").map((o, i) => {
        const track = this.parseTrack(o, null, i);
        return track.album ? track : { ...track, album: name };
      });
    }
    const playlist = albumId.startsWith(PLAYLIST);
    const id = albumId.replace(/^playlist:/, "").replace(/^album:/, "");
    const body = await this.get([playlist ? "playlist" : "album", id]);
    const album = this.parseAlbum(body, playlist ? "playlist" : "album");
    return list(body.tracks).map((o, i) => this.parseTrack(o, playlist ? null : album, i));
  }

  async search(query: string): Promise<MusicSearchResult> {
    const body = await this.get(["search"], { q: query });
    return {
      artists: list(body.artists).map((o) => this.parseArtist(o)),
      albums: list(body.albums).map((o) => this.parseAlbum(o, "album")),
      playlists: list(body.playlists).map((o) => this.parseAlbum(o, "playlist")),
      tracks: list(body.tracks).map((o) => this.parseTrack(o, null, null))
    };
  }

  async reportPlayed() {}

  private parseAlbum(o: Json, type: string | null): MusicAlbum {
    const prefix = type === "playlist" ? PLAYLIST : ALBUM;
    return { id: prefix + (str(o.id) ?? "").replace(/^playlist:/, "").replace(/^album:/, ""), name: str(o.title) ?? str(o.name) ?? "", artist: str(o.artist) ?? str(o.creator) ?? "", artistId: str(o.artistId), coverUrl: str(o.artworkURL), year: num(str(o.year)?.slice(0, 4)), trackCount: num(o.trackCount) };
  }

  private parseArtist(o: Json): MusicArtist {
    return { id: str(o.id) ?? "", name: str(o.name) ?? str(o.title) ?? "", coverUrl: str(o.artworkURL) };
  }

  private parseTrack(o: Json, album: MusicAlbum | null, index: number | null): MusicTrack {
    const id = str(o.id) ?? "";
    const ms = num(o.durationMs);
    return {
      id, title: str(o.title) ?? "", artist: str(o.artist) ?? album?.artist ?? "", album: str(o.album) ?? album?.name ?? "",
      albumId: str(o.albumId) ? ALBUM + str(o.albumId) : album?.id ?? null, durationSec: num(o.duration) ?? (ms ? Math.floor(ms / 1000) : 0),
      coverUrl: str(o.artworkURL) ?? album?.coverUrl ?? null, streamUrl: str(o.streamURL) ?? addonStreamUri(this.addon.baseUrl, id, ECLIPSE),
      trackNumber: num(o.trackNumber) ?? (index === null ? null : index + 1)
    };
  }
}

/** Asks an addon for a track's stream when it starts playing. */
export async function resolveAddonStream(uri: string): Promise<string> {
  const params = placeholderParams(uri);
  const base = params.get("base") ?? "";
  const id = params.get("id") ?? "";
  if (params.get("p") === ECLIPSE) {
    const url = str((await fetchJson(`${base}/stream/${encodeURIComponent(id)}`, undefined, true)).url);
    if (url) return url;
  } else {
    const url = list((await fetchJson(`${base}/stream/track/${encodeURIComponent(id)}.json`, undefined, true)).streams).map((s) => str(s.url)).find(Boolean);
    if (url) return url;
  }
  throw new MusicError("No stream for this track");
}

// ── Charts ──

export const CHARTS_KEY = "charts";

export class ChartsSource implements MusicSource {
  private trackCache = new Map<string, MusicTrack[]>();
  constructor(private language: string) {}

  private async api(query: string) {
    const response = await fetch(`/api/music/deezer?${query}`, { headers: { accept: "application/json" } });
    if (!response.ok) throw new MusicError("Charts unavailable");
    return obj(await response.json());
  }

  async shelves(): Promise<MusicShelf[]> {
    const d = new Date();
    const day = `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, "0")}-${String(d.getDate()).padStart(2, "0")}`;
    return list((await this.api(`home=${encodeURIComponent(this.language)}&d=${day}`)).shelves) as unknown as MusicShelf[];
  }
  async artists() { return []; }
  async artistAlbums() { return []; }

  async albumTracks(albumId: string) {
    const id = albumId.replace(/^playlist:/, "");
    const cached = this.trackCache.get(id);
    if (cached) return cached;
    const tracks = list((await this.api(`playlist=${encodeURIComponent(id)}`)).tracks) as unknown as MusicTrack[];
    this.trackCache.set(id, tracks);
    return tracks;
  }

  /** Only playlists: songs from here would duplicate the ones the user's sources find themselves. */
  async search(query: string) {
    return { ...emptySearchResult(), playlists: list((await this.api(`q=${encodeURIComponent(query)}`)).playlists) as unknown as MusicAlbum[] };
  }
  async reportPlayed() {}
}

function dedupe<T extends { id: string }>(items: T[]): T[] {
  const seen = new Set<string>();
  return items.filter((item) => !seen.has(item.id) && seen.add(item.id));
}

// ── All sources as one library ──

export const SEPARATOR = "~";
export const SERVER_KEY = "server";

/**
 * Every id is prefixed with its source's key ("server~abc", "tv.arvio.music.audius~xyz",
 * "charts~playlist:1"), so a later call goes back to the source the item came from.
 */
export class CompositeMusicSource implements MusicSource {
  private keys: string[];
  constructor(private sources: MusicSource[], keys: string[]) {
    this.keys = keys.map((k) => k.replaceAll(SEPARATOR, "_"));
  }

  private tag(i: number, id: string) { return `${this.keys[i]}${SEPARATOR}${id}`; }
  private album(i: number, a: MusicAlbum): MusicAlbum { return { ...a, id: this.tag(i, a.id), artistId: a.artistId ? this.tag(i, a.artistId) : null }; }
  private artist(i: number, a: MusicArtist): MusicArtist { return { ...a, id: this.tag(i, a.id) }; }
  private track(i: number, t: MusicTrack): MusicTrack { return { ...t, id: this.tag(i, t.id), albumId: t.albumId ? this.tag(i, t.albumId) : null }; }

  route(id: string): [number, string] | null {
    const at = id.indexOf(SEPARATOR);
    if (at < 0) return null;
    const index = this.keys.indexOf(id.slice(0, at));
    return index < 0 ? null : [index, id.slice(at + 1)];
  }

  sourceKey(index: number) { return this.keys[index]; }
  source(index: number) { return this.sources[index]; }

  async shelves() {
    const parts = await Promise.all(this.sources.map((source, i) => source.shelves()
      .then((shelves) => shelves.map((s) => ({ ...s, key: `${i}/${s.key}`, albums: s.albums.map((a) => this.album(i, a)) })))
      .catch((error: unknown) => ({ error }))));
    const shelves = parts.flatMap((p) => Array.isArray(p) ? p : []);
    if (!shelves.length) {
      const failure = parts.find((p): p is { error: unknown } => !Array.isArray(p));
      if (failure) throw failure.error;
    }
    return shelves;
  }

  async artists() {
    const parts = await Promise.all(this.sources.map((s, i) => s.artists().then((a) => a.map((x) => this.artist(i, x))).catch(() => [])));
    return parts.flat().sort((a, b) => a.name.localeCompare(b.name));
  }

  async artistAlbums(artistId: string) {
    const route = this.route(artistId);
    if (!route) return [];
    return (await this.sources[route[0]].artistAlbums(route[1])).map((a) => this.album(route[0], a));
  }

  async albumTracks(albumId: string) {
    const route = this.route(albumId);
    if (!route) return [];
    return (await this.sources[route[0]].albumTracks(route[1])).map((t) => this.track(route[0], t));
  }

  async search(query: string, only?: (key: string) => boolean): Promise<MusicSearchResult> {
    const parts = await Promise.all(this.sources.map((s, i) => (only && !only(this.keys[i]) ? Promise.resolve(emptySearchResult()) : s.search(query).catch(() => emptySearchResult())).then((r) => [i, r] as const)));
    return {
      artists: parts.flatMap(([i, r]) => r.artists.map((x) => this.artist(i, x))),
      albums: parts.flatMap(([i, r]) => r.albums.map((x) => this.album(i, x))),
      tracks: parts.flatMap(([i, r]) => r.tracks.map((x) => this.track(i, x))),
      playlists: parts.flatMap(([i, r]) => r.playlists.map((x) => this.album(i, x)))
    };
  }

  async reportPlayed(trackId: string) {
    const route = this.route(trackId);
    if (route) await this.sources[route[0]].reportPlayed(route[1]);
  }

  /** The user's own track for a chart song, found by name in their sources (not the charts). */
  async findMatch(title: string, artist: string, durationSec: number): Promise<MusicTrack | null> {
    const query = [artist, title].filter(Boolean).join(" ");
    const found = (await this.search(query, (key) => key !== CHARTS_KEY)).tracks.filter((t) => !t.streamUrl.startsWith(`${MATCH_SCHEME}:`));
    let best: MusicTrack | null = null, bestScore = 0;
    for (const track of found) {
      const score = matchScore({ title, artist, durationSec }, track);
      if (score > bestScore) { best = track; bestScore = score; }
    }
    return bestScore >= MATCH_THRESHOLD ? best : null;
  }
}
