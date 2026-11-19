// Test bundle only. This adapter is never imported by the application build.
export class AuthClient {
  session: { email: string; userId: string } | null = null;
  async signIn(email: string, password: string) {
    if (!["operator@example.test", "viewer0@example.test"].includes(email) || password !== "fixture-only") throw new Error("Invalid test credentials");
    this.session = { email, userId: email.split("@")[0] };
    return this.session;
  }
  async accessToken() {
    if (!this.session) throw new Error("Sign in required");
    return `fixture-${this.session.userId}`;
  }
  signOut() { this.session = null; }
}
