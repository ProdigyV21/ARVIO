"use client";
import { useTranslation } from "@/lib/i18n";


import { BadgeCheck, Bookmark, Home, Search, Settings, Tv } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { useAppPick } from "@/lib/store";
import { kidsLevelOf } from "@/lib/kids";
import { ProfileAvatarVisual } from "@/components/profile/ProfileAvatar";
import type { NavSection } from "@/lib/types";
import { useEntitlement } from "@/lib/entitlementContext";
import { trialRemainingLabel } from "@/lib/entitlementPolicy";
import { MembershipCheckout } from "./MembershipCheckout";
import { screenTitle } from "@/lib/screenTitle";
import "@/app/topnav.css";

const nav = [
  { id: "home", label: "Home", icon: Home },
  { id: "search", label: "Search", icon: Search },
  { id: "watchlist", label: "Library", icon: Bookmark },
  { id: "tv", label: "TV", icon: Tv }
] satisfies Array<{ id: NavSection; label: string; icon: typeof Home }>;

export function TopNav() {
  const translateUi = useTranslation();
  const { view, section, setSection, switchProfile, activeProfile, avatarImages, settings, closeDetails, selected } = useAppPick("view", "section", "setSection", "switchProfile", "activeProfile", "avatarImages", "settings", "closeDetails", "selected");
  // Per-screen tab titles ("Home · ARVIO"), so history, tabs and screen
  // readers say where the user is.
  useEffect(() => {
    document.title = screenTitle(section, selected?.title, translateUi);
  }, [section, selected?.title, translateUi]);
  const skipToContent = (event: React.MouseEvent<HTMLAnchorElement>) => {
    const target = document.querySelector<HTMLElement>(".content");
    if (!target) return;
    event.preventDefault();
    if (!target.hasAttribute("tabindex")) target.setAttribute("tabindex", "-1");
    target.focus();
  };
  const [scrolled, setScrolled] = useState(false);
  const barRef = useRef<HTMLElement>(null);
  // Live TV carries no age ratings: a kids profile loses the tab (Android KidsNavigation).
  const visibleNav = kidsLevelOf(activeProfile) ? nav.filter((item) => item.id !== "tv") : nav;
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

  // Publish where the bar's controls end (--topnav-bottom), so pages that put
  // their own controls under it (the details Back button and title) never
  // collide with the profile or the tabs, whatever the window size or zoom.
  useEffect(() => {
    const bar = barRef.current;
    if (!bar) return;
    const root = document.documentElement;
    const measure = () => {
      if (getComputedStyle(bar).display === "none") { root.style.removeProperty("--topnav-bottom"); return; }
      const top = bar.getBoundingClientRect().top;
      let bottom = 0;
      for (const child of Array.from(bar.children)) bottom = Math.max(bottom, child.getBoundingClientRect().bottom - top);
      if (bottom > 0) root.style.setProperty("--topnav-bottom", `${Math.round(bottom)}px`);
    };
    measure();
    const observer = typeof ResizeObserver === "undefined" ? null : new ResizeObserver(measure);
    observer?.observe(bar);
    for (const child of Array.from(bar.children)) observer?.observe(child);
    window.addEventListener("resize", measure);
    return () => { observer?.disconnect(); window.removeEventListener("resize", measure); root.style.removeProperty("--topnav-bottom"); };
  }, []);

  useEffect(() => {
    const onScroll = () => setScrolled(window.scrollY > 24);
    onScroll();
    window.addEventListener("scroll", onScroll, { passive: true });
    return () => window.removeEventListener("scroll", onScroll);
  }, []);


  return (
    <>
      <a className="skip-link" href="#main-content" onClick={skipToContent}>{translateUi("Skip to content")}</a>
      {/* Desktop/Tablet top bar: a banner landmark with the main navigation. */}
      <header ref={barRef} className={`sidebar ${scrolled ? "is-scrolled" : ""}`}>
        <div className="profile-cluster">
          <button type="button" className="brand" onClick={switchProfile} aria-label={translateUi("Switch profile")}>
            {activeProfile ? <ProfileAvatarVisual profile={activeProfile} avatarImages={avatarImages} /> : <img src="/arvio-logo.svg" alt="" />}
          </button>
          <span className="profile-name-text">{activeProfile?.name ?? ""}</span>
        </div>
        <nav aria-label={translateUi("ARVIO navigation")}>
          {visibleNav.map((item) => {
            const Icon = item.icon;
            const current = !selected && section === item.id;
            return (
              <button
                type="button"
                key={item.id}
                className={`nav-item ${current ? "is-active" : ""}`}
                // The labels are hidden at 681-980px: the name must not depend on them.
                aria-label={translateUi(item.label)}
                aria-current={current ? "page" : undefined}
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
            aria-current={!selected && section === "settings" ? "page" : undefined}
          >
            <Settings size={26} />
          </button>
        </div>
      </header>

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
              iconOnly
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
        {visibleNav.map((item) => {
          const Icon = item.icon;
          return (
            <button
              type="button"
              key={item.id}
              aria-current={!selected && section === item.id ? "page" : undefined}
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
          aria-current={!selected && section === "settings" ? "page" : undefined}
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
