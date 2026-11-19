"use client";
import { premiumAllows } from "@/lib/premiumGate";
import { useTranslation } from "@/lib/i18n";
import "./livetv.css";

import { ArrowDown, ArrowUp, PanelLeft, CalendarClock, ChevronDown, ExternalLink, Eye, EyeOff, History, LayoutGrid, List, ListVideo, Play, Plus, RefreshCw, Search, Star, Trophy, Tv, X } from "lucide-react";
import { useCallback, useEffect, useMemo, useRef, useState } from "react";
import { externalLaunchMode, openExternalPlayer } from "@/lib/externalPlayers";
import { applyChannelOrder, groupNameOf, isFavoriteGroup, toggleFavoriteGroup } from "@/lib/iptvGroups";
import { channelPlaylistId, liveTvStalkerPortals, referencedStalkerPortalIds, stalkerPortalsOf, stalkerPortalsPatch, stalkerSourceSignature } from "@/lib/stalkerPortals";
import { accessibleChannels, groupKey, loadXtreamCatchup, type CatchupProgram } from "@/lib/iptv";
import { VirtualList } from "@/components/ui/VirtualList";
import { SportsGuidePane } from "@/components/livetv/SportsGuidePane";
import { sportsEventCatalogs } from "@/lib/sportsAddons";
import { ChannelLogo } from "@/components/livetv/ChannelLogo";
import { ChannelProgramInfo } from "@/components/livetv/ChannelProgramInfo";
import { ChannelRow, GuideGrid, type GuideCallbacks } from "@/components/livetv/GuideGrid";
import { ChannelMenu, type ChannelMenuState } from "@/components/livetv/ChannelMenu";
import { LiveZapOverlay } from "@/components/livetv/LiveZapOverlay";
import { PlaylistErrors, PlaylistManager, type PlaylistError } from "@/components/livetv/PlaylistManager";
import { channelIdentityIndex, normalizeTvSession, resolveChannelReferences } from "@/lib/iptvSession";
import { IPTV_SNAPSHOT_TTL_MS, iptvPlaylistSignature } from "@/lib/iptv";
import { channelMatchesQuery, formatGuideDay, formatGuideTime, splitPlaylistWarning } from "@/lib/iptvGuide";
import { favoritePosition, moveFavoriteChannel, toggleFavoriteChannel, toggleGroupKey } from "@/lib/iptvFavorites";
import { appendChannelDigit, DIGIT_COMMIT_MS, exactChannelNumber, keepLivePlayerExpanded, MIN_ZAP_INTERVAL_MS, registerLiveZap, uniqueChannelNumberPrefix, zapTarget } from "@/lib/liveZap";
import { requestSourceSettings } from "@/lib/sourceSetup";
import { playerIsOpen } from "@/lib/tvNav";
import { loadStored, saveStored } from "@/lib/storage";
import { authClient, useAppPick } from "@/lib/store";
import { trackPremiumEvent } from "@/lib/premiumAnalytics";
import type { IptvChannel } from "@/lib/types";

const LAST_CHANNEL_KEY = "arvio.web.livetv.lastChannel";
const GUIDE_BATCH_DELAY_MS = 500;
const SEARCH_DEBOUNCE_MS = 150;
const INFO_VISIBLE_MS = 5000;
const rowKey = (item: { id: string }) => item.id;

/** Categories start collapsed below tablet-landscape width (phones and portrait tablets). */
export function categoriesOpenByDefault(width: number) {
  return width >= 1024;
}

function blurIntoPlayer() {
  // The fullscreen player listens on window: a guide button that keeps focus
  // swallows Back (the guide's own key handler) and Enter (replays the row).
  for (const delay of [60, 320]) window.setTimeout(() => {
    if (!playerIsOpen()) return;
    const active = document.activeElement as HTMLElement | null;
    if (active && !active.closest(".player-overlay")) active.blur();
  }, delay);
}

