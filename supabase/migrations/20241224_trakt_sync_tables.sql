-- Trakt Sync Tables Migration
-- This migration creates tables for syncing watched state between ARVIO and Trakt
-- Supabase is the source of truth for all watched state

-- ============================================================
-- Table: watched_movies
-- Stores movies that have been marked as watched
-- ============================================================
CREATE TABLE IF NOT EXISTS watched_movies (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
    tmdb_id INTEGER NOT NULL,
    trakt_id INTEGER,
    watched_at TIMESTAMPTZ DEFAULT NOW(),
    created_at TIMESTAMPTZ DEFAULT NOW(),

    -- Unique constraint: one entry per user per movie
    UNIQUE(user_id, tmdb_id)
);

-- Index for fast lookups by user
CREATE INDEX IF NOT EXISTS idx_watched_movies_user_id ON watched_movies(user_id);
CREATE INDEX IF NOT EXISTS idx_watched_movies_tmdb_id ON watched_movies(tmdb_id);
CREATE INDEX IF NOT EXISTS idx_watched_movies_watched_at ON watched_movies(watched_at DESC);

-- RLS policies
ALTER TABLE watched_movies ENABLE ROW LEVEL SECURITY;

CREATE POLICY "Users can view their own watched movies"
    ON watched_movies FOR SELECT
    USING (auth.uid() = user_id);

CREATE POLICY "Users can insert their own watched movies"
    ON watched_movies FOR INSERT
    WITH CHECK (auth.uid() = user_id);

CREATE POLICY "Users can update their own watched movies"
    ON watched_movies FOR UPDATE
    USING (auth.uid() = user_id);

CREATE POLICY "Users can delete their own watched movies"
    ON watched_movies FOR DELETE
    USING (auth.uid() = user_id);

-- ============================================================
-- Table: watched_episodes
-- Stores episodes that have been marked as watched
-- ============================================================
CREATE TABLE IF NOT EXISTS watched_episodes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
    tmdb_id INTEGER NOT NULL, -- Show TMDB ID
    season INTEGER NOT NULL,
    episode INTEGER NOT NULL,
    trakt_id INTEGER, -- Trakt episode ID (if available)
    show_trakt_id INTEGER, -- Trakt show ID
    watched_at TIMESTAMPTZ DEFAULT NOW(),
    source VARCHAR(20) DEFAULT 'arvio', -- 'trakt' or 'arvio'
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    created_at TIMESTAMPTZ DEFAULT NOW(),

    -- Unique constraint: one entry per user per episode
    UNIQUE(user_id, tmdb_id, season, episode)
);

-- Indexes for fast lookups
CREATE INDEX IF NOT EXISTS idx_watched_episodes_user_id ON watched_episodes(user_id);
CREATE INDEX IF NOT EXISTS idx_watched_episodes_tmdb_id ON watched_episodes(tmdb_id);
CREATE INDEX IF NOT EXISTS idx_watched_episodes_show ON watched_episodes(user_id, tmdb_id);
CREATE INDEX IF NOT EXISTS idx_watched_episodes_watched_at ON watched_episodes(watched_at DESC);

-- RLS policies
ALTER TABLE watched_episodes ENABLE ROW LEVEL SECURITY;

CREATE POLICY "Users can view their own watched episodes"
    ON watched_episodes FOR SELECT
    USING (auth.uid() = user_id);

CREATE POLICY "Users can insert their own watched episodes"
    ON watched_episodes FOR INSERT
    WITH CHECK (auth.uid() = user_id);

CREATE POLICY "Users can update their own watched episodes"
    ON watched_episodes FOR UPDATE
    USING (auth.uid() = user_id);

CREATE POLICY "Users can delete their own watched episodes"
    ON watched_episodes FOR DELETE
    USING (auth.uid() = user_id);

