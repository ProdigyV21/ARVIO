/**
 * One runtime format everywhere on Home ("2h 25m", "48m", "2h"). Accepts the
 * minutes as a number or the "145m" / "145 min" strings list mappers produce.
 */
export function formatRuntime(minutesRaw?: string | number | null): string {
  const minutes = typeof minutesRaw === "string" ? Number(minutesRaw.replace(/\D/g, "")) : minutesRaw;
  if (!minutes || !Number.isFinite(minutes) || minutes <= 0) return "";
  const rounded = Math.round(minutes);
  const h = Math.floor(rounded / 60);
  const m = rounded % 60;
  return h > 0 ? (m > 0 ? `${h}h ${m}m` : `${h}h`) : `${m}m`;
}

export function formatReleaseDate(raw?: string | null, language?: string): string {
  if (!raw) return "";
  const date = new Date(`${raw}T00:00:00`);
  if (Number.isNaN(date.getTime())) return raw.slice(0, 4);
  try {
    return new Intl.DateTimeFormat(language, { day: "numeric", month: "short", year: "numeric" }).format(date);
  } catch {
    return raw;
  }
}
