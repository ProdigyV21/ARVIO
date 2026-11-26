// Complementary fixture adapters inspired by SeriousSportSync's multi-source design.
// No playback discovery or per-user provider requests belong in this service.
const DAY = 86_400_000;
const FEEDS = [
  { id: 'nfl', path: 'football/nfl', league: 'NFL', sport: 'American Football' },
  { id: 'nba', path: 'basketball/nba', league: 'NBA', sport: 'Basketball' },
  { id: 'wnba', path: 'basketball/wnba', league: 'WNBA', sport: 'Basketball' },
  { id: 'nhl', path: 'hockey/nhl', league: 'NHL', sport: 'Ice Hockey' },
  { id: 'epl', path: 'soccer/eng.1', league: 'English Premier League', sport: 'Soccer' },
  { id: 'ucl', path: 'soccer/uefa.champions', league: 'UEFA Champions League', sport: 'Soccer' },
  { id: 'mlb', league: 'MLB', sport: 'Baseball' },
];
const text = value => typeof value === 'string' ? value.trim().slice(0, 240) : '';
const date = value => typeof value === 'string' && /T.*(?:Z|[+-]\d\d:\d\d)$/i.test(value) ? Date.parse(value) : NaN;
function logo(value) {
  try {
    const url = new URL(value);
    return url.protocol === 'https:' && url.hostname === 'a.espncdn.com' && url.pathname.startsWith('/i/teamlogos/')
      && !url.username && !url.password && !url.search ? url.href : null;
  } catch { return null; }
}
/** Both scores or neither; clients keep them behind "show score" and use them for alerts. */
function scores(home, away) {
  const value = v => v !== null && v !== undefined && v !== '' && /^\d{1,3}$/.test(String(v)) ? Number(v) : null;
  const h = value(home), a = value(away);
  return h === null || a === null ? {} : { homeScore: h, awayScore: a };
}
function base(id, feed, startsAt, home, away, now) {
  return { id, source: feed.id === 'mlb' ? 'MLB' : 'ESPN', title: `${home} vs ${away}`,
    sport: feed.sport, league: feed.league, qualifier: feed.qualifier || null, startsAt,
    homeTeam: home, awayTeam: away, background: null, homeBadge: null, awayBadge: null,
    status: 'scheduled', observedAt: now, broadcasters: [] };
}
function parseEspn(payload, feed, now) {
  if (!Array.isArray(payload?.events)) throw new Error('Invalid scoreboard');
  return payload.events.flatMap(event => (event.competitions || []).flatMap(game => {
    const homeSide = game.competitors?.find(c => c.homeAway === 'home');
    const awaySide = game.competitors?.find(c => c.homeAway === 'away');
    const home = homeSide?.team, away = awaySide?.team;
    const start = date(game.date || event.date);
    const id = String(game.id || event.id || '');
    if (!/^\d+$/.test(id) || !Number.isFinite(start) || game.timeValid === false || event.dateValid === false
      || !text(home?.displayName) || !text(away?.displayName) || home.id === away.id) return [];
    const status = game.status?.type || event.status?.type || {};
    const result = base(`espn:${feed.id}:${id}`, feed, start, text(home.displayName), text(away.displayName), now);
    result.homeBadge = logo(home.logo); result.awayBadge = logo(away.logo);
    result.status = /POSTPON|CANCEL|SUSPEND/i.test(status.name || '') ? 'postponed'
      : status.completed || status.state === 'post' ? 'finished' : status.state === 'in' ? 'live' : 'scheduled';
    if (result.status === 'live' || result.status === 'finished') Object.assign(result, scores(homeSide?.score, awaySide?.score));
    // Broadcast territories are not consistently supplied; never infer country from league.
    return [result];
  }));
}
function parseMlb(payload, feed, now) {
  if (!Array.isArray(payload?.dates)) throw new Error('Invalid MLB schedule');
  return payload.dates.flatMap(day => (day.games || []).flatMap(game => {
    const home = text(game.teams?.home?.team?.name), away = text(game.teams?.away?.team?.name);
    const start = date(game.gameDate);
    if (!/^\d+$/.test(String(game.gamePk)) || !home || !away || !Number.isFinite(start) || game.status?.startTimeTBD) return [];
    const result = base(`mlb:${game.gamePk}`, feed, start, home, away, now);
    const badge = team => /^\d+$/.test(String(team?.id)) ? `https://www.mlbstatic.com/team-logos/${team.id}.svg` : null;
    result.homeBadge = badge(game.teams.home.team); result.awayBadge = badge(game.teams.away.team);
    result.status = /postpon|cancel|suspend/i.test(game.status?.detailedState || '') ? 'postponed'
      : game.status?.abstractGameState === 'Final' ? 'finished' : game.status?.abstractGameState === 'Live' ? 'live' : 'scheduled';
    if (result.status === 'live' || result.status === 'finished') Object.assign(result, scores(game.teams?.home?.score, game.teams?.away?.score));
    return [result];
  }));
}
async function readJson(fetcher, url, signal) {
  const response = await fetcher(url, { signal, redirect: 'error', headers: { Accept: 'application/json' } });
  if (!response.ok) throw new Error('Schedule unavailable');
  const reader = response.body.getReader();
  const chunks = []; let size = 0;
  try {
    while (true) {
      const { done, value } = await reader.read(); if (done) break;
      size += value.byteLength;
      if (size > 4 * 1024 * 1024) throw new Error('Schedule too large');
      chunks.push(Buffer.from(value));
    }
    return JSON.parse(Buffer.concat(chunks).toString('utf8'));
  } finally { await reader.cancel().catch(() => {}); }
}
async function fetchFeed({ fetcher = fetch, now }, feed) {
  // One deadline for the entire feed, not a fresh timeout for every day.
  const signal = AbortSignal.timeout(4500);
  const days = [-1, 0, 1, 2].map(offset => new Date(now + offset * DAY).toISOString().slice(0, 10));
  let events = [];
  if (feed.id === 'mlb') {
    events = parseMlb(await readJson(fetcher, `https://statsapi.mlb.com/api/v1/schedule?sportId=1&startDate=${days[0]}&endDate=${days[3]}&hydrate=team`, signal), feed, now);
  } else {
    // Daily queries work where ESPN date ranges can return HTTP 400. A failed day
    // keeps this feed's previous complete snapshot instead of replacing it with a partial slate.
    for (const day of days) events.push(...parseEspn(await readJson(fetcher,
      `https://site.api.espn.com/apis/site/v2/sports/${feed.path}/scoreboard?dates=${day.replaceAll('-', '')}&limit=1000`, signal), feed, now));
  }
  const start = Date.parse(`${days[0]}T00:00:00Z`), end = Date.parse(`${days[3]}T00:00:00Z`) + DAY;
  return { version: 1, updatedAt: now, events: [...new Map(events.filter(e => e.startsAt >= start && e.startsAt < end).map(e => [e.id, e])).values()] };
}

