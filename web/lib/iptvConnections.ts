// IPTV panels (XC_VM, Xtream Codes) count every open stream request as a
// connection, keep counting a closed one for several seconds, and close the
// oldest viewer's stream when a shared line goes over its limit (#849). These
// rules keep the browser from being the one that pushes a line over.
import type { StreamSource } from "./types";

/** Live TV comes with the player's liveTv flag; these are the IPTV VOD and catch-up sources. */
export function isIptvSource(stream: Pick<StreamSource, "addonId" | "addonName">) {
  return /^(iptv|stalker|xtream)/i.test(stream.addonId ?? "") || stream.addonName === "Catch-up";
}
