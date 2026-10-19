"use client";
import { useTranslation } from "@/lib/i18n";


import { BadgeCheck, Bookmark, Home, Search, Settings, Tv } from "lucide-react";
import { useEffect, useState } from "react";
import { useApp } from "@/lib/store";
import { ProfileAvatarVisual } from "@/components/profile/ProfileAvatar";
import type { NavSection } from "@/lib/types";
import { useEntitlement } from "@/lib/entitlementContext";
import { trialRemainingLabel } from "@/lib/entitlementPolicy";
import { MembershipCheckout } from "./MembershipCheckout";

const nav = [
  { id: "home", label: "Home", icon: Home },
  { id: "search", label: "Search", icon: Search },
  { id: "watchlist", label: "Library", icon: Bookmark },
  { id: "tv", label: "TV", icon: Tv }
] satisfies Array<{ id: NavSection; label: string; icon: typeof Home }>;

export function TopNav() {
  const translateUi = useTranslation();
  const { view, section, setSection, switchProfile, activeProfile, avatarImages, settings, closeDetails, selected } = useApp();
  const [scrolled, setScrolled] = useState(false);
  const entitlement = useEntitlement();
  const [now, setNow] = useState(Date.now);
  const trialLabel = trialRemainingLabel(entitlement, now);
  const trialActive = trialLabel !== null;
  const trialTitle = trialActive ? `${trialLabel}. ${translateUi("Keep Premium - $2.99/month")}` : translateUi("Keep Premium - $2.99/month");

  useEffect(() => {
    setNow(Date.now());
    if (!entitlement?.entitled || entitlement.reason !== "trial") return;
    const timer = setInterval(() => setNow(Date.now()), 60_000);
    return () => clearInterval(timer);
  }, [entitlement?.entitled, entitlement?.reason, entitlement?.expiresAt]);

  useEffect(() => {
    const onScroll = () => setScrolled(window.scrollY > 24);
    onScroll();
    window.addEventListener("scroll", onScroll, { passive: true });
    return () => window.removeEventListener("scroll", onScroll);
  }, []);


  return (
    <>
      {/* Desktop/Tablet Sidebar / TopNav */}
      <aside className={`sidebar ${scrolled ? "is-scrolled" : ""}`} aria-label={translateUi("ARVIO navigation")}>
        <div className="profile-cluster">
          <button type="button" className="brand" onClick={switchProfile} aria-label={translateUi("Switch profile")}>
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
                <span>{translateUi(item.label)}</span>
              </button>
            );
          })}
        </nav>
        <div className="top-right">
          {trialActive && (
            <MembershipCheckout
              className="trial-membership-link"
              entry="trial_nav"
              title={trialTitle}
            >
              <BadgeCheck size={18} />
              <span>{translateUi("Keep Premium")}</span>
            </MembershipCheckout>
          )}
          <button
            type="button"
            className={`settings-gear ${!selected && section === "settings" ? "is-active" : ""}`}
            onClick={() => {
              closeDetails();
              setSection("settings");
            }}
            aria-label={translateUi("Settings")}
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
        <div className="mobile-header-actions">
          {trialActive && (
            <MembershipCheckout
              className="mobile-premium-link"
              entry="trial_mobile_nav"
              title={trialTitle}
            >
              <BadgeCheck size={20} />
            </MembershipCheckout>
          )}
          <button
            type="button"
            className={`mobile-profile-btn ${!selected && view === "profiles" ? "is-active" : ""}`}
            onClick={switchProfile}
            aria-label={translateUi("Switch profile")}
          >
            <div className="mobile-avatar-container">
              {activeProfile ? (
                <ProfileAvatarVisual profile={activeProfile} avatarImages={avatarImages} />
              ) : (
                <img src="/arvio-logo.svg" alt="" />
              )}
            </div>
          </button>
        </div>
      </header>

      {/* Mobile Bottom Navigation (screen <= 680px) */}
      <nav className="mobile-bottom-nav" aria-label={translateUi("Mobile navigation")}>
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
              <span>{translateUi(item.label)}</span>
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
          aria-label={translateUi("Settings")}
        >
          <Settings size={22} />
          <span>{translateUi("Settings")}</span>
        </button>
      </nav>
    </>
  );
}
