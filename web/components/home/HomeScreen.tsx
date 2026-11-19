"use client";
import { DEFAULT_COLLECTIONS_DISABLED, isDefaultCollection, mergeCatalogs, updateHiddenCatalogIds } from "@/lib/catalogs";
import { createCollectionLoader } from "@/lib/collectionLoader";
import {
  collectionFolders,
  collectionMediaTypes,
  collectionParentRail,
  orphanedEnabledFolders,
  setCatalogsEnabledWithParentRails,
  visibleHomeCatalogs
} from "@/lib/collectionPresentation";
import { useTranslation } from "@/lib/i18n";
import { requestSourceSettings } from "@/lib/sourceSetup";

import { useCallback, useEffect, useMemo, useRef, useState } from "react";
import { createHeroController } from "@/lib/homeHero";
import { HOME_WATCHLIST_ROW_ID, withHomeWatchlistRow } from "@/lib/homeRows";
import { requestPlayOnOpen } from "@/lib/playIntent";
import { useAppPick } from "@/lib/store";
import { useKidsFilter } from "@/lib/useKids";
import { LazyRail, type LazyRailOutcome } from "@/components/media/LazyRail";
import { CustomCollectionRail } from "@/components/media/CustomCollectionRail";
import { MediaRail } from "@/components/media/MediaRail";
import { CategoryViewAll } from "@/components/media/CategoryViewAll";
import { FavoriteTvRail } from "@/components/media/FavoriteTvRail";
import { FAVORITE_TV_CATALOG_ID } from "@/lib/favoriteTv";
import { HOME_ROW_LIMIT } from "@/lib/tmdb";
import type { CatalogConfig, Category, MediaItem } from "@/lib/types";
import { RowBoundary } from "@/components/shell/ErrorBoundary";
import { HomeHero } from "./HomeHero";
import "@/app/home.css";

type ViewAll = { catalog: CatalogConfig | null; category: Category };

