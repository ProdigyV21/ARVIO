"use client";
// Live TV layout editor: which categories show and in what order, which
// channel sits where inside a category, and the favourites — per playlist,
// synced to the Live TV page on every ARVIO app.

import { Check, Eye, EyeOff, ListOrdered, Lock, Pencil, RotateCcw, Search, Star, X } from "lucide-react";
import { useDeferredValue, useMemo, useRef, useState, type ReactNode } from "react";
import "./liveTvSettings.css";
import { useTranslation } from "@/lib/i18n";
import {
  MAX_CHANNEL_ORDER_ENTRIES, MAX_GROUP_NAME_LENGTH, UNGROUPED, applyChannelOrder, clearPlaylistChannelOrder, groupDisplayName, groupMatchesQuery,
  isFavoriteGroup, minimalChannelOrder, playlistGroupKey, playlistGroups, replacePlaylistGroupOrder, setGroupChannelOrder,
  setGroupDisplayName, setPlaylistGroupsVisible, sortChannelsLikeLiveTv, toggleFavoriteGroup, type PlaylistGroup,
} from "@/lib/iptvGroups";
import { channelPlaylistId, stalkerPortalsOf } from "@/lib/stalkerPortals";
import { toggleFavoriteChannel } from "@/lib/iptvFavorites";
import { channelIdentityIndex } from "@/lib/iptvSession";
import { moveIndex, moveWithinSubset } from "@/lib/ordering";
import type { AppSettings, IptvChannel } from "@/lib/types";
import { SortableList } from "./SortableList";
import { ScopeBadge, Segmented, Select } from "./ui";

type View = "categories" | "channels" | "favorites";
const SEARCH_LIMIT = 200;
const ROW_HEIGHT = 60;

