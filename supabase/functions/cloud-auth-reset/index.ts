import { serve } from "https://deno.land/std@0.168.0/http/server.ts"

const corsHeaders = {
  "Access-Control-Allow-Origin": "*",
  "Access-Control-Allow-Headers": "authorization, apikey, x-client-info, content-type",
  "Access-Control-Allow-Methods": "POST, OPTIONS",
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
  "guerrillamail.com",
  "guerrillamail.de",
  "guerrillamail.info",
  "guerrillamail.net",
  "guerrillamail.org",
  "invalid",
  "localhost",
  "maildrop.cc",
  "mailinator.com",
  "moakt.com",
  "sharklasers.com",
  "temp-mail.org",
  "tempmail.com",
  "tempmailo.com",
  "trashmail.com",
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
const blockedResetLocalParts = new Set([
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

const resetWindowMs = 60 * 60 * 1000
const maxResetAttemptsPerIp = 4
const emailResetCooldownMs = 10 * 60 * 1000
const ipResetAttempts = new Map<string, { count: number; resetAt: number }>()
const emailResetAttempts = new Map<string, number>()

function clientIp(req: Request): string {
  const forwardedFor = req.headers.get("x-forwarded-for")
  if (forwardedFor) {
    return forwardedFor.split(",")[0]?.trim() || "unknown"
  }
  return req.headers.get("cf-connecting-ip") || req.headers.get("x-real-ip") || "unknown"
}

function pruneResetAttempts(now: number) {
  for (const [ip, bucket] of ipResetAttempts.entries()) {
    if (bucket.resetAt <= now) ipResetAttempts.delete(ip)
  }
  for (const [email, timestamp] of emailResetAttempts.entries()) {
    if (now - timestamp > emailResetCooldownMs) emailResetAttempts.delete(email)
  }
}

function enforceResetRateLimit(req: Request, email: string): string | null {
  const now = Date.now()
  pruneResetAttempts(now)

  const lastEmailAttempt = emailResetAttempts.get(email)
  if (lastEmailAttempt && now - lastEmailAttempt < emailResetCooldownMs) {
    return "Please wait a few minutes before requesting another reset email."
  }

  const ip = clientIp(req)
  const existing = ipResetAttempts.get(ip)
  if (existing && existing.resetAt > now && existing.count >= maxResetAttemptsPerIp) {
    return "Too many password reset attempts. Try again later."
  }

  if (!existing || existing.resetAt <= now) {
    ipResetAttempts.set(ip, { count: 1, resetAt: now + resetWindowMs })
  } else {
    existing.count += 1
    ipResetAttempts.set(ip, existing)
  }
  emailResetAttempts.set(email, now)
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

  if (blockedResetLocalParts.has(localPart)) return "Use a real email address"
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
    return new Response("ok", { headers: corsHeaders })
  }

  if (req.method !== "POST") {
    return new Response(JSON.stringify({ error: "Method not allowed" }), {
      status: 405,
      headers: { ...corsHeaders, "Content-Type": "application/json" },
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
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    const supabaseUrl = Deno.env.get("SUPABASE_URL")
    const anonKey = expectedAnon
    if (!supabaseUrl || !anonKey) {
      throw new Error("Missing Supabase credentials")
    }

    const body = await req.json().catch(() => ({})) as {
      email?: string
      redirect_to?: string
    }

    const email = body.email?.trim().toLowerCase() || ""
    const redirectTo = body.redirect_to?.trim() || "https://auth.arvio.tv/?mode=recovery"

    const emailError = validateEmail(email)
    if (emailError) {
      return new Response(JSON.stringify({ error: emailError }), {
        status: 400,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    const rateLimitError = enforceResetRateLimit(req, email)
    if (rateLimitError) {
      return new Response(JSON.stringify({ error: rateLimitError }), {
        status: 429,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    const resetResp = await fetch(`${supabaseUrl}/auth/v1/recover`, {
      method: "POST",
      headers: {
        apikey: anonKey,
        Authorization: `Bearer ${anonKey}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email,
        redirect_to: redirectTo,
      }),
    })

    if (!resetResp.ok) {
      const resetText = await resetResp.text()
      return new Response(JSON.stringify({ error: parseAuthError(resetText) || "Password reset failed" }), {
        status: resetResp.status,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    return new Response(JSON.stringify({ ok: true }), {
      headers: { ...corsHeaders, "Content-Type": "application/json" },
    })
  } catch (error) {
    return new Response(
      JSON.stringify({ error: error instanceof Error ? error.message : "Unexpected error" }),
      { status: 500, headers: { ...corsHeaders, "Content-Type": "application/json" } },
    )
  }
})
