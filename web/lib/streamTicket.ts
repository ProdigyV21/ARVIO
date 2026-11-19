import type { AuthClient } from "./auth";
import { fetchStreamTicket } from "./entitlement";

// A short-lived signed ticket from the auth site that lets the resolver
// worker's /media relay serve this Premium account. Kept in memory only; it
// is refreshed well before it expires and dropped when access ends.
let ticket: { value: string; expiresAt: number; refreshAt: number; account: string } | null = null;
let inflight: { account: string; generation: number; promise: Promise<void> } | null = null;
let generation = 0;

// Tickets last 14 days; refresh once fewer than 7 remain.
const REFRESH_MARGIN_MS = 7 * 24 * 60 * 60 * 1000;

/** The current ticket, or "" (the relay then decides). The Premium gate
 *  clears it whenever the account changes or access ends. */
export function currentStreamTicket(): string {
  if (!ticket || ticket.expiresAt <= Date.now()) return "";
  return ticket.value;
}

export function clearStreamTicket() {
  generation++;
  ticket = null;
  inflight = null;
}

/** Fetch or refresh the ticket. Failures are quiet: without a configured
 *  secret the relay does not ask for one. */
export function refreshStreamTicket(auth: AuthClient): Promise<void> {
  const account = auth.session?.userId;
  if (!account) { clearStreamTicket(); return Promise.resolve(); }
  if ((ticket && ticket.account !== account) || (inflight && inflight.account !== account)) clearStreamTicket();
  if (ticket && Date.now() < ticket.refreshAt) return Promise.resolve();
  if (inflight?.account === account && inflight.generation === generation) return inflight.promise;
  const requestGeneration = generation;
  const request = fetchStreamTicket(auth)
    .then((next) => {
      const expiresAt = Date.parse(next.expiresAt);
      const now = Date.now();
      if (next.ticket && Number.isFinite(expiresAt) && expiresAt > now && auth.session?.userId === account && generation === requestGeneration) {
        // Partner leases can be shorter than consumer tickets. Refresh halfway
        // through their actual lifetime instead of fetching on every call.
        const margin = Math.min(REFRESH_MARGIN_MS, (expiresAt - now) / 2);
        ticket = { value: next.ticket, expiresAt, refreshAt: expiresAt - margin, account };
      }
    })
    .catch(() => { /* not configured, not Premium or offline: playback proceeds without */ })
    .finally(() => { if (inflight?.promise === request) inflight = null; });
  inflight = { account, generation: requestGeneration, promise: request };
  return request;
}
