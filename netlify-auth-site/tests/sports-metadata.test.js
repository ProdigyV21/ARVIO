const { test } = require('node:test');
const assert = require('node:assert/strict');
const { normalizeEvent, eventStatus, fetchLive, getMetadata, createHandler } = require('../netlify/functions/_sports-metadata');
const now = Date.parse('2026-09-10T12:00:00Z');
const badge = 'https://r2.thesportsdb.com/images/media/team/badge/test.png';
const fixture = { idEvent: '123', strEvent: 'Alpha vs Beta', strSport: 'Soccer', strTimestamp: '2026-09-10T14:00:00', idHomeTeam: '1', idAwayTeam: '2', strHomeTeam: 'Alpha', strAwayTeam: 'Beta', strHomeTeamBadge: badge, strAwayTeamBadge: badge };
function store() {
  const records = new Map(); let revision = 0;
  return {
    getWithMetadata: async key => structuredClone(records.get(key)) ?? null,
    setJSON: async (key, data, options) => {
      const old = records.get(key);
      if ((options.onlyIfNew && old) || (options.onlyIfMatch !== undefined && options.onlyIfMatch !== old?.etag)) return { modified: false };
      const etag = String(++revision); records.set(key, { data: structuredClone(data), etag }); return { modified: true, etag };
    },
  };
}
test('UTC fixtures, verified pairs, and only public artwork fields', () => {
  const event = normalizeEvent({ ...fixture, secret: 'must-not-escape' });
  assert.equal(event.startsAt, Date.parse('2026-09-10T14:00:00Z'));
  assert.equal(event.homeBadge, badge);
  assert.equal(event.secret, undefined);
  assert.equal(normalizeEvent({ ...fixture, strAwayTeamBadge: 'https://evil.test/art.png' }).awayBadge, null);
  assert.equal(normalizeEvent({ ...fixture, strStatus: 'Postponed' }).status, 'postponed');
  assert.equal(normalizeEvent({ ...fixture, strLeague: 'Women\'s Champions League' }).qualifier, 'women');
  assert.equal(normalizeEvent({ ...fixture, strLeague: 'Youth League' }).qualifier, 'youth');
  assert.equal(normalizeEvent({ ...fixture, strTimestamp: '', dateEvent: '2026-09-10' }), null);
  assert.equal(normalizeEvent({ ...fixture, strThumb: 'https://r2.thesportsdb.com/images/media/event/thumb/a.jpg' }).background.endsWith('a.jpg'), true);
});
const fetchPayload = url => url.includes('/livescore/') ? { livescore: [] } : url.includes('eventstv.php') ? { tvevents: [] } : url.includes('/filter/') ? { filter: [] } : { events: [fixture] };
test('100 concurrent clients consume NINE bounded upstream requests, then reuse shared cache', async () => {
  let calls = 0;
  const shared = store();
  const deps = { store: shared, apiKey: 'test-only-key', now, fetcher: async url => { calls++; return { ok: true, json: async () => fetchPayload(url) }; } };
  await Promise.all(Array.from({ length: 100 }, () => getMetadata(deps)));
  assert.equal(calls, 9);
  const result = await getMetadata({ ...deps, now: now + 60_000 });
  assert.equal(result.events.length, 1);
  assert.equal(calls, 9);
  await getMetadata({ ...deps, now: now + 121_000 });
  assert.equal(calls, 10, 'only the live feed refreshes after two minutes');
});
test('refresh failure serves stale artwork and globally backs off; no secret in response', async () => {
  const shared = store(); let calls = 0;
  const deps = { store: shared, apiKey: 'secret-key', now, fetcher: async url => ({ ok: true, json: async () => fetchPayload(url) }) };
  const old = await getMetadata(deps);
  const failed = { ...deps, now: now + 31 * 60_000, fetcher: async () => { calls++; throw new Error('https://api/secret-key'); } };
  assert.deepEqual(await getMetadata(failed), old);
  assert.deepEqual(await getMetadata(failed), old);
  assert.equal(calls, 2);
  const handler = createHandler(() => ({ ...failed, store: store() }));
  const response = await handler({ httpMethod: 'GET' });
  assert.equal(response.statusCode, 503);
  assert.ok(!JSON.stringify(response).includes('secret-key'));
  assert.equal((await handler({ httpMethod: 'POST' })).statusCode, 405);
});
test('no-art fixtures survive; terminal/live status and null scores are honest', async () => {
  assert.ok(normalizeEvent({ ...fixture, strHomeTeamBadge: null, strAwayTeamBadge: null }));
  assert.equal(eventStatus('FT'), 'finished');
  assert.equal(eventStatus('NS'), 'scheduled');
  assert.equal(eventStatus("45+2'"), 'live');
  const response = await fetchLive({ now, apiKey: 'test', fetcher: async () => ({ ok: true, json: async () => ({ livescore: [{ idEvent: '123', strProgress: '2H', updated: '2026-09-10 11:59:00', intHomeScore: null, intAwayScore: '0' }] }) }) });
  assert.equal(response.events[0].observedAt, now - 60_000);
  assert.equal(response.events[0].homeScore, null);
  assert.equal(response.events[0].awayScore, 0);
});
test('cache failure and missing API key never fall back to direct traffic', async () => {
  let calls = 0;
  const fetcher = async () => { calls++; throw new Error(); };
  assert.equal(await getMetadata({ store: store(), fetcher, now }), null);
  const handler = createHandler(() => ({ store: { getWithMetadata: async () => { throw new Error(); } }, apiKey: 'key', fetcher, now }));
  assert.equal((await handler({ httpMethod: 'GET' })).statusCode, 503);
  assert.equal(calls, 0);
});

