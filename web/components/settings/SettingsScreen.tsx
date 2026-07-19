"use client";

import {
  ArrowDown,
  ArrowUp,
  Captions,
  Check,
  ChevronDown,
  Cloud,
  Eye,
  EyeOff,
  Languages,
  LayoutGrid,
  ListVideo,
  LogOut,
  Menu,
  Network,
  Play,
  Plus,
  RefreshCw,
  RotateCcw,
  Server,
  Sparkles,
  Subtitles,
  Trash2,
  Tv,
  User,
  UserCircle,
} from "lucide-react";
import { Component, useEffect, useState, type ReactNode } from "react";
import { createPortal } from "react-dom";
import { defaultCatalogs, mergeCatalogs } from "@/lib/catalogs";
import {
  hasNetlifyBackendConfig,
  hasSupabaseConfig,
  hasTraktConfig,
} from "@/lib/config";
import { defaultSettings, useApp } from "@/lib/store";
import type {
  AppSettings,
  CatalogConfig,
  HomeServerConfig,
  IptvPlaylistEntry,
  QualityFilterConfig,
} from "@/lib/types";

const settingsKey = "arvio.web.settings";

const SECTIONS = [
  { id: "accounts", label: "Accounts", icon: Cloud },
  { id: "profiles", label: "Profiles", icon: User },
  { id: "playback", label: "Playback", icon: Play },
  { id: "language", label: "Language & Audio", icon: Languages },
  { id: "subtitles", label: "Subtitles", icon: Subtitles },
  { id: "ai", label: "AI Subtitles", icon: Captions },
  { id: "appearance", label: "Appearance", icon: LayoutGrid },
  { id: "network", label: "Network", icon: Network },
  { id: "tv", label: "TV (IPTV)", icon: Tv },
  { id: "homeserver", label: "Home Server", icon: Server },
  { id: "catalogs", label: "Catalogs", icon: ListVideo },
  { id: "addons", label: "Addons", icon: Sparkles },
] as const;

type SectionId = (typeof SECTIONS)[number]["id"];

const SUBTITLE_COLOR_HEX: Record<AppSettings["subtitleColorName"], string> = {
  White: "#ffffff",
  Yellow: "#ffeb3b",
  Green: "#4caf50",
  Cyan: "#00bcd4",
  Red: "#f44336",
  Orange: "#ff9800",
  Blue: "#2196f3",
  Violet: "#8b5cf6",
};

const QUALITY_PRESET_LABELS: Array<
  [AppSettings["qualityFilterPreset"], string]
> = [
  ["off", "Off"],
  ["1080p-plus", "1080p and above"],
  ["1080p-only", "1080p only"],
  ["720p-plus", "720p and above"],
  ["custom", "Custom"],
];

const CONTENT_LANGUAGE_OPTIONS: Array<[string, string]> = [
  ["en-US", "English (US)"],
  ["en-GB", "English (UK)"],
  ["nl-NL", "Dutch"],
  ["de-DE", "German"],
  ["fr-FR", "French"],
  ["es-ES", "Spanish"],
  ["it-IT", "Italian"],
  ["pt-PT", "Portuguese"],
  ["pt-BR", "Portuguese (Brazil)"],
  ["tr-TR", "Turkish"],
  ["pl-PL", "Polish"],
  ["sv-SE", "Swedish"],
  ["da-DK", "Danish"],
  ["fi-FI", "Finnish"],
  ["no-NO", "Norwegian"],
  ["ja-JP", "Japanese"],
  ["ko-KR", "Korean"],
  ["zh-CN", "Chinese (Simplified)"],
];

const TRACK_LANGUAGE_OPTIONS: Array<[string, string]> = [
  ["", "Off / Auto"],
  ["en", "English"],
  ["nl", "Dutch"],
  ["de", "German"],
  ["fr", "French"],
  ["es", "Spanish"],
  ["it", "Italian"],
  ["pt", "Portuguese"],
  ["tr", "Turkish"],
  ["pl", "Polish"],
  ["sv", "Swedish"],
  ["da", "Danish"],
  ["fi", "Finnish"],
  ["no", "Norwegian"],
  ["ja", "Japanese"],
  ["ko", "Korean"],
  ["zh", "Chinese"],
];

function optionsWithCurrent(
  options: Array<[string, string]>,
  value: string,
  fallbackLabel = "Current",
): Array<[string, string]> {
  if (!value || options.some(([option]) => option === value)) return options;
  return [[value, `${fallbackLabel}: ${value}`], ...options];
}

function qualityPresetFilters(
  preset: AppSettings["qualityFilterPreset"],
): QualityFilterConfig[] {
  const poorSources =
    "cam|hdcam|camrip|ts|hdts|telesync|tc|hdtc|telecine|screener|scr|dvdscr|r5";
  switch (preset) {
    case "1080p-plus":
      return [
        {
          id: "preset-quality-1080-plus",
          deviceName: "Preset: 1080p+",
          regexPattern: `(?:360|480|576|720)p|${poorSources}`,
          enabled: true,
          createdAt: Date.now(),
        },
      ];
    case "1080p-only":
      return [
        {
          id: "preset-quality-1080-only",
          deviceName: "Preset: 1080p only",
          regexPattern: `(?:2160|4k|uhd)|(?:360|480|576|720)p|${poorSources}`,
          enabled: true,
          createdAt: Date.now(),
        },
      ];
    case "720p-plus":
      return [
        {
          id: "preset-quality-720-plus",
          deviceName: "Preset: 720p+",
          regexPattern: `(?:360|480|576)p|${poorSources}`,
          enabled: true,
          createdAt: Date.now(),
        },
      ];
    default:
      return [];
  }
}

