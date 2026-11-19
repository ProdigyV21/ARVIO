/**
 * Network priority for Home. Rows (the titles themselves) load first; card
 * decoration (title logo, service badges, runtime, IMDb rating) waits until no
 * row is loading, and then runs a few at a time. Before this, every mounted
 * card fired 3-4 requests at once and the lower rows sat as skeletons for
 * 30-50 s behind hundreds of decoration calls.
 */
export type Release = () => void;

function abortError() {
  const error = new Error("Aborted");
  error.name = "AbortError";
  return error;
}

export function createFetchPriority({ concurrency = 6, maxRowWaitMs = 2500, setTimer = setTimeout, clearTimer = clearTimeout }: {
  concurrency?: number;
  maxRowWaitMs?: number;
  setTimer?: (fn: () => void, ms: number) => unknown;
  clearTimer?: (handle: any) => void;
} = {}) {
  let rowLoads = 0;
  let running = 0;
  let starvationTimer: unknown = null;
  let rowGateOpen = true;
  type Waiter = { resolve: (release: Release) => void; reject: (error: unknown) => void; signal?: AbortSignal; onAbort?: () => void; urgent?: boolean };
  const queue: Waiter[] = [];

  const release = () => {
    running = Math.max(0, running - 1);
    pump();
  };
  const pump = () => {
    while (queue.length && running < concurrency && (rowLoads === 0 || rowGateOpen || queue[0].urgent)) {
      const waiter = queue.shift()!;
      if (waiter.signal?.aborted) continue;
      if (waiter.onAbort) waiter.signal?.removeEventListener("abort", waiter.onAbort);
      running += 1;
      let released = false;
      waiter.resolve(() => { if (!released) { released = true; release(); } });
    }
  };
  const armStarvationGuard = () => {
    // Never starve decoration forever behind a slow row (a hung addon, a
    // paging list): after maxRowWaitMs the gate opens until rows settle.
    if (starvationTimer !== null) return;
    starvationTimer = setTimer(() => {
      starvationTimer = null;
      rowGateOpen = true;
      pump();
    }, maxRowWaitMs);
  };

  return {
    /** Wrap a row load: decoration is held back while it runs. */
    trackRow<T>(work: Promise<T>): Promise<T> {
      rowLoads += 1;
      rowGateOpen = false;
      armStarvationGuard();
      const done = () => {
        rowLoads = Math.max(0, rowLoads - 1);
        if (rowLoads === 0) {
          rowGateOpen = true;
          if (starvationTimer !== null) { clearTimer(starvationTimer); starvationTimer = null; }
          pump();
        }
      };
      return work.then((value) => { done(); return value; }, (error) => { done(); throw error; });
    },
    /**
     * Wait for a decoration slot. Call the returned release when the request
     * ends. Urgent requests (the card under the pointer/focus) jump the queue.
     */
    slot(signal?: AbortSignal, urgent = false): Promise<Release> {
      if (signal?.aborted) return Promise.reject(abortError());
      return new Promise<Release>((resolve, reject) => {
        const waiter: Waiter = { resolve, reject, signal, urgent };
        if (signal) {
          waiter.onAbort = () => {
            const index = queue.indexOf(waiter);
            if (index >= 0) queue.splice(index, 1);
            reject(abortError());
          };
          signal.addEventListener("abort", waiter.onAbort, { once: true });
        }
        if (urgent) queue.unshift(waiter);
        else queue.push(waiter);
        pump();
      });
    },
    /** Run a decoration task in a slot. */
    async run<T>(task: () => Promise<T>, signal?: AbortSignal, urgent = false): Promise<T> {
      const done = await this.slot(signal, urgent);
      try { return await task(); } finally { done(); }
    },
    stats: () => ({ rowLoads, running, queued: queue.length })
  };
}

export const homeFetchPriority = createFetchPriority();
