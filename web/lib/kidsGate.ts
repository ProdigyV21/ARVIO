import { tmdb } from "./tmdb";
import { createKidsRatingStore, KIDS_LEVEL_MAX_AGE, kidsLevelOf, regionOf, type KidsRatingStore } from "./kids";
import type { MediaItem, MediaType, Profile } from "./types";

let ratingStore: KidsRatingStore | null = null;

/** One rating store per page: ages are shared by every screen, like Android's singleton filter. */
export function kidsRatingStore(): KidsRatingStore {
  if (!ratingStore) {
    const storage = (() => { try { return typeof window !== "undefined" ? window.localStorage : null; } catch { return null; } })();
    ratingStore = createKidsRatingStore(
      (mediaType: MediaType, id: number) => tmdb(mediaType === "tv" ? `tv/${id}/content_ratings` : `movie/${id}/release_dates`),
      storage
    );
  }
  return ratingStore;
}

/** The age cap of [profile] (null = regular profile) and the country its ratings are read for. */
export function kidsGateFor(profile: Profile | null | undefined, language: string) {
  const level = kidsLevelOf(profile);
  return { maxAge: level ? KIDS_LEVEL_MAX_AGE[level] : null, region: regionOf(language) };
}

/** Blocking check before a title opens or plays on a kids profile. */
export function isAllowedForProfile(item: MediaItem, profile: Profile | null | undefined, language: string) {
  const { maxAge, region } = kidsGateFor(profile, language);
  if (maxAge === null) return Promise.resolve(true);
  return kidsRatingStore().isAllowed(item, maxAge, region);
}
