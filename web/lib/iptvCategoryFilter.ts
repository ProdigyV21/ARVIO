// Big IPTV playlists bring hundreds of categories (one tested playlist: ~970),
// each starting with the playlist's name ("Strong8K: NL| ALGEMEEN HD/4K").
// These helpers let the Live TV sidebar say that name once and filter the list.

/** Folds case, accents and the superscript letters playlists use ("ᵁᴴᴰ" → "uhd"). */
export function foldCategoryText(value: string): string {
  return value.normalize("NFKC").normalize("NFD").replace(/[̀-ͯ]/g, "").toLowerCase().replace(/\s+/g, " ").trim();
}

/** "Name: " when every label (at least three) starts with that same playlist name, else "". */
export function sharedCategoryPrefix(labels: string[]): string {
  if (labels.length < 3) return "";
  const first = labels[0].match(/^([^:|]{1,40}):\s+/);
  if (!first) return "";
  const prefix = first[0];
  return labels.every((label) => label.startsWith(prefix) && label.length > prefix.length) ? prefix : "";
}

/** Labels containing every word of the query, in any order. */
export function matchesCategoryQuery(label: string, query: string): boolean {
  const words = foldCategoryText(query).split(" ").filter(Boolean);
  if (!words.length) return true;
  const text = foldCategoryText(label);
  return words.every((word) => text.includes(word));
}
