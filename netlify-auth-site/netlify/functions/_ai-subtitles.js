// Hosted AI subtitle translation for ARVIO Premium members.
//
// Same prompt, providers and line-break sentinel as the apps' bring-your-own-key
// translators (web/lib/subtitleAi.ts, SubtitleTranslationService.kt), so hosted and
// personal-key translations read the same. Lines are cached by language + line text,
// so a popular film or episode is translated once and then served to every member.
//
// Only plain subtitle text reaches this code: no titles, file names or stream links.

const crypto = require("node:crypto");

const GROQ_URL = "https://api.groq.com/openai/v1/chat/completions";
const GROQ_MODEL_ID = "openai/gpt-oss-120b";
const GEMINI_MODEL_ID = "gemini-3.5-flash-lite";
const GEMINI_URL = `https://generativelanguage.googleapis.com/v1beta/models/${GEMINI_MODEL_ID}:generateContent`;
const NL = "⏎"; // ⏎ — the apps encode in-cue line breaks with it.

const LIMITS = {
  maxLines: 60,
  maxLineChars: 400,
  maxBatchChars: 12_000,
  // Fair use: about 40 hours of translated video a month (a 2-hour film is ~1,400 lines).
  monthlyLines: Number(process.env.AI_SUBTITLES_MONTHLY_LINES || 30_000),
  // Requests per account per minute; playback asks every few seconds at most.
  perMinute: Number(process.env.AI_SUBTITLES_PER_MINUTE || 30),
  providerTimeoutMs: 8_000
};

function systemPrompt(targetLanguage) {
  return `You are a professional subtitle translator. Translate the following JSON array into natural ${targetLanguage}.\n` +
    "Rules:\n" +
    "1. Return ONLY a valid JSON array.\n" +
    "2. Keep the exact same order and element count.\n" +
    `3. Preserve the '${NL}' symbol exactly where it appears as a line break.\n` +
    `4. Use informal, spoken ${targetLanguage} suitable for cinema.`;
}

/** "Dutch", "Português (Brasil)", "Chinese (Simplified)": letters, spaces, brackets and dashes only. */
function cleanLanguage(value) {
  const language = String(value || "").trim().replace(/\s+/g, " ");
  if (language.length < 2 || language.length > 40) return null;
  if (!/^[\p{L}\p{M} ()\-]+$/u.test(language)) return null;
  return language;
}

/** Validates the request body; returns { lines, language } or { error }. */
function validateRequest(body) {
  const language = cleanLanguage(body && body.targetLanguage);
  if (!language) return { error: "invalid_language" };
  const raw = Array.isArray(body && body.lines) ? body.lines : null;
  if (!raw || !raw.length) return { error: "no_lines" };
  if (raw.length > LIMITS.maxLines) return { error: "too_many_lines" };
  const lines = raw.map((line) => String(line ?? ""));
  if (lines.some((line) => line.length > LIMITS.maxLineChars)) return { error: "line_too_long" };
  if (lines.reduce((sum, line) => sum + line.length, 0) > LIMITS.maxBatchChars) return { error: "batch_too_large" };
  return { lines, language };
}

function extractJsonArray(text) {
  const value = String(text || "");
  const codeBlocks = [...value.matchAll(/```(?:json)?\s*([\s\S]*?)```/g)].map((m) => m[1].trim()).reverse();
  const stripped = value.replace(/```[^`]*```/g, "").trim();
  for (const candidate of [...codeBlocks, stripped, value]) {
    try {
      const parsed = JSON.parse(candidate);
      if (Array.isArray(parsed)) return parsed.map(String);
    } catch { /* try the next shape */ }
    const start = candidate.indexOf("[");
    const end = candidate.lastIndexOf("]");
    if (start < 0 || end <= start) continue;
    try {
      const parsed = JSON.parse(candidate.slice(start, end + 1));
      if (Array.isArray(parsed)) return parsed.map(String);
    } catch { /* try the next shape */ }
  }
  return null;
}

function lineKey(language, line) {
  const slug = language.toLowerCase().replace(/[^\p{L}\p{N}]+/gu, "-");
  return `v1/${slug}/${crypto.createHash("sha256").update(line).digest("hex")}`;
}

async function withTimeout(fetchImpl, url, init, ms) {
  const controller = new AbortController();
  const timer = setTimeout(() => controller.abort(), ms);
  try {
    return await fetchImpl(url, { ...init, signal: controller.signal });
  } finally {
    clearTimeout(timer);
  }
}

async function callGroq(fetchImpl, apiKey, lines, language) {
  const response = await withTimeout(fetchImpl, GROQ_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json", Authorization: `Bearer ${apiKey}` },
    body: JSON.stringify({
      model: GROQ_MODEL_ID,
      temperature: 0.1,
      reasoning_effort: "low",
      reasoning_format: "hidden",
      messages: [
        { role: "system", content: systemPrompt(language) },
        { role: "user", content: JSON.stringify(lines) }
      ]
    })
  }, LIMITS.providerTimeoutMs);
  if (!response.ok) throw new Error(`groq_${response.status}`);
  const payload = await response.json();
  const parsed = extractJsonArray(payload?.choices?.[0]?.message?.content);
  return parsed && parsed.length === lines.length ? parsed : null;
}

async function callGemini(fetchImpl, apiKey, lines, language) {
  const response = await withTimeout(fetchImpl, `${GEMINI_URL}?key=${encodeURIComponent(apiKey)}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      systemInstruction: { parts: [{ text: systemPrompt(language) }] },
      contents: [{ parts: [{ text: JSON.stringify(lines) }] }],
      generationConfig: { temperature: 0.1 }
    })
  }, LIMITS.providerTimeoutMs);
  if (!response.ok) throw new Error(`gemini_${response.status}`);
  const payload = await response.json();
  const parsed = extractJsonArray(payload?.candidates?.[0]?.content?.parts?.[0]?.text);
  return parsed && parsed.length === lines.length ? parsed : null;
}

