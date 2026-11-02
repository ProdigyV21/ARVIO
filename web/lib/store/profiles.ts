import { weTrakrClient } from "../wetrakr";
// App store (lib/store.tsx): Profiles: helpers, the cloud pull / local save effects and select, create, edit, delete.
import { applyPendingWatchlist } from "../watchlistOutbox";
import { useCallback, useEffect } from "react";
import { config, getAuthPortalUrl } from "../config";
import { pullCloudProfiles, saveCloudProfiles } from "../cloud";
import { loadStored, saveStored } from "../storage";
import { saveLocalAddons } from "../addons";
import { savePlaylists } from "../iptv";
import { defaultSettings, settingsKey } from "./settings";
import { verifyProfilePin } from "../profilePin";
import { hydratedProfileId } from "../profiles";
import { partnerLoginRedirect } from "../partnerLinks";
import { mdblistClient } from "../mdblist";
import { simklClient } from "../simkl";
import type { Profile } from "../types";
import { authClient, traktClient } from "./clients";
import { cwCacheKeyFor, readCachedList, watchlistCacheKeyFor } from "./listCache";
import type { useRefreshData } from "./refresh";
import type { StoreCore } from "./core";

export const MAX_PROFILES = 5;

/** Saved on this device, but ARVIO Cloud did not take it (the other devices won't see it yet). */
export class ProfileCloudSaveError extends Error {
  readonly profile: Profile;
  constructor(profile: Profile, cause?: unknown) {
    super("Saved on this device, but ARVIO Cloud could not be reached. Try again to sync it to your other devices.");
    this.name = "ProfileCloudSaveError";
    this.profile = profile;
    this.cause = cause;
  }
}
export const PROFILES_KEY = "arvio.web.profiles";
export const ACTIVE_PROFILE_KEY = "arvio.web.activeProfileId";
export const AVATAR_IMAGES_KEY = "arvio.web.avatarImages";
// Which account the locally-cached profiles belong to. Profiles live in a single
// browser-global localStorage key, so without this a second account signing in
// on the same browser would see the FIRST account's profiles (they persist until
// the cloud pull replaces them — and a brand-new account has no cloud profiles to
// replace them with). We stamp the owner email and ignore cached profiles that
// belong to a different account.
export const PROFILES_OWNER_KEY = "arvio.web.profilesOwner";

export function currentAccountEmail(): string {
  return (authClient.session?.email ?? "").trim().toLowerCase();
}

/** Run before adopting a different account, including portal callbacks and signup after logout. */
export function resetDifferentAccount(store: StoreCore, email: string) {
  const next = email.trim().toLowerCase();
  const owner = (loadStored<string | null>(PROFILES_OWNER_KEY, null) ?? "").trim().toLowerCase();
  if (!next || owner === next) return;
  const fresh = [makeProfile("Profile 1", randomProfileColor(), 0)];
  ++store.refreshGenerationRef.current;
  store.refreshKeyRef.current = null;
  store.lastSyncedSettingsRef.current = null;
  store.activeProfileIdRef.current = fresh[0].id;
  store.settingsRef.current = { ...defaultSettings };
  store.setProfiles(fresh);
  store.setActiveProfileId(fresh[0].id);
  store.setAvatarImages({});
  store.setAddons([]);
  store.setAddonsReady(false);
  store.setSettings({ ...defaultSettings });
  store.setContinueWatching([]);
  store.setWatchlist([]);
  store.setCategories([]);
  store.setWatchedKeys(new Set());
  store.cwSourceRef.current = "none";
  saveStored(PROFILES_KEY, fresh);
  saveStored(ACTIVE_PROFILE_KEY, fresh[0].id);
  saveStored(AVATAR_IMAGES_KEY, {});
  saveStored(settingsKey, defaultSettings);
  saveLocalAddons([]);
  savePlaylists([]);
  saveStored(PROFILES_OWNER_KEY, next);
}

// Cached profiles are only trusted when they belong to the signed-in account
// (or when signed out, where local-only profiles are expected).
export function localProfilesMatchAccount(): boolean {
  const owner = (loadStored<string | null>(PROFILES_OWNER_KEY, null) ?? "").trim().toLowerCase();
  const email = currentAccountEmail();
  if (!email) return true; // signed out: local profiles are fine
  return owner === email;
}

export function randomProfileColor() {
  const colors = [0xffe50914, 0xff1db954, 0xff3b82f6, 0xfff59e0b, 0xff8b5cf6, 0xffec4899, 0xff14b8a6, 0xff6366f1];
  return colors[Math.floor(Math.random() * colors.length)];
}

