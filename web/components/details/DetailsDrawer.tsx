"use client";

import { BadgeCheck, Bookmark, CalendarDays, Check, Clapperboard, Copy, Download, ExternalLink, EyeOff, Filter, Info, MapPin, Play, Search, Star, Trash2, TriangleAlert, UserCircle, X } from "lucide-react";
import { useEffect, useMemo, useState, useSyncExternalStore } from "react";
import { createPortal } from "react-dom";
import { MediaCard } from "@/components/media/MediaCard";
import { RailScroller } from "@/components/media/RailScroller";
import { config } from "@/lib/config";
import { trackPremiumEvent } from "@/lib/premiumAnalytics";
import { createPendingExternalPlayback } from "@/lib/externalPlayback";
import { saveWatchedState } from "@/lib/cloud";
import { copyStreamUrl, downloadStreamUrl, downloadToVlc, externalLaunchMode, isAppleMobile, isDesktop, isLinux, isWindows, openExternalPlayer, openInAnyPlayer, setVlcProtocolReady, triggerDownload, vlcProtocolReady, VLC_SETUP_SH_URL, VLC_SETUP_URL } from "@/lib/externalPlayers";
import { fetchSubtitlesForItem } from "@/lib/addons";
import { cachedDebridDirectUrl, isUncachedDebridStream, parseDebridStream, prefetchDebridDirectUrl, resolveDebridDirectUrl } from "@/lib/debrid";
import { canonicalServiceName, IMDB_LOGO, serviceClearLogo } from "@/lib/serviceLogos";
import { getImdbRating } from "@/lib/imdbRatings";
import { mdblistClient, type MdbExternalRating } from "@/lib/mdblist";
import { sourcePickerScore } from "@/lib/sourceRank";
import { playbackCompatibilityRevision, playbackPlan, subscribePlaybackCompatibility } from "@/lib/streamCompatibility";
import { authClient, getPriorityConfig, useApp } from "@/lib/store";
import { simklClient, getSimklItemUrl } from "@/lib/simkl";
import { syncClient, syncSeasonWatched } from "@/lib/sync";
import { getDetails, getLogoUrl, getPersonDetails, getReviews, getSeasonEpisodes } from "@/lib/tmdb";
import type { EpisodeInfo, InstalledAddon, MediaItem, PersonCredit, PersonDetails, ReviewInfo, StreamSource, SubtitleTrack } from "@/lib/types";
import { sourcePlaybackPresentation } from "./sourcePlaybackPresentation";

