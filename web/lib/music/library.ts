// What the Music tab plays from and keeps, per profile: the server login (this browser only),
// music addons and the user's liked songs, favourite albums and playlists (both synced with the
// Android app through the cloud profile settings).
import { useSyncExternalStore } from "react";
import { loadStored, saveStored } from "../storage";
import { pullCloudMusic, saveCloudMusic, type CloudMusicField } from "../cloud";
import { authClient } from "../store/clients";
import type { HomeServerConfig } from "../types";
import {
  ChartsSource, CHARTS_KEY, CompositeMusicSource, EclipseAddonSource, ECLIPSE, JellyfinMusicSource, MusicAddonSource,
  SERVER_KEY, SubsonicSource, blockedOnThisPage, installMusicAddon, jellyfinSignIn, normalizeServerUrl, subsonicCredentials
} from "./sources";
import { browserCountry } from "./chartCatalog";
import { emptyTaste, parseTaste, recordPlay, recordSearch, type MusicTaste } from "./taste";
import {
  emptyCollections, MusicError,
  type MusicAddon, type MusicAlbum, type MusicCollections, type MusicPlaylist, type MusicServerConfig, type MusicServerType, type MusicSource, type MusicTrack
} from "./types";

export interface MusicLibraryState {
  profileKey: string | null;
  server: MusicServerConfig | null;
  addons: MusicAddon[];
  collections: MusicCollections;
  /** Artists listened to and searches, for the Music home's personal rows (synced). */
  taste: MusicTaste;
  /** The synced home server (Jellyfin/Emby) used for music when no music login is saved here. */
  homeServer: MusicServerConfig | null;
  language: string;
  /** The cloud copy was read (or there is no cloud account). */
  synced: boolean;
}

let state: MusicLibraryState = { profileKey: null, server: null, addons: [], collections: emptyCollections(), taste: emptyTaste(), homeServer: null, language: "en-US", synced: false };
const listeners = new Set<() => void>();
function set(patch: Partial<MusicLibraryState>) {
  state = { ...state, ...patch };
  listeners.forEach((listener) => listener());
}
export const musicLibrary = {
  get: () => state,
  subscribe(listener: () => void) { listeners.add(listener); return () => { listeners.delete(listener); }; }
};
export function useMusicLibrary() {
  return useSyncExternalStore(musicLibrary.subscribe, musicLibrary.get, musicLibrary.get);
}

const storageKey = (kind: string) => `arvio.web.music.${kind}:${state.profileKey ?? "none"}`;

// Gson leaves fields missing from older saves null, whatever Kotlin says.
function sanitizeTrack(t: unknown): MusicTrack | null {
  const o = t as Partial<MusicTrack> | null;
  if (!o || typeof o.id !== "string") return null;
  return { id: o.id, title: o.title ?? "", artist: o.artist ?? "", album: o.album ?? "", albumId: o.albumId ?? null, durationSec: Number(o.durationSec) || 0, coverUrl: o.coverUrl ?? null, streamUrl: o.streamUrl ?? "", trackNumber: o.trackNumber ?? null };
}
function sanitizeAlbum(a: unknown): MusicAlbum | null {
  const o = a as Partial<MusicAlbum> | null;
  if (!o || typeof o.id !== "string") return null;
  return { id: o.id, name: o.name ?? "", artist: o.artist ?? "", artistId: o.artistId ?? null, coverUrl: o.coverUrl ?? null, year: o.year ?? null, trackCount: o.trackCount ?? null };
}
export function parseCollections(json: string | null | undefined): MusicCollections {
  try {
    const raw = JSON.parse(json || "{}") as Record<string, unknown>;
    const arr = (v: unknown) => (Array.isArray(v) ? v : []);
    return {
      favoriteTracks: arr(raw.favoriteTracks).map(sanitizeTrack).filter((t): t is MusicTrack => Boolean(t)),
      favoriteAlbums: arr(raw.favoriteAlbums).map(sanitizeAlbum).filter((a): a is MusicAlbum => Boolean(a)),
      playlists: arr(raw.playlists).filter((p) => p && typeof (p as MusicPlaylist).id === "string").map((p) => {
        const playlist = p as Partial<MusicPlaylist>;
        return { id: playlist.id!, name: playlist.name || "Playlist", tracks: arr(playlist.tracks).map(sanitizeTrack).filter((t): t is MusicTrack => Boolean(t)), origin: playlist.origin ?? null };
      })
    };
  } catch {
    return emptyCollections();
  }
}
export function parseAddons(json: string | null | undefined): MusicAddon[] {
  try {
    const raw = JSON.parse(json || "[]");
    return (Array.isArray(raw) ? raw : []).filter((a) => a && typeof a.baseUrl === "string" && a.baseUrl)
      .map((a) => ({ baseUrl: a.baseUrl, id: a.id || a.baseUrl, name: a.name || a.baseUrl, catalogs: Array.isArray(a.catalogs) ? a.catalogs.filter(Boolean) : [], protocol: a.protocol ?? null }));
  } catch {
    return [];
  }
}

