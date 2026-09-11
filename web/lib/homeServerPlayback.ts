import { getMediaCapabilities, type BrowserMediaCapabilities } from "./capabilities";
import { jsonRequest, proxiedUrl, textRequest } from "./http";
import {
  ensureHomeServerSession, jellyfinSourceMedia, plexSourceMedia,
  type JellyfinMediaSource, type PlexMetadata
} from "./homeserver";
import type { AppSettings, HomeServerConfig, StreamSource } from "./types";

export type HomeServerPlaybackConfig = Pick<AppSettings, "homeServers"> | HomeServerConfig | HomeServerConfig[];
export interface HomeServerPlaybackOptions {
  forceTranscode?: boolean;
  signal?: AbortSignal;
  /** Absolute title position in seconds. File playback still needs a client-side seek. */
  startTime?: number;
}
export interface HomeServerPlaybackPosition {
  /** Absolute title position, not the time relative to a transcoded playlist. */
  positionSeconds?: number;
  durationSeconds?: number;
  paused?: boolean;
}
export interface HomeServerPlaybackReportOptions extends HomeServerPlaybackPosition {
  signal?: AbortSignal;
}

type PlayMethod = "DirectPlay" | "DirectStream" | "Transcode";
type Session = NonNullable<StreamSource["playbackSession"]>;
interface SessionState {
  server: HomeServerConfig;
  token: string;
  deviceId: string;
  playMethod: PlayMethod;
  latest: HomeServerPlaybackPosition;
  started: boolean;
  stopped: boolean;
  tail: Promise<void>;
}
const sessions = new Map<string, SessionState>();
const MAX_BITRATE = 120_000_000;
const seconds = (value?: number) => Number.isFinite(value) ? Math.max(0, value!) : 0;
const ticks = (value?: number) => Math.min(Number.MAX_SAFE_INTEGER, Math.round(seconds(value) * 10_000_000));
const sessionKey = (session: Session) => JSON.stringify([session.serverId, session.sessionId]);

function findServer(id: string, config: HomeServerPlaybackConfig): HomeServerConfig {
  const servers = Array.isArray(config) ? config : "homeServers" in config ? config.homeServers : [config];
  const server = servers.find((candidate) => candidate.id === id || candidate.serverId === id);
  if (!server?.enabled || !server.url) throw new Error("The selected home server is unavailable or disabled.");
  return server;
}

function endpoint(server: HomeServerConfig, path: string): URL {
  const base = new URL(server.url.replace(/\/+$/, "") + "/");
  if (!["http:", "https:"].includes(base.protocol) || base.username || base.password || base.search || base.hash) {
    throw new Error("The home server must have an HTTP(S) URL without embedded credentials or a query.");
  }
  return new URL(path.replace(/^\/+/, ""), base);
}

