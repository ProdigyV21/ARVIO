"use client";
import { useTranslation } from "@/lib/i18n";


import { defaultRangeExtractor, useVirtualizer } from "@tanstack/react-virtual";
import { useCallback, useRef, useState, type ReactNode } from "react";

/**
 * Windowed list with a retained keyboard focus row and adjacent-row navigation.
 * Up/Down move a row, Page Up/Down a screen of rows, Home/End to the ends.
 * `focusTarget` picks the element to focus in the destination row (the guide
 * uses it to keep a remembered time); otherwise `preserveHorizontalFocus`
 * keeps the nearest left edge, or the row's first button is used.
 */
export function VirtualList<T>({ items, itemKey, renderItem, estimate = 80, label, className = "", contentWidth, header, preserveHorizontalFocus = false, focusTarget }: {
  items: T[]; itemKey: (item: T) => string; renderItem: (item: T, index: number) => ReactNode;
  estimate?: number; label: string; className?: string;
  contentWidth?: string; header?: ReactNode; preserveHorizontalFocus?: boolean;
  focusTarget?: (row: HTMLElement, from: HTMLElement) => HTMLElement | null | undefined;
}) {
  const translateUi = useTranslation();
  const parent = useRef<HTMLDivElement>(null);
  const [focused, setFocused] = useState<{ key: string; index: number } | null>(null);
  const focusedIndex = !focused ? -1 : items[focused.index] && itemKey(items[focused.index]) === focused.key
    ? focused.index : items.findIndex((item) => itemKey(item) === focused.key);
  const getItemKey = useCallback((index: number) => itemKey(items[index]), [itemKey, items]);
  const estimateSize = useCallback(() => estimate, [estimate]);
  const virtual = useVirtualizer({
    useFlushSync: false,
    count: items.length, getScrollElement: () => parent.current,
    estimateSize, overscan: 5, getItemKey,
    paddingStart: header ? 36 : 0,
    rangeExtractor: (range) => {
      const indices = defaultRangeExtractor(range);
      if (focusedIndex >= 0 && !indices.includes(focusedIndex)) indices.push(focusedIndex);
      return indices.sort((a, b) => a - b);
    }
  });
  return (
    <div ref={parent} className={`virtual-list ${className}`} aria-label={translateUi(label)}
      onBlur={(event) => { if (!event.currentTarget.contains(event.relatedTarget)) setFocused(null); }}
      onKeyDown={(event) => {
        if (!['ArrowDown', 'ArrowUp', 'Home', 'End', 'PageUp', 'PageDown'].includes(event.key)) return;
        if ((event.target as HTMLElement).matches('input, select, textarea')) return;
        const row = (event.target as HTMLElement).closest<HTMLElement>('[data-virtual-index]');
        if (!row) return;
        const index = Number(row.dataset.virtualIndex);
        const page = Math.max(1, Math.floor((parent.current?.clientHeight ?? estimate) / estimate) - 1);
        const raw = event.key === 'Home' ? 0 : event.key === 'End' ? items.length - 1
          : event.key === 'PageDown' ? index + page : event.key === 'PageUp' ? index - page
          : index + (event.key === 'ArrowDown' ? 1 : -1);
        const paging = event.key === 'PageUp' || event.key === 'PageDown';
        const next = paging ? Math.max(0, Math.min(items.length - 1, raw)) : raw;
        if (next < 0 || next >= items.length || next === index) {
          // Page keys at the ends must not scroll the page behind the list.
          if (paging) event.preventDefault();
          return;
        }
        event.preventDefault(); event.stopPropagation();
        setFocused({ key: itemKey(items[next]), index: next });
        virtual.scrollToIndex(next, { align: paging ? 'start' : 'auto' });
        const from = event.target as HTMLElement;
        const x = from.getBoundingClientRect().left;
        const focusRow = (attempt: number) => {
          const rowElement = parent.current?.querySelector<HTMLElement>(`[data-virtual-index="${next}"]`);
          if (!rowElement) { if (attempt < 3) requestAnimationFrame(() => focusRow(attempt + 1)); return; }
          const picked = focusTarget?.(rowElement, from);
          if (picked) { picked.focus({ preventScroll: true }); return; }
          const buttons = Array.from(rowElement.querySelectorAll<HTMLElement>('button:not([tabindex="-1"])'));
          const target = preserveHorizontalFocus ? buttons.reduce<HTMLElement | undefined>((best, button) =>
            !best || Math.abs(button.getBoundingClientRect().left - x) < Math.abs(best.getBoundingClientRect().left - x) ? button : best, undefined) : buttons[0];
          target?.focus({ preventScroll: true });
        };
        requestAnimationFrame(() => focusRow(0));
      }}>
      <div style={{ height: virtual.getTotalSize(), width: contentWidth ?? '100%', minWidth: '100%', position: 'relative' }}>
        {header}
        {virtual.getVirtualItems().map((row) => (
          <div key={row.key} data-index={row.index} data-virtual-index={row.index} ref={virtual.measureElement}
            onFocus={() => { if (focused?.index !== row.index || focused.key !== row.key) setFocused({ key: itemKey(items[row.index]), index: row.index }); }}
            style={{ position: 'absolute', top: 0, left: 0, width: '100%', transform: `translateY(${row.start}px)` }}>
            {renderItem(items[row.index], row.index)}
          </div>
        ))}
      </div>
    </div>
  );
}