export function makeProfile(name: string, avatarColor: number, avatarId = 0): Profile {
  const now = Date.now();
  return {
    id: (globalThis.crypto?.randomUUID?.() ?? `p_${now}_${Math.floor(Math.random() * 1e6)}`),
    name,
    avatarColor,
    avatarId,
    avatarImageVersion: 0,
    isKidsProfile: false,
    pin: null,
    isLocked: false,
    createdAt: now,
    lastUsedAt: now,
    updatedAt: now
  };
}

export function useProfileSync(store: StoreCore & Pick<ReturnType<typeof useRefreshData>, "refreshData">) {
  const { activeProfileId, activeProfileIdRef, auth, profiles, refreshData, setActiveProfileId, setAvatarImages, setCloudProfilesHydrated, setProfiles } = store;
  useEffect(() => {
    // Never relabel the old account's cache merely because auth changed. Signup
    // after logout and portal callbacks must reset it before the new cloud pull.
    if (authClient.session && !localProfilesMatchAccount()) return;
    saveStored(PROFILES_KEY, profiles);
    saveStored(ACTIVE_PROFILE_KEY, activeProfileId);
    // Keep ownership across logout, so a later signup cannot adopt this cache.
    if (currentAccountEmail()) saveStored(PROFILES_OWNER_KEY, currentAccountEmail());
  }, [profiles, activeProfileId, auth]);

  // When signed in, pull the shared profiles from the same account_sync_state
  // payload Android writes to (cloud wins, matching replaceProfilesFromCloud).
  useEffect(() => {
    if (!authClient.session) {
      setCloudProfilesHydrated(true);
      return;
    }
    resetDifferentAccount(store, currentAccountEmail());
    const accountId = authClient.session.userId;
    let cancelled = false;
    void pullCloudProfiles(authClient)
      .then((cloud) => {
        if (cancelled || authClient.session?.userId !== accountId) return;
        if (cloud.profiles.length) {
          setProfiles(cloud.profiles);
          setAvatarImages(cloud.avatarImages);
          // Read the current selection when the request completes: a user may
          // have chosen a profile while this older cloud snapshot was loading.
          const selectedId = hydratedProfileId(activeProfileIdRef.current, cloud.profiles, cloud.activeProfileId);
          activeProfileIdRef.current = selectedId;
          setActiveProfileId(selectedId);
          void refreshData(selectedId);
        } else {
          // New account with no cloud profiles yet. If the local profiles were
          // stamped for a DIFFERENT account, they leaked from a previous
          // session — replace them with one clean profile.
          if (!localProfilesMatchAccount()) {
            const fresh = [makeProfile("Profile 1", randomProfileColor(), 0)];
            setProfiles(fresh);
            setActiveProfileId(fresh[0].id);
            saveStored(PROFILES_OWNER_KEY, currentAccountEmail());
            void refreshData(fresh[0].id);
          } else {
            void refreshData(activeProfileIdRef.current);
          }
        }
        setCloudProfilesHydrated(true);
      })
      .catch(() => {
        if (!cancelled && authClient.session?.userId === accountId) setCloudProfilesHydrated(true);
      });
    return () => {
      cancelled = true;
    };
  }, [auth, refreshData]);
  return {  };
}

