import http from 'node:http';
import https from 'node:https';
import dns from 'node:dns/promises';
import { createHash } from 'node:crypto';
import { pathToFileURL } from 'node:url';

export const pinnedPlugin = {
  internalName: 'StreamPlay', name: 'StreamPlay', version: 694, status: 1, language: 'en', apiVersion: 1,
  url: 'https://raw.githubusercontent.com/phisher98/cloudstream-extensions-phisher/6f9edcb8164b4a155e2a255b4d4aa9b67126c7ae/StreamPlay.cs3',
  fileHash: 'sha256-a63bd63f0203db0c62ed0e85a1d3f039a9e98d7cdc9fa79f233af6cce825f805', fileSize: 2106820
};

export const manifest = {
  id: 'arvio.local.cloudstream.streamplay.poc', version: '0.0.1',
  name: 'Phisher · StreamPlay (local POC)',
  description: 'Experimental local Cloudstream bridge. Direct browser sources only; no media relay.',
  types: ['movie', 'series'], idPrefixes: ['cs-poc:', 'tmdb:'],
  resources: ['catalog', { name: 'meta', types: ['movie', 'series'], idPrefixes: ['cs-poc:'] },
    { name: 'stream', types: ['movie', 'series'], idPrefixes: ['cs-poc:', 'tmdb:'] }],
  catalogs: ['movie', 'series'].map(type => ({ type, id: 'streamplay', name: 'StreamPlay', extra: [{ name: 'search' }] })),
  behaviorHints: { configurable: false }
};

export function publicAddress(address) {
  if (address.includes(':')) return /^[23][0-9a-f]{3}:/i.test(address);
  const p = address.split('.').map(Number);
  if (p.length !== 4 || p.some(n => !Number.isInteger(n) || n < 0 || n > 255)) return false;
  const [a, b, c] = p;
  return !(a === 0 || a === 10 || a === 127 || a >= 224 || a === 169 && b === 254 ||
    a === 172 && b >= 16 && b <= 31 || a === 192 && b === 168 ||
    a === 100 && b >= 64 && b <= 127 || a === 192 && b === 0 ||
    a === 198 && [18, 19, 51].includes(b) || a === 203 && b === 0 && c === 113);
}

export function candidateRejection(link) {
  if (link.drm) return 'drm';
  if (link.interceptor) return 'requires-runtime-interceptor';
  if (!['VIDEO', 'M3U8', 'DASH'].includes(link.type)) return 'unsupported-type';
  try {
    const url = new URL(link.url);
    if (url.protocol !== 'https:' || url.username || url.password || url.port && url.port !== '443') return 'unsafe-url';
  } catch { return 'invalid-url'; }
  return null;
}

