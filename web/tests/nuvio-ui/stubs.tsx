import type { ReactNode } from "react";

const snapshot = {
  email: "demo@nuvio.example",
  warnings: ["Nuvio did not return collections for profiles 1 - retry the import."],
  profiles: [{ profileId: 1, name: "Main", addons: [], plugins: [], collectionsJson: null, homeCatalogSettings: null }]
};

export const authClient = { session: { email: "demo@arvio.example" } };
export const defaultSettings = {};
export const CONTENT_LANGUAGE_OPTIONS = [["en-US", "English"]];
export const LanguageProvider = ({ children }: { children: ReactNode }) => children;
export const useTranslation = () => (value: string) => value;
export const loadStored = (_key: string, fallback: unknown) => fallback;
export const saveStored = () => {};
export const installAddon = async () => { throw new Error("Unexpected addon installation"); };
export const normalizeAddons = (value: unknown) => value;
export const saveCloudAddons = async () => { throw new Error("Unexpected addon write"); };
export const OFFICIAL_NUVIO_URL = "https://nuvio.invalid";
export const discoverNuvioBackend = async () => ({});
export const signInToNuvio = async () => ({});
export const fetchNuvioSnapshot = async () => snapshot;
export const defaultChoices = () => [{ nuvioProfileId: 1, target: { kind: "create" } }];
export const reconcileChoices = (_snapshot: unknown, _profiles: unknown, choices: unknown) => choices;
export const summarizePlan = () => snapshot.profiles;

let reads = 0;
const controls = {
  imports: 0,
  resolveProfiles: null as null | (() => void)
};
Object.assign(window, { nuvioTest: controls });
export const pullCloudProfiles = async () => {
  reads += 1;
  if (reads === 1) throw new Error("Account unavailable");
  if (reads === 2) await new Promise<void>(resolve => { controls.resolveProfiles = resolve; });
  return { profiles: [{ id: "p1", name: "Main" }], activeProfileId: "p1" };
};
export const pullCloudPayload = async () => ({ addons: [] });
export const applyNuvioImport = async () => {
  controls.imports += 1;
  return {
    profiles: [{ id: "p1", name: "Main" }],
    summaries: [{ profileName: "Main", created: false, addons: 0, addonsFailed: 0, addonsDisabled: 0, collections: 0, plugins: 0 }]
  };
};
