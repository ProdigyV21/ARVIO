import { mediaSourceConstructor } from "./capabilities";
import { classifyMediaError } from "./playerRecovery";
import type { LoadPolicy } from "hls.js";

export type PlaybackTransport = "file" | "hls" | "dash" | "mpegts";

export type PlaybackError = {
  transport: PlaybackTransport;
  kind: "network" | "media" | "unsupported" | "unknown";
  /** The transport has stopped automatic recovery. */
  fatal: boolean;
  retryable: boolean;
  message: string;
  code?: string | number;
};

export type PlaybackAudioTrack = { id: string; label: string; language?: string };
export type PlaybackQuality = { id: string; label: string; width?: number; height?: number; bitrate?: number };
export type PlaybackTracks = {
  audioTracks: PlaybackAudioTrack[];
  qualities: PlaybackQuality[];
  selectedAudioTrackId: string | null;
  /** null means automatic quality selection. */
  selectedQualityId: string | null;
};

/** Callable for compatibility with existing effect cleanup functions. */
export type PlaybackHandle = (() => void) & {
  destroy(): void;
  /** Reload this source, preserving VOD position by default. Does not force autoplay. */
  reload(resumeAt?: number): void;
  selectAudioTrack(id: string): boolean;
  /** null selects Auto. Unsupported or unavailable selections return false. */
  selectQuality(id: string | null): boolean;
  goLive(): boolean;
};

export type PlaybackOptions = {
  onError?: (error?: PlaybackError) => void;
  /** Track IDs belong to this handle; empty lists mean the engine exposes no selection. */
  onTracks?: (tracks: PlaybackTracks) => void;
  transport?: PlaybackTransport;
  /** Browser-permitted headers for adaptive/TS requests; native src cannot set headers. */
  requestHeaders?: Record<string, string>;
  live?: boolean;
};

const HLS_NETWORK_RECOVERIES = 2;
const HLS_MEDIA_RECOVERIES = 2;
const owners = new WeakMap<HTMLVideoElement, PlaybackHandle>();
const emptyTracks = (): PlaybackTracks => ({ audioTracks: [], qualities: [], selectedAudioTrackId: null, selectedQualityId: null });
type Controls = Pick<PlaybackHandle, "selectAudioTrack" | "selectQuality" | "goLive">;
type NativeAudioTrack = { id: string; label: string; language: string; enabled: boolean };
type NativeAudioTracks = EventTarget & ArrayLike<NativeAudioTrack>;

