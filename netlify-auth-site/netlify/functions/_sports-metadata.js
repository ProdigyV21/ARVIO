const DAY = 86_400_000;
const CACHE_KEY = 'fixtures-v5';
const REFRESH_MS = 30 * 60_000;
const RETRY_MS = 5 * 60_000;
const MAX_AGE = 24 * 60 * 60_000;
const LIVE_STALE_MS = 15 * 60_000;
const { FEEDS, fetchFeed, mergeFixtures } = require('./_sports-supplement');
const { gameId, fetchGame } = require('./_sports-game');

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
  const sport = rawSport === 'Fighting' && /boxing|boxen/i.test(event.strLeague || '') ? 'Boxing'
    : rawSport === 'Fighting' && /ufc|mma|mixed martial/i.test(event.strLeague || '') ? 'MMA'
    : rawSport === 'Motorsport' && /formula 1|formula one/i.test(event.strLeague || '') ? 'Formula 1' : rawSport;
  // SportsDB timestamps without an offset are UTC, never the server's local time.
  const timestamp = event.strTimestamp || (event.dateEvent && event.strTime ? `${event.dateEvent}T${event.strTime}` : '');
  const startsAt = utcTimestamp(timestamp);
  if (!title || !sport || !Number.isFinite(startsAt)) return null;
  const background = image(event.strThumb) || image(event.strFanart) || image(event.strBanner) || image(event.strPoster);
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
    ...finalScore(event),
    broadcasters: [],
  };
}

// Results for a finished game. Clients keep them behind a "show score" tap.
function finalScore(event) {
  if (event.strPostponed === 'yes' || eventStatus(event.strStatus) !== 'finished') return {};
  const score = value => value !== null && value !== undefined && value !== '' && /^\d{1,3}$/.test(String(value)) ? Number(value) : null;
  const homeScore = score(event.intHomeScore), awayScore = score(event.intAwayScore);
  return homeScore === null || awayScore === null ? {} : { homeScore, awayScore };
}

const DAY_RANGE = 8;
/** One calendar day (UTC) within about a week of today, or null. Anything else is not fetched. */
function requestedDay(value, now = Date.now()) {
  if (typeof value !== 'string' || !/^\d{4}-\d{2}-\d{2}$/.test(value)) return null;
  const start = Date.parse(`${value}T00:00:00Z`);
  if (!Number.isFinite(start) || new Date(start).toISOString().slice(0, 10) !== value) return null;
  return Math.abs(start - now) <= DAY_RANGE * DAY ? value : null;
}

async function fetchDay({ fetcher, apiKey, now }, day) {
  const response = await fetcher(`https://www.thesportsdb.com/api/v1/json/${encodeURIComponent(apiKey)}/eventsday.php?d=${day}`, {
    signal: AbortSignal.timeout(4_000), redirect: 'error', headers: { Accept: 'application/json' },
  });
  if (!response.ok) throw new Error('Sports metadata unavailable');
  const payload = await response.json();
  if (!payload || !Object.prototype.hasOwnProperty.call(payload, 'events') || (payload.events !== null && !Array.isArray(payload.events))) throw new Error('Invalid sports metadata');
  const events = new Map();
  for (const row of payload.events || []) {
    const event = normalizeEvent(row);
    if (event) events.set(event.id, { ...event, observedAt: now });
  }
  return { version: 1, catalogueEnabled: true, updatedAt: now, day, partial: (payload.events?.length ?? 0) >= 1500,
    rankingBasis: 'competition-and-broadcast-reach', attribution: 'TheSportsDB', events: [...events.values()] };
}

/**
 * The fixtures of one day further back or ahead than the shared window, for the
 * day tabs. One shared upstream request per day and refresh, never per viewer.
 */
async function getDayMetadata(dependencies, day) {
  const now = dependencies.now ?? Date.now();
  const finished = Date.parse(`${day}T23:59:59Z`) + 6 * 3600_000 < now;
  return cachedResource(dependencies, `day-v1:${day}`, finished ? 12 * 3600_000 : 3600_000, options => fetchDay(options, day));
}

