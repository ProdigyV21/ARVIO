"use client";
import { MAX_IPTV_PLAYLISTS } from "@/lib/iptvPlaylistIdentity";
import { Eye, EyeOff, Pencil, Plus, RefreshCw, Trash2 } from "lucide-react";
import { useEffect, useId, useRef, useState } from "react";
import { useTranslation } from "@/lib/i18n";
import { iptvPlaylistSignature, testIptvPlaylist, xtreamInfoFromUrl } from "@/lib/iptv";
import { playlistErrorCause } from "@/lib/iptvGuide";
import { maskedUrl } from "@/lib/maskUrl";
import { moveIndex } from "@/lib/ordering";
import { useAppPick } from "@/lib/store";
import { isManagedId } from "@/lib/managedConfiguration";
import { ManagedConnections } from "../ManagedConnections";
import type { IptvPlaylistEntry } from "@/lib/types";
import {
  MAX_STALKER_PORTALS,
  hasStalkerPortals,
  referencedStalkerPortalIds,
  stalkerPortalsOf,
  stalkerPortalsPatch,
  stalkerSourceSignature,
  upsertStalkerPortal
} from "@/lib/stalkerPortals";
import {
  playlistDraftFromEntry,
  playlistFromDraft,
  usePlaylistErrorText,
  usePlaylistProblemText,
  type PlaylistDraft
} from "@/components/livetv/PlaylistManager";
import { IptvGroupSettings } from "../IptvGroupSettings";
import { PortalForm } from "../StalkerPortalSettings";
import { SortableList } from "../SortableList";
import { Empty, Group, Row, ScopeBadge, SecretInput, Segmented, Select, SectionHeader, SettingAnchor, Status, Toggle, safeArray } from "../ui";

// Moved to their own files; re-exported so existing imports keep working.
export { AddonsSection } from "./Addons";
export { CatalogsSection } from "./HomeRows";
export { HomeServerSection } from "./HomeServer";

/* ---------- Live TV (IPTV) ---------- */

type SourceKind = "xtream" | "m3u" | "stalker";
type PlaylistKind = "xtream" | "m3u";
type PlaylistTestState = { state: "idle" } | { state: "testing" } | { state: "ok"; channels: number } | { state: "error"; text: string };
const emptyPlaylistDraft = (kind: PlaylistKind): PlaylistDraft => ({
  kind,
  name: "",
  url: "",
  epg: "",
  host: "",
  username: "",
  password: "",
  portal: "",
  mac: ""
});

