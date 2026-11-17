"use client";

import { memo, useEffect, useRef, useState } from "react";
import dynamic from "next/dynamic";
import { useAppPick } from "@/lib/store";
import { kidsLevelOf } from "@/lib/kids";
import { config } from "@/lib/config";
import { ACCENTS } from "@/lib/accents";
import { useTranslation } from "@/lib/i18n";
import { focusAfterViewChange, installTvNav } from "@/lib/tvNav";
import { watchHoverCapability } from "@/lib/deviceClass";
import { HomeScreen as HomeScreenView } from "@/components/home/HomeScreen";
import { ProfileSelectionScreen } from "@/components/profile/ProfileSelectionScreen";
import { HomeScreenTip } from "@/components/player/HomeScreenTip";
import { BackHandler as BackHandlerView } from "./BackHandler";
import { ExternalPlaybackPrompt as ExternalPlaybackPromptView } from "./ExternalPlaybackPrompt";
import { MediaContextMenu as MediaContextMenuView } from "./MediaContextMenu";
import { NoAddonsPrompt as NoAddonsPromptView } from "./NoAddonsPrompt";
import { EntitlementGate, PaywallScreen } from "./Paywall";
import { Toast as ToastView } from "./Toast";
import { TopNav as TopNavView } from "./TopNav";
import { PremiumUsage as PremiumUsageView } from "./PremiumUsage";
import { PartnerLinkHandler as PartnerLinkHandlerView } from "./PartnerLinkHandler";
import { ScreenBoundary } from "./ErrorBoundary";
import { OnScreenKeyboard } from "./OnScreenKeyboard";
import { UpdateBanner } from "./UpdateBanner";
import { notifySectionNavigation } from "./UpdateWatcher";
import { XboxControllerTip } from "./XboxControllerTip";
import { OfflineNotice } from "./OfflineNotice";
import { PremiumPurchaseIntent } from "./PremiumPurchaseIntent";

// The shell re-renders whenever the view, section, details or player change.
// Its always-mounted children subscribe to the store fields they show
// (useAppPick), so they are memoized here: a shell render alone (opening a
// title, starting a channel) no longer re-renders the nav, the toast, the
// context menu and every screen with it.
const HomeScreen = memo(HomeScreenView);
const BackHandler = memo(BackHandlerView);
const ExternalPlaybackPrompt = memo(ExternalPlaybackPromptView);
const MediaContextMenu = memo(MediaContextMenuView);
const NoAddonsPrompt = memo(NoAddonsPromptView);
const Toast = memo(ToastView);
const TopNav = memo(TopNavView);
const PremiumUsage = memo(PremiumUsageView);
const PartnerLinkHandler = memo(PartnerLinkHandlerView);

// Code splitting: Home (the first screen after the profile picker) ships in the
// first-load bundle; every other section, the details page and the player are
// separate chunks fetched on first use. The player chunk (plus hls.js and
// mediabunny) is prefetched once a details page opens or playback starts —
// see PaidPlayerWarmup.

function ScreenLoading() {
  const translateUi = useTranslation();
  return (
    <div className="screen-loading" role="status" aria-label={translateUi("Loading")}>
      <span className="screen-loading-spinner" aria-hidden="true" />
    </div>
  );
}

function PlayerLoading() {
  return <div className="player-loading-shell" aria-hidden="true"><span className="screen-loading-spinner" /></div>;
}

const loadPlayer = () => import("@/components/player/PlayerOverlay");

const SearchScreen = memo(dynamic(() => import("@/components/search/SearchScreen").then((m) => m.SearchScreen), { ssr: false, loading: () => <ScreenLoading /> }));
const WatchlistScreen = memo(dynamic(() => import("@/components/watchlist/WatchlistScreen").then((m) => m.WatchlistScreen), { ssr: false, loading: () => <ScreenLoading /> }));
const LiveTvScreen = memo(dynamic(() => import("@/components/livetv/LiveTvScreen").then((m) => m.LiveTvScreen), { ssr: false, loading: () => <ScreenLoading /> }));
const AddonsScreen = memo(dynamic(() => import("@/components/addons/AddonsScreen").then((m) => m.AddonsScreen), { ssr: false, loading: () => <ScreenLoading /> }));
const SettingsScreen = memo(dynamic(() => import("@/components/settings/SettingsScreen").then((m) => m.SettingsScreen), { ssr: false, loading: () => <ScreenLoading /> }));
const DetailsDrawer = memo(dynamic(() => loadDetails().then((m) => m.DetailsDrawer), { ssr: false, loading: () => <ScreenLoading /> }));
const LoginScreen = memo(dynamic(() => import("@/components/login/LoginScreen").then((m) => m.LoginScreen), { ssr: false, loading: () => <ScreenLoading /> }));
const PlayerOverlay = memo(dynamic(() => loadPlayer().then((m) => m.PlayerOverlay), { ssr: false, loading: () => <PlayerLoading /> }));

