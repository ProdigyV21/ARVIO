import React from "react";
import {
  AbsoluteFill,
  interpolate,
  spring,
  useCurrentFrame,
  useVideoConfig,
} from "remotion";
import { Phone, TV } from "./devices";
import {
  Backdrop,
  Body,
  clamp,
  ease,
  Headline,
  Kicker,
  Pill,
  Screen,
} from "./primitives";
import { C, SCREENS, TEXT } from "./theme";

const PHONE_ASPECT = 2400 / 1080;

/** Left column copy used by the feature scenes. */
const Copy: React.FC<{
  kicker: string;
  headline: string;
  body: string;
  pills?: string[];
  width?: number;
  size?: number;
  delay?: number;
}> = ({ kicker, headline, body, pills = [], width = 760, size = 100, delay = 0 }) => (
  <AbsoluteFill
    style={{
      padding: "0 0 0 130px",
      justifyContent: "center",
      gap: 28,
      width,
    }}
  >
    <Kicker delay={delay}>{kicker}</Kicker>
    <Headline text={headline} delay={delay + 4} size={size} />
    <Body delay={delay + 22}>{body}</Body>
    {pills.length > 0 && (
      <div style={{ display: "flex", gap: 14, flexWrap: "wrap", marginTop: 6 }}>
        {pills.map((p, i) => (
          <Pill key={p} delay={delay + 36 + i * 4}>
            {p}
          </Pill>
        ))}
      </div>
    )}
  </AbsoluteFill>
);

const useSpring = (delay: number, soft = false) => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();
  return spring({
    frame: frame - delay,
    fps,
    config: soft ? { damping: 18, stiffness: 90 } : { damping: 200 },
    durationInFrames: soft ? undefined : 30,
  });
};

/** TV on the right with an optional phone in front of it. */
const TvAndPhone: React.FC<{
  tv: string;
  phone?: string;
  tvTrim?: number;
  phoneTrim?: number;
  tvWidth?: number;
  phoneWidth?: number;
  phonePosition?: string;
}> = ({ tv, phone, tvTrim, phoneTrim, tvWidth = 1100, phoneWidth = 250, phonePosition }) => {
  const frame = useCurrentFrame();
  const a = useSpring(2);
  const b = useSpring(16, true);
  const drift = interpolate(frame, [0, 150], [10, -14], clamp);
  return (
    <AbsoluteFill style={{ perspective: 2200 }}>
      <div
        style={{
          position: "absolute",
          right: -90,
          top: 190,
          translate: `${(1 - a) * 700}px ${drift}px`,
          rotate: `y ${-12 + a * 4}deg`,
        }}
      >
        <TV width={tvWidth} stand={false}>
          <Screen src={tv} trimBefore={tvTrim} />
        </TV>
      </div>
      {phone && (
        <div
          style={{
            position: "absolute",
            left: 830,
            top: 500,
            translate: `0 ${(1 - b) * 900 - drift}px`,
            rotate: `${-4 * b}deg`,
          }}
        >
          <Phone width={phoneWidth} aspect={PHONE_ASPECT}>
            <Screen src={phone} trimBefore={phoneTrim} position={phonePosition} />
          </Phone>
        </div>
      )}
    </AbsoluteFill>
  );
};

const Fade: React.FC = () => (
  <AbsoluteFill
    style={{
      background:
        "linear-gradient(90deg, rgba(5,7,7,0.97) 0%, rgba(5,7,7,0.8) 30%, transparent 50%)",
    }}
  />
);

/* Live TV */
export const LiveTv: React.FC = () => (
  <AbsoluteFill>
    <Backdrop />
    <TvAndPhone tv={SCREENS.tvClipLiveTv} phone={SCREENS.phoneClipLiveTv} />
    <Fade />
    <Copy
      kicker="New · Live TV"
      headline={"Live TV,\n*silky smooth.*"}
      body="A full guide that glides, even with tens of thousands of channels. Catch-up, favourites and a brand-new player."
      pills={["TV guide", "Catch-up", "Xtream · M3U"]}
    />
  </AbsoluteFill>
);

/* Sports */
export const Sports: React.FC = () => (
  <AbsoluteFill>
    <Backdrop hue="amber" />
    <TvAndPhone tv={SCREENS.tvClipSports} phone={SCREENS.phoneClipSports} phoneTrim={60} />
    <Fade />
    <Copy
      kicker="New · Sports"
      headline={"Every game.\n*One hub.*"}
      body="Live scores, today's fixtures and the channels showing them, on your TV and your phone."
      pills={["Live scores", "My teams", "Where to watch"]}
    />
  </AbsoluteFill>
);

