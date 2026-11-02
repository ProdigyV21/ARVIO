import type { IptvPlaylistEntry } from "./types";

export const MAX_IPTV_PLAYLISTS = 10;

/** Keep every provider when older Android versions assigned the same ID twice. */
export function repairIptvPlaylistIds(playlists: IptvPlaylistEntry[]): IptvPlaylistEntry[] {
  const reserved = new Set(playlists.map(entry => entry.id.trim()));
  const used = new Set<string>();
  return playlists.map((entry, index) => {
    let id = entry.id.trim();
    if (!id || used.has(id)) {
      const base = `list_recovered_${index + 1}`;
      id = base;
      let suffix = 1;
      while (reserved.has(id)) id = `${base}_${suffix++}`;
      reserved.add(id);
    }
    used.add(id);
    return id === entry.id ? entry : { ...entry, id };
  });
}
