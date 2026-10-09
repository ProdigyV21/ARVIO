# Web interface translations

The web language menu follows Android's `TMDB_LANGUAGES` catalog, with an
additional English UK option. The saved content language selects both metadata
language and interface language; cloud profile synchronization uses that same setting.

Run `node scripts/generate-translations.mjs` from `web` after changing wording
or Android translations. The normal build runs this automatically. It produces
the public dictionaries, fingerprint manifest, language registry and phrase list.
Only the active dictionary is downloaded by the browser.

Translation precedence is Android resources, then explicit `<locale>.json`
overrides in this directory. `generated/<locale>.json` fills remaining gaps.
These gap dictionaries were machine translated from public UI wording and
spot checked, including placeholders, product names and media terminology.
They have not all been reviewed by native speakers. Builds and the running
application do not contact a translation service. Language improvements belong
in the explicit overrides or the community Android resources.
All XML string resource files in each Android values directory are included.
New phrases without a translated value use English until a translation is supplied;
English fallback entries are not a claim of complete native-language coverage.

`translation-sources.mjs` collects literal calls and supported label registries.
When introducing dynamically selected UI text, include its registry in that
collector. Personal list names, profile names and provider content remain intact.

Run `npm test` for dictionary coverage, placeholder integrity and locale tests.
Run `ARVIO_TEST_ALL_LANGUAGES=1 npm run test:i18n-ui` for all selectable languages
at TV, tablet and phone sizes, including RTL navigation and live changes without
losing typed search input. These use local fixtures and do not access a TV.
