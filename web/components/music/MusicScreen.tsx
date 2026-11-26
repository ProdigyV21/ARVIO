"use client";
import "./music.css";

import { ArrowLeft, Check, Disc3, Heart, ListMusic, ListPlus, Loader2, MoreHorizontal, Music2, Pencil, Play, Plus, Search, Server, Shuffle, Trash2, User, X, ArrowUp, ArrowDown, ListEnd, ListStart, Puzzle } from "lucide-react";
import { useCallback, useEffect, useMemo, useRef, useState } from "react";
import { useTranslation } from "@/lib/i18n";
import {
  activeServer, addMusicAddon, addToPlaylist, connectMusicServer, createPlaylist, deletePlaylist, disconnectMusicServer,
  hasMusicSources, isFavoriteAlbum, isFavoriteTrack, moveInPlaylist, musicSource, removeFromPlaylist, removeMusicAddon,
  recordMusicSearch, renamePlaylist, toggleFavoriteAlbum, toggleFavoriteTrack, useMusicLibrary
} from "@/lib/music/library";
import { addToQueue, currentTrack, playNext, playTracks, useMusicPlayer } from "@/lib/music/player";
import { arrangeHome, formatDuration, rankTracks, searchOrder, type SearchSection } from "@/lib/music/text";
import { blockedOnThisPage, CHARTS_KEY, SEPARATOR } from "@/lib/music/sources";
import { emptySearchResult, type MusicAlbum, type MusicArtist, type MusicSearchResult, type MusicServerType, type MusicShelf, type MusicTrack } from "@/lib/music/types";

type Tab = "home" | "mine";
type Detail =
  | { kind: "album"; album: MusicAlbum }
  | { kind: "artist"; artist: MusicArtist }
  | { kind: "playlist"; id: string }
  | { kind: "liked" }
  | { kind: "sources" };

type T = (text: string, values?: Record<string, string | number>) => string;

const SHELF_TITLES: Record<string, string> = { NEWEST: "Recently added", RECENT: "Recently played", FREQUENT: "Most played", RANDOM: "Discover" };
function shelfTitle(shelf: MusicShelf, translateUi: T) {
  if (shelf.title) return shelf.title;
  switch (shelf.kind) {
    case "NEWEST": return translateUi("Recently added");
    case "RECENT": return translateUi("Recently played");
    case "FREQUENT": return translateUi("Most played");
    case "RANDOM": return translateUi("Discover");
    default: return SHELF_TITLES[shelf.key] ?? "";
  }
}

function songCount(translateUi: T, count: number) {
  return count === 1 ? translateUi("1 song") : translateUi("{value0} songs", { value0: count });
}

function errorText(error: unknown) {
  return error instanceof Error ? error.message : String(error);
}

function Cover({ src, round = false, size }: { src: string | null | undefined; round?: boolean; size?: number }) {
  const [failed, setFailed] = useState(false);
  const usable = src && !failed && !blockedOnThisPage(src);
  return (
    <div className={`music-cover ${round ? "is-round" : ""}`} style={size ? { width: size, height: size } : undefined}>
      {usable ? <img src={src} alt="" loading="lazy" decoding="async" onError={() => setFailed(true)} /> : <Music2 aria-hidden="true" />}
    </div>
  );
}

function AlbumCard({ album, onOpen }: { album: MusicAlbum; onOpen: (album: MusicAlbum) => void }) {
  return (
    <button type="button" className="music-card" onClick={() => onOpen(album)}>
      <Cover src={album.coverUrl} />
      <span className="music-card-title">{album.name}</span>
      <span className="music-card-sub">{album.artist}{album.year ? ` · ${album.year}` : ""}</span>
    </button>
  );
}

function ArtistCard({ artist, onOpen }: { artist: MusicArtist; onOpen: (artist: MusicArtist) => void }) {
  return (
    <button type="button" className="music-card is-artist" onClick={() => onOpen(artist)}>
      <Cover src={artist.coverUrl} round />
      <span className="music-card-title">{artist.name}</span>
    </button>
  );
}

function Rail({ title, children }: { title: string; children: React.ReactNode }) {
  return (
    <section className="music-rail">
      <h2>{title}</h2>
      <div className="music-rail-track">{children}</div>
    </section>
  );
}

