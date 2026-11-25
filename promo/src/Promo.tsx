import React from "react";
import { Audio } from "@remotion/media";
import { AbsoluteFill, interpolate, staticFile } from "remotion";
import { linearTiming, TransitionSeries } from "@remotion/transitions";
import { fade } from "@remotion/transitions/fade";
import { slide } from "@remotion/transitions/slide";
import { wipe } from "@remotion/transitions/wipe";
import {
  BigScreen,
  Details,
  Everywhere,
  Intro,
  Library,
  Music,
  Outro,
  Sources,
  Subtitles,
  Touch,
} from "./scenes";
import { loadBrandFonts } from "./theme";
import { easeInOut } from "./primitives";

loadBrandFonts();

export const T = 12;
const timing = linearTiming({ durationInFrames: T, easing: easeInOut });

export const SCENES = [
  { id: "Intro", C: Intro, d: 48 },
  { id: "BigScreen", C: BigScreen, d: 120 },
  { id: "Details", C: Details, d: 100 },
  { id: "Touch", C: Touch, d: 105 },
  { id: "Music", C: Music, d: 125 },
  { id: "Subtitles", C: Subtitles, d: 140 },
  { id: "Library", C: Library, d: 105 },
  { id: "Sources", C: Sources, d: 100 },
  { id: "Everywhere", C: Everywhere, d: 120 },
  { id: "Outro", C: Outro, d: 150 },
] as const;

export const PROMO_DURATION =
  SCENES.reduce((s, x) => s + x.d, 0) - T * (SCENES.length - 1);

// eslint-disable-next-line @typescript-eslint/no-explicit-any
const transitions: any[] = [
  fade(),
  slide({ direction: "from-bottom" }),
  fade(),
  slide({ direction: "from-right" }),
  fade(),
  wipe({ direction: "from-left" }),
  fade(),
  slide({ direction: "from-bottom" }),
  fade(),
];

export const Promo: React.FC = () => (
  <AbsoluteFill>
    <Audio
      src={staticFile("audio/soundtrack.mp3")}
      trimBefore={Math.round(45 * 30 - PROMO_DURATION)}
      volume={(f) =>
        interpolate(
          f,
          [0, 10, PROMO_DURATION - 45, PROMO_DURATION - 1],
          [0, 0.9, 0.9, 0],
          { extrapolateLeft: "clamp", extrapolateRight: "clamp" },
        )
      }
    />
    <TransitionSeries>
      {SCENES.map((s, i) => (
        <React.Fragment key={s.id}>
          <TransitionSeries.Sequence name={s.id} durationInFrames={s.d}>
            <s.C />
          </TransitionSeries.Sequence>
          {i < SCENES.length - 1 && (
            <TransitionSeries.Transition
              presentation={transitions[i]}
              timing={timing}
            />
          )}
        </React.Fragment>
      ))}
    </TransitionSeries>
  </AbsoluteFill>
);
