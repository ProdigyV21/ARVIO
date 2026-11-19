"use client";
import { useDirection, useTranslation } from "@/lib/i18n";
import { ChevronLeft, ChevronRight, Play, Star } from "lucide-react";
import { memo, useCallback, useEffect, useLayoutEffect, useMemo, useRef, useState, type KeyboardEvent as ReactKeyboardEvent, type PointerEvent as ReactPointerEvent } from "react";
import { VirtualList } from "@/components/ui/VirtualList";
import { ChannelLogo } from "@/components/livetv/ChannelLogo";
import { focusTimeForSlot, formatGuideDay, formatGuideTime, guidePrograms, slotAtFocusTime, type ClockFormat } from "@/lib/iptvGuide";
import type { IptvChannel, IptvNowNext, IptvProgram, IptvSnapshot } from "@/lib/types";

export const GUIDE_WINDOW_HOURS = 4;
export const GUIDE_PX_PER_MIN = 6;
const HALF_HOUR = 1_800_000;
const rowKey = (item: { id: string }) => item.id;

export type GuideCallbacks = {
  onFocusChannel: (channel: IptvChannel) => void;
  onVisible: (channel: IptvChannel) => void;
  onPlay: (channel: IptvChannel) => void;
  onCatchup: (channel: IptvChannel, program: IptvProgram) => void;
  onToggleFavorite: (channel: IptvChannel) => void;
  onMenu: (channel: IptvChannel, anchor: HTMLElement) => void;
  /** The category name to show for a channel (the user's own name when set). */
  groupName?: (channel: IptvChannel) => string;
};

/**
 * Long-press (touch/mouse hold or a held remote OK) opens the channel menu,
 * a short press plays — Android's channel row behaviour.
 */
function usePressHandlers(channel: IptvChannel, callbacks: Pick<GuideCallbacks, "onPlay" | "onMenu">) {
  const timer = useRef<number | null>(null);
  const opened = useRef(false);
  const keyDownAt = useRef(0);
  const clear = () => { if (timer.current) window.clearTimeout(timer.current); timer.current = null; };
  useEffect(() => clear, []);
  return {
    onClick: () => {
      if (opened.current) { opened.current = false; return; }
      callbacks.onPlay(channel);
    },
    onContextMenu: (event: React.MouseEvent<HTMLElement>) => { event.preventDefault(); clear(); opened.current = false; callbacks.onMenu(channel, event.currentTarget); },
    onPointerDown: (event: ReactPointerEvent<HTMLElement>) => {
      opened.current = false;
      keyDownAt.current = 0;
      if (event.pointerType !== "touch") return;
      const target = event.currentTarget;
      clear();
      timer.current = window.setTimeout(() => { opened.current = true; callbacks.onMenu(channel, target); }, 550);
    },
    onPointerUp: clear, onPointerCancel: clear, onPointerLeave: clear,
    onKeyDown: (event: ReactKeyboardEvent<HTMLElement>) => {
      if (event.key === "ContextMenu") { event.preventDefault(); callbacks.onMenu(channel, event.currentTarget); return; }
      if (event.key !== "Enter") return;
      // Enter plays on key up, so holding OK can open the menu instead.
      event.preventDefault();
      if (!event.repeat) { keyDownAt.current = Date.now(); opened.current = false; return; }
      if (!opened.current && Date.now() - keyDownAt.current > 450) { opened.current = true; callbacks.onMenu(channel, event.currentTarget); }
    },
    onKeyUp: (event: ReactKeyboardEvent<HTMLElement>) => {
      if (event.key !== "Enter") return;
      event.preventDefault();
      const wasMenu = opened.current;
      opened.current = false;
      if (!wasMenu && keyDownAt.current) callbacks.onPlay(channel);
      keyDownAt.current = 0;
    }
  };
}

function useVisibility(ref: React.RefObject<HTMLElement | null>, id: string, onVisible: () => void, margin: string) {
  useEffect(() => {
    const el = ref.current;
    if (!el || typeof IntersectionObserver === "undefined") { onVisible(); return undefined; }
    const observer = new IntersectionObserver((entries) => {
      if (entries.some((entry) => entry.isIntersecting)) { onVisible(); observer.disconnect(); }
    }, { rootMargin: margin });
    observer.observe(el);
    return () => observer.disconnect();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id]);
}

