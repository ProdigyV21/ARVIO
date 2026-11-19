"use client";

import { useEffect } from "react";
import { authClient, useAppPick } from "@/lib/store";
import { trackPremiumDaily } from "@/lib/premiumAnalytics";
import { sourceSetupState } from "@/lib/sourceSetup";

export function PremiumUsage() {
  const { auth, activeProfile, addons, addonsReady, settings } = useAppPick("auth", "activeProfile", "addons", "addonsReady", "settings");
  const configured = sourceSetupState(addons, settings).hasAny;
  useEffect(() => {
    if (!auth || !activeProfile) return;
    void trackPremiumDaily(authClient, "web_opened");
    if (addonsReady) void trackPremiumDaily(authClient, configured ? "sources_configured" : "sources_missing");
  }, [auth?.userId, activeProfile?.id, addonsReady, configured]);
  return null;
}
