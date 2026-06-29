import type { AuthClient } from "./auth";
import type { AppSettings, InstalledAddon, Profile, WatchHistoryEntry } from "./types";

export interface CloudPayload {
  version: number;
  addons: InstalledAddon[];
  settings?: Partial<AppSettings>;
  updatedAt: number;
}

type RawPayload = Record<string, unknown>;

/** Read the full account_sync_state payload object (shared with Android). */
export async function pullRawPayload(auth: AuthClient): Promise<RawPayload> {
  if (!auth.session) return {};
  const rows = await auth.supabase<Array<{ payload?: string | null }>>(
    `/rest/v1/account_sync_state?user_id=eq.${auth.session.userId}&select=user_id,payload,updated_at`
  );
  const raw = rows[0]?.payload;
  if (!raw) return {};
  try {
    return (JSON.parse(raw) as RawPayload) ?? {};
  } catch {
    return {};
  }
}

async function writeRawPayload(auth: AuthClient, payload: RawPayload) {
  if (!auth.session) return;
  payload.userId = auth.session.userId;
  payload.updatedAt = Date.now() / 1000;
  await auth.supabase("/rest/v1/account_sync_state", {
    method: "POST",
    headers: { Prefer: "resolution=merge-duplicates" },
    body: JSON.stringify({
      user_id: auth.session.userId,
      payload: JSON.stringify(payload),
      updated_at: new Date().toISOString()
    })
  });
}

/**
 * Read-modify-write the shared payload, preserving keys this app doesn't own
 * (e.g. Android's profiles / avatar images). Mirrors Android's
 * AuthRepository.mutateAccountSyncPayload.
 */
export async function mutateCloudPayload(auth: AuthClient, mutator: (root: RawPayload) => void) {
  if (!auth.session) return;
  const root = await pullRawPayload(auth);
  mutator(root);
  await writeRawPayload(auth, root);
}

export async function pullCloudPayload(auth: AuthClient): Promise<CloudPayload> {
  const root = await pullRawPayload(auth);
  return {
    version: typeof root.version === "number" ? root.version : 1,
    addons: Array.isArray(root.addons) ? (root.addons as InstalledAddon[]) : [],
    settings: (root.settings as Partial<AppSettings> | undefined) ?? undefined,
    updatedAt: typeof root.updatedAt === "number" ? root.updatedAt : 0
  };
}

export async function saveCloudAddons(auth: AuthClient, addons: InstalledAddon[]) {
  await mutateCloudPayload(auth, (root) => {
    root.version = 2;
    root.addons = addons;
  });
}

export async function saveCloudSettings(auth: AuthClient, settings: AppSettings, addons: InstalledAddon[]) {
  await mutateCloudPayload(auth, (root) => {
    root.version = 2;
    root.addons = addons;
    root.settings = settings;
  });
}

export interface CloudProfiles {
  profiles: Profile[];
  activeProfileId: string | null;
  avatarImages: Record<string, string>;
}

export async function pullCloudProfiles(auth: AuthClient): Promise<CloudProfiles> {
  const root = await pullRawPayload(auth);
  return {
    profiles: Array.isArray(root.profiles) ? (root.profiles as Profile[]) : [],
    activeProfileId: typeof root.activeProfileId === "string" ? root.activeProfileId : null,
    avatarImages: (root.profileAvatarImagesById as Record<string, string> | undefined) ?? {}
  };
}

export async function saveCloudProfiles(auth: AuthClient, profiles: Profile[], activeProfileId: string | null) {
  await mutateCloudPayload(auth, (root) => {
    root.profiles = profiles;
    root.activeProfileId = activeProfileId;
  });
}

export async function getContinueWatching(auth: AuthClient) {
  if (!auth.session) return [];
  return auth.supabase<WatchHistoryEntry[]>(
    `/rest/v1/watch_history?user_id=eq.${auth.session.userId}&progress=lt.0.9&select=*&order=updated_at.desc&limit=50`
  );
}

export async function saveProgress(auth: AuthClient, entry: Omit<WatchHistoryEntry, "user_id">) {
  if (!auth.session) return;
  await auth.supabase("/rest/v1/watch_history", {
    method: "POST",
    headers: { Prefer: "resolution=merge-duplicates" },
    body: JSON.stringify({
      ...entry,
      user_id: auth.session.userId,
      paused_at: new Date().toISOString(),
      updated_at: new Date().toISOString()
    })
  });
}

export async function markWatched(auth: AuthClient, entry: Omit<WatchHistoryEntry, "user_id" | "progress" | "position_seconds">) {
  await saveProgress(auth, {
    ...entry,
    progress: 1,
    position_seconds: entry.duration_seconds
  });
}