// Timeline guide: channels down, time across (window of four hours), programme
// blocks positioned by their real start/end times. EPG loads lazily per row.
export function GuideGrid({ channels, favorites, nowNext, settled, selectedId, playingId, clockFormat, resetKey, callbacks }: {
  channels: IptvChannel[];
  favorites: Set<string>;
  nowNext: IptvSnapshot["nowNext"];
  /** Channels whose guide request finished (an absent entry then means "no data"). */
  settled: Set<string>;
  selectedId: string | null;
  playingId: string | null;
  clockFormat?: ClockFormat;
  /** Changes when the list is filtered: scroll back to the top, keep the time window. */
  resetKey: string;
  callbacks: GuideCallbacks;
}) {
  const translateUi = useTranslation();
  // Right-to-left mirrors the time axis like the Android guide: programmes are
  // placed from the right edge and the later-in-time key is ArrowLeft.
  const rtl = useDirection() === "rtl";
  const [clock, setClock] = useState(0);
  const [manualStart, setManualStart] = useState<number | null>(null);
  const currentStart = Math.floor(clock / HALF_HOUR) * HALF_HOUR;
  const windowStart = manualStart ?? currentStart;
  const windowEnd = windowStart + GUIDE_WINDOW_HOURS * 3_600_000;
  const minStart = currentStart - 48 * 3_600_000;
  const maxStart = currentStart + 44 * 3_600_000;
  const totalWidth = GUIDE_WINDOW_HOURS * 60 * GUIDE_PX_PER_MIN;
  const root = useRef<HTMLDivElement>(null);
  // The time a D-pad user is "at": Up/Down keep it, Left/Right move it.
  const focusTime = useRef<number | null>(null);
  const verticalMove = useRef(false);
  const pendingFocus = useRef<{ channelId: string; direction: "left" | "right"; start: number; end: number } | null>(null);

  useEffect(() => {
    setClock(Date.now());
    const timer = window.setInterval(() => setClock(Date.now()), 60_000);
    return () => window.clearInterval(timer);
  }, []);
  useEffect(() => {
    root.current?.querySelector<HTMLElement>(".tv-guide-grid")?.scrollTo({ top: 0 });
  }, [resetKey]);

  const shiftWindow = useCallback((next: number) => setManualStart(Math.max(minStart, Math.min(maxStart, next))), [minStart, maxStart]);

  // After the window moved under a focused programme, land on the next one.
  useLayoutEffect(() => {
    const pending = pendingFocus.current;
    if (!pending) return;
    pendingFocus.current = null;
    const row = root.current?.querySelector<HTMLElement>(`[data-channel-id="${CSS.escape(pending.channelId)}"]`);
    if (!row) return;
    const blocks = Array.from(row.querySelectorAll<HTMLElement>(".livetv-guide-block"));
    const start = (el: HTMLElement) => Number(el.dataset.start);
    const end = (el: HTMLElement) => Number(el.dataset.end);
    const target = pending.direction === "right"
      ? blocks.find((el) => start(el) >= pending.end) ?? blocks.find((el) => start(el) === pending.start)
      : [...blocks].reverse().find((el) => end(el) <= pending.start) ?? blocks.find((el) => start(el) === pending.start);
    if (target) target.focus({ preventScroll: true });
    else row.querySelector<HTMLElement>(".livetv-guide-channel")?.focus({ preventScroll: true });
  }, [windowStart]);

  const onKeyDown = (event: ReactKeyboardEvent<HTMLDivElement>) => {
    if (event.key !== "ArrowLeft" && event.key !== "ArrowRight") return;
    const target = event.target as HTMLElement;
    const row = target.closest<HTMLElement>("[data-channel-id]");
    if (!row) return;
    const cells = Array.from(row.querySelectorAll<HTMLElement>(".livetv-guide-channel, .livetv-guide-block"));
    const index = cells.indexOf(target);
    if (index < 0) return;
    // "right" = later in time, whichever way the axis runs.
    const right = event.key === (rtl ? "ArrowLeft" : "ArrowRight");
    const next = cells[index + (right ? 1 : -1)];
    const isBlock = target.classList.contains("livetv-guide-block");
    const blockStart = Number(target.dataset.start ?? windowStart);
    const blockEnd = Number(target.dataset.end ?? windowStart);
    if (right && !next) {
      // At the end of the window: move the window on, never out of the guide.
      event.preventDefault(); event.stopPropagation();
      if (!isBlock || windowStart >= maxStart) return;
      pendingFocus.current = { channelId: row.dataset.channelId!, direction: "right", start: blockStart, end: blockEnd };
      shiftWindow(windowStart + HALF_HOUR);
      return;
    }
    if (!right && isBlock && index === 1 && blockStart <= windowStart && manualStart !== null && windowStart > currentStart) {
      // First programme of a window the user moved forward: step back in time.
      event.preventDefault(); event.stopPropagation();
      pendingFocus.current = { channelId: row.dataset.channelId!, direction: "left", start: blockStart, end: blockEnd };
      shiftWindow(windowStart - HALF_HOUR);
      return;
    }
    if (!next) return; // Left from the channel: Live TV opens the categories.
    event.preventDefault(); event.stopPropagation();
    next.focus({ preventScroll: false });
  };

  const onFocusCapture = (event: React.FocusEvent<HTMLDivElement>) => {
    const target = event.target as HTMLElement;
    if (!target.classList.contains("livetv-guide-block")) return;
    if (verticalMove.current) { verticalMove.current = false; return; }
    focusTime.current = focusTimeForSlot({ start: Number(target.dataset.start), end: Number(target.dataset.end) }, windowStart);
  };

  const focusTarget = useCallback((row: HTMLElement, from: HTMLElement) => {
    if (!from.classList.contains("livetv-guide-block")) return row.querySelector<HTMLElement>(".livetv-guide-channel");
    const blocks = Array.from(row.querySelectorAll<HTMLElement>(".livetv-guide-block"));
    const time = focusTime.current ?? Number(from.dataset.start);
    const index = slotAtFocusTime(blocks.map((el) => ({ start: Number(el.dataset.start), end: Number(el.dataset.end) })), time);
    verticalMove.current = index >= 0;
    return index >= 0 ? blocks[index] : row.querySelector<HTMLElement>(".livetv-guide-channel");
  }, []);

  const nowOffset = ((clock - windowStart) / 60000) * GUIDE_PX_PER_MIN;
  const ticks = useMemo(() => Array.from({ length: GUIDE_WINDOW_HOURS * 2 }, (_, index) => windowStart + index * HALF_HOUR), [windowStart]);
  const renderRow = useCallback((channel: IptvChannel) => (
    <GuideRow
      channel={channel}
      favorite={favorites.has(channel.id)}
      guide={nowNext[channel.id]}
      settled={settled.has(channel.id)}
      selected={selectedId === channel.id}
      playing={playingId === channel.id}
      windowStart={windowStart}
      windowEnd={windowEnd}
      totalWidth={totalWidth}
      nowOffset={nowOffset}
      clockFormat={clockFormat}
      callbacks={callbacks}
    />
  ), [favorites, nowNext, settled, selectedId, playingId, windowStart, windowEnd, totalWidth, nowOffset, clockFormat, callbacks]);

  if (!clock) return <div className="livetv-guide" aria-busy="true" aria-label={translateUi("Programme guide")} />;
  const showNow = nowOffset >= 0 && nowOffset <= totalWidth;
  return (
    <div ref={root} className="livetv-guide" aria-label={translateUi("Programme guide")} onKeyDown={onKeyDown} onFocusCapture={onFocusCapture}>
      <div className="guide-window-controls" role="group" aria-label={translateUi("Guide time window")}>
        <button type="button" title={translateUi("Previous four hours")} aria-label={translateUi("Previous four hours")} disabled={windowStart <= minStart} onClick={() => shiftWindow(windowStart - 4 * 3_600_000)}><ChevronLeft size={18} /></button>
        <button type="button" className="guide-now" onClick={() => { setClock(Date.now()); setManualStart(null); focusTime.current = null; }}>{translateUi("Now")}</button>
        <button type="button" title={translateUi("Next four hours")} aria-label={translateUi("Next four hours")} disabled={windowStart >= maxStart} onClick={() => shiftWindow(windowStart + 4 * 3_600_000)}><ChevronRight size={18} /></button>
        <span aria-live="polite">{formatGuideDay(windowStart)} · {formatGuideTime(windowStart, clockFormat)}–{formatGuideTime(windowEnd, clockFormat)}</span>
      </div>
      <VirtualList items={channels} itemKey={rowKey} label={translateUi("Guide channels")} className="tv-guide-grid" contentWidth={`calc(${totalWidth}px + var(--guide-channel-width))`} focusTarget={focusTarget} estimate={62} header={<div className="livetv-guide-timebar" aria-hidden="true">
        <span className="livetv-guide-corner" />
        <div className="livetv-guide-ticks" style={{ width: `${totalWidth}px` }}>
          {ticks.map((tick) => {
            const offset = ((tick - windowStart) / 60000) * GUIDE_PX_PER_MIN;
            // The "now" pill sits on the time bar: hide the one label it would cover.
            const covered = showNow && nowOffset > offset - 34 && nowOffset < offset + 96;
            return <span key={tick} style={{ width: `${30 * GUIDE_PX_PER_MIN}px` }}><b className={covered ? "is-covered" : undefined}>{formatGuideTime(tick, clockFormat)}</b></span>;
          })}
          {showNow && <i className="livetv-guide-nowline" style={{ [rtl ? "right" : "left"]: `${nowOffset}px` }}><b>{formatGuideTime(clock, clockFormat)}</b></i>}
        </div>
      </div>} renderItem={renderRow} />
    </div>
  );
}

