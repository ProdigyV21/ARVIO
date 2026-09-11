"use client";
import { useEffect, useMemo, useState } from "react";
import { SportsGuidePane } from "@/components/livetv/SportsGuidePane";
import { loadSportsMetadata, type SportsEventArtwork } from "@/lib/sportsArtwork";
import { guideSports } from "@/lib/sportsGuide";
import type { IptvChannel, IptvNowNext } from "@/lib/types";

export function SportsMetadataFixture({ emptyGuide = false }: { emptyGuide?: boolean }) {
  const [items, setItems] = useState<SportsEventArtwork[]>([]);
  useEffect(() => { void loadSportsMetadata().then(setItems); }, []);
  const { channels, guide } = useMemo(() => {
    if (emptyGuide) return { channels: [], guide: {} };
    const relevant = items.filter(item => item.startsAt! > Date.now() - 7_200_000 && guideSports.some(s => s.pattern.test(item.genres.join(" ")))).slice(0, 32);
    const channels: IptvChannel[] = relevant.map((item, index) => ({id: `fixture:${index}`, name: "Artwork test channel", group: item.genres.join(" "), streamUrl: "https://example.invalid/not-played", number: String(index + 1)}));
    const guide: Record<string, IptvNowNext> = {};
    relevant.forEach((item, index) => {
      const programme = {title: item.title, category: item.genres.join(" "), startUtcMillis: item.startsAt!, endUtcMillis: item.startsAt! + 7_200_000};
      guide[channels[index].id] = {now: programme, next: programme, upcoming: [programme], recent: []};
    });
    const missingArtChannel: IptvChannel = { id: 'fixture:missing-art', name: 'Schedule test channel', group: 'Rugby', streamUrl: 'https://example.invalid/not-played' };
    channels.push(missingArtChannel);
    guide[missingArtChannel.id] = { now: { title: 'North Test Team vs South Test Team', category: 'Rugby', startUtcMillis: Date.now() - 60_000, endUtcMillis: Date.now() + 3_600_000 }, next: undefined, upcoming: [], recent: [] };
    return {channels, guide};
  }, [items, emptyGuide]);
  return <main className="tv-guide-workspace sports-active groups-collapsed" style={{height: "100dvh", padding: "16px", background: "#050607", display: "block", overflow: "auto"}}><SportsGuidePane channels={channels} guide={guide} onPlay={() => {}} onEnter={() => {}} onOpenCategories={() => {}} /></main>;
}