/** A synced Jellyfin/Emby home server that can play music here without a separate login. */
function musicHomeServer(servers: HomeServerConfig[] | undefined): MusicServerConfig | null {
  const server = (servers ?? []).find((s) => s.enabled && (s.type === "jellyfin" || s.type === "emby") && s.url && s.token && s.userId && !blockedOnThisPage(s.url));
  return server ? { type: "JELLYFIN", baseUrl: server.url.replace(/\/+$/, ""), username: server.userName || server.username || "", token: server.token!, userId: server.userId, fromHomeServer: server.name || server.type } : null;
}

let loadGeneration = 0;

/** Called whenever the account, profile, language or home servers change. */
export function syncMusicProfile(accountId: string | undefined, profileId: string | null | undefined, language: string, homeServers: HomeServerConfig[] | undefined) {
  const profileKey = profileId ? `${accountId ?? "local"}:${profileId}` : null;
  const homeServer = musicHomeServer(homeServers);
  if (profileKey === state.profileKey) {
    if (language !== state.language || JSON.stringify(homeServer) !== JSON.stringify(state.homeServer)) set({ language, homeServer });
    return;
  }
  const generation = ++loadGeneration;
  state = { ...state, profileKey };
  set({
    server: loadStored<MusicServerConfig | null>(storageKey("server"), null),
    addons: parseAddons(loadStored<string | null>(storageKey("addons"), null)),
    collections: parseCollections(loadStored<string | null>(storageKey("collections"), null)),
    taste: parseTaste(loadStored<string | null>(storageKey("taste"), null)),
    homeServer, language, synced: !authClient.session
  });
  if (!profileId || !authClient.session) return;
  void pullCloudMusic(authClient, profileId).then((cloud) => {
    if (generation !== loadGeneration) return;
    const patch: Partial<MusicLibraryState> = { synced: true };
    // A change made here that has not reached the cloud yet wins over the copy read back.
    if (cloud.musicAddonsJson && !pending.has("musicAddonsJson")) {
      patch.addons = parseAddons(cloud.musicAddonsJson);
      saveStored(storageKey("addons"), cloud.musicAddonsJson);
    }
    if (cloud.musicCollectionsJson && !pending.has("musicCollectionsJson")) {
      patch.collections = parseCollections(cloud.musicCollectionsJson);
      saveStored(storageKey("collections"), cloud.musicCollectionsJson);
    }
    if (cloud.musicTasteJson && !pending.has("musicTasteJson")) {
      patch.taste = parseTaste(cloud.musicTasteJson);
      saveStored(storageKey("taste"), cloud.musicTasteJson);
    }
    set(patch);
  }).catch(() => { if (generation === loadGeneration) set({ synced: true }); });
}

const pending = new Map<CloudMusicField, number>();