const GuideRow = memo(function GuideRow({ channel, favorite, guide, settled, selected, playing, windowStart, windowEnd, totalWidth, nowOffset, clockFormat, callbacks }: {
  channel: IptvChannel;
  favorite: boolean;
  guide?: IptvNowNext;
  settled: boolean;
  selected: boolean;
  playing: boolean;
  windowStart: number;
  windowEnd: number;
  totalWidth: number;
  nowOffset: number;
  clockFormat?: ClockFormat;
  callbacks: GuideCallbacks;
}) {
  const translateUi = useTranslation();
  const side = useDirection() === "rtl" ? "right" : "left";
  const rowRef = useRef<HTMLDivElement | null>(null);
  useVisibility(rowRef, channel.id, () => callbacks.onVisible(channel), "300px");
  const press = usePressHandlers(channel, callbacks);
  const programs = useMemo(() => guidePrograms(guide)
    .filter((program) => program.endUtcMillis > windowStart && program.startUtcMillis < windowEnd), [guide, windowStart, windowEnd]);
  const now = Date.now();
  const time = (ms: number) => formatGuideTime(ms, clockFormat);

  return (
    // Focus inside the row (D-pad) keeps the details pane in sync. Hover does
    // not: re-rendering the screen per mouse move cost a frame at 55k channels.
    <div ref={rowRef} data-channel-id={channel.id} className={`livetv-guide-row ${selected ? "is-selected" : ""} ${playing ? "is-playing" : ""}`} onFocus={() => callbacks.onFocusChannel(channel)}>
      <div className="livetv-guide-channel-cell">
        <button type="button" className="livetv-guide-channel" title={channel.name} aria-label={[channel.number, channel.name, favorite ? translateUi("Favorite") : "", playing ? translateUi("Playing") : ""].filter(Boolean).join(", ")} {...press}>
          <small className="tv-guide-channel-number">{channel.number}</small>
          <span className="livetv-row-logo"><ChannelLogo channel={channel} size={16} /></span>
          <strong>{channel.name}</strong>
          {playing && <Play size={13} fill="currentColor" className="livetv-guide-playing" aria-hidden="true" />}
        </button>
        {/* Pointer shortcut; remote users use the long-press menu, so it is not a focus stop. */}
        <button type="button" tabIndex={-1} className={`livetv-guide-star ${favorite ? "is-active" : ""}`} onClick={() => callbacks.onToggleFavorite(channel)}
          aria-label={favorite ? translateUi("Remove favorite") : translateUi("Add favorite")} title={favorite ? translateUi("Remove favorite") : translateUi("Add favorite")}>
          <Star size={15} fill={favorite ? "currentColor" : "none"} />
        </button>
      </div>
      <div className="livetv-guide-lane" style={{ width: `${totalWidth}px` }}>
        {programs.map((program) => {
          const left = Math.max(0, ((program.startUtcMillis - windowStart) / 60000) * GUIDE_PX_PER_MIN);
          const right = Math.min(totalWidth, ((program.endUtcMillis - windowStart) / 60000) * GUIDE_PX_PER_MIN);
          const live = now >= program.startUtcMillis && now < program.endUtcMillis;
          const archived = program.endUtcMillis <= now && Boolean(channel.catchupDays && now - program.startUtcMillis <= channel.catchupDays * 86_400_000);
          const status = live ? translateUi("Live") : archived ? translateUi("Catch-up") : program.startUtcMillis > now ? translateUi("Upcoming") : translateUi("Archive unavailable");
          const span = `${time(program.startUtcMillis)}–${time(program.endUtcMillis)}`;
          return (
            <button
              type="button"
              key={program.startUtcMillis}
              data-start={program.startUtcMillis}
              data-end={program.endUtcMillis}
              className={`livetv-guide-block ${live ? "is-live" : ""}`}
              style={{ [side]: `${left}px`, width: `${Math.max(14, right - left - 2)}px` }}
              aria-disabled={!live && !archived}
              aria-label={`${program.title}, ${span}, ${status}`}
              onClick={() => { if (live) callbacks.onPlay(channel); else if (archived) callbacks.onCatchup(channel, program); }}
              title={`${program.title} · ${span} · ${status}`}
            >
              <strong>{program.title}</strong><small>{span}</small>
            </button>
          );
        })}
        {programs.length === 0 && (settled || guide
          ? <span className="livetv-guide-empty">{translateUi("No guide data")}</span>
          : <span className="livetv-guide-skeleton" aria-hidden="true"><i /><i /><i /></span>)}
        {nowOffset >= 0 && nowOffset <= totalWidth && <i className="livetv-guide-nowline" style={{ [side]: `${nowOffset}px` }} />}
      </div>
    </div>
  );
});

