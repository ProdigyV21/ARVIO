import { serverSelfhostRuntimeConfig } from "@/lib/selfhostRuntimeConfig";

// Never prerender these values or cache them in a proxy between deployments.
export const dynamic = "force-dynamic";

export function GET() {
  const selfHosted = process.env.NEXT_PUBLIC_SELF_HOSTED === "true";
  const configuration = serverSelfhostRuntimeConfig(process.env);
  // Keep the bootstrap shape compatible, but never advertise credentials for
  // an integration absent from this particular compiled image.
  if (process.env.NEXT_PUBLIC_TELEGRAM_ENABLED === "false") {
    configuration.telegramApiId = "";
    configuration.telegramApiHash = "";
  }
  const body = selfHosted
    ? `window.__ARVIO_SELFHOST_CONFIG__ = ${JSON.stringify(configuration).replace(/</g, "\\u003c")};\n`
    : "/* Runtime self-host configuration is unavailable on the hosted service. */\n";
  return new Response(body, {
    status: selfHosted ? 200 : 404,
    headers: {
      "content-type": "application/javascript; charset=utf-8",
      "cache-control": "private, no-store, max-age=0",
      "x-content-type-options": "nosniff"
    }
  });
}
