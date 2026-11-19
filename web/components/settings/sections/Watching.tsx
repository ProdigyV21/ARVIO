"use client";
import { ExternalLink, Eye, EyeOff, KeyRound, Plus, Trash2 } from "lucide-react";
import { useRef, useState } from "react";
import { useTranslation } from "@/lib/i18n";
import { CONTENT_LANGUAGE_OPTIONS } from "@/lib/i18n/languageOptions";
import { languageCode, TRACK_LANGUAGES, trackLanguageName } from "@/lib/languages";
import { useApp, useAppPick } from "@/lib/store";
import type { AppSettings, QualityFilterConfig } from "@/lib/types";
import { Group, Row, SecretInput, Segmented, Select, SectionHeader, Slider, Status, Swatches, Toggle, safeArray } from "../ui";

type Set = (patch: Partial<AppSettings>) => void;

function useSettings(): [AppSettings, Set] {
  const app = useApp();
  return [app.settings, app.updateSettings];
}

/* ---------- Playback ---------- */

const QUALITY_PRESETS: Array<[AppSettings["qualityFilterPreset"], string]> = [
  ["off", "Off"],
  ["1080p-plus", "1080p and above"],
  ["1080p-only", "1080p only"],
  ["720p-plus", "720p and above"],
  ["custom", "Custom"],
];

function qualityPresetFilters(preset: AppSettings["qualityFilterPreset"]): QualityFilterConfig[] {
  const poorSources = "cam|hdcam|camrip|ts|hdts|telesync|tc|hdtc|telecine|screener|scr|dvdscr|r5";
  const make = (id: string, deviceName: string, regexPattern: string): QualityFilterConfig[] =>
    [{ id, deviceName, regexPattern, enabled: true, createdAt: Date.now() }];
  switch (preset) {
    case "1080p-plus": return make("preset-quality-1080-plus", "Preset: 1080p+", `(?:360|480|576|720)p|${poorSources}`);
    case "1080p-only": return make("preset-quality-1080-only", "Preset: 1080p only", `(?:2160|4k|uhd)|(?:360|480|576|720)p|${poorSources}`);
    case "720p-plus": return make("preset-quality-720-plus", "Preset: 720p+", `(?:360|480|576)p|${poorSources}`);
    default: return [];
  }
}

const MAX_SIZES = [0, 1, 2, 3, 5, 10, 15, 20, 30, 50];

