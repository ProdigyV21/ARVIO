// App store (lib/store.tsx): Sign-in / sign-out and the ARVIO auth portal callback (#access_token=...).
import { useCallback, useEffect } from "react";
import { SESSION_KEY, decodeJwtPayload } from "../auth";
import { config } from "../config";
import { loadStored, saveStored } from "../storage";
import type { Profile } from "../types";
import { authClient } from "./clients";
import { ACTIVE_PROFILE_KEY, PROFILES_KEY, resetDifferentAccount } from "./profiles";
import type { StoreCore } from "./core";
import type { useRefreshData } from "./refresh";

export function useAuthCallback(store: StoreCore) {
  const { setAuth, setCloudProfilesHydrated, setView, settings } = store;
  useEffect(() => {
    if (typeof window === "undefined") return;
    const hash = window.location.hash || "";
    if (hash.includes("access_token=") && hash.includes("refresh_token=")) {
      if (config.selfHosted) {
        window.history.replaceState({}, document.title, window.location.pathname + window.location.search);
        return;
      }
      try {
        const params = new URLSearchParams(hash.replace(/^#/, ""));
        const access_token = params.get("access_token");
        const refresh_token = params.get("refresh_token");
        const email = params.get("email") || "";
        const expires_in = Number(params.get("expires_in") || "3600");
        if (access_token && refresh_token) {
          const payload = decodeJwtPayload(access_token);
          const userId = (payload.sub as string | undefined) ?? "";
          const provider = ((payload.iss as string | undefined) === "arvio-netlify" ? "netlify" : "supabase") as "netlify" | "supabase";
          const session = {
            accessToken: access_token,
            refreshToken: refresh_token,
            userId,
            email: typeof payload.email === "string" ? payload.email : email,
            expiresAt: Date.now() + expires_in * 1000,
            provider
          };

          resetDifferentAccount(store, session.email);
          saveStored(SESSION_KEY, session);
          authClient.session = session;
          setAuth(session);
          setCloudProfilesHydrated(false);

          // Clear hash parameters from URL without a page reload
          const cleanUrl = window.location.pathname + window.location.search;
          window.history.replaceState({}, document.title, cleanUrl);

          // Redirect to appropriate view
          const stored = loadStored<Profile[]>(PROFILES_KEY, []);
          const activeId = loadStored<string | null>(ACTIVE_PROFILE_KEY, null);
          const skip = settings.skipProfileSelection;
          if (skip && activeId && stored.some((p) => p.id === activeId && !p.isLocked)) {
            setView("app");
          } else {
            setView("profiles");
          }
        }
      } catch (err) {
        console.error("Failed to parse callback auth parameters", err);
      }
    }
  }, [settings.skipProfileSelection]);
  return {  };
}

export function useAccountActions(store: StoreCore & Pick<ReturnType<typeof useRefreshData>, "refreshData">) {
  const { activeProfileId, refreshData, setAuth, setBusy, setCloudProfilesHydrated, setToast } = store;
  const signIn = useCallback(async (email: string, password: string, mode: "sign-in" | "sign-up") => {
    const trimmedEmail = email.trim();
    if (!trimmedEmail || !password) throw new Error("Enter your email and password.");
    setBusy(mode === "sign-up" ? "Creating account" : "Signing in");
    setToast(null);
    try {
      const session = mode === "sign-up" ? await authClient.signUp(trimmedEmail, password) : await authClient.signIn(trimmedEmail, password);
      // Signing into a DIFFERENT account on this browser: drop the previous
      // account's in-memory + cached profiles so they don't show through while
      // the new account's cloud profiles load. A brand-new account then starts
      // with one clean profile instead of inheriting the old ones.
      resetDifferentAccount(store, session.email);
      setCloudProfilesHydrated(false);
      setAuth(session);
    } catch (error) {
      const message = error instanceof Error ? error.message : "Authentication failed.";
      setToast(message);
      throw error;
    } finally {
      setBusy("");
    }
  }, [refreshData, activeProfileId]);

  const signOut = useCallback(() => {
    authClient.signOut();
    setAuth(null);
    setCloudProfilesHydrated(true);
    setToast("Signed out of ARVIO Cloud.");
  }, []);
  return { signIn, signOut };
}
