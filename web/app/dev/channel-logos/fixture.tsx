"use client";
import { useState } from "react";
import { ChannelLogo } from "@/components/livetv/ChannelLogo";
import type { IptvChannel } from "@/lib/types";

const channels: IptvChannel[] = [
  { id: "provider", name: "Provider image", logo: "https://logos.example.test/provider.png" },
  { id: "broken", name: "Broken provider", logo: "https://logos.example.test/broken.png", tvgId: "Known.us" },
  { id: "missing", name: "NL | ESPN FHD" },
  { id: "ambiguous", name: "ESPN" },
  { id: "unknown", name: "Unknown channel" },
  { id: "exhausted", name: "All images fail", tvgId: "Failed.us" },
].map(channel => ({ ...channel, group: "Test", streamUrl: "https://example.invalid/not-played" }));

export function ChannelLogoFixture() {
  const [generation, setGeneration] = useState(0);
  return <main style={{ padding: 24, background: "#101214", minHeight: "100dvh" }}>
    <button onClick={() => setGeneration(value => value + 1)}>Remount</button>
    <div key={generation} style={{ display: "grid", gridTemplateColumns: "repeat(auto-fit, minmax(140px, 1fr))", gap: 16 }}>
      {channels.map(channel => <section key={channel.id} data-testid={channel.id}>
        <div style={{ width: 140, height: 78 }}><ChannelLogo channel={channel} /></div><p>{channel.name}</p>
      </section>)}
    </div>
  </main>;
}
