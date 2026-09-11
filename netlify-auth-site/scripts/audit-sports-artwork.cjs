// Read-only audit: no playlist credentials or playback URLs are read or printed.
const fs = require('node:fs');
const vm = require('node:vm');
const path = require('node:path');
const { DatabaseSync } = require('node:sqlite');
const ts = require('../../web/node_modules/typescript');
const root = path.resolve(__dirname, '../..');
function load(file, requireFn) {
  const sandbox = { exports: {}, URL, require: requireFn };
  vm.runInNewContext(ts.transpileModule(fs.readFileSync(path.join(root, file), 'utf8'), {compilerOptions: {module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022}}).outputText, sandbox);
  return sandbox.exports;
}
(async () => {
  const endpoint = process.env.SPORTS_METADATA_URL;
  if (!endpoint || !process.env.EPG_AUDIT_DB) throw new Error('Set public SPORTS_METADATA_URL and local EPG_AUDIT_DB');
  const response = await fetch(endpoint);
  if (!response.ok) throw new Error(`Metadata HTTP ${response.status}`);
  const payload = await response.json();
  const guide = load('web/lib/sportsGuide.ts');
  const artwork = load('web/lib/sportsArtwork.ts', name => name === './sportsGuide' ? guide : {});
  const catalogue = load('web/lib/sportsCatalogue.ts', name => name === './sportsGuide' ? guide : artwork);
  const metadata = artwork.parseSportsMetadata(payload);
  const db = new DatabaseSync(process.env.EPG_AUDIT_DB, {readOnly: true});
  const now = Date.now();
  const rows = db.prepare('SELECT DISTINCT title, start_ms, end_ms, category FROM epg_programs WHERE end_ms > ? AND start_ms < ?').all(now, now + 172800000);
  db.close();
  const events = rows.flatMap(row => {
    const sport = guide.guideSports.find(s => s.pattern.test(`${row.category || ''} ${row.title}`));
    if (!sport) return [];
    return [{id: `${row.title}|${row.start_ms}`, title: row.title, sportId: sport.id, programme: {startUtcMillis: row.start_ms, endUtcMillis: row.end_ms}, channels: []}];
  });
  const decorated = artwork.attachSportsArtwork(events, metadata);
  const matches = decorated.filter(event => event.artwork || event.teamArtwork);
  const merged = catalogue.buildSportsCatalogue(events, metadata, [], now);
  const catalogueRows = guide.sportsGuideRows(merged, now);
  console.log(JSON.stringify({feedEvents: metadata.length, banners: metadata.filter(x => x.background).length, badgePairs: metadata.filter(x => x.homeBadge && x.awayBadge).length,
    catalogueEvents: merged.filter(e => e.fixture).length, guideEntriesIdentified: events.length - merged.filter(e => !e.fixture).length,
    catalogueRows: catalogueRows.map(row => ({title: row.title, events: row.events.length})),
    epgSportsCandidates: events.length, matched: matches.length, examples: matches.slice(0, 8).map(x => ({title: x.title, startsAt: new Date(x.programme.startUtcMillis).toISOString(), banner: !!x.artwork, badges: !!x.teamArtwork}))}, null, 2));
})().catch(error => { console.error(error.message); process.exitCode = 1; });
