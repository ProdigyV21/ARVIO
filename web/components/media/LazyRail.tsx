"use client";

import { useEffect, useRef, useState } from "react";
import { useApp } from "@/lib/store";
import type { CatalogConfig, Category, MediaItem } from "@/lib/types";
import { MediaRail } from "./MediaRail";

export function LazyRail({ catalog, eager = false, posterMode = false, onOpen, onFocus, onLoaded }: {
  catalog: CatalogConfig;
  eager?: boolean;
  posterMode?: boolean;
  onOpen: (item: MediaItem) => void;
  onFocus?: (item: MediaItem) => void;
  onLoaded?: (category: Category) => void;
}) {
  const { loadCatalogRow } = useApp();
  const ref = useRef<HTMLDivElement | null>(null);
  const startedRef = useRef(false);
  const [category, setCategory] = useState<Category | null>(null);
  const [loading, setLoading] = useState(false);
  const [done, setDone] = useState(false);

  useEffect(() => {
    if (startedRef.current) return undefined;

    const load = () => {
      if (startedRef.current) return;
      startedRef.current = true;
      setLoading(true);
      void loadCatalogRow(catalog)
        .then((row) => {
          setLoading(false);
          setDone(true);
          if (row?.items.length) {
            setCategory(row);
            onLoaded?.(row);
          }
        })
        .catch(() => {
          setLoading(false);
          setDone(true);
        });
    };

    // Eager rows (top of the home) load immediately; the rest load when scrolled
    // near the viewport. setTimeout is used (not rAF/IntersectionObserver) so it
    // still fires in backgrounded/embedded tabs.
    if (eager) {
      const timer = setTimeout(load, 0);
      return () => clearTimeout(timer);
    }

    const check = () => {
      const node = ref.current;
      if (!node || startedRef.current) return;
      const rect = node.getBoundingClientRect();
      if (rect.top < window.innerHeight + 600 && rect.bottom > -600) load();
    };

    const timer = setTimeout(check, 0);
    window.addEventListener("scroll", check, { passive: true });
    window.addEventListener("resize", check);
    return () => {
      clearTimeout(timer);
      window.removeEventListener("scroll", check);
      window.removeEventListener("resize", check);
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [catalog.id, eager]);

  if (category) {
    return <MediaRail category={category} onOpen={onOpen} onFocus={onFocus} posterMode={posterMode} />;
  }
  if (done) return null;

  return (
    <section ref={ref} className={`rail rail-skeleton ${posterMode ? "is-poster" : ""}`} aria-hidden>
      <div className="rail-head">
        <h3>{catalog.name}</h3>
      </div>
      <div className="rail-strip">
        {loading && Array.from({ length: 6 }).map((_, index) => <div key={index} className="card-skeleton" />)}
      </div>
    </section>
  );
}