/** At most 32 KiB per candidate. This is an eligibility check, not proof of browser playback. */
export async function probeDirect(raw, origin, metrics, redirect = 0, deadline = Date.now() + 4500) {
  const url = new URL(raw);
  if (candidateRejection({ url: raw, type: 'VIDEO' })) return { ok: false, reason: 'unsafe-url' };
  if (Date.now() >= deadline) return { ok: false, reason: 'timeout' };
  let dnsTimer;
  const addresses = await Promise.race([dns.lookup(url.hostname, { all: true }), new Promise((_, reject) => {
    dnsTimer = setTimeout(() => reject(new Error('DNS timeout')), Math.max(1, deadline - Date.now()));
  })]).finally(() => clearTimeout(dnsTimer));
  if (!addresses.length || addresses.some(a => !publicAddress(a.address))) return { ok: false, reason: 'private-address' };
  const address = addresses.find(a => a.family === 4) ?? addresses[0];
  return new Promise(resolve => {
    let settled = false;
    let timer;
    const done = value => { if (!settled) { settled = true; clearTimeout(timer); resolve(value); } };
    // Pin the connection to the validated address. No cookies/provider headers are forwarded.
    const req = https.get(url, {
      agent: false, lookup: (_host, opts, cb) => opts?.all ? cb(null, [address]) : cb(null, address.address, address.family),
      headers: { Origin: origin, Range: 'bytes=0-32767', Accept: '*/*' },
      timeout: 4500
    }, res => {
      if ([301, 302, 303, 307, 308].includes(res.statusCode) && res.headers.location) {
        res.destroy();
        if (redirect >= 3) return done({ ok: false, reason: 'redirect-limit' });
        probeDirect(new URL(res.headers.location, url).href, origin, metrics, redirect + 1, deadline).then(done, () => done({ ok: false, reason: 'redirect-failed' }));
        return;
      }
      const chunks = [];
      let bytes = 0;
      const finish = () => {
        const body = Buffer.concat(chunks).subarray(0, 32768);
        const cors = res.headers['access-control-allow-origin'];
        const contentType = String(res.headers['content-type'] ?? '').toLowerCase();
        const signature = body.subarray(0, 4096).toString('utf8').trimStart();
        const media = contentType.startsWith('video/') || contentType.startsWith('audio/') ||
          signature.startsWith('#EXTM3U') || /<MPD[\s>]/.test(signature) || body.subarray(4, 8).toString() === 'ftyp' ||
          body.subarray(0, 4).equals(Buffer.from([0x1a, 0x45, 0xdf, 0xa3]));
        // Native <video src> can play an MP4 without CORS. HLS/DASH and remux need readable responses.
        const nativeMp4 = body.subarray(4, 8).toString() === 'ftyp' && contentType.startsWith('video/mp4');
        // Conservative browser-only contract: require readable responses even for MP4.
        // Some hosts allow server range probes but challenge actual browser video requests.
        const ok = [200, 206].includes(res.statusCode) && media && [origin, '*'].includes(cors);
        done({ ok, reason: ok ? null : ![200, 206].includes(res.statusCode) ? `http-${res.statusCode}` : !media ? 'not-media' : 'cors', contentType, nativeWithoutCors: nativeMp4 && ![origin, '*'].includes(cors) });
        res.destroy();
      };
      res.on('data', chunk => { metrics.probeBytes += chunk.length; bytes += chunk.length; chunks.push(chunk); if (bytes >= 32768) finish(); });
      res.on('end', finish);
      res.on('error', () => done({ ok: false, reason: 'response-error' }));
    });
    req.on('timeout', () => { req.destroy(); done({ ok: false, reason: 'timeout' }); });
    req.on('error', () => done({ ok: false, reason: 'network' }));
    timer = setTimeout(() => { req.destroy(); done({ ok: false, reason: 'timeout' }); }, Math.max(1, deadline - Date.now()));
  });
}

