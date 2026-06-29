"use client";

import { Plus, Sparkles } from "lucide-react";
import { useState } from "react";
import { useApp } from "@/lib/store";

export function AddonsScreen() {
  const { addons, installAddon, removeAddon } = useApp();
  const [url, setUrl] = useState("");
  return (
    <div className="screen">
      <section className="section-heading">
        <p className="eyebrow">Sources</p>
        <h2>Addons</h2>
      </section>
      <div className="inline-form wide">
        <input value={url} onChange={(event) => setUrl(event.target.value)} placeholder="https://addon.example.com/manifest.json" />
        <button className="primary" onClick={async () => {
          if (!url.trim()) return;
          await installAddon(url);
          setUrl("");
        }}><Plus size={18} /> Install</button>
      </div>
      <div className="addon-grid">
        {addons.map((addon) => (
          <article className="addon-tile" key={addon.id}>
            <Sparkles size={24} />
            <h3>{addon.name}</h3>
            <p>{addon.description || addon.manifestUrl}</p>
            <div className="chips">
              <span>{addon.version}</span>
              <span>{addon.resources.join(", ") || "manifest"}</span>
            </div>
            <button className="secondary" onClick={() => removeAddon(addon)}>Remove</button>
          </article>
        ))}
      </div>
    </div>
  );
}
