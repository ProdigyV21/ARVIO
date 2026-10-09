import type { MediaType } from "./types";

export const PARTNER_LINK_KEYS = ["open", "imdb", "type", "id", "season", "episode"] as const;
const MAX_ID = 2_147_483_647;
const MAX_COORDINATE = 10_000;

export type PartnerTarget = {
  imdb?: string;
  mediaType?: MediaType;
  id?: number;
  season?: number;
  episode?: number;
};

export type PartnerLinkParse =
  | { status: "none" }
  | { status: "invalid"; error: string }
  | { status: "valid"; target: PartnerTarget };

export type PartnerReference = PartnerTarget & { mediaType: MediaType; id: number };
export type PartnerTmdbRequest = <T>(path: string, params?: Record<string, string | number | undefined>) => Promise<T>;

/** Only controlled validation messages may reach the UI; provider errors may contain private URLs. */
export class PartnerLinkError extends Error {}

function integer(value: string, minimum: number, maximum: number): number | undefined {
  if (!/^(0|[1-9]\d*)$/.test(value)) return undefined;
  const parsed = Number(value);
  return Number.isSafeInteger(parsed) && parsed >= minimum && parsed <= maximum ? parsed : undefined;
}

/** Query-only input: partner data can identify a title, never a URL to fetch or play. */
export function parsePartnerLink(search: string): PartnerLinkParse {
  const params = new URLSearchParams(search);
  if (!params.has("open")) return { status: "none" };
  if (search.length > 4096) return { status: "invalid", error: "This ARVIO title link is too long." };
  const invalid = (error = "This ARVIO title link is invalid."): PartnerLinkParse => ({ status: "invalid", error });
  if (PARTNER_LINK_KEYS.some(key => params.getAll(key).length > 1)) return invalid();
  if (params.get("open") !== "1") return invalid();
  const imdb = params.get("imdb") || undefined;
  const rawType = params.get("type") || undefined;
  const rawId = params.get("id") || undefined;
  const rawSeason = params.get("season") || undefined;
  const rawEpisode = params.get("episode") || undefined;
  if (rawType && rawType !== "movie" && rawType !== "tv") return invalid();
  if (params.has("imdb") && (!imdb || !/^tt\d{5,12}$/.test(imdb) || params.has("id") || params.has("type"))) return invalid();
  const id = rawId ? integer(rawId, 1, MAX_ID) : undefined;
  if (!imdb && (!id || !rawType)) return invalid();
  const season = rawSeason ? integer(rawSeason, 0, MAX_COORDINATE) : undefined;
  const episode = rawEpisode ? integer(rawEpisode, 1, MAX_COORDINATE) : undefined;
  if ((rawSeason && season === undefined) || (rawEpisode && episode === undefined)) return invalid();
  if (episode !== undefined && season === undefined) return invalid();
  if (rawType === "movie" && (season !== undefined || episode !== undefined)) return invalid();
  return { status: "valid", target: { imdb, mediaType: rawType as MediaType | undefined, id, season, episode } };
}

/** Unrelated navigation/attribution changes must not invalidate an in-flight title lookup. */
export function partnerLinkSignature(search: string): string {
  if (search.length > 4096) return search;
  const params = new URLSearchParams(search);
  return JSON.stringify(PARTNER_LINK_KEYS.map(key => params.getAll(key)));
}

/** Preserve attribution/other query data and hash; only our six title-link keys are removed. */
export function stripPartnerLink(url: string): string {
  const clean = new URL(url);
  PARTNER_LINK_KEYS.forEach(key => clean.searchParams.delete(key));
  return clean.pathname + clean.search + clean.hash;
}

/** Used by both sign-in entry points: only validated title links survive the portal round-trip. */
export function partnerLoginRedirect(origin: string, search: string): string {
  if (parsePartnerLink(search).status !== "valid") return origin + "/";
  // Fixed same-origin homepage, not a user-controlled return URL. Keep attribution
  // and trial intent intact; arbitrary query values are never navigation targets.
  const destination = new URL("/", origin);
  destination.search = search;
  return destination.toString();
}

type FindItem = { id?: number; adult?: boolean; show_id?: number; season_number?: number; episode_number?: number };
type FindResponse = { movie_results?: FindItem[]; tv_results?: FindItem[]; tv_episode_results?: FindItem[] };

function validId(id: unknown): id is number {
  return typeof id === "number" && Number.isInteger(id) && id > 0 && id <= MAX_ID;
}

/** IMDb episode IDs identify an episode, not the parent series' TMDB ID. */
export async function resolvePartnerReference(target: PartnerTarget, request: PartnerTmdbRequest): Promise<PartnerReference> {
  if (!target.imdb) return { ...target, id: target.id!, mediaType: target.mediaType! };
  const found = await request<FindResponse>(`find/${target.imdb}`, { external_source: "imdb_id" });
  const candidates: PartnerReference[] = [];
  if (!target.mediaType || target.mediaType === "movie") {
    for (const item of found.movie_results ?? []) {
      if (validId(item.id) && !item.adult) candidates.push({ ...target, mediaType: "movie", id: item.id });
    }
  }
  if (!target.mediaType || target.mediaType === "tv") {
    for (const item of found.tv_results ?? []) {
      if (validId(item.id) && !item.adult) candidates.push({ ...target, mediaType: "tv", id: item.id });
    }
    for (const item of found.tv_episode_results ?? []) {
      if (!validId(item.show_id) || item.adult) continue;
      const season = item.season_number;
      const episode = item.episode_number;
      if (typeof season !== "number" || !Number.isInteger(season) || season < 0 || season > MAX_COORDINATE ||
          typeof episode !== "number" || !Number.isInteger(episode) || episode <= 0 || episode > MAX_COORDINATE) continue;
      if ((target.season !== undefined && target.season !== season) || (target.episode !== undefined && target.episode !== episode)) {
        throw new PartnerLinkError("The episode numbers do not match this IMDb title.");
      }
      candidates.push({ ...target, mediaType: "tv", id: item.show_id, season, episode });
    }
  }
  const unique = [...new Map(candidates.map(item => [`${item.mediaType}:${item.id}:${item.season ?? ""}:${item.episode ?? ""}`, item])).values()];
  if (!unique.length) throw new PartnerLinkError("This title could not be found. Check the link or try again.");
  if (unique.length !== 1) throw new PartnerLinkError("This title could not be matched unambiguously. Use a TMDB title link instead.");
  const reference = unique[0];
  if (reference.mediaType === "movie" && (reference.season !== undefined || reference.episode !== undefined)) {
    throw new PartnerLinkError("A movie link cannot contain season or episode numbers.");
  }
  return reference;
}

/** Shared, testable lifecycle: no API call before access/profile readiness, no stale completion. */
export async function runPartnerLink<T>({ ready, isCurrent, resolve, open, error }: {
  ready: boolean;
  isCurrent: () => boolean;
  resolve: () => Promise<T>;
  open: (item: T) => void;
  error: (message: string) => void;
}): Promise<"waiting" | "stale" | "opened" | "error"> {
  if (!ready) return "waiting";
  try {
    const item = await resolve();
    if (!isCurrent()) return "stale";
    open(item);
    return "opened";
  } catch (cause) {
    if (!isCurrent()) return "stale";
    error(cause instanceof PartnerLinkError ? cause.message : "Could not open this title. Please try again.");
    return "error";
  }
}
