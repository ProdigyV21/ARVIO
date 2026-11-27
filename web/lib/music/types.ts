// Music tab models. The field names match the Android app's (MusicModels.kt), because liked songs,
// favourite albums, playlists and music addons sync between both through the cloud profile settings.

export interface MusicArtist {
  id: string;
  name: string;
  coverUrl: string | null;
  albumCount?: number | null;
}

/** An album, or a playlist (an addon's, a server's or a chart's); both open the same way. */
export interface MusicAlbum {
  id: string;
  name: string;
  artist: string;
  artistId: string | null;
  coverUrl: string | null;
  year?: number | null;
  trackCount?: number | null;
}

export interface MusicTrack {
  id: string;
  title: string;
  artist: string;
  album: string;
  albumId: string | null;
  durationSec: number;
  coverUrl: string | null;
  /** A direct URL, or a placeholder (arvio-music-match://, arvio-music-addon://) resolved when it plays. */
  streamUrl: string;
  trackNumber?: number | null;
}

export interface MusicSearchResult {
  artists: MusicArtist[];
  albums: MusicAlbum[];
  tracks: MusicTrack[];
  playlists: MusicAlbum[];
}

export const emptySearchResult = (): MusicSearchResult => ({ artists: [], albums: [], tracks: [], playlists: [] });

export type AlbumShelf = "NEWEST" | "RECENT" | "FREQUENT" | "RANDOM" | "ALPHABETICAL";

/** One row on the Music home: a server's standard list ([kind]) or a titled catalog/chart row. */
export interface MusicShelf {
  key: string;
  albums: MusicAlbum[];
  kind?: AlbumShelf | null;
  title?: string | null;
}

export interface MusicAddonCatalog {
  id: string;
  name: string;
  searchOnly: boolean;
  searchable: boolean;
  type?: string | null;
}

/** A music addon added by its manifest URL (Stremio-style with music types, or the Eclipse format). */
export interface MusicAddon {
  baseUrl: string;
  id: string;
  name: string;
  catalogs: MusicAddonCatalog[];
  protocol?: string | null;
}

export type MusicServerType = "SUBSONIC" | "JELLYFIN";

/** A login to the user's own music server. It stays in this browser, as on Android it stays on the device. */
export interface MusicServerConfig {
  type: MusicServerType;
  baseUrl: string;
  username: string;
  token: string;
  salt?: string;
  userId?: string;
  deviceId?: string;
  /** Set when the server comes from the synced home servers instead of a music login. */
  fromHomeServer?: string;
}

export interface MusicPlaylist {
  id: string;
  name: string;
  tracks: MusicTrack[];
  origin?: string | null;
}

export interface MusicCollections {
  favoriteTracks: MusicTrack[];
  favoriteAlbums: MusicAlbum[];
  playlists: MusicPlaylist[];
  /** The Android app's pinned albums, playlists and artists; kept as read so a change made here does not drop them. */
  pins?: unknown[];
}

export const emptyCollections = (): MusicCollections => ({ favoriteTracks: [], favoriteAlbums: [], playlists: [] });

export class MusicError extends Error {}

/** One music source, whatever API it speaks. */
export interface MusicSource {
  shelves(): Promise<MusicShelf[]>;
  artists(): Promise<MusicArtist[]>;
  artistAlbums(artistId: string): Promise<MusicAlbum[]>;
  albumTracks(albumId: string): Promise<MusicTrack[]>;
  search(query: string): Promise<MusicSearchResult>;
  reportPlayed(trackId: string): Promise<void>;
}
