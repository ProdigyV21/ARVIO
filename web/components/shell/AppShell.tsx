"use client";

import { useEffect } from "react";
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
import { SyncStrip } from "./SyncStrip";
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
  const { view, section, settings } = useApp();

  useEffect(() => {
    document.documentElement.style.scrollBehavior = settings.smoothScrolling ? "smooth" : "auto";
  }, [settings.smoothScrolling]);

  if (view === "login") return <LoginScreen />;
  if (view === "profiles") return <ProfileSelectionScreen />;

  const accent = ACCENTS[settings.accentColor] ?? ACCENTS.arctic;

  return (
    <main
      className={`app-shell ${settings.oledBlack ? "oled" : ""} ${settings.spoilerBlur ? "spoiler-blur" : ""}`}
      style={{ ["--accent" as string]: accent }}
    >
      <TopNav />

      <section className="content">
        <SyncStrip />
        {section === "home" && <HomeScreen />}
        {section === "search" && <SearchScreen />}
        {section === "watchlist" && <WatchlistScreen />}
        {section === "tv" && <LiveTvScreen />}
        {section === "addons" && <AddonsScreen />}
        {section === "settings" && <SettingsScreen />}
      </section>

      <DetailsDrawer />
      <PlayerOverlay />
      <Toast />
    </main>
  );
}
