"use client";
import "@/app/collections.css";

import { useEffect, useState } from "react";
import { FolderOpen } from "lucide-react";
import type { CatalogConfig, MediaItem } from "@/lib/types";
import { CollectionDetails } from "./CollectionDetails";
import { RailScroller } from "./RailScroller";

export function CustomCollectionRail({ catalog, folders, onOpen, onCollectionOpenChange }: {
  catalog: CatalogConfig; folders: CatalogConfig[]; onOpen: (item: MediaItem) => void; onCollectionOpenChange?: (open: boolean) => void;
}) {
  const [selectedId, setSelectedId] = useState<string | null>(null);
  const selected = folders.find(f => f.id === selectedId);
  useEffect(() => {
    if (!selectedId) return;
    onCollectionOpenChange?.(true);
    return () => onCollectionOpenChange?.(false);
  }, [selectedId, onCollectionOpenChange]);
  if (!folders.length) return null;
  return <section className="rail custom-collection-rail">
    <div className="rail-head"><h3>{catalog.name}</h3></div>
    <RailScroller className="rail-strip" ariaLabel={catalog.name}>
      {folders.map(folder => <CollectionTile key={`${folder.id}:${folder.collectionCoverImageUrl}`} folder={folder} onOpen={() => setSelectedId(folder.id)} />)}
    </RailScroller>
    {selected && <CollectionDetails key={selected.id} catalog={selected} onBack={() => setSelectedId(null)} onOpen={onOpen} />}
  </section>;
}

function CollectionTile({ folder, onOpen }: { folder: CatalogConfig; onOpen: () => void }) {
  const [failed, setFailed] = useState(false);
  const artwork = !failed && folder.collectionCoverImageUrl;
  return <button type="button" className={`custom-collection-tile ${String(folder.collectionTileShape).toUpperCase() === "POSTER" ? "is-poster" : ""}`}
    aria-label={folder.name} title={folder.name} onClick={onOpen}
    onFocus={e => e.currentTarget.scrollIntoView({ block: "nearest", inline: "nearest" })}
    onKeyDown={e => {
      if (e.key !== "ArrowLeft" && e.key !== "ArrowRight") return;
      const next = e.key === "ArrowRight" ? e.currentTarget.nextElementSibling : e.currentTarget.previousElementSibling;
      if (next instanceof HTMLButtonElement) { e.preventDefault(); next.focus(); }
    }}>
    {artwork ? <img src={artwork} alt="" loading="lazy" onError={() => setFailed(true)} /> : <div className="custom-collection-art"><FolderOpen size={36} aria-hidden /></div>}
    {(!folder.collectionHideTitle || !artwork) && <span>{folder.name}</span>}
  </button>;
}
