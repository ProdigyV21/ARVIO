import fixtures from "../../../app/src/androidTest/assets/library/titles.json";
import { app as libraryApp } from "../library-ui/stubs";
export * from "../library-ui/stubs";
const params = new URLSearchParams(location.search);
const onlyArvio = params.has("arvio");
const fixtureMonth = params.get("month") || "2026-10";
const metadataReady = params.has("loading") ? new Promise<void>(resolve => { (window as any).finishCalendarLoading = resolve; }) : Promise.resolve();
const trackerReady = params.has("slowtracker") ? new Promise<void>(resolve => { (window as any).finishCalendarTracker = resolve; }) : Promise.resolve();
const timesReady = params.has("slowtime") ? new Promise<void>(resolve => { (window as any).finishCalendarTimes = resolve; }) : Promise.resolve();
const fixtureItems = fixtures.map(row => ({ id: row.id, title: row.title, mediaType: row.mediaType === "MOVIE" ? "movie" : "tv", image: `/fixtures/${row.poster}`, backdrop: `/fixtures/${row.backdrop}` }));
export const app = { ...libraryApp, watchlist: fixtureItems.slice(0, 3), traktConnected: !onlyArvio, simklConnected: !onlyArvio, mdblistConnected: !onlyArvio };
export const useApp = () => app;
export const authClient = {};
const rows = (items: typeof fixtureItems) => items.map(item => ({ type: item.mediaType === "tv" ? "show" : "movie", [item.mediaType === "tv" ? "show" : "movie"]: { title: item.title, ids: { tmdb: item.id } } }));
export const pullCloudWatchlist = async () => params.has("empty") ? [] : fixtureItems.slice(0, 3);
export const applyPendingWatchlist = (_auth: unknown, _profile: unknown, items: unknown) => items;
const episodeDays = (id: number) => id === 95396 ? [6, 13, 16, 20, 27] : id === 126308 ? [1, 8, 15, 16, 22, 29] : id === 100088 ? [4, 11, 16, 18, 25] : [2, 9, 16, 23, 30];
export const traktClient = { watchlist: async () => { await trackerReady; return rows(fixtureItems.slice(1, 7)); }, calendarSeason: async (id: number, season: number) => { await timesReady; return episodeDays(id).map((day, index) => ({ season, number: index + 1, first_aired: `${fixtureMonth}-${String(day).padStart(2, "0")}T19:00:00Z` })); } };
export const simklClient = { watchlist: async () => rows(fixtureItems.slice(5, 9)) };
export const mdblistClient = { watchlist: async () => { if (params.has("partial")) throw Error("Offline fixture"); return rows(fixtureItems.slice(8)); } };
export const traktItemToMedia = (row: any) => { const item = row.show || row.movie; return { id: item.ids.tmdb, title: item.title, mediaType: row.show ? "tv" : "movie" }; };
export const mapTmdbItem = (row: any, mediaType: string) => ({ id: row.id, title: row.title || row.name, mediaType, image: row.poster_path, backdrop: row.backdrop_path });
export const tmdb = async (path: string) => {
  await metadataReady;
  const [mediaType, rawId, _season, number] = path.split("/");
  const row = fixtureItems.find(item => item.id === Number(rawId));
  if (!row) throw Error(`Missing fixture: ${path}`);
  const index = fixtureItems.indexOf(row);
  if (number) return { episodes: episodeDays(row.id).map((day, index) => ({ season_number: 1, episode_number: index + 1, air_date: `${fixtureMonth}-${String(day).padStart(2, "0")}`, name: `Episode ${index + 1}` })) };
  return { id: row.id, title: row.title, name: row.title, poster_path: row.image, backdrop_path: row.backdrop,
    ...(mediaType === "movie" ? { release_dates: { results: [{ iso_3166_1: "US", release_dates: [{ type: index % 2 ? 4 : 3, release_date: `${fixtureMonth}-${index > 8 ? "23" : "16"}T00:00:00Z` }] }] } }
      : { seasons: [{ id: 1, season_number: 1, air_date: `${fixtureMonth}-02` }] }) };
};