export function PlaybackSection() {
  const translateUi = useTranslation();
  const { setToast } = useAppPick("setToast");
  const [settings, set] = useSettings();
  const [filterName, setFilterName] = useState("");
  const [filterPattern, setFilterPattern] = useState("");
  const filters = safeArray(settings.qualityFilters);
  const editFilters = (next: QualityFilterConfig[]) => set({ qualityFilterPreset: "custom", qualityFilters: next });
  const addFilter = () => {
    const pattern = filterPattern.trim();
    if (!pattern) { setToast("Enter a quality filter regex first."); return; }
    try { new RegExp(pattern, "i"); } catch { setToast("That isn't a valid regular expression."); return; }
    editFilters([{ id: crypto.randomUUID(), deviceName: filterName.trim() || "Custom quality filter", regexPattern: pattern, enabled: true, createdAt: Date.now() }, ...filters]);
    setFilterName("");
    setFilterPattern("");
  };
  return (
    <>
      <SectionHeader title="Playback" description="How ARVIO picks and starts a source." />
      <Group title="Auto-play">
        <Row id="autoplay-next" label="Auto-play next episode" scope="all">
          <Toggle label="Auto-play next episode" value={settings.autoPlayNext} onChange={(v) => set({ autoPlayNext: v })} />
        </Row>
        <Row id="autoplay-single" label="Auto-play single source" hint="Start playing straight away when only one source is found." scope="all">
          <Toggle label="Auto-play single source" value={settings.autoPlaySingleSource} onChange={(v) => set({ autoPlaySingleSource: v })} />
        </Row>
        <Row id="autoplay-min-quality" label="Minimum quality" hint="Auto-play skips sources below this quality." scope="all">
          <Select label="Minimum quality" value={settings.autoPlayMinQuality} onChange={(v) => set({ autoPlayMinQuality: v })}
            options={[["any", "Any"], ["hd", "720p"], ["fhd", "1080p"], ["4k", "4K"]]} />
        </Row>
        <Row id="autoplay-max-quality" label="Maximum quality" hint="Useful on a slow connection or a TV that struggles with 4K." scope="android">
          <Select label="Maximum quality" value={settings.autoPlayMaxQuality ?? "Unlimited"} onChange={(v) => set({ autoPlayMaxQuality: v })}
            options={[["Unlimited", "No limit"], ["720p", "720p"], ["1080p", "1080p"], ["4K", "4K"]]} />
        </Row>
        <Row id="autoplay-max-size" label="Maximum file size" scope="android">
          <Select label="Maximum file size" value={String(settings.autoPlayMaxSizeGb ?? 0)} onChange={(v) => set({ autoPlayMaxSizeGb: Number(v) })}
            options={MAX_SIZES.map((gb) => [String(gb), gb === 0 ? "No limit" : `${gb} GB`])} />
        </Row>
        <Row id="include-specials" label="Include specials" hint="Count season 0 episodes when moving to the next episode." scope="all">
          <Toggle label="Include specials" value={settings.includeSpecials} onChange={(v) => set({ includeSpecials: v })} />
        </Row>
      </Group>

      <Group title="Finding sources">
        <Row id="search-mode" label="Search mode" hint={settings.streamSearchMode === "sequential" ? "Search integrations by priority." : "Search all integrations simultaneously."} scope="android">
          <Segmented label="Search mode" value={settings.streamSearchMode} onChange={(v) => set({ streamSearchMode: v })} options={[["parallel", "Parallel"], ["sequential", "Sequential"]]} />
        </Row>
        <Row id="quality-filter" label="Hide sources" hint="Hide low-quality and camera releases from the source list." scope="web">
          <Select label="Hide sources" value={settings.qualityFilterPreset} options={QUALITY_PRESETS}
            onChange={(preset) => set({ qualityFilterPreset: preset, qualityFilters: preset === "custom" ? settings.qualityFilters : qualityPresetFilters(preset) })} />
        </Row>
        {settings.qualityFilterPreset === "custom" && (
          <>
            <div className="st-form">
              <input className="st-input" value={filterName} onChange={(e) => setFilterName(e.target.value)} placeholder={translateUi("Filter name")} aria-label={translateUi("Filter name")} />
              <input className="st-input" value={filterPattern} onChange={(e) => setFilterPattern(e.target.value)} placeholder={translateUi("Regex to hide matching sources")} aria-label={translateUi("Regex to hide matching sources")}
                onKeyDown={(e) => { if (e.key === "Enter") addFilter(); }} />
              <div className="st-form-actions"><button type="button" className="st-btn" onClick={addFilter}><Plus size={16} /> {translateUi("Add filter")}</button></div>
            </div>
            {filters.length > 0 && (
              <ul className="st-list">
                {filters.map((filter) => (
                  <li className={`st-item ${filter.enabled ? "" : "is-off"}`} key={filter.id}>
                    <button type="button" className="st-icon-btn" aria-label={translateUi(filter.enabled ? "Turn off {value0}" : "Turn on {value0}", { value0: filter.deviceName })}
                      onClick={() => editFilters(filters.map((item) => item.id === filter.id ? { ...item, enabled: !item.enabled } : item))}>
                      {filter.enabled ? <Eye size={17} /> : <EyeOff size={17} />}
                    </button>
                    <div className="st-item-main">
                      <input className="st-input is-wide" aria-label={translateUi("Filter name")} value={filter.deviceName}
                        onChange={(e) => editFilters(filters.map((item) => item.id === filter.id ? { ...item, deviceName: e.target.value } : item))} />
                      <input className="st-input is-wide" aria-label={translateUi("Regex to hide matching sources")} value={filter.regexPattern}
                        onChange={(e) => editFilters(filters.map((item) => item.id === filter.id ? { ...item, regexPattern: e.target.value } : item))} />
                    </div>
                    <button type="button" className="st-icon-btn is-danger" aria-label={translateUi("Remove {value0}", { value0: filter.deviceName })}
                      onClick={() => editFilters(filters.filter((item) => item.id !== filter.id))}><Trash2 size={17} /></button>
                  </li>
                ))}
              </ul>
            )}
          </>
        )}
      </Group>

      <Group title="Trailers" description="Trailer previews on the Android and TV home screen.">
        <Row id="trailer-autoplay" label="Trailer auto-play" hint="Automatically preview trailers on Home." scope="android">
          <Toggle label="Trailer auto-play" value={settings.trailerAutoPlay} onChange={(v) => set({ trailerAutoPlay: v })} />
        </Row>
        <Row id="trailer-sound" label="Trailer sound" scope="android" disabled={!settings.trailerAutoPlay}>
          <Toggle label="Trailer sound" value={settings.trailerSound} disabled={!settings.trailerAutoPlay} onChange={(v) => set({ trailerSound: v })} />
        </Row>
        <Row id="trailer-delay" label="Trailer delay" hint="Seconds before the trailer starts playing." scope="android" disabled={!settings.trailerAutoPlay}>
          <Segmented label="Trailer delay" value={String(settings.trailerDelaySeconds)} disabled={!settings.trailerAutoPlay} onChange={(v) => set({ trailerDelaySeconds: Number(v) })}
            options={[["0", "0 s"], ["1", "1 s"], ["2", "2 s"], ["3", "3 s"], ["5", "5 s"]]} />
        </Row>
        <Row id="trailer-in-cards" label="Trailers in cards" hint="Preview trailers in the expanded card instead of the hero." scope="android" disabled={!settings.trailerAutoPlay}>
          <Toggle label="Trailers in cards" value={settings.trailerInCards} disabled={!settings.trailerAutoPlay} onChange={(v) => set({ trailerInCards: v })} />
        </Row>
      </Group>
    </>
  );
}

