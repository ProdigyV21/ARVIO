import fs from "node:fs/promises";
import pg from "pg";

// Explicit operator command, never an opportunistic migration during a request.
const connectionString = process.env.NETLIFY_DB_URL || process.env.NETLIFY_DATABASE_URL || process.env.DATABASE_URL;
if (!connectionString) throw new Error("A PostgreSQL connection URL is required");
const client = new pg.Client({ connectionString });
try {
  await client.connect();
  await client.query(await fs.readFile(new URL("../sql/partner.sql", import.meta.url), "utf8"));
  console.log("Partner schema is ready. Public access is disabled; server connections only.");
} finally { await client.end(); }