function persist(field: CloudMusicField, json: string) {
  saveStored(storageKey(field === "musicAddonsJson" ? "addons" : field === "musicTasteJson" ? "taste" : "collections"), json);
  const profileId = state.profileKey?.split(":").slice(1).join(":");
  if (!profileId || !authClient.session) return;
  window.clearTimeout(pending.get(field));
  // Changes in quick succession (liking several songs) go up as one write.
  pending.set(field, window.setTimeout(() => {
    pending.delete(field);
    void saveCloudMusic(authClient, profileId, field, json).catch(() => undefined);
  }, 1500));
}

export function updateCollections(transform: (c: MusicCollections) => MusicCollections) {
  const collections = transform(state.collections);
  set({ collections });
  persist("musicCollectionsJson", JSON.stringify(collections));
}

function updateTaste(transform: (t: MusicTaste) => MusicTaste) {
  const taste = transform(state.taste);
  if (taste === state.taste) return;
  set({ taste });
  persist("musicTasteJson", JSON.stringify(taste));
}

/** A song was listened to (half of it, or four minutes): feeds "Because you listened to". */
export function recordMusicPlay(artist: string) {
  if (artist.trim()) updateTaste((t) => recordPlay(t, artist, Date.now()));
}

/** A music search the user stayed on: feeds "Based on your searches". */
export function recordMusicSearch(query: string) {
  updateTaste((t) => recordSearch(t, query, Date.now()));
}

/** The country for the charts, from the browser's time zone and languages (no permission, no request). */
function detectCountry(): string | null {
  if (typeof window === "undefined") return null;
  try {
    return browserCountry(Intl.DateTimeFormat().resolvedOptions().timeZone, navigator.languages?.length ? navigator.languages : [navigator.language]);
  } catch {
    return null;
  }
}

function setAddons(addons: MusicAddon[]) {
  set({ addons });
  persist("musicAddonsJson", JSON.stringify(addons));
}

export async function addMusicAddon(url: string) {
  const addon = await installMusicAddon(url);
  setAddons([...state.addons.filter((a) => a.baseUrl !== addon.baseUrl && a.id !== addon.id), addon]);
  return addon;
}

export function removeMusicAddon(baseUrl: string) {
  setAddons(state.addons.filter((a) => a.baseUrl !== baseUrl));
}

function deviceId() {
  const existing = loadStored<string | null>("arvio.web.music.device", null);
  if (existing) return existing;
  const id = `arvio-web-${crypto.randomUUID()}`;
  saveStored("arvio.web.music.device", id);
  return id;
}

/** Checks the login against the server and saves it only when it works. */
export async function connectMusicServer(type: MusicServerType, url: string, username: string, password: string) {
  const baseUrl = normalizeServerUrl(url);
  if (!baseUrl) throw new MusicError("Enter the server address");
  if (blockedOnThisPage(baseUrl)) throw new MusicError("This address starts with http://, which browsers block on a secure (https) page. Use the server's https address.");
  let config: MusicServerConfig;
  if (type === "SUBSONIC") {
    const { token, salt } = subsonicCredentials(password);
    config = { type, baseUrl, username: username.trim(), token, salt };
    await new SubsonicSource(config).ping();
  } else {
    const id = deviceId();
    const { token, userId } = await jellyfinSignIn(baseUrl, username.trim(), password, id);
    config = { type, baseUrl, username: username.trim(), token, userId, deviceId: id };
  }
  saveStored(storageKey("server"), config);
  set({ server: config });
  return config;
}

export function disconnectMusicServer() {
  saveStored(storageKey("server"), null);
  set({ server: null });
}

/** The server music plays from: this browser's own login, else the synced Jellyfin/Emby home server. */
export const activeServer = (s: MusicLibraryState) => s.server ?? s.homeServer;
export const hasMusicSources = (s: MusicLibraryState) => Boolean(activeServer(s)) || s.addons.length > 0;

let cached: { signature: string; source: CompositeMusicSource; server: SubsonicSource | JellyfinMusicSource | null } | null = null;

