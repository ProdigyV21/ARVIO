const { test } = require('node:test');
const assert = require('node:assert/strict');
const { gameId, fetchGame, formFrom } = require('../netlify/functions/_sports-game');
const { createHandler } = require('../netlify/functions/_sports-metadata');
const now = Date.parse('2026-11-26T12:00:00Z');
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
const respond = body => ({ ok: true, json: async () => body });

test('only known game id shapes reach an upstream URL', () => {
  assert.deepEqual(gameId('123'), { source: 'sportsdb', id: '123' });
  assert.deepEqual(gameId('espn:nba:401'), { source: 'espn', feed: 'nba', id: '401' });
  assert.deepEqual(gameId('mlb:77'), { source: 'mlb', id: '77' });
  for (const bad of ['', 'espn:xyz:1', 'espn:nba:1/../x', '1;2', 'mlb:', '../123', 'a'.repeat(50)]) assert.equal(gameId(bad), null, bad);
});

test('form is told from the team\'s own side and never includes this game', () => {
  const rows = [
    { idEvent: '9', idHomeTeam: '1', idAwayTeam: '2', intHomeScore: '3', intAwayScore: '0' },
    { idEvent: '8', idHomeTeam: '3', idAwayTeam: '1', intHomeScore: '2', intAwayScore: '1' },
    { idEvent: '7', idHomeTeam: '1', idAwayTeam: '4', intHomeScore: '1', intAwayScore: '1' },
    { idEvent: '6', idHomeTeam: '1', idAwayTeam: '4', intHomeScore: null, intAwayScore: null },
  ];
  assert.deepEqual(formFrom(rows, '1', '9'), ['L', 'D']);
  assert.deepEqual(formFrom(rows, '1', 'x'), ['W', 'L', 'D']);
});

test('TheSportsDB game: standings, form and head-to-head, without this game', async () => {
  const calls = [];
  const fetcher = async url => {
    calls.push(url);
    if (url.includes('lookupevent.php')) return respond({ events: [{ idEvent: '500', idLeague: '4328', strSeason: '2026-2027', strLeague: 'English Premier League',
      idHomeTeam: '1', idAwayTeam: '2', strHomeTeam: 'Alpha', strAwayTeam: 'Beta', dateEvent: '2026-11-26', strStatus: 'NS' }] });
    if (url.includes('lookuptable.php')) return respond({ table: [
      { intRank: '1', idTeam: '2', strTeam: 'Beta', intPlayed: '12', intWin: '9', intDraw: '2', intLoss: '1', intGoalDifference: '15', intPoints: '29' },
      { intRank: '2', idTeam: '5', strTeam: 'Gamma', intPlayed: '12', intWin: '8', intDraw: '2', intLoss: '2', intGoalDifference: '9', intPoints: '26' },
    ] });
    if (url.includes('eventslast.php?id=1')) return respond({ results: [{ idEvent: '400', idHomeTeam: '1', idAwayTeam: '5', intHomeScore: '2', intAwayScore: '0' }] });
    if (url.includes('eventslast.php?id=2')) return respond({ results: null });
    if (url.includes('Alpha_vs_Beta')) return respond({ event: [
      { idEvent: '500', strHomeTeam: 'Alpha', strAwayTeam: 'Beta', intHomeScore: null, intAwayScore: null, dateEvent: '2026-11-26' },
      { idEvent: '300', strHomeTeam: 'Alpha', strAwayTeam: 'Beta', intHomeScore: '1', intAwayScore: '2', dateEvent: '2025-03-01' },
    ] });
    if (url.includes('Beta_vs_Alpha')) return respond({ event: [{ idEvent: '200', strHomeTeam: 'Beta', strAwayTeam: 'Alpha', intHomeScore: '0', intAwayScore: '0', dateEvent: '2025-10-10' }] });
    return { ok: false };
  };
  const details = await fetchGame({ fetcher, apiKey: 'k', now }, { source: 'sportsdb', id: '500' });
  assert.equal(details.standings.length, 2);
  assert.equal(details.standings[0].highlight, true);
  assert.equal(details.standings[1].highlight, false);
  assert.deepEqual(details.form, { home: ['W'], away: [] });
  assert.deepEqual(details.headToHead.map(m => m.id), ['200', '300']);
  assert.equal(calls.length, 6);
});

