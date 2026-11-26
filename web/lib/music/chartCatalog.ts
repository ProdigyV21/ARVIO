// Which rows the Music home shows: the charts and genres of the country the user is in first, then a
// long mix of moods, genres and decades whose order changes every day (the daily playlists). The
// country comes from the browser (time zone, then language regions); the app language only names
// the rows, and picks the country when the browser does not say.
// Same catalogue as the Android app (ChartCatalog.kt, DeviceCountry.kt).

export interface ChartRow { key: string; title: string; query: string }

interface Local { countryChart: string; local: Array<[string, string]> }

const LOCAL: Record<string, Local> = {
  NL: { countryChart: "Top Netherlands", local: [["Top 40 Nederland", "Top 40 Nederland"], ["Nederlandse hits", "Nederlandse hits"], ["Nederlandstalig", "Nederlandstalig"], ["Nederhop", "Nederhop"], ["Feestkrakers", "Feest hits nederlands"], ["Top 2000", "Top 2000"]] },
  BE: { countryChart: "Top Belgium", local: [["Ultratop 50", "Ultratop 50"], ["Belgische hits", "Belgische hits"], ["Vlaamse hits", "Vlaamse hits"], ["Hits belges", "Hits belges"], ["Nederlandstalig", "Nederlandstalig"]] },
  SR: { countryChart: "Top Suriname", local: [["Surinaamse hits", "Surinaamse muziek"], ["Kaseko", "Kaseko"], ["Kawina", "Kawina"], ["Nederlandse hits", "Nederlandse hits"]] },
  DE: { countryChart: "Top Germany", local: [["Deutsche Hits", "Deutsche Hits"], ["Deutschrap", "Deutschrap"], ["Deutschpop", "Deutschpop"], ["Schlager", "Schlager"], ["Ballermann Hits", "Ballermann"], ["Party Hits", "Party Hits Deutsch"]] },
  AT: { countryChart: "Top Austria", local: [["Austropop", "Austropop"], ["Deutsche Hits", "Deutsche Hits"], ["Deutschrap", "Deutschrap"], ["Schlager", "Schlager"]] },
  CH: { countryChart: "Top Switzerland", local: [["Schweizer Hits", "Schweizer Hits"], ["Mundart", "Mundart"], ["Deutsche Hits", "Deutsche Hits"], ["Rap français", "Rap français"]] },
  FR: { countryChart: "Top France", local: [["Hits du moment", "Hits du moment"], ["Rap français", "Rap français"], ["Chanson française", "Chanson française"], ["Variété française", "Variété française"], ["Soirée", "Soirée hits"]] },
  ES: { countryChart: "Top Spain", local: [["Éxitos España", "Éxitos España"], ["Reggaetón", "Reggaeton"], ["Pop español", "Pop español"], ["Flamenco", "Flamenco"], ["Fiesta", "Fiesta latina"]] },
  MX: { countryChart: "Top Mexico", local: [["Regional mexicano", "Regional mexicano"], ["Corridos", "Corridos tumbados"], ["Banda", "Banda"], ["Reggaetón", "Reggaeton"]] },
  AR: { countryChart: "Top Argentina", local: [["Trap argentino", "Trap argentino"], ["Cumbia", "Cumbia"], ["Rock nacional", "Rock nacional"], ["Cuarteto", "Cuarteto"]] },
  CO: { countryChart: "Top Colombia", local: [["Reggaetón", "Reggaeton"], ["Vallenato", "Vallenato"], ["Salsa", "Salsa"], ["Champeta", "Champeta"]] },
  IT: { countryChart: "Top Italy", local: [["Hit italiane", "Hit italiane"], ["Rap italiano", "Rap italiano"], ["Pop italiano", "Pop italiano"], ["Cantautori", "Cantautori italiani"]] },
  BR: { countryChart: "Top Brasil", local: [["Sertanejo", "Sertanejo"], ["Funk", "Funk brasileiro"], ["Pagode", "Pagode"], ["MPB", "MPB"], ["Piseiro", "Piseiro"]] },
  PT: { countryChart: "Top Portugal", local: [["Música portuguesa", "Música portuguesa"], ["Fado", "Fado"], ["Kizomba", "Kizomba"], ["Rap tuga", "Rap tuga"]] },
  PL: { countryChart: "Top Poland", local: [["Polskie hity", "Polskie hity"], ["Polski rap", "Polski rap"], ["Disco polo", "Disco polo"]] },
  TR: { countryChart: "Top Turkey", local: [["Türkçe pop", "Türkçe pop"], ["Türkçe rap", "Türkçe rap"], ["Arabesk", "Arabesk"]] },
  RU: { countryChart: "Top Russia", local: [["Русские хиты", "Русские хиты"], ["Русский рэп", "Русский рэп"], ["Русский поп", "Русский поп"]] },
  SA: { countryChart: "Top Saudi Arabia", local: [["Arabic hits", "Arabic hits"], ["Khaleeji", "Khaleeji"], ["Arabic pop", "Arabic pop"]] },
  AE: { countryChart: "Top United Arab Emirates", local: [["Arabic hits", "Arabic hits"], ["Khaleeji", "Khaleeji"], ["Bollywood", "Bollywood"]] },
  EG: { countryChart: "Top Egypt", local: [["Mahraganat", "Mahraganat"], ["Arabic hits", "Arabic hits"], ["Arabic pop", "Arabic pop"]] },
  MA: { countryChart: "Top Morocco", local: [["Rap marocain", "Rap marocain"], ["Raï", "Rai"], ["Chaabi", "Chaabi"]] },
  IN: { countryChart: "Top India", local: [["Bollywood", "Bollywood"], ["Punjabi", "Punjabi hits"], ["Hindi hits", "Hindi hits"]] },
  PK: { countryChart: "Top Pakistan", local: [["Coke Studio", "Coke Studio"], ["Punjabi", "Punjabi hits"], ["Urdu hits", "Urdu hits"]] },
  ID: { countryChart: "Top Indonesia", local: [["Pop Indonesia", "Pop Indonesia"], ["Dangdut", "Dangdut"], ["Koplo", "Dangdut koplo"]] },
  PH: { countryChart: "Top Philippines", local: [["OPM", "OPM"], ["P-Pop", "P-Pop"], ["OPM love songs", "OPM love songs"]] },
  JP: { countryChart: "Top Japan", local: [["J-Pop", "J-Pop"], ["Anime", "Anime songs"], ["City pop", "City pop"]] },
  KR: { countryChart: "Top South Korea", local: [["K-Pop", "K-Pop"], ["K-R&B", "Korean R&B"], ["K-Hip hop", "Korean hip hop"]] },
  TW: { countryChart: "Top Taiwan", local: [["C-Pop", "C-Pop"], ["Mandopop", "Mandopop"], ["Cantopop", "Cantopop"]] },
  GB: { countryChart: "Top UK", local: [["UK hits", "UK hits"], ["UK rap", "UK rap"], ["Britpop", "Britpop"]] },
  IE: { countryChart: "Top Ireland", local: [["Irish hits", "Irish hits"], ["Irish pub songs", "Irish pub songs"], ["UK hits", "UK hits"]] },
  US: { countryChart: "Top USA", local: [["US hits", "US hits"], ["Country", "Country hits"], ["Hip hop USA", "Hip hop hits"]] },
  CA: { countryChart: "Top Canada", local: [["Canadian hits", "Canadian hits"], ["Country", "Country hits"], ["Musique québécoise", "Musique québécoise"]] },
  AU: { countryChart: "Top Australia", local: [["Aussie hits", "Aussie hits"], ["Aussie rock", "Aussie rock"], ["Triple J", "Triple J hottest"]] },
  NZ: { countryChart: "Top New Zealand", local: [["Kiwi hits", "New Zealand hits"], ["Aussie hits", "Aussie hits"]] },
  ZA: { countryChart: "Top South Africa", local: [["Amapiano", "Amapiano"], ["Gqom", "Gqom"], ["SA hip hop", "South African hip hop"]] },
  NG: { countryChart: "Top Nigeria", local: [["Afrobeats", "Afrobeats"], ["Naija hits", "Naija hits"], ["Amapiano", "Amapiano"]] },
  SE: { countryChart: "Top Sweden", local: [["Svenska hits", "Svenska hits"], ["Svensk hiphop", "Svensk hiphop"]] },
  NO: { countryChart: "Top Norway", local: [["Norske hits", "Norske hits"], ["Norsk rap", "Norsk rap"]] },
  DK: { countryChart: "Top Denmark", local: [["Danske hits", "Danske hits"], ["Dansk rap", "Dansk rap"]] },
  FI: { countryChart: "Top Finland", local: [["Suomi hitit", "Suomi hitit"], ["Suomirap", "Suomirap"]] }
};

