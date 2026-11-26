"use client";
import { useCallback, useEffect, useSyncExternalStore } from "react";
import type { AuthClient } from "./auth";
import { pullCloudSportsFollows, saveCloudSportsFollows } from "./cloud";
import { sanitizeFollows, sanitizeReminders, toggleFollow, type SportsFollow, type SportsReminder } from "./sportsFollows";

/*
 * Followed teams sync with the account (same field as the Android app);
 * reminders stay in this browser. One small shared store, so the Sports page
 * and the kick-off banner see the same lists.
 */

interface State { scope: string; follows: SportsFollow[]; reminders: SportsReminder[] }
let state: State = { scope: "", follows: [], reminders: [] };
const listeners = new Set<() => void>();
const emit = () => listeners.forEach(listener => listener());
const subscribe = (listener: () => void) => { listeners.add(listener); return () => { listeners.delete(listener); }; };
const followsKey = (scope: string) => `arvio:sports-follows:v1:${scope}`;
const remindersKey = (scope: string) => `arvio:sports-reminders:v1:${scope}`;

function read(key: string): unknown {
  try { return JSON.parse(localStorage.getItem(key) ?? "null"); } catch { return null; }
}
function write(key: string, value: unknown) {
  try { localStorage.setItem(key, JSON.stringify(value)); } catch { /* Storage can be full or disabled. */ }
}
function ensureScope(scope: string) {
  if (state.scope === scope) return;
  state = { scope, follows: sanitizeFollows(read(followsKey(scope))), reminders: sanitizeReminders(read(remindersKey(scope))) };
  emit();
}
function setFollows(scope: string, follows: SportsFollow[]) {
  ensureScope(scope);
  state = { ...state, follows };
  write(followsKey(scope), follows);
  emit();
}
export function updateSportsReminders(scope: string, transform: (current: SportsReminder[]) => SportsReminder[]) {
  ensureScope(scope);
  const next = transform(state.reminders);
  if (next === state.reminders) return;
  state = { ...state, reminders: next };
  write(remindersKey(scope), next);
  emit();
}

export function sportsScope(auth: AuthClient, profileId?: string | null) {
  return `${auth.session?.userId ?? "local"}:${profileId ?? "local"}`;
}

export function useSportsPreferences(auth: AuthClient, profileId?: string | null) {
  const scope = sportsScope(auth, profileId);
  const snapshot = useSyncExternalStore(subscribe, () => state, () => state);
  useEffect(() => {
    ensureScope(scope);
    if (!auth.session || !profileId) return;
    let active = true;
    void pullCloudSportsFollows(auth, profileId).then(json => {
      if (!active || json === null || state.scope !== scope) return;
      try { setFollows(scope, sanitizeFollows(JSON.parse(json))); } catch { /* Keep the local list. */ }
    }).catch(() => undefined);
    return () => { active = false; };
  }, [auth, profileId, scope]);
  const current = snapshot.scope === scope ? snapshot : { follows: [] as SportsFollow[], reminders: [] as SportsReminder[] };
  const toggle = useCallback((follow: SportsFollow) => {
    ensureScope(scope);
    const next = toggleFollow(state.follows, follow);
    setFollows(scope, next);
    if (auth.session && profileId) void saveCloudSportsFollows(auth, profileId, JSON.stringify(next)).catch(() => undefined);
  }, [auth, profileId, scope]);
  const updateReminders = useCallback((transform: (current: SportsReminder[]) => SportsReminder[]) => updateSportsReminders(scope, transform), [scope]);
  return { follows: current.follows, reminders: current.reminders, toggleFollow: toggle, updateReminders, scope };
}
