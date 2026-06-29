import Hls from "hls.js";

export function attachPlayback(video: HTMLVideoElement, url: string) {
  if (video.canPlayType("application/vnd.apple.mpegurl")) {
    video.src = url;
    return () => {
      video.removeAttribute("src");
      video.load();
    };
  }

  if (Hls.isSupported() && (url.includes(".m3u8") || url.includes("application/vnd.apple.mpegurl"))) {
    const hls = new Hls({
      enableWorker: true,
      lowLatencyMode: true,
      backBufferLength: 90
    });
    hls.loadSource(url);
    hls.attachMedia(video);
    return () => hls.destroy();
  }

  video.src = url;
  return () => {
    video.removeAttribute("src");
    video.load();
  };
}
