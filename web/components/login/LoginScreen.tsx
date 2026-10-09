"use client";
import { useTranslation } from "@/lib/i18n";


import { useEffect, useState } from "react";
import { ArrowLeft } from "lucide-react";
import { hasNetlifyBackendConfig, hasSupabaseConfig, getAuthPortalUrl } from "@/lib/config";
import { capturePremiumAttribution, TRIAL_INTENT_KEY } from "@/lib/premiumAnalytics";
import { useApp } from "@/lib/store";
import { partnerLoginRedirect } from "@/lib/partnerLinks";

export function LoginScreen() {
  const translateUi = useTranslation();
  const { backToProfiles, cloudLoginRequired } = useApp();
  const cloudConfigured = hasNetlifyBackendConfig() || hasSupabaseConfig();
  const [mounted, setMounted] = useState(false);

  const redirectToAuthPortal = () => {
    if (typeof window === "undefined") return;
    const redirectUri = partnerLoginRedirect(window.location.origin, window.location.search);
    const portalUrl = getAuthPortalUrl();
    window.location.href = `${portalUrl}?redirect_uri=${encodeURIComponent(redirectUri)}`;
  };

  useEffect(() => {
    capturePremiumAttribution();
    if (new URLSearchParams(window.location.search).get("intent") === "trial") {
      try { localStorage.setItem(TRIAL_INTENT_KEY, "1"); } catch { /* storage is optional */ }
    }
    setMounted(true);
  }, []);

  useEffect(() => {
    if (mounted && cloudConfigured) {
      const hash = window.location.hash || "";
      if (!hash.includes("access_token=")) {
        redirectToAuthPortal();
      }
    }
  }, [mounted, cloudConfigured]);

  return (
    <main className="login-shell">
      {!cloudLoginRequired && (
        <button type="button" className="login-back" onClick={backToProfiles} aria-label={translateUi("Back")}><ArrowLeft size={20} /> {translateUi(" Back")}</button>
      )}
      <div className="login-hero">
        <div className="login-copy">
          <div className="login-brand-lockup">
            <img src="/arvio-logo.svg" alt="" className="login-brand-logo" />
            <img src="/arvio-wordmark.svg" alt="ARVIO" className="login-wordmark" />
          </div>
          <p className="login-tag">{translateUi("Cloud sign-in required")}</p>
          <p className="login-sub">{translateUi("Use your ARVIO Cloud account to sync profiles, continue watching, Trakt activity, addons, catalogs, and playback settings across devices.")}</p>
          <div className="login-proof">
            <span>{translateUi("Profiles")}</span>
            <span>{translateUi("Watch history")}</span>
            <span>{translateUi("Addons")}</span>
            <span>{translateUi("Trakt sync")}</span>
          </div>
        </div>

        <div className="login-card">
          <p className="login-card-title">{translateUi("Sign in to continue")}</p>
          {!cloudConfigured && <p className="login-error">{translateUi("ARVIO Cloud backend env is missing. Add values in web/.env.local.")}</p>}
          <button type="button" className="primary login-submit" onClick={redirectToAuthPortal} disabled={!cloudConfigured}>
            {translateUi("Sign In with ARVIO Cloud")}</button>
        </div>
      </div>
    </main>
  );
}
