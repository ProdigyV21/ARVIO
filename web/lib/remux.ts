import { mediaSourceConstructor } from "./capabilities";
import type { RemuxCommand, RemuxEvent, RemuxProbe } from "./remuxProtocol";
export type { RemuxAudioTrack, RemuxProbe } from "./remuxProtocol";

export type RemuxHandle = {
  probe: RemuxProbe;
  start: (video: HTMLVideoElement, audioIndex?: number, startTime?: number) => Promise<void>;
  destroy: () => void;
};

export async function probeAndPrepareRemux(
  url: string, requestHeaders?: Record<string, string>, preferredAudioLang?: string,
  options: { signal?: AbortSignal; onError?: (message: string) => void; expectDolbyVision?: boolean } = {}
): Promise<RemuxHandle | null> {
  const Mse = mediaSourceConstructor();
  if (!Mse || options.signal?.aborted) return null;
  const worker = new Worker(new URL("./remux.worker.ts", import.meta.url), { type: "module" });
  const send = (message: RemuxCommand) => { if (!destroyed) worker.postMessage(message); };
  let destroyed = false;
  let objectUrl: string | undefined;
  let element: HTMLVideoElement | undefined;
  let previousRemotePlayback = false;
  let source: MediaSource | undefined;
  let buffer: SourceBuffer | undefined;
  let generation = 0;
  let clockTimer: ReturnType<typeof setInterval> | undefined;
  const cleanups = new Set<() => void>();
  const destroy = () => {
    if (destroyed) return;
    destroyed = true;
    clearInterval(clockTimer);
    for (const cleanup of [...cleanups]) cleanup();
    cleanups.clear();
    worker.terminate(); // Cancels range reads, decoder work and all outstanding packet queues.
    options.signal?.removeEventListener("abort", destroy);
    if (element && element.getAttribute("src") === objectUrl) {
      element.disableRemotePlayback = previousRemotePlayback;
      element.removeAttribute("src"); element.load();
    }
    if (objectUrl) URL.revokeObjectURL(objectUrl);
  };
  options.signal?.addEventListener("abort", destroy, { once: true });

  function event(target: EventTarget, name: string, timeout = 12000): Promise<void> {
    return new Promise((resolve, reject) => {
      const done = (error?: Error) => {
        clearTimeout(timer); target.removeEventListener(name, ready); target.removeEventListener("error", failed);
        cleanups.delete(cancel); error ? reject(error) : resolve();
      };
      const ready = () => done();
      const failed = () => done(new Error("Media buffer could not be decoded"));
      const cancel = () => done(new Error("Playback cancelled"));
      const timer = setTimeout(() => done(new Error(`Timed out waiting for ${name}`)), timeout);
      target.addEventListener(name, ready, { once: true });
      target.addEventListener("error", failed, { once: true });
      cleanups.add(cancel);
    });
  }
  const audioCodecs = ["mp4a.40.2", "ac-3", "ec-3", "opus", "flac", "mp3"]
    .filter((codec) => Mse.isTypeSupported(`audio/mp4; codecs="${codec}"`));
  let probe: RemuxProbe;
  try {
    probe = await new Promise<RemuxProbe>((resolve, reject) => {
      const finish = (value?: RemuxProbe, error?: string) => {
        clearTimeout(timer); cleanups.delete(cancel);
        value ? resolve(value) : reject(new Error(error ?? "Probe cancelled"));
      };
      const cancel = () => finish();
      const timer = setTimeout(() => finish(undefined, "Source probe timed out"), 15000);
      cleanups.add(cancel);
      worker.onerror = () => finish(undefined, "Browser conversion worker failed to load");
      worker.onmessage = ({ data }: MessageEvent<RemuxEvent>) => {
        if (data.type === "probe") finish(data.probe);
        if (data.type === "error") finish(undefined, data.message);
      };
      send({ type: "probe", url, headers: requestHeaders, audioCodecs, language: preferredAudioLang, expectDolbyVision: options.expectDolbyVision });
    });
    probe.videoPlayable = probe.videoPlayable && !!probe.videoCodec && Mse.isTypeSupported(`video/mp4; codecs="${probe.videoCodec}"`);
    if (!probe.videoPlayable && !probe.videoReason) probe.videoReason = `This browser cannot decode the selected video track (${probe.videoCodec ?? "unknown codec"}).`;
  } catch (error) {
    destroy();
    if (!options.signal?.aborted) options.onError?.(error instanceof Error ? error.message : "Source probe failed");
    return null;
  }

  const start = async (video: HTMLVideoElement, audioIndex = probe.chosenAudioIndex, startTime = 0) => {
    if (destroyed || element) throw new Error("Conversion is closed or already started");
    if (!probe.videoPlayable) throw new Error(probe.videoReason ?? "The browser cannot decode this video codec");
    if (probe.audioTracks.length && (audioIndex < 0 || !probe.audioTracks[audioIndex]?.browserPlayable)) {
      throw new Error("No compatible audio track; use server conversion or an external player");
    }
    element = video;
    previousRemotePlayback = video.disableRemotePlayback;
    const audio = probe.audioTracks[audioIndex];
    const codecs = [probe.videoCodec, audio ? audio.passthrough ? audio.codec : "mp4a.40.2" : undefined].filter(Boolean);
    const mime = `video/mp4; codecs="${codecs.join(",")}"`;
    if (!Mse.isTypeSupported(mime)) throw new Error("The browser cannot combine these video/audio codecs");
    source = new Mse();
    const opened = event(source, "sourceopen");
    objectUrl = URL.createObjectURL(source);
    video.disableRemotePlayback = true; // Required when Safari uses ManagedMediaSource.
    video.src = objectUrl;
    await opened;
    if (destroyed) return;
    buffer = source.addSourceBuffer(mime);
    if (Number.isFinite(probe.duration) && probe.duration > 0) source.duration = probe.duration;
    let pending = Promise.resolve();
    let target = Math.max(0, Math.min(startTime, Math.max(0, probe.duration - 0.1)));
    let positionPending = true;
    let failed = false;
    const fail = (error: unknown) => {
      if (destroyed || failed) return;
      failed = true;
      options.onError?.(error instanceof Error ? error.message : "Browser conversion failed");
      destroy();
    };
    const update = async (action: () => void) => {
      if (destroyed || !buffer) throw new Error("Playback cancelled");
      const completed = event(buffer, "updateend");
      try { action(); } catch (error) {
        // Abort the waiter too; never leave rejected promises or a stuck append queue.
        buffer.dispatchEvent(new Event("error"));
        await completed.catch(() => undefined);
        throw error;
      }
      await completed;
    };
    const bufferedAt = (time: number) => {
      for (let i = 0; i < video.buffered.length; i++) {
        if (time >= video.buffered.start(i) && time + 0.25 < video.buffered.end(i)) return true;
      }
      return false;
    };
    const begin = () => send({ type: "start", generation, time: target, audioIndex });
    const onSeek = () => {
      if (destroyed || (positionPending && Math.abs(video.currentTime - target) < 0.05) || bufferedAt(video.currentTime)) return;
      target = video.currentTime;
      const run = ++generation;
      positionPending = true;
      // Cancel the producer first, then serialize removal after any active append.
      send({ type: "start", generation: run, time: target, audioIndex });
      pending = pending.then(async () => {
        if (destroyed || run !== generation || !buffer) return;
        if (buffer.buffered.length) await update(() => buffer!.remove(0, Infinity));
        if (destroyed || run !== generation) return;
        positionPending = false;
      }).catch(fail);
    };
    video.addEventListener("seeking", onSeek);
    cleanups.add(() => video.removeEventListener("seeking", onSeek));
    worker.onerror = () => fail(new Error("Browser conversion worker stopped"));
    worker.onmessage = ({ data }: MessageEvent<RemuxEvent>) => {
      if (data.type === "probe") return;
      if (data.generation !== generation || destroyed) {
        if (data.type === "chunk") send({ type: "ack", id: data.id });
        return;
      }
      if (data.type === "error") { fail(new Error(data.message)); return; }
      if (data.type === "end") {
        pending = pending.then(() => { if (!destroyed && data.generation === generation && source?.readyState === "open") source.endOfStream(); }).catch(fail);
        return;
      }
      pending = pending.then(async () => {
        if (destroyed || data.generation !== generation) return;
        // Keep a short backward buffer. The producer only reads 25 seconds ahead.
        const before = Math.max(0, video.currentTime - 15);
        if (buffer!.buffered.length && buffer!.buffered.start(0) < before - 5) {
          await update(() => buffer!.remove(0, before));
        }
        if (destroyed || data.generation !== generation) return;
        try { await update(() => buffer!.appendBuffer(data.data)); }
        catch (error) {
          if (!(error instanceof DOMException) || error.name !== "QuotaExceededError" || video.currentTime < 2) throw error;
          await update(() => buffer!.remove(0, video.currentTime - 1));
          if (destroyed || data.generation !== generation) return;
          await update(() => buffer!.appendBuffer(data.data));
        }
        if (destroyed || data.generation !== generation) return;
        if (positionPending && video.readyState >= 1) {
          positionPending = false;
          if (target > 0) video.currentTime = target;
        }
      }).then(() => send({ type: "ack", id: data.id })).catch(fail);
    };
    clockTimer = setInterval(() => send({ type: "clock", time: positionPending ? target : video.currentTime }), 250);
    const ready = event(video, "loadeddata", 20000);
    begin();
    try { await ready; } catch (error) { fail(error); throw error; }
  };
  return { probe, start, destroy };
}

export function remuxWorthTrying(url: string, text: string) {
  return /^https?:\/\//i.test(url) && /\.mkv(?:[?#/\s]|$)|\bmatroska\b|\bremux\b/i.test(`${url} ${text}`);
}