/* ---------- Languages ---------- */

const languageOptions = (extra: Array<[string, string]>, current: string): Array<[string, string]> => {
  const names: Array<[string, string]> = TRACK_LANGUAGES.map((name) => [name, name]);
  const options = [...extra, ...names];
  if (current && !options.some(([value]) => value === current)) options.splice(extra.length, 0, [current, current]);
  return options;
};

/** The stored value (a code like "en" from older web builds, or Android's
 *  display name) as the option value the picker uses. */
function subtitleValue(value: string) {
  if (!value || !languageCode(value)) return value?.toLowerCase() === "forced" ? "Forced" : "Off";
  return trackLanguageName(value) || value;
}

function audioValue(value: string) {
  if (!value || value.toLowerCase().startsWith("auto")) return "Auto (Original)";
  if (value.toLowerCase() === "none") return "None";
  return trackLanguageName(value) || value;
}

export function LanguageSection() {
  const [settings, set] = useSettings();
  const subtitle = subtitleValue(settings.defaultSubtitle);
  const secondary = subtitleValue(settings.secondarySubtitle);
  const audio = audioValue(settings.audioLanguage);
  const content = settings.language;
  const contentOptions = CONTENT_LANGUAGE_OPTIONS.some(([value]) => value === content) ? CONTENT_LANGUAGE_OPTIONS : [[content, content] as [string, string], ...CONTENT_LANGUAGE_OPTIONS];
  return (
    <>
      <SectionHeader title="Languages" description="Your preferred languages for menus, titles, audio and subtitles." />
      <Group>
        <Row id="content-language" label="App and metadata language" hint="Menus here and titles, descriptions and artwork from TMDB." scope="all">
          <Select label="App and metadata language" translateLabels={false} value={content} onChange={(v) => set({ language: v })} options={contentOptions} />
        </Row>
        <Row id="audio-language" label="Audio language" hint="Preferred audio track. Original keeps the film's own language." scope="all">
          <Select label="Audio language" value={audio} onChange={(v) => set({ audioLanguage: v === "Auto (Original)" ? "" : v })}
            options={languageOptions([["Auto (Original)", "Original"], ["None", "No preference"]], audio)} />
        </Row>
        <Row id="subtitle-language" label="Subtitle language" hint="Turned on automatically when available. Also the language AI subtitles translate into." scope="all">
          <Select label="Subtitle language" value={subtitle} onChange={(v) => set({ defaultSubtitle: v === "Off" ? "" : v })}
            options={languageOptions([["Off", "Off"], ["Forced", "Forced only"]], subtitle)} />
        </Row>
        <Row id="secondary-subtitle" label="Second subtitle language" hint="Used when the first isn't available." scope="all">
          <Select label="Second subtitle language" value={secondary} onChange={(v) => set({ secondarySubtitle: v === "Off" ? "" : v })}
            options={languageOptions([["Off", "Off"]], secondary)} />
        </Row>
        <Row id="filter-subtitles" label="Only list my subtitle languages" hint="Hide other languages in the subtitle picker." scope="all">
          <Toggle label="Only list my subtitle languages" value={settings.filterSubtitlesByLanguage} onChange={(v) => set({ filterSubtitlesByLanguage: v })} />
        </Row>
        <Row id="forced-subtitles" label="Forced subtitles" hint="Prefer forced subtitles when the audio matches the subtitle language; if there are none, show nothing." scope="android">
          <Toggle label="Forced subtitles" value={settings.useForcedSubtitles} onChange={(v) => set({ useForcedSubtitles: v })} />
        </Row>
      </Group>
    </>
  );
}

