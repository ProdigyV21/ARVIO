export class HttpError extends Error { constructor(public status: number, message: string) { super(message); } }
const trial = { entitled: true, reason: 'trial', status: 'active', source: 'trial', expiresAt: new Date(Date.now() + 86400000).toISOString(), trialAvailable: false, trialDurationDays: 3 };
const paid = { ...trial, reason: 'subscription', source: 'kofi' };
const scenario = new URLSearchParams(location.search).get('case');
let state = scenario === 'paywall' ? { ...trial, entitled: false, reason: 'none', trialAvailable: true } : trial;
let checkoutSeen = false;
let paymentChecks = 0;
let completeStatus: ((value: typeof trial) => void) | undefined;
let completeLink: ((value: typeof trial) => void) | undefined;
export const fixture = {
  events: [] as Array<{ event_name: string; metadata: Record<string, unknown> }>,
  statusRequests: 0,
  billingRequests: [] as Array<Record<string, unknown>>,
  statusMode: scenario === 'error' ? 'error' : 'ready',
  analyticsMode: 'ready',
  deferNextStatus: false,
  deferNextLink: false,
  markPaid: () => { state = paid; },
  completeStatusAsPaid: () => { completeStatus?.(paid); completeStatus = undefined; },
  completeLinkAsPaid: () => { completeLink?.(paid); completeLink = undefined; },
};
export async function jsonRequest(url: string, init: RequestInit = {}) {
  const body = init.body ? JSON.parse(String(init.body)) : {};
  if (url.endsWith('/premium-funnel-event')) {
    fixture.events.push(body);
    if (body.event_name === 'checkout_opened') checkoutSeen = true;
    if (fixture.analyticsMode === 'error') throw new HttpError(503, 'Fixture analytics outage');
    if (fixture.analyticsMode === 'pending') return new Promise(() => {});
    return { ok: true };
  }
  if (url.endsWith('/entitlement-link')) {
    fixture.billingRequests.push(body);
    if (fixture.deferNextLink) { fixture.deferNextLink = false; return new Promise(resolve => { completeLink = resolve; }); }
    if (!body.code) return { verificationRequired: true };
    if (body.code !== '1234567890abcdef') throw new HttpError(400, 'Invalid or expired verification code.');
    state = paid;
    return state;
  }
  if (url.endsWith('/entitlement-status')) {
    fixture.statusRequests++;
    if (fixture.deferNextStatus) { fixture.deferNextStatus = false; return new Promise(resolve => { completeStatus = resolve; }); }
    if (fixture.statusMode === 'error') throw new HttpError(503, 'Fixture outage');
    if (fixture.statusMode === 'pending') return new Promise(() => {});
    if (init.method === 'POST') state = trial;
    if (checkoutSeen && scenario === 'delayed-payment' && ++paymentChecks >= 2) state = paid;
    return state;
  }
  throw Error('Unmocked request blocked');
}
