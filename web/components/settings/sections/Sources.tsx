"use client";
import { MAX_IPTV_PLAYLISTS } from "@/lib/iptvPlaylistIdentity";
import { Plus, RefreshCw, Trash2 } from "lucide-react";
import { useEffect, useState } from "react";
import { useTranslation } from "@/lib/i18n";
import { iptvPlaylistSignature } from "@/lib/iptv";
import { moveIndex } from "@/lib/ordering";
import { useAppPick } from "@/lib/store";
import { isManagedId } from "@/lib/managedConfiguration";
import { ManagedConnections } from "../ManagedConnections";
import type { IptvPlaylistEntry } from "@/lib/types";
import { hasStalkerPortals, stalkerPortalsOf, stalkerSourceSignature } from "@/lib/stalkerPortals";
import { IptvGroupSettings } from "../IptvGroupSettings";
import { StalkerPortalSettings } from "../StalkerPortalSettings";
import { SortableList } from "../SortableList";
import { Empty, Group, Row, ScopeBadge, SecretInput, Select, SectionHeader, SettingAnchor, Status, Toggle, safeArray } from "../ui";

// Moved to their own files; re-exported so existing imports keep working.
export { AddonsSection } from "./Addons";
export { CatalogsSection } from "./HomeRows";
export { HomeServerSection } from "./HomeServer";

/* ---------- Live TV (IPTV) ---------- */

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
  const [name, setName] = useState("");
  const [m3uUrl, setM3uUrl] = useState("");
  const [epgUrl, setEpgUrl] = useState("");
  const playlists = safeArray(settings.iptvPlaylists).filter(playlist => !isManagedId(playlist.id));
  const updatePlaylists = (next: IptvPlaylistEntry[]) => updateSettings({ iptvPlaylists: next });
  const isLoadingTv = Boolean(busy && busy.toLowerCase().includes("tv"));
  const channelCount = groupsLoaded ? (iptvSnapshot.allChannels ?? iptvSnapshot.channels ?? []).length : 0;

  const addPlaylist = () => {
    if (playlists.length >= MAX_IPTV_PLAYLISTS) { setToast("Maximum of 10 IPTV playlists reached"); return; }
    const trimmedM3u = m3uUrl.trim();
    const trimmedEpg = epgUrl.trim();
    if (!trimmedM3u) { setToast("Enter an M3U playlist URL first."); return; }
    if (!/^https?:\/\//i.test(trimmedM3u)) { setToast("Playlist URL must start with http:// or https://."); return; }
    if (trimmedEpg && !/^https?:\/\//i.test(trimmedEpg)) { setToast("EPG URL must start with http:// or https://."); return; }
    updatePlaylists([{ id: crypto.randomUUID(), name: name.trim() || "IPTV Playlist", m3uUrl: trimmedM3u, epgUrl: trimmedEpg, enabled: true }, ...playlists]);
    setName(""); setM3uUrl(""); setEpgUrl("");
    setToast("IPTV playlist saved.");
  };
  const edit = (id: string, patch: Partial<IptvPlaylistEntry>) => updatePlaylists(playlists.map((item) => item.id === id ? { ...item, ...patch } : item));

  return (
    <>
      <SectionHeader title="Live TV" description="IPTV playlists, programme guides and portals for this profile. They sync to the Live TV page on every ARVIO app.">
        <button type="button" className="st-btn" disabled={isLoadingTv} onClick={() => void refreshIptv({ fresh: true })}>
          <RefreshCw size={16} className={isLoadingTv ? "spin" : undefined} /> {translateUi(isLoadingTv ? "Refreshing..." : "Refresh channels")}
        </button>
      </SectionHeader>

      <ManagedConnections items={settings.iptvPlaylists.filter(playlist => isManagedId(playlist.id))} />
      <Group title={`${translateUi("Playlists")} · ${playlists.length}`} description={<>
        {groupsLoaded && channelCount ? `${translateUi("{value0} channels loaded.", { value0: channelCount.toLocaleString() })} ` : ""}
        {translateUi("The first playlist is the main one; drag to change the order the playlists are listed in.")} <ScopeBadge scope="all" />
      </>}>
        {playlists.length === 0 ? <Empty>{translateUi("No IPTV playlists configured.")}</Empty> : (
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
                <input className="st-input is-wide" aria-label={translateUi("Playlist name")} value={playlist.name} onChange={(e) => edit(playlist.id, { name: e.target.value })} />
                <input className="st-input is-wide" aria-label={translateUi("M3U playlist URL")} value={playlist.m3uUrl} onChange={(e) => edit(playlist.id, { m3uUrl: e.target.value })} />
                <input className="st-input is-wide" aria-label={translateUi("EPG URL")} placeholder={translateUi("EPG XMLTV URL (optional)")} value={playlist.epgUrl ?? ""} onChange={(e) => edit(playlist.id, { epgUrl: e.target.value })} />
                <span className="st-import-flags">
                  <span className="st-sort-sub">{translateUi("Import on Android and TV")}</span>
                  {([["importLiveTv", "Live channels"], ["importVod", "Movies"], ["importSeries", "Series"]] as const).map(([flag, label]) => (
                    <label key={flag} className="st-check">
                      <input type="checkbox" checked={playlist[flag] !== false} onChange={(e) => edit(playlist.id, { [flag]: e.target.checked })} />
                      {translateUi(label)}
                    </label>
                  ))}
                  <ScopeBadge scope="android" />
                </span>
              </>
            )}
            renderActions={(playlist) => (
              <>
                <Toggle label={playlist.name} value={playlist.enabled} onChange={(enabled) => edit(playlist.id, { enabled })} />
                <button type="button" className="st-icon-btn is-danger" aria-label={translateUi("Remove {value0}", { value0: playlist.name })} onClick={() => updatePlaylists(playlists.filter((item) => item.id !== playlist.id))}><Trash2 size={17} /></button>
              </>
            )}
          />
        )}
      </Group>

      <Group title="Add a playlist">
        <div className="st-form">
          <input className="st-input" value={name} onChange={(e) => setName(e.target.value)} placeholder={translateUi("Playlist name")} aria-label={translateUi("Playlist name")} />
          <input className="st-input" value={m3uUrl} onChange={(e) => setM3uUrl(e.target.value)} placeholder={translateUi("M3U playlist URL")} aria-label={translateUi("M3U playlist URL")} inputMode="url" />
          <input className="st-input" value={epgUrl} onChange={(e) => setEpgUrl(e.target.value)} placeholder={translateUi("EPG XMLTV URL (optional)")} aria-label={translateUi("EPG XMLTV URL (optional)")} inputMode="url" />
          <div className="st-form-actions"><button type="button" className="st-btn is-primary" onClick={addPlaylist}><Plus size={16} /> {translateUi("Add playlist")}</button></div>
        </div>
      </Group>

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

      {(settings.iptvPlaylists.length > 0 || stalkerPortalsOf(settings).length > 0) && (
        <Group title={translateUi("Layout")} description={translateUi("Arrange categories and channels, hide what you don't watch, and order your favourites. The Live TV page on every ARVIO app follows it.")}>
          <SettingAnchor id="iptv-layout">
            <IptvGroupSettings key={groupScope} settings={settings} updateSettings={updateSettings} notify={setToast} channels={groupsLoaded ? iptvSnapshot.allChannels ?? iptvSnapshot.channels : []} />
          </SettingAnchor>
        </Group>
      )}

      <SettingAnchor id="stalker-url">
        <StalkerPortalSettings settings={settings} updateSettings={updateSettings} />
      </SettingAnchor>
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

