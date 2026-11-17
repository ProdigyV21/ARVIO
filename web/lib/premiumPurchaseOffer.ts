import type { EntitlementState } from "./entitlement";
import { currentEntitlement } from "./entitlementPolicy";
import type { PremiumPurchasePlan } from "./premiumPurchaseIntent";

/** A selection is only a checkout preference. It can never grant access. */
export function premiumPurchaseOffer(plan: PremiumPurchasePlan, regional: boolean, state: EntitlementState | null, now = Date.now()) {
  const active = Boolean(currentEntitlement(state, now)?.entitled);
  if (plan === "monthly") return { active, plan, total: "$2.99", period: "/ month", saving: null, monthlyEquivalent: null } as const;
  return { active, plan, total: regional ? "$10" : "$20", period: "/ 12 months", saving: regional ? "72%" : "44%", monthlyEquivalent: regional ? "$0.83" : "$1.67" } as const;
}
