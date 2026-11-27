// Details for one game page: score per period, standings, recent form and
// head-to-head. Fetched only when a viewer opens a game's details, cached in the
// shared store per game, so many viewers of one game cost one upstream refresh.
const FEEDS = {
  nfl: 'football/nfl', nba: 'basketball/nba', wnba: 'basketball/wnba', nhl: 'hockey/nhl',
  epl: 'soccer/eng.1', ucl: 'soccer/uefa.champions',
};

const text = (value, max = 120) => typeof value === 'string' && value.trim() ? value.trim().slice(0, max) : null;
const number = value => {
  if (value === null || value === undefined || value === '') return null;
  const parsed = Number(String(value).replace(/^\+/, ''));
  return Number.isFinite(parsed) && Math.abs(parsed) < 100_000 ? parsed : null;
};
const result = value => ['W', 'D', 'L'].includes(String(value || '').trim().toUpperCase().replace('T', 'D'))
  ? String(value).trim().toUpperCase().replace('T', 'D') : null;
const key = value => String(value || '').normalize('NFD').replace(/[̀-ͯ]/g, '').toLowerCase().replace(/[^a-z0-9]+/g, ' ').trim();

/** "123", "espn:nba:401", "mlb:7788", or null. Nothing else reaches an upstream URL. */
function gameId(value) {
  if (typeof value !== 'string' || value.length > 40) return null;
  if (/^\d{1,12}$/.test(value)) return { source: 'sportsdb', id: value };
  const espn = value.match(/^espn:([a-z]{2,8}):(\d{1,14})$/);
  if (espn && FEEDS[espn[1]]) return { source: 'espn', feed: espn[1], id: espn[2] };
  const mlb = value.match(/^mlb:(\d{1,12})$/);
  if (mlb) return { source: 'mlb', id: mlb[1] };
  return null;
}

async function json(fetcher, url, headers = {}) {
  const response = await fetcher(url, { signal: AbortSignal.timeout(4_000), redirect: 'error', headers: { Accept: 'application/json', ...headers } });
  if (!response.ok) throw new Error('Unavailable');
  return response.json();
}

function emptyDetails(now, id) {
  return { version: 1, updatedAt: now, id, status: 'scheduled', homeTeam: null, awayTeam: null,
    periods: [], standings: [], standingsTitle: null, form: { home: [], away: [] }, headToHead: [] };
}

// ── TheSportsDB ────────────────────────────────────────────────────────────

/** A team's last results, from that team's side, never including this game. */
function formFrom(rows, teamId, excludeId) {
  // A feed can repeat a game; count each game once, newest first.
  const seen = new Set();
  const games = (rows || []).filter(row => {
    if (!row || String(row.idEvent) === excludeId) return false;
    const id = String(row.idEvent || `${row.dateEvent}|${row.idHomeTeam}|${row.idAwayTeam}`);
    if (seen.has(id)) return false;
    seen.add(id);
    return true;
  }).sort((a, b) => String(b.dateEvent || '').localeCompare(String(a.dateEvent || '')));
  return games.flatMap(row => {
    const home = number(row.intHomeScore), away = number(row.intAwayScore);
    if (home === null || away === null) return [];
    const isHome = String(row.idHomeTeam) === String(teamId);
    if (!isHome && String(row.idAwayTeam) !== String(teamId)) return [];
    const own = isHome ? home : away, other = isHome ? away : home;
    return [own > other ? 'W' : own < other ? 'L' : 'D'];
  }).slice(0, 5);
}

function sportsDbMatch(row) {
  const home = number(row.intHomeScore), away = number(row.intAwayScore);
  const date = typeof row.dateEvent === 'string' && /^\d{4}-\d{2}-\d{2}$/.test(row.dateEvent) ? row.dateEvent : null;
  if (home === null || away === null || !date || !text(row.strHomeTeam) || !text(row.strAwayTeam)) return null;
  return { id: String(row.idEvent || ''), date, homeTeam: text(row.strHomeTeam), awayTeam: text(row.strAwayTeam), homeScore: home, awayScore: away };
}

function sportsDbStandings(rows, teamIds) {
  return (rows || []).slice(0, 40).flatMap((row, index) => {
    const team = text(row?.strTeam);
    if (!team) return [];
    return [{ rank: number(row.intRank) ?? index + 1, team, played: number(row.intPlayed), won: number(row.intWin),
      drawn: number(row.intDraw), lost: number(row.intLoss), difference: number(row.intGoalDifference), points: number(row.intPoints),
      highlight: teamIds.includes(String(row.idTeam)) }];
  });
}