export function musicSource(s: MusicLibraryState = state) {
  if (!hasMusicSources(s)) return null;
  const server = activeServer(s);
  const signature = JSON.stringify([server, s.addons, s.language]);
  if (cached?.signature === signature) return cached;
  const keys: string[] = [];
  const sources: MusicSource[] = [];
  let serverSource: SubsonicSource | JellyfinMusicSource | null = null;
  if (server) {
    serverSource = server.type === "SUBSONIC" ? new SubsonicSource(server) : new JellyfinMusicSource(server);
    keys.push(SERVER_KEY);
    sources.push(serverSource);
  }
  for (const addon of s.addons) {
    keys.push(addon.id || addon.baseUrl);
    sources.push(addon.protocol === ECLIPSE ? new EclipseAddonSource(addon) : new MusicAddonSource(addon));
  }
  // The charts come last, so the user's own sources keep their place in ids and search.
  keys.push(CHARTS_KEY);
  // The taste is read when the home loads, so a new play does not rebuild the sources.
  sources.push(new ChartsSource(s.language, detectCountry(), () => state.taste));
  cached = { signature, source: new CompositeMusicSource(sources, keys), server: serverSource };
  return cached;
}

// ── Collections: pure updates, as on Android (MusicCollections.kt) ──

export const isFavoriteTrack = (c: MusicCollections, id: string) => c.favoriteTracks.some((t) => t.id === id);
export const isFavoriteAlbum = (c: MusicCollections, id: string) => c.favoriteAlbums.some((a) => a.id === id);

export function toggleFavoriteTrack(track: MusicTrack) {
  updateCollections((c) => isFavoriteTrack(c, track.id)
    ? { ...c, favoriteTracks: c.favoriteTracks.filter((t) => t.id !== track.id) }
    : { ...c, favoriteTracks: [track, ...c.favoriteTracks] });
}

export function toggleFavoriteAlbum(album: MusicAlbum) {
  updateCollections((c) => isFavoriteAlbum(c, album.id)
    ? { ...c, favoriteAlbums: c.favoriteAlbums.filter((a) => a.id !== album.id) }
    : { ...c, favoriteAlbums: [album, ...c.favoriteAlbums] });
}

const unique = (tracks: MusicTrack[]) => { const seen = new Set<string>(); return tracks.filter((t) => !seen.has(t.id) && seen.add(t.id)); };
const editPlaylist = (id: string, edit: (p: MusicPlaylist) => MusicPlaylist) =>
  updateCollections((c) => ({ ...c, playlists: c.playlists.map((p) => (p.id === id ? edit(p) : p)) }));

export function createPlaylist(name: string, tracks: MusicTrack[] = []) {
  const id = crypto.randomUUID();
  updateCollections((c) => ({ ...c, playlists: [...c.playlists, { id, name: name.trim() || "Playlist", tracks: unique(tracks), origin: null }] }));
  return id;
}
export function renamePlaylist(id: string, name: string) { if (name.trim()) editPlaylist(id, (p) => ({ ...p, name: name.trim() })); }
export function deletePlaylist(id: string) { updateCollections((c) => ({ ...c, playlists: c.playlists.filter((p) => p.id !== id) })); }
export function addToPlaylist(id: string, tracks: MusicTrack[]) {
  editPlaylist(id, (p) => {
    const present = new Set(p.tracks.map((t) => t.id));
    return { ...p, tracks: [...p.tracks, ...unique(tracks).filter((t) => !present.has(t.id))] };
  });
}
export function removeFromPlaylist(id: string, index: number) { editPlaylist(id, (p) => ({ ...p, tracks: p.tracks.filter((_, i) => i !== index) })); }
export function moveInPlaylist(id: string, from: number, to: number) {
  editPlaylist(id, (p) => {
    if (from === to || from < 0 || to < 0 || from >= p.tracks.length || to >= p.tracks.length) return p;
    const tracks = [...p.tracks];
    tracks.splice(to, 0, tracks.splice(from, 1)[0]);
    return { ...p, tracks };
  });
}
