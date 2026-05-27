// TMDB API Proxy - Secured with rate limiting and path allowlist
// Deploy with: npx supabase functions deploy tmdb-proxy
// Set secrets:
//   npx supabase secrets set TMDB_API_KEY=your_key
//   npx supabase secrets set APP_ANON_KEY=your_anon_key

import { serve } from "https://deno.land/std@0.168.0/http/server.ts"

const TMDB_BASE_URL = "https://api.themoviedb.org/3"

// Rate limiting: 100 requests per minute per IP
const RATE_LIMIT = 100
const RATE_WINDOW_MS = 60 * 1000
const rateLimitMap = new Map<string, { count: number; resetTime: number }>()

// Allowed TMDB paths (prefix matching)
const ALLOWED_PATHS = [
  '/trending/',
  '/movie/',
  '/tv/',
  '/search/',
  '/discover/',
  '/find/',
  '/genre/',
  '/person/',
  '/collection/',
  '/watch/providers',
  '/configuration',
]

function isPathAllowed(path: string): boolean {
  return ALLOWED_PATHS.some(allowed => path.startsWith(allowed))
}

function getClientIP(req: Request): string {
  // Try various headers for real IP (behind proxies/CDN)
  return req.headers.get('x-forwarded-for')?.split(',')[0]?.trim() ||
         req.headers.get('x-real-ip') ||
         req.headers.get('cf-connecting-ip') ||
         'unknown'
}

function checkRateLimit(ip: string): { allowed: boolean; remaining: number; resetIn: number } {
  const now = Date.now()
  const record = rateLimitMap.get(ip)

  if (!record || now > record.resetTime) {
    // New window
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

const corsHeaders = {
  'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Headers': 'authorization, x-client-info, apikey, content-type',
}

function errorMessage(error: unknown): string {
  return error instanceof Error ? error.message : String(error)
}

type TmdbFetchResult = {
  data: unknown
  status: number
  attempts: number
}

function buildRetryUrl(tmdbUrl: URL, attempt: number): URL {
  if (attempt === 0) return new URL(tmdbUrl.toString())

  const retryUrl = new URL(tmdbUrl.toString())
  retryUrl.searchParams.set('_arvio_retry', `${Date.now()}_${attempt}`)
  return retryUrl
}

async function fetchTmdbJson(tmdbUrl: URL): Promise<TmdbFetchResult> {
  let lastError: unknown = null

  for (let attempt = 0; attempt < 3; attempt++) {
    const requestUrl = buildRetryUrl(tmdbUrl, attempt)

    try {
      const response = await fetch(requestUrl.toString(), {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          // TMDB had bad CloudFront gzip cache entries on 2026-05-20 that
          // broke strict clients with truncated gzip bodies. Prefer identity
          // here so the app receives stable JSON through this proxy.
          'Accept-Encoding': 'identity;q=1, *;q=0',
          'Cache-Control': attempt === 0 ? 'no-cache' : 'no-store',
          'Pragma': 'no-cache',
          'User-Agent': 'ARVIO-TMDB-Proxy/1.0',
        },
      })

      const body = await response.text()
      const data = body.length > 0 ? JSON.parse(body) : null
      return { data, status: response.status, attempts: attempt + 1 }
    } catch (error) {
      lastError = error
    }
  }

  throw new Error(`TMDB response could not be decoded after retries: ${errorMessage(lastError)}`)
}

serve(async (req) => {
  // Handle CORS preflight
  if (req.method === 'OPTIONS') {
    return new Response('ok', { headers: corsHeaders })
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
          ...corsHeaders,
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
        headers: { ...corsHeaders, 'Content-Type': 'application/json' },
        status: 401,
      })
    }

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

    // Validate path against allowlist
    if (!isPathAllowed(path)) {
      return new Response(JSON.stringify({ error: 'Path not allowed' }), {
        headers: { ...corsHeaders, 'Content-Type': 'application/json' },
        status: 403,
      })
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

    const result = await fetchTmdbJson(tmdbUrl)

    return new Response(JSON.stringify(result.data), {
      headers: {
        ...corsHeaders,
        'Content-Type': 'application/json',
        'Cache-Control': 'no-store',
        'X-RateLimit-Limit': String(RATE_LIMIT),
        'X-RateLimit-Remaining': String(rateCheck.remaining),
        'X-TMDB-Proxy-Attempts': String(result.attempts),
      },
      status: result.status,
    })
  } catch (error) {
    return new Response(JSON.stringify({ error: errorMessage(error) }), {
      headers: { ...corsHeaders, 'Content-Type': 'application/json', 'Cache-Control': 'no-store' },
      status: 502,
    })
  }
})
