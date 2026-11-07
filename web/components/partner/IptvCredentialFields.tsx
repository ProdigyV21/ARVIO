"use client";
import { useState } from "react";
import type { PartnerIptvOptions } from "@/lib/partnerManagement";

export function IptvCredentialFields({ options }: { options?: PartnerIptvOptions }) {
  const [mode, setMode] = useState(options?.mode === "m3u" ? "m3u" : "xtream");
  return <>
    {(!options || options.mode === "auto") && <label>Connection format<select aria-label="Connection format" value={mode} onChange={event => setMode(event.target.value)}><option value="xtream">Xtream login</option><option value="m3u">M3U playlist</option></select></label>}
    {mode === "m3u" ? <label>Playlist URL<input name="playlistUrl" type="url" required autoComplete="off" /></label> : <>
      <label>Username<input name="username" required autoComplete="off" /></label>
      <label>Password<input name="password" type="password" required autoComplete="off" /></label>
    </>}
    {options?.epg !== false && <label>EPG URL (optional)<input name="epgUrl" type="url" autoComplete="off" /></label>}
  </>;
}
