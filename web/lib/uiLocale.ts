// Dates, weekdays and numbers follow the language ARVIO is shown in, not the
// browser's language: an English UI on a Dutch browser said "Last aired 20 nov
// 2026" and "di 24 nov". The browser's region is kept (en-NL), so day order,
// 24-hour clocks and digit grouping still match where the viewer lives.
export function uiLocales(): string[] | undefined {
  if (typeof document === "undefined") return undefined;
  const ui = document.documentElement.lang;
  if (!ui) return undefined;
  const base = ui.split("-")[0];
  const browser = typeof navigator === "undefined" ? [] : (navigator.languages?.length ? navigator.languages : [navigator.language]);
  const region = browser.map((tag) => tag?.split("-")[1]).find((part) => part && /^[A-Za-z]{2}$/.test(part));
  return region ? [`${base}-${region.toUpperCase()}`, ui] : [ui];
}

/** A stable cache key for formatters built with {@link uiLocales}. */
export function uiLocaleKey(): string {
  return uiLocales()?.join(",") ?? "";
}
