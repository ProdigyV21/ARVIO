import type { PlaybackPlan } from "@/lib/streamCompatibility";
import type { StreamSource } from "@/lib/types";

export type SourcePlaybackPresentation = {
  state: "blocked" | "conversion" | "preparation" | "unverified";
  label: string;
  detail: string;
  className: string;
  canTryBrowser: boolean;
};

/** Present the shared plan without treating a possible route as verified playback. */
export function sourcePlaybackPresentation(
  stream: Pick<StreamSource, "url">,
  plan: Pick<PlaybackPlan, "route" | "method" | "detail">,
  uncached: boolean
): SourcePlaybackPresentation {
  const cacheDetail = uncached ? "Not cached: the provider must download this source first." : "";
  const detail = (fallback: string) => [plan.detail || fallback, cacheDetail].filter(Boolean).join(" ");
  if (!stream.url || plan.route !== "here") {
    return {
      state: "blocked",
      label: "Not playable in this browser",
      detail: detail(!stream.url ? "No resolved playback URL is available." : plan.route === "vlc"
        ? "Use a compatible external player or choose another source."
        : "No browser playback route is available for this source."),
      className: "recommend-external",
      canTryBrowser: false
    };
  }
  if (plan.method === "transcode") {
    return {
      state: "conversion",
      label: "Requires provider conversion",
      detail: detail("Conversion availability depends on provider support and permission."),
      className: "is-transcode",
      canTryBrowser: !uncached
    };
  }
  if (plan.method === "remux") {
    return {
      state: "preparation",
      label: "Requires browser preparation",
      detail: detail("Container or audio preparation is required; playback is not yet verified."),
      className: "",
      canTryBrowser: !uncached
    };
  }
  return {
    state: "unverified",
    label: "Browser playback unverified",
    detail: detail(""),
    className: "",
    canTryBrowser: !uncached
  };
}