export function IptvGroupSettings({ channels, settings, updateSettings, notify }: {
  channels: IptvChannel[];
  settings: AppSettings;
  updateSettings: (patch: Partial<AppSettings>) => void;
  notify?: (message: string) => void;
}) {
  const translateUi = useTranslation();
  const [view, setView] = useState<View>("categories");
  const [selected, setSelected] = useState("");
  const [focusGroup, setFocusGroup] = useState("");
  const [groupQuery, setGroupQuery] = useState("");
  const [channelQuery, setChannelQuery] = useState("");
  const [favoriteQuery, setFavoriteQuery] = useState("");
  const deferredChannelQuery = useDeferredValue(channelQuery);
  const deferredFavoriteQuery = useDeferredValue(favoriteQuery);

  // Each Stalker portal is its own "playlist": its categories, order and names are kept apart.
  const playlists = useMemo(() => [
    ...settings.iptvPlaylists.map((playlist) => ({ id: playlist.id, name: playlist.name || playlist.id })),
    ...stalkerPortalsOf(settings).map((portal) => ({ id: portal.id, name: portal.name || translateUi("Stalker portal") })),
  ], [settings.iptvPlaylists, settings.iptvStalkerPortals, settings.iptvStalkerUrl, settings.iptvStalkerMac, translateUi]); // eslint-disable-line react-hooks/exhaustive-deps
  const playlistId = playlists.some((playlist) => playlist.id === selected) ? selected : playlists[0]?.id ?? "";
  const groupOrder = settings.groupOrder ?? [];
  const hiddenGroups = settings.hiddenGroupIds ?? [];
  const lockedGroups = useMemo(() => new Set(settings.lockedIptvGroupIds ?? []), [settings.lockedIptvGroupIds]);
  const favoriteGroups = settings.favoriteGroupIds ?? [];
  const channelOrder = settings.iptvChannelOrder ?? {};
  const groupNames = settings.iptvGroupNames;

  const groups = useMemo(() => playlistGroups(channels, playlistId, groupOrder, groupNames), [channels, playlistId, groupOrder, groupNames]);
  const playlistChannels = useMemo(() => channels.filter((channel) => channelPlaylistId(channel.id) === playlistId), [channels, playlistId]);
  const channelsByGroup = useMemo(() => {
    const map = new Map<string, IptvChannel[]>();
    for (const channel of playlistChannels) {
      const key = playlistGroupKey(playlistId, channel.group);
      const list = map.get(key);
      if (list) list.push(channel); else map.set(key, [channel]);
    }
    return map;
  }, [playlistChannels, playlistId]);
  const identity = useMemo(() => channelIdentityIndex(channels), [channels]);

  if (!playlists.length) return null;

  const activeGroup = groups.some((group) => group.key === focusGroup) ? focusGroup : groups[0]?.key ?? "";
  const openGroup = (key: string) => { setFocusGroup(key); setChannelQuery(""); setView("channels"); };

  return (
    <section className="iptv-group-settings" aria-label={translateUi("Live TV layout")}>
      <div className="st-toolbar">
        {playlists.length > 1 && (
          <Select label="Playlist" translateLabels={false} value={playlistId} onChange={(id) => { setSelected(id); setFocusGroup(""); }}
            options={playlists.map((playlist) => [playlist.id, playlist.name] as [string, string])} />
        )}
        <Segmented<View> label="Edit" value={view} onChange={setView}
          options={[["categories", "Categories"], ["channels", "Channels"], ["favorites", "Favourites"]]} />
      </div>
      {view === "categories" && (
        <CategoriesView groups={groups} groupOrder={groupOrder} hiddenGroups={hiddenGroups} lockedGroups={lockedGroups}
          favoriteGroups={favoriteGroups} channelOrder={channelOrder} groupNames={groupNames} playlistId={playlistId} query={groupQuery} setQuery={setGroupQuery}
          updateSettings={updateSettings} openGroup={openGroup} />
      )}
      {view === "channels" && (
        <ChannelsView groups={groups} activeGroup={activeGroup} setActiveGroup={setFocusGroup} channelsByGroup={channelsByGroup}
          playlistChannels={playlistChannels} playlistId={playlistId} settings={settings} channelOrder={channelOrder} groupNames={groupNames} identity={identity}
          query={channelQuery} deferredQuery={deferredChannelQuery} setQuery={setChannelQuery} updateSettings={updateSettings} notify={notify} />
      )}
      {view === "favorites" && (
        <FavoritesView channels={channels} identity={identity} settings={settings} playlists={playlists} query={favoriteQuery} deferredQuery={deferredFavoriteQuery}
          setQuery={setFavoriteQuery} updateSettings={updateSettings} />
      )}
    </section>
  );
}

/* ---------- categories ---------- */

/** A category's name as shown: the user's own name, or the playlist's (a blank one reads "Ungrouped"). */
function useGroupLabel() {
  const translateUi = useTranslation();
  return (group: Pick<PlaylistGroup, "name" | "label">) => group.label !== group.name ? group.label
    : group.name === UNGROUPED ? translateUi("Ungrouped") : group.name;
}