export function DetailsDrawer() {
  const { selected: item } = useApp();
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

function DetailsView({ item }: { item: MediaItem }) {
  const { streams, selectedEpisode, activeProfile, addons: installedAddons, loadEpisodeStreams, openDetails, playTrailer, setToast, settings, watchlist, refreshData, busy, isWatched, markWatchedLocally, toggleWatchlist, mdblistConnected } = useApp();
  const [detailsItem, setDetailsItem] = useState<MediaItem>(item);
  const [detailsLoading, setDetailsLoading] = useState(false);
  const [reviews, setReviews] = useState<ReviewInfo[]>([]);
  const [person, setPerson] = useState<PersonDetails | null>(null);
  const [personLoading, setPersonLoading] = useState(false);
  const [personVisible, setPersonVisible] = useState(false);
  const [sourcePickerVisible, setSourcePickerVisible] = useState(false);
  const [logo, setLogo] = useState<string | null>(null);
  const displayItem = detailsItem ?? item;
  const priorityConfig = useMemo(() => getPriorityConfig(settings), [settings]);

  useEffect(() => {
    window.scrollTo({ top: 0, left: 0, behavior: "auto" });
  }, [item.id, item.mediaType]);

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
        const details = await getDetails(item, priorityConfig).catch(() => null);
        if (!active) return;
        if (details) setDetailsItem(details);
        if (details && looksHydrated(details)) break;
        if (attempt < 2) await new Promise((resolve) => setTimeout(resolve, 900 * (attempt + 1)));
      }
      if (active) setDetailsLoading(false);
    })();
    return () => { active = false; };
  }, [item, priorityConfig]);

  useEffect(() => {
    let active = true;
    void getReviews(item).then((r) => active && setReviews(r)).catch(() => undefined);
    return () => { active = false; };
  }, [item.id, item.mediaType]);

  useEffect(() => {
    let active = true;
    setLogo(null);
    void getLogoUrl({ mediaType: item.mediaType, id: item.id }).then((url) => {
      if (active) setLogo(url);
    }).catch(() => undefined);
    return () => { active = false; };
  }, [item.id, item.mediaType]);

  const isTv = displayItem.mediaType === "tv";
  const inWatchlist = watchlist.some((entry) => entry.mediaType === item.mediaType && entry.id === item.id);
  const canPlayBest = streams.length > 0;
  const detailWatched = isWatched(displayItem, selectedEpisode?.season, selectedEpisode?.episode);

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
  const continueLabel = buildContinueLabel(displayItem, selectedEpisode);
  const detailMeta = buildDetailMeta(displayItem, settings.showBudget);
  // Clearlogos (bundled from the app, no background tiles). Providers we have
  // no clearlogo for are skipped rather than shown as white TMDB tiles.
  const serviceLogos = [...(displayItem.providerLogos ?? []), ...(displayItem.networkLogos ?? [])]
    .map((logo) => ({ name: canonicalServiceName(logo.name), logo: serviceClearLogo(logo.name) }))
    .filter((logo): logo is { name: string; logo: string } => Boolean(logo.logo))
    .filter((logo, index, arr) => arr.findIndex((candidate) => candidate.name === logo.name) === index)
    .slice(0, 4);

  const playBest = () => {
    if (isTv && !selectedEpisode) {
      setToast(isTv ? "Pick an episode to find sources first." : "No sources found yet.");
      return;
    }
    setSourcePickerVisible(true);
  };

  const openEpisodeSources = async (season: number, episode: number) => {
    setSourcePickerVisible(true);
    const found = await loadEpisodeStreams(displayItem, season, episode);
    if (!found.length) setToast("No sources found for this episode yet.");
  };

  const addToWatchlist = async () => {
    if (!syncClient().isConnected) {
      setToast("Connect Trakt, Simkl, or MDBList in Settings to use Watchlist.");
      return;
    }
    try {
      await syncClient().addToWatchlist({ mediaType: item.mediaType, tmdbId: item.id });
      setToast("Added to watchlist.");
      void refreshData();
    } catch (error) {
      setToast(error instanceof Error ? error.message : "Could not add to watchlist.");
    }
  };

  const removeFromWatchlist = async () => {
    if (!syncClient().isConnected) {
      setToast("Connect Trakt, Simkl, or MDBList in Settings to remove watchlist items.");
      return;
    }
    try {
      await syncClient().removeFromWatchlist({ mediaType: item.mediaType, tmdbId: item.id });
      setToast("Removed from watchlist.");
      void refreshData();
    } catch (error) {
      setToast(error instanceof Error ? error.message : "Could not remove watchlist item.");
    }
  };

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

  return (
    <article className="details-drawer">
      <div className="detail-backdrop" style={{ backgroundImage: displayItem.backdrop ? `url(${displayItem.backdrop})` : undefined }} />
      <div className="detail-body">
        <section className="detail-hero-content">
          <div className="detail-title-line">
            {logo ? <img className="detail-clearlogo" src={logo} alt={displayItem.title} /> : <h2>{displayItem.title}</h2>}
          </div>
          <div className="detail-rating-line">
            {detailImdbRating ? (
              <span className="imdb-lockup">
                <img src={IMDB_LOGO} alt="IMDb" />
                <b>{detailImdbRating}</b>
              </span>
            ) : null}
            {detailWatched && <span className="detail-watched-chip"><BadgeCheck size={13} /> Watched</span>}
            {simklClient.isConnected && (
              <a
                href={getSimklItemUrl((displayItem as unknown as { ids?: any })?.ids, displayItem.mediaType === "movie" ? "movie" : "tv") ?? `https://simkl.com/search?q=${encodeURIComponent(displayItem.title)}`}
                target="_blank"
                rel="noopener noreferrer"
                className="simkl-lockup text-xs font-semibold px-1.5 py-0.5 rounded bg-surface-sunk hover:underline inline-flex items-center gap-1"
                title="View on Simkl"
              >
                <span>Simkl</span>
              </a>
            )}
            {displayItem.genres?.slice(0, 3).map((genre) => <span key={genre}>{genre}</span>)}
          </div>
          {externalRatings.length ? (
            <div className="detail-external-ratings" aria-label="MDBList external ratings">
              {externalRatings.map((rating) => (
                <span key={rating.source}><small>{rating.label}</small><b>{rating.value}</b></span>
              ))}
            </div>
          ) : null}
          <div className="chips detail-metadata">
            {detailMeta.map((meta) => <span key={meta}>{meta}</span>)}
            {streams.length > 0 && <span>{streams.length} sources</span>}
          </div>
          <p className="detail-overview">{displayItem.overview || "No overview available."}</p>
          {serviceLogos.length ? (
            <div className="detail-service-logos" aria-label="Streaming and network availability">
              {serviceLogos.map((service) => (
                <span key={service.name} title={service.name}>
                  <img src={service.logo} alt={service.name} />
                </span>
              ))}
            </div>
          ) : null}
          <div className="detail-actions">
            <button type="button" className="primary" onClick={playBest}>
              <Play size={18} fill="currentColor" /> {continueLabel}
            </button>
            {inWatchlist ? (
              <button type="button" className="secondary text-button" onClick={() => void toggleWatchlist(displayItem)}><Trash2 size={18} /> Remove</button>
            ) : (
              <button type="button" className="secondary text-button" onClick={() => void toggleWatchlist(displayItem)}><Bookmark size={18} /> Watchlist</button>
            )}
            <button type="button" className={`secondary text-button ${detailWatched ? "is-active" : ""}`} onClick={() => void markWatched()}><BadgeCheck size={18} /> {detailWatched ? "Watched" : "Mark Watched"}</button>
            {displayItem.trailerUrl && (
              <button type="button" className="secondary text-button" onClick={() => void playTrailer(displayItem)}>
                <Play size={18} fill="currentColor" /> Trailer
              </button>
            )}
          </div>
          {!canPlayBest && !isTv && (
            <p className="detail-action-hint">
              {streams.length ? "The installed addons returned sources, but none have a direct browser-playable URL yet." : "Sources will appear here when an installed addon returns results."}
            </p>
          )}
        </section>

        <div className="detail-layout">
          {isTv ? (
            <SeasonEpisodes item={displayItem} loadingDetails={detailsLoading} selectedEpisode={selectedEpisode} isWatched={isWatched} onPlayEpisode={(s, e) => void openEpisodeSources(s, e)} />
          ) : null}

          {displayItem.cast?.length ? (
            <section className="detail-section detail-wide">
              <h3>Cast</h3>
              <RailScroller className="mini-strip" ariaLabel="cast">
                {displayItem.cast.map((person) => (
                  <button type="button" className="mini-card person cast-card" key={person.id} onClick={() => void openPerson(person)}>
                    {person.image ? <img src={person.image} alt="" /> : <UserCircle size={30} />}
                    <strong>{person.name}</strong>
                    <span>{person.character || "Cast"}</span>
                  </button>
                ))}
              </RailScroller>
            </section>
          ) : null}

          {reviews.length > 0 && (
            <section className="detail-section detail-wide">
              <h3>Reviews</h3>
              <div className="review-list">
                {reviews.map((review) => (
                  <article className="review-card" key={review.id}>
                    <div className="review-head">
                      {review.avatar ? <img src={review.avatar} alt="" /> : <UserCircle size={26} />}
                      <strong>{review.author}</strong>
                      {review.rating != null && <span className="review-rating"><Star size={13} fill="currentColor" /> {review.rating}</span>}
                    </div>
                    <p>{review.content.length > 600 ? `${review.content.slice(0, 600)}...` : review.content}</p>
                  </article>
                ))}
              </div>
            </section>
          )}

          {displayItem.related?.length ? (
            <section className="detail-section related detail-wide">
              <h3>More Like This</h3>
              <RailScroller className="rail-strip compact" ariaLabel="more like this">
                {displayItem.related.map((related) => (
                  <MediaCard
                    key={`related-${related.mediaType}-${related.id}`}
                    item={related}
                    onOpen={openDetails}
                    posterMode={settings.cardLayoutMode === "poster"}
                  />
                ))}
              </RailScroller>
            </section>
          ) : null}
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

function SourcePickerModal({
  visible,
  item,
  streams,
  installedAddons,
  selectedEpisode,
  activeProfileId,
  onClose,
  onToast,
  loading
}: {
  visible: boolean;
  item: MediaItem;
  streams: StreamSource[];
  installedAddons: InstalledAddon[];
  selectedEpisode: { season: number; episode: number } | null;
  activeProfileId: string | null;
  onClose: () => void;
  onToast: (message: string) => void;
  loading: boolean;
}) {
  const { settings, playStream } = useApp();
  // Recompute visible plans after selected-source failures without probing the result list.
  useSyncExternalStore(subscribePlaybackCompatibility, playbackCompatibilityRevision, () => 0);
  const [addonFilter, setAddonFilter] = useState("all");
  const [query, setQuery] = useState("");
  // Windows-only: offer the one-time vlc:// setup so "Open in VLC" launches VLC
  // directly instead of downloading a .m3u. Hidden once the user has set it up.
  // macOS is excluded — VLC self-registers vlc:// there, so no installer is
  // needed (and the .bat wouldn't run on a Mac anyway).
  const [vlcReady, setVlcReady] = useState<boolean>(() => vlcProtocolReady());
  const showVlcSetup = (isWindows() || isLinux()) && !vlcReady;
  const enableVlcProtocol = () => {
    // Download the tiny installer script for the platform, then remember the user set it up
    if (isLinux()) {
      triggerDownload(VLC_SETUP_SH_URL, "vlc-setup.sh");
      setVlcProtocolReady(true);
      setVlcReady(true);
      onToast("Run `bash vlc-setup.sh` once in terminal — then Open in VLC works instantly.");
    } else {
      triggerDownload(VLC_SETUP_URL, "vlc-setup.bat");
      setVlcProtocolReady(true);
      setVlcReady(true);
      onToast("Run the downloaded vlc-setup.bat once — then Open in VLC works instantly.");
    }
  };
  // Addon subtitles for this title, fetched in the background when the panel
  // opens so the VLC/Infuse buttons can attach them synchronously on click.
  const [panelSubtitles, setPanelSubtitles] = useState<SubtitleTrack[]>([]);

  useEffect(() => {
    if (!visible) return undefined;
    setAddonFilter("all");
    setQuery("");
    const onKey = (event: KeyboardEvent) => {
      if (event.key === "Escape") onClose();
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, [onClose, visible]);

  useEffect(() => {
    setPanelSubtitles([]);
    if (!visible || !item) return undefined;
    let active = true;
    void fetchSubtitlesForItem(
      installedAddons,
      item,
      selectedEpisode?.season ?? item.seasonNumber ?? undefined,
      selectedEpisode?.episode ?? item.episodeNumber ?? undefined
    ).then((subs) => { if (active) setPanelSubtitles(subs); }).catch(() => undefined);
    return () => { active = false; };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [visible, item?.id, selectedEpisode?.season, selectedEpisode?.episode]);

  const addons = useMemo(() => {
    // Only surface addons that actually returned sources for this title. Seeding a
    // chip for every installed stream-capable addon meant subtitle providers
    // (OpenSubtitles, Ktuvit, Wizdom) — whose manifests also declare a "stream"
    // resource — showed up as empty source filters. A name lookup from the
    // installed list keeps the pretty addon names.
    const nameById = new Map(installedAddons.map((addon) => [addon.id, addon.name]));
    const unique = new Map<string, { id: string; name: string; count: number }>();
    streams.forEach((stream) => {
      const id = stream.addonId || stream.addonName;
      const existing = unique.get(id);
      unique.set(id, {
        id,
        name: existing?.name || nameById.get(id) || stream.addonName,
        count: (existing?.count ?? 0) + 1
      });
    });
    return Array.from(unique.values());
  }, [installedAddons, streams]);

  const filtered = useMemo(() => {
    const needle = query.trim().toLowerCase();
    return streams.filter((stream) => {
      if (addonFilter !== "all" && (stream.addonId || stream.addonName) !== addonFilter) return false;
      if (!needle) return true;
      return `${stream.source} ${stream.addonName} ${stream.description ?? ""} ${stream.quality ?? ""} ${stream.size ?? ""}`.toLowerCase().includes(needle);
      // Keep quality-first ordering independent of the playback warning.
    }).sort((a, b) => sourcePickerScore(b, "external") - sourcePickerScore(a, "external"));
  }, [addonFilter, query, streams]);

  // Warm the direct CDN URLs of the top debrid picks while the user is still
  // looking at the list — pressing Play then skips the resolver round-trips
  // (which ride the Netlify proxy in production and cost 1-3s each).
  const prefetchSignature = filtered.slice(0, 3).map((stream) => stream.url ?? "").join("|");
  useEffect(() => {
    filtered.slice(0, 3).forEach((stream) => {
      if (!isUncachedDebridStream(stream)) prefetchDebridDirectUrl(stream.url);
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [prefetchSignature]);

  const title = selectedEpisode ? `${item.title} - S${selectedEpisode.season} E${selectedEpisode.episode}` : item.title;
  const openExternal = (player: "vlc" | "infuse", stream: StreamSource) => {
    if (!stream.url) {
      onToast("This source has no direct URL for an external player.");
      return;
    }
    // Build the launch target. Use the prefetch-cached CDN url if we already
    // have it; otherwise hand VLC the raw source (it follows the redirect
    // itself). Subtitles are attached only when synchronously present.
    const cachedCdn = parseDebridStream(stream.url) ? cachedDebridDirectUrl(stream.url) : null;
    let target = cachedCdn ? { ...stream, url: cachedCdn, originalUrl: stream.url } : stream;
    if (!target.subtitles?.length && panelSubtitles.length) target = { ...target, subtitles: panelSubtitles };
    // Persist the pending record and toast BEFORE launching. Both are
    // synchronous (localStorage / React state) so they don't consume the user
    // gesture, and writing first guarantees the return-to-app "mark finished"
    // prompt has its record even if the scheme navigation freezes/unloads the
    // page the instant it fires.
    onToast(
      player === "infuse"
        ? "Opening in Infuse..."
        : externalLaunchMode("vlc") === "playlist"
          ? "VLC playlist saved — open it from your downloads to play."
          : "Opening in VLC..."
    );
    createPendingExternalPlayback({
      player,
      item,
      stream: target,
      title,
      profileId: activeProfileId,
      season: selectedEpisode?.season ?? item.seasonNumber ?? null,
      episode: selectedEpisode?.episode ?? item.episodeNumber ?? null
    });
    // The deep link MUST fire synchronously inside the click — iOS Safari blocks
    // navigation to custom schemes (vlc-x-callback://) once the user gesture is
    // lost, so the app would appear to do nothing. Launch last.
    openExternalPlayer(player, target, title, settings.defaultSubtitle);
    void trackPremiumEvent(authClient, "external_playback_requested", { player, entry: "sources" }, true);
  };
  // Android: open in whichever player the user picks (VLC, MX Player, …) via the
  // system chooser — the equivalent of the iOS-only Infuse button.
  const openAnyPlayer = (stream: StreamSource) => {
    if (!stream.url) {
      onToast("This source has no direct URL for an external player.");
      return;
    }
    const cachedCdn = parseDebridStream(stream.url) ? cachedDebridDirectUrl(stream.url) : null;
    let target = cachedCdn ? { ...stream, url: cachedCdn, originalUrl: stream.url } : stream;
    if (!target.subtitles?.length && panelSubtitles.length) target = { ...target, subtitles: panelSubtitles };
    onToast("Opening in your player…");
    createPendingExternalPlayback({
      player: "vlc",
      item,
      stream: target,
      title,
      profileId: activeProfileId,
      season: selectedEpisode?.season ?? item.seasonNumber ?? null,
      episode: selectedEpisode?.episode ?? item.episodeNumber ?? null
    });
    openInAnyPlayer(target, title, settings.defaultSubtitle);
    void trackPremiumEvent(authClient, "external_playback_requested", { player: "chooser", entry: "sources" }, true);
  };
  const copyUrl = async (stream: StreamSource) => {
    const copied = await copyStreamUrl(stream).catch(() => false);
    onToast(copied ? "Stream URL copied." : "Could not copy this stream URL.");
  };
  const downloadSource = async (stream: StreamSource) => {
    void trackPremiumEvent(authClient, "download_requested", { entry: "sources" }, true);
    if (!stream.url) {
      void trackPremiumEvent(authClient, "download_failed", { stage: "missing_url" }, true);
      onToast("This source has no direct URL to download.");
      return;
    }
    // Resolve debrid streams to the final CDN file URL first (the download
    // proxy must get the real file, not the torrentio redirect chain, which
    // 403s server egress). Hard 20s timeout — jsonRequest has none, and a
    // stalled proxy call would leave "Preparing" up forever.
    let target = stream;
    const debridInfo = parseDebridStream(stream.url);
    if (debridInfo) {
      onToast("Preparing download...");
      const direct = cachedDebridDirectUrl(stream.url) ?? await Promise.race([
        resolveDebridDirectUrl(debridInfo).then((result) => result?.url ?? null).catch(() => null),
        new Promise<null>((resolve) => setTimeout(() => resolve(null), 20_000))
      ]);
      if (!direct) {
        void trackPremiumEvent(authClient, "download_failed", { stage: "resolution" }, true);
        onToast("Could not prepare this download — the source may not be cached. Try a [TB+] source.");
        return;
      }
      target = { ...stream, url: direct, originalUrl: stream.url };
    }
    // iOS/iPadOS can't reliably download a multi-GB file in a browser tab —
    // WebKit buffers the whole response in memory and hits the per-tab limit at a
    // random point, with no resume (every browser on iOS is WebKit, incl.
    // Chrome). Hand the resolved direct URL to VLC's `download` action instead:
    // it saves the file to VLC's own storage (outside the browser memory limit)
    // for offline playback on the device. Desktop keeps the normal file download.
    if (isAppleMobile()) {
      const ok = downloadToVlc(target, title, settings.defaultSubtitle);
      void trackPremiumEvent(authClient, ok ? "download_handoff" : "download_failed", { destination: "vlc" }, true);
      onToast(ok
        ? "Downloading to VLC for offline playback. If VLC doesn't open, install it from the App Store."
        : "Could not hand this download to VLC.");
      return;
    }
    const href = downloadStreamUrl(target, title);
    if (!href) {
      void trackPremiumEvent(authClient, "download_failed", { stage: "missing_download_url" }, true);
      onToast("Could not start this download.");
      return;
    }
    // Programmatic <a download> click — reliable in real browser tabs AND
    // installed PWAs, and keeps the app in place (the old window.location.href
    // navigated the whole tab, so a rate-limited/expired download left a blank
    // page with no feedback). The download proxy sets Content-Disposition:
    // attachment, so the browser's download manager owns the transfer.
    const started = triggerDownload(href, `${title}.${/\.mp4(?:[?#/]|$)/i.test(`${target.url} ${target.source ?? ""}`) ? "mp4" : "mkv"}`);
    void trackPremiumEvent(authClient, started ? "download_handoff" : "download_failed", { destination: "browser" }, true);
    onToast(started ? "Download started — check your browser downloads." : "Could not start this download.");
  };

  if (!visible || typeof document === "undefined") return null;
  return createPortal(
    <section className="source-modal" role="dialog" aria-modal="true" aria-label="Choose source">
      <div className="source-modal-bg" onClick={onClose} />
      <div className="source-panel">
        <header className="source-panel-head">
          <div>
            <p className="eyebrow">sources</p>
            <h2>{title}</h2>
            <span>
              {streams.length} sources — highest quality and largest files first.
              Choose browser playback or a compatible external player.
            </span>
          </div>
          <button type="button" className="person-close" onClick={onClose} aria-label="Close source picker"><X size={24} /></button>
        </header>

        {showVlcSetup && (
          <div className="vlc-setup-hint">
            <span>Windows/desktop: enable one-click "Open in VLC" (no more .m3u download).</span>
            <button type="button" onClick={enableVlcProtocol}>Set up VLC integration</button>
          </div>
        )}

        <div className="source-toolbar">
          <label className="source-search">
            <Search size={18} />
            <input value={query} onChange={(event) => setQuery(event.target.value)} placeholder="Search quality, release, provider" />
          </label>
          {/* Possible browser routes are not proof of playback; keep every source searchable. */}
          <div className="source-filter-group" aria-label="Source count">
            <button type="button" className="is-active" disabled>
              <Filter size={16} /> All sources{streams.length ? ` ${streams.length}` : ""}
            </button>
          </div>
        </div>

        <div className="source-addon-tabs">
          <button type="button" className={addonFilter === "all" ? "is-active" : ""} onClick={() => setAddonFilter("all")}>All Addons</button>
          {addons.map((addon) => (
            <button type="button" key={addon.id} className={addonFilter === addon.id ? "is-active" : ""} onClick={() => setAddonFilter(addon.id)}>
              {addon.name}{addon.count > 0 ? ` ${addon.count}` : ""}
            </button>
          ))}
        </div>

        <div className="source-picker-list">
          {filtered.length === 0 && (
            <p className="source-empty">
              {loading
                ? "Searching addons..."
                : addonFilter !== "all"
                  ? `${addons.find((addon) => addon.id === addonFilter)?.name ?? "This addon"} returned no sources for this title.`
                  : "No sources match this filter."}
            </p>
          )}
          {filtered.map((stream, index) => {
            const locked = !stream.url;
            const uncached = isUncachedDebridStream(stream);
            const plan = playbackPlan(stream);
            const playback = sourcePlaybackPresentation(stream, plan, uncached);
            const StatusIcon = playback.state === "blocked" ? TriangleAlert : Info;
            return (
              <article key={`${stream.addonId}-${stream.url ?? stream.source}`} className={`source-picker-row ${locked ? "is-locked" : ""}`}>
                <span className="source-rank">{index + 1}</span>
                <span className="source-main">
                  <strong>{stream.source || stream.addonName}</strong>
                  <em>{stream.addonName}{stream.description ? ` - ${stream.description}` : ""}</em>
                  <span className="source-status-line" data-playback-state={playback.state}>
                    <span className={`source-playback-status ${playback.className}`} style={{ maxWidth: "100%", whiteSpace: "normal", overflowWrap: "anywhere", gap: 6, paddingBlock: 4 }}>
                      <StatusIcon size={13} aria-hidden="true" style={{ flexShrink: 0 }} />
                      <span>{playback.label}</span>
                    </span>
                    {playback.detail && <span className="source-warning">{playback.detail}</span>}
                  </span>
                  <span className="stream-badges">
                    {streamBadges(stream).map((badge) => (
                      <span key={badge.label} className={`stream-badge ${badge.tone ?? ""}`}>{badge.label}</span>
                    ))}
                  </span>
                </span>
                <span className="source-side">
                  <b>{stream.quality || "Unknown"}</b>
                  <small>{locked ? "Needs resolver" : playback.state === "conversion" ? "Conversion required" : playback.state === "blocked" ? "Not browser-playable" : "Unverified"}</small>
                  <span className="source-row-actions">
                    {playback.canTryBrowser && <button type="button" className="source-action primary-action" aria-label={playback.state === "conversion" ? "Try provider conversion in browser" : "Try browser playback"} title={playback.detail || "Try browser playback"} onClick={() => { playStream(stream, { forceBrowser: true }); onClose(); }}><Play size={13} /> Try</button>}
                    <button
                      type="button"
                      className={`source-action ${locked ? "" : "primary-action"}`}
                      disabled={locked}
                      onClick={() => openExternal("vlc", stream)}
                    >
                      <ExternalLink size={13} /> VLC
                    </button>
                    <button type="button" className="source-action" disabled={locked} onClick={() => openAnyPlayer(stream)}>
                      <ExternalLink size={13} /> Player
                    </button>
                    <button type="button" className="source-action icon-only" disabled={locked} onClick={() => void downloadSource(stream)} aria-label="Download this source">
                      <Download size={13} />
                    </button>
                    <button type="button" className="source-action icon-only" disabled={locked} onClick={() => void copyUrl(stream)} aria-label="Copy stream URL">
                      <Copy size={13} />
                    </button>
                  </span>
                </span>
              </article>
            );
          })}
        </div>
      </div>
    </section>,
    document.body
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
  useEffect(() => {
    if (!visible) return undefined;
    const onKey = (event: KeyboardEvent) => {
      if (event.key === "Escape") onClose();
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, [onClose, visible]);

  if (!visible) return null;
  if (typeof document === "undefined") return null;
  return createPortal(
    <section className="person-modal" role="dialog" aria-modal="true" aria-label={person?.name ?? "Cast details"}>
      <div className="person-modal-bg" onClick={onClose} />
      <div className="person-panel">
        <button type="button" className="person-close" onClick={onClose} aria-label="Close cast details"><X size={24} /></button>
        {loading ? (
          <div className="person-loading">Loading cast details...</div>
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
                  <p className="eyebrow">Biography</p>
                  <p className="person-bio">{person.biography}</p>
                </section>
              ) : null}
              {person.knownFor.length ? (
                <section>
                  <p className="eyebrow">Known For</p>
                  <RailScroller className={`person-known-rail ${posterMode ? "is-poster" : ""}`} ariaLabel={`${person.name} known for`}>
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

function formatDate(date: string) {
  const parsed = new Date(`${date}T00:00:00`);
  if (Number.isNaN(parsed.getTime())) return date;
  return parsed.toLocaleDateString(undefined, { year: "numeric", month: "short", day: "numeric" });
}

function streamBadges(stream: StreamSource) {
  const text = `${stream.source} ${stream.description ?? ""} ${stream.size ?? ""}`.toLowerCase();
  const labels: Array<{ label: string; tone?: string }> = [];
  const quality = stream.quality || detectSourceBadge(text);
  if (quality) labels.push({ label: quality, tone: quality === "4K" ? "gold" : "" });
  if (text.includes("hdr10+") || text.includes("hdr")) labels.push({ label: "HDR" });
  if (text.includes("dolby vision") || /\bdv\b/i.test(text)) labels.push({ label: "DV" });
  if (text.includes("atmos")) labels.push({ label: "ATMOS" });
  else if (text.includes("7.1")) labels.push({ label: "7.1" });
  else if (text.includes("5.1")) labels.push({ label: "5.1" });
  const size = stream.size || text.match(/\b\d+(?:\.\d+)?\s?(?:gb|mb|tb)\b/i)?.[0]?.toUpperCase();
  if (size) labels.push({ label: size });
  if (stream.behaviorHints?.cached) labels.push({ label: "CACHED", tone: "ok" });
  if (parseDebridStream(stream.url) || /real-?debrid|premiumize|alldebrid|torbox|\brd\b|\bpm\b|\bad\b|\bdebrid\b/i.test(text)) labels.push({ label: "DEBRID", tone: "ok" });
  // A URL is not evidence of browser support, or of a resolved direct media link.
  if (stream.url) labels.push({ label: "URL" });
  if (!stream.url) labels.push({ label: "NO URL", tone: "warn" });
  const seen = new Set<string>();
  return labels.filter((badge) => {
    if (seen.has(badge.label)) return false;
    seen.add(badge.label);
    return true;
  }).slice(0, 7);
}

function detectSourceBadge(text: string) {
  if (text.includes("2160") || text.includes("4k")) return "4K";
  if (text.includes("1080")) return "1080p";
  if (text.includes("720")) return "720p";
  if (text.includes("480")) return "480p";
  return "";
}

function buildDetailMeta(item: MediaItem, showBudget: boolean) {
  const meta = [
    item.releaseDate || item.year || null,
    item.duration || null,
    item.mediaType === "tv" && item.numberOfSeasons ? `${item.numberOfSeasons} season${item.numberOfSeasons === 1 ? "" : "s"}` : null,
    item.mediaType === "tv" && item.numberOfEpisodes ? `${item.numberOfEpisodes} episodes` : null,
    item.mediaType === "tv" && item.lastAirDate ? `Last aired ${item.lastAirDate}` : null,
    item.status || null,
    item.originalLanguage ? item.originalLanguage.toUpperCase() : null
  ];
  if (showBudget && item.mediaType === "movie") {
    meta.push(formatMoney(item.budget, "Budget"));
    meta.push(formatMoney(item.revenue, "Box office"));
  }
  return meta.filter((value): value is string => Boolean(value));
}

function buildContinueLabel(item: MediaItem, selectedEpisode: { season: number; episode: number } | null) {
  const season = selectedEpisode?.season ?? item.seasonNumber ?? null;
  const episode = selectedEpisode?.episode ?? item.episodeNumber ?? null;
  if (item.mediaType === "tv") {
    return season && episode ? `Continue S${season} E${episode}` : "Choose episode";
  }
  const progress = item.progress ?? 0;
  return progress >= 1 && progress <= 94 ? `Continue ${Math.round(progress)}%` : "Play";
}

function formatMoney(value: number | null | undefined, label: string) {
  if (!value || value <= 0) return null;
  const compact = new Intl.NumberFormat("en-US", {
    notation: "compact",
    maximumFractionDigits: 1,
    style: "currency",
    currency: "USD"
  }).format(value);
  return `${label} ${compact}`;
}

function SeasonEpisodes({ item, loadingDetails, selectedEpisode, isWatched, onPlayEpisode }: {
  item: MediaItem;
  loadingDetails: boolean;
  selectedEpisode: { season: number; episode: number } | null;
  isWatched: (item: MediaItem, seasonNumber?: number | null, episodeNumber?: number | null) => boolean;
  onPlayEpisode: (season: number, episode: number) => void;
}) {
  const { openContextMenu, setToast, settings, toggleWatched } = useApp();
  const seasons = item.seasons ?? [];
  const [season, setSeason] = useState(seasons[0]?.seasonNumber ?? 1);
  const [episodes, setEpisodes] = useState<EpisodeInfo[]>([]);
  const [loading, setLoading] = useState(false);
  const [retryNonce, setRetryNonce] = useState(0);
  const priorityConfig = useMemo(() => getPriorityConfig(settings), [settings]);
  const metadataContext = useMemo(() => ({
    tvdbId: item.tvdbId,
    anilistId: item.anilistId,
    isAnime: item.isAnime
  }), [item.anilistId, item.isAnime, item.tvdbId]);

  useEffect(() => {
    if (seasons.length && !seasons.some((entry) => entry.seasonNumber === season)) {
      setSeason(seasons[0]?.seasonNumber ?? 1);
    }
  }, [season, seasons]);

  useEffect(() => {
    let active = true;
    setLoading(true);
    void getSeasonEpisodes(item.id, season, "en-US", priorityConfig, metadataContext)
      .then((eps) => { if (active) setEpisodes(eps); })
      .catch(() => undefined)
      .finally(() => active && setLoading(false));
    return () => { active = false; };
  }, [item.id, metadataContext, priorityConfig, retryNonce, season]);

  const updateSeasonWatched = async (seasonNum: number, watched: boolean) => {
    try {
      const targetEpisodes = await getSeasonEpisodes(item.id, seasonNum, "en-US", priorityConfig, metadataContext);
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

  const handleSeasonContextMenu = (e: React.MouseEvent, seasonNum: number, seasonName: string) => {
    e.preventDefault();
    e.stopPropagation();
    openContextMenu({
      title: seasonName || `Season ${seasonNum}`,
      subtitle: item.title,
      position: { x: e.clientX, y: e.clientY },
      actions: [
        {
          id: "mark_season_watched",
          label: "Mark Season Watched",
          icon: <Check size={18} />,
          action: async () => {
            await updateSeasonWatched(seasonNum, true);
          }
        },
        {
          id: "mark_season_unwatched",
          label: "Mark Season Unwatched",
          icon: <EyeOff size={18} />,
          action: async () => {
            await updateSeasonWatched(seasonNum, false);
          }
        }
      ]
    });
  };

  const handleEpisodeContextMenu = (e: React.MouseEvent, ep: EpisodeInfo) => {
    e.preventDefault();
    e.stopPropagation();
    const watched = isWatched(item, season, ep.episodeNumber);
    openContextMenu({
      title: ep.name || `Episode ${ep.episodeNumber}`,
      subtitle: `${item.title} - S${season} E${ep.episodeNumber}`,
      position: { x: e.clientX, y: e.clientY },
      actions: [
        {
          id: "play_episode",
          label: "Play Episode",
          icon: <Play size={18} fill="currentColor" />,
          action: () => {
            onPlayEpisode(season, ep.episodeNumber);
          }
        },
        {
          id: "toggle_episode_watched",
          label: watched ? "Mark as Unwatched" : "Mark as Watched",
          icon: watched ? <EyeOff size={18} /> : <Check size={18} />,
          action: () => {
            void toggleWatched(item, season, ep.episodeNumber);
          }
        }
      ]
    });
  };

  return (
    <section className="detail-section episodes-section detail-wide">
      <h3>Episodes</h3>
      <div className="season-tabs">
        {seasons.map((s) => (
          <button
            type="button"
            key={s.id}
            className={`season-tab ${s.seasonNumber === season ? "is-active" : ""}`}
            onClick={() => setSeason(s.seasonNumber)}
            onContextMenu={(e) => handleSeasonContextMenu(e, s.seasonNumber, s.name || `Season ${s.seasonNumber}`)}
          >
            {s.name || `Season ${s.seasonNumber}`}
          </button>
        ))}
        {!seasons.length && loadingDetails ? <span className="season-tab is-loading">Loading seasons...</span> : null}
      </div>
      <RailScroller className="episode-list" ariaLabel={`season ${season} episodes`}>
        {(loading || loadingDetails) && <p className="empty">Loading episodes...</p>}
        {!loading && !loadingDetails && !episodes.length ? (
          <p className="empty">
            No episodes found.{" "}
            <button type="button" className="episode-retry" onClick={() => setRetryNonce((n) => n + 1)}>Retry</button>
          </p>
        ) : null}
        {!loading && episodes.map((episode) => {
          const active = selectedEpisode?.season === season && selectedEpisode?.episode === episode.episodeNumber;
          const episodeRating = episode.imdbRating || (episode.voteAverage && episode.voteAverage > 0 ? episode.voteAverage.toFixed(1) : "");
          const watched = isWatched(item, season, episode.episodeNumber);
          return (
            <button
              type="button"
              key={episode.id}
              className={`episode-row ${active ? "is-active" : ""} ${watched ? "is-watched" : ""}`}
              onClick={() => onPlayEpisode(season, episode.episodeNumber)}
              onContextMenu={(e) => handleEpisodeContextMenu(e, episode)}
            >
              <div className="episode-still">
                {episode.still ? <img src={episode.still} alt="" /> : <Clapperboard size={24} />}
                <span className="episode-chip episode-chip-left">S{season} E{episode.episodeNumber.toString().padStart(2, "0")}</span>
                {episode.airDate && <span className="episode-chip episode-chip-center">{episode.airDate}</span>}
                {watched && <span className="watched-badge episode-watched-badge" aria-label="Watched"><BadgeCheck size={12} /></span>}
                <span className="episode-play"><Play size={18} fill="currentColor" /></span>
              </div>
              <div className="episode-info">
                <strong>{episode.name}</strong>
                <span className="episode-subline">
                  {episode.runtime ? `${episode.runtime}m` : `Episode ${episode.episodeNumber}`}
                  {episodeRating && (
                    <em className="episode-imdb">
                      <img src={IMDB_LOGO} alt="IMDb" loading="lazy" />
                      {episodeRating}
                    </em>
                  )}
                </span>
                <p>{episode.overview || ""}</p>
              </div>
            </button>
          );
        })}
      </RailScroller>
    </section>
  );
}
