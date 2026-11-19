// Hover parity for remote / keyboard / gamepad navigation.
//
// All of the app's visual affordances (card scale, row highlights, reveal-on-
// hover controls) live in `:hover` rules — invisible to someone driving focus
// with a D-pad. Instead of hand-duplicating ~136 hover rules as focus rules
// (and forgetting the next one), this generates them: on the first key-driven
// focus move it walks every stylesheet, and for each selector containing
// `:hover` inserts a twin rule with `:hover` replaced by `:focus-within`,
// scoped under `html.kbd-nav`.
//
//   .media-card:hover .poster img { transform: scale(1.05) }
//     → html.kbd-nav .media-card:focus-within .poster img { … }
//
// `:focus-within` (not `:focus`) because it matches both the focused element
// itself and containers of it — the same shape as hover, where the styled
// element often wraps the actual focusable.
//
// `kbd-nav` mode turns on when navigation happens by keys (real keyboard, TV
// remote, or the gamepad layer's synthetic keys) and off again on any real
// pointer activity, so mouse users never see focus styling from a stale
// click. Engines too old for `:focus-within` (Tizen 4) throw on insertRule;
// those twins are skipped and the focus outline still carries the day.

let sheetBuilt = false;
let exitInstalled = false;

/** The guard every hover rule sits in, so a tap on a touch screen cannot leave
 *  a lift or zoom stuck on (the stylesheets wrap :hover rules in it). */
export const HOVER_GUARD = "(hover: hover) and (pointer: fine)";
const GUARD_PATTERN = /^\(\s*hover:\s*hover\s*\)\s+and\s+\(\s*pointer:\s*fine\s*\)$/i;
const GUARD_SUFFIX = /\s+and\s+\(\s*hover:\s*hover\s*\)\s+and\s+\(\s*pointer:\s*fine\s*\)$/i;

/**
 * A wrapper's media condition for the focus twin: the hover guard removed.
 * Focus twins must apply on every key-driven device, and TV remotes, gamepads
 * and keyboards on touch tablets report no hover-capable pointer. Returns null
 * when nothing is left to wrap (the condition was only the guard).
 */
export function parityCondition(condition: string): string | null {
  const parts = condition.split(",").map((part) => part.trim());
  const kept: string[] = [];
  for (const part of parts) {
    if (GUARD_PATTERN.test(part)) return null; // one branch always applies
    kept.push(part.replace(GUARD_SUFFIX, ""));
  }
  return kept.join(", ");
}

/** Conditional wrappers (`@media …`, `@supports …`) around a nested rule. */
function wrappers(rule: CSSRule): string[] {
  const out: string[] = [];
  let parent: CSSRule | null = rule.parentRule;
  while (parent) {
    if (parent instanceof CSSMediaRule) {
      const condition = parityCondition(parent.conditionText);
      if (condition) out.unshift(`@media ${condition}`);
    }
    else if (typeof CSSSupportsRule !== "undefined" && parent instanceof CSSSupportsRule) {
      out.unshift(`@supports ${parent.conditionText}`);
    }
    parent = parent.parentRule;
  }
  return out;
}

/**
 * Turn a selector list into its focus twin: keep only the comma-parts that
 * mention `:hover` (the others would just duplicate unrelated rules at higher
 * specificity), swap `:hover` for `:focus-within`, scope under html.kbd-nav.
 */
function twinSelector(selectorText: string): string | null {
  const parts = selectorText
    .split(",")
    .map((p) => p.trim())
    .filter((p) => p.includes(":hover"))
    // Scrollbar pieces and other pseudo-elements can never contain focus.
    .filter((p) => !p.includes("::-webkit-scrollbar"))
    // Spoiler blur's hover rule is a deliberate opt-in reveal. As a focus
    // twin it would unblur every synopsis the D-pad merely passes over,
    // silently defeating the setting — suppression rules don't get twins.
    .filter((p) => !p.includes(".spoiler-blur"))
    // Touch-only rules (html.no-hover) never need a keyboard twin.
    .filter((p) => !p.includes(".no-hover"))
    .map((p) => `html.kbd-nav ${p.split(":hover").join(":focus-within")}`);
  return parts.length > 0 ? parts.join(", ") : null;
}

// Screens are code-split (next/dynamic), so their stylesheets arrive AFTER the
// first key press built the twins. Each sheet is processed once; later calls
// only pick up sheets that appeared since.
const processedSheets = new WeakSet<CSSStyleSheet>();
let paritySheet: CSSStyleSheet | null = null;
let seenSheetCount = -1;

function buildParitySheet() {
  if (!sheetBuilt) {
    sheetBuilt = true;
    const style = document.createElement("style");
    style.setAttribute("data-hover-parity", "");
    document.head.appendChild(style);
    paritySheet = style.sheet;
    if (paritySheet) processedSheets.add(paritySheet);
  }
  const target = paritySheet;
  if (!target) return;
  const sheets = Array.from(document.styleSheets);
  if (sheets.length === seenSheetCount && sheets.every((sheet) => processedSheets.has(sheet))) return;
  seenSheetCount = sheets.length;

  const walk = (rules: CSSRuleList) => {
    for (const rule of Array.from(rules)) {
      // Conditional groups (@media/@supports) nest their own rule lists.
      const nested = (rule as CSSGroupingRule).cssRules;
      if (nested && !(rule instanceof CSSStyleRule)) {
        walk(nested);
        continue;
      }
      if (!(rule instanceof CSSStyleRule)) continue;
      if (!rule.selectorText.includes(":hover")) continue;
      const twin = twinSelector(rule.selectorText);
      if (!twin) continue;
      const body = rule.style.cssText;
      if (!body) continue;
      let text = `${twin} { ${body} }`;
      for (const wrap of wrappers(rule).reverse()) text = `${wrap} { ${text} }`;
      try {
        target.insertRule(text, target.cssRules.length);
      } catch {
        // Engine predates :focus-within (or rejects the selector) — skip.
      }
    }
  };

  for (const sheet of sheets) {
    if (processedSheets.has(sheet)) continue;
    let rules: CSSRuleList;
    try {
      rules = sheet.cssRules;
    } catch {
      continue; // cross-origin sheet (or still loading) — retried next time
    }
    processedSheets.add(sheet);
    walk(rules);
  }
}

function onPointerActivity(event: Event) {
  if (!event.isTrusted) return; // synthetic events must not exit key mode
  document.documentElement.classList.remove("kbd-nav");
}

/** Called by tvNav/gamepadNav whenever navigation happens via keys. */
export function enterKbdNav() {
  if (typeof document === "undefined") return;
  buildParitySheet();
  document.documentElement.classList.add("kbd-nav");
  if (exitInstalled) return;
  exitInstalled = true;
  // Real pointer use means the user can hover again — hand the styling back.
  window.addEventListener("mousemove", onPointerActivity, { capture: true, passive: true });
  window.addEventListener("pointerdown", onPointerActivity, { capture: true, passive: true });
  window.addEventListener("wheel", onPointerActivity, { capture: true, passive: true });
}
