"use client";

import { useEffect, useMemo, useState } from "react";
import { currentPlayerEpisode, playerPauseOverview } from "@/lib/episodePresentation";
import { getSeasonEpisodes, peekSeasonEpisodes } from "@/lib/tmdb";
import type { ProviderPriorityConfig } from "@/lib/metadata/types";
import type { EpisodeInfo, MediaItem } from "@/lib/types";

export function usePlayerEpisodeMetadata(
  item: MediaItem | null,
  selectedEpisode: { season: number; episode: number } | null,
  priorityConfig: ProviderPriorityConfig,
  liveTv: boolean
) {
  const season = selectedEpisode?.season ?? item?.seasonNumber;
  const episode = selectedEpisode?.episode ?? item?.episodeNumber;
  const tvId = !liveTv && item?.mediaType === "tv" ? item.tmdbId ?? item.id : null;
  const key = tvId != null && season != null && episode != null ? `${tvId}:${season}:${episode}` : null;
  const context = useMemo(() => ({ tvdbId: item?.tvdbId, anilistId: item?.anilistId, isAnime: item?.isAnime }),
    [item?.tvdbId, item?.anilistId, item?.isAnime]);
  const [loaded, setLoaded] = useState<{ key: string; episode?: EpisodeInfo } | null>(null);
  const cached = useMemo(() => tvId != null && season != null
    ? currentPlayerEpisode(peekSeasonEpisodes(tvId, season), season, episode) : undefined, [tvId, season, episode]);

  useEffect(() => {
    if (key == null || tvId == null || season == null) return;
    let active = true;
    // Metadata is independent of stream preparation; playback never waits for it.
    void getSeasonEpisodes(tvId, season, "en-US", priorityConfig, context).then(episodes => {
      if (active) setLoaded({ key, episode: currentPlayerEpisode(episodes, season, episode) });
    }).catch(() => undefined);
    return () => { active = false; };
  }, [key, tvId, season, episode, priorityConfig, context]);

  const current = loaded?.key === key ? loaded.episode ?? cached : cached;
  return playerPauseOverview(item, current);
}
