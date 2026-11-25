import React from "react";

const glass: React.CSSProperties = {
  position: "absolute",
  inset: 0,
  background:
    "linear-gradient(125deg, rgba(255,255,255,0.10) 0%, rgba(255,255,255,0) 28%)",
  pointerEvents: "none",
};

const shadow =
  "0 60px 140px rgba(0,0,0,0.65), 0 0 0 1px rgba(255,255,255,0.06)";

/** Flat-panel TV: thin bezel, slim stand. `width` is the outer panel width in px. */
export const TV: React.FC<{
  width: number;
  children: React.ReactNode;
  style?: React.CSSProperties;
  stand?: boolean;
}> = ({ width, children, style, stand = true }) => {
  const bezel = Math.max(6, width * 0.008);
  const h = (width - bezel * 2) * (9 / 16) + bezel * 2;
  return (
    <div style={{ position: "relative", width, ...style }}>
      <div
        style={{
          width,
          height: h,
          padding: bezel,
          borderRadius: bezel * 1.4,
          background: "linear-gradient(180deg,#1b1f1e,#0b0d0d)",
          boxShadow: shadow,
          boxSizing: "border-box",
          position: "relative",
        }}
      >
        <div
          style={{
            width: "100%",
            height: "100%",
            overflow: "hidden",
            borderRadius: 3,
            background: "#000",
            position: "relative",
          }}
        >
          {children}
          <div style={glass} />
        </div>
      </div>
      {stand && (
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <div
            style={{
              width: width * 0.05,
              height: width * 0.035,
              background: "linear-gradient(180deg,#141716,#0c0e0e)",
            }}
          />
          <div
            style={{
              width: width * 0.28,
              height: width * 0.008,
              borderRadius: 99,
              background: "#151918",
              boxShadow: "0 20px 40px rgba(0,0,0,0.6)",
            }}
          />
        </div>
      )}
    </div>
  );
};

/** Laptop with browser chrome showing a URL. `width` is the lid width. */
export const Laptop: React.FC<{
  width: number;
  children: React.ReactNode;
  url?: string;
  style?: React.CSSProperties;
}> = ({ width, children, url = "web.arvio.tv", style }) => {
  const bezel = width * 0.022;
  const bar = width * 0.03;
  const screenW = width - bezel * 2;
  const screenH = screenW * (10 / 16);
  return (
    <div
      style={{
        position: "relative",
        width: width * 1.16,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        ...style,
      }}
    >
      <div
        style={{
          width,
          padding: bezel,
          paddingBottom: bezel * 1.2,
          borderRadius: `${bezel * 1.4}px ${bezel * 1.4}px 6px 6px`,
          background: "linear-gradient(180deg,#1c201f,#0e1010)",
          boxShadow: shadow,
          boxSizing: "border-box",
        }}
      >
        <div
          style={{
            width: screenW,
            height: screenH,
            overflow: "hidden",
            borderRadius: 4,
            background: "#000",
            position: "relative",
          }}
        >
          <div
            style={{
              height: bar,
              background: "#121615",
              display: "flex",
              alignItems: "center",
              gap: bar * 0.22,
              padding: `0 ${bar * 0.45}px`,
              borderBottom: "1px solid rgba(255,255,255,0.06)",
            }}
          >
            {["#ff5f57", "#febc2e", "#28c840"].map((c) => (
              <span
                key={c}
                style={{
                  width: bar * 0.26,
                  height: bar * 0.26,
                  borderRadius: 99,
                  background: c,
                  opacity: 0.85,
                }}
              />
            ))}
            <div
              style={{
                marginLeft: bar * 0.4,
                flex: 1,
                maxWidth: screenW * 0.42,
                height: bar * 0.6,
                borderRadius: 99,
                background: "rgba(255,255,255,0.07)",
                color: "rgba(255,255,255,0.75)",
                fontFamily: "Inter, sans-serif",
                fontSize: bar * 0.34,
                display: "flex",
                alignItems: "center",
                paddingLeft: bar * 0.4,
              }}
            >
              {url}
            </div>
          </div>
          <div
            style={{
              position: "absolute",
              top: bar,
              left: 0,
              right: 0,
              bottom: 0,
              overflow: "hidden",
            }}
          >
            {children}
          </div>
          <div style={glass} />
        </div>
      </div>
      <div
        style={{
          width: width * 1.16,
          height: width * 0.028,
          borderRadius: `2px 2px ${width * 0.03}px ${width * 0.03}px`,
          background: "linear-gradient(180deg,#2a2f2e,#0f1211)",
          boxShadow: "0 30px 60px rgba(0,0,0,0.6)",
          position: "relative",
        }}
      >
        <div
          style={{
            position: "absolute",
            left: "50%",
            top: 0,
            translate: "-50% 0",
            width: width * 0.16,
            height: width * 0.01,
            borderRadius: "0 0 8px 8px",
            background: "#0a0c0c",
          }}
        />
      </div>
    </div>
  );
};

/** Tablet in landscape. `width` is the outer width. */
export const Tablet: React.FC<{
  width: number;
  children: React.ReactNode;
  style?: React.CSSProperties;
  aspect?: number;
}> = ({ width, children, style, aspect = 1600 / 2560 }) => {
  const bezel = width * 0.022;
  const inner = width - bezel * 2;
  return (
    <div
      style={{
        width,
        height: inner * aspect + bezel * 2,
        padding: bezel,
        boxSizing: "border-box",
        borderRadius: width * 0.04,
        background: "linear-gradient(160deg,#2a2f2e,#0d0f0f)",
        boxShadow: shadow,
        ...style,
      }}
    >
      <div
        style={{
          width: "100%",
          height: "100%",
          overflow: "hidden",
          borderRadius: width * 0.022,
          background: "#000",
          position: "relative",
        }}
      >
        {children}
        <div style={glass} />
      </div>
    </div>
  );
};

/** Phone in portrait. `width` is the outer width. */
export const Phone: React.FC<{
  width: number;
  children: React.ReactNode;
  style?: React.CSSProperties;
  aspect?: number;
}> = ({ width, children, style, aspect = 2400 / 1080 }) => {
  const bezel = width * 0.035;
  const inner = width - bezel * 2;
  return (
    <div
      style={{
        width,
        height: inner * aspect + bezel * 2,
        padding: bezel,
        boxSizing: "border-box",
        borderRadius: width * 0.14,
        background: "linear-gradient(160deg,#2c3130,#0c0e0e)",
        boxShadow: shadow,
        position: "relative",
        ...style,
      }}
    >
      <div
        style={{
          width: "100%",
          height: "100%",
          overflow: "hidden",
          borderRadius: width * 0.11,
          background: "#000",
          position: "relative",
        }}
      >
        {children}
        <div style={glass} />
      </div>
    </div>
  );
};