export function SettingsScreen() {
  const [section, setSection] = useState<SectionId>("accounts");
  const [collapsed, setCollapsed] = useState(true);

  return (
    <div className={`settings-shell ${collapsed ? "sidebar-collapsed" : "sidebar-expanded"}`}>
      <aside className="settings-sidebar">
        <div className="settings-sidebar-header">
          <button
            type="button"
            className="settings-collapse-btn"
            onClick={() => setCollapsed(!collapsed)}
            aria-label={collapsed ? "Expand settings menu" : "Collapse settings menu"}
          >
            <Menu size={20} />
          </button>
          {!collapsed && <h2>Settings</h2>}
        </div>
        <nav className="settings-nav">
          {SECTIONS.map((s) => {
            const Icon = s.icon;
            return (
              <button
                type="button"
                key={s.id}
                className={`settings-section-btn ${section === s.id ? "is-active" : ""}`}
                onClick={() => {
                  setSection(s.id);
                  // Switching from a long, scrolled section (e.g. Catalogs) to a
                  // short one would otherwise leave the viewport mid-page and the
                  // panel frame visibly leaping around.
                  window.scrollTo({ top: 0 });
                }}
                title={s.label}
              >
                <span className="settings-btn-icon"><Icon size={18} /></span>
                {!collapsed && <span className="settings-btn-label">{s.label}</span>}
              </button>
            );
          })}
        </nav>
      </aside>
      <div className="settings-content">
        <SettingsSectionBoundary section={section}>
          <SectionBody section={section} />
        </SettingsSectionBoundary>
      </div>
    </div>
  );
}

class SettingsSectionBoundary extends Component<
  { section: SectionId; children: ReactNode },
  { hasError: boolean; message: string }
> {
  state = { hasError: false, message: "" };

  static getDerivedStateFromError(error: unknown) {
    return {
      hasError: true,
      message:
        error instanceof Error
          ? error.message
          : "This settings section could not be opened.",
    };
  }

  componentDidUpdate(previous: { section: SectionId }) {
    if (previous.section !== this.props.section && this.state.hasError) {
      this.setState({ hasError: false, message: "" });
    }
  }

  componentDidCatch(error: unknown) {
    console.error("Settings section failed", error);
  }

  render() {
    if (!this.state.hasError) return this.props.children;
    return (
      <section className="settings-panel-card settings-error-card">
        <h2>Settings section unavailable</h2>
        <p className="empty">{this.state.message}</p>
        <button
          type="button"
          className="secondary text-button"
          onClick={() => this.setState({ hasError: false, message: "" })}
        >
          Try again
        </button>
      </section>
    );
  }
}

/* ---------- reusable rows ---------- */

function Row({
  label,
  hint,
  children,
}: {
  label: string;
  hint?: string;
  children: ReactNode;
}) {
  return (
    <div className="set-row">
      <span className="set-label">
        {label}
        {hint && <em>{hint}</em>}
      </span>
      <span className="set-control">{children}</span>
    </div>
  );
}

function Toggle({
  value,
  onChange,
  disabled,
}: {
  value: boolean;
  onChange: (v: boolean) => void;
  disabled?: boolean;
}) {
  return (
    <button
      type="button"
      className={`toggle-switch ${value ? "is-on" : ""}`}
      role="switch"
      aria-checked={value}
      disabled={disabled}
      onClick={() => onChange(!value)}
    >
      <span />
    </button>
  );
}

function Select<T extends string>({
  value,
  options,
  onChange,
  disabled,
}: {
  value: T;
  options: Array<[T, string]>;
  onChange: (v: T) => void;
  disabled?: boolean;
}) {
  const [open, setOpen] = useState(false);
  const selected = options.find(([option]) => option === value)?.[1] ?? value;
  const choose = (next: T) => {
    onChange(next);
    setOpen(false);
  };

  // Lock body scroll while the option sheet is open — the position:fixed
  // technique is the only reliable way to prevent scroll-through on mobile.
  useEffect(() => {
    if (!open) return;
    const scrollY = window.scrollY;
    const { style } = document.body;
    const prev = {
      position: style.position,
      top: style.top,
      left: style.left,
      right: style.right,
      overflow: style.overflow,
    };
    style.position = "fixed";
    style.top = `-${scrollY}px`;
    style.left = "0";
    style.right = "0";
    style.overflow = "hidden";
    return () => {
      style.position = prev.position;
      style.top = prev.top;
      style.left = prev.left;
      style.right = prev.right;
      style.overflow = prev.overflow;
      window.scrollTo(0, scrollY);
    };
  }, [open]);

  return (
    <>
      <button
        type="button"
        className="option-button"
        disabled={disabled}
        onClick={(event) => {
          event.preventDefault();
          setOpen(true);
        }}
      >
        <span>{selected}</span>
        <ChevronDown size={17} />
      </button>
      {open && typeof document !== "undefined" && createPortal(
        <div
          className="option-sheet-backdrop"
          role="presentation"
          onClick={() => setOpen(false)}
        >
          <div
            className="option-sheet"
            role="dialog"
            aria-modal="true"
            aria-label="Choose option"
            onClick={(event) => event.stopPropagation()}
          >
            <div className="option-sheet-head">
              <strong>Choose option</strong>
              <button
                type="button"
                className="secondary"
                onClick={() => setOpen(false)}
              >
                Close
              </button>
            </div>
            <div className="option-sheet-list">
              {options.map(([option, label]) => (
                <button
                  type="button"
                  key={option}
                  className={`option-row ${option === value ? "is-selected" : ""}`}
                  onClick={() => choose(option)}
                >
                  <span>{label}</span>
                  {option === value && <Check size={18} />}
                </button>
              ))}
            </div>
          </div>
        </div>,
        document.body
      )}
    </>
  );
}

/* ---------- section body ---------- */

