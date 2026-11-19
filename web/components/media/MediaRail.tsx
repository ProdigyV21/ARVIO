"use client";

import { ChevronRight } from "lucide-react";
import { useTranslation } from "@/lib/i18n";
import { defaultCatalogs } from "@/lib/catalogs";
import type { Category, MediaItem } from "@/lib/types";
import { useAppPick } from "@/lib/store";
import { useKidsFilter } from "@/lib/useKids";
import { MediaCard } from "./MediaCard";
import { RailScroller } from "./RailScroller";

/** Android ranks the first ten cards of any row titled "Top 10" (HomeScreen isRanked). */
export function isRankedRow(title: string) {
  return /top 10/i.test(title);
}

export function railTitle(category: Pick<Category, "id" | "title">, translateUi: (text: string) => string) {
  return category.id === "continue_watching" || category.id === "home_watchlist" ||
    defaultCatalogs.some(catalog => catalog.id === category.id && catalog.name === category.title) ? translateUi(category.title) : category.title;
}

export function MediaRail({ category, onOpen, onFocus, posterMode, onViewAll }: {
  category: Category;
  onOpen: (item: MediaItem) => void;
  onFocus?: (item: MediaItem) => void;
  posterMode?: boolean;
  /** Shown when the row has more titles than it displays: opens the full grid. */
  onViewAll?: () => void;
}) {
  const { settings } = useAppPick("settings");
  const kids = useKidsFilter();
  const translateUi = useTranslation();
  // Kids profiles: only titles rated for the profile's age; a row nothing survives in is hidden.
  const items = kids.filter(category.items);
  const title = railTitle(category, translateUi);
  const effectivePosterMode = posterMode ?? (category.layout ? category.layout === "poster" : settings.cardLayoutMode === "poster");
  const ranked = isRankedRow(category.title);
  if (!items.length) return null;
  return (
    <section className={`rail ${effectivePosterMode ? "is-poster" : ""}`} data-rail-id={category.id}>
      <div className="rail-head">
        <h3>{title}</h3>
        {/* Pointer shortcut; the D-pad reaches "View all" as the row's last tile. */}
        {onViewAll && <button type="button" className="rail-view-all-link" tabIndex={-1} onClick={onViewAll}>
          {translateUi("View all")}<ChevronRight size={16} aria-hidden />
        </button>}
      </div>
      <RailScroller className="rail-strip" ariaLabel={title}>
        {items.map((item, index) => (
          <MediaCard
            key={`${category.id}-${item.mediaType}-${item.id}-${item.title}`}
            item={item}
            onOpen={onOpen}
            onFocus={onFocus}
            posterMode={effectivePosterMode}
            rank={ranked && index < 10 ? index + 1 : undefined}
          />
        ))}
        {onViewAll && <button type="button" className={`rail-view-all ${effectivePosterMode ? "is-poster" : ""}`} onClick={onViewAll}
          aria-label={translateUi("View all {value0}", { value0: title })}>
          <span className="rail-view-all-art"><ChevronRight size={30} aria-hidden /></span>
          <strong>{translateUi("View all")}</strong>
        </button>}
      </RailScroller>
    </section>
  );
}
