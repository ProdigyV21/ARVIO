// Home rows: grouping by where a row comes from, and the per-row card style
// the Android app keeps in profileSettingsById.<id>.catalogueRowLayoutModes
// ({ "<normalized 'home:<catalogId>'>": "Poster" | "Landscape" }).

import type { CatalogConfig, Category, MediaItem } from "./types";

export type HomeRowSource = "tmdb" | "addon" | "lists" | "home-server" | "collections" | "youtube";

export const HOME_ROW_SOURCES: Array<[HomeRowSource, string]> = [
  ["tmdb", "Built-in"],
  ["addon", "Addon catalogs"],
  ["lists", "Trakt, MDBList and SIMKL lists"],
  ["home-server", "Home server libraries"],
  ["collections", "Collections"],
  ["youtube", "YouTube"],
];

export function homeRowSource(catalog: Pick<CatalogConfig, "sourceType" | "kind">): HomeRowSource {
  const kind = String(catalog.kind ?? "").toUpperCase();
  if (kind === "COLLECTION" || kind === "COLLECTION_RAIL") return "collections";
  switch (catalog.sourceType) {
    case "addon": return "addon";
    case "trakt": case "mdblist": case "simkl": return "lists";
    case "home-server": return "home-server";
    case "youtube": return "youtube";
    default: return "tmdb";
  }
}

/** Kotlin normalizeCatalogueRowLayoutKey("home:<id>"), so web and TV address the same row. */
export function rowLayoutKey(catalogId: string): string {
  const normalized = `home:${catalogId}`.trim().toLowerCase().replace(/[^a-z0-9_.:-]+/g, "_").replace(/^_+|_+$/g, "");
  return normalized || "default";
}

export function rowLayoutFromModes(modes: Record<string, string> | undefined, catalogId: string): CatalogConfig["layout"] | undefined {
  const raw = modes?.[rowLayoutKey(catalogId)];
  if (typeof raw !== "string") return undefined;
  return raw.trim().toLowerCase() === "poster" ? "poster" : "landscape";
}

/** Card style of each row as the Android app shows it; used when reading the cloud. */
export function applyRowLayoutModes(catalogs: CatalogConfig[], modes: Record<string, string> | undefined): CatalogConfig[] {
  if (!modes || !Object.keys(modes).length) return catalogs;
  return catalogs.map((catalog) => {
    const layout = rowLayoutFromModes(modes, catalog.id);
    return layout && layout !== catalog.layout ? { ...catalog, layout } : catalog;
  });
}

export function withRowLayoutMode(modes: Record<string, string> | undefined, catalogId: string, layout: "landscape" | "poster"): Record<string, string> {
  return { ...(modes ?? {}), [rowLayoutKey(catalogId)]: layout === "poster" ? "Poster" : "Landscape" };
}

export function normalizedRowLayoutModes(value: unknown): Record<string, string> | undefined {
  if (!value || typeof value !== "object" || Array.isArray(value)) return undefined;
  const result: Record<string, string> = {};
  for (const [key, mode] of Object.entries(value as Record<string, unknown>)) {
    if (!key.trim() || typeof mode !== "string") continue;
    result[key] = mode.trim().toLowerCase() === "poster" ? "Poster" : "Landscape";
  }
  return result;
}


export const HOME_WATCHLIST_ROW_ID = "home_watchlist";

/**
 * Android withHomeWatchlistRow (HomeWatchlist.kt): with the synced
 * "Watchlist on Home" setting on (default off) and a non-empty watchlist, a
 * Watchlist row follows Continue Watching, or leads Home when there is none.
 * A presentation row, never a saved catalog.
 */
export function withHomeWatchlistRow(categories: Category[], watchlist: MediaItem[], enabled: boolean): Category[] {
  const base = categories.filter((category) => category.id !== HOME_WATCHLIST_ROW_ID);
  const seen = new Set<string>();
  const items = watchlist.filter((item) => {
    const key = `${item.mediaType}:${item.id}`;
    if (!item.title?.trim() || seen.has(key)) return false;
    seen.add(key);
    return true;
  });
  if (!enabled || !items.length) return base;
  const insertion = base.findIndex((category) => category.id === "continue_watching") + 1;
  const next = [...base];
  next.splice(Math.max(0, insertion), 0, { id: HOME_WATCHLIST_ROW_ID, title: "Watchlist", items });
  return next;
}