function SectionBody({ section }: { section: SectionId }) {
  const app = useApp();
  const { settings } = app;
  const set = (patch: Partial<AppSettings>) => app.updateSettings(patch);
  const [qualityFilterName, setQualityFilterName] = useState("");
  const [qualityFilterPattern, setQualityFilterPattern] = useState("");
  const setSubtitleColor = (name: AppSettings["subtitleColorName"]) =>
    set({ subtitleColorName: name, subtitleColor: SUBTITLE_COLOR_HEX[name] });
  const setQualityPreset = (preset: AppSettings["qualityFilterPreset"]) =>
    set({
      qualityFilterPreset: preset,
      qualityFilters:
        preset === "custom"
          ? settings.qualityFilters
          : qualityPresetFilters(preset),
    });
  const addQualityFilter = () => {
    const pattern = qualityFilterPattern.trim();
    if (!pattern) {
      app.setToast("Enter a quality filter regex first.");
      return;
    }
    set({
      qualityFilterPreset: "custom",
      qualityFilters: [
        {
          id: crypto.randomUUID(),
          deviceName: qualityFilterName.trim() || "Custom quality filter",
          regexPattern: pattern,
          enabled: true,
          createdAt: Date.now(),
        },
        ...safeArray(settings.qualityFilters),
      ],
    });
    setQualityFilterName("");
    setQualityFilterPattern("");
  };

  switch (section) {
    case "accounts":
      return <AccountsSection />;
    case "profiles":
      return (
        <Panel title="Profiles">
          <Row label="Skip profile selection on launch">
            <Toggle
              value={settings.skipProfileSelection}
              onChange={(v) => set({ skipProfileSelection: v })}
            />
          </Row>
          <button
            type="button"
            className="secondary text-button"
            onClick={app.switchProfile}
          >
            <User size={18} /> Manage profiles
          </button>
        </Panel>
      );
    case "playback":
      return (
        <Panel title="Playback">
          <Row
            label="Play in"
            hint="VLC/Infuse open the source directly; ARVIO still syncs Trakt when you return"
          >
            <Select
              value={settings.defaultPlayer}
              onChange={(v) => set({ defaultPlayer: v })}
              options={[
                ["browser", "ARVIO player"],
                ["vlc", "VLC"],
                ["infuse", "Infuse"],
              ]}
            />
          </Row>
          <Row label="Auto play next episode">
            <Toggle
              value={settings.autoPlayNext}
              onChange={(v) => set({ autoPlayNext: v })}
            />
          </Row>
          <Row label="Auto play single source">
            <Toggle
              value={settings.autoPlaySingleSource}
              onChange={(v) => set({ autoPlaySingleSource: v })}
            />
          </Row>
          <Row label="Auto play minimum quality">
            <Select
              value={settings.autoPlayMinQuality}
              onChange={(v) => set({ autoPlayMinQuality: v })}
              options={[
                ["any", "Any"],
                ["hd", "HD"],
                ["fhd", "FHD"],
                ["4k", "4K"],
              ]}
            />
          </Row>
          <Row label="Trailer auto play">
            <Toggle
              value={settings.trailerAutoPlay}
              onChange={(v) => set({ trailerAutoPlay: v })}
            />
          </Row>
          <Row label="Trailer sound">
            <Toggle
              value={settings.trailerSound}
              onChange={(v) => set({ trailerSound: v })}
            />
          </Row>
          <Row label="Trailer delay (seconds)">
            <input
              type="number"
              min={0}
              max={10}
              value={settings.trailerDelaySeconds}
              onChange={(e) =>
                set({ trailerDelaySeconds: Number(e.target.value) })
              }
            />
          </Row>
          <Row label="Show trailers inside cards">
            <Toggle
              value={settings.trailerInCards}
              onChange={(v) => set({ trailerInCards: v })}
            />
          </Row>
          <Row
            label="Frame rate matching"
            hint="Applies on TV devices; synced from here"
          >
            <Select
              value={settings.frameRateMatchingMode}
              onChange={(v) => set({ frameRateMatchingMode: v })}
              options={[
                ["off", "Off"],
                ["seamless", "Seamless only"],
                ["always", "Always"],
              ]}
            />
          </Row>
          <Row
            label="Volume boost"
            hint="Applies on TV devices; synced from here"
          >
            <Select
              value={String(settings.volumeBoostDb)}
              onChange={(v) => set({ volumeBoostDb: Number(v) })}
              options={["0", "3", "6", "9", "12", "15"].map((value) => [
                value,
                `${value} dB`,
              ])}
            />
          </Row>
          <Row label="Include specials">
            <Toggle
              value={settings.includeSpecials}
              onChange={(v) => set({ includeSpecials: v })}
            />
          </Row>
          <Row label="Quality filter preset">
            <Select
              value={settings.qualityFilterPreset}
              onChange={setQualityPreset}
              options={QUALITY_PRESET_LABELS}
            />
          </Row>
          <div className="inline-form wide">
            <input
              value={qualityFilterName}
              onChange={(e) => setQualityFilterName(e.target.value)}
              placeholder="Filter name"
            />
            <input
              value={qualityFilterPattern}
              onChange={(e) => setQualityFilterPattern(e.target.value)}
              placeholder="Regex to hide matching sources"
            />
            <button
              type="button"
              className="secondary text-button"
              onClick={addQualityFilter}
            >
              <Plus size={18} /> Add filter
            </button>
          </div>
          <div className="settings-list">
            {safeArray(settings.qualityFilters).map((filter) => (
              <div
                className="settings-list-row quality-filter-row"
                key={filter.id}
              >
                <button
                  type="button"
                  className="icon-button"
                  onClick={() =>
                    set({
                      qualityFilterPreset: "custom",
                      qualityFilters: settings.qualityFilters.map((item) =>
                        item.id === filter.id
                          ? { ...item, enabled: !item.enabled }
                          : item,
                      ),
                    })
                  }
                >
                  {filter.enabled ? <Eye size={18} /> : <EyeOff size={18} />}
                </button>
                <input
                  value={filter.deviceName}
                  onChange={(e) =>
                    set({
                      qualityFilterPreset: "custom",
                      qualityFilters: settings.qualityFilters.map((item) =>
                        item.id === filter.id
                          ? { ...item, deviceName: e.target.value }
                          : item,
                      ),
                    })
                  }
                />
                <input
                  value={filter.regexPattern}
                  onChange={(e) =>
                    set({
                      qualityFilterPreset: "custom",
                      qualityFilters: settings.qualityFilters.map((item) =>
                        item.id === filter.id
                          ? { ...item, regexPattern: e.target.value }
                          : item,
                      ),
                    })
                  }
                />
                <button
                  type="button"
                  className="icon-button danger"
                  onClick={() =>
                    set({
                      qualityFilterPreset: "custom",
                      qualityFilters: settings.qualityFilters.filter(
                        (item) => item.id !== filter.id,
                      ),
                    })
                  }
                >
                  <Trash2 size={18} />
                </button>
              </div>
            ))}
          </div>
        </Panel>
      );
    case "language":
      return (
        <Panel title="Language & Audio">
          <Row label="Content language">
            <Select
              value={settings.language}
              onChange={(v) => set({ language: v })}
              options={optionsWithCurrent(
                CONTENT_LANGUAGE_OPTIONS,
                settings.language,
                "Custom",
              )}
            />
          </Row>
          <Row label="Primary subtitle language">
            <Select
              value={settings.defaultSubtitle || ""}
              onChange={(v) => set({ defaultSubtitle: v })}
              options={optionsWithCurrent(
                TRACK_LANGUAGE_OPTIONS,
                settings.defaultSubtitle || "",
                "Custom",
              )}
            />
          </Row>
          <Row label="Secondary subtitle language">
            <Select
              value={settings.secondarySubtitle || ""}
              onChange={(v) => set({ secondarySubtitle: v })}
              options={optionsWithCurrent(
                TRACK_LANGUAGE_OPTIONS,
                settings.secondarySubtitle || "",
                "Custom",
              )}
            />
          </Row>
          <Row label="Audio language">
            <Select
              value={settings.audioLanguage || ""}
              onChange={(v) => set({ audioLanguage: v })}
              options={optionsWithCurrent(
                TRACK_LANGUAGE_OPTIONS,
                settings.audioLanguage || "",
                "Custom",
              )}
            />
          </Row>
        </Panel>
      );
    case "subtitles":
      return (
        <Panel title="Subtitles">
          <SubtitlePreview settings={settings} />
          <Row label="Subtitle size (%)">
            <input
              type="number"
              min={60}
              max={200}
              value={settings.subtitleSize}
              onChange={(e) => set({ subtitleSize: Number(e.target.value) })}
            />
          </Row>
          <Row label="Subtitle color">
            <Select
              value={settings.subtitleColorName}
              onChange={setSubtitleColor}
              options={(
                Object.keys(
                  SUBTITLE_COLOR_HEX,
                ) as AppSettings["subtitleColorName"][]
              ).map((name) => [name, name])}
            />
          </Row>
          <Row label="Custom subtitle color">
            <input
              type="color"
              value={settings.subtitleColor}
              onChange={(e) =>
                set({
                  subtitleColor: e.target.value,
                  subtitleColorName: "White",
                })
              }
            />
          </Row>
          <Row label="Subtitle offset (ms)">
            <input
              type="number"
              value={settings.subtitleOffsetMs}
              onChange={(e) =>
                set({ subtitleOffsetMs: Number(e.target.value) })
              }
            />
          </Row>
          <Row label="Subtitle screen position">
            <Select
              value={settings.subtitleOffset}
              onChange={(v) => set({ subtitleOffset: v })}
              options={[
                ["bottom", "Bottom"],
                ["low", "Low"],
                ["medium", "Medium"],
                ["high", "High"],
              ]}
            />
          </Row>
          <Row label="Subtitle style">
            <Select
              value={settings.subtitleStyle}
              onChange={(v) => set({ subtitleStyle: v })}
              options={[
                ["outline", "Bold / outline"],
                ["shadow", "Normal / shadow"],
                ["background", "Background"],
                ["raised", "Raised"],
              ]}
            />
          </Row>
          <Row label="Stylized subtitles">
            <Toggle
              value={settings.subtitleStylized}
              onChange={(v) => set({ subtitleStylized: v })}
            />
          </Row>
          <Row label="Filter subtitles by language">
            <Toggle
              value={settings.filterSubtitlesByLanguage}
              onChange={(v) => set({ filterSubtitlesByLanguage: v })}
            />
          </Row>
          <Row label="Remove hearing-impaired [SDH] tags">
            <Toggle
              value={settings.removeHearingImpaired}
              onChange={(v) => set({ removeHearingImpaired: v })}
            />
          </Row>
        </Panel>
      );
    case "ai":
      return (
        <Panel title="AI Subtitles">
          <Row label="AI subtitle enhancement">
            <Toggle
              value={settings.aiSubtitlesEnabled}
              onChange={(v) => set({ aiSubtitlesEnabled: v })}
            />
          </Row>
          <Row label="AI model">
            <Select
              value={settings.aiSubtitleModel}
              onChange={(v) => set({ aiSubtitleModel: v })}
              options={[
                ["off", "Off"],
                ["groq", "Groq"],
                ["gemini", "Gemini"],
              ]}
            />
          </Row>
          <Row label="Auto-select best match">
            <Toggle
              value={settings.aiAutoSelect}
              onChange={(v) => set({ aiAutoSelect: v })}
            />
          </Row>
          <Row label="AI API key">
            <input
              type="password"
              value={settings.aiApiKey}
              onChange={(e) => set({ aiApiKey: e.target.value })}
              placeholder="••••••••"
            />
          </Row>
        </Panel>
      );
    case "appearance":
      return (
        <Panel title="Appearance">
          <Row label="Card layout">
            <Select
              value={settings.cardLayoutMode}
              onChange={(v) => set({ cardLayoutMode: v })}
              options={[
                ["landscape", "Landscape"],
                ["poster", "Poster"],
              ]}
            />
          </Row>
          <Row label="Device mode">
            <Select
              value={settings.deviceModeOverride}
              onChange={(v) => set({ deviceModeOverride: v })}
              options={[
                ["auto", "Auto"],
                ["tv", "TV"],
                ["tablet", "Tablet"],
                ["phone", "Phone"],
                ["desktop", "Desktop / browser"],
              ]}
            />
          </Row>
          <Row label="OLED black background">
            <Toggle
              value={settings.oledBlack}
              onChange={(v) => set({ oledBlack: v })}
            />
          </Row>

          <Row label="Show budget / revenue">
            <Toggle
              value={settings.showBudget}
              onChange={(v) => set({ showBudget: v })}
            />
          </Row>
          <Row label="Smooth scrolling">
            <Toggle
              value={settings.smoothScrolling}
              onChange={(v) => set({ smoothScrolling: v })}
            />
          </Row>
          <Row label="Spoiler blur">
            <Toggle
              value={settings.spoilerBlur}
              onChange={(v) => set({ spoilerBlur: v })}
            />
          </Row>
          <Row label="Accent theme">
            <Select
              value={settings.accentColor}
              onChange={(v) => set({ accentColor: v })}
              options={[
                ["arctic", "Arctic"],
                ["gold", "Gold"],
                ["green", "Green"],
                ["blue", "Blue"],
                ["purple", "Purple"],
              ]}
            />
          </Row>
        </Panel>
      );
    case "network":
      return (
        <Panel title="Network">
          <Row label="DNS provider">
            <Select
              value={settings.dnsProvider}
              onChange={(v) => set({ dnsProvider: v })}
              options={[
                ["system", "System"],
                ["cloudflare", "Cloudflare"],
                ["google", "Google"],
                ["adguard", "AdGuard"],
                ["quad9", "Quad9"],
              ]}
            />
          </Row>
          <Row label="Show loading statistics">
            <Toggle
              value={settings.showLoadingStats}
              onChange={(v) => set({ showLoadingStats: v })}
            />
          </Row>
          <Row
            label="Custom user agent"
            hint="Cloud-saved for Android; browsers may ignore it"
          >
            <input
              value={settings.customUserAgent}
              onChange={(e) => set({ customUserAgent: e.target.value })}
              placeholder="Default"
            />
          </Row>
          <Row label="TorrServer base URL" hint="Cloud-saved for Android">
            <input
              value={settings.torrServerBaseUrl}
              onChange={(e) => set({ torrServerBaseUrl: e.target.value })}
              placeholder="http://127.0.0.1:8090"
            />
          </Row>
        </Panel>
      );
    case "tv":
      return <TvSettingsSection />;
    case "homeserver":
      return <HomeServerSection />;
    case "catalogs":
      return <CatalogsSection />;
    case "addons":
      return <AddonsSection />;
    default:
      return null;
  }
}

