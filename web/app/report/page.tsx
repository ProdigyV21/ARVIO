"use client";

import React, { Suspense, useEffect, useState } from "react";
import { useSearchParams } from "next/navigation";

const DISCORD_URL = "https://discord.gg/UavuEYMfQ4";

function ReportContent() {
  const searchParams = useSearchParams();
  const id = searchParams.get("id") || "N/A";
  const version = searchParams.get("v") || searchParams.get("version") || "1.0";
  const error = searchParams.get("err") || searchParams.get("error") || "Unexpected crash";
  const timeParam = searchParams.get("t") || searchParams.get("time");

  const [copied, setCopied] = useState(false);
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

  const sentryLink = id !== "N/A" ? `https://sentry.io/issues/?query=id%3A${id}` : "N/A";

  const reportText = `**🚨 ARVIO Crash Report**
**Crash ID:** \`${id}\`
**Sentry Link:** ${sentryLink}
**Version:** ${version}
**Time:** ${timeStr}
**Error:** ${error}`;

  const handleCopyAndRedirect = async () => {
    try {
      if (navigator.clipboard) {
        await navigator.clipboard.writeText(reportText);
      } else {
        const textArea = document.createElement("textarea");
        textArea.value = reportText;
        document.body.appendChild(textArea);
        textArea.select();
        document.execCommand("copy");
        document.body.removeChild(textArea);
      }
    } catch {
      // Fallback ignore
    }

    setCopied(true);
    setTimeout(() => {
      window.location.href = DISCORD_URL;
    }, 700);
  };

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
          ARVIO Crash Diagnostics
        </h1>
        <p style={{ margin: "0 0 24px", color: "#a0a6b2", fontSize: "15px", lineHeight: 1.5 }}>
          Tap below to instantly copy this bug report to your phone clipboard and open the ARVIO Discord bug channel.
        </p>

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
            Formatted Bug Report
          </div>
          <pre
            style={{
              margin: 0,
              fontFamily: "ui-monospace, SFMono-Regular, Consolas, monospace",
              fontSize: "13px",
              color: "#e0e4eb",
              lineHeight: 1.6,
              whiteSpace: "pre-wrap",
              wordBreak: "break-word"
            }}
          >
            {reportText}
          </pre>
        </div>

        <button
          onClick={handleCopyAndRedirect}
          style={{
            width: "100%",
            padding: "16px 22px",
            borderRadius: "14px",
            background: copied ? "#10b981" : "#5865f2",
            color: "#ffffff",
            fontSize: "16px",
            fontWeight: 650,
            border: "none",
            cursor: "pointer",
            transition: "all 0.2s ease",
            boxShadow: copied
              ? "0 8px 24px rgba(16, 185, 129, 0.4)"
              : "0 8px 24px rgba(88, 101, 242, 0.4)",
            marginBottom: "16px"
          }}
        >
          {copied ? "Copied! Redirecting to Discord..." : "Copy Report & Open Discord"}
        </button>

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
            Open Discord Without Copying &rarr;
          </a>
        </div>
      </div>
    </div>
  );
}

export default function ReportPage() {
  return (
    <Suspense fallback={<div style={{ minHeight: "100vh", background: "#050505" }} />}>
      <ReportContent />
    </Suspense>
  );
}
