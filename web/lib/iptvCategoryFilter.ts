// Big IPTV playlists bring hundreds of categories (one tested playlist: ~970),
// each starting with the playlist's name ("Strong8K: NL| ALGEMEEN HD/4K").
// These helpers let the Live TV sidebar say that name once and filter the list.

/** Folds case, accents and the superscript letters playlists use ("ᵁᴴᴰ" → "uhd"). */
export function foldCategoryText(value: string): string {
  return value.normalize("NFKC").normalize("NFD").replace(/[̀-ͯ]/g, "").toLowerCase().replace(/\s+/g, " ").trim();
}

/**
 * "Name: " when every "Name: …" label (at least three, and at least half of all
 * labels) uses that same playlist name, else "". Labels in another form, such as
 * the sports "Events · Climbing" groups, neither count nor block it.
 */
export function sharedCategoryPrefix(labels: string[]): string {
  const prefixes = labels.map((label) => label.match(/^([^:|]{1,40}):\s+(?=\S)/)?.[0] ?? null);
  const named = prefixes.filter((prefix): prefix is string => prefix !== null);
  if (named.length < 3 || named.length * 2 < labels.length) return "";
  return named.every((prefix) => prefix === named[0]) ? named[0] : "";
}

/** The label without the shared prefix, when it carries it. */
export function withoutCategoryPrefix(label: string, prefix: string): string {
  return prefix && label.startsWith(prefix) ? label.slice(prefix.length) : label;
}

/** Labels containing every word of the query, in any order. */
export function matchesCategoryQuery(label: string, query: string): boolean {
  const words = foldCategoryText(query).split(" ").filter(Boolean);
  if (!words.length) return true;
  const text = foldCategoryText(label);
  return words.every((word) => text.includes(word));
}
