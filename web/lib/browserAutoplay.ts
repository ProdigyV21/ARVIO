import { isUncachedDebridStream } from './debrid';
import { playbackPlan } from './streamCompatibility';
import { compareForBrowser } from './sourceRank';
import type { StreamSource } from './types';
/** Browser compatibility takes precedence over file size and resolution. */
export function browserAutoplayCandidates(streams: StreamSource[], excluded = new Set<string>()) {
  const seen = new Set<string>();
  return streams.filter(s => {
    const url = s.originalUrl ?? s.url;
    if (!s.url || !url || seen.has(url) || excluded.has(url) || isUncachedDebridStream(s) || playbackPlan(s).route !== 'here') return false;
    seen.add(url); return true;
  }).sort((a, b) => compareForBrowser(a, b));
}
