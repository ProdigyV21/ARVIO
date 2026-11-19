import type { AuthClient } from "./auth";
import { claimReferralCode } from "./entitlement";

// "Give a month, get a month": an invite link is web.arvio.tv/?ref=CODE. The
// code is remembered until the visitor signs in, then claimed once.
const KEY = "arvio.referral.pending";
const CODE = /^[A-HJ-NP-Z2-9]{8}$/;

function read(): string | null {
  try { return localStorage.getItem(KEY); } catch { return null; }
}

function write(code: string | null) {
  try { if (code) localStorage.setItem(KEY, code); else localStorage.removeItem(KEY); } catch { /* storage is optional */ }
}

/** Remember ?ref=CODE from the address bar and tidy the URL. */
export function captureReferralCode(location: Location = window.location, history: History = window.history) {
  const url = new URL(location.href);
  const code = url.searchParams.get("ref")?.trim().toUpperCase();
  if (!code) return;
  if (CODE.test(code)) write(code);
  url.searchParams.delete("ref");
  try { history.replaceState(history.state, "", `${url.pathname}${url.search}${url.hash}`); } catch { /* keep the URL */ }
}

export function pendingReferralCode(): string | null {
  const code = read();
  return code && CODE.test(code) ? code : null;
}

/** Claim a remembered invite for the signed-in account (once). Accounts that
 *  already paid cannot be invited; the code is dropped either way. */
export async function claimPendingReferral(auth: AuthClient, everPaid: boolean): Promise<"claimed" | "skipped" | "none"> {
  const code = pendingReferralCode();
  if (!code || !auth.session) return "none";
  if (everPaid) { write(null); return "skipped"; }
  try {
    const result = await claimReferralCode(auth, code);
    write(null);
    return result.ok ? "claimed" : "skipped";
  } catch (error) {
    // 409 (own code, already invited, already a member) is final; keep the
    // code only for network errors so the next visit can try again.
    if ((error as { status?: number })?.status === 409) write(null);
    return "skipped";
  }
}
