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
    textArea.select();
    const ok = document.execCommand("copy");
    document.body.removeChild(textArea);
    return ok;
  } catch { return false; }
}

function ReportContent() {
  const translateUi = useTranslation();
  const searchParams = useSearchParams();
  // The Android app opens this page with ?id=…&v=…&err=… after a crash.
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

  // Only say "Copied" when it was; otherwise leave the report on screen to copy by hand.
  const handleCopyAndRedirect = async () => {
    const ok = await copyText(reportText);
    setCopied(ok ? "copied" : "failed");
    if (ok) setTimeout(() => { window.location.href = DISCORD_URL; }, 700);
  };
  const fields: Array<[string, string, boolean?]> = [
    [translateUi("Crash ID"), id, true],
    [firebase ? "Crashlytics" : "Sentry", dashboard],
    ...(firebase ? [[translateUi("Report reference"), `arvio_report_id=${id}`, true] as [string, string, boolean]] : []),
    [translateUi("Version"), version],
    [translateUi("Time"), timeStr],
    [translateUi("Error"), error]
  ];

  return (
    <div
      style={{
        minHeight: "100vh",
        background:
          "radial-gradient(900px 600px at 50% -10%, rgba(13, 240, 194, 0.16), transparent 60%), radial-gradient(900px 600px at 50% 110%, rgba(88, 101, 242, 0.14), transparent 60%), #050505",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        padding: "24px",
        color: "#f5f5f5",
        fontFamily: 'Inter, system-ui, -apple-system, "Segoe UI", sans-serif'
      }}
    >
      <div
        style={{
          width: "100%",
          maxWidth: "520px",
          background: "rgba(18, 20, 26, 0.92)",
          border: "1px solid rgba(255, 255, 255, 0.14)",
          borderRadius: "24px",
          padding: "36px 28px",
          boxShadow: "0 24px 80px rgba(0, 0, 0, 0.65)",
          textAlign: "center",
          backdropFilter: "blur(16px)"
        }}
      >
        <div
          style={{
            width: "68px",
            height: "68px",
            borderRadius: "18px",
            background: "rgba(255, 255, 255, 0.05)",
            border: "1px solid rgba(255, 255, 255, 0.12)",
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            margin: "0 auto 18px",
            fontSize: "34px"
          }}
        >
          🚨
        </div>

        <h1 style={{ margin: "0 0 8px", fontSize: "26px", fontWeight: 750, color: "#fff" }}>
          {bare ? translateUi("Report a problem") : translateUi("ARVIO crash report")}
        </h1>
        <p style={{ margin: "0 0 24px", color: "#a0a6b2", fontSize: "15px", lineHeight: 1.5 }}>
          {bare
            ? translateUi("Tell us what happened on the ARVIO Discord. Include your device, the app version and what you were doing.")
            : translateUi("Copy this report and paste it in the ARVIO Discord bug channel, so we can find what went wrong.")}
        </p>
        {!bare && <>
        <div
          style={{
            background: "rgba(0, 0, 0, 0.45)",
            border: "1px solid rgba(255, 255, 255, 0.12)",
            borderRadius: "16px",
            padding: "18px",
            textAlign: "left",
            marginBottom: "26px"
          }}
        >
          <div
            style={{
              fontSize: "12px",
              textTransform: "uppercase",
              letterSpacing: "0.08em",
              color: "#0df0c2",
              fontWeight: 700,
              marginBottom: "10px"
            }}
          >
            {translateUi("Report")}
          </div>
          <dl style={{ margin: 0, display: "grid", gridTemplateColumns: "auto 1fr", gap: "6px 14px", fontSize: "13px", lineHeight: 1.5 }}>
            {fields.map(([label, value, code]) => <React.Fragment key={label}>
              <dt style={{ color: "#a0a6b2", fontWeight: 600 }}>{label}</dt>
              <dd style={{ margin: 0, color: "#e0e4eb", wordBreak: "break-word", fontFamily: code ? "ui-monospace, SFMono-Regular, Consolas, monospace" : undefined }}>
                {/^https:\/\//.test(value) ? <a href={value} target="_blank" rel="noopener noreferrer" style={{ color: "#8ab4ff" }}>{value}</a> : value}
              </dd>
            </React.Fragment>)}
          </dl>
          {copied === "failed" && <textarea readOnly value={reportText} aria-label={translateUi("Report text")} onFocus={(event) => event.currentTarget.select()} autoFocus
            style={{ width: "100%", minHeight: "140px", marginTop: "14px", padding: "10px", borderRadius: "10px", border: "1px solid #444", background: "#0b0b0b", color: "#e0e4eb", fontSize: "12px", fontFamily: "ui-monospace, SFMono-Regular, Consolas, monospace" }} />}
        </div>

        <button
          onClick={handleCopyAndRedirect}
          style={{
            width: "100%",
            padding: "16px 22px",
            borderRadius: "14px",
            background: copied === "copied" ? "#10b981" : "#5865f2",
            color: "#ffffff",
            fontSize: "16px",
            fontWeight: 650,
            border: "none",
            cursor: "pointer",
            transition: "all 0.2s ease",
            boxShadow: copied === "copied"
              ? "0 8px 24px rgba(16, 185, 129, 0.4)"
              : "0 8px 24px rgba(88, 101, 242, 0.4)",
            marginBottom: "16px"
          }}
        >
          {copied === "copied" ? translateUi("Copied. Opening Discord…") : copied === "failed" ? translateUi("Couldn't copy: select the text above") : translateUi("Copy report and open Discord")}
        </button>
        </>}
        {copied === "failed" && <p role="alert" style={{ margin: "0 0 12px", color: "#ffb4ab", fontSize: "14px" }}>{translateUi("Your browser blocked copying. Select the report text, copy it, then open Discord.")}</p>}

        <div>
          <a
            href={DISCORD_URL}
            style={{
              color: "#a0a6b2",
              fontSize: "14px",
              textDecoration: "none",
              display: "inline-block",
              padding: "6px 12px"
            }}
          >
            {bare ? translateUi("Open the ARVIO Discord") : translateUi("Open Discord without copying")} &rarr;
          </a>
        </div>
      </div>
    </div>
  );
}

export default function ReportPage() {
  // Outside the app shell: follow the browser language.
  const [language, setLanguage] = useState("en");
  useEffect(() => setLanguage(navigator.language || "en"), []);
  return (
    <LanguageProvider language={language}>
      <Suspense fallback={<div style={{ minHeight: "100vh", background: "#050505" }} />}>
        <ReportContent />
      </Suspense>
    </LanguageProvider>
  );
}
