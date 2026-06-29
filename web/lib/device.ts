"use client";

import { useEffect, useState } from "react";

export type DeviceMode = "tv" | "desktop" | "tablet";

/**
 * The web build targets TV + desktop browsers (with a responsive shrink for
 * tablets). We do not ship a separate phone/bottom-nav layout — see the parity
 * design doc. This mirrors Android's detectDeviceType() at the granularity the
 * web cares about.
 */
export function detectDeviceMode(width: number, coarsePointer: boolean): DeviceMode {
  // Very large screens or TV-style coarse pointers => TV layout.
  if (width >= 1600 && coarsePointer) return "tv";
  if (width >= 1100) return "desktop";
  return "tablet";
}

export function useDeviceMode(): DeviceMode {
  const [mode, setMode] = useState<DeviceMode>("desktop");

  useEffect(() => {
    const compute = () => {
      const coarse = window.matchMedia("(pointer: coarse)").matches;
      setMode(detectDeviceMode(window.innerWidth, coarse));
    };
    compute();
    window.addEventListener("resize", compute);
    return () => window.removeEventListener("resize", compute);
  }, []);

  return mode;
}
