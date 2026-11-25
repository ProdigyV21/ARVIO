# ARVIO promo film

The 34-second film on [arvio.tv](https://arvio.tv/#demo) and at the top of the main README, built with [Remotion](https://www.remotion.dev/).

```bash
cd promo
npm ci
npm run dev      # Remotion Studio: preview and tweak every scene
npm run render   # out/arvio-promo-1080p.mp4 (1920x1080, 30 fps, with music)
```

If Remotion can't download its own Chrome Headless Shell, point it at a local one with `REMOTION_CHROME=/path/to/chrome-headless-shell`.

## Layout

- `src/Promo.tsx`: scene order, durations, transitions and the soundtrack.
- `src/scenes.tsx`: the scenes (intro, TV, details, phone/tablet, music, AI subtitles, library, sources, every screen, end card). Each one is also its own composition under **Scenes** in the Studio.
- `src/devices.tsx`: TV, laptop, tablet and phone frames drawn in CSS.
- `src/theme.ts`: brand colors and fonts, plus `SCREENS`, the list of captures the film uses.
- `public/screens/`: real captures of ARVIO 2.4 on Android TV, phone and tablet emulators and ARVIO Web, with a local demo profile and no accounts connected. Replace a file (same name) to refresh the footage.
- `public/audio/soundtrack.mp3`: original instrumental soundtrack made for this film.

## Publishing

The site uses these encodes in `netlify-arvio-tv-site/assets/promo/`:

| File | Use |
| --- | --- |
| `arvio-promo-1080p.mp4` | Download (1080p, with music) |
| `arvio-tour-720p.mp4` | 720p download |
| `arvio-tour-web.mp4` | Homepage and Premium page player, must stay under 5 MB |
| `arvio-tour-poster.jpg`, `arvio-premium-poster.webp` | Posters |
| `arvio-tour-preview.gif` | Looping preview at the top of the GitHub README |

Remotion has its own license: free for individuals and companies of up to three people. See [remotion.dev/license](https://www.remotion.dev/license).