function safeArray<T>(value: T[] | null | undefined): T[] {
  return Array.isArray(value) ? value : [];
}

function fallbackId(prefix: string, index: number, preferred?: string | null) {
  return preferred && String(preferred).trim()
    ? String(preferred)
    : `${prefix}-${index}`;
}

function Panel({ title, children }: { title: string; children: ReactNode }) {
  return (
    <section className="settings-panel-card">
      <h2>{title}</h2>
      {children}
    </section>
  );
}

/* ---------- Accounts ---------- */

function AccountsSection() {
  const {
    auth,
    traktConnected,
    deviceCode,
    signIn,
    signOut,
    beginTrakt,
    pollTrakt,
    disconnectTrakt,
    refreshData,
  } = useApp();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loginError, setLoginError] = useState<string | null>(null);
  const [loginBusy, setLoginBusy] = useState<"sign-in" | "sign-up" | null>(
    null,
  );
  const [traktError, setTraktError] = useState<string | null>(null);
  const [traktBusy, setTraktBusy] = useState<"start" | "poll" | null>(null);
  const [syncBusy, setSyncBusy] = useState(false);
  const cloudConfigured = hasNetlifyBackendConfig() || hasSupabaseConfig();

  const submitCloudAuth = async (mode: "sign-in" | "sign-up") => {
    if (!email.trim() || !password) {
      setLoginError("Enter your email and password.");
      return;
    }
    setLoginBusy(mode);
    setLoginError(null);
    try {
      await signIn(email.trim(), password, mode);
      setEmail("");
      setPassword("");
    } catch (error) {
      setLoginError(
        error instanceof Error ? error.message : "Authentication failed.",
      );
    } finally {
      setLoginBusy(null);
    }
  };

  const startTraktLink = async () => {
    setTraktBusy("start");
    setTraktError(null);
    try {
      await beginTrakt();
    } catch (error) {
      setTraktError(
        error instanceof Error
          ? error.message
          : "Could not start Trakt device link.",
      );
    } finally {
      setTraktBusy(null);
    }
  };

  const approveTraktLink = async () => {
    setTraktBusy("poll");
    setTraktError(null);
    try {
      await pollTrakt();
    } catch (error) {
      setTraktError(
        error instanceof Error
          ? error.message
          : "Trakt has not approved this device yet.",
      );
    } finally {
      setTraktBusy(null);
    }
  };

  const syncNow = async () => {
    setSyncBusy(true);
    try {
      await refreshData();
    } finally {
      setSyncBusy(false);
    }
  };

  return (
    <>
      <Panel title="ARVIO Account">
        {!cloudConfigured && (
          <p className="empty">
            ARVIO Cloud backend env is missing. Add backend values in
            web/.env.local.
          </p>
        )}
        <div className="settings-status-grid">
          <div>
            <span>Cloud</span>
            <strong>
              {auth
                ? "Connected"
                : cloudConfigured
                  ? "Ready"
                  : "Missing config"}
            </strong>
          </div>
          <div>
            <span>Trakt</span>
            <strong>
              {traktConnected
                ? "Connected"
                : hasTraktConfig()
                  ? "Not linked"
                  : "Missing config"}
            </strong>
          </div>
          <div>
            <span>Sync</span>
            <strong>{auth ? "Cloud saved" : "Local only"}</strong>
          </div>
        </div>
        {loginError && <p className="login-error">{loginError}</p>}
        {auth ? (
          <div className="account-row">
            <UserCircle size={34} />
            <div className="account-copy">
              <strong>{auth.email}</strong>
              <span title={auth.userId}>ARVIO Cloud account</span>
            </div>
            <button type="button" className="secondary" onClick={signOut}>
              <LogOut size={18} /> Sign out
            </button>
          </div>
        ) : (
          <div className="login-form">
            <input
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="Email"
              type="email"
              autoComplete="email"
            />
            <input
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Password"
              type="password"
              autoComplete="current-password"
              onKeyDown={(event) => {
                if (event.key === "Enter") void submitCloudAuth("sign-in");
              }}
            />
            <div className="hero-actions">
              <button
                type="button"
                className="primary"
                disabled={Boolean(loginBusy) || !cloudConfigured}
                onClick={() => void submitCloudAuth("sign-in")}
              >
                {loginBusy === "sign-in" ? "Signing in..." : "Sign in"}
              </button>
              <button
                type="button"
                className="secondary"
                disabled={Boolean(loginBusy) || !cloudConfigured}
                onClick={() => void submitCloudAuth("sign-up")}
              >
                {loginBusy === "sign-up" ? "Creating..." : "Create"}
              </button>
            </div>
          </div>
        )}
      </Panel>

      <Panel title="Trakt">
        {!hasTraktConfig() && (
          <p className="empty">Trakt client id is missing.</p>
        )}
        {traktError && <p className="login-error">{traktError}</p>}
        {traktConnected ? (
          <button type="button" className="secondary" onClick={disconnectTrakt}>
            Disconnect Trakt
          </button>
        ) : (
          <>
            <button
              type="button"
              className="primary"
              disabled={traktBusy === "start" || !hasTraktConfig()}
              onClick={() => void startTraktLink()}
            >
              {traktBusy === "start" ? "Starting..." : "Start device link"}
            </button>
            {deviceCode && (
              <div className="device-code">
                <span>{deviceCode.user_code}</span>
                <p>Open {deviceCode.verification_url}</p>
                <button
                  type="button"
                  className="secondary"
                  disabled={traktBusy === "poll"}
                  onClick={() => void approveTraktLink()}
                >
                  {traktBusy === "poll" ? "Checking..." : "I approved it"}
                </button>
              </div>
            )}
          </>
        )}
      </Panel>

      <Panel title="Sync & Updates">
        <button
          type="button"
          className="secondary text-button"
          disabled={syncBusy}
          onClick={() => void syncNow()}
        >
          <RefreshCw size={18} />{" "}
          {syncBusy ? "Syncing..." : "Force cloud sync now"}
        </button>
        <p className="empty">
          Telegram bot setup is available in the Android app. The web app
          updates itself when a new version is deployed.
        </p>
        <p className="empty">
          Web build:{" "}
          {process.env.NEXT_PUBLIC_BUILD_STAMP
            ? new Date(
                Number(process.env.NEXT_PUBLIC_BUILD_STAMP),
              ).toLocaleString("en-GB", {
                day: "2-digit",
                month: "short",
                hour: "2-digit",
                minute: "2-digit",
              })
            : "unknown"}
        </p>
      </Panel>
    </>
  );
}

