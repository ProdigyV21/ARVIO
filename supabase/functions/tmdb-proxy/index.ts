// TMDB API Proxy - Keeps API key secure on server
// Deploy with: npx supabase functions deploy tmdb-proxy
// Set secret: npx supabase secrets set TMDB_API_KEY=your_key

import { serve } from "https://deno.land/std@0.168.0/http/server.ts"

const TMDB_BASE_URL = "https://api.themoviedb.org/3"

const corsHeaders = {
  'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Headers': 'authorization, x-client-info, apikey, content-type',
}

serve(async (req) => {
  // Handle CORS preflight
  if (req.method === 'OPTIONS') {
    return new Response('ok', { headers: corsHeaders })
  }

  try {
    const TMDB_API_KEY = Deno.env.get('TMDB_API_KEY')
    if (!TMDB_API_KEY) {
      throw new Error('TMDB_API_KEY not configured')
    }

    // Get the path from the request URL
    const url = new URL(req.url)
    const path = url.searchParams.get('path')

    if (!path) {
      throw new Error('Missing path parameter')
    }

    // Build TMDB URL with all query parameters
    const tmdbUrl = new URL(`${TMDB_BASE_URL}${path}`)
    tmdbUrl.searchParams.set('api_key', TMDB_API_KEY)

    // Forward all other query parameters except 'path'
    url.searchParams.forEach((value, key) => {
      if (key !== 'path') {
        tmdbUrl.searchParams.set(key, value)
      }
    })

    // Make request to TMDB
    const response = await fetch(tmdbUrl.toString(), {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    })

    const data = await response.json()

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
