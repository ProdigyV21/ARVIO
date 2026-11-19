"use client";
import "./sources.css";

import { Check, CircleAlert, Copy, Download, ExternalLink, Gauge, MoreHorizontal, Play, Zap } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { useTranslation } from "@/lib/i18n";
import type { ExternalPlayerOption } from "@/lib/externalPlayers";
import type { SourceBadge, SourcePlaybackPresentation } from "./sourcePlaybackPresentation";

export type SourceRowProps = {
  rank: number;
  title: string;
  /** Addon / provider name shown in the meta line. */
  provider: string;
  presentation: SourcePlaybackPresentation;
  badges: SourceBadge[];
  externalOptions: ExternalPlayerOption[];
  /** No playable URL (or a disabled Telegram source): nothing can start it. */
  locked: boolean;
  lockedReason?: string;
  active?: boolean;
  /** Phones: external players and file actions move into the ⋯ menu. */
  compact?: boolean;
  onPlay: () => void;
  onExternal: (option: ExternalPlayerOption) => void;
  onDownload?: () => void;
  onCopy?: () => void;
};

const TIER_ICON = { here: Zap, conversion: Gauge, external: ExternalLink } as const;

/**
 * One source, one line of facts. The whole left side is the primary action —
 * "Play" when the file can play in this browser, otherwise the device's best
 * external player — so a remote moves straight down the list; the right side
 * holds the external players and a ⋯ menu for everything else.
 */
export function SourceRow(props: SourceRowProps) {
  const translateUi = useTranslation();
  const { presentation, externalOptions, locked, compact } = props;
  const [menuOpen, setMenuOpen] = useState(false);
  const menuButton = useRef<HTMLButtonElement | null>(null);
  const menuRef = useRef<HTMLDivElement | null>(null);
  const canPlay = presentation.canTryBrowser && !locked;
  const primaryExternal = externalOptions[0];
  const TierIcon = TIER_ICON[presentation.tier];
  const primaryLabel = canPlay
    ? translateUi("Play {value0}", { value0: props.title })
    : primaryExternal ? translateUi("Open {value0} in {value1}", { value0: props.title, value1: translateUi(primaryExternal.label) }) : props.title;
  const runPrimary = () => {
    if (locked) return;
    if (canPlay) props.onPlay();
    else if (primaryExternal) props.onExternal(primaryExternal);
  };
  // External buttons beside the row (desktop/tablet). When the primary action
  // already opens the first external player, do not repeat it as a button.
  const sideOptions = compact ? [] : externalOptions.filter((option) => canPlay || option !== primaryExternal);
  const menuItems = [
    ...(compact ? externalOptions.filter((option) => canPlay || option !== primaryExternal).map((option) => ({ key: option.id, label: translateUi("Open in {value0}", { value0: translateUi(option.label) }), icon: ExternalLink, run: () => props.onExternal(option) })) : []),
    ...(props.onDownload ? [{ key: "download", label: translateUi("Download"), icon: Download, run: props.onDownload }] : []),
    ...(props.onCopy ? [{ key: "copy", label: translateUi("Copy stream URL"), icon: Copy, run: props.onCopy }] : [])
  ];

  useEffect(() => {
    if (!menuOpen) return undefined;
    menuRef.current?.querySelector<HTMLButtonElement>("button")?.focus();
    const close = (event: Event) => {
      if (event instanceof KeyboardEvent) {
        if (event.key !== "Escape") return;
        event.preventDefault();
        event.stopImmediatePropagation();
        setMenuOpen(false);
        menuButton.current?.focus();
        return;
      }
      if (menuRef.current?.contains(event.target as Node) || menuButton.current?.contains(event.target as Node)) return;
      setMenuOpen(false);
    };
    // Capture: the menu must see Escape before the picker/player closes.
    window.addEventListener("keydown", close, true);
    window.addEventListener("pointerdown", close, true);
    return () => {
      window.removeEventListener("keydown", close, true);
      window.removeEventListener("pointerdown", close, true);
    };
  }, [menuOpen]);

  return (
    <article
      className={`src-row tier-${presentation.tier} ${props.active ? "is-active" : ""} ${locked ? "is-locked" : ""}`}
      data-playback-state={presentation.state}
      data-tier={presentation.tier}
      aria-current={props.active ? "true" : undefined}
    >
      <button type="button" className="src-row-main" onClick={runPrimary} disabled={locked} aria-label={primaryLabel} title={props.title}>
        <span className="src-rank" aria-hidden="true">{props.active ? <Check size={15} /> : props.rank}</span>
        <span className="src-text">
          <strong className="src-title">{props.title}</strong>
          <span className="src-meta">
            <span className={`src-tier ${presentation.className}`}>
              <TierIcon size={12} aria-hidden="true" />
              <span>{locked && props.lockedReason ? translateUi("Not browser-playable") : translateUi(presentation.label)}</span>
            </span>
            <span className="src-provider">{props.provider}</span>
            {props.badges.map((badge) => (
              <span key={badge.label} className={`src-badge ${badge.tone ?? ""}`}>{translateUi(badge.label)}</span>
            ))}
          </span>
          {(locked && props.lockedReason) || (presentation.tier !== "here" && presentation.detail) ? (
            <span className="src-reason" title={translateUi(props.lockedReason || presentation.detail)}>
              <CircleAlert size={11} aria-hidden="true" /> {translateUi(props.lockedReason || presentation.detail)}
            </span>
          ) : presentation.detail ? (
            <span className="src-reason">{translateUi(presentation.detail)}</span>
          ) : null}
        </span>
        {canPlay ? <span className="src-play-cue" aria-hidden="true"><Play size={14} fill="currentColor" /></span> : null}
      </button>
      {!locked && (sideOptions.length > 0 || menuItems.length > 0) ? (
        <span className="src-actions">
          {sideOptions.map((option) => (
            <button key={option.id} type="button" className="src-action" onClick={() => props.onExternal(option)}>
              <ExternalLink size={13} aria-hidden="true" /> {translateUi(option.label)}
            </button>
          ))}
          {menuItems.length > 0 ? (
            <span className="src-menu-wrap">
              <button
                ref={menuButton}
                type="button"
                className="src-action icon-only"
                aria-haspopup="menu"
                aria-expanded={menuOpen}
                aria-label={translateUi("More actions for {value0}", { value0: props.title })}
                onClick={() => setMenuOpen((open) => !open)}
              >
                <MoreHorizontal size={16} />
              </button>
              {menuOpen ? (
                <div ref={menuRef} className="src-menu" role="menu">
                  {menuItems.map((entry) => {
                    const Icon = entry.icon;
                    return (
                      <button key={entry.key} type="button" role="menuitem" onClick={() => { setMenuOpen(false); entry.run(); }}>
                        <Icon size={14} aria-hidden="true" /> {entry.label}
                      </button>
                    );
                  })}
                </div>
              ) : null}
            </span>
          ) : null}
        </span>
      ) : null}
    </article>
  );
}
