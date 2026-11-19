"use client";

import { useEffect, useState } from "react";
import { RotateCw, WifiOff } from "lucide-react";
import { useTranslation } from "@/lib/i18n";

/**
 * Offline handling inside the app. Opened without a network (the service
 * worker served the cached shell), ARVIO can't load anything, so a full
 * offline screen replaces the app until the connection returns. Losing the
 * network mid-session only shows a small banner — the screen the user is on
 * stays usable for what is already loaded.
 */
export function OfflineNotice() {
  const translateUi = useTranslation();
  const [state, setState] = useState<"online" | "boot" | "session">("online");
  useEffect(() => {
    const bootOffline = typeof navigator !== "undefined" && navigator.onLine === false;
    if (bootOffline) setState("boot");
    const offline = () => setState((current) => (current === "boot" ? current : "session"));
    const online = () => {
      // Nothing loaded while offline at boot: start fresh now.
      if (bootOffline) window.location.reload();
      else setState("online");
    };
    window.addEventListener("offline", offline);
    window.addEventListener("online", online);
    return () => {
      window.removeEventListener("offline", offline);
      window.removeEventListener("online", online);
    };
  }, []);

  if (state === "online") return null;
  if (state === "session") {
    return (
      <div className="offline-banner" role="status">
        <WifiOff size={18} aria-hidden="true" />
        <span>{translateUi("You're offline. Some things won't load until you reconnect.")}</span>
      </div>
    );
  }
  return (
    <div className="offline-screen" role="alert">
      <div>
        <WifiOff size={40} aria-hidden="true" />
        <h1>{translateUi("You're offline")}</h1>
        <p>{translateUi("ARVIO needs a connection to browse and play. Downloads you saved on this device stay in the folder you chose and play in any video player.")}</p>
        <button type="button" className="recover-btn recover-btn-primary" data-autofocus onClick={() => window.location.reload()}>
          <RotateCw size={18} aria-hidden="true" />
          {translateUi("Try again")}
        </button>
      </div>
    </div>
  );
}