export function HomeScreen({ onCollectionOpenChange }: { onCollectionOpenChange?: (open: boolean) => void }) {
  const translateUi = useTranslation();
  const {
    categories, catalogConfigs, homeServerRows, continueWatching, watchlist, openDetails,
    settings, addons, favoriteTvChannels, setSection, updateSettings
  } = useAppPick(
    "categories", "catalogConfigs", "homeServerRows", "continueWatching", "watchlist",
    "openDetails", "settings", "addons", "favoriteTvChannels", "setSection", "updateSettings"
  );
  const posterMode = settings.cardLayoutMode === "poster";
  const kids = useKidsFilter();
  const kidsAllows = useRef(kids.allows);
  kidsAllows.current = kids.allows;

  // The billboard's state lives here, outside the app store: only HomeHero
  // subscribes, so its 8 s rotation and card hovers don't re-render the app.
  const [hero] = useState(() => createHeroController());
  const hoverTimer = useRef<ReturnType<typeof setTimeout> | null>(null);
  useEffect(() => () => { if (hoverTimer.current) clearTimeout(hoverTimer.current); }, []);

  // The eager rails (trending/popular/provider lists) overlap heavily; keep each
  // title in the first rail it appears in and trim repeats from later rails,
  // unless doing so would hollow a rail out.
  const dedupedCategories = useMemo(() => {
    const seen = new Set<string>();
    return withHomeWatchlistRow(categories, watchlist, settings.watchlistOnHome === true).map((category) => {
      if (category.id === HOME_WATCHLIST_ROW_ID) return category;
      const kept = category.items.filter((item) => !seen.has(`${item.mediaType}-${item.id}`));
      const items = kept.length >= Math.min(8, category.items.length) ? kept : category.items;
      items.forEach((item) => seen.add(`${item.mediaType}-${item.id}`));
      return items === category.items ? category : { ...category, items };
    });
  }, [categories, watchlist, settings.watchlistOnHome]);

  const [heroSeeded, setHeroSeeded] = useState(false);
  const [railOutcomes, setRailOutcomes] = useState<ReadonlyMap<string, LazyRailOutcome>>(() => new Map());
  const homeCatalogs = useMemo(() => visibleHomeCatalogs(catalogConfigs), [catalogConfigs]);
  const eagerLazyIds = useMemo(
    () => homeCatalogs.slice(0, 8)
      .filter((catalog) => catalog.id !== FAVORITE_TV_CATALOG_ID && String(catalog.kind).toUpperCase() !== "COLLECTION_RAIL")
      .map((catalog) => catalog.id),
    [homeCatalogs]
  );
  const heroFolders = useMemo(
    () => homeCatalogs
      .filter((catalog) => String(catalog.kind).toUpperCase() === "COLLECTION_RAIL")
      .flatMap((rail) => collectionFolders(rail, catalogConfigs)),
    [homeCatalogs, catalogConfigs]
  );

  // Stable callbacks: MediaCard is memoised on them.
  const onCardFocus = useCallback((item: MediaItem) => {
    if (hoverTimer.current) clearTimeout(hoverTimer.current);
    hoverTimer.current = setTimeout(() => hero.pin(item), 220);
  }, [hero]);
  const seedHeroFromRow = useCallback((row: Category) => {
    hero.addToPool(row.items, (item) => kidsAllows.current(item));
    if (hero.getState().pool.length > 0) setHeroSeeded(true);
  }, [hero]);
  const markRailSettled = useCallback((catalogId: string, outcome: LazyRailOutcome) => {
    setRailOutcomes((prev) => {
      if (prev.get(catalogId) === outcome) return prev;
      const next = new Map(prev);
      next.set(catalogId, outcome);
      return next;
    });
  }, []);
  const playFromHero = useCallback((item: MediaItem) => {
    // Android billboard Play: open the title with play intent; details starts
    // playback through its automatic source choice (see DetailsView).
    if (item.id > 0) requestPlayOnOpen(item);
    void openDetails(item);
  }, [openDetails]);

  const fallbackHero = continueWatching[0] ?? categories[0]?.items[0] ?? null;
  const hasHeroFallback = Boolean(fallbackHero && kids.allows(fallbackHero));
  const heroFallbackKey = eagerLazyIds.length === 0 && heroFolders.length > 0
    ? `${settings.language}:${heroFolders[0].id}:${heroFolders.length}`
    : "";
  const [collectionHeroLoading, setCollectionHeroLoading] = useState(() => Boolean(heroFallbackKey));
  useEffect(() => {
    if (!heroFallbackKey || heroSeeded || hasHeroFallback) {
      setCollectionHeroLoading(false);
      return undefined;
    }
    const folder = heroFolders[0];
    if (!folder) { setCollectionHeroLoading(false); return undefined; }
    let cancelled = false;
    setCollectionHeroLoading(true);
    const types = collectionMediaTypes(folder);
    const primary = types[0] ?? "movie";
    void (async () => {
      try {
        let result = await createCollectionLoader(folder, primary, settings.language, addons, settings.homeServers)();
        if (!result.items.some((item) => item.backdrop && kidsAllows.current(item)) && types[1]) {
          result = await createCollectionLoader(folder, types[1], settings.language, addons, settings.homeServers)();
        }
        if (!cancelled && result.items.length) seedHeroFromRow({ id: folder.id, title: folder.name, items: result.items });
      } catch {
        // Offline / TMDB failure: fall back cleanly without leaving a skeleton loop.
      } finally {
        if (!cancelled) setCollectionHeroLoading(false);
      }
    })();
    return () => { cancelled = true; };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [heroFallbackKey]);

  const [viewAll, setViewAll] = useState<ViewAll | null>(null);
  useEffect(() => {
    if (!viewAll) return undefined;
    // Keeps Home (and so this page) mounted while a title's details are open.
    onCollectionOpenChange?.(true);
    return () => onCollectionOpenChange?.(false);
  }, [viewAll, onCollectionOpenChange]);
  const openViewAll = useCallback((catalog: CatalogConfig | null, category: Category) => setViewAll({ catalog, category }), []);

  const eagerLazyPending = eagerLazyIds.some((id) => {
    const state = railOutcomes.get(id);
    return !state || state === "loading";
  });
  const heroLoading = !heroSeeded && !hasHeroFallback && (eagerLazyIds.length > 0 ? eagerLazyPending : collectionHeroLoading);
  const heroRendered = heroSeeded || hasHeroFallback || heroLoading;
  const hasFavoriteTv = !kids.active && favoriteTvChannels.length > 0;
  const hasMountedRows = dedupedCategories.length > 0 || homeServerRows.length > 0 || hasFavoriteTv
    || homeCatalogs.some((catalog) =>
      catalog.id !== FAVORITE_TV_CATALOG_ID &&
      (String(catalog.kind).toUpperCase() === "COLLECTION_RAIL" || railOutcomes.get(catalog.id) !== "empty")
    );

  const mergedCatalogs = useMemo(
    () => hasMountedRows ? [] : mergeCatalogs(settings.catalogs, settings.hiddenCatalogIds, settings.hiddenAddonCatalogIds),
    [hasMountedRows, settings.catalogs, settings.hiddenCatalogIds, settings.hiddenAddonCatalogIds]
  );
  const orphanedFolders = useMemo(() => orphanedEnabledFolders(mergedCatalogs), [mergedCatalogs]);
  const orphanedRails = useMemo(() => {
    const rails = new Map<string, CatalogConfig>();
    for (const folder of orphanedFolders) {
      const parent = collectionParentRail(folder, mergedCatalogs);
      if (parent) rails.set(parent.id, parent);
    }
    return [...rails.values()];
  }, [orphanedFolders, mergedCatalogs]);

  const openHomeRowsSettings = () => {
    requestSourceSettings("catalogs");
    setSection("settings");
  };
  const showOrphanedRails = () => {
    if (!orphanedFolders.length) return;
    const next = setCatalogsEnabledWithParentRails(mergedCatalogs, new Set(orphanedFolders.map((c) => c.id)), true);
    const standard = next.filter((c) => c.sourceType !== "home-server");
    const baseHidden = next.some((c) => isDefaultCollection(c) && c.enabled)
      ? settings.hiddenCatalogIds.filter((id) => id !== DEFAULT_COLLECTIONS_DISABLED)
      : settings.hiddenCatalogIds;
    updateSettings({
      catalogs: next,
      hiddenCatalogIds: updateHiddenCatalogIds(standard, baseHidden),
      hiddenAddonCatalogIds: updateHiddenCatalogIds(standard.filter((c) => c.sourceType === "addon"), settings.hiddenAddonCatalogIds),
    });
  };

  const folderNames = orphanedFolders.slice(0, 3).map((c) => c.name).join(", ")
    + (orphanedFolders.length > 3 ? ` +${orphanedFolders.length - 3}` : "");
  const railNames = orphanedRails.map((c) => c.name).join(", ");

  return (
    // has-hero mirrors the CSS :has(.hero) padding rule for TV browsers whose
    // engines predate :has() support (Tizen/webOS).
    <div className={`screen ${heroRendered ? "has-hero " : ""}home-screen`}>
      <HomeHero controller={hero} fallback={hasHeroFallback ? fallbackHero : null}
        loading={heroLoading} onOpen={openDetails} onPlay={playFromHero} />
      {dedupedCategories.map((category) => {
        // Presentation rows (Continue Watching, Watchlist) are complete lists;
        // they show the first HOME_ROW_LIMIT titles and open the rest in the grid.
        const capped = category.items.length > HOME_ROW_LIMIT;
        const shown = capped ? { ...category, items: category.items.slice(0, HOME_ROW_LIMIT) } : category;
        return <RowBoundary key={category.id} name={category.id} resetKey={category.items}>
          <MediaRail category={shown} onOpen={openDetails} onFocus={onCardFocus} posterMode={posterMode}
            onViewAll={capped ? () => openViewAll(null, category) : undefined} />
        </RowBoundary>;
      })}
      {/* Each row is isolated: one that throws drops out instead of taking Home down. */}
      {homeCatalogs.map((catalog, index) => <RowBoundary key={catalog.id} name={catalog.id} resetKey={catalog}>{
        catalog.id === FAVORITE_TV_CATALOG_ID ? (kids.active ? null : <FavoriteTvRail title={catalog.name} />) :
        String(catalog.kind).toUpperCase() === "COLLECTION_RAIL" ?
        <CustomCollectionRail catalog={catalog} onOpen={openDetails} onCollectionOpenChange={onCollectionOpenChange}
          folders={collectionFolders(catalog, catalogConfigs)} /> :
        <LazyRail
          catalog={catalog}
          eager={index < 8}
          onOpen={openDetails}
          onFocus={onCardFocus}
          onLoaded={seedHeroFromRow}
          onSettled={markRailSettled}
          onViewAll={openViewAll}
        />
      }</RowBoundary>)}
      {/* Like Android, a catalog list without the row still shows it, after the others. */}
      {!kids.active && !catalogConfigs.some((catalog) => catalog.id === FAVORITE_TV_CATALOG_ID) && <FavoriteTvRail />}
      {homeServerRows.map((category) => (
        <RowBoundary key={category.id} name={category.id} resetKey={category.items}>
          <MediaRail category={category} onOpen={openDetails} onFocus={onCardFocus} posterMode={posterMode} />
        </RowBoundary>
      ))}
      {!hasMountedRows && !heroLoading && (
        <section className="home-empty-state" role="status">
          {orphanedRails.length > 0 ? <>
            <h2>{translateUi("Your selected collections are in a hidden rail")}</h2>
            <p>{translateUi("You enabled {value0}, which lives inside the hidden {value1} rail.", { value0: folderNames, value1: railNames })}</p>
            <div className="home-empty-actions">
              <button type="button" className="primary" onClick={showOrphanedRails}>{translateUi("Show {value0} on Home", { value0: railNames })}</button>
              <button type="button" className="secondary" onClick={openHomeRowsSettings}>{translateUi("Customize Home rows")}</button>
            </div>
          </> : <>
            <h2>{translateUi("No Home rows are shown")}</h2>
            <p>{translateUi("Turn rows back on in Settings to populate your Home screen.")}</p>
            <div className="home-empty-actions">
              <button type="button" className="primary" onClick={openHomeRowsSettings}>{translateUi("Customize Home rows")}</button>
            </div>
          </>}
        </section>
      )}
      {viewAll && <CategoryViewAll key={viewAll.category.id} catalog={viewAll.catalog} preview={viewAll.category}
        onBack={() => setViewAll(null)} onOpen={openDetails} />}
    </div>
  );
}
