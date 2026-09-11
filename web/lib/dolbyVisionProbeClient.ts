import type { DolbyVisionProbeOptions, DolbyVisionProbeResult } from './dolbyVision';

/** Keep parser CPU work off the caller, including when called from the remux worker. */
export function runDolbyVisionProbe(url: string, options: DolbyVisionProbeOptions): Promise<DolbyVisionProbeResult> {
  if (options.signal?.aborted) return Promise.resolve({ status: 'unknown', reason: 'aborted' });
  const timeoutMs = options.timeoutMs ?? 10_000;
  if (!Number.isFinite(timeoutMs) || timeoutMs <= 0) {
    return Promise.resolve({ status: 'unknown', reason: 'invalid-options' });
  }
  return new Promise((resolve) => {
    let worker: Worker | undefined;
    let timer: ReturnType<typeof setTimeout> | undefined;
    let finished = false;
    const finish = (result: DolbyVisionProbeResult) => {
      if (finished) return;
      finished = true;
      clearTimeout(timer);
      options.signal?.removeEventListener('abort', abort);
      worker?.terminate();
      resolve(result);
    };
    const abort = () => finish({ status: 'unknown', reason: 'aborted' });
    options.signal?.addEventListener('abort', abort, { once: true });
    timer = setTimeout(() => finish({ status: 'unknown', reason: 'timeout' }), Math.min(timeoutMs, 10_000));
    try {
      worker = new Worker(new URL('./dolbyVisionProbe.worker.ts', import.meta.url), { type: 'module' });
      worker.onmessage = (event: MessageEvent<DolbyVisionProbeResult>) => finish(event.data);
      worker.onerror = (event) => { event.preventDefault(); finish({ status: 'unknown', reason: 'parser-failed' }); };
      worker.onmessageerror = () => finish({ status: 'unknown', reason: 'parser-failed' });
      const { signal: _signal, ...serializable } = options;
      worker.postMessage({ url, options: serializable });
    } catch {
      finish({ status: 'unknown', reason: 'worker-unavailable' });
    }
  });
}
