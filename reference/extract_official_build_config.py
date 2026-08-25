#!/usr/bin/env python3
"""Extract selected ARVIO BuildConfig values without printing secrets."""
from pathlib import Path
import json
import re

ROOT = Path(__file__).resolve().parents[1]
SOURCE = ROOT / "reference/official-playstore-jadx/sources/com/arflix/tv/BuildConfig.java"
DEST = ROOT / "secrets.properties"

text = SOURCE.read_text(encoding="utf-8")
pairs = re.findall(
    r'public static final String ([A-Z0-9_]+) = "((?:\\.|[^"\\])*)";',
    text,
)
constants = {name: json.loads(f'"{raw}"') for name, raw in pairs}

keys = [
    "SUPABASE_URL",
    "SUPABASE_ANON_KEY",
    "APP_ANON_KEY",
    "NETLIFY_BACKEND_URL",
    "TMDB_API_KEY",
    "TRAKT_CLIENT_ID",
    "TRAKT_CLIENT_SECRET",
    "SIMKL_CLIENT_ID",
    "GOOGLE_WEB_CLIENT_ID",
]
missing = [key for key in keys if key not in constants]
if missing:
    raise SystemExit(f"Missing expected constants: {', '.join(missing)}")

lines = [
    "# Extracted locally from the official ARVIO Play build for fork testing.",
    "# This file is gitignored. Do not commit.",
]
for key in keys:
    lines.append(f"{key}={constants[key]}")

# The build script reads DISCORD_CLIENT_ID, while BuildConfig names the output
# field DISCORD_APPLICATION_ID.
if "DISCORD_APPLICATION_ID" in constants:
    lines.append(f"DISCORD_CLIENT_ID={constants['DISCORD_APPLICATION_ID']}")

DEST.write_text("\n".join(lines) + "\n", encoding="utf-8")
DEST.chmod(0o600)
print(f"Wrote {len(lines) - 2} keys to {DEST}")
for key in keys:
    value = constants[key]
    print(f"{key}: length={len(value)}, placeholder={value.startswith('your-')}")