function CategoriesView({ groups, groupOrder, hiddenGroups, lockedGroups, favoriteGroups, channelOrder, groupNames, playlistId, query, setQuery, updateSettings, openGroup }: {
  groups: PlaylistGroup[];
  groupOrder: string[];
  hiddenGroups: string[];
  lockedGroups: Set<string>;
  favoriteGroups: string[];
  channelOrder: Record<string, string[]>;
  groupNames: Record<string, string> | undefined;
  playlistId: string;
  query: string;
  setQuery: (value: string) => void;
  updateSettings: (patch: Partial<AppSettings>) => void;
  openGroup: (key: string) => void;
}) {
  const translateUi = useTranslation();
  const labelOf = useGroupLabel();
  const [renaming, setRenaming] = useState<string | null>(null);
  // Escape closes the field without saving, even if the browser blurs it on the way out.
  const cancelRename = useRef(false);
  const needle = query.trim().toLowerCase();
  // Search finds a category by the playlist's name and by the name it was given.
  const shown = needle ? groups.filter((group) => groupMatchesQuery(group, needle)) : groups;
  const rename = (group: PlaylistGroup, value: string) => {
    setRenaming(null);
    const next = setGroupDisplayName(groupNames, group.key, value);
    if ((next[group.key] ?? "") !== (groupNames?.[group.key] ?? "")) updateSettings({ iptvGroupNames: next });
  };
  const hidden = new Set(hiddenGroups);
  const hasLocked = groups.some((group) => lockedGroups.has(group.key));
  const move = (from: number, to: number) => {
    const keys = groups.map((group) => group.key);
    const next = needle ? moveWithinSubset(keys, (key) => key, shown.map((group) => group.key), from, to) : moveIndex(keys, from, to);
    if (next !== keys) updateSettings({ groupOrder: replacePlaylistGroupOrder(groupOrder, next) });
  };
  const visibleInOrder = groups.filter((group) => !hidden.has(group.key) && !lockedGroups.has(group.key));

  return (
    <div className="st-layout">
      <div className="st-layout-main">
        <p className="st-hint">
          {translateUi("Drag categories, or use the arrows, to set their order on the Live TV page. Your order replaces the playlist's own order. Use the pencil to give a category your own name.")}{" "}
          <ScopeBadge scope="all" />
        </p>
        <div className="st-toolbar">
          <input className="st-input" type="search" value={query} onChange={(event) => setQuery(event.target.value)}
            placeholder={translateUi("Find a category")} aria-label={translateUi("Find a category")} />
          <button type="button" className="st-btn" disabled={!groups.length} onClick={() => updateSettings({ hiddenGroupIds: setPlaylistGroupsVisible(hiddenGroups, shown, true) })}><Eye size={16} />{translateUi("Show all")}</button>
          <button type="button" className="st-btn" disabled={!groups.length} onClick={() => updateSettings({ hiddenGroupIds: setPlaylistGroupsVisible(hiddenGroups, shown, false) })}><EyeOff size={16} />{translateUi("Hide all")}</button>
          <button type="button" className="st-btn" title={translateUi("Reset order")} aria-label={translateUi("Reset order")}
            onClick={() => updateSettings({ groupOrder: groupOrder.filter((key) => !key.startsWith(`${playlistId}|`)) })}><RotateCcw size={16} /></button>
        </div>
        {hasLocked && <p className="st-hint">{translateUi("Locked categories stay hidden on ARVIO Web. Lock and unlock them on your TV, where the PIN is asked.")}</p>}
        <SortableList
          items={shown}
          label={translateUi("Categories")}
          getKey={(group) => group.key}
          getLabel={labelOf}
          onMove={move}
          rowHeight={ROW_HEIGHT}
          viewportHeight={540}
          rowClassName={(group) => hidden.has(group.key) ? "is-off" : ""}
          emptyText={groups.length ? translateUi("No categories match “{value0}”.", { value0: query.trim() }) : translateUi("Channels are still loading. Categories appear here once the playlist has loaded.")}
          renderMain={(group) => {
            const locked = lockedGroups.has(group.key);
            const custom = Boolean(channelOrder[group.key]?.length);
            const renamed = group.label !== group.name;
            return <>
              <span className="st-sort-title">
                {renaming === group.key ? (
                  <input className="st-input iptv-group-rename" defaultValue={group.label} maxLength={MAX_GROUP_NAME_LENGTH} autoFocus
                    aria-label={translateUi("New name for {value0}", { value0: labelOf(group) })} placeholder={group.name}
                    onFocus={(event) => event.currentTarget.select()}
                    onBlur={(event) => { if (cancelRename.current) cancelRename.current = false; else rename(group, event.currentTarget.value); }}
                    onKeyDown={(event) => {
                      event.stopPropagation();
                      if (event.key === "Escape") { event.preventDefault(); cancelRename.current = true; setRenaming(null); }
                      if (event.key === "Enter") { event.preventDefault(); rename(group, event.currentTarget.value); }
                    }} />
                ) : (
                  <button type="button" className="st-sort-name-btn iptv-group-name" title={translateUi("Arrange channels")} onClick={() => openGroup(group.key)}>{labelOf(group)}</button>
                )}
                {locked && <span className="st-tag" title={translateUi("Locked on your TV")}><Lock size={11} /> {translateUi("Locked")}</span>}
              </span>
              <span className="st-sort-sub">
                {renamed ? `${translateUi("Playlist name: {value0}", { value0: group.name === UNGROUPED ? translateUi("Ungrouped") : group.name })} · ` : ""}
                {translateUi("{value0} channels", { value0: group.count.toLocaleString() })}
                {hidden.has(group.key) ? ` · ${translateUi("Hidden")}` : ""}
                {custom ? ` · ${translateUi("Custom channel order")}` : ""}
              </span>
            </>;
          }}
          renderActions={(group) => {
            const isHidden = hidden.has(group.key);
            const favorite = isFavoriteGroup(favoriteGroups, group.key);
            const label = labelOf(group);
            return <>
              {renaming === group.key ? (
                <button type="button" className="st-icon-btn" aria-label={`${translateUi("Save name")}: ${label}`} title={translateUi("Save name")}
                  onMouseDown={(event) => event.preventDefault()}
                  onClick={(event) => rename(group, (event.currentTarget.closest("li")?.querySelector<HTMLInputElement>(".iptv-group-rename")?.value) ?? group.label)}><Check size={17} /></button>
              ) : (
                <button type="button" className="st-icon-btn" aria-label={`${translateUi("Rename")}: ${label}`} title={translateUi("Rename")}
                  onClick={() => { cancelRename.current = false; setRenaming(group.key); }}><Pencil size={16} /></button>
              )}
              {group.label !== group.name && renaming !== group.key && (
                <button type="button" className="st-icon-btn" aria-label={`${translateUi("Use the playlist's name")}: ${label}`} title={translateUi("Use the playlist's name")}
                  onClick={() => rename(group, "")}><RotateCcw size={16} /></button>
              )}
              <button type="button" className={`st-icon-btn${favorite ? " is-on" : ""}`} aria-pressed={favorite}
                aria-label={`${(favorite ? translateUi("Remove from favourites") : translateUi("Add to favourites"))}: ${label}`} title={(favorite ? translateUi("Remove from favourites") : translateUi("Add to favourites"))}
                onClick={() => updateSettings({ favoriteGroupIds: toggleFavoriteGroup(favoriteGroups, group.key) })}><Star size={17} /></button>
              <button type="button" className="st-icon-btn" aria-label={`${(isHidden ? translateUi("Show") : translateUi("Hide"))}: ${label}`} title={(isHidden ? translateUi("Show") : translateUi("Hide"))}
                onClick={() => updateSettings({ hiddenGroupIds: setPlaylistGroupsVisible(hiddenGroups, [group], isHidden) })}>{isHidden ? <EyeOff size={17} /> : <Eye size={17} />}</button>
            </>;
          }}
        />
      </div>
      <Preview title={translateUi("Live TV page")} note={translateUi("Favourite categories are also pinned to the top on ARVIO Web.")}>
        <li className="is-fixed"><Star size={13} /><span>{translateUi("Favorites")}</span></li>
        <li className="is-fixed"><span>{translateUi("Recently Watched")}</span></li>
        <li className="is-fixed"><span>{translateUi("All Channels")}</span></li>
        {visibleInOrder.slice(0, 14).map((group) => (
          <li key={group.key}>{isFavoriteGroup(favoriteGroups, group.key) && <Star size={13} />}<span>{labelOf(group)}</span></li>
        ))}
        {visibleInOrder.length > 14 && <li className="st-preview-more">{translateUi("+ {value0} more", { value0: visibleInOrder.length - 14 })}</li>}
      </Preview>
    </div>
  );
}

