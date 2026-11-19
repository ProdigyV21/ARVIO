"use client";
import { useTranslation } from "@/lib/i18n";
import "./details.css";

import { ArrowLeft, BadgeCheck, Bookmark, CalendarDays, Check, ChevronDown, Clapperboard, EyeOff, Folder, Loader2, MapPin, MoreHorizontal, Play, RotateCcw, Star, Trash2, UserCircle, X } from "lucide-react";
import { useCallback, useEffect, useMemo, useRef, useState, type ReactNode } from "react";
import { createPortal } from "react-dom";
import { MediaCard } from "@/components/media/MediaCard";
import { RailScroller } from "@/components/media/RailScroller";
import { premiumAllows } from "@/lib/premiumGate";
import { relaySupportsTraktLink, simklRedirectUrl, traktLinkUrl, traktPageViaSimkl } from "@/lib/externalLinks";
import { getContinueWatching, saveWatchedState } from "@/lib/cloud";
import { parseDebridStream } from "@/lib/debrid";
import { clearPlayOnOpen, peekPlayOnOpen } from "@/lib/playIntent";
import { canonicalServiceName, IMDB_LOGO, serviceClearLogo } from "@/lib/serviceLogos";
import { getImdbRating } from "@/lib/imdbRatings";
import { mdblistClient, type MdbExternalRating } from "@/lib/mdblist";
import { browserAutoplayCandidates } from "@/lib/browserAutoplay";
import { chooseAutoplaySource, loadAutoplayPreference } from "@/lib/autoplaySelection";
import { verifyAutoplayCandidates } from "@/lib/autoplayVerify";
import { playbackPlan } from "@/lib/streamCompatibility";
import { claimEscape, isTopmostLayer } from "@/lib/playerKeys";
import { collectResumePoints, episodeKey, episodeProgressMap, firstUnwatchedEpisode, planPrimaryAction, type ResumePoint } from "@/lib/playerResume";
import { authClient, getPriorityConfig, useAppPick } from "@/lib/store";
import { simklClient, getSimklItemUrl } from "@/lib/simkl";
import { syncClient, syncSeasonWatched } from "@/lib/sync";
import { getLogoUrl, getPersonDetails } from "@/lib/tmdb";
import type { EpisodeInfo, MediaItem, PersonCredit, PersonDetails, ReviewInfo, WatchHistoryEntry } from "@/lib/types";
import { EpisodeCard } from "./EpisodeCard";
import { loadDetails, loadReviews, loadSeasonEpisodes } from "./detailsLoaders";
import { buildDetailMeta, cleanReviewText, formatDate } from "./detailsText";
import { SourcePickerModal, useCompactSources } from "./SourcePicker";

export function DetailsDrawer() {
  const { selected: item } = useAppPick("selected");
  if (!item) return null;
  return <DetailsView key={`${item.mediaType}-${item.id}`} item={item} />;
}

function needsDetailsHydration(item: MediaItem) {
  // TV shows ALWAYS need a full details fetch: an item opened from a rail or
  // Continue Watching may carry a partial/stale `seasons` array (or one built
  // from a single episode), so trusting `seasons?.length` skipped hydration and
  // left the details page missing seasons/episodes. getDetails is cached, so
  // re-fetching a fully-hydrated item is cheap.
  if (item.mediaType === "tv") return true;
  return !item.cast?.length && !item.related?.length && !item.trailerUrl;
}

// ---------- Focus return ----------
//
// Details replaces the screen it was opened from, so the card that opened it
// is unmounted while details are shown. Remember where it was (its rail and
// position) and put focus back there once that screen has rendered again.
type FocusReturn = { element: HTMLElement; railLabel: string | null; index: number; text: string };

function describeOpener(element: Element | null): FocusReturn | null {
  if (!(element instanceof HTMLElement) || element === document.body) return null;
  const rail = element.closest<HTMLElement>("[aria-label]");
  const cards = rail ? Array.from(rail.querySelectorAll<HTMLElement>(element.tagName)) : [];
  return { element, railLabel: rail?.getAttribute("aria-label") ?? null, index: cards.indexOf(element), text: element.textContent?.trim().slice(0, 80) ?? "" };
}

function restoreFocus(target: FocusReturn | null) {
  if (!target) return;
  let attempts = 0;
  const tryFocus = () => {
    attempts += 1;
    if (document.querySelector(".details-drawer")) return; // another title opened
    if (target.element.isConnected) { target.element.focus({ preventScroll: false }); return; }
    const rails = target.railLabel ? Array.from(document.querySelectorAll<HTMLElement>(`[aria-label="${CSS.escape(target.railLabel)}"]`)) : [];
    for (const rail of rails) {
      const cards = Array.from(rail.querySelectorAll<HTMLElement>(target.element.tagName));
      const match = cards.find((card) => card.textContent?.trim().slice(0, 80) === target.text) ?? (target.index >= 0 ? cards[target.index] : undefined);
      if (match) { match.focus({ preventScroll: false }); match.scrollIntoView?.({ block: "nearest", inline: "nearest" }); return; }
    }
    if (attempts < 12) window.setTimeout(tryFocus, 120);
  };
  window.setTimeout(tryFocus, 0);
}

/** Mount children only when the placeholder nears the viewport. */
function LazyMount({ children, minHeight }: { children: ReactNode; minHeight: number }) {
  const ref = useRef<HTMLDivElement | null>(null);
  const [shown, setShown] = useState(false);
  useEffect(() => {
    const node = ref.current;
    if (!node || shown) return undefined;
    if (typeof IntersectionObserver === "undefined") { setShown(true); return undefined; }
    const observer = new IntersectionObserver((entries) => {
      if (entries.some((entry) => entry.isIntersecting)) { setShown(true); observer.disconnect(); }
    }, { rootMargin: "400px 0px" });
    observer.observe(node);
    return () => observer.disconnect();
  }, [shown]);
  return <div ref={ref} style={shown ? undefined : { minHeight }}>{shown ? children : null}</div>;
}