/** Deezer's spelling where its chart name is not the plain English country name. */
const CHART_NAMES: Record<string, string> = { US: "USA", GB: "UK" };

/** The country an app language stands for, when the browser gives no country. */
const LANGUAGE_COUNTRY: Record<string, string> = {
  nl: "NL", de: "DE", fr: "FR", es: "ES", it: "IT", "pt-BR": "BR", "pt-PT": "PT", pt: "BR",
  pl: "PL", tr: "TR", ru: "RU", ar: "SA", hi: "IN", ja: "JP", ko: "KR", zh: "TW",
  "en-GB": "GB", en: "US", sv: "SE", nb: "NO", da: "DK", fi: "FI", id: "ID"
};

/** The charts row's title, per app language. */
const CHARTS_TITLES: Record<string, string> = {
  nl: "Hitlijsten", fr: "Classements", es: "Listas de éxitos", it: "Classifiche", pt: "Paradas", "pt-PT": "Tabelas",
  pl: "Listy przebojów", tr: "Listeler", ru: "Чарты", ar: "الأكثر استماعًا", ja: "チャート", ko: "차트", zh: "排行榜"
};

export const ZONES: Record<string, string> = {
  "Europe/Amsterdam": "NL", "Europe/Brussels": "BE", "America/Paramaribo": "SR", "America/Curacao": "CW",
  "America/Aruba": "AW", "Europe/Berlin": "DE", "Europe/Vienna": "AT", "Europe/Zurich": "CH",
  "Europe/Paris": "FR", "Europe/Madrid": "ES", "Atlantic/Canary": "ES", "Europe/Rome": "IT",
  "Europe/Lisbon": "PT", "Europe/London": "GB", "Europe/Dublin": "IE", "Europe/Luxembourg": "LU",
  "Europe/Copenhagen": "DK", "Europe/Oslo": "NO", "Europe/Stockholm": "SE", "Europe/Helsinki": "FI",
  "Europe/Warsaw": "PL", "Europe/Prague": "CZ", "Europe/Budapest": "HU", "Europe/Bucharest": "RO",
  "Europe/Sofia": "BG", "Europe/Athens": "GR", "Europe/Istanbul": "TR", "Europe/Moscow": "RU",
  "Europe/Kiev": "UA", "Europe/Kyiv": "UA", "Europe/Belgrade": "RS", "Europe/Zagreb": "HR",
  "America/New_York": "US", "America/Chicago": "US", "America/Denver": "US", "America/Los_Angeles": "US",
  "America/Phoenix": "US", "America/Anchorage": "US", "Pacific/Honolulu": "US", "America/Detroit": "US",
  "America/Toronto": "CA", "America/Vancouver": "CA", "America/Edmonton": "CA", "America/Winnipeg": "CA",
  "America/Halifax": "CA", "America/Montreal": "CA", "America/Mexico_City": "MX", "America/Monterrey": "MX",
  "America/Tijuana": "MX", "America/Cancun": "MX", "America/Bogota": "CO", "America/Lima": "PE",
  "America/Santiago": "CL", "America/Argentina/Buenos_Aires": "AR", "America/Buenos_Aires": "AR", "America/Sao_Paulo": "BR",
  "America/Caracas": "VE", "America/Guayaquil": "EC", "America/Montevideo": "UY", "America/Port_of_Spain": "TT",
  "America/Jamaica": "JM", "America/Santo_Domingo": "DO", "America/Puerto_Rico": "PR", "Asia/Kolkata": "IN",
  "Asia/Calcutta": "IN", "Asia/Karachi": "PK", "Asia/Dhaka": "BD", "Asia/Tokyo": "JP",
  "Asia/Seoul": "KR", "Asia/Taipei": "TW", "Asia/Hong_Kong": "HK", "Asia/Shanghai": "CN",
  "Asia/Singapore": "SG", "Asia/Kuala_Lumpur": "MY", "Asia/Jakarta": "ID", "Asia/Manila": "PH",
  "Asia/Bangkok": "TH", "Asia/Ho_Chi_Minh": "VN", "Asia/Saigon": "VN", "Asia/Dubai": "AE",
  "Asia/Riyadh": "SA", "Asia/Qatar": "QA", "Asia/Kuwait": "KW", "Asia/Jerusalem": "IL",
  "Asia/Tehran": "IR", "Asia/Baghdad": "IQ", "Asia/Beirut": "LB", "Asia/Amman": "JO",
  "Africa/Cairo": "EG", "Africa/Casablanca": "MA", "Africa/Algiers": "DZ", "Africa/Tunis": "TN",
  "Africa/Lagos": "NG", "Africa/Accra": "GH", "Africa/Nairobi": "KE", "Africa/Johannesburg": "ZA",
  "Australia/Sydney": "AU", "Australia/Melbourne": "AU", "Australia/Brisbane": "AU", "Australia/Perth": "AU",
  "Australia/Adelaide": "AU", "Pacific/Auckland": "NZ"
};

