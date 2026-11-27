import { loadFont } from "@remotion/fonts";
import { staticFile } from "remotion";

export const C = {
  bg: "#050707",
  panel: "#0d1312",
  ink: "#f3f7f5",
  soft: "#c3cdc9",
  muted: "#8d9a95",
  mint: "#9efbd5",
  mintDeep: "#2fd39a",
  amber: "#ffd88a",
  line: "rgba(255,255,255,0.08)",
};

export const DISPLAY = '"Funnel Display", Inter, system-ui, sans-serif';
export const TEXT = "Inter, system-ui, sans-serif";

export const FPS = 30;

let loaded = false;
export const loadBrandFonts = () => {
  if (loaded) return;
  loaded = true;
  loadFont({
    family: "Funnel Display",
    url: staticFile("fonts/funnel-display-latin.woff2"),
    weight: "300 800",
  });
  loadFont({
    family: "Inter",
    url: staticFile("fonts/inter.woff2"),
    weight: "100 900",
  });
};

/** All screen captures used by the video. Swap files in public/screens to refresh the footage. */
export const SCREENS = {
  tvHero: "screens/tv/02-home-hero.webp",
  tvHome: "screens/tv/04-home-expanded-card.webp",
  tvClipHome: "screens/tv/clip-home.mp4",
  tvDetails: "screens/tv/07-details-movie.webp",
  tvDetailsSeries: "screens/tv/08-details-series.webp",
  tvLibrary: "screens/tv/11-library.webp",
  tvCalendar: "screens/tv/12-calendar.webp",
  webHome: "screens/web/01-home.webp",
  webDetails: "screens/web/04-details-odyssey.webp",
  tabletHome: "screens/tablet/02-home-hero.webp",
  tabletDetails: "screens/tablet/06-details-movie.webp",
  phoneHome: "screens/phone/02-home-hero.webp",
  phoneDetails: "screens/phone/06-details-movie.webp",
  tabletClip: "screens/tablet/clip-home.mp4",
  phoneClip: "screens/phone/clip-home.mp4",
  subtitlesPlayer: "screens/subtitles-player.webp",
  musicHome: "screens/phone/music-home.webp",
  musicLyrics: "screens/phone/music-lyrics.webp",
  musicQueue: "screens/phone/music-queue.webp",
  tvClipLiveTv: "screens/tv/clip-livetv.mp4",
  phoneClipLiveTv: "screens/phone/clip-livetv.mp4",
  phoneClipSports: "screens/phone/clip-sports.mp4",
  tvClipSports: "screens/tv/clip-sports.mp4",
  tvNews: "screens/tv/news.webp",
  tvHomeServers: "screens/tv/settings-homeservers.webp",
  tvSources: "screens/tv/settings-sources.webp",
  tvTelegram: "screens/tv/settings-telegram.webp",
};