// Provider URLs are untrusted. Keep credentials and media on the configured server,
// including its reverse-proxy base path; never route manifests or segments via Netlify.
function mediaUrl(server: HomeServerConfig, raw: string, token: string): URL {
  const base = endpoint(server, "");
  let url: URL;
  if (/^https?:\/\//i.test(raw) || raw.startsWith("//")) url = new URL(raw, base);
  else if (raw.startsWith(base.pathname)) url = new URL(raw, base.origin);
  else url = new URL(raw.replace(/^\/+/, ""), base);
  if (url.origin !== base.origin || url.username || url.password || !url.pathname.startsWith(base.pathname)
    || /\/api\/proxy(?:\/|$)/i.test(url.pathname)) {
    throw new Error("The home server returned an unsafe playback URL.");
  }
  setParam(url, server.type === "plex" ? "X-Plex-Token" : "api_key", token);
  return url;
}

function setParam(url: URL, name: string, value: string): void {
  // Provider query names are case-insensitive, URLSearchParams names are not.
  for (const key of [...url.searchParams.keys()]) {
    if (key.toLowerCase() === name.toLowerCase()) url.searchParams.delete(key);
  }
  url.searchParams.set(name, value);
}

function param(url: URL, name: string): string | undefined {
  return [...url.searchParams].find(([key]) => key.toLowerCase() === name.toLowerCase())?.[1];
}

function headers(server: HomeServerConfig, token: string, deviceId: string): Record<string, string> {
  if (server.type === "plex") return { Accept: "application/json", "X-Plex-Token": token, "X-Plex-Client-Identifier": deviceId };
  return {
    "X-Emby-Token": token,
    "X-Emby-Authorization": `MediaBrowser Client="ARVIO Web", Device="Browser", DeviceId="${deviceId}", Version="1.0.0"`
  };
}

function directVideoCodecs(caps: BrowserMediaCapabilities): string[] {
  return [caps.h264 && "h264", caps.hevc && "hevc", caps.av1 && "av1"].filter((codec): codec is string => !!codec);
}
function directAudioCodecs(caps: BrowserMediaCapabilities): string[] {
  return [caps.aac && "aac", caps.ac3 && "ac3", caps.eac3 && "eac3"].filter((codec): codec is string => !!codec);
}
function canTranscode(caps: BrowserMediaCapabilities): boolean {
  return (caps.nativeHls || caps.mse) && caps.h264 && caps.aac;
}

export function buildHomeServerDeviceProfile(caps: BrowserMediaCapabilities = getMediaCapabilities(), provider: "jellyfin" | "emby" = "jellyfin") {
  const video = directVideoCodecs(caps);
  const audio = directAudioCodecs(caps);
  return {
    Name: "ARVIO Browser", MaxStreamingBitrate: MAX_BITRATE,
    // Do not advertise MKV, lossless surround, or software decoders as native browser support.
    DirectPlayProfiles: video.length && audio.length
      ? [{ Type: "Video", Container: "mp4,m4v", VideoCodec: video.join(","), AudioCodec: audio.join(",") }] : [],
    TranscodingProfiles: canTranscode(caps) ? [{
      Type: "Video", Context: "Streaming", Protocol: "hls", Container: "ts",
      VideoCodec: "h264", AudioCodec: "aac", MaxAudioChannels: "2", MinSegments: 1,
      BreakOnNonKeyFrames: false, CopyTimestamps: false
    }] : [],
    CodecProfiles: video.map((codec) => ({
      Type: "Video", Codec: codec, Conditions: [
        { Condition: "LessThanEqual", Property: "VideoBitDepth", Value: codec === "hevc" && caps.hevc10 ? "10" : "8", IsRequired: false },
        { Condition: "Equals", Property: "IsInterlaced", Value: "false", IsRequired: false },
        // Decoder support alone does not establish correct HDR output / tone mapping.
        { Condition: "Equals", Property: provider === "emby" ? "VideoRange" : "VideoRangeType", Value: "SDR", IsRequired: false },
        ...(codec === "h264" ? [
          { Condition: "EqualsAny", Property: "VideoProfile", Value: "high|main|baseline|constrained baseline", IsRequired: false },
          { Condition: "LessThanEqual", Property: "VideoLevel", Value: "40", IsRequired: false }
        ] : [])
      ]
    })),
    SubtitleProfiles: [{ Format: "vtt", Method: "External" }]
  };
}

function sdr(media: StreamSource["media"]): boolean {
  return !media?.hdr || /^(sdr|unknown)$/i.test(media.hdr);
}
function directCompatible(media: StreamSource["media"], caps: BrowserMediaCapabilities): boolean {
  return !!media && ["mp4", "m4v"].includes(media.container ?? "") && sdr(media)
    && directVideoCodecs(caps).includes(media.videoCodec ?? "")
    && directAudioCodecs(caps).includes(media.audioCodec ?? "");
}
function jellyfinVideoCompatible(source: JellyfinMediaSource, caps: BrowserMediaCapabilities): boolean {
  const video = source.MediaStreams?.find((stream) => stream.Type === "Video");
  if (!video || video.IsInterlaced) return false;
  const depth = video.BitDepth ?? (/10/.test(video.Profile ?? "") ? 10 : 8);
  return depth <= (video.Codec === "hevc" && caps.hevc10 ? 10 : 8)
    && !(video.Codec === "h264" && ((video.Level ?? 0) > 40 || /high\s*(10|422|444)/i.test(video.Profile ?? "")));
}

function remember(stream: StreamSource, state: Pick<SessionState, "server" | "token" | "deviceId" | "playMethod">): StreamSource {
  sessions.set(sessionKey(stream.playbackSession!), {
    ...state, server: { ...state.server }, started: false, stopped: false, tail: Promise.resolve(),
    latest: { positionSeconds: seconds(stream.playbackSession?.startOffset), paused: true }
  });
  return stream;
}

/** Negotiate the exact selected version. Never silently choose another version or return an unverified URL on failure. */
export async function prepareHomeServerPlayback(
  stream: StreamSource,
  config: HomeServerPlaybackConfig,
  options: HomeServerPlaybackOptions = {}
): Promise<StreamSource> {
  options.signal?.throwIfAborted();
  if (!stream.homeServer) return stream;
  const server = findServer(stream.homeServer.serverId, config);
  endpoint(server, "");
  const caps = getMediaCapabilities();
  if (options.forceTranscode && !canTranscode(caps)) throw new Error("This browser cannot play the server's H.264/AAC HLS output.");
  return server.type === "plex" ? preparePlex(stream, server, caps, options) : prepareJellyfin(stream, server, caps, options);
}

async function prepareJellyfin(stream: StreamSource, server: HomeServerConfig, caps: BrowserMediaCapabilities, options: HomeServerPlaybackOptions): Promise<StreamSource> {
  const context = stream.homeServer!;
  if (!context.itemId || !context.mediaSourceId) throw new Error("The selected version has no media-source identifier. Refresh the sources.");
  const auth = await ensureHomeServerSession(server, options.signal);
  options.signal?.throwIfAborted();
  if (!auth) throw new Error("Sign in to the home server before starting playback.");
  const deviceId = `arvio-web-${globalThis.crypto.randomUUID()}`;
  const url = endpoint(server, `Items/${encodeURIComponent(context.itemId)}/PlaybackInfo`);
  url.searchParams.set("api_key", auth.token);
  url.searchParams.set("UserId", auth.userId);
  const body = {
    UserId: auth.userId, MediaSourceId: context.mediaSourceId, DeviceProfile: buildHomeServerDeviceProfile(caps, server.type === "emby" ? "emby" : "jellyfin"),
    StartTimeTicks: ticks(options.startTime), MaxStreamingBitrate: MAX_BITRATE, MaxAudioChannels: 2,
    IsPlayback: true, AutoOpenLiveStream: false, EnableDirectPlay: !options.forceTranscode,
    EnableDirectStream: !options.forceTranscode, EnableTranscoding: canTranscode(caps),
    AllowVideoStreamCopy: !options.forceTranscode, AllowAudioStreamCopy: !options.forceTranscode,
    SubtitleStreamIndex: -1
  };
  const info = await jsonRequest<{ MediaSources?: JellyfinMediaSource[]; PlaySessionId?: string; ErrorCode?: string }>(
    proxiedUrl(url.toString(), headers(server, auth.token, deviceId)),
    { method: "POST", body: JSON.stringify(body), signal: options.signal }
  );
  const session: Session = { serverId: server.id, itemId: context.itemId, sessionId: info.PlaySessionId ?? "", mediaSourceId: context.mediaSourceId, transcoding: false, startOffset: 0 };
  try {
    options.signal?.throwIfAborted();
    if (info.ErrorCode) throw new Error(`The home server refused playback (${info.ErrorCode}).`);
    if (!session.sessionId) throw new Error("The home server did not return a playback session.");
    const source = info.MediaSources?.find((candidate) => candidate.Id === context.mediaSourceId);
    if (!source) throw new Error("The selected media version is no longer available. Refresh the sources.");
    if (source.RequiresOpening) throw new Error("Opening live home-server streams is not supported by this player.");
    const media = jellyfinSourceMedia(source);
    const direct = !options.forceTranscode && directCompatible(media, caps) && jellyfinVideoCompatible(source, caps);
    let playMethod: PlayMethod;
    let playbackUrl: URL;
    let outputMedia = media;
    let transport: StreamSource["transport"] = "file";
    if (direct && source.SupportsDirectPlay) {
      playMethod = "DirectPlay";
      playbackUrl = mediaUrl(server, `Videos/${encodeURIComponent(context.itemId)}/stream.${media.container}`, auth.token);
      playbackUrl.searchParams.set("Static", "true");
      if (source.ETag) playbackUrl.searchParams.set("Tag", source.ETag);
    } else if (direct && source.SupportsDirectStream) {
      playMethod = "DirectStream";
      playbackUrl = mediaUrl(server, source.DirectStreamUrl || `Videos/${encodeURIComponent(context.itemId)}/stream.${media.container}`, auth.token);
      if (!source.DirectStreamUrl) playbackUrl.searchParams.set("Static", "true");
      if (/\.m3u8$/i.test(playbackUrl.pathname)) {
        if (media.videoCodec !== "h264" || media.audioCodec !== "aac") throw new Error("The home server did not offer compatible direct-stream HLS codecs.");
        transport = "hls";
      }
    } else if (canTranscode(caps) && source.TranscodingUrl && (source.SupportsTranscoding || (!options.forceTranscode && source.SupportsDirectStream))) {
      playbackUrl = mediaUrl(server, source.TranscodingUrl, auth.token);
      if (!/\.m3u8$/i.test(playbackUrl.pathname) || (source.TranscodingSubProtocol && source.TranscodingSubProtocol.toLowerCase() !== "hls")) {
        throw new Error("The home server did not offer a compatible HLS stream.");
      }
      transport = "hls";
      const videoCodec = param(playbackUrl, "VideoCodec")?.toLowerCase();
      const audioCodec = param(playbackUrl, "AudioCodec")?.toLowerCase();
      if ((videoCodec && !["h264", "copy"].includes(videoCodec)) || (audioCodec && !["aac", "copy"].includes(audioCodec))) {
        throw new Error("The home server offered codecs outside the requested browser profile.");
      }
      if ((videoCodec === "copy" && (media.videoCodec !== "h264" || !sdr(media) || !jellyfinVideoCompatible(source, caps)))
        || (audioCodec === "copy" && media.audioCodec !== "aac")) {
        throw new Error("The home server cannot copy this source into browser-compatible HLS.");
      }
      if (options.forceTranscode) {
        setParam(playbackUrl, "AllowVideoStreamCopy", "false");
        setParam(playbackUrl, "AllowAudioStreamCopy", "false");
        setParam(playbackUrl, "VideoCodec", "h264");
        setParam(playbackUrl, "AudioCodec", "aac");
      }
      playMethod = videoCodec === "copy" && audioCodec === "copy" && !options.forceTranscode ? "DirectStream" : "Transcode";
      outputMedia = { container: source.TranscodingContainer ?? "ts", videoCodec: "h264", audioCodec: "aac", hdr: "SDR" };
    } else {
      throw new Error("The home server cannot provide browser-compatible playback for this version. Check transcoding permissions or use an external player.");
    }
    const itemPath = endpoint(server, `Videos/${encodeURIComponent(context.itemId)}/`).pathname.toLowerCase();
    if (!playbackUrl.pathname.toLowerCase().startsWith(itemPath)) throw new Error("The home server returned playback for a different item.");
    if (transport === "hls" && !canTranscode(caps)) throw new Error("This browser cannot play the offered HLS stream.");
    setParam(playbackUrl, "MediaSourceId", context.mediaSourceId);
    setParam(playbackUrl, "PlaySessionId", session.sessionId);
    setParam(playbackUrl, "DeviceId", deviceId);
    if (transport === "hls") setParam(playbackUrl, "StartTimeTicks", String(ticks(options.startTime)));
    session.transcoding = transport === "hls" || playMethod === "Transcode";
    session.startOffset = transport === "hls" ? seconds(options.startTime) : 0;
    const prepared = {
      ...stream, url: playbackUrl.toString(), originalUrl: stream.originalUrl || stream.url,
      transport, media: outputMedia, remux: false, transcoded: session.transcoding, playbackSession: session,
      behaviorHints: { ...stream.behaviorHints, notWebReady: false, browserPlayable: true }
    };
    return remember(prepared, { server, token: auth.token, deviceId, playMethod });
  } catch (error) {
    // PlaybackInfo must not open live streams. A late response can nevertheless allocate
    // a play session: release it even if its caller has already cancelled.
    if (session.sessionId) await sendReport(session, { server, token: auth.token, deviceId, playMethod: "Transcode" }, "stop", {}).catch(() => {});
    throw error;
  }
}

interface PlexDecision {
  MediaContainer?: {
    generalDecisionCode?: number; generalDecisionText?: string;
    mdeDecisionCode?: number;
    directPlayDecisionCode?: number; transcodeDecisionCode?: number;
    Metadata?: PlexMetadata[];
  };
}

async function preparePlex(stream: StreamSource, server: HomeServerConfig, caps: BrowserMediaCapabilities, options: HomeServerPlaybackOptions): Promise<StreamSource> {
  const context = stream.homeServer!;
  const { mediaIndex, partIndex } = context;
  if (!context.itemId || !Number.isInteger(mediaIndex) || mediaIndex! < 0 || !Number.isInteger(partIndex) || partIndex! < 0) {
    throw new Error("The selected Plex version has no media/part index. Refresh the sources.");
  }
  if (!server.token) throw new Error("Sign in to Plex before starting playback.");
  const token = server.token;
  const sessionId = `arvio-web-${globalThis.crypto.randomUUID()}`;
  const requestHeaders = headers(server, token, sessionId);
  const metadataUrl = endpoint(server, `library/metadata/${encodeURIComponent(context.itemId)}`);
  metadataUrl.search = new URLSearchParams({ includeMedia: "1", "X-Plex-Token": token }).toString();
  const metadata = await jsonRequest<{ MediaContainer?: { Metadata?: PlexMetadata[] } }>(
    proxiedUrl(metadataUrl.toString(), requestHeaders), { signal: options.signal }
  );
  options.signal?.throwIfAborted();
  const item = metadata.MediaContainer?.Metadata?.find((candidate) => candidate.ratingKey === context.itemId);
  const media = item?.Media?.[mediaIndex!];
  const part = media?.Part?.[partIndex!];
  if (!media || !part?.key || (context.mediaSourceId && String(media.id) !== context.mediaSourceId)) {
    throw new Error("The selected Plex media version is no longer available. Refresh the sources.");
  }
  const original = mediaUrl(server, stream.originalUrl || stream.url || part.key, token);
  const directUrl = mediaUrl(server, part.key, token);
  if (original.pathname !== directUrl.pathname) throw new Error("The selected Plex part changed. Refresh the sources.");
  const sourceMedia = plexSourceMedia(media, part);
  const video = part.Stream?.find((candidate) => candidate.streamType === 1);
  const direct = !options.forceTranscode && directCompatible(sourceMedia, caps)
    && (video?.bitDepth ?? 8) <= (sourceMedia.videoCodec === "hevc" && caps.hevc10 ? 10 : 8)
    && !/high\s*(10|422|444)/i.test(video?.profile ?? "");
  // The universal HLS target is deliberately H.264/AAC. Only copy those codecs;
  // a browser's MP4 HEVC/AV1 support does not imply support in MPEG-TS HLS.
  const copyVideo = !options.forceTranscode && sourceMedia.videoCodec === "h264" && caps.h264 && sdr(sourceMedia)
    && (video?.bitDepth ?? 8) <= 8 && !/high\s*(10|422|444)/i.test(video?.profile ?? "");
  const copyAudio = !options.forceTranscode && sourceMedia.audioCodec === "aac" && caps.aac;
  if (!direct && !canTranscode(caps)) throw new Error("This browser cannot play the selected Plex version or HLS output.");
  const params = new URLSearchParams({
    path: `/library/metadata/${context.itemId}`, mediaIndex: String(mediaIndex), partIndex: String(partIndex),
    protocol: "hls", hasMDE: "1", directPlay: direct ? "1" : "0", directStream: copyVideo ? "1" : "0",
    directStreamAudio: copyAudio ? "1" : "0", offset: String(seconds(options.startTime)),
    videoQuality: "100", maxVideoBitrate: "20000", audioBoost: "100", subtitles: "none",
    session: sessionId, "X-Plex-Session-Identifier": sessionId, "X-Plex-Client-Identifier": sessionId,
    "X-Plex-Product": "ARVIO Web", "X-Plex-Version": "1.0.0", "X-Plex-Platform": "Chrome",
    "X-Plex-Client-Profile-Name": "Chrome", "X-Plex-Token": token,
    "X-Plex-Client-Profile-Extra": "add-transcode-target(type=videoProfile&context=streaming&protocol=hls&container=mpegts&videoCodec=h264&audioCodec=aac)"
      + "+add-limitation(scope=videoAudioCodec&scopeName=aac&type=upperBound&name=audio.channels&value=2&onlyTranscodes=true&replace=true)"
  });
  const decisionUrl = endpoint(server, "video/:/transcode/universal/decision");
  decisionUrl.search = params.toString();
  const decision = (await jsonRequest<PlexDecision>(proxiedUrl(decisionUrl.toString(), requestHeaders), { signal: options.signal })).MediaContainer;
  options.signal?.throwIfAborted();
  if (!decision || (decision.generalDecisionCode != null && decision.generalDecisionCode >= 2000)
    || (decision.mdeDecisionCode != null && decision.mdeDecisionCode >= 2000)) {
    throw new Error("Plex refused playback. Check server availability and transcoding permissions.");
  }
  const decisionItem = decision.Metadata?.find((candidate) => candidate.ratingKey === context.itemId);
  const decisionMedia = decisionItem?.Media?.find((candidate) => media.id != null && String(candidate.id) === String(media.id))
    ?? (decisionItem?.Media?.length === 1 && media.id == null ? decisionItem.Media[0] : undefined);
  const decisionPart = decisionMedia?.Part?.find((candidate) => part.id != null && String(candidate.id) === String(part.id))
    ?? decisionMedia?.Part?.find((candidate) => candidate.key === part.key);
  if (decision.Metadata?.length && (!decisionItem || !decisionMedia || !decisionPart)) {
    throw new Error("Plex returned a decision for a different media version or part.");
  }
  const directDecision = decisionPart?.decision === "directplay" || decisionPart?.decision === "direct play"
    || (!decisionPart?.decision && (decision.directPlayDecisionCode === 1000 || decision.mdeDecisionCode === 1000));
  if (directDecision && !direct) throw new Error("Plex ignored the requested browser-compatible conversion.");
  let playbackUrl = directUrl;
  const isDirect = direct && directDecision;
  if (!isDirect) {
    if (!canTranscode(caps) || (decision.transcodeDecisionCode != null && decision.transcodeDecisionCode >= 2000)
      || (!["transcode", "copy"].includes(decisionPart?.decision ?? "") && decision.transcodeDecisionCode !== 1001 && decision.mdeDecisionCode !== 1001)) {
      throw new Error("Plex did not offer compatible conversion for the selected version.");
    }
    const outputStreams = decisionPart?.Stream ?? [];
    if (outputStreams.some((candidate) => candidate.codec
      && ((candidate.streamType === 1 && candidate.codec !== "h264") || (candidate.streamType === 2 && candidate.codec !== "aac")))) {
      throw new Error("Plex offered codecs outside the requested browser HLS profile.");
    }
    playbackUrl = endpoint(server, "video/:/transcode/universal/start.m3u8");
    // Keep the decision request and start request identical, including selected version.
    playbackUrl.search = params.toString();
  }
  return remember({
    ...stream, url: playbackUrl.toString(), originalUrl: stream.originalUrl || directUrl.toString(),
    media: isDirect ? sourceMedia : { container: "mpegts", videoCodec: "h264", audioCodec: "aac", hdr: "SDR" },
    transport: isDirect ? "file" : "hls", remux: false, transcoded: !isDirect,
    behaviorHints: { ...stream.behaviorHints, notWebReady: false, browserPlayable: true },
    playbackSession: { serverId: server.id, itemId: context.itemId, mediaSourceId: context.mediaSourceId,
      sessionId, transcoding: !isDirect, startOffset: isDirect ? 0 : seconds(options.startTime) }
  }, { server, token, deviceId: sessionId, playMethod: isDirect ? "DirectPlay" : copyVideo && copyAudio ? "DirectStream" : "Transcode" });
}

async function sendReport(session: Session, state: Pick<SessionState, "server" | "token" | "deviceId" | "playMethod">,
  event: "start" | "progress" | "stop", options: HomeServerPlaybackReportOptions): Promise<void> {
  options.signal?.throwIfAborted();
  const { server, token, deviceId } = state;
  const requestHeaders = headers(server, token, deviceId);
  if (server.type === "plex") {
    const url = endpoint(server, ":/timeline");
    url.search = new URLSearchParams({
      ratingKey: session.itemId, key: `/library/metadata/${session.itemId}`,
      state: event === "stop" ? "stopped" : options.paused ? "paused" : "playing",
      time: String(Math.round(seconds(options.positionSeconds) * 1000)),
      duration: String(Math.round(seconds(options.durationSeconds) * 1000)),
      "X-Plex-Session-Identifier": session.sessionId, "X-Plex-Client-Identifier": deviceId,
      "X-Plex-Token": token
    }).toString();
    try {
      await textRequest(proxiedUrl(url.toString(), requestHeaders), { signal: options.signal });
    } finally {
      if (event === "stop" && session.transcoding) {
        const stopUrl = endpoint(server, "video/:/transcode/universal/stop");
        stopUrl.search = new URLSearchParams({ session: session.sessionId, "X-Plex-Token": token }).toString();
        await textRequest(proxiedUrl(stopUrl.toString(), requestHeaders), { signal: options.signal });
      }
    }
  } else {
    const url = endpoint(server, event === "start" ? "Sessions/Playing" : event === "progress" ? "Sessions/Playing/Progress" : "Sessions/Playing/Stopped");
    url.searchParams.set("api_key", token);
    await textRequest(proxiedUrl(url.toString(), requestHeaders), {
      method: "POST", signal: options.signal, body: JSON.stringify({
        ItemId: session.itemId, MediaSourceId: session.mediaSourceId, PlaySessionId: session.sessionId,
        PositionTicks: ticks(options.positionSeconds), IsPaused: !!options.paused, CanSeek: true,
        PlayMethod: state.playMethod, EventName: event === "progress" ? "timeupdate" : undefined
      })
    });
  }
}

/** Cache a prepared session's absolute playhead without performing network I/O.
 * Unknown/stopped sessions are ignored; invalid media-element values cannot erase a valid snapshot.
 */
export function updateHomeServerPlaybackPosition(stream: StreamSource, position: HomeServerPlaybackPosition): void {
  const state = stream.playbackSession && sessions.get(sessionKey(stream.playbackSession));
  if (!state || state.stopped) return;
  if (Number.isFinite(position.positionSeconds)) state.latest.positionSeconds = seconds(position.positionSeconds);
  if (Number.isFinite(position.durationSeconds) && position.durationSeconds! > 0) state.latest.durationSeconds = position.durationSeconds;
  if (typeof position.paused === "boolean") state.latest.paused = position.paused;
}

/** Reports are serialized, start is deduplicated only after acknowledgement, and
 * omitted position fields use the latest local snapshot. Stop permanently closes this session.
 */
export async function reportHomeServerPlayback(stream: StreamSource, config: HomeServerPlaybackConfig,
  event: "start" | "progress" | "stop", options: HomeServerPlaybackReportOptions = {}): Promise<void> {
  options.signal?.throwIfAborted();
  const session = stream.playbackSession;
  if (!session) return;
  const key = sessionKey(session);
  let state = sessions.get(key);
  if (!state) {
    const server = findServer(session.serverId, config);
    const auth = server.type === "plex" ? { token: server.token } : await ensureHomeServerSession(server, options.signal);
    if (!auth?.token) throw new Error("The home-server playback session is no longer authenticated.");
    // Another report can establish this session while authentication is pending.
    state = sessions.get(key);
    if (!state) {
      state = { server: { ...server }, token: auth.token, deviceId: session.sessionId,
        playMethod: session.transcoding ? "Transcode" : "DirectPlay", started: false, stopped: false, tail: Promise.resolve(),
        latest: { positionSeconds: seconds(session.startOffset), paused: true } };
      sessions.set(key, state);
    }
  }
  if (state.stopped) return event === "stop" ? state.tail : undefined;
  updateHomeServerPlaybackPosition(stream, options);
  const snapshot = { ...state.latest, signal: options.signal };
  if (event === "stop") state.stopped = true;
  const current = state;
  const pending = current.tail.catch(() => {}).then(async () => {
    options.signal?.throwIfAborted();
    if (event === "start" && current.started) return;
    await sendReport(session, current, event, snapshot);
    if (event === "start") current.started = true;
  });
  current.tail = pending;
  try { await pending; }
  catch (error) { if (event === "stop") current.stopped = false; throw error; }
  // Retain a bounded tombstone to make late progress and duplicate stop calls harmless.
  if (event === "stop" && sessions.size > 100) {
    for (const [id, entry] of sessions) {
      if (entry.stopped && entry !== current) sessions.delete(id);
      if (sessions.size <= 100) break;
    }
  }
}
