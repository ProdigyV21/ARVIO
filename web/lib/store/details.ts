// App store (lib/store.tsx): openDetails and the source search: addon streams plus Xtream VOD, home-server and
// Telegram sources.
import { useCallback, useRef } from "react";
import { getStreamsProgressive } from "../addons";
import { isAddonNative } from "../addonNative";
import { isYouTubeItem } from "../youtubeCollection";
import { config } from "../config";
import { resolveTmdbId } from "../tmdb";
import { loadDetails } from "@/components/details/detailsLoaders";
import { resumeFromContinueWatching } from "../playerResume";
import { isAllowedForProfile } from "../kidsGate";
import { kidsLevelOf } from "../kids";
import type { MediaItem, StreamSource } from "../types";
import { getPriorityConfig } from "./settings";
import type { StoreCore } from "./core";

export function useDetailsSources(store: StoreCore) {
  const { activeProfileRef, addonsRef, continueWatching, playbackGeneration, setBusy, setSelected, setSelectedEpisode, setStreams, setToast, settingsRef, sourceGeneration } = store;
  // Merge a fresh addon-stream list in without dropping IPTV VOD sources that
  // were appended asynchronously (they carry addonId "iptv_xtream_vod").
  const mergeStreams = useCallback((incoming: StreamSource[]) => {
    setStreams((prev) => {
      // Preserve the supplemental sources injected in parallel (Xtream VOD and
      // home-server files) when the addon/debrid streams resolve.
      const extras = prev.filter(
        (source) =>
          source.addonId === "iptv_xtream_vod" ||
          source.addonId === "home_server" ||
          source.addonId === "telegram_native"
      );
      const seen = new Set(incoming.map((s) => s.url ?? s.source));
      return [...incoming, ...extras.filter((s) => !seen.has(s.url ?? s.source))];
    });
  }, []);

  // Look up the opened title in the Xtream VOD/series catalog and append any
  // match as a supplemental source (parity with the Android app).
  const appendVodSources = useCallback((item: MediaItem, season?: number, episode?: number) => {
    const generation = sourceGeneration.current;
    const playlists = settingsRef.current.iptvPlaylists;
    if (!playlists?.length) return Promise.resolve([] as StreamSource[]);
    return (async () => {
      try {
        const { findMovieVodSources, findEpisodeVodSource } = await import("../xtreamVod");
        const ua = settingsRef.current.customUserAgent;
        const sources = season != null && episode != null
          ? await findEpisodeVodSource(playlists, item, season, episode, ua)
          : await findMovieVodSources(playlists, item, ua);
        if (!sources.length || sourceGeneration.current !== generation) return [];
        setStreams((prev) => {
          const seen = new Set(prev.map((s) => s.url ?? s.source));
          const fresh = sources.filter((s) => s.url && !seen.has(s.url));
          return fresh.length ? [...prev, ...fresh] : prev;
        });
        return sources;
      } catch {
        // VOD is best-effort; addon sources are unaffected on failure.
        return [];
      }
    })();
  }, []);

  // Match the opened title on the user's home servers (Jellyfin/Emby/Plex) and
  // append any files found as sources — parity with the Android app, which
  // surfaces home-server media in the same source list as addons.
  const appendHomeServerSources = useCallback((item: MediaItem, season?: number, episode?: number) => {
    const generation = sourceGeneration.current;
    const servers = settingsRef.current.homeServers;
    if (!servers?.length) return Promise.resolve([] as StreamSource[]);
    return (async () => {
      try {
        const { resolveHomeServerMovieSources, resolveHomeServerEpisodeSources } = await import("../homeserver");
        const target = {
          title: item.title,
          year: item.year ? Number(item.year) || undefined : undefined,
          imdbId: item.imdbId ?? undefined,
          tmdbId: item.tmdbId ?? (item.id > 0 && !item.isHomeServer ? item.id : undefined)
        };
        const sources = season != null && episode != null
          ? await resolveHomeServerEpisodeSources(servers, target, season, episode)
          : await resolveHomeServerMovieSources(servers, target);
        if (!sources.length || sourceGeneration.current !== generation) return [];
        setStreams((prev) => {
          const seen = new Set(prev.map((s) => s.url ?? s.source));
          const fresh = sources.filter((s) => s.url && !seen.has(s.url));
          return fresh.length ? [...prev, ...fresh] : prev;
        });
        return sources;
      } catch {
        // Best-effort; addon/debrid sources are unaffected on failure.
        return [];
      }
    })();
  }, []);

  // Search the user's connected Telegram account for the opened title and append
  // any matching video files as sources — parity with the Android app, which
  // surfaces Telegram media in the same source list as addons.
  const appendTelegramSources = useCallback((item: MediaItem, season?: number, episode?: number) => {
    if (!config.telegramEnabled) return Promise.resolve([] as StreamSource[]);
    const generation = sourceGeneration.current;
    if (item.isHomeServer) return Promise.resolve([] as StreamSource[]);
    return (async () => {
      try {
        const { resolveTelegramSources, isConnected } = await import("@/lib/telegram");
        if (!isConnected()) return [];
        const sources = await resolveTelegramSources(item, season, episode, {
          language: settingsRef.current.language
        });
        if (!sources.length || sourceGeneration.current !== generation) return [];
        setStreams((prev) => {
          const seen = new Set(prev.map((s) => s.url ?? s.source));
          const fresh = sources.filter((s) => !seen.has(s.url ?? s.source));
          return fresh.length ? [...prev, ...fresh] : prev;
        });
        return sources;
      } catch {
        // Best-effort; addon/debrid sources are unaffected on failure.
        return [];
      }
    })();
  }, []);

  // openDetails reads the latest rail without re-creating the callback.
  const continueWatchingRef = useRef(continueWatching);
  continueWatchingRef.current = continueWatching;
  const openDetails = useCallback(async (item: MediaItem) => {
    // Kids profiles: a title opens only when its rating fits the profile, whatever the
    // entry point (card, billboard, Continue Watching, a shared link).
    if (kidsLevelOf(activeProfileRef.current)) {
      const gateGeneration = ++sourceGeneration.current;
      const allowed = await isAllowedForProfile(item, activeProfileRef.current, settingsRef.current.language);
      if (sourceGeneration.current !== gateGeneration) return;
      if (!allowed) { setToast("Not available on this kids profile"); return; }
    }
    const generation = ++sourceGeneration.current;
    const publish = (rows: StreamSource[]) => { if (sourceGeneration.current === generation) mergeStreams(rows); };
    setSelected(item);
    setSelectedEpisode(null);
    // Home-server items carry their own metadata + a direct stream URL — no TMDB.
    if (item.isHomeServer && !item.tmdbId) {
      setSelected(item);
      setStreams(item.homeServerUrl
        ? [{ source: item.title, addonName: "Home Server", quality: "Direct", size: "", url: item.homeServerUrl }]
        : []);
      setBusy("");
      return;
    }
    setBusy("Opening details");
    setStreams([]);
    const priorityConfig = getPriorityConfig(settingsRef.current);
    // Native addon items keep their own (negative) id; their details come from the addon.
    const resolvedId = item.id > 0 || isAddonNative(item) || isYouTubeItem(item) ? item.id : await resolveTmdbId(item).catch(() => null);
    if (sourceGeneration.current !== generation) return;
    if (!resolvedId && !item.isHomeServer) { setBusy(""); setToast("Metadata could not be matched for this title."); return; }
    const detailsTarget = item.isHomeServer && item.tmdbId ? { ...item, id: item.tmdbId } : { ...item, id: resolvedId ?? item.id };
    const detailed = await loadDetails(detailsTarget, priorityConfig).catch(() => item);
    if (sourceGeneration.current !== generation) return;
    // Resume from ANY entry point: a title opened from search or a rail carries
    // no progress, but the Continue Watching row for it does (episode, position).
    const resume = resumeFromContinueWatching(item, continueWatchingRef.current);
    const withResumeEpisode = {
      ...detailed,
      ...(item.isHomeServer ? {
        isHomeServer: true,
        homeServerUrl: item.homeServerUrl,
        homeServerItemId: item.homeServerItemId,
        homeServerId: item.homeServerId,
        homeServerType: item.homeServerType,
        tmdbId: item.tmdbId,
        image: detailed.image || item.image,
        backdrop: detailed.backdrop || item.backdrop
      } : {}),
      seasonNumber: item.seasonNumber ?? resume?.seasonNumber ?? detailed.seasonNumber ?? null,
      episodeNumber: item.episodeNumber ?? resume?.episodeNumber ?? detailed.episodeNumber ?? null,
      episodeTitle: item.episodeTitle ?? resume?.episodeTitle ?? detailed.episodeTitle ?? null,
      progress: item.progress ?? resume?.progress ?? detailed.progress,
      resumePositionSeconds: item.resumePositionSeconds ?? resume?.resumePositionSeconds ?? detailed.resumePositionSeconds,
      durationSeconds: item.durationSeconds ?? resume?.durationSeconds ?? detailed.durationSeconds
    };
    setSelected(withResumeEpisode);
    // Movies fetch sources immediately. Continue-watching TV entries already
    // know their episode, so load that episode's sources without an extra tap.
    if (item.mediaType === "movie") {
      setBusy("Finding sources");
      const supplemental = Promise.allSettled([appendVodSources(withResumeEpisode), appendHomeServerSources(withResumeEpisode), appendTelegramSources(withResumeEpisode)]);
      const found = await getStreamsProgressive(addonsRef.current, withResumeEpisode, undefined, undefined, publish).catch(() => []);
      publish(found);
      await supplemental;
    } else if (withResumeEpisode.seasonNumber != null && withResumeEpisode.episodeNumber != null) {
      setSelectedEpisode({ season: withResumeEpisode.seasonNumber, episode: withResumeEpisode.episodeNumber });
      setBusy("Finding sources");
      const supplemental = Promise.allSettled([appendVodSources(withResumeEpisode, withResumeEpisode.seasonNumber, withResumeEpisode.episodeNumber), appendHomeServerSources(withResumeEpisode, withResumeEpisode.seasonNumber, withResumeEpisode.episodeNumber), appendTelegramSources(withResumeEpisode, withResumeEpisode.seasonNumber, withResumeEpisode.episodeNumber)]);
      const found = await getStreamsProgressive(
        addonsRef.current,
        withResumeEpisode,
        withResumeEpisode.seasonNumber,
        withResumeEpisode.episodeNumber,
        publish
      ).catch(() => []);
      publish(found);
      await supplemental;
    }
    if (sourceGeneration.current === generation) setBusy("");
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const loadEpisodeStreams = useCallback(async (item: MediaItem, season: number, episode: number) => {
    const generation = ++sourceGeneration.current;
    const publish = (rows: StreamSource[]) => { if (sourceGeneration.current === generation) mergeStreams(rows); };
    setSelectedEpisode({ season, episode });
    setStreams([]);
    setBusy("Finding sources");

    const supplemental = Promise.all([appendVodSources(item, season, episode), appendHomeServerSources(item, season, episode), appendTelegramSources(item, season, episode)]);
    const found = await getStreamsProgressive(addonsRef.current, item, season, episode, publish).catch(() => []);
    publish(found);
    const extras = await supplemental;
    if (sourceGeneration.current !== generation) return [];
    setBusy("");
    return [...found, ...extras.flat()];
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const closeDetails = useCallback(() => {
    sourceGeneration.current++;
    playbackGeneration.current++;
    setSelected(null);
    setSelectedEpisode(null);
    setStreams([]);
  }, []);
  return { mergeStreams, appendVodSources, appendHomeServerSources, appendTelegramSources, continueWatchingRef, openDetails, loadEpisodeStreams, closeDetails };
}
