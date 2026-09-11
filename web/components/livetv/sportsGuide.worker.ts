import { buildSportsGuideEvents } from "@/lib/sportsGuide";
import type { IptvChannel, IptvNowNext } from "@/lib/types";
import { buildSportsCatalogue } from "@/lib/sportsCatalogue";
import type { SportsEventArtwork } from "@/lib/sportsArtwork";

self.onmessage = (message: MessageEvent<{ channels: IptvChannel[]; guide: Record<string, IptvNowNext>; now: number; artwork?: SportsEventArtwork[] }>) => {
  const { channels, guide, now, artwork = [] } = message.data;
  self.postMessage(buildSportsCatalogue(buildSportsGuideEvents(channels, guide, now), artwork, channels, now));
};
