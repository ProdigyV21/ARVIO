#!/usr/bin/env python3
"""Synthetic Live TV fixture for load/zap/scroll measurements.

Writes playlist.m3u and guide.xml(.gz) with N channels spread over groups. Every
channel points at a public test HLS stream (or your own --stream URLs), made unique
with a query parameter so the app treats them as separate channels. The guide has
30-minute programmes from 6 hours ago to 18 hours ahead.

    python3 make_fixture.py --out fixture --channels 2000 --base http://10.0.2.2:8765
    python3 -m http.server 8765 --directory fixture

Then add http://10.0.2.2:8765/playlist.m3u (emulator view of the host) as an M3U
playlist with EPG http://10.0.2.2:8765/guide.xml.gz.
"""
import argparse
import gzip
import os
from datetime import datetime, timedelta, timezone
from xml.sax.saxutils import escape

# Public test streams published for player testing.
DEFAULT_STREAMS = [
    "https://cph-p2p-msl.akamaized.net/hls/live/2000341/test/master.m3u8",
    "https://moctobpltc-i.akamaihd.net/hls/live/571329/eight/playlist.m3u8",
    "https://demo.unified-streaming.com/k8s/live/stable/scte35.isml/.m3u8",
    "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8",
    "https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_16x9/bipbop_16x9_variant.m3u8",
]
GROUPS = ["News", "Sports", "Movies", "Kids", "Music", "Documentary", "Entertainment",
          "Regional", "Lifestyle", "International"]
TITLES = ["Morning Update", "World Report", "Match Day Live", "Classic Cinema", "Cartoon Hour",
          "Top Hits", "Nature Files", "Late Show", "Cooking Today", "Travel Diaries",
          "Science Now", "Evening News", "Quiz Night", "Drama Premiere", "Weekend Sports"]


def xmltv_time(t):
    return t.strftime("%Y%m%d%H%M%S +0000")


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--out", default="fixture")
    ap.add_argument("--channels", type=int, default=2000)
    ap.add_argument("--base", default="http://10.0.2.2:8765")
    ap.add_argument("--stream", action="append", help="stream URL (repeatable); defaults to public test streams")
    args = ap.parse_args()
    streams = args.stream or DEFAULT_STREAMS
    os.makedirs(args.out, exist_ok=True)

    with open(os.path.join(args.out, "playlist.m3u"), "w", encoding="utf-8") as m3u:
        m3u.write(f'#EXTM3U url-tvg="{args.base}/guide.xml.gz"\n')
        for i in range(1, args.channels + 1):
            group = GROUPS[(i - 1) % len(GROUPS)]
            cid = f"bench{i}.test"
            url = streams[(i - 1) % len(streams)]
            sep = "&" if "?" in url else "?"
            m3u.write(f'#EXTINF:-1 tvg-id="{cid}" tvg-chno="{i}" group-title="{group}",Bench {group} {i}\n')
            m3u.write(f"{url}{sep}bench={i}\n")

    start = datetime.now(timezone.utc).replace(minute=0, second=0, microsecond=0) - timedelta(hours=6)
    slots = 48
    path = os.path.join(args.out, "guide.xml.gz")
    with gzip.open(path, "wt", encoding="utf-8") as xml:
        xml.write('<?xml version="1.0" encoding="UTF-8"?>\n<tv generator-info-name="arvio-bench">\n')
        for i in range(1, args.channels + 1):
            xml.write(f'<channel id="bench{i}.test"><display-name>Bench {i}</display-name></channel>\n')
        for i in range(1, args.channels + 1):
            for s in range(slots):
                a = start + timedelta(minutes=30 * s)
                b = a + timedelta(minutes=30)
                title = TITLES[(i + s) % len(TITLES)]
                xml.write(
                    f'<programme start="{xmltv_time(a)}" stop="{xmltv_time(b)}" channel="bench{i}.test">'
                    f"<title>{escape(title)}</title><desc>{escape(title)} on channel {i}, slot {s}.</desc>"
                    "</programme>\n"
                )
        xml.write("</tv>\n")
    with gzip.open(path, "rb") as src, open(os.path.join(args.out, "guide.xml"), "wb") as dst:
        dst.write(src.read())
    print(f"wrote {args.channels} channels, {args.channels * slots} programmes to {args.out}/")


if __name__ == "__main__":
    main()
