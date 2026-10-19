"use client";
import { useTranslation } from "@/lib/i18n";


import { useEffect, useLayoutEffect, useRef, useState, type ReactNode } from "react";
import { useVirtualizer } from "@tanstack/react-virtual";
import { MediaCard } from "@/components/media/MediaCard";
import type { MediaItem } from "@/lib/types";

let activeCoverRequests = 0;
const waitingCovers: Array<() => void> = [];
async function loadCoverItems(load: () => Promise<MediaItem[]>, active: () => boolean) {
  if (activeCoverRequests >= 2) await new Promise<void>((resolve) => waitingCovers.push(resolve));
  else activeCoverRequests++;
  try { return active() ? await load() : []; }
  finally {
    const next = waitingCovers.shift();
    if (next) next(); else activeCoverRequests--;
  }
}

export function LibraryGrid({ items, poster, onOpen, onNearEnd, onPreview, positions, positionKey }: { positions: Map<string, number>; positionKey: string; items: MediaItem[]; poster: boolean; onOpen: (item: MediaItem) => void; onNearEnd?: () => void; onPreview?: (item: MediaItem) => void }) {
  const viewport = useRef<HTMLDivElement>(null);
  const [width, setWidth] = useState(1000);
  useLayoutEffect(() => {
    const node = viewport.current;
    if (!node) return;
    const observer = new ResizeObserver(([entry]) => setWidth(entry.contentRect.width));
    observer.observe(node);
    return () => observer.disconnect();
  }, []);
  const columns = Math.max(2, Math.min(poster ? 8 : 4, Math.floor(width / (poster ? 145 : 245))));
  const cardWidth = (width - (columns - 1) * 20) / columns;
  const rowHeight = cardWidth * (poster ? 1.5 : 9 / 16) + 86;
  const previewRef = useRef(onPreview); previewRef.current = onPreview;
  useEffect(() => {
    const item = items[Math.floor((viewport.current?.scrollTop ?? 0) / rowHeight) * columns];
    if (item) previewRef.current?.(item);
  }, [items, rowHeight, columns]);
  const virtual = useVirtualizer({ count: Math.ceil(items.length / columns), getScrollElement: () => viewport.current, estimateSize: () => rowHeight, initialOffset: positions.get(positionKey) ?? 0, overscan: 3 });
  useEffect(() => {
    const node = viewport.current;
    return () => { if(node?.isConnected) positions.set(positionKey, node.scrollTop); if(positions.size > 40) positions.delete(positions.keys().next().value!); };
  }, [positions, positionKey]);
  useEffect(() => virtual.measure(), [rowHeight, virtual]);
  const lastRow = virtual.getVirtualItems().at(-1)?.index ?? -1;
  useEffect(() => { if (lastRow >= 0 && (lastRow + 2) * columns >= items.length) onNearEnd?.(); }, [lastRow, columns, items.length, onNearEnd]);
  return <div ref={viewport} className="oled-grid-viewport" onScroll={(event) => {
    positions.set(positionKey, event.currentTarget.scrollTop);
    if (matchMedia('(hover: none)').matches) {
      const item = items[Math.floor(event.currentTarget.scrollTop / rowHeight) * columns];
      if (item) previewRef.current?.(item);
    }
  }} onKeyDown={(event) => {
    const cell = (event.target as HTMLElement).closest<HTMLElement>("[data-library-index]");
    if (!cell) return;
    const index = Number(cell.dataset.libraryIndex);
    const rtl = getComputedStyle(event.currentTarget).direction === "rtl";
    const offset = ({ ArrowLeft: rtl ? 1 : -1, ArrowRight: rtl ? -1 : 1, ArrowUp: -columns, ArrowDown: columns } as Record<string, number>)[event.key];
    if (!offset) return;
    const next = index + offset;
    if(event.key === (rtl ? "ArrowRight" : "ArrowLeft") && index % columns === 0) {
      const source = document.querySelector<HTMLButtonElement>(".oled-source-nav button[aria-current]");
      if(source) { event.preventDefault(); source.focus(); return; }
    }
    if (next < 0) { event.preventDefault(); document.querySelector<HTMLButtonElement>(".oled-library-toolbar nav button[aria-current]")?.focus(); return; }
    if (next >= items.length) return;
    event.preventDefault();
    virtual.scrollToIndex(Math.floor(next / columns), { align: "auto" });
    requestAnimationFrame(() => viewport.current?.querySelector<HTMLButtonElement>(`[data-library-index="${next}"] button`)?.focus({ preventScroll: true }));
  }}>
    <div className="oled-virtual-grid" style={{ height: virtual.getTotalSize() }}>
      {virtual.getVirtualItems().map((row) => <div key={row.key} className="oled-grid-row" style={{ transform: `translateY(${row.start}px)`, gridTemplateColumns: `repeat(${columns}, minmax(0, 1fr))` }}>
        {items.slice(row.index * columns, (row.index + 1) * columns).map((item, column) => <div key={`${item.homeServerId ?? item.mediaType}:${item.homeServerItemId ?? item.id}`} data-library-index={row.index * columns + column} onMouseEnter={() => previewRef.current?.(item)}>
          <MediaCard item={item} posterMode={poster} onOpen={onOpen} onFocus={onPreview}/>
        </div>)}
      </div>)}
    </div>
  </div>;
}

