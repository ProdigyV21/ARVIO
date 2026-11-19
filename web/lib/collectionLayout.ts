import { canonicalServiceName, serviceClearLogo } from "./serviceLogos";
import type { CatalogConfig } from "./types";

/**
 * The streaming service a collection is about ("Netflix", "Prime Video"), if
 * any. Its badge would repeat on every card of the collection, so cards hide it.
 */
export function collectionService(catalog: Pick<CatalogConfig, "name" | "collectionGroup" | "collectionSources">): string | undefined {
  const byProvider = (catalog.collectionSources ?? []).some((source) => String(source.kind).toUpperCase() === "TMDB_WATCH_PROVIDER");
  if (!byProvider && String(catalog.collectionGroup ?? "").toUpperCase() !== "SERVICE") return undefined;
  return serviceClearLogo(catalog.name) ? canonicalServiceName(catalog.name) : undefined;
}

/**
 * Columns for the collection grid: 4-5 landscape cards on a 1440px laptop
 * (3 before), 2 on phones; posters about twice as many.
 */
export function collectionColumns(width: number, posters: boolean) {
  const min = posters ? (width < 600 ? 126 : 196) : (width < 600 ? 160 : width < 1100 ? 260 : 268);
  return Math.max(1, Math.floor((width + 16) / min));
}
