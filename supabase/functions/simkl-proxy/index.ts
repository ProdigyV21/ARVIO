// Simkl API Proxy - Secured with rate limiting and path allowlist
// Deploy with: npx supabase functions deploy simkl-proxy
// Set secrets:
//   npx supabase secrets set SIMKL_CLIENT_ID=your_id
//   npx supabase secrets set SIMKL_CLIENT_SECRET=your_secret
//   npx supabase secrets set APP_ANON_KEY=your_anon_key

import { serve } from "https://deno.land/std@0.168.0/http/server.ts"

const SIMKL_BASE_URL = "https://api.simkl.com"

const RATE_LIMIT = 100
const RATE_WINDOW_MS = 60 * 1000
const rateLimitMap = new Map<string, { count: number; resetTime: number }>()

const REQUEST_RULES = [
  { path: /^\/oauth\/pin(?:\/[A-Za-z0-9-]+)?$/, methods: ['GET'] },
  { path: /^\/oauth\/token$/, methods: ['POST'] },
  { path: /^\/users\/settings$/, methods: ['POST'] },
  { path: /^\/scrobble\/(?:start|pause|stop)$/, methods: ['POST'] },
  { path: /^\/sync\/activities$/, methods: ['GET'] },
  { path: /^\/sync\/all-items(?:\/(?:movies|shows|anime|all)(?:\/(?:watching|plantowatch|hold|completed|dropped|all))?)?$/, methods: ['GET'] },
  { path: /^\/sync\/playback(?:\/(?:movies|episodes|shows|anime|all))?$/, methods: ['GET'] },
  { path: /^\/sync\/playback\/\d+$/, methods: ['DELETE'] },
  { path: /^\/sync\/(?:history|history\/remove|add-to-list)$/, methods: ['POST'] },
]

function isRequestAllowed(path: string, method: string): boolean {
  return REQUEST_RULES.some(rule => rule.path.test(path) && rule.methods.includes(method))
}

function isAppAuthorized(req: Request): boolean {
  const expected = Deno.env.get('APP_ANON_KEY')?.trim() || ''
  if (!expected) return false
  const apiKey = req.headers.get('apikey')?.trim() || ''
  const bearer = req.headers.get('authorization')?.match(/^Bearer\s+(.+)$/i)?.[1]?.trim() || ''
  return apiKey === expected || bearer === expected
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
  if (req.method === 'OPTIONS') {
    return new Response('ok', { headers: corsHeaders(req) })
  }

  try {
    if (!isAppAuthorized(req)) {
      return new Response(JSON.stringify({ error: 'Unauthorized' }), {
        headers: { ...corsHeaders(req), 'Content-Type': 'application/json' },
        status: 401,
      })
    }

    const clientIP = getClientIP(req)
    const rateCheck = checkRateLimit(clientIP)
    if (!rateCheck.allowed) {
      return new Response(JSON.stringify({ error: 'Rate limit exceeded' }), {
        headers: { ...corsHeaders(req), 'Content-Type': 'application/json' },
        status: 429,
      })
    }

    const SIMKL_CLIENT_ID = Deno.env.get('SIMKL_CLIENT_ID')
    if (!SIMKL_CLIENT_ID) {
      throw new Error('Simkl credentials not configured')
    }

    const url = new URL(req.url)
    const path = url.searchParams.get('path')
    const method = (url.searchParams.get('method') || 'GET').toUpperCase()

    if (req.method.toUpperCase() !== method) {
      return new Response(JSON.stringify({ error: 'HTTP method mismatch' }), {
        headers: { ...corsHeaders(req), 'Content-Type': 'application/json' },
        status: 400,
      })
    }

    if (!path || !isRequestAllowed(path, method)) {
      return new Response(JSON.stringify({ error: 'Path or method not allowed' }), {
        headers: { ...corsHeaders(req), 'Content-Type': 'application/json' },
        status: 403,
      })
    }

    const simklUrl = new URL(`${SIMKL_BASE_URL}${path}`)
    url.searchParams.forEach((value, key) => {
      if (!['path', 'method', 'client_id', 'client_secret'].includes(key)) {
        simklUrl.searchParams.set(key, value)
      }
    })
    simklUrl.searchParams.set('client_id', SIMKL_CLIENT_ID)
    if (!simklUrl.searchParams.has('app-name') || simklUrl.searchParams.get('app-name') === 'ARVIO') {
      simklUrl.searchParams.set('app-name', 'arvio')
    }
    if (!simklUrl.searchParams.has('app-version')) {
      const incomingVersion = req.headers.get('x-app-version') || '1.9.996'
      simklUrl.searchParams.set('app-version', incomingVersion)
    }

    const appVersion = simklUrl.searchParams.get('app-version') || '1.9.996'
    const incomingUa = req.headers.get('user-agent')?.trim()
    const userAgent = incomingUa || `ARVIO/${appVersion} (Supabase Proxy)`

    const headers: Record<string, string> = {
      'Content-Type': 'application/json',
      'simkl-api-key': SIMKL_CLIENT_ID,
      'User-Agent': userAgent,
    }

    const userToken = req.headers.get('x-user-token')
    if (userToken) {
      headers['Authorization'] = `Bearer ${userToken}`
    }

    let body: string | undefined
    if (method === 'POST' || method === 'DELETE') {
      let reqBody: Record<string, unknown> = {}
      try {
        reqBody = await req.json()
      } catch {
        // empty body
      }
      if (path.includes('/oauth/token')) {
        reqBody.client_id = SIMKL_CLIENT_ID
        const SIMKL_CLIENT_SECRET = Deno.env.get('SIMKL_CLIENT_SECRET')
        if (SIMKL_CLIENT_SECRET) reqBody.client_secret = SIMKL_CLIENT_SECRET
      }
      body = Object.keys(reqBody).length > 0 ? JSON.stringify(reqBody) : undefined
    }

    const response = await fetch(simklUrl.toString(), {
      method: method,
      headers: headers,
      body: body,
    })

    const responseText = await response.text()
    let data
    try {
      data = responseText ? JSON.parse(responseText) : { status: response.status }
    } catch {
      data = responseText ? { raw: responseText } : { status: response.status }
    }

    const returnHeaders: Record<string, string> = { ...corsHeaders(req), 'Content-Type': 'application/json' }
    const upstreamRetryAfter = response.headers.get('retry-after')
    if (upstreamRetryAfter) {
      returnHeaders['Retry-After'] = upstreamRetryAfter
    }

    return new Response(JSON.stringify(data), {
      headers: returnHeaders,
      status: response.status,
    })
  } catch (error) {
    return new Response(JSON.stringify({ error: (error as Error).message }), {
      headers: { ...corsHeaders(req), 'Content-Type': 'application/json' },
      status: 500,
    })
  }
})
