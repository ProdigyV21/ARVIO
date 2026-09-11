# TV guide scrolling performance

## Changes

- Isolate the selected focus-requester anchor with derived state so changing focus
  does not invalidate every visible channel row.
- Group accessibility traversal by channel row and expose each programme as one
  combined entry, including its full title, time and description.
- Keep programme accessibility actions available for live/catch-up playback.
- Draw rounded programme backgrounds directly. Avoid clip/opacity graphics layers
  for ordinary opaque programme cells; retain fading where needed.

## Verification

Five GuideRenderingDeviceTest instrumentation tests passed on an Android 12 TV
emulator configured with 2 GB RAM. The fixture has 55,000 channels, with 144 in
the rendering window. Tests cover bounded programme entries, accessible programme
details/actions, vertical channel navigation, offscreen EPG navigation, and
60-down/40-up position retention. This is not a full provider-load benchmark.

The signed sideload release built successfully and was installed as an update on
the TCL. The certificate matches the existing release. Live NPO 1 HD playback,
channel scrolling, programme focus and return to the channel column were checked.

## Device measurements

Baseline: 933c5352b. Same TCL, NL ALGEMEEN category, NPO 1 HD playing, category
drawer closed, ten down and ten up key events starting at channel 17. Statistics
were reset before each run and collected after settling. No sampling profiler ran
during frame measurements. The existing launcher accessibility service stayed on.

| Build/run | Frames | Janky | Median | P90 | P99 |
| --- | ---: | ---: | ---: | ---: | ---: |
| Baseline 1 | 148 | 37.16% | 32 ms | 93 ms | 200 ms |
| Baseline 2 | 152 | 34.87% | 31 ms | 81 ms | 150 ms |
| Final 1 | 140 | 30.71% | 32 ms | 69 ms | 150 ms |
| Final 2 | 151 | 27.15% | 30 ms | 81 ms | 150 ms |

These are short device comparisons, not a controlled lab benchmark. Live content,
EPG time windows and background activity can vary. Improvements are modest and
do not establish lag-free scrolling, 60 fps, or parity on every low-memory device.

Method sampling still identifies Compose accessibility geometry processing as a
major main-thread cost. A broader rendering/runtime change needs separate
accessibility and navigation regression coverage. Near-lag-free acceptance remains
unmet; do not advertise this patch as eliminating stutter.

## Follow-up: lightweight channel-mode programme rendering

Compact LTR TV programme cells now draw their read-only content on a canvas with
one accessible entry. Entering EPG mode restores the interactive programme layout;
touch devices, RTL layouts and taller rows retain the existing renderer. Programme
focus, full accessible descriptions and live/archive accessibility actions remain.

Two warmed TCL channel-scroll runs with live NPO 1 HD playing measured:

| Run | Frames | Janky | Median | P90 | P99 |
| --- | ---: | ---: | ---: | ---: | ---: |
| Canvas 1 | 151 | 21.85% | 23 ms | 77 ms | 150 ms |
| Canvas 2 | 155 | 21.29% | 24 ms | 61 ms | 113 ms |
| Final compatibility-guard build | 160 | 21.88% | 29 ms | 61 ms | 121 ms |

The EPG clock/content changed during builds, so these are directional measurements,
not a strict A/B percentage claim. Earlier runs in this session varied from 14.71%
in a future-time viewport to 32-36% in other guide windows before the canvas change.
The five emulator guide tests passed with canvas rendering, including the switch
to an actually focused interactive programme. Remaining jank is still noticeable
under rapid scrolling; there is no claim of lag-free operation on all devices.

## Follow-up: indexed focus and bounded time ruler

Channel up/down now requests the known adjacent channel directly instead of
performing a spatial search through the programme tree. Pending repeats retain
their requested channel index; the focused-channel callback is emitted by actual
focus acquisition rather than speculatively before it. The time ruler retains its
full horizontal scroll extent but only composes labels near the viewport. A
redundant inner programme clipping layer was removed; the viewport still clips.

Seven instrumentation tests passed on the 2 GB Android 12 TV emulator, including
rapid 26-down/10-up input, long-click accessibility, viewport-bounded ruler labels,
and offscreen programme navigation. The tests request Android's actual keyboard
input mode. The 55,000-channel fixture still uses a 144-row rendering window;
these are not end-to-end provider import or EPG loading-time tests.

The release-signed candidate was installed over the existing TCL installation,
without clearing data. Repeated 10-down/10-up checks returned from channel 17 to
27 and back to 17 with the outline fully visible after settling. The long-press
menu stayed open; right entered a programme and Back restored channel focus.
An earlier capture showed a partially clipped row/different channel number;
this was not reproduced in the three subsequently checked settled sequences.
Duplicate focus targets were considered but not established as the cause, and
no change to ChannelRow's focus modifiers was retained.

### Measurements and acceptance caveat

| Sequence | Frames | Janky | Median | P90 | P99 |
| --- | ---: | ---: | ---: | ---: | ---: |
| Initial 1 | 161 | 18.63% | 23 ms | 53 ms | 69 ms |
| Initial 2 | 153 | 21.57% | 23 ms | 57 ms | 81 ms |
| Initial 3 | 160 | 13.12% | 24 ms | 40 ms | 97 ms |
| Settled repeat 1 | 157 | 12.74% | 23 ms | 40 ms | 73 ms |
| Settled repeat 2 | 156 | 9.62% | 23 ms | 38 ms | 73 ms |
| Settled repeat 3 | 155 | 10.97% | 23 ms | 38 ms | 81 ms |

IMPORTANT: later screenshot comparisons exposed a retained, unmoving mini-player
frame. Android audio diagnostics recorded playback stopping at 14:56:50, before
the settled repeats at 15:08-15:09. The repeats' combined 11.1% is a guide-only
result, NOT a valid live-playback acceptance score. The initial runs were not
continuously checked for video motion either and must not establish a live-video
pass. The under-10% target remains unmet.

After restarting the app, cached guide queries reported 18/18 matched rows in
18-65 ms (one initial query 191 ms), but stream preparation logged live URLs on
the FlixStreams addon host and video did not restart. Existing playlist data was
not changed. These query timings are not total TV-page launch times, nor proof
that every provider channel has guide coverage.

Method sampling identifies accessibility semantics/coordinate traversal as a
remaining cost. Trials of consolidated channel semantics, canvas channel labels,
and full package compilation did not demonstrate useful gains and were not kept.
The existing launcher accessibility service remained enabled throughout. Further
live-playback measurements require a working stream and frame-motion checks;
do not advertise this candidate as sub-10% or lag-free.
