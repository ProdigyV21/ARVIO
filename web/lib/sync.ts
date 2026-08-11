import { mdblistClient } from "./mdblist";
import { simklClient } from "./simkl";
import { traktClient } from "./store";

export type SyncProvider = "trakt" | "mdblist" | "simkl" | "none";

export interface SyncMediaRef {
  mediaType: "movie" | "tv";
  tmdbId: number;
  season?: number | null;
  episode?: number | null;
}

/**
 * The read/write surface shared by Trakt, MDBList, and Simkl.
 */
export interface SyncClient {
  readonly isConnected: boolean;
  watchlist(): Promise<unknown[]>;
  playback(): Promise<unknown[]>;
  watched(type: "movies" | "shows"): Promise<unknown[]>;
  addToWatchlist(item: SyncMediaRef): Promise<void>;
  removeFromWatchlist(item: SyncMediaRef): Promise<void>;
  addToHistory(item: SyncMediaRef): Promise<void>;
  removeFromHistory(item: SyncMediaRef): Promise<void>;
  dismissFromContinueWatching(item: SyncMediaRef): Promise<void>;
  scrobble(action: "start" | "pause" | "stop", item: SyncMediaRef & { progress: number }): Promise<void>;
}

/** Which remote a profile is actively connected to. */
export function activeSyncProvider(): SyncProvider {
  if (mdblistClient.isConnected) return "mdblist";
  if (simklClient.isConnected) return "simkl";
  if (traktClient.isConnected) return "trakt";
  return "none";
}

/** The active provider client, or the Trakt client when none is connected. */
export function syncClient(): SyncClient {
  if (mdblistClient.isConnected) return mdblistClient as unknown as SyncClient;
  if (simklClient.isConnected) return simklClient as unknown as SyncClient;
  return traktClient as unknown as SyncClient;
}
