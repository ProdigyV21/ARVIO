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
    frame,
    fps,
    config: { damping: 13, stiffness: 180 },
  });
  const ring = interpolate(frame, [0, 34], [0, 1], { ...clamp, easing: ease });
  const word = useProgress(5, 14);
  const tag = useProgress(12, 12);
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

/* 3 ─ TV */
export const BigScreen: React.FC = () => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();
  const rise = spring({
    frame,
    fps,
    config: { damping: 200 },
    durationInFrames: 22,
  });
  const tilt = interpolate(frame, [0, 120], [8, 0], {
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
          text="Everything you watch. *One home.*"
          align="center"
          size={92}
          delay={2}
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
            translate: `0 ${(1 - rise) * 260 + 70}px`,
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

/* 5c ─ AI subtitles */
const LANGS = [
  { name: "English", line: "I've been waiting for this moment." },
  { name: "Nederlands", line: "Ik heb op dit moment gewacht." },
  { name: "Español", line: "He esperado este momento." },
  { name: "Deutsch", line: "Ich habe auf diesen Moment gewartet." },
  { name: "Français", line: "J'attendais ce moment." },
];

export const Subtitles: React.FC = () => {
  const frame = useCurrentFrame();
  const { fps, durationInFrames } = useVideoConfig();
  const enter = spring({
    frame,
    fps,
    config: { damping: 200 },
    durationInFrames: 22,
  });
  const step = Math.floor((durationInFrames - 20) / LANGS.length);
  const idx = Math.min(
    LANGS.length - 1,
    Math.max(0, Math.floor((frame - 14) / step)),
  );
  const local = frame - 14 - idx * step;
  const lineIn = interpolate(local, [0, 6], [0, 1], clamp);
  const progress = interpolate(
    frame,
    [0, durationInFrames],
    [0.34, 0.42],
    clamp,
  );
  const bars = [0.5, 0.9, 0.6, 1, 0.7, 0.4, 0.85, 0.55];
  return (
    <AbsoluteFill>
      <Backdrop />
      <AbsoluteFill style={{ alignItems: "center", paddingTop: 70 }}>
        <Kicker delay={0}>AI subtitles</Kicker>
        <div style={{ height: 22 }} />
        <Headline
          text="Every word. *Any language.*"
          align="center"
          size={92}
          delay={2}
        />
      </AbsoluteFill>
      <div
        style={{
          position: "absolute",
          left: 130,
          top: 360,
          width: 1220,
          borderRadius: 26,
          overflow: "hidden",
          background: "#0b0f0e",
          boxShadow:
            "0 60px 140px rgba(0,0,0,0.6), 0 0 0 1px rgba(255,255,255,0.08)",
          translate: `0 ${(1 - enter) * 120}px`,
          opacity: enter,
        }}
      >
        <div style={{ position: "relative", height: 1220 * (304 / 826) }}>
          <Screen
            src={SCREENS.subtitlesPlayer}
            zoom={[1.2, 1.25]}
            position="center"
            style={{ transformOrigin: "100% 100%" }}
          />
          <div
            style={{
              position: "absolute",
              left: 24,
              top: 22,
              display: "flex",
              alignItems: "center",
              gap: 12,
              padding: "10px 18px 10px 12px",
              borderRadius: 99,
              background: "rgba(10,14,13,0.92)",
              color: C.ink,
              fontFamily: TEXT,
              fontSize: 24,
              fontWeight: 600,
            }}
          >
            <span
              style={{
                background: C.mint,
                color: "#04150f",
                borderRadius: 7,
                padding: "2px 8px",
                fontSize: 20,
                fontWeight: 800,
              }}
            >
              AI
            </span>
            Subtitles · {LANGS[idx].name}
          </div>
          <div
            style={{
              position: "absolute",
              left: 0,
              right: 0,
              bottom: 34,
              textAlign: "center",
              fontFamily: TEXT,
              fontWeight: 600,
              fontSize: 46,
              color: "#fff",
              textShadow: "0 2px 12px rgba(0,0,0,0.85)",
              opacity: lineIn,
              translate: `0 ${(1 - lineIn) * 10}px`,
            }}
          >
            {LANGS[idx].line}
          </div>
        </div>
        <div
          style={{
            display: "flex",
            alignItems: "center",
            gap: 26,
            padding: "22px 30px",
          }}
        >
          <div
            style={{
              width: 0,
              height: 0,
              borderLeft: "22px solid #fff",
              borderTop: "13px solid transparent",
              borderBottom: "13px solid transparent",
            }}
          />
          <div
            style={{
              flex: 1,
              height: 6,
              borderRadius: 9,
              background: "rgba(255,255,255,0.14)",
              position: "relative",
            }}
          >
            <div
              style={{
                width: `${progress * 100}%`,
                height: "100%",
                borderRadius: 9,
                background: `linear-gradient(90deg, ${C.mintDeep}, ${C.mint})`,
              }}
            />
          </div>
          <div style={{ fontFamily: TEXT, fontSize: 24, color: C.soft }}>
            0:38:14 / 1:49:04
          </div>
          <div
            style={{
              fontFamily: TEXT,
              fontSize: 20,
              fontWeight: 700,
              color: C.mint,
              border: `2px solid ${C.mint}`,
              borderRadius: 6,
              padding: "1px 8px",
            }}
          >
            CC
          </div>
        </div>
      </div>
      <div
        style={{
          position: "absolute",
          left: 1400,
          top: 360,
          width: 400,
          padding: 26,
          borderRadius: 26,
          background: "rgba(13,19,18,0.92)",
          border: "1px solid rgba(255,255,255,0.08)",
          translate: `${(1 - enter) * 200}px 0`,
          opacity: enter,
          fontFamily: TEXT,
        }}
      >
        <div
          style={{
            fontSize: 20,
            letterSpacing: "0.24em",
            color: C.muted,
            fontWeight: 600,
            marginBottom: 16,
          }}
        >
          TRANSLATE TO
        </div>
        {LANGS.map((l, i) => (
          <div
            key={l.name}
            style={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              padding: "14px 18px",
              marginBottom: 8,
              borderRadius: 12,
              fontSize: 30,
              color: i === idx ? C.ink : C.muted,
              background: i === idx ? "rgba(158,251,213,0.10)" : "transparent",
              border: `1px solid ${i === idx ? "rgba(158,251,213,0.45)" : "transparent"}`,
            }}
          >
            {l.name}
            {i === idx && (
              <span
                style={{
                  width: 10,
                  height: 10,
                  borderRadius: 99,
                  background: C.mint,
                }}
              />
            )}
          </div>
        ))}
        <div
          style={{
            borderTop: "1px solid rgba(255,255,255,0.08)",
            marginTop: 14,
            paddingTop: 18,
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <div>
            <div style={{ fontSize: 24, color: C.ink, fontWeight: 600 }}>
              Sync by hearing
            </div>
            <div style={{ fontSize: 18, color: C.muted, marginTop: 4 }}>
              Times every line to the audio
            </div>
          </div>
          <div
            style={{
              display: "flex",
              gap: 4,
              alignItems: "center",
              height: 40,
            }}
          >
            {bars.map((b, i) => (
              <span
                key={i}
                style={{
                  width: 5,
                  borderRadius: 9,
                  background: C.mint,
                  height:
                    8 +
                    30 * b * (0.55 + 0.45 * Math.abs(Math.sin(frame / 4 + i))),
                }}
              />
            ))}
          </div>
        </div>
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
