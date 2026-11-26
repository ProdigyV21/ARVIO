"use client";
import { useTranslation } from "@/lib/i18n";
import { Bell, BellRing, Play, X } from "lucide-react";
import { useEffect, useRef } from "react";
import { formatGuideDay, formatGuideTime, type ClockFormat } from "@/lib/iptvGuide";
import type { IptvChannel, IptvProgram } from "@/lib/types";

export type ProgrammeInfoState = { channel: IptvChannel; program: IptvProgram; anchor: HTMLElement | null };

/**
 * A show that can't be played right now (later today, or aired without
 * catch-up): its details, Remind me and Watch channel. Same look as the
 * channel menu; Android shows the same choices in ProgrammeInfoDialog.
 */
export function ProgrammeInfo({ state, reminded, clockFormat, onToggleReminder, onWatch, onClose }: {
  state: ProgrammeInfoState;
  reminded: boolean;
  clockFormat?: ClockFormat;
  onToggleReminder: () => void;
  onWatch: () => void;
  onClose: () => void;
}) {
  const translateUi = useTranslation();
  const dialog = useRef<HTMLDialogElement>(null);
  useEffect(() => {
    const element = dialog.current;
    if (!element) return undefined;
    if (!element.open) element.showModal();
    element.querySelector<HTMLButtonElement>("[role=menu] button:not(:disabled)")?.focus();
    const anchor = state.anchor;
    return () => { if (anchor?.isConnected) anchor.focus({ preventScroll: true }); };
  }, [state]);
  const { channel, program } = state;
  const upcoming = program.startUtcMillis > Date.now();
  const when = `${formatGuideDay(program.startUtcMillis, false, clockFormat)} · ${formatGuideTime(program.startUtcMillis, clockFormat)}–${formatGuideTime(program.endUtcMillis, clockFormat)}`;
  const run = (action: () => void) => () => { action(); onClose(); };
  return (
    <dialog ref={dialog} className="livetv-channel-menu livetv-programme-info" aria-label={program.title}
      onCancel={(event) => { event.preventDefault(); onClose(); }}
      onClick={(event) => { if (event.target === event.currentTarget) onClose(); }}>
      <header><strong>{program.title}</strong><button type="button" onClick={onClose} aria-label={translateUi("Close")}><X size={18} /></button></header>
      <p className="livetv-programme-meta">{channel.name} · {when}</p>
      {program.category && <p className="livetv-programme-meta">{program.category}</p>}
      {program.description && <p className="livetv-programme-description">{program.description}</p>}
      <div role="menu" aria-label={program.title}>
        {upcoming && <button type="button" role="menuitem" aria-pressed={reminded} onClick={run(onToggleReminder)}>
          {reminded ? <BellRing size={17} /> : <Bell size={17} />}{reminded ? translateUi("Reminder on") : translateUi("Remind me")}
        </button>}
        <button type="button" role="menuitem" onClick={run(onWatch)}><Play size={17} fill="currentColor" />{translateUi("Watch")}</button>
      </div>
    </dialog>
  );
}
