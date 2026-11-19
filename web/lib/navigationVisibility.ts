import { loadStored, saveStored } from "./storage";

export const OPTIONAL_NAVIGATION_SECTIONS = ["search", "watchlist", "tv"] as const;
export type OptionalNavigationSection = typeof OPTIONAL_NAVIGATION_SECTIONS[number];

export function normalizedHiddenNavigationSections(value: unknown): OptionalNavigationSection[] {
  return OPTIONAL_NAVIGATION_SECTIONS.filter((section) => Array.isArray(value) && value.includes(section));
}

export function navigationSectionVisible(section: string, hidden: unknown, isKids = false): boolean {
  return !normalizedHiddenNavigationSections(hidden).includes(section as OptionalNavigationSection) && !(isKids && section === "tv");
}

export function withNavigationSectionVisible(hidden: unknown, section: OptionalNavigationSection, visible: boolean): OptionalNavigationSection[] {
  const next = normalizedHiddenNavigationSections(hidden).filter((item) => item !== section);
  return normalizedHiddenNavigationSections(visible ? next : [...next, section]);
}

function navigationVisibilityKey(accountId: string | undefined, profileId: string) {
  return `arvio.web.navigation:${encodeURIComponent(accountId ?? "local")}:${encodeURIComponent(profileId)}`;
}

export function loadNavigationVisibility(accountId: string | undefined, profileId: string | null | undefined): OptionalNavigationSection[] | undefined {
  if (!profileId) return undefined;
  const stored = loadStored<unknown>(navigationVisibilityKey(accountId, profileId), null);
  return Array.isArray(stored) ? normalizedHiddenNavigationSections(stored) : undefined;
}

export function saveNavigationVisibility(accountId: string | undefined, profileId: string | null | undefined, hidden: unknown) {
  if (profileId && Array.isArray(hidden)) saveStored(navigationVisibilityKey(accountId, profileId), normalizedHiddenNavigationSections(hidden));
}