async function fetchFixtures({ fetcher, apiKey, now }) {
  const events = new Map();
  let partial = false;
  // Fixed shared window, not client-controlled upstream queries. Covers local today/tomorrow in every timezone.
  // Four independent days share one bounded refresh, instead of accumulating
  // up to four network timeouts before the next stage can begin.
  const fixtureDays = await Promise.all([-1, 0, 1, 2].map(async offset => {
    const day = new Date(now + offset * DAY).toISOString().slice(0, 10);
    const response = await fetcher(`https://www.thesportsdb.com/api/v1/json/${encodeURIComponent(apiKey)}/eventsday.php?d=${day}`, {
      signal: AbortSignal.timeout(4_000), redirect: 'error', headers: { Accept: 'application/json' },
    });
    if (!response.ok) throw new Error('Sports metadata unavailable');
    const payload = await response.json();
    if (!payload || !Object.prototype.hasOwnProperty.call(payload, 'events') || (payload.events !== null && !Array.isArray(payload.events))) throw new Error('Invalid sports metadata');
    return payload.events || [];
  }));
  for (const rows of fixtureDays) {
    partial ||= rows.length >= 1500;
    for (const row of rows) {
      const event = normalizeEvent(row);
      if (event) events.set(event.id, event);
    }
  }
  // A bounded broadcast feed, never one request per event or per user's channel.
  // Keep fixtures usable even if the optional TV listing service is unavailable.
  let broadcastsPartial = false;
  const mergeBroadcasts = rows => {
    for (const row of rows) {
      const event = events.get(String(row.idEvent));
      const start = utcTimestamp(row.strTimeStamp || row.strTimestamp);
      if (!event || typeof row.strChannel !== 'string' || !row.strChannel.trim() || !Number.isFinite(start)) continue;
      const broadcaster = { name: row.strChannel.trim().slice(0, 120), country: String(row.strCountry || '').slice(0, 80), startsAt: start };
      if (!event.broadcasters.some(b => b.name === broadcaster.name && b.country === broadcaster.country && b.startsAt === start)) event.broadcasters.push(broadcaster);
    }
  };
  await Promise.all([-1, 0, 1, 2].map(async offset => {
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
  }));
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
    // `updated` is the provider's event timestamp, not the freshness of our
    // response. The response itself is the observation and must keep live
    // fixtures visible across a short upstream timestamp lag.
    observedAt: now,
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
    const delay = typeof refresh === 'function' ? refresh(payload) : refresh;
    await store.setJSON(key, { payload, refreshAfter: now + delay }, { onlyIfMatch: claim.etag });
    return payload;
  } catch {
    // Do not log upstream URLs or errors: V1 embeds the secret in its URL.
    report('upstream-unavailable');
    return usable;
  }
}

async function getPrimaryMetadata(dependencies) {
  const fixtures = await cachedResource(dependencies, CACHE_KEY, REFRESH_MS, fetchFixtures);
  if (!fixtures) return null;
  // The observation timestamp semantics changed from upstream-event time to
  // response time; use a new key so old five-minute records cannot linger.
  const live = await cachedResource(dependencies, 'live-v2', 120_000, fetchLive);
  const now = dependencies.now ?? Date.now();
  const byId = new Map((live?.events || []).filter(row => now >= row.observedAt && now - row.observedAt < LIVE_STALE_MS).map(row => [row.id, row]));
  return { ...fixtures, liveUpdatedAt: live?.updatedAt || null, events: fixtures.events.map(event => {
    const update = byId.get(event.id);
    return { ...event, ...(update || {}), observedAt: update?.observedAt ?? fixtures.updatedAt };
  }) };
}

async function supplementalMetadata(dependencies) {
  const now = dependencies.now ?? Date.now();
  const snapshots = [];
  // At most three feeds refresh concurrently, behind per-feed cross-instance leases.
  for (let i = 0; i < FEEDS.length; i += 3) {
    snapshots.push(...await Promise.all(FEEDS.slice(i, i + 3).map(async feed => {
      const key = `supplement-v1:${feed.id}`;
      try {
        if (dependencies.refreshSupplemental) return await cachedResource(
          { ...dependencies, apiKey: 'public-metadata', now }, key, 5 * 60_000, options => fetchFeed(options, feed));
        // Viewer requests only read these records; extra feeds never delay opening Sports.
        const record = await dependencies.store.getWithMetadata(key, { type: 'json', consistency: 'strong' });
        const payload = record?.data?.payload;
        return payload?.version === 1 && now >= payload.updatedAt && now - payload.updatedAt < MAX_AGE ? payload : null;
      } catch { return null; }
    })));
  }
  return snapshots.filter(Boolean).flatMap(snapshot => snapshot.events);
}

/**
 * One game's details, fetched when a viewer opens them. A live game refreshes
 * every minute, a finished one twice a day, anything else every ten minutes.
 */
