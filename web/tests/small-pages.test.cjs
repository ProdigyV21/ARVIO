const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const read = (file) => fs.readFileSync(path.resolve(__dirname, '..', file), 'utf8');
const fn = (source, name) => {
  const match = source.match(new RegExp(`function ${name}\\((\\w+): string\\) \\{([\\s\\S]*?)\\n\\}`));
  return new Function(match[1], match[2]);
};

test('/discover only offers the app link where it works and never invents a domain', () => {
  const source = read('app/discover/page.tsx');
  assert.doesNotMatch(source, /https:\/\/arvio\.app/);
  const canOpen = fn(source, 'canOpenPackInApp');
  assert.equal(canOpen('Mozilla/5.0 (Linux; Android 14)'), true);
  assert.equal(canOpen('Mozilla/5.0 (iPhone; CPU iPhone OS 18_0 like Mac OS X)'), false);
  assert.equal(canOpen('Mozilla/5.0 (Windows NT 10.0; Win64; x64)'), false);
  const link = fn(source, 'packInstallLink');
  assert.equal(link('https://web.example/packs/a.json'), 'arvio://install-pack?url=https%3A%2F%2Fweb.example%2Fpacks%2Fa.json');
  assert.match(source, /role="dialog" aria-modal="true"/);
  assert.match(source, /event\.key === "Escape"/);
  assert.match(read('app/discover/layout.tsx'), /Catalog packs · ARVIO/);
});

test('/report says Copied only when copying worked, and explains a bare visit', () => {
  const source = read('app/report/page.tsx');
  assert.match(source, /setCopied\(ok \? "copied" : "failed"\)/);
  assert.match(source, /if \(ok\) setTimeout/);
  assert.match(source, /const bare = /);
  assert.match(source, /copied === "failed"/);
  assert.match(source, /role="alert"/);
  assert.match(source, /aria-label=\{translateUi\("Report text"\)\}/);
  assert.match(source, /currentTarget\.select\(\)/);
  assert.doesNotMatch(source, /phone clipboard/);
  assert.match(read('app/report/layout.tsx'), /title:/);
});

test('/migrate signs in through the ARVIO auth portal and can switch account', () => {
  const source = read('components/migrate/MigrateApp.tsx');
  assert.match(source, /redirect_uri=\$\{encodeURIComponent\(target\)\}/);
  assert.match(source, /consumePortalSignIn\(\)/);
  assert.doesNotMatch(source, /authClient\.signIn\(/);
  assert.match(source, /Sign out \/ switch account/);
  assert.doesNotMatch(source, /install it for this profile/);
});
