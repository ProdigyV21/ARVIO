"use client";
import "@/app/collections.css";

import { useCallback, useEffect, useRef, useState } from 'react';
import { createPortal } from 'react-dom';
import { useVirtualizer } from '@tanstack/react-virtual';
import { ArrowLeft, LoaderCircle, RotateCw } from 'lucide-react';
import { useAppPick } from '@/lib/store';
import { useTranslation } from '@/lib/i18n';
import { collectionMediaTypes } from '@/lib/collectionPresentation';
import { createCollectionLoader } from '@/lib/collectionLoader';
import { collectionColumns, collectionService } from '@/lib/collectionLayout';
import { CollectionPreview } from './CollectionPreview';
import type { CatalogConfig, MediaItem, MediaType } from '@/lib/types';
import { MediaCard } from './MediaCard';
import { useModalDialog } from './useModalDialog';

export function CollectionDetails({ catalog, onBack, onOpen }: { catalog: CatalogConfig; onBack: () => void; onOpen: (item: MediaItem) => void }) {
  const { settings, addons, selected } = useAppPick("settings", "addons", "selected");
  const types = collectionMediaTypes(catalog);
  const [type, setType] = useState<MediaType>(types[0] || 'movie');
  const dialog = useRef<HTMLDialogElement>(null);
  // Focus moves inside on open, returns to the card a title opened from after
  // its details, and to the collection tile on close; Escape/TV Back close it.
  const { onKeyDown, onCancel } = useModalDialog(dialog, { open: !selected, onClose: onBack });
  useEffect(() => {
    if (selected) return;
    const previous = document.body.style.overflow;
    document.body.style.overflow = 'hidden';
    return () => { document.body.style.overflow = previous; };
  }, [selected]);
  return createPortal(<dialog ref={dialog} className="collection-page" role="dialog" aria-label={catalog.name}
    onKeyDown={onKeyDown} onCancel={onCancel}>
    <CollectionContent key={`${catalog.id}:${settings.language}:${JSON.stringify(catalog.collectionSources)}`} catalog={catalog}
      type={type} types={types} setType={setType} onBack={onBack} onOpen={onOpen}
      language={settings.language} addons={addons} servers={settings.homeServers} posterMode={settings.cardLayoutMode === 'poster'} />
  </dialog>, document.body);
}

type TabState = { items: MediaItem[]; loading: boolean; hasMore: boolean; errors: string[]; scroll: number; focus?: MediaItem };
const emptyTab = (): TabState => ({ items: [], loading: false, hasMore: true, errors: [], scroll: 0 });

