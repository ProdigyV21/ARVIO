import { serve } from "https://deno.land/std@0.168.0/http/server.ts"

// CORS: restrict origins using env `CORS_ALLOWED_ORIGINS` (comma-separated).
const DEFAULT_ALLOWED_ORIGINS = (Deno.env.get('CORS_ALLOWED_ORIGINS') || 'https://auth.arvio.tv,https://arvio.tv').split(',').map(s => s.trim()).filter(Boolean)

function corsHeaders(req: Request) {
  const origin = req.headers.get('origin') || ''
  const allowed = DEFAULT_ALLOWED_ORIGINS
  const allowOrigin = allowed.includes(origin) ? origin : 'null'
  return {
    'Access-Control-Allow-Origin': allowOrigin,
    'Access-Control-Allow-Headers': 'authorization, apikey, x-client-info, content-type',
    'Access-Control-Allow-Methods': 'POST, OPTIONS',
  }
}

const emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,63}$/i
const blockedEmailDomains = new Set([
  "10minutemail.com",
  "20minutemail.com",
  "dispostable.com",
  "emailondeck.com",
  "example.com",
  "example.net",
  "example.org",
  "fakeinbox.com",
  "getnada.com",
  "grr.la",
  "guerrillamail.biz",
  "invalid",
  "localhost",
  "maildrop.cc",
  "mailinator.com",
  "moakt.com",
  "sharklasers.com",
  "tempmailo.com",
  "trashmail.com",
  "guerrillamail.com",
  "guerrillamail.de",
  "guerrillamail.info",
  "guerrillamail.net",
  "guerrillamail.org",
  "tempmail.com",
  "temp-mail.org",
  "yopmail.com",
])
const blockedEmailDomainFragments = [
  "10minutemail",
  "disposable",
  "fakeinbox",
  "guerrillamail",
  "maildrop",
  "mailinator",
  "tempmail",
  "temp-mail",
  "trashmail",
  "yopmail",
]
const blockedSignupLocalParts = new Set([
  "asdf",
  "example",
  "fake",
  "invalid",
  "no-reply",
  "none",
  "noreply",
  "null",
  "qwerty",
  "test",
])

const signupWindowMs = 60 * 60 * 1000
const maxSignupAttemptsPerIp = 4
const emailSignupCooldownMs = 5 * 60 * 1000
const ipSignupAttempts = new Map<string, { count: number; resetAt: number }>()
const emailSignupAttempts = new Map<string, number>()

function clientIp(req: Request): string {
  const forwardedFor = req.headers.get("x-forwarded-for")
  if (forwardedFor) {
    return forwardedFor.split(",")[0]?.trim() || "unknown"
  }
  return req.headers.get("cf-connecting-ip") || req.headers.get("x-real-ip") || "unknown"
}

function pruneSignupAttempts(now: number) {
  for (const [ip, bucket] of ipSignupAttempts.entries()) {
    if (bucket.resetAt <= now) ipSignupAttempts.delete(ip)
  }
  for (const [email, timestamp] of emailSignupAttempts.entries()) {
    if (now - timestamp > emailSignupCooldownMs) emailSignupAttempts.delete(email)
  }
}

function enforceSignupRateLimit(req: Request, email: string): string | null {
  const now = Date.now()
  pruneSignupAttempts(now)

  const lastEmailAttempt = emailSignupAttempts.get(email)
  if (lastEmailAttempt && now - lastEmailAttempt < emailSignupCooldownMs) {
    return "Please wait a few minutes before creating this account again"
  }

  const ip = clientIp(req)
  const existing = ipSignupAttempts.get(ip)
  if (existing && existing.resetAt > now && existing.count >= maxSignupAttemptsPerIp) {
    return "Too many account creation attempts. Try again later."
  }

  if (!existing || existing.resetAt <= now) {
    ipSignupAttempts.set(ip, { count: 1, resetAt: now + signupWindowMs })
  } else {
    existing.count += 1
    ipSignupAttempts.set(ip, existing)
  }
  emailSignupAttempts.set(email, now)
  return null
}

function isBlockedEmailDomain(domain: string): boolean {
  return blockedEmailDomains.has(domain) ||
    blockedEmailDomainFragments.some((fragment) => domain.includes(fragment))
}

function validateEmail(email: string): string | null {
  if (!email) return "Email is required"
  if (email.length > 254 || !emailRegex.test(email)) return "Enter a valid email address"
  if ((email.match(/@/g) ?? []).length !== 1) return "Enter a valid email address"

  const [localPart, domain = ""] = email.split("@")
  if (!localPart || !domain) return "Use a real email address"
  if (localPart.length > 64 || localPart.startsWith(".") || localPart.endsWith(".") || localPart.includes("..")) {
    return "Enter a valid email address"
  }

  const domainLabels = domain.split(".")
  if (domainLabels.length < 2 || domainLabels.some((part) => !part || part.length > 63)) {
    return "Enter a valid email address"
  }
  if (domainLabels.some((part) => part.startsWith("-") || part.endsWith("-"))) {
    return "Enter a valid email address"
  }
  if (/^\d+$/.test(domainLabels[domainLabels.length - 1])) {
    return "Enter a valid email address"
  }

  if (blockedSignupLocalParts.has(localPart)) return "Use a real email address"
  if (isBlockedEmailDomain(domain)) return "Use a real email address"
  if (
    domain.endsWith(".example") ||
    domain.endsWith(".invalid") ||
    domain.endsWith(".localhost") ||
    domain.endsWith(".local") ||
    domain.endsWith(".test")
  ) {
    return "Use a real email address"
  }
  return null
}

