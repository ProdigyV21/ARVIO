import { createHash, randomBytes } from "node:crypto";
import { mkdir, open, readFile, rename, stat, unlink } from "node:fs/promises";
import { resolve } from "node:path";
import { getStore } from "@netlify/blobs";

export interface WeTrakrSessionStore {
  read(key: string): Promise<{ data: string; etag: string } | null>;
  compareAndSet(key: string, data: string, etag: string | null): Promise<boolean>;
}

const digest = (value: string) => createHash("sha256").update(value).digest("hex");
const wait = (ms: number) => new Promise(resolve => setTimeout(resolve, ms));

/** Persistent local store for Docker/source installs; no tokens enter the public directory. */
export class FileWeTrakrSessionStore implements WeTrakrSessionStore {
  constructor(private directory: string) {}

  private path(key: string) { return resolve(this.directory, `${digest(key)}.json`); }

  async read(key: string) {
    try {
      const data = await readFile(this.path(key), "utf8");
      return { data, etag: digest(data) };
    } catch (error) {
      if ((error as NodeJS.ErrnoException).code === "ENOENT") return null;
      throw error;
    }
  }

  async compareAndSet(key: string, data: string, etag: string | null) {
    await mkdir(this.directory, { recursive: true, mode: 0o700 });
    const path = this.path(key);
    const lockPath = `${path}.lock`;
    const deadline = Date.now() + 5000;
    let lock;
    while (!lock) {
      try { lock = await open(lockPath, "wx", 0o600); }
      catch (error) {
        if ((error as NodeJS.ErrnoException).code !== "EEXIST") throw error;
        // Recover a lock left by a terminated process, never an active short write.
        const info = await stat(lockPath).catch(() => null);
        if (info && Date.now() - info.mtimeMs > 30_000) await unlink(lockPath).catch(() => undefined);
        if (Date.now() >= deadline) throw new Error("WETRAKR session storage is busy");
        await wait(25);
      }
    }
    const temporary = `${path}.${randomBytes(8).toString("hex")}.tmp`;
    try {
      if ((await this.read(key))?.etag !== (etag ?? undefined)) return false;
      const file = await open(temporary, "wx", 0o600);
      try { await file.writeFile(data); await file.sync(); }
      finally { await file.close(); }
      await rename(temporary, path);
      return true;
    } finally {
      await unlink(temporary).catch(() => undefined);
      await lock.close();
      await unlink(lockPath).catch(() => undefined);
    }
  }
}

export function wetrakrSessionStore(): WeTrakrSessionStore {
  if (process.env.NETLIFY === "true" || process.env.NETLIFY_BLOBS_CONTEXT) {
    const store = getStore({ name: "wetrakr-web-sessions-v1", consistency: "strong" });
    return {
      async read(key) {
        const entry = await store.getWithMetadata(key, { type: "text", consistency: "strong" });
        if (!entry) return null;
        if (!entry.etag) throw new Error("WETRAKR session storage returned no version");
        return { data: entry.data, etag: entry.etag };
      },
      async compareAndSet(key, data, etag) {
        const result = await store.set(key, data, etag ? { onlyIfMatch: etag } : { onlyIfNew: true });
        return result.modified;
      }
    };
  }
  // Do not silently put production serverless grants on an ephemeral filesystem.
  if (process.env.AWS_LAMBDA_FUNCTION_NAME || process.env.VERCEL) throw new Error("Configure a persistent WETRAKR session store");
  return new FileWeTrakrSessionStore(resolve(process.env.WETRAKR_SESSION_DIR || ".data/wetrakr-sessions"));
}