/* ---------- Subtitle appearance ---------- */

const SUB_SIZES: Array<[string, string, number]> = [["Small", "Small", 80], ["Medium", "Medium", 100], ["Large", "Large", 140], ["Extra Large", "Extra large", 170]];
const SUB_COLORS: Array<[AppSettings["subtitleColorName"], string, string]> = [
  ["White", "White", "#ffffff"], ["Yellow", "Yellow", "#ffeb3b"], ["Green", "Green", "#4caf50"], ["Cyan", "Cyan", "#00bcd4"],
];
const WEB_COLORS: Array<[AppSettings["subtitleColorName"], string, string]> = [
  ["Red", "Red", "#f44336"], ["Orange", "Orange", "#ff9800"], ["Blue", "Blue", "#2196f3"], ["Violet", "Violet", "#8b5cf6"],
];
const SUB_FONTS = ["System", "Noto Sans", "Atkinson Hyperlegible", "Lexend", "Roboto Condensed", "Nunito Sans", "Quicksand", "Rubik", "Varela Round"];

function sizeName(pct: number) {
  if (pct <= 85) return "Small";
  if (pct >= 160) return "Extra Large";
  if (pct >= 125) return "Large";
  return "Medium";
}

export function SubtitlesSection() {
  const translateUi = useTranslation();
  const [settings, set] = useSettings();
  const all = [...SUB_COLORS, ...WEB_COLORS];
  const knownColor = all.find(([name, , hex]) => name === settings.subtitleColorName && hex.toLowerCase() === settings.subtitleColor.toLowerCase());
  const style = settings.subtitleStyle === "raised" ? "shadow" : settings.subtitleStyle;
  const pct = Math.max(60, Math.min(200, settings.subtitleSize || 100));
  return (
    <>
      <SectionHeader title="Subtitle style" description="How subtitles look on screen." />
      <figure className="st-sub-preview" aria-label={translateUi("Subtitle preview")}>
        <figcaption>{translateUi("Preview")}</figcaption>
        <span className={`subtitle-preview-text st-sub-text subtitle-style-${style} st-sub-pos-${settings.subtitleOffset}`}
          style={{ color: settings.subtitleColor, fontSize: `calc(${pct / 100} * clamp(15px, 2.2vw, 24px))`, fontFamily: settings.subtitleFont && settings.subtitleFont !== "System" ? `"${settings.subtitleFont}", system-ui, sans-serif` : undefined }}>
          {translateUi("This is how subtitles will appear.")}
        </span>
      </figure>
      <Group>
        <Row id="subtitle-size" label="Size" scope="all">
          <Segmented label="Subtitle size" value={sizeName(pct)} onChange={(v) => set({ subtitleSize: SUB_SIZES.find(([name]) => name === v)?.[2] ?? 100 })}
            options={SUB_SIZES.map(([value, label]) => [value, label])} />
        </Row>
        <Row id="subtitle-color" label="Color" scope="all" hint="White, yellow, green and cyan also apply on your TV.">
          <Swatches label="Subtitle color" value={knownColor ? knownColor[0] : null} options={all}
            onChange={(name) => set({ subtitleColorName: name, subtitleColor: all.find(([n]) => n === name)?.[2] ?? "#ffffff" })} />
        </Row>
        <Row id="subtitle-custom-color" label="Custom color" scope="web">
          <input className="st-color" type="color" aria-label={translateUi("Custom subtitle color")} value={settings.subtitleColor}
            onChange={(e) => set({ subtitleColor: e.target.value, subtitleColorName: "White" })} />
        </Row>
        <Row id="subtitle-style" label="Style" scope="all">
          <Segmented label="Subtitle style" value={style} onChange={(v) => set({ subtitleStyle: v })}
            options={[["outline", "Bold outline"], ["shadow", "Normal"], ["background", "Background"]]} />
        </Row>
        <Row id="subtitle-position" label="Position" scope="all">
          <Segmented label="Subtitle position" value={settings.subtitleOffset} onChange={(v) => set({ subtitleOffset: v })}
            options={[["bottom", "Bottom"], ["low", "Low"], ["medium", "Middle"], ["high", "Top"]]} />
        </Row>
        <Row id="subtitle-font" label="Font" scope="android">
          <Select label="Subtitle font" translateLabels={false} value={settings.subtitleFont || "System"} onChange={(v) => set({ subtitleFont: v })}
            options={SUB_FONTS.map((font) => [font, font])} />
        </Row>
        <Row id="subtitle-stylized" label="Keep subtitle file styling" hint="Honor embedded ASS/SSA styles when using the System font. Bundled font choices override embedded styling." scope="all">
          <Toggle label="Keep subtitle file styling" value={settings.subtitleStylized} onChange={(v) => set({ subtitleStylized: v })} />
        </Row>
      </Group>
      <Group title="Text and timing">
        <Row id="remove-sdh" label="Remove hearing-impaired tags" hint="Strip [MUSIC], (sighs) and speaker names." scope="all">
          <Toggle label="Remove hearing-impaired tags" value={settings.removeHearingImpaired} onChange={(v) => set({ removeHearingImpaired: v })} />
        </Row>
        <Row id="subtitle-preload" label="Preload subtitles" hint="Wait for subtitles before starting the video so switching them is instant." scope="android">
          <Toggle label="Preload subtitles" value={settings.subtitlePreload} onChange={(v) => set({ subtitlePreload: v })} />
        </Row>
        <Row id="subtitle-delay" label="Subtitle delay" hint="Shift subtitles earlier or later in this player." scope="web">
          <Slider label="Subtitle delay" value={settings.subtitleOffsetMs} min={-5000} max={5000} step={100}
            format={(v) => `${v > 0 ? "+" : ""}${(v / 1000).toFixed(1)} s`} onChange={(v) => set({ subtitleOffsetMs: v })} />
        </Row>
      </Group>
    </>
  );
}

