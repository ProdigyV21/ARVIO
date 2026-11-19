// AI subtitle translation — web port of the Android app's
// SubtitleTranslationService/Manager: same providers (Groq gpt-oss-120b /
// Gemini), same prompt, same ⏎ line-break sentinel, batched with a
// short window, cached per cue text, 5s backoff on 429.

// llama-3.3-70b-versatile was decommissioned by Groq (August 16, 2026); gpt-oss-120b is the
// recommended replacement — faster, same 30 RPM free tier. It always reasons: "low" is the floor,
// and "hidden" keeps the chain-of-thought out of message.content. Mirrors the Android service.
const GROQ_MODEL_ID = "openai/gpt-oss-120b";
const GROQ_REASONING_EFFORT = "low";
const GROQ_REASONING_FORMAT = "hidden";
const GROQ_URL = "https://api.groq.com/openai/v1/chat/completions";
// gemini-2.5-flash was retired by Google (HTTP 404 "no longer available", July 2026).
// gemini-3.5-flash-lite is the model the Android SubtitleTranslationService uses:
// same v1beta generateContent API, thinking switched to "minimal" for speed.
export const GEMINI_MODEL_ID = "gemini-3.5-flash-lite";
const GEMINI_URL = `https://generativelanguage.googleapis.com/v1beta/models/${GEMINI_MODEL_ID}:generateContent`;
const GEMINI_MAX_TRANSIENT_RETRIES = 2;
const GEMINI_RETRY_BASE_DELAY_MS = 400;
const GEMINI_RETRYABLE_HTTP = new Set([500, 502, 503]);
const GEMINI_SAFETY_CATEGORIES = ["HARM_CATEGORY_HARASSMENT", "HARM_CATEGORY_HATE_SPEECH", "HARM_CATEGORY_SEXUALLY_EXPLICIT", "HARM_CATEGORY_DANGEROUS_CONTENT"];

/** A translation failure the viewer should see (rather than silently getting the original text). */
export class SubtitleTranslationError extends Error {
  constructor(message: string, readonly kind: "rate_limited" | "auth" | "model" | "network" | "response" | "blocked") {
    super(message);
    this.name = "SubtitleTranslationError";
  }
}

export function translationErrorFor(provider: "groq" | "gemini", status: number): SubtitleTranslationError {
  const name = provider === "gemini" ? "Gemini" : "Groq";
  if (status === 429) return new SubtitleTranslationError(`${name} rate limit reached — AI subtitles will retry shortly.`, "rate_limited");
  if (status === 401 || status === 403) return new SubtitleTranslationError(`${name} rejected the API key. Check AI subtitles in Settings.`, "auth");
  if (status === 400) return new SubtitleTranslationError(`${name} could not process this subtitle batch (HTTP 400).`, "network");
  if (status === 404) return new SubtitleTranslationError(`The ${name} translation model is unavailable right now.`, "model");
  return new SubtitleTranslationError(`${name} could not translate subtitles (HTTP ${status}).`, "network");
}

/** Gemini request body — mirrors Android's SubtitleTranslationService.translateGemini. */
export function geminiRequestBody(systemPromptText: string, targetLanguage: string, lines: string[]) {
  return {
    system_instruction: { parts: [{ text: systemPromptText }] },
    contents: [{ parts: [{ text: `Translate to ${targetLanguage}:\n${JSON.stringify(lines)}` }] }],
    generationConfig: {
      temperature: 0.1,
      responseMimeType: "application/json",
      // Subtitles need raw speed, not reasoning: "minimal" is ~1.3 s per batch
      // versus ~5 s with default thinking (measured on Android).
      thinkingConfig: { thinkingLevel: "minimal" }
    },
    // Film dialogue routinely trips the default filters (HTTP 200 with no
    // candidates); this translates the film's own subtitles.
    safetySettings: GEMINI_SAFETY_CATEGORIES.map((category) => ({ category, threshold: "BLOCK_NONE" }))
  };
}

/** Every non-thought text part — Gemini 3.x may emit thought parts first. */
export function geminiResponseText(payload: unknown): string {
  const parts = (payload as { candidates?: Array<{ content?: { parts?: Array<{ text?: unknown; thought?: unknown }> } }> })
    ?.candidates?.[0]?.content?.parts ?? [];
  return parts.filter((part) => part && part.thought !== true && typeof part.text === "string").map((part) => part.text as string).join("");
}
const NL = "⏎";
const BATCH_WINDOW_MS = 150;

function systemPrompt(targetLanguage: string) {
  return `You are a professional subtitle translator. Translate the following JSON array into natural ${targetLanguage}.\n` +
    "Rules:\n" +
    "1. Return ONLY a valid JSON array.\n" +
    "2. Keep the exact same order and element count.\n" +
    `3. Preserve the '${NL}' symbol exactly where it appears as a line break.\n` +
    `4. Use informal, spoken ${targetLanguage} suitable for cinema.`;
}

