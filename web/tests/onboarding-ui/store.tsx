import { useSyncExternalStore } from "react";
const params = new URLSearchParams(location.search);
const listeners = new Set<() => void>();
let current = {
  view: "app", section: "home", activeProfile: { id: "onboarding-fixture" }, addonsReady: true,
  settings: { homeServers: [], iptvPlaylists: [] },
  addons: params.get("sources") === "ready"
    ? [{ id: "streams", name: "Fixture sources", resources: ["stream"], catalogs: [], version: "1", manifestUrl: "https://fixture.invalid/manifest.json" }]
    : [{ id: "catalog", name: "Fixture catalog", resources: ["catalog"], catalogs: [], version: "1", manifestUrl: "https://fixture.invalid/manifest.json" }],
  toast: ""
};
function change(next: Partial<typeof current>) { current = { ...current, ...next }; listeners.forEach(listener => listener()); }
export function useAppPick(..._keys: string[]) { return useApp(); }
export function useAppSelector<T>(select: (store: ReturnType<typeof useApp>) => T) { return select(useApp()); }
export function useApp() {
  const state = useSyncExternalStore(callback => { listeners.add(callback); return () => { listeners.delete(callback); }; }, () => current);
  return { ...state,
    setSection: (section: string) => change({ section }),
    setToast: (toast: string) => change({ toast }),
    installAddon: async (manifestUrl: string) => change({ addons: [...current.addons, { id: "installed", name: "Installed fixture", resources: ["stream"], catalogs: [], version: "1", manifestUrl }] }),
    removeAddon: (addon: { id: string }) => change({ addons: current.addons.filter(item => item.id !== addon.id) })
  };
}
