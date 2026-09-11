# Android Library server-tab startup

## Report and cause

Multiple Jellyfin and Emby connections could take around ten seconds to appear
as tabs when entering Library on Android, while the webapp did not have the delay.

The Library ViewModel called `HomeServerRepository.getCatalogCandidates()` for
each connection snapshot. That function performed sequential remote box-set
discovery across all usable servers (and across individual Plex libraries).
Library then discarded the box-set results because it only displays library
types. This put unnecessary network requests ahead of publishing every tab.
The webapp already derives provider tabs from saved connections independently
of library requests.

## Changes

- Publish Android provider tabs and sidebar libraries from saved connections.
- Keep full box-set discovery available for catalog settings and other callers.
- Decode/decrypt connection settings on IO, only when their stored value changes.
- Keep navigation available while selected-library content is loading.
- Invalidate canceled requests when leaving a provider or removing a connection.
- Select a remaining library when the previous selection disappears, including
  when saved libraries arrive after selecting an initially empty provider.
- Do not expose cancellation as a connection error or let stale results replace
  the current selection after playback-history enrichment.

## Verification

- Sideload unit suite: 816 tests, 815 passed, one existing skip, zero failures/errors.
- Play unit suite: 817 tests, 816 passed, one existing skip, zero failures/errors.
- Nine new unit regressions cover saved mapping, multiple servers, disabled
  connections/libraries, stalled requests, late responses and removed selections.
- Two Android UI tests passed on the API 31, 2 GB TV emulator: D-pad navigation
  and mobile-style touch navigation. No physical Shield or phone was tested.
- UI tests use the real repository and encrypted saved-settings path with five
  synthetic connections: two Jellyfin, two Emby, one Plex; ten saved libraries.
  Cloud and selected-library requests are deliberately stalled. The repository's
  HTTP client receives zero requests during tab creation and navigation.
- Observed time from ViewModel creation through visible provider assertions:
  638 ms for TV mode; 1789 ms for the first, mobile-mode composition. These are
  single debug-emulator observations, not production guarantees or content-load
  timings. The mobile check uses the TV emulator viewport, not a physical phone.
- Screenshots: `library-jellyfin-saved-tabs.png`, `library-emby-saved-tabs.png` in
  the local `C:/Users/arvin/ARVIO-library-evidence` directory. The content spinner
  is intentional: the tests verify usable tabs/sidebar while content is stalled.

The sideload debug app and test APK built successfully using an isolated
application-ID suffix. The existing production app/data/signing configuration
was not modified. Existing compiler warnings and Robolectric's Windows cache
cleanup warning remain; neither caused a failing test. Changes are not deployed
or included in an installed production release by this verification.
