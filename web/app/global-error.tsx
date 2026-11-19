"use client";

import { useEffect } from "react";
import { RecoverPanel } from "@/components/shell/ErrorBoundary";
import { clearScreenCaches, SCREEN_CACHE_KEYS } from "@/lib/storage";
import "./app-foundations.css";

// Last-resort crash screen: the root layout itself failed, so this renders its
// own <html>/<body> and carries its own styles (app-foundations.css only —
// the layout's stylesheets may be gone). English only: the translation
// provider lives inside what crashed.
export default function GlobalError({ error }: { error: Error & { digest?: string }; reset: () => void }) {
  useEffect(() => {
    console.error("[ARVIO] root layout crashed", error);
  }, [error]);
  const reload = () => {
    for (const screen of Object.keys(SCREEN_CACHE_KEYS)) clearScreenCaches(screen);
    window.location.reload();
  };
  return (
    <html lang="en">
      <body className="recover-body">
        <main className="recover-page">
          <RecoverPanel fullPage error={error} onRetry={reload} />
        </main>
      </body>
    </html>
  );
}
