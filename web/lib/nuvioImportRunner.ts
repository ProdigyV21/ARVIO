import { installAddon, normalizeAddons } from "./addons";
import type { AuthClient } from "./auth";
import { defaultCatalogs } from "./catalogs";
import { pullCloudPayload, saveCloudAddons, saveCloudProfiles, saveCloudSettings } from "./cloud";
import { mergeImportedCollections, parseCustomCollections } from "./customCollections";
import {
  applyHomeCatalogSettings, mergeAddonUrls, newProfileFrom,
  type MigrationChoice, type NuvioSnapshot, type ProfileImportSummary
} from "./nuvioMigration";
import { profileColors } from "./profiles";
import type { AppSettings, InstalledAddon, Profile } from "./types";

/**
 * Writes a Nuvio snapshot into an ARVIO account.
 *
 * Everything goes through the cloud payload, which is what the free Android and
 * TV apps sync from — so a user who never pays for the web interface still ends
 * up with their addons, collections and profiles on their devices.
 */
export async function applyNuvioImport(options: {
  auth: AuthClient;
  profiles: Profile[];
  snapshot: NuvioSnapshot;
  choices: MigrationChoice[];
  baseSettings: AppSettings;
  onProgress?: (message: string) => void;
}): Promise<{ summaries: ProfileImportSummary[]; profiles: Profile[] }> {
  const { auth, snapshot, choices, baseSettings, onProgress } = options;
  if (!auth.session) throw new Error("Sign in to your ARVIO account first");

  let profiles = [...options.profiles];
  const targets = new Map<number, { id: string; created: boolean }>();

  // Profiles first: a Nuvio profile with no ARVIO counterpart is created, so a
  // multi-profile Nuvio account arrives complete instead of collapsing into one.
  for (const profile of snapshot.profiles) {
    const choice = choices.find(entry => entry.nuvioProfileId === profile.profileId);
    if (!choice || choice.target.kind === "skip") continue;
    if (choice.target.kind === "existing") {
      targets.set(profile.profileId, { id: choice.target.profileId, created: false });
      continue;
    }
    onProgress?.(`Creating profile ${profile.name}`);
    const id = globalThis.crypto?.randomUUID?.() ?? `p_${Date.now()}_${Math.floor(Math.random() * 1e6)}`;
    const color = profileColors[profiles.length % profileColors.length];
    profiles = [...profiles, newProfileFrom(profile, id, color)];
    targets.set(profile.profileId, { id, created: true });
  }
  if (!targets.size) return { summaries: [], profiles };
  await saveCloudProfiles(auth, profiles, profiles[0]?.id ?? null);

  const summaries: ProfileImportSummary[] = [];
  for (const profile of snapshot.profiles) {
    const target = targets.get(profile.profileId);
    if (!target) continue;
    const summary: ProfileImportSummary = {
      profileName: profile.name, created: target.created, addons: 0, addonsFailed: 0,
      plugins: profile.plugins.length, collections: 0, catalogSettings: false
    };

    onProgress?.(`${profile.name}: reading what is already there`);
    const existing = target.created ? null : await pullCloudPayload(auth, target.id).catch(() => null);
    const currentAddons = normalizeAddons(existing?.addons ?? []);
    const currentCatalogs = existing?.settings?.catalogs?.length ? existing.settings.catalogs : defaultCatalogs;

    // Addons are stored as resolved manifests, so each new URL is fetched once.
    const resolved: InstalledAddon[] = [];
    for (const url of mergeAddonUrls(currentAddons.map(addon => addon.manifestUrl ?? addon.id), profile.addons)) {
      onProgress?.(`${profile.name}: installing ${url}`);
      try {
        resolved.push(await installAddon(url));
        summary.addons += 1;
      } catch {
        // An addon whose server is down must not abort the rest of the import.
        summary.addonsFailed += 1;
      }
    }
    const nextAddons = normalizeAddons([...currentAddons, ...resolved]);

    let nextCatalogs = currentCatalogs;
    if (Array.isArray(profile.collectionsJson) && profile.collectionsJson.length) {
      onProgress?.(`${profile.name}: importing collections`);
      try {
        const imported = await parseCustomCollections(JSON.stringify(profile.collectionsJson));
        nextCatalogs = mergeImportedCollections(nextCatalogs, imported);
        summary.collections = profile.collectionsJson.length;
      } catch {
        summary.collections = 0;
      }
    }
    const orderedCatalogs = applyHomeCatalogSettings(nextCatalogs, profile.homeCatalogSettings);
    summary.catalogSettings = orderedCatalogs !== nextCatalogs;

    onProgress?.(`${profile.name}: saving to your ARVIO account`);
    const settings: AppSettings = { ...baseSettings, ...(existing?.settings ?? {}), catalogs: orderedCatalogs };
    if (resolved.length) await saveCloudAddons(auth, nextAddons, target.id, { changes: undefined });
    await saveCloudSettings(auth, settings, nextAddons, target.id, profiles, null);
    summaries.push(summary);
  }
  return { summaries, profiles };
}
