"use client";

import {
  ArrowDown,
  ArrowUp,
  Captions,
  Check,
  CheckCircle,
  ChevronDown,
  Cloud,
  Download,
  ExternalLink,
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
  Send,
  Server,
  Sparkles,
  Subtitles,
  Trash2,
  Tv,
  User,
  UserCircle,
  X,
} from "lucide-react";
import { Component, CSSProperties, useEffect, useState, type ReactNode } from "react";
import { createPortal } from "react-dom";
import { defaultCatalogs, mergeCatalogs } from "@/lib/catalogs";
import {
  hasNetlifyBackendConfig,
  hasSupabaseConfig,
  hasTraktConfig,
  hasSimklConfig,
  getAuthPortalUrl,
} from "@/lib/config";
import {
  isLinux,
  isMac,
  isWindows,
  setVlcProtocolReady,
  triggerDownload,
  vlcProtocolReady,
  VLC_SETUP_SH_URL,
  VLC_SETUP_URL,
} from "@/lib/externalPlayers";
import { buildHomeServerCatalogConfigs } from "@/lib/homeserver";
import { defaultSettings, useApp } from "@/lib/store";
import type {
  AppSettings,
  CatalogConfig,
  HomeServerConfig,
  IptvPlaylistEntry,
  QualityFilterConfig,
} from "@/lib/types";

const settingsKey = "arvio.web.settings";

function VlcIcon({
  size = 18,
  className = "",
  style = {},
}: {
  size?: number;
  className?: string;
  style?: CSSProperties;
}) {
  return (
    <svg
      width={size}
      height={size}
      viewBox="0 0 24 24"
      fill="currentColor"
      xmlns="http://www.w3.org/2000/svg"
      className={className}
      style={{ display: "inline-block", verticalAlign: "middle", ...style }}
    >
      {/* Cone tip */}
      <path d="M10.8 4.2C11.1 3.4 11.5 3 12 3C12.5 3 12.9 3.4 13.2 4.2L14.2 6.5H9.8L10.8 4.2Z" />
      {/* Upper cone band */}
      <path d="M9.1 8H14.9L16.2 11H7.8L9.1 8Z" />
      {/* Lower cone band */}
      <path d="M7.1 12.5H16.9L18.4 16H5.6L7.1 12.5Z" />
      {/* Base stand */}
      <path d="M2.5 17.5L4.5 17H19.5L21.5 17.5L20.5 21C20.4 21.6 19.8 22 19.2 22H4.8C4.2 22 3.6 21.6 3.5 21L2.5 17.5Z" />
    </svg>
  );
}

