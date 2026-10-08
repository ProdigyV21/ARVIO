import { validateDraft } from './studio-core.mjs';

// Authored discovery queries only: no private lists, tokens or playable media.
const folder = (id, title, description, theme, preset) => ({
  id, title, description, theme, source: { kind: 'tmdb', preset }
});
export const STARTERS = [
  { id: 'weekend', title: 'Weekend discoveries', description: 'A movie night, a new series and a little science fiction.', draft: {
    version: 1, title: 'Weekend discoveries', folders: [
      folder('cinema', 'Cinema night', 'Highly rated movies with at least 1,000 votes.', 'sage', 'movie-acclaimed'),
      folder('episodes', 'One more episode', 'Popular shows with at least 100 votes.', 'plum', 'tv-popular'),
      folder('scifi', 'Beyond the ordinary', 'Science fiction rated 6+ with at least 100 votes.', 'ocean', 'movie-scifi')
    ]
  } },
  { id: 'highly-rated', title: 'Highly rated picks', description: 'Two focused folders for well-rated movies and series.', draft: {
    version: 1, title: 'Highly rated picks', folders: [
      folder('movies', 'Highly rated movies', 'Rating 7+ and at least 1,000 votes, sorted by rating.', 'sage', 'movie-acclaimed'),
      folder('shows', 'Highly rated shows', 'Rating 7+ and at least 500 votes, sorted by rating.', 'plum', 'tv-acclaimed')
    ]
  } },
  { id: 'animation-scifi', title: 'Animation and sci-fi', description: 'Genre discovery for animation and science-fiction fans.', draft: {
    version: 1, title: 'Animation and sci-fi', folders: [
      folder('animation', 'Animated movies', 'Animated movies with at least 100 votes. Not age-filtered.', 'ember', 'movie-animation'),
      folder('scifi', 'Science fiction', 'Science fiction rated 6+ with at least 100 votes.', 'ocean', 'movie-scifi')
    ]
  } }
];
for (const starter of STARTERS) validateDraft(starter.draft);