/**
 * Translates [lines] into [language]: cached lines are served from [cache], the rest go to
 * Groq first and Gemini when Groq fails. Returns { translations, cached, translated } where a
 * line that could not be translated is returned unchanged (the apps then show the original).
 */
async function translateLines({ lines, language, cache, fetchImpl, keys }) {
  const encoded = lines.map((line) => line.replace(/\r?\n/g, NL));
  const keysFor = encoded.map((line) => lineKey(language, line));
  const hits = await Promise.all(keysFor.map((key) => cache.get(key, { type: "json" }).catch(() => null)));
  const result = encoded.map((line, index) => (hits[index] && typeof hits[index].t === "string" ? hits[index].t : null));

  const missingIndexes = result.map((value, index) => (value === null && encoded[index].trim() ? index : -1)).filter((index) => index >= 0);
  let translatedCount = 0;
  if (missingIndexes.length) {
    const missing = missingIndexes.map((index) => encoded[index]);
    let translated = null;
    const errors = [];
    for (const [name, call] of [["groq", callGroq], ["gemini", callGemini]]) {
      const key = name === "groq" ? keys.groq : keys.gemini;
      if (!key) continue;
      try {
        translated = await call(fetchImpl, key, missing, language);
        if (translated) break;
        errors.push(`${name}_mismatch`);
      } catch (error) {
        errors.push(String(error && error.message || name));
      }
    }
    if (!translated && !keys.groq && !keys.gemini) {
      const error = new Error("not_configured");
      error.code = "not_configured";
      throw error;
    }
    if (!translated) {
      const error = new Error(errors.join(",") || "translation_failed");
      error.code = "translation_failed";
      throw error;
    }
    await Promise.all(missingIndexes.map((index, position) => {
      result[index] = translated[position];
      translatedCount += 1;
      return cache.setJSON(keysFor[index], { t: translated[position] }).catch(() => null);
    }));
  }

  return {
    translations: result.map((value, index) => (value ?? encoded[index]).split(NL).join("\n")),
    cached: hits.filter((hit) => hit && typeof hit.t === "string").length,
    translated: translatedCount
  };
}

function monthKey(emailHash, now = new Date()) {
  return `month/${now.toISOString().slice(0, 7)}/${emailHash}`;
}

/**
 * Fair-use check and accounting. Counts only lines that needed a fresh translation, so
 * cached lines are free. Not transactional: two parallel requests can each pass the check,
 * which is fine for a soft monthly limit.
 */
async function checkUsage(usage, emailHash, now = Date.now()) {
  const minuteKey = `minute/${emailHash}`;
  const minute = (await usage.get(minuteKey, { type: "json" }).catch(() => null)) || { windowStart: 0, count: 0 };
  const windowStart = now - minute.windowStart < 60_000 ? minute.windowStart : now;
  const count = windowStart === minute.windowStart ? minute.count : 0;
  if (count >= LIMITS.perMinute) return { allowed: false, reason: "rate_limited", retryAfterSeconds: Math.ceil((windowStart + 60_000 - now) / 1000) };
  await usage.setJSON(minuteKey, { windowStart, count: count + 1 }).catch(() => null);

  const month = (await usage.get(monthKey(emailHash, new Date(now)), { type: "json" }).catch(() => null)) || { lines: 0 };
  if (month.lines >= LIMITS.monthlyLines) return { allowed: false, reason: "monthly_limit_reached" };
  return { allowed: true, usedLines: month.lines };
}

async function recordUsage(usage, emailHash, usedBefore, freshLines, now = Date.now()) {
  if (!freshLines) return usedBefore;
  const used = usedBefore + freshLines;
  await usage.setJSON(monthKey(emailHash, new Date(now)), { lines: used }).catch(() => null);
  return used;
}

module.exports = { LIMITS, NL, systemPrompt, cleanLanguage, validateRequest, extractJsonArray, lineKey, translateLines, checkUsage, recordUsage };
