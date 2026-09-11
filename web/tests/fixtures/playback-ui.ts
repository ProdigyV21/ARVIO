import { probeAndPrepareRemux, type RemuxHandle } from "../../lib/remux";
import { attachPlayback } from "../../lib/player";
import { parseDebridStream, resolveDebridDirectUrl } from "../../lib/debrid";

document.body.innerHTML = `<main><h1>ARVIO browser playback verification</h1>
<video controls playsinline width="960" height="540"></video><p>
<button data-file="aac.mkv">MKV / AAC</button> <button data-file="eac3.mkv">MKV / E-AC-3</button>
<button data-file="dts.mkv">MKV / DTS</button> <button data-file="silent.mkv">Silent MKV</button>
<button data-file="multi.mkv">Two audio tracks</button> <button data-direct="hls/index.m3u8">HLS</button>
<button data-direct="aac.mp4">MP4</button> <button id="seek">Seek to 65s</button>
<button data-file="hevc.mp4">HEVC Main10</button> <button data-file="vp9.webm">VP9 / Opus</button>
<button data-file="av1.mp4">AV1 / AAC</button>
<button id="back">Seek to 12s</button> <button id="pause">Pause</button> <button id="play">Play</button>
<button id="switch">Second audio track</button> <button id="close">Close</button></p>
<p><label>Selected test URL <input id="remote" type="password" autocomplete="off"></label>
<button id="remote-play">Test selected URL</button></p><pre id="status"></pre></main>`;
document.body.style.cssText = "background:#080808;color:white;font:16px system-ui;padding:24px";
const video = document.querySelector("video")!;
video.style.cssText = "max-width:100%;height:auto;background:#161616";
const status = document.querySelector("#status")!;
let handle: RemuxHandle | undefined;
let detach: (() => void) | undefined;
let phase = "idle";
let error = "";
let probeMs = 0;
let firstFrameMs = 0;
let started = 0;
let sourceFile = "";
let rms = 0;
let analyser: AnalyserNode | undefined;
let context: AudioContext | undefined;
let selection = 0;
let abort: AbortController | undefined;
function audioMeter() {
  if (!context) {
    context = new AudioContext();
    analyser = context.createAnalyser();
    context.createMediaElementSource(video).connect(analyser).connect(context.destination);
  }
  void context.resume();
}
const stop = () => { selection++; abort?.abort(); handle?.destroy(); handle = undefined; detach?.(); detach = undefined; phase = "closed"; };
async function start(file: string, audioIndex?: number, position = 0) {
  stop(); audioMeter(); started = performance.now(); phase = "probing"; error = ""; sourceFile = file;
  probeMs = 0; firstFrameMs = 0;
  const run = selection;
  abort = new AbortController();
  try {
    let url = /^https?:/.test(file) ? file : `${location.origin}/media/${file}`;
    const debrid = parseDebridStream(url);
    if (debrid) {
      const resolved = await resolveDebridDirectUrl(debrid);
      if (run !== selection) return;
      if (!resolved.url) throw new Error(resolved.error);
      url = resolved.url;
    }
    const prepared = await probeAndPrepareRemux(url, undefined, "English", { signal: abort.signal, onError: (message) => { if (run === selection) { error = message; phase = "failed"; } } });
    if (run !== selection) { prepared?.destroy(); return; }
    probeMs = performance.now() - started;
    if (!prepared) throw new Error(error || "Probe failed");
    handle = prepared; phase = "buffering";
    await handle.start(video, audioIndex ?? handle.probe.chosenAudioIndex, position);
    if (run !== selection) return;
    firstFrameMs = performance.now() - started;
    await video.play(); phase = "playing";
  } catch (reason) { if (run === selection) { error = String(reason); phase = "failed"; } }
}
document.querySelectorAll<HTMLButtonElement>("[data-file]").forEach((button) => button.onclick = () => { void start(button.dataset.file!); });
document.querySelectorAll<HTMLButtonElement>("[data-direct]").forEach((button) => button.onclick = () => {
  stop(); audioMeter(); error = ""; started = performance.now(); phase = "buffering";
  detach = attachPlayback(video, `${location.origin}/media/${button.dataset.direct}`, { onError: () => { error = "Transport failed"; } });
  void video.play().catch((reason) => { error = String(reason); });
});
document.querySelector<HTMLButtonElement>("#seek")!.onclick = () => { video.currentTime = 65; };
document.querySelector<HTMLButtonElement>("#back")!.onclick = () => { video.currentTime = 12; };
document.querySelector<HTMLButtonElement>("#pause")!.onclick = () => video.pause();
document.querySelector<HTMLButtonElement>("#play")!.onclick = () => { void video.play(); };
document.querySelector<HTMLButtonElement>("#close")!.onclick = stop;
document.querySelector<HTMLButtonElement>("#remote-play")!.onclick = () => {
  const input = document.querySelector<HTMLInputElement>("#remote")!;
  const url = input.value.trim();
  input.value = "";
  if (/^https?:/.test(url)) void start(url);
};
document.querySelector<HTMLButtonElement>("#switch")!.onclick = () => { void start(sourceFile, 1, video.currentTime); };
void fetch('/test-sources').then(response => response.json()).then(({ count }) => {
  for (let i = 0; i < count; i++) {
    const button = document.createElement('button');
    button.textContent = `Configured source ${i + 1}`;
    button.onclick = () => { void fetch(`/test-sources/${i}`).then(response => response.json()).then(({ url }) => start(url)); };
    document.querySelector('main')!.insertBefore(button, status);
  }
}).catch(() => {});
video.addEventListener("playing", () => { phase = "playing"; });
video.addEventListener("error", () => { error = `${video.error?.code}: ${video.error?.message}`; });
setInterval(() => {
  if (analyser) { const samples = new Float32Array(analyser.fftSize); analyser.getFloatTimeDomainData(samples); rms = Math.sqrt(samples.reduce((sum, x) => sum + x*x, 0) / samples.length); }
  status.textContent = JSON.stringify({ phase, error, probeMs: Math.round(probeMs), firstFrameMs: Math.round(firstFrameMs),
    time: video.currentTime, duration: video.duration, paused: video.paused, seeking: video.seeking, readyState: video.readyState,
    width: video.videoWidth, height: video.videoHeight, audioRms: rms,
    frames: video.getVideoPlaybackQuality()?.totalVideoFrames,
    buffered: Array.from({ length: video.buffered.length }, (_, i) => [video.buffered.start(i), video.buffered.end(i)]), probe: handle?.probe }, null, 2);
}, 250);
