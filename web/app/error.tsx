"use client";

import { useEffect } from "react";
import { RecoverPanel } from "@/components/shell/ErrorBoundary";
import { LanguageProvider } from "@/lib/i18n";
import { clearScreenCaches, loadStored, SCREEN_CACHE_KEYS } from "@/lib/storage";

// Route-level crash screen (everything below the root layout). Screens inside
// AppShell have their own boundaries; this catches what escapes them (the
// providers themselves, the profile gate, a crashing boundary).
export default function AppError({ error }: { error: Error & { digest?: string }; reset: () => void }) {
  useEffect(() => {
    console.error("[ARVIO] app crashed", error);
  }, [error]);
  const language = loadStored<{ language?: string } | null>("arvio.web.settings", null)?.language || "en";
  const reload = () => {
    // A poisoned cache is the usual suspect — start the reload from fresh caches.
    for (const screen of Object.keys(SCREEN_CACHE_KEYS)) clearScreenCaches(screen);
    window.location.reload();
  };
  return (
    <LanguageProvider language={language}>
      <main className="recover-page">
        <RecoverPanel fullPage error={error} onRetry={reload} />
      </main>
    </LanguageProvider>
  );
}