/* ---------- Home Server ---------- */

function HomeServerSection() {
  const { settings, updateSettings, setToast } = useApp();
  const [type, setType] = useState<HomeServerConfig["type"]>("jellyfin");
  const [name, setName] = useState("");
  const [url, setUrl] = useState("");
  const [token, setToken] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [testing, setTesting] = useState(false);

  const servers = safeArray(settings.homeServers);
  const update = (next: HomeServerConfig[]) =>
    updateSettings({ homeServers: next });

  const buildDraft = (): HomeServerConfig => ({
    id: crypto.randomUUID(),
    type,
    name: name.trim() || type,
    url: url.trim(),
    token: token.trim() || undefined,
    username: username.trim() || undefined,
    password: password || undefined,
    enabled: true,
  });

  const testConnection = async () => {
    if (!url.trim()) {
      setToast("Enter a Home Server URL first.");
      return;
    }
    if (type === "plex" && !token.trim()) {
      setToast("Plex needs an access token (X-Plex-Token).");
      return;
    }
    setTesting(true);
    try {
      const { testHomeServerConnection } = await import("@/lib/homeserver");
      const result = await testHomeServerConnection(buildDraft());
      setToast(
        result.ok
          ? `Connected to ${result.serverName || "server"}${result.libraryCount ? ` — ${result.libraryCount} libraries` : ""}.`
          : `Could not connect: ${result.error}`,
      );
    } catch (error) {
      setToast(
        error instanceof Error ? error.message : "Connection test failed.",
      );
    } finally {
      setTesting(false);
    }
  };

  return (
    <Panel title="Home Server">
      <p className="empty">
        Connect Plex, Jellyfin, or Emby. Plex requires an access token
        (X-Plex-Token). Jellyfin/Emby can use an API token or username +
        password. Matched movies and episodes appear as sources in the player,
        and cloud-sync with the Android app.
      </p>
      <div className="inline-form">
        <Select
          value={type}
          onChange={setType}
          options={[
            ["jellyfin", "Jellyfin"],
            ["emby", "Emby"],
            ["plex", "Plex"],
          ]}
        />
        <input
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="Name"
        />
        <input
          value={url}
          onChange={(e) => setUrl(e.target.value)}
          placeholder="https://server:8096"
        />
        <input
          value={token}
          onChange={(e) => setToken(e.target.value)}
          placeholder={
            type === "plex" ? "X-Plex-Token (required)" : "API token (optional)"
          }
        />
        {type !== "plex" && (
          <input
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Username (optional)"
          />
        )}
        {type !== "plex" && (
          <input
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Password"
            type="password"
          />
        )}
        <button
          type="button"
          className="secondary"
          disabled={testing}
          onClick={() => void testConnection()}
        >
          {testing ? "Testing…" : "Test"}
        </button>
        <button
          type="button"
          className="primary"
          onClick={() => {
            if (!url.trim()) {
              setToast("Enter a Home Server URL first.");
              return;
            }
            update([buildDraft(), ...servers]);
            setName("");
            setUrl("");
            setToken("");
            setUsername("");
            setPassword("");
            setToast("Home server saved.");
          }}
        >
          <Plus size={18} /> Add
        </button>
      </div>
      <div className="settings-list">
        {servers.map((server, index) => (
          <div
            className="settings-list-row server-row"
            key={fallbackId("server", index, server.id)}
          >
            <button
              type="button"
              className="icon-button"
              onClick={() =>
                update(
                  servers.map((s) =>
                    s.id === server.id ? { ...s, enabled: !s.enabled } : s,
                  ),
                )
              }
            >
              {server.enabled ? <Eye size={18} /> : <EyeOff size={18} />}
            </button>
            <strong>{server.name || server.type || "Home server"}</strong>
            <span>{server.type || "server"}</span>
            <span>{server.url || "No URL"}</span>
            <button
              type="button"
              className="icon-button danger"
              onClick={() => update(servers.filter((s) => s.id !== server.id))}
            >
              <Trash2 size={18} />
            </button>
          </div>
        ))}
        {servers.length === 0 && (
          <p className="empty">No home servers configured.</p>
        )}
      </div>
    </Panel>
  );
}

