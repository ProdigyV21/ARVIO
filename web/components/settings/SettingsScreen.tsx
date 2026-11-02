"use client";
// Settings. Grouped sections, a search across every setting, and a badge on
// each row saying where it applies, so ARVIO Web can be used to set up the
// Android and TV app too (settings sync through ARVIO Cloud).
import "@/app/settings.css";

import {
  Bot,
  Captions,
  ChevronLeft,
  ChevronRight,
  Cloud,
  Crown,
  Database,
  Info,
  Languages,
  Link2,
  ListVideo,
  Monitor,
  Palette,
  Play,
  Puzzle,
  Search,
  Send,
  Server,
  Tv,
  User,
  X,
} from "lucide-react";
import { Component, useCallback, useEffect, useMemo, useRef, useState, type ReactNode } from "react";
import { useTranslation } from "@/lib/i18n";
import { config } from "@/lib/config";
import { trackLanguageName } from "@/lib/languages";
import { useEntitlement } from "@/lib/entitlementContext";
import { useAppPick } from "@/lib/store";
import { clearSourceSettingsRequest, requestedSourceSettings, SOURCE_SETTINGS_EVENT } from "@/lib/sourceSetup";
import { SETTINGS_INDEX, type SettingsSectionId } from "./settingsIndex";
import { AccountSection, PremiumSection, ProfilesSection, ServicesSection } from "./sections/Account";
import { AiSubtitlesSection, LanguageSection, PlaybackSection, SubtitlesSection } from "./sections/Watching";
import { AddonsSection, CatalogsSection, HomeServerSection, LiveTvSection, MetadataSection } from "./sections/Sources";
import { TelegramSection } from "./sections/Telegram";
import { AndroidTvSection, AppearanceSection, BrowserSection } from "./sections/Devices";
import { AboutSection } from "./sections/About";
import { ScopeBadge } from "./ui";

const SECTIONS = [
  { id: "account", group: "Account", label: "ARVIO account", icon: Cloud },
  { id: "premium", group: "Account", label: "Premium", icon: Crown },
  { id: "profiles", group: "Account", label: "Profiles", icon: User },
  { id: "services", group: "Account", label: "Connected services", icon: Link2 },
  { id: "playback", group: "Watching", label: "Playback", icon: Play },
  { id: "language", group: "Watching", label: "Languages", icon: Languages },
  { id: "subtitles", group: "Watching", label: "Subtitle style", icon: Captions },
  { id: "ai", group: "Watching", label: "AI subtitles", icon: Bot },
  { id: "addons", group: "Sources", label: "Addons", icon: Puzzle },
  { id: "catalogs", group: "Sources", label: "Home rows", icon: ListVideo },
  { id: "tv", group: "Sources", label: "Live TV", icon: Tv },
  { id: "homeserver", group: "Sources", label: "Home server", icon: Server },
  { id: "telegram", group: "Sources", label: "Telegram", icon: Send },
  { id: "metadata", group: "Sources", label: "Metadata and keys", icon: Database },
  { id: "appearance", group: "Devices", label: "Home and appearance", icon: Palette },
  { id: "android", group: "Devices", label: "Android and TV", icon: Tv },
  { id: "browser", group: "Devices", label: "This browser", icon: Monitor },
  { id: "credits", group: "Devices", label: "About", icon: Info },
] as const;

const VISIBLE_SECTIONS = SECTIONS.filter(section =>
  (config.telegramEnabled || section.id !== "telegram") && (config.paywallEnabled || section.id !== "premium"));

type SectionId = (typeof SECTIONS)[number]["id"];
const GROUPS = ["Account", "Watching", "Sources", "Devices"] as const;
const PHONE = "(max-width: 760px)";

function isPhone() {
  return typeof window !== "undefined" && typeof window.matchMedia === "function" && window.matchMedia(PHONE).matches;
}

function isSection(value: unknown): value is SectionId {
  return VISIBLE_SECTIONS.some((section) => section.id === value);
}

