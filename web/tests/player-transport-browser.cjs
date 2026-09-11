// Standalone transport smoke test, using the parent's synthetic media without changing it.
// Run: node tests/player-transport-browser.cjs (requires ffmpeg and .playback-fixtures/aac.mp4).
const fs = require('node:fs');
const path = require('node:path');
const os = require('node:os');
const http = require('node:http');
const { spawnSync } = require('node:child_process');
const ts = require('typescript');
const root = path.resolve(__dirname, '..');
const media = path.resolve(root, '../.playback-fixtures');
const tempRoot = fs.realpathSync(os.tmpdir());
const dash = fs.mkdtempSync(path.join(tempRoot, 'arvio-transport-'));
const generated = spawnSync('ffmpeg', ['-hide_banner', '-loglevel', 'error', '-i', path.join(media, 'aac.mp4'),
  '-t', '90', '-map', '0:v:0', '-map', '0:a:0', '-c', 'copy', '-seg_duration', '2', '-use_template', '1', '-use_timeline', '1',
  '-f', 'dash', 'index.mpd'], { cwd: dash, windowsHide: true, encoding: 'utf8' });
if (generated.status !== 0) throw new Error(generated.stderr || String(generated.error));

const modules = new Map(['player', 'playerRecovery', 'capabilities'].map((name) => [name, ts.transpileModule(
  fs.readFileSync(path.join(root, 'lib', `${name}.ts`), 'utf8'),
  { compilerOptions: { target: ts.ScriptTarget.ES2022, module: ts.ModuleKind.ESNext } }
).outputText]));
const packages = {
  '/hls.mjs': 'hls.js/dist/hls.mjs',
  '/dash.mjs': 'dashjs/dist/modern/esm/dash.all.min.js',
  '/mpegts.umd.js': 'mpegts.js/dist/mpegts.js'
};
const html = `<!doctype html><html><head><meta name="viewport" content="width=device-width,initial-scale=1"><title>Transport smoke test</title>
<style>body{font:14px system-ui;margin:20px}video{width:720px;max-width:100%;background:#111}button,select{margin:4px}pre{white-space:pre-wrap}</style>
<script type="importmap">{"imports":{"hls.js":"/hls.mjs","dashjs":"/dash.mjs","mpegts.js":"/mpegts.mjs"}}</script></head><body>
<h1>Transport smoke test</h1><video controls playsinline muted></video><div>
<button data-url="/media/hls/index.m3u8">HLS</button><button data-url="/dash/index.mpd">DASH</button>
<button data-url="/media/hls/index0.ts">TS VOD</button><button id="reload">Reload</button><button id="seek">Seek 45s</button>
<button id="pause">Pause</button><button id="play">Play</button><button id="close">Close</button>
<label>Audio <select id="audio"></select></label><label>Quality <select id="quality"></select></label></div><pre id="status"></pre>
<script src="/mpegts.umd.js"></script><script type="module">
import { attachPlayback } from '/player';
const video = document.querySelector('video');
let handle, tracks = {}, errors = [], source = '', actions = [];
function updateTracks(value) {
  tracks = value;
  for (const [id, entries, selected] of [['audio', value.audioTracks, value.selectedAudioTrackId], ['quality', [{id:'',label:'Auto'}, ...value.qualities], value.selectedQualityId]]) {
    const select = document.getElementById(id); select.replaceChildren();
    for (const entry of entries) { const option = document.createElement('option'); option.value = entry.id; option.textContent = entry.label; select.append(option); }
    select.value = selected ?? '';
  }
}
document.querySelectorAll('[data-url]').forEach(button => button.onclick = () => {
  handle?.(); errors = []; source = button.dataset.url;
  handle = attachPlayback(video, location.origin + source, { onTracks: updateTracks, onError: error => errors.push(error), requestHeaders: { 'X-Transport-Test': 'smoke' } });
});
video.addEventListener('canplay', () => { if (!video.dataset.userPaused) void video.play().catch(error => errors.push(String(error))); });
document.getElementById('reload').onclick = () => handle?.reload();
document.getElementById('seek').onclick = () => { video.currentTime = 45; };
document.getElementById('pause').onclick = () => { video.dataset.userPaused = 'true'; video.pause(); };
document.getElementById('play').onclick = () => { delete video.dataset.userPaused; void video.play(); };
document.getElementById('close').onclick = () => handle?.();
document.getElementById('audio').onchange = event => actions.push({audio: handle?.selectAudioTrack(event.target.value)});
document.getElementById('quality').onchange = event => actions.push({quality: handle?.selectQuality(event.target.value || null)});
setInterval(() => document.getElementById('status').textContent = JSON.stringify({source, errors, actions, time: video.currentTime, paused: video.paused,
  readyState: video.readyState, width: video.videoWidth, height: video.videoHeight, frames: video.getVideoPlaybackQuality().totalVideoFrames,
  buffered: Array.from({length: video.buffered.length}, (_, i) => [video.buffered.start(i), video.buffered.end(i)]), tracks}, null, 2), 200);
</script></body></html>`;

function sendFile(req, res, file) {
  const size = fs.statSync(file).size;
  const range = req.headers.range?.match(/^bytes=(\d+)-(\d*)$/);
  const start = range ? Number(range[1]) : 0;
  const end = range?.[2] ? Math.min(size - 1, Number(range[2])) : size - 1;
  if (start >= size || end < start) { res.writeHead(416).end(); return; }
  const mime = { '.m3u8': 'application/vnd.apple.mpegurl', '.mpd': 'application/dash+xml', '.ts': 'video/mp2t', '.mp4': 'video/mp4', '.m4s': 'video/iso.segment', '.mjs': 'text/javascript', '.js': 'text/javascript' };
  res.writeHead(range ? 206 : 200, { 'Content-Type': mime[path.extname(file)] || 'application/octet-stream', 'Accept-Ranges': 'bytes',
    'Content-Length': end - start + 1, ...(range ? { 'Content-Range': `bytes ${start}-${end}/${size}` } : {}) });
  fs.createReadStream(file, { start, end }).pipe(res);
}
const server = http.createServer((req, res) => {
  const url = new URL(req.url, 'http://localhost');
  if (modules.has(url.pathname.slice(1))) { res.setHeader('Content-Type', 'text/javascript'); res.end(modules.get(url.pathname.slice(1))); return; }
  if (url.pathname === '/mpegts.mjs') { res.setHeader('Content-Type', 'text/javascript'); res.end('export default globalThis.mpegts;'); return; }
  if (packages[url.pathname]) { sendFile(req, res, path.join(root, 'node_modules', packages[url.pathname])); return; }
  for (const [prefix, directory] of [['/media/', media], ['/dash/', dash]]) {
    if (!url.pathname.startsWith(prefix)) continue;
    const file = path.resolve(directory, decodeURIComponent(url.pathname.slice(prefix.length)));
    if (!file.startsWith(directory + path.sep) || !fs.existsSync(file) || !fs.statSync(file).isFile()) { res.writeHead(404).end(); return; }
    sendFile(req, res, file); return;
  }
  res.setHeader('Content-Type', 'text/html'); res.end(html);
});
const port = Number(process.env.PORT || 3101);
server.listen(port, '127.0.0.1', () => console.log(`Transport fixture: http://127.0.0.1:${port}`));
process.on('SIGINT', () => server.close(() => process.exit(0)));
process.on('exit', () => {
  const target = path.resolve(dash);
  if (path.dirname(target) === tempRoot && path.basename(target).startsWith('arvio-transport-')) fs.rmSync(target, { recursive: true, force: true });
});