-- ============================================================
-- Table: episode_progress
-- Stores in-progress playback state (partially watched content)
-- ============================================================
CREATE TABLE IF NOT EXISTS episode_progress (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
    tmdb_id INTEGER NOT NULL, -- TMDB ID (show or movie)
    media_type VARCHAR(10) NOT NULL, -- 'movie' or 'tv'
    season INTEGER, -- NULL for movies
    episode INTEGER, -- NULL for movies
    trakt_id INTEGER, -- Trakt episode/movie ID
    show_trakt_id INTEGER, -- Trakt show ID (for TV)
    progress REAL NOT NULL DEFAULT 0, -- 0.0 to 1.0
    position_seconds BIGINT NOT NULL DEFAULT 0,
    duration_seconds BIGINT NOT NULL DEFAULT 0,
    paused_at TIMESTAMPTZ,
    last_updated_at TIMESTAMPTZ DEFAULT NOW(),
    source VARCHAR(20) DEFAULT 'arvio', -- 'trakt' or 'arvio'
    title VARCHAR(500),
    episode_title VARCHAR(500),
    backdrop_path VARCHAR(500),
    poster_path VARCHAR(500),
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- Indexes for fast lookups
CREATE UNIQUE INDEX IF NOT EXISTS idx_episode_progress_user_content_uidx
    ON episode_progress(user_id, tmdb_id, COALESCE(season, -1), COALESCE(episode, -1));
CREATE INDEX IF NOT EXISTS idx_episode_progress_user_id ON episode_progress(user_id);
CREATE INDEX IF NOT EXISTS idx_episode_progress_updated ON episode_progress(last_updated_at DESC);
CREATE INDEX IF NOT EXISTS idx_episode_progress_content ON episode_progress(user_id, tmdb_id, season, episode);

-- RLS policies
ALTER TABLE episode_progress ENABLE ROW LEVEL SECURITY;

CREATE POLICY "Users can view their own episode progress"
    ON episode_progress FOR SELECT
    USING (auth.uid() = user_id);

CREATE POLICY "Users can insert their own episode progress"
    ON episode_progress FOR INSERT
    WITH CHECK (auth.uid() = user_id);

CREATE POLICY "Users can update their own episode progress"
    ON episode_progress FOR UPDATE
    USING (auth.uid() = user_id);

CREATE POLICY "Users can delete their own episode progress"
    ON episode_progress FOR DELETE
    USING (auth.uid() = user_id);

-- ============================================================
-- Table: sync_state
-- Tracks Trakt synchronization status per user
-- ============================================================
CREATE TABLE IF NOT EXISTS sync_state (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
    last_sync_at TIMESTAMPTZ,
    last_full_sync_at TIMESTAMPTZ,
    last_trakt_activities JSONB, -- Stores Trakt last_activities response
    movies_synced INTEGER DEFAULT 0,
    episodes_synced INTEGER DEFAULT 0,
    sync_in_progress BOOLEAN DEFAULT FALSE,
    last_error TEXT,
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    created_at TIMESTAMPTZ DEFAULT NOW(),

    -- One sync state entry per user
    UNIQUE(user_id)
);

-- Index
CREATE INDEX IF NOT EXISTS idx_sync_state_user_id ON sync_state(user_id);

-- RLS policies
ALTER TABLE sync_state ENABLE ROW LEVEL SECURITY;

CREATE POLICY "Users can view their own sync state"
    ON sync_state FOR SELECT
    USING (auth.uid() = user_id);

CREATE POLICY "Users can insert their own sync state"
    ON sync_state FOR INSERT
    WITH CHECK (auth.uid() = user_id);

CREATE POLICY "Users can update their own sync state"
    ON sync_state FOR UPDATE
    USING (auth.uid() = user_id);

CREATE POLICY "Users can delete their own sync state"
    ON sync_state FOR DELETE
    USING (auth.uid() = user_id);

-- ============================================================
-- Trigger: Auto-update updated_at timestamp
-- ============================================================
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Apply trigger to tables with updated_at
CREATE TRIGGER update_watched_episodes_updated_at
    BEFORE UPDATE ON watched_episodes
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_episode_progress_updated_at
    BEFORE UPDATE ON episode_progress
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_sync_state_updated_at
    BEFORE UPDATE ON sync_state
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- ============================================================
-- Function: Get next unwatched episode for a show
-- Returns the next episode to watch based on watched_episodes
-- ============================================================
CREATE OR REPLACE FUNCTION get_next_unwatched_episode(
    p_user_id UUID,
    p_tmdb_id INTEGER,
    p_total_seasons INTEGER,
    p_episodes_per_season INTEGER[]
)
RETURNS TABLE(next_season INTEGER, next_episode INTEGER) AS $$
DECLARE
    s INTEGER;
    e INTEGER;
    is_watched BOOLEAN;
BEGIN
    FOR s IN 1..p_total_seasons LOOP
        FOR e IN 1..COALESCE(p_episodes_per_season[s], 10) LOOP
            SELECT EXISTS(
                SELECT 1 FROM watched_episodes
                WHERE user_id = p_user_id
                AND tmdb_id = p_tmdb_id
                AND season = s
                AND episode = e
            ) INTO is_watched;

            IF NOT is_watched THEN
                next_season := s;
                next_episode := e;
                RETURN NEXT;
                RETURN;
            END IF;
        END LOOP;
    END LOOP;

    -- All watched
    next_season := NULL;
    next_episode := NULL;
    RETURN NEXT;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

-- ============================================================
-- Function: Clean up completed progress entries
-- Removes episode_progress entries for content that's now watched
-- ============================================================
CREATE OR REPLACE FUNCTION cleanup_completed_progress()
RETURNS TRIGGER AS $$
BEGIN
    -- When an episode is marked as watched, remove its progress entry
    IF TG_TABLE_NAME = 'watched_episodes' THEN
        DELETE FROM episode_progress
        WHERE user_id = NEW.user_id
        AND tmdb_id = NEW.tmdb_id
        AND season = NEW.season
        AND episode = NEW.episode;
    END IF;

    -- When a movie is marked as watched, remove its progress entry
    IF TG_TABLE_NAME = 'watched_movies' THEN
        DELETE FROM episode_progress
        WHERE user_id = NEW.user_id
        AND tmdb_id = NEW.tmdb_id
        AND media_type = 'movie';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

-- Apply cleanup trigger
CREATE TRIGGER cleanup_progress_on_episode_watched
    AFTER INSERT ON watched_episodes
    FOR EACH ROW EXECUTE FUNCTION cleanup_completed_progress();

CREATE TRIGGER cleanup_progress_on_movie_watched
    AFTER INSERT ON watched_movies
    FOR EACH ROW EXECUTE FUNCTION cleanup_completed_progress();

-- ============================================================
-- Grant permissions for service role (for bulk operations)
-- ============================================================
GRANT ALL ON watched_movies TO authenticated;
GRANT ALL ON watched_episodes TO authenticated;
GRANT ALL ON episode_progress TO authenticated;
GRANT ALL ON sync_state TO authenticated;

-- Allow service role to bypass RLS for sync operations
