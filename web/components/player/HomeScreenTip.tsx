"use client";
import { useTranslation } from "@/lib/i18n";

import { Share, X } from "lucide-react";
import { useEffect, useState } from "react";
import { dismissHomeScreenTip, HOME_SCREEN_TIP_EVENT, shouldShowHomeScreenTip } from "@/lib/homeScreenTip";

/**
 * Shown once, after the first playback that started on iPhone/iPad Safari,
 * and only while the player is closed so it never covers the video.
 */
export function HomeScreenTip({ playerOpen }: { playerOpen: boolean }) {
  const translateUi = useTranslation();
  const [due, setDue] = useState(false);
  useEffect(() => {
    const check = () => setDue(shouldShowHomeScreenTip());
    check();
    window.addEventListener(HOME_SCREEN_TIP_EVENT, check);
    return () => window.removeEventListener(HOME_SCREEN_TIP_EVENT, check);
  }, []);
  if (!due || playerOpen) return null;
  const dismiss = () => {
    dismissHomeScreenTip();
    setDue(false);
  };
  return (
    <div className="toast home-screen-tip" role="status">
      <Share size={20} aria-hidden="true" />
      <p>{translateUi("Add ARVIO to your Home Screen: tap Share, then Add to Home Screen")}</p>
      <button type="button" className="home-screen-tip-close" onClick={dismiss} aria-label={translateUi("Dismiss")}>
        <X size={18} />
      </button>
    </div>
  );
}