/** One-line current value shown next to a section on phones. */
function useSummaries(): Partial<Record<SectionId, string>> {
  const translateUi = useTranslation();
  const { auth, settings, addons, traktConnected, simklConnected, mdblistConnected, activeProfile } = useAppPick("auth", "settings", "addons", "traktConnected", "simklConnected", "mdblistConnected", "activeProfile");
  const entitlement = useEntitlement();
  return useMemo(() => {
    const services = [traktConnected && "Trakt", simklConnected && "Simkl", mdblistConnected && "MDBList"].filter(Boolean).join(", ");
    const subtitle = trackLanguageName(settings.defaultSubtitle);
    return {
      account: auth?.email ?? translateUi(config.selfHosted ? "This browser" : "Not signed in"),
      premium: !auth ? undefined : entitlement?.entitled ? translateUi("Active") : translateUi("Not a member"),
      profiles: activeProfile?.name,
      services: services || translateUi("None"),
      language: subtitle ? `${translateUi("Subtitles")}: ${subtitle}` : undefined,
      ai: settings.aiSubtitlesEnabled ? `${translateUi("On")} · ${settings.aiSubtitleModel === "gemini" ? "Gemini" : "Groq"}` : translateUi("Off"),
      addons: String(addons.length),
      tv: settings.iptvPlaylists.length ? String(settings.iptvPlaylists.length) : undefined,
      homeserver: settings.homeServers.length ? String(settings.homeServers.length) : undefined,
    };
  }, [translateUi, auth, settings, addons.length, traktConnected, simklConnected, mdblistConnected, activeProfile, entitlement]);
}

