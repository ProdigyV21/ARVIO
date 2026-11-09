const fs = require('node:fs');
const path = require('node:path');
const { execFileSync } = require('node:child_process');
const evidence = path.resolve(__dirname, '../../artifacts/cloudstream-poc');
const sources = JSON.parse(fs.readFileSync(path.join(evidence, 'sources.private.json')));
const directory = process.env.ARVIO_FFMPEG_DIR;
if (!directory) throw new Error('Set ARVIO_FFMPEG_DIR to your FFmpeg bin directory');
const source = sources.find(s => s.quality === '1080p') ?? sources[0];
const begin = Date.now();
try {
  const metadata = JSON.parse(execFileSync(path.join(directory, 'ffprobe.exe'), [
    '-v', 'error', '-rw_timeout', '10000000', '-probesize', '2000000', '-analyzeduration', '2000000',
    '-show_entries', 'format=duration:stream=codec_type,codec_name,width,height', '-of', 'json', source.url
  ], { timeout: 25000, encoding: 'utf8', stdio: ['ignore', 'pipe', 'pipe'] }));
  const samples = [];
  for (const at of [0, 120]) {
    const start = Date.now();
    execFileSync(path.join(directory, 'ffmpeg.exe'), ['-v', 'error', '-rw_timeout', '10000000', '-ss', String(at),
      '-i', source.url, '-t', '6', '-map', '0:v:0', '-map', '0:a:0?', '-f', 'null', '-'],
    { timeout: 30000, stdio: ['ignore', 'pipe', 'pipe'] });
    samples.push({ startSeconds: at, decodedSeconds: 6, elapsedMs: Date.now() - start, passed: true });
  }
  const report = { checkedAt: new Date().toISOString(), title: 'Sintel', host: new URL(source.url).hostname,
    metadata, samples, elapsedMs: Date.now() - begin, path: 'Direct media host, no ARVIO relay, no provider headers', browserPlaybackVerified: false };
  fs.writeFileSync(path.join(evidence, 'media-result.json'), JSON.stringify(report, null, 2));
  console.log(JSON.stringify(report, null, 2));
} catch (e) {
  console.error('Direct media verification failed', { exitCode: e.status, signal: e.signal });
  process.exitCode = 1;
}
