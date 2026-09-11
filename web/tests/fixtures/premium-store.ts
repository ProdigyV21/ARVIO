// Test-only store: never imported by a production entry point.
export const authClient = {
  session: { userId: 'premium-fixture', email: 'tester@example.test', accessToken: 'fixture', provider: 'netlify' },
  accessToken: async () => 'fixture',
  refresh: async () => undefined
};
export const useApp = () => ({ auth: authClient.session, signOut: () => { location.href = '/?case=signedout'; }, goToLogin: () => { location.href = '/?case=login'; } });