export function SettingsScreen() {
  const translateUi = useTranslation();
  const { settingsSyncState, auth, managedStatus } = useAppPick("settingsSyncState", "auth", "managedStatus");
  const requested = requestedSourceSettings();
  const [section, setSectionState] = useState<SectionId>(() => requested ?? "account");
  // Phones show the list of sections first; a deep link opens its section.
  const [detail, setDetail] = useState(() => Boolean(requested));
  const [query, setQuery] = useState("");
  const [searchLocked, setSearchLocked] = useState(true);
  const pushedHistory = useRef(false);
  const mainRef = useRef<HTMLDivElement>(null);
  const summaries = useSummaries();

  const open = useCallback((id: string, settingId?: string) => {
    if (!isSection(id)) return;
    setSectionState(id);
    setQuery("");
    setDetail(true);
    if (isPhone() && !pushedHistory.current) {
      history.pushState({ ...(history.state ?? {}), arvio: true, settingsDetail: true }, "", location.href);
      pushedHistory.current = true;
    }
    window.requestAnimationFrame(() => {
      if (settingId) {
        const row = document.getElementById(`st-${settingId}`);
        if (row) {
          row.scrollIntoView({ block: "center" });
          row.classList.remove("is-highlighted");
          void row.offsetWidth;
          row.classList.add("is-highlighted");
          (row.querySelector("button, input, [tabindex]") as HTMLElement | null)?.focus({ preventScroll: true });
          return;
        }
      }
      window.scrollTo({ top: 0 });
      if (isPhone()) mainRef.current?.querySelector<HTMLElement>(".st-back")?.focus({ preventScroll: true });
    });
  }, []);

  const closeDetail = useCallback(() => {
    if (pushedHistory.current) { history.back(); return; }
    setDetail(false);
    window.scrollTo({ top: 0 });
  }, []);

  useEffect(() => {
    const onPop = () => {
      if (!pushedHistory.current) return;
      pushedHistory.current = false;
      setDetail(false);
      window.scrollTo({ top: 0 });
    };
    window.addEventListener("popstate", onPop);
    return () => window.removeEventListener("popstate", onPop);
  }, []);

  useEffect(() => {
    clearSourceSettingsRequest();
    const navigate = (event: Event) => {
      const target = (event as CustomEvent).detail;
      if (target !== "homeserver" && target !== "tv") return;
      clearSourceSettingsRequest();
      open(target);
    };
    window.addEventListener(SOURCE_SETTINGS_EVENT, navigate);
    return () => window.removeEventListener(SOURCE_SETTINGS_EVENT, navigate);
  }, [open]);

  const results = useMemo(() => {
    const words = query.trim().toLowerCase().split(/\s+/).filter(Boolean);
    if (!words.length) return null;
    return SETTINGS_INDEX.filter((entry) => isSection(entry.section)).filter((entry) => {
      const text = `${entry.label} ${translateUi(entry.label)} ${entry.keywords ?? ""} ${labelOf(entry.section)} ${translateUi(labelOf(entry.section))}`.toLowerCase();
      return words.every((word) => text.includes(word));
    }).slice(0, 40);
  }, [query, translateUi]);

  const active = VISIBLE_SECTIONS.find((s) => s.id === section) ?? VISIBLE_SECTIONS[0];
  const sync = !auth ? { tone: "", text: config.selfHosted ? "Saved in this browser" : "Not signed in — changes stay in this browser" }
    : settingsSyncState === "saved" ? { tone: "is-saved", text: "Synced with your ARVIO apps" }
    : settingsSyncState === "pending" ? { tone: "is-pending", text: "Saving…" }
    : settingsSyncState === "error" ? { tone: "is-error", text: "Couldn't sync — retrying" }
    : { tone: "", text: "Saved in this browser" };

  return (
    <div className={`st-shell ${detail ? "is-detail" : ""} ${results ? "is-searching" : ""}`}>
      <aside className="st-aside" aria-label={translateUi("Settings sections")}>
        <h1 className="st-title">{translateUi("Settings")}</h1>
        {managedStatus?.managed && <a className="st-btn" href="/partner/account">{managedStatus.name || "Managed account"}</a>}
        <p className={`st-sync ${sync.tone}`} role="status" aria-live="polite"><span className="st-sync-dot" aria-hidden="true" />{translateUi(sync.text)}</p>
        <div className="st-search">
          <Search size={16} aria-hidden="true" />
          {/* Browsers' password managers fill a saved e-mail into the text field
              before any password/key field on the page; this box is not a login
              field: no autocomplete, and read-only until the person focuses it. */}
          <input type="search" name="arvio-settings-search" autoComplete="off" autoCorrect="off" spellCheck={false} data-1p-ignore="" data-lpignore="true" data-form-type="other"
            readOnly={searchLocked} onFocus={() => setSearchLocked(false)} onPointerDown={() => setSearchLocked(false)}
            value={query} onChange={(e) => setQuery(e.target.value)} placeholder={translateUi("Search settings")} aria-label={translateUi("Search settings")}
            onKeyDown={(e) => { if (e.key === "Escape" && query) { e.stopPropagation(); setQuery(""); } }} />
          {query && <button type="button" className="st-search-clear" aria-label={translateUi("Clear search")} onClick={() => setQuery("")}><X size={16} /></button>}
        </div>
        <nav className="st-nav">
          {GROUPS.map((group) => {
            const items = VISIBLE_SECTIONS.filter((s) => s.group === group);
            if (!items.length) return null;
            return (
              <div className="st-nav-group" key={group} role="group" aria-label={translateUi(group)}>
                <p className="st-nav-heading">{translateUi(group)}</p>
                {items.map((s) => {
                  const Icon = s.icon;
                  const summary = summaries[s.id];
                  return (
                    <button type="button" key={s.id} className={`st-nav-item ${section === s.id && !results ? "is-active" : ""}`}
                      aria-current={section === s.id && !results ? "page" : undefined} onClick={() => open(s.id)}>
                      <Icon size={18} aria-hidden="true" />
                      <span className="st-nav-label">{translateUi(s.label)}</span>
                      {summary && <span className="st-nav-summary">{summary}</span>}
                      <ChevronRight size={16} className="st-nav-chevron" aria-hidden="true" />
                    </button>
                  );
                })}
              </div>
            );
          })}
        </nav>
        <div className="st-legend-list" aria-label={translateUi("Where settings apply")}>
          <p>{translateUi("Where settings apply")}</p>
          <div><ScopeBadge scope="all" />{translateUi("Android, TV and ARVIO Web")}</div>
          <div><ScopeBadge scope="android" />{translateUi("Only the Android and TV app")}</div>
          <div><ScopeBadge scope="web" />{translateUi("ARVIO Web in any browser")}</div>
          <div><ScopeBadge scope="browser" />{translateUi("Not synced")}</div>
        </div>
      </aside>

      <div className="st-main" ref={mainRef}>
        {results ? (
          <>
            <header className="st-section-head">
              <div>
                <h2>{translateUi("Search results")}</h2>
                <p>{results.length ? translateUi("{value0} settings match “{value1}”.", { value0: results.length, value1: query.trim() }) : translateUi("Nothing matches “{value0}”.", { value0: query.trim() })}</p>
              </div>
            </header>
            {results.length > 0 && (
              <section className="st-group">
                <div className="st-results">
                  {results.map((entry) => (
                    <button type="button" className="st-result" key={`${entry.section}:${entry.id ?? entry.label}`} onClick={() => open(entry.section, entry.id)}>
                      <span>
                        <strong>{translateUi(entry.label)}</strong>
                        <small>{translateUi(labelOf(entry.section))}</small>
                      </span>
                      {entry.scope && <ScopeBadge scope={entry.scope} />}
                    </button>
                  ))}
                </div>
              </section>
            )}
          </>
        ) : (
          <>
            <button type="button" className="st-back" onClick={closeDetail}><ChevronLeft size={20} /> {translateUi("Settings")}</button>
            <SettingsSectionBoundary section={active.id}>
              <SectionBody section={active.id} open={open} />
            </SettingsSectionBoundary>
          </>
        )}
      </div>
    </div>
  );
}

