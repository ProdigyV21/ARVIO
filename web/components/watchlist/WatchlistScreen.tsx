"use client";

import { useApp } from "@/lib/store";
import { RailsView } from "@/components/media/RailsView";

export function WatchlistScreen() {
  const { watchlist, traktConnected, openDetails, settings } = useApp();
  return (
    <RailsView
      title="Watchlist"
      eyebrow={traktConnected ? "Trakt newest-first watchlist" : "Connect Trakt in Settings"}
      categories={[{ id: "watchlist", title: "Saved", items: watchlist }]}
      onOpen={openDetails}
      posterMode={settings.cardLayoutMode === "poster"}
    />
  );
}
