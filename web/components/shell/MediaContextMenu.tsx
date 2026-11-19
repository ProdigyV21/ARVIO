"use client";
import { useTranslation } from "@/lib/i18n";


import { Bookmark, Check, EyeOff, Info, Trash2, X } from "lucide-react";
import { useEffect, useState } from "react";
import { useAppPick } from "@/lib/store";

export function MediaContextMenu() {
  const translateUi = useTranslation();
  const {
    activeContextMenu,
    closeContextMenu,
    openDetails,
    toggleWatchlist,
    toggleWatched,
    removeFromContinueWatching,
    watchlist,
    isWatched,
    continueWatching
  } = useAppPick("activeContextMenu", "closeContextMenu", "openDetails", "toggleWatchlist", "toggleWatched", "removeFromContinueWatching", "watchlist", "isWatched", "continueWatching");

  const [mounted, setMounted] = useState(false);

  useEffect(() => {
    setMounted(true);
  }, []);

  useEffect(() => {
    if (!activeContextMenu) return undefined;
    const handleKeyDown = (e: KeyboardEvent) => {
      if (e.key === "Escape" || e.key === "GoBack") {
        e.preventDefault();
        closeContextMenu();
      }
    };
    window.addEventListener("keydown", handleKeyDown);
    return () => window.removeEventListener("keydown", handleKeyDown);
  }, [activeContextMenu, closeContextMenu]);

  if (!mounted || !activeContextMenu) return null;

  const { item, title: customTitle, subtitle: customSubtitle, actions: customActions, isContinueWatching: forceCw, position } = activeContextMenu;
  const title = item?.title ?? customTitle ?? "Options";
  const subtitle = item?.subtitle ?? customSubtitle ?? "";
  const backdrop = item?.backdrop || item?.image;

  let actions = customActions ?? [];

  if (item && !customActions) {
    const inWatchlist = watchlist.some((entry) => entry.mediaType === item.mediaType && entry.id === item.id);
    const watched = isWatched(item);
    const inCwList = continueWatching.some(
      (entry) => entry.mediaType === item.mediaType && entry.id === item.id
    );
    const showRemoveCw = forceCw || inCwList || (item.progress ?? 0) > 0 || Boolean(item.timeRemainingLabel);

    actions = [
      {
        id: "details",
        label: "View Details",
        icon: <Info size={18} />,
        action: () => {
          void openDetails(item);
        }
      },
      {
        id: "watchlist",
        label: inWatchlist ? "Remove from Watchlist" : "Add to Watchlist",
        icon: inWatchlist ? <Trash2 size={18} /> : <Bookmark size={18} />,
        action: () => {
          void toggleWatchlist(item);
        }
      },
      {
        id: "watched",
        label: watched ? "Mark as Unwatched" : "Mark as Watched",
        icon: watched ? <EyeOff size={18} /> : <Check size={18} />,
        action: () => {
          void toggleWatched(item);
        }
      }
    ];

    if (showRemoveCw) {
      actions.push({
        id: "remove_cw",
        label: "Remove from Continue Watching",
        icon: <X size={18} />,
        danger: true,
        action: () => {
          void removeFromContinueWatching(item);
        }
      });
    }
  }

  // Position desktop float card safely inside viewport
  const isDesktop = position && typeof window !== "undefined" && window.innerWidth >= 768;
  const menuStyle: React.CSSProperties = isDesktop
    ? {
        position: "fixed",
        top: Math.max(16, Math.min(position.y, window.innerHeight - 340)),
        left: Math.max(16, Math.min(position.x, window.innerWidth - 320)),
        margin: 0
      }
    : {};

  return (
    <div className="context-menu-scrim" role="dialog" aria-modal="true" onClick={closeContextMenu}>
      <div
        className={`context-menu-card ${isDesktop ? "is-floating" : "is-bottom-sheet"}`}
        style={menuStyle}
        onClick={(e) => e.stopPropagation()}
      >
        <div className="context-menu-drag-handle" />

        {backdrop && !isDesktop && (
          <div className="context-menu-header-art">
            <img src={backdrop} alt="" />
            <div className="context-menu-header-art-gradient" />
          </div>
        )}

        <div className="context-menu-header">
          <h3 className="context-menu-title">{title}</h3>
          {subtitle && <p className="context-menu-subtitle">{subtitle}</p>}
        </div>

        <div className="context-menu-divider" />

        <div className="context-menu-actions">
          {actions.map((act) => (
            <button
              key={act.id}
              type="button"
              className={`context-menu-action-btn ${act.danger ? "is-danger" : ""}`}
              onClick={() => {
                closeContextMenu();
                act.action();
              }}
            >
              <span className="context-menu-icon">{act.icon}</span>
              <span className="context-menu-label">{translateUi(act.label)}</span>
            </button>
          ))}
        </div>

        <div className="context-menu-footer-hint">
          <span>{translateUi("Press ESC to close")}</span>
        </div>
      </div>
    </div>
  );
}