const SECTIONS = [
  { id: "accounts", label: "Accounts", icon: Cloud },
  { id: "profiles", label: "Profiles", icon: User },
  { id: "playback", label: "Playback", icon: Play },
  { id: "vlc", label: "VLC Integration", icon: VlcIcon },
  { id: "language", label: "Language & Audio", icon: Languages },
  { id: "subtitles", label: "Subtitles", icon: Subtitles },
  { id: "ai", label: "AI Subtitles", icon: Captions },
  { id: "appearance", label: "Appearance", icon: LayoutGrid },
  { id: "network", label: "Network", icon: Network },
  { id: "tv", label: "TV (IPTV)", icon: Tv },
  { id: "homeserver", label: "Home Server", icon: Server },
  { id: "telegram", label: "Telegram", icon: Send },
  { id: "catalogs", label: "Catalogs", icon: ListVideo },
  { id: "addons", label: "Addons", icon: Sparkles },
  { id: "metadata", label: "Metadata & Keys", icon: Sparkles },
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
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

  const activeSectionObj = SECTIONS.find((s) => s.id === section);

  return (
    <div className={`settings-shell ${collapsed ? "sidebar-collapsed" : "sidebar-expanded"}`}>
      {/* Mobile Floating Transparent Menu Button (Constant) */}
      <button
        type="button"
        className={`settings-floating-menu-btn ${mobileMenuOpen ? "is-open" : ""}`}
        onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
        aria-label={mobileMenuOpen ? "Close navigation menu" : "Open navigation menu"}
      >
        <Menu size={20} />
      </button>

      {/* Desktop Sidebar */}
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
          <h2 className="settings-sidebar-title">Settings</h2>
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
                <span className="settings-btn-label">{s.label}</span>
              </button>
            );
          })}
        </nav>
      </aside>

      {/* Mobile Navigation Drawer & Backdrop Overlay */}
      <div
        className={`settings-mobile-overlay ${mobileMenuOpen ? "is-open" : ""}`}
        onClick={() => setMobileMenuOpen(false)}
      >
        <div className="settings-mobile-drawer" onClick={(e) => e.stopPropagation()}>
          <div className="settings-mobile-drawer-header">
            <span className="settings-mobile-drawer-title">{activeSectionObj?.label ?? "Navigation"}</span>
            <button
              type="button"
              className="settings-mobile-drawer-close"
              onClick={() => setMobileMenuOpen(false)}
              aria-label="Close settings menu"
            >
              <X size={20} />
            </button>
          </div>
          <nav className="settings-mobile-nav">
            {SECTIONS.map((s) => {
              const Icon = s.icon;
              const isActive = section === s.id;
              return (
                <button
                  type="button"
                  key={s.id}
                  className={`settings-section-btn ${isActive ? "is-active" : ""}`}
                  onClick={() => {
                    setSection(s.id);
                    setMobileMenuOpen(false);
                    window.scrollTo({ top: 0 });
                  }}
                >
                  <span className="settings-btn-icon"><Icon size={18} /></span>
                  <span className="settings-btn-label">{s.label}</span>
                </button>
              );
            })}
          </nav>
        </div>
      </div>

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
            label="Play Live TV in"
            // Movies and series always open in an external player now, so this
            // choice only routes Live TV. Saying otherwise here would be the
            // same broken promise we removed from the source list.
            hint="Movies and series always open in an external player like VLC. This picks where Live TV channels play; ARVIO still syncs Trakt when you return"
          >
            <Select
              value={settings.defaultPlayer}
              onChange={(v) => set({ defaultPlayer: v })}
              options={[
                ["browser", "ARVIO player (browser)"],
                ["vlc", "VLC"],
                ["infuse", "Infuse"],
              ]}
            />
          </Row>
          {isMac() ? (
            <Row
              label="VLC One-Click Setup"
              hint="macOS self-registers the vlc:// protocol upon VLC installation — no setup script required"
            >
              <span className="muted" style={{ fontSize: "13px" }}>Natively supported</span>
            </Row>
          ) : isLinux() ? (
            <Row
              label="VLC One-Click Setup"
              hint="Download vlc-setup.sh to enable direct vlc:// launching on Linux without saving .m3u playlist files"
            >
              <button
                type="button"
                className="secondary text-button"
                onClick={() => {
                  triggerDownload(VLC_SETUP_SH_URL, "vlc-setup.sh");
                  setVlcProtocolReady(true);
                  app.setToast(
                    "Downloaded vlc-setup.sh — run `bash vlc-setup.sh` to enable direct VLC launching.",
                  );
                }}
              >
                <Download size={16} /> Download .sh
              </button>
            </Row>
          ) : isWindows() ? (
            <Row
              label="VLC One-Click Setup"
              hint="Download vlc-setup.bat to enable direct vlc:// launching on Windows without saving .m3u playlist files"
            >
              <button
                type="button"
                className="secondary text-button"
                onClick={() => {
                  triggerDownload(VLC_SETUP_URL, "vlc-setup.bat");
                  setVlcProtocolReady(true);
                  app.setToast(
                    "Downloaded vlc-setup.bat — run it once on Windows to enable direct VLC launching.",
                  );
                }}
              >
                <Download size={16} /> Download .bat
              </button>
            </Row>
          ) : null}
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
    case "telegram":
      return <TelegramSection />;
    case "catalogs":
      return <CatalogsSection />;
    case "addons":
      return <AddonsSection />;
    case "vlc":
      return <VlcSection />;
    case "metadata":
      return <MetadataSection settings={settings} set={set} />;
    default:
      return null;
  }
}

