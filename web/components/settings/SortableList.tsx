"use client";
// One reorderable list for every layout editor in Settings (Live TV
// categories and channels, favourites, addons, source priority, Home rows,
// home servers).
//
//  - Mouse and touch: drag the grip handle.
//  - Keyboard: focus the grip, Space/Enter picks the row up, arrow keys move it
//    (Page Up/Down by ten, Home/End to the ends), Space/Enter drops it, Escape
//    puts it back. Leaving the row (Tab, Left/Right on a TV remote) drops it.
//  - TV remotes and screen readers: explicit up/down/top/bottom buttons.
//  - Every move is announced through an aria-live region.
//  - Long lists (50,000 channels) are virtualised when `rowHeight` is given.

import "@/app/settings-order.css";
import { ArrowDown, ArrowUp, ChevronsDown, ChevronsUp, GripVertical } from "lucide-react";
import { useCallback, useEffect, useId, useMemo, useRef, useState, type KeyboardEvent, type PointerEvent as ReactPointerEvent, type ReactNode } from "react";
import { useTranslation } from "@/lib/i18n";
import { previewSourceIndex } from "@/lib/ordering";

export interface SortableRowContext {
  index: number;
  total: number;
  moving: boolean;
}

interface Props<T> {
  items: T[];
  getKey: (item: T) => string;
  getLabel: (item: T) => string;
  /** Commit a move. Indices are positions in `items`. */
  onMove: (from: number, to: number) => void;
  renderMain: (item: T, context: SortableRowContext) => ReactNode;
  renderActions?: (item: T, context: SortableRowContext) => ReactNode;
  renderFooter?: (item: T, context: SortableRowContext) => ReactNode;
  /** Accessible name of the list (already translated). */
  label: string;
  /** Fixed row height in px: turns on virtualisation once the list is longer than `virtualizeAbove`. */
  rowHeight?: number;
  /** Height of the scroll viewport for a virtualised list. */
  viewportHeight?: number;
  virtualizeAbove?: number;
  /** Turn reordering off (e.g. while a sort order other than manual applies). */
  disabled?: boolean;
  /** Hide the top/bottom buttons (very short lists). */
  edgeButtons?: boolean;
  /** Extra class per row. */
  rowClassName?: (item: T) => string;
  className?: string;
  emptyText?: ReactNode;
}

type Moving = { key: string; from: number; to: number; mode: "keyboard" | "pointer" };

const OVERSCAN = 8;