function TrackMenu({ track, playlistId, index, onClose, onOpenAlbum }: { track: MusicTrack; playlistId?: string; index: number; onClose: () => void; onOpenAlbum?: (track: MusicTrack) => void }) {
  const translateUi = useTranslation();
  const { collections } = useMusicLibrary();
  const [choosing, setChoosing] = useState(false);
  const [newName, setNewName] = useState("");
  const ref = useRef<HTMLDivElement>(null);
  useEffect(() => {
    const onDown = (event: PointerEvent) => { if (ref.current && !ref.current.contains(event.target as Node)) onClose(); };
    const onKey = (event: KeyboardEvent) => { if (event.key === "Escape") { event.stopPropagation(); onClose(); } };
    document.addEventListener("pointerdown", onDown);
    document.addEventListener("keydown", onKey, true);
    ref.current?.querySelector<HTMLButtonElement>("button")?.focus();
    return () => { document.removeEventListener("pointerdown", onDown); document.removeEventListener("keydown", onKey, true); };
  }, [onClose]);
  const playlist = playlistId ? collections.playlists.find((p) => p.id === playlistId) : null;
  return (
    <div className="music-menu" ref={ref} role="menu">
      {!choosing ? (
        <>
          <button type="button" role="menuitem" onClick={() => { playNext([track]); onClose(); }}><ListStart size={18} />{translateUi("Play next")}</button>
          <button type="button" role="menuitem" onClick={() => { addToQueue([track]); onClose(); }}><ListEnd size={18} />{translateUi("Add to queue")}</button>
          <button type="button" role="menuitem" onClick={() => { toggleFavoriteTrack(track); onClose(); }}>
            <Heart size={18} />{isFavoriteTrack(collections, track.id) ? translateUi("Remove from liked songs") : translateUi("Add to liked songs")}
          </button>
          <button type="button" role="menuitem" onClick={() => setChoosing(true)}><ListPlus size={18} />{translateUi("Add to playlist")}</button>
          {track.albumId && onOpenAlbum && !track.id.startsWith(CHARTS_KEY + SEPARATOR) && (
            <button type="button" role="menuitem" onClick={() => { onOpenAlbum(track); onClose(); }}><Disc3 size={18} />{translateUi("Go to album")}</button>
          )}
          {playlist && (
            <>
              <button type="button" role="menuitem" disabled={index === 0} onClick={() => { moveInPlaylist(playlist.id, index, index - 1); onClose(); }}><ArrowUp size={18} />{translateUi("Move up")}</button>
              <button type="button" role="menuitem" disabled={index >= playlist.tracks.length - 1} onClick={() => { moveInPlaylist(playlist.id, index, index + 1); onClose(); }}><ArrowDown size={18} />{translateUi("Move down")}</button>
              <button type="button" role="menuitem" className="is-danger" onClick={() => { removeFromPlaylist(playlist.id, index); onClose(); }}><Trash2 size={18} />{translateUi("Remove from this playlist")}</button>
            </>
          )}
        </>
      ) : (
        <>
          {collections.playlists.map((p) => (
            <button type="button" role="menuitem" key={p.id} onClick={() => { addToPlaylist(p.id, [track]); onClose(); }}><ListMusic size={18} />{p.name}</button>
          ))}
          <form className="music-menu-new" onSubmit={(event) => { event.preventDefault(); const id = createPlaylist(newName || translateUi("New playlist"), [track]); void id; onClose(); }}>
            <input value={newName} onChange={(event) => setNewName(event.target.value)} placeholder={translateUi("New playlist")} aria-label={translateUi("Playlist name")} />
            <button type="submit" aria-label={translateUi("Create")}><Plus size={18} /></button>
          </form>
        </>
      )}
    </div>
  );
}

