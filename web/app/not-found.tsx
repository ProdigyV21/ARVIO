import React from "react";
import Link from "next/link";

export default function NotFound() {
  return (
    <div
      style={{
        minHeight: "100vh",
        display: "flex",
        flexDirection: "column",
        background: "#070b0c",
        color: "#f4f7f6",
        fontFamily: 'Inter, system-ui, -apple-system, "Segoe UI", sans-serif',
        position: "relative",
        isolation: "isolate",
        overflowX: "hidden"
      }}
    >
      {/* Background aurora atmosphere */}
      <div
        aria-hidden="true"
        style={{
          position: "absolute",
          inset: 0,
          zIndex: -1,
          pointerEvents: "none",
          overflow: "hidden"
        }}
      >
        <div
          style={{
            position: "absolute",
            width: "65%",
            height: "80%",
            borderRadius: "50%",
            filter: "blur(70px)",
            opacity: 0.55,
            right: "-5%",
            top: "-15%",
            background: "radial-gradient(ellipse, rgba(20, 108, 106, 0.4), transparent 70%)"
          }}
        />
        <div
          style={{
            position: "absolute",
            width: "55%",
            height: "75%",
            borderRadius: "50%",
            filter: "blur(75px)",
            opacity: 0.45,
            right: "10%",
            bottom: "-30%",
            background: "radial-gradient(ellipse, rgba(92, 87, 115, 0.3), transparent 70%)"
          }}
        />
        <div
          style={{
            position: "absolute",
            left: "30%",
            right: "-10%",
            bottom: "28%",
            height: "1px",
            background: "linear-gradient(90deg, transparent, rgba(129, 190, 168, 0.22), transparent)",
            boxShadow: "0 0 65px 12px rgba(144, 187, 172, 0.05)",
            transform: "rotate(-12deg)"
          }}
        />
      </div>

      {/* Navigation */}
      <header
        style={{
          height: "76px",
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
          padding: "0 36px",
          borderBottom: "1px solid rgba(255, 255, 255, 0.06)",
          background: "rgba(7, 11, 12, 0.8)",
          backdropFilter: "blur(18px)",
          WebkitBackdropFilter: "blur(18px)",
          position: "sticky",
          top: 0,
          zIndex: 10
        }}
      >
        <Link href="/" aria-label="ARVIO home" style={{ display: "inline-flex", alignItems: "center" }}>
          <img src="/arvio-wordmark.svg" alt="ARVIO" width={134} height={34} style={{ width: "134px", height: "auto", display: "block" }} />
        </Link>
        <nav style={{ display: "flex", alignItems: "center", gap: "24px", fontSize: "13px", fontWeight: 500 }}>
          <Link href="/" style={{ color: "#d6d9da", textDecoration: "none" }}>
            Home
          </Link>
          <a
            href="https://discord.gg/UavuEYMfQ4"
            target="_blank"
            rel="noopener noreferrer"
            style={{ color: "#9efbd5", textDecoration: "none" }}
          >
            Discord <span aria-hidden="true">↗</span>
          </a>
        </nav>
      </header>

      {/* Main Content */}
      <main
        style={{
          flex: "1 0 auto",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          padding: "80px 24px 60px",
          position: "relative",
          zIndex: 2
        }}
      >
        <div style={{ width: "min(800px, 100%)", textAlign: "center" }}>
          {/* Badge */}
          <div
            style={{
              display: "inline-flex",
              alignItems: "center",
              gap: "10px",
              fontSize: "10px",
              letterSpacing: "1.8px",
              color: "#c7d6cd",
              border: "1px solid rgba(181, 212, 197, 0.14)",
              background: "rgba(181, 212, 197, 0.05)",
              padding: "8px 16px",
              borderRadius: "30px",
              marginBottom: "24px",
              textTransform: "uppercase"
            }}
          >
            <span
              style={{
                width: "5px",
                height: "5px",
                borderRadius: "50%",
                background: "#9efbd5",
                boxShadow: "0 0 14px rgba(158, 251, 213, 0.4)"
              }}
            />
            404 · Page Not Found
          </div>

          {/* Heading */}
          <h1
            style={{
              fontSize: "clamp(36px, 5.2vw, 64px)",
              fontWeight: 580,
              letterSpacing: "-0.055em",
              lineHeight: 1.08,
              margin: "0 0 18px",
              color: "#f5f8f6"
            }}
          >
            This page isn’t{" "}
            <span
              style={{
                background: "linear-gradient(115deg, #ddf3e6 4%, #9bd1ba 60%, #749d9c)",
                WebkitBackgroundClip: "text",
                color: "transparent"
              }}
            >
              available.
            </span>
          </h1>

          {/* Lead */}
          <p
            style={{
              fontSize: "16px",
              lineHeight: 1.7,
              color: "#a5b5ae",
              maxWidth: "540px",
              margin: "0 auto 36px"
            }}
          >
            The link you followed may be broken, or the screen may have been moved or retired. Return to your home screen or connect with us on Discord.
          </p>

          {/* Actions */}
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              gap: "14px",
              flexWrap: "wrap",
              marginBottom: "56px"
            }}
          >
            <Link
              href="/"
              style={{
                display: "inline-flex",
                alignItems: "center",
                justifyContent: "center",
                gap: "14px",
                padding: "14px 28px",
                borderRadius: "10px",
                background: "#f3f7f5",
                color: "#102019",
                fontSize: "13px",
                fontWeight: 650,
                textDecoration: "none",
                boxShadow: "0 4px 18px rgba(255, 255, 255, 0.05)"
              }}
            >
              Return to Home <span aria-hidden="true">&rarr;</span>
            </Link>
            <a
              href="https://discord.gg/UavuEYMfQ4"
              target="_blank"
              rel="noopener noreferrer"
              style={{
                display: "inline-flex",
                alignItems: "center",
                justifyContent: "center",
                gap: "14px",
                padding: "14px 26px",
                borderRadius: "10px",
                background: "rgba(255, 255, 255, 0.04)",
                border: "1px solid rgba(255, 255, 255, 0.14)",
                color: "#f5f8f6",
                fontSize: "13px",
                fontWeight: 600,
                textDecoration: "none"
              }}
            >
              Discord Community <span aria-hidden="true">&#8599;</span>
            </a>
          </div>

          {/* Destination Cards */}
          <div
            style={{
              borderTop: "1px solid rgba(255, 255, 255, 0.08)",
              paddingTop: "36px",
              textAlign: "left"
            }}
          >
            <p
              style={{
                fontSize: "10px",
                letterSpacing: "2px",
                color: "#8f9f94",
                textTransform: "uppercase",
                margin: "0 0 18px",
                fontWeight: 600
              }}
            >
              Popular Destinations
            </p>
            <div
              style={{
                display: "grid",
                gridTemplateColumns: "repeat(auto-fit, minmax(240px, 1fr))",
                gap: "14px"
              }}
            >
              <Link
                href="/"
                style={{
                  display: "flex",
                  alignItems: "flex-start",
                  gap: "16px",
                  padding: "18px 20px",
                  background: "#0d1311",
                  border: "1px solid rgba(255, 255, 255, 0.08)",
                  borderRadius: "12px",
                  textDecoration: "none"
                }}
              >
                <span style={{ fontSize: "11px", fontWeight: 600, color: "#9efbd5", letterSpacing: "1px", marginTop: "2px" }}>
                  01
                </span>
                <div>
                  <div style={{ fontSize: "14px", fontWeight: 600, color: "#f4f7f6", marginBottom: "4px" }}>
                    Home Screen
                  </div>
                  <p style={{ fontSize: "12px", color: "#919c98", margin: 0, lineHeight: 1.5 }}>
                    Browse your media libraries, continue watching and explore suggestions.
                  </p>
                </div>
              </Link>

              <a
                href="https://arvio.tv/guides/"
                target="_blank"
                rel="noopener noreferrer"
                style={{
                  display: "flex",
                  alignItems: "flex-start",
                  gap: "16px",
                  padding: "18px 20px",
                  background: "#0d1311",
                  border: "1px solid rgba(255, 255, 255, 0.08)",
                  borderRadius: "12px",
                  textDecoration: "none"
                }}
              >
                <span style={{ fontSize: "11px", fontWeight: 600, color: "#9efbd5", letterSpacing: "1px", marginTop: "2px" }}>
                  02
                </span>
                <div>
                  <div style={{ fontSize: "14px", fontWeight: 600, color: "#f4f7f6", marginBottom: "4px" }}>
                    Setup Guides
                  </div>
                  <p style={{ fontSize: "12px", color: "#919c98", margin: 0, lineHeight: 1.5 }}>
                    Instructions for connecting Jellyfin, Plex, Emby and live TV streams.
                  </p>
                </div>
              </a>

              <a
                href="https://discord.gg/UavuEYMfQ4"
                target="_blank"
                rel="noopener noreferrer"
                style={{
                  display: "flex",
                  alignItems: "flex-start",
                  gap: "16px",
                  padding: "18px 20px",
                  background: "#0d1311",
                  border: "1px solid rgba(255, 255, 255, 0.08)",
                  borderRadius: "12px",
                  textDecoration: "none"
                }}
              >
                <span style={{ fontSize: "11px", fontWeight: 600, color: "#9efbd5", letterSpacing: "1px", marginTop: "2px" }}>
                  03
                </span>
                <div>
                  <div style={{ fontSize: "14px", fontWeight: 600, color: "#f4f7f6", marginBottom: "4px" }}>
                    Discord Community
                  </div>
                  <p style={{ fontSize: "12px", color: "#919c98", margin: 0, lineHeight: 1.5 }}>
                    Connect with other users, ask questions and report problems.
                  </p>
                </div>
              </a>
            </div>
          </div>
        </div>
      </main>

      {/* Footer */}
      <footer
        style={{
          borderTop: "1px solid rgba(255, 255, 255, 0.08)",
          padding: "32px 36px",
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          flexWrap: "wrap",
          gap: "20px",
          fontSize: "12px",
          color: "#919c98"
        }}
      >
        <div style={{ display: "flex", alignItems: "center", gap: "16px" }}>
          <Link href="/" aria-label="ARVIO home">
            <img src="/arvio-wordmark.svg" alt="ARVIO" width={100} height={25} style={{ width: "100px", height: "auto" }} />
          </Link>
          <span>&copy; 2026 ARVIO</span>
        </div>
        <div style={{ display: "flex", gap: "20px" }}>
          <a href="https://arvio.tv/" target="_blank" rel="noopener noreferrer" style={{ color: "#b9c1bd", textDecoration: "none" }}>
            Website
          </a>
          <a href="https://arvio.tv/guides/" target="_blank" rel="noopener noreferrer" style={{ color: "#b9c1bd", textDecoration: "none" }}>
            Guides
          </a>
          <a href="https://github.com/ProdigyV21/ARVIO" target="_blank" rel="noopener noreferrer" style={{ color: "#b9c1bd", textDecoration: "none" }}>
            GitHub
          </a>
          <a href="https://discord.gg/UavuEYMfQ4" target="_blank" rel="noopener noreferrer" style={{ color: "#b9c1bd", textDecoration: "none" }}>
            Discord
          </a>
        </div>
      </footer>
    </div>
  );
}
