// Manifest and playlist links often embed debrid/API keys or logins.
/** Show the host of a link and hide the path/query, which often embed credentials or API keys. */
export function maskedUrl(url: string): string {
  try {
    const parsed = new URL(url);
    const hidden = parsed.pathname.length > 1 || parsed.search || parsed.username;
    return `${parsed.host}${hidden ? "/•••" : ""}`;
  } catch {
    return url.length > 12 ? `${url.slice(0, 8)}•••` : "•••";
  }
}
