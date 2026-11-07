"use client";
import { useMemo } from "react";
import { LanguageProvider } from "./i18n";
import { iptvPlaylistSignature } from "./iptv";
import { isCurrentIptvSnapshot } from "./iptvSession";
import { stalkerSourceSignature } from "./stalkerPortals";
import { AppStoreContext, useAppStoreHandle } from "./store/context";
import { useStoreCore } from "./store/core";
import { useRefreshData, useRefreshTriggers } from "./store/refresh";
import { emptyIptv, useIptvState } from "./store/iptv";
import { useAccountActions, useAuthCallback } from "./store/account";
import { useSettingsAutosave, useUpdateSettings } from "./store/settingsSync";
import { useProfileActions, useProfileSync } from "./store/profiles";
import { useLibraryActions, useWatchState } from "./store/library";
import { useDetailsSources } from "./store/details";
import { usePlaybackActions } from "./store/playback";
import { useAddonActions } from "./store/addons";
import { useTrackingActions } from "./store/tracking";
import { useContextMenu } from "./store/ui";
import type { AppStore, AppView } from "./store/types";

// The app store. State lives in one provider (so every action keeps the exact
// closures and effect order it always had), split by concern into lib/store/*:
//   core.ts          all useState/useRef declarations and the small sync effects
//   refresh.ts       refreshData (cloud + tracker pull) and when it runs
//   iptv.ts          Live TV snapshot, guide and the Home "Favorite TV" row
//   account.ts       sign-in/out and the auth portal callback
//   settingsSync.ts  updateSettings and the settings autosave/outbox
//   profiles.ts      profiles: cloud pull, save, select/create/edit/delete
//   library.ts       watched state, watchlist and Continue Watching actions
//   details.ts       openDetails and the source search (addons, VOD, home server, Telegram)
//   playback.ts      playStream, next episode, trailers, Live TV and catch-up
//   addons.ts        install/remove/toggle addons, catalog rows
//   tracking.ts      Trakt / Simkl / MDBList connections and lists
//   ui.ts            the media context menu
// Consumers subscribe through a stable handle (store/context.ts): useAppPick
// and useAppSelector re-render a component only when the fields it reads change;
// useApp() remains for code that wants every field.
export { authClient, traktClient } from "./store/clients";
export { ProfileCloudSaveError } from "./store/profiles";
export { defaultSettings, getPriorityConfig } from "./store/settings";
export type { AppStore, AppView, ContextMenuTarget } from "./store/types";
export { AppContext, useApp, useAppPick, useAppSelector } from "./store/context";

