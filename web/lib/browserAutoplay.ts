import { isUncachedDebridStream } from './debrid';
import { playbackPlan } from './streamCompatibility';
import { compareForBrowser } from './sourceRank';
import { unreliableSourceCheck } from './sourceReliability';
import type { StreamSource } from './types';
/** Browser compatibility takes precedence over file size and resolution. */
export function browserAutoplayCandidates(streams: StreamSource[], excluded = new Set<string>()) {
  const seen = new Set<string>();
  const unreliable = unreliableSourceCheck();
  return streams.filter(s => {
    const url = s.originalUrl ?? s.url;
    if (!s.url || !url || seen.has(url) || excluded.has(url) || isUncachedDebridStream(s) || playbackPlan(s).route !== 'here') return false;
    seen.add(url); return true;
  })
    // Families that keep failing on this device go last (see sourceReliability).
    .map((stream) => ({ stream, unreliable: unreliable(stream) ? 1 : 0 }))
    .sort((a, b) => (a.unreliable - b.unreliable) || compareForBrowser(a.stream, b.stream))
    .map((entry) => entry.stream);
}
