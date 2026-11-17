const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');
const { load } = require('./load.cjs');
const policy = load('lib/entitlementPolicy.ts');
const offer = load('lib/premiumPurchaseOffer.ts', { './entitlementPolicy': policy });
const unpaid = { entitled: false, reason: 'none', source: null, expiresAt: null };
const paid = { entitled: true, reason: 'subscription', source: 'kofi', plan: 'yearly', expiresAt: '2099-01-01T00:00:00Z' };

// Run the production component and its real asynchronous effects with isolated
// identity/transport. No DOM browser, customer session, payment or email is used.
function confirmationFixture(options = {}) {
  const slots = [];
  let cursor = 0, dirty = false, effects = [], tree;
  const app = { auth: options.signedOut ? null : { userId: 'account-a', email: 'a@example.test' } };
  const authClient = { session: app.auth };
  const calls = { requests: 0, signIns: 0, closes: 0 };
  function MembershipCheckout() {}
  const same = (a, b) => a?.length === b?.length && a.every((value, index) => Object.is(value, b[index]));
  const react = {
    useId: () => 'test-title',
    useState: initial => {
      const index = cursor++;
      slots[index] ??= { value: typeof initial === 'function' ? initial() : initial };
      return [slots[index].value, value => {
        const next = typeof value === 'function' ? value(slots[index].value) : value;
        if (!Object.is(next, slots[index].value)) { slots[index].value = next; dirty = true; }
      }];
    },
    useRef: initial => { const index = cursor++; slots[index] ??= { value: { current: initial } }; return slots[index].value; },
    useEffect: (effect, deps) => {
      const index = cursor++, previous = slots[index];
      if (previous && same(previous.deps, deps)) return;
      slots[index] = { deps, cleanup: previous?.cleanup };
      effects.push(() => { slots[index].cleanup?.(); slots[index].cleanup = effect(); });
    }
  };
  const regional = Boolean(options.regional);
  const mocks = {
    react,
    'react/jsx-runtime': { jsx: (type, props) => ({ type, props }), jsxs: (type, props) => ({ type, props }) },
    'lucide-react': Object.fromEntries(['ArrowRight', 'BadgeCheck', 'Check', 'Loader2', 'ShieldCheck', 'X'].map(key => [key, () => null])),
    '@/lib/store': { useAppPick: () => app, authClient },
    '@/lib/config': { config: { paywallEnabled: true, selfHosted: false } },
    '@/lib/entitlement': {
      cachedEntitlement: () => options.cached ?? null,
      fetchEntitlement: async () => { calls.requests++; return options.fetch ? options.fetch(calls.requests) : options.state ?? unpaid; },
      kofiYearlyUrl: () => options.yearlyMissing ? '' : regional ? 'https://ko-fi.com/arvio/link/ARVIOREGION50' : 'https://ko-fi.com/s/373de24a07'
    },
    '@/lib/premiumAnalytics': { capturePremiumAttribution() {} },
    '@/lib/premiumPurchaseIntent': {},
    '@/lib/premiumPurchaseOffer': offer,
    '@/lib/regionalPricing': { useRegionalYearly: () => regional, loadVisitorCountry: () => Promise.resolve(regional ? 'BR' : 'US') },
    '@/lib/i18n': { useTranslation: () => (text, values = {}) => text.replace(/\{(\w+)\}/g, (_, key) => values[key] ?? '') },
    './MembershipCheckout': { MembershipCheckout },
    './PremiumPlans': { DISCORD_URL: 'https://discord.gg/test' },
    './premium-purchase-intent.css': {}
  };
  const filename = path.resolve(__dirname, '../components/shell/PremiumPurchaseIntent.tsx');
  const code = ts.transpileModule(fs.readFileSync(filename, 'utf8'), { compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022, jsx: ts.JsxEmit.ReactJSX, esModuleInterop: true } }).outputText;
  const module = { exports: {} };
  vm.runInNewContext(code, {
    module, exports: module.exports, require: name => { if (!(name in mocks)) throw Error(`Unmocked ${name}`); return mocks[name]; },
    document: { activeElement: null }, HTMLElement: class {}, window: { setTimeout, clearTimeout }
  }, { filename });
  const props = { plan: options.plan ?? 'yearly', onSignIn: () => calls.signIns++, onClose: () => calls.closes++ };
  function render(patch) {
    if (patch) { Object.assign(app, patch); authClient.session = app.auth; }
    for (let pass = 0; pass < 15; pass++) {
      dirty = false; cursor = 0; effects = [];
      tree = module.exports.PremiumPurchaseConfirmation(props);
      effects.forEach(effect => effect());
      if (!dirty) return tree;
    }
    throw Error('Confirmation did not settle');
  }
  function nodes(node, predicate) {
    if (Array.isArray(node)) return node.flatMap(child => nodes(child, predicate));
    if (!node || typeof node !== 'object') return [];
    return [...(predicate(node) ? [node] : []), ...nodes(node.props?.children, predicate)];
  }
  function text(node) {
    if (Array.isArray(node)) return node.map(text).join(' ');
    if (node && typeof node === 'object') return text(node.props?.children);
    return ['string', 'number'].includes(typeof node) ? String(node) : '';
  }
  render();
  return {
    app, calls, render,
    flush: async () => { await new Promise(setImmediate); render(); },
    checkout: () => nodes(tree, node => node.type === MembershipCheckout),
    buttons: () => nodes(tree, node => node.type === 'button'),
    text: () => text(tree),
    unmount: () => slots.forEach(slot => slot?.cleanup?.())
  };
}

