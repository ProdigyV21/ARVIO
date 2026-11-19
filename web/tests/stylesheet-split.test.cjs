// The stylesheet is split per screen: the root layout loads only the shared
// sheets, and every screen sheet is imported by the component that renders the
// screen, so code-split screens bring (and only then load) their own CSS.
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { APP_SHEETS } = require('../scripts/app-styles.cjs');

const root = path.join(__dirname, '..');
const read = (file) => fs.readFileSync(path.join(root, file), 'utf8');
const cssImports = (file) => [...read(file).matchAll(/^import\s+["']([^"']+\.css)["'];/gm)].map((m) => m[1]);

test('the root layout loads only the shared sheets, in cascade order', () => {
  assert.deepEqual(cssImports('app/layout.tsx'), ['./globals.css', './source-setup.css', './app-foundations.css']);
});

test('each screen imports its own sheet', () => {
  const owners = {
    'components/home/HomeScreen.tsx': '@/app/home.css',
    'components/media/CollectionDetails.tsx': '@/app/collections.css',
    'components/media/CustomCollectionRail.tsx': '@/app/collections.css',
    'components/media/CategoryViewAll.tsx': '@/app/collections.css',
    'components/profile/ProfileSelectionScreen.tsx': './profiles.css',
    'components/profile/ProfileDialog.tsx': './profiles.css',
    'components/shell/Paywall.tsx': '@/app/premium.css',
    'components/shell/PremiumOffer.tsx': '@/app/premium.css',
    'components/shell/TopNav.tsx': '@/app/topnav.css',
    'components/search/SearchScreen.tsx': '@/app/search.css',
    'components/watchlist/WatchlistScreen.tsx': '@/app/library.css',
    'components/watchlist/CalendarScreen.tsx': '@/app/calendar.css',
    'components/settings/SettingsScreen.tsx': '@/app/settings.css',
    'components/details/DetailsDrawer.tsx': './details.css',
    'components/details/SourcePicker.tsx': './sources.css',
    'components/details/SourceRow.tsx': './sources.css',
    'components/player/PlayerOverlay.tsx': './player.css',
    'components/livetv/LiveTvScreen.tsx': './livetv.css',
    'components/addons/AddonsScreen.tsx': './addons.css',
    'components/login/LoginScreen.tsx': './login.css',
  };
  for (const [component, sheet] of Object.entries(owners)) assert.ok(cssImports(component).includes(sheet), `${component} imports ${sheet}`);
});

test('sheets moved out of the layout load before the screen component\'s other imports', () => {
  // They used to load with the layout, ahead of everything a screen imports.
  for (const file of ['components/settings/SettingsScreen.tsx', 'components/watchlist/CalendarScreen.tsx', 'components/shell/Paywall.tsx']) {
    const first = read(file).split('\n').find((line) => /^import\s/.test(line));
    assert.match(first, /\.css["'];$/, file);
  }
});

test('every app sheet exists and the retired round-1 sheets are gone', () => {
  for (const sheet of APP_SHEETS) assert.ok(fs.existsSync(path.join(root, sheet)), sheet);
  for (const gone of ['app/tv-guide.css', 'app/ui-refinements.css', 'app/player-platform.css']) assert.equal(fs.existsSync(path.join(root, gone)), false, gone);
});