/* ---------- channels inside a category ---------- */

function ChannelsView({ groups, activeGroup, setActiveGroup, channelsByGroup, playlistChannels, playlistId, settings, channelOrder, groupNames, identity, query, deferredQuery, setQuery, updateSettings, notify }: {
  groupNames: Record<string, string> | undefined;
  identity: Map<string, IptvChannel>;
  groups: PlaylistGroup[];
  activeGroup: string;
  setActiveGroup: (key: string) => void;
  channelsByGroup: Map<string, IptvChannel[]>;
  playlistChannels: IptvChannel[];
  playlistId: string;
  settings: AppSettings;
  channelOrder: Record<string, string[]>;
  query: string;
  deferredQuery: string;
  setQuery: (value: string) => void;
  updateSettings: (patch: Partial<AppSettings>) => void;
  notify?: (message: string) => void;
}) {
  const translateUi = useTranslation();
  const labelOf = useGroupLabel();
  const [editingPosition, setEditingPosition] = useState<string | null>(null);
  const sortOrder = settings.iptvSortOrder ?? "provider";
  const base = useMemo(() => sortChannelsLikeLiveTv(channelsByGroup.get(activeGroup) ?? [], sortOrder), [channelsByGroup, activeGroup, sortOrder]);
  const ordered = useMemo(() => applyChannelOrder(base, channelOrder[activeGroup]), [base, channelOrder, activeGroup]);
  const favorites = settings.favoriteChannelIds ?? [];
  const favoriteSet = useMemo(() => new Set(favorites), [favorites]);
  const isFavorite = (channel: IptvChannel) => favoriteSet.has(channel.id) || Boolean(channel.cloudId && favoriteSet.has(channel.cloudId)) ||
    Boolean(channel.syncAliases?.some((alias) => favoriteSet.has(alias)));
  const toggleFavorite = (channel: IptvChannel) => updateSettings({ favoriteChannelIds: toggleFavoriteChannel(favorites, channel.id, identity) });
  const custom = Boolean(channelOrder[activeGroup]?.length);
  const needle = deferredQuery.trim().toLowerCase();
  const results = useMemo(() => {
    if (!needle) return [];
    const found: IptvChannel[] = [];
    for (const channel of playlistChannels) {
      if (channel.name.toLowerCase().includes(needle) || channel.number === needle || channel.tvgId?.toLowerCase() === needle) {
        found.push(channel);
        if (found.length >= SEARCH_LIMIT) break;
      }
    }
    return found;
  }, [needle, playlistChannels]);

  const save = (next: IptvChannel[]) => {
    const refs = minimalChannelOrder(next, base);
    if (refs.length > MAX_CHANNEL_ORDER_ENTRIES) {
      notify?.(translateUi("This category is too big to place a channel that far down. Move channels up instead, or sort the playlist by name or number."));
      return;
    }
    updateSettings({ iptvChannelOrder: setGroupChannelOrder(channelOrder, activeGroup, refs) });
  };
  const move = (from: number, to: number) => {
    const next = moveIndex(ordered, from, to);
    if (next !== ordered) save(next);
  };
  const activeEntry = groups.find((group) => group.key === activeGroup);
  const groupName = activeEntry ? labelOf(activeEntry) : groupDisplayName(groupNames, activeGroup);

  return (
    <div className="st-layout">
      <div className="st-layout-main">
        <p className="st-hint">
          {translateUi("Put channels where you want them inside a category. A manual order replaces the sort order for that category only; everything you have not moved keeps the sort order.")}{" "}
          <ScopeBadge scope="all" />{" "}
          <span>{translateUi("On Android and TV from the next app update.")}</span>
        </p>
        <div className="st-toolbar">
          {groups.length > 0 && (
            <Select label="Category" translateLabels={false} value={activeGroup} onChange={(key) => { setActiveGroup(key); setQuery(""); }}
              options={groups.map((group) => [group.key, `${labelOf(group)} (${group.count.toLocaleString()})`] as [string, string])} />
          )}
          <input className="st-input" type="search" value={query} onChange={(event) => setQuery(event.target.value)}
            placeholder={translateUi("Search this playlist's channels")} aria-label={translateUi("Search this playlist's channels")} />
          <button type="button" className="st-btn" disabled={!custom}
            onClick={() => updateSettings({ iptvChannelOrder: setGroupChannelOrder(channelOrder, activeGroup, []) })}><RotateCcw size={16} />{translateUi("Reset channel order")}</button>
          {Object.keys(channelOrder).some((key) => key.startsWith(`${playlistId}|`)) && (
            <button type="button" className="st-btn is-ghost" onClick={() => updateSettings({ iptvChannelOrder: clearPlaylistChannelOrder(channelOrder, playlistId) })}>{translateUi("Reset all categories")}</button>
          )}
        </div>
        {needle ? (
          <ul className="st-picker-results" aria-label={translateUi("Search results")}>
            {results.length === 0 && <li className="st-sort-empty">{translateUi("No channels match “{value0}”.", { value0: deferredQuery.trim() })}</li>}
            {results.map((channel) => {
              const key = playlistGroupKey(playlistId, channel.group);
              return (
                <li key={channel.id}>
                  <ChannelLabel channel={channel} sub={groupNames?.[key] || channel.group || translateUi("Ungrouped")} />
                  <button type="button" className={`st-icon-btn${isFavorite(channel) ? " is-on" : ""}`} aria-pressed={isFavorite(channel)}
                    aria-label={`${(isFavorite(channel) ? translateUi("Remove from favourites") : translateUi("Add to favourites"))}: ${channel.name}`} onClick={() => toggleFavorite(channel)}><Star size={17} /></button>
                  <button type="button" className="st-btn" onClick={() => { setActiveGroup(key); setQuery(""); }}><ListOrdered size={16} />{translateUi("Arrange")}</button>
                </li>
              );
            })}
            {results.length >= SEARCH_LIMIT && <li className="st-sort-empty">{translateUi("Showing the first {value0} matches. Type more to narrow it down.", { value0: SEARCH_LIMIT })}</li>}
          </ul>
        ) : (
          <SortableList
            items={ordered}
            label={translateUi("Channels")}
            getKey={(channel) => channel.id}
            getLabel={(channel) => channel.name}
            onMove={move}
            rowHeight={ROW_HEIGHT}
            viewportHeight={540}
            emptyText={translateUi("This category has no channels yet.")}
            renderMain={(channel, { index }) => (
              <span className="st-sort-title">
                {editingPosition === channel.id ? (
                  <input className="st-pos-input" type="number" min={1} max={ordered.length} defaultValue={index + 1} autoFocus aria-label={translateUi("New position for {value0}", { value0: channel.name })}
                    onBlur={() => setEditingPosition(null)}
                    onKeyDown={(event) => {
                      if (event.key === "Escape") { event.stopPropagation(); setEditingPosition(null); }
                      if (event.key !== "Enter") return;
                      const target = Number((event.target as HTMLInputElement).value);
                      setEditingPosition(null);
                      if (Number.isFinite(target)) move(index, Math.round(target) - 1);
                    }} />
                ) : (
                  <button type="button" className="st-pos" title={translateUi("Move to position…")} aria-label={translateUi("Position {value0}. Move {value1} to another position", { value0: index + 1, value1: channel.name })}
                    onClick={() => setEditingPosition(channel.id)}>{index + 1}</button>
                )}
                <ChannelLabel channel={channel} />
              </span>
            )}
            renderActions={(channel) => (
              <button type="button" className={`st-icon-btn${isFavorite(channel) ? " is-on" : ""}`} aria-pressed={isFavorite(channel)}
                aria-label={`${(isFavorite(channel) ? translateUi("Remove from favourites") : translateUi("Add to favourites"))}: ${channel.name}`} title={(isFavorite(channel) ? translateUi("Remove from favourites") : translateUi("Add to favourites"))}
                onClick={() => toggleFavorite(channel)}><Star size={17} /></button>
            )}
          />
        )}
      </div>
      <Preview title={groupName} note={custom ? translateUi("Custom order. Channels you have not moved follow the sort order.")
        : sortOrder === "number" ? translateUi("Sorted by channel number.") : sortOrder === "name" ? translateUi("Sorted by name.") : translateUi("In the playlist's own order.")}>
        {ordered.slice(0, 14).map((channel, index) => <li key={channel.id}><small>{index + 1}</small><span>{channel.name}</span></li>)}
        {ordered.length > 14 && <li className="st-preview-more">{translateUi("+ {value0} more", { value0: (ordered.length - 14).toLocaleString() })}</li>}
      </Preview>
    </div>
  );
}

