// Seek previews (thumbnails above the scrubber) for home-server titles that
// already have them: Jellyfin trickplay tiles and Plex BIF index files. They
// load straight from the person's own server, never through ARVIO's servers,
// and a server without them (or without HTTPS/CORS) simply shows no preview.
import { ensureHomeServerSession } from "./homeserver";
import type { HomeServerConfig, StreamSource } from "./types";

export type SeekPreview =
  | { kind: "tile"; url: string; column: number; row: number; columns: number; rows: number }
  | { kind: "frame"; url: string };
export type Trickplay = { at: (seconds: number) => SeekPreview | null; destroy: () => void };

/** Jellyfin's default trickplay layout: 320 px wide thumbnails every 10 s, 10x10 per tile. */
const JELLYFIN_WIDTH = 320;
const JELLYFIN_INTERVAL = 10;
const MAX_FRAME_URLS = 120;

function httpsBase(server: HomeServerConfig) {
  try {
    const base = new URL(server.url.replace(/\/+$/, "") + "/");
    // An http:// server cannot serve images to an https page (mixed content).
    return base.protocol === "https:" && !base.username && !base.password ? base : null;
  } catch { return null; }
}

function loadImage(url: string, signal: AbortSignal) {
  return new Promise<HTMLImageElement>((resolve, reject) => {
    const image = new Image();
    image.decoding = "async";
    image.referrerPolicy = "no-referrer";
    image.onload = () => resolve(image);
    image.onerror = () => reject(new Error("No trickplay image"));
    signal.addEventListener("abort", () => { image.src = ""; reject(new Error("Cancelled")); }, { once: true });
    image.src = url;
  });
}

/** Tile and position within it for a time, given the grid and the interval. */
export function tilePosition(seconds: number, interval: number, columns: number, rows: number) {
  const frame = Math.max(0, Math.floor(seconds / interval));
  const perTile = columns * rows;
  const within = frame % perTile;
  return { tile: Math.floor(frame / perTile), column: within % columns, row: Math.floor(within / columns) };
}

async function jellyfinTrickplay(stream: StreamSource, server: HomeServerConfig, aspect: number, signal: AbortSignal): Promise<Trickplay | null> {
  const base = httpsBase(server);
  const itemId = stream.homeServer?.itemId ?? stream.playbackSession?.itemId;
  if (!base || !itemId) return null;
  const session = await ensureHomeServerSession(server, signal).catch(() => null);
  if (!session) return null;
  const mediaSourceId = stream.homeServer?.mediaSourceId ?? stream.playbackSession?.mediaSourceId;
  const tileUrl = (index: number) => {
    const url = new URL(`Videos/${encodeURIComponent(itemId)}/Trickplay/${JELLYFIN_WIDTH}/${index}.jpg`, base);
    if (mediaSourceId) url.searchParams.set("MediaSourceId", mediaSourceId);
    url.searchParams.set("api_key", session.token);
    return url.toString();
  };
  const first = await loadImage(tileUrl(0), signal).catch(() => null);
  if (!first || !first.naturalWidth) return null;
  const columns = Math.max(1, Math.round(first.naturalWidth / JELLYFIN_WIDTH));
  const thumbHeight = JELLYFIN_WIDTH / (aspect > 0 ? aspect : 16 / 9);
  const rows = Math.max(1, Math.round(first.naturalHeight / thumbHeight));
  return {
    at: (seconds) => {
      const position = tilePosition(seconds, JELLYFIN_INTERVAL, columns, rows);
      return { kind: "tile", url: tileUrl(position.tile), column: position.column, row: position.row, columns, rows };
    },
    destroy: () => undefined
  };
}

/** Parses a Roku BIF index: frame offsets into one file of JPEGs. */
export function parseBif(buffer: ArrayBuffer): { interval: number; frames: { start: number; end: number }[] } | null {
  const view = new DataView(buffer);
  const magic = [0x89, 0x42, 0x49, 0x46, 0x0d, 0x0a, 0x1a, 0x0a];
  if (buffer.byteLength < 64 || magic.some((byte, index) => view.getUint8(index) !== byte)) return null;
  const count = view.getUint32(12, true);
  const intervalMs = view.getUint32(16, true) || 1000;
  if (!count || 64 + (count + 1) * 8 > buffer.byteLength) return null;
  const frames: { start: number; end: number }[] = [];
  for (let index = 0; index < count; index += 1) {
    const start = view.getUint32(64 + index * 8 + 4, true);
    const end = view.getUint32(64 + (index + 1) * 8 + 4, true);
    if (start >= end || end > buffer.byteLength) return null;
    frames.push({ start, end });
  }
  return { interval: intervalMs / 1000, frames };
}

async function plexTrickplay(stream: StreamSource, server: HomeServerConfig, signal: AbortSignal): Promise<Trickplay | null> {
  const base = httpsBase(server);
  const partId = (stream.originalUrl ?? stream.url ?? "").match(/\/library\/parts\/(\d+)\//)?.[1];
  if (!base || !partId || !server.token) return null;
  const url = new URL(`library/parts/${partId}/indexes/sd`, base);
  url.searchParams.set("X-Plex-Token", server.token);
  // Plex Media Server answers browsers directly (Plex Web relies on it); no relay.
  const response = await fetch(url.toString(), { signal, credentials: "omit", referrerPolicy: "no-referrer" }).catch(() => null);
  if (!response?.ok) return null;
  const buffer = await response.arrayBuffer().catch(() => null);
  const bif = buffer ? parseBif(buffer) : null;
  if (!buffer || !bif) return null;
  const urls = new Map<number, string>();
  return {
    at: (seconds) => {
      const index = Math.min(bif.frames.length - 1, Math.max(0, Math.floor(seconds / bif.interval)));
      let frameUrl = urls.get(index);
      if (!frameUrl) {
        if (urls.size >= MAX_FRAME_URLS) {
          const [oldest, oldUrl] = urls.entries().next().value!;
          URL.revokeObjectURL(oldUrl);
          urls.delete(oldest);
        }
        const frame = bif.frames[index];
        frameUrl = URL.createObjectURL(new Blob([buffer.slice(frame.start, frame.end)], { type: "image/jpeg" }));
        urls.set(index, frameUrl);
      }
      return { kind: "frame", url: frameUrl };
    },
    destroy: () => { urls.forEach((value) => URL.revokeObjectURL(value)); urls.clear(); }
  };
}

/** Seek previews for a home-server source, or null when the server has none. */
export async function loadTrickplay(stream: StreamSource, servers: HomeServerConfig[], aspect: number, signal: AbortSignal): Promise<Trickplay | null> {
  const id = stream.homeServer?.serverId ?? stream.playbackSession?.serverId;
  if (!id) return null;
  const server = servers.find((candidate) => (candidate.id === id || candidate.serverId === id) && candidate.enabled);
  if (!server) return null;
  if (server.type === "jellyfin") return jellyfinTrickplay(stream, server, aspect, signal);
  if (server.type === "plex") return plexTrickplay(stream, server, signal);
  return null;
}
