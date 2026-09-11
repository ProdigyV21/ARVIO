const DAY = 86_400_000;
const CACHE_KEY = 'fixtures-v5';
const REFRESH_MS = 30 * 60_000;
const RETRY_MS = 5 * 60_000;
const MAX_AGE = 24 * 60 * 60_000;

function image(value) {
  if (typeof value !== 'string' || value.length > 2048) return null;
  try {
    const url = new URL(value);
    return url.protocol === 'https:' && ['www.thesportsdb.com', 'r2.thesportsdb.com'].includes(url.hostname)
      && url.pathname.startsWith('/images/media/') && !url.search && !url.username && !url.password ? url.href : null;
  } catch { return null; }
}

function utcTimestamp(value) {
  if (typeof value !== 'string' || !/^\d{4}-\d{2}-\d{2}[T ]/.test(value)) return NaN;
  const timestamp = value.replace(' ', 'T');
  return Date.parse(/[zZ]|[+-]\d{2}:?\d{2}$/.test(timestamp) ? timestamp : `${timestamp}Z`);
}

function eventStatus(value) {
  const status = String(value || '').trim().toLowerCase();
  if (/^(ft|aet|ap|finished|match finished|full time|final|ended)$/.test(status)) return 'finished';
  if (/postpon|cancel|abandon/.test(status)) return 'postponed';
  if (/^(live|in progress|in play|1h|2h|ht|et|bt|p|q[1-4]|[1-4]q|[1-3]p|[1-5]s|half time|halftime)$/.test(status) || /^\d{1,3}(\+\d{1,2})?'?$/.test(status)) return 'live';
  return 'scheduled';
}

function normalizeEvent(event) {
  if (!event || typeof event !== 'object' || !/^\d+$/.test(event.idEvent)) return null;
  const title = typeof event.strEvent === 'string' ? event.strEvent.trim().slice(0, 240) : '';
  const rawSport = typeof event.strSport === 'string' ? event.strSport.trim().slice(0, 80) : '';
  // Some feeds omit women/youth qualifiers from team titles. Do not let those
  // banners match an unqualified men's programme simply because names coincide.
  const qualifier = /women|womens|women's|youth|u\d{2}\b|under[ -]?\d{2}/i;
  const leagueQualifier = String(event.strLeague || '').match(qualifier)?.[0];
  const sport = rawSport === 'Fighting' && /ufc|mma|mixed martial/i.test(event.strLeague || '') ? 'MMA'
    : rawSport === 'Motorsport' && /formula 1|formula one/i.test(event.strLeague || '') ? 'Formula 1' : rawSport;
  // SportsDB timestamps without an offset are UTC, never the server's local time.
  const timestamp = event.strTimestamp || (event.dateEvent && event.strTime ? `${event.dateEvent}T${event.strTime}` : '');
  const startsAt = utcTimestamp(timestamp);
  if (!title || !sport || !Number.isFinite(startsAt)) return null;
  const background = image(event.strThumb) || image(event.strFanart);
  const homeBadge = image(event.strHomeTeamBadge), awayBadge = image(event.strAwayTeamBadge);
  const teamPair = event.idHomeTeam && event.idAwayTeam && event.idHomeTeam !== event.idAwayTeam
    && event.strHomeTeam && event.strAwayTeam;
  return {
    id: String(event.idEvent), title, sport, startsAt, background,
    homeBadge: teamPair ? homeBadge : null, awayBadge: teamPair ? awayBadge : null,
    homeTeam: teamPair ? String(event.strHomeTeam).slice(0, 120) : null,
    awayTeam: teamPair ? String(event.strAwayTeam).slice(0, 120) : null,
    league: typeof event.strLeague === 'string' ? event.strLeague.slice(0, 120) : null,
    leagueId: /^\d+$/.test(event.idLeague) ? String(event.idLeague) : null,
    homeId: teamPair ? String(event.idHomeTeam) : null, awayId: teamPair ? String(event.idAwayTeam) : null,
    qualifier: leagueQualifier ? leagueQualifier.toLowerCase() : null,
    venue: typeof event.strVenue === 'string' ? event.strVenue.slice(0, 160) : null,
    round: event.intRound ? String(event.intRound).slice(0, 20) : null,
    status: event.strPostponed === 'yes' ? 'postponed' : eventStatus(event.strStatus),
    broadcasters: [],
  };
}

async function fetchFixtures({ fetcher, apiKey, now }) {
  const events = new Map();
  let partial = false;
  // Fixed shared window, not client-controlled upstream queries. Covers local today/tomorrow in every timezone.
  for (const offset of [-1, 0, 1, 2]) {
    const day = new Date(now + offset * DAY).toISOString().slice(0, 10);
    const response = await fetcher(`https://www.thesportsdb.com/api/v1/json/${encodeURIComponent(apiKey)}/eventsday.php?d=${day}`, {
      signal: AbortSignal.timeout(4_000), redirect: 'error', headers: { Accept: 'application/json' },
    });
    if (!response.ok) throw new Error('Sports metadata unavailable');
    const payload = await response.json();
    if (!payload || !Object.prototype.hasOwnProperty.call(payload, 'events') || (payload.events !== null && !Array.isArray(payload.events))) throw new Error('Invalid sports metadata');
    const rows = payload.events || [];
    partial ||= rows.length >= 1500;
    for (const row of rows.slice(0, 1500)) {
      const event = normalizeEvent(row);
      if (event) events.set(event.id, event);
    }
  }
  // A bounded broadcast feed, never one request per event or per user's channel.
  // Keep fixtures usable even if the optional TV listing service is unavailable.
  let broadcastsPartial = false;
  const mergeBroadcasts = rows => {
    for (const row of rows.slice(0, 1500)) {
      const event = events.get(String(row.idEvent));
      const start = utcTimestamp(row.strTimeStamp || row.strTimestamp);
      if (!event || typeof row.strChannel !== 'string' || !row.strChannel.trim() || !Number.isFinite(start)) continue;
      const broadcaster = { name: row.strChannel.trim().slice(0, 120), country: String(row.strCountry || '').slice(0, 80), startsAt: start };
      if (!event.broadcasters.some(b => b.name === broadcaster.name && b.country === broadcaster.country && b.startsAt === start)) event.broadcasters.push(broadcaster);
    }
  };
  for (const offset of [-1, 0, 1, 2]) {
    try {
      const day = new Date(now + offset * DAY).toISOString().slice(0, 10);
      // Premium V1 returns up to 1500 listings; the V2 day filter stops at 100.
      const response = await fetcher(`https://www.thesportsdb.com/api/v1/json/${encodeURIComponent(apiKey)}/eventstv.php?d=${day}`, {
        signal: AbortSignal.timeout(4_000), redirect: 'error', headers: { Accept: 'application/json' },
      });
      if (!response.ok) throw new Error('Unavailable');
      const payload = await response.json();
      if (!Object.prototype.hasOwnProperty.call(payload, 'tvevents') || (payload.tvevents !== null && !Array.isArray(payload.tvevents))) throw new Error('Unavailable');
      broadcastsPartial ||= (payload.tvevents?.length ?? 0) >= 1500;
      mergeBroadcasts(payload.tvevents || []);
    } catch { broadcastsPartial = true; }
  }
  // Supplement only truncated/failed feeds. These are shared requests, never per viewer.
  if (broadcastsPartial) {
    const countries = ['united_kingdom', 'netherlands', 'united_states', 'germany', 'france', 'spain', 'italy', 'brazil'];
    for (let i = 0; i < countries.length; i += 3) {
      await Promise.all(countries.slice(i, i + 3).map(async country => {
        try {
          const response = await fetcher(`https://www.thesportsdb.com/api/v2/json/filter/tv/country/${country}`, {
            signal: AbortSignal.timeout(4_000), redirect: 'error', headers: { Accept: 'application/json', 'X-API-KEY': apiKey },
          });
          if (!response.ok) return;
          const payload = await response.json();
          if (Array.isArray(payload.filter)) mergeBroadcasts(payload.filter);
        } catch { /* A missing regional feed must not discard the fixture catalogue. */ }
      }));
    }
  }
  return { version: 1, catalogueEnabled: true, updatedAt: now, partial, broadcastsPartial,
    rankingBasis: 'competition-and-broadcast-reach', attribution: 'TheSportsDB', events: [...events.values()] };
}

async function fetchLive({ fetcher, apiKey, now }) {
  const response = await fetcher('https://www.thesportsdb.com/api/v2/json/livescore/all', {
    signal: AbortSignal.timeout(4_000), redirect: 'error', headers: { Accept: 'application/json', 'X-API-KEY': apiKey },
  });
  if (!response.ok) throw new Error('Unavailable');
  const payload = await response.json();
  if (!Array.isArray(payload.livescore)) throw new Error('Unavailable');
  const score = value => value !== null && value !== '' && /^\d{1,3}$/.test(String(value)) ? Number(value) : null;
  return { version: 1, updatedAt: now, events: payload.livescore.slice(0, 500).filter(row => /^\d+$/.test(row.idEvent)).map(row => ({
    id: String(row.idEvent), status: eventStatus(row.strStatus || row.strProgress),
    observedAt: Math.min(now, utcTimestamp(row.updated) || 0),
    homeScore: score(row.intHomeScore), awayScore: score(row.intAwayScore),
  })) };
}

// The shared record doubles as a CAS lease. A failed refresh backs off across ALL instances,
// not just one warm function. Never fall back to uncoordinated upstream requests on cache errors.
async function cachedResource({ store, apiKey, fetcher = fetch, now = Date.now(), report = () => {} }, key, refresh, fetchResource) {
  const record = await store.getWithMetadata(key, { type: 'json', consistency: 'strong' });
  const cached = record?.data?.payload;
  const usable = cached?.version === 1 && now - cached.updatedAt < MAX_AGE ? cached : null;
  if (record?.data?.refreshAfter > now) { report('backoff'); return usable; }
  if (!apiKey) { report('not-configured'); return usable; }
  if (record && !record.etag) return usable;
  const claim = await store.setJSON(key, { payload: usable, refreshAfter: now + RETRY_MS },
    record ? { onlyIfMatch: record.etag } : { onlyIfNew: true });
  if (!claim.modified || !claim.etag) return usable;
  try {
    const payload = await fetchResource({ fetcher, apiKey, now });
    await store.setJSON(key, { payload, refreshAfter: now + refresh }, { onlyIfMatch: claim.etag });
    return payload;
  } catch {
    // Do not log upstream URLs or errors: V1 embeds the secret in its URL.
    report('upstream-unavailable');
    return usable;
  }
}

async function getMetadata(dependencies) {
  const fixtures = await cachedResource(dependencies, CACHE_KEY, REFRESH_MS, fetchFixtures);
  if (!fixtures) return null;
  const live = await cachedResource(dependencies, 'live-v1', 120_000, fetchLive);
  const now = dependencies.now ?? Date.now();
  const byId = new Map((live?.events || []).filter(row => now - row.observedAt < 300_000).map(row => [row.id, row]));
  return { ...fixtures, liveUpdatedAt: live?.updatedAt || null, events: fixtures.events.map(event => {
    const update = byId.get(event.id);
    return { ...event, ...(update || {}), observedAt: update?.observedAt ?? fixtures.updatedAt };
  }) };
}

const headers = {
  'content-type': 'application/json; charset=utf-8',
  'access-control-allow-origin': '*', 'access-control-allow-methods': 'GET,OPTIONS',
  'x-content-type-options': 'nosniff',
};
function createHandler(dependencies) {
  return async event => {
    if (event.httpMethod === 'OPTIONS') return { statusCode: 204, headers, body: '' };
    if (event.httpMethod !== 'GET') return { statusCode: 405, headers: { ...headers, allow: 'GET,OPTIONS' }, body: '{}' };
    let state = 'refreshing';
    try {
      const payload = await getMetadata({ ...await dependencies(event), report: value => { state = value; } });
      if (payload) return { statusCode: 200, headers: { ...headers, 'cache-control': 'public, max-age=30, s-maxage=60' },
        body: JSON.stringify({ ...payload, catalogueEnabled: process.env.SPORTS_CATALOGUE_ENABLED !== 'false' }) };
    } catch { state = 'cache-unavailable'; }
    return { statusCode: 503, headers: { ...headers, 'cache-control': 'no-store', 'retry-after': '60', 'x-artwork-status': state }, body: '{"error":"Sports artwork is temporarily unavailable"}' };
  };
}
module.exports = { normalizeEvent, eventStatus, fetchFixtures, fetchLive, getMetadata, createHandler };
