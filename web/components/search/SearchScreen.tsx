"use client";

import { LoaderCircle, Search } from "lucide-react";
import { useApp } from "@/lib/store";
import { MediaCard } from "@/components/media/MediaCard";

export function SearchScreen() {
  const { query, setQuery, results, openDetails, settings, searchState } = useApp();
  const posterMode = settings.cardLayoutMode === "poster";
  return (
    // has-search-hero mirrors the CSS :has(.search-hero) rules for TV
    // browsers whose engines predate :has() support (Tizen/webOS).
    <div className={`screen has-search-hero ${posterMode ? "poster-results" : ""}`}>
      <section className="search-hero">
        <span className="search-icon-shell"><Search size={28} /></span>
        <input
          value={query}
          onChange={(event) => setQuery(event.target.value)}
          autoFocus
          placeholder="Search movies and series"
          aria-label="Search movies and series"
        />
      </section>
      {searchState === "loading" && <div className="search-status" role="status"><LoaderCircle size={20} /> Searching</div>}
      {searchState === "error" && <div className="library-error" role="alert">Search is temporarily unavailable. Please try again.</div>}
      {query.trim() && searchState === "idle" && !results.length && <div className="watchlist-empty"><Search size={34} /><p>No matching titles</p></div>}
      <div className="grid-results">
        {results.map((item) => <MediaCard key={`${item.mediaType}-${item.id}`} item={item} onOpen={openDetails} posterMode={posterMode} />)}
      </div>
    </div>
  );
}