/**
 * Preload the playback stack (player chunk, hls.js, mediabunny) — but only
 * once someone is heading for playback: a details page opened or Play pressed.
 * Browsing visitors never download it.
 */
function PaidPlayerWarmup({ active }: { active: boolean }) {
  const warmed = useRef(false);
  useEffect(() => {
    if (!active || warmed.current) return undefined;
    warmed.current = true;
    const warm = () => {
      void loadPlayer().catch(() => undefined);
      void import("hls.js").catch(() => undefined);
      void import("mediabunny").catch(() => undefined);
    };
    // Start after the details page had its moment: these modules are large,
    // and parsing them alongside the details chunk made opening a title slow.
    let idle = 0;
    const timer = window.setTimeout(() => {
      if (typeof window.requestIdleCallback === "function") idle = window.requestIdleCallback(warm, { timeout: 8000 });
      else warm();
    }, 2500);
    return () => {
      window.clearTimeout(timer);
      if (idle) window.cancelIdleCallback(idle);
    };
  }, [active]);
  return null;
}

const loadDetails = () => import("@/components/details/DetailsDrawer");

/**
 * The details page is the most common next step from any screen and its chunk
 * is small (~40 kB). Fetch it as soon as the app view mounts — before Home's
 * rails start their burst of metadata requests, which would otherwise queue
 * the chunk behind them on HTTP/1.1 connections (seen: 10 s to open a title).
 * It still stays out of the first-load bundle.
 */
function DetailsPrefetch() {
  useEffect(() => {
    void loadDetails().catch(() => undefined);
  }, []);
  return null;
}

/** Overlays that live outside any one screen (all views). */
const ShellOverlays = memo(function ShellOverlays() {
  return (
    <>
      <OnScreenKeyboard />
      <UpdateBanner />
      <XboxControllerTip />
      <OfflineNotice />
      <PremiumPurchaseIntent />
    </>
  );
});