type AutoplayRequest = { resumeSeconds: number };

function DetailsView({ item }: { item: MediaItem }) {
  const translateUi = useTranslation();
  const { streams, playStream, selectedEpisode, activeProfile, activeStream, addons: installedAddons, loadEpisodeStreams, openDetails, closeDetails, playTrailer, setToast, settings, watchlist, refreshData, busy, isWatched, markWatchedLocally, toggleWatchlist, mdblistConnected, continueWatching } = useAppPick("streams", "playStream", "selectedEpisode", "activeProfile", "activeStream", "addons", "loadEpisodeStreams", "openDetails", "closeDetails", "playTrailer", "setToast", "settings", "watchlist", "refreshData", "busy", "isWatched", "markWatchedLocally", "toggleWatchlist", "mdblistConnected", "continueWatching");
  const [detailsItem, setDetailsItem] = useState<MediaItem>(item);
  const [detailsLoading, setDetailsLoading] = useState(false);
  const [reviews, setReviews] = useState<ReviewInfo[]>([]);
  const [person, setPerson] = useState<PersonDetails | null>(null);
  const [personLoading, setPersonLoading] = useState(false);
  const [personVisible, setPersonVisible] = useState(false);
  const [sourcePickerVisible, setSourcePickerVisible] = useState(false);
  const [autoplayRequest, setAutoplayRequest] = useState<AutoplayRequest | null>(null);
  const [starting, setStarting] = useState(false);
  const rootRef = useRef<HTMLElement | null>(null);
  const playButtonRef = useRef<HTMLButtonElement | null>(null);
  const episodesRef = useRef<HTMLElement | null>(null);
  const headingId = `details-title-${item.mediaType}-${item.id}`;
  // The chooser polls these while sources are still arriving; refs keep one
  // selection run alive across the progressive stream updates.
  const autoplayLive = useRef({ streams, busy, playStream, setToast });
  autoplayLive.current = { streams, busy, playStream, setToast };
  useEffect(() => {
    if (!autoplayRequest) return undefined;
    const controller = new AbortController();
    // Wait briefly for a debrid source or the addon that last played on this
    // device, probe the top candidates, then start — as soon as the best one
    // passes — instead of taking the first row that happened to arrive.
    void chooseAutoplaySource({
      candidates: () => browserAutoplayCandidates(autoplayLive.current.streams),
      searching: () => Boolean(autoplayLive.current.busy),
      preference: loadAutoplayPreference(),
      isDebrid: (stream) => Boolean(parseDebridStream(stream.originalUrl ?? stream.url)),
      methodOf: (stream) => playbackPlan(stream).method,
      verify: verifyAutoplayCandidates,
      signal: controller.signal
    }).then(({ stream: source }) => {
      if (controller.signal.aborted) return;
      setAutoplayRequest(null);
      if (source) {
        setStarting(true);
        autoplayLive.current.playStream({ ...source, autoSelect: true, resumePositionSeconds: autoplayRequest.resumeSeconds }, { forceBrowser: true });
      } else {
        setSourcePickerVisible(true);
        autoplayLive.current.setToast("No browser-playable source is available. Choose a source manually.");
      }
    });
    return () => controller.abort();
  }, [autoplayRequest]);
  // "Starting…" lasts until the player opens (or a bounded time on failure).
  useEffect(() => {
    if (!starting) return undefined;
    if (activeStream) { setStarting(false); return undefined; }
    const timer = window.setTimeout(() => setStarting(false), 25_000);
    return () => window.clearTimeout(timer);
  }, [starting, activeStream]);
  const [logo, setLogo] = useState<string | null>(null);
  const [relayTraktLink, setRelayTraktLink] = useState(false);
  useEffect(() => { let active = true; void relaySupportsTraktLink().then((ok) => { if (active) setRelayTraktLink(ok); }); return () => { active = false; }; }, []);
  // Trakt's own page, found through Simkl's id mapping; the relay redirect and
  // a title search remain the fallbacks.
  const [traktPage, setTraktPage] = useState<string | null>(null);
  const traktImdb = detailsItem?.imdbId ?? item.imdbId ?? null;
  useEffect(() => {
    setTraktPage(null);
    let active = true;
    void traktPageViaSimkl({ id: item.id, imdbId: traktImdb, mediaType: item.mediaType }).then((url) => { if (active) setTraktPage(url); });
    return () => { active = false; };
  }, [item.id, item.mediaType, traktImdb]);

  const displayItem = detailsItem ?? item;
  const priorityConfig = useMemo(() => getPriorityConfig(settings), [settings]);

  // ---------- Dialog semantics, focus and Escape ----------
  const close = useCallback(() => {
    // Details were pushed onto history by BackHandler: go back so Back/forward
    // stay in sync; a deep-linked first entry just closes.
    if (window.history.length > 1 && (window.history.state as { arvio?: boolean } | null)?.arvio) window.history.back();
    else closeDetails();
  }, [closeDetails]);
  useEffect(() => {
    const opener = describeOpener(document.activeElement);
    window.scrollTo({ top: 0, left: 0, behavior: "auto" });
    const timer = window.setTimeout(() => playButtonRef.current?.focus({ preventScroll: true }), 30);
    return () => { window.clearTimeout(timer); restoreFocus(opener); };
  }, []);
  useEffect(() => {
    const onKey = (event: KeyboardEvent) => {
      if (event.key !== "Escape" || !claimEscape(event) || !isTopmostLayer(rootRef.current)) return;
      if (event.target instanceof HTMLInputElement || event.target instanceof HTMLTextAreaElement) return;
      event.preventDefault();
      close();
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, [close]);

  useEffect(() => {
    let active = true;
    setDetailsItem(item);
    if (!needsDetailsHydration(item)) {
      setDetailsLoading(false);
      return () => { active = false; };
    }
    setDetailsLoading(true);
    // getDetails swallows fetch errors and returns the bare item, so a proxy
    // hiccup (common mid-startup-burst) used to leave the page permanently
    // without seasons/cast. Retry until the payload actually looks hydrated.
    const looksHydrated = (details: MediaItem) => details.mediaType === "tv"
      ? Boolean(details.seasons?.length)
      : Boolean(details.cast?.length || details.related?.length || details.trailerUrl);
    void (async () => {
      for (let attempt = 0; attempt < 3 && active; attempt += 1) {
        const details = await loadDetails(item, priorityConfig).catch(() => null);
        if (!active) return;
        if (details) setDetailsItem((current) => ({ ...details, seasonNumber: current.seasonNumber ?? details.seasonNumber, episodeNumber: current.episodeNumber ?? details.episodeNumber, resumePositionSeconds: current.resumePositionSeconds ?? details.resumePositionSeconds, progress: current.progress ?? details.progress }));
        if (details && looksHydrated(details)) break;
        if (attempt < 2) await new Promise((resolve) => setTimeout(resolve, 900 * (attempt + 1)));
      }
      if (active) setDetailsLoading(false);
    })();
    return () => { active = false; };
    // The store swaps the shallow item for the hydrated one under the same key:
    // the in-flight loader shares one request for both.
  }, [item, priorityConfig]);

  // Below the fold: reviews load after the details payload, not alongside it.
  useEffect(() => {
    if (detailsLoading) return undefined;
    let active = true;
    void loadReviews(item).then((r) => active && setReviews(r)).catch(() => undefined);
    return () => { active = false; };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [item.id, item.mediaType, detailsLoading]);

  useEffect(() => {
    let active = true;
    setLogo(null);
    void getLogoUrl({ mediaType: item.mediaType, id: item.id }).then((url) => {
      if (active) setLogo(url);
    }).catch(() => undefined);
    return () => { active = false; };
  }, [item.id, item.mediaType]);

  // MKV sources need the in-browser remux path: fetch its code while the
  // viewer is still reading, so pressing Play does not wait for the chunk.
  const hasRemuxSource = streams.some((stream) => stream.url && playbackPlan(stream).method === "remux");
  useEffect(() => {
    if (hasRemuxSource) void import("@/lib/remux").catch(() => undefined);
  }, [hasRemuxSource]);

  const isTv = displayItem.mediaType === "tv";
  const inWatchlist = watchlist.some((entry) => entry.mediaType === item.mediaType && entry.id === item.id);
  const detailWatched = isWatched(displayItem, selectedEpisode?.season, selectedEpisode?.episode);

  // ---------- Resume, from any entry point ----------
  // The rail item, the Continue Watching row and the cloud watch history all
  // know where the viewer stopped; a title opened from search knows nothing.
  const [history, setHistory] = useState<WatchHistoryEntry[]>([]);
  useEffect(() => {
    if (!authClient.session) return undefined;
    let active = true;
    void getContinueWatching(authClient, activeProfile?.id ?? null, installedAddons)
      .then((rows) => { if (active) setHistory(rows.filter((row) => row.media_type === item.mediaType && (row.show_tmdb_id === item.id || row.show_tmdb_id === item.tmdbId))); })
      .catch(() => undefined);
    return () => { active = false; };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [item.id, item.mediaType, activeProfile?.id]);
  const resumePoints = useMemo<ResumePoint[]>(() => collectResumePoints(displayItem, { self: item, continueWatching, history }),
    [continueWatching, displayItem, history, item]);
  const watchedEpisode = useCallback((season: number, episode: number) => isWatched(displayItem, season, episode), [displayItem, isWatched]);
  const episodeProgress = useMemo(() => episodeProgressMap(resumePoints, watchedEpisode), [resumePoints, watchedEpisode]);

  // Series with nothing in progress: find the first unwatched aired episode.
  const [firstUnwatched, setFirstUnwatched] = useState<{ season: number; episode: number } | null>(null);
  const needsFirstUnwatched = isTv && !selectedEpisode && !resumePoints.some((point) => point.season != null);
  const regularSeasons = useMemo(() => (displayItem.seasons ?? []).map((season) => season.seasonNumber).filter((n) => n > 0).sort((a, b) => a - b), [displayItem.seasons]);
  useEffect(() => {
    if (!needsFirstUnwatched || !regularSeasons.length) return undefined;
    let active = true;
    void (async () => {
      for (const season of regularSeasons.slice(0, 4)) {
        const episodes = await loadSeasonEpisodes(displayItem.id, season, "en-US", priorityConfig, { tvdbId: displayItem.tvdbId, anilistId: displayItem.anilistId, isAnime: displayItem.isAnime }).catch(() => []);
        if (!active) return;
        const found = firstUnwatchedEpisode(episodes, watchedEpisode);
        if (found) { setFirstUnwatched({ season: found.seasonNumber, episode: found.episodeNumber }); return; }
      }
    })();
    return () => { active = false; };
  }, [displayItem.anilistId, displayItem.id, displayItem.isAnime, displayItem.tvdbId, needsFirstUnwatched, priorityConfig, regularSeasons, watchedEpisode]);

  const plan = planPrimaryAction({
    item: displayItem,
    points: resumePoints,
    selectedEpisode,
    firstUnwatched,
    isWatched: (season, episode) => season == null ? isWatched(displayItem) : isWatched(displayItem, season, episode)
  });

  // Real IMDb rating for the title (Cinemeta by imdb id), matching the Android
  // app. Episode rows get theirs from Agregarr (see getSeasonEpisodeRatings).
  const [detailImdbRating, setDetailImdbRating] = useState<string | null>(null);
  const [externalRatings, setExternalRatings] = useState<MdbExternalRating[]>([]);
  useEffect(() => {
    setDetailImdbRating(null);
    let active = true;
    const imdbId = displayItem.imdbId;
    if (!imdbId) return () => { active = false; };
    void getImdbRating(displayItem.mediaType, imdbId).then((rating) => {
      if (active && rating) setDetailImdbRating(rating);
    }).catch(() => undefined);
    return () => { active = false; };
  }, [displayItem.imdbId, displayItem.mediaType]);
  useEffect(() => {
    setExternalRatings([]);
    let active = true;
    if (!mdblistConnected || !mdblistClient.isConnected) return () => { active = false; };
    void mdblistClient.externalRatings(displayItem.mediaType, displayItem.id).then((ratings) => {
      if (active) setExternalRatings(ratings);
    }).catch(() => undefined);
    return () => { active = false; };
  }, [displayItem.id, displayItem.mediaType, mdblistConnected]);
  const detailMeta = buildDetailMeta(displayItem, settings.showBudget);
  // Clearlogos (bundled from the app, no background tiles). Providers we have
  // no clearlogo for are skipped rather than shown as white TMDB tiles.
  const serviceLogos = [...(displayItem.providerLogos ?? []), ...(displayItem.networkLogos ?? [])]
    .map((logo) => ({ name: canonicalServiceName(logo.name), logo: serviceClearLogo(logo.name) }))
    .filter((logo): logo is { name: string; logo: string } => Boolean(logo.logo))
    .filter((logo, index, arr) => arr.findIndex((candidate) => candidate.name === logo.name) === index)
    .slice(0, 4);

  const requestAutoplay = (request: AutoplayRequest) => {
    // Explore mode: ask for Premium before waiting on sources.
    if (!premiumAllows("play", () => setAutoplayRequest(request))) return;
    setAutoplayRequest(request);
  };

  const openEpisodeSources = async (season: number, episode: number, resumeSeconds?: number) => {
    if (!premiumAllows("play", () => { void openEpisodeSources(season, episode, resumeSeconds); })) return;
    const progress = episodeProgress.get(episodeKey(season, episode));
    const resume = resumeSeconds ?? (progress ? progress.positionSeconds || Math.round(progress.durationSeconds * progress.percent / 100) : 0);
    setAutoplayRequest(null);
    setStarting(true);
    await loadEpisodeStreams(displayItem, season, episode);
    setStarting(false);
    setAutoplayRequest({ resumeSeconds: resume });
  };

  const playPrimary = (fromStart = false) => {
    if (autoplayRequest || starting) return;
    const resumeSeconds = fromStart ? 0 : plan.resumeSeconds;
    if (!isTv) { requestAutoplay({ resumeSeconds }); return; }
    if (plan.season == null || plan.episode == null) {
      episodesRef.current?.scrollIntoView({ behavior: "smooth", block: "start" });
      episodesRef.current?.querySelector<HTMLElement>(".episode-row")?.focus({ preventScroll: true });
      return;
    }
    if (selectedEpisode?.season === plan.season && selectedEpisode?.episode === plan.episode) requestAutoplay({ resumeSeconds });
    else void openEpisodeSources(plan.season, plan.episode, resumeSeconds);
  };

  // Home billboard "Play": start playback here through the same automatic
  // source choice as the Play button. Movies start at once (the chooser waits
  // for sources); series wait briefly for the resume / next episode, else play
  // the episode the billboard named, or S1 E1.
  const [heroPlayIntent] = useState(() => peekPlayOnOpen(item));
  const heroPlayDone = useRef(false);
  useEffect(() => {
    if (!heroPlayIntent || heroPlayDone.current) return undefined;
    const start = (run: () => void) => { heroPlayDone.current = true; clearPlayOnOpen(); run(); };
    if (!isTv) { start(() => playPrimary(false)); return undefined; }
    if (plan.season != null && plan.episode != null) { start(() => playPrimary(false)); return undefined; }
    const timer = window.setTimeout(() => {
      if (heroPlayDone.current) return;
      start(() => { void openEpisodeSources(heroPlayIntent.season ?? 1, heroPlayIntent.episode ?? 1); });
    }, 4000);
    return () => window.clearTimeout(timer);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [heroPlayIntent, isTv, plan.season, plan.episode]);

  const markWatched = async () => {
    const season = selectedEpisode?.season ?? item.seasonNumber ?? null;
    const episode = selectedEpisode?.episode ?? item.episodeNumber ?? null;
    const alreadyWatched = detailWatched;
    // Update the badge + Continue Watching instantly, before Trakt round-trips.
    markWatchedLocally({ mediaType: displayItem.mediaType, id: displayItem.id, season, episode }, !alreadyWatched);
    let saved = false;
    try {
      if (authClient.session) {
        await saveWatchedState(authClient, {
          id: displayItem.id,
          mediaType: displayItem.mediaType,
          seasonNumber: season,
          episodeNumber: episode
        }, !alreadyWatched, activeProfile?.id ?? null);
        saved = true;
      }
      if (syncClient().isConnected) {
        // Register in the connected provider's history so the watched badge syncs
        // everywhere (parity with the app). Toggle removes it from history.
        const ref = { mediaType: displayItem.mediaType, tmdbId: displayItem.id, season, episode };
        if (alreadyWatched) await syncClient().removeFromHistory(ref);
        else await syncClient().addToHistory(ref);
        saved = true;
      }
      setToast(saved ? (alreadyWatched ? "Removed from watched." : "Marked as watched.") : "Connect ARVIO Cloud, Trakt or MDBList to sync watched state.");
      if (saved) void refreshData();
    } catch (error) {
      setToast(error instanceof Error ? error.message : "Could not update watched state.");
    }
  };

  const openPerson = async (castMember: PersonCredit) => {
    setPersonVisible(true);
    setPersonLoading(true);
    setPerson(null);
    try {
      const details = await getPersonDetails(castMember.id);
      if (!details) {
        setToast("Could not load cast details.");
        setPersonVisible(false);
        return;
      }
      setPerson(details);
    } catch (error) {
      setToast(error instanceof Error ? error.message : "Could not load cast details.");
      setPersonVisible(false);
    } finally {
      setPersonLoading(false);
    }
  };

  const simklIds = simklClient.findItemIds(displayItem.id, displayItem.mediaType === "movie" ? "movie" : "tv")
    ?? (displayItem as unknown as { ids?: Parameters<typeof getSimklItemUrl>[0] })?.ids;
  const simklUrl = getSimklItemUrl(simklIds, displayItem.mediaType === "movie" ? "movie" : "tv")
    ?? simklRedirectUrl(displayItem);
  const busyPlaying = Boolean(autoplayRequest) || starting;
  const playLabel = busyPlaying
    // Finding sources → checking the best ones → opening the player.
    ? (busy === "Finding sources" && streams.length === 0 ? translateUi("Finding sources…")
      : autoplayRequest ? translateUi("Picking the best source…") : translateUi("Starting…"))
    : translateUi(plan.label, plan.labelValues);

  return (
    <article className="details-drawer" role="dialog" aria-modal="true" aria-labelledby={headingId} ref={rootRef}>
      <div className="detail-backdrop" style={{ backgroundImage: displayItem.backdrop ? `url(${displayItem.backdrop})` : undefined }} />
      <button type="button" className="detail-back" onClick={close} aria-label={translateUi("Back")}>
        <ArrowLeft size={22} />
      </button>
      <div className="detail-body">
        <section className="detail-hero-content">
          <h1 className="detail-title-line" id={headingId}>
            {logo ? <img className="detail-clearlogo" src={logo} alt={displayItem.title} /> : <span className="detail-title-text">{displayItem.title}</span>}
          </h1>
          <div className="detail-rating-line">
            {detailImdbRating ? (
              <span className="imdb-lockup">
                <img src={IMDB_LOGO} alt="IMDb" />
                <b>{detailImdbRating}</b>
              </span>
            ) : null}
            {detailWatched && <span className="detail-watched-chip"><BadgeCheck size={13} /> {translateUi(" Watched")}</span>}
            {displayItem.genres?.slice(0, 3).map((genre) => <span key={genre}>{genre}</span>)}
          </div>
          {externalRatings.length ? (
            <div className="detail-external-ratings" aria-label={translateUi("MDBList external ratings")}>
              {externalRatings.map((rating) => (
                <span key={rating.source}><small>{translateUi(rating.label)}</small><b>{rating.value}</b></span>
              ))}
            </div>
          ) : null}
          <div className="chips detail-metadata">
            {detailMeta.map((meta) => <span key={meta}>{meta}</span>)}
            {streams.length > 0 && <span>{streams.length} {translateUi(" sources")}</span>}
          </div>
          <p className="detail-overview">{displayItem.overview || translateUi("No overview available.")}</p>
          {serviceLogos.length ? (
            <div className="detail-service-logos" aria-label={translateUi("Streaming and network availability")}>
              {serviceLogos.map((service) => (
                <span key={service.name} title={service.name}>
                  <img src={service.logo} alt={service.name} />
                </span>
              ))}
            </div>
          ) : null}
          <div className="detail-actions">
            <button type="button" ref={playButtonRef} className="primary detail-play" onClick={() => playPrimary(false)} aria-busy={busyPlaying || undefined}>
              {busyPlaying ? <Loader2 size={18} className="detail-spin" /> : <Play size={18} fill="currentColor" />} {playLabel}
            </button>
            {plan.kind === "resume" && !busyPlaying ? (
              <button type="button" className="secondary text-button" onClick={() => playPrimary(true)}>
                <RotateCcw size={18} /> {translateUi(plan.startOverLabel)}
              </button>
            ) : null}
            <button type="button" className="secondary text-button" onClick={() => setSourcePickerVisible(true)}><Folder size={18} /> {translateUi("Sources")}</button>
            {inWatchlist ? (
              <button type="button" className="secondary text-button" onClick={() => void toggleWatchlist(displayItem)}><Trash2 size={18} /> {translateUi(" Remove")}</button>
            ) : (
              <button type="button" className="secondary text-button" onClick={() => void toggleWatchlist(displayItem)}><Bookmark size={18} /> {translateUi(" Watchlist")}</button>
            )}
            <button type="button" className={`secondary text-button ${detailWatched ? "is-active" : ""}`} onClick={() => void markWatched()}><BadgeCheck size={18} /> {detailWatched ? translateUi("Watched") : translateUi("Mark Watched")}</button>
            {displayItem.trailerUrl && (
              <button type="button" className="secondary text-button detail-trailer" onClick={() => void playTrailer(displayItem)}>
                <Play size={18} fill="currentColor" /> {translateUi("Trailer")}</button>
            )}
          </div>
          <div className="detail-links">
            <a href={simklUrl} target="_blank" rel="noopener noreferrer" className="detail-link">{translateUi("View on Simkl")}</a>
            <a href={traktPage ?? traktLinkUrl(displayItem, relayTraktLink)} target="_blank" rel="noopener noreferrer" className="detail-link">{translateUi("View on Trakt")}</a>
          </div>
          {!streams.length && !isTv && busy !== "Finding sources" && (
            <p className="detail-action-hint">{translateUi("Sources will appear here when an installed addon returns results.")}</p>
          )}
        </section>

        <div className="detail-layout">
          {isTv ? (
            <SeasonEpisodes
              sectionRef={episodesRef}
              item={displayItem}
              loadingDetails={detailsLoading}
              selectedEpisode={selectedEpisode}
              preferredSeason={plan.season}
              isWatched={isWatched}
              progress={episodeProgress}
              onPlayEpisode={(s, e) => void openEpisodeSources(s, e)}
            />
          ) : null}

          {displayItem.cast?.length ? (
            <section className="detail-section detail-wide">
              <h2 className="detail-section-title">{translateUi("Cast")}</h2>
              <RailScroller className="mini-strip" ariaLabel={translateUi("cast")}>
                {displayItem.cast.map((person) => (
                  <button type="button" className="mini-card person cast-card" key={person.id} onClick={() => void openPerson(person)}>
                    {person.image ? <img src={person.image} alt="" loading="lazy" /> : <UserCircle size={30} />}
                    <strong>{person.name}</strong>
                    <span>{person.character || translateUi("Cast")}</span>
                  </button>
                ))}
              </RailScroller>
            </section>
          ) : null}

          {displayItem.related?.length ? (
            <section className="detail-section related detail-wide">
              <h2 className="detail-section-title">{translateUi("More Like This")}</h2>
              {/* Cards fetch logos, providers and IMDb ratings on mount: only
                  mount them when the row is about to be seen. */}
              <LazyMount minHeight={240}>
                <RailScroller className="rail-strip compact" ariaLabel={translateUi("more like this")}>
                  {displayItem.related.map((related) => (
                    <MediaCard
                      key={`related-${related.mediaType}-${related.id}`}
                      item={related}
                      onOpen={openDetails}
                      posterMode={settings.cardLayoutMode === "poster"}
                    />
                  ))}
                </RailScroller>
              </LazyMount>
            </section>
          ) : null}

          {reviews.length > 0 && <ReviewsSection reviews={reviews} />}
        </div>
      </div>
      <PersonModal
        visible={personVisible}
        loading={personLoading}
        person={person}
        onClose={() => setPersonVisible(false)}
        onOpenMedia={(media) => {
          setPersonVisible(false);
          void openDetails(media);
        }}
        posterMode={settings.cardLayoutMode === "poster"}
      />
      <SourcePickerModal
        visible={sourcePickerVisible}
        item={displayItem}
        streams={streams}
        installedAddons={installedAddons}
        selectedEpisode={selectedEpisode}
        activeProfileId={activeProfile?.id ?? null}
        onClose={() => setSourcePickerVisible(false)}
        onToast={setToast}
        loading={busy === "Finding sources"}
      />
    </article>
  );
}

const REVIEW_LIMIT = 3;

function ReviewsSection({ reviews }: { reviews: ReviewInfo[] }) {
  const translateUi = useTranslation();
  const [expanded, setExpanded] = useState<Set<string>>(() => new Set());
  const [showAll, setShowAll] = useState(false);
  const visible = showAll ? reviews : reviews.slice(0, REVIEW_LIMIT);
  return (
    <section className="detail-section detail-wide detail-reviews">
      <h2 className="detail-section-title">{translateUi("Reviews")}</h2>
      <div className="review-list">
        {visible.map((review) => {
          const text = cleanReviewText(review.content);
          const open = expanded.has(review.id);
          const long = text.length > 320;
          return (
            <article className="review-card" key={review.id}>
              <div className="review-head">
                {review.avatar ? <img src={review.avatar} alt="" loading="lazy" /> : <UserCircle size={26} />}
                <strong>{review.author}</strong>
                {review.rating != null && <span className="review-rating"><Star size={13} fill="currentColor" /> {review.rating}</span>}
                {review.createdAt ? <time className="review-date" dateTime={review.createdAt}>{formatDate(review.createdAt)}</time> : null}
              </div>
              <p className={`review-text ${open || !long ? "is-open" : ""}`}>{text}</p>
              {long ? (
                <button type="button" className="review-more" aria-expanded={open} onClick={() => setExpanded((current) => {
                  const next = new Set(current);
                  if (next.has(review.id)) next.delete(review.id); else next.add(review.id);
                  return next;
                })}>
                  {open ? translateUi("Show less") : translateUi("Read more")}
                </button>
              ) : null}
            </article>
          );
        })}
      </div>
      {reviews.length > REVIEW_LIMIT ? (
        <button type="button" className="secondary text-button review-all" onClick={() => setShowAll((value) => !value)}>
          {showAll ? translateUi("Show fewer reviews") : translateUi("All {value0} reviews", { value0: reviews.length })}
        </button>
      ) : null}
    </section>
  );
}

function PersonModal({
  visible,
  loading,
  person,
  onClose,
  onOpenMedia,
  posterMode
}: {
  visible: boolean;
  loading: boolean;
  person: PersonDetails | null;
  onClose: () => void;
  onOpenMedia: (item: MediaItem) => void;
  posterMode: boolean;
}) {
  const translateUi = useTranslation();
  const layerRef = useRef<HTMLElement | null>(null);
  useEffect(() => {
    if (!visible) return undefined;
    const opener = document.activeElement instanceof HTMLElement ? document.activeElement : null;
    const timer = window.setTimeout(() => layerRef.current?.querySelector<HTMLElement>(".person-close")?.focus(), 0);
    const onKey = (event: KeyboardEvent) => {
      if (event.key === "Escape" && claimEscape(event) && isTopmostLayer(layerRef.current)) onClose();
    };
    window.addEventListener("keydown", onKey);
    return () => {
      window.clearTimeout(timer);
      window.removeEventListener("keydown", onKey);
      if (opener?.isConnected) window.setTimeout(() => opener.focus({ preventScroll: true }), 0);
    };
  }, [onClose, visible]);

  if (!visible) return null;
  if (typeof document === "undefined") return null;
  return createPortal(
    <section className="person-modal" role="dialog" aria-modal="true" aria-label={person?.name ?? translateUi("Cast details")} ref={layerRef}>
      <div className="person-modal-bg" onClick={onClose} />
      <div className="person-panel">
        <button type="button" className="person-close" onClick={onClose} aria-label={translateUi("Close cast details")}><X size={24} /></button>
        {loading ? (
          <div className="person-loading">{translateUi("Loading cast details...")}</div>
        ) : person ? (
          <>
            <aside className="person-sidebar">
              <div className="person-photo">
                {person.profilePath ? <img src={person.profilePath} alt="" /> : <UserCircle size={74} />}
              </div>
              <h2>{person.name}</h2>
              <div className="person-facts">
                {person.birthday ? <span><CalendarDays size={15} /> {formatDate(person.birthday)}</span> : null}
                {person.placeOfBirth ? <span><MapPin size={15} /> {person.placeOfBirth}</span> : null}
              </div>
            </aside>
            <div className="person-content">
              {person.biography ? (
                <section>
                  <p className="eyebrow">{translateUi("Biography")}</p>
                  <p className="person-bio">{person.biography}</p>
                </section>
              ) : null}
              {person.knownFor.length ? (
                <section>
                  <p className="eyebrow">{translateUi("Known For")}</p>
                  <RailScroller className={`person-known-rail ${posterMode ? "is-poster" : ""}`} ariaLabel={translateUi("{value0} known for", {value0:person.name})}>
                    {person.knownFor.map((item) => (
                      <button type="button" className="person-known-card" key={`${item.mediaType}-${item.id}`} onClick={() => onOpenMedia(item)}>
                        <div>{item.backdrop || item.image ? <img src={item.backdrop || item.image} alt="" /> : <Clapperboard size={32} />}</div>
                        <strong>{item.title}</strong>
                        <span>{item.subtitle || item.year || (item.mediaType === "tv" ? "Series" : "Movie")}</span>
                      </button>
                    ))}
                  </RailScroller>
                </section>
              ) : null}
            </div>
          </>
        ) : null}
      </div>
    </section>,
    document.body
  );
}

function SeasonEpisodes({ sectionRef, item, loadingDetails, selectedEpisode, preferredSeason, isWatched, progress, onPlayEpisode }: {
  sectionRef: React.RefObject<HTMLElement | null>;
  item: MediaItem;
  loadingDetails: boolean;
  selectedEpisode: { season: number; episode: number } | null;
  preferredSeason: number | null;
  isWatched: (item: MediaItem, seasonNumber?: number | null, episodeNumber?: number | null) => boolean;
  progress: Map<string, ResumePoint>;
  onPlayEpisode: (season: number, episode: number) => void;
}) {
  const translateUi = useTranslation();
  const { openContextMenu, setToast, settings, toggleWatched } = useAppPick("openContextMenu", "setToast", "settings", "toggleWatched");
  const narrow = useCompactSources();
  const seasons = (item.seasons ?? []).filter(entry => entry.seasonNumber > 0 || settings.includeSpecials || item.seasonNumber === 0);
  const [season, setSeason] = useState(selectedEpisode?.season ?? item.seasonNumber ?? preferredSeason ?? seasons[0]?.seasonNumber ?? 1);
  const userPicked = useRef(false);
  const [episodes, setEpisodes] = useState<EpisodeInfo[]>([]);
  const [loading, setLoading] = useState(false);
  const [retryNonce, setRetryNonce] = useState(0);
  const priorityConfig = useMemo(() => getPriorityConfig(settings), [settings]);
  const metadataContext = useMemo(() => ({
    tvdbId: item.tvdbId,
    anilistId: item.anilistId,
    isAnime: item.isAnime
  }), [item.anilistId, item.isAnime, item.tvdbId]);

  // Follow the resume/next episode's season until the viewer picks one.
  useEffect(() => {
    if (userPicked.current) return;
    const target = selectedEpisode?.season ?? preferredSeason;
    if (target != null && seasons.some((entry) => entry.seasonNumber === target)) setSeason(target);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [preferredSeason, selectedEpisode?.season, seasons.length]);

  useEffect(() => {
    if (seasons.length && !seasons.some((entry) => entry.seasonNumber === season)) {
      setSeason(seasons[0]?.seasonNumber ?? 1);
    }
  }, [season, seasons]);

  useEffect(() => {
    let active = true;
    setLoading(true);
    void loadSeasonEpisodes(item.id, season, "en-US", priorityConfig, metadataContext)
      .then((eps) => { if (active) setEpisodes(eps); })
      .catch(() => undefined)
      .finally(() => active && setLoading(false));
    return () => { active = false; };
  }, [item.id, metadataContext, priorityConfig, retryNonce, season]);

  const pickSeason = (value: number) => {
    userPicked.current = true;
    setSeason(value);
  };

  const updateSeasonWatched = async (seasonNum: number, watched: boolean) => {
    try {
      const targetEpisodes = await loadSeasonEpisodes(item.id, seasonNum, "en-US", priorityConfig, metadataContext);
      const changedEpisodes = targetEpisodes.filter(ep => isWatched(item, seasonNum, ep.episodeNumber) !== watched);
      await syncSeasonWatched({
        mediaType: "tv",
        tmdbId: item.id,
        isAnime: item.originalLanguage === "ja" && Boolean(item.genreIds?.includes(16))
      }, seasonNum, changedEpisodes.map(ep => ep.episodeNumber), watched);
      for (const ep of targetEpisodes) {
        if (isWatched(item, seasonNum, ep.episodeNumber) !== watched) {
          await toggleWatched(item, seasonNum, ep.episodeNumber, true);
        }
      }
      setToast(`Season ${seasonNum} marked as ${watched ? "watched" : "unwatched"}.`);
    } catch {
      setToast(`Could not update Season ${seasonNum}. Please try again.`);
    }
  };

  const anchorPosition = (anchor: HTMLElement | { x: number; y: number }) => {
    if (!(anchor instanceof HTMLElement)) return anchor;
    const rect = anchor.getBoundingClientRect();
    return { x: Math.max(16, rect.right - 300), y: rect.bottom + 6 };
  };

  const openSeasonMenu = (anchor: HTMLElement | { x: number; y: number }, seasonNum: number, seasonName: string) => {
    openContextMenu({
      title: seasonName || `Season ${seasonNum}`,
      subtitle: item.title,
      position: anchorPosition(anchor),
      actions: [
        { id: "mark_season_watched", label: "Mark Season Watched", icon: <Check size={18} />, action: async () => { await updateSeasonWatched(seasonNum, true); } },
        { id: "mark_season_unwatched", label: "Mark Season Unwatched", icon: <EyeOff size={18} />, action: async () => { await updateSeasonWatched(seasonNum, false); } }
      ]
    });
  };

  const openEpisodeMenu = (anchor: HTMLElement | { x: number; y: number }, ep: EpisodeInfo) => {
    const watched = isWatched(item, season, ep.episodeNumber);
    openContextMenu({
      title: ep.name || `Episode ${ep.episodeNumber}`,
      subtitle: `${item.title} - S${season} E${ep.episodeNumber}`,
      position: anchorPosition(anchor),
      actions: [
        { id: "play_episode", label: "Play Episode", icon: <Play size={18} fill="currentColor" />, action: () => { onPlayEpisode(season, ep.episodeNumber); } },
        { id: "toggle_episode_watched", label: watched ? "Mark as Unwatched" : "Mark as Watched", icon: watched ? <EyeOff size={18} /> : <Check size={18} />, action: () => { void toggleWatched(item, season, ep.episodeNumber); } }
      ]
    });
  };

  const currentSeason = seasons.find((entry) => entry.seasonNumber === season);
  const seasonLabel = (entry: { seasonNumber: number; name?: string }) => entry.name || translateUi("Season {value0}", { value0: entry.seasonNumber });
  const cards = !loading ? episodes.map((episode) => {
    const active = (selectedEpisode?.season ?? item.seasonNumber) === season &&
      (selectedEpisode?.episode ?? item.episodeNumber) === episode.episodeNumber;
    const watched = isWatched(item, season, episode.episodeNumber);
    return (
      <EpisodeCard
        key={episode.id}
        episode={episode}
        active={active}
        watched={watched}
        progress={progress.get(episodeKey(season, episode.episodeNumber))?.percent ?? null}
        onPlay={() => onPlayEpisode(season, episode.episodeNumber)}
        onContextMenu={(event) => { event.preventDefault(); event.stopPropagation(); openEpisodeMenu({ x: event.clientX, y: event.clientY }, episode); }}
        onMenu={(anchor) => openEpisodeMenu(anchor, episode)}
      />
    );
  }) : [];
  const status = (
    <>
      {(loading || loadingDetails) && <p className="empty">{translateUi("Loading episodes...")}</p>}
      {!loading && !loadingDetails && !episodes.length ? (
        <p className="empty">
          {translateUi("No episodes found.")}{" "}
          <button type="button" className="episode-retry" onClick={() => setRetryNonce((n) => n + 1)}>{translateUi("Retry")}</button>
        </p>
      ) : null}
    </>
  );

  return (
    <section className={`detail-section episodes-section detail-wide ${narrow ? "is-narrow" : ""}`} ref={sectionRef}>
      <div className="episodes-head">
        <h2 className="detail-section-title">{translateUi("Episodes")}</h2>
        <div className="season-picker">
          {narrow || seasons.length > 8 ? (
            <label className="season-select">
              <span className="sr-only">{translateUi("Season")}</span>
              <select value={season} onChange={(event) => pickSeason(Number(event.target.value))} aria-label={translateUi("Season")}>
                {seasons.map((entry) => <option key={entry.id} value={entry.seasonNumber}>{seasonLabel(entry)}</option>)}
              </select>
              <ChevronDown size={16} aria-hidden="true" />
            </label>
          ) : (
            <div className="season-tabs" role="group" aria-label={translateUi("Seasons")}>
              {seasons.map((s) => (
                <button
                  type="button"
                  key={s.id}
                  className={`season-tab ${s.seasonNumber === season ? "is-active" : ""}`}
                  aria-pressed={s.seasonNumber === season}
                  onClick={() => pickSeason(s.seasonNumber)}
                  onContextMenu={(e) => { e.preventDefault(); e.stopPropagation(); openSeasonMenu({ x: e.clientX, y: e.clientY }, s.seasonNumber, seasonLabel(s)); }}
                >
                  {seasonLabel(s)}
                </button>
              ))}
              {!seasons.length && loadingDetails ? <span className="season-tab is-loading">{translateUi("Loading seasons...")}</span> : null}
            </div>
          )}
          {seasons.length ? (
            <button
              type="button"
              className="season-menu-button"
              aria-haspopup="dialog"
              aria-label={translateUi("Options for {value0}", { value0: currentSeason ? seasonLabel(currentSeason) : translateUi("Season {value0}", { value0: season }) })}
              onClick={(event) => openSeasonMenu(event.currentTarget, season, currentSeason ? seasonLabel(currentSeason) : "")}
            >
              <MoreHorizontal size={18} />
            </button>
          ) : null}
        </div>
      </div>
      {narrow ? (
        <div className="episode-list episode-list-vertical" aria-label={translateUi("season {value0} episodes", { value0: season })}>
          {status}
          {cards}
        </div>
      ) : (
        <RailScroller className="episode-list" ariaLabel={translateUi("season {value0} episodes", { value0: season })}>
          {status}
          {cards}
        </RailScroller>
      )}
    </section>
  );
}
