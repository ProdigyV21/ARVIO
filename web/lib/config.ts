function envValue(value: string | undefined, fallback = "") {
  return value && !value.startsWith("$") && !value.includes("****") ? value : fallback;
}

const selfHosted = process.env.NEXT_PUBLIC_SELF_HOSTED === "true";

export const config = {
  selfHosted,
  sportsMetadataUrl: process.env.NEXT_PUBLIC_SPORTS_METADATA_URL ?? "",
  supabaseUrl: selfHosted ? "" : process.env.NEXT_PUBLIC_SUPABASE_URL ?? "",
  supabaseAnonKey: selfHosted ? "" : process.env.NEXT_PUBLIC_SUPABASE_ANON_KEY ?? "",
  appAnonKey: selfHosted ? "" : envValue(process.env.NEXT_PUBLIC_ARVIO_APP_ANON_KEY, process.env.NEXT_PUBLIC_SUPABASE_ANON_KEY ?? ""),
  netlifyBackendUrl: selfHosted ? "" : process.env.NEXT_PUBLIC_NETLIFY_BACKEND_URL ?? process.env.NETLIFY_BACKEND_URL ?? "https://auth.arvio.tv/.netlify/functions",
  resolverUrl: envValue(process.env.NEXT_PUBLIC_ARVIO_RESOLVER_URL, ""),
  traktClientId: process.env.NEXT_PUBLIC_TRAKT_CLIENT_ID ?? "",
  // OAuth secrets belong only on the server, never in the browser bundle.
  traktClientSecret: "",
  simklClientId: process.env.NEXT_PUBLIC_SIMKL_CLIENT_ID || process.env.SIMKL_CLIENT_ID || "",
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
  return hasNetlifyBackendUrl() || (config.simklClientId.length > 10 && !config.simklClientId.startsWith("__"));
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

