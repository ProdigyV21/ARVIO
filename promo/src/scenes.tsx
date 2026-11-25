import React from "react";
import {
  AbsoluteFill,
  Img,
  interpolate,
  spring,
  staticFile,
  useCurrentFrame,
  useVideoConfig,
} from "remotion";
import { Laptop, Phone, Tablet, TV } from "./devices";
import {
  Backdrop,
  Body,
  clamp,
  ease,
  easeInOut,
  Headline,
  Kicker,
  Pill,
  Screen,
  useProgress,
} from "./primitives";
import { C, DISPLAY, SCREENS, TEXT } from "./theme";

const W = 1920;

/* 1 ─ Logo reveal */
export const Intro: React.FC = () => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();
  const pop = spring({
    frame: frame - 6,
    fps,
    config: { damping: 14, stiffness: 120 },
  });
  const ring = interpolate(frame, [6, 60], [0, 1], { ...clamp, easing: ease });
  const word = useProgress(26, 30);
  const tag = useProgress(50, 24);
  return (
    <AbsoluteFill>
      <Backdrop intensity={0.7 + ring * 0.6} />
      <AbsoluteFill
        style={{
          alignItems: "center",
          justifyContent: "center",
          flexDirection: "column",
        }}
      >
        <div
          style={{
            position: "relative",
            width: 260,
            height: 220,
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
          }}
        >
          <div
            style={{
              position: "absolute",
              width: 520,
              height: 520,
              borderRadius: 999,
              border: `2px solid rgba(158,251,213,${0.35 * (1 - ring)})`,
              scale: String(0.3 + ring * 1.2),
            }}
          />
          <div
            style={{
              position: "absolute",
              width: 420,
              height: 420,
              borderRadius: 999,
              background:
                "radial-gradient(circle, rgba(158,251,213,0.28), transparent 65%)",
              opacity: ring,
            }}
          />
          <Img
            src={staticFile("brand/arvio-mark.png")}
            style={{
              width: 230,
              scale: String(pop),
              filter: "drop-shadow(0 0 40px rgba(158,251,213,0.35))",
            }}
          />
        </div>
        <div
          style={{
            marginTop: 56,
            clipPath: `inset(0 ${(1 - word) * 100}% 0 0)`,
          }}
        >
          <Img
            src={staticFile("brand/arvio-wordmark.svg")}
            style={{ width: 520 }}
          />
        </div>
        <div
          style={{
            marginTop: 44,
            fontFamily: TEXT,
            fontSize: 38,
            letterSpacing: "0.04em",
            color: C.soft,
            opacity: tag,
            translate: `0 ${(1 - tag) * 16}px`,
          }}
        >
          Your media, together.
        </div>
      </AbsoluteFill>
    </AbsoluteFill>
  );
};

/* 2 ─ Full-bleed home with headline */
export const Hook: React.FC = () => {
  const frame = useCurrentFrame();
  const { durationInFrames } = useVideoConfig();
  const reveal = interpolate(frame, [0, 26], [1.18, 1], {
    ...clamp,
    easing: ease,
  });
  const pan = interpolate(frame, [0, durationInFrames], [0, -40], clamp);
  return (
    <AbsoluteFill style={{ background: C.bg }}>
      <AbsoluteFill
        style={{
          scale: String(reveal),
          translate: `${pan}px 0`,
          maskImage:
            "linear-gradient(90deg, transparent 0%, transparent 30%, black 68%)",
        }}
      >
        <Screen
          src={SCREENS.tvDetails}
          zoom={[1.32, 1.42]}
          position="center top"
          style={{ transformOrigin: "100% 48%" }}
        />
      </AbsoluteFill>
      <AbsoluteFill
        style={{
          background:
            "linear-gradient(90deg, rgba(5,7,7,0.98) 0%, rgba(5,7,7,0.92) 38%, rgba(5,7,7,0.3) 62%, rgba(5,7,7,0) 100%), linear-gradient(0deg, rgba(5,7,7,1) 0%, rgba(5,7,7,0.85) 22%, transparent 48%)",
        }}
      />
      <AbsoluteFill
        style={{ padding: "0 140px", justifyContent: "center", gap: 34 }}
      >
        <Kicker delay={6}>One app for all of it</Kicker>
        <Headline
          text={"Everything you watch.\nOne *home.*"}
          delay={10}
          size={112}
        />
        <Body delay={30} style={{ maxWidth: 720 }}>
          Your servers, live TV, add-ons and watchlists, together in one
          beautiful app.
        </Body>
      </AbsoluteFill>
    </AbsoluteFill>
  );
};