export function createBridge({ workerUrl = 'http://127.0.0.1:8951', token, origins = ['http://127.0.0.1:8876', 'http://localhost:8876'], workerFetch = fetch, probe = probeDirect } = {}) {
  if (!token || token.length < 32) throw new Error('A private worker token is required');
  const entries = new Map(), cache = new Map(), inflight = new Map();
  const metrics = { resolveRequests: 0, workerResolutions: 0, probeBytes: 0, mediaRelayBytes: 0, directCandidates: 0, rejected: {} };
  let active = 0;
  const reject = reason => { metrics.rejected[reason] = (metrics.rejected[reason] ?? 0) + 1; };
  async function worker(path, params = {}) {
    const url = new URL(path, workerUrl);
    for (const [k, v] of Object.entries(params)) if (v != null) url.searchParams.set(k, v);
    const response = await workerFetch(url, { headers: { Authorization: `Bearer ${token}` }, signal: AbortSignal.timeout(27000) });
    if (!response.ok) throw new Error(`Runtime returned ${response.status}`);
    return response.json();
  }
  function register(item) {
    let tmdbId;
    try { const data = JSON.parse(item.url); if (Number.isSafeInteger(data.id) && data.id > 0 && data.id < 1e10) tmdbId = data.id; } catch {}
    // Self-describing IDs survive worker/bridge restarts and cached home rows.
    const id = `cs-poc:${tmdbId ?? createHash('sha256').update(item.url).digest('hex').slice(0, 24)}`;
    const type = /series|anime|drama/i.test(item.type ?? '') && !/movie/i.test(item.type ?? '') ? 'series' : 'movie';
    entries.set(id, { ...item, id, type });
    while (entries.size > 500) entries.delete(entries.keys().next().value);
    return { id, type, name: item.name, poster: item.poster, releaseInfo: item.year ? String(item.year) : undefined };
  }
  async function streams(entry, season, episode, origin) {
    metrics.resolveRequests++;
    const key = `${entry.id}:${season ?? ''}:${episode ?? ''}:${origin}`;
    if (cache.get(key)?.expires > Date.now()) return cache.get(key).value;
    if (inflight.has(key)) return inflight.get(key);
    if (active >= 2) throw new Error('Two resolutions are already running; retry shortly');
    active++;
    const job = (async () => {
      metrics.workerResolutions++;
      const result = [];
      const seen = new Set();
      const deadline = Date.now() + 25000;
      do {
      const raw = await worker('/resolve', { url: entry.url, season, episode });
      const eligible = new Map();
      for (const link of (raw.links ?? [])) {
        const reason = candidateRejection(link);
        if (reason) { if (!seen.has(link.url)) reject(reason); seen.add(link.url); continue; }
        if (!seen.has(link.url)) eligible.set(link.url, link);
      }
      // Probe different hosts before spending the budget on quality variants of one host.
      const ordered = [...eligible.values()].sort((a, b) => b.quality - a.quality);
      const hosts = new Set();
      const diverse = ordered.filter(link => {
        const host = new URL(link.url).hostname;
        if (hosts.has(host)) return false;
        hosts.add(host); return true;
      });
      const candidates = diverse.slice(0, 4);
      candidates.forEach(link => seen.add(link.url));
      // Bounded metadata/media-header probes, four at a time, no streaming proxy.
      for (let i = 0; i < candidates.length; i += 4) {
        await Promise.all(candidates.slice(i, i + 4).map(async link => {
          const check = await probe(link.url, origin, metrics).catch(() => ({ ok: false, reason: 'probe-error' }));
          if (!check.ok) { reject(check.reason); return; }
          metrics.directCandidates++;
          result.push({ name: `StreamPlay · ${link.source || link.name || 'Direct'}`,
            title: `${link.quality > 0 ? link.quality + 'p · ' : ''}${link.type === 'M3U8' ? 'HLS · ' : link.type === 'DASH' ? 'DASH · ' : ''}Direct`, url: link.url,
            transport: link.type === 'M3U8' ? 'hls' : link.type === 'DASH' ? 'dash' : 'file',
            behaviorHints: { notWebReady: false, bingeGroup: `streamplay-${link.source ?? ''}` } });
        }));
      }
      if (result.length || (raw.done && eligible.size <= candidates.length) || Date.now() >= deadline || seen.size >= 24) break;
      await new Promise(resolve => setTimeout(resolve, 750));
      } while (Date.now() < deadline);
      const value = { streams: result, cacheMaxAge: 0 };
      // Session-local, deliberately short. Never cache an empty result from a still-running extraction.
      if (result.length) cache.set(key, { value, expires: Date.now() + 45_000 });
      for (const [k, v] of cache) if (v.expires <= Date.now()) cache.delete(k);
      return value;
    })().finally(() => { active--; inflight.delete(key); });
    inflight.set(key, job);
    return job;
  }
  const server = http.createServer(async (req, res) => {
    const origin = req.headers.origin;
    const host = req.headers.host ?? '';
    const send = (status, value) => { res.writeHead(status, { 'Content-Type': 'application/json', 'Cache-Control': 'no-store', 'X-Content-Type-Options': 'nosniff' }); res.end(JSON.stringify(value)); };
    if (!/^(127\.0\.0\.1|localhost):\d+$/.test(host)) return send(403, { error: 'Local host only' });
    if (origin && !origins.includes(origin)) return send(403, { error: 'Origin not allowed' });
    if (origin) { res.setHeader('Access-Control-Allow-Origin', origin); res.setHeader('Vary', 'Origin'); }
    if (req.method === 'OPTIONS') {
      res.setHeader('Access-Control-Allow-Methods', 'GET, OPTIONS'); res.setHeader('Access-Control-Allow-Headers', 'Accept');
      res.setHeader('Access-Control-Allow-Private-Network', 'true'); return send(200, {});
    }
    if (req.method !== 'GET') return send(405, { error: 'GET required' });
    try {
      const url = new URL(req.url, 'http://127.0.0.1');
      if (url.pathname === '/manifest.json') return send(200, manifest);
      if (url.pathname === '/plugins.json') return send(200, [pinnedPlugin]);
      if (url.pathname === '/health') return send(200, { runtime: await worker('/health'), metrics, mediaRelay: false });
      const parts = url.pathname.split('/').filter(Boolean).map(decodeURIComponent);
      if (parts[0] === 'catalog' && ['movie', 'series'].includes(parts[1]) && parts[2]?.replace(/\.json$/, '') === 'streamplay') {
        const extra = new URLSearchParams((parts[3] ?? '').replace(/\.json$/, ''));
        const q = (extra.get('search') || (parts[1] === 'movie' ? 'Sintel' : 'Fallout')).trim();
        if (q.length < 2 || q.length > 100) return send(400, { error: 'Search must be 2–100 characters' });
        const result = await worker('/search', { q });
        if (result.error && !result.items?.length) return send(502, { error: 'StreamPlay search failed' });
        return send(200, { metas: (result.items ?? []).map(register).filter(item => item.type === parts[1]) });
      }
      if (['meta', 'stream'].includes(parts[0]) && ['movie', 'series'].includes(parts[1])) {
        const id = (parts[2] ?? '').replace(/\.json$/, '');
        const match = /^(cs-poc:(?:[0-9a-f]{24}|\d{1,10})|tmdb:\d{1,10})(?::(\d+):(\d+))?$/.exec(id);
        const numericId = match && /^(?:cs-poc|tmdb):(\d{1,10})$/.exec(match[1]);
        const standard = numericId && (parts[0] === 'stream' || match[1].startsWith('cs-poc:')) ? {
          id: match[1], type: parts[1], url: JSON.stringify({ id: Number(numericId[1]), type: parts[1] === 'series' ? 'tv' : 'movie', aniId: null, malId: null })
        } : null;
        const entry = standard || (match && entries.get(match[1]));
        if (!entry || entry.type !== parts[1]) return send(404, { error: 'Search for this item first' });
        if (parts[0] === 'stream') return send(200, await streams(entry, match[2], match[3], origin || origins[0]));
        const d = await worker('/load', { url: entry.url });
        return send(200, { meta: { id: entry.id, type: entry.type, name: d.name, poster: d.poster,
          background: d.background, logo: d.logo, description: d.plot, releaseInfo: d.year ? String(d.year) : undefined,
          genres: d.tags, videos: (d.episodes ?? []).filter(e => e.season != null && e.episode != null).map(e => ({
            id: `${entry.id}:${e.season}:${e.episode}`, title: e.name || `Episode ${e.episode}`,
            season: e.season, episode: e.episode, thumbnail: e.poster, overview: e.description,
            released: e.date ? new Date(e.date).toISOString() : undefined })) } });
      }
      send(404, { error: 'Not found. This bridge never relays video.' });
    } catch { send(502, { error: 'StreamPlay could not complete this request; check the local runtime.' }); }
  });
  return { server, metrics };
}

if (process.argv[1] && import.meta.url === pathToFileURL(process.argv[1]).href) {
  const { server } = createBridge({ token: process.env.ARVIO_CS_WORKER_TOKEN });
  server.listen(8950, '127.0.0.1', () => console.log('Local StreamPlay manifest: http://127.0.0.1:8950/manifest.json (no media relay)'));
}
