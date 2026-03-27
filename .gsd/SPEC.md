# SPEC: Episode List layout Improvements

## Status: FINALIZED

## Problem Statement
The current horizontal scroll layout for episodes is inefficient for anime/shows with high episode counts (e.g., 500-1000+). Users spend too much time scrolling horizontally.

## Goals
- Add a toggle to switch between Horizontal (default) and Vertical scrollable lists.
- Improve navigation for high-count series via grouping/batches.
- Maintain premium aesthetics and TV focus compatibility.

## Functional Requirements

### 1. Toggle Mechanism
- A "List View" or "Expand" button/icon in the metadata/action row.
- Persist the selection (at least within the session).

### 2. Vertical List View (Mobile)
- Switch `LazyRow` to a vertical column structure.
- Show episode thumbnails, titles, and synopses in a vertical scrollable list.

### 3. Vertical List View (TV)
- On TV, a vertical list within the main `DetailsScreen` scroll is complex for focus.
- **Approach**: Clicking "Expand" opens an immersive vertical list overlay/modal or transitions the episode section into a full-width vertical grid/list that grabs focus logic.
- Support D-pad navigation.

### 4. Grouping (Batches)
- If episode count > 100, provide batch selectors (e.g., "1-100", "101-200").
- Batch selectors should be horizontal chips at the top of the episode section.

### 5. Jump to Episode (Optional/Phase 2)
- Input field to jump to a specific episode number.

## Technical Design

### ViewModel State (`DetailsUiState`)
- `isEpisodesExpanded: Boolean` (default: false)
- `episodesBatches: List<EpisodeBatch>` (computed from full episode list)
- `selectedBatchIndex: Int` (default: 0)

### UI Components
- `HorizontalEpisodeRow`: Existing implementation.
- `VerticalEpisodeList`: New implementation.
- `EpisodeBatchSelector`: New component for switching batches.

## Verification
- Verify layout switch on both platforms.
- Verify batching logic for > 100 episodes.
- Verify focus doesn't get "stuck" when entering/exiting vertical mode on TV.