function labelOf(id: SettingsSectionId) {
  return SECTIONS.find((s) => s.id === id)?.label ?? id;
}

class SettingsSectionBoundary extends Component<{ section: SectionId; children: ReactNode }, { hasError: boolean; message: string }> {
  state = { hasError: false, message: "" };

  static getDerivedStateFromError(error: unknown) {
    return { hasError: true, message: error instanceof Error ? error.message : "This settings section could not be opened." };
  }

  componentDidUpdate(previous: { section: SectionId }) {
    if (previous.section !== this.props.section && this.state.hasError) this.setState({ hasError: false, message: "" });
  }

  componentDidCatch(error: unknown) {
    console.error("Settings section failed", error);
  }

  render() {
    if (!this.state.hasError) return this.props.children;
    return <SettingsError message={this.state.message} retry={() => this.setState({ hasError: false, message: "" })} />;
  }
}

function SettingsError({ message, retry }: { message: string; retry: () => void }) {
  const translateUi = useTranslation();
  return (
    <section className="st-group">
      <div className="st-group-head"><h3>{translateUi("Settings section unavailable")}</h3><p>{translateUi(message)}</p></div>
      <div className="st-group-foot"><button type="button" className="st-btn" onClick={retry}>{translateUi("Try again")}</button></div>
    </section>
  );
}

function SectionBody({ section, open }: { section: SectionId; open: (id: string, settingId?: string) => void }) {
  switch (section) {
    case "account": return <AccountSection />;
    case "premium": return <PremiumSection />;
    case "profiles": return <ProfilesSection />;
    case "services": return <ServicesSection />;
    case "playback": return <PlaybackSection />;
    case "language": return <LanguageSection />;
    case "subtitles": return <SubtitlesSection />;
    case "ai": return <AiSubtitlesSection openSection={(id) => open(id, id === "language" ? "subtitle-language" : undefined)} />;
    case "addons": return <AddonsSection />;
    case "catalogs": return <CatalogsSection />;
    case "tv": return <LiveTvSection />;
    case "homeserver": return <HomeServerSection />;
    case "telegram":
      return config.telegramEnabled ? <TelegramSection /> : null;
    case "metadata": return <MetadataSection />;
    case "appearance": return <AppearanceSection />;
    case "android": return <AndroidTvSection />;
    case "browser": return <BrowserSection />;
    case "credits": return <AboutSection />;
    default: return null;
  }
}
