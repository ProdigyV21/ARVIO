import { serve } from "https://deno.land/std@0.168.0/http/server.ts"

const corsHeaders = {
  "Access-Control-Allow-Origin": "*",
  "Access-Control-Allow-Headers": "authorization, apikey, x-client-info, content-type",
}

function baseUrlJoin(base: string, path: string): string {
  const b = base.replace(/\/+$/, "")
  const p = path.replace(/^\/+/, "")
  return `${b}/${p}`
}

function randomCode(length: number): string {
  const alphabet = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789"
  const bytes = crypto.getRandomValues(new Uint8Array(length))
  return Array.from(bytes).map((b) => alphabet[b % alphabet.length]).join("")
}

serve(async (req) => {
  if (req.method === "OPTIONS") {
    return new Response("ok", { headers: corsHeaders })
  }

  try {
    const anonHeader = req.headers.get("apikey")
    const authHeader = req.headers.get("authorization")
    const expectedAnon = Deno.env.get("APP_ANON_KEY") ?? Deno.env.get("SUPABASE_ANON_KEY")

    const hasValidApiKey = !!anonHeader && !!expectedAnon && anonHeader === expectedAnon
    const hasValidBearer = !!authHeader && authHeader.startsWith("Bearer ") && !!expectedAnon && authHeader.replace("Bearer ", "") === expectedAnon

    if (!hasValidApiKey && !hasValidBearer) {
      return new Response(JSON.stringify({ error: "Unauthorized" }), {
        status: 401,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    const supabaseUrl = Deno.env.get("SUPABASE_URL")
    const serviceRole = Deno.env.get("SUPABASE_SERVICE_ROLE_KEY")
    if (!supabaseUrl || !serviceRole) {
      throw new Error("Missing Supabase service credentials")
    }

    const deviceCode = randomCode(32)
    const userCode = `${randomCode(4)}-${randomCode(4)}`

    const insertResponse = await fetch(`${supabaseUrl}/rest/v1/tv_device_auth_sessions`, {
      method: "POST",
      headers: {
        apikey: serviceRole,
        Authorization: `Bearer ${serviceRole}`,
        "Content-Type": "application/json",
        Prefer: "return=representation",
      },
      body: JSON.stringify({
        device_code: deviceCode,
        user_code: userCode,
      }),
    })

    if (!insertResponse.ok) {
      const errorText = await insertResponse.text()
      throw new Error(`Failed to create auth session: ${errorText}`)
    }

    // Where the TV should send the user to approve the sign-in.
    // For Framer, set TV_AUTH_VERIFY_BASE_URL to "https://arvio.tv/pair" (no trailing slash).
    const verifyBase = Deno.env.get("TV_AUTH_VERIFY_BASE_URL") ??
      Deno.env.get("SITE_URL") ??
      ""

    const verificationUrl = verifyBase
      ? `${verifyBase.replace(/\/+$/, "")}?code=${encodeURIComponent(userCode)}`
      : baseUrlJoin(
        supabaseUrl,
        `storage/v1/object/public/public-pages/tv-pair/index.html?code=${encodeURIComponent(userCode)}`,
      )

    return new Response(
      JSON.stringify({
        device_code: deviceCode,
        user_code: userCode,
        verification_url: verificationUrl,
        expires_in: 600,
        interval: 3,
      }),
      { headers: { ...corsHeaders, "Content-Type": "application/json" } },
    )
  } catch (error) {
    return new Response(
      JSON.stringify({ error: error instanceof Error ? error.message : "Unexpected error" }),
      { status: 500, headers: { ...corsHeaders, "Content-Type": "application/json" } },
    )
  }
})