export function LiveTvSection() {
  const translateUi = useTranslation();
  const { settings, updateSettings, refreshIptv, setToast, busy, iptvSnapshot, activeProfile, auth } = useAppPick("settings", "updateSettings", "refreshIptv", "setToast", "busy", "iptvSnapshot", "activeProfile", "auth");
  const activeProfileId = activeProfile?.id;
  const groupScope = `${auth?.userId ?? "local"}:${activeProfileId ?? "local"}`;
  const playlistSignature = iptvPlaylistSignature(settings.iptvPlaylists) + stalkerSourceSignature(settings);
  const groupsLoaded = iptvSnapshot.scopeKey === groupScope && iptvSnapshot.signature === playlistSignature;
  useEffect(() => {
    if (!groupsLoaded && (settings.iptvPlaylists.length || hasStalkerPortals(settings))) void refreshIptv();
  }, [groupScope, playlistSignature, groupsLoaded, refreshIptv, settings.iptvPlaylists.length]);

  const [sourceKind, setSourceKind] = useState<SourceKind>("xtream");
  const [editingPlaylistId, setEditingPlaylistId] = useState<string | null>(null);
  const [editingPortalId, setEditingPortalId] = useState<string | null>(null);
  const managedPlaylists = safeArray(settings.iptvPlaylists).filter((playlist) => isManagedId(playlist.id));
  const playlists = safeArray(settings.iptvPlaylists).filter((playlist) => !isManagedId(playlist.id));
  const portals = stalkerPortalsOf(settings);
  const totalSources = playlists.length + portals.length;
  const portalsFull = portals.length >= MAX_STALKER_PORTALS;
  const playlistsFull = playlists.length >= MAX_IPTV_PLAYLISTS;
  const updatePlaylists = (next: IptvPlaylistEntry[]) => updateSettings({ iptvPlaylists: [...managedPlaylists, ...next] });
  const savePortals = (next: typeof portals) => updateSettings(stalkerPortalsPatch(next));
  const edit = (id: string, patch: Partial<IptvPlaylistEntry>) => updatePlaylists(playlists.map((item) => item.id === id ? { ...item, ...patch } : item));
  const editingPlaylist = playlists.find((playlist) => playlist.id === editingPlaylistId) ?? null;
  const editingPortal = portals.find((portal) => portal.id === editingPortalId) ?? null;
  const isLoadingTv = Boolean(busy && busy.toLowerCase().includes("tv"));
  const channelCount = groupsLoaded ? (iptvSnapshot.allChannels ?? iptvSnapshot.channels ?? []).length : 0;

  const addSourceDescription = sourceKind === "xtream"
    ? (playlistsFull
      ? translateUi("Maximum of 10 IPTV playlists reached")
      : translateUi("ARVIO builds the playlist and guide links from these and syncs them to your other devices."))
    : sourceKind === "m3u"
      ? (playlistsFull
        ? translateUi("Maximum of 10 IPTV playlists reached")
        : translateUi("The M3U or M3U8 link from your provider. Xtream get.php links work too."))
      : (portalsFull
        ? translateUi("You can add up to {value0} portals.", { value0: MAX_STALKER_PORTALS })
        : translateUi("For providers that give you a portal address and a MAC address instead of a playlist."));

  const importFlags = (
    item: { importLiveTv?: boolean; importVod?: boolean; importSeries?: boolean },
    onFlag: (flag: "importLiveTv" | "importVod" | "importSeries", checked: boolean) => void
  ) => (
    <span className="st-import-flags">
      <span className="st-sort-sub">{translateUi("Import on Android and TV")}</span>
      {([["importLiveTv", "Live channels"], ["importVod", "Movies"], ["importSeries", "Series"]] as const).map(([flag, label]) => (
        <label key={flag} className="st-check">
          <input type="checkbox" checked={item[flag] !== false} onChange={(e) => onFlag(flag, e.target.checked)} />
          {translateUi(label)}
        </label>
      ))}
      <ScopeBadge scope="android" />
    </span>
  );

  return (
    <>
      <SectionHeader title="Live TV" description="IPTV playlists and programme guides for this profile. They sync to the Live TV page on every ARVIO app.">
        <button type="button" className="st-btn" disabled={isLoadingTv} onClick={() => void refreshIptv({ fresh: true })}>
          <RefreshCw size={16} className={isLoadingTv ? "spin" : undefined} /> {translateUi(isLoadingTv ? "Refreshing..." : "Refresh channels")}
        </button>
      </SectionHeader>

      <ManagedConnections items={managedPlaylists} />

      <SettingAnchor id="iptv-add">
        <Group title="Add a playlist" description={addSourceDescription}>
          <div className="st-tabs">
            <Segmented<SourceKind>
              label="Playlist type"
              value={sourceKind}
              onChange={setSourceKind}
              options={[["xtream", "Xtream Codes"], ["m3u", "M3U link"], ["stalker", "Stalker portal"]]}
            />
          </div>
          {sourceKind === "stalker" ? (
            !portalsFull && (
              <PortalForm
                portals={portals}
                userAgent={settings.customUserAgent}
                submitLabel={translateUi("Add playlist")}
                resetOnSubmit
                onSubmit={(draft) => {
                  savePortals(upsertStalkerPortal(
                    portals,
                    { name: draft.name, portalUrl: draft.url, macAddress: draft.mac },
                    referencedStalkerPortalIds(settings)
                  ));
                  setToast("Playlist saved.");
                }}
              />
            )
          ) : (
            !playlistsFull && (
              <PlaylistForm
                controlledKind={sourceKind}
                userAgent={settings.customUserAgent}
                submitLabel={translateUi("Add playlist")}
                resetOnSubmit
                onSubmit={(entry) => {
                  updatePlaylists([{ ...entry, name: entry.name === "Playlist" ? "IPTV Playlist" : entry.name, epgUrls: entry.epgUrl ? [entry.epgUrl] : [] }, ...playlists]);
                  setToast("IPTV playlist saved.");
                }}
              />
            )
          )}
        </Group>
      </SettingAnchor>

      <SettingAnchor id="stalker-url">
        <Group title={`${translateUi("Playlists")} · ${totalSources}`} description={<>
          {groupsLoaded && channelCount ? `${translateUi("{value0} channels loaded.", { value0: channelCount.toLocaleString() })} ` : ""}
          {translateUi("The first playlist is the main one; drag to change the order the playlists are listed in.")} <ScopeBadge scope="all" />
        </>}>
          {totalSources === 0 ? <Empty>{translateUi("No IPTV playlists configured.")}</Empty> : (
            <>
              {playlists.length > 0 && (
                <SortableList
                  items={playlists}
                  label={translateUi("Playlists")}
                  getKey={(playlist) => playlist.id}
                  getLabel={(playlist) => playlist.name || playlist.id}
                  onMove={(from, to) => updatePlaylists(moveIndex(playlists, from, to))}
                  edgeButtons={playlists.length > 2}
                  rowClassName={(playlist) => playlist.enabled ? "" : "is-off"}
                  renderMain={(playlist) => (
                    <>
                      <span className="st-sort-title">
                        <span className="st-sort-name">{playlist.name || translateUi("IPTV Playlist")}</span>
                        <span className="st-tag">{xtreamInfoFromUrl(playlist.m3uUrl) ? "Xtream" : "M3U"}</span>
                      </span>
                      <span className="st-sort-sub">
                        {maskedUrl(playlist.m3uUrl)} · {playlist.epgUrl || playlist.epgUrls?.length ? translateUi("Playlist + EPG") : translateUi("Playlist")}
                      </span>
                      {importFlags(playlist, (flag, checked) => edit(playlist.id, { [flag]: checked }))}
                    </>
                  )}
                  renderActions={(playlist) => (
                    <>
                      <Toggle label={playlist.name || playlist.id} value={playlist.enabled} onChange={(enabled) => edit(playlist.id, { enabled })} />
                      <button
                        type="button"
                        className="st-icon-btn"
                        aria-pressed={editingPlaylistId === playlist.id}
                        aria-label={translateUi("Edit {value0}", { value0: playlist.name || playlist.id })}
                        title={translateUi("Edit")}
                        onClick={() => { setEditingPortalId(null); setEditingPlaylistId(editingPlaylistId === playlist.id ? null : playlist.id); }}
                      >
                        <Pencil size={17} />
                      </button>
                      <button
                        type="button"
                        className="st-icon-btn is-danger"
                        aria-label={translateUi("Remove {value0}", { value0: playlist.name || playlist.id })}
                        title={translateUi("Remove")}
                        onClick={() => {
                          if (editingPlaylistId === playlist.id) setEditingPlaylistId(null);
                          updatePlaylists(playlists.filter((item) => item.id !== playlist.id));
                        }}
                      >
                        <Trash2 size={17} />
                      </button>
                    </>
                  )}
                />
              )}
              {editingPlaylist && (
                <div className="st-edit-panel" role="region" aria-label={translateUi("Edit {value0}", { value0: editingPlaylist.name || editingPlaylist.id })}>
                  <div className="st-subhead stalker-edit-head"><h4>{translateUi("Edit {value0}", { value0: editingPlaylist.name || editingPlaylist.id })}</h4></div>
                  <PlaylistForm
                    key={editingPlaylist.id}
                    playlist={editingPlaylist}
                    userAgent={settings.customUserAgent}
                    submitLabel={translateUi("Save")}
                    onSubmit={(entry) => {
                      edit(editingPlaylist.id, {
                        name: entry.name,
                        m3uUrl: entry.m3uUrl,
                        epgUrl: entry.epgUrl,
                        epgUrls: entry.epgUrl ? [entry.epgUrl] : []
                      });
                      setEditingPlaylistId(null);
                      setToast("IPTV playlist saved.");
                    }}
                    onCancel={() => setEditingPlaylistId(null)}
                  />
                </div>
              )}
              {portals.length > 0 && (
                <div className={playlists.length > 0 ? "iptv-portal-divider" : undefined}>
                  <SortableList
                    items={portals}
                    label={translateUi("Stalker portals")}
                    getKey={(portal) => portal.id}
                    getLabel={(portal) => portal.name}
                    onMove={(from, to) => savePortals(moveIndex(portals, from, to))}
                    edgeButtons={portals.length > 2}
                    rowClassName={(portal) => portal.enabled !== false ? "" : "is-off"}
                    renderMain={(portal) => (
                      <>
                        <span className="st-sort-title">
                          <span className="st-sort-name">{portal.name}</span>
                          <span className="st-tag">Stalker</span>
                        </span>
                        <span className="st-sort-sub">{maskedUrl(portal.portalUrl)} · {portal.macAddress}</span>
                        {importFlags(portal, (flag, checked) => savePortals(portals.map((item) => item.id === portal.id ? { ...item, [flag]: checked } : item)))}
                      </>
                    )}
                    renderActions={(portal) => (
                      <>
                        <Toggle
                          label={portal.name}
                          value={portal.enabled !== false}
                          onChange={(enabled) => savePortals(portals.map((item) => item.id === portal.id ? { ...item, enabled } : item))}
                        />
                        <button
                          type="button"
                          className="st-icon-btn"
                          aria-pressed={editingPortalId === portal.id}
                          aria-label={translateUi("Edit {value0}", { value0: portal.name })}
                          title={translateUi("Edit")}
                          onClick={() => { setEditingPlaylistId(null); setEditingPortalId(editingPortalId === portal.id ? null : portal.id); }}
                        >
                          <Pencil size={17} />
                        </button>
                        <button
                          type="button"
                          className="st-icon-btn is-danger"
                          aria-label={translateUi("Remove {value0}", { value0: portal.name })}
                          title={translateUi("Remove")}
                          onClick={() => {
                            if (editingPortalId === portal.id) setEditingPortalId(null);
                            savePortals(portals.filter((item) => item.id !== portal.id));
                          }}
                        >
                          <Trash2 size={17} />
                        </button>
                      </>
                    )}
                  />
                </div>
              )}
              {editingPortal && (
                <div className="st-edit-panel" role="region" aria-label={translateUi("Edit {value0}", { value0: editingPortal.name })}>
                  <div className="st-subhead stalker-edit-head"><h4>{translateUi("Edit {value0}", { value0: editingPortal.name })}</h4></div>
                  <PortalForm
                    key={editingPortal.id}
                    portal={editingPortal}
                    portals={portals}
                    userAgent={settings.customUserAgent}
                    submitLabel={translateUi("Save")}
                    onSubmit={(draft) => {
                      savePortals(upsertStalkerPortal(portals, { id: editingPortal.id, name: draft.name, portalUrl: draft.url, macAddress: draft.mac }));
                      setEditingPortalId(null);
                    }}
                    onCancel={() => setEditingPortalId(null)}
                  />
                </div>
              )}
            </>
          )}
        </Group>
      </SettingAnchor>

      <Group title="Channel list">
        <Row id="iptv-sort" label="Sort order" hint="How channels are ordered inside a category. A manual order you set under Layout replaces it for that category." scope="all">
          <Select label="Sort order" value={settings.iptvSortOrder ?? "provider"} onChange={(v) => updateSettings({ iptvSortOrder: v as "provider" | "number" | "name" })}
            options={[["provider", "Provider order"], ["number", "Channel number"], ["name", "Alphabetical (A-Z)"]]} />
        </Row>
        <Row id="iptv-favorites-home" label="Favorite channels on Home" hint="Pin your starred channels to the top of Home." scope="all">
          <Toggle label="Favorite channels on Home" value={settings.iptvFavoritesOnHome !== false} onChange={(v) => updateSettings({ iptvFavoritesOnHome: v })} />
        </Row>
        <Row id="guide-rows" label="Guide rows" hint="Channel rows shown in the TV guide." scope="android">
          <Select label="Guide rows" value={String(settings.guideRowCount ?? 0)} onChange={(v) => updateSettings({ guideRowCount: Number(v) })}
            options={[["0", "Automatic"], ["6", "6"], ["7", "7"], ["8", "8"], ["9", "9"], ["10", "10"]]} />
        </Row>
      </Group>

      {(settings.iptvPlaylists.length > 0 || portals.length > 0) && (
        <Group title={translateUi("Layout")} description={translateUi("Arrange categories and channels, hide what you don't watch, and order your favourites. The Live TV page on every ARVIO app follows it.")}>
          <SettingAnchor id="iptv-layout">
            <IptvGroupSettings key={groupScope} settings={settings} updateSettings={updateSettings} notify={setToast} channels={groupsLoaded ? iptvSnapshot.allChannels ?? iptvSnapshot.channels : []} />
          </SettingAnchor>
        </Group>
      )}
    </>
  );
}

