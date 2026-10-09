import { browserSelfhostRuntimeConfig } from "./selfhostRuntimeConfig";

function envValue(value: string | undefined, fallback = "") {
  return value && !value.startsWith("$") && !value.includes("****") ? value : fallback;
}

const selfHosted = process.env.NEXT_PUBLIC_SELF_HOSTED === "true";

export const config = {
  selfHosted,
  // Fixed by the browser build. Missing preserves the hosted feature; runtime
  // credentials cannot re-enable a distribution that intentionally omits it.
  telegramEnabled: process.env.NEXT_PUBLIC_TELEGRAM_ENABLED !== "false",
  sportsMetadataUrl: process.env.NEXT_PUBLIC_SPORTS_METADATA_URL ?? "",
  supabaseUrl: selfHosted ? "" : process.env.NEXT_PUBLIC_SUPABASE_URL ?? "",
  supabaseAnonKey: selfHosted ? "" : process.env.NEXT_PUBLIC_SUPABASE_ANON_KEY ?? "",
  appAnonKey: selfHosted ? "" : envValue(process.env.NEXT_PUBLIC_ARVIO_APP_ANON_KEY, process.env.NEXT_PUBLIC_SUPABASE_ANON_KEY ?? ""),
  netlifyBackendUrl: selfHosted ? "" : process.env.NEXT_PUBLIC_NETLIFY_BACKEND_URL ?? process.env.NETLIFY_BACKEND_URL ?? "https://auth.arvio.tv/.netlify/functions",
  // Next can evaluate client modules before beforeInteractive scripts execute.
  // Read bootstrap values when used, never freeze an empty module-load snapshot.
  get resolverUrl() { return browserSelfhostRuntimeConfig()?.resolverUrl ?? envValue(process.env.NEXT_PUBLIC_ARVIO_RESOLVER_URL, ""); },
  get traktClientId() { return browserSelfhostRuntimeConfig()?.traktClientId ?? process.env.NEXT_PUBLIC_TRAKT_CLIENT_ID ?? ""; },
  // OAuth secrets belong only on the server, never in the browser bundle.
  traktClientSecret: "",
  get simklV2ClientId() { return browserSelfhostRuntimeConfig()?.simklV2ClientId ?? (process.env.NEXT_PUBLIC_SIMKL_V2_CLIENT_ID || process.env.SIMKL_V2_CLIENT_ID || (selfHosted ? "" : "ebde0a0712da059710ba65ba3efe1edb601c2e5ae8e23c048c8d6df58ed922c2")); },
  get simklClientId() { return browserSelfhostRuntimeConfig()?.simklClientId ?? (process.env.NEXT_PUBLIC_SIMKL_CLIENT_ID || process.env.SIMKL_CLIENT_ID || ""); },
  allowNetlifyMediaProxy: envValue(process.env.NEXT_PUBLIC_ALLOW_NETLIFY_MEDIA_PROXY, "false") === "true",
  // Web subscription: the Ko-fi membership page the paywall links to, and a
  // master switch to enable the paywall (off by default so nothing changes for
  // users until you flip it in the environment).
  kofiUrl: envValue(process.env.NEXT_PUBLIC_KOFI_URL, ""),
  paywallEnabled: !selfHosted && envValue(process.env.NEXT_PUBLIC_PAYWALL_ENABLED, "false") === "true",
  imageBase: "https://image.tmdb.org/t/p/w780",
  backdropBase: "https://image.tmdb.org/t/p/w1280",
  backdropOriginal: "https://image.tmdb.org/t/p/original"
};

export const TELEGRAM_DISABLED_MESSAGE = "Telegram is not available in this Unraid preview. Choose another source.";

function containsTelegramStreamUrl(value: unknown): boolean {
  // Managed download records may contain the resolver's /media?url= wrapper,
  // rather than the original stream URL. Bound unwrapping to avoid recursion.
  for (let depth = 0; depth <= 2; depth++) {
    if (typeof value !== "string" || !value.trim()) return false;
    try {
      const url = new URL(value, "https://arvio.invalid");
      if (url.pathname.startsWith("/tg-stream/")) return true;
      value = url.searchParams.get("url");
    } catch {
      return false;
    }
  }
  return false;
}

// Saved sources can predate this build and may have lost their addon metadata.
// Check both URL fields, including absolute URLs, without trusting their shape.
export function isTelegramSource(source: unknown): boolean {
  if (!source || typeof source !== "object" || Array.isArray(source)) return false;
  const value = source as { addonId?: unknown; url?: unknown; originalUrl?: unknown };
  if (value.addonId === "telegram_native") return true;
  return [value.url, value.originalUrl].some(containsTelegramStreamUrl);
}

export function isDisabledTelegramSource(source: unknown): boolean {
  return !config.telegramEnabled && isTelegramSource(source);
}

export function assertTelegramSourceAvailable(source: unknown): void {
  if (isDisabledTelegramSource(source)) throw new Error(TELEGRAM_DISABLED_MESSAGE);
}

export function hasSupabaseConfig() {
  return config.supabaseUrl.startsWith("https://") && config.supabaseAnonKey.length > 40;
}

export function hasNetlifyBackendUrl() {
  return config.netlifyBackendUrl.startsWith("https://");
}

export function hasNetlifyBackendConfig() {
  return hasNetlifyBackendUrl() && config.appAnonKey.length > 40;
}

export function hasResolverConfig() {
  return config.resolverUrl.startsWith("https://") || config.resolverUrl.startsWith("http://localhost:");
}

export function hasTraktConfig() {
  return hasNetlifyBackendUrl() ||
    (config.traktClientId.length > 10 && !config.traktClientId.startsWith("__"));
}

export function hasSimklConfig() {
  return config.simklV2ClientId.length > 10 && !config.simklV2ClientId.startsWith("__");
}

export function getAuthPortalUrl(): string {
  if (config.selfHosted) return "/";
  const backend = config.netlifyBackendUrl;
  try {
    const url = new URL(backend);
    const cleanPath = url.pathname.replace(/\/\.netlify\/functions\/?$/, "/");
    return `${url.protocol}//${url.host}${cleanPath}`;
  } catch {
    return "https://auth.arvio.tv/";
  }
}

