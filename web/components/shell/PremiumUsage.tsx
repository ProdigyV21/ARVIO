"use client";

import { useEffect } from "react";
import { authClient, useApp } from "@/lib/store";
import { trackPremiumDaily } from "@/lib/premiumAnalytics";

export function PremiumUsage() {
  const { auth, activeProfile, addons, addonsReady, settings } = useApp();
  const configured = addons.length > 0 || settings.homeServers.some(server => server.enabled) || settings.iptvPlaylists.some(playlist => playlist.enabled);
  useEffect(() => {
    if (!auth || !activeProfile) return;
    void trackPremiumDaily(authClient, "web_opened");
    if (addonsReady) void trackPremiumDaily(authClient, configured ? "sources_configured" : "sources_missing");
  }, [auth?.userId, activeProfile?.id, addonsReady, configured]);
  return null;
}