const isCountry = (code: string | null | undefined): code is string => typeof code === "string" && /^[A-Za-z]{2}$/.test(code);

/** The browser's country without asking: its time zone, else the first language with a region ("nl-BE"). */
export function browserCountry(timeZone: string | undefined, languages: readonly string[]): string | null {
  const zone = timeZone ? ZONES[timeZone] : undefined;
  if (zone) return zone;
  for (const language of languages) {
    const region = language.replace("_", "-").split("-").slice(1).find((part) => isCountry(part));
    if (region) return region.toUpperCase();
  }
  return null;
}

/** The country whose charts lead: the device's, else the language's region, else the language's country. */
export function chartCountry(languageTag: string, deviceCountry?: string | null): string {
  const device = deviceCountry?.trim();
  if (isCountry(device)) return device.toUpperCase();
  const tag = languageTag.replace("_", "-");
  const region = tag.split("-").slice(1).find((part) => isCountry(part))?.toUpperCase();
  if (region && LOCAL[region]) return region;
  const language = tag.split("-")[0].toLowerCase();
  return LANGUAGE_COUNTRY[tag] ?? LANGUAGE_COUNTRY[language] ?? region ?? "US";
}

function countryName(code: string, language: string): string | null {
  try {
    const name = new Intl.DisplayNames([language], { type: "region" }).of(code);
    return name && name !== code ? name : null;
  } catch {
    return null;
  }
}