async function createConfirmedUser(
  supabaseUrl: string,
  serviceRole: string,
  email: string,
  password: string,
): Promise<Response> {
  return fetch(`${supabaseUrl}/auth/v1/admin/users`, {
    method: "POST",
    headers: {
      apikey: serviceRole,
      Authorization: `Bearer ${serviceRole}`,
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      email,
      password,
      email_confirm: true,
      user_metadata: {
        provider: "email",
      },
    }),
  })
}

async function passwordToken(
  supabaseUrl: string,
  anonKey: string,
  email: string,
  password: string,
): Promise<Response> {
  return fetch(`${supabaseUrl}/auth/v1/token?grant_type=password`, {
    method: "POST",
    headers: {
      apikey: anonKey,
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ email, password }),
  })
}

function parseAuthError(raw: string): string {
  try {
    const json = JSON.parse(raw)
    return String(json.error_description || json.msg || json.message || json.error || raw)
  } catch {
    return raw
  }
}

serve(async (req) => {
  if (req.method === "OPTIONS") {
    return new Response("ok", { headers: corsHeaders(req) })
  }

  if (req.method !== "POST") {
    return new Response(JSON.stringify({ error: "Method not allowed" }), {
      status: 405,
      headers: { ...corsHeaders(req), "Content-Type": "application/json" },
    })
  }

  try {
    const anonHeader = req.headers.get("apikey")
    const authHeader = req.headers.get("authorization")
    const expectedAnon = Deno.env.get("APP_ANON_KEY") ?? Deno.env.get("SUPABASE_ANON_KEY")

    const hasValidApiKey = !!anonHeader && !!expectedAnon && anonHeader === expectedAnon
    const hasValidBearer = !!authHeader && authHeader.startsWith("Bearer ") &&
      !!expectedAnon && authHeader.replace("Bearer ", "") === expectedAnon

    if (!hasValidApiKey && !hasValidBearer) {
      return new Response(JSON.stringify({ error: "Unauthorized" }), {
        status: 401,
        headers: { ...corsHeaders(req), "Content-Type": "application/json" },
      })
    }

    const supabaseUrl = Deno.env.get("SUPABASE_URL")
    const serviceRole = Deno.env.get("SUPABASE_SERVICE_ROLE_KEY")
    const anonKey = expectedAnon
    if (!supabaseUrl || !serviceRole || !anonKey) {
      throw new Error("Missing Supabase credentials")
    }

    const body = await req.json().catch(() => ({})) as {
      email?: string
      password?: string
    }

    const email = body.email?.trim().toLowerCase() || ""
    const password = body.password || ""

    const emailError = validateEmail(email)
    if (emailError) {
      return new Response(JSON.stringify({ error: emailError }), {
        status: 400,
        headers: { ...corsHeaders(req), "Content-Type": "application/json" },
      })
    }

    if (password.length < 6) {
      return new Response(JSON.stringify({ error: "Password must be at least 6 characters" }), {
        status: 400,
        headers: { ...corsHeaders(req), "Content-Type": "application/json" },
      })
    }

    const rateLimitError = enforceSignupRateLimit(req, email)
    if (rateLimitError) {
      return new Response(JSON.stringify({ error: rateLimitError }), {
        status: 429,
        headers: { ...corsHeaders(req), "Content-Type": "application/json" },
      })
    }

    const createResp = await createConfirmedUser(supabaseUrl, serviceRole, email, password)
    if (!createResp.ok) {
      const createText = await createResp.text()
      const createError = parseAuthError(createText).toLowerCase()
      const alreadyExists = createResp.status === 422 ||
        createResp.status === 409 ||
        createError.includes("already") ||
        createError.includes("registered") ||
        createError.includes("exists")

      if (!alreadyExists) {
        return new Response(JSON.stringify({ error: "Unable to create account" }), {
          status: 400,
          headers: { ...corsHeaders(req), "Content-Type": "application/json" },
        })
      }
    }

    const tokenResp = await passwordToken(supabaseUrl, anonKey, email, password)
    const tokenText = await tokenResp.text()
    if (!tokenResp.ok) {
      const message = createResp.ok
        ? "Account created but sign-in failed. Try signing in."
        : "Account already exists. Sign in instead."
      return new Response(JSON.stringify({ error: message }), {
        status: tokenResp.status === 400 ? 409 : tokenResp.status,
        headers: { ...corsHeaders(req), "Content-Type": "application/json" },
      })
    }

    const tokenJson = JSON.parse(tokenText)
    return new Response(JSON.stringify({
      access_token: tokenJson.access_token,
      refresh_token: tokenJson.refresh_token,
      user: tokenJson.user,
    }), {
      headers: { ...corsHeaders(req), "Content-Type": "application/json" },
    })
  } catch (error) {
    return new Response(
      JSON.stringify({ error: error instanceof Error ? error.message : "Unexpected error" }),
      { status: 500, headers: { ...corsHeaders(req), "Content-Type": "application/json" } },
    )
  }
})