function TvSettingsSection() {
  const { settings, updateSettings, refreshIptv, setToast, busy } = useApp();
  const [name, setName] = useState("");
  const [m3uUrl, setM3uUrl] = useState("");
  const [epgUrl, setEpgUrl] = useState("");
  const playlists = safeArray(settings.iptvPlaylists);
  const updatePlaylists = (next: IptvPlaylistEntry[]) =>
    updateSettings({ iptvPlaylists: next });
  const isLoadingTv = Boolean(busy && busy.toLowerCase().includes("tv"));

  const addPlaylist = () => {
    const trimmedM3u = m3uUrl.trim();
    const trimmedEpg = epgUrl.trim();
    if (!trimmedM3u) {
      setToast("Enter an M3U playlist URL first.");
      return;
    }
    if (!/^https?:\/\//i.test(trimmedM3u)) {
      setToast("Playlist URL must start with http:// or https://.");
      return;
    }
    if (trimmedEpg && !/^https?:\/\//i.test(trimmedEpg)) {
      setToast("EPG URL must start with http:// or https://.");
      return;
    }
    updatePlaylists([
      {
        id: crypto.randomUUID(),
        name: name.trim() || "IPTV Playlist",
        m3uUrl: trimmedM3u,
        epgUrl: trimmedEpg,
        enabled: true,
      },
      ...playlists,
    ]);
    setName("");
    setM3uUrl("");
    setEpgUrl("");
    setToast("IPTV playlist saved.");
  };

  return (
    <Panel title="TV (IPTV)">
      <p className="empty">
        {playlists.length} playlist(s) configured. These are cloud-saved and
        used by the TV page.
      </p>
      <div className="inline-form wide">
        <input
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="Playlist name"
        />
        <input
          value={m3uUrl}
          onChange={(e) => setM3uUrl(e.target.value)}
          placeholder="M3U playlist URL"
        />
        <input
          value={epgUrl}
          onChange={(e) => setEpgUrl(e.target.value)}
          placeholder="EPG XMLTV URL (optional)"
        />
        <button type="button" className="primary" onClick={addPlaylist}>
          <Plus size={18} /> Add playlist
        </button>
      </div>
      <div className="settings-list">
        {playlists.map((playlist, index) => (
          <div
            className="settings-list-row iptv-row"
            key={fallbackId("playlist", index, playlist.id)}
          >
            <button
              type="button"
              className="icon-button"
              onClick={() =>
                updatePlaylists(
                  playlists.map((item) =>
                    item.id === playlist.id
                      ? { ...item, enabled: !item.enabled }
                      : item,
                  ),
                )
              }
            >
              {playlist.enabled ? <Eye size={18} /> : <EyeOff size={18} />}
            </button>
            <input
              value={playlist.name}
              onChange={(e) =>
                updatePlaylists(
                  playlists.map((item) =>
                    item.id === playlist.id
                      ? { ...item, name: e.target.value }
                      : item,
                  ),
                )
              }
            />
            <input
              value={playlist.m3uUrl}
              onChange={(e) =>
                updatePlaylists(
                  playlists.map((item) =>
                    item.id === playlist.id
                      ? { ...item, m3uUrl: e.target.value }
                      : item,
                  ),
                )
              }
            />
            <input
              value={playlist.epgUrl ?? ""}
              onChange={(e) =>
                updatePlaylists(
                  playlists.map((item) =>
                    item.id === playlist.id
                      ? { ...item, epgUrl: e.target.value }
                      : item,
                  ),
                )
              }
              placeholder="EPG URL"
            />
            <button
              type="button"
              className="icon-button danger"
              onClick={() =>
                updatePlaylists(
                  playlists.filter((item) => item.id !== playlist.id),
                )
              }
            >
              <Trash2 size={18} />
            </button>
          </div>
        ))}
        {!playlists.length && (
          <p className="empty">No IPTV playlists configured.</p>
        )}
      </div>
      <button
        type="button"
        className="secondary text-button"
        disabled={isLoadingTv}
        onClick={() => void refreshIptv()}
      >
        <RefreshCw size={18} />{" "}
        {isLoadingTv ? "Refreshing..." : "Refresh TV now"}
      </button>
      <Row label="Stalker portal URL">
        <input
          value={settings.iptvStalkerUrl}
          onChange={(e) => updateSettings({ iptvStalkerUrl: e.target.value })}
          placeholder="http://portal.example.com/c/"
        />
      </Row>
      <Row label="Stalker MAC address">
        <input
          value={settings.iptvStalkerMac}
          onChange={(e) => updateSettings({ iptvStalkerMac: e.target.value })}
          placeholder="00:1A:79:00:00:00"
        />
      </Row>
    </Panel>
  );
}

