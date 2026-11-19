"use client";
import { useTranslation } from "@/lib/i18n";

import { RotateCw } from "lucide-react";
import { useEffect, useRef, useState, type ReactNode } from "react";
import { homeFetchPriority } from "@/lib/fetchPriority";
import { loadStored, saveStored } from "@/lib/storage";
import { useAppPick } from "@/lib/store";
import { HOME_ROW_LIMIT, loadCatalog } from "@/lib/tmdb";
import type { CatalogConfig, Category, MediaItem } from "@/lib/types";
import { MediaRail } from "./MediaRail";

// v3: v2 entries were poisoned by collection rails colliding on a cache key
// that omitted collectionSources (all service rows shared one entry).
const CATALOG_ROW_CACHE_KEY = "arvio.web.catalogRows.v3";
const CATALOG_ROW_CACHE_TTL = 12 * 60 * 60 * 1000;
// A row cached this recently is shown without asking the network again (a
// warm Home load re-hydrated every row title, one TMDB call each).
const CATALOG_ROW_FRESH_MS = 30 * 60 * 1000;
type CatalogRowCache = Record<string, { at: number; category: Category }>;

export type LazyRailOutcome = "loading" | "ready" | "empty" | "error";

export function LazyRail({ catalog, eager = false, posterMode, onOpen, onFocus, onLoaded, onSettled, emptyContent, onViewAll, limit = HOME_ROW_LIMIT }: {
  catalog: CatalogConfig;
  eager?: boolean;
  posterMode?: boolean;
  onOpen: (item: MediaItem) => void;
  onFocus?: (item: MediaItem) => void;
  onLoaded?: (category: Category) => void;
  onSettled?: (catalogId: string, outcome: LazyRailOutcome) => void;
  emptyContent?: ReactNode;
  /** Opens the full row; rows show `limit` titles and end with a "View all" tile. */
  onViewAll?: (catalog: CatalogConfig, category: Category) => void;
  limit?: number;
}) {
  const { addons, settings } = useAppPick("addons", "settings");
  const translateUi = useTranslation();
  const sources = useRef({ addons, homeServers: settings.homeServers });
  sources.current = { addons, homeServers: settings.homeServers };
  const settledRef = useRef(onSettled);
  settledRef.current = onSettled;
  const cacheKey = catalogCacheKey(catalog, settings.language);
  const ref = useRef<HTMLDivElement | null>(null);
  const startedRef = useRef(false);
  const [category, setCategory] = useState<Category | null>(() => readCachedCatalog(cacheKey, limit));
  const categoryRef = useRef(category);
  categoryRef.current = category;
  const effectivePosterMode = catalog.layout === "poster" || category?.layout === "poster" || (posterMode ?? (settings.cardLayoutMode === "poster"));
  const [status, setStatus] = useState<"idle" | "loading" | "done" | "error">("idle");
  const [attempt, setAttempt] = useState(0);

  useEffect(() => {
    const cached = readCachedCatalog(cacheKey, limit);
    if (cached) {
      setCategory(cached);
      categoryRef.current = cached;
      onLoaded?.(cached);
    }
    startedRef.current = false;
    setStatus("idle");
    settledRef.current?.(catalog.id, cached ? "ready" : "loading");
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [cacheKey]);

  useEffect(() => {
    if (startedRef.current) return undefined;

    const load = () => {
      if (startedRef.current) return;
      startedRef.current = true;
      if (attempt === 0 && cachedCatalogAge(cacheKey) < CATALOG_ROW_FRESH_MS) {
        setStatus("done");
        settledRef.current?.(catalog.id, readCachedCatalog(cacheKey, limit) || categoryRef.current ? "ready" : "empty");
        return;
      }
      setStatus("loading");
      // Rows take network priority over card decoration (logos, badges, ratings).
      void homeFetchPriority.trackRow(loadCatalog(catalog, settings.language, sources.current.addons, sources.current.homeServers, { limit }))
        .then((row) => {
          setStatus("done");
          if (row?.items.length) {
            setCategory(row);
            categoryRef.current = row;
            writeCachedCatalog(cacheKey, row);
            onLoaded?.(row);
          }
          settledRef.current?.(catalog.id, row?.items.length || categoryRef.current ? "ready" : "empty");
        })
        .catch(() => {
          // A cached copy stays on screen; without one the row offers a retry
          // instead of silently disappearing.
          setStatus("error");
          settledRef.current?.(catalog.id, categoryRef.current ? "ready" : "error");
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
  }, [cacheKey, catalog.id, eager, attempt]);

  const title = catalog.isPreinstalled ? translateUi(catalog.name) : catalog.name;
  if (category) {
    return <MediaRail category={category} onOpen={onOpen} onFocus={onFocus} posterMode={effectivePosterMode}
      onViewAll={onViewAll && category.hasMore ? () => onViewAll(catalog, category) : undefined} />;
  }
  if (status === "error") {
    return (
      <section ref={ref} className={`rail rail-error ${effectivePosterMode ? "is-poster" : ""}`} role="group" aria-label={title}>
        <div className="rail-head"><h3>{title}</h3></div>
        <div className="rail-error-body">
          <p>{translateUi("This row could not be loaded.")}</p>
          <button type="button" className="secondary rail-retry" onClick={() => { startedRef.current = false; setStatus("idle"); setAttempt((n) => n + 1); }}>
            <RotateCw size={16} aria-hidden />{translateUi("Retry")}
          </button>
        </div>
      </section>
    );
  }
  if (status === "done") return <>{emptyContent ?? null}</>;

  return (
    <section ref={ref} className={`rail rail-skeleton ${effectivePosterMode ? "is-poster" : ""}`} aria-hidden>
      <div className="rail-head">
        <h3>{title}</h3>
      </div>
      <div className="rail-strip">
        {status === "loading" && Array.from({ length: 6 }).map((_, index) => <div key={index} className="card-skeleton" />)}
      </div>
    </section>
  );
}

function catalogCacheKey(catalog: CatalogConfig, language: string) {
  return [
    catalog.id,
    language,
    catalog.sourceType,
    catalog.mediaType ?? "",
    catalog.sourceUrl ?? "",
    catalog.endpoint ?? "",
    JSON.stringify(catalog.params ?? {}),
    catalog.layout ?? "",
    // Collection rails (Prime/HBO/Hulu/Paramount…) are distinguished ONLY by
    // their collectionSources (e.g. the tmdbWatchProviderId) and kind — without
    // these in the key, every service row collides on the same cache entry and
    // renders identical content.
    catalog.kind ?? "",
    catalog.collectionSources?.length ? JSON.stringify(catalog.collectionSources) : ""
  ].join("|");
}

/** A cached row, cut to the Home limit (older builds cached up to 96 titles). */
export function readCachedCatalog(key: string, limit?: number) {
  const cache = loadStored<CatalogRowCache>(CATALOG_ROW_CACHE_KEY, {});
  const entry = cache[key];
  if (!entry || Date.now() - entry.at > CATALOG_ROW_CACHE_TTL) return null;
  const category = entry.category;
  if (!category?.items?.length) return null;
  if (limit && category.items.length > limit) return { ...category, items: category.items.slice(0, limit), hasMore: true };
  return category;
}

function cachedCatalogAge(key: string) {
  const entry = loadStored<CatalogRowCache>(CATALOG_ROW_CACHE_KEY, {})[key];
  return entry?.category?.items?.length ? Date.now() - entry.at : Infinity;
}

function writeCachedCatalog(key: string, category: Category) {
  const cache = loadStored<CatalogRowCache>(CATALOG_ROW_CACHE_KEY, {});
  const next: CatalogRowCache = { ...cache, [key]: { at: Date.now(), category } };
  const entries = Object.entries(next).sort((a, b) => b[1].at - a[1].at).slice(0, 80);
  saveStored(CATALOG_ROW_CACHE_KEY, Object.fromEntries(entries));
}
