/** Ignore ratings/collection changes that cannot affect Continue Watching. */
export function continueWatchingActivitySignature(raw: unknown): string | null {
  if (!raw || typeof raw !== "object") return null;
  const activities = raw as Record<string, unknown>;
  const fields = [
    ["movies", "watched_at"], ["movies", "paused_at"], ["movies", "hidden_at"],
    ["episodes", "watched_at"], ["episodes", "paused_at"],
    ["shows", "hidden_at"], ["shows", "reset_at"]
  ];
  const values = fields.map(([section, key]) => {
    const group = activities[section];
    const value = group && typeof group === "object" ? (group as Record<string, unknown>)[key] : null;
    return typeof value === "string" ? value : "";
  });
  return values.some(Boolean) ? values.join("|") : null;
}

export type TraktActivitySnapshot = { key: string; signature: string } | null;

/** At most one direct activity read in flight; a failed read never means an empty library. */
export function createTraktActivityCheck(options: {
  key: string;
  snapshot: { current: TraktActivitySnapshot };
  isActive: () => boolean;
  read: () => Promise<string | null>;
  refresh: () => Promise<unknown>;
}) {
  let busy = false;
  let disposed = false;
  let attemptedSignature: string | null = null;
  let attemptedAt = 0;
  return {
    dispose() { disposed = true; },
    async check() {
      if (disposed || busy || !options.isActive()) return;
      busy = true;
      try {
        const signature = await options.read();
        if (disposed || !options.isActive() || !signature) return;
        const previous = options.snapshot.current;
        if (previous?.key === options.key && previous.signature !== signature) {
          // A partial outage must not turn activity polling into a full-sync retry loop.
          if (signature === attemptedSignature && Date.now() - attemptedAt < 10 * 60_000) return;
          attemptedSignature = signature;
          attemptedAt = Date.now();
          await options.refresh();
          // Refresh records its start-of-read signature, so changes arriving
          // during the refresh are still noticed on the next check.
        } else if (!previous || previous.key !== options.key) {
          options.snapshot.current = { key: options.key, signature };
        }
      } catch {
        // Keep the previous signature so the next successful check can retry.
      } finally {
        busy = false;
      }
    }
  };
}
