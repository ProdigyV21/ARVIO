// The Music tab's player: one <audio> element for the whole app, so music keeps playing while
// the user moves between sections. Chart songs and addon songs carry placeholders that are
// resolved when they start; a song no source has is skipped, as on Android.
import { useSyncExternalStore } from "react";
import { premiumAllows } from "../premiumGate";
import { musicLibrary, musicSource, recordMusicPlay } from "./library";
import { ADDON_STREAM_SCHEME, MATCH_SCHEME, placeholderParams } from "./text";
import { blockedOnThisPage, resolveAddonStream, SERVER_KEY, SEPARATOR } from "./sources";
import type { MusicTrack } from "./types";

export type RepeatMode = "off" | "all" | "one";

export interface MusicPlayerState {
  queue: MusicTrack[];
  index: number;
  playing: boolean;
  loading: boolean;
  position: number;
  duration: number;
  repeat: RepeatMode;
  shuffle: boolean;
  /** The last problem, shown briefly (a song not found in the user's sources). */
  notice: string | null;
  expanded: boolean;
}

let state: MusicPlayerState = { queue: [], index: -1, playing: false, loading: false, position: 0, duration: 0, repeat: "off", shuffle: false, notice: null, expanded: false };
const listeners = new Set<() => void>();
function set(patch: Partial<MusicPlayerState>) {
  state = { ...state, ...patch };
  listeners.forEach((listener) => listener());
}
export const musicPlayer = {
  get: () => state,
  subscribe(listener: () => void) { listeners.add(listener); return () => { listeners.delete(listener); }; }
};
export function useMusicPlayer() {
  return useSyncExternalStore(musicPlayer.subscribe, musicPlayer.get, musicPlayer.get);
}
export const currentTrack = (s: MusicPlayerState = state) => (s.index >= 0 ? s.queue[s.index] ?? null : null);

let audio: HTMLAudioElement | null = null;
let loadToken = 0;
let reported = "";
let noticeTimer = 0;
/** Songs skipped in a row; a queue where nothing plays stops instead of skipping forever. */
let skippedInRow = 0;

function element() {
  if (audio) return audio;
  audio = new Audio();
  audio.preload = "auto";
  audio.addEventListener("timeupdate", () => {
    const a = audio!;
    set({ position: a.currentTime, duration: Number.isFinite(a.duration) ? a.duration : currentTrack()?.durationSec ?? 0 });
    const track = currentTrack();
    // Counts as listened after half the song or four minutes, as scrobblers do.
    if (track && reported !== track.id && a.duration > 0 && (a.currentTime > a.duration / 2 || a.currentTime > 240)) {
      reported = track.id;
      void musicSource()?.source.reportPlayed(track.id).catch(() => undefined);
      recordMusicPlay(track.artist);
    }
    if ("mediaSession" in navigator && Number.isFinite(a.duration) && a.duration > 0) {
      try { navigator.mediaSession.setPositionState({ duration: a.duration, position: Math.min(a.currentTime, a.duration), playbackRate: a.playbackRate }); } catch { /* not supported */ }
    }
  });
  audio.addEventListener("playing", () => { skippedInRow = 0; set({ playing: true, loading: false }); });
  audio.addEventListener("pause", () => set({ playing: false }));
  audio.addEventListener("waiting", () => set({ loading: true }));
  audio.addEventListener("ended", () => {
    if (state.repeat === "one") { audio!.currentTime = 0; void audio!.play(); return; }
    next(true);
  });
  audio.addEventListener("error", () => {
    if (!audio?.src) return;
    skip("This song could not be played");
  });
  installMediaSession();
  return audio;
}

function notice(text: string) {
  window.clearTimeout(noticeTimer);
  set({ notice: text });
  noticeTimer = window.setTimeout(() => set({ notice: null }), 4000);
}

function skip(reason: string) {
  notice(reason);
  skippedInRow += 1;
  if (skippedInRow >= Math.min(8, state.queue.length)) {
    set({ playing: false, loading: false });
    return;
  }
  next(true);
}

/** The playable URL for a track: placeholders resolved, server tracks rebuilt with this browser's login. */
async function streamFor(track: MusicTrack): Promise<string> {
  const lib = musicSource();
  const url = track.streamUrl;
  if (url.startsWith(`${MATCH_SCHEME}:`)) {
    const params = placeholderParams(url);
    const found = lib ? await lib.source.findMatch(params.get("t") ?? track.title, params.get("a") ?? track.artist, Number(params.get("d")) || track.durationSec) : null;
    if (!found) throw new Error(`Not in your sources: ${track.title}`);
    return streamFor(found);
  }
  if (url.startsWith(`${ADDON_STREAM_SCHEME}:`)) return resolveAddonStream(url);
  // A song from the server (also one liked on another device) streams with this browser's login.
  if (track.id.startsWith(SERVER_KEY + SEPARATOR) && lib?.server) return lib.server.streamUrl(track.id.slice(SERVER_KEY.length + 1));
  return url;
}

async function load(autoplay = true) {
  const track = currentTrack();
  const a = element();
  const token = ++loadToken;
  if (!track) { a.removeAttribute("src"); a.load(); set({ playing: false, loading: false, position: 0, duration: 0 }); return; }
  set({ loading: true, position: 0, duration: track.durationSec });
  updateMediaMetadata(track);
  let url: string;
  try {
    url = await streamFor(track);
  } catch (error) {
    if (token === loadToken) skip(error instanceof Error ? error.message : "This song could not be played");
    return;
  }
  if (token !== loadToken) return;
  if (blockedOnThisPage(url)) { skip("This song's server uses http://, which browsers block on a secure page"); return; }
  a.src = url;
  if (autoplay) {
    try { await a.play(); }
    catch (error) {
      if (token !== loadToken) return;
      // A blocked autoplay just waits for the play button; anything else skips.
      if (error instanceof DOMException && error.name === "NotAllowedError") set({ loading: false, playing: false });
      else skip("This song could not be played");
    }
  }
}

