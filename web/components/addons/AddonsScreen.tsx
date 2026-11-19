"use client";
import "./addons.css";
import { useTranslation } from "@/lib/i18n";

import { Server, Tv } from "lucide-react";
import { useAppPick } from "@/lib/store";
import { requestSourceSettings, sourceSetupState } from "@/lib/sourceSetup";
import { AddonManager } from "./AddonManager";

/**
 * The one addon manager (install with preview, enable/disable, order,
 * configure, remove). Open it with `setSection("addons")` from anywhere.
 */
export function AddonsScreen() {
  const translateUi = useTranslation();
  const { addons, addonsReady, settings, setSection } = useAppPick("addons", "addonsReady", "settings", "setSection");
  const sources = sourceSetupState(addons, settings);
  const openSettings = (target: "homeserver" | "tv") => {
    requestSourceSettings(target);
    setSection("settings");
  };
  return (
    <div className="screen has-section-heading addons-screen">
      <section className="section-heading">
        <p className="eyebrow">{translateUi("Sources")}</p>
        <h2>{translateUi("Addons")}</h2>
        <p className="addons-intro">{translateUi("Addons supply catalogs, sources and subtitles. They are shared by every profile and every ARVIO app on your account.")}</p>
      </section>
      {addonsReady && !sources.hasOnDemand && <section className="source-setup-panel" aria-labelledby="addon-setup-title">
        <h3 id="addon-setup-title">{translateUi("Connect your media sources")}</h3>
        {sources.catalogOnly && <p className="source-setup-warning">{translateUi("None of your enabled addons provide playback sources.")}</p>}
        <p>{translateUi("ARVIO does not include a film or TV subscription. Connect your Plex, Emby or Jellyfin server in Settings, or add a compatible addon supplied by a service you are authorized to use.")}</p>
        <div className="source-setup-actions">
          <button type="button" className="secondary" onClick={() => openSettings("homeserver")}><Server size={17} />{translateUi("Home Server")}</button>
          <button type="button" className="secondary" onClick={() => openSettings("tv")}><Tv size={17} />{translateUi("Live TV")}</button>
        </div>
      </section>}
      <AddonManager />
    </div>
  );
}
