// App store (lib/store.tsx): Watched state, the watchlist and Continue Watching actions.
import { prepareWatchlistChange, queueWatchlistChange, flushWatchlistOutbox } from "../watchlistOutbox";
import { useCallback } from "react";
import { removeContinueWatchingProgress, saveWatchedState } from "../cloud";
import { activeSyncProvider, syncClient } from "../sync";
import type { MediaItem } from "../types";
import { isMediaWatched, mediaWatchKey } from "./watchHistory";
import { authClient } from "./clients";
import { cwCacheKeyFor, saveCachedList, slimCacheItem, watchlistCacheKeyFor } from "./listCache";
import type { StoreCore } from "./core";

export function useWatchState(store: StoreCore) {
  const { setCategories, setContinueWatching, setWatchedKeys, watchedKeys } = store;
  const isWatched = useCallback((item: MediaItem, seasonNumber?: number | null, episodeNumber?: number | null) => (
    isMediaWatched(item, watchedKeys, seasonNumber, episodeNumber)
  ), [watchedKeys]);

  // Optimistic watched-mark: badge + drop from Continue Watching instantly,
  // before the Trakt round-trip returns (used by the external-playback prompt
  // and the details Mark Watched flow so the UI responds immediately).
  const markWatchedLocally = useCallback((item: { mediaType: MediaItem["mediaType"]; id: number; season?: number | null; episode?: number | null }, watched = true) => {
    const target = { ...item, seasonNumber: item.season ?? null, episodeNumber: item.episode ?? null } as unknown as MediaItem;
    const key = mediaWatchKey(target, item.season, item.episode);
    setWatchedKeys((prev) => {
      const next = new Set(prev);
      if (watched) next.add(key); else next.delete(key);
      return next;
    });
    if (watched) {
      setContinueWatching((prev) => prev.filter((entry) => mediaWatchKey(entry) !== key && mediaWatchKey(entry) !== `${item.mediaType}:${item.id}`));
      setCategories((prev) => prev.map((cat) => cat.id === "continue_watching"
        ? { ...cat, items: cat.items.filter((entry) => mediaWatchKey(entry) !== key && mediaWatchKey(entry) !== `${item.mediaType}:${item.id}`) }
        : cat).filter((cat) => cat.id !== "continue_watching" || cat.items.length));
    }
  }, []);
  return { isWatched, markWatchedLocally };
}

