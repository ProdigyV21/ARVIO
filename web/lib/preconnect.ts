// Opens the TLS connection to a host the next step will call (the stream
// resolver) while the person is still reading the details page, so pressing
// Play saves the DNS + TLS handshake (often 100-300 ms on mobile networks).
// A hint only: browsers drop unused preconnects after a few seconds, and no
// request or data is sent.
const done = new Set<string>();

export function preconnectOrigin(url: string | null | undefined, doc: Document | undefined = typeof document === "undefined" ? undefined : document) {
  if (!url || !doc) return false;
  let origin: string;
  try {
    const parsed = new URL(url);
    if (parsed.protocol !== "https:") return false;
    origin = parsed.origin;
  } catch { return false; }
  if (done.has(origin)) return false;
  done.add(origin);
  const link = doc.createElement("link");
  link.rel = "preconnect";
  link.href = origin;
  link.crossOrigin = "anonymous";
  doc.head.appendChild(link);
  return true;
}
