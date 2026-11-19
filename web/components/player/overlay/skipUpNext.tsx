// Part of the player overlay (see components/player/PlayerOverlay.tsx).
import { useState, useEffect, type RefObject, type Dispatch, type SetStateAction } from "react";
import { type SkipSegment, fetchSkipSegments, activeSkipSegment, isCreditsSegment, upNextStartTime, skipSegmentLabel } from "@/lib/playerSkipSegments";
import { proxiedUrl } from "@/lib/http";
import type { EpisodeInfo, MediaItem, StreamSource } from "@/lib/types";
import { loadSeasonEpisodes } from "@/components/details/detailsLoaders";
import { SkipForward, Play, X } from "lucide-react";
import { fetchJson } from "./helpers";
import type { ProviderPriorityConfig } from "@/lib/metadata/types";
import { useTranslation } from "@/lib/i18n";

/** Skip intro / recap / credits and the "Up next" card. */
export function useSkipAndUpNext({ booted, canAdvance, current, duration, ended, error, flashControls, item, liveTv, metadataPriority, nextCountdown, nextDismissed, onAdvance, selectedEpisode, setNextCountdown, setUpNextDismissed, stream, upNextDismissed, upNextShownRef, videoRef }: { booted: boolean; canAdvance: boolean; current: number; duration: number; ended: boolean; error: boolean; flashControls: () => void; item: MediaItem | null; liveTv: boolean; metadataPriority: ProviderPriorityConfig; nextCountdown: number | null; nextDismissed: RefObject<boolean>; onAdvance: () => Promise<boolean>; selectedEpisode: { season: number; episode: number; } | null; setNextCountdown: Dispatch<SetStateAction<number | null>>; setUpNextDismissed: Dispatch<SetStateAction<boolean>>; stream: StreamSource; upNextDismissed: boolean; upNextShownRef: RefObject<boolean>; videoRef: RefObject<HTMLVideoElement | null> }) {
  // ---------- Skip intro / recap / credits and "Up next" ----------
  const [skipSegments, setSkipSegments] = useState<SkipSegment[]>([]);
  const episodeSeason = selectedEpisode?.season ?? item?.seasonNumber ?? null;
  const episodeNumber = selectedEpisode?.episode ?? item?.episodeNumber ?? null;
  useEffect(() => {
    setSkipSegments([]);
    if (liveTv || item?.mediaType !== "tv" || episodeSeason == null || episodeNumber == null || !item.imdbId) return undefined;
    const controller = new AbortController();
    void fetchSkipSegments({ imdbId: item.imdbId, season: episodeSeason, episode: episodeNumber }, {
      fetchJson, introDbUrl: (url) => proxiedUrl(url), signal: controller.signal
    }).then((segments) => { if (!controller.signal.aborted) setSkipSegments(segments); }).catch(() => undefined);
    return () => controller.abort();
  }, [liveTv, item?.mediaType, item?.imdbId, episodeSeason, episodeNumber]);
  const activeSkip = !liveTv && booted && !error ? activeSkipSegment(skipSegments, current * 1000) : null;
  const [skipDismissedKey, setSkipDismissedKey] = useState<string | null>(null);
  const activeSkipKey = activeSkip ? `${activeSkip.type}:${activeSkip.startMs}` : null;
  const showSkip = Boolean(activeSkip) && activeSkipKey !== skipDismissedKey && !(isCreditsSegment(activeSkip!) && canAdvance);
  const skipNow = () => {
    const video = videoRef.current;
    if (!video || !activeSkip) return;
    video.currentTime = Math.min(video.duration || Infinity, activeSkip.endMs / 1000 + 0.5);
    setSkipDismissedKey(activeSkipKey);
    flashControls();
  };

  // The next episode's title and still, for the "Up next" card.
  const [nextEpisode, setNextEpisode] = useState<{ season: number; episode: number; name: string; still?: string } | null>(null);
  const metadataPriority2 = metadataPriority;
  useEffect(() => {
    setNextEpisode(null);
    if (!canAdvance || !item || episodeSeason == null || episodeNumber == null) return undefined;
    let active = true;
    const tvId = item.tmdbId ?? item.id;
    const context = { tvdbId: item.tvdbId, anilistId: item.anilistId, isAnime: item.isAnime };
    void (async () => {
      const pick = (episodes: EpisodeInfo[], season: number, episode: number) => episodes.find((entry) => entry.seasonNumber === season && entry.episodeNumber === episode);
      let found = pick(await loadSeasonEpisodes(tvId, episodeSeason, "en-US", metadataPriority2, context).catch(() => []), episodeSeason, episodeNumber + 1);
      if (!found) {
        const nextSeason = (item.seasons ?? []).map((entry) => entry.seasonNumber).filter((n) => n > episodeSeason).sort((a, b) => a - b)[0];
        if (nextSeason != null) found = pick(await loadSeasonEpisodes(tvId, nextSeason, "en-US", metadataPriority2, context).catch(() => []), nextSeason, 1);
      }
      if (!active || !found) return;
      if (found.airDate && Date.parse(found.airDate) > Date.now()) return;
      setNextEpisode({ season: found.seasonNumber, episode: found.episodeNumber, name: found.name, still: found.still });
    })();
    return () => { active = false; };
  }, [canAdvance, item, episodeSeason, episodeNumber, metadataPriority2]);
  const upNextAt = upNextStartTime(duration, skipSegments);
  const showUpNext = canAdvance && Boolean(nextEpisode) && !upNextDismissed && !error && booted
    && (current >= upNextAt || ended || nextCountdown !== null);
  useEffect(() => { if (showUpNext) upNextShownRef.current = true; }, [showUpNext]);
  useEffect(() => { upNextShownRef.current = false; }, [stream.url]);
  const dismissUpNext = () => {
    setUpNextDismissed(true);
    nextDismissed.current = true;
    setNextCountdown(null);
  };
  const playNextNow = () => {
    setNextCountdown(null);
    void onAdvance();
  };
  return { activeSkip, showSkip, skipNow, nextEpisode, showUpNext, dismissUpNext, playNextNow };
}

