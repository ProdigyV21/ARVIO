#!/usr/bin/env python3
"""Validate extracted ARVIO service configuration without printing secrets."""
from pathlib import Path
import json
import urllib.error
import urllib.parse
import urllib.request

ROOT = Path(__file__).resolve().parents[1]
props = {}
for line in (ROOT / "secrets.properties").read_text(encoding="utf-8").splitlines():
    if line and not line.startswith("#") and "=" in line:
        key, value = line.split("=", 1)
        props[key] = value


def request(name, url, headers=None):
    req = urllib.request.Request(
        url,
        headers={"User-Agent": "ARVIO/1.9.995", **(headers or {})},
    )
    try:
        with urllib.request.urlopen(req, timeout=30) as response:
            body = response.read(500_000)
            schema = []
            try:
                parsed = json.loads(body)
                schema = sorted(parsed) if isinstance(parsed, dict) else [f"list[{len(parsed)}]"]
            except Exception:
                pass
            print(f"{name}: status={response.status}, ok=True, schema={schema[:8]}")
    except urllib.error.HTTPError as exc:
        body = exc.read(500).decode("utf-8", errors="replace").replace("\n", " ")
        print(f"{name}: status={exc.code}, ok=False, response={body[:200]!r}")
    except Exception as exc:
        print(f"{name}: error={type(exc).__name__}, detail={str(exc)[:160]!r}")

request(
    "TMDB direct",
    "https://api.themoviedb.org/3/configuration?" + urllib.parse.urlencode({"api_key": props["TMDB_API_KEY"]}),
)
request(
    "Trakt direct",
    "https://api.trakt.tv/movies/trending?limit=1",
    {"trakt-api-key": props["TRAKT_CLIENT_ID"], "trakt-api-version": "2", "Content-Type": "application/json"},
)
request(
    "Simkl via ARVIO proxy",
    props["NETLIFY_BACKEND_URL"].rstrip("/") + "/simkl-proxy?" + urllib.parse.urlencode({"path": "/oauth/pin", "method": "GET"}),
    {"apikey": props["APP_ANON_KEY"], "Authorization": "Bearer " + props["APP_ANON_KEY"]},
)
request(
    "Supabase cloud endpoint",
    props["SUPABASE_URL"].rstrip("/") + "/rest/v1/",
    {"apikey": props["SUPABASE_ANON_KEY"], "Authorization": "Bearer " + props["SUPABASE_ANON_KEY"]},
)