export function LiveTvScreen() {
  const translateUi = useTranslation();
  const { iptvSnapshot, settings, updateSettings, playChannel, recordChannelPlayback, playCatchup, setToast, refreshIptv, loadIptvGuide, busy, auth, activeProfile, activeChannel, addons, setSection } = useAppPick("iptvSnapshot", "settings", "updateSettings", "playChannel", "recordChannelPlayback", "playCatchup", "setToast", "refreshIptv", "loadIptvGuide", "busy", "auth", "activeProfile", "activeChannel", "addons", "setSection");
  const lastChannelKey = `${LAST_CHANNEL_KEY}:${auth?.userId ?? "local"}:${activeProfile?.id ?? "local"}`;
  const listRef = useRef<HTMLElement>(null);
  const pointerNavigation = useRef(false);

  // Open a channel straight in VLC/Infuse from the detail panel — the reliable
  // path for the many IPTV providers whose plain-HTTP streams a secure web page
  // can't play, without first waiting for the in-browser attempt to fail.
  const openChannelExternally = useCallback(function openExternally(channel: IptvChannel, player: "vlc" | "infuse") {
    // After paying from the Premium offer, finish what the user asked for.
    if (!premiumAllows("live", () => openExternally(channel, player))) return;
    setToast(
      player === "infuse"
        ? "Opening in Infuse…"
        : externalLaunchMode("vlc") === "playlist"
          ? "VLC playlist saved — open it from your downloads to play."
          : "Opening in VLC…"
    );
    openExternalPlayer(
      player,
      { source: channel.name, addonName: "Live TV", quality: "Live", size: "", url: channel.streamUrl, description: channel.group },
      channel.name,
      settings.defaultSubtitle
    );
    recordChannelPlayback(channel);
    void trackPremiumEvent(authClient, "external_playback_requested", { player, entry: "live_tv" }, true);
  }, [setToast, settings.defaultSubtitle, recordChannelPlayback]);

  const playlists = settings.iptvPlaylists;
  const portals = useMemo(() => stalkerPortalsOf(settings), [settings.iptvStalkerPortals, settings.iptvStalkerUrl, settings.iptvStalkerMac]); // eslint-disable-line react-hooks/exhaustive-deps
  const livePortals = useMemo(() => liveTvStalkerPortals(settings), [settings.iptvStalkerPortals, settings.iptvStalkerUrl, settings.iptvStalkerMac]); // eslint-disable-line react-hooks/exhaustive-deps
  const groupNames = settings.iptvGroupNames;
  const hasGroupNames = Boolean(groupNames && Object.keys(groupNames).length);
  const favorites = settings.favoriteChannelIds;
  const favoriteGroups = settings.favoriteGroupIds;
  const hiddenGroups = settings.hiddenGroupIds;

  const [activeCategory, setActiveCategory] = useState("all");
  const hasSportsAddons = addons.some(addon => sportsEventCatalogs(addon).length > 0);
  useEffect(() => {
    if (hasSportsAddons && !settings.iptvPlaylists.length && !portals.length) setActiveCategory("sports");
  }, [hasSportsAddons, settings.iptvPlaylists.length, portals.length]);
  const [queryInput, setQueryInput] = useState("");
  const [query, setQuery] = useState("");
  useEffect(() => {
    const timer = window.setTimeout(() => setQuery(queryInput), queryInput ? SEARCH_DEBOUNCE_MS : 0);
    return () => window.clearTimeout(timer);
  }, [queryInput]);
  // Restore local state in the effect below, after server/client markup agrees.
  const [selectedChannelId, setSelectedChannelId] = useState<string | null>(null);
  const [managing, setManaging] = useState(false);
  const [editRequest, setEditRequest] = useState<{ id: string; at: number } | null>(null);
  const [view, setView] = useState<"list" | "guide">("guide");
  const [groupsOpen, setGroupsOpen] = useState(() => typeof window === "undefined" || categoriesOpenByDefault(window.innerWidth));
  const [provider, setProvider] = useState("all");
  const [catchup, setCatchup] = useState<{ channelId: string; programs: CatchupProgram[]; loading: boolean } | null>(null);
  const [archiveOpen, setArchiveOpen] = useState(false);
  const [menu, setMenu] = useState<ChannelMenuState | null>(null);
  const [zapDigits, setZapDigits] = useState("");
  const [zapInfo, setZapInfo] = useState<IptvChannel | null>(null);
  const tvSession = useMemo(() => normalizeTvSession(settings.iptvTvSession), [settings.iptvTvSession]);

  const allChannels = iptvSnapshot.allChannels ?? iptvSnapshot.channels;
  const providerChannels = useMemo(() => provider === "all" ? allChannels : allChannels.filter((ch) => channelPlaylistId(ch.id) === provider), [allChannels, provider]);
  const channels = useMemo(() => accessibleChannels(providerChannels, [...hiddenGroups, ...(settings.lockedIptvGroupIds ?? [])]), [providerChannels, hiddenGroups, settings.lockedIptvGroupIds]);
  const groups = useMemo(() => {
    const result: Record<string, IptvChannel[]> = {};
    for (const channel of channels) (result[groupKey(channel)] ??= []).push(channel);
    return result;
  }, [channels]);
  const channelById = useMemo(() => channelIdentityIndex(channels), [channels]);
  const enabledPlaylists = useMemo(() => playlists.filter((playlist) => playlist.enabled && playlist.m3uUrl.trim()), [playlists]);
  const sourceCount = enabledPlaylists.length + livePortals.length;
  useEffect(() => {
    if (provider !== "all" && !enabledPlaylists.some(playlist => playlist.id === provider) && !livePortals.some(portal => portal.id === provider)) setProvider("all");
  }, [enabledPlaylists, livePortals, provider]);
  /** A category's name on screen: the user's own name, else the playlist's. */
  const groupNameFor = useCallback((channel: IptvChannel) => (hasGroupNames ? groupNames?.[groupKey(channel)] : undefined) || channel.group,
    [groupNames, hasGroupNames]);
  const favoriteChannels = useMemo(() => resolveChannelReferences(favorites, channelById), [favorites, channelById]);
  const favoriteIds = useMemo(() => new Set(favoriteChannels.map(channel => channel.id)), [favoriteChannels]);
  const recentChannels = useMemo(() => resolveChannelReferences([...tvSession.recentChannelIds].reverse(), channelById), [tvSession, channelById]);
  const isLoadingTv = Boolean(busy && (busy.toLowerCase().includes("syncing") || busy.toLowerCase().includes("loading tv")));
  const playlistErrors: PlaylistError[] = useMemo(() => iptvSnapshot.playlistErrors
    ?? (iptvSnapshot.playlistWarnings ?? []).map((warning, index) => ({ playlistId: `warning-${index}`, ...splitPlaylistWarning(warning) })),
  [iptvSnapshot.playlistErrors, iptvSnapshot.playlistWarnings]);
  const hasWarnings = playlistErrors.length > 0;
  const resolvingSavedChannels = !iptvSnapshot.identitiesLoaded && Boolean(allChannels.length)
    && (favorites.length > favoriteChannels.length || tvSession.recentChannelIds.length > recentChannels.length);
  // Same helper the store stamps onto the snapshot, so both sides agree on when
  // a cached channel list still matches the configured playlists.
  const playlistSignature = iptvPlaylistSignature(playlists) + stalkerSourceSignature(settings);

  // Re-entering Live TV used to rebuild the whole snapshot every time — with a
  // large provider that is ~139k channels re-parsed and re-grouped on each
  // visit, measured at ~3.3s with ZERO network calls (the playlist text itself
  // is already cached). Reuse the snapshot that is still in memory and only
  // rebuild when the playlists actually changed, or when it has gone stale.
  useEffect(() => {
    if (!playlists.length && !livePortals.length) return;
    const snapshotMatchesPlaylists = iptvSnapshot.channels.length > 0
      && iptvSnapshot.signature === playlistSignature;
    const age = Date.now() - (iptvSnapshot.loadedAt ?? 0);
    if (snapshotMatchesPlaylists && age < IPTV_SNAPSHOT_TTL_MS) return;
    void refreshIptv();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [playlistSignature, refreshIptv, playlists.length]);

  const categories = useMemo(() => {
    const orderMap = new Map(settings.groupOrder.map((id, index) => [id, index]));
    const groupRank = (group: string, items: IptvChannel[]) => {
      const keyed = items[0] ? groupKey(items[0]) : group;
      return orderMap.get(keyed) ?? orderMap.get(group) ?? Number.MAX_SAFE_INTEGER;
    };
    const sortMode = settings.iptvSortOrder ?? "provider";
    const groupRows = Object.entries(groups)
      .map(([group, items]) => ({
        id: `group:${group}`,
        // Keys keep the playlist's name; the label is the name the user gave the category, if any.
        label: groupNames?.[group] || groupNameOf(group),
        custom: Boolean(groupNames?.[group]),
        count: items.length,
        favorite: isFavoriteGroup(favoriteGroups, group) || Boolean(items[0] && isFavoriteGroup(favoriteGroups, groupKey(items[0]))),
        hidden: hiddenGroups.includes(group) || Boolean(items[0] && hiddenGroups.includes(groupKey(items[0]))),
        rank: groupRank(group, items)
      }))
      .filter((group) => !group.hidden)
      .sort((a, b) => {
        if (Number(b.favorite) !== Number(a.favorite)) return Number(b.favorite) - Number(a.favorite);
        if (a.rank !== b.rank) return a.rank - b.rank;
        if (sortMode === "name") return a.label.localeCompare(b.label);
        if (sortMode === "number") return b.count - a.count || a.label.localeCompare(b.label);
        return 0;
      });
    return [
      { id: "favorites", label: "Favorites", custom: false, count: favoriteChannels.length, favorite: true, hidden: false },
      { id: "recent", label: "Recently Watched", custom: false, count: recentChannels.length, favorite: false, hidden: false },
      { id: "all", label: "All Channels", custom: false, count: channels.length, favorite: false, hidden: false },
      { id: "sports", label: "Sports", custom: false, count: 0, favorite: false, hidden: false },
      ...groupRows
    ];
  }, [channels.length, favoriteChannels.length, recentChannels.length, favoriteGroups, groups, hiddenGroups, settings.groupOrder, settings.iptvSortOrder, groupNames]);

  const categoryChannels = useMemo(() => {
    const base = activeCategory === "favorites"
      ? favoriteChannels
      : activeCategory === "recent" ? recentChannels
      : activeCategory.startsWith("group:")
        ? groups[activeCategory.slice(6)] ?? []
        : channels;
    const sortMode = settings.iptvSortOrder ?? "provider";
    if (activeCategory === "favorites" || activeCategory === "recent") return base;
    let sorted = base;
    if (sortMode === "number") {
      sorted = [...base].sort((a, b) => {
        const numA = a.number ? parseInt(a.number, 10) : Number.MAX_SAFE_INTEGER;
        const numB = b.number ? parseInt(b.number, 10) : Number.MAX_SAFE_INTEGER;
        if (numA !== numB) return numA - numB;
        return a.name.localeCompare(b.name);
      });
    } else if (sortMode === "name") sorted = [...base].sort((a, b) => a.name.localeCompare(b.name));
    // A manual channel order set in Settings overrides the sort for that category only (as on Android).
    if (activeCategory.startsWith("group:")) return applyChannelOrder(sorted, settings.iptvChannelOrder?.[activeCategory.slice(6)]);
    return sorted;
  }, [activeCategory, channels, favoriteChannels, recentChannels, groups, settings.iptvSortOrder, settings.iptvChannelOrder]);
  // Search also matches the titles of programmes whose guide is loaded.
  const nowNext = iptvSnapshot.nowNext;
  const needle = query.trim().toLowerCase();
  const visibleChannels = useMemo(() => needle
    ? categoryChannels.filter((channel) => channelMatchesQuery(channel, needle, nowNext[channel.id], hasGroupNames ? groupNames?.[groupKey(channel)] : undefined))
    : categoryChannels, [categoryChannels, needle, nowNext, groupNames, hasGroupNames]);

  useEffect(() => {
    if (!categories.some((category) => category.id === activeCategory)) setActiveCategory("all");
  }, [categories, activeCategory]);
  const renderedChannels = visibleChannels;
  const selectedChannel = channelById.get(selectedChannelId ?? "") ?? renderedChannels[0] ?? null;
  // While a channel plays in the docked mini-player the hero describes what is
  // playing; the focused row is a secondary "Selected" line.
  const heroChannel = activeChannel && !activeChannel.id.startsWith("sports-addon:") ? (channelById.get(activeChannel.id) ?? activeChannel) : selectedChannel;
  const heroGuide = heroChannel ? nowNext[heroChannel.id] : undefined;
  const secondaryChannel = heroChannel && selectedChannel && heroChannel.id !== selectedChannel.id ? selectedChannel : null;

  // Restore the last played channel, not every row crossed while browsing.
  useEffect(() => {
    setSelectedChannelId(tvSession.lastChannelId || loadStored<string | null>(lastChannelKey, null));
  }, [lastChannelKey, tvSession.lastChannelId]);

  const focusGuideRow = useCallback((channelId: string | null | undefined) => {
    requestAnimationFrame(() => {
      const root = listRef.current;
      if (!root) return;
      const row = channelId ? root.querySelector<HTMLElement>(`[data-channel-id="${CSS.escape(channelId)}"]`) : null;
      (row?.querySelector<HTMLElement>(".livetv-guide-channel, .livetv-row-main")
        ?? root.querySelector<HTMLElement>(".is-selected .livetv-guide-channel, .is-selected .livetv-row-main, [data-virtual-index] .livetv-guide-channel, [data-virtual-index] .livetv-row-main"))?.focus({ preventScroll: true });
    });
  }, []);

  const watchChannel = useCallback((channel: IptvChannel) => {
    setSelectedChannelId(channel.id);
    saveStored(lastChannelKey, channel.id);
    if (activeChannel?.id === channel.id) {
      window.dispatchEvent(new Event("arvio:expand-live-player"));
      blurIntoPlayer();
      return;
    }
    void playChannel(channel);
    blurIntoPlayer();
    if (!categoriesOpenByDefault(window.innerWidth)) setGroupsOpen(false);
  }, [lastChannelKey, playChannel, activeChannel?.id]);

  // Catch-up listings for the selected channel (channels the panel archives).
  useEffect(() => {
    if (!archiveOpen || !selectedChannel?.catchupDays || selectedChannel.catchupType !== "xtream") {
      setCatchup(null);
      return undefined;
    }
    let active = true;
    setCatchup({ channelId: selectedChannel.id, programs: [], loading: true });
    const timer = window.setTimeout(() => {
      void loadXtreamCatchup(settings.iptvPlaylists, selectedChannel)
        .then((programs) => { if (active) setCatchup({ channelId: selectedChannel.id, programs, loading: false }); })
        .catch(() => { if (active) setCatchup({ channelId: selectedChannel.id, programs: [], loading: false }); });
    }, 400);
    return () => { active = false; window.clearTimeout(timer); };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [selectedChannel?.id, archiveOpen]);
  useEffect(() => setArchiveOpen(false), [selectedChannel?.id]);

  // Guide loads lazily for rows as they scroll into view, batched so a fast
  // scroll doesn't fire hundreds of EPG requests. Rows whose request finished
  // without data say "No guide data"; until then they show a placeholder.
  const guideQueueRef = useRef(new Map<string, IptvChannel>());
  const guideTimerRef = useRef<number | null>(null);
  const nowNextRef = useRef(nowNext);
  nowNextRef.current = nowNext;
  const [guideSettled, setGuideSettled] = useState<Set<string>>(() => new Set());
  const requestGuide = useCallback((channel: IptvChannel) => {
    const entry = nowNextRef.current[channel.id];
    if (entry?.now && entry.now.endUtcMillis > Date.now()) return;
    guideQueueRef.current.set(channel.id, channel);
    if (guideTimerRef.current) return;
    guideTimerRef.current = window.setTimeout(() => {
      guideTimerRef.current = null;
      const batch = Array.from(guideQueueRef.current.values());
      guideQueueRef.current.clear();
      if (!batch.length) return;
      void loadIptvGuide(batch).finally(() => setGuideSettled((current) => {
        const next = new Set(current);
        for (const item of batch) next.add(item.id);
        return next;
      }));
    }, GUIDE_BATCH_DELAY_MS);
  }, [loadIptvGuide]);

  useEffect(() => () => {
    if (guideTimerRef.current) window.clearTimeout(guideTimerRef.current);
    // Effect replay/remount must not leave a cancelled timer blocking future batches.
    guideTimerRef.current = null;
    guideQueueRef.current.clear();
    setGuideSettled(new Set());
  }, [playlistSignature, lastChannelKey]);

  useEffect(() => {
    if (selectedChannel) requestGuide(selectedChannel);
  }, [selectedChannel, requestGuide]);

  // Synced lists go through updateSettings: it updates the store's settings
  // ref synchronously, so the playlist refresh below sees this edit (the raw
  // setter made a first playlist load the old, empty list).
  const settingsRef = useRef(settings);
  settingsRef.current = settings;
  const channelByIdRef = useRef(channelById);
  channelByIdRef.current = channelById;
  const toggleFavorite = useCallback((channelId: string) => {
    updateSettings({ favoriteChannelIds: toggleFavoriteChannel(settingsRef.current.favoriteChannelIds, channelId, channelByIdRef.current) });
  }, [updateSettings]);
  const moveFavorite = useCallback((channelId: string, direction: -1 | 1) => {
    updateSettings({ favoriteChannelIds: moveFavoriteChannel(settingsRef.current.favoriteChannelIds, channelId, direction, channelByIdRef.current) });
  }, [updateSettings]);
  const groupId = (group: string) => {
    const first = groups[group]?.[0];
    return first ? groupKey(first) : group;
  };
  const toggleGroupFavorite = (group: string) => updateSettings({ favoriteGroupIds: toggleFavoriteGroup(favoriteGroups, groupId(group)) });
  const toggleHiddenGroup = (group: string) => updateSettings({ hiddenGroupIds: toggleGroupKey(hiddenGroups, groupId(group)) });
  const hideChannelGroup = (channel: IptvChannel) => {
    const key = groupKey(channel);
    if (!settingsRef.current.hiddenGroupIds.includes(key)) updateSettings({ hiddenGroupIds: [key, ...settingsRef.current.hiddenGroupIds] });
    setToast(translateUi("Category hidden. Show it again from Playlists."));
  };

  const openSettings = () => { requestSourceSettings("tv"); setSection("settings"); };
  const openEditor = (id: string) => { setManaging(true); setEditRequest({ id, at: Date.now() }); };

  // Stable callbacks so memoised guide rows skip re-rendering when another row
  // is selected (most of an Arrow Down's cost with large playlists).
  const watchRef = useRef(watchChannel);
  watchRef.current = watchChannel;
  const playCatchupRef = useRef(playCatchup);
  playCatchupRef.current = playCatchup;
  const guideCallbacks: GuideCallbacks = useMemo(() => ({
    onFocusChannel: (channel) => setSelectedChannelId((current) => current === channel.id ? current : channel.id),
    onVisible: requestGuide,
    onPlay: (channel) => watchRef.current(channel),
    onCatchup: (channel, program) => playCatchupRef.current(channel, program),
    onToggleFavorite: (channel) => toggleFavorite(channel.id),
    onMenu: (channel, anchor) => setMenu({ channel, anchor }),
    groupName: groupNameFor
  }), [requestGuide, toggleFavorite, groupNameFor]);

  // Fullscreen zapping (keys arrive from the player via lib/liveZap.ts).
  const zapState = useRef({ list: renderedChannels, all: channels, active: activeChannel, lastZapAt: 0, digits: "", digitsAt: 0, info: null as IptvChannel | null });
  zapState.current.list = renderedChannels;
  zapState.current.all = channels;
  zapState.current.active = activeChannel;
  zapState.current.info = zapInfo;
  const digitTimer = useRef<number | null>(null);
  const infoTimer = useRef<number | null>(null);
  const showInfo = useCallback((channel: IptvChannel | null, ms = INFO_VISIBLE_MS) => {
    if (infoTimer.current) window.clearTimeout(infoTimer.current);
    setZapInfo(channel);
    zapState.current.info = channel;
    if (channel) infoTimer.current = window.setTimeout(() => { setZapInfo(null); zapState.current.info = null; }, ms);
  }, []);
  const tune = useCallback((channel: IptvChannel) => {
    keepLivePlayerExpanded();
    setSelectedChannelId(channel.id);
    saveStored(lastChannelKey, channel.id);
    void playChannel(channel);
    showInfo(channel, 3500);
  }, [lastChannelKey, playChannel, showInfo]);
  useEffect(() => registerLiveZap((action) => {
    const state = zapState.current;
    if (!state.active || state.active.id.startsWith("sports-addon:")) return false;
    const clearDigits = () => {
      if (digitTimer.current) window.clearTimeout(digitTimer.current);
      digitTimer.current = null;
      state.digits = "";
      setZapDigits("");
    };
    switch (action.type) {
      case "zap": {
        const now = Date.now();
        if (now - state.lastZapAt < MIN_ZAP_INTERVAL_MS) return true;
        state.lastZapAt = now;
        const list = state.list.some((channel) => channel.id === state.active!.id) ? state.list : state.all;
        const target = zapTarget(list, state.active.id, action.delta);
        if (target) tune(target);
        return true;
      }
      case "digit": {
        const now = Date.now();
        const digits = appendChannelDigit(state.digits, action.digit, state.digitsAt, now);
        state.digits = digits;
        state.digitsAt = now;
        const exact = exactChannelNumber(state.list, digits) ?? exactChannelNumber(state.all, digits);
        const prefixMatch = uniqueChannelNumberPrefix(state.list, digits) ?? uniqueChannelNumberPrefix(state.all, digits);
        // Tune at once only when no longer number could still be meant.
        if (exact && prefixMatch?.id === exact.id) { clearDigits(); tune(exact); return true; }
        setZapDigits(digits);
        if (digitTimer.current) window.clearTimeout(digitTimer.current);
        digitTimer.current = window.setTimeout(() => {
          const typed = state.digits;
          const target = exactChannelNumber(state.list, typed) ?? exactChannelNumber(state.all, typed)
            ?? uniqueChannelNumberPrefix(state.list, typed) ?? uniqueChannelNumberPrefix(state.all, typed);
          clearDigits();
          if (target) tune(target);
          else setToast(translateUi("No channel {value0}", { value0: typed }));
        }, DIGIT_COMMIT_MS);
        return true;
      }
      case "info":
        showInfo(state.info ? null : (channelByIdRef.current.get(state.active.id) ?? state.active));
        return true;
      case "back":
        if (state.digits || state.info) { clearDigits(); showInfo(null); return true; }
        // Not handled: the player docks back into the guide; return focus there.
        window.setTimeout(() => focusGuideRow(state.active?.id), 80);
        return false;
      default:
        return false;
    }
  }), [tune, showInfo, focusGuideRow, setToast, translateUi]);
  useEffect(() => () => {
    if (digitTimer.current) window.clearTimeout(digitTimer.current);
    if (infoTimer.current) window.clearTimeout(infoTimer.current);
  }, []);
  useEffect(() => { if (!activeChannel) { setZapDigits(""); showInfo(null); } }, [activeChannel, showInfo]);
  const zapDigitMatch = useMemo(() => zapDigits ? exactChannelNumber(renderedChannels, zapDigits) ?? uniqueChannelNumberPrefix(renderedChannels, zapDigits) : null, [zapDigits, renderedChannels]);

  const savePlaylist = (playlist: (typeof playlists)[number]) => {
    const current = settingsRef.current.iptvPlaylists;
    updateSettings({ iptvPlaylists: current.some((item) => item.id === playlist.id) ? current.map((item) => item.id === playlist.id ? playlist : item) : [playlist, ...current] });
    setManaging(false);
  };

  const activeCategoryEntry = categories.find((category) => category.id === activeCategory);
  // Names the user typed are shown as typed; built-in labels and playlist names go through translation as before.
  const categoryText = (category: { label: string; custom: boolean }) => category.custom ? category.label : translateUi(category.label);
  const activeCategoryLabel = activeCategoryEntry ? categoryText(activeCategoryEntry) : translateUi("All Channels");
  const renderCategory = (category: typeof categories[number]) => <button type="button"
    className={activeCategory === category.id ? "is-active" : ""} title={categoryText(category)} aria-current={activeCategory === category.id ? "true" : undefined}
    onClick={() => {
      setActiveCategory(category.id); setSelectedChannelId(null);
      if (window.matchMedia("(max-width: 760px)").matches) setGroupsOpen(false);
    }}>
    {category.id === "favorites" ? <Star size={20} /> : category.id === "recent" ? <History size={20} /> : category.id === "sports" ? <Trophy size={20} /> : <LayoutGrid size={20} />}
    <span>{categoryText(category)}</span>{category.id !== "sports" && <em>{resolvingSavedChannels && ["favorites", "recent"].includes(category.id) && !category.count ? <RefreshCw size={14} className="is-spinning" aria-label={translateUi("Loading saved channels")} /> : category.count.toLocaleString()}</em>}
  </button>;
  const openCategories = () => { setGroupsOpen(true); requestAnimationFrame(() => document.querySelector<HTMLElement>(".livetv-cats button.is-active")?.focus()); };
  const hasContent = channels.length > 0 || hasSportsAddons;
  const menuFavoriteIndex = menu ? favoritePosition(favorites, menu.channel.id, channelById) : -1;

  return (
    <div className="screen livetv-shell" onPointerDownCapture={() => { pointerNavigation.current = true; }} onKeyDownCapture={() => { pointerNavigation.current = false; }}>
      {!hasContent && <header className="livetv-topbar">
        <div className="livetv-heading">
          <h2>{translateUi("Live TV")}</h2>
          <span>{isLoadingTv ? translateUi("Loading channels…") : translateUi("No channels loaded")}</span>
        </div>
        <div className="livetv-topbar-actions">
          <button type="button" className="livetv-chipbtn" onClick={() => setManaging((value) => !value)} aria-expanded={managing}>
            <ListVideo size={17} /> {translateUi("Playlists")}</button>
          {(playlists.length > 0 || portals.length > 0) && <button type="button" className="livetv-chipbtn" onClick={() => void refreshIptv({ fresh: true })} disabled={isLoadingTv} aria-label={translateUi("Refresh channels")}>
            <RefreshCw size={17} className={isLoadingTv ? "is-spinning" : ""} /> {isLoadingTv ? translateUi("Refreshing") : translateUi("Refresh")}
          </button>}
        </div>
      </header>}

      {managing && <PlaylistManager
        playlists={playlists}
        hiddenGroups={hiddenGroups}
        groupNames={groupNames}
        portals={portals}
        reservedPortalIds={referencedStalkerPortalIds(settings)}
        userAgent={settings.customUserAgent}
        editRequest={editRequest}
        onSavePlaylist={savePlaylist}
        onRemovePlaylist={(id) => updateSettings({ iptvPlaylists: settingsRef.current.iptvPlaylists.filter((item) => item.id !== id) })}
        onTogglePlaylist={(id) => updateSettings({ iptvPlaylists: settingsRef.current.iptvPlaylists.map((item) => item.id === id ? { ...item, enabled: !item.enabled } : item) })}
        onSavePortals={(next, closeManager) => { updateSettings(stalkerPortalsPatch(next)); if (closeManager) setManaging(false); }}
        onShowGroup={(id) => updateSettings({ hiddenGroupIds: settingsRef.current.hiddenGroupIds.filter((item) => item !== id) })}
        onClose={() => setManaging(false)}
      />}

      <PlaylistErrors errors={playlistErrors} retrying={isLoadingTv} onEdit={openEditor} onRetry={() => void refreshIptv({ fresh: true })} onOpenSettings={openSettings} />

      {!playlists.length && !portals.length && !channels.length && !managing && !hasSportsAddons && (
        <section className="livetv-empty">
          <Tv size={44} />
          <h3>{translateUi("Add your IPTV playlist")}</h3>
          <p>{translateUi("Paste an M3U link or Xtream login. Playlists sync through ARVIO Cloud")}{auth ? "" : translateUi(" when you sign in")}.</p>
          <button type="button" className="primary" onClick={() => openEditor("new")}><Plus size={18} /> {translateUi("Add playlist")}</button>
        </section>
      )}

      {hasContent && (
        <div className={`livetv-columns tv-guide-workspace ${activeCategory === "sports" ? "sports-active" : ""} ${groupsOpen ? "" : "groups-collapsed"}`}>
          {groupsOpen && <button className="tv-drawer-scrim" type="button" aria-label={translateUi("Close categories")} onClick={() => setGroupsOpen(false)} />}
          <nav className="livetv-cats" aria-label={translateUi("Channel categories")} inert={!groupsOpen} onKeyDown={event => {
            const target = event.target as HTMLElement;
            if (target.matches("input, select, textarea")) return;
            if (event.key === "ArrowRight") {
              const first = listRef.current?.querySelector<HTMLElement>('.is-selected .livetv-guide-channel, .is-selected .livetv-row-main')
                ?? listRef.current?.querySelector<HTMLElement>('[data-virtual-index] .livetv-guide-channel, [data-virtual-index] .livetv-row-main, .tv-event-card');
              if (first) { event.preventDefault(); event.stopPropagation(); first.focus({ preventScroll: true }); }
            } else if (event.key === "ArrowLeft") {
              // The categories are the left edge of Live TV: stay here instead
              // of jumping to the profile avatar.
              event.preventDefault(); event.stopPropagation();
            }
          }}>
            {sourceCount > 1 && <div className="livetv-providers" role="group" aria-label={translateUi("Playlist provider")}>
              {[{ id: "all", name: translateUi("All playlists") }, ...livePortals.map((portal) => ({ id: portal.id, name: portal.name })), ...enabledPlaylists.map((playlist) => ({ id: playlist.id, name: playlist.name }))].map((item) => (
                <button type="button" key={item.id} aria-pressed={provider === item.id} className={provider === item.id ? "is-active" : ""}
                  onClick={() => { setProvider(item.id); setActiveCategory("all"); }}>{item.name}</button>
              ))}
            </div>}
            <div className="livetv-search"><Search size={18} />
              <input value={queryInput} onChange={event => setQueryInput(event.target.value)} placeholder={translateUi("Search channels")} aria-label={translateUi("Search channels")}
                onKeyDown={event => {
                  // Remote / keyboard: Down goes to the first destination below the box,
                  // Enter (or Right at the end of the text) jumps into the results.
                  const input = event.currentTarget;
                  const atEnd = input.selectionStart === input.value.length && input.selectionEnd === input.value.length;
                  if (event.key === "ArrowDown") {
                    const next = input.closest(".livetv-cats")?.querySelector<HTMLElement>(".tv-sidebar-destinations button, .tv-sidebar-destinations [role='button']");
                    if (next) { event.preventDefault(); event.stopPropagation(); next.focus({ preventScroll: false }); }
                  } else if (event.key === "Enter" || (event.key === "ArrowRight" && atEnd && input.value)) {
                    const first = listRef.current?.querySelector<HTMLElement>('[data-virtual-index] .livetv-guide-channel, [data-virtual-index] .livetv-row-main, .livetv-guide-channel, .livetv-row-main');
                    if (first) { event.preventDefault(); event.stopPropagation(); first.focus({ preventScroll: false }); }
                  }
                }} />
              {queryInput && <button type="button" onClick={() => setQueryInput("")} aria-label={translateUi("Clear search")}><X size={16} /></button>}
            </div>
            <div className="tv-sidebar-destinations" onKeyDown={event => {
              // Up from the first destination returns to the channel search box.
              if (event.key !== "ArrowUp") return;
              const items = [...event.currentTarget.querySelectorAll<HTMLElement>("button, [role='button']")];
              if (items[0] && items[0] === document.activeElement) {
                const input = event.currentTarget.closest(".livetv-cats")?.querySelector<HTMLInputElement>(".livetv-search input");
                if (input) { event.preventDefault(); event.stopPropagation(); input.focus(); }
              }
            }}>{categories.filter(category => !category.id.startsWith("group:")).map(category => <div key={category.id}>{renderCategory(category)}</div>)}</div>
            <div className="tv-sidebar-group-heading"><span>{translateUi("Categories")}</span>
              <button type="button" title={translateUi("Manage playlists")} aria-label={translateUi("Manage playlists")} onClick={() => setManaging(value => !value)} aria-expanded={managing}><ListVideo size={18} /></button>
              <button type="button" title={translateUi("Refresh channels")} aria-label={translateUi("Refresh channels")} disabled={isLoadingTv} onClick={() => void refreshIptv({ fresh: true })}><RefreshCw size={16} className={isLoadingTv ? "is-spinning" : ""} /></button>
            </div>
            <VirtualList items={categories.filter(category => category.id.startsWith("group:"))} estimate={56} itemKey={rowKey} label={translateUi("Categories")} renderItem={renderCategory} />
          </nav>

          <section ref={listRef} className="livetv-list" aria-label={translateUi(activeCategoryLabel)} onFocusCapture={event => {
            if (!pointerNavigation.current && !categoriesOpenByDefault(window.innerWidth) && (event.target as HTMLElement).closest(".livetv-guide-channel, .livetv-guide-block, .livetv-row-main, .tv-event-card")) setGroupsOpen(false);
          }} onKeyDown={(event) => {
            const target = event.target as HTMLElement;
            // The fullscreen player and dialogs own their keys.
            if (target.closest("dialog") || playerIsOpen()) return;
            if (event.key === "Escape" || (event.key === "ArrowLeft" && !target.closest(".livetv-guide-block") && !target.matches("input, select, textarea"))) {
              if (!groupsOpen || event.key === "ArrowLeft") { event.preventDefault(); event.stopPropagation(); openCategories(); }
            }
          }}>
            {activeCategory === "sports" ? <SportsGuidePane key={activeProfile?.id ?? "local"} clockFormat={settings.clockFormat} channels={channels} guide={nowNext} addons={addons}
              providerNames={Object.fromEntries([...playlists.map((playlist) => [playlist.id, playlist.name]), ...portals.map((portal) => [portal.id, portal.name])])}
              onPlay={channel => channel.id.startsWith("sports-addon:") ? void playChannel(channel) : watchChannel(channel)} onEnter={() => { if (!pointerNavigation.current && !categoriesOpenByDefault(window.innerWidth)) setGroupsOpen(false); }}
              onOpenCategories={openCategories} /> : <>
            <div className="livetv-list-head">
              <button type="button" className="livetv-chipbtn" title={translateUi("Toggle categories")} aria-label={translateUi("Toggle categories")} aria-expanded={groupsOpen} onClick={() => setGroupsOpen(!groupsOpen)}><PanelLeft size={20} /></button>
              <h3>{translateUi(activeCategoryLabel)}</h3>
              <span>{visibleChannels.length.toLocaleString()}</span>
              <div className="livetv-view-toggle" role="group" aria-label={translateUi("Channel view")}>
                <button type="button" className={view === "list" ? "is-active" : ""} aria-pressed={view === "list"} onClick={() => setView("list")} title={translateUi("List view")} aria-label={translateUi("List view")}><List size={18} /></button>
                <button type="button" className={view === "guide" ? "is-active" : ""} aria-pressed={view === "guide"} onClick={() => setView("guide")} title={translateUi("Guide view")} aria-label={translateUi("Guide view")}><LayoutGrid size={18} /></button>
              </div>
              {activeCategory.startsWith("group:") && (
                <div className="livetv-group-actions">
                  <button type="button" onClick={() => toggleGroupFavorite(activeCategory.slice(6))} aria-label={translateUi("Favorite this category")} title={translateUi("Favorite this category")}><Star size={15} /></button>
                  <button type="button" onClick={() => toggleHiddenGroup(activeCategory.slice(6))} aria-label={translateUi("Hide this category")} title={translateUi("Hide this category")}>
                    {hiddenGroups.includes(activeCategory.slice(6)) ? <Eye size={15} /> : <EyeOff size={15} />}
                  </button>
                </div>
              )}
            </div>
            {renderedChannels.length === 0 && (
              <div className="livetv-list-empty">
                <Search size={28} />
                <p>{resolvingSavedChannels && ["favorites", "recent"].includes(activeCategory) ? translateUi("Loading saved channels...")
                  : needle ? translateUi("No channels or programmes match \"{value0}\".", { value0: query.trim() })
                  : activeCategory === "favorites" ? translateUi("No favorite channels yet. Hold OK on a channel, or use its star, to add one.")
                  : translateUi("No channels in this category.")}</p>
                {queryInput.trim() && <button type="button" className="secondary" onClick={() => setQueryInput("")}>{translateUi("Clear search")}</button>}
              </div>
            )}
            {view === "list" ? (
              <div className="livetv-rows">
                <VirtualList key={`${provider}:${activeCategory}`} items={renderedChannels} itemKey={rowKey} label={translateUi("Channels")} estimate={86} renderItem={(channel) => (
                  <ChannelRow
                    channel={channel}
                    guide={nowNext[channel.id]}
                    favorite={favoriteIds.has(channel.id)}
                    selected={selectedChannel?.id === channel.id}
                    playing={activeChannel?.id === channel.id}
                    clockFormat={settings.clockFormat}
                    callbacks={guideCallbacks}
                  />
                )} />
              </div>
            ) : (
              <GuideGrid
                key={`${provider}:${activeCategory}`}
                resetKey={needle}
                channels={renderedChannels}
                favorites={favoriteIds}
                nowNext={nowNext}
                settled={guideSettled}
                selectedId={selectedChannel?.id ?? null}
                playingId={activeChannel?.id ?? null}
                clockFormat={settings.clockFormat}
                callbacks={guideCallbacks}
              />
            )}
            </>}
          </section>

          {activeCategory !== "sports" && <aside className="livetv-detail" aria-label={translateUi("Channel details")}>
            {heroChannel ? (
              <>
                <div id="live-tv-player-dock" className={`livetv-detail-art ${activeChannel ? "has-live-playback" : ""}`} aria-label={translateUi("Live player")}>
                  <ChannelLogo channel={heroChannel} size={48} />
                  {!activeChannel && <button type="button" className="livetv-preview-play" aria-label={translateUi("Play {value0}", { value0: heroChannel.name })} title={translateUi("Play {value0}", { value0: heroChannel.name })} onClick={() => watchChannel(heroChannel)}><Play size={28} fill="currentColor" /></button>}
                </div>
                {activeChannel && <p className="livetv-detail-playing"><span className="livetv-live-dot" />{translateUi("Playing")}</p>}
                <p className="livetv-detail-group">{groupNameFor(heroChannel) || translateUi("Live TV")}</p>
                <ChannelProgramInfo channel={heroChannel} guide={heroGuide} formatTime={(ms) => formatGuideTime(ms, settings.clockFormat)} />
                {secondaryChannel && <div className="livetv-detail-selected">
                  <span>{translateUi("Selected")}</span>
                  <strong>{secondaryChannel.number ? `${secondaryChannel.number} · ` : ""}{secondaryChannel.name}</strong>
                  {nowNext[secondaryChannel.id]?.now?.title && <em>{nowNext[secondaryChannel.id]!.now!.title}</em>}
                  <button type="button" className="secondary" onClick={() => watchChannel(secondaryChannel)}><Play size={15} fill="currentColor" /> {translateUi("Watch")}</button>
                </div>}
                {selectedChannel && <div className="livetv-detail-actions">
                  {favoriteIds.has(selectedChannel.id) && <><button className="secondary" type="button" title={translateUi("Move favorite up")} aria-label={translateUi("Move favorite up")} disabled={favoritePosition(favorites, selectedChannel.id, channelById) <= 0} onClick={() => moveFavorite(selectedChannel.id, -1)}><ArrowUp size={17} /></button><button className="secondary" type="button" title={translateUi("Move favorite down")} aria-label={translateUi("Move favorite down")} disabled={favoritePosition(favorites, selectedChannel.id, channelById) >= favorites.length - 1} onClick={() => moveFavorite(selectedChannel.id, 1)}><ArrowDown size={17} /></button></>}
                  {!secondaryChannel && <button type="button" className="primary" onClick={() => watchChannel(selectedChannel)}><Play size={17} fill="currentColor" /> {activeChannel?.id === selectedChannel.id ? translateUi("Full screen") : translateUi("Watch")}</button>}
                  <button type="button" className="secondary" onClick={() => openChannelExternally(selectedChannel, "vlc")}>
                    <ExternalLink size={17} /> {translateUi("VLC")}</button>
                  <button
                    type="button"
                    className={favoriteIds.has(selectedChannel.id) ? "secondary is-active" : "secondary"}
                    aria-label={favoriteIds.has(selectedChannel.id) ? translateUi("Remove selected favorite") : translateUi("Add selected favorite")}
                    title={favoriteIds.has(selectedChannel.id) ? translateUi("Remove favorite") : translateUi("Add favorite")}
                    onClick={() => toggleFavorite(selectedChannel.id)}
                  >
                    <Star size={17} fill={favoriteIds.has(selectedChannel.id) ? "currentColor" : "none"} />
                  </button>
                  {Boolean(selectedChannel.catchupDays) && <button type="button" className="secondary" aria-label={translateUi("Show catch-up archive")} aria-expanded={archiveOpen} onClick={() => setArchiveOpen(value => !value)}><History size={17} /> {translateUi("Catch-up")}</button>}
                </div>}
                {selectedChannel && archiveOpen && catchup?.channelId === selectedChannel.id && (
                  <div className="livetv-catchup">
                    <p className="livetv-catchup-head"><History size={14} /> {translateUi("Catch-up")}{selectedChannel.catchupDays ? translateUi(" · {value0}d archive", { value0: selectedChannel.catchupDays }) : ""}</p>
                    {catchup.loading && <p className="livetv-detail-empty">{translateUi("Loading archive…")}</p>}
                    {!catchup.loading && !catchup.programs.length && <p className="livetv-detail-empty">{translateUi("No archive available.")}</p>}
                    {catchup.programs.map((program) => (
                      <button type="button" key={`${program.startUtcMillis}`} className="livetv-catchup-row" onClick={() => playCatchup(selectedChannel, program)}>
                        <CalendarClock size={14} />
                        <span>
                          <strong>{program.title}</strong>
                          <em>{formatGuideDay(program.startUtcMillis, true, settings.clockFormat)}</em>
                        </span>
                        <Play size={13} fill="currentColor" />
                      </button>
                    ))}
                  </div>
                )}
              </>
            ) : (
              <div className="livetv-detail-empty-state">
                <Tv size={44} />
                <p>{translateUi("Select a channel to see the guide.")}</p>
              </div>
            )}
          </aside>}
        </div>
      )}

      {(playlists.length > 0 || portals.length > 0) && !channels.length && !hasWarnings && activeCategory !== "sports" && (
        <section className="livetv-empty">
          <ChevronDown size={36} className={isLoadingTv ? "is-spinning" : ""} />
          <h3>{isLoadingTv ? translateUi("Loading channels…") : translateUi("No channels yet")}</h3>
          <p>{isLoadingTv ? translateUi("Big playlists can take a few seconds.") : translateUi("Refresh, or double-check the playlist details with your provider.")}</p>
          {!isLoadingTv && <button type="button" className="secondary" onClick={() => void refreshIptv({ fresh: true })}><RefreshCw size={16} /> {translateUi("Refresh")}</button>}
        </section>
      )}

      {menu && <ChannelMenu
        state={menu}
        groupName={groupNameFor(menu.channel)}
        favorite={favoriteIds.has(menu.channel.id)}
        canMoveUp={menuFavoriteIndex > 0}
        canMoveDown={menuFavoriteIndex >= 0 && menuFavoriteIndex < favorites.length - 1}
        onPlay={() => watchChannel(menu.channel)}
        onToggleFavorite={() => toggleFavorite(menu.channel.id)}
        onMove={(direction) => moveFavorite(menu.channel.id, direction)}
        onHideGroup={() => hideChannelGroup(menu.channel)}
        onClose={() => setMenu(null)}
      />}
      <LiveZapOverlay digits={zapDigits} digitMatch={zapDigitMatch} info={zapInfo} guide={zapInfo ? nowNext[zapInfo.id] : undefined} clockFormat={settings.clockFormat} />
    </div>
  );
}
