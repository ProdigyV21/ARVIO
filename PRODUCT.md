# Product

<!-- impeccable:product-schema 1 -->

## Platform

web

## Users

- **Android TV and Android phone owners** looking for one media hub for their own home servers (Jellyfin, Plex, Emby), IPTV, addons and Trakt/Simkl. They come mostly from search and guide pages and want the free app (APK or Google Play).
- **The same people on other screens.** Many Android users also have an iPad, iPhone or laptop. On those devices, hosted ARVIO Web gives them their existing ARVIO setup (profiles, addons, watch progress via ARVIO Cloud) with no server to run.
- **iPhone, iPad, Mac and Windows visitors without an Android device**, who can only use ARVIO through the browser.

## Product Purpose

ARVIO brings libraries, live TV and discovery from the user's own sources into one interface that feels the same on TV, phone, tablet and browser. Success on the website means the visitor takes the right next step for their situation: get the free app on Android or TV, or start ARVIO Web (paid hosting) on other screens.

## Positioning

- Open source (Apache-2.0), with a free Android and TV app and free self-hosting.
- Hosted ARVIO Web is the only paid part. It is justified by hosting costs: no server to maintain, and the same setup instantly on iPhone, iPad and laptop.
- One account syncs profiles, watchlists, progress and addons across all screens.

## Operating Context

- Traffic is mostly from Android phones and TVs, then Windows, iPhone and Mac. Spanish, Brazilian Portuguese and French audiences are significant, and the site is localized into many languages.
- Payment runs through Ko-fi: $2.99/month or $20 for 12 months, with a regional $10 pass in eligible countries. Sign-in runs via auth.arvio.tv (ARVIO Cloud). The 7-day refund applies if it doesn't work on the visitor's device.
- Hosted Web is paid-only, with no trial. Signed-in users can explore; the paywall appears at Play.
- Hosting: Netlify (arvio.tv is static, web.arvio.tv is Next.js, auth.arvio.tv runs functions). Netlify credits are limited: batch changes and deploy rarely.

## Capabilities and Constraints

- ARVIO includes no movies, series, channels or subscriptions. Always state clearly that users connect their own authorized sources.
- No Premium listing, upsell or supporter tier inside the Android/TV app. No Black Friday or other sale. No price drop.
- Code stays open source. Do not copy GPL code.
- Never name the competitor streaming app (exception: the account-migration page on Web).

## Brand Commitments

- Name ARVIO, with the existing wordmark and logo mark (netlify-arvio-tv-site/assets/arvio-wordmark.svg, arvio-mark.png).
- Dark, cinematic product with the mint/arctic accent used in the apps.

## Evidence on Hand

- Real product screenshots for TV, phone, tablet and web: netlify-arvio-tv-site/assets/screenshots-v2/, screenshots/, web/public/premium/*.png
- An 85-second tour video: netlify-arvio-tv-site/assets/promo/
- GitHub releases and download counts, plus Google Play availability.
- No collected testimonials (assumption, pending Arvind's confirmation). Do not fabricate quotes, user counts or ratings.

## Product Principles

1. Send each visitor to their right next step: the free app on Android/TV, ARVIO Web on other screens. Offer both to everyone; never hide Web from Android visitors.
2. Be honest about what ARVIO is: bring your own sources, no content included, playback depends on the source.
3. Show the real product rather than describing it.
4. Paid means hosting, not locked features: the app and self-hosting stay free.
