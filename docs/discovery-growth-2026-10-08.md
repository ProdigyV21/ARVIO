# ARVIO discovery growth — 8 October 2026

## Scope
Incremental marketing-site and first-party report changes only. No Android or webapp runtime changes, prices, subscriptions, paid advertising, bought backlinks or automatic public posting. Reviewer messages below are drafts, not sent messages. Do not resubmit listings without checking for an existing listing/application first.

## Evidence and priorities
Google Search Console's 28-day report covers 8 September–5 October: approximately 4,360 clicks and 19,700 impressions. A query filter excluding `arvio` shows 79 clicks / 1,191 impressions / average position 17.4, but includes misspellings and omits anonymized queries. It is not an exact non-brand share, keyword search-volume estimate or post-change evaluation.

Small discovery signals: `android tv open source` (25 impressions, one click, position 7.8), `cinemeta alternative` (13 impressions, no clicks, position 8.0), and `servidor jellyfin android` (10 impressions, no clicks, position 10.9). The last query concerns a server, while ARVIO is a client: do not target it with a misleading server claim.

Official competitor pages confirm that multi-source clients already exist (https://plezy.app/ and https://spectati.com/plex-jellyfin). This validates a product category, not keyword volume or ARVIO superiority. Improve existing pages before publishing additional keyword pages. Three candidates to evaluate next in Search Console: Jellyfin alternative client Android TV, Plex/Emby/Jellyfin one-app workflow, and compatible addon manifest setup. No commercial keyword-volume tool was used; no high-volume claim is made.

Search guidance: https://developers.google.com/search/docs/fundamentals/creating-helpful-content and https://developers.google.com/search/docs/crawling-indexing/links-crawlable. Publish substantive help with accurate first-hand screenshots, not near-duplicate comparison pages or keyword stuffing.

## Implementation
- Extend three existing English guides with decision/checklist sections and honest feature limits. Preserve their URLs, existing screenshots, FAQs, translated counterparts and navigation.
- Add three generated, public-safe Studio starter documents and read-only preview links. Separate discovery metadata from playable media, preserve edits in the original tab, and keep Studio network-free.
- Improve guide/tool links and browser/free-self-hosting/Premium choices. Do not imply an Android user needs to subscribe.
- Add bounded `guide_page_view` measurements and a `byLandingPage` report to the existing first-party funnel. No arbitrary path, collection fragment, email, cookie or browser storage is collected. Existing DNT/GPC opt-outs, deduplication and write ceilings stay in place.
- Keep Ko-fi subscription confirmation server-side. A direct purchase without a matched authenticated journey can remain unattributed; do not call it a tracked conversion merely because a visitor clicked Ko-fi.

Studio visits and JSON downloads can be examined separately with Netlify analytics; Studio does not send marketing events. A visit/download alone does not prove import into the app.

The guide counter is the number of measured transient journeys with a guide event, not unique visitors or a complete page-view total. The landing label belongs to the earliest observed event in the reporting window, not necessarily the visitor's original lifetime landing. Existing global and per-network write ceilings may undercount fast multi-step journeys; keep that limitation visible when comparing counts.

## Crawl state observed
The aggregate indexing report was updated 4 October and must not be treated as real-time. Direct URL Inspection on 8 October: Stremio guide and clean media-kit URL indexed; Collection Studio, collections guide and self-hosting guide discovered but not indexed; browser-playback guide unknown. Check after production publishing; request indexing where eligible. A request does not guarantee indexing or ranking.

## Distribution shortlist and draft messages
Verified contacts below come from the publisher's own pages. No audience-size, response, partnership or coverage guarantee is made.

### AFTVnews — contact@aftvnews.com
Verified at https://www.aftvnews.com/contact/ on 8 October. Relevant Fire TV / Android TV / TV app audience. Use a Fire OS device; do not claim compatibility with Vega OS. No existing-message check was possible: Gmail search returned RATE_LIMIT_EXCEEDED, although the connected profile is arvio.app@gmail.com. Check sent mail before sending this draft.

Subject: ARVIO: free Android TV media hub and a shareable collection builder

Hi Elias,

I'm the developer behind ARVIO, an independent media hub for Android TV and Android. It connects users' own Jellyfin, Plex and Emby libraries, with supported live TV sources and custom Home collections.

A possible angle for your readers is Collection Studio: a free, no-account browser tool that turns TMDB discovery presets or public lists into an importable Home row. We've added three ready-to-use examples. No media or streaming subscriptions are included.

Website and device-specific setup: https://arvio.tv/fire-tv-media-player/?utm_source=aftvnews&utm_medium=outreach&utm_campaign=discovery-oct2026
Collection examples: https://arvio.tv/collection-studio/#examples
Screenshots: https://arvio.tv/media-kit/
Short testing/recording outline: https://arvio.tv/media-kit/reviewer-brief.txt
Official APK/source: https://github.com/ProdigyV21/ARVIO

The Android app and self-hosted web version are free; optional managed browser hosting is $2.99/month. I'd welcome independent feedback if you think it's a fit. There's no payment or required positive coverage attached to this invitation.

Best,
ARVIO
arvio.app@gmail.com

### AndroidTVNews — contact form
Verified at https://androidtvnews.com/contact/ on 8 October. Do not invent an email address. Draft not submitted.

Subject: A free Android TV client for existing Jellyfin, Plex and Emby libraries

Hi AndroidTVNews team,

I develop ARVIO, an independent Android TV media hub. If you cover alternative media-server clients, one useful angle is keeping Jellyfin, Plex and Emby connections in one interface without migrating the server libraries. Connections remain separate; ARVIO does not promise a merged or automatically deduplicated database.

The Android app is free and users bring their own media. Our setup guide includes the actual app screenshot, a first-session checklist and limitations: https://arvio.tv/plex-emby-jellyfin/?utm_source=androidtvnews&utm_medium=outreach&utm_campaign=discovery-oct2026

Screenshots and source: https://arvio.tv/media-kit/ and https://github.com/ProdigyV21/ARVIO
Testing outline: https://arvio.tv/media-kit/reviewer-brief.txt

Would this be useful for an independent review or setup tutorial? No paid placement or positive review is requested.

Best,
ARVIO
arvio.app@gmail.com

### selfh.st — newsletter submission candidate, not a duplicate directory submission
Official form: https://selfh.st/submit/. Directory rules: https://selfh.st/apps-about/. Previous directory submission was authorized in this chat, but no current acceptance or rejection was verified. Do not claim it is listed or send a duplicate. The new collection examples could be pitched as a companion-tool update only if the newsletter form's current criteria permit it.

Draft: ARVIO's free Collection Studio builds a personal Home row from authored TMDB discovery queries or public MDBList / numeric Trakt list references. Three starter templates can be previewed, customized and exported to JSON without an account. The tool makes no source requests or uploads; share links encode public-safe collection definitions. It complements the free Android client and self-hostable web version; no media is included. https://arvio.tv/collection-studio/#examples — source: https://github.com/ProdigyV21/ARVIO

### Existing listing confirmed
AlternativeTo is already live: https://alternativeto.net/software/arvio/about/ (added 3 October). Its body correctly describes free Android / free self-hosting versus optional hosting. Its pricing summary describes the free tier as having limited functionality: worth clarifying with the directory rather than resubmitting or asking for fake reviews. No external edit made in this task.

## Measurement and evaluation
- First complete 28-day baseline above is pre-change; keep it separate from the post-release window.
- Review discovery impressions and clicks after 7 complete days for crawl/measurement issues; 28 complete days for an initial traffic comparison. More time may be needed for rankings.
- Compare the same complete-day lengths, separate brand/misspellings from discovery terms, and inspect device/country mix. Do not infer causality from a before/after total alone.
- Compare guide landings, Premium views, membership clicks, connected trials, confirmed NEW subscriptions and observed paid access. Renewals are not new conversions.
- Do not calculate a Ko-fi conversion rate by dividing memberships by all site visits: attribution coverage, blockers and direct purchases differ.
- Add no automation/reminders unless the user asks. No broad ads until subscriber lifetime value and actual acquisition costs can be evaluated.

## Release and verification
Fill in production/PR references only after tests, publication and live verification succeed. Backend must be deployed before the site starts emitting guide events. Deploy neither secrets nor local research artifacts. Google and reviewer outcomes remain outside ARVIO's control.
