"use client";
import { useTranslation } from "@/lib/i18n";
import { Check, Clock, Minus, Plus, Search, X } from "lucide-react";
import { useDeferredValue, useEffect, useMemo, useRef, useState } from "react";
import {
  GUIDE_OFFSET_STEP_MINUTES, GUIDE_SOURCE_NONE, MAX_GUIDE_OFFSET_MINUTES, formatGuideOffset, guideChannelOf,
  guidePlaylistIdOf, prepareGuideCandidates, rankGuideCandidates, type GuideFixes
} from "@/lib/iptvGuideFixes";
import type { IptvChannel, IptvNowNext } from "@/lib/types";

/**
 * "Fix guide" for one channel, like Android's GuideFixDialog: show another
 * channel's guide (or none), and move a playlist's guide times when its
 * shows start earlier or later than listed. Both sync to every ARVIO app.
 */
export function GuideFixDialog({ channel, channels, guide, fixes, sourceNames, onPickSource, onOffsetChange, onClose }: {
  channel: IptvChannel;
  channels: IptvChannel[];
  /** The guide as shown (fixes applied), for the "on now" hints. */
  guide: Record<string, IptvNowNext>;
  fixes: GuideFixes;
  sourceNames: Record<string, string>;
  onPickSource: (sourceId: string | null) => void;
  onOffsetChange: (playlistId: string, minutes: number) => void;
  onClose: () => void;
}) {
  const translateUi = useTranslation();
  const dialog = useRef<HTMLDialogElement>(null);
  const [query, setQuery] = useState("");
  const deferredQuery = useDeferredValue(query);
  useEffect(() => {
    const element = dialog.current;
    if (!element) return;
    if (!element.open) element.showModal();
    element.querySelector<HTMLElement>(".livetv-guidefix-offset button:not(:disabled), [role=listbox] [role=option]")?.focus();
  }, []);
  // Names are split once per opening; typing then only filters.
  const prepared = useMemo(() => prepareGuideCandidates(channels.filter((item) => item.id !== channel.id), (item) => item.name),
    [channels, channel.id]);
  const ranked = useMemo(() => rankGuideCandidates(channel.name, deferredQuery, prepared, (item) => Boolean(guide[item.id]?.now)),
    [channel.name, deferredQuery, prepared, guide]);
  const current = fixes.sources[channel.id];
  const currentChannel = current && current !== GUIDE_SOURCE_NONE ? channels.find((item) => item.id === current) : undefined;
  const shown = currentChannel && !ranked.some((item) => item.id === currentChannel.id) ? [currentChannel, ...ranked] : ranked;
  const guideId = guideChannelOf(fixes, channel.id);
  const offsetPlaylist = guideId ? guidePlaylistIdOf(guideId) : "";
  const offsetName = offsetPlaylist ? sourceNames[offsetPlaylist] : undefined;
  const offset = fixes.offsets[offsetPlaylist] ?? 0;
  const step = (delta: number) => onOffsetChange(offsetPlaylist, Math.max(-MAX_GUIDE_OFFSET_MINUTES, Math.min(MAX_GUIDE_OFFSET_MINUTES, offset + delta)));

  const option = (key: string, title: string, subtitle: string | undefined, selected: boolean, pick: () => void) => (
    <button key={key} type="button" role="option" aria-selected={selected} className="livetv-guidefix-option" onClick={pick}>
      <span className="livetv-guidefix-check" aria-hidden="true">{selected && <Check size={16} />}</span>
      <span className="livetv-guidefix-text"><strong>{title}</strong>{subtitle && <small>{subtitle}</small>}</span>
    </button>
  );

  return (
    <dialog ref={dialog} className="livetv-channel-menu livetv-guidefix" aria-label={translateUi("Fix guide")}
      onCancel={(event) => { event.preventDefault(); onClose(); }}
      onClick={(event) => { if (event.target === event.currentTarget) onClose(); }}>
      <header>
        <div className="livetv-guidefix-title"><strong>{translateUi("Fix guide")}</strong><small>{channel.name}</small></div>
        <button type="button" onClick={onClose} aria-label={translateUi("Close")}><X size={18} /></button>
      </header>
      {offsetName && (
        <section className="livetv-guidefix-offset" aria-label={translateUi("Guide times")}>
          <Clock size={18} aria-hidden="true" />
          <div className="livetv-guidefix-text">
            <strong>{translateUi("Guide times")}</strong>
            <small>{translateUi("Moves every programme of {value0}. Use it when shows start earlier or later than listed.", { value0: offsetName })}</small>
          </div>
          <button type="button" aria-label={translateUi("Earlier")} disabled={offset <= -MAX_GUIDE_OFFSET_MINUTES} onClick={() => step(-GUIDE_OFFSET_STEP_MINUTES)}><Minus size={16} /></button>
          <output aria-live="polite" className={offset ? "" : "is-zero"}>{formatGuideOffset(offset)}</output>
          <button type="button" aria-label={translateUi("Later")} disabled={offset >= MAX_GUIDE_OFFSET_MINUTES} onClick={() => step(GUIDE_OFFSET_STEP_MINUTES)}><Plus size={16} /></button>
        </section>
      )}
      <p className="livetv-guidefix-heading">{translateUi("Use the guide of")}</p>
      <label className="livetv-guidefix-search">
        <Search size={16} aria-hidden="true" />
        <input type="search" value={query} placeholder={translateUi("Search for a channel")} aria-label={translateUi("Search for a channel")}
          onChange={(event) => setQuery(event.target.value)} />
      </label>
      <div role="listbox" aria-label={translateUi("Use the guide of")} className="livetv-guidefix-list">
        {option("auto", translateUi("Automatic"), translateUi("The guide your provider matched"), !current, () => onPickSource(null))}
        {option("none", translateUi("No guide"), translateUi("Hide a guide that is wrong"), current === GUIDE_SOURCE_NONE, () => onPickSource(GUIDE_SOURCE_NONE))}
        {shown.map((item) => {
          const now = guide[item.id]?.now?.title;
          const subtitle = [now ? `▶ ${now}` : "", sourceNames[guidePlaylistIdOf(item.id)] ?? ""].filter(Boolean).join("  ·  ");
          return option(item.id, item.name, subtitle || undefined, item.id === current, () => onPickSource(item.id));
        })}
        {!shown.length && <p className="livetv-guidefix-empty">{deferredQuery.trim() ? translateUi("No channels found") : translateUi("No similar channels. Search for one by name.")}</p>}
      </div>
    </dialog>
  );
}
