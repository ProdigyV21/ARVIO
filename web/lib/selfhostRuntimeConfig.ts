/** Public application configuration only. API keys and OAuth secrets must never
 * enter this object: it is sent to every browser visiting an independent install.
 * Deployment mode remains a build-time choice, not a runtime/visitor override. */
export interface SelfhostRuntimeConfig {
  traktClientId: string;
  simklClientId: string;
  simklV2ClientId: string;
  telegramApiId: string;
  telegramApiHash: string;
  resolverUrl: string;
}

declare global {
  interface Window {
    __ARVIO_SELFHOST_CONFIG__?: SelfhostRuntimeConfig;
  }
}

function publicId(value: unknown): string {
  return typeof value === "string" && /^[A-Za-z0-9._-]{1,256}$/.test(value) &&
    !value.toLowerCase().startsWith("your-") && value.toLowerCase() !== "disabled" ? value : "";
}

function publicResolver(value: unknown): string {
  if (typeof value !== "string" || value.length > 2048 || !value || value !== value.trim()) return "";
  try {
    const url = new URL(value);
    if (url.username || url.password || url.search || url.hash) return "";
    if (url.protocol !== "https:" && !(url.protocol === "http:" && url.hostname === "localhost")) return "";
    return url.href.replace(/\/+$/, "");
  } catch {
    return "";
  }
}

export function normalizeSelfhostRuntimeConfig(value: Partial<SelfhostRuntimeConfig>): SelfhostRuntimeConfig {
  const id = typeof value.telegramApiId === "string" && /^[1-9]\d{0,9}$/.test(value.telegramApiId) &&
    Number(value.telegramApiId) <= 2147483647 ? value.telegramApiId : "";
  const hash = typeof value.telegramApiHash === "string" && /^[a-f0-9]{32}$/i.test(value.telegramApiHash) ? value.telegramApiHash : "";
  return {
    traktClientId: publicId(value.traktClientId),
    simklClientId: publicId(value.simklClientId),
    simklV2ClientId: publicId(value.simklV2ClientId),
    telegramApiId: id && hash ? id : "",
    telegramApiHash: id && hash ? hash : "",
    resolverUrl: publicResolver(value.resolverUrl)
  };
}

export function browserSelfhostRuntimeConfig(): SelfhostRuntimeConfig | undefined {
  if (process.env.NEXT_PUBLIC_SELF_HOSTED !== "true" || typeof window === "undefined") return undefined;
  // If the runtime endpoint is blocked/unavailable, do not silently reuse IDs
  // from a stale source build. Core local profiles still work without trackers.
  const value = window.__ARVIO_SELFHOST_CONFIG__;
  return normalizeSelfhostRuntimeConfig(value && typeof value === "object" ? value : {});
}

/** Must be called from the server only. Deliberately list fields rather than
 * serializing process.env, even if a deployment contains hosted credentials. */
export function serverSelfhostRuntimeConfig(env: Record<string, string | undefined>): SelfhostRuntimeConfig {
  return normalizeSelfhostRuntimeConfig({
    traktClientId: env.TRAKT_CLIENT_ID || env.NEXT_PUBLIC_TRAKT_CLIENT_ID || "",
    simklClientId: env.SIMKL_CLIENT_ID || env.NEXT_PUBLIC_SIMKL_CLIENT_ID || "",
    simklV2ClientId: env.SIMKL_V2_CLIENT_ID || env.NEXT_PUBLIC_SIMKL_V2_CLIENT_ID || "",
    telegramApiId: env.TELEGRAM_API_ID || env.NEXT_PUBLIC_TELEGRAM_API_ID || "",
    telegramApiHash: env.TELEGRAM_API_HASH || env.NEXT_PUBLIC_TELEGRAM_API_HASH || "",
    resolverUrl: env.ARVIO_RESOLVER_URL || env.NEXT_PUBLIC_ARVIO_RESOLVER_URL || ""
  });
}
