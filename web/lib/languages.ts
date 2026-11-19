// Track languages as the Android/TV app stores them. Subtitle and audio
// preferences sync through ARVIO Cloud as these display names ("English",
// "Portuguese (Brazil)"), so the web writes the same values and reads codes,
// names or labels through `languageCode`, a port of Android's
// util/LanguageCodes.kt normalizeLanguageCode.

export const TRACK_LANGUAGES = [
  "English", "Arabic", "Bengali", "Bulgarian", "Chinese", "Croatian", "Czech", "Danish", "Dutch",
  "Estonian", "Finnish", "French", "German", "Greek", "Gujarati", "Hebrew", "Hindi", "Hungarian",
  "Indonesian", "Italian", "Japanese", "Kannada", "Korean", "Lithuanian", "Malay", "Malayalam",
  "Marathi", "Norwegian", "Persian", "Polish", "Portuguese", "Portuguese (Brazil)", "Punjabi",
  "Romanian", "Russian", "Serbian", "Slovak", "Slovenian", "Spanish", "Swedish", "Tamil", "Telugu",
  "Thai", "Turkish", "Ukrainian", "Vietnamese",
] as const;

const NAME_TO_CODE: Record<string, string> = {
  english: "en", arabic: "ar", bengali: "bn", bulgarian: "bg", chinese: "zh", croatian: "hr", czech: "cs",
  danish: "da", dutch: "nl", estonian: "et", finnish: "fi", french: "fr", german: "de", greek: "el",
  gujarati: "gu", hebrew: "he", hindi: "hi", hungarian: "hu", indonesian: "id", italian: "it", japanese: "ja",
  kannada: "kn", korean: "ko", lithuanian: "lt", malay: "ms", malayalam: "ml", marathi: "mr", norwegian: "no",
  persian: "fa", polish: "pl", portuguese: "pt", "portuguese (brazil)": "pt-br", punjabi: "pa", romanian: "ro",
  russian: "ru", serbian: "sr", slovak: "sk", slovenian: "sl", spanish: "es", swedish: "sv", tamil: "ta",
  telugu: "te", thai: "th", turkish: "tr", ukrainian: "uk", vietnamese: "vi",
};

// "malayalam" must be tried before "malay", "portuguese (brazil)" before "portuguese".
const NAMES_LONGEST_FIRST = Object.keys(NAME_TO_CODE).sort((a, b) => b.length - a.length);

const THREE_LETTER: Record<string, string> = {
  eng: "en", spa: "es", fra: "fr", fre: "fr", deu: "de", ger: "de", ita: "it", por: "pt", pob: "pt-br", pobr: "pt-br",
  nld: "nl", dut: "nl", rus: "ru", zho: "zh", chi: "zh", jpn: "ja", kor: "ko", ara: "ar", hin: "hi", tur: "tr",
  pol: "pl", swe: "sv", nor: "no", nob: "no", nno: "no", dan: "da", fin: "fi", ell: "el", gre: "el", ces: "cs", cze: "cs", hun: "hu",
  ron: "ro", rum: "ro", tha: "th", vie: "vi", ind: "id", heb: "he", iw: "he", fas: "fa", per: "fa", ukr: "uk",
  ben: "bn", bul: "bg", hrv: "hr", srp: "sr", slk: "sk", slo: "sk", slv: "sl", lit: "lt", est: "et", msa: "ms", may: "ms",
  guj: "gu", kan: "kn", mal: "ml", mar: "mr", pan: "pa", tam: "ta", tel: "te",
};

/** "English", "eng", "en-US", "Portuguese (Brazil)", "pt_BR" → "en", "en", "en", "pt-br", "pt-br".
 *  Off/blank/Auto → "". Unknown values come back lower-cased. */
export function languageCode(value: string | null | undefined): string {
  const raw = String(value ?? "").trim().toLowerCase();
  if (!raw || raw === "off" || raw === "none" || raw === "forced" || raw.startsWith("auto")) return "";
  if (raw in NAME_TO_CODE) return NAME_TO_CODE[raw];
  if (raw === "pt-br" || raw === "pt_br" || raw === "ptbr" || raw.includes("brazil")) return "pt-br";
  const name = NAMES_LONGEST_FIRST.find((key) => raw.startsWith(key));
  if (name) return NAME_TO_CODE[name];
  if (raw === "espanol") return "es";
  if (raw === "francais") return "fr";
  if (raw === "deutsch") return "de";
  if (raw === "farsi") return "fa";
  if (raw in THREE_LETTER) return THREE_LETTER[raw];
  const base = raw.split(/[-_]/)[0];
  if (base.length === 2) return base;
  if (base in THREE_LETTER) return THREE_LETTER[base];
  return raw;
}

/** The Android display name for a code or name, or "" when unknown/off. */
export function trackLanguageName(value: string | null | undefined): string {
  const code = languageCode(value);
  if (!code) return "";
  const match = TRACK_LANGUAGES.find((name) => NAME_TO_CODE[name.toLowerCase()] === code);
  if (match) return match;
  return String(value ?? "").trim();
}

/** Do two language values (codes, names or track labels) mean the same language?
 *  "pt-br" matches "pt" tracks too; a plain "pt" does not claim Brazilian tracks first. */
export function sameLanguage(a: string | null | undefined, b: string | null | undefined): boolean {
  const x = languageCode(a);
  const y = languageCode(b);
  if (!x || !y) return false;
  if (x === y) return true;
  return x.split("-")[0] === y.split("-")[0];
}
