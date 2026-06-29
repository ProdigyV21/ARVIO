"use client";

import { ArrowLeft } from "lucide-react";
import { useState } from "react";
import { hasSupabaseConfig } from "@/lib/config";
import { useApp } from "@/lib/store";

export function LoginScreen() {
  const { signIn, backToProfiles } = useApp();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isSignUp, setIsSignUp] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);

  const submit = async () => {
    if (!email.trim() || !password) return;
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
      <button className="login-back" onClick={backToProfiles} aria-label="Back"><ArrowLeft size={20} /> Back</button>
      <div className="login-hero">
        <div className="login-copy">
          <h1 className="login-logo">ARVIO</h1>
          <p className="login-tag">Your library, tuned for TV.</p>
          <p className="login-sub">Keep your watchlist, history, and Trakt sync tied to your account.</p>
        </div>

        <div className="login-card">
          <p className="login-card-title">{isSignUp ? "Create your account" : "Sign in to continue"}</p>
          {!hasSupabaseConfig() && <p className="login-error">Supabase env is missing. Add values in web/.env.local.</p>}
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
          <button className="primary login-submit" onClick={submit} disabled={loading}>
            {loading ? "Please wait…" : isSignUp ? "Sign Up" : "Sign In"}
          </button>
          <button className="login-toggle" onClick={() => setIsSignUp(!isSignUp)} disabled={loading}>
            {isSignUp ? "Already have an account? Sign In" : "Don't have an account? Sign Up"}
          </button>
        </div>
      </div>
    </main>
  );
}
