import type { EntitlementState } from "./entitlement";

// Bounded recovery after returning from Ko-fi, not permanent background polling.
// Account/visibility guards also protect a late response after sign-out.
export function createPaymentRecovery(options: {
  check: () => Promise<EntitlementState>;
  isCurrent: () => boolean;
  isVisible: () => boolean;
  activeWhen?: (state: EntitlementState) => boolean;
  onResult: (status: "checking" | "pending" | "active" | "error", state?: EntitlementState) => void;
  schedule?: (callback: () => void, delay: number) => ReturnType<typeof setTimeout>;
  cancel?: (timer: ReturnType<typeof setTimeout>) => void;
}) {
  const schedule = options.schedule ?? setTimeout;
  const cancel = options.cancel ?? clearTimeout;
  let timer: ReturnType<typeof setTimeout> | undefined;
  let attempts = 0;
  let stopped = false;
  let busy = false;
  const valid = () => !stopped && options.isCurrent();
  const run = async () => {
    if (!valid() || !options.isVisible() || busy || attempts >= 4) return;
    if (timer !== undefined) { cancel(timer); timer = undefined; }
    busy = true; attempts++;
    options.onResult("checking");
    try {
      const state = await options.check();
      if (!valid()) return;
      if (state.entitled && state.reason === "subscription" && (options.activeWhen?.(state) ?? true)) {
        stopped = true;
        options.onResult("active", state);
        return;
      }
      options.onResult("pending", state);
    } catch {
      if (valid()) options.onResult("error");
    } finally {
      busy = false;
      if (valid() && attempts < 4) timer = schedule(() => { timer = undefined; void run(); }, [3000, 7000, 15000][attempts - 1]);
    }
  };
  return {
    resume: run,
    retry: () => { if (valid() && !busy) { attempts = 0; return run(); } },
    stop: () => { stopped = true; if (timer !== undefined) cancel(timer); }
  };
}
