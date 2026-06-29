"use client";

import type { Category, MediaItem } from "@/lib/types";
import { MediaCard } from "./MediaCard";

export function MediaRail({ category, onOpen, onFocus, posterMode = false }: {
  category: Category;
  onOpen: (item: MediaItem) => void;
  onFocus?: (item: MediaItem) => void;
  posterMode?: boolean;
}) {
  if (!category.items.length) return null;
  return (
    <section className={`rail ${posterMode ? "is-poster" : ""}`}>
      <div className="rail-head">
        <h3>{category.title}</h3>
        <span>{category.items.length}</span>
      </div>
      <div className="rail-strip">
        {category.items.map((item) => (
          <MediaCard
            key={`${category.id}-${item.mediaType}-${item.id}-${item.title}`}
            item={item}
            onOpen={onOpen}
            onFocus={onFocus}
            posterMode={posterMode}
          />
        ))}
      </div>
    </section>
  );
}
