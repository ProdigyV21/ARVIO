const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const ts = require('typescript');
const vm = require('node:vm');
const crypto = require('node:crypto');
const { sourceOf, storeSource } = require('./sources.cjs');

// resolveProfilePins is a plain function exported from the dialog module.
function loadPins() {
  const source = fs.readFileSync(path.resolve(__dirname, '../components/profile/ProfileDialog.tsx'), 'utf8');
  const start = source.indexOf('export async function resolveProfilePins');
  const end = source.indexOf('\nexport function ProfileDialog');
  const code = ts.transpileModule(`type PinEdit = "keep" | "set" | "remove"; type Profile = any;\n${source.slice(start, end)}`, { compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022 } }).outputText;
  const module = { exports: {} };
  vm.runInNewContext(code, { module, exports: module.exports, hashProfilePin: async (pin) => `hash:${pin}` });
  return module.exports.resolveProfilePins;
}
const hash = async (pin) => `hash:${pin}`;

test('profile lock PIN: set, change and remove write Android\'s pin/isLocked pair', async () => {
  const resolve = loadPins();
  const base = { lockPin: '', kidsOn: false, parentEdit: false, parentPin: '' };
  assert.deepEqual({ ...await resolve({ pin: null, isLocked: false }, { ...base, lock: 'set', lockPin: '1234' }, hash) }, { pin: 'hash:1234', isLocked: true, parentPin: null });
  assert.deepEqual({ ...await resolve({ pin: 'old', isLocked: true }, { ...base, lock: 'keep' }, hash) }, { pin: 'old', isLocked: true, parentPin: null });
  assert.deepEqual({ ...await resolve({ pin: 'old', isLocked: true }, { ...base, lock: 'set', lockPin: '98765' }, hash) }, { pin: 'hash:98765', isLocked: true, parentPin: null });
  assert.deepEqual({ ...await resolve({ pin: 'old', isLocked: true }, { ...base, lock: 'remove' }, hash) }, { pin: null, isLocked: false, parentPin: null });
});

test('parent PIN: kept, changed on request, required for a new kids profile, dropped with kids mode', async () => {
  const resolve = loadPins();
  const kids = { lock: 'keep', lockPin: '', kidsOn: true, parentEdit: false, parentPin: '' };
  assert.equal((await resolve({ parentPin: 'p' }, kids, hash)).parentPin, 'p');
  assert.equal((await resolve({ parentPin: 'p' }, { ...kids, parentEdit: true, parentPin: '4321' }, hash)).parentPin, 'hash:4321');
  assert.equal((await resolve(undefined, { ...kids, parentPin: '1111' }, hash)).parentPin, 'hash:1111');
  assert.equal((await resolve({ parentPin: 'p' }, { ...kids, kidsOn: false }, hash)).parentPin, null);
});

test('profile actions report a failed cloud save instead of closing as if synced', () => {
  const store = storeSource();
  const screen = fs.readFileSync(path.resolve(__dirname, '../components/profile/ProfileSelectionScreen.tsx'), 'utf8');
  assert.match(store, /catch \(error\) \{ throw new ProfileCloudSaveError\(profile, error\); \}/);
  assert.match(screen, /await createProfile\(/);
  // Delete needs a second, explicit confirmation in the dialog.
  const dialog = fs.readFileSync(path.resolve(__dirname, '../components/profile/ProfileDialog.tsx'), 'utf8');
  assert.match(dialog, /setConfirmDelete\(true\)/);
  assert.match(dialog, /removed from every device signed in to this account/);
});

test('billing verification accepts the 8-digit code with or without spaces, and legacy hex codes', () => {
  const source = fs.readFileSync(path.resolve(__dirname, '../components/shell/BillingEmailForm.tsx'), 'utf8');
  const body = source.match(/export function normalizeBillingCode\(value: string\): string \| null \{([\s\S]*?)\n\}/)[1];
  const normalize = new Function('value', body);
  assert.equal(normalize('1234 5678'), '12345678');
  assert.equal(normalize(' 1234-5678 '), '12345678');
  assert.equal(normalize('ABCDEF0123456789'), 'abcdef0123456789');
  assert.equal(normalize('1234567'), null);
  assert.equal(normalize('12345678x'), null);
  void crypto;
});
