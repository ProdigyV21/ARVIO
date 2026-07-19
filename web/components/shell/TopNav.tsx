"use client";

import { Bookmark, Home, Search, Settings, Tv } from "lucide-react";
import { useEffect, useState } from "react";
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
  const { view, section, setSection, switchProfile, activeProfile, avatarImages, settings, closeDetails, selected } = useApp();
  const [scrolled, setScrolled] = useState(false);

  useEffect(() => {
    const onScroll = () => setScrolled(window.scrollY > 24);
    onScroll();
    window.addEventListener("scroll", onScroll, { passive: true });
    return () => window.removeEventListener("scroll", onScroll);
  }, []);


  return (
    <>
      {/* Desktop/Tablet Sidebar / TopNav */}
      <aside className={`sidebar ${scrolled ? "is-scrolled" : ""}`} aria-label="ARVIO navigation">
        <div className="profile-cluster">
          <button type="button" className="brand" onClick={switchProfile} aria-label="Switch profile">
            {activeProfile ? <ProfileAvatarVisual profile={activeProfile} avatarImages={avatarImages} /> : <img src="/arvio-logo.svg" alt="" />}
          </button>
          <span className="profile-name-text">{activeProfile?.name ?? ""}</span>
        </div>
        <nav>
          {nav.map((item) => {
            const Icon = item.icon;
            return (
              <button
                type="button"
                key={item.id}
                className={`nav-item ${!selected && section === item.id ? "is-active" : ""}`}
                onClick={() => {
                  closeDetails();
                  setSection(item.id);
                }}
              >
                <Icon size={22} />
                <span>{item.label}</span>
              </button>
            );
          })}
        </nav>
        <div className="top-right">
          <button
            type="button"
            className={`settings-gear ${!selected && section === "settings" ? "is-active" : ""}`}
            onClick={() => {
              closeDetails();
              setSection("settings");
            }}
            aria-label="Settings"
          >
            <Settings size={26} />
          </button>
        </div>
      </aside>

      {/* Mobile Top Header (screen <= 680px) */}
      <header className={`mobile-header ${scrolled ? "is-scrolled" : ""}`}>
        <div className="mobile-brand">
          <img src="/arvio-logo.svg" alt="" className="mobile-brand-logo" />
          <img src="/arvio-wordmark.svg" alt="ARVIO" className="mobile-wordmark" />
        </div>
        <button
          type="button"
          className={`mobile-profile-btn ${!selected && view === "profiles" ? "is-active" : ""}`}
          onClick={switchProfile}
          aria-label="Switch profile"
        >
          <div className="mobile-avatar-container">
            {activeProfile ? (
              <ProfileAvatarVisual profile={activeProfile} avatarImages={avatarImages} />
            ) : (
              <img src="/arvio-logo.svg" alt="" />
            )}
          </div>
        </button>
      </header>

      {/* Mobile Bottom Navigation (screen <= 680px) */}
      <nav className="mobile-bottom-nav" aria-label="Mobile navigation">
        {nav.map((item) => {
          const Icon = item.icon;
          return (
            <button
              type="button"
              key={item.id}
              className={`mobile-nav-item ${!selected && section === item.id ? "is-active" : ""}`}
              onClick={() => {
                closeDetails();
                setSection(item.id);
              }}
            >
              <Icon size={22} />
              <span>{item.label}</span>
            </button>
          );
        })}
        {/* Settings tab at bottom right */}
        <button
          type="button"
          className={`mobile-nav-item ${!selected && section === "settings" ? "is-active" : ""}`}
          onClick={() => {
            closeDetails();
            setSection("settings");
          }}
          aria-label="Settings"
        >
          <Settings size={22} />
          <span>Settings</span>
        </button>
      </nav>
    </>
  );
}
