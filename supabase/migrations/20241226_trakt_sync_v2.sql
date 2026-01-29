-- Trakt Sync Tables Migration v2
-- Adds episode ID mapping, watch_history, and sync_state fields required for Trakt + Supabase sync.

BEGIN;

-- ============================================================
-- watched_episodes: add mapping fields + watched flag
-- ============================================================
ALTER TABLE watched_episodes
    ADD COLUMN IF NOT EXISTS trakt_episode_id INTEGER,
    ADD COLUMN IF NOT EXISTS tmdb_episode_id INTEGER,
    ADD COLUMN IF NOT EXISTS watched BOOLEAN DEFAULT TRUE,
    ADD COLUMN IF NOT EXISTS show_tmdb_id INTEGER;

-- Backfill new columns from legacy fields
UPDATE watched_episodes
SET show_tmdb_id = tmdb_id
WHERE show_tmdb_id IS NULL;

UPDATE watched_episodes
SET trakt_episode_id = trakt_id
WHERE trakt_episode_id IS NULL AND trakt_id IS NOT NULL;

-- Unique indexes for stable dedupe (prefer trakt_episode_id, fallback to show IDs)
CREATE UNIQUE INDEX IF NOT EXISTS watched_episodes_user_trakt_episode_uidx
    ON watched_episodes(user_id, trakt_episode_id)
    WHERE trakt_episode_id IS NOT NULL;

CREATE UNIQUE INDEX IF NOT EXISTS watched_episodes_user_show_trakt_uidx
    ON watched_episodes(user_id, show_trakt_id, season, episode)
    WHERE trakt_episode_id IS NULL AND show_trakt_id IS NOT NULL;

CREATE UNIQUE INDEX IF NOT EXISTS watched_episodes_user_show_tmdb_uidx
    ON watched_episodes(user_id, show_tmdb_id, season, episode)
    WHERE trakt_episode_id IS NULL AND show_trakt_id IS NULL AND show_tmdb_id IS NOT NULL;

-- ============================================================
-- watch_history: progress/resume state mirror (Supabase source of truth)
-- ============================================================
CREATE TABLE IF NOT EXISTS watch_history (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
    media_type VARCHAR(10) NOT NULL, -- 'movie' or 'tv'
    show_tmdb_id INTEGER, -- Show TMDB ID (or movie TMDB ID)
    show_trakt_id INTEGER, -- Trakt show ID
    season INTEGER,
    episode INTEGER,
    trakt_episode_id INTEGER, -- Trakt episode ID
    tmdb_episode_id INTEGER,
    progress NUMERIC NOT NULL DEFAULT 0, -- 0.0 to 1.0
    position_seconds BIGINT NOT NULL DEFAULT 0,
    duration_seconds BIGINT NOT NULL DEFAULT 0,
    paused_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    source VARCHAR(20) DEFAULT 'arvio', -- 'trakt' or 'arvio'
    title VARCHAR(500),
    episode_title VARCHAR(500),
    backdrop_path VARCHAR(500),
    poster_path VARCHAR(500),
    created_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_watch_history_user_id ON watch_history(user_id);
CREATE INDEX IF NOT EXISTS idx_watch_history_updated ON watch_history(updated_at DESC);
CREATE INDEX IF NOT EXISTS idx_watch_history_content ON watch_history(user_id, show_tmdb_id, season, episode);

-- Unique indexes for dedupe
CREATE UNIQUE INDEX IF NOT EXISTS watch_history_user_trakt_episode_uidx
    ON watch_history(user_id, trakt_episode_id)
    WHERE trakt_episode_id IS NOT NULL;

CREATE UNIQUE INDEX IF NOT EXISTS watch_history_user_show_trakt_uidx
    ON watch_history(user_id, show_trakt_id, season, episode)
    WHERE trakt_episode_id IS NULL AND show_trakt_id IS NOT NULL;

CREATE UNIQUE INDEX IF NOT EXISTS watch_history_user_show_tmdb_uidx
    ON watch_history(user_id, show_tmdb_id, season, episode)
    WHERE trakt_episode_id IS NULL AND show_trakt_id IS NULL AND show_tmdb_id IS NOT NULL;

CREATE UNIQUE INDEX IF NOT EXISTS watch_history_user_movie_tmdb_uidx
    ON watch_history(user_id, show_tmdb_id)
    WHERE media_type = 'movie';

-- RLS policies
ALTER TABLE watch_history ENABLE ROW LEVEL SECURITY;

CREATE POLICY "Users can view their own watch history"
    ON watch_history FOR SELECT
    USING (auth.uid() = user_id);

CREATE POLICY "Users can insert their own watch history"
    ON watch_history FOR INSERT
    WITH CHECK (auth.uid() = user_id);

CREATE POLICY "Users can update their own watch history"
    ON watch_history FOR UPDATE
    USING (auth.uid() = user_id);

CREATE POLICY "Users can delete their own watch history"
    ON watch_history FOR DELETE
    USING (auth.uid() = user_id);

-- ============================================================
-- sync_state: add JSON field for last activities
-- ============================================================
ALTER TABLE sync_state
    ADD COLUMN IF NOT EXISTS last_trakt_activities_json JSONB;

UPDATE sync_state
SET last_trakt_activities_json = last_trakt_activities
WHERE last_trakt_activities_json IS NULL AND last_trakt_activities IS NOT NULL;

-- ============================================================
-- Trigger updates for updated_at columns
-- ============================================================
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ language 'plpgsql';

DROP TRIGGER IF EXISTS update_watch_history_updated_at ON watch_history;
CREATE TRIGGER update_watch_history_updated_at
    BEFORE UPDATE ON watch_history
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- ============================================================
-- Cleanup progress when content is marked watched
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

        DELETE FROM watch_history
        WHERE user_id = NEW.user_id
        AND ((show_tmdb_id = NEW.show_tmdb_id) OR (show_tmdb_id = NEW.tmdb_id))
        AND season = NEW.season
        AND episode = NEW.episode;
    END IF;

    -- When a movie is marked as watched, remove its progress entry
    IF TG_TABLE_NAME = 'watched_movies' THEN
        DELETE FROM episode_progress
        WHERE user_id = NEW.user_id
        AND tmdb_id = NEW.tmdb_id
        AND media_type = 'movie';

        DELETE FROM watch_history
        WHERE user_id = NEW.user_id
        AND show_tmdb_id = NEW.tmdb_id
        AND media_type = 'movie';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

-- ============================================================
-- Grants
-- ============================================================
GRANT ALL ON watch_history TO authenticated;

COMMIT;