export function AppProvider({
  children,
  initialView,
  cloudLoginRequired = false
}: {
  children: React.ReactNode;
  initialView?: AppView;
  cloudLoginRequired?: boolean;
}) {
  const core = useStoreCore(initialView);
  const {
    view, cloudProfilesHydrated, profiles, activeProfile, activeProfileId, avatarImages, manageMode, setManageMode,
    section, setSection, categories, catalogConfigs, homeServerRows, continueWatching, watchlist, selected, streams, selectedEpisode,
    activeStream, activeChannel, addons, addonsReady, iptvSnapshot, query, setQuery, results, searchState, settingsSyncState,
    settings, setSettings, auth, traktConnected, mdblistConnected, simklConnected, trackingPreferences, deviceCode, simklDeviceCode,
    busy, toast, setToast
  } = core;
  const { refreshData } = useRefreshData(core);
  const { refreshIptv, favoriteTvChannels, loadIptvGuide } = useIptvState(core);
  useAuthCallback(core);
  useRefreshTriggers({ ...core, refreshData });
  useSettingsAutosave(core);
  useProfileSync({ ...core, refreshData });
  const { updateSettings } = useUpdateSettings(core);
  const { isWatched, markWatchedLocally } = useWatchState(core);
  const { mergeStreams, appendVodSources, appendHomeServerSources, appendTelegramSources, openDetails, loadEpisodeStreams, closeDetails } = useDetailsSources(core);
  const { playStream, advanceEpisode, playTrailer, playChannel, recordChannelPlayback, playCatchup, closePlayer } =
    usePlaybackActions({ ...core, mergeStreams, appendVodSources, appendHomeServerSources, appendTelegramSources });
  const { loadCatalogRow, installAddon, removeAddon, setAddonsState } = useAddonActions({ ...core, updateSettings });
  const { signIn, signOut } = useAccountActions({ ...core, refreshData });
  const {
    beginTrakt, pollTrakt, disconnectTrakt, connectMdblist, disconnectMdblist, beginSimkl, pollSimkl, disconnectSimkl,
    updateTrackingPreferences, loadTraktLists, loadTraktListItems, loadTrackerLibrary
  } = useTrackingActions({ ...core, refreshData });
  const {
    selectProfile, createProfile, updateProfileAction, deleteProfileAction, switchProfile, goToLogin, backToProfiles
  } = useProfileActions({ ...core, refreshData });
  const { activeContextMenu, openContextMenu, closeContextMenu } = useContextMenu();
  const { toggleWatchlist, toggleWatched, removeFromContinueWatching } = useLibraryActions({ ...core, isWatched, markWatchedLocally });
  const managedStatus = useMemo(() => {
    const delivery = core.managed.delivery;
    return delivery ? { managed: delivery.managed, active: delivery.active, name: delivery.name, permissions: delivery.permissions, reason: delivery.reason } : null;
  }, [core.managed.delivery]);

  const value = useMemo<AppStore>(() => ({
    view,
    partnerLinkReady: view === "app" && Boolean(activeProfile) && (!auth || cloudProfilesHydrated),
    cloudLoginRequired,
    managedStatus,
    profiles,
    activeProfile,
    avatarImages,
    manageMode,
    setManageMode,
    selectProfile,
    createProfile,
    updateProfile: updateProfileAction,
    deleteProfile: deleteProfileAction,
    switchProfile,
    goToLogin,
    backToProfiles,
    section,
    setSection,
    categories,
    catalogConfigs,
    loadCatalogRow,
    homeServerRows,
    continueWatching,
    watchlist,
    isWatched,
    markWatchedLocally,
    selected,
    streams,
    selectedEpisode,
    loadEpisodeStreams,
    advanceEpisode,
    activeStream,
    activeChannel,
    addons,
    addonsReady,
    iptvSnapshot: isCurrentIptvSnapshot(iptvSnapshot, `${auth?.userId ?? "local"}:${activeProfileId ?? "local"}`,
      iptvPlaylistSignature(settings.iptvPlaylists) + stalkerSourceSignature(settings)) ? iptvSnapshot : emptyIptv,
    query,
    setQuery,
    results,
    searchState,
    settingsSyncState,
    settings,
    setSettings,
    updateSettings,
    auth,
    traktConnected,
    mdblistConnected,
    simklConnected,
    trackingPreferences,
    updateTrackingPreferences,
    deviceCode,
    simklDeviceCode,
    busy,
    toast,
    setToast,
    refreshData,
    refreshIptv,
    loadIptvGuide,
    favoriteTvChannels,
    openDetails,
    closeDetails,
    playStream,
    playTrailer,
    playChannel,
    recordChannelPlayback,
    playCatchup,
    closePlayer,
    installAddon,
    removeAddon,
    setAddonsState,
    signIn,
    signOut,
    beginTrakt,
    pollTrakt,
    disconnectTrakt,
    connectMdblist,
    disconnectMdblist,
    beginSimkl,
    pollSimkl,
    disconnectSimkl,
    loadTraktLists,
    loadTraktListItems,
    loadTrackerLibrary,
    toggleWatchlist,
    toggleWatched,
    removeFromContinueWatching,
    activeContextMenu,
    openContextMenu,
    closeContextMenu
  }), [
    view, cloudProfilesHydrated, cloudLoginRequired, managedStatus, profiles, activeProfile, activeProfileId, avatarImages, manageMode,
    selectProfile, createProfile, updateProfileAction, deleteProfileAction, switchProfile, goToLogin, backToProfiles,
    section, categories, catalogConfigs, loadCatalogRow, homeServerRows, continueWatching, watchlist, isWatched, selected, streams, selectedEpisode, loadEpisodeStreams, advanceEpisode, activeStream, activeChannel,
    addons, addonsReady, iptvSnapshot, query, results, searchState, settingsSyncState, settings, auth, traktConnected, mdblistConnected, simklConnected, trackingPreferences, deviceCode, simklDeviceCode, busy, toast,
    updateSettings, refreshData, openDetails, closeDetails, playStream, playTrailer, playChannel, recordChannelPlayback, playCatchup, closePlayer,
    refreshIptv, loadIptvGuide, favoriteTvChannels,
    installAddon, removeAddon, setAddonsState, signIn, signOut, beginTrakt, pollTrakt, disconnectTrakt,
    connectMdblist, disconnectMdblist, beginSimkl, pollSimkl, disconnectSimkl, updateTrackingPreferences,
    loadTraktLists, loadTraktListItems, loadTrackerLibrary,
    toggleWatchlist, toggleWatched, removeFromContinueWatching, activeContextMenu, openContextMenu, closeContextMenu
  ]);

  const handle = useAppStoreHandle(value);
  return <AppStoreContext.Provider value={handle}><LanguageProvider language={settings.language}>{children}</LanguageProvider></AppStoreContext.Provider>;
}
