import { cachedDebridDirectUrl, parseDebridStream, resolveDebridDirectUrl, resolveTranscodeStream } from "./debrid";
import { playbackPlan, canProviderTranscode, canTryRemux, videoDecodableForDevice, recordBrowserPlaybackFailure } from "./streamCompatibility";
import { prepareHomeServerPlayback } from "./homeServerPlayback";
import type { AppSettings, StreamSource } from "./types";

export type PreparePlaybackOptions = { forceRemux?: boolean; forceTranscode?: boolean; signal?: AbortSignal };

export async function prepareBrowserStream(stream: StreamSource, settings: AppSettings, options: PreparePlaybackOptions = {}): Promise<StreamSource> {
  const check = () => { if (options.signal?.aborted) throw new DOMException("Playback cancelled", "AbortError"); };
  check();
  if (!stream.url) throw new Error("This source has no playback URL");
  if (stream.homeServer) {
    return prepareHomeServerPlayback(stream, settings, { forceTranscode: options.forceTranscode || options.forceRemux, signal: options.signal });
  }
  // The converted HLS URL is already the provider's browser output. Reopening
  // it must not reclassify the original filename (DV/TrueHD/MKV) or start another
  // conversion. Explicitly reselecting the original source permits a new try.
  if (stream.transcoded) {
    if (options.forceRemux || options.forceTranscode) throw new Error("Provider conversion was already attempted. Choose another source or use an external player.");
    return { ...stream, remux: false };
  }
  const plan = playbackPlan(stream);
  const debrid = parseDebridStream(stream.originalUrl ?? stream.url);
  if (options.forceTranscode || plan.method === "transcode") {
    if (!debrid || !canProviderTranscode(stream)) throw new Error("This source cannot be converted by its provider. Use an external player.");
    const result = await resolveTranscodeStream(debrid);
    check();
    if (!result.url) {
      const reason = result.error ?? "Server conversion is unavailable";
      if (/not supported|unsupported|permission|forbidden|subscription|premium|pro plan|not available|unavailable/i.test(reason)) {
        recordBrowserPlaybackFailure(stream, `Provider conversion is unavailable: ${reason}`, true);
      }
      throw new Error(reason);
    }
    return {
      ...stream, url: result.url, originalUrl: stream.originalUrl ?? stream.url,
      remux: false, transcoded: true, transport: "hls", media: undefined,
      // The original addon's headers do not belong to the provider's signed
      // HLS URL. They also force iPad playback off the native HLS path.
      behaviorHints: { ...stream.behaviorHints, notWebReady: false, proxyHeaders: undefined }
    };
  }
  // Remux can extract a verified HDR10 base, but cannot convert profile 5 colours.
  if (plan.route !== "here" && (!options.forceRemux || !videoDecodableForDevice(stream))) throw new Error(plan.detail || "This format requires an external player");
  const remux = !!options.forceRemux || plan.method === "remux"
    || (Object.keys(stream.behaviorHints?.proxyHeaders?.request ?? {}).length > 0 && canTryRemux(stream));
  const cached = cachedDebridDirectUrl(stream.originalUrl ?? stream.url);
  let url = cached ?? stream.url;
  if (debrid && remux && !cached) {
    const result = await resolveDebridDirectUrl(debrid);
    check();
    if (!result.url) throw new Error(result.error ?? "The provider could not resolve this source");
    url = result.url;
  }
  return { ...stream, url, originalUrl: stream.originalUrl ?? (url !== stream.url ? stream.url : undefined), remux };
}