/* 3 ─ TV */
export const BigScreen: React.FC = () => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();
  const rise = spring({
    frame: frame - 8,
    fps,
    config: { damping: 200 },
    durationInFrames: 40,
  });
  const tilt = interpolate(frame, [0, 180], [10, 0], {
    ...clamp,
    easing: easeInOut,
  });
  return (
    <AbsoluteFill>
      <Backdrop />
      <AbsoluteFill style={{ alignItems: "center", paddingTop: 92 }}>
        <Kicker delay={0}>Android TV · Google TV · Fire TV</Kicker>
        <div style={{ height: 26 }} />
        <Headline
          text="Made for the *big screen.*"
          align="center"
          size={92}
          delay={4}
        />
      </AbsoluteFill>
      <AbsoluteFill
        style={{
          alignItems: "center",
          justifyContent: "flex-end",
          perspective: 2400,
        }}
      >
        <div
          style={{
            translate: `0 ${(1 - rise) * 500 + 70}px`,
            rotate: `x ${tilt}deg`,
          }}
        >
          <TV width={1380} stand={false}>
            <Screen src={SCREENS.tvClipHome} trimBefore={75} />
          </TV>
        </div>
      </AbsoluteFill>
    </AbsoluteFill>
  );
};

/* 4 ─ Details, full bleed */
export const Details: React.FC = () => {
  const frame = useCurrentFrame();
  const wipe = interpolate(frame, [0, 22], [100, 0], {
    ...clamp,
    easing: ease,
  });
  return (
    <AbsoluteFill style={{ background: C.bg }}>
      <AbsoluteFill style={{ clipPath: `inset(0 0 0 ${wipe}%)` }}>
        <Screen
          src={SCREENS.tvDetailsSeries}
          zoom={[1.1, 1.0]}
          position="right top"
        />
      </AbsoluteFill>
      <AbsoluteFill
        style={{
          background:
            "linear-gradient(0deg, rgba(5,7,7,1) 0%, rgba(5,7,7,0.92) 30%, rgba(5,7,7,0.4) 55%, rgba(5,7,7,0) 75%), linear-gradient(90deg, rgba(5,7,7,0.6) 0%, transparent 50%)",
        }}
      />
      <AbsoluteFill
        style={{
          padding: "0 140px 110px",
          justifyContent: "flex-end",
          gap: 26,
        }}
      >
        <Kicker delay={10}>Discover</Kicker>
        <Headline
          text="Every title, *beautifully* told."
          delay={14}
          size={96}
        />
        <Body delay={32} style={{ maxWidth: 1100 }}>
          Trailers, cast, seasons and ratings, plus every source you have for
          it, in one place.
        </Body>
      </AbsoluteFill>
    </AbsoluteFill>
  );
};

/* 5 ─ Music */
export const Music: React.FC = () => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();
  const phones = [
    { src: SCREENS.musicAlbum, x: -330, y: 70, r: -8, d: 10, w: 330 },
    { src: SCREENS.musicPlayer, x: 330, y: 70, r: 8, d: 16, w: 330 },
    { src: SCREENS.musicCharts, x: 0, y: 0, r: 0, d: 4, w: 380 },
  ];
  const drift = interpolate(frame, [0, 210], [20, -20], clamp);
  return (
    <AbsoluteFill>
      <Backdrop hue="mix" />
      <AbsoluteFill
        style={{
          padding: "0 0 0 140px",
          justifyContent: "center",
          gap: 30,
          width: 820,
        }}
      >
        <Kicker delay={0}>New · Music</Kicker>
        <Headline text={"Your music,\n*too.*"} delay={4} size={112} />
        <Body delay={24}>
          Daily charts and hundreds of playlists for your language, played from
          your own sources.
        </Body>
        <div
          style={{ display: "flex", gap: 14, flexWrap: "wrap", marginTop: 8 }}
        >
          <Pill delay={40}>Charts</Pill>
          <Pill delay={44}>Playlists</Pill>
          <Pill delay={48}>Your library</Pill>
        </div>
      </AbsoluteFill>
      <AbsoluteFill
        style={{
          left: 900,
          width: 1020,
          alignItems: "center",
          justifyContent: "center",
        }}
      >
        {phones.map((p, i) => {
          const s = spring({
            frame: frame - p.d,
            fps,
            config: { damping: 18, stiffness: 90 },
          });
          return (
            <div
              key={i}
              style={{
                position: "absolute",
                left: "50%",
                top: "50%",
                marginLeft: -p.w / 2,
                marginTop: -(p.w * 0.93 * (2553 / 1179) + p.w * 0.07) / 2,
                translate: `${p.x}px ${p.y + (1 - s) * 900 + drift * (i === 2 ? 1 : 0.5)}px`,
                rotate: `${p.r * s}deg`,
              }}
            >
              <Phone width={p.w} aspect={2553 / 1179}>
                <Screen src={p.src} />
              </Phone>
            </div>
          );
        })}
      </AbsoluteFill>
    </AbsoluteFill>
  );
};