/* ---------- Catalogs ---------- */

function CatalogsSection() {
  const { settings, updateSettings, setToast } = useApp();
  const catalogs = mergeCatalogs(
    safeArray(settings.catalogs),
    safeArray(settings.hiddenCatalogIds),
  );
  const [customCatalogUrl, setCustomCatalogUrl] = useState("");

  const updateCatalogs = (next: CatalogConfig[]) =>
    updateSettings({
      catalogs: next,
      hiddenCatalogIds: next.filter((c) => !c.enabled).map((c) => c.id),
    });
  const moveCatalog = (id: string, offset: number) => {
    const index = catalogs.findIndex((c) => c.id === id);
    const target = index + offset;
    if (index < 0 || target < 0 || target >= catalogs.length) return;
    const next = [...catalogs];
    const [moved] = next.splice(index, 1);
    next.splice(target, 0, moved);
    updateCatalogs(next);
  };

  return (
    <Panel title="Catalogs (Home Rows)">
      <div className="inline-form">
        <input
          value={customCatalogUrl}
          onChange={(e) => setCustomCatalogUrl(e.target.value)}
          placeholder="https://mdblist.com/lists/user/list"
        />
        <button
          type="button"
          className="primary"
          onClick={() => {
            if (!customCatalogUrl.trim()) {
              setToast("Enter a catalog URL first.");
              return;
            }
            updateCatalogs([
              {
                id: `custom_${crypto.randomUUID()}`,
                name: "Custom MDBList",
                sourceType: "mdblist",
                mediaType: "all",
                sourceUrl: customCatalogUrl.trim(),
                enabled: true,
              },
              ...catalogs,
            ]);
            setCustomCatalogUrl("");
          }}
        >
          <Plus size={18} /> Add
        </button>
        <button
          type="button"
          className="secondary text-button"
          onClick={() => updateCatalogs(defaultCatalogs)}
        >
          <RotateCcw size={18} /> Reset
        </button>
      </div>
      <div className="settings-list">
        {catalogs.map((catalog, index) => (
          <div
            className="settings-list-row catalog-row"
            key={fallbackId("catalog", index, catalog.id)}
          >
            <button
              type="button"
              className="icon-button"
              onClick={() =>
                updateCatalogs(
                  catalogs.map((c) =>
                    c.id === catalog.id ? { ...c, enabled: !c.enabled } : c,
                  ),
                )
              }
            >
              {catalog.enabled ? <Eye size={18} /> : <EyeOff size={18} />}
            </button>
            <input
              value={catalog.name}
              onChange={(e) =>
                updateCatalogs(
                  catalogs.map((c) =>
                    c.id === catalog.id ? { ...c, name: e.target.value } : c,
                  ),
                )
              }
            />
            <span>{(catalog.sourceType || "custom").toUpperCase()}</span>
            <Select
              value={catalog.layout ?? "landscape"}
              onChange={(layout) =>
                updateCatalogs(
                  catalogs.map((c) =>
                    c.id === catalog.id ? { ...c, layout } : c,
                  ),
                )
              }
              options={[
                ["landscape", "Landscape"],
                ["poster", "Poster"],
              ]}
            />
            <button
              type="button"
              className="icon-button"
              disabled={index === 0}
              onClick={() => moveCatalog(catalog.id, -1)}
              aria-label={`Move ${catalog.name} up`}
            >
              <ArrowUp size={18} />
            </button>
            <button
              type="button"
              className="icon-button"
              disabled={index === catalogs.length - 1}
              onClick={() => moveCatalog(catalog.id, 1)}
              aria-label={`Move ${catalog.name} down`}
            >
              <ArrowDown size={18} />
            </button>
            {!catalog.isPreinstalled && (
              <button
                type="button"
                className="icon-button danger"
                onClick={() =>
                  updateCatalogs(catalogs.filter((c) => c.id !== catalog.id))
                }
              >
                <Trash2 size={18} />
              </button>
            )}
            {(catalog.sourceUrl ||
              catalog.endpoint ||
              catalog.addonCatalogId) && (
              <small className="catalog-source-line">
                {catalog.sourceUrl ||
                  catalog.endpoint ||
                  catalog.addonCatalogId}
              </small>
            )}
          </div>
        ))}
      </div>
    </Panel>
  );
}

