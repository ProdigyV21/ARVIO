"use client";
import { useTranslation } from "@/lib/i18n";
import { ArrowDown, ArrowUp, EyeOff, Play, Star, X } from "lucide-react";
import { useEffect, useRef } from "react";
import type { IptvChannel } from "@/lib/types";

export type ChannelMenuState = { channel: IptvChannel; anchor: HTMLElement | null };

/**
 * Channel context menu (long-press / right-click / menu key), like Android's
 * ChannelContextMenu: favourite, reorder favourites, hide the channel's group.
 */
export function ChannelMenu({ state, groupName, favorite, canMoveUp, canMoveDown, onPlay, onToggleFavorite, onMove, onHideGroup, onClose }: {
  state: ChannelMenuState;
  /** The channel's category as shown on the Live TV page (the user's own name when set). */
  groupName?: string;
  favorite: boolean;
  canMoveUp: boolean;
  canMoveDown: boolean;
  onPlay: () => void;
  onToggleFavorite: () => void;
  onMove: (direction: -1 | 1) => void;
  onHideGroup: () => void;
  onClose: () => void;
}) {
  const translateUi = useTranslation();
  const dialog = useRef<HTMLDialogElement>(null);
  useEffect(() => {
    const element = dialog.current;
    if (!element) return undefined;
    if (!element.open) element.showModal();
    element.querySelector<HTMLButtonElement>("button:not(:disabled)")?.focus();
    const anchor = state.anchor;
    return () => { if (anchor?.isConnected) anchor.focus({ preventScroll: true }); };
  }, [state]);
  const run = (action: () => void) => () => { action(); onClose(); };
  const group = groupName || state.channel.group?.trim() || translateUi("Ungrouped");
  return (
    <dialog ref={dialog} className="livetv-channel-menu" aria-label={translateUi("Channel options for {value0}", { value0: state.channel.name })}
      onCancel={(event) => { event.preventDefault(); onClose(); }}
      onClick={(event) => { if (event.target === event.currentTarget) onClose(); }}>
      <header><strong>{state.channel.name}</strong><button type="button" onClick={onClose} aria-label={translateUi("Close")}><X size={18} /></button></header>
      <div role="menu" aria-label={translateUi("Channel options")}>
        <button type="button" role="menuitem" onClick={run(onPlay)}><Play size={17} fill="currentColor" />{translateUi("Watch")}</button>
        <button type="button" role="menuitem" onClick={run(onToggleFavorite)}><Star size={17} fill={favorite ? "currentColor" : "none"} />{favorite ? translateUi("Remove favorite") : translateUi("Add favorite")}</button>
        {favorite && <button type="button" role="menuitem" disabled={!canMoveUp} onClick={run(() => onMove(-1))}><ArrowUp size={17} />{translateUi("Move favorite up")}</button>}
        {favorite && <button type="button" role="menuitem" disabled={!canMoveDown} onClick={run(() => onMove(1))}><ArrowDown size={17} />{translateUi("Move favorite down")}</button>}
        <button type="button" role="menuitem" onClick={run(onHideGroup)}><EyeOff size={17} />{translateUi("Hide category {value0}", { value0: group })}</button>
      </div>
    </dialog>
  );
}