function CollectionContent({ catalog, type, types, setType, onBack, onOpen, language, addons, servers, posterMode }: {
  catalog: CatalogConfig; type: MediaType; types: MediaType[]; setType: (type: MediaType) => void;
  onBack: () => void; onOpen: (item: MediaItem) => void; language: string;
  addons: Parameters<typeof createCollectionLoader>[3]; servers: Parameters<typeof createCollectionLoader>[4]; posterMode: boolean;
}) {
  const t = useTranslation();
  const tabs = useRef<Record<MediaType, TabState>>({ movie: emptyTab(), tv: emptyTab() });
  const loaders = useRef<Partial<Record<MediaType, ReturnType<typeof createCollectionLoader>>>>({});
  const alive = useRef(true);
  const [, update] = useState(0);
  const viewport = useRef<HTMLDivElement>(null);
  const [width, setWidth] = useState(1000);
  const tab = tabs.current[type];
  const posters = catalog.collectionGroup !== 'GENRE' && posterMode;
  const columns = collectionColumns(width, posters);
  const hideService = collectionService(catalog);
  const cardWidth = (width - (columns - 1) * 16) / columns;
  const rowHeight = Math.ceil(cardWidth * (posters ? 1.5 : 9 / 16)) + 88;
  const rows = useVirtualizer({ count: Math.ceil(tab.items.length / columns), getScrollElement: () => viewport.current,
    estimateSize: () => rowHeight, overscan: 2, getItemKey: index => `${type}:${index}:${columns}` });
  useEffect(() => { rows.measure(); }, [rowHeight, type, rows]);
  useEffect(() => {
    const node = viewport.current;
    if (!node) return;
    const observer = new ResizeObserver(entries => setWidth(entries[0].contentRect.width));
    observer.observe(node);
    return () => observer.disconnect();
  }, []);
  useEffect(() => { alive.current = true; return () => { alive.current = false; }; }, []);
  const load = useCallback(async (target: MediaType) => {
    const state = tabs.current[target];
    if (state.loading || !state.hasMore) return;
    state.loading = true;
    update(n => n + 1);
    try {
      loaders.current[target] ??= createCollectionLoader(catalog, target, language, addons, servers);
      const result = await loaders.current[target]!();
      if (!alive.current) return;
      state.items = [...state.items, ...result.items];
      state.hasMore = result.hasMore;
      state.errors = result.errors;
    } catch (error) {
      state.errors = [error instanceof Error ? error.message : 'Collection could not be loaded'];
    } finally {
      state.loading = false;
      if (alive.current) update(n => n + 1);
    }
  }, [catalog, language, addons, servers]);
  useEffect(() => {
    viewport.current?.scrollTo({ top: tabs.current[type].scroll, behavior: 'instant' });
    if (!tabs.current[type].items.length && !tabs.current[type].errors.length) void load(type);
  }, [type, load]);
  const focus = useCallback((item: MediaItem) => { tabs.current[item.mediaType].focus = item; update(n => n + 1); }, []);
  const preview = tab.focus || tab.items[0];
  return <div className="collection-page-body">
    <header className="collection-page-header"><button className="icon-button" type="button" data-autofocus title={t('Back')} aria-label={t('Back')} onClick={onBack}><ArrowLeft size={24} /></button><h1>{catalog.name}</h1></header>
    <CollectionPreview preview={preview} language={language} fallbackTitle={catalog.name} hideService={hideService}
      fallbackOverview={catalog.collectionDescription} fallbackArtwork={catalog.collectionHeroImageUrl} />
    <div className="collection-tabs" role="tablist" aria-label={catalog.name}>{types.map(value => <button key={value} id={`collection-tab-${value}`} type="button" role="tab" aria-selected={type === value} aria-controls="collection-items"
      onClick={() => setType(value)} onKeyDown={e => { if (e.key === 'ArrowLeft' || e.key === 'ArrowRight') { e.preventDefault(); const next = types[(types.indexOf(value) + 1) % types.length]; setType(next); document.getElementById(`collection-tab-${next}`)?.focus(); } }}>{t(value === 'movie' ? 'Movies' : 'Series')}</button>)}</div>
    <div ref={viewport} id="collection-items" className="collection-grid-viewport" role="tabpanel" aria-labelledby={`collection-tab-${type}`} aria-busy={tab.loading}
      onScroll={e => { tab.scroll = e.currentTarget.scrollTop; if (!tab.errors.length && e.currentTarget.scrollHeight - e.currentTarget.scrollTop - e.currentTarget.clientHeight < 350) void load(type); }}>
      <div className="collection-virtual-grid" style={{ height: rows.getTotalSize() }}>
        {rows.getVirtualItems().map(row => <div key={row.key} className="collection-grid-row" style={{ transform: `translateY(${row.start}px)`, gridTemplateColumns: `repeat(${columns}, minmax(0, 1fr))` }}>
          {tab.items.slice(row.index * columns, (row.index + 1) * columns).map(media => <div key={`${media.mediaType}:${media.id}`} onMouseEnter={() => focus(media)}><MediaCard item={media} onOpen={onOpen} onFocus={focus} posterMode={posters} hideService={hideService} /></div>)}
        </div>)}
      </div>
      <footer className="collection-load-state">
        {tab.loading ? <span role="status"><LoaderCircle className="spin" size={20} />{t('Loading...')}</span> : <>
          {tab.errors.length > 0 && <p role="alert">{t('Some sources could not be loaded.')}</p>}
          {!tab.items.length && !tab.errors.length && <p>{t('No titles found')}</p>}
          {tab.hasMore && <button className="secondary" type="button" onClick={() => void load(type)}>{tab.errors.length ? <><RotateCw size={18} />{t('Retry')}</> : t('Load more')}</button>}
        </>}
      </footer>
    </div>
  </div>;
}
