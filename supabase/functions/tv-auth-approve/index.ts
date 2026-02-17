import { serve } from "https://deno.land/std@0.168.0/http/server.ts"

const corsHeaders = {
  "Access-Control-Allow-Origin": "*",
  "Access-Control-Allow-Headers": "authorization, apikey, x-client-info, content-type",
  "Access-Control-Allow-Methods": "POST, OPTIONS",
}

type ApproveBody = {
  code?: string
  refresh_token?: string
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
    const authHeader = req.headers.get("authorization") ?? ""
    const expectedAnon = Deno.env.get("APP_ANON_KEY") ?? Deno.env.get("SUPABASE_ANON_KEY")

    const hasValidApiKey = !!anonHeader && !!expectedAnon && anonHeader === expectedAnon
    if (!hasValidApiKey) {
      return new Response(JSON.stringify({ error: "Unauthorized" }), {
        status: 401,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    if (!authHeader.startsWith("Bearer ")) {
      return new Response(JSON.stringify({ error: "Missing user access token" }), {
        status: 401,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    const accessToken = authHeader.replace("Bearer ", "").trim()
    if (!accessToken) {
      return new Response(JSON.stringify({ error: "Missing user access token" }), {
        status: 401,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    const supabaseUrl = Deno.env.get("SUPABASE_URL")
    const serviceRole = Deno.env.get("SUPABASE_SERVICE_ROLE_KEY")
    const anonKey = expectedAnon
    if (!supabaseUrl || !serviceRole || !anonKey) {
      throw new Error("Missing Supabase credentials")
    }

    const body = (await req.json().catch(() => ({}))) as ApproveBody
    const code = body.code?.trim() || ""
    const refreshToken = body.refresh_token?.trim() || ""

    if (!code || !refreshToken) {
      return new Response(JSON.stringify({ error: "Missing required fields" }), {
        status: 400,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    // Validate caller access token and get the associated user.
    const userResp = await fetch(`${supabaseUrl}/auth/v1/user`, {
      headers: {
        apikey: anonKey,
        Authorization: `Bearer ${accessToken}`,
      },
    })

    if (!userResp.ok) {
      return new Response(JSON.stringify({ error: "Invalid session" }), {
        status: 401,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    const user = (await userResp.json()) as { id?: string; email?: string }
    const userId = user.id?.trim() || ""
    if (!userId) {
      return new Response(JSON.stringify({ error: "Invalid session" }), {
        status: 401,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    // Validate code is pending + not expired.
    const sessionQuery = await fetch(
      `${supabaseUrl}/rest/v1/tv_device_auth_sessions?select=id,status,expires_at,user_code&user_code=eq.${encodeURIComponent(code)}&limit=1`,
      {
        headers: {
          apikey: serviceRole,
          Authorization: `Bearer ${serviceRole}`,
        },
      },
    )

    if (!sessionQuery.ok) {
      const txt = await sessionQuery.text()
      throw new Error(`Unable to validate code (${txt})`)
    }

    const rows = (await sessionQuery.json()) as Array<{ expires_at: string; status: string }>
    const row = rows[0]
    if (!row) {
      return new Response(JSON.stringify({ error: "Invalid or expired code" }), {
        status: 400,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    const isExpired = Date.now() > Date.parse(row.expires_at)
    if (isExpired || row.status !== "pending") {
      return new Response(JSON.stringify({ error: "Code has expired" }), {
        status: 400,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    const updateResp = await fetch(
      `${supabaseUrl}/rest/v1/tv_device_auth_sessions?user_code=eq.${encodeURIComponent(code)}`,
      {
        method: "PATCH",
        headers: {
          apikey: serviceRole,
          Authorization: `Bearer ${serviceRole}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          status: "approved",
          approved_at: new Date().toISOString(),
          user_id: userId,
          user_email: user.email ?? null,
          access_token: accessToken,
          refresh_token: refreshToken,
        }),
      },
    )

    if (!updateResp.ok) {
      const txt = await updateResp.text()
      throw new Error(`Failed to link device (${txt})`)
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

