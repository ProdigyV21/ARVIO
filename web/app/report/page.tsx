"use client";

import React, { Suspense, useEffect, useState } from "react";
import { useSearchParams } from "next/navigation";
import { LanguageProvider, useTranslation } from "@/lib/i18n";

const DISCORD_URL = "https://discord.gg/UavuEYMfQ4";

async function copyText(text: string): Promise<boolean> {
  try {
    if (navigator.clipboard) { await navigator.clipboard.writeText(text); return true; }
  } catch { /* try the legacy path */ }
  try {
    const textArea = document.createElement("textarea");
    textArea.value = text;
    document.body.appendChild(textArea);
    try {
      textArea.select();
      return document.execCommand("copy");
    } finally {
      textArea.remove();
    }
  } catch { return false; }
}

function ReportContent() {
  const translateUi = useTranslation();
  const searchParams = useSearchParams();
  const bare = !searchParams.get("id") && !searchParams.get("err") && !searchParams.get("error");
  const id = searchParams.get("id") || "N/A";
  const version = searchParams.get("v") || searchParams.get("version") || "1.0";
  const error = searchParams.get("err") || searchParams.get("error") || "Unexpected crash";
  const timeParam = searchParams.get("t") || searchParams.get("time");

  const [copied, setCopied] = useState<"idle" | "copied" | "failed">("idle");
  const [timeStr, setTimeStr] = useState("Just now");

  useEffect(() => {
    if (timeParam) {
      const parsed = parseInt(timeParam, 10);
      if (!isNaN(parsed)) {
        setTimeStr(new Date(parsed).toLocaleString());
      } else {
        setTimeStr(timeParam);
      }
    } else {
      setTimeStr(new Date().toLocaleString());
    }
  }, [timeParam]);

  const isSentry = searchParams.get("provider") === "sentry";
  const firebase = !isSentry;
  const dashboard = firebase
    ? "https://console.firebase.google.com/project/arvio-15c5a/crashlytics"
    : id !== "N/A" ? `https://sentry.io/issues/?query=id%3A${encodeURIComponent(id)}` : "N/A";

  const reportText = `**🚨 ARVIO Crash Report**
**Crash ID:** \`${id}\`
**${firebase ? "Crashlytics" : "Sentry Link"}:** ${dashboard}
${firebase ? `**Report reference:** \`arvio_report_id=${id}\`` : ""}
**Version:** ${version}
**Time:** ${timeStr}
**Error:** ${error}`;

  const handleCopyAndRedirect = async () => {
    const ok = await copyText(reportText);
    setCopied(ok ? "copied" : "failed");
    if (ok) setTimeout(() => { window.location.href = DISCORD_URL; }, 700);
  };

  return (
    <div style={{ minHeight: "100vh", background: "#050606", color: "#f6f7f7", fontFamily: 'Inter, system-ui, -apple-system, "Segoe UI", sans-serif' }}>
      <header style={{ borderBottom: "1px solid rgba(255, 255, 255, 0.11)", background: "rgba(4, 5, 5, 0.85)", position: "sticky", top: 0, zIndex: 10 }}>
        <div style={{ maxWidth: "1180px", margin: "0 auto", padding: "0 24px", minHeight: "70px", display: "flex", alignItems: "center", justifyContent: "space-between" }}>
          <a href="/" aria-label="ARVIO home" style={{ display: "inline-flex", alignItems: "center" }}>
            <img src="/arvio-wordmark.svg" alt="ARVIO" width={134} height={34} style={{ width: "134px", height: "auto" }} />
          </a>
          <nav style={{ display: "flex", gap: "22px", fontSize: "14px", fontWeight: 600 }}>
            <a href="/" style={{ color: "#d6d9da", textDecoration: "none" }}>{translateUi("Home")}</a>
            <a href={DISCORD_URL} target="_blank" rel="noopener noreferrer" style={{ color: "#d6d9da", textDecoration: "none" }}>Discord</a>
          </nav>
        </div>
      </header>

      <main style={{ width: "min(720px, calc(100% - 48px))", margin: "0 auto", padding: "64px 0 80px" }}>
        <p style={{ color: "#717779", fontSize: "12px", textTransform: "uppercase", letterSpacing: "0.08em", fontWeight: 650, margin: "0 0 8px" }}>
          {translateUi("Support & Troubleshooting")}
        </p>
        <h1 style={{ fontSize: "32px", fontWeight: 750, letterSpacing: "-0.03em", margin: "0 0 10px", color: "#fff" }}>
          {translateUi("ARVIO Diagnostics")}
        </h1>
        <p style={{ color: "#a6abad", fontSize: "15px", lineHeight: 1.6, margin: "0 0 32px" }}>
          {translateUi("App health status, crash reports, and troubleshooting references.")}
        </p>

        {!bare ? (
          <div style={{ background: "#0d0f10", border: "1px solid #25292b", borderRadius: "12px", padding: "24px", marginBottom: "32px" }}>
            <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between", marginBottom: "14px", paddingBottom: "14px", borderBottom: "1px solid #25292b" }}>
              <div style={{ display: "flex", alignItems: "center", gap: "8px", fontSize: "14px", fontWeight: 650, color: "#fff" }}>
                <span style={{ width: "8px", height: "8px", borderRadius: "50%", background: "#f87171", display: "inline-block" }}></span>
                <span>{translateUi("Crash Report Captured")}</span>
              </div>
              <span style={{ color: "#717779", fontSize: "13px" }}>{timeStr}</span>
            </div>
            <p style={{ color: "#a6abad", fontSize: "14px", lineHeight: 1.6, margin: "0 0 16px" }}>
              {translateUi("An unexpected issue was captured on your device. Copy the diagnostic summary below to paste in the ARVIO Discord bug channel.")}
            </p>
            <div style={{ background: "#000", border: "1px solid #25292b", borderRadius: "8px", padding: "14px 16px", marginBottom: "20px" }}>
              <pre style={{ margin: 0, fontFamily: "ui-monospace, SFMono-Regular, Consolas, monospace", fontSize: "12.5px", color: "#d6d9da", lineHeight: 1.6, whiteSpace: "pre-wrap", wordBreak: "break-word" }}>
                {reportText}
              </pre>
            </div>
            {copied === "failed" && <>
              <p role="alert" style={{ color: "#ffb4ab", fontSize: "14px", lineHeight: 1.6 }}>
                {translateUi("Your browser blocked copying. Select the report text, copy it, then open Discord.")}
              </p>
              <textarea
                readOnly
                autoFocus
                aria-label={translateUi("Report text")}
                value={reportText}
                onFocus={(event) => event.currentTarget.select()}
                style={{ width: "100%", boxSizing: "border-box", minHeight: "180px", marginBottom: "20px", padding: "12px", border: "1px solid #373d40", borderRadius: "7px", background: "#000", color: "#f6f7f7", fontSize: "16px" }}
              />
            </>}
            <div style={{ display: "flex", gap: "12px", flexWrap: "wrap", alignItems: "center" }}>
              <button
                onClick={handleCopyAndRedirect}
                type="button"
                style={{
                  display: "inline-flex",
                  alignItems: "center",
                  justifyContent: "center",
                  padding: "10px 18px",
                  borderRadius: "7px",
                  background: copied === "copied" ? "#53d9c1" : "#fff",
                  color: "#000",
                  fontSize: "14px",
                  fontWeight: 650,
                  border: "1px solid #fff",
                  cursor: "pointer"
                }}
              >
                {copied === "copied" ? translateUi("Copied! Opening Discord...") : copied === "failed" ? translateUi("Couldn't copy: select the text above") : translateUi("Copy Report & Open Discord")}
              </button>
              <a
                href={DISCORD_URL}
                target="_blank"
                rel="noopener noreferrer"
                style={{
                  display: "inline-flex",
                  alignItems: "center",
                  justifyContent: "center",
                  padding: "10px 16px",
                  borderRadius: "7px",
                  background: "transparent",
                  color: "#fff",
                  fontSize: "14px",
                  fontWeight: 600,
                  border: "1px solid #373d40",
                  textDecoration: "none"
                }}
              >
                {translateUi("Open Discord Without Copying")} &rarr;
              </a>
            </div>
          </div>
        ) : (
          <div style={{ background: "#0d0f10", border: "1px solid #25292b", borderRadius: "12px", padding: "24px", marginBottom: "32px" }}>
            <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between", marginBottom: "14px", paddingBottom: "14px", borderBottom: "1px solid #25292b" }}>
              <div style={{ display: "flex", alignItems: "center", gap: "8px", fontSize: "14px", fontWeight: 650, color: "#fff" }}>
                <span style={{ width: "8px", height: "8px", borderRadius: "50%", background: "#53d9c1", display: "inline-block" }}></span>
                <span>{translateUi("No Crash Detected")}</span>
              </div>
              <span style={{ color: "#717779", fontSize: "13px" }}>{translateUi("Normal Session")}</span>
            </div>
            <p style={{ color: "#a6abad", fontSize: "14px", lineHeight: 1.6, margin: "0 0 20px" }}>
              {translateUi("No crash data was passed to this page. When ARVIO encounters an unexpected exception on Android TV or mobile, scanning the on-screen QR code opens this diagnostic page with the captured error trace.")}
            </p>
            <div style={{ display: "flex", gap: "12px", flexWrap: "wrap", alignItems: "center" }}>
              <a
                href={DISCORD_URL}
                target="_blank"
                rel="noopener noreferrer"
                style={{
                  display: "inline-flex",
                  alignItems: "center",
                  justifyContent: "center",
                  padding: "10px 18px",
                  borderRadius: "7px",
                  background: "#fff",
                  color: "#000",
                  fontSize: "14px",
                  fontWeight: 650,
                  textDecoration: "none"
                }}
              >
                {translateUi("Join Discord Community")}
              </a>
              <a
                href="/"
                style={{
                  display: "inline-flex",
                  alignItems: "center",
                  justifyContent: "center",
                  padding: "10px 16px",
                  borderRadius: "7px",
                  background: "transparent",
                  color: "#fff",
                  fontSize: "14px",
                  fontWeight: 600,
                  border: "1px solid #373d40",
                  textDecoration: "none"
                }}
              >
                {translateUi("Return to ARVIO Web")} &rarr;
              </a>
            </div>
          </div>
        )}

        <section style={{ borderTop: "1px solid #25292b", paddingTop: "32px" }}>
          <h2 style={{ fontSize: "16px", fontWeight: 650, color: "#fff", margin: "0 0 14px" }}>
            {translateUi("Troubleshooting resources")}
          </h2>
          <ul style={{ listStyle: "none", padding: 0, margin: 0, display: "grid", gap: "10px" }}>
            <li style={{ padding: "14px 18px", background: "#0d0f10", border: "1px solid #25292b", borderRadius: "8px" }}>
              <a href="https://github.com/ProdigyV21/ARVIO/issues" target="_blank" rel="noopener noreferrer" style={{ display: "flex", justifyContent: "space-between", alignItems: "center", color: "#f6f7f7", textDecoration: "none", fontSize: "14px", fontWeight: 500 }}>
                <span>GitHub Issue Tracker</span>
                <span style={{ color: "#717779" }}>&rarr;</span>
              </a>
            </li>
            <li style={{ padding: "14px 18px", background: "#0d0f10", border: "1px solid #25292b", borderRadius: "8px" }}>
              <a href={DISCORD_URL} target="_blank" rel="noopener noreferrer" style={{ display: "flex", justifyContent: "space-between", alignItems: "center", color: "#f6f7f7", textDecoration: "none", fontSize: "14px", fontWeight: 500 }}>
                <span>Discord Support Channel</span>
                <span style={{ color: "#717779" }}>&rarr;</span>
              </a>
            </li>
          </ul>
        </section>
      </main>
    </div>
  );
}

export default function ReportPage() {
  const [language, setLanguage] = useState("en");
  useEffect(() => setLanguage(navigator.language || "en"), []);
  return (
    <LanguageProvider language={language}>
      <Suspense fallback={<div style={{ minHeight: "100vh", background: "#050606" }} />}>
        <ReportContent />
      </Suspense>
    </LanguageProvider>
  );
}
