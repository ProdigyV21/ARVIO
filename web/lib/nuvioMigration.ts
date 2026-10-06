import type { CatalogConfig, Profile } from "./types";

/**
 * Migration from a Nuvio account into ARVIO.
 *
 * Nuvio stores a user's setup server side (Supabase): `profiles`, `addons`,
 * `plugins`, `collections` and `home_catalog_settings`, all keyed by an integer
 * `profile_id` (1-6). Nuvio Web keeps nothing importable in the browser, so the
 * only way to carry a setup over is to read it from the user's own account.
 *
 * Deployments publish their connection settings at `/.well-known/nuvio`, which
 * is how a self-hosted server hands clients its publishable key. Nothing here is
 * hard-coded: the user names their server and signs in with their own account,
 * and the access token lives in memory for the length of the import only.
 */

export interface NuvioDiscovery {
  backendUrl: string;
  publishableKey: string;
  selfHosted: boolean;
  emailPasswordAuth: boolean;
}

export interface NuvioSession {
  accessToken: string;
  userId: string;
  email: string;
}

/** One Nuvio profile plus everything that belongs to it. */
export interface NuvioProfileData {
  profileId: number;
  name: string;
  avatarColorHex: string;
  avatarId: string | null;
  pinEnabled: boolean;
  addons: NuvioAddon[];
  plugins: NuvioPlugin[];
  collectionsJson: unknown;
  homeCatalogSettings: unknown;
}

export interface NuvioAddon {
  url: string;
  name: string | null;
  enabled: boolean;
  sortOrder: number;
}

export interface NuvioPlugin extends NuvioAddon {
  repoType: string | null;
}

export interface NuvioSnapshot {
  email: string;
  profiles: NuvioProfileData[];
  /** Tables that could not be read, so the UI can say what is missing rather than implying nothing was there. */
  warnings: string[];
}

const trimSlash = (value: string) => value.trim().replace(/\/+$/, "");

/** Accepts "example.com", "https://example.com" or a full well-known URL. */
export function normalizeBackendUrl(raw: string): string {
  const input = raw.trim();
  if (!input) throw new Error("Enter your Nuvio server address");
  // A local server is reached over plain HTTP, so a scheme-less "localhost:8000"
  // must not be turned into an https URL nothing is listening on.
  const isLocal = /^(localhost|127\.0\.0\.1)(:|\/|$)/i.test(input);
  const withScheme = /^https?:\/\//i.test(input) ? input : `${isLocal ? "http" : "https"}://${input}`;
  const url = new URL(withScheme);
  if (url.protocol !== "https:" && url.hostname !== "localhost" && url.hostname !== "127.0.0.1") {
    throw new Error("The Nuvio server address must use HTTPS");
  }
  return trimSlash(url.origin + url.pathname.replace(/\/\.well-known\/nuvio\/?$/, ""));
}

export function parseDiscovery(raw: unknown, fallbackUrl: string): NuvioDiscovery {
  const doc = (raw && typeof raw === "object" ? raw : {}) as Record<string, unknown>;
  const publishableKey = typeof doc.publishable_key === "string" ? doc.publishable_key.trim() : "";
  if (!publishableKey) throw new Error("That server did not return a Nuvio publishable key");
  const capabilities = (doc.capabilities && typeof doc.capabilities === "object" ? doc.capabilities : {}) as Record<string, unknown>;
  return {
    backendUrl: typeof doc.backend_url === "string" && doc.backend_url.trim() ? trimSlash(doc.backend_url) : fallbackUrl,
    publishableKey,
    selfHosted: doc.self_hosted === true,
    // Older deployments omit the capability block; assume the standard login works.
    emailPasswordAuth: capabilities.email_password_auth !== false
  };
}

/**
 * The public Nuvio cloud. It publishes its own `/.well-known/nuvio`, so the
 * page only needs the address: the publishable key is read at connect time and
 * nothing has to be embedded here (and a rotated key keeps working).
 */
export const OFFICIAL_NUVIO_URL = "https://api.nuvio.tv";

type Fetcher = typeof fetch;