async function getGameDetails(dependencies, game) {
  const deps = game.source === 'sportsdb' ? dependencies : { ...dependencies, apiKey: 'public-metadata' };
  const refresh = payload => payload.status === 'live' ? 60_000 : payload.status === 'finished' ? 12 * 3600_000 : 10 * 60_000;
  return cachedResource(deps, `game-v1:${game.source}:${game.feed || ''}:${game.id}`, refresh, options => fetchGame(options, game));
}

async function getMetadata(dependencies) {
  if (!dependencies.supplemental) return getPrimaryMetadata(dependencies);
  const [primary, extra] = await Promise.all([getPrimaryMetadata(dependencies), supplementalMetadata(dependencies)]);
  if (!primary && !extra.length) return null;
  const baseline = primary || { version: 1, catalogueEnabled: true, updatedAt: dependencies.now ?? Date.now(),
    partial: true, broadcastsPartial: true, rankingBasis: 'competition-and-broadcast-reach', events: [] };
  return { ...baseline, events: mergeFixtures(baseline.events, extra) };
}

/**
 * A small slice of the shared catalogue for score alerts: games with two teams
 * that are live, ended in the last six hours or start in the next twelve.
 * Built from the same cached records; never an extra upstream request.
 */
function scoresSlice(payload, now) {
  const events = (payload?.events || []).filter(event => event.homeTeam && event.awayTeam &&
    event.status !== 'postponed' && event.startsAt > now - 6 * 3600_000 && event.startsAt < now + 12 * 3600_000)
    .map(({ id, source, title, sport, league, qualifier, startsAt, homeTeam, awayTeam, status, observedAt, homeScore, awayScore }) =>
      ({ id, source, title, sport, league, qualifier, startsAt, homeTeam, awayTeam, status, observedAt, homeScore, awayScore }));
  return { version: 1, catalogueEnabled: true, updatedAt: payload.updatedAt, liveUpdatedAt: payload.liveUpdatedAt || null, events };
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
    const dayParameter = event.queryStringParameters?.day;
    const gameParameter = event.queryStringParameters?.game;
    let day = null;
    try {
      const deps = { ...await dependencies(event), report: value => { state = value; } };
      if (dayParameter !== undefined) {
        day = requestedDay(dayParameter, deps.now ?? Date.now());
        if (!day) return { statusCode: 400, headers: { ...headers, 'cache-control': 'no-store' }, body: '{"error":"Unsupported day"}' };
      }
      if (gameParameter !== undefined) {
        const game = gameId(gameParameter);
        if (!game) return { statusCode: 400, headers: { ...headers, 'cache-control': 'no-store' }, body: '{"error":"Unsupported game"}' };
        const details = await getGameDetails(deps, game);
        if (details) return { statusCode: 200, headers: { ...headers, 'cache-control': details.status === 'finished' ? 'public, max-age=600, s-maxage=3600' : 'public, max-age=30, s-maxage=60' },
          body: JSON.stringify(details) };
        return { statusCode: 503, headers: { ...headers, 'cache-control': 'no-store', 'retry-after': '60' }, body: '{"error":"Game details are temporarily unavailable"}' };
      }
      if (event.queryStringParameters?.scores !== undefined) {
        const payload = await getMetadata(deps);
        if (payload) return { statusCode: 200, headers: { ...headers, 'cache-control': 'public, max-age=30, s-maxage=60' },
          body: JSON.stringify(scoresSlice(payload, deps.now ?? Date.now())) };
        return { statusCode: 503, headers: { ...headers, 'cache-control': 'no-store', 'retry-after': '60' }, body: '{"error":"Scores are temporarily unavailable"}' };
      }
      const payload = day ? await getDayMetadata(deps, day) : await getMetadata(deps);
      if (payload) return { statusCode: 200, headers: { ...headers, 'cache-control': day ? 'public, max-age=300, s-maxage=900' : 'public, max-age=30, s-maxage=60' },
        body: JSON.stringify({ ...payload, catalogueEnabled: process.env.SPORTS_CATALOGUE_ENABLED !== 'false' }) };
    } catch { state = 'cache-unavailable'; }
    return { statusCode: 503, headers: { ...headers, 'cache-control': 'no-store', 'retry-after': '60', 'x-artwork-status': state }, body: '{"error":"Sports artwork is temporarily unavailable"}' };
  };
}
module.exports = { normalizeEvent, eventStatus, fetchFixtures, fetchLive, getMetadata, getDayMetadata, getGameDetails, requestedDay, scoresSlice, createHandler };
