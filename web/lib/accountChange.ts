export type AccountRevisionSnapshot = { key: string; revision: string } | null;

/**
 * Notices that another device saved the account document, without downloading it.
 *
 * The server's revision changes on every save, so an unchanged revision costs one tiny request and
 * nothing else. The first read only sets the baseline (the page has just loaded the document). A
 * read that fails or has no revision never means "changed", so an outage cannot turn into a
 * refresh loop, and one revision is retried at most once every ten minutes.
 */
export function createAccountChangeCheck(options: {
  key: string;
  snapshot: { current: AccountRevisionSnapshot };
  isActive: () => boolean;
  read: () => Promise<string | null>;
  refresh: () => Promise<unknown>;
}) {
  let busy = false;
  let disposed = false;
  let attemptedRevision: string | null = null;
  let attemptedAt = 0;
  return {
    dispose() { disposed = true; },
    async check() {
      if (disposed || busy || !options.isActive()) return;
      busy = true;
      try {
        const revision = await options.read();
        if (disposed || !options.isActive() || !revision) return;
        const previous = options.snapshot.current;
        if (!previous || previous.key !== options.key) {
          options.snapshot.current = { key: options.key, revision };
          return;
        }
        if (previous.revision === revision) return;
        if (revision === attemptedRevision && Date.now() - attemptedAt < 10 * 60_000) return;
        attemptedRevision = revision;
        attemptedAt = Date.now();
        // Record the revision the refresh was started for, so a save that lands during the
        // refresh still differs from it on the next check.
        options.snapshot.current = { key: options.key, revision };
        await options.refresh();
      } catch {
        // Keep the previous revision so the next successful check can retry.
      } finally {
        busy = false;
      }
    }
  };
}
