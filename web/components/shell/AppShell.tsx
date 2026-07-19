"use client";

import { useEffect, useState } from "react";
import { useApp } from "@/lib/store";
import { AddonsScreen } from "@/components/addons/AddonsScreen";
import { DetailsDrawer } from "@/components/details/DetailsDrawer";
import { HomeScreen } from "@/components/home/HomeScreen";
import { LiveTvScreen } from "@/components/livetv/LiveTvScreen";
import { LoginScreen } from "@/components/login/LoginScreen";
import { PlayerOverlay } from "@/components/player/PlayerOverlay";
import { ProfileSelectionScreen } from "@/components/profile/ProfileSelectionScreen";
import { SearchScreen } from "@/components/search/SearchScreen";
import { SettingsScreen } from "@/components/settings/SettingsScreen";
import { WatchlistScreen } from "@/components/watchlist/WatchlistScreen";
import { BackHandler } from "./BackHandler";
import { ExternalPlaybackPrompt } from "./ExternalPlaybackPrompt";
import { Toast } from "./Toast";
import { TopNav } from "./TopNav";

const ACCENTS: Record<string, string> = {
  arctic: "#ededed",
  gold: "#ffcd3c",
  green: "#00d588",
  blue: "#3b82f6",
  purple: "#8b5cf6"
};

export function AppShell() {
  const { view, section, settings, selected, activeStream } = useApp();
  const [mounted, setMounted] = useState(false);

  useEffect(() => {
    setMounted(true);
  }, []);

  // Warm the lazy player libraries during idle time so the first Play press
  // doesn't pay their download cost. They stay out of the initial bundle.
  useEffect(() => {
    const warm = () => {
      void import("hls.js").catch(() => undefined);
      void import("mediabunny").catch(() => undefined);
    };
    if (typeof window.requestIdleCallback === "function") {
      const id = window.requestIdleCallback(warm, { timeout: 8000 });
      return () => window.cancelIdleCallback(id);
    }
    const timer = window.setTimeout(warm, 4000);
    return () => window.clearTimeout(timer);
  }, []);

  useEffect(() => {
    document.documentElement.style.scrollBehavior = settings.smoothScrolling ? "smooth" : "auto";
  }, [settings.smoothScrolling]);

  if (!mounted) {
    return (
      <main className="app-boot">
        <img src="/arvio-logo.svg" alt="" className="app-boot-logo" />
        <img src="/arvio-wordmark.svg" alt="ARVIO" className="app-boot-wordmark" />
      </main>
    );
  }

  if (view === "login") return <LoginScreen />;
  if (view === "profiles") return <ProfileSelectionScreen />;

  const accent = ACCENTS[settings.accentColor] ?? ACCENTS.arctic;

  return (
    <main
      className={`app-shell ${settings.oledBlack ? "oled" : ""} ${settings.spoilerBlur ? "spoiler-blur" : ""}`}
      style={{ ["--accent" as string]: accent }}
    >
      {!activeStream && <TopNav />}

      <section className="content">
        {selected ? (
          <DetailsDrawer />
        ) : (
          <>
            {section === "home" && <HomeScreen />}
            {section === "search" && <SearchScreen />}
            {section === "watchlist" && <WatchlistScreen />}
            {section === "tv" && <LiveTvScreen />}
            {section === "addons" && <AddonsScreen />}
            {section === "settings" && <SettingsScreen />}
          </>
        )}
      </section>

      <PlayerOverlay />
      <ExternalPlaybackPrompt />
      <BackHandler />
      <Toast />
    </main>
  );
}
