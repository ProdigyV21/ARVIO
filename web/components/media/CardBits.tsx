"use client";

import { useEffect, useState, type RefObject } from "react";
import { useTranslation } from "@/lib/i18n";

/**
 * The IMDb wordmark, inline. As an <img> it could lay out at 0px wide until the
 * SVG file arrived (and sometimes stayed that way in the hero); inline with a
 * fixed aspect ratio it is always drawn at its size.
 */
export function ImdbLogo({ height = 13, className }: { height?: number; className?: string }) {
  return <svg className={className} width={height * 2} height={height} viewBox="0 0 256 128" role="img" aria-label="IMDb" focusable="false">
    <rect width="256" height="128" rx="16" fill="#f5c518" />
    <g transform="matrix(4,0,0,4,32,28)" fill="#000">
      <polygon points="0 18 5 18 5 0 0 0" />
      <path d="M 15.672518,0 14.553483,8.4084693 13.858201,3.8350243 C 13.65661,2.3700926 13.463247,1.0917512 13.278113,0 H 7 v 18 h 4.241635 L 11.258091,6.1138068 13.043609,18 h 3.019748 L 17.758365,5.8517865 17.770708,18 H 22 V 0 Z" />
      <path d="m24 18v-18h7.804559c1.764793 0 3.195441 1.4199441 3.195441 3.1766042v11.6467918c0 1.75439-1.428338 3.176604-3.195441 3.176604zm5.832248-14.7604764c-0.198326-0.1071901-0.577732-0.1588002-1.129596-0.1588002v11.8107626c0.728633 0 1.177022-0.13101 1.345167-0.40494 0.168146-0.26996 0.254375-1.000441 0.254375-2.199382v-6.9792681c0-0.8138509-0.03018-1.3339215-0.08623-1.5641817-0.05605-0.2302603-0.18108-0.3970005-0.383717-0.5041906z" />
      <path d="m44.429908 4.5068582h0.31961c1.795192 0 3.250482 1.4058177 3.250482 3.1380094v7.2171234c0 1.733074-1.454818 3.138009-3.250482 3.138009h-0.31961c-1.098446 0-2.069633-0.526338-2.658038-1.331726l-0.287974 1.100504h-4.483896v-17.768778h4.784326v5.7805356c0.618172-0.7703782 1.570825-1.2736774 2.645582-1.2736774zm-1.02434 8.7773578v-4.2651379c0-0.7047386-0.04524-1.1672234-0.139493-1.3801133-0.09425-0.2128898-0.470487-0.3495732-0.734393-0.3495732s-0.670889 0.1110822-0.75006 0.2982784v7.219809c0.09048 0.205549 0.478614 0.319927 0.75006 0.319927s0.666531-0.110708 0.749473-0.319927c0.08294-0.20922 0.124413-0.719421 0.124413-1.523263z" />
    </g>
  </svg>;
}

/** The Top 10 ribbon (Android TopRankRibbon), drawn as SVG instead of 3334px PNGs. */
export function RankRibbon({ rank, poster }: { rank: number; poster?: boolean }) {
  const translateUi = useTranslation();
  const clamped = Math.min(10, Math.max(1, Math.round(rank)));
  return <svg className={`rank-ribbon ${poster ? "is-poster" : ""}`} viewBox="0 0 60 72" role="img"
    aria-label={translateUi("Rank #{value0}", { value0: clamped })} focusable="false">
    <defs>
      <linearGradient id="rank-ribbon-fill" x1="0" y1="0" x2="0" y2="1">
        <stop offset="0" stopColor="#f8c623" />
        <stop offset="1" stopColor="#f0a500" />
      </linearGradient>
    </defs>
    <path d="M4 0h52v66c-8-4-17-6-26-6s-18 2-26 6z" fill="url(#rank-ribbon-fill)" />
    <path d="M8 0v58c7-3 14-4.5 22-4.5s15 1.5 22 4.5V0" fill="none" stroke="#fff3c4" strokeOpacity=".7" strokeWidth="1" strokeDasharray="2.5 2.5" />
    <text x="30" y={clamped === 10 ? 38 : 39} textAnchor="middle" fontFamily="Inter, system-ui, sans-serif" fontWeight="800"
      fontSize={clamped === 10 ? 25 : 31} fill="#0b0b0b">{clamped}</text>
  </svg>;
}

// One observer for every card. Cards only fetch their logo/badges/rating once
// they are within a screen of the viewport, instead of all on mount.
type Callback = (near: boolean) => void;
let observer: IntersectionObserver | null = null;
const callbacks = new WeakMap<Element, Callback>();

function sharedObserver() {
  if (observer || typeof IntersectionObserver === "undefined") return observer;
  observer = new IntersectionObserver((entries) => {
    entries.forEach((entry) => callbacks.get(entry.target)?.(entry.isIntersecting));
  }, { rootMargin: "400px 200px" });
  return observer;
}

/** True while the element is on or near the screen (always true without IntersectionObserver). */
export function useNearViewport(ref: RefObject<Element | null>) {
  const [near, setNear] = useState(false);
  useEffect(() => {
    const node = ref.current;
    const io = sharedObserver();
    if (!node || !io) { setNear(true); return undefined; }
    callbacks.set(node, setNear);
    io.observe(node);
    return () => {
      io.unobserve(node);
      callbacks.delete(node);
    };
  }, [ref]);
  return near;
}
