// "What's new in Premium": a short monthly note so members see ARVIO Web
// improving. Newest first; ids must stay unique (they remember dismissal).
export interface WhatsNewEntry { id: string; date: string; title: string; body: string }

export const WHATS_NEW: WhatsNewEntry[] = [
  {
    id: "2026-11-explore-yearly",
    date: "2026-11-01",
    title: "A 12-month pass and invites",
    body: "Premium now has a 12-month pass, and you can invite a friend from Settings. When they join Premium, you both get a free month."
  },
  {
    id: "2026-10-kids",
    date: "2026-10-24",
    title: "Kids profiles on the web",
    body: "Kids profiles now follow the same age limits as the Android app, with an optional parent PIN."
  }
];

const KEY = "arvio.whatsnew.seen";

export function unseenWhatsNew(seen: string | null = (() => { try { return localStorage.getItem(KEY); } catch { return null; } })()): WhatsNewEntry | null {
  const latest = WHATS_NEW[0];
  return latest && latest.id !== seen ? latest : null;
}

export function markWhatsNewSeen(id: string) {
  try { localStorage.setItem(KEY, id); } catch { /* storage is optional */ }
}