function shuffled<T>(items: T[]) {
  const out = [...items];
  for (let i = out.length - 1; i > 0; i--) { const j = Math.floor(Math.random() * (i + 1)); [out[i], out[j]] = [out[j], out[i]]; }
  return out;
}

/** Plays a list from one song; asks for Premium first on the hosted web app, like any playback. */
export function playTracks(tracks: MusicTrack[], startIndex = 0, options: { shuffle?: boolean } = {}) {
  if (!tracks.length) return;
  if (!premiumAllows("play", () => playTracks(tracks, startIndex, options))) return;
  let queue = tracks;
  let index = Math.max(0, Math.min(startIndex, tracks.length - 1));
  if (options.shuffle) { queue = shuffled(tracks); index = 0; }
  skippedInRow = 0;
  set({ queue, index, shuffle: Boolean(options.shuffle) });
  void load();
}

export function playNext(tracks: MusicTrack[]) {
  if (!state.queue.length) { playTracks(tracks); return; }
  const queue = [...state.queue];
  queue.splice(state.index + 1, 0, ...tracks);
  set({ queue });
}

export function addToQueue(tracks: MusicTrack[]) {
  if (!state.queue.length) { playTracks(tracks); return; }
  set({ queue: [...state.queue, ...tracks] });
}

export function jumpTo(index: number) {
  if (index < 0 || index >= state.queue.length) return;
  skippedInRow = 0;
  set({ index });
  void load();
}

export function togglePlay() {
  const a = element();
  if (!currentTrack()) return;
  if (a.paused) {
    if (!a.src) { void load(); return; }
    void a.play().catch(() => undefined);
  } else a.pause();
}

export function pauseMusic() { audio?.pause(); }

export function next(auto = false) {
  if (!state.queue.length) return;
  if (state.index + 1 < state.queue.length) { set({ index: state.index + 1 }); void load(); return; }
  if (state.repeat === "all" || !auto) { set({ index: 0 }); void load(auto || state.playing); return; }
  set({ playing: false, loading: false });
}

export function previous() {
  const a = element();
  // Like other players: back to the start first, the previous song only right at the start.
  if (a.currentTime > 4 || state.index === 0) { a.currentTime = 0; return; }
  set({ index: state.index - 1 });
  void load();
}

export function seek(seconds: number) {
  const a = element();
  if (Number.isFinite(seconds)) a.currentTime = Math.max(0, Math.min(seconds, a.duration || seconds));
}

export function cycleRepeat() { set({ repeat: state.repeat === "off" ? "all" : state.repeat === "all" ? "one" : "off" }); }

export function toggleShuffle() {
  const track = currentTrack();
  if (!track) { set({ shuffle: !state.shuffle }); return; }
  // Shuffles what is still to come; the current song keeps playing.
  if (!state.shuffle) {
    const rest = shuffled(state.queue.filter((_, i) => i !== state.index));
    set({ shuffle: true, queue: [track, ...rest], index: 0 });
  } else set({ shuffle: false });
}

export function removeFromQueue(index: number) {
  if (index === state.index || index < 0 || index >= state.queue.length) return;
  const queue = state.queue.filter((_, i) => i !== index);
  set({ queue, index: index < state.index ? state.index - 1 : state.index });
}

export function setExpanded(expanded: boolean) { set({ expanded }); }

export function stopMusic() {
  loadToken++;
  audio?.pause();
  audio?.removeAttribute("src");
  audio?.load();
  set({ queue: [], index: -1, playing: false, loading: false, position: 0, duration: 0, expanded: false });
  if ("mediaSession" in navigator) navigator.mediaSession.metadata = null;
}

// ── Lock screen, headphones and media keys ──

function installMediaSession() {
  if (!("mediaSession" in navigator)) return;
  const session = navigator.mediaSession;
  const handlers: Array<[MediaSessionAction, MediaSessionActionHandler]> = [
    ["play", () => togglePlay()],
    ["pause", () => pauseMusic()],
    ["nexttrack", () => next()],
    ["previoustrack", () => previous()],
    ["seekto", (details) => seek(details.seekTime ?? 0)]
  ];
  for (const [action, handler] of handlers) {
    try { session.setActionHandler(action, handler); } catch { /* unsupported action */ }
  }
}

function updateMediaMetadata(track: MusicTrack) {
  if (!("mediaSession" in navigator) || typeof MediaMetadata === "undefined") return;
  navigator.mediaSession.metadata = new MediaMetadata({
    title: track.title,
    artist: track.artist,
    album: track.album,
    artwork: track.coverUrl && !blockedOnThisPage(track.coverUrl) ? [{ src: track.coverUrl, sizes: "600x600" }] : []
  });
}

// Profile switches stop the music: the next profile has its own sources.
let lastProfile: string | null = null;
musicLibrary.subscribe(() => {
  const profile = musicLibrary.get().profileKey;
  if (profile !== lastProfile) {
    if (lastProfile !== null && state.queue.length) stopMusic();
    lastProfile = profile;
  }
});
