import React from 'react';
import { createRoot } from 'react-dom/client';
import { EntitlementGate } from '../../components/shell/Paywall';
import { PremiumAccount } from '../../components/shell/PremiumAccount';
import '../../app/globals.css';
import '../../app/premium.css';

// These are the production components and entitlement client. Only identity
// and HTTP transport are fixtures; no customer trial, payment or email is used.
localStorage.clear();
createRoot(document.getElementById('root')!).render(<>
  <div style={{padding: 12, background: '#202020', fontSize: 13}}>Local payment-flow test: no real payments or emails. Valid fixture code: 1234567890abcdef.</div>
  <EntitlementGate>
    <main style={{maxWidth: 820, margin: '24px auto', padding: '0 20px'}}>
      <h1 style={{fontSize: 24, marginBottom: 24}}>Accounts</h1>
      <PremiumAccount />
    </main>
  </EntitlementGate>
</>);
