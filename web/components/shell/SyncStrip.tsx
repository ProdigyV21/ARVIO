"use client";

import { Cloud } from "lucide-react";
import { useApp } from "@/lib/store";

export function SyncStrip() {
  const { busy, auth, traktConnected } = useApp();
  return (
    <div className="sync-strip" aria-hidden={!busy}>
      <Cloud size={16} />
      <span>{busy || (auth ? "Cloud online" : "Cloud offline")}</span>
      <span>{traktConnected ? "Trakt On" : "Trakt Off"}</span>
    </div>
  );
}