test('annual handoff checks account before payment and displays the matching regional total and trusted destination', async () => {
  for (const regional of [false, true]) {
    const ui = confirmationFixture({ regional });
    assert.equal(ui.checkout().length, 0, 'no payment link while membership check is unresolved');
    await ui.flush();
    const checkout = ui.checkout()[0];
    assert.equal(checkout.props.entry, 'website_yearly');
    assert.equal(checkout.props.href, regional ? 'https://ko-fi.com/arvio/link/ARVIOREGION50' : 'https://ko-fi.com/s/373de24a07');
    assert.match(ui.text(), regional ? /\$10/ : /\$20/);
    assert.match(ui.text(), /12 monthly payments of \$2\.99 \(\$35\.88\)/);
    assert.match(ui.text(), /no automatic renewal/);
    assert.equal(checkout.props.showEmailHint, true);
    assert.match(ui.text(), /a@example\.test/);
    ui.unmount();
  }
});

test('monthly selection stays monthly; payment is only requested on a user action through existing checkout', async () => {
  const ui = confirmationFixture({ plan: 'monthly', regional: true });
  await ui.flush();
  assert.equal(ui.checkout().length, 1);
  assert.equal(ui.checkout()[0].props.entry, 'website_monthly');
  assert.equal(ui.checkout()[0].props.href, undefined, 'MembershipCheckout keeps its configured monthly URL');
  assert.match(ui.text(), /\$2\.99/);
  assert.match(ui.text(), /Cancel anytime through Ko-fi/);
  assert.equal(ui.calls.requests, 1);
  ui.unmount();
});

test('existing paid members and annual holders receive access continuation without another purchase', async () => {
  for (const plan of ['monthly', 'yearly']) {
    const ui = confirmationFixture({ plan, state: paid });
    await ui.flush();
    assert.equal(ui.checkout().length, 0);
    assert.match(ui.text(), /already have access/);
    const continueButton = ui.buttons().find(node => node.props.children?.[0] === 'Open ARVIO Web');
    assert.ok(continueButton);
    continueButton.props.onClick();
    assert.equal(ui.calls.closes, 1);
    ui.unmount();
  }
});

test('signed-out visitors see selected total and sign-in, with no anonymous payment link', async () => {
  const ui = confirmationFixture({ signedOut: true, plan: 'monthly' });
  await ui.flush();
  assert.equal(ui.calls.requests, 0);
  assert.equal(ui.checkout().length, 0);
  const signIn = ui.buttons().find(node => node.props.children?.[0] === 'Sign in to continue');
  assert.ok(signIn);
  signIn.props.onClick();
  assert.equal(ui.calls.signIns, 1);
  ui.unmount();
});

test('an entitlement outage never prompts an unknown existing buyer to pay again', async () => {
  const ui = confirmationFixture({ fetch: async () => { throw Error('outage'); } });
  await ui.flush();
  assert.equal(ui.checkout().length, 0);
  assert.match(ui.text(), /Could not check access/);
  assert.ok(ui.buttons().find(node => node.props.children === 'Check payment status'));
  ui.unmount();
});

test('a stale paid response or checkout callback cannot apply to another account', async () => {
  let resolve;
  const ui = confirmationFixture({ fetch: count => count === 1 ? new Promise(done => { resolve = done; }) : Promise.resolve(unpaid) });
  ui.render({ auth: { userId: 'account-b', email: 'b@example.test' } });
  resolve(paid);
  await ui.flush();
  assert.equal(ui.checkout().length, 1);
  assert.match(ui.text(), /b@example\.test/);
  assert.doesNotMatch(ui.text(), /already have access/);
  const staleCallback = ui.checkout()[0].props.onEntitled;
  ui.render({ auth: { userId: 'account-c', email: 'c@example.test' } });
  staleCallback(paid);
  await ui.flush();
  assert.equal(ui.checkout().length, 1);
  assert.doesNotMatch(ui.text(), /already have access/);
  ui.unmount();
});
