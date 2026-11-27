import React from "react";
import { Video } from "@remotion/media";
import {
  AbsoluteFill,
  Easing,
  Img,
  interpolate,
  staticFile,
  useCurrentFrame,
  useVideoConfig,
} from "remotion";
import { C, DISPLAY, TEXT } from "./theme";

export const ease = Easing.bezier(0.16, 1, 0.3, 1);
export const easeInOut = Easing.bezier(0.65, 0, 0.35, 1);

export const clamp = {
  extrapolateLeft: "clamp",
  extrapolateRight: "clamp",
} as const;

/** 0 → 1 over [start, start + dur] frames with the brand ease. */
export const useProgress = (start: number, dur: number, easing = ease) => {
  const frame = useCurrentFrame();
  return interpolate(frame, [start, start + dur], [0, 1], { ...clamp, easing });
};

/** Fades a scene out over its last `dur` frames. */
export const useExit = (dur = 12) => {
  const frame = useCurrentFrame();
  const { durationInFrames } = useVideoConfig();
  return interpolate(
    frame,
    [durationInFrames - dur, durationInFrames],
    [1, 0],
    clamp,
  );
};

/** A screen capture: still image or a screen recording (mp4/webm). */
export const Screen: React.FC<{
  src: string;
  style?: React.CSSProperties;
  fit?: "cover" | "contain";
  position?: string;
  zoom?: [number, number];
  trimBefore?: number;
}> = ({
  src,
  style,
  fit = "cover",
  position = "top center",
  zoom = [1, 1],
  trimBefore,
}) => {
  const frame = useCurrentFrame();
  const { durationInFrames } = useVideoConfig();
  const scale = interpolate(frame, [0, durationInFrames], zoom, clamp);
  const common: React.CSSProperties = {
    width: "100%",
    height: "100%",
    objectFit: fit,
    objectPosition: position,
    scale: String(scale),
    display: "block",
    ...style,
  };
  if (/\.(mp4|webm|mov)$/i.test(src)) {
    return (
      <Video
        src={staticFile(src)}
        muted
        trimBefore={trimBefore}
        style={common}
      />
    );
  }
  return <Img src={staticFile(src)} style={common} />;
};

/** Soft, slowly drifting brand glows over near-black. */
export const Backdrop: React.FC<{
  hue?: "mint" | "amber" | "mix";
  intensity?: number;
}> = ({ hue = "mint", intensity = 1 }) => {
  const frame = useCurrentFrame();
  const t = frame / 30;
  const a = hue === "amber" ? "255,216,138" : "158,251,213";
  const b = hue === "mix" ? "255,216,138" : "47,211,154";
  return (
    <AbsoluteFill style={{ backgroundColor: C.bg, overflow: "hidden" }}>
      <AbsoluteFill
        style={{
          background: `radial-gradient(circle at ${30 + Math.sin(t * 0.35) * 8}% ${28 + Math.cos(t * 0.3) * 6}%, rgba(${a},${0.16 * intensity}) 0%, transparent 42%),
            radial-gradient(circle at ${74 + Math.cos(t * 0.25) * 7}% ${76 + Math.sin(t * 0.3) * 6}%, rgba(${b},${0.12 * intensity}) 0%, transparent 46%)`,
        }}
      />
      <AbsoluteFill
        style={{
          backgroundImage:
            "linear-gradient(rgba(255,255,255,0.025) 1px, transparent 1px), linear-gradient(90deg, rgba(255,255,255,0.025) 1px, transparent 1px)",
          backgroundSize: "96px 96px",
          maskImage:
            "radial-gradient(ellipse at center, black 20%, transparent 75%)",
          opacity: 0.6,
        }}
      />
    </AbsoluteFill>
  );
};

/** Small uppercase label with a mint dash. */
export const Kicker: React.FC<{
  children: React.ReactNode;
  delay?: number;
  style?: React.CSSProperties;
}> = ({ children, delay = 0, style }) => {
  const p = useProgress(delay, 12);
  return (
    <div
      style={{
        display: "flex",
        alignItems: "center",
        gap: 18,
        fontFamily: TEXT,
        fontWeight: 600,
        fontSize: 24,
        letterSpacing: "0.32em",
        textTransform: "uppercase",
        color: C.mint,
        opacity: p,
        translate: `${(1 - p) * -24}px 0`,
        ...style,
      }}
    >
      <span
        style={{
          width: 44 * p,
          height: 2,
          background: C.mint,
          display: "inline-block",
        }}
      />
      {children}
    </div>
  );
};

/**
 * Headline that rises in word by word from behind a mask.
 * Wrap a word in *asterisks* to colour it mint.
 */
export const Headline: React.FC<{
  text: string;
  delay?: number;
  size?: number;
  stagger?: number;
  align?: "left" | "center";
  style?: React.CSSProperties;
}> = ({ text, delay = 0, size = 96, stagger = 2, align = "left", style }) => {
  const frame = useCurrentFrame();
  const lines = text.split("\n");
  let i = 0;
  return (
    <div
      style={{
        fontFamily: DISPLAY,
        fontWeight: 650,
        fontSize: size,
        lineHeight: 1.02,
        letterSpacing: "-0.035em",
        color: C.ink,
        textAlign: align,
        ...style,
      }}
    >
      {lines.map((line, li) => (
        <div
          key={li}
          style={{
            display: "flex",
            flexWrap: "wrap",
            justifyContent: align === "center" ? "center" : "flex-start",
            columnGap: size * 0.24,
          }}
        >
          {line.split(" ").map((word, wi) => {
            const idx = i++;
            const p = interpolate(
              frame,
              [delay + idx * stagger, delay + idx * stagger + 16],
              [0, 1],
              { ...clamp, easing: ease },
            );
            const accent = word.startsWith("*");
            const clean = word.replace(/\*/g, "");
            return (
              <span
                key={wi}
                style={{
                  display: "inline-block",
                  overflow: "hidden",
                  paddingBottom: size * 0.12,
                  marginBottom: -size * 0.12,
                }}
              >
                <span
                  style={{
                    display: "inline-block",
                    translate: `0 ${(1 - p) * 105}%`,
                    color: accent ? C.mint : undefined,
                  }}
                >
                  {clean}
                </span>
              </span>
            );
          })}
        </div>
      ))}
    </div>
  );
};

export const Body: React.FC<{
  children: React.ReactNode;
  delay?: number;
  style?: React.CSSProperties;
}> = ({ children, delay = 0, style }) => {
  const p = useProgress(delay, 14);
  return (
    <div
      style={{
        fontFamily: TEXT,
        fontSize: 34,
        lineHeight: 1.4,
        color: C.soft,
        opacity: p,
        translate: `0 ${(1 - p) * 20}px`,
        ...style,
      }}
    >
      {children}
    </div>
  );
};

export const Pill: React.FC<{
  children: React.ReactNode;
  delay?: number;
  strong?: boolean;
}> = ({ children, delay = 0, strong }) => {
  const p = useProgress(delay, 11);
  return (
    <div
      style={{
        fontFamily: TEXT,
        fontSize: 28,
        fontWeight: 550,
        padding: "14px 26px",
        borderRadius: 999,
        color: strong ? "#04150f" : C.ink,
        background: strong ? C.mint : "rgba(255,255,255,0.06)",
        border: `1px solid ${strong ? C.mint : "rgba(255,255,255,0.12)"}`,
        opacity: p,
        scale: String(0.9 + 0.1 * p),
        whiteSpace: "nowrap",
      }}
    >
      {children}
    </div>
  );
};
