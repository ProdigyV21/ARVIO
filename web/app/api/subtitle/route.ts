import { NextRequest, NextResponse } from "next/server";

export async function GET(request: NextRequest) {
  const raw = new URL(request.url).searchParams.get("url");
  if (!raw) return NextResponse.json({ error: "Missing url" }, { status: 400 });

  const response = await fetch(raw, { cache: "no-store" });
  const text = await response.text();
  const webvtt = text.trimStart().startsWith("WEBVTT") ? text : srtToVtt(text);

  return new NextResponse(webvtt, {
    status: response.ok ? 200 : response.status,
    headers: {
      "content-type": "text/vtt; charset=utf-8",
      "cache-control": "no-store",
      "access-control-allow-origin": "*"
    }
  });
}

function srtToVtt(input: string) {
  return `WEBVTT\n\n${input.replace(/\r/g, "").replace(/(\d\d:\d\d:\d\d),(\d\d\d)/g, "$1.$2")}`;
}
