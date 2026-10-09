"use client";

import { useEffect, useState } from "react";
import { Tv } from "lucide-react";
import { useTranslation } from "@/lib/i18n";
import { useApp } from "@/lib/store";
import type { IptvChannel } from "@/lib/types";
import { RailScroller } from "./RailScroller";

// Guide requests are per channel; the first screenful is what the row can show.
const GUIDE_CHANNELS = 16;

/** Home row of the profile's favorite IPTV channels (Android "Favorite TV"). */
export function FavoriteTvRail({ title }: { title?: string }) {
  const { favoriteTvChannels, iptvSnapshot, loadIptvGuide, playChannel } = useApp();
  const label = title || "Favorite TV";
  // The guide only applies to a loaded playlist; the saved copy shows channels alone.
  const playlistLoaded = Boolean((iptvSnapshot.allChannels ?? iptvSnapshot.channels).length);
  useEffect(() => {
    if (playlistLoaded && favoriteTvChannels.length) void loadIptvGuide(favoriteTvChannels.slice(0, GUIDE_CHANNELS));
  }, [playlistLoaded, favoriteTvChannels, loadIptvGuide]);
  if (!favoriteTvChannels.length) return null;
  return (
    <section className="rail favorite-tv-rail">
      <div className="rail-head">
        <h3>{label}</h3>
      </div>
      <RailScroller className="rail-strip" ariaLabel={label}>
        {favoriteTvChannels.map((channel) => (
          <FavoriteTvCard key={channel.id} channel={channel} now={iptvSnapshot.nowNext[channel.id]?.now} onPlay={playChannel} />
        ))}
      </RailScroller>
    </section>
  );
}

function FavoriteTvCard({ channel, now, onPlay }: {
  channel: IptvChannel;
  now?: { title: string; startUtcMillis: number; endUtcMillis: number };
  onPlay: (channel: IptvChannel) => void;
}) {
  const translateUi = useTranslation();
  const [logoFailed, setLogoFailed] = useState(false);
  const onAir = now && now.endUtcMillis > Date.now() ? now : undefined;
  const progress = onAir ? Math.min(100, Math.max(0, ((Date.now() - onAir.startUtcMillis) / (onAir.endUtcMillis - onAir.startUtcMillis)) * 100)) : 0;
  return (
    <button type="button" className="media-card favorite-tv-card" title={channel.name} onClick={() => onPlay(channel)}>
      <div className="poster favorite-tv-poster">
        {channel.logo && !logoFailed
          ? <img src={channel.logo} alt="" loading="lazy" decoding="async" onError={() => setLogoFailed(true)} />
          : <Tv size={42} />}
        <span className="cw-badge top-right favorite-tv-live">{translateUi("LIVE")}</span>
        {onAir && <span className="cw-progress"><span style={{ width: `${progress}%` }} /></span>}
      </div>
      <strong>{channel.name}</strong>
      <div className="card-meta-row">
        <span className="card-date">{onAir?.title || channel.group}</span>
      </div>
    </button>
  );
}