/* 5b ─ Phone and tablet */
export const Touch: React.FC = () => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();
  const t = spring({
    frame: frame - 4,
    fps,
    config: { damping: 200 },
    durationInFrames: 34,
  });
  const p = spring({
    frame: frame - 16,
    fps,
    config: { damping: 18, stiffness: 90 },
  });
  const drift = interpolate(frame, [0, 150], [10, -10], clamp);
  return (
    <AbsoluteFill>
      <Backdrop hue="mix" />
      <AbsoluteFill
        style={{
          padding: "0 0 0 140px",
          justifyContent: "center",
          gap: 28,
          width: 760,
        }}
      >
        <Kicker delay={4}>Phone · Tablet</Kicker>
        <Headline text={"Just as good\nin your *hands.*"} delay={8} size={96} />
        <Body delay={26}>
          A touch layout for phones and tablets, with the same home, profiles
          and progress.
        </Body>
      </AbsoluteFill>
      <div
        style={{
          position: "absolute",
          left: 860,
          top: 250,
          translate: `${(1 - t) * 900}px ${drift}px`,
        }}
      >
        <Tablet width={880} aspect={1200 / 1920}>
          <Screen src={SCREENS.tabletClip} />
        </Tablet>
      </div>
      <div
        style={{
          position: "absolute",
          left: 1610,
          top: 400,
          translate: `0 ${(1 - p) * 900 - drift}px`,
          rotate: `${4 * p}deg`,
        }}
      >
        <Phone width={270}>
          <Screen src={SCREENS.phoneClip} />
        </Phone>
      </div>
    </AbsoluteFill>
  );
};

/* 6 ─ Library & calendar */
export const Library: React.FC = () => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();
  const a = spring({
    frame: frame - 4,
    fps,
    config: { damping: 200 },
    durationInFrames: 36,
  });
  const b = spring({
    frame: frame - 18,
    fps,
    config: { damping: 18, stiffness: 90 },
  });
  const drift = interpolate(frame, [0, 165], [0, -24], clamp);
  return (
    <AbsoluteFill>
      <Backdrop />
      <AbsoluteFill style={{ perspective: 2200 }}>
        <div
          style={{
            position: "absolute",
            right: -60,
            top: 120,
            translate: `${(1 - a) * 700}px ${drift}px`,
            rotate: `y ${-14 + a * 4}deg`,
          }}
        >
          <TV width={1180} stand={false}>
            <Screen src={SCREENS.tvLibrary} zoom={[1, 1.05]} />
          </TV>
        </div>
        <div
          style={{
            position: "absolute",
            right: 520,
            top: 600,
            translate: `0 ${(1 - b) * 700 - drift}px`,
            rotate: `${-3 * b}deg`,
          }}
        >
          <TV width={720} stand={false}>
            <Screen src={SCREENS.tvCalendar} />
          </TV>
        </div>
      </AbsoluteFill>
      <AbsoluteFill
        style={{
          background:
            "linear-gradient(90deg, rgba(5,7,7,0.96) 0%, rgba(5,7,7,0.75) 30%, transparent 52%)",
        }}
      />
      <AbsoluteFill
        style={{
          padding: "0 140px",
          justifyContent: "center",
          gap: 28,
          width: 860,
        }}
      >
        <Kicker delay={6}>Library &amp; calendar</Kicker>
        <Headline text={"Never miss a\n*new episode.*"} delay={10} size={96} />
        <Body delay={28}>
          Watchlists, your home servers and a calendar of what airs next, synced
          with Trakt and SIMKL.
        </Body>
      </AbsoluteFill>
    </AbsoluteFill>
  );
};

/* 7 ─ Your sources */
const SOURCES = [
  "Plex",
  "Jellyfin",
  "Emby",
  "Trakt",
  "SIMKL",
  "MDBList",
  "Add-ons",
  "Debrid",
  "Live TV + EPG",
  "Telegram",
  "Music servers",
  "AI subtitles",
];

export const Sources: React.FC = () => {
  const frame = useCurrentFrame();
  return (
    <AbsoluteFill>
      <Backdrop intensity={1.2} />
      <AbsoluteFill
        style={{ alignItems: "center", justifyContent: "center", gap: 40 }}
      >
        <Kicker delay={0}>Bring your own</Kicker>
        <Headline
          text="Connect what you *already have.*"
          align="center"
          size={96}
          delay={4}
        />
        <div
          style={{
            display: "flex",
            flexWrap: "wrap",
            justifyContent: "center",
            gap: 18,
            maxWidth: 1360,
            marginTop: 20,
          }}
        >
          {SOURCES.map((s, i) => (
            <Pill
              key={s}
              delay={24 + i * 3}
              strong={
                i === Math.floor(frame / 6) % SOURCES.length && frame > 70
              }
            >
              {s}
            </Pill>
          ))}
        </div>
        <Body
          delay={60}
          style={{
            fontSize: 28,
            color: C.muted,
            marginTop: 24,
            textAlign: "center",
          }}
        >
          ARVIO includes no movies, shows or channels. You connect sources you
          are authorized to use.
        </Body>
      </AbsoluteFill>
    </AbsoluteFill>
  );
};