export async function discoverNuvioBackend(rawUrl: string, fetcher: Fetcher = fetch): Promise<NuvioDiscovery> {
  const backendUrl = normalizeBackendUrl(rawUrl);
  const response = await fetcher(`${backendUrl}/.well-known/nuvio`, { headers: { accept: "application/json" } });
  if (!response.ok) {
    throw new Error(`Could not read the Nuvio server settings (HTTP ${response.status})`);
  }
  return parseDiscovery(await response.json(), backendUrl);
}

export async function signInToNuvio(
  discovery: NuvioDiscovery,
  email: string,
  password: string,
  fetcher: Fetcher = fetch
): Promise<NuvioSession> {
  if (!discovery.emailPasswordAuth) throw new Error("This Nuvio server has email sign-in disabled");
  const response = await fetcher(`${discovery.backendUrl}/auth/v1/token?grant_type=password`, {
    method: "POST",
    headers: { "content-type": "application/json", apikey: discovery.publishableKey },
    body: JSON.stringify({ email: email.trim(), password })
  });
  const payload = await response.json().catch(() => ({})) as Record<string, unknown>;
  if (!response.ok) {
    const message = typeof payload.error_description === "string" ? payload.error_description
      : typeof payload.msg === "string" ? payload.msg
      : "Nuvio rejected that email or password";
    throw new Error(message);
  }
  const accessToken = typeof payload.access_token === "string" ? payload.access_token : "";
  const user = (payload.user && typeof payload.user === "object" ? payload.user : {}) as Record<string, unknown>;
  if (!accessToken) throw new Error("Nuvio did not return an access token");
  return {
    accessToken,
    userId: typeof user.id === "string" ? user.id : "",
    email: typeof user.email === "string" ? user.email : email.trim()
  };
}

/**
 * A table read. `ok: false` means the request itself did not succeed, which is
 * not the same thing as a table that answered with nothing — conflating the two
 * is how an import ends up deciding an account is empty when it is unreachable.
 */
export interface TableRead {
  table: string;
  ok: boolean;
  rows: Record<string, unknown>[];
}

const objectRows = (value: unknown): Record<string, unknown>[] =>
  Array.isArray(value) ? value.filter((row): row is Record<string, unknown> => Boolean(row) && typeof row === "object") : [];

export async function selectRows(
  discovery: NuvioDiscovery,
  session: NuvioSession,
  table: string,
  query: string,
  fetcher: Fetcher
): Promise<TableRead> {
  const response = await fetcher(`${discovery.backendUrl}/rest/v1/${table}?${query}`, {
    headers: {
      apikey: discovery.publishableKey,
      authorization: `Bearer ${session.accessToken}`,
      accept: "application/json"
    }
  }).catch(() => null);
  // A table a deployment does not have (or does not expose) must not fail the
  // whole import — the user still gets everything else, and the failure is
  // reported instead of passing for an empty table.
  if (!response?.ok) return { table, ok: false, rows: [] };
  const rows: unknown = await response.json().catch(() => null);
  if (rows === null) return { table, ok: false, rows: [] };
  return { table, ok: true, rows: objectRows(rows) };
}

/**
 * Row-level security on a Nuvio deployment can leave a plain table read empty
 * even though the data is there; the sync RPCs are the supported way in. They
 * take one profile at a time, so they are only used to fill a gap.
 */
async function callSyncRpc(
  discovery: NuvioDiscovery,
  session: NuvioSession,
  name: string,
  profileIds: number[],
  fetcher: Fetcher
): Promise<Record<string, unknown>[]> {
  const rows: Record<string, unknown>[] = [];
  for (const profileId of profileIds) {
    const response = await fetcher(`${discovery.backendUrl}/rest/v1/rpc/${name}`, {
      method: "POST",
      headers: {
        apikey: discovery.publishableKey,
        authorization: `Bearer ${session.accessToken}`,
        "content-type": "application/json",
        accept: "application/json"
      },
      body: JSON.stringify({ p_profile_id: profileId })
    }).catch(() => null);
    if (!response?.ok) continue;
    const payload: unknown = await response.json().catch(() => null);
    const list = Array.isArray(payload) ? payload : payload ? [payload] : [];
    for (const row of list) {
      if (row && typeof row === "object") {
        rows.push({ profile_id: profileId, ...(row as Record<string, unknown>) });
      }
    }
  }
  return rows;
}