const GENERAL: Array<[string, string]> = [
  ["top40", "Top 40"], ["worldwide", "Top Worldwide"], ["new", "New releases"], ["viral", "Viral hits"],
  ["gym", "Gym hits"], ["workout", "Workout"], ["running", "Running"], ["chill", "Chill"],
  ["party", "Party"], ["throwback", "Throwback"], ["80s", "80s hits"], ["90s", "90s hits"],
  ["2000s", "2000s hits"], ["2010s", "2010s hits"], ["hiphop", "Hip hop"], ["rnb", "R&B"],
  ["dance", "Dance hits"], ["house", "House"], ["techno", "Techno"], ["edm", "EDM"],
  ["rock", "Rock"], ["classicrock", "Classic rock"], ["metal", "Metal"], ["indie", "Indie"],
  ["pop", "Pop hits"], ["latin", "Latin hits"], ["afro", "Afrobeats"], ["reggae", "Reggae"],
  ["jazz", "Jazz"], ["classical", "Classical"], ["lofi", "Lo-fi"], ["focus", "Focus"],
  ["sleep", "Sleep"], ["acoustic", "Acoustic"], ["love", "Love songs"], ["sad", "Sad songs"],
  ["roadtrip", "Road trip"], ["soul", "Soul"], ["piano", "Piano"], ["kids", "Kids"],
  ["summer", "Summer hits"], ["feelgood", "Feel good"], ["christmas", "Christmas hits"]
];

const TITLES: Record<string, Record<string, string>> = {
  nl: { worldwide: "Wereldwijd top", new: "Nieuwe releases", viral: "Viral hits", gym: "Sportschool", running: "Hardlopen", party: "Feest", throwback: "Throwback", love: "Liefdesliedjes", sad: "Verdrietig", roadtrip: "Onderweg", sleep: "Slapen", summer: "Zomerhits", feelgood: "Goed gevoel", christmas: "Kersthits", classical: "Klassiek" },
  de: { worldwide: "Weltweit top", new: "Neuerscheinungen", gym: "Gym Hits", running: "Laufen", party: "Party", love: "Liebeslieder", sad: "Traurige Songs", roadtrip: "Roadtrip", sleep: "Schlafen", summer: "Sommerhits", feelgood: "Gute Laune", christmas: "Weihnachtshits", classical: "Klassik", kids: "Kinder" },
  fr: { worldwide: "Top mondial", new: "Nouveautés", gym: "Muscu", running: "Course à pied", party: "Soirée", love: "Chansons d'amour", sad: "Chansons tristes", sleep: "Dormir", summer: "Tubes de l'été", christmas: "Chansons de Noël", classical: "Classique", kids: "Enfants" },
  es: { worldwide: "Top mundial", new: "Novedades", gym: "Gimnasio", running: "Correr", party: "Fiesta", love: "Canciones de amor", sad: "Canciones tristes", sleep: "Dormir", summer: "Éxitos del verano", christmas: "Navidad", classical: "Clásica", kids: "Niños" },
  it: { worldwide: "Top mondiale", new: "Nuove uscite", gym: "Palestra", running: "Corsa", party: "Festa", love: "Canzoni d'amore", sleep: "Dormire", summer: "Hit dell'estate", christmas: "Natale", classical: "Classica" },
  pt: { worldwide: "Top mundial", new: "Lançamentos", gym: "Academia", running: "Corrida", party: "Festa", love: "Músicas de amor", sleep: "Dormir", summer: "Hits do verão", christmas: "Natal", classical: "Clássica" }
};

