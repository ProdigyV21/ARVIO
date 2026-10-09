"use client";

import { useTranslation } from "@/lib/i18n";
import { ChannelLogo } from "@/components/livetv/ChannelLogo";
import type { IptvChannel, IptvNowNext } from "@/lib/types";

export function ChannelProgramInfo({ channel, guide, formatTime }: {
  channel: IptvChannel;
  guide?: IptvNowNext;
  formatTime: (time: number) => string;
}) {
  const translateUi = useTranslation();
  const programTitle = guide?.now?.title?.trim();
  const now = guide?.now;
  // A fallback heading already identifies the channel. Keep quality and
  // artwork here without repeating its long event name in a truncated label.
  const identity = programTitle
    ? `${channel.name}${channel.qualityLabel ? ` · ${channel.qualityLabel}` : ""}`
    : channel.qualityLabel;

  return <>
    <div className="livetv-channel-identity">
      <div className="tv-identity-logo"><ChannelLogo channel={channel} size={28} /></div>
      {identity && <span>{identity}</span>}
    </div>
    <h2 className="livetv-program-title">{programTitle || channel.name}</h2>
    {now ? <div className="livetv-program">
      <div className="livetv-program-head">
        <em>{formatTime(now.startUtcMillis)} – {formatTime(now.endUtcMillis)}</em>
      </div>
      {now.description && <p>{now.description}</p>}
    </div> : <p className="livetv-detail-empty">{translateUi("No guide data for this channel.")}</p>}
    {guide?.next?.title && <div className="livetv-program is-next">
      <div className="livetv-program-head">
        <span>{translateUi("NEXT")}</span>
        <em>{formatTime(guide.next.startUtcMillis)}</em>
      </div>
      <strong>{guide.next.title}</strong>
    </div>}
  </>;
}
