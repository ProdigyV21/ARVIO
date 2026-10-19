import React, { useState } from 'react';
import { createRoot } from 'react-dom/client';
import { EntitlementGate } from '../../components/shell/Paywall';
import { PremiumAccount } from '../../components/shell/PremiumAccount';
import { MembershipCheckout } from '../../components/shell/MembershipCheckout';
import { fixture } from './premium-http';
import { setFixtureAccount } from './premium-store';
import { BadgeCheck } from 'lucide-react';
import '../../app/globals.css';
import '../../app/premium.css';
import '../../app/source-setup.css';

// These are the production components and entitlement client. Only identity
// and HTTP transport are fixtures; no customer trial, payment or email is used.
localStorage.clear();
// Preserve control setters/getters rather than copying their initial values.
Object.assign(window, { __premiumFixture: fixture });
Object.assign(fixture, { setAccount: setFixtureAccount });
function CheckoutOnly() {
  const [activated, setActivated] = useState(false);
  const [offerMounted, setOfferMounted] = useState(true);
  const [offerVisible, setOfferVisible] = useState(new URLSearchParams(location.search).get('case') !== 'checkout-hidden');
  const iconOnly = new URLSearchParams(location.search).get('case') === 'checkout-icon-only';
  Object.assign(fixture, { setOfferVisible, setOfferMounted });
  return <main style={{maxWidth: 820, margin: '24px auto', padding: '0 20px'}}>
    <h1>Isolated membership checkout</h1>
    {offerMounted && <div hidden={!offerVisible} data-testid="fixture-offer"><MembershipCheckout className={iconOnly ? 'mobile-premium-link' : 'paywall-primary'} entry={iconOnly ? 'trial_mobile_nav' : 'account_settings'} iconOnly={iconOnly} title={iconOnly ? 'Keep Premium' : undefined} showEmailHint={!iconOnly} onEntitled={() => setActivated(true)}>{iconOnly ? <BadgeCheck size={20} /> : 'Keep Premium - $2.99/month'}</MembershipCheckout></div>}
    <p data-testid="activation-result">{activated ? 'Paid activation applied' : 'No paid activation applied'}</p>
  </main>;
}
const checkoutOnly = new URLSearchParams(location.search).get('case')?.startsWith('checkout');
createRoot(document.getElementById('root')!).render(<>
  <div style={{padding: 12, background: '#202020', fontSize: 13}}>Local payment-flow test: no real payments or emails. Valid fixture code: 1234567890abcdef.</div>
  {checkoutOnly ? <CheckoutOnly /> : <EntitlementGate>
    <main style={{maxWidth: 820, margin: '24px auto', padding: '0 20px'}}>
      <h1 style={{fontSize: 24, marginBottom: 24}}>Accounts</h1>
      <PremiumAccount />
    </main>
  </EntitlementGate>}
</>);
