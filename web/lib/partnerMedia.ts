import { PartnerLinkError, resolvePartnerReference, type PartnerTarget, type PartnerTmdbRequest } from "./partnerLinks";
import { mapTmdbItem, tmdb } from "./tmdb";
import type { MediaItem } from "./types";

/** Deliberately bypasses getBasicItem's permanent null cache so network failures remain retryable. */
export async function resolvePartnerMedia(target: PartnerTarget, language: string, customKey?: string): Promise<MediaItem> {
  const request: PartnerTmdbRequest = (path, params) => tmdb(path, { ...params, language }, customKey);
  const reference = await resolvePartnerReference(target, request);
  const details = await request<Parameters<typeof mapTmdbItem>[0]>(`${reference.mediaType}/${reference.id}`);
  if (details.id !== reference.id || details.adult) throw new PartnerLinkError("This title is unavailable in ARVIO.");
  const item = mapTmdbItem(details, reference.mediaType);
  if (!details.title && !details.name) throw new PartnerLinkError("This title's metadata is unavailable. Please try again.");
  if (reference.season !== undefined) {
    const path = `tv/${reference.id}/season/${reference.season}` +
      (reference.episode !== undefined ? `/episode/${reference.episode}` : "");
    const episode = await request<{ season_number?: number; episode_number?: number; name?: string }>(path);
    if (episode.season_number !== reference.season ||
        (reference.episode !== undefined && episode.episode_number !== reference.episode)) {
      throw new PartnerLinkError("This season or episode could not be found.");
    }
    item.seasonNumber = reference.season;
    item.episodeNumber = reference.episode ?? null;
    item.episodeTitle = reference.episode !== undefined ? episode.name ?? null : null;
  }
  // IMDb episode IDs must not be used as the parent show's ratings/scrobbling identity.
  if (target.imdb && reference.episode === undefined) item.imdbId = target.imdb;
  return item;
}