export function useProfileActions(store: StoreCore & Pick<ReturnType<typeof useRefreshData>, "refreshData">) {
  const { activeProfileId, activeProfileIdRef, cloudProfilesHydrated, cwSourceRef, profiles, refreshData, setActiveProfileId, setCategories, setContinueWatching, setDeviceCode, setManageMode, setMdblistConnected, setProfiles, setSection, setSimklConnected, setSimklDeviceCode, setToast, setTraktConnected, setView, setWatchedKeys, setWatchlist } = store;
  // The cloud save merges into the account's list (saveCloudProfiles), so a list cached
  // in this browser can no longer delete profiles; only [deletedIds] remove one.
  const persistProfiles = useCallback((next: Profile[], activeId: string | null, deletedIds: string[] = []) => {
    activeProfileIdRef.current = activeId;
    setProfiles(next);
    setActiveProfileId(activeId);
    saveStored(PROFILES_KEY, next);
    saveStored(ACTIVE_PROFILE_KEY, activeId);
    // Callers that edit a profile await this to report a failed cloud save.
    const saved = saveCloudProfiles(authClient, next, activeId, deletedIds);
    void saved.catch(() => undefined);
    return saved;
  }, []);

  const selectProfile = useCallback(async (profile: Profile, pin?: string) => {
    // Until the account's profiles arrive, this browser shows the list it cached on its last
    // visit; picking from it would act on a list that may be out of date.
    if (authClient.session && !cloudProfilesHydrated) {
      setToast("Loading your profiles, try again in a moment.");
      return;
    }
    if (profile.isLocked && profile.pin && !await verifyProfilePin(pin ?? "", profile.pin)) {
      setToast("Unlock this profile with its PIN.");
      return;
    }
    const updated = profiles.map((p) => (p.id === profile.id ? { ...p, lastUsedAt: Date.now() } : p));
    const switching = profile.id !== activeProfileId;
    persistProfiles(updated, profile.id);
    traktClient.setProfile(profile.id);
    mdblistClient.setProfile(profile.id);
    simklClient.setProfile(profile.id);
    weTrakrClient.setProfile(profile.id);
    setTraktConnected(traktClient.isConnected);
    setMdblistConnected(mdblistClient.isConnected);
    setSimklConnected(simklClient.isConnected);
    setManageMode(false);
    if (switching) {
      setDeviceCode(null);
      setSimklDeviceCode(null);
      // Drop the previous profile's rows and seed from the new profile's cache
      // so its Continue Watching paints instantly. refreshKeyRef (updated by the
      // refreshData call below) invalidates any in-flight refresh for the old
      // profile before it can write its rows over these.
      const seededCw = readCachedList(cwCacheKeyFor(profile.id));
      cwSourceRef.current = seededCw.length ? "seed" : "none";
      setContinueWatching(seededCw);
      setCategories(seededCw.length ? [{ id: "continue_watching", title: "Continue Watching", items: seededCw }] : []);
      setWatchlist(applyPendingWatchlist(authClient, profile.id, readCachedList(watchlistCacheKeyFor(profile.id))));
      setWatchedKeys(new Set());
    }
    setView("app");
    setSection("home");
    void refreshData(profile.id);
  }, [profiles, activeProfileId, persistProfiles, refreshData, cloudProfilesHydrated]);

  // Profile edits save on this device first; a failed cloud save rejects with
  // ProfileCloudSaveError (carrying the saved profile) so the dialog can say so
  // and offer to retry instead of closing as if it had synced.
  const createProfile = useCallback(async (name: string, avatarColor: number, avatarId: number, extra?: Partial<Profile>) => {
    if (profiles.length >= MAX_PROFILES) throw new Error("You can have up to 5 profiles.");
    const profile = { ...makeProfile(name || "Profile", avatarColor || randomProfileColor(), avatarId), ...extra };
    try { await persistProfiles([...profiles, profile], activeProfileId); }
    catch (error) { throw new ProfileCloudSaveError(profile, error); }
    return profile;
  }, [profiles, activeProfileId, persistProfiles]);

  const updateProfileAction = useCallback(async (profile: Profile) => {
    const edited = { ...profile, updatedAt: Date.now() };
    try { await persistProfiles(profiles.map((p) => (p.id === profile.id ? edited : p)), activeProfileId); }
    catch (error) { throw new ProfileCloudSaveError(edited, error); }
    return edited;
  }, [profiles, activeProfileId, persistProfiles]);

  const deleteProfileAction = useCallback(async (id: string) => {
    const next = profiles.filter((p) => p.id !== id);
    const removed = profiles.find((p) => p.id === id);
    try { await persistProfiles(next, activeProfileId === id ? null : activeProfileId, [id]); }
    catch (error) { if (removed) throw new ProfileCloudSaveError(removed, error); throw error; }
  }, [profiles, activeProfileId, persistProfiles]);

  const switchProfile = useCallback(() => {
    setManageMode(false);
    setView("profiles");
  }, []);

  const goToLogin = useCallback(() => {
    if (config.selfHosted) { setView("profiles"); return; }
    if (typeof window !== "undefined") {
      const redirectUri = partnerLoginRedirect(window.location.origin, window.location.search);
      const portalUrl = getAuthPortalUrl();
      window.location.href = `${portalUrl}?redirect_uri=${encodeURIComponent(redirectUri)}`;
    }
  }, []);
  const backToProfiles = useCallback(() => setView("profiles"), []);
  return { persistProfiles, selectProfile, createProfile, updateProfileAction, deleteProfileAction, switchProfile, goToLogin, backToProfiles };
}
