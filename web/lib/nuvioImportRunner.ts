import { installAddon, normalizeAddon, normalizeAddons } from "./addons";
import type { AuthClient } from "./auth";
import { defaultCatalogs } from "./catalogs";
import { addCloudProfiles, invalidateRawPayloadCache, pullCloudPayload, pullCloudProfiles, saveCloudAddons, saveCloudSettings } from "./cloud";
import { mergeImportedCollections, parseCustomCollections } from "./customCollections";
import {
  addonsToInstall, applyHomeCatalogSettings, newProfileFrom,
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
 *
 * The import owns exactly one field: the profile's catalog list. Everything
 * else in the account — theme, AI subtitle settings, IPTV playlists — is read
 * first and handed straight back as the baseline, so `saveCloudSettings` can
 * see that this session changed nothing there and leaves those fields alone.
 * A profile whose current state cannot be read is therefore not importable:
 * writing without that baseline would assert defaults over real data.
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

  if (!choices.some(choice => choice.target.kind !== "skip")) return { summaries: [], profiles: options.profiles };
  invalidateRawPayloadCache();
  let profiles = [...(await pullCloudProfiles(auth)).profiles];
  const additions: Profile[] = [];
  const targets = new Map<number, { id: string; created: boolean }>();

  // Profiles first: a Nuvio profile with no ARVIO counterpart is created, so a
  // multi-profile Nuvio account arrives complete instead of collapsing into one.
  for (const profile of snapshot.profiles) {
    const choice = choices.find(entry => entry.nuvioProfileId === profile.profileId);
    if (!choice || choice.target.kind === "skip") continue;
    if (choice.target.kind === "existing") {
      const profileId = choice.target.profileId;
      if (!profiles.some(entry => entry.id === profileId)) {
        throw new Error("An ARVIO profile is no longer available. Reload your account and choose again.");
      }
      targets.set(profile.profileId, { id: profileId, created: false });
      continue;
    }
    onProgress?.(`Creating profile ${profile.name}`);
    const id = globalThis.crypto?.randomUUID?.() ?? `p_${Date.now()}_${Math.floor(Math.random() * 1e6)}`;
    const color = profileColors[profiles.length % profileColors.length];
    const created = newProfileFrom(profile, id, color);
    profiles = [...profiles, created];
    additions.push(created);
    targets.set(profile.profileId, { id, created: true });
  }
  if (!targets.size) return { summaries: [], profiles };
  if (additions.length) profiles = await addCloudProfiles(auth, additions);

  const summaries: ProfileImportSummary[] = [];
  for (const profile of snapshot.profiles) {
    const target = targets.get(profile.profileId);
    if (!target) continue;
    const summary: ProfileImportSummary = {
      profileName: profile.name, created: target.created, addons: 0, addonsFailed: 0,
      addonsDisabled: 0, plugins: profile.plugins.length, collections: 0, catalogSettings: false
    };

    onProgress?.(`${profile.name}: reading what is already there`);
    // A read failure is not an empty profile. Stop here and write nothing: the
    // alternative is asserting default settings over an account we cannot see.
    // A newly created profile is read too — the account-wide settings behind it
    // (theme, AI key, IPTV) are shared and must survive the same way.
    let existing;
    try {
      invalidateRawPayloadCache();
      existing = await pullCloudPayload(auth, target.id);
    } catch (failure) {
      const message = (failure as { message?: unknown } | null)?.message;
      summary.failed = typeof message === "string" && message ? message : "Could not read this profile from your account";
      summaries.push(summary);
      continue;
    }
    const currentAddons = normalizeAddons(existing.addons ?? []);
    // `base` is the account exactly as it stands. It is both what the write is
    // built from and the baseline it is compared against, which is what keeps
    // every field except `catalogs` out of the write.
    const base: AppSettings = { ...baseSettings, ...(existing.settings ?? {}) };
    if (!Array.isArray(base.catalogs)) base.catalogs = [];
    const currentCatalogs = base.catalogs.length ? base.catalogs : defaultCatalogs;

    // Addons are stored as resolved manifests, so each new URL is fetched once.
    const resolved: InstalledAddon[] = [];
    for (const addon of addonsToInstall(currentAddons.map(entry => entry.manifestUrl ?? entry.id), profile.addons)) {
      onProgress?.(`${profile.name}: installing ${addon.url}`);
      try {
        const installed = await installAddon(addon.url);
        // An addon switched off in Nuvio arrives switched off, rather than
        // turning itself back on in the middle of someone's home screen.
        resolved.push(addon.enabled ? installed : normalizeAddon({ ...installed, enabled: false })!);
        summary.addons += 1;
        if (!addon.enabled) summary.addonsDisabled += 1;
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
    const settings: AppSettings = { ...base, catalogs: orderedCatalogs };
    if (resolved.length) await saveCloudAddons(auth, nextAddons, target.id, { changes: undefined });
    // Profile creation has its own additive write; settings must not reassert
    // the list if another device creates or edits a profile during the import.
    await saveCloudSettings(auth, settings, nextAddons, target.id, [], base);
    summaries.push(summary);
  }
  return { summaries, profiles };
}
