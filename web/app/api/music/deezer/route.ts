import { NextRequest, NextResponse } from "next/server";
import { artistMixes, homeShelves, mixTracks, playlistTracks, searchPlaylists } from "@/lib/music/deezer";

// Music home charts, playlist search, playlist tracks and artist mixes (metadata only; songs play
// from the user's own sources). Answers are the same for everyone who asks the same thing, so the
// CDN keeps them: one build per language and country per day for the home, a day per artist, a
// few hours for playlists and mixes.

function cached(body: unknown, seconds: number) {
  const headers = new Headers({ "content-type": "application/json" });
  headers.set("cache-control", `public, max-age=${Math.min(seconds, 3600)}, s-maxage=${seconds}, stale-while-revalidate=${seconds}`);
  headers.set("netlify-cdn-cache-control", `public, durable, max-age=${seconds}, stale-while-revalidate=${seconds}`);
  headers.set("cdn-cache-control", `public, max-age=${seconds}`);
  headers.set("netlify-vary", "query");
  return new NextResponse(JSON.stringify(body), { status: 200, headers });
}

function failed(error: unknown) {
  return NextResponse.json({ error: error instanceof Error ? error.message : "Charts unavailable" }, { status: 502, headers: { "cache-control": "no-store" } });
}

export async function GET(request: NextRequest) {
  const params = new URL(request.url).searchParams;
  try {
    const home = params.get("home");
    if (home !== null) {
      const language = /^[a-zA-Z]{2,3}([-_][a-zA-Z0-9]{2,4})?$/.test(home) ? home : "en-US";
      const date = /^\d{4}-\d{2}-\d{2}$/.test(params.get("d") ?? "") ? params.get("d")! : new Date().toISOString().slice(0, 10);
      const country = /^[A-Za-z]{2}$/.test(params.get("c") ?? "") ? params.get("c")!.toUpperCase() : null;
      return cached({ shelves: await homeShelves(language, date, country) }, 86400);
    }
    const artist = (params.get("artist") ?? "").trim().slice(0, 120);
    if (artist) return cached(await artistMixes(artist) ?? { name: artist, mixes: [] }, 86400);
    const mix = params.get("mix");
    if (mix !== null) {
      if (!/^\d{1,20}$/.test(mix)) return NextResponse.json({ error: "Invalid mix" }, { status: 400 });
      return cached({ tracks: await mixTracks(mix) }, 21600);
    }
    const playlist = params.get("playlist");
    if (playlist !== null) {
      if (!/^\d{1,20}$/.test(playlist)) return NextResponse.json({ error: "Invalid playlist" }, { status: 400 });
      return cached({ tracks: await playlistTracks(playlist) }, 21600);
    }
    const query = (params.get("q") ?? "").trim().slice(0, 120);
    if (query) return cached({ playlists: await searchPlaylists(query) }, 3600);
    return NextResponse.json({ error: "Missing query" }, { status: 400 });
  } catch (error) {
    return failed(error);
  }
}