async function sportsDbDetails({ fetcher, apiKey, now }, id) {
  const base = `https://www.thesportsdb.com/api/v1/json/${encodeURIComponent(apiKey)}`;
  const payload = await json(fetcher, `${base}/lookupevent.php?id=${id}`);
  const event = Array.isArray(payload?.events) ? payload.events[0] : null;
  if (!event || String(event.idEvent) !== id) throw new Error('Unknown game');
  const details = emptyDetails(now, id);
  details.homeTeam = text(event.strHomeTeam); details.awayTeam = text(event.strAwayTeam);
  const finished = /^(ft|aet|ap|finished|match finished|full time|final|ended)$/i.test(String(event.strStatus || '').trim());
  details.status = finished ? 'finished' : 'scheduled';
  const homeId = /^\d+$/.test(event.idHomeTeam) ? String(event.idHomeTeam) : null;
  const awayId = /^\d+$/.test(event.idAwayTeam) ? String(event.idAwayTeam) : null;
  const leagueId = /^\d+$/.test(event.idLeague) ? String(event.idLeague) : null;
  const season = typeof event.strSeason === 'string' && /^[\d-]{4,9}$/.test(event.strSeason) ? event.strSeason : null;
  // Every part is optional: one missing feed must not hide the others.
  const optional = promise => promise.catch(() => null);
  const [table, homeLast, awayLast, h2hA, h2hB] = await Promise.all([
    leagueId && season ? optional(json(fetcher, `${base}/lookuptable.php?l=${leagueId}&s=${encodeURIComponent(season)}`)) : null,
    homeId ? optional(json(fetcher, `${base}/eventslast.php?id=${homeId}`)) : null,
    awayId ? optional(json(fetcher, `${base}/eventslast.php?id=${awayId}`)) : null,
    details.homeTeam && details.awayTeam ? optional(json(fetcher, `${base}/searchevents.php?e=${encodeURIComponent(`${details.homeTeam}_vs_${details.awayTeam}`.replaceAll(' ', '_'))}`)) : null,
    details.homeTeam && details.awayTeam ? optional(json(fetcher, `${base}/searchevents.php?e=${encodeURIComponent(`${details.awayTeam}_vs_${details.homeTeam}`.replaceAll(' ', '_'))}`)) : null,
  ]);
  if (Array.isArray(table?.table)) {
    details.standings = sportsDbStandings(table.table, [homeId, awayId].filter(Boolean));
    details.standingsTitle = text(event.strLeague);
  }
  details.form.home = formFrom(homeLast?.results, homeId, id);
  details.form.away = formFrom(awayLast?.results, awayId, id);
  const startsOn = typeof event.dateEvent === 'string' ? event.dateEvent : '9999-99-99';
  details.headToHead = [...(h2hA?.event || []), ...(h2hB?.event || [])]
    .map(row => row && String(row.idEvent) !== id ? sportsDbMatch(row) : null)
    .filter(match => match && match.date <= startsOn)
    .filter((match, index, all) => all.findIndex(other => other.id === match.id) === index)
    .sort((a, b) => b.date.localeCompare(a.date)).slice(0, 5);
  return details;
}

// ── ESPN ───────────────────────────────────────────────────────────────────

function espnStandings(payload, names) {
  const wanted = new Set(names.map(key));
  const groups = Array.isArray(payload?.standings?.groups) ? payload.standings.groups : [];
  // The group (division, conference) that holds one of the two teams.
  const group = groups.find(g => (g?.standings?.entries || []).some(e => wanted.has(key(e?.team?.displayName || e?.team)))) || groups[0];
  const entries = Array.isArray(group?.standings?.entries) ? group.standings.entries : [];
  return {
    title: text(group?.header) || text(group?.name),
    rows: entries.slice(0, 40).flatMap((entry, index) => {
      const team = text(entry?.team?.displayName) || text(entry?.team);
      if (!team) return [];
      const stat = (...keys) => {
        const found = (entry.stats || []).find(s => keys.includes(s?.name) || keys.includes(s?.type) || keys.includes(s?.abbreviation));
        return number(found?.value ?? found?.displayValue);
      };
      return [{ rank: stat('rank') ?? index + 1, team, played: stat('gamesPlayed', 'GP'), won: stat('wins', 'W'),
        drawn: stat('ties', 'T', 'D'), lost: stat('losses', 'L'), difference: stat('pointDifferential', 'differential', 'GD'),
        points: stat('points', 'P', 'PTS'), highlight: wanted.has(key(team)) }];
    }),
  };
}

function espnForm(payload, name) {
  const lists = [...(payload?.lastFiveGames || []), ...(payload?.boxscore?.form || [])];
  const entry = lists.find(item => key(item?.team?.displayName) === key(name));
  return (entry?.events || []).map(event => result(event?.gameResult)).filter(Boolean).slice(0, 5);
}