export function AppShell() {
  const { auth, goToLogin, signOut, view, section, setSection, settings, selected, activeStream, activeChannel, activeProfile, closePlayer } = useAppPick("auth", "goToLogin", "signOut", "view", "section", "setSection", "settings", "selected", "activeStream", "activeChannel", "activeProfile", "closePlayer");
  const kidsProfile = Boolean(kidsLevelOf(activeProfile));
  // A kids profile never stays on Live TV, however it got there (a saved section, a link).
  useEffect(() => {
    if (kidsProfile && section === "tv") setSection("home");
  }, [kidsProfile, section, setSection]);
  const [mounted, setMounted] = useState(false);
  const [collectionOpen, setCollectionOpen] = useState(false);
  // The player chunk mounts on first playback and then stays mounted (it keeps
  // its own state between streams, as before code splitting).
  const [playerMounted, setPlayerMounted] = useState(false);
  useEffect(() => {
    if (activeStream) setPlayerMounted(true);
  }, [activeStream]);

  useEffect(() => {
    setMounted(true);
  }, []);

  // D-pad / keyboard spatial navigation for TV browsers (Tizen, webOS, Xbox)
  // and desktop keyboards: arrows move focus, Enter activates, TV Back runs
  // the Back ladder. The gamepad layer (HTPC mode: Xbox Ally, Steam Deck, any
  // pad) feeds the same engine, so a controller drives the whole app.
  useEffect(() => {
    installTvNav();
    const stopHoverWatch = watchHoverCapability();
    void import("@/lib/gamepadNav").then((mod) => mod.installGamepadNav()).catch(() => undefined);
    return stopHoverWatch;
  }, []);

  useEffect(() => {
    document.documentElement.style.scrollBehavior = settings.smoothScrolling ? "smooth" : "auto";
  }, [settings.smoothScrolling]);

  // Starting focus after every view change: the control that triggered it
  // usually unmounted, leaving focus on <body> (remotes then had nowhere to
  // start from, and screen readers announced nothing).
  const playerOpen = Boolean(activeStream);
  useEffect(() => {
    if (!mounted || playerOpen) return undefined;
    return focusAfterViewChange();
  }, [mounted, view, section, selected?.id, selected?.mediaType, playerOpen]);

  // Switching top-level sections is a safe moment for a pending app update.
  const previousSection = useRef(section);
  useEffect(() => {
    if (previousSection.current === section) return undefined;
    previousSection.current = section;
    // After BackHandler has written the new route, so the reload lands here.
    const timer = window.setTimeout(notifySectionNavigation, 0);
    return () => window.clearTimeout(timer);
  }, [section]);

  if (!mounted) {
    return (
      <main className="app-boot">
        <img src="/arvio-logo.svg" alt="" className="app-boot-logo" />
        <img src="/arvio-wordmark.svg" alt="ARVIO" className="app-boot-wordmark" />
      </main>
    );
  }

  // Show the product before requesting a login. After signing in, the app
  // opens in explore mode (see EntitlementGate).
  if (config.paywallEnabled && !auth) {
    return <><PaywallScreen state={null} accountId={null} isSignedIn={false} onEntitled={() => {}} onConnect={goToLogin} onSignOut={signOut} /><ShellOverlays /></>;
  }
  if (view === "login") return <><ScreenBoundary screen="login"><LoginScreen /></ScreenBoundary><ShellOverlays /></>;

  const accent = ACCENTS[settings.accentColor] ?? ACCENTS.arctic;

  // Explore mode: signed-in accounts use the app; the gate asks for Premium
  // only when something would play. Self-hosted builds and the Android app
  // remain unchanged.
  return (
    <EntitlementGate>
    <PaidPlayerWarmup active={Boolean(selected) || Boolean(activeStream)} />
    {view === "app" && <DetailsPrefetch />}
    {view === "profiles" ? <ScreenBoundary screen="profiles"><ProfileSelectionScreen /></ScreenBoundary> :
    <main
      className={`app-shell ${settings.oledBlack ? "oled" : ""} ${settings.spoilerBlur ? "spoiler-blur" : ""}`}
      style={{ ["--accent" as string]: accent }}
    >
      {(!activeStream || (section === "tv" && activeChannel)) && <TopNav />}
      <PartnerLinkHandler />

      <section className="content">
        {section === "home" && (!selected || collectionOpen) && (
          <div hidden={Boolean(selected)}>
            <ScreenBoundary screen="home"><NoAddonsPrompt /><HomeScreen onCollectionOpenChange={setCollectionOpen} /></ScreenBoundary>
          </div>
        )}
        {selected ? (
          <ScreenBoundary screen="details" resetKey={`${selected.mediaType}:${selected.id}`}><DetailsDrawer /></ScreenBoundary>
        ) : (
          <>
            {section === "search" && <ScreenBoundary screen="search"><SearchScreen /></ScreenBoundary>}
            {section === "watchlist" && <ScreenBoundary screen="library"><WatchlistScreen /></ScreenBoundary>}
            {section === "tv" && !kidsProfile && <ScreenBoundary screen="tv"><LiveTvScreen /></ScreenBoundary>}
            {section === "addons" && <ScreenBoundary screen="addons"><AddonsScreen /></ScreenBoundary>}
            {section === "settings" && <ScreenBoundary screen="settings"><SettingsScreen /></ScreenBoundary>}
          </>
        )}
      </section>

      {playerMounted ? (
        <ScreenBoundary screen="player" resetKey={activeStream?.url} overlay onDismiss={closePlayer}><PlayerOverlay /></ScreenBoundary>
      ) : (
        // Before the first playback the player chunk is not loaded; the
        // Add-to-Home-Screen tip it normally hosts renders on its own.
        <HomeScreenTip playerOpen={false} />
      )}
      <ExternalPlaybackPrompt />
      <MediaContextMenu />
      <PremiumUsage />
      <BackHandler />
      <Toast />
    </main>
    }
    <ShellOverlays />
    </EntitlementGate>
  );
}
