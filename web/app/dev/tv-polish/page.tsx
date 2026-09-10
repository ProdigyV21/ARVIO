import { notFound } from "next/navigation";
import { headers } from "next/headers";
import { ChannelLogo } from "@/components/livetv/ChannelLogo";

export const dynamic = "force-dynamic";
export default async function Page() {
  if (process.env.NODE_ENV !== "development" || process.env.ARVIO_UI_FIXTURES !== "true") notFound();
  const origin = `http://${(await headers()).get("host")}`;
  const rows = [
    { name: "Square provider image", tvgId: "fixture-square", logo: `${origin}/arvio-icon-512.png` },
    { name: "NL| NPO 1 FHD", tvgId: "NPO1.nl", logo: `${origin}/arvio-icon-512.png` },
    { name: "UK-NOWTV| TNT Sports 2 FHD", tvgId: "TNTSports2.uk", logo: "" },
    { name: "Unmatched provider image", tvgId: "fixture-unmatched", logo: "" }
  ];
  return <main style={{ padding: 20, maxWidth: 1000, margin: "auto" }}><h1>Channel artwork regression</h1>
    {rows.map((row, i) => <button className="tv-event-source" key={row.name}>
      <span className="tv-source-logo-fallback"><ChannelLogo channel={{ ...row, id: `fixture:${i}`, group: "Test", streamUrl: "" }} /></span>
      <span><strong>{row.name}</strong><small>Test playlist</small></span>
    </button>)}
  </main>;
}