export function CollectionCover({ title, provider, load, onOpen }: { title: string; provider: string; load: () => Promise<MediaItem[]>; onOpen: () => void }) {
  const translateUi = useTranslation();
  const target = useRef<HTMLButtonElement>(null);
  const loader = useRef(load);
  loader.current = load;
  const [cover, setCover] = useState("");
  const [count, setCount] = useState<number | null>(null);
  useEffect(() => {
    let active = true;
    const observer = new IntersectionObserver(([entry]) => {
      if (!entry.isIntersecting) return;
      observer.disconnect();
      void loadCoverItems(() => loader.current(), () => active).then((items) => { if (active) { setCover(items[0]?.backdrop || items[0]?.image || ""); setCount(items.length); } }).catch(() => undefined);
    }, { rootMargin: "100px" });
    if (target.current) observer.observe(target.current);
    return () => { active = false; observer.disconnect(); };
  }, []);
  return <button ref={target} className="oled-collection" onClick={onOpen}>
    <div className="oled-collection-cover">{cover ? <img src={cover} alt="" loading="lazy"/> : <span aria-hidden="true">▤</span>}</div>
    <div className="oled-collection-caption"><strong>{title}</strong><span>{count !== null ? translateUi("{value0} titles · ", {value0: count}) : ""}{provider}</span></div>
  </button>;
}

export function LibraryDialog({ title, close, children }: { title: string; close: () => void; children: ReactNode }) {
  const translateUi = useTranslation();
  const dialog = useRef<HTMLDivElement>(null);
  const closeRef = useRef(close); closeRef.current = close;
  useEffect(() => {
    const previous = document.activeElement as HTMLElement | null;
    dialog.current?.querySelector<HTMLElement>("input, button")?.focus();
    const onKey = (event: KeyboardEvent) => {
      if (event.key === "Escape") { event.preventDefault(); event.stopImmediatePropagation(); closeRef.current(); }
      if (event.key === "Tab") {
        const controls = Array.from(dialog.current?.querySelectorAll<HTMLElement>("button, input, select, [tabindex='0']") ?? []);
        const first = controls[0], last = controls[controls.length - 1];
        if (event.shiftKey && document.activeElement === first) { event.preventDefault(); last?.focus(); }
        else if (!event.shiftKey && document.activeElement === last) { event.preventDefault(); first?.focus(); }
      }
    };
    document.addEventListener("keydown", onKey, true);
    return () => { document.removeEventListener("keydown", onKey, true); previous?.focus(); };
  }, []);
  return <div className="oled-dialog-backdrop" onClick={close}><div ref={dialog} role="dialog" aria-modal="true" aria-label={title} className="oled-dialog" onClick={(event) => event.stopPropagation()}>
    <button className="oled-dialog-close" aria-label={translateUi("Close")} onClick={close}>×</button><h2>{title}</h2>{children}
  </div></div>;
}
