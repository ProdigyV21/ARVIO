// Runtime media capability detection. Replaces pessimistic release-name
// sniffing: whether HEVC/AV1/E-AC-3 actually plays depends on this device's
// hardware and browser, so ask the browser instead of guessing from text.

export type BrowserMediaCapabilities = {
  mse: boolean;
  nativeHls: boolean;
  h264: boolean;
  hevc: boolean;
  hevc10: boolean;
  /**
   * True only when the browser advertises a Dolby Vision decoder.
   *
   * HEVC support alone does not prove correct Dolby Vision decoding. Unsupported
   * profiles can produce a black picture or incorrect colours with working audio.
   */
  dolbyVision: boolean;
  av1: boolean;
  vp9: boolean;
  aac: boolean;
  ac3: boolean;
  eac3: boolean;
  opus: boolean;
  flac: boolean;
};

const NO_CAPABILITIES: BrowserMediaCapabilities = {
  mse: false,
  nativeHls: false,
  h264: true,
  hevc: false,
  hevc10: false,
  dolbyVision: false,
  av1: false,
  vp9: false,
  aac: true,
  ac3: false,
  eac3: false,
  opus: false,
  flac: false
};

let cached: BrowserMediaCapabilities | null = null;

function mseSupports(type: string) {
  try {
    return mediaSourceConstructor()?.isTypeSupported(type) ?? false;
  } catch {
    return false;
  }
}

function videoCanPlay(video: HTMLVideoElement, type: string) {
  try {
    return video.canPlayType(type) !== "";
  } catch {
    return false;
  }
}

export function mediaSourceConstructor(): typeof MediaSource | undefined {
  if (typeof window === "undefined") return undefined;
  return (window as Window & { ManagedMediaSource?: typeof MediaSource }).ManagedMediaSource ?? window.MediaSource;
}

export function getMediaCapabilities(): BrowserMediaCapabilities {
  if (cached) return cached;
  if (typeof window === "undefined" || typeof document === "undefined") return NO_CAPABILITIES;
  const video = document.createElement("video");
  const supports = (type: string) => mseSupports(type) || videoCanPlay(video, type);
  cached = {
    mse: !!mediaSourceConstructor(),
    nativeHls: videoCanPlay(video, "application/vnd.apple.mpegurl"),
    h264: supports('video/mp4; codecs="avc1.640028"'),
    hevc: supports('video/mp4; codecs="hvc1.1.6.L120.90"') || supports('video/mp4; codecs="hev1.1.6.L120.90"'),
    // This is a discovery hint, not a promise about every resolution/frame rate.
    // A decoder supporting level 5.0 (including 4K30) may reject level 5.1.
    // The remux probe still checks the selected track's exact codec string.
    hevc10: ["L123", "L150", "L153"].some((level) =>
      ["hvc1", "hev1"].some((entry) => supports(`video/mp4; codecs="${entry}.2.4.${level}.B0"`))),
    // Profile 5 (single layer) and profile 8.1 (HDR10-compatible base) cover
    // the two shapes a browser could plausibly render.
    dolbyVision: supports('video/mp4; codecs="dvhe.05.06"')
      || supports('video/mp4; codecs="dvh1.05.06"')
      || supports('video/mp4; codecs="dvhe.08.06"')
      || supports('video/mp4; codecs="dvh1.08.06"'),
    av1: supports('video/mp4; codecs="av01.0.08M.08"'),
    vp9: supports('video/mp4; codecs="vp09.00.50.08"') || supports('video/webm; codecs="vp9"'),
    aac: supports('audio/mp4; codecs="mp4a.40.2"'),
    ac3: supports('audio/mp4; codecs="ac-3"'),
    eac3: supports('audio/mp4; codecs="ec-3"'),
    opus: supports('audio/mp4; codecs="opus"') || supports('audio/webm; codecs="opus"'),
    flac: supports('audio/mp4; codecs="flac"')
  };
  return cached;
}
