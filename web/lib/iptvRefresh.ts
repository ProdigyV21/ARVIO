/**
 * Coordinates Live TV playlist reloads.
 *
 * - Calls for the same account/profile/playlists share one in-flight load.
 * - A load whose playlists changed while it ran drops its result and starts
 *   again with the playlists the user has now (same account/profile only).
 * - The "loading" flag always clears once the last visible load settles. A
 *   load made stale by an edit used to skip that and leave Live TV on
 *   "Loading channels…" forever.
 */
export type IptvRefreshState<S> = { scope: string; signature: string; settings: S };

export type IptvRefreshDeps<S, T> = {
  /** Current account/profile scope, playlist signature and settings. */
  read: () => IptvRefreshState<S>;
  /** `fresh`: the user asked for a refresh, so sources must not answer from a recent copy. */
  load: (settings: S, options: { fresh: boolean }) => Promise<T>;
  /** Only called when the result still matches the current scope and playlists. */
  apply: (result: T, state: IptvRefreshState<S>) => void;
  /** Only called for a visible (non-quiet) load that is still current. */
  report: (error: unknown) => void;
  setLoading: (loading: boolean) => void;
};

export function createIptvRefresher<S, T>(deps: IptvRefreshDeps<S, T>) {
  let inFlight: { key: string; promise: Promise<void> } | null = null;
  let visibleRuns = 0;
  const refresh = ({ quiet = false, fresh = false }: { quiet?: boolean; fresh?: boolean } = {}): Promise<void> => {
    const start = deps.read();
    const key = `${start.scope}\n${start.signature}`;
    if (inFlight?.key === key) return inFlight.promise;
    const sameScope = () => deps.read().scope === start.scope;
    const isCurrent = () => {
      const now = deps.read();
      return now.scope === start.scope && now.signature === start.signature;
    };
    if (!quiet) {
      visibleRuns++;
      deps.setLoading(true);
    }
    const run = (async () => {
      try {
        const result = await deps.load(start.settings, { fresh });
        if (isCurrent()) deps.apply(result, start);
      } catch (error) {
        if (isCurrent() && !quiet) deps.report(error);
      } finally {
        if (!quiet && --visibleRuns <= 0) {
          visibleRuns = 0;
          deps.setLoading(false);
        }
      }
    })();
    const tracked: Promise<void> = run.then(() => {
      if (inFlight?.promise === tracked) inFlight = null;
      if (sameScope() && !isCurrent()) return refresh({ quiet });
      return undefined;
    });
    inFlight = { key, promise: tracked };
    return tracked;
  };
  return refresh;
}