function TrackList({ tracks, showCover = true, numbered = false, playlistId, onOpenAlbum }: { tracks: MusicTrack[]; showCover?: boolean; numbered?: boolean; playlistId?: string; onOpenAlbum?: (track: MusicTrack) => void }) {
  const translateUi = useTranslation();
  const { collections } = useMusicLibrary();
  const player = useMusicPlayer();
  const playing = currentTrack(player);
  const [menu, setMenu] = useState<number | null>(null);
  const closeMenu = useCallback(() => setMenu(null), []);
  return (
    <ol className="music-tracks">
      {tracks.map((track, index) => {
        const liked = isFavoriteTrack(collections, track.id);
        const current = playing?.id === track.id && playing.title === track.title;
        return (
          <li key={`${track.id}-${index}`} className={`music-track ${current ? "is-current" : ""}`}>
            <button type="button" className="music-track-main" onClick={() => playTracks(tracks, index)} aria-label={`${translateUi("Play")} ${track.title} · ${track.artist}`}>
              {showCover ? <Cover src={track.coverUrl} size={44} /> : <span className="music-track-no">{numbered ? track.trackNumber ?? index + 1 : index + 1}</span>}
              <span className="music-track-text">
                <span className="music-track-title">{track.title}</span>
                <span className="music-track-artist">{track.artist}{!showCover || !track.album ? "" : ` · ${track.album}`}</span>
              </span>
              <span className="music-track-time">{formatDuration(track.durationSec)}</span>
            </button>
            <button type="button" className={`music-icon-btn ${liked ? "is-on" : ""}`} onClick={() => toggleFavoriteTrack(track)} aria-pressed={liked} aria-label={liked ? translateUi("Remove from liked songs") : translateUi("Add to liked songs")}>
              <Heart size={18} fill={liked ? "currentColor" : "none"} />
            </button>
            <div className="music-menu-anchor">
              <button type="button" className="music-icon-btn" aria-haspopup="menu" aria-expanded={menu === index} onClick={() => setMenu(menu === index ? null : index)} aria-label={translateUi("More")}>
                <MoreHorizontal size={18} />
              </button>
              {menu === index && <TrackMenu track={track} index={index} playlistId={playlistId} onClose={closeMenu} onOpenAlbum={onOpenAlbum} />}
            </div>
          </li>
        );
      })}
    </ol>
  );
}

