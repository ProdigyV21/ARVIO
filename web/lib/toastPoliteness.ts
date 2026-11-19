// Messages that report a failure are announced assertively; everything else
// ("Added to watchlist", "Preparing browser playback...") politely.
const ERROR_TOAST = /\b(fail(ed|s|ure)?|error|could ?n[o']t|cannot|can't|unable|not available|denied|expired|invalid|no (direct )?stream|offline)\b/i;

export function toastPoliteness(message: string): "assertive" | "polite" {
  return ERROR_TOAST.test(message) ? "assertive" : "polite";
}
