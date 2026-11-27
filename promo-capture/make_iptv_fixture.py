"""Neutral promo Live TV fixture: playlist.m3u + guide.xml.gz + logos (via ffmpeg)."""
import gzip, os, random, subprocess, sys
from datetime import datetime, timedelta, timezone
from xml.sax.saxutils import escape

OUT = sys.argv[1]
BASE = sys.argv[2]  # e.g. http://10.0.2.2:8775
FFMPEG = sys.argv[3]
os.makedirs(os.path.join(OUT, "logos2"), exist_ok=True)
random.seed(7)

STREAMS = [
    "https://demo.unified-streaming.com/k8s/live/stable/scte35.isml/.m3u8",
    "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8",
]

CH = {
    "News": [("Arvio News", "#C62828"), ("World 24", "#1565C0"), ("Metro News", "#283593"), ("Business Today", "#00695C"), ("Weather Now", "#0277BD")],
    "Sports": [("City Sports 1", "#2E7D32"), ("City Sports 2", "#388E3C"), ("Goal TV", "#1B5E20"), ("Court Side", "#E65100"), ("Motor Max", "#B71C1C"), ("Tennis Live", "#9E9D24")],
    "Movies": [("Cinema One", "#4A148C"), ("Cinema Two", "#6A1B9A"), ("Classic Films", "#5D4037"), ("Action Max", "#BF360C"), ("Drama House", "#880E4F")],
    "Entertainment": [("Prime Comedy", "#F9A825"), ("Star Life", "#AD1457"), ("Show Time", "#6D4C41"), ("Talk Live", "#455A64"), ("Quiz Channel", "#7B1FA2")],
    "Documentary": [("Nature HD", "#33691E"), ("Planet Wild", "#558B2F"), ("History Plus", "#795548"), ("Science One", "#01579B"), ("Ocean World", "#006064")],
    "Kids": [("Kids Fun", "#FF6F00"), ("Toon Town", "#F57C00"), ("Junior Learn", "#43A047"), ("Teen Zone", "#8E24AA")],
    "Music": [("Hit Music", "#D81B60"), ("Rock Arena", "#424242"), ("Chill Beats", "#00897B"), ("Retro Hits", "#5E35B1")],
    "Lifestyle": [("Food & Home", "#EF6C00"), ("Travel Go", "#0097A7"), ("Fit Life", "#C0CA33"), ("Style TV", "#EC407A")],
}
TITLES = {
    "News": ["Morning Briefing", "World Report", "Midday Headlines", "Global Markets", "Evening News", "Late Edition", "The Interview", "Weather Outlook"],
    "Sports": ["Match Day Live", "Football Tonight", "Highlights Show", "Grand Prix Qualifying", "Tennis: Quarter Final", "Basketball Live", "Sports Desk", "Goal Rush"],
    "Movies": ["The Long Road", "Midnight Harbor", "Silver Horizon", "City of Echoes", "The Last Summer", "Northern Lights", "Paper Kingdom", "Iron Valley"],
    "Entertainment": ["Comedy Hour", "Late Night Live", "Family Quiz", "Talent Stage", "The Big Bake", "Celebrity Chat", "Sitcom Classics", "Weekend Special"],
    "Documentary": ["Wild Africa", "Deep Blue", "Ancient Empires", "Planet Earth Today", "Inside the Machine", "Mountain Life", "Rainforest Secrets", "Space Frontier"],
    "Kids": ["Cartoon Morning", "Adventure Pals", "Learn & Play", "Dino Friends", "Robot Academy", "Story Time", "Puzzle Quest", "Super Squad"],
    "Music": ["Top 40 Countdown", "Rock Classics", "Chill Mix", "80s Party", "Live Sessions", "New Releases", "Acoustic Hour", "Dance Floor"],
    "Lifestyle": ["Kitchen Stories", "Weekend Getaway", "Home Makeover", "Fit in 30", "Street Food Tour", "Garden Life", "Fashion Week", "City Guides"],
}
DESC = "An all-new episode with the latest stories, guests and highlights."

now = datetime.now(timezone.utc).replace(minute=0, second=0, microsecond=0)
start0 = now - timedelta(hours=6)
font = "C\\:/Windows/Fonts/arialbd.ttf"

m3u = [f'#EXTM3U url-tvg="{BASE}/guide.xml.gz"']
xml = ['<?xml version="1.0" encoding="UTF-8"?>', '<tv generator-info-name="promo-fixture">']
progs = []
n = 0
for group, chans in CH.items():
    for name, color in chans:
        n += 1
        cid = f"ch{n}.promo"
        logo = f"logos2/ch{n}.png"
        lp = os.path.join(OUT, logo)
        if not os.path.exists(lp):
            txt = "".join(w[0] for w in name.replace("&","").split() if w[0].isalpha()) + "".join(c for c in name if c.isdigit())
            subprocess.run([FFMPEG, "-loglevel", "error", "-y", "-f", "lavfi", "-i", f"color=c={color}:s=400x400",
                            "-vf", f"drawtext=fontfile='{font}':text='{txt}':fontcolor=white:fontsize=170:x=(w-text_w)/2:y=(h-text_h)/2",
                            "-frames:v", "1", lp], check=True)
        stream = STREAMS[n % len(STREAMS)] + f"?ch={n}"
        m3u.append(f'#EXTINF:-1 tvg-id="{cid}" tvg-chno="{n}" tvg-logo="{BASE}/{logo}" group-title="{group}",{escape(name)}')
        m3u.append(stream)
        xml.append(f'<channel id="{cid}"><display-name>{escape(name)}</display-name><icon src="{BASE}/{logo}"/></channel>')
        t = start0 - timedelta(minutes=random.choice([0, 15, 30]))
        titles = TITLES[group]
        i = random.randrange(len(titles))
        while t < now + timedelta(hours=24):
            dur = timedelta(minutes=random.choice([30, 30, 60, 60, 90, 120] if group == "Movies" else [30, 30, 45, 60, 60, 90]))
            title = titles[i % len(titles)]; i += 1
            progs.append(f'<programme start="{t.strftime("%Y%m%d%H%M%S +0000")}" stop="{(t+dur).strftime("%Y%m%d%H%M%S +0000")}" channel="{cid}">'
                         f'<title>{escape(title)}</title><desc>{escape(DESC)}</desc><category>{group}</category></programme>')
            t += dur
xml += progs
xml.append("</tv>")
open(os.path.join(OUT, "playlist.m3u"), "w", encoding="utf-8").write("\n".join(m3u) + "\n")
data = "\n".join(xml).encode("utf-8")
open(os.path.join(OUT, "guide.xml"), "wb").write(data)
with gzip.open(os.path.join(OUT, "guide.xml.gz"), "wb") as f:
    f.write(data)
print(n, "channels", len(progs), "programmes")
