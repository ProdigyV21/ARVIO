"use client";

import {
  Captions, Cloud, Eye, EyeOff, Languages, LayoutGrid, ListVideo, LogOut,
  Network, Play, Plus, RefreshCw, RotateCcw, Server, Sparkles, Subtitles, Trash2, Tv, User, UserCircle
} from "lucide-react";
import { useState, type ReactNode } from "react";
import { defaultCatalogs, mergeCatalogs } from "@/lib/catalogs";
import { hasSupabaseConfig, hasTraktConfig } from "@/lib/config";
import { defaultSettings, useApp } from "@/lib/store";
import type { AppSettings, CatalogConfig, HomeServerConfig } from "@/lib/types";

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
  { id: "addons", label: "Addons", icon: Sparkles }
] as const;

type SectionId = (typeof SECTIONS)[number]["id"];

export function SettingsScreen() {
  const [section, setSection] = useState<SectionId>("accounts");
  return (
    <div className="settings-shell">
      <aside className="settings-sidebar">
        <h2>Settings</h2>
        {SECTIONS.map((s) => {
          const Icon = s.icon;
          return (
            <button key={s.id} className={`settings-section-btn ${section === s.id ? "is-active" : ""}`} onClick={() => setSection(s.id)}>
              <Icon size={18} /> <span>{s.label}</span>
            </button>
          );
        })}
      </aside>
      <div className="settings-content">
        <SectionBody section={section} />
      </div>
    </div>
  );
}

/* ---------- reusable rows ---------- */

function Row({ label, hint, children }: { label: string; hint?: string; children: ReactNode }) {
  return (
    <label className="set-row">
      <span className="set-label">{label}{hint && <em>{hint}</em>}</span>
      <span className="set-control">{children}</span>
    </label>
  );
}

function Toggle({ value, onChange, disabled }: { value: boolean; onChange: (v: boolean) => void; disabled?: boolean }) {
  return <input type="checkbox" checked={value} disabled={disabled} onChange={(e) => onChange(e.target.checked)} />;
}

function Select<T extends string>({ value, options, onChange, disabled }: { value: T; options: Array<[T, string]>; onChange: (v: T) => void; disabled?: boolean }) {
  return (
    <select value={value} disabled={disabled} onChange={(e) => onChange(e.target.value as T)}>
      {options.map(([v, l]) => <option key={v} value={v}>{l}</option>)}
    </select>
  );
}

/* ---------- section body ---------- */

