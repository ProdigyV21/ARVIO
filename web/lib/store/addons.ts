// App store (lib/store.tsx): Installing, removing and enabling addons, and loading one catalog row.
import { queueAddons, flushAddonOutbox } from "../addonOutbox";
import { useCallback } from "react";
import { installAddon as installAddonManifest, loadLocalAddons, normalizeAddons, saveLocalAddons } from "../addons";
import { AddonAlreadyInstalledError, addonInstanceId, addonManifestId, applyAddonInstall, duplicateAddons, isSameInstall, type AddonInstallMode } from "../addonInstall";
import { loadCatalog } from "../tmdb";
import type { InstalledAddon, CatalogConfig } from "../types";
import { authClient } from "./clients";
import type { useUpdateSettings } from "./settingsSync";
import type { StoreCore } from "./core";

export function useAddonActions(store: StoreCore & Pick<ReturnType<typeof useUpdateSettings>, "updateSettings">) {
  const { activeProfileId, addonsRef, personalAddonsRef, managed, setAddons, settings, settingsRef, updateSettings } = store;
  const persistAddons = useCallback(async (next: InstalledAddon[], options: { removedIds?: string[]; addedIds?: string[]; onLocalSave?: () => void } = {}) => {
    const delivery = managed.ref.current.delivery;
    if (delivery?.active && !delivery.permissions?.addConnections) throw new Error("Connections are managed by your administrator.");
    const assigned = managed.ref.current.resources?.addons || [];
    if (options.removedIds?.some(id => assigned.some(addon => addon.id === id))) throw new Error("This connection is managed by your administrator.");
    const now = Date.now();
    const previous = new Map(personalAddonsRef.current.map(addon => [addon.id, addon]));
    const normalized = normalizeAddons(next.filter(addon => !addon.managedOrganizationId).map(addon => {
      const old = previous.get(addon.id);
      const changed = old && (old.name !== addon.name || old.manifestUrl !== addon.manifestUrl || old.enabled !== addon.enabled);
      return changed ? { ...addon, isEnabled: addon.enabled !== false,
        setupUpdatedAt: Math.max(now, Number(old.setupUpdatedAt || 0) + 1) } : addon;
    }));
    queueAddons(authClient, normalized, activeProfileId, options.removedIds, options.addedIds);
    saveLocalAddons(normalized);
    if (!authClient.session && JSON.stringify(loadLocalAddons()) !== JSON.stringify(normalized)) throw new Error("Device storage is full. Keep this page open and retry saving.");
    personalAddonsRef.current = normalized;
    addonsRef.current = [...assigned, ...normalized.filter(addon => !assigned.some(value => value.id === addon.id))];
    setAddons(normalized);
    options.onLocalSave?.();
    // Cloud writes are union-based; a removal must be an explicit id list so the
    // shared library can never be shrunk by a stale/partial in-memory view.
    try { await flushAddonOutbox(authClient); }
    catch { throw new Error("Addons saved on this device. Cloud sync is pending and will retry automatically."); }
  }, [activeProfileId]);

  const loadCatalogRow = useCallback((catalog: CatalogConfig) => loadCatalog(catalog, settings.language, addonsRef.current, settingsRef.current.homeServers), [settings.language]);

  // An addon that is already installed (same manifest id or link) is never
  // replaced silently: without a mode this throws AddonAlreadyInstalledError
  // and the Addons screen asks "Replace / Keep both" (Android parity).
  const installAddon = useCallback(async (source: string | InstalledAddon, options: { mode?: AddonInstallMode } = {}) => {
    const fetched = typeof source === "string" ? await installAddonManifest(source) : source;
    const current = personalAddonsRef.current;
    if (managed.ref.current.resources?.addons.some(addon => addon.id === fetched.id)) throw new Error("This connection is managed by your administrator.");
    const existing = duplicateAddons(current, fetched);
    if (existing.length && !options.mode) throw new AddonAlreadyInstalledError(fetched, existing);
    let candidate = fetched;
    if (existing.length && options.mode === "keep-both") {
      if (isSameInstall(current, fetched)) throw new Error("This exact addon link is already installed.");
      candidate = { ...fetched, id: await addonInstanceId(addonManifestId(fetched), fetched.manifestUrl) };
    }
    const { next, removedIds } = applyAddonInstall(current, candidate, existing.length && options.mode ? options.mode : "new");
    await persistAddons(next, { addedIds: [candidate.id], removedIds });
    return candidate;
  }, [persistAddons]);

  const removeAddon = useCallback(async (addon: InstalledAddon) => {
    const next = addonsRef.current.filter((candidate) => candidate.id !== addon.id);
    await persistAddons(next, { removedIds: [addon.id] });
  }, [persistAddons]);

  const setAddonsState = useCallback(async (next: InstalledAddon[]) => {
    await persistAddons(next, { onLocalSave: () => updateSettings({
      disabledAddonIds: next.filter((addon) => !addon.managedOrganizationId && addon.enabled === false).map((addon) => addon.id)
    }) });
  }, [persistAddons, updateSettings]);
  return { persistAddons, loadCatalogRow, installAddon, removeAddon, setAddonsState };
}
