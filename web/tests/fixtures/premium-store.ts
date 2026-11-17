// Test-only identity: never imported by a production entry point. The live
// auth client, browser profile and real customer accounts are never used.
import { useSyncExternalStore } from 'react';
const listeners = new Set<() => void>();
type FixtureSession = { userId: string; email: string; accessToken: string; provider: string };
const scenario = new URLSearchParams(location.search).get('case');
const signedOut = scenario === 'preview-signedout' || scenario === 'purchase-signedout';
let view = signedOut ? 'login' : 'profiles';
export const authClient = {
  session: (signedOut ? null : { userId: 'premium-fixture', email: 'tester@example.test', accessToken: 'fixture', provider: 'netlify' }) as FixtureSession | null,
  accessToken: async () => 'fixture',
  refresh: async () => undefined
};
export function setFixtureAccount(userId: string | null) {
  authClient.session = userId ? { userId, email: `${userId}@example.test`, accessToken: 'fixture', provider: 'netlify' } : null;
  for (const listener of listeners) listener();
}
export function setFixtureView(next: string) { view = next; for (const listener of listeners) listener(); }
export const useAppPick = (..._keys: string[]) => useApp();
export const useAppSelector = <T>(select: (store: ReturnType<typeof useApp>) => T) => select(useApp());
export const useApp = () => {
  const auth = useSyncExternalStore(listener => { listeners.add(listener); return () => { listeners.delete(listener); }; }, () => authClient.session);
  const currentView = useSyncExternalStore(listener => { listeners.add(listener); return () => { listeners.delete(listener); }; }, () => view);
  return { auth, view: currentView, section: 'home', selected: null, activeStream: null, activeChannel: null, addons: [], addonsReady: true,
    settings: { homeServers: [], iptvPlaylists: [], smoothScrolling: false, oledBlack: true, spoilerBlur: false, accentColor: 'arctic' },
    setSection: () => {}, signOut: () => setFixtureAccount(null), goToLogin: () => {
      const windowFixture = window as unknown as { __authNavigateCount?: number };
      windowFixture.__authNavigateCount = (windowFixture.__authNavigateCount || 0) + 1;
    }, backToProfiles: () => setFixtureView('profiles'), cloudLoginRequired: true };
};
