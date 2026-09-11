/* Fixed destinations only. No cookies, user identifiers or analytics API calls. */
function premiumDestination(pathname) {
  const match = /^\/go\/(premium|membership)(?:\/(nav|hero|spotlight|preview|details|faq|footer))?\/?$/.exec(pathname);
  if (!match) return null;
  const [, kind, placement] = match;
  if (kind === "membership" && !["nav", "spotlight", "details", "footer"].includes(placement)) return null;
  const target = new URL(kind === "premium" ? "https://web.arvio.tv/" : "https://ko-fi.com/arvio/tiers");
  target.searchParams.set("utm_source", "arvio.tv");
  target.searchParams.set("utm_medium", "website");
  target.searchParams.set("utm_campaign", "premium");
  if (placement) target.searchParams.set("utm_content", placement === "nav" ? "navigation" : placement);
  if (kind === "premium") target.searchParams.set("intent", "trial");
  return target.href;
}
if (typeof module !== "undefined") module.exports = { premiumDestination };
if (typeof window !== "undefined") {
  const destination = premiumDestination(window.location.pathname);
  if (destination) window.location.replace(destination);
}