test('truncated daily broadcasts get bounded shared regional coverage', async () => {
  const { fetchFixtures } = require('../netlify/functions/_sports-metadata');
  let calls = 0;
  const result = await fetchFixtures({ apiKey: 'test', now, fetcher: async url => {
    calls++;
    const payload = url.includes('/country/') ? { filter: [{ idEvent: '123', strChannel: 'TNT Sports 2', strCountry: 'United Kingdom', strTimeStamp: '2026-09-10T14:00:00Z' }] }
      : url.includes('eventstv.php') ? { tvevents: Array.from({length: 1500}, () => ({idEvent: 'not-in-window'})) } : { events: [fixture] };
    return { ok: true, json: async () => payload };
  } });
  assert.equal(calls, 16, 'four fixture days, four TV days, eight regional supplements');
  assert.equal(result.events[0].broadcasters.length, 1, 'deduplicate across feeds');
  assert.equal(result.events[0].broadcasters[0].name, 'TNT Sports 2');
  assert.equal(result.broadcastsPartial, true, 'supplementing cannot promise complete worldwide coverage');
});

test('premium daily schedule keeps broadcasts after the old 100-row cutoff', async () => {
  const { fetchFixtures } = require('../netlify/functions/_sports-metadata');
  const calls = [];
  const result = await fetchFixtures({ apiKey: 'test', now, fetcher: async url => {
    calls.push(url);
    return { ok: true, json: async () => url.includes('eventstv.php') ? { tvevents: Array.from({ length: 350 }, (_, i) => ({
      idEvent: '123', strChannel: `Sports ${i}`, strCountry: 'Netherlands', strTimestamp: fixture.strTimestamp,
    })) } : { events: [fixture] } };
  } });
  assert.equal(result.events[0].broadcasters.length, 350);
  assert.equal(result.broadcastsPartial, false);
  assert.equal(calls.length, 8, 'no per-event or per-user requests');
});

test('one unavailable TV day does not discard the remaining days', async () => {
  const { fetchFixtures } = require('../netlify/functions/_sports-metadata');
  let days = 0;
  const result = await fetchFixtures({ apiKey: 'test', now, fetcher: async url => {
    if (url.includes('eventstv.php')) {
      if (++days === 1) throw new Error('Unavailable');
      return { ok: true, json: async () => ({ tvevents: [{ idEvent: '123', strChannel: 'Sports', strCountry: 'NL', strTimeStamp: fixture.strTimestamp }] }) };
    }
    return { ok: true, json: async () => url.includes('/country/') ? { filter: [] } : { events: [fixture] } };
  } });
  assert.equal(days, 4);
  assert.equal(result.events[0].broadcasters.length, 1);
  assert.equal(result.broadcastsPartial, true);
});