test('ESPN game: score per period, standings group, form and head-to-head', async () => {
  const summary = {
    header: { competitions: [{ status: { type: { state: 'in', completed: false } }, competitors: [
      { homeAway: 'home', team: { id: '10', displayName: 'Boston Celtics' }, linescores: [{ value: 30 }, { value: 25 }] },
      { homeAway: 'away', team: { id: '11', displayName: 'New York Knicks' }, linescores: [{ displayValue: '28' }, { displayValue: '27' }] },
    ] }] },
    standings: { groups: [
      { header: 'Western Conference', standings: { entries: [{ team: 'Denver Nuggets', stats: [{ name: 'wins', value: 10 }] }] } },
      { header: 'Eastern Conference', standings: { entries: [
        { team: 'New York Knicks', stats: [{ name: 'wins', value: 12 }, { name: 'losses', value: 4 }] },
        { team: { displayName: 'Boston Celtics' }, stats: [{ name: 'wins', displayValue: '11' }, { name: 'losses', displayValue: '5' }] },
      ] } },
    ] },
    lastFiveGames: [{ team: { displayName: 'Boston Celtics' }, events: [{ gameResult: 'W' }, { gameResult: 'L' }] }],
    seasonseries: [{ events: [{ id: '1', date: '2026-11-01T00:00Z', statusType: { state: 'post' }, competitors: [
      { homeAway: 'home', score: '101', team: { displayName: 'New York Knicks' } }, { homeAway: 'away', score: '99', team: { displayName: 'Boston Celtics' } }] }] }],
  };
  const details = await fetchGame({ fetcher: async () => respond(summary), now }, { source: 'espn', feed: 'nba', id: '401' });
  assert.equal(details.status, 'live');
  assert.deepEqual(details.periods, [{ label: 'Q1', home: 30, away: 28 }, { label: 'Q2', home: 25, away: 27 }]);
  assert.equal(details.standingsTitle, 'Eastern Conference');
  assert.deepEqual(details.standings.map(r => [r.team, r.won, r.highlight]), [['New York Knicks', 12, true], ['Boston Celtics', 11, true]]);
  assert.deepEqual(details.form, { home: ['W', 'L'], away: [] });
  assert.equal(details.headToHead[0].homeScore, 101);
});

test('game endpoint validates the id and shares one cached upstream fetch', async () => {
  let calls = 0;
  const shared = store();
  const handler = createHandler(async () => ({ store: shared, apiKey: 'k', now, fetcher: async () => {
    calls++; return respond({ innings: [{ num: 1, home: { runs: 1 }, away: { runs: 0 } }] });
  } }));
  assert.equal((await handler({ httpMethod: 'GET', queryStringParameters: { game: 'mlb:x' } })).statusCode, 400);
  const first = await handler({ httpMethod: 'GET', queryStringParameters: { game: 'mlb:77' } });
  assert.equal(first.statusCode, 200);
  assert.deepEqual(JSON.parse(first.body).periods, [{ label: '1', home: 1, away: 0 }]);
  await handler({ httpMethod: 'GET', queryStringParameters: { game: 'mlb:77' } });
  assert.equal(calls, 1);
  assert.equal(first.body.includes('"k"'), false);
});

test('scores slice keeps only near games with two teams and drops artwork and broadcasters', () => {
  const { scoresSlice } = require('../netlify/functions/_sports-metadata');
  const game = { id: '1', title: 'A vs B', sport: 'Soccer', startsAt: now - 3600_000, homeTeam: 'A', awayTeam: 'B', status: 'live', homeScore: 1, awayScore: 0,
    background: 'https://r2.thesportsdb.com/images/media/x.jpg', broadcasters: [{ name: 'X' }] };
  const slice = scoresSlice({ updatedAt: now, events: [game, { ...game, id: '2', homeTeam: null }, { ...game, id: '3', startsAt: now - 7 * 3600_000 },
    { ...game, id: '4', startsAt: now + 13 * 3600_000 }, { ...game, id: '5', status: 'postponed' }] }, now);
  assert.deepEqual(slice.events.map(e => e.id), ['1']);
  assert.equal(slice.events[0].background, undefined);
  assert.equal(slice.events[0].broadcasters, undefined);
  assert.equal(slice.catalogueEnabled, true);
});