function espnHeadToHead(payload, gameIdValue) {
  const output = [];
  for (const block of payload?.headToHeadGames || []) {
    const teamName = text(block?.team?.displayName);
    for (const event of block?.events || []) {
      const date = typeof event?.gameDate === 'string' ? event.gameDate.slice(0, 10) : null;
      const home = number(event?.homeTeamScore), away = number(event?.awayTeamScore);
      const opponent = text(event?.opponent?.displayName);
      if (!date || home === null || away === null || !teamName || !opponent || String(event?.id) === gameIdValue) continue;
      const atHome = event?.atVs === 'vs' || String(event?.homeTeamId) === String(block?.team?.id);
      output.push({ id: String(event.id || `${date}${home}${away}`), date, homeTeam: atHome ? teamName : opponent,
        awayTeam: atHome ? opponent : teamName, homeScore: home, awayScore: away });
    }
  }
  for (const series of payload?.seasonseries || []) {
    for (const event of series?.events || []) {
      const competitors = event?.competitors || [];
      const home = competitors.find(c => c?.homeAway === 'home'), away = competitors.find(c => c?.homeAway === 'away');
      const date = typeof event?.date === 'string' ? event.date.slice(0, 10) : null;
      if (!home || !away || !date || String(event?.id) === gameIdValue || !/post|final/i.test(event?.statusType?.state || event?.status || 'post')) continue;
      const hs = number(home.score), as = number(away.score);
      if (hs === null || as === null) continue;
      output.push({ id: String(event.id || date), date, homeTeam: text(home.team?.displayName), awayTeam: text(away.team?.displayName), homeScore: hs, awayScore: as });
    }
  }
  return output.filter(m => m.homeTeam && m.awayTeam)
    .filter((m, index, all) => all.findIndex(o => o.id === m.id) === index)
    .sort((a, b) => b.date.localeCompare(a.date)).slice(0, 5);
}

async function espnDetails({ fetcher, now }, game) {
  const payload = await json(fetcher, `https://site.api.espn.com/apis/site/v2/sports/${FEEDS[game.feed]}/summary?event=${game.id}`);
  const competition = payload?.header?.competitions?.[0];
  const competitors = competition?.competitors || [];
  const home = competitors.find(c => c?.homeAway === 'home'), away = competitors.find(c => c?.homeAway === 'away');
  if (!home || !away) throw new Error('Unknown game');
  const details = emptyDetails(now, `espn:${game.feed}:${game.id}`);
  details.homeTeam = text(home.team?.displayName); details.awayTeam = text(away.team?.displayName);
  const state = competition?.status?.type?.state;
  details.status = competition?.status?.type?.completed || state === 'post' ? 'finished' : state === 'in' ? 'live' : 'scheduled';
  const homeLines = home.linescores || [], awayLines = away.linescores || [];
  const soccer = game.feed === 'epl' || game.feed === 'ucl';
  const label = index => soccer ? (index < 2 ? `H${index + 1}` : index < 4 ? `ET${index - 1}` : 'P')
    : game.feed === 'nhl' ? (index < 3 ? `P${index + 1}` : index === 3 ? 'OT' : 'SO') : (index < 4 ? `Q${index + 1}` : `OT${index > 4 ? index - 3 : ''}`);
  for (let i = 0; i < Math.min(12, Math.max(homeLines.length, awayLines.length)); i++) {
    const h = number(homeLines[i]?.value ?? homeLines[i]?.displayValue), a = number(awayLines[i]?.value ?? awayLines[i]?.displayValue);
    if (h !== null && a !== null) details.periods.push({ label: label(i), home: h, away: a });
  }
  const standings = espnStandings(payload, [details.homeTeam, details.awayTeam].filter(Boolean));
  details.standings = standings.rows; details.standingsTitle = standings.title;
  details.form.home = espnForm(payload, details.homeTeam);
  details.form.away = espnForm(payload, details.awayTeam);
  details.headToHead = espnHeadToHead(payload, game.id);
  return details;
}

// ── MLB ────────────────────────────────────────────────────────────────────

async function mlbDetails({ fetcher, now }, game) {
  const payload = await json(fetcher, `https://statsapi.mlb.com/api/v1/game/${game.id}/linescore`);
  if (!Array.isArray(payload?.innings)) throw new Error('Unknown game');
  const details = emptyDetails(now, `mlb:${game.id}`);
  for (const inning of payload.innings.slice(0, 20)) {
    const h = number(inning?.home?.runs), a = number(inning?.away?.runs);
    if (h !== null || a !== null) details.periods.push({ label: String(number(inning.num) ?? details.periods.length + 1), home: h ?? 0, away: a ?? 0 });
  }
  const outs = number(payload.outs);
  details.status = payload.currentInning && outs !== null && payload.isTopInning !== undefined ? 'live' : 'scheduled';
  return details;
}

async function fetchGame(options, game) {
  if (game.source === 'espn') return espnDetails(options, game);
  if (game.source === 'mlb') return mlbDetails(options, game);
  return sportsDbDetails(options, game.id);
}

module.exports = { gameId, fetchGame, formFrom, espnStandings, espnForm, espnHeadToHead, sportsDbStandings };
