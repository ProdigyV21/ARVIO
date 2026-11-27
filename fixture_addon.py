"""A tiny local music addon for testing ARVIO's Music tab on an emulator.

Run:  python fixture_addon.py   (serves on port 8766; the emulator reaches it as http://10.0.2.2:8766)
Stdlib only. Songs are well-known titles so LRCLIB finds lyrics; the audio is a generated tone.
"""
import http.server, json, math, os, struct, sys, tempfile, wave, zlib, urllib.parse

PORT = int(sys.argv[1]) if len(sys.argv) > 1 else 8766
HOST = os.environ.get("ADDON_HOST", "10.0.2.2")
BASE = f"http://{HOST}:{PORT}"
TMP = tempfile.mkdtemp(prefix="arvio-addon-")

SONGS = [
    ("t1", "Blinding Lights", "The Weeknd", "After Hours", 200, (200, 40, 60)),
    ("t2", "Levitating", "Dua Lipa", "Future Nostalgia", 203, (40, 90, 200)),
    ("t3", "Shape of You", "Ed Sheeran", "Divide", 233, (30, 160, 120)),
    ("t4", "Bohemian Rhapsody", "Queen", "A Night at the Opera", 354, (210, 170, 40)),
]
ALBUMS = {"fixture-hits": ("Fixture Hits", "Various Artists", ["t1", "t2", "t3", "t4"])}


def png(path, rgb):
    w = h = 300
    raw = bytearray()
    for y in range(h):
        raw.append(0)
        for x in range(w):
            f = 0.45 + 0.55 * (x + y) / (w + h)
            raw += bytes([int(rgb[0] * f), int(rgb[1] * f), int(rgb[2] * f)])
    def chunk(t, d):
        return struct.pack(">I", len(d)) + t + d + struct.pack(">I", zlib.crc32(t + d) & 0xFFFFFFFF)
    data = b"\x89PNG\r\n\x1a\n" + chunk(b"IHDR", struct.pack(">IIBBBBB", w, h, 8, 2, 0, 0, 0)) + chunk(b"IDAT", zlib.compress(bytes(raw))) + chunk(b"IEND", b"")
    open(path, "wb").write(data)


def tone(path, seconds, freq):
    rate = 8000
    with wave.open(path, "wb") as w:
        w.setnchannels(1); w.setsampwidth(2); w.setframerate(rate)
        frames = bytearray()
        for i in range(seconds * rate):
            frames += struct.pack("<h", int(1500 * math.sin(2 * math.pi * freq * i / rate)))
        w.writeframes(bytes(frames))


for n, (tid, title, artist, album, dur, rgb) in enumerate(SONGS):
    png(os.path.join(TMP, f"{tid}.png"), rgb)
    tone(os.path.join(TMP, f"{tid}.wav"), dur, 220 + 110 * n)
png(os.path.join(TMP, "fixture-hits.png"), (120, 60, 180))


def track_meta(tid):
    t = next(s for s in SONGS if s[0] == tid)
    return {"id": t[0], "type": "track", "title": t[1], "name": t[1], "artist": t[2], "album": t[3],
            "albumId": "fixture-hits", "duration": t[4], "poster": f"{BASE}/art/{t[0]}.png"}


def album_meta(aid, with_tracks=False):
    name, artist, ids = ALBUMS[aid]
    meta = {"id": aid, "type": "album", "name": name, "artist": artist, "poster": f"{BASE}/art/{aid}.png", "trackCount": len(ids)}
    if with_tracks:
        meta["videos"] = [track_meta(t) for t in ids]
    return meta


class Handler(http.server.BaseHTTPRequestHandler):
    def send_json(self, obj):
        body = json.dumps(obj).encode()
        self.send_response(200); self.send_header("Content-Type", "application/json")
        self.send_header("Content-Length", str(len(body))); self.end_headers(); self.wfile.write(body)

    def send_file(self, path, ctype):
        if not os.path.exists(path):
            self.send_error(404); return
        data = open(path, "rb").read()
        self.send_response(200); self.send_header("Content-Type", ctype)
        self.send_header("Content-Length", str(len(data))); self.end_headers(); self.wfile.write(data)

    def do_GET(self):
        p = urllib.parse.unquote(self.path.split("?")[0])
        if p == "/manifest.json":
            return self.send_json({"id": "test.fixture.music", "name": "Fixture Music", "version": "1.0.0", "types": ["music"],
                                   "resources": ["catalog", "meta", "stream"],
                                   "catalogs": [{"type": "music", "id": "hits", "name": "Fixture Hits", "extra": [{"name": "search"}]}]})
        if p == "/catalog/music/hits.json":
            return self.send_json({"metas": [album_meta(a) for a in ALBUMS]})
        if p.startswith("/catalog/music/hits/search="):
            q = p[len("/catalog/music/hits/search="):-len(".json")].lower()
            hits = [track_meta(s[0]) for s in SONGS if any(w in (s[1] + " " + s[2]).lower() for w in q.split())]
            return self.send_json({"metas": hits})
        if p.startswith("/meta/album/"):
            aid = p[len("/meta/album/"):-len(".json")]
            return self.send_json({"meta": album_meta(aid, True)}) if aid in ALBUMS else self.send_error(404)
        if p.startswith("/stream/track/"):
            tid = p[len("/stream/track/"):-len(".json")]
            return self.send_json({"streams": [{"url": f"{BASE}/audio/{tid}.wav"}]})
        if p.startswith("/audio/"):
            return self.send_file(os.path.join(TMP, os.path.basename(p)), "audio/wav")
        if p.startswith("/art/"):
            return self.send_file(os.path.join(TMP, os.path.basename(p)), "image/png")
        self.send_error(404)

    def log_message(self, fmt, *args):
        sys.stderr.write("addon: " + fmt % args + "\n")


print(f"Fixture music addon on port {PORT}; add {BASE}/manifest.json in ARVIO", flush=True)
http.server.ThreadingHTTPServer(("0.0.0.0", PORT), Handler).serve_forever()
