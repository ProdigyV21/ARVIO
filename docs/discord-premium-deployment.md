# ARVIO Discord Premium badge

1. The normal Git-triggered Auth deploy applies the versioned Netlify Database migration. It creates the role link table and its due-work index. For a manual migration, run `node scripts/migrate-discord-premium.mjs` from the auth site with a server-only Postgres connection that can create tables. The migration is idempotent.
2. Create or select a dedicated application in the Discord Developer Portal. Add the OAuth redirect URL `https://auth.arvio.tv/.netlify/functions/discord-premium-callback`. The account-linking flow requests the `identify` scope. Users join the ARVIO server themselves.
3. Install the application's bot on the ARVIO server with Manage Roles. Give it a dedicated bot role without Administrator. Place that role directly above the dedicated ARVIO-managed Premium role and below staff and other privileged roles. Use the same badge and Premium channel access for the ARVIO-managed role that members already receive with the Ko-fi role. Keep the existing Ko-fi role mapping separate so neither bot can remove the other's entitlement.
4. Set these production Functions environment variables on the auth site. Keep both credentials in server-side secrets.

   | Variable | Value |
   | --- | --- |
   | `DISCORD_PREMIUM_CLIENT_ID` | Discord application's client ID |
   | `DISCORD_PREMIUM_CLIENT_SECRET` | Discord application's OAuth client secret |
   | `DISCORD_PREMIUM_BOT_TOKEN` | Discord application's bot token |
   | `DISCORD_PREMIUM_GUILD_ID` | ARVIO Discord server ID |
   | `DISCORD_PREMIUM_ROLE_ID` | Dedicated ARVIO-managed Premium role ID |
   | `DISCORD_PREMIUM_ENABLED` | `true` after the above setup |

5. Deploy the auth site. Verify with a signed-in existing paid account and a Discord identity already in the server. Also verify that an unpaid account cannot start linking, a different Discord account cannot overwrite an existing link, and an identity outside the server receives the join prompt. The production `discord-premium-reconcile` function runs every 15 minutes and picks up pending links, retries failures and removes managed badges when eligibility ends.

Links contain only the ARVIO account ID, its email hash and its Discord user ID. No Discord OAuth access or refresh token is retained. If a linked user deletes their ARVIO account, the worker removes the managed role before deleting its link, so transient Discord failures can still be retried.
