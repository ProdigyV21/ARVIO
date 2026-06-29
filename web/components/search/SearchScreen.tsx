"use client";

import { Search } from "lucide-react";
import { useApp } from "@/lib/store";
import { MediaCard } from "@/components/media/MediaCard";

export function SearchScreen() {
  const { query, setQuery, results, openDetails } = useApp();
  return (
    <div className="screen">
      <section className="search-hero">
        <Search size={28} />
        <input
          value={query}
          onChange={(event) => setQuery(event.target.value)}
          autoFocus
          placeholder="Search movies, series, people, and catalogs"
        />
      </section>
      <div className="grid-results">
        {results.map((item) => <MediaCard key={`${item.mediaType}-${item.id}`} item={item} onOpen={openDetails} />)}
      </div>
    </div>
  );
}