export const ChannelRow = memo(function ChannelRow({ channel, guide, favorite, selected, playing, clockFormat, callbacks }: {
  channel: IptvChannel;
  guide?: IptvNowNext;
  favorite: boolean;
  selected: boolean;
  playing: boolean;
  clockFormat?: ClockFormat;
  callbacks: GuideCallbacks;
}) {
  const translateUi = useTranslation();
  const rowRef = useRef<HTMLElement | null>(null);
  useVisibility(rowRef, channel.id, () => callbacks.onVisible(channel), "240px");
  const press = usePressHandlers(channel, callbacks);
  const now = guide?.now;
  const next = guide?.next ?? guide?.later ?? guide?.upcoming?.[0];
  const progress = now ? Math.min(100, Math.max(0, ((Date.now() - now.startUtcMillis) / (now.endUtcMillis - now.startUtcMillis)) * 100)) : 0;

  return (
    <article ref={rowRef} data-channel-id={channel.id} className={`livetv-row ${selected ? "is-selected" : ""} ${playing ? "is-playing" : ""}`} onFocus={() => callbacks.onFocusChannel(channel)}>
      <button type="button" className="livetv-row-main" {...press}>
        <span className="livetv-row-logo"><ChannelLogo channel={channel} size={20} /></span>
        <span className="livetv-row-copy">
          <span className="livetv-row-title">
            {channel.number && <small className="tv-guide-channel-number">{channel.number}</small>}
            <strong>{channel.name}</strong>
            {channel.qualityLabel && <i className="livetv-quality">{channel.qualityLabel}</i>}
          </span>
          {now?.title ? (
            <span className="livetv-row-now">
              <em>{now.title}</em>
              <span className="livetv-progress"><span style={{ width: `${progress}%` }} /></span>
            </span>
          ) : (
            <span className="livetv-row-now"><em className="is-muted">{callbacks.groupName?.(channel) || channel.group || translateUi("Live TV")}</em></span>
          )}
          {next?.title && <small>{formatGuideTime(next.startUtcMillis, clockFormat)} · {next.title}</small>}
        </span>
        <span className="livetv-row-play"><Play size={16} fill="currentColor" /></span>
      </button>
      <button type="button" className={`livetv-row-star ${favorite ? "is-active" : ""}`} onClick={() => callbacks.onToggleFavorite(channel)} aria-label={favorite ? translateUi("Remove favorite") : translateUi("Add favorite")}>
        <Star size={17} fill={favorite ? "currentColor" : "none"} />
      </button>
    </article>
  );
});
