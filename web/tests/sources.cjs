// Source text for tests that read the app store or the player as text.
// lib/store.tsx is split into modules under lib/store/, and the player overlay
// into modules under components/player/overlay/. These helpers return the whole
// of each (entry file first, then its modules in name order) so a test keeps
// finding a declaration wherever it now lives.
const fs = require('node:fs');
const path = require('node:path');

const root = path.join(__dirname, '..');

function withModules(entry, dir) {
  const files = [path.join(root, entry)];
  const folder = path.join(root, dir);
  if (fs.existsSync(folder)) {
    for (const name of fs.readdirSync(folder).sort()) {
      if (/\.(ts|tsx)$/.test(name) && !name.endsWith('.d.ts')) files.push(path.join(folder, name));
    }
  }
  return files.map((file) => fs.readFileSync(file, 'utf8')).join('\n');
}

/** lib/store.tsx and every lib/store/* module. */
function storeSource() {
  return withModules('lib/store.tsx', 'lib/store');
}

/** components/player/PlayerOverlay.tsx and every components/player/overlay/* module. */
function playerSource() {
  return withModules('components/player/PlayerOverlay.tsx', 'components/player/overlay');
}

/** The source of a web/ file; the store and the player overlay include their modules. */
function sourceOf(relative) {
  const normalized = relative.replace(/^\.\.\//, '').replace(/^web\//, '');
  if (normalized === 'lib/store.tsx') return storeSource();
  if (normalized === 'components/player/PlayerOverlay.tsx') return playerSource();
  return fs.readFileSync(path.join(root, normalized), 'utf8');
}

module.exports = { storeSource, playerSource, sourceOf };
