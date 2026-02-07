// Trakt API Proxy - Keeps client_id and client_secret secure on server
// Deploy with: npx supabase functions deploy trakt-proxy
// Set secrets:
//   npx supabase secrets set TRAKT_CLIENT_ID=your_id
//   npx supabase secrets set TRAKT_CLIENT_SECRET=your_secret

import { serve } from "https://deno.land/std@0.168.0/http/server.ts"

const TRAKT_BASE_URL = "https://api.trakt.tv"

const corsHeaders = {
  'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Headers': 'authorization, x-client-info, apikey, content-type, x-user-token',
}

serve(async (req) => {
  // Handle CORS preflight
  if (req.method === 'OPTIONS') {
    return new Response('ok', { headers: corsHeaders })
  }

  try {
    const TRAKT_CLIENT_ID = Deno.env.get('TRAKT_CLIENT_ID')
    const TRAKT_CLIENT_SECRET = Deno.env.get('TRAKT_CLIENT_SECRET')

    if (!TRAKT_CLIENT_ID || !TRAKT_CLIENT_SECRET) {
      throw new Error('Trakt credentials not configured')
    }

    const url = new URL(req.url)
    const path = url.searchParams.get('path')
    const method = url.searchParams.get('method') || 'GET'

    if (!path) {
      throw new Error('Missing path parameter')
    }

    // Build Trakt URL
    const traktUrl = new URL(`${TRAKT_BASE_URL}${path}`)

    // Forward query parameters except control params
    url.searchParams.forEach((value, key) => {
      if (key !== 'path' && key !== 'method') {
        traktUrl.searchParams.set(key, value)
      }
    })

    // Build headers
    const headers: Record<string, string> = {
      'Content-Type': 'application/json',
      'trakt-api-key': TRAKT_CLIENT_ID,
      'trakt-api-version': '2',
    }

    // Forward user's auth token if provided
    const userToken = req.headers.get('x-user-token')
    if (userToken) {
      headers['Authorization'] = `Bearer ${userToken}`
    }

    // Get request body for POST requests
    let body: string | undefined
    if (method === 'POST' || method === 'DELETE') {
      try {
        const reqBody = await req.json()

        // Inject client credentials for auth endpoints
        if (path.includes('/oauth/device/code')) {
          reqBody.client_id = TRAKT_CLIENT_ID
        } else if (path.includes('/oauth/device/token')) {
          reqBody.client_id = TRAKT_CLIENT_ID
          reqBody.client_secret = TRAKT_CLIENT_SECRET
        } else if (path.includes('/oauth/token')) {
          reqBody.client_id = TRAKT_CLIENT_ID
          reqBody.client_secret = TRAKT_CLIENT_SECRET
        }

        body = JSON.stringify(reqBody)
      } catch {
        // No body or invalid JSON
      }
    }

    // Make request to Trakt
    const response = await fetch(traktUrl.toString(), {
      method: method,
      headers: headers,
      body: body,
    })

    // Handle different response types
    const contentType = response.headers.get('content-type')
    let data

    if (contentType?.includes('application/json')) {
      data = await response.json()
    } else {
      data = await response.text()
    }

    return new Response(JSON.stringify(data), {
      headers: { ...corsHeaders, 'Content-Type': 'application/json' },
      status: response.status,
    })
  } catch (error) {
    return new Response(JSON.stringify({ error: error.message }), {
      headers: { ...corsHeaders, 'Content-Type': 'application/json' },
      status: 500,
    })
  }
})
