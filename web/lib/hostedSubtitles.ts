// Hosted AI subtitles (ARVIO Premium): the auth backend translates with ARVIO's own
// provider keys, so members need no Groq or Gemini key. See
// netlify-auth-site/netlify/functions/ai-subtitles-translate.js.
import { config } from "./config";

export class HostedSubtitleError extends Error {
  constructor(readonly code: string, readonly status: number, readonly retryAfterSeconds?: number | null) {
    super(code);
  }
}

/** Lines per request: the server accepts up to 60. */
export const HOSTED_BATCH_SIZE = 24;

/** True when this build talks to ARVIO's hosted backend (self-hosted web has none). */
export function hostedSubtitlesSupported() {
  return !config.selfHosted && Boolean(config.netlifyBackendUrl);
}

export async function translateHosted(accessToken: () => Promise<string>, lines: string[], targetLanguage: string): Promise<string[]> {
  const token = await accessToken();
  const response = await fetch(`${config.netlifyBackendUrl.replace(/\/+$/, "")}/ai-subtitles-translate`, {
    method: "POST",
    headers: { "Content-Type": "application/json", Authorization: `Bearer ${token}` },
    body: JSON.stringify({ lines, targetLanguage })
  });
  const payload = await response.json().catch(() => ({})) as { translations?: unknown; error?: string; retryAfterSeconds?: number | null };
  if (!response.ok) throw new HostedSubtitleError(payload.error || `http_${response.status}`, response.status, payload.retryAfterSeconds);
  const translations = Array.isArray(payload.translations) ? payload.translations.map(String) : null;
  if (!translations || translations.length !== lines.length) throw new HostedSubtitleError("bad_response", response.status);
  return translations;
}