export function SortableList<T>({
  items, getKey, getLabel, onMove, renderMain, renderActions, renderFooter, label, rowHeight, viewportHeight = 480,
  virtualizeAbove = 120, disabled, edgeButtons = true, rowClassName, className, emptyText,
}: Props<T>) {
  const translateUi = useTranslation();
  const instructionsId = useId();
  const [moving, setMoving] = useState<Moving | null>(null);
  const [announcement, setAnnouncement] = useState("");
  const [scrollTop, setScrollTop] = useState(0);
  const viewportRef = useRef<HTMLDivElement>(null);
  const listRef = useRef<HTMLUListElement>(null);
  const movingRef = useRef<Moving | null>(null);
  movingRef.current = moving;
  const dragRef = useRef<{ pointerId: number; startY: number; startScroll: number; mids: number[]; moved: boolean; lastY: number } | null>(null);
  const suppressClick = useRef(false);
  // Space/Enter are handled on keydown; ignore the click the browser may still synthesise.
  const keyActivated = useRef(false);
  const total = items.length;
  const virtual = Boolean(rowHeight) && total > virtualizeAbove;

  // The moved row follows the pointer/keyboard; everything else shifts around it.
  const sourceIndexAt = useCallback((display: number) => moving ? previewSourceIndex(display, moving.from, moving.to) : display, [moving]);

  // A row that disappears (filter, sync from another device) ends the move.
  useEffect(() => {
    if (!moving) return;
    const index = items.findIndex((item) => getKey(item) === moving.key);
    if (index < 0) setMoving(null);
    else if (index !== moving.from) setMoving({ ...moving, from: index, to: Math.min(moving.to, total - 1) });
  }, [items, getKey, moving, total]);

  const announce = (text: string) => setAnnouncement(text);
  const position = (index: number) => translateUi("position {value0} of {value1}", { value0: index + 1, value1: total });

  const commit = useCallback((state: Moving | null, silent = false) => {
    if (!state) return;
    setMoving(null);
    const item = items[state.from];
    if (state.to !== state.from) onMove(state.from, state.to);
    if (!silent && item) announce(translateUi("{value0} dropped at {value1}.", { value0: getLabel(item), value1: translateUi("position {value0} of {value1}", { value0: state.to + 1, value1: total }) }));
  }, [getLabel, items, onMove, translateUi, total]);

  const cancel = useCallback(() => {
    const state = movingRef.current;
    if (!state) return;
    setMoving(null);
    const item = items[state.from];
    if (item) announce(translateUi("Move cancelled. {value0} is back at {value1}.", { value0: getLabel(item), value1: translateUi("position {value0} of {value1}", { value0: state.from + 1, value1: total }) }));
  }, [getLabel, items, translateUi, total]);

  const moveNow = (from: number, to: number) => {
    const target = Math.max(0, Math.min(total - 1, to));
    if (target === from || disabled) return;
    const item = items[from];
    onMove(from, target);
    if (item) announce(translateUi("{value0} moved to {value1}.", { value0: getLabel(item), value1: translateUi("position {value0} of {value1}", { value0: target + 1, value1: total }) }));
  };

  // Moving a row re-inserts its DOM node, which can drop focus: give it back.
  useEffect(() => {
    if (!moving || moving.mode !== "keyboard") return;
    const handle = listRef.current?.querySelector<HTMLElement>(`[data-sort-key="${CSS.escape(moving.key)}"] .st-sort-handle`);
    if (handle && document.activeElement !== handle) handle.focus({ preventScroll: true });
  });

  // A click anywhere else drops a row held with the keyboard.
  const keyboardKey = moving?.mode === "keyboard" ? moving.key : null;
  useEffect(() => {
    if (!keyboardKey) return;
    const onDown = (event: PointerEvent) => {
      const target = event.target as Element | null;
      if (target?.closest?.(`[data-sort-key="${CSS.escape(keyboardKey)}"] .st-sort-handle`)) return;
      commit(movingRef.current, true);
    };
    document.addEventListener("pointerdown", onDown, true);
    return () => document.removeEventListener("pointerdown", onDown, true);
  }, [keyboardKey, commit]);

  // Keep the moving row on screen.
  useEffect(() => {
    if (!moving || moving.mode !== "keyboard") return;
    if (virtual && viewportRef.current && rowHeight) {
      const viewport = viewportRef.current;
      const top = moving.to * rowHeight;
      if (top < viewport.scrollTop) viewport.scrollTop = top;
      else if (top + rowHeight > viewport.scrollTop + viewport.clientHeight) viewport.scrollTop = top + rowHeight - viewport.clientHeight;
    } else {
      const handle = listRef.current?.querySelector<HTMLElement>(`[data-sort-key="${CSS.escape(moving.key)}"] .st-sort-handle`);
      handle?.scrollIntoView({ block: "nearest" });
    }
  }, [moving, virtual, rowHeight]);

  const onHandleKeyDown = (event: KeyboardEvent<HTMLButtonElement>, index: number, key: string) => {
    if (disabled) return;
    const state = movingRef.current;
    const pick = event.key === " " || event.key === "Enter";
    if (pick) keyActivated.current = true;
    if (!state || state.key !== key) {
      if (!pick) return;
      event.preventDefault();
      event.stopPropagation();
      setMoving({ key, from: index, to: index, mode: "keyboard" });
      announce(translateUi("Picked up {value0}, {value1}. Use the arrow keys to move it, Enter to drop it, Escape to cancel.", { value0: getLabel(items[index]), value1: position(index) }));
      return;
    }
    const step = (to: number) => {
      event.preventDefault();
      event.stopPropagation();
      const target = Math.max(0, Math.min(total - 1, to));
      if (target === state.to) return;
      setMoving({ ...state, to: target });
      announce(translateUi("{value0}, {value1}.", { value0: getLabel(items[state.from]), value1: translateUi("position {value0} of {value1}", { value0: target + 1, value1: total }) }));
    };
    switch (event.key) {
      case "ArrowUp": step(state.to - 1); break;
      case "ArrowDown": step(state.to + 1); break;
      case "PageUp": step(state.to - 10); break;
      case "PageDown": step(state.to + 10); break;
      case "Home": step(0); break;
      case "End": step(total - 1); break;
      case " ": case "Enter": event.preventDefault(); event.stopPropagation(); commit(state); break;
      case "Escape": event.preventDefault(); event.stopPropagation(); cancel(); break;
      case "ArrowLeft": case "ArrowRight": event.preventDefault(); commit(state); break;
      case "Tab": commit(state); break;
      default: break;
    }
  };

  /* ---------- pointer drag ---------- */

  const rowMidpoints = () => {
    if (virtual && rowHeight) return [];
    const rows = Array.from(listRef.current?.querySelectorAll<HTMLElement>(":scope > .st-sort-row") ?? []);
    return rows.map((row) => { const rect = row.getBoundingClientRect(); return rect.top + rect.height / 2 + window.scrollY; });
  };

  const targetFor = (clientY: number) => {
    const drag = dragRef.current;
    const state = movingRef.current;
    if (!drag || !state) return 0;
    if (virtual && rowHeight && viewportRef.current) {
      const rect = viewportRef.current.getBoundingClientRect();
      return Math.max(0, Math.min(total - 1, Math.floor((clientY - rect.top + viewportRef.current.scrollTop) / rowHeight)));
    }
    const y = clientY + window.scrollY;
    let target = 0;
    drag.mids.forEach((mid, index) => { if (index !== state.from && mid < y) target++; });
    return Math.max(0, Math.min(total - 1, target));
  };

  const autoScroll = useRef<number | null>(null);
  const stopAutoScroll = () => { if (autoScroll.current !== null) cancelAnimationFrame(autoScroll.current); autoScroll.current = null; };
  useEffect(() => stopAutoScroll, []);

  const onPointerDown = (event: ReactPointerEvent<HTMLButtonElement>, index: number, key: string) => {
    if (disabled || event.button !== 0) return;
    event.currentTarget.setPointerCapture(event.pointerId);
    dragRef.current = { pointerId: event.pointerId, startY: event.clientY, startScroll: viewportRef.current?.scrollTop ?? window.scrollY, mids: rowMidpoints(), moved: false, lastY: event.clientY };
    setMoving({ key, from: index, to: index, mode: "pointer" });
  };

  const onPointerMove = (event: ReactPointerEvent<HTMLButtonElement>) => {
    const drag = dragRef.current;
    const state = movingRef.current;
    if (!drag || drag.pointerId !== event.pointerId || !state) return;
    drag.lastY = event.clientY;
    if (!drag.moved && Math.abs(event.clientY - drag.startY) < 4) return;
    drag.moved = true;
    const to = targetFor(event.clientY);
    if (to !== state.to) setMoving({ ...state, to });
    // Scroll while the pointer is held near an edge.
    if (autoScroll.current === null) {
      const tick = () => {
        const current = dragRef.current;
        if (!current) { autoScroll.current = null; return; }
        const viewport = virtual ? viewportRef.current : null;
        const top = viewport ? viewport.getBoundingClientRect().top : 0;
        const bottom = viewport ? viewport.getBoundingClientRect().bottom : window.innerHeight;
        const edge = 48;
        const speed = current.lastY < top + edge ? -12 : current.lastY > bottom - edge ? 12 : 0;
        if (speed) {
          if (viewport) viewport.scrollTop += speed; else window.scrollBy(0, speed);
          const latest = movingRef.current;
          const to = targetFor(current.lastY);
          if (latest && to !== latest.to) setMoving({ ...latest, to });
        }
        autoScroll.current = requestAnimationFrame(tick);
      };
      autoScroll.current = requestAnimationFrame(tick);
    }
  };

  const onPointerEnd = (event: ReactPointerEvent<HTMLButtonElement>, cancelled = false) => {
    const drag = dragRef.current;
    if (!drag || drag.pointerId !== event.pointerId) return;
    dragRef.current = null;
    stopAutoScroll();
    const state = movingRef.current;
    if (!drag.moved) { setMoving(null); return; }
    suppressClick.current = true;
    if (cancelled) cancel(); else commit(state);
  };

  /* ---------- rendering ---------- */

  const onScroll = () => {
    const viewport = viewportRef.current;
    if (!viewport) return;
    window.requestAnimationFrame(() => setScrollTop(viewport.scrollTop));
  };

  const range = useMemo(() => {
    if (!virtual || !rowHeight) return [0, total] as const;
    const first = Math.max(0, Math.floor(scrollTop / rowHeight) - OVERSCAN);
    const last = Math.min(total, Math.ceil((scrollTop + viewportHeight) / rowHeight) + OVERSCAN);
    return [first, last] as const;
  }, [virtual, rowHeight, scrollTop, viewportHeight, total]);

  if (!total) return emptyText ? <div className="st-sort-empty">{emptyText}</div> : null;

  const rows: ReactNode[] = [];
  for (let display = range[0]; display < range[1]; display++) {
    const sourceIndex = sourceIndexAt(display);
    const item = items[sourceIndex];
    if (item === undefined) continue;
    const key = getKey(item);
    const isMoving = moving?.key === key;
    const context: SortableRowContext = { index: display, total, moving: isMoving };
    const itemLabel = getLabel(item);
    const footer = renderFooter?.(item, context);
    rows.push(
      <li
        key={key}
        data-sort-key={key}
        className={`st-sort-row${footer ? " has-footer" : ""}${isMoving ? " is-moving" : ""}${moving?.mode === "pointer" && isMoving ? " is-dragging" : ""}${rowClassName ? ` ${rowClassName(item)}` : ""}`}
        style={virtual && rowHeight ? { position: "absolute", top: display * rowHeight, left: 0, right: 0, height: rowHeight } : undefined}
        aria-posinset={virtual ? display + 1 : undefined}
        aria-setsize={virtual ? total : undefined}
      >
        {!disabled && (
          <button
            type="button"
            className="st-sort-handle"
            aria-label={`${translateUi("Hold to move")}: ${itemLabel}`}
            title={translateUi("Drag, or press Enter and use the arrow keys")}
            aria-pressed={isMoving && moving?.mode === "keyboard"}
            aria-describedby={instructionsId}
            onKeyDown={(event) => onHandleKeyDown(event, sourceIndex, key)}
            onKeyUp={() => { window.setTimeout(() => { keyActivated.current = false; }, 0); }}
            onClick={() => {
              if (keyActivated.current) { keyActivated.current = false; return; }
              if (suppressClick.current) { suppressClick.current = false; return; }
              // A click without dragging toggles "hold to move" for remotes and mice.
              const state = movingRef.current;
              if (state?.key === key) commit(state);
              else {
                setMoving({ key, from: sourceIndex, to: sourceIndex, mode: "keyboard" });
                announce(translateUi("Picked up {value0}, {value1}. Use the arrow keys to move it, Enter to drop it, Escape to cancel.", { value0: itemLabel, value1: position(sourceIndex) }));
              }
            }}
            onBlur={() => {
              // Leaving the row drops it — unless focus only blinked while the row was re-inserted.
              window.requestAnimationFrame(() => {
                const state = movingRef.current;
                if (state?.key !== key || state.mode !== "keyboard") return;
                const handle = listRef.current?.querySelector(`[data-sort-key="${CSS.escape(key)}"] .st-sort-handle`);
                if (document.activeElement === handle) return;
                if (document.activeElement && document.activeElement !== document.body) commit(state, true);
              });
            }}
            onPointerDown={(event) => onPointerDown(event, sourceIndex, key)}
            onPointerMove={onPointerMove}
            onPointerUp={(event) => onPointerEnd(event)}
            onPointerCancel={(event) => onPointerEnd(event, true)}
          >
            <GripVertical size={18} aria-hidden="true" />
          </button>
        )}
        <div className="st-sort-main">{renderMain(item, context)}</div>
        <div className="st-sort-actions">
          {renderActions?.(item, context)}
          {!disabled && (
            <span className="st-sort-move">
              {edgeButtons && <button type="button" className="st-icon-btn st-sort-btn is-edge" disabled={display === 0 || Boolean(moving)} aria-label={translateUi("Move {value0} to the top", { value0: itemLabel })} title={translateUi("Move to top")} onClick={() => moveNow(sourceIndex, 0)}><ChevronsUp size={17} /></button>}
              <button type="button" className="st-icon-btn st-sort-btn" disabled={display === 0 || Boolean(moving)} aria-label={translateUi("Move {value0} up", { value0: itemLabel })} title={translateUi("Move up")} onClick={() => moveNow(sourceIndex, sourceIndex - 1)}><ArrowUp size={17} /></button>
              <button type="button" className="st-icon-btn st-sort-btn" disabled={display === total - 1 || Boolean(moving)} aria-label={translateUi("Move {value0} down", { value0: itemLabel })} title={translateUi("Move down")} onClick={() => moveNow(sourceIndex, sourceIndex + 1)}><ArrowDown size={17} /></button>
              {edgeButtons && <button type="button" className="st-icon-btn st-sort-btn is-edge" disabled={display === total - 1 || Boolean(moving)} aria-label={translateUi("Move {value0} to the bottom", { value0: itemLabel })} title={translateUi("Move to bottom")} onClick={() => moveNow(sourceIndex, total - 1)}><ChevronsDown size={17} /></button>}
            </span>
          )}
        </div>
        {footer && <div className="st-sort-footer">{footer}</div>}
      </li>
    );
  }

  const list = (
    <ul
      ref={listRef}
      className={`st-sort-list${virtual ? " is-virtual" : ""}`}
      aria-label={label}
      style={virtual && rowHeight ? { height: total * rowHeight, position: "relative" } : undefined}
    >
      {rows}
    </ul>
  );

  return (
    <div className={`st-sort${moving ? " is-sorting" : ""}${className ? ` ${className}` : ""}`}>
      <p id={instructionsId} className="st-sr-only">{translateUi("Press Space or Enter to pick up, arrow keys to move, Enter to drop, Escape to cancel.")}</p>
      {virtual
        ? <div ref={viewportRef} className="st-sort-viewport" style={{ height: Math.min(viewportHeight, total * (rowHeight ?? 56)) }} onScroll={onScroll}>{list}</div>
        : list}
      <p className="st-sr-only" role="status" aria-live="assertive" aria-atomic="true">{announcement}</p>
    </div>
  );
}
