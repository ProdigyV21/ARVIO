"use client";

import type { Category, MediaItem } from "@/lib/types";
import { useApp } from "@/lib/store";
import { MediaCard } from "./MediaCard";
import { RailScroller } from "./RailScroller";

export function MediaRail({ category, onOpen, onFocus, posterMode }: {
  category: Category;
  onOpen: (item: MediaItem) => void;
  onFocus?: (item: MediaItem) => void;
  posterMode?: boolean;
}) {
  const { settings } = useApp();
  const effectivePosterMode = posterMode ?? (category.layout ? category.layout === "poster" : settings.cardLayoutMode === "poster");
  if (!category.items.length) return null;
  return (
    <section className={`rail ${effectivePosterMode ? "is-poster" : ""}`}>
      <div className="rail-head">
        <h3>{category.title}</h3>
      </div>
      <RailScroller className="rail-strip" ariaLabel={category.title}>
        {category.items.map((item) => (
          <MediaCard
            key={`${category.id}-${item.mediaType}-${item.id}-${item.title}`}
            item={item}
            onOpen={onOpen}
            onFocus={onFocus}
            posterMode={effectivePosterMode}
          />
        ))}
      </RailScroller>
    </section>
  );
}