const text = (value: unknown): string => typeof value === "string" ? value.trim() : "";
const int = (value: unknown, fallback: number): number => {
  const parsed = typeof value === "number" ? value : Number.parseInt(text(value), 10);
  return Number.isFinite(parsed) ? parsed : fallback;
};

export function mapAddonRows(rows: Record<string, unknown>[]): NuvioAddon[] {
  return rows
    .map(row => ({
      url: text(row.url),
      name: text(row.name) || null,
      enabled: row.enabled !== false,
      sortOrder: int(row.sort_order, 0)
    }))
    .filter(addon => /^https?:\/\//i.test(addon.url))
    .sort((a, b) => a.sortOrder - b.sortOrder);
}

export function mapPluginRows(rows: Record<string, unknown>[]): NuvioPlugin[] {
  return mapAddonRows(rows).map((addon, index) => ({
    ...addon,
    repoType: text(rows[index]?.repo_type) || null
  }));
}

/** Groups every table by Nuvio's integer profile id. */
export function buildSnapshot(
  email: string,
  profileRows: Record<string, unknown>[],
  addonRows: Record<string, unknown>[],
  pluginRows: Record<string, unknown>[],
  collectionRows: Record<string, unknown>[],
  catalogSettingRows: Record<string, unknown>[],
  warnings: string[] = []
): NuvioSnapshot {
  const byProfile = <T extends Record<string, unknown>>(rows: T[], profileId: number) =>
    rows.filter(row => int(row.profile_id, 1) === profileId);
  const profiles = profileRows
    .map(row => {
      const profileId = int(row.profile_id, int(row.profile_index, 1));
      const addons = byProfile(addonRows, profileId);
      const plugins = byProfile(pluginRows, profileId);
      return {
        profileId,
        name: text(row.name) || `Profile ${profileId}`,
        avatarColorHex: text(row.avatar_color_hex) || "#1E88E5",
        avatarId: text(row.avatar_id) || null,
        pinEnabled: row.pin_enabled === true,
        addons: mapAddonRows(addons),
        plugins: mapPluginRows(plugins),
        collectionsJson: byProfile(collectionRows, profileId)[0]?.collections_json ?? null,
        homeCatalogSettings: byProfile(catalogSettingRows, profileId)[0]?.settings_json ?? null
      };
    })
    .sort((a, b) => a.profileId - b.profileId);
  return { email, profiles, warnings };
}

/** Nuvio's profile ids are 1-6; a gap-free scan is what the fallback probes. */
const PROBE_PROFILE_IDS = [1, 2, 3, 4, 5, 6];

/**
 * Recovers the profile ids present in an account when the `profiles` table
 * itself is unavailable. Every other table carries `profile_id`, so a profile
 * is known to exist as soon as any of its rows comes back — building only
 * profile 1 silently dropped everything that belonged to the others.
 */
export function profileIdsFromRows(...rowSets: Record<string, unknown>[][]): number[] {
  const ids = new Set<number>();
  for (const rows of rowSets) {
    for (const row of rows) {
      const id = int(row.profile_id, int(row.profile_index, 0));
      if (id >= 1) ids.add(id);
    }
  }
  return [...ids].sort((a, b) => a - b);
}

export async function fetchNuvioSnapshot(
  discovery: NuvioDiscovery,
  session: NuvioSession,
  fetcher: Fetcher = fetch
): Promise<NuvioSnapshot> {
  const [profiles, addons, plugins, collections, catalogSettings] = await Promise.all([
    selectRows(discovery, session, "profiles", "select=*&order=profile_id.asc", fetcher),
    selectRows(discovery, session, "addons", "select=*&order=sort_order.asc", fetcher),
    selectRows(discovery, session, "plugins", "select=*&order=sort_order.asc", fetcher),
    selectRows(discovery, session, "collections", "select=*", fetcher),
    selectRows(discovery, session, "home_catalog_settings", "select=*", fetcher)
  ]);

  const warnings: string[] = [];
  // A failed read is reported; a read that succeeded with no rows is simply an
  // empty table and says nothing is there to bring over.
  for (const read of [addons, plugins]) {
    if (!read.ok) warnings.push(`Nuvio did not return your ${read.table} — none were imported`);
  }

  // The RPCs only answer one profile at a time, so they are probed across
  // Nuvio's whole 1-6 range whenever the table read did not produce rows.
  const knownIds = profiles.ok && profiles.rows.length
    ? profileIdsFromRows(profiles.rows)
    : profileIdsFromRows(addons.rows, plugins.rows, collections.rows, catalogSettings.rows);
  const probeIds = profiles.ok && profiles.rows.length ? knownIds : PROBE_PROFILE_IDS;

  const effectiveCollections = collections.ok && collections.rows.length ? collections.rows
    : await callSyncRpc(discovery, session, "sync_pull_collections", probeIds, fetcher);
  const effectiveCatalogSettings = catalogSettings.ok && catalogSettings.rows.length ? catalogSettings.rows
    : await callSyncRpc(discovery, session, "sync_pull_home_catalog_settings", probeIds, fetcher);

  let effectiveProfiles = profiles.rows;
  if (!profiles.ok || !profiles.rows.length) {
    if (!profiles.ok) warnings.push("Nuvio's profile list could not be read, so profiles were recovered from their content");
    // Every id any table answered for is a profile that exists. Falling back to
    // profile 1 alone used to drop the rest of the account on the floor.
    const recovered = profileIdsFromRows(
      addons.rows, plugins.rows, effectiveCollections, effectiveCatalogSettings
    );
    const ids = (recovered.length ? recovered : knownIds.length ? knownIds : [1]);
    effectiveProfiles = ids.map(id => ({ profile_id: id, name: `Profile ${id}` }));
  }

  const snapshot = buildSnapshot(
    session.email, effectiveProfiles, addons.rows, plugins.rows,
    effectiveCollections, effectiveCatalogSettings, warnings
  );
  if (!snapshot.profiles.length) throw new Error("That Nuvio account has no profiles to import");
  return snapshot;
}

// ── Mapping Nuvio profiles onto ARVIO profiles ──────────────────────────────

/** Nuvio stores "#RRGGBB"; ARVIO stores the Android ARGB long. */
export function hexToArgb(hex: string, fallback: number): number {
  const match = /^#?([0-9a-f]{6})$/i.exec(hex.trim());
  if (!match) return fallback;
  return (0xff000000 | Number.parseInt(match[1], 16)) >>> 0;
}

/** Nuvio avatar ids are free text; ARVIO uses 0 (initial) or an avatar 1-84. */
export function avatarIdToArvio(avatarId: string | null): number {
  const digits = /(\d{1,3})/.exec(avatarId ?? "");
  if (!digits) return 0;
  const parsed = Number.parseInt(digits[1], 10);
  return parsed >= 1 && parsed <= 84 ? parsed : 0;
}

export type ProfileTarget =
  | { kind: "existing"; profileId: string }
  | { kind: "create" }
  | { kind: "skip" };

export interface MigrationChoice {
  nuvioProfileId: number;
  target: ProfileTarget;
  /** Set once the user picks a target by hand, so a later recalculation leaves it alone. */
  userSet?: boolean;
}

const normalizedName = (value: string) => value.trim().toLocaleLowerCase("en");

/**
 * Default plan: a Nuvio profile lands on the ARVIO profile with the same name,
 * otherwise it is created. That keeps a repeated import idempotent instead of
 * growing a second copy of every profile.
 */
export function defaultChoices(snapshot: NuvioSnapshot, existing: Pick<Profile, "id" | "name">[]): MigrationChoice[] {
  const taken = new Set<string>();
  return snapshot.profiles.map(profile => {
    const match = existing.find(candidate =>
      normalizedName(candidate.name) === normalizedName(profile.name) && !taken.has(candidate.id));
    if (match) taken.add(match.id);
    return { nuvioProfileId: profile.profileId, target: match ? { kind: "existing", profileId: match.id } : { kind: "create" } };
  });
}

/**
 * Recomputes the default plan against a profile list that arrived later — the
 * Nuvio step runs before the ARVIO sign-in, so the first pass necessarily saw
 * no profiles and proposed creating all of them. A choice the user already
 * changed by hand is kept exactly as they set it.
 */
export function reconcileChoices(
  snapshot: NuvioSnapshot,
  existing: Pick<Profile, "id" | "name">[],
  previous: MigrationChoice[]
): MigrationChoice[] {
  const kept = new Map(previous.filter(choice => choice.userSet).map(choice => [choice.nuvioProfileId, choice]));
  // Names the user has already claimed by hand must not be matched again.
  const claimed = new Set(
    [...kept.values()].flatMap(choice => choice.target.kind === "existing" ? [choice.target.profileId] : [])
  );
  const available = existing.filter(profile => !claimed.has(profile.id));
  const recomputed = defaultChoices(snapshot, available);
  return recomputed.map(choice => kept.get(choice.nuvioProfileId) ?? choice);
}

export function newProfileFrom(profile: NuvioProfileData, id: string, fallbackColor: number): Profile {
  return {
    id,
    name: profile.name.slice(0, 40),
    avatarColor: hexToArgb(profile.avatarColorHex, fallbackColor),
    avatarId: avatarIdToArvio(profile.avatarId),
    createdAt: Date.now(),
    lastUsedAt: 0,
    // A Nuvio PIN hash cannot be carried over, so the copy starts unlocked and
    // the user sets a new PIN in ARVIO rather than being locked out of it.
    isLocked: false,
    pin: null
  };
}

export interface ProfileImportSummary {
  profileName: string;
  created: boolean;
  addons: number;
  addonsFailed: number;
  /** Installed but left switched off, because they were switched off in Nuvio. */
  addonsDisabled: number;
  plugins: number;
  collections: number;
  catalogSettings: boolean;
  /** Why nothing was written for this profile. Set only when the import stopped. */
  failed?: string;
}

/** Counts what a plan would bring over, for the confirmation step. */
export function summarizePlan(snapshot: NuvioSnapshot, choices: MigrationChoice[]): ProfileImportSummary[] {
  return snapshot.profiles.flatMap(profile => {
    const choice = choices.find(entry => entry.nuvioProfileId === profile.profileId);
    if (!choice || choice.target.kind === "skip") return [];
    const collections = Array.isArray(profile.collectionsJson) ? profile.collectionsJson.length : 0;
    return [{
      profileName: profile.name,
      created: choice.target.kind === "create",
      addons: profile.addons.length,
      addonsFailed: 0,
      addonsDisabled: profile.addons.filter(addon => !addon.enabled).length,
      plugins: profile.plugins.length,
      collections,
      catalogSettings: Boolean(profile.homeCatalogSettings)
    }];
  });
}

/**
 * The Nuvio addons that are not on the profile yet. Whole entries rather than
 * URLs, so the import can carry each one's `enabled` state across instead of
 * switching on an addon the user had deliberately switched off.
 */
export function addonsToInstall(existing: string[], incoming: NuvioAddon[]): NuvioAddon[] {
  const seen = new Set(existing.map(url => url.trim().toLowerCase()));
  const added: NuvioAddon[] = [];
  for (const addon of incoming) {
    const key = addon.url.trim().toLowerCase();
    if (seen.has(key)) continue;
    seen.add(key);
    added.push(addon);
  }
  return added;
}

/**
 * Nuvio's home catalog settings describe row order and visibility by catalog id.
 * ARVIO keeps the same two ideas, so the parts that line up are applied and the
 * rest is ignored rather than guessed at.
 */
export function applyHomeCatalogSettings(catalogs: CatalogConfig[], settings: unknown): CatalogConfig[] {
  const doc = (settings && typeof settings === "object" ? settings : {}) as Record<string, unknown>;
  const order = Array.isArray(doc.order) ? doc.order.map(text).filter(Boolean)
    : Array.isArray(doc.catalogOrder) ? doc.catalogOrder.map(text).filter(Boolean) : [];
  const hidden = new Set(
    (Array.isArray(doc.hidden) ? doc.hidden : Array.isArray(doc.hiddenCatalogIds) ? doc.hiddenCatalogIds : [])
      .map(text).filter(Boolean)
  );
  if (!order.length && !hidden.size) return catalogs;
  const ranked = catalogs.map(catalog => ({
    catalog: hidden.has(catalog.id) ? { ...catalog, enabled: false } : catalog,
    rank: order.indexOf(catalog.id)
  }));
  return ranked
    .sort((a, b) => (a.rank < 0 ? Number.MAX_SAFE_INTEGER : a.rank) - (b.rank < 0 ? Number.MAX_SAFE_INTEGER : b.rank))
    .map(entry => entry.catalog);
}
