export type LogoEntry = [id: string, country: string, names: string[], urls: string[]];

const countries: Record<string, string> = { UK: "GB", GB: "GB", USA: "US", US: "US", NL: "NL", NLD: "NL", DE: "DE", GER: "DE", FR: "FR", ES: "ES", IT: "IT", CA: "CA", AU: "AU", PT: "PT", BR: "BR", BE: "BE", CH: "CH", AT: "AT", IE: "IE", DK: "DK", DNK: "DK", SE: "SE", NO: "NO", FI: "FI", PL: "PL", RO: "RO", TR: "TR", IN: "IN", AR: "AR" };
const nameKey = (name: string) => name.trim()
  .replace(/(?:[\s|_-]+(?:SD|HD|FHD|UHD|4K|8K|HEVC|H265|H264|RAW|BACKUP|1080P|720P|2160P))+$/i, "")
  .normalize("NFKD").replace(/\p{M}+/gu, "").replace(/[^\p{L}\p{N}+]/gu, "").toLowerCase();

/** No fuzzy matches: retain channel numbers, time shifts and regional names. */
export function channelLogoIndex(entries: LogoEntry[]) {
  const ids = new Map(entries.map(entry => [entry[0].toLowerCase(), entry]));
  const names = new Map<string, LogoEntry[]>();
  for (const entry of entries) for (const key of new Set(entry[2].map(nameKey))) {
    if (key) names.set(key, [...(names.get(key) ?? []), entry]);
  }
  return (epgId: string | undefined, name: string): string[] => {
    const exact = epgId && ids.get(epgId.trim().toLowerCase());
    if (exact) return exact[3];
    // A provider qualifier is not part of the station name (e.g. UK-NOWTV).
    const cleaned = name.trim().replace(/^(?:4K|8K|UHD|FHD|HD)\s*[|:]\s*/i, "");
    const prefix = /^([A-Za-z]{2,3})(?:-[A-Za-z0-9]+)?\s*[|:]\s*/.exec(cleaned);
    const country = prefix && countries[prefix[1].toUpperCase()];
    const title = country ? cleaned.slice(prefix![0].length) : cleaned;
    const matches = (names.get(nameKey(title)) ?? []).filter(entry => !country || (countries[entry[1].toUpperCase()] ?? entry[1].toUpperCase()) === country);
    return matches.length === 1 ? matches[0][3] : [];
  };
}

let directory: Promise<ReturnType<typeof channelLogoIndex>> | undefined;
let retryAfter = 0;
export async function channelLogoCandidates(epgId: string | undefined, name: string): Promise<string[]> {
  if (!directory && Date.now() < retryAfter) return [];
  directory ??= fetch("/data/channel-logos.json", { cache: "force-cache" })
    .then(response => { if (!response.ok) throw new Error("Logo directory unavailable"); return response.json(); })
    .then(data => channelLogoIndex(data.entries));
  try { return (await directory)(epgId, name); }
  catch { directory = undefined; retryAfter = Date.now() + 600_000; return []; }
}

const failures = new Map<string, number>();
export function channelLogoFailed(url: string): boolean {
  const at = failures.get(url);
  if (at === undefined) return false;
  if (Date.now() - at < 600_000) return true;
  failures.delete(url);
  return false;
}
export function failChannelLogo(url: string) {
  failures.set(url, Date.now());
  while (failures.size > 1024) failures.delete(failures.keys().next().value!);
}

export function providerLogoUrl(raw?: string): string | undefined {
  if (!raw?.trim()) return undefined;
  let url = raw.trim();
  if (url.startsWith("//")) url = `https:${url}`;
  if (!/^https?:\/\//i.test(url)) {
    try { url = atob(url.replace(/-/g, "+").replace(/_/g, "/")).trim(); } catch { return undefined; }
  }
  return /^https?:\/\//i.test(url) ? url : undefined;
}
