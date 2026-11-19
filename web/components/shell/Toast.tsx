"use client";

import { useEffect } from "react";
import { useTranslation } from "@/lib/i18n";
import { useAppPick } from "@/lib/store";
import { toastPoliteness } from "@/lib/toastPoliteness";

/**
 * Both live regions stay mounted so screen readers register them before a
 * message arrives (a region inserted together with its text is often not
 * announced). The toast never receives focus — clicking it dismisses it.
 */
export function Toast() {
  const translateUi = useTranslation();
  const { toast, setToast } = useAppPick("toast", "setToast");
  useEffect(() => {
    if (!toast) return undefined;
    const timer = window.setTimeout(() => setToast(null), toastPoliteness(toast) === "assertive" ? 6000 : 3200);
    return () => window.clearTimeout(timer);
  }, [setToast, toast]);

  const politeness = toast ? toastPoliteness(toast) : null;
  const body = toast ? (
    <button type="button" className="toast" tabIndex={-1} onClick={() => setToast(null)}>
      {translateUi(toast)}
    </button>
  ) : null;
  return (
    <>
      <div className="toast-region" role="status" aria-live="polite" aria-atomic="true">
        {politeness === "polite" ? body : null}
      </div>
      <div className="toast-region" role="alert" aria-live="assertive" aria-atomic="true">
        {politeness === "assertive" ? body : null}
      </div>
    </>
  );
}
