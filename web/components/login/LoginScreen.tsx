"use client";

import { ArrowLeft } from "lucide-react";
import { useState } from "react";
import { hasNetlifyBackendConfig, hasSupabaseConfig } from "@/lib/config";
import { useApp } from "@/lib/store";

export function LoginScreen() {
  const { signIn, backToProfiles, cloudLoginRequired } = useApp();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isSignUp, setIsSignUp] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);
  const cloudConfigured = hasNetlifyBackendConfig() || hasSupabaseConfig();

  const submit = async () => {
    if (!email.trim() || !password) {
      setError("Enter your email and password.");
      return;
    }
    setLoading(true);
    setError(null);
    try {
      await signIn(email.trim(), password, isSignUp ? "sign-up" : "sign-in");
      backToProfiles();
    } catch (err) {
      setError(err instanceof Error ? err.message : "Authentication failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <main className="login-shell">
      {!cloudLoginRequired && (
        <button type="button" className="login-back" onClick={backToProfiles} aria-label="Back"><ArrowLeft size={20} /> Back</button>
      )}
      <div className="login-hero">
        <div className="login-copy">
          <div className="login-brand-lockup">
            <img src="/arvio-logo.svg" alt="" className="login-brand-logo" />
            <img src="/arvio-wordmark.svg" alt="ARVIO" className="login-wordmark" />
          </div>
          <p className="login-tag">Cloud sign-in required</p>
          <p className="login-sub">Use your ARVIO Cloud account to sync profiles, continue watching, Trakt activity, addons, catalogs, and playback settings across devices.</p>
          <div className="login-proof">
            <span>Profiles</span>
            <span>Watch history</span>
            <span>Addons</span>
            <span>Trakt sync</span>
          </div>
        </div>

        <div className="login-card">
          <p className="login-card-title">{isSignUp ? "Create your account" : "Sign in to continue"}</p>
          {!cloudConfigured && <p className="login-error">ARVIO Cloud backend env is missing. Add values in web/.env.local.</p>}
          {error && <p className="login-error">{error}</p>}
          <input
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            placeholder="Email"
            type="email"
            autoFocus
            onKeyDown={(event) => event.key === "Enter" && submit()}
          />
          <input
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            placeholder="Password"
            type="password"
            onKeyDown={(event) => event.key === "Enter" && submit()}
          />
          <button type="button" className="primary login-submit" onClick={submit} disabled={loading || !cloudConfigured}>
            {loading ? "Please wait…" : isSignUp ? "Sign Up" : "Sign In"}
          </button>
          <button type="button" className="login-toggle" onClick={() => setIsSignUp(!isSignUp)} disabled={loading}>
            {isSignUp ? "Already have an account? Sign In" : "Don't have an account? Sign Up"}
          </button>
        </div>
      </div>
    </main>
  );
}