function PlaylistForm({
  playlist,
  controlledKind,
  userAgent,
  submitLabel,
  resetOnSubmit,
  onSubmit,
  onCancel
}: {
  playlist?: IptvPlaylistEntry;
  controlledKind?: PlaylistKind;
  userAgent?: string;
  submitLabel: string;
  resetOnSubmit?: boolean;
  onSubmit: (entry: IptvPlaylistEntry) => void;
  onCancel?: () => void;
}) {
  const translateUi = useTranslation();
  const causeText = usePlaylistErrorText();
  const problemText = usePlaylistProblemText();
  const passwordId = useId();
  const [draft, setDraft] = useState<PlaylistDraft>(() =>
    playlist ? playlistDraftFromEntry(playlist) : emptyPlaylistDraft(controlledKind ?? "xtream")
  );
  const [showPassword, setShowPassword] = useState(false);
  const [test, setTest] = useState<PlaylistTestState>({ state: "idle" });
  const [error, setError] = useState("");
  const run = useRef(0);
  const activeKind: PlaylistKind = controlledKind ?? (draft.kind === "m3u" ? "m3u" : "xtream");

  const set = (patch: Partial<PlaylistDraft>) => {
    setDraft((current) => ({ ...current, ...patch }));
    setTest({ state: "idle" });
    setError("");
    run.current++;
  };

  const buildEntry = (): IptvPlaylistEntry | null => {
    const result = playlistFromDraft({ ...draft, kind: activeKind }, playlist?.id ?? crypto.randomUUID());
    if (typeof result === "string") {
      setError(problemText(result));
      return null;
    }
    return result;
  };

  const runTest = async () => {
    const entry = buildEntry();
    if (!entry) return;
    const current = ++run.current;
    setTest({ state: "testing" });
    const result = await testIptvPlaylist(entry, { userAgent });
    if (current !== run.current) return;
    setTest(result.ok
      ? { state: "ok", channels: result.channels }
      : { state: "error", text: causeText(playlistErrorCause(result.message)) });
  };

  const submit = () => {
    const entry = buildEntry();
    if (!entry) return;
    run.current++;
    onSubmit(entry);
    if (resetOnSubmit) {
      setDraft(emptyPlaylistDraft(activeKind));
      setShowPassword(false);
      setTest({ state: "idle" });
    }
  };

  return (
    <>
      {!controlledKind && (
        <div className="st-tabs iptv-edit-tabs">
          <Segmented<PlaylistKind>
            label="Playlist type"
            value={activeKind}
            onChange={(kind) => set({ kind })}
            options={[["xtream", "Xtream Codes"], ["m3u", "M3U link"]]}
          />
        </div>
      )}
      <form className="st-form" onSubmit={(event) => { event.preventDefault(); submit(); }}>
        <input
          className="st-input"
          value={draft.name}
          onChange={(e) => set({ name: e.target.value })}
          placeholder={translateUi("Name (e.g. My provider)")}
          aria-label={translateUi("Playlist name")}
          autoComplete="off"
        />
        {activeKind === "xtream" ? (
          <>
            <input
              className="st-input"
              value={draft.host}
              onChange={(e) => set({ host: e.target.value })}
              placeholder="http://provider.example:8080"
              aria-label={translateUi("Server address")}
              inputMode="url"
              autoComplete="off"
              autoCapitalize="none"
              spellCheck={false}
            />
            <input
              className="st-input"
              value={draft.username}
              onChange={(e) => set({ username: e.target.value })}
              placeholder={translateUi("Username")}
              aria-label={translateUi("Username")}
              autoComplete="off"
              autoCapitalize="none"
              spellCheck={false}
            />
            <div className="st-secret">
              <input
                id={passwordId}
                className="st-input is-wide"
                type={showPassword ? "text" : "password"}
                value={draft.password}
                onChange={(e) => set({ password: e.target.value })}
                placeholder={translateUi("Password")}
                aria-label={translateUi("Password")}
                autoComplete="new-password"
                data-1p-ignore=""
                data-lpignore="true"
                spellCheck={false}
              />
              <button
                type="button"
                className="st-icon-btn"
                aria-controls={passwordId}
                aria-label={translateUi(showPassword ? "Hide" : "Show")}
                onClick={() => setShowPassword(!showPassword)}
              >
                {showPassword ? <EyeOff size={17} /> : <Eye size={17} />}
              </button>
            </div>
          </>
        ) : (
          <>
            <input
              className="st-input"
              value={draft.url}
              onChange={(e) => set({ url: e.target.value })}
              placeholder="https://provider.example/playlist.m3u"
              aria-label={translateUi("M3U playlist URL")}
              inputMode="url"
              autoComplete="off"
              autoCapitalize="none"
              spellCheck={false}
            />
            <input
              className="st-input"
              value={draft.epg}
              onChange={(e) => set({ epg: e.target.value })}
              placeholder={translateUi("EPG XMLTV URL (optional)")}
              aria-label={translateUi("EPG XMLTV URL (optional)")}
              inputMode="url"
              autoComplete="off"
              autoCapitalize="none"
              spellCheck={false}
            />
          </>
        )}
        {(error || test.state !== "idle") && (
          <p className="st-row-hint stalker-test" role={error ? "alert" : "status"}>
            {error ? <Status tone="warn">{error}</Status>
              : test.state === "testing" ? <Status tone="busy">{translateUi("Testing…")}</Status>
              : test.state === "ok" ? <Status tone="ok">{translateUi("Works: {value0} channels found.", { value0: test.channels.toLocaleString() })}</Status>
              : test.state === "error" ? <Status tone="warn">{test.text}</Status> : null}
          </p>
        )}
        <div className="st-form-actions">
          <button type="button" className="st-btn" disabled={test.state === "testing"} onClick={() => void runTest()}>{translateUi("Test")}</button>
          <button type="submit" className="st-btn is-primary">{!playlist && <Plus size={16} />} {submitLabel}</button>
          {onCancel && <button type="button" className="st-btn is-ghost" onClick={onCancel}>{translateUi("Cancel")}</button>}
        </div>
      </form>
    </>
  );
}

