"use client";
import "./sources.css";
import { useTranslation } from "@/lib/i18n";

import { Search, X } from "lucide-react";
import { useEffect, useMemo, useRef, useState, useSyncExternalStore } from "react";
import { createPortal } from "react-dom";
import { isDisabledTelegramSource, TELEGRAM_DISABLED_MESSAGE } from "@/lib/config";
import { chooseDownloadFile, startManagedDownload, recordDownloadHandoff } from "@/lib/downloads";
import { premiumAllows } from "@/lib/premiumGate";
import { trackPremiumEvent } from "@/lib/premiumAnalytics";
import { createPendingExternalPlayback } from "@/lib/externalPlayback";
import {
  copyStreamUrl, downloadStreamUrl, downloadToVlc, externalLaunchMode, externalPlayerOptions, isAppleMobile, isLinux, isWindows,
  openExternalPlayer, openInAnyPlayer, setVlcProtocolReady, triggerDownload, vlcProtocolReady, VLC_SETUP_SH_URL, VLC_SETUP_URL,
  type ExternalPlayerOption
} from "@/lib/externalPlayers";
import { fetchSubtitlesForItem } from "@/lib/addons";
import { cachedDebridDirectUrl, isUncachedDebridStream, parseDebridStream, prefetchDebridDirectUrl, resolveDebridDirectUrl } from "@/lib/debrid";
import { groupSourcesForPlayback } from "@/lib/sourceRank";
import { playbackCompatibilityRevision, playbackPlan, subscribePlaybackCompatibility } from "@/lib/streamCompatibility";
import { claimEscape, isTopmostLayer, trapTab } from "@/lib/playerKeys";
import { authClient, useAppPick } from "@/lib/store";
import type { InstalledAddon, MediaItem, StreamSource, SubtitleTrack } from "@/lib/types";
import { isCachedSource, matchesSourceFilter, sourceBadges, sourcePlaybackPresentation, sourceQuality, sourceTitle, type SourceFilter } from "./sourcePlaybackPresentation";
import { SourceRow } from "./SourceRow";

export type SourceFacts = {
  stream: StreamSource;
  key: string;
  title: string;
  presentation: ReturnType<typeof sourcePlaybackPresentation>;
  tier: ReturnType<typeof sourcePlaybackPresentation>["tier"];
  badges: ReturnType<typeof sourceBadges>;
  quality: string;
  cached: boolean;
  locked: boolean;
  telegramDisabled: boolean;
};

/** Everything a source row shows, computed once per stream from the shared plan. */
export function sourceFacts(stream: StreamSource): SourceFacts {
  const telegramDisabled = isDisabledTelegramSource(stream);
  const uncached = isUncachedDebridStream(stream);
  const debrid = Boolean(parseDebridStream(stream.originalUrl ?? stream.url));
  const plan = playbackPlan(stream);
  const presentation = telegramDisabled
    ? sourcePlaybackPresentation({ url: null }, { ...plan, detail: TELEGRAM_DISABLED_MESSAGE }, uncached)
    : sourcePlaybackPresentation(stream, plan, uncached);
  return {
    stream,
    key: `${stream.addonId ?? stream.addonName}-${stream.url ?? stream.source}`,
    title: sourceTitle(stream),
    presentation,
    tier: presentation.tier,
    badges: sourceBadges(stream, { debrid, uncached }),
    quality: sourceQuality(stream),
    cached: isCachedSource(stream, debrid, uncached),
    locked: !stream.url || telegramDisabled,
    telegramDisabled
  };
}

export function useCompactSources() {
  const [compact, setCompact] = useState(false);
  useEffect(() => {
    const query = window.matchMedia("(max-width: 680px)");
    const update = () => setCompact(query.matches);
    update();
    query.addEventListener?.("change", update);
    return () => query.removeEventListener?.("change", update);
  }, []);
  return compact;
}

