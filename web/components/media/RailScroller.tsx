"use client";
import { useDirection, useTranslation } from "@/lib/i18n";


import { ChevronLeft, ChevronRight } from "lucide-react";
import { ReactNode, useCallback, useEffect, useRef, useState } from "react";
import { scrollFromStart, towardsEnd } from "@/lib/rtlScroll";

// Right-to-left (Arabic, Hebrew, Persian, Urdu) mirrors the rail: it starts at
// the right, the "previous" arrow sits on the right edge (app/globals.css
// mirrors the arrows) and "next" scrolls towards the left.
const isRtl = (node: HTMLElement) => getComputedStyle(node).direction === "rtl";

export function RailScroller({
  children,
  className,
  ariaLabel
}: {
  children: ReactNode;
  className: string;
  ariaLabel: string;
}) {
  const translateUi = useTranslation();
  const rtl = useDirection() === "rtl";
  const ref = useRef<HTMLDivElement | null>(null);
  const [canPrev, setCanPrev] = useState(false);
  const [canNext, setCanNext] = useState(false);

  const update = useCallback(() => {
    const node = ref.current;
    if (!node) return;
    const max = node.scrollWidth - node.clientWidth;
    const offset = scrollFromStart(node, isRtl(node));
    setCanPrev(offset > 6);
    setCanNext(offset < max - 6);
  }, []);

  const scrollByPage = (direction: -1 | 1) => {
    const node = ref.current;
    if (!node) return;
    node.scrollBy({
      left: towardsEnd(direction * Math.max(220, node.clientWidth * 0.82), isRtl(node)),
      behavior: "smooth"
    });
  };

  useEffect(() => {
    const node = ref.current;
    if (!node) return undefined;
    update();
    const onScroll = () => update();
    node.addEventListener("scroll", onScroll, { passive: true });
    const resizeObserver = new ResizeObserver(update);
    resizeObserver.observe(node);
    const timer = window.setTimeout(update, 250);
    return () => {
      window.clearTimeout(timer);
      node.removeEventListener("scroll", onScroll);
      resizeObserver.disconnect();
    };
  }, [update, children]);

  return (
    <div className={`rail-scroll-shell ${canPrev ? "can-prev" : ""} ${canNext ? "can-next" : ""}`}>
      <button
        type="button"
        // Pointer-only: D-pad/Tab users move card to card, so the arrows are
        // not focus stops (they stay clickable); data-tv-skip keeps tvNav's
        // spatial navigation off them.
        tabIndex={-1}
        data-tv-skip
        className="rail-arrow rail-arrow-left"
        onClick={() => scrollByPage(-1)}
        disabled={!canPrev}
        aria-label={rtl ? translateUi("Scroll {value0} right", {value0: ariaLabel}) : translateUi("Scroll {value0} left", {value0: ariaLabel})}
      >
        <ChevronLeft size={24} />
      </button>
      <div ref={ref} className={className}>
        {children}
      </div>
      <button
        type="button"
        // Pointer-only: D-pad/Tab users move card to card, so the arrows are
        // not focus stops (they stay clickable); data-tv-skip keeps tvNav's
        // spatial navigation off them.
        tabIndex={-1}
        data-tv-skip
        className="rail-arrow rail-arrow-right"
        onClick={() => scrollByPage(1)}
        disabled={!canNext}
        aria-label={rtl ? translateUi("Scroll {value0} left", {value0: ariaLabel}) : translateUi("Scroll {value0} right", {value0: ariaLabel})}
      >
        <ChevronRight size={24} />
      </button>
    </div>
  );
}
