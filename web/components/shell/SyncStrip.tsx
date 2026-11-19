"use client";
import { useTranslation } from "@/lib/i18n";


import { Cloud } from "lucide-react";
import { useAppPick } from "@/lib/store";

export function SyncStrip() {
  const translateUi = useTranslation();
  const { busy, auth, traktConnected, simklConnected, mdblistConnected } = useAppPick("busy", "auth", "traktConnected", "simklConnected", "mdblistConnected");
  const syncLabel = traktConnected ? "Trakt On" : simklConnected ? "Simkl On" : mdblistConnected ? "MDBList On" : "Sync Off";
  return (
    <div className="sync-strip" aria-hidden={!busy}>
      <Cloud size={16} />
      <span>{translateUi(busy || (auth ? "Cloud online" : "Cloud offline"))}</span>
      <span>{translateUi(syncLabel)}</span>
    </div>
  );
}
