"use client";

import { Component, type ErrorInfo, type ReactNode } from "react";
import { AlertTriangle, Home, RotateCw, MessageSquareWarning } from "lucide-react";
import { useTranslation } from "@/lib/i18n";
import { clearScreenCaches } from "@/lib/storage";

// Crash containment. Three layers, outermost last:
//   <RowBoundary>     one home/search rail — a bad row disappears, the screen stays.
//   <ScreenBoundary>  one section (home, search, library, live TV, details,
//                     player, settings). On the first crash it clears that
//                     screen's rebuildable caches and retries once by itself —
//                     a poisoned cache is the common cause and would otherwise
//                     crash every load until its TTL ran out. A second crash
//                     shows the recover panel; the rest of the app keeps working.
//   app/error.tsx and app/global-error.tsx — the full-page recover screen.

/** /report link carrying what a support thread needs (see app/report/page.tsx). */
export function reportUrl(error: unknown, screen?: string): string {
  const message = error instanceof Error ? `${error.name}: ${error.message}` : String(error ?? "Unexpected crash");
  const digest = error && typeof error === "object" && "digest" in error ? String((error as { digest?: unknown }).digest ?? "") : "";
  const params = new URLSearchParams({
    id: digest ? `web-${digest}` : `web-${Date.now().toString(36)}`,
    v: process.env.NEXT_PUBLIC_BUILD_STAMP || "web",
    err: `${screen ? `[${screen}] ` : ""}${message}`.slice(0, 400),
    t: String(Date.now())
  });
  return `/report?${params.toString()}`;
}

function goHome() {
  // A plain navigation (not history) so a broken in-memory state is dropped.
  window.location.assign("/");
}

export function RecoverPanel({ error, screen, onRetry, onDismiss, fullPage = false }: {
  error: unknown;
  screen?: string;
  onRetry: () => void;
  /** Overlay screens (the player): close it instead of leaving for Home. */
  onDismiss?: () => void;
  fullPage?: boolean;
}) {
  const translateUi = useTranslation();
  return (
    <div className={fullPage ? "recover-screen recover-screen-full" : "recover-screen"} role="alert">
      <AlertTriangle size={fullPage ? 40 : 28} aria-hidden="true" />
      <h2>{fullPage ? translateUi("ARVIO hit a problem") : translateUi("This screen hit a problem")}</h2>
      <p>{translateUi("Your library, settings and progress are safe. Try again, or go back to Home.")}</p>
      <div className="recover-actions">
        <button type="button" className="recover-btn recover-btn-primary" data-autofocus onClick={onRetry}>
          <RotateCw size={18} aria-hidden="true" />
          {fullPage ? translateUi("Reload") : translateUi("Try again")}
        </button>
        {onDismiss ? (
          <button type="button" className="recover-btn" onClick={onDismiss}>
            <Home size={18} aria-hidden="true" />
            {translateUi("Close player")}
          </button>
        ) : (
          <button type="button" className="recover-btn" onClick={goHome}>
            <Home size={18} aria-hidden="true" />
            {translateUi("Go Home")}
          </button>
        )}
        <a className="recover-btn" href={reportUrl(error, screen)}>
          <MessageSquareWarning size={18} aria-hidden="true" />
          {translateUi("Report a problem")}
        </a>
      </div>
    </div>
  );
}

type BoundaryState = { error: unknown; attempt: number };

/**
 * Per-section boundary. `screen` names the section (home, search, library,
 * tv, details, player, settings, addons) — it selects which caches a retry
 * clears (lib/storage SCREEN_CACHE_KEYS). `resetKey` changes (e.g. the open
 * title) reset a crashed boundary automatically.
 */
type ScreenBoundaryProps = {
  screen: string;
  resetKey?: unknown;
  /** Render the recover panel as a full-screen layer (the player). */
  overlay?: boolean;
  onDismiss?: () => void;
  children: ReactNode;
};

export class ScreenBoundary extends Component<ScreenBoundaryProps, BoundaryState> {
  state: BoundaryState = { error: null, attempt: 0 };
  private autoRetried = false;

  static getDerivedStateFromError(error: unknown): Partial<BoundaryState> {
    return { error };
  }

  componentDidCatch(error: unknown, info: ErrorInfo) {
    console.error(`[ARVIO] ${this.props.screen} screen crashed`, error, info.componentStack);
    if (this.autoRetried) return;
    // First crash: clear what this screen reads from cache and retry once.
    this.autoRetried = true;
    clearScreenCaches(this.props.screen);
    this.setState((state) => ({ error: null, attempt: state.attempt + 1 }));
  }

  componentDidUpdate(previous: { resetKey?: unknown }) {
    if (previous.resetKey !== this.props.resetKey && this.state.error) {
      this.autoRetried = false;
      this.setState((state) => ({ error: null, attempt: state.attempt + 1 }));
    }
  }

  private retry = () => {
    clearScreenCaches(this.props.screen);
    this.setState((state) => ({ error: null, attempt: state.attempt + 1 }));
  };

  render() {
    if (this.state.error) {
      const dismiss = this.props.onDismiss ? () => {
        this.setState((state) => ({ error: null, attempt: state.attempt + 1 }));
        this.props.onDismiss?.();
      } : undefined;
      const panel = <RecoverPanel error={this.state.error} screen={this.props.screen} onRetry={this.retry} onDismiss={dismiss} />;
      return this.props.overlay ? <div className="recover-overlay" role="dialog" aria-modal="true">{panel}</div> : panel;
    }
    // Keyed by attempt so a retry remounts the subtree with fresh state.
    return <ScreenAttempt key={this.state.attempt}>{this.props.children}</ScreenAttempt>;
  }
}

function ScreenAttempt({ children }: { children: ReactNode }) {
  return <>{children}</>;
}

/**
 * Wrap one rail/row: if it throws, the row is dropped (or `fallback` shown)
 * and the rest of the screen renders. Resets when `resetKey` changes.
 */
export class RowBoundary extends Component<{ children: ReactNode; fallback?: ReactNode; resetKey?: unknown; name?: string }, { failed: boolean }> {
  state = { failed: false };

  static getDerivedStateFromError() {
    return { failed: true };
  }

  componentDidCatch(error: unknown) {
    console.error(`[ARVIO] row ${this.props.name ?? ""} crashed`, error);
  }

  componentDidUpdate(previous: { resetKey?: unknown }) {
    if (previous.resetKey !== this.props.resetKey && this.state.failed) this.setState({ failed: false });
  }

  render() {
    if (this.state.failed) return this.props.fallback ?? null;
    return this.props.children;
  }
}