/* 8 ─ Every screen */
export const Everywhere: React.FC = () => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();
  const sp = (d: number) =>
    spring({ frame: frame - d, fps, config: { damping: 20, stiffness: 80 } });
  const l = sp(8);
  const t = sp(16);
  const p = sp(24);
  const v = sp(0);
  const push = interpolate(frame, [0, 180], [0.96, 1.02], clamp);
  return (
    <AbsoluteFill>
      <Backdrop hue="mix" />
      <AbsoluteFill style={{ alignItems: "center", paddingTop: 84 }}>
        <Kicker delay={0}>TV · Phone · Tablet · Web</Kicker>
        <div style={{ height: 24 }} />
        <Headline
          text="One account. *Every screen.*"
          align="center"
          size={92}
          delay={4}
        />
      </AbsoluteFill>
      <AbsoluteFill style={{ scale: String(push) }}>
        <div
          style={{
            position: "absolute",
            left: 70,
            top: 330,
            opacity: v,
            translate: `${(1 - v) * -200}px 0`,
          }}
        >
          <TV width={680}>
            <Screen src={SCREENS.tvDetails} />
          </TV>
        </div>
        <div
          style={{
            position: "absolute",
            left: 600,
            top: 400,
            translate: `0 ${(1 - l) * 700}px`,
          }}
        >
          <Laptop width={820}>
            <Screen src={SCREENS.webDetails} />
          </Laptop>
        </div>
        <div
          style={{
            position: "absolute",
            left: 1390,
            top: 500,
            translate: `${(1 - t) * 600}px 0`,
          }}
        >
          <Tablet width={470}>
            <Screen src={SCREENS.tabletDetails} />
          </Tablet>
        </div>
        <div
          style={{
            position: "absolute",
            left: 1650,
            top: 620,
            translate: `0 ${(1 - p) * 600}px`,
          }}
        >
          <Phone width={200}>
            <Screen src={SCREENS.phoneDetails} />
          </Phone>
        </div>
      </AbsoluteFill>
      <AbsoluteFill
        style={{
          justifyContent: "flex-end",
          alignItems: "center",
          paddingBottom: 56,
        }}
      >
        <Body delay={40} style={{ fontSize: 30, color: C.soft }}>
          Profiles, watchlists and progress stay in sync.
        </Body>
      </AbsoluteFill>
    </AbsoluteFill>
  );
};

/* 9 ─ End card */
export const Outro: React.FC = () => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();
  const logo = spring({ frame, fps, config: { damping: 14 } });
  const url = useProgress(40, 26);
  const glow = 0.5 + 0.5 * Math.sin(frame / 14);
  return (
    <AbsoluteFill>
      <Backdrop intensity={1.4} />
      <AbsoluteFill
        style={{ alignItems: "center", justifyContent: "center", gap: 36 }}
      >
        <div
          style={{
            display: "flex",
            alignItems: "center",
            gap: 34,
            scale: String(0.8 + 0.2 * logo),
            opacity: logo,
          }}
        >
          <Img
            src={staticFile("brand/arvio-mark.png")}
            style={{ width: 130 }}
          />
          <Img
            src={staticFile("brand/arvio-wordmark.svg")}
            style={{ width: 420 }}
          />
        </div>
        <Headline
          text="Free and *open source.*"
          align="center"
          size={84}
          delay={10}
        />
        <div style={{ display: "flex", gap: 18, marginTop: 6 }}>
          <Pill delay={30}>Google Play</Pill>
          <Pill delay={34}>GitHub</Pill>
          <Pill delay={38}>Web</Pill>
        </div>
        <div
          style={{
            marginTop: 26,
            fontFamily: DISPLAY,
            fontWeight: 650,
            fontSize: 76,
            letterSpacing: "-0.02em",
            color: C.mint,
            opacity: url,
            translate: `0 ${(1 - url) * 20}px`,
            textShadow: `0 0 ${30 + glow * 30}px rgba(158,251,213,0.35)`,
          }}
        >
          arvio.tv
        </div>
      </AbsoluteFill>
      <AbsoluteFill
        style={{
          justifyContent: "flex-end",
          alignItems: "center",
          paddingBottom: 48,
        }}
      >
        <div
          style={{
            fontFamily: TEXT,
            fontSize: 20,
            color: C.muted,
            opacity: url,
          }}
        >
          No content included. Artwork shown for discovery. Playback depends on
          your sources and device.
        </div>
      </AbsoluteFill>
    </AbsoluteFill>
  );
};

export const SCENE_LIST = { W };
