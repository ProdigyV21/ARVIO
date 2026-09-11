"use client";

import { Puzzle, Settings, X } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { useApp } from "@/lib/store";

const DISMISS_KEY_PREFIX = "arvio.web.noAddonsPrompt.v1:";

export function NoAddonsPrompt() {
  const {
    view,
    section,
    setSection,
    activeProfile,
    addons,
    addonsReady,
    settings,
    closeDetails
  } = useApp();
  const [dismissal, setDismissal] = useState<{ profileId: string; dismissed: boolean } | null>(null);
  const browseRef = useRef<HTMLButtonElement>(null);
  const profileId = activeProfile?.id ?? "";

  useEffect(() => {
    if (!profileId) {
      setDismissal(null);
      return;
    }
    let dismissed = false;
    try {
      dismissed = window.sessionStorage.getItem(`${DISMISS_KEY_PREFIX}${profileId}`) === "1";
    } catch {
      // A blocked session store should not prevent onboarding from rendering.
    }
    setDismissal({ profileId, dismissed });
  }, [profileId]);

  const visible =
    view === "app" &&
    Boolean(profileId) &&
    addonsReady &&
    addons.length === 0 &&
    !settings.homeServers.some((server) => server.enabled) &&
    !settings.iptvPlaylists.some((playlist) => playlist.enabled) &&
    section !== "addons" &&
    dismissal?.profileId === profileId &&
    !dismissal.dismissed;

  const dismiss = () => {
    if (!profileId) return;
    try {
      window.sessionStorage.setItem(`${DISMISS_KEY_PREFIX}${profileId}`, "1");
    } catch {
      // Keep the in-memory dismissal when browser storage is unavailable.
    }
    setDismissal({ profileId, dismissed: true });
  };

  useEffect(() => {
    if (!visible) return undefined;
    const focusTimer = window.setTimeout(() => browseRef.current?.focus(), 0);
    const onKeyDown = (event: KeyboardEvent) => {
      if (event.key === "Escape") dismiss();
    };
    window.addEventListener("keydown", onKeyDown);
    return () => {
      window.clearTimeout(focusTimer);
      window.removeEventListener("keydown", onKeyDown);
    };
  }, [visible, profileId]);

  if (!visible) return null;

  return (
    <div className="modal-scrim no-addons-scrim" onClick={dismiss}>
      <section
        className="no-addons-dialog"
        role="dialog"
        aria-modal="true"
        aria-labelledby="no-addons-title"
        aria-describedby="no-addons-description"
        onClick={(event) => event.stopPropagation()}
      >
        <button type="button" className="no-addons-close" onClick={dismiss} aria-label="Close" title="Close">
          <X size={20} />
        </button>

        <div className="no-addons-heading">
          <span className="no-addons-icon" aria-hidden="true"><Puzzle size={26} /></span>
          <div>
            <p className="eyebrow">Sources required</p>
            <h2 id="no-addons-title">Connect your media sources</h2>
          </div>
        </div>

        <p id="no-addons-description" className="no-addons-copy">
          ARVIO does not include a film or TV subscription. Connect your Plex, Emby or Jellyfin
          server in Settings, or add a compatible addon supplied by a service you are authorized to use.
        </p>
        <p className="no-addons-disclaimer">
          Catalog information and artwork do not grant viewing rights. Access depends on your
          own media and the permissions provided by your chosen services.
        </p>

        <div className="no-addons-actions">
          <button
            type="button"
            className="secondary no-addons-settings"
            onClick={() => {
              dismiss();
              closeDetails();
              setSection("addons");
            }}
          >
            <Settings size={17} /> Addon settings
          </button>
          <button
            type="button"
            ref={browseRef}
            className="primary no-addons-browse"
            onClick={() => {
              dismiss();
              closeDetails();
              setSection("settings");
            }}
          >
            <Settings size={17} /> Open settings
          </button>
        </div>
      </section>
    </div>
  );
}
