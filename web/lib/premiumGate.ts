// Explore mode: anyone signed in can use ARVIO Web; Premium is asked for only
// when they start something that plays (a title, a channel, a catch-up
// programme, an external player, a download or a copied stream link).
//
// The store and the details screen call premiumAllows() at those moments. The
// hosted EntitlementGate registers the gate; self-hosted builds and the
// Android app never do, so everything is allowed there.
import { config } from "./config";

export type PremiumIntent = "play" | "live" | "external" | "download" | "copy";

type Gate = (intent: PremiumIntent, resume?: () => void) => boolean;

let gate: Gate | null = null;

export function setPremiumGate(next: Gate | null) {
  gate = next;
}

/** True when the action may go ahead now. When it may not, the Premium offer
 *  opens; `resume` runs if the account becomes entitled while it is open. */
export function premiumAllows(intent: PremiumIntent, resume?: () => void): boolean {
  if (gate) return gate(intent, resume);
  // Hosted builds deny until the gate is registered (a play during the very
  // first render must not slip through); self-hosted builds have no paywall.
  return !config.paywallEnabled;
}
