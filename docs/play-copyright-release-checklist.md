# Copyright And Store Compliance Review

Review date: 2026-09-07. This is an engineering review, not legal clearance.

## Implemented

- Both Android distributions open manual trailers through an official YouTube watch URL. Direct stream extraction, audio/video splitting and extraction caches were removed. Background/card YouTube autoplay controls are no longer offered. Saved settings are retained for schema compatibility, not used to bypass this restriction.
- The web app retains the official YouTube embed. Its ARVIO toolbar occupies a separate layout row and does not obscure the player. Referrer identification is preserved.
- About & Credits includes the approved, unmodified TMDB logo and required notice in Android and the web app. Attribution assets are bundled, not fetched at runtime.
- Source onboarding directs users to their own authorized services, not an unvetted addon directory. Compatibility with user-provided addons is not removed.
- Copyright reports about ARVIO-controlled assets and links have a published contact process. README language no longer claims that not hosting movies eliminates responsibility.

## Still Required Before Claiming Clearance

1. Obtain or verify the applicable TMDB commercial agreement for revenue-generating use, particularly the paid hosted web app. An API key and attribution alone do not establish that agreement.
2. Replace Play listing and promotional screenshots containing commercial film/TV artwork or logos unless permission for that promotional use is documented. Inventory includes the public Play mobile home/detail shots, `screenshots/`, and marketing `assets/` screenshots of home, details, player and live TV. Do not publish composited fake screenshots as actual app captures.
3. Use original media or a documented licence allowing the intended commercial demonstration use. Capture the real app with that content, including avatars, EPG, subtitle text and background imagery. Keep the asset source, licence version, attribution and permission record.
4. Upload the approved screenshots and current source-policy copy in Play Console. A repository change cannot update an existing Play Store listing or installed APK.
5. Have qualified counsel review the app's actual integrations and marketing if a complaint arrives or a commercial partnership changes its use. Obtain the full notice before assuming which aspect of another app caused its suspension.

Do not blacklist the six mentioned IMDb titles as a substitute for this review. Their presence in a metadata catalog is not proof that the app hosts or distributes their videos.

## TMDB Logo Provenance

Downloaded without modification from TMDB's approved logo page on the review date:

- https://www.themoviedb.org/about/logos-attribution
- https://www.themoviedb.org/assets/v4/logos/v2/blue_short-8e7b30f73a4020692ccca9c88bafe5dcb6f8a62a4c6bc55cd9ba82bb2cd95f6c.svg

The same bytes are bundled in Android, web and the credits website. Use is for required attribution, not endorsement. TMDB's brand-use guidance applies; do not treat the logo as owned by ARVIO or generally relicensed under this repository's licence.

## References

- Google Play intellectual property: https://support.google.com/googleplay/android-developer/answer/9888072?hl=en
- TMDB attribution and commercial use: https://developer.themoviedb.org/docs/faq
- YouTube player requirements: https://developers.google.com/youtube/terms/required-minimum-functionality
- YouTube developer policies: https://developers.google.com/youtube/terms/developer-policies

## Suggested Store Description Addition

ARVIO is a media hub for your own media and services you are authorized to use. It does not include a film, TV or live-channel subscription. You configure compatible sources yourself. Catalog information and artwork do not grant viewing rights. Copyright reports concerning ARVIO-controlled material can be sent to arvio.app@gmail.com.

## Verification

- Play-flavour debug app, unit tests and instrumentation APK compile successfully. This is not a signed production release or a Play Console submission.
- YouTube URL validation: two passing unit tests, including malformed keys and URL-injection inputs.
- Web suite: 40 passing tests; TypeScript and production build pass.
- Browser checks at 1440x900 and 390x844: credits logo loads, text does not overflow, onboarding opens configuration, and the YouTube toolbar does not overlap its iframe. These are layout checks, not proof of third-party YouTube playback availability.
- Android credits dialog passes display/dismiss checks in portrait and landscape on an emulator; captures are in the local `artifacts/copyright-ui/` directory. These are verification screenshots, not a replacement set for the existing store listing.
- Owner confirmed that commercial TMDB/artwork permission is not yet held. Do not mark licensing or screenshot clearance as complete.

## Draft TMDB Enquiry (Not Sent)

Subject: Commercial API and artwork licensing enquiry for ARVIO

Hello TMDB team,

I maintain ARVIO in the Netherlands. It is an open-source Android media hub with a free Android app and an optional paid, hosted browser app. We use TMDB metadata and artwork for browsing movies and TV shows; users configure their own playback sources.

Could you advise on the commercial agreement required for this setup and provide pricing? Please also clarify what rights your agreement grants for displaying metadata artwork in the product and in website/Google Play screenshots, and which permissions must instead be obtained directly from the relevant rights holders. We can provide current usage and request statistics for your assessment.

Thank you,
Arvind, ARVIO
