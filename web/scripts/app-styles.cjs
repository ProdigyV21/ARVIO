// The app's stylesheets in production cascade order: the layout's shared
// sheets, then the sheets of the eager screens (home, collections, profiles,
// premium), then the code-split screens. UI check scripts that render real
// components outside Next serve this, so they see every screen's styles.
const fs = require('node:fs');
const path = require('node:path');

const APP_SHEETS = [
  'app/globals.css',
  'app/source-setup.css',
  'app/app-foundations.css',
  'app/collections.css',
  'app/home.css',
  'components/profile/profiles.css',
  'app/premium.css',
  'components/shell/premium-plans.css',
  'app/topnav.css',
  'app/search.css',
  'app/library.css',
  'app/calendar.css',
  'app/settings.css',
  'app/settings-order.css',
  'components/settings/liveTvSettings.css',
  'components/details/details.css',
  'components/details/sources.css',
  'components/player/player.css',
  'components/livetv/livetv.css',
  'components/addons/addons.css',
  'components/login/login.css',
];

function appCss(root) {
  return APP_SHEETS.map((file) => fs.readFileSync(path.join(root, file), 'utf8')).join('\n');
}

module.exports = { APP_SHEETS, appCss };