/* ---------- AI subtitles ---------- */

const PROVIDERS = {
  groq: { name: "Groq", keyUrl: "https://console.groq.com/keys", note: "Free, very fast. About 30 requests a minute on the free plan." },
  gemini: { name: "Gemini", keyUrl: "https://aistudio.google.com/apikey", note: "Google's free tier. Handles more languages well." },
} as const;

async function testKey(provider: "groq" | "gemini", key: string): Promise<"ok" | "bad" | "limit" | "error"> {
  try {
    const response = provider === "groq"
      ? await fetch("https://api.groq.com/openai/v1/models", { headers: { Authorization: `Bearer ${key}` } })
      : await fetch("https://generativelanguage.googleapis.com/v1beta/models?pageSize=1", { headers: { "x-goog-api-key": key } });
    if (response.ok) return "ok";
    if (response.status === 429) return "limit";
    if (response.status === 400 || response.status === 401 || response.status === 403) return "bad";
    return "error";
  } catch {
    return "error";
  }
}

export function AiSubtitlesSection({ openSection }: { openSection: (id: string) => void }) {
  const translateUi = useTranslation();
  const [settings, set] = useSettings();
  const provider = settings.aiSubtitleModel === "gemini" ? "gemini" : "groq";
  const [check, setCheck] = useState<null | "busy" | "ok" | "bad" | "limit" | "error">(null);
  // A result only counts for the key and provider it was run with.
  const checkRun = useRef(0);
  const target = trackLanguageName(settings.defaultSubtitle);
  const targetCode = languageCode(settings.defaultSubtitle);
  const info = PROVIDERS[provider];
  return (
    <>
      <SectionHeader title="AI subtitles" description="When a film has English subtitles but none in your language, ARVIO can translate them as you watch. It uses your own free API key, so there's nothing to pay." />
      <Group>
        <Row id="ai-enabled" label="AI subtitle translation" hint="Translate subtitles in real time using Groq or Gemini." scope="all">
          <Toggle label="AI subtitle translation" value={settings.aiSubtitlesEnabled} onChange={(v) => set({ aiSubtitlesEnabled: v, ...(settings.aiSubtitleModel === "off" ? { aiSubtitleModel: "groq" } : {}) })} />
        </Row>
        <Row id="ai-target" label="Translate into" scope="all"
          hint={!targetCode ? "Choose a subtitle language first." : targetCode === "en" ? "Your subtitle language is English, so there's nothing to translate. Pick another language to use this." : "Your subtitle language. Change it in Languages."}>
          {targetCode ? <Status tone={targetCode === "en" ? "warn" : "ok"}>{target}</Status> : <Status tone="warn">{translateUi("Off")}</Status>}
          <button type="button" className="st-btn is-ghost" onClick={() => openSection("language")}>{translateUi("Change")}</button>
        </Row>
      </Group>

      <Group title="Provider and key" description="Both providers have a free tier. Create a key, paste it here, and it's used on ARVIO Web and your Android and TV app.">
        <div className="st-choice" role="radiogroup" aria-label={translateUi("AI provider")}>
          {(Object.keys(PROVIDERS) as Array<keyof typeof PROVIDERS>).map((id) => (
            <button key={id} type="button" role="radio" aria-checked={provider === id} className={provider === id ? "is-active" : ""}
              onClick={() => { set({ aiSubtitleModel: id }); checkRun.current++; setCheck(null); }}>
              <strong>{PROVIDERS[id].name}{provider === id && <Status tone="ok">{translateUi("Selected")}</Status>}</strong>
              <span>{translateUi(PROVIDERS[id].note)}</span>
            </button>
          ))}
        </div>
        <Row id="ai-key" label={`${info.name} API key`} stack scope="all"
          hint={<>{translateUi("Free from")} <a className="st-link" href={info.keyUrl} target="_blank" rel="noreferrer">{info.keyUrl.replace("https://", "")} <ExternalLink size={12} /></a>. {translateUi("The key is stored in your ARVIO account so your TV can use it too.")}</>}>
          <SecretInput label={`${info.name} API key`} value={settings.aiApiKey} placeholder="Paste your key" onChange={(v) => { set({ aiApiKey: v }); checkRun.current++; setCheck(null); }} />
          <button type="button" className="st-btn" disabled={!settings.aiApiKey || check === "busy"} onClick={async () => {
            const run = ++checkRun.current;
            setCheck("busy");
            const result = await testKey(provider, settings.aiApiKey);
            if (run === checkRun.current) setCheck(result);
          }}>
            <KeyRound size={16} /> {translateUi(check === "busy" ? "Checking…" : "Test key")}
          </button>
          {check === "ok" && <Status tone="ok">{translateUi("Key works")}</Status>}
          {check === "bad" && <Status tone="warn">{translateUi("{value0} rejected this key", { value0: info.name })}</Status>}
          {check === "limit" && <Status tone="warn">{translateUi("Key works, but the rate limit is reached right now")}</Status>}
          {check === "error" && <Status tone="warn">{translateUi("Couldn't reach {value0}. Try again.", { value0: info.name })}</Status>}
        </Row>
        <p className="st-note">{translateUi("Clearing the key here stops ARVIO Web using it. A TV that already has the key keeps it until you remove it on the TV.")}</p>
      </Group>

      <Group title="When to translate">
        <Row id="ai-auto" label="Translate automatically" hint="Pick AI subtitles on their own when your language is missing. Otherwise choose AI from the subtitle menu." scope="all">
          <Toggle label="Translate automatically" value={settings.aiAutoSelect} onChange={(v) => set({ aiAutoSelect: v })} />
        </Row>
        <Row id="ai-best-match" label="Find best match" hint="Auto-pick the best-synced subtitle from your addons." scope="android">
          <Toggle label="Find best match" value={settings.aiFindBestMatch} onChange={(v) => set({ aiFindBestMatch: v })} />
        </Row>
      </Group>
    </>
  );
}