export function useLibraryActions(store: StoreCore & Pick<ReturnType<typeof useWatchState>, "isWatched" | "markWatchedLocally">) {
  const { activeProfileId, activeProfileIdRef, isWatched, markWatchedLocally, setCategories, setContinueWatching, setToast, setWatchlist, watchlist, watchlistMutationRef } = store;
  const toggleWatchlist = useCallback(async (item: MediaItem) => {
    const userId = authClient.session?.userId;
    const inWatchlist = watchlist.some((entry) => entry.mediaType === item.mediaType && entry.id === item.id);
    // Like Android (WatchlistRepository), the watchlist lives in ARVIO Cloud
    // (watchlistByProfile via the outbox below) and on this device; Trakt,
    // Simkl and MDBList are optional mirrors written when connected.
    const mutationKey = JSON.stringify([userId, activeProfileId, item.mediaType, item.id]);
    const mutationId = (watchlistMutationRef.current.get(mutationKey) ?? 0) + 1;
    watchlistMutationRef.current.set(mutationKey, mutationId);
    const isCurrentAction = () => authClient.session?.userId === userId &&
      activeProfileIdRef.current === activeProfileId && watchlistMutationRef.current.get(mutationKey) === mutationId;
    const slim = slimCacheItem(item);
    const cloudChange = prepareWatchlistChange(authClient, slim, inWatchlist, activeProfileId);
    const cacheKey = watchlistCacheKeyFor(activeProfileId);
    const nextWatchlist = inWatchlist
      ? watchlist.filter((entry) => !(entry.mediaType === item.mediaType && entry.id === item.id))
      : [slim, ...watchlist];
    setWatchlist(nextWatchlist);
    saveCachedList(cacheKey, nextWatchlist, 60);

    try {
      const ref = {
        mediaType: item.mediaType,
        tmdbId: item.id,
        isAnime: item.mediaType === "tv" && item.originalLanguage === "ja" && Boolean(item.genreIds?.includes(16))
      };
      if (inWatchlist) {
        await syncClient().removeFromWatchlist(ref);
        if (isCurrentAction()) setToast("Removed from watchlist.");
      } else {
        await syncClient().addToWatchlist(ref);
        if (isCurrentAction()) setToast("Added to watchlist.");
      }
      if (userId && authClient.session?.userId === userId) {
        queueWatchlistChange(authClient, slim, inWatchlist, activeProfileId, cloudChange);
        await flushWatchlistOutbox(authClient).catch(() => {
          if (isCurrentAction()) setToast('Watchlist updated. Cloud sync will retry when you reconnect.');
        });
      } else if (!userId && !authClient.session) {
        // No ARVIO account: the device-local outbox bucket is the watchlist.
        queueWatchlistChange(authClient, slim, inWatchlist, activeProfileId, cloudChange);
      }
    } catch (err) {
      if (!isCurrentAction()) return;
      setWatchlist((prev) => {
        const next = inWatchlist ? [slim, ...prev] : prev.filter((entry) => !(entry.mediaType === item.mediaType && entry.id === item.id));
        saveCachedList(cacheKey, next, 60);
        return next;
      });
      setToast(err instanceof Error ? err.message : "Failed to update watchlist.");
    }
  }, [watchlist, activeProfileId, authClient]);

  const toggleWatched = useCallback(async (item: MediaItem, seasonNumber?: number | null, episodeNumber?: number | null, skipSync?: boolean) => {
    const currentlyWatched = isWatched(item, seasonNumber, episodeNumber);
    markWatchedLocally({ mediaType: item.mediaType, id: item.id, season: seasonNumber, episode: episodeNumber }, !currentlyWatched);
    setToast(!currentlyWatched ? "Marked as watched." : "Marked as unwatched.");

    if (authClient.session) {
      try {
        await saveWatchedState(authClient, {
          id: item.id,
          mediaType: item.mediaType,
          seasonNumber: seasonNumber ?? item.seasonNumber ?? null,
          episodeNumber: episodeNumber ?? item.episodeNumber ?? null
        }, !currentlyWatched, activeProfileId);
      } catch {
        // Cloud sync best effort
      }
    }

    if (!skipSync && activeSyncProvider() !== "none") {
      try {
        const ref = {
          mediaType: item.mediaType,
          tmdbId: item.id,
          season: typeof seasonNumber === "number" ? seasonNumber : item.seasonNumber ?? undefined,
          episode: typeof episodeNumber === "number" ? episodeNumber : item.episodeNumber ?? undefined,
          isAnime: item.mediaType === "tv" && item.originalLanguage === "ja" && Boolean(item.genreIds?.includes(16))
        };
        if (!currentlyWatched) {
          await syncClient().addToHistory(ref);
        } else {
          await syncClient().removeFromHistory(ref);
        }
      } catch {
        // Sync best effort
      }
    }
  }, [isWatched, markWatchedLocally, authClient, activeProfileId]);

  const removeFromContinueWatching = useCallback(async (item: MediaItem) => {
    const key = mediaWatchKey(item);
    const cacheKey = cwCacheKeyFor(activeProfileId);

    setContinueWatching((prev) => {
      const next = prev.filter((entry) => mediaWatchKey(entry) !== key && mediaWatchKey(entry) !== `${item.mediaType}:${item.id}`);
      saveCachedList(cacheKey, next, 30);
      return next;
    });

    setCategories((prev) => prev.map((cat) => cat.id === "continue_watching"
      ? { ...cat, items: cat.items.filter((entry) => mediaWatchKey(entry) !== key && mediaWatchKey(entry) !== `${item.mediaType}:${item.id}`) }
      : cat).filter((cat) => cat.id !== "continue_watching" || cat.items.length));

    setToast("Removed from Continue Watching.");

    if (authClient.session) {
      try {
        await removeContinueWatchingProgress(authClient, item, activeProfileId);
      } catch {
        // Cloud sync best effort
      }
    }

    if (activeSyncProvider() !== "none") {
      try {
        await syncClient().dismissFromContinueWatching({ mediaType: item.mediaType, tmdbId: item.id, season: item.seasonNumber, episode: item.episodeNumber });
      } catch {
        // Sync best effort
      }
    }
  }, [activeProfileId, authClient]);
  return { toggleWatchlist, toggleWatched, removeFromContinueWatching };
}
