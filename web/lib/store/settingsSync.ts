// App store (lib/store.tsx): updateSettings and the settings autosave / outbox retry.
import { hasPendingAddons, flushAddonOutbox } from "../addonOutbox";
import { flushWatchlistOutbox } from "../watchlistOutbox";
import { useCallback, useEffect } from "react";
import { savePlaylists } from "../iptv";
import { saveStored } from "../storage";
import { flushSettingsOutbox, hasPendingSettings, queueSettings } from "../settingsOutbox";
import type { AppSettings } from "../types";
import { settingsKey } from "./settings";
import { authClient } from "./clients";
import type { StoreCore } from "./core";
import { personalManagedPatch } from "../managedConfiguration";

export function useSettingsAutosave(store: StoreCore) {
  const { activeProfileId, activeProfileIdRef, cloudProfilesHydrated, lastSyncedSettingsRef, profiles, setSettingsSyncState, setToast, personalSettings: settings } = store;
  useEffect(() => {
    saveStored(settingsKey, settings);
    savePlaylists(settings.iptvPlaylists);
    if (!authClient.session || !activeProfileId) { setSettingsSyncState("local"); return; }
    if (authClient.session && !cloudProfilesHydrated) return;
    // Skip the cloud push when settings + active profile still match what we
    // last synced from the cloud — otherwise every boot echoes the just-pulled
    // settings straight back, a wasted account-sync-push per user per session. A
    // genuine change (user toggled a setting, switched profile) differs from the
    // snapshot and still saves.
    const snapshot = JSON.stringify({ settings, activeProfileId });
    if (lastSyncedSettingsRef.current !== null && snapshot === lastSyncedSettingsRef.current && !hasPendingSettings(authClient, activeProfileId)) {
      setSettingsSyncState(hasPendingSettings(authClient) ? "pending" : "saved");
      return;
    }
    // Persist immediately; only the network flush is debounced.
    // Compare only this profile's acknowledged values, preserving other devices' edits.
    let baseline: AppSettings | null = null;
    try {
      if (lastSyncedSettingsRef.current) {
        const parsed = JSON.parse(lastSyncedSettingsRef.current) as { settings?: AppSettings; activeProfileId?: string | null };
        if (parsed.activeProfileId === activeProfileId && parsed.settings) baseline = parsed.settings;
      }
    } catch {
      baseline = null;
    }
    // Profile hydration is asynchronous. Until this profile has an acknowledged
    // baseline, settings still belong to the previous profile or browser defaults.
    if (!baseline && !hasPendingSettings(authClient, activeProfileId)) return;
    const accountId = authClient.session?.userId;
    const submitted = { settings, activeProfileId };
    setSettingsSyncState("pending");
    try { if (baseline) queueSettings(authClient, activeProfileId, settings, baseline); }
    catch (error) { setSettingsSyncState("error"); setToast(error instanceof Error ? error.message : "Could not save settings"); return; }
    const handle = setTimeout(() => {
      void flushSettingsOutbox(authClient).then(() => {
        if (authClient.session?.userId !== accountId || activeProfileIdRef.current !== activeProfileId) return;
        lastSyncedSettingsRef.current = JSON.stringify(submitted);
        setSettingsSyncState(hasPendingSettings(authClient) ? "pending" : "saved");
      }).catch(() => setSettingsSyncState("error"));
    }, 1200);
    return () => clearTimeout(handle);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [settings, activeProfileId, profiles, cloudProfilesHydrated]);

  useEffect(() => {
    const retry = () => {
      if (hasPendingAddons(authClient)) void flushAddonOutbox(authClient).catch(() => undefined);
      void flushWatchlistOutbox(authClient).catch(() => undefined);
      if (!hasPendingSettings(authClient)) return;
      setSettingsSyncState("pending");
      void flushSettingsOutbox(authClient).then(() => setSettingsSyncState(hasPendingSettings(authClient) ? "pending" : "saved")).catch(() => setSettingsSyncState("error"));
    };
    window.addEventListener("online", retry);
    const timer = window.setInterval(retry, 30_000);
    return () => { window.removeEventListener("online", retry); window.clearInterval(timer); };
  }, []);
  return {  };
}

export function useUpdateSettings(store: StoreCore) {
  const { activeProfileIdRef, setSettings, setSettingsSyncState, setToast, settingsRef, personalSettingsRef, managed } = store;
  const updateSettings = useCallback((patch: Partial<AppSettings>) => {
    try { managed.recordPreferences(patch); }
    catch (error) { setToast(error instanceof Error ? error.message : "This preference is managed by your administrator."); return; }
    const previous = personalSettingsRef.current;
    const next = { ...previous, ...personalManagedPatch(patch) };
    const profileId = activeProfileIdRef.current;
    // Record the explicit user edit immediately, even before the first cloud
    // pull finishes. The autosave effect cannot queue without a hydrated baseline.
    // Using the pre-edit values here describes only this action, not browser defaults.
    if (authClient.session && profileId) {
      try {
        queueSettings(authClient, profileId, next, previous);
        setSettingsSyncState("pending");
      } catch (error) {
        setSettingsSyncState("error");
        setToast(error instanceof Error ? error.message : "Could not save settings");
      }
    }
    personalSettingsRef.current = next;
    settingsRef.current = managed.applySettings(next);
    saveStored(settingsKey, next);
    setSettings(next);
  }, [managed.recordPreferences]);
  return { updateSettings };
}
