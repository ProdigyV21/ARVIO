// Direction-independent horizontal scrolling for rails.
//
// In a right-to-left container `scrollLeft` does not count from the start of
// the content. Current engines (Chromium 85+, Safari, Firefox) report 0 at the
// start and negative values towards the end; older Chromium/WebKit (many TV
// browsers) report the maximum at the start, counting down. Rails ask "how far
// from the start am I?" and "move one page towards the end" instead.

export type RtlScrollType = "negative" | "default" | "reverse";

let detected: RtlScrollType | null = null;

/** Which `scrollLeft` convention this engine uses for RTL boxes (detected once). */
export function rtlScrollType(): RtlScrollType {
  if (detected) return detected;
  if (typeof document === "undefined" || !document.body) return "negative";
  const outer = document.createElement("div");
  const inner = document.createElement("div");
  outer.dir = "rtl";
  outer.style.cssText = "position:absolute;top:-1000px;width:4px;height:1px;overflow:scroll;visibility:hidden";
  inner.style.cssText = "width:8px;height:1px";
  outer.appendChild(inner);
  document.body.appendChild(outer);
  let type: RtlScrollType = "negative";
  if (outer.scrollLeft > 0) type = "default";
  else {
    outer.scrollLeft = 1;
    type = outer.scrollLeft === 0 ? "negative" : "reverse";
  }
  outer.remove();
  detected = type;
  return type;
}

type ScrollBox = { scrollLeft: number; scrollWidth: number; clientWidth: number };

/** Distance in px from the inline start of the content (0 at the start). */
export function scrollFromStart(node: ScrollBox, rtl: boolean, type: RtlScrollType = rtlScrollType()): number {
  const max = Math.max(0, node.scrollWidth - node.clientWidth);
  if (!rtl) return node.scrollLeft;
  if (type === "negative") return Math.max(0, -node.scrollLeft);
  if (type === "default") return max - node.scrollLeft;
  return node.scrollLeft;
}

/** Physical `scrollBy` left offset that moves `delta` px towards the inline end. */
export function towardsEnd(delta: number, rtl: boolean, type: RtlScrollType = rtlScrollType()): number {
  if (!rtl || type === "reverse") return delta;
  return -delta;
}
