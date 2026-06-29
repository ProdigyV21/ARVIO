"use client";

import { BadgeCheck, Eye, EyeOff, Plus, Star, Tv } from "lucide-react";
import { useState } from "react";
import { useApp } from "@/lib/store";
import type { IptvChannel, IptvSnapshot } from "@/lib/types";

function fmtTime(ms: number): string {
  try {
    return new Intl.DateTimeFormat([], { hour: "2-digit", minute: "2-digit" }).format(new Date(ms));
  } catch {
    return "";
  }
}

export function LiveTvScreen() {
  const { iptvSnapshot, settings, setSettings, playChannel } = useApp();
  const playlists = settings.iptvPlaylists;
  const favorites = settings.favoriteChannelIds;
  const favoriteGroups = settings.favoriteGroupIds;
  const hiddenGroups = settings.hiddenGroupIds;

  const [name, setName] = useState("");
  const [url, setUrl] = useState("");
  const [epgUrl, setEpgUrl] = useState("");

  const channels = iptvSnapshot.channels;
  const groups = iptvSnapshot.grouped;
  const favoriteChannels = channels.filter((channel) => favorites.includes(channel.id));

  const toggleFavorite = (channelId: string) =>
    setSettings({
      ...settings,
      favoriteChannelIds: favorites.includes(channelId)
        ? favorites.filter((id) => id !== channelId)
        : [channelId, ...favorites]
    });

  const toggleGroupFavorite = (group: string) =>
    setSettings({
      ...settings,
      favoriteGroupIds: favoriteGroups.includes(group) ? favoriteGroups.filter((id) => id !== group) : [group, ...favoriteGroups]
    });

  const toggleHiddenGroup = (group: string) =>
    setSettings({
      ...settings,
      hiddenGroupIds: hiddenGroups.includes(group) ? hiddenGroups.filter((id) => id !== group) : [group, ...hiddenGroups]
    });

  return (
    <div className="screen live-layout">
      <section className="live-panel">
        <p className="eyebrow">Live TV</p>
        <h2>{channels.length ? `${channels.length} Channels` : "Add an M3U playlist"}</h2>
        <div className="inline-form">
          <input value={name} onChange={(event) => setName(event.target.value)} placeholder="Playlist name" />
          <input value={url} onChange={(event) => setUrl(event.target.value)} placeholder="M3U URL" />
          <input value={epgUrl} onChange={(event) => setEpgUrl(event.target.value)} placeholder="EPG XMLTV URL" />
          <button className="primary" onClick={() => {
            if (!url.trim()) return;
            setSettings({
              ...settings,
              iptvPlaylists: [{ id: crypto.randomUUID(), name: name || "Playlist", m3uUrl: url, epgUrl, enabled: true }, ...playlists]
            });
            setName("");
            setUrl("");
            setEpgUrl("");
          }}><Plus size={18} /> Add</button>
        </div>
        <div className="playlist-list">
          {playlists.map((playlist) => (
            <button
              key={playlist.id}
              onClick={() => setSettings({
                ...settings,
                iptvPlaylists: playlists.map((p) => p.id === playlist.id ? { ...p, enabled: !p.enabled } : p)
              })}
            >
              <BadgeCheck size={18} />
              <span>{playlist.name}</span>
              <em>{playlist.enabled ? (playlist.epgUrl ? "M3U + EPG" : "Enabled") : "Paused"}</em>
            </button>
          ))}
        </div>
      </section>
      <section className="channel-browser">
        {favoriteChannels.length > 0 && (
          <ChannelGroup
            title="Favorites"
            channels={favoriteChannels}
            nowNext={iptvSnapshot.nowNext}
            favorites={favorites}
            onPlay={playChannel}
            onToggleFavorite={toggleFavorite}
          />
        )}
        {Object.entries(groups).map(([group, items]) => (
          <ChannelGroup
            key={group}
            title={group}
            channels={items}
            nowNext={iptvSnapshot.nowNext}
            favorites={favorites}
            isFavoriteGroup={favoriteGroups.includes(group)}
            isHiddenGroup={hiddenGroups.includes(group)}
            onPlay={playChannel}
            onToggleFavorite={toggleFavorite}
            onToggleGroupFavorite={() => toggleGroupFavorite(group)}
            onToggleHiddenGroup={() => toggleHiddenGroup(group)}
          />
        ))}
      </section>
    </div>
  );
}

function ChannelGroup({ title, channels, nowNext, favorites, isFavoriteGroup, isHiddenGroup, onPlay, onToggleFavorite, onToggleGroupFavorite, onToggleHiddenGroup }: {
  title: string;
  channels: IptvChannel[];
  nowNext: Record<string, IptvSnapshot["nowNext"][string]>;
  favorites: string[];
  isFavoriteGroup?: boolean;
  isHiddenGroup?: boolean;
  onPlay: (channel: IptvChannel) => void;
  onToggleFavorite: (channelId: string) => void;
  onToggleGroupFavorite?: () => void;
  onToggleHiddenGroup?: () => void;
}) {
  return (
    <section className="channel-group">
      <div className="channel-group-head">
        <h3>{title}</h3>
        {onToggleGroupFavorite && (
          <button className={isFavoriteGroup ? "pill active" : "pill"} onClick={onToggleGroupFavorite}>
            <Star size={15} fill="currentColor" /> Group
          </button>
        )}
        {onToggleHiddenGroup && (
          <button className={isHiddenGroup ? "pill active danger" : "pill"} onClick={onToggleHiddenGroup}>
            {isHiddenGroup ? <Eye size={15} /> : <EyeOff size={15} />} Hide
          </button>
        )}
      </div>
      <div className="channel-list">
        {channels.slice(0, 120).map((channel) => {
          const epg = nowNext[channel.id];
          const now = epg?.now;
          const next = epg?.next ?? epg?.later ?? epg?.upcoming?.[0];
          const progress = now ? Math.min(100, Math.max(0, ((Date.now() - now.startUtcMillis) / (now.endUtcMillis - now.startUtcMillis)) * 100)) : 0;
          return (
            <div className="channel-row" key={channel.id}>
              <button onClick={() => onPlay(channel)}>
                {channel.logo ? <img src={channel.logo} alt="" /> : <Tv size={22} />}
                <span className="channel-meta">
                  <span className="channel-name">{channel.name}</span>
                  {now?.title && <em className="epg-now">{fmtTime(now.startUtcMillis)} {now.title}</em>}
                  {now && <span className="epg-progress"><span style={{ width: `${progress}%` }} /></span>}
                  {next?.title && <em className="epg-next">Next · {fmtTime(next.startUtcMillis)} {next.title}</em>}
                </span>
              </button>
              <button className={favorites.includes(channel.id) ? "star active" : "star"} onClick={() => onToggleFavorite(channel.id)}>
                <Star size={18} fill="currentColor" />
              </button>
            </div>
          );
        })}
      </div>
    </section>
  );
}