function inferTransport(url: string, live: boolean): PlaybackTransport {
  // Decode once for existing encoded media URLs, without changing the request URL.
  let decoded = url;
  try { decoded = decodeURIComponent(url); } catch { /* A malformed escape need not make a URL unplayable. */ }
  if (/\.m3u8(?:$|[?#&])|application\/(?:vnd\.apple\.mpegurl|x-mpegurl)/i.test(decoded)) return "hls";
  if (/\.mpd(?:$|[?#&])|application\/dash\+xml/i.test(decoded)) return "dash";
  if (/\.ts(?:$|[?#&])/i.test(decoded)) return "mpegts";
  if (live && /\/live\/[^/]+\/[^/]+\/\d+(?:$|[?#&])/i.test(decoded)) return "mpegts";
  return "file";
}

function preferNativeHls() {
  if (typeof navigator === "undefined") return false;
  const ua = navigator.userAgent;
  return /iPad|iPhone|iPod/.test(ua) || (/Safari/.test(ua) && !/Chrome|Chromium|Android|Edg|OPR/.test(ua));
}

function qualityLabel(height?: number, bitrate?: number, index = 0) {
  return height ? `${height}p` : bitrate ? `${Math.round(bitrate / 1000)} kbps` : `Quality ${index + 1}`;
}

function seekToLiveEdge(video: HTMLVideoElement): boolean {
  const ranges = video.seekable.length ? video.seekable : video.buffered;
  if (!ranges.length) return false;
  const last = ranges.length - 1;
  const end = ranges.end(last);
  if (!Number.isFinite(end)) return false;
  video.currentTime = Math.max(ranges.start(last), end - 0.5);
  return true;
}

function hlsLoadPolicy(): LoadPolicy {
  const retry = { maxNumRetry: 2, retryDelayMs: 500, maxRetryDelayMs: 2_000 };
  return { default: { maxTimeToFirstByteMs: 10_000, maxLoadTimeMs: 20_000, timeoutRetry: { ...retry }, errorRetry: { ...retry } } };
}

export function attachPlayback(video: HTMLVideoElement, url: string, options: PlaybackOptions = {}): PlaybackHandle {
  owners.get(video)?.();
  const live = options.live === true;
  const transport = options.transport ?? inferTransport(url, live);
  const headers = { ...options.requestHeaders };
  const hasHeaders = Object.keys(headers).length > 0;
  const memory = typeof navigator === "undefined" ? undefined : (navigator as Navigator & { deviceMemory?: number }).deviceMemory;
  // Unknown memory includes iOS, so use the smaller budget unless RAM is advertised.
  const lowMemory = !memory || memory <= 4;
  let disposed = false;
  let generation = 0;
  let cleanup: (() => void)[] = [];
  let controls: Controls | null = null;
  let audioSelection: string | null = null;
  let qualitySelection: string | null = null;

  const release = () => {
    controls = null;
    const previous = cleanup;
    cleanup = [];
    for (const dispose of previous.reverse()) {
      try { dispose(); } catch { /* Engines may throw while tearing down a failed MediaSource. */ }
    }
  };
  const resetVideo = () => {
    video.removeAttribute("src");
    video.load();
  };
  const destroy = () => {
    if (disposed) return;
    disposed = true;
    generation += 1;
    release();
    if (owners.get(video) === handle) {
      owners.delete(video);
      resetVideo();
    }
  };
  const handle: PlaybackHandle = Object.assign(destroy, {
    destroy,
    reload: (resumeAt?: number) => {
      if (!disposed) start(resumeAt ?? (live ? undefined : video.currentTime), !video.paused && !video.ended);
    },
    selectAudioTrack: (id: string) => {
      try {
        if (!controls?.selectAudioTrack(id)) return false;
        audioSelection = id;
        return true;
      } catch { return false; }
    },
    selectQuality: (id: string | null) => {
      try {
        if (!controls?.selectQuality(id)) return false;
        qualitySelection = id;
        return true;
      } catch { return false; }
    },
    goLive: () => {
      try { return live && (controls?.goLive() ?? false); } catch { return false; }
    }
  });

  function start(resumeAt?: number, resumePlaying = false) {
    const session = ++generation;
    release();
    resetVideo();
    let failed = false;
    const active = () => !disposed && !failed && session === generation;
    const fail = (kind: PlaybackError["kind"], message: string, code?: string | number, retryable = kind === "network" || kind === "unknown") => {
      if (!active()) return;
      failed = true;
      release();
      // Return the handle before notifying initial failures, and cancel queued notifications too.
      void Promise.resolve().then(() => {
        if (!disposed && session === generation) options.onError?.({ transport, kind, fatal: true, retryable, message, code });
      });
    };
    const publish = (tracks: PlaybackTracks) => { if (active()) options.onTracks?.(tracks); };
    const listen = (target: EventTarget, event: string, listener: () => void) => {
      const guarded = () => { if (active()) listener(); };
      target.addEventListener(event, guarded);
      cleanup.push(() => target.removeEventListener(event, guarded));
    };
    let restored = false;
    const restore = () => {
      if (restored) return;
      if (resumeAt !== undefined && Number.isFinite(resumeAt) && resumeAt > 0) {
        let target = resumeAt;
        if (Number.isFinite(video.duration) && video.duration > 0) target = Math.min(target, Math.max(0, video.duration - 0.1));
        try { video.currentTime = target; } catch { return; }
      }
      restored = true;
      if (resumePlaying) void video.play().catch(() => { /* Autoplay rejection is not a source failure. */ });
    };
    listen(video, "loadedmetadata", restore);
    listen(video, "canplay", restore);

    const native = () => {
      if (!active()) return;
      if (hasHeaders) {
        fail("unsupported", "Native media playback cannot send custom request headers.", "NATIVE_HEADERS_UNSUPPORTED");
        return;
      }
      const tracks = (video as HTMLVideoElement & { audioTracks?: NativeAudioTracks }).audioTracks;
      const snapshot = () => {
        const audio = Array.from(tracks ?? []);
        publish({ ...emptyTracks(), audioTracks: audio.map((track, index) => ({
          id: track.id || String(index), label: track.label || track.language || `Audio ${index + 1}`, language: track.language || undefined
        })), selectedAudioTrackId: audio.reduce<string | null>((id, track, index) => track.enabled ? track.id || String(index) : id, null) });
      };
      controls = {
        selectAudioTrack: (id) => {
          const audio = Array.from(tracks ?? []);
          const selected = audio.findIndex((track, index) => (track.id || String(index)) === id);
          if (selected < 0) return false;
          audio.forEach((track, index) => { track.enabled = selected === index; });
          snapshot();
          return true;
        },
        selectQuality: () => false,
        goLive: () => seekToLiveEdge(video)
      };
      listen(video, "error", () => {
        const code = video.error?.code;
        if (!code) return;
        fail(code === 4 ? "unsupported" : code === 3 ? "media" : "network", "Native media playback failed.", code, classifyMediaError(code) === "retryable");
      });
      listen(video, "loadedmetadata", () => {
        if (audioSelection !== null) controls?.selectAudioTrack(audioSelection);
        snapshot();
      });
      if (tracks) for (const event of ["addtrack", "removetrack", "change"]) listen(tracks, event, snapshot);
      video.src = url;
      snapshot();
    };
    const unsupported = () => fail("unsupported", `This browser does not support ${transport} playback.`, "TRANSPORT_UNSUPPORTED");
    const importFailure = () => fail("unknown", `Could not initialize the ${transport} playback engine.`, "ENGINE_LOAD_FAILED");
    const prepareMse = () => {
      if (typeof window === "undefined" || !("ManagedMediaSource" in window)) return;
      // MMS needs remote playback disabled when no AirPlay alternative source is supplied.
      const previous = video.disableRemotePlayback;
      video.disableRemotePlayback = true;
      cleanup.push(() => { video.disableRemotePlayback = previous; });
    };
    publish(emptyTracks());
    if (!active()) return;

    if (transport === "file") { native(); return; }
    const mse = !!mediaSourceConstructor();
    const nativeHls = !!video.canPlayType("application/vnd.apple.mpegurl");
    if (transport === "hls") {
      if (nativeHls && !hasHeaders && (!mse || preferNativeHls())) { native(); return; }
      if (!mse) { unsupported(); return; }
      void import("hls.js").then(({ default: Hls }) => {
        if (!active()) return;
        if (!Hls.isSupported()) { if (nativeHls && !hasHeaders) native(); else unsupported(); return; }
        prepareMse();
        const instance = new Hls({
          enableWorker: true, preferManagedMediaSource: true, lowLatencyMode: live,
          backBufferLength: lowMemory ? 15 : 30,
          maxBufferLength: lowMemory ? 20 : 30, maxMaxBufferLength: lowMemory ? 30 : 60,
          maxBufferSize: (lowMemory ? 20 : 40) * 1024 * 1024,
          manifestLoadPolicy: hlsLoadPolicy(), playlistLoadPolicy: hlsLoadPolicy(),
          fragLoadPolicy: hlsLoadPolicy(), keyLoadPolicy: hlsLoadPolicy(),
          xhrSetup: (xhr) => { for (const [name, value] of Object.entries(headers)) xhr.setRequestHeader(name, value); }
        });
        cleanup.push(() => instance.destroy());
        let networkAttempts = 0;
        let mediaAttempts = 0;
        let retryTimer: ReturnType<typeof setTimeout> | undefined;
        cleanup.push(() => clearTimeout(retryTimer));
        instance.on(Hls.Events.ERROR, (_event, data) => {
          if (!active() || !data.fatal) return;
          const network = data.type === Hls.ErrorTypes.NETWORK_ERROR;
          const media = data.type === Hls.ErrorTypes.MEDIA_ERROR;
          const status = data.response?.code;
          const retryable = network && (!status || status === 408 || status === 429 || status >= 500);
          if (retryable && retryTimer !== undefined) return;
          if (retryable && networkAttempts < HLS_NETWORK_RECOVERIES) {
            networkAttempts += 1;
            retryTimer = setTimeout(() => {
              retryTimer = undefined;
              if (!active()) return;
              try {
                if (/manifest/i.test(data.details)) instance.loadSource(url);
                else instance.startLoad();
              } catch { fail("network", "HLS network recovery failed.", data.details); }
            }, 500 * networkAttempts);
            return;
          }
          if (media && mediaAttempts < HLS_MEDIA_RECOVERIES) {
            mediaAttempts += 1;
            try { instance.recoverMediaError(); } catch { fail("media", "HLS media recovery failed.", data.details); }
            return;
          }
          fail(network ? "network" : media ? "media" : "unknown", "HLS playback recovery exhausted.", data.details, network ? retryable : !media);
        });
        const snapshot = () => {
          if (!active()) return;
          publish({
            audioTracks: instance.audioTracks.map((track, index) => ({ id: String(index), label: track.name || track.lang || `Audio ${index + 1}`, language: track.lang })),
            qualities: instance.levels.map((level, index) => ({ id: String(index), label: qualityLabel(level.height, level.bitrate, index), width: level.width, height: level.height, bitrate: level.bitrate })),
            selectedAudioTrackId: instance.audioTrack < 0 ? null : String(instance.audioTrack),
            selectedQualityId: instance.autoLevelEnabled ? null : String(instance.manualLevel)
          });
        };
        controls = {
          selectAudioTrack: (id) => {
            const index = instance.audioTracks.findIndex((_track, i) => String(i) === id);
            if (index < 0) return false;
            if (instance.audioTrack !== index) instance.audioTrack = index;
            snapshot();
            return true;
          },
          selectQuality: (id) => {
            const index = id === null ? -1 : instance.levels.findIndex((_level, i) => String(i) === id);
            if (id !== null && index < 0) return false;
            instance.currentLevel = index;
            snapshot();
            return true;
          },
          goLive: () => {
            const position = instance.liveSyncPosition;
            if (position !== null && Number.isFinite(position) && position >= 0) { video.currentTime = position; return true; }
            return seekToLiveEdge(video);
          }
        };
        const restoreSelections = () => {
          if (!active()) return;
          if (audioSelection !== null) controls?.selectAudioTrack(audioSelection);
          if (qualitySelection !== null) controls?.selectQuality(qualitySelection);
          snapshot();
        };
        instance.on(Hls.Events.MANIFEST_PARSED, restoreSelections);
        instance.on(Hls.Events.AUDIO_TRACKS_UPDATED, restoreSelections);
        instance.on(Hls.Events.LEVELS_UPDATED, snapshot);
        instance.on(Hls.Events.AUDIO_TRACK_SWITCHED, snapshot);
        instance.on(Hls.Events.LEVEL_SWITCHED, snapshot);
        instance.loadSource(url);
        if (active()) instance.attachMedia(video);
      }).catch(importFailure);
      return;
    }

    if (transport === "dash") {
      if (!mse) { unsupported(); return; }
      void import("dashjs").then((dashjs) => {
        if (!active()) return;
        prepareMse();
        const instance = dashjs.MediaPlayer().create();
        cleanup.push(() => instance.reset());
        instance.updateSettings({ streaming: {
          buffer: { bufferToKeep: lowMemory ? 10 : 20, bufferTimeDefault: lowMemory ? 15 : 25,
            bufferTimeAtTopQuality: lowMemory ? 20 : 30, bufferTimeAtTopQualityLongForm: lowMemory ? 20 : 30,
            bufferPruningInterval: 5 },
          // Prebuffer without autoplay so callers can wait for metadata before playing/seeking.
          scheduling: { scheduleWhilePaused: true },
          retryAttempts: { MPD: 2, MediaSegment: 2, InitializationSegment: 2, IndexSegment: 2, other: 2, lowLatencyMultiplyFactor: 1 }
        } });
        if (hasHeaders) {
          const interceptor: Parameters<typeof instance.addRequestInterceptor>[0] = async (request) => {
            if (active()) request.headers = { ...request.headers, ...headers };
            return request;
          };
          instance.addRequestInterceptor(interceptor);
          cleanup.push(() => instance.removeRequestInterceptor(interceptor));
        }
        let ready = false;
        let manualQuality: string | null = null;
        const audioId = (track: { id: string | null; index: number | null }, index: number) => String(track.id ?? track.index ?? index);
        const snapshot = () => {
          if (!active() || !ready) return;
          const audio = instance.getTracksFor("audio");
          const current = instance.getCurrentTrackFor("audio");
          publish({ audioTracks: audio.map((track, index) => ({ id: audioId(track, index), label: track.labels?.[0]?.text || track.lang || `Audio ${index + 1}`, language: track.lang || undefined })),
            qualities: instance.getRepresentationsByType("video").map((quality, index) => ({ id: quality.id, label: qualityLabel(quality.height, quality.bandwidth, index), width: quality.width, height: quality.height, bitrate: quality.bandwidth })),
            selectedAudioTrackId: current ? audioId(current, audio.indexOf(current)) : null,
            selectedQualityId: manualQuality });
        };
        controls = {
          selectAudioTrack: (id) => {
            if (!ready) return false;
            const selected = instance.getTracksFor("audio").find((track, index) => audioId(track, index) === id);
            if (!selected) return false;
            if (instance.getCurrentTrackFor("audio") !== selected) instance.setCurrentTrack(selected);
            snapshot();
            return true;
          },
          selectQuality: (id) => {
            if (!ready || (id !== null && !instance.getRepresentationsByType("video").some((quality) => quality.id === id))) return false;
            instance.updateSettings({ streaming: { abr: { autoSwitchBitrate: { video: id === null } } } });
            manualQuality = id;
            if (id !== null) instance.setRepresentationForTypeById("video", id);
            snapshot();
            return true;
          },
          goLive: () => {
            if (!ready || !instance.isDynamic()) return false;
            instance.seekToOriginalLive();
            return true;
          }
        };
        const events = dashjs.MediaPlayer.events;
        const on = (event: string, listener: (event: { error?: { code?: number; message?: string } | string }) => void) => {
          const guarded: typeof listener = (data) => { if (active()) listener(data); };
          instance.on(event, guarded);
          cleanup.push(() => instance.off(event, guarded));
        };
        on(events.ERROR, (event) => {
          if (!event?.error) return;
          const code = typeof event.error === "object" ? event.error.code : undefined;
          const errors = dashjs.MediaPlayer.errors;
          // Clock sync and optional text parsing can fail while audio/video remain playable.
          if (event.error === "cc" || (code !== undefined && [errors.TIME_SYNC_FAILED_ERROR_CODE, errors.TIMED_TEXT_ERROR_ID_PARSE_CODE].some((expected) => expected === code))) return;
          const network = event.error === "download" || (code !== undefined && [errors.MANIFEST_LOADER_LOADING_FAILURE_ERROR_CODE,
            errors.FRAGMENT_LOADER_LOADING_FAILURE_ERROR_CODE, errors.DOWNLOAD_ERROR_ID_MANIFEST_CODE,
            errors.DOWNLOAD_ERROR_ID_CONTENT_CODE, errors.DOWNLOAD_ERROR_ID_INITIALIZATION_CODE].some((expected) => expected === code));
          const unsupportedCodec = event.error === "capability" || event.error === "manifestError" || (code !== undefined && [
            errors.CAPABILITY_MEDIASOURCE_ERROR_CODE, errors.CAPABILITY_MEDIAKEYS_ERROR_CODE,
            errors.MANIFEST_LOADER_PARSING_FAILURE_ERROR_CODE, errors.MEDIASOURCE_TYPE_UNSUPPORTED_CODE,
            errors.MANIFEST_ERROR_ID_NOSTREAMS_CODE, errors.MANIFEST_ERROR_ID_MULTIPLEXED_CODE].some((expected) => expected === code));
          const media = event.error === "mediasource" || code === errors.APPEND_ERROR_CODE;
          fail(network ? "network" : unsupportedCodec ? "unsupported" : media ? "media" : "unknown", "DASH playback failed.", code);
        });
        on(events.PLAYBACK_ERROR, (event) => {
          const code = typeof event?.error === "object" ? event.error.code : undefined;
          if (!code) return;
          fail(code === 4 ? "unsupported" : code === 3 ? "media" : "network", "DASH media playback failed.", code, classifyMediaError(code) === "retryable");
        });
        const initialized = () => {
          ready = true;
          if (audioSelection !== null) controls?.selectAudioTrack(audioSelection);
          if (qualitySelection !== null && !controls?.selectQuality(qualitySelection)) controls?.selectQuality(null);
          snapshot();
        };
        on(events.STREAM_INITIALIZED, initialized);
        on(events.PERIOD_SWITCH_COMPLETED, initialized);
        for (const event of [events.TRACK_CHANGE_RENDERED, events.NEW_TRACK_SELECTED, events.QUALITY_CHANGE_RENDERED]) on(event, snapshot);
        instance.initialize(video, url, false);
      }).catch(importFailure);
      return;
    }

    if (!mse) { if (video.canPlayType("video/mp2t")) native(); else unsupported(); return; }
    void import("mpegts.js").then(({ default: mpegts }) => {
      if (!active()) return;
      if (!mpegts.isSupported()) { if (video.canPlayType("video/mp2t")) native(); else unsupported(); return; }
      prepareMse();
      const instance = mpegts.createPlayer({ type: "mpegts", isLive: live, url }, {
        // Keep a rolling live buffer even when paused; VOD must never chase the live edge.
        enableWorker: true, headers, liveBufferLatencyChasing: live, liveBufferLatencyChasingOnPaused: live,
        liveBufferLatencyMaxLatency: 5, liveBufferLatencyMinRemain: 1,
        enableStashBuffer: !live, stashInitialSize: 128 * 1024,
        lazyLoad: !live, lazyLoadMaxDuration: lowMemory ? 20 : 40, lazyLoadRecoverDuration: 5,
        autoCleanupSourceBuffer: true, autoCleanupMaxBackwardDuration: lowMemory ? 20 : 40,
        autoCleanupMinBackwardDuration: lowMemory ? 10 : 20
      });
      cleanup.push(() => instance.destroy());
      const onError = (type: string, detail: string) => {
        const kind = type === mpegts.ErrorTypes.NETWORK_ERROR ? "network" : type === mpegts.ErrorTypes.MEDIA_ERROR ? "media" : "unknown";
        fail(kind, "MPEG-TS playback failed.", detail);
      };
      instance.on(mpegts.Events.ERROR, onError);
      cleanup.push(() => instance.off(mpegts.Events.ERROR, onError));
      controls = { selectAudioTrack: () => false, selectQuality: () => false, goLive: () => seekToLiveEdge(video) };
      instance.attachMediaElement(video);
      if (active()) instance.load();
    }).catch(importFailure);
  }

  owners.set(video, handle);
  start();
  return handle;
}
