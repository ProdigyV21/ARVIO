// Part of the player overlay (see components/player/PlayerOverlay.tsx).
import type { MediaItem, StreamSource } from "@/lib/types";
import { useTranslation } from "@/lib/i18n";
import { useRef, useEffect } from "react";
import { trapTab, claimEscape } from "@/lib/playerKeys";
import { YouTubeEmbed } from "../YouTubeEmbed";
import { ArrowLeft, ExternalLink, X } from "lucide-react";

/** In-app YouTube (trailers and the YouTube collection), as a modal layer. */
export function YouTubeLayer({ videoId, item, stream, title, onClose }: { videoId: string; item: MediaItem | null; stream: StreamSource; title: string; onClose: () => void }) {
  const translateUi = useTranslation();
  const ref = useRef<HTMLElement | null>(null);
  const openerRef = useRef<HTMLElement | null>(null);
  useEffect(() => {
    const node = ref.current;
    const active = document.activeElement;
    if (active instanceof HTMLElement && !node?.contains(active)) openerRef.current = active;
    node?.querySelector<HTMLElement>(".player-icon-btn")?.focus({ preventScroll: true });
    const onKey = (event: KeyboardEvent) => {
      if (event.key === "Tab" && ref.current) { trapTab(event, ref.current); return; }
      if (event.key !== "Escape" || !claimEscape(event)) return;
      event.preventDefault();
      onClose();
    };
    window.addEventListener("keydown", onKey);
    return () => {
      window.removeEventListener("keydown", onKey);
      // Only on a real close (React dev re-runs effects without unmounting).
      window.setTimeout(() => {
        const opener = openerRef.current;
        if (node && !node.isConnected && opener?.isConnected) opener.focus({ preventScroll: true });
      }, 0);
    };
  }, [onClose]);
  const kind = stream.addonId === "arvio_youtube" ? "YouTube" : translateUi("Trailer");
  return (
    <section className="player-overlay youtube-player-layout" role="dialog" aria-modal="true" aria-label={`${kind}: ${title}`} ref={ref}>
      <YouTubeEmbed videoId={videoId} item={item} stream={stream} />
      <div className="youtube-player-toolbar">
        <div className="player-top-left">
          <button type="button" className="player-icon-btn" onClick={onClose} aria-label={translateUi("Back")}><ArrowLeft size={24} /></button>
          <div>
            <p className="eyebrow">{kind}</p>
            <h2>{title}</h2>
          </div>
        </div>
        <a className="player-icon-btn" href={`https://www.youtube.com/watch?v=${videoId}`} target="_blank" rel="noopener noreferrer" aria-label={translateUi("Open in YouTube")} title={translateUi("Open in YouTube")}><ExternalLink size={24} /></a>
        <button type="button" className="player-icon-btn" onClick={onClose} aria-label={translateUi("Close")}><X size={24} /></button>
      </div>
    </section>
  );
}