const key = value => String(value || '').normalize('NFD').replace(/[\u0300-\u036f]/g, '').toLowerCase().replace(/[^a-z0-9]+/g, ' ').trim();
function teamKey(value) {
  const name = key(value).replace(/^(?:fc|club|clube) /, '').replace(/ (?:fc|cf|afc)$/, '');
  const aliases = { 'man utd': 'manchester united', 'man city': 'manchester city', 'psg': 'paris saint germain', 'paris sg': 'paris saint germain', 'internazionale': 'inter milan', 'bayern munchen': 'bayern munich', 'atl madrid': 'atletico madrid', 'la dodgers': 'los angeles dodgers' };
  return aliases[name] || name;
}
function identity(event) {
  if (!event.homeTeam || !event.awayTeam) return null;
  const sport = key(event.sport).replace(/^football$/, 'soccer');
  const league = key(event.league).replace(/^english premier league$/, 'premier league');
  return `${sport}|${league}|${key(event.qualifier)}|${[teamKey(event.homeTeam), teamKey(event.awayTeam)].sort().join('|')}`;
}
function mergeFixtures(primary, extras) {
  const output = primary.map(e => ({ ...e }));
  const index = new Map();
  const add = e => { const k = identity(e); if (k) index.set(k, [...(index.get(k) || []), e]); };
  output.forEach(add);
  for (const event of extras) {
    // Strict start tolerance preserves doubleheaders and separate same-team fixtures.
    const matches = (index.get(identity(event)) || []).filter(e => Math.abs(e.startsAt - event.startsAt) <= 30 * 60_000);
    if (matches.length === 1) {
      const target = matches[0];
      if (!(target.homeBadge && target.awayBadge) && event.homeBadge && event.awayBadge) {
        const same = teamKey(target.homeTeam) === teamKey(event.homeTeam);
        target.homeBadge = same ? event.homeBadge : event.awayBadge;
        target.awayBadge = same ? event.awayBadge : event.homeBadge;
      }
      // Preserve SportsDB identity, broadcaster records, status and official artwork.
    } else if (!matches.length) { output.push({ ...event }); add(output.at(-1)); }
  }
  return output;
}
module.exports = { FEEDS, fetchFeed, parseEspn, parseMlb, mergeFixtures };
