import fs from "node:fs/promises";
import pg from "pg";

const connectionString = process.env.NETLIFY_DB_URL || process.env.NETLIFY_DATABASE_URL || process.env.DATABASE_URL;
if (!connectionString) throw new Error("An auth-site PostgreSQL connection URL is required");

const client = new pg.Client({ connectionString });
try {
  await client.connect();
  await client.query(await fs.readFile(new URL("../netlify/database/migrations/20261103131500_discord_premium_links/migration.sql", import.meta.url), "utf8"));
  console.log("Discord Premium link storage is ready.");
} finally {
  await client.end();
}
