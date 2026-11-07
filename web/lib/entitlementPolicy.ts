import type { EntitlementState } from "./entitlement";

export function currentEntitlement(state: EntitlementState | null, now = Date.now()): EntitlementState | null {
  // Trials are no longer a hosted access tier. Reject both historic shapes,
  // including a still-valid cache or a malformed mixed paid/trial response.
  if (state?.reason === "trial" || state?.source === "trial") {
    return { ...state, entitled: false, reason: "none", status: "expired", trialAvailable: false, trialDurationDays: 0 };
  }
  if (!state?.entitled || !state.expiresAt) return state;
  const expiry = Date.parse(accessEndsAt(state) ?? "");
  if (Number.isFinite(expiry) && expiry > now) return state;
  return { ...state, entitled: false, reason: "expired", trialAvailable: false, graceEndsAt: null };
}

// When access actually stops: the paid end, or the end of the grace period
// after a renewal that did not come through yet.
export function accessEndsAt(state: EntitlementState | null): string | null {
  if (!state) return null;
  return (state.reason === "grace" || state.reason === "partner") && state.graceEndsAt ? state.graceEndsAt : state.expiresAt;
}

// Recheck expiry even if the tab stays focused for the whole trial. Cap long
// deadlines to avoid overflowing browser timers and to notice paid renewals.
export function entitlementCheckDelay(state: EntitlementState | null, now = Date.now()): number | null {
  if (!state?.entitled) return null;
  const end = accessEndsAt(state);
  const expiry = end ? Date.parse(end) : Infinity;
  return Math.max(1000, Math.min(15 * 60_000, Number.isFinite(expiry) ? expiry - now + 100 : 15 * 60_000));
}

export function trialRemainingLabel(state: EntitlementState | null, now = Date.now()): string | null {
  if (!currentEntitlement(state, now)?.entitled || state?.reason !== "trial" || !state.expiresAt) return null;
  const remaining = Date.parse(state.expiresAt) - now;
  if (!Number.isFinite(remaining) || remaining <= 0) return null;
  const hours = Math.ceil(remaining / 3_600_000);
  if (hours > 24) return `Trial: ${Math.ceil(hours / 24)} days left`;
  if (hours > 1) return `Trial: ${hours} hours left`;
  const minutes = Math.ceil(remaining / 60_000);
  return `Trial: ${minutes} ${minutes === 1 ? "minute" : "minutes"} left`;
}
