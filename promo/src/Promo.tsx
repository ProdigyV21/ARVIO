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
  Outro,
  Subtitles,
} from "./scenes";
import {
  HomeServers,
  LiveTv,
  MusicEverywhere,
  News,
  OwnSources,
  Sports,
} from "./scenes2";
import { loadBrandFonts } from "./theme";
import { easeInOut } from "./primitives";

loadBrandFonts();

export const T = 12;
const timing = linearTiming({ durationInFrames: T, easing: easeInOut });

export const SCENES = [
  { id: "Intro", C: Intro, d: 45 },
  { id: "BigScreen", C: BigScreen, d: 105 },
  { id: "Details", C: Details, d: 85 },
  { id: "LiveTv", C: LiveTv, d: 130 },
  { id: "Sports", C: Sports, d: 125 },
  { id: "Music", C: MusicEverywhere, d: 125 },
  { id: "Subtitles", C: Subtitles, d: 135 },
  { id: "News", C: News, d: 100 },
  { id: "HomeServers", C: HomeServers, d: 110 },
  { id: "OwnSources", C: OwnSources, d: 115 },
  { id: "Everywhere", C: Everywhere, d: 110 },
  { id: "Outro", C: Outro, d: 135 },
] as const;

export const PROMO_DURATION =
  SCENES.reduce((s, x) => s + x.d, 0) - T * (SCENES.length - 1);

// eslint-disable-next-line @typescript-eslint/no-explicit-any
const transitions: any[] = [
  fade(),
  slide({ direction: "from-bottom" }),
  wipe({ direction: "from-left" }),
  slide({ direction: "from-right" }),
  fade(),
  wipe({ direction: "from-left" }),
  slide({ direction: "from-bottom" }),
  fade(),
  slide({ direction: "from-right" }),
  fade(),
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
