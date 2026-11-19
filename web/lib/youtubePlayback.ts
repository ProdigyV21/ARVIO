export type YouTubeCheckpoint = { position: number; duration: number; progress: number };

export interface YouTubePlayer {
  getCurrentTime(): number;
  getDuration(): number;
  destroy(): void;
}

export interface YouTubeApi {
  Player: new (element: HTMLElement, options: {
    host: string;
    videoId: string;
    playerVars: Record<string, string | number>;
    events: { onStateChange(event: { data: number }): void; onError(): void };
  }) => YouTubePlayer;
}

type YouTubeWindow = Window & { YT?: YouTubeApi; onYouTubeIframeAPIReady?: () => void };
let apiPromise: Promise<YouTubeApi> | null = null;

export function youTubeEmbedUrl(videoId: string, resumeSeconds: number, origin: string) {
  const url = new URL(`https://www.youtube-nocookie.com/embed/${videoId}`);
  url.search = new URLSearchParams({ autoplay: "1", playsinline: "1", enablejsapi: "1", origin,
    start: String(Number.isFinite(resumeSeconds) ? Math.max(0, Math.floor(resumeSeconds)) : 0) }).toString();
  return url.toString();
}

export function loadYouTubeApi(): Promise<YouTubeApi> {
  const target = window as YouTubeWindow;
  if (target.YT?.Player) return Promise.resolve(target.YT);
  if (apiPromise) return apiPromise;
  apiPromise = new Promise<YouTubeApi>((resolve, reject) => {
    const previousReady = target.onYouTubeIframeAPIReady;
    const timer = window.setTimeout(() => fail(), 15_000);
    const fail = () => {
      window.clearTimeout(timer);
      script.remove();
      target.onYouTubeIframeAPIReady = previousReady;
      reject(new Error("YouTube player could not load. Please try again."));
    };
    target.onYouTubeIframeAPIReady = () => {
      window.clearTimeout(timer);
      try { previousReady?.(); } finally {
        if (target.YT?.Player) resolve(target.YT);
        else fail();
      }
    };
    const script = document.createElement("script");
    script.src = "https://www.youtube.com/iframe_api";
    script.onerror = fail;
    document.head.appendChild(script);
  }).catch(error => { apiPromise = null; throw error; });
  return apiPromise;
}

/** Bound snapshot writes while preserving pause/end/close checkpoints and retrying failures. */
export function createYouTubeCheckpointWriter(
  save: (checkpoint: YouTubeCheckpoint) => Promise<unknown>,
  isCurrent: () => boolean,
  now: () => number = Date.now
) {
  let lastSaved = -Infinity;
  let lastPosition = -1;
  let lastProgress = -1;
  return (position: number, duration: number, force = false) => {
    if (!isCurrent() || !Number.isFinite(position) || !Number.isFinite(duration) || position <= 0 || duration <= 0) return;
    position = Math.min(position, duration);
    const rounded = Math.round(position);
    const progress = Math.min(1, position / duration);
    if ((rounded === lastPosition && progress === lastProgress) || (!force && now() - lastSaved < 60_000)) return;
    lastPosition = rounded;
    lastProgress = progress;
    lastSaved = now();
    void save({ position: rounded, duration: Math.round(duration), progress })
      .catch(() => { if (lastPosition === rounded) { lastPosition = -1; lastSaved = -Infinity; } });
  };
}

export function mountYouTubePlayer(options: {
  api: YouTubeApi;
  element: HTMLElement;
  videoId: string;
  resumeSeconds: number;
  origin: string;
  checkpoint: (position: number, duration: number, force?: boolean) => void;
  onError: () => void;
}) {
  let started = false;
  let ended = false;
  const player = new options.api.Player(options.element, {
    host: "https://www.youtube-nocookie.com",
    videoId: options.videoId,
    playerVars: {
      autoplay: 1, playsinline: 1, enablejsapi: 1, origin: options.origin,
      start: Number.isFinite(options.resumeSeconds) ? Math.max(0, Math.floor(options.resumeSeconds)) : 0
    },
    events: {
      onStateChange: ({ data }) => {
        if (data === 1) { started = true; ended = false; }
        if (data === 0) ended = true;
        if (started && (data === 2 || data === 0)) capture(true);
      },
      onError: options.onError
    }
  });
  function capture(force = false) {
    if (!started) return;
    try {
      const duration = player.getDuration();
      options.checkpoint(ended ? duration : player.getCurrentTime(), duration, force);
    } catch { /* The iframe can disappear during page teardown. */ }
  }
  return {
    capture,
    destroy: () => { capture(true); player.destroy(); }
  };
}
