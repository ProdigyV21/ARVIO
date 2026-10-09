import { simklClient } from "./simkl";

export function parseSimklListUrl(raw: string): { id: number; url: string } | null {
  try {
    const url = new URL(/^https?:\/\//i.test(raw.trim()) ? raw.trim() : `https://${raw.trim()}`);
    if (!["https:", "http:"].includes(url.protocol) || !["simkl.com", "www.simkl.com"].includes(url.hostname) || url.username || url.password) return null;
    const owner = url.pathname.match(/^\/([1-9]\d*)\/list\/([1-9]\d*)(?:\/.*)?$/);
    const canonical = url.pathname.match(/^\/lists\/([1-9]\d*)(?:\/.*)?$/);
    const id = Number(owner?.[2] ?? canonical?.[1]);
    return Number.isSafeInteger(id) && id > 0 ? { id, url: owner ? `https://simkl.com/${owner[1]}/list/${id}` : `https://simkl.com/lists/${id}` } : null;
  } catch { return null; }
}

export interface SimklCustomList {
  id: number; name: string; media_type: string; type?: string; updated_at?: string;
  description?: string; user?: { id: number; name?: string }; counts?: { items?: number; likes?: number };
  top_items?: Array<{ poster?: string }>;
  pagination?: { total_pages: number };
  items: Array<{ title: string; year?: number; type?: string; anime_type?: string; ids?: { tmdb?: number | string; imdb?: string } }>;
  error?: string;
}

const lists = new Map<string, { at: number; list: SimklCustomList }>();
const indexes = new Map<string, { at: number; lists: SimklCustomList[] }>();

/** SIMKL has no global list-name search API: search popular official, owned, followed and shared lists. */
export async function searchSimklCustomLists(query: string): Promise<SimklCustomList[]> {
  if (!simklClient.token?.refresh_token) throw new Error("Reconnect SIMKL once to enable custom lists.");
  const normalized = query.trim().toLowerCase();
  if (normalized.length < 2) return [];
  const key = simklClient.token.connection_id!;
  let cached = indexes.get(key);
  if (!cached || Date.now() - cached.at >= 900_000) {
    const settings = await simklClient.customListsRequest<{ account?: { id?: number | string } }>("/users/settings", { method: "POST", body: "{}" });
    const user = Number(settings.account?.id);
    if (!Number.isSafeInteger(user) || user <= 0) throw new Error("SIMKL could not identify your account.");
    const all: SimklCustomList[] = [];
    const sources = user === 5 ? [{ owner: 5, sort: null }] : [{ owner: user, sort: null }, { owner: 5, sort: "updated" }, { owner: 5, sort: "popularity" }];
    for (const { owner, sort } of sources) {
      let page = 1, pages = 1;
      do {
        const related = sort ? `sort=${sort}&direction=desc` : "followed=true&collaborants=true";
        const response = await simklClient.customListsRequest<{ error?: string; lists?: SimklCustomList[]; pagination?: { total_pages?: number } }>(`/lists/user/${owner}?limit=500&page=${page}&${related}`);
        if (response.error || !Array.isArray(response.lists)) throw new Error(`SIMKL could not search lists (${response.error || "invalid response"}).`);
        pages = sort ? 1 : Math.max(1, response.pagination?.total_pages || 1);
        if (pages > 20) throw new Error("Your list index exceeds SIMKL's API limit.");
        all.push(...response.lists);
        page++;
      } while (page <= pages);
    }
    const unique = [...new Map(all.map(list => [list.id, list])).values()];
    for (const list of unique) {
      const previous = lists.get(`${key}:${list.id}`);
      if (list.type !== "auto" && previous?.list.updated_at !== list.updated_at) lists.delete(`${key}:${list.id}`);
    }
    cached = { at: Date.now(), lists: unique };
    indexes.set(key, cached);
  }
  return cached.lists.filter(list => (typeof list.name === "string" && list.name.toLowerCase().includes(normalized)) ||
    (typeof list.description === "string" && list.description.toLowerCase().includes(normalized)));
}

export async function loadSimklCustomList(raw: string): Promise<SimklCustomList> {
  const parsed = parseSimklListUrl(raw);
  if (!parsed) throw new Error("Enter a SIMKL custom-list URL.");
  if (!simklClient.token?.refresh_token) throw new Error("Reconnect SIMKL once to enable custom lists.");
  const key = `${simklClient.token.connection_id}:${parsed.id}`;
  const cached = lists.get(key);
  if (cached && Date.now() - cached.at < (cached.list.type === "auto" ? 86_400_000 : 900_000)) return cached.list;
  const read = async (page: number) => {
    const list = await simklClient.customListsRequest<SimklCustomList>(`/lists/${parsed.id}?limit=500&page=${page}`);
    if (list.error === "premium_only") throw new Error("SIMKL custom lists require a SIMKL PRO or VIP account.");
    if (list.error || !Array.isArray(list.items) || !list.name) throw new Error(`SIMKL could not load this list (${list.error || "invalid response"}).`);
    return list;
  };
  const first = await read(1);
  const pages = Math.max(1, first.pagination?.total_pages || 1);
  if (pages > 20) throw new Error("This list exceeds SIMKL's 10,000-item API limit.");
  const list = { ...first, items: [...first.items] };
  for (let page = 2; page <= pages; page++) list.items.push(...(await read(page)).items);
  lists.set(key, { at: Date.now(), list });
  return list;
}
