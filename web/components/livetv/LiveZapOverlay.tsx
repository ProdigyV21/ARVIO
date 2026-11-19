"use client";
import { useTranslation } from "@/lib/i18n";
import { createPortal } from "react-dom";
import { ChannelLogo } from "@/components/livetv/ChannelLogo";
import { formatGuideTime, type ClockFormat } from "@/lib/iptvGuide";
import type { IptvChannel, IptvNowNext } from "@/lib/types";

/** Fullscreen live player overlays: the typed channel number and the now/next card. */
export function LiveZapOverlay({ digits, digitMatch, info, guide, clockFormat }: {
  digits: string;
  digitMatch: IptvChannel | null;
  info: IptvChannel | null;
  guide?: IptvNowNext;
  clockFormat?: ClockFormat;
}) {
  const translateUi = useTranslation();
  if (typeof document === "undefined" || (!digits && !info)) return null;
  const now = guide?.now;
  const next = guide?.next ?? guide?.upcoming?.[0];
  const progress = now ? Math.min(100, Math.max(0, ((Date.now() - now.startUtcMillis) / Math.max(1, now.endUtcMillis - now.startUtcMillis)) * 100)) : 0;
  const time = (ms: number) => formatGuideTime(ms, clockFormat);
  return createPortal(<>
    {digits && <div className="livetv-zap-number" role="status" aria-live="assertive">
      <strong>{digits}</strong>
      <span>{digitMatch ? digitMatch.name : translateUi("Channel number")}</span>
    </div>}
    {info && <div className="livetv-zap-info" role="status" aria-live="polite">
      <span className="livetv-zap-logo"><ChannelLogo channel={info} size={28} /></span>
      <div>
        <p className="livetv-zap-channel">{info.number && <b>{info.number}</b>}{info.name}</p>
        {now ? <>
          <p className="livetv-zap-now"><strong>{now.title}</strong><em>{time(now.startUtcMillis)}–{time(now.endUtcMillis)}</em></p>
          <span className="livetv-progress"><span style={{ width: `${progress}%` }} /></span>
        </> : <p className="livetv-zap-now"><em>{translateUi("No guide data for this channel.")}</em></p>}
        {next?.title && <p className="livetv-zap-next"><span>{translateUi("NEXT")}</span>{time(next.startUtcMillis)} · {next.title}</p>}
        <p className="livetv-zap-hint">{translateUi("Up/Down change channel · Back returns to the guide")}</p>
      </div>
    </div>}
  </>, document.body);
}