function ChannelLabel({ channel, sub }: { channel: IptvChannel; sub?: string }) {
  return (
    <span className="st-sort-main" style={{ gap: 2 }}>
      <span className="st-sort-title">
        {channel.logo ? <img className="st-logo" src={channel.logo} alt="" loading="lazy" decoding="async" referrerPolicy="no-referrer" onError={(event) => { event.currentTarget.style.visibility = "hidden"; }} /> : null}
        <span className="st-sort-name">{channel.name}</span>
      </span>
      {(sub || channel.number) && <span className="st-sort-sub">{[channel.number ? `#${channel.number}` : "", sub ?? ""].filter(Boolean).join(" · ")}</span>}
    </span>
  );
}

/* ---------- favourites ---------- */

type FavoriteEntry = { ref: string; channel?: IptvChannel };

function FavoritesView({ channels, identity, settings, playlists, query, deferredQuery, setQuery, updateSettings }: {
  channels: IptvChannel[];
  identity: Map<string, IptvChannel>;
  settings: AppSettings;
  playlists: Array<{ id: string; name: string }>;
  query: string;
  deferredQuery: string;
  setQuery: (value: string) => void;
  updateSettings: (patch: Partial<AppSettings>) => void;
}) {
  const translateUi = useTranslation();
  const favorites = settings.favoriteChannelIds ?? [];
  const favoriteGroups = settings.favoriteGroupIds ?? [];
  const entries = useMemo<FavoriteEntry[]>(() => favorites.map((ref) => ({ ref, channel: identity.get(ref) })), [favorites, identity]);
  const favoriteIds = useMemo(() => new Set(entries.map((entry) => entry.channel?.id).filter(Boolean)), [entries]);
  const needle = deferredQuery.trim().toLowerCase();
  const results = useMemo(() => {
    if (!needle) return [];
    const found: IptvChannel[] = [];
    for (const channel of channels) {
      if (channel.name.toLowerCase().includes(needle)) { found.push(channel); if (found.length >= SEARCH_LIMIT) break; }
    }
    return found;
  }, [needle, channels]);
  const playlistName = (channel: IptvChannel) => playlists.find((playlist) => playlist.id === channelPlaylistId(channel.id))?.name;
  const groupNames = settings.iptvGroupNames;
  const channelGroup = (channel: IptvChannel) => groupNames?.[playlistGroupKey(channelPlaylistId(channel.id), channel.group)] || channel.group;
  const remove = (ref: string) => updateSettings({ favoriteChannelIds: favorites.filter((id) => id !== ref) });
  const toggle = (channel: IptvChannel) => updateSettings({ favoriteChannelIds: toggleFavoriteChannel(favorites, channel.id, identity) });

  return (
    <div className="st-layout">
      <div className="st-layout-main">
        <p className="st-hint">{translateUi("Favourite channels appear in this order in the Favourites category and on Home.")} <ScopeBadge scope="all" /></p>
        <div className="st-subhead"><h4>{translateUi("Favourite channels")} · {favorites.length}</h4></div>
        <SortableList
          items={entries}
          label={translateUi("Favourite channels")}
          getKey={(entry) => entry.ref}
          getLabel={(entry) => entry.channel?.name ?? entry.ref}
          onMove={(from, to) => updateSettings({ favoriteChannelIds: moveIndex(favorites, from, to) })}
          rowHeight={ROW_HEIGHT}
          viewportHeight={420}
          emptyText={translateUi("No favourite channels yet. Search below to add some.")}
          renderMain={(entry) => entry.channel
            ? <ChannelLabel channel={entry.channel} sub={[channelGroup(entry.channel), playlistName(entry.channel)].filter(Boolean).join(" · ")} />
            : <><span className="st-sort-title"><span className="st-sort-name">{entry.ref}</span></span><span className="st-sort-sub">{translateUi("Not in the playlists loaded here")}</span></>}
          renderActions={(entry) => (
            <button type="button" className="st-icon-btn is-danger" aria-label={`${translateUi("Remove from favourites")}: ${entry.channel?.name ?? entry.ref}`} title={translateUi("Remove from favourites")}
              onClick={() => remove(entry.ref)}><X size={17} /></button>
          )}
        />
        <div className="st-picker">
          <div className="st-toolbar">
            <Search size={16} aria-hidden="true" />
            <input className="st-input" type="search" value={query} onChange={(event) => setQuery(event.target.value)}
              placeholder={translateUi("Find a channel to add")} aria-label={translateUi("Find a channel to add")} />
          </div>
          {needle && (
            <ul className="st-picker-results" aria-label={translateUi("Search results")}>
              {results.length === 0 && <li className="st-sort-empty">{translateUi("No channels match “{value0}”.", { value0: deferredQuery.trim() })}</li>}
              {results.map((channel) => {
                const on = favoriteIds.has(channel.id);
                return (
                  <li key={channel.id}>
                    <ChannelLabel channel={channel} sub={[channelGroup(channel), playlistName(channel)].filter(Boolean).join(" · ")} />
                    <button type="button" className={`st-btn${on ? "" : " is-primary"}`} aria-pressed={on} onClick={() => toggle(channel)}>
                      <Star size={16} />{(on ? translateUi("Remove") : translateUi("Add"))}
                    </button>
                  </li>
                );
              })}
            </ul>
          )}
        </div>
        <div className="st-subhead"><h4>{translateUi("Favourite categories")} · {favoriteGroups.length}</h4></div>
        <SortableList
          items={favoriteGroups}
          label={translateUi("Favourite categories")}
          getKey={(entry) => entry}
          getLabel={(entry) => groupDisplayName(groupNames, entry)}
          onMove={(from, to) => updateSettings({ favoriteGroupIds: moveIndex(favoriteGroups, from, to) })}
          edgeButtons={favoriteGroups.length > 3}
          emptyText={translateUi("Star a category in Categories to add it here.")}
          renderMain={(entry) => <span className="st-sort-title"><span className="st-sort-name">{groupDisplayName(groupNames, entry)}</span></span>}
          renderActions={(entry) => (
            <button type="button" className="st-icon-btn is-danger" aria-label={`${translateUi("Remove from favourites")}: ${groupDisplayName(groupNames, entry)}`} title={translateUi("Remove from favourites")}
              onClick={() => updateSettings({ favoriteGroupIds: favoriteGroups.filter((item) => item !== entry) })}><X size={17} /></button>
          )}
        />
      </div>
      <Preview title={translateUi("Favourites")}>
        {entries.slice(0, 14).map((entry, index) => <li key={entry.ref}><small>{index + 1}</small><span>{entry.channel?.name ?? entry.ref}</span></li>)}
        {entries.length > 14 && <li className="st-preview-more">{translateUi("+ {value0} more", { value0: entries.length - 14 })}</li>}
      </Preview>
    </div>
  );
}

function Preview({ title, note, children }: { title: string; note?: string; children: ReactNode }) {
  const translateUi = useTranslation();
  return (
    <aside className="st-preview" aria-label={translateUi("Preview")}>
      <h4>{translateUi("Preview")} · {title}</h4>
      {note && <p>{note}</p>}
      <ol>{children}</ol>
    </aside>
  );
}
