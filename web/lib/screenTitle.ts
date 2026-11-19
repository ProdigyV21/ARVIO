import type { NavSection } from "./types";

const SECTION_LABELS: Record<NavSection, string> = {
  home: "Home",
  search: "Search",
  watchlist: "Library",
  tv: "TV",
  addons: "Addons",
  settings: "Settings"
};

/** The document title for a screen: "Home · ARVIO", or the open title's name. */
export function screenTitle(section: NavSection, selectedTitle: string | null | undefined, translate: (text: string) => string = (text) => text) {
  const name = selectedTitle?.trim() || translate(SECTION_LABELS[section] ?? "Home");
  return `${name} · ARVIO`;
}