/* News */
export const News: React.FC = () => {
  const frame = useCurrentFrame();
  const a = useSpring(2);
  const drift = interpolate(frame, [0, 120], [8, -10], clamp);
  return (
    <AbsoluteFill>
      <Backdrop hue="mix" />
      <AbsoluteFill style={{ perspective: 2200 }}>
        <div
          style={{
            position: "absolute",
            right: -60,
            top: 170,
            translate: `${(1 - a) * 700}px ${drift}px`,
            rotate: `y ${-12 + a * 4}deg`,
          }}
        >
          <TV width={1200} stand={false}>
            <div style={{ position: "relative", width: "100%", height: "100%" }}>
              <Screen src={SCREENS.tvNews} zoom={[1, 1.04]} />
              {/* Headline list is real, live news: keep it soft so the featured story leads. */}
              <div
                style={{
                  position: "absolute",
                  left: "0%",
                  width: "47%",
                  top: "15%",
                  bottom: 0,
                  backdropFilter: "blur(7px)",
                  WebkitBackdropFilter: "blur(7px)",
                  background: "rgba(5,7,7,0.25)",
                }}
              />
            </div>
          </TV>
        </div>
      </AbsoluteFill>
      <Fade />
      <Copy
        kicker="New · News"
        headline={"The headlines,\n*built in.*"}
        body="Pick your providers and topics, or add any RSS feed. Scan a code to keep reading on your phone."
        pills={["Topics", "RSS feeds", "Scan to read"]}
      />
    </AbsoluteFill>
  );
};

/* Home servers */
const SERVERS = [
  { name: "Plex", color: "#e5a00d" },
  { name: "Jellyfin", color: "#aa5cc3" },
  { name: "Emby", color: "#52b54b" },
  { name: "Silo", color: C.mint },
  { name: "Navidrome", color: "#4f8dfd" },
  { name: "Subsonic", color: "#ffa733" },
];

export const HomeServers: React.FC = () => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();
  const a = useSpring(2);
  const drift = interpolate(frame, [0, 120], [8, -10], clamp);
  return (
    <AbsoluteFill>
      <Backdrop />
      <AbsoluteFill style={{ perspective: 2200 }}>
        <div
          style={{
            position: "absolute",
            right: -60,
            top: 150,
            translate: `${(1 - a) * 700}px ${drift}px`,
            rotate: `y ${-12 + a * 4}deg`,
          }}
        >
          <TV width={1120} stand={false}>
            <Screen src={SCREENS.tvHomeServers} zoom={[1.02, 1.07]} />
          </TV>
        </div>
      </AbsoluteFill>
      <Fade />
      <Copy
        kicker="Home servers"
        headline={"Your server,\n*on Home.*"}
        body="Films, shows and music from your own server show up next to everything else, newest first."
        width={780}
      />
      <div
        style={{
          position: "absolute",
          left: 760,
          right: 80,
          bottom: 90,
          display: "flex",
          gap: 18,
          justifyContent: "center",
        }}
      >
        {SERVERS.map((s, i) => {
          const p = spring({
            frame: frame - 26 - i * 4,
            fps,
            config: { damping: 16, stiffness: 120 },
          });
          return (
            <div
              key={s.name}
              style={{
                display: "flex",
                alignItems: "center",
                gap: 14,
                padding: "16px 26px",
                borderRadius: 18,
                background: "rgba(10,14,13,0.9)",
                border: "1px solid rgba(255,255,255,0.12)",
                boxShadow: "0 20px 50px rgba(0,0,0,0.5)",
                fontFamily: TEXT,
                fontSize: 30,
                fontWeight: 650,
                color: C.ink,
                opacity: p,
                translate: `0 ${(1 - p) * 60}px`,
              }}
            >
              <span
                style={{
                  width: 16,
                  height: 16,
                  borderRadius: 99,
                  background: s.color,
                  boxShadow: `0 0 16px ${s.color}`,
                }}
              />
              {s.name}
            </div>
          );
        })}
      </div>
    </AbsoluteFill>
  );
};

/* Bring your own sources, incl. Telegram */
const OWN = [
  "Add-ons",
  "Debrid",
  "Usenet",
  "Telegram",
  "Xtream · M3U",
  "Trakt",
  "SIMKL",
  "Music add-ons",
];