const PINNED_GENERAL = 3;

/** A small seeded generator, so a day's order is the same for everyone and every reload. */
function seeded(seed: number) {
  let a = seed >>> 0;
  return () => {
    a = (a + 0x6d2b79f5) >>> 0;
    let t = a;
    t = Math.imul(t ^ (t >>> 15), t | 1);
    t ^= t + Math.imul(t ^ (t >>> 7), t | 61);
    return ((t ^ (t >>> 14)) >>> 0) / 4294967296;
  };
}

/** date: "YYYY-MM-DD". */
export function chartRows(languageTag: string, date: string, deviceCountry?: string | null): ChartRow[] {
  const tag = languageTag.replace("_", "-");
  const language = tag.split("-")[0].toLowerCase();
  const country = chartCountry(tag, deviceCountry);
  const local = LOCAL[country];
  const englishName = CHART_NAMES[country] ?? countryName(country, "en") ?? country;
  const chartQuery = local?.countryChart ?? `Top ${englishName}`;
  const shownName = countryName(country, tag) ?? englishName;
  const titles = TITLES[language] ?? {};
  const [year, month, day] = date.split("-").map(Number);
  const general = GENERAL
    .filter(([key]) => key !== "christmas" || month === 12 || (month === 11 && day >= 15))
    .filter(([key]) => key !== "summer" || (month >= 5 && month <= 8))
    .map(([key, query]) => ({ key, title: titles[key] ?? query, query }));
  const pinned: ChartRow[] = [
    { key: "country", title: `Top ${shownName}`, query: chartQuery },
    // Deezer's own chart list follows the server's location, so the country's chart is searched instead.
    { key: "charts", title: CHARTS_TITLES[tag] ?? CHARTS_TITLES[language] ?? "Charts", query: `Top 50 ${chartQuery.replace(/^Top /, "")}` },
    ...(local?.local ?? []).map(([title, query], i) => ({ key: `local${i}`, title, query })),
    ...general.slice(0, PINNED_GENERAL),
    { key: "year", title: `Hits ${year}`, query: `Hits ${year}` }
  ];
  const rotating = general.slice(PINNED_GENERAL);
  const random = seeded(Math.floor(Date.UTC(year, month - 1, day) / 86_400_000));
  for (let i = rotating.length - 1; i > 0; i--) {
    const j = Math.floor(random() * (i + 1));
    [rotating[i], rotating[j]] = [rotating[j], rotating[i]];
  }
  return [...pinned, ...rotating];
}

/** The titles of the personal rows: "because you listened to {artist}", "based on your searches", a mix's subtitle. */
export interface PersonalTitles { because: string; searches: string; mix: string }

const PERSONAL: Record<string, PersonalTitles> = {
  nl: { because: "Omdat je luisterde naar %s", searches: "Op basis van je zoekopdrachten", mix: "Mix" },
  de: { because: "Weil du %s gehört hast", searches: "Basierend auf deinen Suchen", mix: "Mix" },
  fr: { because: "Parce que vous avez écouté %s", searches: "D'après vos recherches", mix: "Mix" },
  es: { because: "Porque escuchaste a %s", searches: "Según tus búsquedas", mix: "Mix" },
  it: { because: "Perché hai ascoltato %s", searches: "In base alle tue ricerche", mix: "Mix" },
  pt: { because: "Porque você ouviu %s", searches: "Com base nas suas pesquisas", mix: "Mix" },
  pl: { because: "Bo słuchałeś: %s", searches: "Na podstawie twoich wyszukiwań", mix: "Mix" },
  tr: { because: "%s dinlediğin için", searches: "Aramalarına göre", mix: "Mix" }
};

export function personalTitles(languageTag: string): PersonalTitles {
  return PERSONAL[languageTag.replace("_", "-").split("-")[0].toLowerCase()] ?? { because: "Because you listened to %s", searches: "Based on your searches", mix: "Mix" };
}
