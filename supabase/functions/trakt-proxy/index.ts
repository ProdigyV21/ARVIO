// Trakt API Proxy - Secured with rate limiting and path allowlist
// Version: 2.1
// Deploy with: npx supabase functions deploy trakt-proxy
// Set secrets:
//   npx supabase secrets set TRAKT_CLIENT_ID=your_id
//   npx supabase secrets set TRAKT_CLIENT_SECRET=your_secret
//   npx supabase secrets set APP_ANON_KEY=your_anon_key

import { serve } from "https://deno.land/std@0.168.0/http/server.ts"

const TRAKT_BASE_URL = "https://api.trakt.tv"

// Rate limiting: 100 requests per minute per IP
const RATE_LIMIT = 100
const RATE_WINDOW_MS = 60 * 1000
const rateLimitMap = new Map<string, { count: number; resetTime: number }>()

// Allowed Trakt paths (prefix matching)
const ALLOWED_PATHS = [
  '/oauth/device/code',
  '/oauth/device/token',
  '/oauth/token',
  '/users/me',
  '/users/',
  '/sync/last_activities',
  '/sync/history',
  '/sync/watchlist',
  '/sync/watched',
  '/sync/playback',
  '/scrobble/',
  '/movies/',
  '/shows/',
  '/lists/',
  '/search/',
  '/calendars/',
]

function isPathAllowed(path: string): boolean {
  return ALLOWED_PATHS.some(allowed => path.startsWith(allowed))
}

function getClientIP(req: Request): string {
  return req.headers.get('x-forwarded-for')?.split(',')[0]?.trim() ||
         req.headers.get('x-real-ip') ||
         req.headers.get('cf-connecting-ip') ||
         'unknown'
}

function checkRateLimit(ip: string): { allowed: boolean; remaining: number; resetIn: number } {
  const now = Date.now()
  const record = rateLimitMap.get(ip)

  if (!record || now > record.resetTime) {
    rateLimitMap.set(ip, { count: 1, resetTime: now + RATE_WINDOW_MS })
    return { allowed: true, remaining: RATE_LIMIT - 1, resetIn: RATE_WINDOW_MS }
  }

  if (record.count >= RATE_LIMIT) {
    return { allowed: false, remaining: 0, resetIn: record.resetTime - now }
  }

  record.count++
  return { allowed: true, remaining: RATE_LIMIT - record.count, resetIn: record.resetTime - now }
}

// Clean up old rate limit entries periodically
setInterval(() => {
  const now = Date.now()
  for (const [ip, record] of rateLimitMap.entries()) {
    if (now > record.resetTime) {
      rateLimitMap.delete(ip)
    }
  }
}, 60000)

// CORS: restrict origins using env `CORS_ALLOWED_ORIGINS` (comma-separated).
const DEFAULT_ALLOWED_ORIGINS = (Deno.env.get('CORS_ALLOWED_ORIGINS') || 'https://auth.arvio.tv,https://arvio.tv').split(',').map(s => s.trim()).filter(Boolean)

function corsHeaders(req: Request) {
  const origin = req.headers.get('origin') || ''
  const allowed = DEFAULT_ALLOWED_ORIGINS
  const allowOrigin = allowed.includes(origin) ? origin : 'null'
  return {
    'Access-Control-Allow-Origin': allowOrigin,
    'Access-Control-Allow-Headers': 'authorization, x-client-info, apikey, content-type, x-user-token',
  }
}

