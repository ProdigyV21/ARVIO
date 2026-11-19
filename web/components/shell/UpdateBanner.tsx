"use client";

import { useSyncExternalStore } from "react";
import { RotateCw, X } from "lucide-react";
import { useTranslation } from "@/lib/i18n";
import { applyUpdate, dismissUpdate, getUpdateState, subscribeUpdate } from "./UpdateWatcher";

const serverState = { version: null, dismissed: false };

/**
 * "Update ready — Reload". Polite live region, never takes focus: the update
 * also applies itself at the next safe moment (see UpdateWatcher), so this is
 * an offer, not an interruption.
 */
export function UpdateBanner() {
  const translateUi = useTranslation();
  const update = useSyncExternalStore(subscribeUpdate, getUpdateState, () => serverState);
  const visible = Boolean(update.version) && !update.dismissed;
  return (
    <div className="update-banner-region" role="status" aria-live="polite">
      {visible && (
        <div className="update-banner">
          <span>{translateUi("Update ready")}</span>
          <button type="button" className="update-banner-reload" onClick={() => applyUpdate("manual")}>
            <RotateCw size={16} aria-hidden="true" />
            {translateUi("Reload")}
          </button>
          <button type="button" className="update-banner-close" onClick={dismissUpdate} aria-label={translateUi("Later")}>
            <X size={16} aria-hidden="true" />
          </button>
        </div>
      )}
    </div>
  );
}
