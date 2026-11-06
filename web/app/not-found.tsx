import React from "react";
import Link from "next/link";

export default function NotFound() {
  return (
    <div style={{ minHeight: "100vh", background: "#050606", color: "#f6f7f7", fontFamily: 'Inter, system-ui, -apple-system, "Segoe UI", sans-serif' }}>
      <header style={{ borderBottom: "1px solid rgba(255, 255, 255, 0.11)", background: "rgba(4, 5, 5, 0.85)", position: "sticky", top: 0, zIndex: 10 }}>
        <div style={{ maxWidth: "1180px", margin: "0 auto", padding: "0 24px", minHeight: "70px", display: "flex", alignItems: "center", justifyContent: "space-between" }}>
          <Link href="/" aria-label="ARVIO home" style={{ display: "inline-flex", alignItems: "center" }}>
            <img src="/arvio-wordmark.svg" alt="ARVIO" width={134} height={34} style={{ width: "134px", height: "auto" }} />
          </Link>
          <nav style={{ display: "flex", gap: "22px", fontSize: "14px", fontWeight: 600 }}>
            <Link href="/" style={{ color: "#d6d9da", textDecoration: "none" }}>Home</Link>
            <a href="https://discord.gg/UavuEYMfQ4" target="_blank" rel="noopener noreferrer" style={{ color: "#d6d9da", textDecoration: "none" }}>Discord</a>
          </nav>
        </div>
      </header>

      <main style={{ width: "min(720px, calc(100% - 48px))", margin: "0 auto", padding: "80px 0 90px" }}>
        <p style={{ color: "#53d9c1", fontSize: "13px", textTransform: "uppercase", letterSpacing: "0.1em", fontWeight: 700, margin: "0 0 10px" }}>
          404 · Page Not Found
        </p>
        <h1 style={{ fontSize: "36px", fontWeight: 750, letterSpacing: "-0.03em", color: "#fff", margin: "0 0 14px", lineHeight: 1.15 }}>
          This page isn’t available.
        </h1>
        <p style={{ color: "#a6abad", fontSize: "16px", lineHeight: 1.6, margin: "0 0 32px", maxWidth: "580px" }}>
          The address you entered doesn’t exist, has been moved, or is no longer available. Return to the home screen or explore our community below.
        </p>

        <div style={{ display: "flex", gap: "12px", flexWrap: "wrap", marginBottom: "48px" }}>
          <Link
            href="/"
            style={{
              display: "inline-flex",
              alignItems: "center",
              justifyContent: "center",
              padding: "11px 22px",
              borderRadius: "7px",
              background: "#fff",
              color: "#000",
              fontSize: "14px",
              fontWeight: 650,
              textDecoration: "none"
            }}
          >
            Return to Home
          </Link>
          <a
            href="https://discord.gg/UavuEYMfQ4"
            target="_blank"
            rel="noopener noreferrer"
            style={{
              display: "inline-flex",
              alignItems: "center",
              justifyContent: "center",
              padding: "11px 20px",
              borderRadius: "7px",
              border: "1px solid #373d40",
              background: "transparent",
              color: "#fff",
              fontSize: "14px",
              fontWeight: 600,
              textDecoration: "none"
            }}
          >
            Discord Community
          </a>
        </div>
      </main>
    </div>
  );
}
