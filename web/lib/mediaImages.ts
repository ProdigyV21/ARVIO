export function tmdbImageUrl(base: string, value?: string | null) {
  if (!value) return "";
  if (value.startsWith("http://") || value.startsWith("https://")) return value;
  return `${base}${value.startsWith("/") ? value : `/${value}`}`;
}

const TMDB_SIZED = /^(https:\/\/image\.tmdb\.org\/t\/p\/)(w\d+|original)(\/.+)$/;

/**
 * A srcset of TMDB renditions for one image URL ("…/w300/x.jpg 300w, …").
 * Non-TMDB URLs (home servers, addons) get no srcset and load as-is.
 */
export function tmdbSrcSet(url: string | null | undefined, widths: number[]): string | undefined {
  const match = url ? TMDB_SIZED.exec(url) : null;
  if (!match || !widths.length) return undefined;
  return widths.map((width) => `${match[1]}w${width}${match[3]} ${width}w`).join(", ");
}

/** The same TMDB image at one given width (or the URL unchanged when it isn't TMDB's). */
export function tmdbSized(url: string, width: number): string {
  const match = TMDB_SIZED.exec(url);
  return match ? `${match[1]}w${width}${match[3]}` : url;
}
