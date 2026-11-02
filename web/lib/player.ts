import { mediaSourceConstructor } from "./capabilities";
import { classifyMediaError } from "./playerRecovery";
import { mediaInfoFromMime } from "./liveTvPlayback";
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
  /** Capture before the failed engine destroys/reset its MediaSource. */
  positionSeconds?: number;
  /** The HTTP status the server answered, when the engine could see it. */
  httpStatus?: number;
  /** What the engine learned about the media before failing (codecs, tracks). */
  mediaInfo?: PlaybackMediaInfo;
  /** The playlist / segment URL the failing request was for, when known. */
  resourceUrl?: string;
};

/** Codec strings as the engines report them ("mp4a.40.2", "ac-3", "avc1.64001f"). */
export type PlaybackMediaInfo = { hasAudio?: boolean; hasVideo?: boolean; audioCodec?: string; videoCodec?: string };

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
  /** HLS only: "native" uses the browser's own player, "mse" uses hls.js. Default: the platform's preference. */
  hlsEngine?: "native" | "mse";
  /** The tracks the stream actually carries, once the engine knows them. */
  onMediaInfo?: (info: PlaybackMediaInfo) => void;
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

/** Apple browsers: their own HLS player is preferred over hls.js when both exist. */
export function preferNativeHls() {
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
    let mediaInfo: PlaybackMediaInfo | undefined;
    const active = () => !disposed && !failed && session === generation;
    const fail = (kind: PlaybackError["kind"], message: string, code?: string | number, retryable = kind === "network" || kind === "unknown", detail: Pick<PlaybackError, "httpStatus" | "mediaInfo" | "resourceUrl"> = {}) => {
      if (!active()) return;
      const positionSeconds = Number.isFinite(video.currentTime) ? video.currentTime : 0;
      failed = true;
      release();
      const info = detail.mediaInfo || mediaInfo ? { ...mediaInfo, ...detail.mediaInfo } : undefined;
      // Return the handle before notifying initial failures, and cancel queued notifications too.
      void Promise.resolve().then(() => {
        if (!disposed && session === generation) options.onError?.({ transport, kind, fatal: true, retryable, message, code, positionSeconds,
          ...(detail.httpStatus ? { httpStatus: detail.httpStatus } : {}), ...(info ? { mediaInfo: info } : {}),
          ...(detail.resourceUrl ? { resourceUrl: detail.resourceUrl } : {}) });
      });
    };
    const learn = (info: PlaybackMediaInfo) => {
      if (!active()) return;
      mediaInfo = { ...mediaInfo, ...info };
      options.onMediaInfo?.(mediaInfo);
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
      const engine = options.hlsEngine;
      if (nativeHls && !hasHeaders && engine !== "mse" && (engine === "native" || !mse || preferNativeHls())) { native(); return; }
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
          if (!active()) return;
          // Report every failing request (hls.js retries segments quietly, so a
          // startup timeout may be the only fatal outcome): the player then
          // knows WHICH server refused, e.g. an http:// IP the page blocks.
          const failedUrl = (data as { frag?: { url?: string }; url?: string; context?: { url?: string } }).frag?.url
            ?? (data as { url?: string }).url ?? (data as { context?: { url?: string } }).context?.url;
          if (failedUrl && data.type === Hls.ErrorTypes.NETWORK_ERROR) {
            try { video.dispatchEvent(new CustomEvent("arvio-resource-error", { detail: { url: failedUrl, status: data.response?.code } })); } catch { /* no CustomEvent */ }
          }
          if (!data.fatal) return;
          const network = data.type === Hls.ErrorTypes.NETWORK_ERROR;
          const media = data.type === Hls.ErrorTypes.MEDIA_ERROR;
          const status = data.response?.code;
          const httpStatus = typeof status === "number" && status >= 400 ? status : undefined;
          const resourceUrl = (data as { frag?: { url?: string }; url?: string; context?: { url?: string } }).frag?.url
            ?? (data as { url?: string }).url ?? (data as { context?: { url?: string } }).context?.url;
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
              } catch { fail("network", "HLS network recovery failed.", data.details, true, { httpStatus, resourceUrl }); }
            }, 500 * networkAttempts);
            return;
          }
          if (media && mediaAttempts < HLS_MEDIA_RECOVERIES) {
            mediaAttempts += 1;
            try { instance.recoverMediaError(); } catch { fail("media", "HLS media recovery failed.", data.details); }
            return;
          }
          // A SourceBuffer the browser refused names the codec it could not take.
          const mime = (data as { mimeType?: string }).mimeType ?? (data.error instanceof Error ? data.error.message : undefined);
          fail(network ? "network" : media ? "media" : "unknown", "HLS playback recovery exhausted.", data.details, network ? retryable : !media,
            { httpStatus, resourceUrl, mediaInfo: media ? mediaInfoFromMime(mime) : undefined });
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
        instance.on(Hls.Events.BUFFER_CODECS, (_event, data) => {
          const tracks = data as { audio?: { codec?: string }; video?: { codec?: string }; audiovideo?: { codec?: string } };
          learn({ hasAudio: !!(tracks.audio || tracks.audiovideo), hasVideo: !!(tracks.video || tracks.audiovideo),
            audioCodec: tracks.audio?.codec, videoCodec: tracks.video?.codec });
        });
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
      const onError = (type: string, detail: string, info?: { code?: number; msg?: string }) => {
        const network = type === mpegts.ErrorTypes.NETWORK_ERROR;
        // Not MPEG-TS at all (an HLS playlist or an HTML error page): another engine may play it.
        const details: Partial<typeof mpegts.ErrorDetails> = mpegts.ErrorDetails ?? {};
        const notTs = detail === details.MEDIA_FORMAT_UNSUPPORTED || detail === details.MEDIA_FORMAT_ERROR;
        const kind = network ? "network" : notTs ? "unsupported" : type === mpegts.ErrorTypes.MEDIA_ERROR ? "media" : "unknown";
        const httpStatus = network && detail === details.NETWORK_STATUS_CODE_INVALID && typeof info?.code === "number" && info.code >= 400 ? info.code : undefined;
        fail(kind, "MPEG-TS playback failed.", detail, kind === "network" || kind === "unknown", { httpStatus, mediaInfo: kind === "media" ? mediaInfoFromMime(info?.msg) : undefined });
      };
      instance.on(mpegts.Events.ERROR, onError);
      cleanup.push(() => instance.off(mpegts.Events.ERROR, onError));
      const onMediaInfo = (info: { hasAudio?: boolean; hasVideo?: boolean; audioCodec?: string; videoCodec?: string }) => {
        learn({ hasAudio: info.hasAudio, hasVideo: info.hasVideo, audioCodec: info.audioCodec || undefined, videoCodec: info.videoCodec || undefined });
      };
      instance.on(mpegts.Events.MEDIA_INFO, onMediaInfo);
      cleanup.push(() => instance.off(mpegts.Events.MEDIA_INFO, onMediaInfo));
      controls = { selectAudioTrack: () => false, selectQuality: () => false, goLive: () => seekToLiveEdge(video) };
      instance.attachMediaElement(video);
      if (active()) instance.load();
    }).catch(importFailure);
  }

  owners.set(video, handle);
  start();
  return handle;
}
