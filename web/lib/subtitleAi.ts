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
// gemini-2.5-flash was retired by Google (July 2026); same model as the Android app.
const GEMINI_MODEL_ID = "gemini-3.5-flash-lite";
const GEMINI_URL = `https://generativelanguage.googleapis.com/v1beta/models/${GEMINI_MODEL_ID}:generateContent`;
const NL = "⏎";
const BATCH_WINDOW_MS = 150;

/** Hosted (Premium) translation: lines in, translated lines out, same order. */
export type HostedTranslate = (lines: string[], targetLanguage: string) => Promise<string[]>;

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

  /** Set when hosted translation was refused for good (e.g. Premium ended): stop asking. */
  private disabled = false;
  onHostedError?: (code: string) => void;

  constructor(
    private apiKey: string,
    private model: "groq" | "gemini" | "hosted",
    private targetLanguage: string,
    private hosted?: HostedTranslate
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
    const batch = this.queue.splice(0, this.model === "hosted" ? 24 : 12);
    if (!batch.length) return;
    this.busy = true;
    this.onTranslatingChanged?.(true);
    try {
      if (Date.now() < this.backoffUntil) await new Promise((r) => setTimeout(r, this.backoffUntil - Date.now()));
      const encoded = batch.map((line) => line.replace(/\n/g, NL));
      if (this.disabled) throw new Error("hosted translation unavailable");
      const translated = this.model === "hosted"
        ? await this.callHosted(encoded)
        : this.model === "gemini"
          ? await this.callGemini(encoded)
          : await this.callGroq(encoded);
      batch.forEach((key, index) => {
        const value = (translated?.[index] ?? key).replace(new RegExp(NL, "g"), "\n");
        if (translated) this.cache.set(key, value);
        this.pending.get(key)?.forEach((resolve) => resolve(value));
        this.pending.delete(key);
      });
    } catch {
      batch.forEach((key) => {
        this.pending.get(key)?.forEach((resolve) => resolve(key));
        this.pending.delete(key);
      });
    } finally {
      this.busy = false;
      this.onTranslatingChanged?.(false);
      if (this.queue.length) this.timer = setTimeout(() => void this.flush(), 40);
    }
  }

  private async callHosted(lines: string[]): Promise<string[] | null> {
    if (!this.hosted) return null;
    try {
      return await this.hosted(lines, this.targetLanguage);
    } catch (error) {
      const code = (error as { code?: string }).code ?? "unavailable";
      const status = (error as { status?: number }).status ?? 0;
      const retry = (error as { retryAfterSeconds?: number | null }).retryAfterSeconds;
      if (status === 429) this.backoffUntil = Date.now() + Math.max(5, retry ?? 30) * 1000;
      // Premium ended, signed out, or the monthly fair-use limit: originals from here on.
      if (status === 401 || status === 403 || code === "monthly_limit_reached") this.disabled = true;
      this.onHostedError?.(code);
      throw error;
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
    if (response.status === 429) {
      this.backoffUntil = Date.now() + 5000;
      throw new Error("rate limited");
    }
    if (!response.ok) throw new Error(`groq ${response.status}`);
    const payload = await response.json();
    const content = payload?.choices?.[0]?.message?.content ?? "";
    const parsed = extractJsonArray(content);
    return parsed && parsed.length === lines.length ? parsed : null;
  }

  private async callGemini(lines: string[]): Promise<string[] | null> {
    const response = await fetch(`${GEMINI_URL}?key=${encodeURIComponent(this.apiKey)}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        systemInstruction: { parts: [{ text: systemPrompt(this.targetLanguage) }] },
        contents: [{ parts: [{ text: JSON.stringify(lines) }] }],
        generationConfig: { temperature: 0.1 }
      })
    });
    if (response.status === 429) {
      this.backoffUntil = Date.now() + 5000;
      throw new Error("rate limited");
    }
    if (!response.ok) throw new Error(`gemini ${response.status}`);
    const payload = await response.json();
    const content = payload?.candidates?.[0]?.content?.parts?.[0]?.text ?? "";
    const parsed = extractJsonArray(content);
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
