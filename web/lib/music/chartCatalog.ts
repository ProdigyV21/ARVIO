// Which rows the Music home shows for an app language: the country's own charts and genres first,
// then a long mix of moods, genres and decades whose order changes every day (the daily playlists).
// Same catalogue as the Android app (ChartCatalog.kt).

export interface ChartRow { key: string; title: string; query: string }

interface Local { countryChart: string; chartsTitle: string; local: Array<[string, string]> }

const LOCAL: Record<string, Local> = {
  nl: { countryChart: "Top Netherlands", chartsTitle: "Hitlijsten", local: [["Top 40 Nederland", "Top 40 Nederland"], ["Nederlandse hits", "Nederlandse hits"], ["Nederlandstalig", "Nederlandstalig"], ["Nederhop", "Nederhop"], ["Feestkrakers", "Feest hits nederlands"], ["Top 2000", "Top 2000"]] },
  de: { countryChart: "Top Germany", chartsTitle: "Charts", local: [["Deutsche Hits", "Deutsche Hits"], ["Deutschrap", "Deutschrap"], ["Deutschpop", "Deutschpop"], ["Schlager", "Schlager"], ["Ballermann Hits", "Ballermann"], ["Party Hits", "Party Hits Deutsch"]] },
  fr: { countryChart: "Top France", chartsTitle: "Classements", local: [["Hits du moment", "Hits du moment"], ["Rap français", "Rap français"], ["Chanson française", "Chanson française"], ["Variété française", "Variété française"], ["Soirée", "Soirée hits"]] },
  es: { countryChart: "Top Spain", chartsTitle: "Listas de éxitos", local: [["Éxitos España", "Éxitos España"], ["Reggaetón", "Reggaeton"], ["Pop español", "Pop español"], ["Flamenco", "Flamenco"], ["Fiesta", "Fiesta latina"]] },
  it: { countryChart: "Top Italy", chartsTitle: "Classifiche", local: [["Hit italiane", "Hit italiane"], ["Rap italiano", "Rap italiano"], ["Pop italiano", "Pop italiano"], ["Cantautori", "Cantautori italiani"]] },
  "pt-BR": { countryChart: "Top Brasil", chartsTitle: "Paradas", local: [["Sertanejo", "Sertanejo"], ["Funk", "Funk brasileiro"], ["Pagode", "Pagode"], ["MPB", "MPB"], ["Piseiro", "Piseiro"]] },
  "pt-PT": { countryChart: "Top Portugal", chartsTitle: "Tabelas", local: [["Música portuguesa", "Música portuguesa"], ["Fado", "Fado"], ["Kizomba", "Kizomba"], ["Rap tuga", "Rap tuga"]] },
  pl: { countryChart: "Top Poland", chartsTitle: "Listy przebojów", local: [["Polskie hity", "Polskie hity"], ["Polski rap", "Polski rap"], ["Disco polo", "Disco polo"]] },
  tr: { countryChart: "Top Turkey", chartsTitle: "Listeler", local: [["Türkçe pop", "Türkçe pop"], ["Türkçe rap", "Türkçe rap"], ["Arabesk", "Arabesk"]] },
  ru: { countryChart: "Top Russia", chartsTitle: "Чарты", local: [["Русские хиты", "Русские хиты"], ["Русский рэп", "Русский рэп"], ["Русский поп", "Русский поп"]] },
  ar: { countryChart: "Top Saudi Arabia", chartsTitle: "الأكثر استماعًا", local: [["Arabic hits", "Arabic hits"], ["Khaleeji", "Khaleeji"], ["Arabic pop", "Arabic pop"]] },
  hi: { countryChart: "Top India", chartsTitle: "Charts", local: [["Bollywood", "Bollywood"], ["Punjabi", "Punjabi hits"], ["Hindi hits", "Hindi hits"]] },
  ja: { countryChart: "Top Japan", chartsTitle: "チャート", local: [["J-Pop", "J-Pop"], ["Anime", "Anime songs"], ["City pop", "City pop"]] },
  ko: { countryChart: "Top South Korea", chartsTitle: "차트", local: [["K-Pop", "K-Pop"], ["K-R&B", "Korean R&B"], ["K-Hip hop", "Korean hip hop"]] },
  zh: { countryChart: "Top Taiwan", chartsTitle: "排行榜", local: [["C-Pop", "C-Pop"], ["Mandopop", "Mandopop"], ["Cantopop", "Cantopop"]] },
  "en-GB": { countryChart: "Top UK", chartsTitle: "Charts", local: [["UK hits", "UK hits"], ["UK rap", "UK rap"], ["Britpop", "Britpop"]] },
  en: { countryChart: "Top USA", chartsTitle: "Charts", local: [["US hits", "US hits"], ["Country", "Country hits"], ["Hip hop USA", "Hip hop hits"]] }
};

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

const COUNTRY_TITLES: Record<string, string> = {
  nl: "Top Nederland", de: "Top Deutschland", fr: "Top France", es: "Top España", it: "Top Italia",
  "pt-BR": "Top Brasil", "pt-PT": "Top Portugal", pl: "Top Polska", tr: "Top Türkiye"
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
export function chartRows(languageTag: string, date: string): ChartRow[] {
  const tag = languageTag.replace("_", "-");
  const language = tag.split("-")[0].toLowerCase();
  const local = LOCAL[tag] ?? LOCAL[language] ?? LOCAL.en;
  const titles = TITLES[language] ?? {};
  const [year, month, day] = date.split("-").map(Number);
  const general = GENERAL
    .filter(([key]) => key !== "christmas" || month === 12 || (month === 11 && day >= 15))
    .filter(([key]) => key !== "summer" || (month >= 5 && month <= 8))
    .map(([key, query]) => ({ key, title: titles[key] ?? query, query }));
  const pinned: ChartRow[] = [
    { key: "country", title: COUNTRY_TITLES[tag] ?? COUNTRY_TITLES[language] ?? local.countryChart, query: local.countryChart },
    // Deezer's own chart list follows the caller's location, so the language's country is searched instead.
    { key: "charts", title: local.chartsTitle, query: `Top 50 ${local.countryChart.replace(/^Top /, "")}` },
    ...local.local.map(([title, query], i) => ({ key: `local${i}`, title, query })),
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
