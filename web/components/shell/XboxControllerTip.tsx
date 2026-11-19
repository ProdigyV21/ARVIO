"use client";

import { useEffect, useState } from "react";
import { Gamepad2, X } from "lucide-react";
import { useTranslation } from "@/lib/i18n";
import { loadStored, saveStored } from "@/lib/storage";
import { shouldShowXboxTip } from "@/lib/deviceClass";

export const XBOX_TIP_DISMISSED_KEY = "arvio.web.xboxControllerTip.dismissed.v1";

export function XboxControllerTip() {
  const translateUi = useTranslation();
  const [visible, setVisible] = useState(false);
  useEffect(() => {
    setVisible(shouldShowXboxTip(document.documentElement, loadStored<boolean>(XBOX_TIP_DISMISSED_KEY, false) === true));
  }, []);
  if (!visible) return null;
  const dismiss = () => {
    saveStored(XBOX_TIP_DISMISSED_KEY, true);
    setVisible(false);
  };
  return (
    <div className="xbox-tip" role="status">
      <Gamepad2 size={22} aria-hidden="true" />
      <p>{translateUi("Using a controller? Hold the Menu button and choose controller navigation for the best experience.")}</p>
      <button type="button" className="xbox-tip-close" onClick={dismiss} aria-label={translateUi("Dismiss")}>
        <X size={18} aria-hidden="true" />
      </button>
    </div>
  );
}