serve(async (req) => {
  // Handle CORS preflight
  if (req.method === 'OPTIONS') {
    return new Response('ok', { headers: corsHeaders(req) })
  }

  try {
    const clientIP = getClientIP(req)

    // Check rate limit
    const rateCheck = checkRateLimit(clientIP)
    if (!rateCheck.allowed) {
      return new Response(JSON.stringify({
        error: 'Rate limit exceeded',
        retryAfter: Math.ceil(rateCheck.resetIn / 1000)
      }), {
        headers: {
          ...corsHeaders(req),
          'Content-Type': 'application/json',
          'Retry-After': String(Math.ceil(rateCheck.resetIn / 1000)),
          'X-RateLimit-Limit': String(RATE_LIMIT),
          'X-RateLimit-Remaining': '0',
          'X-RateLimit-Reset': String(Math.ceil(rateCheck.resetIn / 1000))
        },
        status: 429,
      })
    }

    // Verify authentication
    const apiKey = req.headers.get('apikey')
    const authHeader = req.headers.get('authorization')
    const EXPECTED_ANON_KEY = Deno.env.get('APP_ANON_KEY')

    // Accept either: valid apikey OR valid Authorization Bearer token
    const hasValidApiKey = apiKey && EXPECTED_ANON_KEY && apiKey === EXPECTED_ANON_KEY
    const hasValidAuth = authHeader?.startsWith('Bearer ') &&
                         EXPECTED_ANON_KEY &&
                         authHeader.replace('Bearer ', '') === EXPECTED_ANON_KEY

    if (!hasValidApiKey && !hasValidAuth) {
      return new Response(JSON.stringify({ error: 'Unauthorized' }), {
        headers: { ...corsHeaders(req), 'Content-Type': 'application/json' },
        status: 401,
      })
    }

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

    // Validate path against allowlist
    if (!isPathAllowed(path)) {
      return new Response(JSON.stringify({ error: 'Path not allowed' }), {
        headers: { ...corsHeaders(req), 'Content-Type': 'application/json' },
        status: 403,
      })
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
      let reqBody: Record<string, unknown> = {}
      try {
        reqBody = await req.json()
      } catch {
        // No body or invalid JSON - start with empty object
      }

      // Inject client credentials for auth endpoints
      if (path.includes('/oauth/device/code')) {
        reqBody.client_id = TRAKT_CLIENT_ID
        body = JSON.stringify(reqBody)
      } else if (path.includes('/oauth/device/token')) {
        reqBody.client_id = TRAKT_CLIENT_ID
        reqBody.client_secret = TRAKT_CLIENT_SECRET
        body = JSON.stringify(reqBody)
      } else if (path.includes('/oauth/token')) {
        reqBody.client_id = TRAKT_CLIENT_ID
        reqBody.client_secret = TRAKT_CLIENT_SECRET
        body = JSON.stringify(reqBody)
      } else if (Object.keys(reqBody).length > 0) {
        body = JSON.stringify(reqBody)
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

    // Try to parse response body, handling empty or invalid JSON
    const responseText = await response.text()
    if (!responseText && path.includes('/oauth/device/token') && response.status === 400) {
      data = { error: 'authorization_pending', status: response.status }
    } else if (responseText && contentType?.includes('application/json')) {
      try {
        data = JSON.parse(responseText)
      } catch {
        data = { raw: responseText }
      }
    } else {
      data = responseText ? { raw: responseText } : { status: response.status }
    }

    return new Response(JSON.stringify(data), {
      headers: {
        ...corsHeaders(req),
        'Content-Type': 'application/json',
        'Cache-Control': 'no-store',
        'X-RateLimit-Limit': String(RATE_LIMIT),
        'X-RateLimit-Remaining': String(rateCheck.remaining),
        'X-Pagination-Page': response.headers.get('X-Pagination-Page') ?? '',
        'X-Pagination-Limit': response.headers.get('X-Pagination-Limit') ?? '',
        'X-Pagination-Page-Count': response.headers.get('X-Pagination-Page-Count') ?? '',
        'X-Pagination-Item-Count': response.headers.get('X-Pagination-Item-Count') ?? '',
      },
      status: response.status,
    })
  } catch (error) {
    return new Response(JSON.stringify({ error: error.message }), {
      headers: { ...corsHeaders(req), 'Content-Type': 'application/json' },
      status: 500,
    })
  }
})
