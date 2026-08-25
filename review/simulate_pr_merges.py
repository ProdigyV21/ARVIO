#!/usr/bin/env python3
import json
import os
import subprocess
from pathlib import Path

REPO = Path("/home/tormox/projects/ARVIO")
EXTERNAL = [598, 586, 549, 548, 543, 518, 504, 417, 416, 415, 414, 411, 409, 408, 324, 310, 309, 308, 307, 306, 305, 290, 288, 284, 280]
OURS = {
    599: "fork/fix/mobile-landscape-bottom-nav",
    600: "fork/fix/iptv-landscape-phone-guide",
    601: "fork/fix/iptv-manual-refresh-epg",
    602: "fork/fix/autoplay-unaired-episodes",
    603: "fork/feat/iptv-playlist-category-navigation",
    604: "fork/fix/iptv-epg-temporal-actions",
}
ENV = os.environ.copy()
ENV.update({
    "GIT_AUTHOR_NAME": "ARVIO comparison",
    "GIT_AUTHOR_EMAIL": "comparison@localhost",
    "GIT_COMMITTER_NAME": "ARVIO comparison",
    "GIT_COMMITTER_EMAIL": "comparison@localhost",
})


def git(*args, input_text=None):
    return subprocess.run(
        ["git", *args], cwd=REPO, env=ENV, input=input_text,
        text=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT,
    )


def merge_tree(left, right):
    result = git("merge-tree", "--write-tree", left, right)
    lines = result.stdout.splitlines()
    tree = lines[0].strip() if lines and len(lines[0].strip()) == 40 else None
    conflicts = []
    for line in lines:
        if "CONFLICT" in line:
            conflicts.append(line.strip())
    return {"clean": result.returncode == 0, "tree": tree, "conflicts": conflicts, "output": result.stdout[-4000:]}


results = {}
for number in EXTERNAL:
    ext_ref = f"origin/pr/{number}"
    ext_merge = merge_tree("origin/main", ext_ref)
    record = {"external_to_current_main": ext_merge, "against_ours": {}}
    if ext_merge["clean"] and ext_merge["tree"]:
        commit = git(
            "commit-tree", ext_merge["tree"],
            "-p", "origin/main", "-p", ext_ref,
            input_text=f"synthetic merge of external PR {number}\n",
        )
        synthetic = commit.stdout.strip().splitlines()[-1] if commit.returncode == 0 else None
        record["synthetic_commit"] = synthetic
        if synthetic:
            for our_number, our_ref in OURS.items():
                pair = merge_tree(synthetic, our_ref)
                record["against_ours"][str(our_number)] = {
                    "clean": pair["clean"],
                    "conflicts": pair["conflicts"],
                }
    results[str(number)] = record

out_path = REPO / "review" / "open-external-pr-merge-simulation.json"
out_path.write_text(json.dumps(results, indent=2) + "\n")
summary = {}
for number, record in results.items():
    summary[number] = {
        "external_to_current_main_clean": record["external_to_current_main"]["clean"],
        "conflicts_with_our_prs": [int(n) for n, x in record["against_ours"].items() if not x["clean"]],
    }
print(json.dumps({"path": str(out_path), "summary": summary}, indent=2))
