"use client";
import "@/app/collections.css";

import { useEffect, useRef, useState } from "react";
import { createPortal } from "react-dom";
import { ArrowLeft, LoaderCircle, RotateCw } from "lucide-react";
import { useTranslation } from "@/lib/i18n";
import { useAppPick } from "@/lib/store";
import { useKidsFilter } from "@/lib/useKids";
import type { CatalogConfig, Category, MediaItem } from "@/lib/types";
import { MediaCard } from "./MediaCard";
import { isRankedRow, railTitle } from "./MediaRail";
import { useModalDialog } from "./useModalDialog";

/**
 * A Home row's full list as a grid (Android CategoryViewAllScreen). Home rows
 * stop at 20 titles; this loads the whole source once it is opened.
 */
export function CategoryViewAll({ catalog, preview, onBack, onOpen }: {
  /** The row's catalog; null for presentation rows that are already complete (Watchlist). */
  catalog: CatalogConfig | null;
  preview: Category;
  onBack: () => void;
  onOpen: (item: MediaItem) => void;
}) {
  const translateUi = useTranslation();
  const { loadCatalogRow, selected, settings } = useAppPick("loadCatalogRow", "selected", "settings");
  const kids = useKidsFilter();
  const dialog = useRef<HTMLDialogElement>(null);
  const { onKeyDown, onCancel } = useModalDialog(dialog, { open: !selected, onClose: onBack });
  const [items, setItems] = useState<MediaItem[]>(preview.items);
  const [status, setStatus] = useState<"loading" | "done" | "error">(catalog ? "loading" : "done");
  const [attempt, setAttempt] = useState(0);

  useEffect(() => {
    if (selected) return undefined;
    const previous = document.body.style.overflow;
    document.body.style.overflow = "hidden";
    return () => { document.body.style.overflow = previous; };
  }, [selected]);

  useEffect(() => {
    if (!catalog) return undefined;
    let active = true;
    setStatus("loading");
    void loadCatalogRow(catalog).then((row) => {
      if (!active) return;
      if (row?.items.length) setItems(row.items);
      setStatus("done");
    }).catch(() => { if (active) setStatus("error"); });
    return () => { active = false; };
  }, [catalog, loadCatalogRow, attempt]);

  const title = railTitle(preview, translateUi);
  const posterMode = preview.layout ? preview.layout === "poster" : settings.cardLayoutMode === "poster";
  const ranked = isRankedRow(preview.title);
  const visible = kids.filter(items);
  return createPortal(<dialog ref={dialog} className="collection-page view-all-page" role="dialog" aria-label={title} onKeyDown={onKeyDown} onCancel={onCancel}>
    <div className="collection-page-body">
      <header className="collection-page-header">
        <button className="icon-button" type="button" data-autofocus title={translateUi("Back")} aria-label={translateUi("Back")} onClick={onBack}><ArrowLeft size={24} /></button>
        <h1>{title}</h1>
        <span className="view-all-count" aria-live="polite">{status !== "done" ? "" : visible.length === 1 ? translateUi("{value0} title", { value0: 1 }) : translateUi("{value0} titles", { value0: visible.length })}</span>
      </header>
      <div className={`view-all-grid ${posterMode ? "is-poster" : ""}`} aria-busy={status === "loading"}>
        {visible.map((item, index) => <MediaCard key={`${item.mediaType}:${item.id}`} item={item} onOpen={onOpen} posterMode={posterMode}
          rank={ranked && index < 10 ? index + 1 : undefined} />)}
      </div>
      <footer className="collection-load-state">
        {status === "loading" && <span role="status"><LoaderCircle className="spin" size={20} />{translateUi("Loading")}</span>}
        {status === "error" && <>
          <p role="alert">{translateUi("Some titles could not be loaded.")}</p>
          <button className="secondary" type="button" onClick={() => setAttempt((n) => n + 1)}><RotateCw size={18} />{translateUi("Retry")}</button>
        </>}
      </footer>
    </div>
  </dialog>, document.body);
}