export const OwnSources: React.FC = () => {
  const frame = useCurrentFrame();
  const a = useSpring(2);
  const b = useSpring(12, true);
  const hl = Math.floor(frame / 7) % OWN.length;
  return (
    <AbsoluteFill>
      <Backdrop intensity={1.15} />
      <AbsoluteFill style={{ perspective: 2200 }}>
        <div
          style={{
            position: "absolute",
            right: 380,
            top: 120,
            translate: `${(1 - a) * 700}px 0`,
            rotate: `y ${-10 + a * 4}deg`,
          }}
        >
          <TV width={820} stand={false}>
            <Screen src={SCREENS.tvSources} />
          </TV>
        </div>
        <div
          style={{
            position: "absolute",
            right: 70,
            top: 470,
            translate: `${(1 - b) * 600}px 0`,
            rotate: `y ${-8 + b * 4}deg`,
          }}
        >
          <TV width={720} stand={false}>
            <Screen src={SCREENS.tvTelegram} />
          </TV>
        </div>
      </AbsoluteFill>
      <Fade />
      <Copy
        kicker="Bring your own"
        headline={"Use your\n*own sources.*"}
        body="Add-ons, debrid, your IPTV, even your own Telegram channels. ARVIO plays what you connect."
        width={780}
        size={96}
      />
      <div
        style={{
          position: "absolute",
          left: 130,
          right: 130,
          bottom: 70,
          display: "flex",
          flexWrap: "wrap",
          gap: 14,
          justifyContent: "center",
        }}
      >
        {OWN.map((s, i) => (
          <Pill key={s} delay={30 + i * 3} strong={frame > 60 && i === hl}>
            {s}
          </Pill>
        ))}
      </div>
    </AbsoluteFill>
  );
};

/* Small badge used by the subtitles scene to say it also works on live TV */
export const LiveBadge: React.FC<{ delay: number }> = ({ delay }) => {
  const frame = useCurrentFrame();
  const p = interpolate(frame - delay, [0, 12], [0, 1], { ...clamp, easing: ease });
  const pulse = 0.5 + 0.5 * Math.sin(frame / 5);
  return (
    <div
      style={{
        display: "inline-flex",
        alignItems: "center",
        gap: 12,
        padding: "10px 20px",
        borderRadius: 99,
        background: "rgba(255,70,70,0.14)",
        border: "1px solid rgba(255,90,90,0.55)",
        color: "#ffd6d6",
        fontFamily: TEXT,
        fontSize: 26,
        fontWeight: 650,
        opacity: p,
        scale: String(0.9 + 0.1 * p),
      }}
    >
      <span
        style={{
          width: 12,
          height: 12,
          borderRadius: 99,
          background: "#ff4d4d",
          boxShadow: `0 0 ${8 + pulse * 12}px #ff4d4d`,
        }}
      />
      Works on live TV too
    </div>
  );
};

/* Music, on phone and now on TV */
export const MusicEverywhere: React.FC = () => {
  const frame = useCurrentFrame();
  const a = useSpring(2);
  const b = useSpring(12, true);
  const c = useSpring(20, true);
  const drift = interpolate(frame, [0, 125], [10, -14], clamp);
  return (
    <AbsoluteFill>
      <Backdrop hue="mix" />
      <AbsoluteFill style={{ perspective: 2200 }}>
        <div
          style={{
            position: "absolute",
            right: -90,
            top: 150,
            translate: `${(1 - a) * 700}px ${drift}px`,
            rotate: `y ${-12 + a * 4}deg`,
          }}
        >
          <TV width={1100} stand={false}>
            <Screen src={SCREENS.tvClipMusic} trimBefore={75} />
          </TV>
        </div>
        <div
          style={{
            position: "absolute",
            left: 800,
            top: 470,
            translate: `0 ${(1 - b) * 900 - drift}px`,
            rotate: `${-5 * b}deg`,
          }}
        >
          <Phone width={250} aspect={PHONE_ASPECT}>
            <Screen src={SCREENS.musicLyrics} />
          </Phone>
        </div>
        <div
          style={{
            position: "absolute",
            left: 1080,
            top: 560,
            translate: `0 ${(1 - c) * 900 - drift * 0.6}px`,
            rotate: `${3 * c}deg`,
          }}
        >
          <Phone width={220} aspect={PHONE_ASPECT}>
            <Screen src={SCREENS.musicQueue} />
          </Phone>
        </div>
      </AbsoluteFill>
      <Fade />
      <Copy
        kicker="Music · Now on TV"
        headline={"Your music,\n*too.*"}
        body="Charts for your country, synced lyrics, a queue and a sleep timer, on your phone and now on your TV."
        pills={["Charts", "Lyrics", "Queue", "Your library"]}
        size={108}
      />
    </AbsoluteFill>
  );
};
