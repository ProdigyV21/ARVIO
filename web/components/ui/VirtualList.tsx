"use client";

import { defaultRangeExtractor, useVirtualizer } from "@tanstack/react-virtual";
import { useCallback, useRef, useState, type ReactNode } from "react";

/** Windowed list with a retained keyboard focus row and adjacent-row navigation. */
export function VirtualList<T>({ items, itemKey, renderItem, estimate = 80, label, className = "", contentWidth, header, preserveHorizontalFocus = false }: {
  items: T[]; itemKey: (item: T) => string; renderItem: (item: T, index: number) => ReactNode;
  estimate?: number; label: string; className?: string;
  contentWidth?: string; header?: ReactNode; preserveHorizontalFocus?: boolean;
}) {
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
    <div ref={parent} className={`virtual-list ${className}`} aria-label={label}
      onBlur={(event) => { if (!event.currentTarget.contains(event.relatedTarget)) setFocused(null); }}
      onKeyDown={(event) => {
        if (!['ArrowDown', 'ArrowUp', 'Home', 'End'].includes(event.key)) return;
        if ((event.target as HTMLElement).matches('input, select, textarea')) return;
        const row = (event.target as HTMLElement).closest<HTMLElement>('[data-virtual-index]');
        if (!row) return;
        const index = Number(row.dataset.virtualIndex);
        const next = event.key === 'Home' ? 0 : event.key === 'End' ? items.length - 1 : index + (event.key === 'ArrowDown' ? 1 : -1);
        if (next < 0 || next >= items.length) return;
        event.preventDefault(); event.stopPropagation();
        setFocused({ key: itemKey(items[next]), index: next });
        virtual.scrollToIndex(next, { align: 'auto' });
        const x = (event.target as HTMLElement).getBoundingClientRect().left;
        requestAnimationFrame(() => {
          const buttons = Array.from(parent.current?.querySelectorAll<HTMLElement>(`[data-virtual-index="${next}"] button`) ?? []);
          const target = preserveHorizontalFocus ? buttons.reduce<HTMLElement | undefined>((best, button) =>
            !best || Math.abs(button.getBoundingClientRect().left - x) < Math.abs(best.getBoundingClientRect().left - x) ? button : best, undefined) : buttons[0];
          target?.focus({ preventScroll: true });
        });
      }}>
      <div style={{ height: virtual.getTotalSize(), width: contentWidth ?? '100%', minWidth: '100%', position: 'relative' }}>
        {header}
        {virtual.getVirtualItems().map((row) => (
          <div key={row.key} data-index={row.index} data-virtual-index={row.index} ref={virtual.measureElement}
            onFocus={() => setFocused({ key: itemKey(items[row.index]), index: row.index })}
            style={{ position: 'absolute', top: 0, left: 0, width: '100%', transform: `translateY(${row.start}px)` }}>
            {renderItem(items[row.index], row.index)}
          </div>
        ))}
      </div>
    </div>
  );
}