function SectionBody({ section }: { section: SectionId }) {
  const app = useApp();
  const { settings } = app;
  const set = (patch: Partial<AppSettings>) => app.updateSettings(patch);

  switch (section) {
    case "accounts":
      return <AccountsSection />;
    case "profiles":
      return (
        <Panel title="Profiles">
          <Row label="Skip profile selection on launch"><Toggle value={settings.skipProfileSelection} onChange={(v) => set({ skipProfileSelection: v })} /></Row>
          <button className="secondary text-button" onClick={app.switchProfile}><User size={18} /> Manage profiles</button>
        </Panel>
      );
    case "playback":
      return (
        <Panel title="Playback">
          <Row label="Auto play next episode"><Toggle value={settings.autoPlayNext} onChange={(v) => set({ autoPlayNext: v })} /></Row>
          <Row label="Auto play single source"><Toggle value={settings.autoPlaySingleSource} onChange={(v) => set({ autoPlaySingleSource: v })} /></Row>
          <Row label="Auto play minimum quality">
            <Select value={settings.autoPlayMinQuality} onChange={(v) => set({ autoPlayMinQuality: v })}
              options={[["any", "Any"], ["hd", "HD"], ["fhd", "FHD"], ["4k", "4K"]]} />
          </Row>
          <Row label="Trailer auto play"><Toggle value={settings.trailerAutoPlay} onChange={(v) => set({ trailerAutoPlay: v })} /></Row>
          <Row label="Trailer sound"><Toggle value={settings.trailerSound} onChange={(v) => set({ trailerSound: v })} /></Row>
          <Row label="Trailer delay (seconds)"><input type="number" min={0} max={10} value={settings.trailerDelaySeconds} onChange={(e) => set({ trailerDelaySeconds: Number(e.target.value) })} /></Row>
          <Row label="Frame rate matching" hint="Android only"><Toggle value={false} disabled onChange={() => undefined} /></Row>
          <Row label="Volume boost" hint="Android only"><Toggle value={false} disabled onChange={() => undefined} /></Row>
        </Panel>
      );
    case "language":
      return (
        <Panel title="Language & Audio">
          <Row label="Content language" hint="TMDB code, e.g. en-US"><input value={settings.language} onChange={(e) => set({ language: e.target.value })} /></Row>
          <Row label="Primary subtitle language"><input value={settings.defaultSubtitle} onChange={(e) => set({ defaultSubtitle: e.target.value })} /></Row>
          <Row label="Secondary subtitle language"><input value={settings.secondarySubtitle} onChange={(e) => set({ secondarySubtitle: e.target.value })} /></Row>
          <Row label="Audio language"><input value={settings.audioLanguage} onChange={(e) => set({ audioLanguage: e.target.value })} /></Row>
        </Panel>
      );
    case "subtitles":
      return (
        <Panel title="Subtitles">
          <Row label="Subtitle size (%)"><input type="number" min={60} max={200} value={settings.subtitleSize} onChange={(e) => set({ subtitleSize: Number(e.target.value) })} /></Row>
          <Row label="Subtitle color"><input type="color" value={settings.subtitleColor} onChange={(e) => set({ subtitleColor: e.target.value })} /></Row>
          <Row label="Subtitle offset (ms)"><input type="number" value={settings.subtitleOffsetMs} onChange={(e) => set({ subtitleOffsetMs: Number(e.target.value) })} /></Row>
          <Row label="Subtitle style">
            <Select value={settings.subtitleStyle} onChange={(v) => set({ subtitleStyle: v })}
              options={[["outline", "Outline"], ["shadow", "Drop shadow"], ["background", "Background"], ["raised", "Raised"]]} />
          </Row>
          <Row label="Stylized subtitles"><Toggle value={settings.subtitleStylized} onChange={(v) => set({ subtitleStylized: v })} /></Row>
          <Row label="Filter subtitles by language"><Toggle value={settings.filterSubtitlesByLanguage} onChange={(v) => set({ filterSubtitlesByLanguage: v })} /></Row>
          <Row label="Remove hearing-impaired [SDH] tags"><Toggle value={settings.removeHearingImpaired} onChange={(v) => set({ removeHearingImpaired: v })} /></Row>
        </Panel>
      );
    case "ai":
      return (
        <Panel title="AI Subtitles">
          <Row label="AI subtitle enhancement"><Toggle value={settings.aiSubtitlesEnabled} onChange={(v) => set({ aiSubtitlesEnabled: v })} /></Row>
          <Row label="AI model">
            <Select value={settings.aiSubtitleModel} onChange={(v) => set({ aiSubtitleModel: v })}
              options={[["off", "Off"], ["groq", "Groq"], ["gemini", "Gemini"]]} />
          </Row>
          <Row label="Auto-select best match"><Toggle value={settings.aiAutoSelect} onChange={(v) => set({ aiAutoSelect: v })} /></Row>
          <Row label="AI API key"><input type="password" value={settings.aiApiKey} onChange={(e) => set({ aiApiKey: e.target.value })} placeholder="••••••••" /></Row>
        </Panel>
      );
    case "appearance":
      return (
        <Panel title="Appearance">
          <Row label="Card layout">
            <Select value={settings.cardLayoutMode} onChange={(v) => set({ cardLayoutMode: v })} options={[["landscape", "Landscape"], ["poster", "Poster"]]} />
          </Row>
          <Row label="Device mode">
            <Select value={settings.deviceModeOverride} onChange={(v) => set({ deviceModeOverride: v })} options={[["auto", "Auto"], ["tv", "TV"], ["desktop", "Desktop"]]} />
          </Row>
          <Row label="OLED black background"><Toggle value={settings.oledBlack} onChange={(v) => set({ oledBlack: v })} /></Row>
          <Row label="Clock format">
            <Select value={settings.clockFormat} onChange={(v) => set({ clockFormat: v })} options={[["24h", "24-hour"], ["12h", "12-hour"]]} />
          </Row>
          <Row label="Show budget / revenue"><Toggle value={settings.showBudget} onChange={(v) => set({ showBudget: v })} /></Row>
          <Row label="Smooth scrolling"><Toggle value={settings.smoothScrolling} onChange={(v) => set({ smoothScrolling: v })} /></Row>
          <Row label="Spoiler blur"><Toggle value={settings.spoilerBlur} onChange={(v) => set({ spoilerBlur: v })} /></Row>
          <Row label="Accent theme">
            <Select value={settings.accentColor} onChange={(v) => set({ accentColor: v })}
              options={[["arctic", "Arctic"], ["gold", "Gold"], ["green", "Green"], ["blue", "Blue"], ["purple", "Purple"]]} />
          </Row>
        </Panel>
      );
    case "network":
      return (
        <Panel title="Network">
          <Row label="DNS provider">
            <Select value={settings.dnsProvider} onChange={(v) => set({ dnsProvider: v })}
              options={[["system", "System"], ["cloudflare", "Cloudflare"], ["google", "Google"], ["quad9", "Quad9"]]} />
          </Row>
          <Row label="Show loading statistics"><Toggle value={settings.showLoadingStats} onChange={(v) => set({ showLoadingStats: v })} /></Row>
          <Row label="Custom user agent" hint="Android only"><input value={settings.customUserAgent} disabled placeholder="Browser-controlled" /></Row>
        </Panel>
      );
    case "tv":
      return (
        <Panel title="TV (IPTV)">
          <p className="empty">{settings.iptvPlaylists.length} playlist(s) configured. Add and manage playlists, EPG and favorites on the TV page.</p>
        </Panel>
      );
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
  const { auth, traktConnected, deviceCode, signIn, signOut, beginTrakt, pollTrakt, disconnectTrakt, refreshData } = useApp();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  return (
    <>
      <Panel title="ARVIO Account">
        {!hasSupabaseConfig() && <p className="empty">Supabase env is missing. Add values in web/.env.local.</p>}
        {auth ? (
          <div className="account-row">
            <UserCircle size={34} />
            <div><strong>{auth.email}</strong><span>{auth.userId}</span></div>
            <button className="secondary" onClick={signOut}><LogOut size={18} /> Sign out</button>
          </div>
        ) : (
          <div className="login-form">
            <input value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Email" />
            <input value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" type="password" />
            <div className="hero-actions">
              <button className="primary" onClick={() => signIn(email, password, "sign-in")}>Sign in</button>
              <button className="secondary" onClick={() => signIn(email, password, "sign-up")}>Create</button>
            </div>
          </div>
        )}
      </Panel>

      <Panel title="Trakt">
        {!hasTraktConfig() && <p className="empty">Trakt client id is missing.</p>}
        {traktConnected ? (
          <button className="secondary" onClick={disconnectTrakt}>Disconnect Trakt</button>
        ) : (
          <>
            <button className="primary" onClick={beginTrakt}>Start device link</button>
            {deviceCode && (
              <div className="device-code">
                <span>{deviceCode.user_code}</span>
                <p>Open {deviceCode.verification_url}</p>
                <button className="secondary" onClick={pollTrakt}>I approved it</button>
              </div>
            )}
          </>
        )}
      </Panel>

      <Panel title="Sync & Updates">
        <button className="secondary text-button" onClick={() => void refreshData()}><RefreshCw size={18} /> Force cloud sync now</button>
        <p className="empty">Telegram bot setup is available on Android. The web app auto-updates on each deploy.</p>
      </Panel>
    </>
  );
}

/* ---------- Home Server ---------- */

function HomeServerSection() {
  const { settings, updateSettings } = useApp();
  const [type, setType] = useState<HomeServerConfig["type"]>("jellyfin");
  const [name, setName] = useState("");
  const [url, setUrl] = useState("");
  const [token, setToken] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const servers = settings.homeServers ?? [];
  const update = (next: HomeServerConfig[]) => updateSettings({ homeServers: next });

  return (
    <Panel title="Home Server">
      <p className="empty">Connect Jellyfin / Emby with an API token, or a username + password. Movies play directly in the browser. (Plex accepted; browse coming soon.)</p>
      <div className="inline-form">
        <select value={type} onChange={(e) => setType(e.target.value as HomeServerConfig["type"])}>
          <option value="jellyfin">Jellyfin</option>
          <option value="emby">Emby</option>
          <option value="plex">Plex</option>
        </select>
        <input value={name} onChange={(e) => setName(e.target.value)} placeholder="Name" />
        <input value={url} onChange={(e) => setUrl(e.target.value)} placeholder="https://server:8096" />
        <input value={token} onChange={(e) => setToken(e.target.value)} placeholder="API token (optional)" />
        <input value={username} onChange={(e) => setUsername(e.target.value)} placeholder="Username (optional)" />
        <input value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" type="password" />
        <button className="primary" onClick={() => {
          if (!url.trim()) return;
          update([{ id: crypto.randomUUID(), type, name: name || type, url: url.trim(), token: token.trim(), username: username.trim() || undefined, password: password || undefined, enabled: true }, ...servers]);
          setName(""); setUrl(""); setToken(""); setUsername(""); setPassword("");
        }}><Plus size={18} /> Add</button>
      </div>
      <div className="settings-list">
        {servers.map((server) => (
          <div className="settings-list-row" key={server.id}>
            <button className="icon-button" onClick={() => update(servers.map((s) => s.id === server.id ? { ...s, enabled: !s.enabled } : s))}>
              {server.enabled ? <Eye size={18} /> : <EyeOff size={18} />}
            </button>
            <strong>{server.name}</strong>
            <span>{server.type}</span>
            <span>{server.url}</span>
            <button className="icon-button danger" onClick={() => update(servers.filter((s) => s.id !== server.id))}><Trash2 size={18} /></button>
          </div>
        ))}
        {servers.length === 0 && <p className="empty">No home servers configured.</p>}
      </div>
    </Panel>
  );
}

/* ---------- Catalogs ---------- */

function CatalogsSection() {
  const { settings, updateSettings } = useApp();
  const catalogs = mergeCatalogs(settings.catalogs, settings.hiddenCatalogIds);
  const [customCatalogUrl, setCustomCatalogUrl] = useState("");

  const updateCatalogs = (next: CatalogConfig[]) => updateSettings({
    catalogs: next,
    hiddenCatalogIds: next.filter((c) => !c.enabled).map((c) => c.id)
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
        <input value={customCatalogUrl} onChange={(e) => setCustomCatalogUrl(e.target.value)} placeholder="https://mdblist.com/lists/user/list" />
        <button className="primary" onClick={() => {
          if (!customCatalogUrl.trim()) return;
          updateCatalogs([{ id: `custom_${crypto.randomUUID()}`, name: "Custom MDBList", sourceType: "mdblist", mediaType: "all", sourceUrl: customCatalogUrl.trim(), enabled: true }, ...catalogs]);
          setCustomCatalogUrl("");
        }}><Plus size={18} /> Add</button>
        <button className="secondary text-button" onClick={() => updateCatalogs(defaultCatalogs)}><RotateCcw size={18} /> Reset</button>
      </div>
      <div className="settings-list">
        {catalogs.map((catalog) => (
          <div className="settings-list-row" key={catalog.id}>
            <button className="icon-button" onClick={() => updateCatalogs(catalogs.map((c) => c.id === catalog.id ? { ...c, enabled: !c.enabled } : c))}>
              {catalog.enabled ? <Eye size={18} /> : <EyeOff size={18} />}
            </button>
            <input value={catalog.name} onChange={(e) => updateCatalogs(catalogs.map((c) => c.id === catalog.id ? { ...c, name: e.target.value } : c))} />
            <span>{catalog.sourceType.toUpperCase()}</span>
            <button className="icon-button" onClick={() => moveCatalog(catalog.id, -1)}>↑</button>
            <button className="icon-button" onClick={() => moveCatalog(catalog.id, 1)}>↓</button>
            {!catalog.isPreinstalled && <button className="icon-button danger" onClick={() => updateCatalogs(catalogs.filter((c) => c.id !== catalog.id))}><Trash2 size={18} /></button>}
          </div>
        ))}
      </div>
    </Panel>
  );
}

/* ---------- Addons ---------- */

function AddonsSection() {
  const { addons, installAddon, setAddonsState } = useApp();
  const [addonUrl, setAddonUrl] = useState("");
  return (
    <Panel title="Stremio Addons">
      <div className="inline-form">
        <input value={addonUrl} onChange={(e) => setAddonUrl(e.target.value)} placeholder="https://addon.example.com/manifest.json" />
        <button className="primary" onClick={async () => { if (!addonUrl.trim()) return; await installAddon(addonUrl); setAddonUrl(""); }}><Plus size={18} /> Install</button>
      </div>
      <div className="settings-list">
        {addons.map((addon) => (
          <div className="settings-list-row" key={addon.id}>
            <button className="icon-button" onClick={() => setAddonsState(addons.map((a) => a.id === addon.id ? { ...a, enabled: a.enabled === false } : a))}>
              {addon.enabled === false ? <EyeOff size={18} /> : <Eye size={18} />}
            </button>
            <strong>{addon.name}</strong>
            <span>{addon.resources.join(", ") || "manifest"}</span>
            <span>{addon.catalogs.length} catalogs</span>
          </div>
        ))}
        {addons.length === 0 && <p className="empty">Install Stremio-compatible addons by URL above.</p>}
      </div>
      <button className="secondary text-button danger" style={{ marginTop: 16 }} onClick={() => {
        localStorage.removeItem(settingsKey);
        window.location.reload();
      }}><Trash2 size={18} /> Reset all web settings</button>
    </Panel>
  );
}