function useAsync<V>(load: () => Promise<V>, deps: unknown[]) {
  const [state, setState] = useState<{ value?: V; error?: string; loading: boolean }>({ loading: true });
  const [attempt, setAttempt] = useState(0);
  useEffect(() => {
    let alive = true;
    setState((s) => ({ value: s.value, loading: true }));
    load().then((value) => alive && setState({ value, loading: false }), (error) => alive && setState({ error: errorText(error), loading: false }));
    return () => { alive = false; };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [...deps, attempt]);
  return { ...state, retry: () => setAttempt((n) => n + 1) };
}

function Loading() {
  const translateUi = useTranslation();
  return <div className="music-state" role="status"><Loader2 className="music-spin" aria-hidden="true" /><span>{translateUi("Loading")}</span></div>;
}

function Failure({ message, retry }: { message?: string; retry: () => void }) {
  const translateUi = useTranslation();
  return (
    <div className="music-state">
      <p>{translateUi("Could not load your music.")}{message ? ` (${message})` : ""}</p>
      <button type="button" className="music-pill" onClick={retry}>{translateUi("Try again")}</button>
    </div>
  );
}

function DetailHeader({ cover, round, eyebrow, title, subtitle, tracks, actions }: { cover: string | null; round?: boolean; eyebrow: string; title: string; subtitle?: string; tracks: MusicTrack[]; actions?: React.ReactNode }) {
  const translateUi = useTranslation();
  return (
    <header className="music-detail-head">
      <Cover src={cover} round={round} />
      <div className="music-detail-copy">
        <span className="music-eyebrow">{eyebrow}</span>
        <h1>{title}</h1>
        {subtitle && <p>{subtitle}</p>}
        <div className="music-detail-actions">
          <button type="button" className="music-primary" disabled={!tracks.length} onClick={() => playTracks(tracks)}><Play size={20} fill="currentColor" />{translateUi("Play")}</button>
          <button type="button" className="music-pill" disabled={tracks.length < 2} onClick={() => playTracks(tracks, 0, { shuffle: true })}><Shuffle size={18} />{translateUi("Shuffle")}</button>
          {actions}
        </div>
      </div>
    </header>
  );
}

function AlbumView({ album, onOpenAlbum }: { album: MusicAlbum; onOpenAlbum: (track: MusicTrack) => void }) {
  const translateUi = useTranslation();
  const library = useMusicLibrary();
  const lib = musicSource(library);
  const result = useAsync(() => (lib ? lib.source.albumTracks(album.id) : Promise.resolve([])), [album.id, lib]);
  const liked = isFavoriteAlbum(library.collections, album.id);
  const tracks = result.value ?? [];
  const isPlaylist = album.id.startsWith(`${CHARTS_KEY}${SEPARATOR}`) || /(^|~)playlist:/.test(album.id);
  return (
    <>
      <DetailHeader
        cover={album.coverUrl}
        eyebrow={isPlaylist ? translateUi("Playlist") : translateUi("Album")}
        title={album.name}
        subtitle={[album.artist, album.year, tracks.length ? songCount(translateUi, tracks.length) : null].filter(Boolean).join(" · ")}
        tracks={tracks}
        actions={(
          <button type="button" className={`music-icon-btn is-large ${liked ? "is-on" : ""}`} aria-pressed={liked} onClick={() => toggleFavoriteAlbum(album)} aria-label={liked ? translateUi("Remove from favourites") : translateUi("Add to favourites")}>
            <Heart size={22} fill={liked ? "currentColor" : "none"} />
          </button>
        )}
      />
      {album.id.startsWith(`${CHARTS_KEY}${SEPARATOR}`) && <p className="music-credit">{translateUi("Charts and playlists: Deezer. Songs play from your own sources.")}</p>}
      {result.loading && !result.value ? <Loading /> : result.error ? <Failure message={result.error} retry={result.retry} /> : <TrackList tracks={tracks} showCover={isPlaylist} numbered={!isPlaylist} onOpenAlbum={onOpenAlbum} />}
    </>
  );
}

function ArtistView({ artist, onOpen }: { artist: MusicArtist; onOpen: (album: MusicAlbum) => void }) {
  const translateUi = useTranslation();
  const library = useMusicLibrary();
  const lib = musicSource(library);
  const result = useAsync(() => (lib ? lib.source.artistAlbums(artist.id) : Promise.resolve([])), [artist.id, lib]);
  return (
    <>
      <header className="music-detail-head">
        <Cover src={artist.coverUrl} round />
        <div className="music-detail-copy"><span className="music-eyebrow">{translateUi("Artist")}</span><h1>{artist.name}</h1></div>
      </header>
      {result.loading && !result.value ? <Loading /> : result.error ? <Failure message={result.error} retry={result.retry} /> : (
        <div className="music-grid">{(result.value ?? []).map((album) => <AlbumCard key={album.id} album={album} onOpen={onOpen} />)}</div>
      )}
    </>
  );
}

function PlaylistView({ id, onBack, onOpenAlbum }: { id: string; onBack: () => void; onOpenAlbum: (track: MusicTrack) => void }) {
  const translateUi = useTranslation();
  const { collections } = useMusicLibrary();
  const playlist = collections.playlists.find((p) => p.id === id);
  const [renaming, setRenaming] = useState(false);
  const [name, setName] = useState(playlist?.name ?? "");
  const [confirming, setConfirming] = useState(false);
  if (!playlist) return <div className="music-state"><p>{translateUi("This playlist no longer exists.")}</p></div>;
  return (
    <>
      <DetailHeader
        cover={playlist.tracks.find((t) => t.coverUrl)?.coverUrl ?? null}
        eyebrow={translateUi("Playlist")}
        title={playlist.name}
        subtitle={songCount(translateUi, playlist.tracks.length)}
        tracks={playlist.tracks}
        actions={(
          <>
            <button type="button" className="music-icon-btn is-large" onClick={() => { setName(playlist.name); setRenaming(true); }} aria-label={translateUi("Rename")}><Pencil size={20} /></button>
            <button type="button" className="music-icon-btn is-large" onClick={() => setConfirming(true)} aria-label={translateUi("Delete playlist")}><Trash2 size={20} /></button>
          </>
        )}
      />
      {renaming && (
        <form className="music-inline-form" onSubmit={(event) => { event.preventDefault(); renamePlaylist(playlist.id, name); setRenaming(false); }}>
          <input autoFocus value={name} onChange={(event) => setName(event.target.value)} aria-label={translateUi("Playlist name")} />
          <button type="submit" className="music-primary">{translateUi("Save")}</button>
          <button type="button" className="music-pill" onClick={() => setRenaming(false)}>{translateUi("Cancel")}</button>
        </form>
      )}
      {confirming && (
        <div className="music-inline-form" role="alertdialog" aria-label={translateUi("Delete playlist")}>
          <p>{translateUi("Delete \"{value0}\"? The songs themselves stay where they are.", { value0: playlist.name })}</p>
          <button type="button" className="music-primary is-danger" onClick={() => { deletePlaylist(playlist.id); onBack(); }}>{translateUi("Delete")}</button>
          <button type="button" className="music-pill" onClick={() => setConfirming(false)}>{translateUi("Cancel")}</button>
        </div>
      )}
      {playlist.tracks.length ? <TrackList tracks={playlist.tracks} playlistId={playlist.id} onOpenAlbum={onOpenAlbum} />
        : <p className="music-empty">{translateUi("This playlist is empty. Add songs with the ⋮ menu next to any song.")}</p>}
    </>
  );
}

function LikedView({ onOpenAlbum }: { onOpenAlbum: (track: MusicTrack) => void }) {
  const translateUi = useTranslation();
  const { collections } = useMusicLibrary();
  return (
    <>
      <DetailHeader cover={collections.favoriteTracks.find((t) => t.coverUrl)?.coverUrl ?? null} eyebrow={translateUi("Playlist")} title={translateUi("Liked songs")} subtitle={songCount(translateUi, collections.favoriteTracks.length)} tracks={collections.favoriteTracks} />
      {collections.favoriteTracks.length ? <TrackList tracks={collections.favoriteTracks} onOpenAlbum={onOpenAlbum} />
        : <p className="music-empty">{translateUi("No liked songs yet. Tap the heart in the player or use the ⋮ menu next to a song.")}</p>}
    </>
  );
}

function SourcesView() {
  const translateUi = useTranslation();
  const library = useMusicLibrary();
  const [type, setType] = useState<MusicServerType>("SUBSONIC");
  const [url, setUrl] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [busy, setBusy] = useState<"server" | "addon" | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [addonUrl, setAddonUrl] = useState("");
  const [addonError, setAddonError] = useState<string | null>(null);
  const insecurePage = typeof window !== "undefined" && window.location.protocol === "https:";
  const server = library.server;
  return (
    <div className="music-sources">
      <header className="music-sources-intro">
        <h1>{translateUi("Your own music in ARVIO")}</h1>
        <p>{translateUi("Play music from music addons you add, just like film addons, or from your own server (Navidrome and other Subsonic servers, or Jellyfin). ARVIO itself does not provide any music.")}</p>
      </header>

      <section className="music-panel">
        <h2><Server size={20} />{translateUi("Your music server")}</h2>
        {server ? (
          <div className="music-source-row">
            <span>{translateUi("Connected to {value0} as {value1}", { value0: server.baseUrl.replace(/^https?:\/\//, ""), value1: server.username })}</span>
            <button type="button" className="music-pill" onClick={disconnectMusicServer}>{translateUi("Disconnect server")}</button>
          </div>
        ) : (
          <form className="music-form" onSubmit={async (event) => {
            event.preventDefault();
            setBusy("server"); setError(null);
            try { await connectMusicServer(type, url, username, password); setPassword(""); }
            catch (e) { setError(errorText(e)); }
            finally { setBusy(null); }
          }}>
            {library.homeServer && <p className="music-hint">{translateUi("Music from your Jellyfin home server ({value0}) already plays here. Sign in below only to use another server.", { value0: library.homeServer.fromHomeServer ?? "" })}</p>}
            <div className="music-segmented" role="radiogroup" aria-label={translateUi("Server type")}>
              {(["SUBSONIC", "JELLYFIN"] as const).map((value) => (
                <button type="button" key={value} role="radio" aria-checked={type === value} className={type === value ? "is-on" : ""} onClick={() => setType(value)}>
                  {value === "SUBSONIC" ? translateUi("Navidrome / Subsonic") : translateUi("Jellyfin")}
                </button>
              ))}
            </div>
            <label>{translateUi("Server address")}<input value={url} onChange={(e) => setUrl(e.target.value)} placeholder="https://music.example.com" inputMode="url" autoComplete="url" required /></label>
            <label>{translateUi("Username")}<input value={username} onChange={(e) => setUsername(e.target.value)} autoComplete="username" required /></label>
            <label>{translateUi("Password")}<input type="password" value={password} onChange={(e) => setPassword(e.target.value)} autoComplete="current-password" /></label>
            {insecurePage && <p className="music-hint">{translateUi("Use the server's https address. Browsers block plain http:// servers (like 192.168.1.10:4533) on a secure page; a reverse proxy or Tailscale gives your server an https address.")}</p>}
            {error && <p className="music-error" role="alert">{translateUi("Could not connect: {value0}", { value0: error })}</p>}
            <button type="submit" className="music-primary" disabled={busy !== null}>{busy === "server" ? translateUi("Connecting…") : translateUi("Connect")}</button>
          </form>
        )}
      </section>

      <section className="music-panel">
        <h2><Puzzle size={20} />{translateUi("Music addons")}</h2>
        {library.addons.length > 0 && (
          <ul className="music-addon-list">
            {library.addons.map((addon) => (
              <li key={addon.baseUrl}>
                <span><strong>{addon.name}</strong><small>{addon.baseUrl.replace(/^https?:\/\//, "")}</small>{blockedOnThisPage(addon.baseUrl) && <small className="music-error">{translateUi("Uses http://, which this secure page cannot reach.")}</small>}</span>
                <button type="button" className="music-icon-btn" onClick={() => removeMusicAddon(addon.baseUrl)} aria-label={translateUi("Remove {value0}", { value0: addon.name })}><X size={18} /></button>
              </li>
            ))}
          </ul>
        )}
        <form className="music-form is-inline" onSubmit={async (event) => {
          event.preventDefault();
          setBusy("addon"); setAddonError(null);
          try { await addMusicAddon(addonUrl); setAddonUrl(""); }
          catch (e) { setAddonError(errorText(e)); }
          finally { setBusy(null); }
        }}>
          <label>{translateUi("Addon address")}<input value={addonUrl} onChange={(e) => setAddonUrl(e.target.value)} placeholder="https://…/manifest.json" inputMode="url" required /></label>
          <button type="submit" className="music-primary" disabled={busy !== null}>{busy === "addon" ? translateUi("Connecting…") : translateUi("Add addon")}</button>
        </form>
        {addonError && <p className="music-error" role="alert">{translateUi("Could not add the addon: {value0}", { value0: addonError })}</p>}
        <p className="music-hint">{translateUi("Addons and your liked songs and playlists sync with ARVIO on your phone and TV. A server login stays in this browser.")}</p>
      </section>
    </div>
  );
}

function HomeView({ onOpen }: { onOpen: (album: MusicAlbum) => void }) {
  const translateUi = useTranslation();
  const library = useMusicLibrary();
  const lib = musicSource(library);
  const result = useAsync(() => (lib ? lib.source.shelves().then((s) => arrangeHome(s)) : Promise.resolve([])), [lib]);
  const favorites = library.collections.favoriteAlbums;
  return (
    <>
      {favorites.length > 0 && (
        <Rail title={translateUi("Your favourites")}>{favorites.map((album) => <AlbumCard key={album.id} album={album} onOpen={onOpen} />)}</Rail>
      )}
      {result.loading && !result.value ? <Loading /> : result.error && !result.value?.length ? <Failure message={result.error} retry={result.retry} /> : (
        <>
          {(result.value ?? []).map((shelf) => (
            <Rail key={shelf.key} title={shelfTitle(shelf, translateUi)}>
              {shelf.albums.map((album) => <AlbumCard key={album.id} album={album} onOpen={onOpen} />)}
            </Rail>
          ))}
          {!result.value?.length && <p className="music-empty">{translateUi("No music found on this server yet.")}</p>}
          <p className="music-credit">{translateUi("Charts and playlists: Deezer. Songs play from your own sources.")}</p>
        </>
      )}
    </>
  );
}

function MineView({ onOpen, onDetail }: { onOpen: (album: MusicAlbum) => void; onDetail: (detail: Detail) => void }) {
  const translateUi = useTranslation();
  const { collections } = useMusicLibrary();
  const [name, setName] = useState("");
  const [creating, setCreating] = useState(false);
  return (
    <>
      <div className="music-mine-grid">
        <button type="button" className="music-tile is-liked" onClick={() => onDetail({ kind: "liked" })}>
          <Heart size={28} fill="currentColor" />
          <span className="music-card-title">{translateUi("Liked songs")}</span>
          <span className="music-card-sub">{songCount(translateUi, collections.favoriteTracks.length)}</span>
        </button>
        {collections.playlists.map((playlist) => (
          <button type="button" key={playlist.id} className="music-tile" onClick={() => onDetail({ kind: "playlist", id: playlist.id })}>
            <Cover src={playlist.tracks.find((t) => t.coverUrl)?.coverUrl} size={56} />
            <span className="music-card-title">{playlist.name}</span>
            <span className="music-card-sub">{songCount(translateUi, playlist.tracks.length)}</span>
          </button>
        ))}
        {creating ? (
          <form className="music-tile is-new" onSubmit={(event) => { event.preventDefault(); const id = createPlaylist(name || translateUi("New playlist")); setCreating(false); setName(""); onDetail({ kind: "playlist", id }); }}>
            <input autoFocus value={name} onChange={(e) => setName(e.target.value)} placeholder={translateUi("Playlist name")} aria-label={translateUi("Playlist name")} />
            <span className="music-tile-actions">
              <button type="submit" className="music-icon-btn" aria-label={translateUi("Create")}><Check size={18} /></button>
              <button type="button" className="music-icon-btn" onClick={() => setCreating(false)} aria-label={translateUi("Cancel")}><X size={18} /></button>
            </span>
          </form>
        ) : (
          <button type="button" className="music-tile is-new" onClick={() => setCreating(true)}><Plus size={28} /><span className="music-card-title">{translateUi("New playlist")}</span></button>
        )}
      </div>
      {collections.favoriteAlbums.length > 0 && (
        <Rail title={translateUi("Favourite albums & playlists")}>{collections.favoriteAlbums.map((album) => <AlbumCard key={album.id} album={album} onOpen={onOpen} />)}</Rail>
      )}
      {collections.favoriteTracks.length === 0 && collections.favoriteAlbums.length === 0 && collections.playlists.length === 0 && (
        <p className="music-empty">{translateUi("Tap the heart on a song or album to keep it here, or make your own playlist.")}</p>
      )}
    </>
  );
}

function SearchView({ query, onOpen, onArtist, onOpenAlbum }: { query: string; onOpen: (album: MusicAlbum) => void; onArtist: (artist: MusicArtist) => void; onOpenAlbum: (track: MusicTrack) => void }) {
  const translateUi = useTranslation();
  const library = useMusicLibrary();
  const lib = musicSource(library);
  const [debounced, setDebounced] = useState(query);
  useEffect(() => { const timer = window.setTimeout(() => setDebounced(query.trim()), 350); return () => window.clearTimeout(timer); }, [query]);
  const result = useAsync<MusicSearchResult>(() => (lib && debounced ? lib.source.search(debounced) : Promise.resolve(emptySearchResult())), [debounced, lib]);
  const value = result.value ?? emptySearchResult();
  const tracks = useMemo(() => rankTracks(debounced, value.tracks), [debounced, value.tracks]);
  const order: SearchSection[] = searchOrder(debounced, value);
  const empty = !value.tracks.length && !value.albums.length && !value.artists.length && !value.playlists.length;
  // A search the user stays on (not one they are still typing) feeds the recommendations.
  useEffect(() => {
    if (empty || result.loading || !debounced) return;
    const timer = window.setTimeout(() => recordMusicSearch(debounced), 2500);
    return () => window.clearTimeout(timer);
  }, [debounced, empty, result.loading]);
  if (result.loading && empty) return <Loading />;
  if (empty) return <p className="music-empty">{translateUi("Nothing found for \"{value0}\"", { value0: debounced })}</p>;
  return (
    <>
      {order.map((section) => {
        if (section === "tracks" && tracks.length) return <section key={section} className="music-section"><h2>{translateUi("Songs")}</h2><TrackList tracks={tracks.slice(0, 30)} onOpenAlbum={onOpenAlbum} /></section>;
        if (section === "albums" && value.albums.length) return <Rail key={section} title={translateUi("Albums")}>{value.albums.map((a) => <AlbumCard key={a.id} album={a} onOpen={onOpen} />)}</Rail>;
        if (section === "artists" && value.artists.length) return <Rail key={section} title={translateUi("Artists")}>{value.artists.map((a) => <ArtistCard key={a.id} artist={a} onOpen={onArtist} />)}</Rail>;
        if (section === "playlists" && value.playlists.length) return <Rail key={section} title={translateUi("Playlists")}>{value.playlists.map((a) => <AlbumCard key={a.id} album={a} onOpen={onOpen} />)}</Rail>;
        return null;
      })}
    </>
  );
}

export function MusicScreen() {
  const translateUi = useTranslation();
  const library = useMusicLibrary();
  const [tab, setTab] = useState<Tab>("home");
  const [stack, setStack] = useState<Detail[]>([]);
  const [query, setQuery] = useState("");
  const detail = stack[stack.length - 1] ?? null;
  const ready = hasMusicSources(library);
  const contentRef = useRef<HTMLDivElement>(null);

  const push = useCallback((next: Detail) => { setStack((s) => [...s, next]); window.scrollTo({ top: 0 }); }, []);
  const back = useCallback(() => setStack((s) => s.slice(0, -1)), []);
  const openAlbum = useCallback((album: MusicAlbum) => push({ kind: "album", album }), [push]);
  const openArtist = useCallback((artist: MusicArtist) => push({ kind: "artist", artist }), [push]);
  const openTrackAlbum = useCallback((track: MusicTrack) => {
    if (track.albumId) push({ kind: "album", album: { id: track.albumId, name: track.album, artist: track.artist, artistId: null, coverUrl: track.coverUrl } });
  }, [push]);

  // The TV remote's / browser's Back closes an open album before leaving the tab.
  useEffect(() => {
    if (!stack.length) return undefined;
    const onKey = (event: KeyboardEvent) => {
      if ((event.key === "Escape" || event.key === "GoBack" || event.key === "BrowserBack") && !event.defaultPrevented) {
        const target = event.target as HTMLElement | null;
        if (target && /^(INPUT|TEXTAREA)$/.test(target.tagName)) return;
        event.preventDefault();
        event.stopPropagation();
        back();
      }
    };
    window.addEventListener("keydown", onKey, true);
    return () => window.removeEventListener("keydown", onKey, true);
  }, [stack.length, back]);

  if (!library.profileKey) return <div className="screen music-screen"><Loading /></div>;
  const showSources = !ready || detail?.kind === "sources";
  const searching = !detail && query.trim().length > 0 && ready;

  return (
    <div className="screen music-screen" ref={contentRef}>
      <div className="music-toolbar">
        {detail ? (
          <button type="button" className="music-pill" onClick={back}><ArrowLeft size={18} />{translateUi("Back")}</button>
        ) : (
          <h1 className="music-title">{translateUi("Music")}</h1>
        )}
        {ready && !detail && (
          <>
            <label className="music-search">
              <Search size={18} aria-hidden="true" />
              <input type="search" value={query} onChange={(event) => setQuery(event.target.value)} placeholder={translateUi("Search artists, albums, songs")} aria-label={translateUi("Search artists, albums, songs")} />
              {query && <button type="button" className="music-icon-btn" onClick={() => setQuery("")} aria-label={translateUi("Clear")}><X size={16} /></button>}
            </label>
            <div className="music-tabs" role="tablist">
              <button type="button" role="tab" aria-selected={tab === "home" && !searching} className={tab === "home" && !searching ? "is-on" : ""} onClick={() => { setQuery(""); setTab("home"); }}>{translateUi("For you")}</button>
              <button type="button" role="tab" aria-selected={tab === "mine" && !searching} className={tab === "mine" && !searching ? "is-on" : ""} onClick={() => { setQuery(""); setTab("mine"); }}>{translateUi("My music")}</button>
            </div>
            <button type="button" className="music-pill" onClick={() => push({ kind: "sources" })}><Plus size={18} />{translateUi("Add source")}</button>
          </>
        )}
      </div>

      <div className="music-body">
        {showSources ? <SourcesView />
          : detail?.kind === "album" ? <AlbumView key={detail.album.id} album={detail.album} onOpenAlbum={openTrackAlbum} />
            : detail?.kind === "artist" ? <ArtistView key={detail.artist.id} artist={detail.artist} onOpen={openAlbum} />
              : detail?.kind === "playlist" ? <PlaylistView key={detail.id} id={detail.id} onBack={back} onOpenAlbum={openTrackAlbum} />
                : detail?.kind === "liked" ? <LikedView onOpenAlbum={openTrackAlbum} />
                  : searching ? <SearchView query={query} onOpen={openAlbum} onArtist={openArtist} onOpenAlbum={openTrackAlbum} />
                    : tab === "mine" ? <MineView onOpen={openAlbum} onDetail={push} />
                      : <HomeView onOpen={openAlbum} />}
        {ready && !detail && !searching && activeServer(library)?.fromHomeServer && !library.server && tab === "home" && (
          <p className="music-credit"><User size={14} /> {translateUi("Music from your Jellyfin home server ({value0}).", { value0: activeServer(library)?.fromHomeServer ?? "" })}</p>
        )}
      </div>
    </div>
  );
}
