package com.arflix.tv.data.api;

import com.arflix.tv.util.Constants;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class f {
    public static /* synthetic */ Object a(SupabaseApi supabaseApi, String str, String str2, String str3, List list, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: bulkUpsertWatchedEpisodes");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str4 = str2;
        if ((i10 & 4) != 0) {
            str3 = "resolution=merge-duplicates";
        }
        return supabaseApi.bulkUpsertWatchedEpisodes(str, str4, str3, list, dVar);
    }

    public static /* synthetic */ Object b(SupabaseApi supabaseApi, String str, String str2, String str3, List list, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: bulkUpsertWatchedMovies");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str4 = str2;
        if ((i10 & 4) != 0) {
            str3 = "resolution=merge-duplicates";
        }
        return supabaseApi.bulkUpsertWatchedMovies(str, str4, str3, list, dVar);
    }

    public static /* synthetic */ Object c(SupabaseApi supabaseApi, String str, String str2, String str3, String str4, String str5, String str6, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteEpisodeProgress");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        return supabaseApi.deleteEpisodeProgress(str, str2, str3, str4, str5, str6, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object d(SupabaseApi supabaseApi, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, d7.d dVar, int i10, Object obj) {
        String str10;
        SupabaseApi supabaseApi2;
        String str11;
        String str12;
        d7.d dVar2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteWatchHistory");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str13 = str2;
        String str14 = (i10 & 8) != 0 ? null : str4;
        String str15 = (i10 & 16) != 0 ? null : str5;
        String str16 = (i10 & 32) != 0 ? null : str6;
        String str17 = (i10 & 64) != 0 ? null : str7;
        String str18 = (i10 & 128) != 0 ? null : str8;
        if ((i10 & 256) != 0) {
            str10 = null;
            supabaseApi2 = supabaseApi;
            str12 = str3;
            dVar2 = dVar;
            str11 = str;
        } else {
            str10 = str9;
            supabaseApi2 = supabaseApi;
            str11 = str;
            str12 = str3;
            dVar2 = dVar;
        }
        return supabaseApi2.deleteWatchHistory(str11, str13, str12, str14, str15, str16, str17, str18, str10, dVar2);
    }

    public static /* synthetic */ Object e(SupabaseApi supabaseApi, String str, String str2, String str3, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteWatchHistoryByIds");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        return supabaseApi.deleteWatchHistoryByIds(str, str2, str3, dVar);
    }

    public static /* synthetic */ Object f(SupabaseApi supabaseApi, String str, String str2, String str3, String str4, String str5, String str6, String str7, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteWatchedEpisode");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str8 = str2;
        if ((i10 & 8) != 0) {
            str4 = null;
        }
        return supabaseApi.deleteWatchedEpisode(str, str8, str3, str4, str5, str6, str7, dVar);
    }

    public static /* synthetic */ Object g(SupabaseApi supabaseApi, String str, String str2, String str3, String str4, String str5, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteWatchedMovie");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str6 = str2;
        if ((i10 & 8) != 0) {
            str4 = null;
        }
        return supabaseApi.deleteWatchedMovie(str, str6, str3, str4, str5, dVar);
    }

    public static /* synthetic */ Object h(SupabaseApi supabaseApi, String str, String str2, String str3, String str4, String str5, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteWatchlist");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        return supabaseApi.deleteWatchlist(str, str2, str3, str4, str5, dVar);
    }

    public static /* synthetic */ Object i(SupabaseApi supabaseApi, String str, String str2, String str3, String str4, String str5, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getEpisodeProgress");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str6 = str2;
        if ((i10 & 8) != 0) {
            str4 = "*";
        }
        String str7 = str4;
        if ((i10 & 16) != 0) {
            str5 = "last_updated_at.desc";
        }
        return supabaseApi.getEpisodeProgress(str, str6, str3, str7, str5, dVar);
    }

    public static /* synthetic */ Object j(SupabaseApi supabaseApi, String str, String str2, String str3, String str4, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getProfile");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str5 = str2;
        if ((i10 & 8) != 0) {
            str4 = "*";
        }
        return supabaseApi.getProfile(str, str5, str3, str4, dVar);
    }

    public static /* synthetic */ Object k(SupabaseApi supabaseApi, String str, String str2, String str3, String str4, String str5, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getSyncState");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str6 = str2;
        if ((i10 & 8) != 0) {
            str4 = null;
        }
        String str7 = str4;
        if ((i10 & 16) != 0) {
            str5 = "*";
        }
        return supabaseApi.getSyncState(str, str6, str3, str7, str5, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object l(SupabaseApi supabaseApi, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i10, Integer num, d7.d dVar, int i11, Object obj) {
        Integer num2;
        SupabaseApi supabaseApi2;
        String str9;
        String str10;
        d7.d dVar2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWatchHistory");
        }
        String supabase_anon_key = (i11 & 2) != 0 ? Constants.INSTANCE.getSUPABASE_ANON_KEY() : str2;
        String str11 = (i11 & 8) != 0 ? null : str4;
        String str12 = (i11 & 16) != 0 ? null : str5;
        String str13 = (i11 & 32) != 0 ? null : str6;
        String str14 = (i11 & 64) != 0 ? "*" : str7;
        String str15 = (i11 & 128) != 0 ? "updated_at.desc" : str8;
        int i12 = (i11 & 256) != 0 ? 50 : i10;
        if ((i11 & 512) != 0) {
            num2 = null;
            str9 = str;
            str10 = str3;
            dVar2 = dVar;
            supabaseApi2 = supabaseApi;
        } else {
            num2 = num;
            supabaseApi2 = supabaseApi;
            str9 = str;
            str10 = str3;
            dVar2 = dVar;
        }
        return supabaseApi2.getWatchHistory(str9, supabase_anon_key, str10, str11, str12, str13, str14, str15, i12, num2, dVar2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object m(SupabaseApi supabaseApi, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, Integer num, d7.d dVar, int i10, Object obj) {
        Integer num2;
        SupabaseApi supabaseApi2;
        String str12;
        String str13;
        String str14;
        String str15;
        d7.d dVar2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWatchHistoryItem");
        }
        String supabase_anon_key = (i10 & 2) != 0 ? Constants.INSTANCE.getSUPABASE_ANON_KEY() : str2;
        String str16 = (i10 & 8) != 0 ? null : str4;
        String str17 = (i10 & 64) != 0 ? null : str7;
        String str18 = (i10 & 128) != 0 ? null : str8;
        String str19 = (i10 & 256) != 0 ? null : str9;
        String str20 = (i10 & 512) != 0 ? "*" : str10;
        String str21 = (i10 & 1024) != 0 ? null : str11;
        if ((i10 & 2048) != 0) {
            num2 = null;
            str12 = str;
            str13 = str3;
            str14 = str5;
            str15 = str6;
            dVar2 = dVar;
            supabaseApi2 = supabaseApi;
        } else {
            num2 = num;
            supabaseApi2 = supabaseApi;
            str12 = str;
            str13 = str3;
            str14 = str5;
            str15 = str6;
            dVar2 = dVar;
        }
        return supabaseApi2.getWatchHistoryItem(str12, supabase_anon_key, str13, str16, str14, str15, str17, str18, str19, str20, str21, num2, dVar2);
    }

    public static /* synthetic */ Object n(SupabaseApi supabaseApi, String str, String str2, String str3, String str4, String str5, String str6, int i10, int i11, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWatchedEpisodes");
        }
        if ((i12 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        return supabaseApi.getWatchedEpisodes(str, str2, str3, (i12 & 8) != 0 ? null : str4, (i12 & 16) != 0 ? "user_id,profile_id,tmdb_id,show_trakt_id,season,episode,trakt_episode_id,tmdb_episode_id,watched_at,updated_at,source" : str5, (i12 & 32) != 0 ? "tmdb_id,season,episode" : str6, (i12 & 64) != 0 ? 0 : i10, (i12 & 128) != 0 ? 1000 : i11, dVar);
    }

    public static /* synthetic */ Object o(SupabaseApi supabaseApi, String str, String str2, String str3, String str4, String str5, String str6, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWatchedEpisodesForShow");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str7 = str2;
        if ((i10 & 8) != 0) {
            str4 = null;
        }
        String str8 = str4;
        if ((i10 & 32) != 0) {
            str6 = "user_id,profile_id,tmdb_id,show_trakt_id,season,episode,trakt_episode_id,tmdb_episode_id,watched_at,updated_at,source";
        }
        return supabaseApi.getWatchedEpisodesForShow(str, str7, str3, str8, str5, str6, dVar);
    }

    public static /* synthetic */ Object p(SupabaseApi supabaseApi, String str, String str2, String str3, String str4, String str5, String str6, int i10, int i11, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWatchedMovies");
        }
        if ((i12 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        return supabaseApi.getWatchedMovies(str, str2, str3, (i12 & 8) != 0 ? null : str4, (i12 & 16) != 0 ? "user_id,profile_id,tmdb_id,trakt_id,watched_at" : str5, (i12 & 32) != 0 ? "tmdb_id" : str6, (i12 & 64) != 0 ? 0 : i10, (i12 & 128) != 0 ? 1000 : i11, dVar);
    }

    public static /* synthetic */ Object q(SupabaseApi supabaseApi, String str, String str2, String str3, String str4, String str5, String str6, String str7, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWatchlist");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        return supabaseApi.getWatchlist(str, str2, str3, (i10 & 8) != 0 ? null : str4, (i10 & 16) != 0 ? null : str5, (i10 & 32) != 0 ? "*" : str6, (i10 & 64) != 0 ? "added_at.desc" : str7, dVar);
    }

    public static /* synthetic */ Object r(SupabaseApi supabaseApi, String str, String str2, String str3, WatchedEpisodeRecord watchedEpisodeRecord, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: markEpisodeWatched");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str4 = str2;
        if ((i10 & 4) != 0) {
            str3 = "resolution=merge-duplicates";
        }
        return supabaseApi.markEpisodeWatched(str, str4, str3, watchedEpisodeRecord, dVar);
    }

    public static /* synthetic */ Object s(SupabaseApi supabaseApi, String str, String str2, String str3, MarkEpisodeWatchedParams markEpisodeWatchedParams, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: markEpisodeWatchedRpc");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str4 = str2;
        if ((i10 & 4) != 0) {
            str3 = "no-cache, no-store";
        }
        return supabaseApi.markEpisodeWatchedRpc(str, str4, str3, markEpisodeWatchedParams, dVar);
    }

    public static /* synthetic */ Object t(SupabaseApi supabaseApi, String str, String str2, String str3, WatchedMovieRecord watchedMovieRecord, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: markMovieWatched");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str4 = str2;
        if ((i10 & 4) != 0) {
            str3 = "resolution=merge-duplicates";
        }
        return supabaseApi.markMovieWatched(str, str4, str3, watchedMovieRecord, dVar);
    }

    public static /* synthetic */ Object u(SupabaseApi supabaseApi, String str, String str2, String str3, UserProfileUpdate userProfileUpdate, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateProfile");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        return supabaseApi.updateProfile(str, str2, str3, userProfileUpdate, dVar);
    }

    public static /* synthetic */ Object v(SupabaseApi supabaseApi, String str, String str2, String str3, EpisodeProgressRecord episodeProgressRecord, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: upsertEpisodeProgress");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str4 = str2;
        if ((i10 & 4) != 0) {
            str3 = "resolution=merge-duplicates";
        }
        return supabaseApi.upsertEpisodeProgress(str, str4, str3, episodeProgressRecord, dVar);
    }

    public static /* synthetic */ Object w(SupabaseApi supabaseApi, String str, String str2, String str3, SyncStateRecord syncStateRecord, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: upsertSyncState");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str4 = str2;
        if ((i10 & 4) != 0) {
            str3 = "resolution=merge-duplicates";
        }
        return supabaseApi.upsertSyncState(str, str4, str3, syncStateRecord, dVar);
    }

    public static /* synthetic */ Object x(SupabaseApi supabaseApi, String str, String str2, String str3, WatchHistoryRecord watchHistoryRecord, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: upsertWatchHistory");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str4 = str2;
        if ((i10 & 4) != 0) {
            str3 = "resolution=merge-duplicates";
        }
        return supabaseApi.upsertWatchHistory(str, str4, str3, watchHistoryRecord, dVar);
    }

    public static /* synthetic */ Object y(SupabaseApi supabaseApi, String str, String str2, String str3, WatchlistRecord watchlistRecord, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: upsertWatchlist");
        }
        if ((i10 & 2) != 0) {
            str2 = Constants.INSTANCE.getSUPABASE_ANON_KEY();
        }
        String str4 = str2;
        if ((i10 & 4) != 0) {
            str3 = "resolution=merge-duplicates";
        }
        return supabaseApi.upsertWatchlist(str, str4, str3, watchlistRecord, dVar);
    }
}
