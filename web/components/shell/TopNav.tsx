"use client";

import { Bookmark, Home, Search, Settings, Tv } from "lucide-react";
import { useApp } from "@/lib/store";
import { ProfileAvatarVisual } from "@/components/profile/ProfileAvatar";
import type { NavSection } from "@/lib/types";

const nav = [
  { id: "home", label: "Home", icon: Home },
  { id: "search", label: "Search", icon: Search },
  { id: "watchlist", label: "Watchlist", icon: Bookmark },
  { id: "tv", label: "TV", icon: Tv }
] satisfies Array<{ id: NavSection; label: string; icon: typeof Home }>;

export function TopNav() {
  const { section, setSection, switchProfile, activeProfile, avatarImages, settings } = useApp();
  const clock = new Intl.DateTimeFormat([], {
    hour: "2-digit",
    minute: "2-digit",
    hour12: settings.clockFormat === "12h"
  }).format(new Date());

  return (
    <aside className="sidebar" aria-label="ARVIO navigation">
      <div className="profile-cluster">
        <button className="brand" onClick={switchProfile} aria-label="Switch profile">
          {activeProfile ? <ProfileAvatarVisual profile={activeProfile} avatarImages={avatarImages} /> : <img src="/arvio-logo.svg" alt="" />}
        </button>
        <span className="profile-name-text">{activeProfile?.name ?? ""}</span>
      </div>
      <nav>
        {nav.map((item) => {
          const Icon = item.icon;
          return (
            <button
              key={item.id}
              className={`nav-item ${section === item.id ? "is-active" : ""}`}
              onClick={() => setSection(item.id)}
            >
              <Icon size={22} />
              <span>{item.label}</span>
            </button>
          );
        })}
      </nav>
      <div className="top-right">
        <button
          className={`settings-gear ${section === "settings" ? "is-active" : ""}`}
          onClick={() => setSection("settings")}
          aria-label="Settings"
        >
          <Settings size={26} />
        </button>
        <span className="top-clock">{clock}</span>
      </div>
    </aside>
  );
}