/** The skip button and the "Up next" card. */
export function PlayerSkipUpNext({ activeSkip, dismissUpNext, nextCountdown, nextEpisode, playNextNow, showSkip, showUpNext, skipNow }: { activeSkip: SkipSegment | null; dismissUpNext: () => void; nextCountdown: number | null; nextEpisode: { season: number; episode: number; name: string; still?: string; } | null; playNextNow: () => void; showSkip: boolean; showUpNext: boolean; skipNow: () => void }) {
  const translateUi = useTranslation();
  return (<>
      {showSkip && activeSkip ? (
        <div className="player-skip">
          <button type="button" onClick={skipNow}><SkipForward size={16} /> {translateUi(skipSegmentLabel(activeSkip))}</button>
        </div>
      ) : null}
      {showUpNext && nextEpisode ? (
        <div className="player-upnext" role="status" aria-live="polite">
          {nextEpisode.still ? <img src={nextEpisode.still} alt="" /> : null}
          <div className="player-upnext-text">
            <small>{nextCountdown !== null ? translateUi("Next episode in {value0}s", { value0: nextCountdown }) : translateUi("Up next")}</small>
            <strong>{translateUi("S{value0} E{value1}", { value0: nextEpisode.season, value1: nextEpisode.episode })} · {nextEpisode.name}</strong>
          </div>
          <div className="player-upnext-actions">
            <button type="button" className="player-upnext-play" onClick={playNextNow}><Play size={16} fill="currentColor" /> {translateUi("Play now")}</button>
            <button type="button" className="player-icon-btn" onClick={dismissUpNext} aria-label={translateUi("Cancel next episode")}><X size={18} /></button>
          </div>
        </div>
      ) : null}
  </>);
}