function extractJsonArray(text: string): string[] | null {
  const codeBlocks = [...text.matchAll(/```(?:json)?\s*([\s\S]*?)```/g)].map((m) => m[1].trim()).reverse();
  const stripped = text.replace(/```[^`]*```/g, "").trim();
  for (const candidate of [...codeBlocks, stripped, text]) {
    try {
      const parsed = JSON.parse(candidate);
      if (Array.isArray(parsed)) return parsed.map(String);
    } catch { /* try next */ }
    const start = candidate.indexOf("[");
    const end = candidate.lastIndexOf("]");
    if (start < 0 || end <= start) continue;
    try {
      const parsed = JSON.parse(candidate.slice(start, end + 1));
      if (Array.isArray(parsed)) return parsed.map(String);
    } catch { /* try next */ }
  }
  return null;
}

export class SubtitleTranslator {
  private cache = new Map<string, string>();
  private pending = new Map<string, Array<(value: string) => void>>();
  private queue: string[] = [];
  private timer: ReturnType<typeof setTimeout> | null = null;
  private busy = false;
  private backoffUntil = 0;
  onTranslatingChanged?: (translating: boolean) => void;
  /** Called when a batch could not be translated; its cues keep the original text. */
  onError?: (error: SubtitleTranslationError) => void;

  constructor(
    private apiKey: string,
    private model: "groq" | "gemini",
    private targetLanguage: string
  ) {}

  get translatedCount() {
    return this.cache.size;
  }

  /** Translate one cue text; resolves with the original on failure. */
  translate(text: string): Promise<string> {
    const key = text.trim();
    if (!key) return Promise.resolve(text);
    const cached = this.cache.get(key);
    if (cached) return Promise.resolve(cached);
    return new Promise((resolve) => {
      const waiters = this.pending.get(key);
      if (waiters) {
        waiters.push(resolve);
        return;
      }
      this.pending.set(key, [resolve]);
      this.queue.push(key);
      if (!this.timer) this.timer = setTimeout(() => void this.flush(), BATCH_WINDOW_MS);
    });
  }

  /** Pre-warm upcoming cues so swaps happen before they display. */
  prefetch(texts: string[]) {
    for (const text of texts) void this.translate(text);
  }

  private async flush() {
    this.timer = null;
    if (this.busy) return;
    const batch = this.queue.splice(0, 12);
    if (!batch.length) return;
    this.busy = true;
    this.onTranslatingChanged?.(true);
    try {
      if (Date.now() < this.backoffUntil) await new Promise((r) => setTimeout(r, this.backoffUntil - Date.now()));
      const encoded = batch.map((line) => line.replace(/\n/g, NL));
      const translated = this.model === "gemini"
        ? await this.callGemini(encoded)
        : await this.callGroq(encoded);
      if (!translated) throw new SubtitleTranslationError("AI subtitles returned an unreadable translation — showing the original line.", "response");
      batch.forEach((key, index) => {
        const value = (translated[index] ?? key).replace(new RegExp(NL, "g"), "\n");
        this.cache.set(key, value);
        this.pending.get(key)?.forEach((resolve) => resolve(value));
        this.pending.delete(key);
      });
    } catch (error) {
      batch.forEach((key) => {
        this.pending.get(key)?.forEach((resolve) => resolve(key));
        this.pending.delete(key);
      });
      const failure = error instanceof SubtitleTranslationError ? error
        : new SubtitleTranslationError("AI subtitles could not reach the translation service — showing the original line.", "network");
      // A content-policy block hits one line, is not actionable and must not
      // nag (same rule as Android); everything else is shown to the viewer.
      if (failure.kind !== "blocked") this.onError?.(failure);
    } finally {
      this.busy = false;
      this.onTranslatingChanged?.(false);
      if (this.queue.length) this.timer = setTimeout(() => void this.flush(), 40);
    }
  }

  private async callGroq(lines: string[]): Promise<string[] | null> {
    const response = await fetch(GROQ_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json", Authorization: `Bearer ${this.apiKey}` },
      body: JSON.stringify({
        model: GROQ_MODEL_ID,
        temperature: 0.1,
        reasoning_effort: GROQ_REASONING_EFFORT,
        reasoning_format: GROQ_REASONING_FORMAT,
        messages: [
          { role: "system", content: systemPrompt(this.targetLanguage) },
          { role: "user", content: JSON.stringify(lines) }
        ]
      })
    });
    if (response.status === 429) this.backoffUntil = Date.now() + 5000;
    if (!response.ok) throw translationErrorFor("groq", response.status);
    const payload = await response.json();
    const content = payload?.choices?.[0]?.message?.content ?? "";
    const parsed = extractJsonArray(content);
    return parsed && parsed.length === lines.length ? parsed : null;
  }

  private async callGemini(lines: string[], transientAttempt = 0): Promise<string[] | null> {
    // Key in a header (as on Android), not the URL, so it never lands in logs.
    const response = await fetch(GEMINI_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json", "x-goog-api-key": this.apiKey },
      body: JSON.stringify(geminiRequestBody(systemPrompt(this.targetLanguage), this.targetLanguage, lines))
    });
    if (GEMINI_RETRYABLE_HTTP.has(response.status) && transientAttempt < GEMINI_MAX_TRANSIENT_RETRIES) {
      // Google-side capacity blips usually clear within a second.
      const delay = GEMINI_RETRY_BASE_DELAY_MS * 2 ** transientAttempt * (0.7 + Math.random() * 0.6);
      await new Promise((resolve) => setTimeout(resolve, delay));
      return this.callGemini(lines, transientAttempt + 1);
    }
    if (response.status === 429) this.backoffUntil = Date.now() + 5000;
    if (!response.ok) throw translationErrorFor("gemini", response.status);
    const payload = await response.json();
    if (!payload?.candidates?.length) {
      if (payload?.promptFeedback?.blockReason) throw new SubtitleTranslationError("Gemini declined to translate this line.", "blocked");
      throw new SubtitleTranslationError("Gemini returned no translation — showing the original line.", "response");
    }
    const parsed = extractJsonArray(geminiResponseText(payload));
    return parsed && parsed.length === lines.length ? parsed : null;
  }
}

/** ISO code or English display name for the target language prompt. */
export function subtitleLanguageName(codeOrName: string): string {
  const value = (codeOrName ?? "").trim();
  if (!value || value.toLowerCase() === "off") return "";
  if (value.length > 3) return value;
  try {
    return new Intl.DisplayNames(["en"], { type: "language" }).of(value.toLowerCase()) ?? value;
  } catch {
    return value;
  }
}
