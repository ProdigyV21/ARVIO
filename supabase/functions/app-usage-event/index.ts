import { serve } from "https://deno.land/std@0.168.0/http/server.ts"

// CORS: restrict origins using env `CORS_ALLOWED_ORIGINS` (comma-separated).
const DEFAULT_ALLOWED_ORIGINS = (Deno.env.get('CORS_ALLOWED_ORIGINS') || 'https://auth.arvio.tv,https://arvio.tv').split(',').map(s => s.trim()).filter(Boolean)

function corsHeaders(req: Request) {
  const origin = req.headers.get('origin') || ''
  const allowed = DEFAULT_ALLOWED_ORIGINS
  const allowOrigin = allowed.includes(origin) ? origin : 'null'
  return {
    'Access-Control-Allow-Origin': allowOrigin,
    'Access-Control-Allow-Headers': 'authorization, apikey, x-client-info, content-type, x-user-token',
    'Access-Control-Allow-Methods': 'POST, OPTIONS',
  }
}

const RATE_LIMIT = 120
const RATE_WINDOW_MS = 60 * 1000
const rateLimitMap = new Map<string, { count: number; resetAt: number }>()

function clientIp(req: Request): string {
  return req.headers.get("x-forwarded-for")?.split(",")[0]?.trim() ||
    req.headers.get("cf-connecting-ip") ||
    req.headers.get("x-real-ip") ||
    "unknown"
}

function checkRateLimit(req: Request): boolean {
  const now = Date.now()
  const ip = clientIp(req)
  const bucket = rateLimitMap.get(ip)
  if (!bucket || bucket.resetAt <= now) {
    rateLimitMap.set(ip, { count: 1, resetAt: now + RATE_WINDOW_MS })
    return true
  }
  if (bucket.count >= RATE_LIMIT) return false
  bucket.count += 1
  return true
}

setInterval(() => {
  const now = Date.now()
  for (const [ip, bucket] of rateLimitMap.entries()) {
    if (bucket.resetAt <= now) rateLimitMap.delete(ip)
  }
}, RATE_WINDOW_MS)

function jsonResponse(req: Request, body: Record<string, unknown>, status = 200): Response {
  return new Response(JSON.stringify(body), {
    status,
    headers: { ...corsHeaders(req), "Content-Type": "application/json" },
  })
}

function cleanText(value: unknown, maxLength: number): string | null {
  if (typeof value !== "string") return null
  const trimmed = value.trim()
  if (!trimmed) return null
  return trimmed.slice(0, maxLength)
}

function cleanNumber(value: unknown): number | null {
  if (typeof value !== "number" || !Number.isFinite(value)) return null
  return Math.trunc(value)
}

function cleanMetadata(value: unknown): Record<string, unknown> {
  if (!value || typeof value !== "object" || Array.isArray(value)) return {}
  const out: Record<string, unknown> = {}
  for (const [key, raw] of Object.entries(value as Record<string, unknown>)) {
    if (Object.keys(out).length >= 20) break
    const safeKey = key.trim().slice(0, 64)
    if (!safeKey) continue
    if (typeof raw === "string") out[safeKey] = raw.slice(0, 160)
    else if (typeof raw === "number" && Number.isFinite(raw)) out[safeKey] = raw
    else if (typeof raw === "boolean") out[safeKey] = raw
  }
  return out
}

async function resolveUserId(supabaseUrl: string, anonKey: string, token: string | null): Promise<string | null> {
  if (!token || token.length < 20 || /\s/.test(token)) return null

  const response = await fetch(`${supabaseUrl}/auth/v1/user`, {
    headers: {
      apikey: anonKey,
      Authorization: `Bearer ${token}`,
    },
  })
  if (!response.ok) return null

  const json = await response.json().catch(() => null) as { id?: string } | null
  return cleanText(json?.id, 64)
}

serve(async (req) => {
  if (req.method === "OPTIONS") return new Response("ok", { headers: corsHeaders(req) })
  if (req.method !== "POST") return jsonResponse(req, { error: "Method not allowed" }, 405)

  try {
    const anonHeader = req.headers.get("apikey")
    const authHeader = req.headers.get("authorization")
    const expectedAnon = Deno.env.get("APP_ANON_KEY") ?? Deno.env.get("SUPABASE_ANON_KEY")

    const hasValidApiKey = !!anonHeader && !!expectedAnon && anonHeader === expectedAnon
    const hasValidBearer = !!authHeader && authHeader.startsWith("Bearer ") &&
      !!expectedAnon && authHeader.replace("Bearer ", "") === expectedAnon

    if (!hasValidApiKey && !hasValidBearer) return jsonResponse(req, { error: "Unauthorized" }, 401)
    if (!checkRateLimit(req)) return jsonResponse(req, { error: "Rate limit exceeded" }, 429)

    const supabaseUrl = Deno.env.get("SUPABASE_URL")
    const serviceRole = Deno.env.get("SUPABASE_SERVICE_ROLE_KEY")
    if (!supabaseUrl || !serviceRole || !expectedAnon) {
      throw new Error("Missing Supabase credentials")
    }

    const body = await req.json().catch(() => ({})) as Record<string, unknown>
    const eventName = cleanText(body.event_name, 40) || "app_open"
    if (eventName !== "app_open") return jsonResponse(req, { error: "Unsupported event" }, 400)

    const installId = cleanText(body.install_id, 128)
    if (!installId || installId.length < 8) return jsonResponse(req, { error: "Invalid install_id" }, 400)

    const userToken = cleanText(req.headers.get("x-user-token"), 4096)
    const userId = await resolveUserId(supabaseUrl, expectedAnon, userToken)
    const now = new Date()
    const row = {
      event_name: eventName,
      event_date: now.toISOString().slice(0, 10),
      install_id: installId,
      user_id: userId,
      profile_id: cleanText(body.profile_id, 128),
      platform: cleanText(body.platform, 24) || "android",
      device_type: cleanText(body.device_type, 24),
      app_version: cleanText(body.app_version, 40),
      app_version_code: cleanNumber(body.app_version_code),
      distribution: cleanText(body.distribution, 24),
      metadata: cleanMetadata(body.metadata),
      updated_at: now.toISOString(),
    }

    const response = await fetch(
      `${supabaseUrl}/rest/v1/app_usage_events?on_conflict=event_name,event_date,install_id`,
      {
        method: "POST",
        headers: {
          apikey: serviceRole,
          Authorization: `Bearer ${serviceRole}`,
          "Content-Type": "application/json",
          Prefer: "resolution=merge-duplicates,return=minimal",
        },
        body: JSON.stringify(row),
      },
    )

    if (!response.ok) {
      const text = await response.text().catch(() => "")
      throw new Error(`Usage upsert failed: ${response.status} ${text}`)
    }

    return jsonResponse(req, { ok: true })
  } catch (error) {
    console.error(error)
    return jsonResponse(req, { error: "Internal server error" }, 500)
  }
})