/* ---------- Addons ---------- */

function AddonsSection() {
  const { addons, installAddon, removeAddon, setAddonsState, setToast } =
    useApp();
  const [addonUrl, setAddonUrl] = useState("");
  const [installing, setInstalling] = useState(false);
  const install = async () => {
    const trimmedUrl = addonUrl.trim();
    if (!trimmedUrl) {
      setToast("Enter an addon manifest URL first.");
      return;
    }
    if (!/^https?:\/\/.+\/manifest\.json(?:[?#].*)?$/i.test(trimmedUrl)) {
      setToast("Addon URL must be a full http(s) manifest.json URL.");
      return;
    }
    setInstalling(true);
    try {
      await installAddon(trimmedUrl);
      setAddonUrl("");
      setToast("Addon installed.");
    } catch (error) {
      setToast(
        error instanceof Error ? error.message : "Could not install addon.",
      );
    } finally {
      setInstalling(false);
    }
  };
  return (
    <Panel title="Stremio Addons">
      <div className="inline-form">
        <input
          value={addonUrl}
          onChange={(e) => setAddonUrl(e.target.value)}
          placeholder="https://addon.example.com/manifest.json"
        />
        <button
          type="button"
          className="primary"
          disabled={installing}
          onClick={() => void install()}
        >
          <Plus size={18} /> {installing ? "Installing..." : "Install"}
        </button>
      </div>
      <div className="settings-list">
        {safeArray(addons).map((addon, index) => {
          const resources = safeArray(addon.resources);
          const addonCatalogs = safeArray(addon.catalogs);
          const hasResource = (resource: string) =>
            resources.length === 0 ||
            resources.some((item) =>
              typeof item === "string"
                ? item === resource
                : item?.name === resource,
            );
          const canStream = hasResource("stream");
          const resourceLabel =
            [
              canStream ? "Streams" : "",
              hasResource("subtitles") ? "Subtitles" : "",
              addonCatalogs.length ? `${addonCatalogs.length} catalogs` : "",
            ]
              .filter(Boolean)
              .join(" / ") || "Manifest";
          return (
            <div
              className="settings-list-row addon-row"
              key={fallbackId("addon", index, addon.id || addon.manifestUrl)}
            >
              <button
                type="button"
                className="icon-button"
                onClick={() =>
                  setAddonsState(
                    addons.map((a) =>
                      a.id === addon.id
                        ? { ...a, enabled: a.enabled === false }
                        : a,
                    ),
                  )
                }
              >
                {addon.enabled === false ? (
                  <EyeOff size={18} />
                ) : (
                  <Eye size={18} />
                )}
              </button>
              <div className="addon-main">
                <strong>{addon.name || "Unnamed addon"}</strong>
                <span title={addon.manifestUrl}>{addon.manifestUrl}</span>
              </div>
              <span>{resourceLabel}</span>
              <span>{addon.version || "1.0.0"}</span>
              <button
                type="button"
                className="icon-button danger"
                onClick={() => void removeAddon(addon)}
                aria-label={`Remove ${addon.name || "addon"}`}
              >
                <Trash2 size={18} />
              </button>
            </div>
          );
        })}
        {addons.length === 0 && (
          <p className="empty">
            Install Stremio-compatible addons by URL above.
          </p>
        )}
      </div>
      <button
        type="button"
        className="secondary text-button danger reset-settings-button"
        onClick={() => {
          localStorage.removeItem(settingsKey);
          window.location.reload();
        }}
      >
        <Trash2 size={18} /> Reset all web settings
      </button>
    </Panel>
  );
}

function SubtitlePreview({ settings }: { settings: AppSettings }) {
  const previewClass = `subtitle-preview-text subtitle-style-${settings.subtitleStyle} subtitle-pos-${settings.subtitleOffset}`;
  return (
    <div className="subtitle-preview">
      <div className="subtitle-preview-frame">
        <span
          className={previewClass}
          style={{
            color: settings.subtitleColor,
            fontSize: `${Math.max(60, Math.min(200, settings.subtitleSize))}%`,
          }}
        >
          This is how subtitles will appear.
        </span>
      </div>
      <p>
        Preview updates instantly and is saved to cloud like Android subtitle
        settings.
      </p>
    </div>
  );
}
