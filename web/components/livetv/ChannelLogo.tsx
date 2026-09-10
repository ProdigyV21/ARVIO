"use client";

import { useEffect, useRef, useState } from "react";
import { Tv } from "lucide-react";
import { channelLogoCandidates, channelLogoFailed, failChannelLogo, providerLogoUrl } from "@/lib/channelLogos";
import type { IptvChannel } from "@/lib/types";

export function ChannelLogo({ channel, size = 24 }: { channel: IptvChannel; size?: number }) {
  return <Logo key={`${channel.id}:${channel.logo}:${channel.tvgId}:${channel.name}`} channel={channel} size={size} />;
}

function Logo({ channel, size }: { channel: IptvChannel; size: number }) {
  const provider = providerLogoUrl(channel.logo);
  const [failed, setFailed] = useState<string[]>([]);
  const [alternatives, setAlternatives] = useState<string[]>([]);
  const [loaded, setLoaded] = useState<string>();
  const image = useRef<HTMLImageElement>(null);
  useEffect(() => {
    let active = true;
    void channelLogoCandidates(channel.tvgId, channel.name).then(urls => { if (active) setAlternatives(urls); });
    return () => { active = false; };
  }, [channel.tvgId, channel.name]);
  // Provider-generated text placeholders return HTTP 200 too. Prefer an exact
  // directory identity; ambiguous names still use the provider's own artwork.
  const url = [...alternatives, provider].find((candidate): candidate is string => Boolean(candidate && !failed.includes(candidate) && !channelLogoFailed(candidate)));
  useEffect(() => {
    // Cached images can finish before React hydrates the server-rendered element.
    if (!url || !image.current?.complete) return;
    if (image.current.naturalWidth > 0) setLoaded(url);
    else { failChannelLogo(url); setFailed(old => old.includes(url) ? old : [...old, url]); }
  }, [url]);
  return <span className="channel-logo" style={{ display: "grid", gridTemplate: "minmax(0, 1fr) / minmax(0, 1fr)", placeItems: "center", width: "100%", height: "100%", minWidth: 0, minHeight: 0, overflow: "hidden" }}>
    {(!url || loaded !== url) && <Tv size={size} style={{ gridArea: "1 / 1" }} />}
    {url && <img ref={image} key={url} src={url} alt="" loading="lazy" decoding="async"
      style={{ gridArea: "1 / 1", width: "100%", height: "100%", minWidth: 0, minHeight: 0, maxHeight: "100%", objectFit: "contain", opacity: loaded === url ? 1 : 0 }}
      onLoad={() => setLoaded(url)} onError={() => { failChannelLogo(url); setFailed(old => [...old, url]); }} />}
  </span>;
}