function MetadataSection({ settings, set }: { settings: AppSettings; set: (patch: Partial<AppSettings>) => void }) {
  const animeChain = (settings.metadataAnimeProviders || ["anilist", "tvdb", "tmdb"]).join(" → ").toUpperCase();
  const tvChain = (settings.metadataTvProviders || ["tvdb", "tmdb"]).join(" → ").toUpperCase();
  const movieChain = (settings.metadataMovieProviders || ["tmdb"]).join(" → ").toUpperCase();
  const tvdbActive = Boolean(settings.customTvdbApiKey?.trim());

  return (
    <div className="settings-section">
      <Panel title="Custom API Keys (Bring Your Own Key)">
        <Row label="TMDB API Key" hint="Custom v3 API key for TMDB requests">
          <input
            type="password"
            autoComplete="off"
            className="settings-input"
            placeholder="System Default Key"
            value={settings.customTmdbApiKey || ""}
            onChange={(e) => set({ customTmdbApiKey: e.target.value })}
          />
        </Row>

        <Row
          label="TVDB v4 API Key"
          hint={tvdbActive ? "Active — TVDB enabled for metadata fallback" : "TVDB is disabled until a custom API key is provided"}
        >
          <input
            type="password"
            autoComplete="off"
            className="settings-input"
            placeholder="Enter Custom TVDB Key to Enable"
            value={settings.customTvdbApiKey || ""}
            onChange={(e) => set({ customTvdbApiKey: e.target.value })}
          />
        </Row>

        <Row label="TVDB User PIN" hint="Required if using subscriber user key">
          <input
            type="password"
            autoComplete="off"
            className="settings-input"
            placeholder="Optional User PIN"
            value={settings.customTvdbUserPin || ""}
            onChange={(e) => set({ customTvdbUserPin: e.target.value })}
          />
        </Row>
      </Panel>

      <Panel title="Metadata Provider Priorities">
        <Row label="Anime Metadata Priority" hint={`Active chain: ${animeChain}`}>
          <span className="accent-badge">{animeChain}</span>
        </Row>

        <Row label="TV Shows Metadata Priority" hint={`Active chain: ${tvChain}`}>
          <span className="accent-badge">{tvChain}</span>
        </Row>

        <Row label="Movies Metadata Priority" hint={`Active chain: ${movieChain}`}>
          <span className="accent-badge">{movieChain}</span>
        </Row>
      </Panel>
    </div>
  );
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
    mdblistConnected,
    simklConnected,
    trackingPreferences,
    updateTrackingPreferences,
    deviceCode,
    simklDeviceCode,
    signOut,
    beginTrakt,
    pollTrakt,
    disconnectTrakt,
    connectMdblist,
    disconnectMdblist,
    beginSimkl,
    pollSimkl,
    disconnectSimkl,
    refreshData,
  } = useApp();
  const [traktError, setTraktError] = useState<string | null>(null);
  const [traktBusy, setTraktBusy] = useState<"start" | "poll" | null>(null);
  const [simklError, setSimklError] = useState<string | null>(null);
  const [simklBusy, setSimklBusy] = useState<"start" | "poll" | null>(null);
  const [mdblistKey, setMdblistKey] = useState("");
  const [mdblistError, setMdblistError] = useState<string | null>(null);
  const [mdblistBusy, setMdblistBusy] = useState(false);
  const [syncBusy, setSyncBusy] = useState(false);
  const cloudConfigured = hasNetlifyBackendConfig() || hasSupabaseConfig();
  const routingOptions: Array<[typeof trackingPreferences.watchlistReadMode, string]> = [];
  if (traktConnected && simklConnected) routingOptions.push(["both", "Trakt + Simkl"]);
  if (traktConnected) routingOptions.push(["trakt", "Trakt"]);
  if (simklConnected) routingOptions.push(["simkl", "Simkl"]);
  if (mdblistConnected) routingOptions.push(["mdblist", "MDBList"]);

  const redirectToAuthPortal = () => {
    const redirectUri = window.location.origin + "/";
    window.location.href = `${getAuthPortalUrl()}?redirect_uri=${encodeURIComponent(redirectUri)}`;
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

  const startSimklLink = async () => {
    setSimklBusy("start");
    setSimklError(null);
    try {
      await beginSimkl();
    } catch (error) {
      setSimklError(
        error instanceof Error
          ? error.message
          : "Could not start Simkl device link.",
      );
    } finally {
      setSimklBusy(null);
    }
  };

  const approveSimklLink = async () => {
    setSimklBusy("poll");
    setSimklError(null);
    try {
      await pollSimkl();
    } catch (error) {
      setSimklError(
        error instanceof Error
          ? error.message
          : "Simkl has not approved this device yet.",
      );
    } finally {
      setSimklBusy(null);
    }
  };

  const connectMdblistLink = async () => {
    setMdblistBusy(true);
    setMdblistError(null);
    try {
      await connectMdblist(mdblistKey);
      setMdblistKey("");
    } catch (error) {
      setMdblistError(
        error instanceof Error ? error.message : "Could not connect MDBList.",
      );
    } finally {
      setMdblistBusy(false);
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
            <span>Simkl</span>
            <strong>
              {simklConnected
                ? "Connected"
                : hasSimklConfig()
                  ? "Not linked"
                  : "Missing config"}
            </strong>
          </div>
          <div>
            <span>MDBList</span>
            <strong>{mdblistConnected ? "Connected" : "Not linked"}</strong>
          </div>
          <div>
            <span>Sync</span>
            <strong>{auth ? "Cloud saved" : "Local only"}</strong>
          </div>
        </div>
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
            <button
              type="button"
              className="primary"
              disabled={!cloudConfigured}
              onClick={redirectToAuthPortal}
            >
              Sign In with ARVIO Cloud
            </button>
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

      <Panel title="Simkl">
        {!hasSimklConfig() && (
          <p className="empty">Simkl client configuration is missing.</p>
        )}
        {simklError && <p className="login-error">{simklError}</p>}
        {simklConnected ? (
          <button type="button" className="secondary" onClick={disconnectSimkl}>
            Disconnect Simkl
          </button>
        ) : (
          <>
            <button
              type="button"
              className="primary"
              disabled={simklBusy === "start" || !hasSimklConfig()}
              onClick={() => void startSimklLink()}
            >
              {simklBusy === "start" ? "Starting..." : "Start device link"}
            </button>
            {simklDeviceCode && (
              <div className="device-code">
                <span>{simklDeviceCode.user_code}</span>
                <p>
                  Open{" "}
                  <a
                    href={simklDeviceCode.verification_url || "https://simkl.com/pin"}
                    target="_blank"
                    rel="noreferrer"
                    style={{ color: "var(--accent)", textDecoration: "underline" }}
                  >
                    {simklDeviceCode.verification_url || "https://simkl.com/pin"}
                  </a>{" "}
                  and enter the code above
                </p>
                <button
                  type="button"
                  className="secondary"
                  disabled={simklBusy === "poll"}
                  onClick={() => void approveSimklLink()}
                >
                  {simklBusy === "poll" ? "Checking..." : "I approved it"}
                </button>
              </div>
            )}
          </>
        )}
      </Panel>

      {(traktConnected || simklConnected || mdblistConnected) && (
        <Panel title="Tracking behavior">
          <Row label="Watchlist source" hint="Choose which connected service fills your watchlist.">
            <Select
              value={trackingPreferences.watchlistReadMode}
              options={routingOptions}
              onChange={(watchlistReadMode) => void updateTrackingPreferences({ watchlistReadMode })}
            />
          </Row>
          <Row label="Continue Watching source" hint="Choose one service or merge progress from both.">
            <Select
              value={trackingPreferences.continueWatchingReadMode}
              options={routingOptions}
              onChange={(continueWatchingReadMode) => void updateTrackingPreferences({ continueWatchingReadMode })}
            />
          </Row>
          <Row label="Watched history source" hint="Watched badges merge safely when both is selected.">
            <Select
              value={trackingPreferences.watchedReadMode}
              options={routingOptions}
              onChange={(watchedReadMode) => void updateTrackingPreferences({ watchedReadMode })}
            />
          </Row>
          {traktConnected && (
            <Row label="Update Trakt while watching">
              <Toggle
                value={trackingPreferences.writeToTrakt}
                onChange={(writeToTrakt) => void updateTrackingPreferences({ writeToTrakt })}
              />
            </Row>
          )}
          {simklConnected && (
            <Row label="Update Simkl while watching">
              <Toggle
                value={trackingPreferences.writeToSimkl}
                onChange={(writeToSimkl) => void updateTrackingPreferences({ writeToSimkl })}
              />
            </Row>
          )}
        </Panel>
      )}

      <Panel title="MDBList">
        {mdblistError && <p className="login-error">{mdblistError}</p>}
        {mdblistConnected ? (
          <button type="button" className="secondary" onClick={disconnectMdblist}>
            Disconnect MDBList
          </button>
        ) : (
          <div className="login-form">
            <input
              type="password"
              placeholder="MDBList API key"
              value={mdblistKey}
              onChange={(event) => setMdblistKey(event.target.value)}
              autoComplete="off"
            />
            <button
              type="button"
              className="primary"
              disabled={mdblistBusy || !mdblistKey.trim()}
              onClick={() => void connectMdblistLink()}
            >
              {mdblistBusy ? "Connecting..." : "Connect"}
            </button>
            <p className="empty">Get your API key from mdblist.com/preferences</p>
          </div>
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
  const [adding, setAdding] = useState(false);

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

  const addConnection = async () => {
    if (!url.trim()) {
      setToast("Enter a Home Server URL first.");
      return;
    }
    if (type === "plex" && !token.trim()) {
      setToast("Plex needs an access token (X-Plex-Token).");
      return;
    }
    setAdding(true);
    try {
      const { testHomeServerConnection } = await import("@/lib/homeserver");
      const result = await testHomeServerConnection(buildDraft());
      if (!result.ok || !result.connection) {
        setToast(`Could not connect: ${result.error || "Connection failed"}`);
        return;
      }
      update([result.connection, ...servers]);
      setName("");
      setUrl("");
      setToken("");
      setUsername("");
      setPassword("");
      setToast(`Connected to ${result.serverName || result.connection.name} and saved ${result.libraryCount ?? 0} libraries.`);
    } catch (error) {
      setToast(error instanceof Error ? error.message : "Connection failed.");
    } finally {
      setAdding(false);
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
          disabled={adding || testing}
          onClick={() => void addConnection()}
        >
          <Plus size={18} /> {adding ? "Connecting…" : "Add"}
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

/* ---------- Telegram ---------- */

// Lazily-loaded shape of @/lib/telegram. GramJS is a large browser-only bundle,
// so the module (and its dynamic gramjs import) only load when this section is
// first opened.
type TgModule = typeof import("@/lib/telegram");
type TgAuthState = import("@/lib/telegram").TgAuthState;

function TelegramSection() {
  const { settings, setToast } = useApp();
  const [mod, setMod] = useState<TgModule | null>(null);
  const [authState, setAuthState] = useState<TgAuthState>({ k: "idle" });
  const [qrDataUrl, setQrDataUrl] = useState<string | null>(null);
  const [phone, setPhone] = useState("+");
  const [code, setCode] = useState("");
  const [password, setPassword] = useState("");
  const [usePhone, setUsePhone] = useState(false);
  const [confirmDisconnect, setConfirmDisconnect] = useState(false);

  // Load the Telegram module and subscribe to its auth state.
  useEffect(() => {
    let unsub: (() => void) | undefined;
    let active = true;
    void (async () => {
      try {
        const m = await import("@/lib/telegram");
        if (!active) return;
        setMod(m);
        unsub = m.subscribe(setAuthState);
      } catch {
        setToast("Telegram module failed to load in this browser.");
      }
    })();
    return () => {
      active = false;
      unsub?.();
    };
  }, [setToast]);

  // Render the QR login link to an image whenever it (re)appears.
  useEffect(() => {
    if (authState.k !== "waitQr") {
      setQrDataUrl(null);
      return;
    }
    const url = authState.url;
    let active = true;
    void (async () => {
      try {
        const QRCode = await import("qrcode");
        const dataUrl = await QRCode.toDataURL(url, { margin: 1, width: 320 });
        if (active) setQrDataUrl(dataUrl);
      } catch {
        /* Fall back to showing the raw link below. */
      }
    })();
    return () => {
      active = false;
    };
  }, [authState]);

  if (!mod) {
    return (
      <Panel title="Telegram">
        <p className="empty">Loading…</p>
      </Panel>
    );
  }

  const connect = () => {
    setUsePhone(false);
    void mod.startQrAuth();
  };
  const connectPhone = () => {
    const digits = phone.replace(/\D/g, "");
    if (!phone.startsWith("+") || digits.length < 7) {
      setToast("Enter a valid phone number in international format (e.g. +1 650 555 1234).");
      return;
    }
    void mod.startPhoneAuth(phone.trim());
  };

  return (
    <Panel title="Telegram">
      <p className="empty">
        Connect your Telegram account to stream video files from your chats and
        channels as sources — the same feature as the Android app. Everything
        runs in your browser; nothing is sent to ARVIO servers.
      </p>

      {authState.k === "idle" && !usePhone && (
        <div className="tg-center">
          <p className="tg-lead">Scan a QR code with the Telegram app on your phone to sign in.</p>
          <div className="tg-actions">
            <button type="button" className="primary" onClick={connect}>
              <Send size={18} /> Connect with QR
            </button>
            <button type="button" className="secondary" onClick={() => setUsePhone(true)}>
              Use phone number instead
            </button>
          </div>
        </div>
      )}

      {authState.k === "idle" && usePhone && (
        <div className="tg-center">
          <p className="tg-lead">Enter your phone number in international format.</p>
          <div className="inline-form">
            <input
              value={phone}
              onChange={(e) => setPhone(e.target.value)}
              placeholder="+1 650 555 1234"
              inputMode="tel"
            />
            <button type="button" className="primary" onClick={connectPhone}>
              Send code
            </button>
            <button type="button" className="secondary" onClick={() => setUsePhone(false)}>
              Back to QR
            </button>
          </div>
        </div>
      )}

      {authState.k === "initializing" && <p className="empty">Connecting to Telegram…</p>}

      {authState.k === "waitQr" && (
        <div className="tg-center">
          <p className="tg-lead">Open Telegram on your phone → Settings → Devices → Link Desktop Device, then scan:</p>
          <div className="tg-qr">
            {qrDataUrl ? (
              // eslint-disable-next-line @next/next/no-img-element
              <img src={qrDataUrl} alt="Telegram login QR code" width={280} height={280} />
            ) : (
              <p className="empty">Generating QR…</p>
            )}
          </div>
          <p className="empty tg-fineprint">The code refreshes automatically. Approving it on your phone signs you in here.</p>
          <button
            type="button"
            className="secondary"
            onClick={() => {
              mod.resetToIdle();
              setUsePhone(true);
            }}
          >
            Use phone number instead
          </button>
        </div>
      )}

      {authState.k === "waitCode" && (
        <div className="tg-center">
          <p className="tg-lead">Enter the {authState.codeLength}-digit code Telegram just sent you.</p>
          <div className="inline-form">
            <input
              value={code}
              onChange={(e) => setCode(e.target.value.replace(/\D/g, "").slice(0, authState.codeLength))}
              placeholder="Login code"
              inputMode="numeric"
            />
            <button
              type="button"
              className="primary"
              onClick={() => {
                if (code) mod.submitCode(code);
              }}
            >
              Confirm
            </button>
          </div>
        </div>
      )}

      {authState.k === "waitPassword" && (
        <div className="tg-center">
          <p className="tg-lead">
            Two-step verification is on. Enter your Telegram password
            {authState.hint ? ` (hint: ${authState.hint})` : ""}.
          </p>
          <div className="inline-form">
            <input
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="2FA password"
              type="password"
            />
            <button
              type="button"
              className="primary"
              onClick={() => {
                if (password) mod.submitPassword(password);
                setPassword("");
              }}
            >
              Confirm
            </button>
          </div>
        </div>
      )}

      {authState.k === "ready" && (
        <div className="tg-connected">
          <div className="tg-badge">
            <strong>Connected</strong>
            <span>Signed in as {authState.firstName || "your account"}</span>
          </div>
          {!confirmDisconnect ? (
            <button type="button" className="secondary" onClick={() => setConfirmDisconnect(true)}>
              <LogOut size={18} /> Disconnect
            </button>
          ) : (
            <div className="tg-actions">
              <span className="empty">Disconnect and forget this session?</span>
              <button
                type="button"
                className="danger-button"
                onClick={() => {
                  setConfirmDisconnect(false);
                  void mod.disconnect();
                }}
              >
                Disconnect
              </button>
              <button type="button" className="secondary" onClick={() => setConfirmDisconnect(false)}>
                Cancel
              </button>
            </div>
          )}
        </div>
      )}

      {authState.k === "error" && (
        <div className="tg-center">
          <p className="tg-lead tg-error">Connection failed: {authState.message}</p>
          <button type="button" className="primary" onClick={connect}>
            Try again
          </button>
        </div>
      )}
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
      <Row label="Sort order" hint="Choose how live channels and groups are ordered in the list">
        <Select
          value={settings.iptvSortOrder ?? "provider"}
          onChange={(v) => updateSettings({ iptvSortOrder: v as "provider" | "number" | "name" })}
          options={[
            ["provider", "Provider Order (Default)"],
            ["number", "Channel Number"],
            ["name", "Alphabetical (A-Z)"]
          ]}
        />
      </Row>
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
  const standardCatalogs = mergeCatalogs(
    safeArray(settings.catalogs),
    safeArray(settings.hiddenCatalogIds),
  ).filter((catalog) => catalog.sourceType !== "home-server");
  const [homeServerCatalogs, setHomeServerCatalogs] = useState<CatalogConfig[]>([]);
  const [customCatalogUrl, setCustomCatalogUrl] = useState("");
  const catalogs = [...homeServerCatalogs, ...standardCatalogs];

  useEffect(() => {
    let cancelled = false;
    void buildHomeServerCatalogConfigs(
      safeArray(settings.homeServers),
      safeArray(settings.catalogs),
      safeArray(settings.hiddenHomeServerCatalogIds),
    ).then((next) => {
      if (!cancelled) setHomeServerCatalogs(next);
    }).catch(() => {
      if (!cancelled) setHomeServerCatalogs([]);
    });
    return () => {
      cancelled = true;
    };
  }, [settings.catalogs, settings.hiddenHomeServerCatalogIds, settings.homeServers]);

  const updateCatalogs = (next: CatalogConfig[]) => {
    const homeServer = next.filter((catalog) => catalog.sourceType === "home-server");
    const standard = next.filter((catalog) => catalog.sourceType !== "home-server");
    updateSettings({
      catalogs: next,
      hiddenCatalogIds: standard.filter((catalog) => !catalog.enabled).map((catalog) => catalog.id),
      hiddenHomeServerCatalogIds: homeServer.filter((catalog) => !catalog.enabled).map((catalog) => catalog.id),
    });
  };
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
          onClick={() => updateCatalogs([...homeServerCatalogs, ...defaultCatalogs])}
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
              aria-label={`${catalog.enabled ? "Hide" : "Show"} ${catalog.name}`}
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
            {!catalog.isPreinstalled && catalog.sourceType !== "home-server" && (
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
          disabled={installing || !addonUrl.trim()}
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

/* ---------- VLC Integration ---------- */

function VlcSection() {
  const { setToast } = useApp();
  const [vlcReady, setVlcReady] = useState<boolean>(() => vlcProtocolReady());
  const [checkStatus, setCheckStatus] = useState<
    "idle" | "testing" | "working" | "not_installed"
  >("idle");

  const handleDownloadWindows = () => {
    triggerDownload(VLC_SETUP_URL, "vlc-setup.bat");
    setVlcProtocolReady(true);
    setVlcReady(true);
    setToast(
      "Downloaded vlc-setup.bat — run it once on Windows to enable direct VLC launching.",
    );
  };

  const handleDownloadLinux = () => {
    triggerDownload(VLC_SETUP_SH_URL, "vlc-setup.sh");
    setVlcProtocolReady(true);
    setVlcReady(true);
    setToast(
      "Downloaded vlc-setup.sh — run `bash vlc-setup.sh` to enable direct VLC launching on Linux.",
    );
  };

  const handleToggleReady = () => {
    const next = !vlcReady;
    setVlcProtocolReady(next);
    setVlcReady(next);
    setToast(
      next
        ? "VLC direct protocol handler marked as ready."
        : "VLC direct protocol handler status reset.",
    );
  };

  const handleCheckIntegration = () => {
    setCheckStatus("testing");
    let blurred = false;

    const onBlur = () => {
      blurred = true;
    };

    window.addEventListener("blur", onBlur);

    const testUrl =
      "https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/720/Big_Buck_Bunny_720_10s_5MB.mp4";
    try {
      const a = document.createElement("a");
      a.href = `vlc://${testUrl}`;
      a.rel = "noopener";
      a.style.position = "fixed";
      a.style.left = "-9999px";
      document.body.appendChild(a);
      a.click();
      setTimeout(() => a.remove(), 1000);
    } catch {
      /* ignore */
    }

    setTimeout(() => {
      window.removeEventListener("blur", onBlur);
      if (blurred) {
        setCheckStatus("working");
        setVlcProtocolReady(true);
        setVlcReady(true);
        setToast(
          "✓ VLC Integration Confirmed! VLC launched directly via vlc:// protocol.",
        );
      } else {
        setCheckStatus("not_installed");
        setToast(
          "✕ VLC handler not detected. Please run the setup script for your OS.",
        );
      }
    }, 1500);
  };

  return (
    <Panel title="VLC Integration">
      <Row
        label="Check VLC Integration"
        hint="Automatically tests launching VLC via vlc:// and reports if integration is working"
      >
        <button
          type="button"
          className={
            checkStatus === "working"
              ? "secondary text-button"
              : checkStatus === "not_installed"
                ? "text-button danger"
                : "secondary text-button"
          }
          disabled={checkStatus === "testing"}
          onClick={handleCheckIntegration}
        >
          {checkStatus === "testing" ? (
            <RefreshCw size={16} className="spin" />
          ) : checkStatus === "working" ? (
            <CheckCircle size={16} />
          ) : (
            <CheckCircle size={16} />
          )}
          {checkStatus === "testing"
            ? "Testing VLC Launcher..."
            : checkStatus === "working"
              ? "Working (VLC Integration Verified)"
              : checkStatus === "not_installed"
                ? "Not Working — Run Setup"
                : "Check Integration"}
        </button>
      </Row>

      <Row
        label="Protocol Handler Status"
        hint="When enabled, ARVIO launches streams directly via vlc:// instead of saving .m3u playlist files"
      >
        <div style={{ display: "flex", gap: "8px", alignItems: "center" }}>
          {isMac() ? (
            <span className="muted" style={{ fontSize: "13px", display: "flex", alignItems: "center", gap: "6px" }}>
              <Check size={16} style={{ color: "#4ade80" }} /> Natively Enabled on macOS (no setup script needed)
            </span>
          ) : (
            <button
              type="button"
              className={vlcReady ? "secondary text-button" : "text-button"}
              onClick={handleToggleReady}
            >
              {vlcReady ? <Check size={16} /> : null}
              {vlcReady ? "Protocol Enabled (vlc://)" : "Mark Protocol Enabled"}
            </button>
          )}
        </div>
      </Row>

      <Row
        label="Download Setup Scripts Anytime"
        hint="Re-download the installer script for your desktop OS"
      >
        <div style={{ display: "flex", gap: "8px", flexWrap: "wrap" }}>
          <button
            type="button"
            className="secondary text-button"
            onClick={handleDownloadWindows}
          >
            <Download size={15} /> vlc-setup.bat (Windows)
          </button>
          <button
            type="button"
            className="secondary text-button"
            onClick={handleDownloadLinux}
          >
            <Download size={15} /> vlc-setup.sh (Linux)
          </button>
        </div>
      </Row>

      <div
        style={{
          marginTop: "24px",
          padding: "16px",
          background: "rgba(255, 255, 255, 0.03)",
          borderRadius: "12px",
          border: "1px solid rgba(255, 255, 255, 0.08)",
          fontSize: "13px",
          lineHeight: "1.6",
          color: "var(--muted)",
        }}
      >
        <strong
          style={{
            color: "#fff",
            display: "flex",
            alignItems: "center",
            gap: "8px",
            marginBottom: "8px",
          }}
        >
          <VlcIcon size={18} /> Platform Integration Guide:
        </strong>
        <ul style={{ paddingLeft: "20px", margin: 0 }}>
          <li style={{ marginBottom: "6px" }}>
            <strong style={{ color: "#fff" }}>Windows Desktop:</strong> Run{" "}
            <code>vlc-setup.bat</code> once to register the <code>vlc://</code>{" "}
            protocol handler for your user account (no administrator rights
            needed).
          </li>
          <li style={{ marginBottom: "6px" }}>
            <strong style={{ color: "#fff" }}>Linux Desktop:</strong> Download{" "}
            <code>vlc-setup.sh</code> and run <code>bash vlc-setup.sh</code> in
            your terminal. It creates a <code>.desktop</code> entry and registers{" "}
            <code>x-scheme-handler/vlc</code> via <code>xdg-mime</code> for
            GNOME, KDE, XFCE, etc.
          </li>
          <li style={{ marginBottom: "6px" }}>
            <strong style={{ color: "#fff" }}>macOS Desktop:</strong> VLC
            automatically self-registers the <code>vlc://</code> protocol
            handler upon installation. No setup script required.
          </li>
          <li style={{ marginBottom: "6px" }}>
            <strong style={{ color: "#fff" }}>Android:</strong> Uses native
            Android intents to launch VLC directly or prompt with an app chooser
            (MX Player, Just Player, VLC).
          </li>
          <li>
            <strong style={{ color: "#fff" }}>iOS / iPadOS:</strong> Launches
            VLC directly using the native <code>vlc-x-callback://</code>{" "}
            protocol.
          </li>
        </ul>
      </div>
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
