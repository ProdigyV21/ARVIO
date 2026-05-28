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
  }
}

serve(async (req) => {
  if (req.method === "OPTIONS") {
    return new Response("ok", { headers: corsHeaders(req) })
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
        headers: { ...corsHeaders(req), "Content-Type": "application/json" },
      })
    }

    const supabaseUrl = Deno.env.get("SUPABASE_URL")
    const serviceRole = Deno.env.get("SUPABASE_SERVICE_ROLE_KEY")
    if (!supabaseUrl || !serviceRole) {
      throw new Error("Missing Supabase service credentials")
    }

    const body = await req.json().catch(() => ({})) as { device_code?: string }
    const deviceCode = body.device_code?.trim()
    if (!deviceCode) {
      return new Response(JSON.stringify({ error: "device_code is required" }), {
        status: 400,
        headers: { ...corsHeaders(req), "Content-Type": "application/json" },
      })
    }

    const queryResponse = await fetch(
      `${supabaseUrl}/rest/v1/tv_device_auth_sessions?select=id,device_code,status,access_token,refresh_token,user_email,expires_at&device_code=eq.${encodeURIComponent(deviceCode)}&limit=1`,
      {
        method: "GET",
        headers: {
          apikey: serviceRole,
          Authorization: `Bearer ${serviceRole}`,
        },
      },
    )

    if (!queryResponse.ok) {
      const errorText = await queryResponse.text()
      throw new Error(`Failed to query auth session: ${errorText}`)
    }

    const rows = await queryResponse.json() as Array<Record<string, string | null>>
    const row = rows[0]
    if (!row) {
      return new Response(JSON.stringify({ status: "expired", message: "Session not found" }), {
        headers: { ...corsHeaders(req), "Content-Type": "application/json" },
      })
    }

    const expiresAt = row.expires_at ? Date.parse(row.expires_at) : 0
    const isExpired = !Number.isNaN(expiresAt) && Date.now() > expiresAt
    if (isExpired && row.status === "pending") {
      await fetch(`${supabaseUrl}/rest/v1/tv_device_auth_sessions?device_code=eq.${encodeURIComponent(deviceCode)}`, {
        method: "PATCH",
        headers: {
          apikey: serviceRole,
          Authorization: `Bearer ${serviceRole}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ status: "expired" }),
      })
      return new Response(JSON.stringify({ status: "expired", message: "Code expired" }), {
        headers: { ...corsHeaders(req), "Content-Type": "application/json" },
      })
    }

    if (row.status === "approved" && row.access_token && row.refresh_token) {
      await fetch(`${supabaseUrl}/rest/v1/tv_device_auth_sessions?device_code=eq.${encodeURIComponent(deviceCode)}`, {
        method: "PATCH",
        headers: {
          apikey: serviceRole,
          Authorization: `Bearer ${serviceRole}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          status: "consumed",
          consumed_at: new Date().toISOString(),
          access_token: null,
          refresh_token: null,
        }),
      })

      return new Response(
        JSON.stringify({
          status: "approved",
          access_token: row.access_token,
          refresh_token: row.refresh_token,
          email: row.user_email,
        }),
        { headers: { ...corsHeaders(req), "Content-Type": "application/json" } },
      )
    }

    if (row.status === "expired" || row.status === "consumed") {
      return new Response(JSON.stringify({ status: "expired", message: "Code expired" }), {
        headers: { ...corsHeaders(req), "Content-Type": "application/json" },
      })
    }

    return new Response(JSON.stringify({ status: "pending" }), {
      headers: { ...corsHeaders(req), "Content-Type": "application/json" },
    })
  } catch (error) {
    return new Response(
      JSON.stringify({ error: error instanceof Error ? error.message : "Unexpected error" }),
      { status: 500, headers: { ...corsHeaders(req), "Content-Type": "application/json" } },
    )
  }
})