export function SourcePickerModal({
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
  const translateUi = useTranslation();
  const { settings, playStream } = useAppPick("settings", "playStream");
  // Recompute visible plans after selected-source failures without probing the result list.
  useSyncExternalStore(subscribePlaybackCompatibility, playbackCompatibilityRevision, () => 0);
  const [addonFilter, setAddonFilter] = useState("all");
  const [filter, setFilter] = useState<SourceFilter>("all");
  const [query, setQuery] = useState("");
  const compact = useCompactSources();
  const panelRef = useRef<HTMLElement | null>(null);
  const openerRef = useRef<HTMLElement | null>(null);
  const externalOptions = useMemo(() => externalPlayerOptions(), []);
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

  // Focus moves into the picker on open, stays there (Tab is trapped, arrows
  // are scoped by tvNav to the topmost dialog) and returns to the button that
  // opened it on close.
  useEffect(() => {
    if (!visible) return undefined;
    setAddonFilter("all");
    setFilter("all");
    setQuery("");
    if (document.activeElement instanceof HTMLElement && !document.activeElement.closest(".source-modal")) openerRef.current = document.activeElement;
    const focusTimer = window.setTimeout(() => {
      const panel = panelRef.current;
      const target = panel?.querySelector<HTMLElement>(".src-row-main:not([disabled])") ?? panel?.querySelector<HTMLElement>(".person-close");
      target?.focus({ preventScroll: true });
    }, 0);
    const onKey = (event: KeyboardEvent) => {
      const layer = panelRef.current?.closest(".source-modal") ?? null;
      if (event.key === "Tab" && panelRef.current && isTopmostLayer(layer)) { trapTab(event, panelRef.current); return; }
      if (event.key !== "Escape" || !claimEscape(event) || !isTopmostLayer(layer)) return;
      // Escape inside the search field first leaves the field (tvNav).
      if (event.target instanceof HTMLInputElement) return;
      event.preventDefault();
      onClose();
    };
    window.addEventListener("keydown", onKey);
    return () => {
      window.clearTimeout(focusTimer);
      window.removeEventListener("keydown", onKey);
      const opener = openerRef.current;
      if (opener?.isConnected) window.setTimeout(() => opener.focus({ preventScroll: true }), 0);
    };
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

  const searched = useMemo(() => {
    const needle = query.trim().toLowerCase();
    return streams.filter((stream) => {
      if (addonFilter !== "all" && (stream.addonId || stream.addonName) !== addonFilter) return false;
      if (!needle) return true;
      return `${stream.source} ${stream.addonName} ${stream.description ?? ""} ${stream.quality ?? ""} ${stream.size ?? ""}`.toLowerCase().includes(needle);
    });
  }, [addonFilter, query, streams]);
  const facts = useMemo(() => new Map(searched.map((stream) => [stream, sourceFacts(stream)])), [searched]);
  const counts = useMemo(() => {
    const all = [...facts.values()];
    const count = (value: SourceFilter) => all.filter((entry) => matchesSourceFilter(value, entry)).length;
    return { all: all.length, here: count("here"), "4k": count("4k"), "1080p": count("1080p"), cached: count("cached") } as Record<SourceFilter, number>;
  }, [facts]);
  // One order for every list: what plays in this browser first (direct, then
  // conversion), then external-only sources — the same order the player uses.
  const groups = useMemo(() => {
    const visibleStreams = searched.filter((stream) => matchesSourceFilter(filter, facts.get(stream)!));
    return groupSourcesForPlayback(visibleStreams);
  }, [facts, filter, searched]);
  const ordered = useMemo(() => [...groups.here, ...groups.external], [groups]);

  // Warm the direct CDN URLs of the top debrid picks while the user is still
  // looking at the list — pressing Play then skips the resolver round-trips
  // (which ride the Netlify proxy in production and cost 1-3s each).
  const prefetchSignature = groups.here.slice(0, 3).map((stream) => stream.url ?? "").join("|");
  useEffect(() => {
    groups.here.slice(0, 3).forEach((stream) => {
      if (isDisabledTelegramSource(stream)) return;
      if (!isUncachedDebridStream(stream)) prefetchDebridDirectUrl(stream.url);
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [prefetchSignature]);

  const title = selectedEpisode ? `${item.title} - S${selectedEpisode.season} E${selectedEpisode.episode}` : item.title;
  const rejectDisabledTelegram = (stream: StreamSource) => {
    if (!isDisabledTelegramSource(stream)) return false;
    onToast(TELEGRAM_DISABLED_MESSAGE);
    return true;
  };
  const launchTarget = (stream: StreamSource) => {
    // Use the prefetch-cached CDN url if we already have it; otherwise hand the
    // player the raw source (it follows the redirect itself). Subtitles are
    // attached only when synchronously present.
    const cachedCdn = parseDebridStream(stream.url) ? cachedDebridDirectUrl(stream.url!) : null;
    let target = cachedCdn ? { ...stream, url: cachedCdn, originalUrl: stream.url } : stream;
    if (!target.subtitles?.length && panelSubtitles.length) target = { ...target, subtitles: panelSubtitles };
    return target;
  };
  const openExternal = (option: ExternalPlayerOption, stream: StreamSource) => {
    if (rejectDisabledTelegram(stream)) return;
    // No resume: iOS only opens an app from inside the original tap.
    if (!premiumAllows("external")) return;
    if (!stream.url) {
      onToast("This source has no direct URL for an external player.");
      return;
    }
    const target = launchTarget(stream);
    const player = option.id === "infuse" ? "infuse" : "vlc";
    // Persist the pending record and toast BEFORE launching. Both are
    // synchronous (localStorage / React state) so they don't consume the user
    // gesture, and writing first guarantees the return-to-app "mark finished"
    // prompt has its record even if the scheme navigation freezes/unloads the
    // page the instant it fires.
    onToast(
      option.id === "chooser"
        ? "Opening in your player…"
        : player === "infuse"
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
    if (option.id === "chooser") openInAnyPlayer(target, title, settings.defaultSubtitle);
    else openExternalPlayer(player, target, title, settings.defaultSubtitle);
    void trackPremiumEvent(authClient, "external_playback_requested", { player: option.id, entry: "sources" }, true);
  };
  const copyUrl = async (stream: StreamSource) => {
    if (rejectDisabledTelegram(stream)) return;
    if (!premiumAllows("copy")) return;
    const copied = await copyStreamUrl(stream).catch(() => false);
    onToast(copied ? "Stream URL copied." : "Could not copy this stream URL.");
  };
  const downloadSource = async (stream: StreamSource) => {
    if (rejectDisabledTelegram(stream)) return;
    if (!premiumAllows("download")) return;
    void trackPremiumEvent(authClient, "download_requested", { entry: "sources" }, true);
    if (!stream.url) {
      void trackPremiumEvent(authClient, "download_failed", { stage: "missing_url" }, true);
      onToast("This source has no direct URL to download.");
      return;
    }
    let fileHandle: Awaited<ReturnType<typeof chooseDownloadFile>>;
    try { fileHandle = await chooseDownloadFile(title, /\.mp4(?:[?#/]|$)/i.test(`${stream.url} ${stream.source ?? ""}`) ? "mp4" : "mkv"); }
    catch (error) { if (!(error instanceof DOMException && error.name === "AbortError")) onToast("Could not select a download file."); return; }
    const downloadScope = `${authClient.session?.userId ?? "local"}:${activeProfileId ?? "default"}`;
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
    if (fileHandle) {
      const href = downloadStreamUrl(target, title);
      if (!href) { onToast("Could not start this download."); return; }
      try { await startManagedDownload(title, href, downloadScope, fileHandle); onToast("Download started. Manage it in Settings > Downloads."); }
      catch (error) { onToast(error instanceof Error ? error.message : "Could not start this download."); }
      return;
    }
    // iOS/iPadOS can't reliably download a multi-GB file in a browser tab —
    // WebKit buffers the whole response in memory and hits the per-tab limit at a
    // random point, with no resume (every browser on iOS is WebKit, incl.
    // Chrome). Hand the resolved direct URL to VLC's `download` action instead:
    // it saves the file to VLC's own storage (outside the browser memory limit)
    // for offline playback on the device. Desktop keeps the normal file download.
    if (isAppleMobile()) {
      const ok = downloadToVlc(target, title, settings.defaultSubtitle);
      if (ok) recordDownloadHandoff(title, downloadScope);
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
    // installed PWAs, and keeps the app in place. The download proxy sets
    // Content-Disposition: attachment, so the browser's download manager owns
    // the transfer.
    const started = triggerDownload(href, `${title}.${/\.mp4(?:[?#/]|$)/i.test(`${target.url} ${target.source ?? ""}`) ? "mp4" : "mkv"}`);
    if (started) recordDownloadHandoff(title, downloadScope);
    void trackPremiumEvent(authClient, started ? "download_handoff" : "download_failed", { destination: "browser" }, true);
    onToast(started ? "Download started — check your browser downloads." : "Could not start this download.");
  };

  if (!visible || typeof document === "undefined") return null;
  const filterChips: Array<[SourceFilter, string]> = [["all", "All"], ["here", "Plays here"], ["4k", "4K"], ["1080p", "1080p"], ["cached", "Cached"]];
  const renderRow = (stream: StreamSource, index: number) => {
    const entry = facts.get(stream) ?? sourceFacts(stream);
    return (
      <SourceRow
        key={entry.key}
        rank={index + 1}
        title={entry.title}
        provider={stream.addonName}
        presentation={entry.presentation}
        badges={entry.badges}
        externalOptions={externalOptions}
        locked={entry.locked}
        lockedReason={entry.telegramDisabled ? TELEGRAM_DISABLED_MESSAGE : !stream.url ? "No resolved playback URL is available." : undefined}
        compact={compact}
        onPlay={() => { playStream(stream, { forceBrowser: true }); onClose(); }}
        onExternal={(option) => openExternal(option, stream)}
        onDownload={entry.locked ? undefined : () => void downloadSource(stream)}
        onCopy={entry.locked ? undefined : () => void copyUrl(stream)}
      />
    );
  };
  return createPortal(
    <section className="source-modal" role="dialog" aria-modal="true" aria-label={translateUi("Choose source")}>
      <div className="source-modal-bg" onClick={onClose} />
      <div className="source-panel src-panel" ref={(node) => { panelRef.current = node; }}>
        <header className="source-panel-head">
          <div>
            <p className="eyebrow">{translateUi("sources")}</p>
            <h2>{title}</h2>
            <span>
              {translateUi("{value0} sources · {value1} play in this browser", { value0: streams.length, value1: counts.here })}
            </span>
          </div>
          <button type="button" className="person-close" onClick={onClose} aria-label={translateUi("Close source picker")}><X size={24} /></button>
        </header>

        {showVlcSetup && (
          <div className="vlc-setup-hint">
            <span>{translateUi("Windows/desktop: enable one-click \"Open in VLC\" (no more .m3u download).")}</span>
            <button type="button" onClick={enableVlcProtocol}>{translateUi("Set up VLC integration")}</button>
          </div>
        )}

        <div className="source-toolbar">
          <label className="source-search">
            <Search size={18} />
            <input value={query} onChange={(event) => setQuery(event.target.value)} placeholder={translateUi("Search quality, release, provider")} aria-label={translateUi("Search sources")} />
          </label>
        </div>

        <div className="src-filters" role="group" aria-label={translateUi("Filter sources")}>
          {filterChips.filter(([value]) => value === "all" || value === "here" || counts[value] > 0).map(([value, label]) => (
            <button type="button" key={value} aria-pressed={filter === value} onClick={() => setFilter(value)}>
              {translateUi(label)} {counts[value]}
            </button>
          ))}
          {addons.length > 1 ? <span className="src-filter-sep" aria-hidden="true" /> : null}
          {addons.length > 1 ? addons.map((addon) => (
            <button type="button" key={addon.id} aria-pressed={addonFilter === addon.id} onClick={() => setAddonFilter(addonFilter === addon.id ? "all" : addon.id)}>
              {addon.name} {addon.count}
            </button>
          )) : null}
        </div>

        <div className="src-list">
          {ordered.length === 0 && (
            <p className="source-empty">
              {loading
                ? translateUi("Searching addons...")
                : addonFilter !== "all"
                  ? translateUi("{value0} returned no sources for this title.", { value0: addons.find((addon) => addon.id === addonFilter)?.name ?? "This addon" })
                  : streams.length ? translateUi("No sources match this filter.") : translateUi("Sources will appear here when an installed addon returns results.")}
            </p>
          )}
          {groups.here.length > 0 && (
            <h3 className="src-group-head">{translateUi("Plays here")} <small>{translateUi("{value0} sources", { value0: groups.here.length })}</small></h3>
          )}
          {groups.here.map((stream, index) => renderRow(stream, index))}
          {groups.external.length > 0 && (
            <h3 className="src-group-head">{translateUi("External player")} <small>{translateUi("{value0} sources", { value0: groups.external.length })}</small></h3>
          )}
          {groups.external.map((stream, index) => renderRow(stream, groups.here.length + index))}
          {loading && ordered.length > 0 ? <p className="source-empty" role="status">{translateUi("Searching addons...")}</p> : null}
        </div>
      </div>
    </section>,
    document.body
  );
}
