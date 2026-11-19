import type { EpisodeInfo } from "./types";

export type SeasonEpisodesLookup = (tvId: number, seasonNumber: number) => Promise<EpisodeInfo[]>;

function playable(episodes: EpisodeInfo[]) {
  return episodes
    .filter((episode) => Number.isInteger(episode?.episodeNumber) && episode.episodeNumber > 0)
    .sort((a, b) => a.episodeNumber - b.episodeNumber);
}

/**
 * The episode after the one just finished, exactly as Android's
 * persistNextEpisodeAfterCompletion picks it: the next episode of the same
 * season, otherwise the first episode of the following season. Returns null
 * when the current season cannot be read (keep the show as it is) or when
 * there is no successor (series finale). An episode that has not aired yet is
 * still returned: Android keeps it as a dated, hidden pointer.
 */
export async function findNextEpisode(
  tvId: number,
  season: number,
  episode: number,
  lookup: SeasonEpisodesLookup
): Promise<EpisodeInfo | null> {
  const current = playable(await lookup(tvId, season));
  if (!current.length) return null;
  const sameSeason = current.find((candidate) => candidate.episodeNumber > episode);
  if (sameSeason) return { ...sameSeason, seasonNumber: sameSeason.seasonNumber ?? season };
  const following = playable(await lookup(tvId, season + 1).catch(() => [] as EpisodeInfo[]));
  const first = following[0];
  return first ? { ...first, seasonNumber: first.seasonNumber ?? season + 1 } : null;
}
