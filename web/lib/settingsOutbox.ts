import type { AuthClient } from "./auth";
import { saveCloudSettings } from "./cloud";
import { loadStored, saveStored } from "./storage";
import type { AppSettings } from "./types";

type Pending = { id: string; profileId: string; settings: AppSettings; baseline: AppSettings | null; changedAt: number };
const keyFor = (userId: string) => `arvio.web.settingsOutbox.v1:${userId}`;
const running = new Map<string, Promise<void>>();

export function hasPendingSettings(auth: AuthClient, profileId?: string | null) {
  if (!auth.session) return false;
  return loadStored<Pending[]>(keyFor(auth.session.userId), []).some((entry) => !profileId || entry.profileId === profileId);
}

export function queueSettings(auth: AuthClient, profileId: string, settings: AppSettings, baseline: AppSettings | null) {
  if (!auth.session || !baseline) return;
  const key = keyFor(auth.session.userId);
  const entries = loadStored<Pending[]>(key, []);
  const previous = entries.find((entry) => entry.profileId === profileId);
  const next = { id: crypto.randomUUID(), profileId, settings, baseline: previous?.baseline ?? baseline, changedAt: Date.now() };
  saveStored(key, [...entries.filter((entry) => entry.profileId !== profileId), next]);
  if (!loadStored<Pending[]>(key, []).some((entry) => entry.id === next.id)) throw new Error("Device storage is full. Keep this page open and retry saving.");
}

export async function flushSettingsOutbox(auth: AuthClient): Promise<void> {
  const userId = auth.session?.userId;
  if (!userId) return;
  const current = running.get(userId);
  if (current) return current;
  const promise = (async () => {
    const key = keyFor(userId);
    while (auth.session?.userId === userId) {
      const entry = loadStored<Pending[]>(key, [])[0];
      if (!entry) return;
      // Older clients queued full default snapshots before profile hydration.
      // They have no acknowledged baseline, so cannot safely describe user edits.
      if (!entry.baseline) {
        saveStored(key, loadStored<Pending[]>(key, []).filter(pending => pending.id !== entry.id));
        continue;
      }
      await saveCloudSettings(auth, entry.settings, [], entry.profileId, [], entry.baseline, entry.changedAt);
      // Do not acknowledge a newer edit queued while the request was in flight.
      saveStored(key, loadStored<Pending[]>(key, []).filter((pending) => pending.id !== entry.id));
    }
  })();
  running.set(userId, promise);
  try { await promise; } finally { if (running.get(userId) === promise) running.delete(userId); }
}
