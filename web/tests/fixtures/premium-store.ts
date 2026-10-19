// Test-only identity: never imported by a production entry point. The live
// auth client, browser profile and real customer accounts are never used.
import { useSyncExternalStore } from 'react';
const listeners = new Set<() => void>();
export const authClient = {
  session: { userId: 'premium-fixture', email: 'tester@example.test', accessToken: 'fixture', provider: 'netlify' },
  accessToken: async () => 'fixture',
  refresh: async () => undefined
};
export function setFixtureAccount(userId: string) {
  authClient.session = { ...authClient.session, userId, email: `${userId}@example.test` };
  for (const listener of listeners) listener();
}
export const useApp = () => {
  const auth = useSyncExternalStore(listener => { listeners.add(listener); return () => { listeners.delete(listener); }; }, () => authClient.session);
  return { auth, addons: [], addonsReady: true, settings: { homeServers: [], iptvPlaylists: [] }, setSection: () => {}, signOut: () => { location.href = '/?case=signedout'; }, goToLogin: () => { location.href = '/?case=login'; } };
};