/* ---------- Metadata & keys ---------- */

export function MetadataSection() {
  const translateUi = useTranslation();
  const { settings, updateSettings: set } = useAppPick("settings", "updateSettings");
  const chain = (value: string[] | undefined, fallback: string[]) => (value?.length ? value : fallback).map((p) => p.toUpperCase()).join(" → ");
  const tvdbActive = Boolean(settings.customTvdbApiKey?.trim());
  return (
    <>
      <SectionHeader title="Metadata and keys" description="Bring your own TMDB or TVDB key if you have one. Keys stay in this browser and are never uploaded." />
      <Group title="Your API keys">
        <Row id="tmdb-key" label="TMDB API key" hint="Custom v3 API key for TMDB requests. Leave empty to use ARVIO's." scope="browser" stack>
          <SecretInput label="TMDB API key" value={settings.customTmdbApiKey || ""} placeholder="System Default Key" onChange={(v) => set({ customTmdbApiKey: v })} />
        </Row>
        <Row id="tvdb-key" label="TVDB v4 API key" hint={tvdbActive ? "Active — TVDB enabled for metadata fallback" : "TVDB is disabled until a custom API key is provided"} scope="browser" stack>
          <SecretInput label="TVDB v4 API key" value={settings.customTvdbApiKey || ""} placeholder="Enter Custom TVDB Key to Enable" onChange={(v) => set({ customTvdbApiKey: v })} />
        </Row>
        <Row id="tvdb-pin" label="TVDB user PIN" hint="Required if using subscriber user key" scope="browser" stack>
          <SecretInput label="TVDB user PIN" value={settings.customTvdbUserPin || ""} placeholder="Optional User PIN" onChange={(v) => set({ customTvdbUserPin: v })} />
        </Row>
      </Group>
      <Group title="Where details come from" description="ARVIO asks these sources in order and uses the first good match.">
        <Row label="Anime"><Status tone="off">{chain(settings.metadataAnimeProviders, ["anilist", "tvdb", "tmdb"])}</Status></Row>
        <Row label="TV shows"><Status tone="off">{chain(settings.metadataTvProviders, ["tvdb", "tmdb"])}</Status></Row>
        <Row label="Movies"><Status tone="off">{chain(settings.metadataMovieProviders, ["tmdb"])}</Status></Row>
      </Group>
      {!tvdbActive && <p className="st-row-hint">{translateUi("Without a TVDB key, TV shows use TMDB.")}</p>}
    </>
  );
}

