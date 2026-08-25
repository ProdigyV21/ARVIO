package com.arflix.tv.data.api;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class g {
    public static /* synthetic */ Object a(TmdbApi tmdbApi, String str, String str2, String str3, String str4, Integer num, String str5, String str6, Integer num2, String str7, String str8, Integer num3, String str9, String str10, int i10, d7.d dVar, int i11, Object obj) {
        if (obj == null) {
            return tmdbApi.discoverMovies(str, (i11 & 2) != 0 ? null : str2, (i11 & 4) != 0 ? null : str3, (i11 & 8) != 0 ? "popularity.desc" : str4, (i11 & 16) != 0 ? null : num, (i11 & 32) != 0 ? null : str5, (i11 & 64) != 0 ? null : str6, (i11 & 128) != 0 ? null : num2, (i11 & 256) != 0 ? null : str7, (i11 & 512) != 0 ? null : str8, (i11 & 1024) != 0 ? null : num3, (i11 & 2048) != 0 ? null : str9, (i11 & 4096) != 0 ? null : str10, (i11 & 8192) != 0 ? 1 : i10, dVar);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: discoverMovies");
    }

    public static /* synthetic */ Object b(TmdbApi tmdbApi, String str, Integer num, String str2, String str3, String str4, String str5, String str6, Integer num2, Integer num3, String str7, String str8, String str9, String str10, int i10, d7.d dVar, int i11, Object obj) {
        if (obj == null) {
            return tmdbApi.discoverTv(str, (i11 & 2) != 0 ? null : num, (i11 & 4) != 0 ? "US" : str2, (i11 & 8) != 0 ? "popularity.desc" : str3, (i11 & 16) != 0 ? null : str4, (i11 & 32) != 0 ? null : str5, (i11 & 64) != 0 ? null : str6, (i11 & 128) != 0 ? null : num2, (i11 & 256) != 0 ? null : num3, (i11 & 512) != 0 ? null : str7, (i11 & 1024) != 0 ? null : str8, (i11 & 2048) != 0 ? null : str9, (i11 & 4096) != 0 ? null : str10, (i11 & 8192) != 0 ? 1 : i10, dVar);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: discoverTv");
    }

    public static /* synthetic */ Object c(TmdbApi tmdbApi, String str, String str2, String str3, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: findByExternalId");
        }
        if ((i10 & 4) != 0) {
            str3 = "imdb_id";
        }
        return tmdbApi.findByExternalId(str, str2, str3, dVar);
    }

    public static /* synthetic */ Object d(TmdbApi tmdbApi, String str, int i10, String str2, String str3, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCredits");
        }
        if ((i11 & 8) != 0) {
            str3 = null;
        }
        return tmdbApi.getCredits(str, i10, str2, str3, dVar);
    }

    public static /* synthetic */ Object e(TmdbApi tmdbApi, int i10, String str, String str2, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getMovieDetails");
        }
        if ((i11 & 4) != 0) {
            str2 = null;
        }
        return tmdbApi.getMovieDetails(i10, str, str2, dVar);
    }

    public static /* synthetic */ Object f(TmdbApi tmdbApi, int i10, String str, String str2, String str3, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPersonDetails");
        }
        if ((i11 & 4) != 0) {
            str2 = "combined_credits";
        }
        String str4 = str2;
        if ((i11 & 8) != 0) {
            str3 = null;
        }
        return tmdbApi.getPersonDetails(i10, str, str4, str3, dVar);
    }

    public static /* synthetic */ Object g(TmdbApi tmdbApi, String str, int i10, String str2, String str3, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getRecommendations");
        }
        if ((i11 & 8) != 0) {
            str3 = null;
        }
        return tmdbApi.getRecommendations(str, i10, str2, str3, dVar);
    }

    public static /* synthetic */ Object h(TmdbApi tmdbApi, String str, int i10, String str2, String str3, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getReviews");
        }
        if ((i11 & 8) != 0) {
            str3 = null;
        }
        return tmdbApi.getReviews(str, i10, str2, str3, dVar);
    }

    public static /* synthetic */ Object i(TmdbApi tmdbApi, String str, int i10, String str2, String str3, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getSimilar");
        }
        if ((i11 & 8) != 0) {
            str3 = null;
        }
        return tmdbApi.getSimilar(str, i10, str2, str3, dVar);
    }

    public static /* synthetic */ Object j(TmdbApi tmdbApi, int i10, String str, String str2, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTmdbCollection");
        }
        if ((i11 & 4) != 0) {
            str2 = null;
        }
        return tmdbApi.getTmdbCollection(i10, str, str2, dVar);
    }

    public static /* synthetic */ Object k(TmdbApi tmdbApi, String str, String str2, int i10, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTrendingMovies");
        }
        if ((i11 & 2) != 0) {
            str2 = null;
        }
        if ((i11 & 4) != 0) {
            i10 = 1;
        }
        return tmdbApi.getTrendingMovies(str, str2, i10, dVar);
    }

    public static /* synthetic */ Object l(TmdbApi tmdbApi, String str, String str2, int i10, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTrendingTv");
        }
        if ((i11 & 2) != 0) {
            str2 = null;
        }
        if ((i11 & 4) != 0) {
            i10 = 1;
        }
        return tmdbApi.getTrendingTv(str, str2, i10, dVar);
    }

    public static /* synthetic */ Object m(TmdbApi tmdbApi, int i10, String str, String str2, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTvDetails");
        }
        if ((i11 & 4) != 0) {
            str2 = null;
        }
        return tmdbApi.getTvDetails(i10, str, str2, dVar);
    }

    public static /* synthetic */ Object n(TmdbApi tmdbApi, int i10, int i11, String str, String str2, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTvSeason");
        }
        if ((i12 & 8) != 0) {
            str2 = null;
        }
        return tmdbApi.getTvSeason(i10, i11, str, str2, dVar);
    }

    public static /* synthetic */ Object o(TmdbApi tmdbApi, String str, int i10, String str2, String str3, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getVideos");
        }
        if ((i11 & 8) != 0) {
            str3 = null;
        }
        return tmdbApi.getVideos(str, i10, str2, str3, dVar);
    }

    public static /* synthetic */ Object p(TmdbApi tmdbApi, String str, String str2, String str3, int i10, Integer num, Integer num2, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: searchMovies");
        }
        if ((i11 & 4) != 0) {
            str3 = null;
        }
        if ((i11 & 8) != 0) {
            i10 = 1;
        }
        if ((i11 & 16) != 0) {
            num = null;
        }
        if ((i11 & 32) != 0) {
            num2 = null;
        }
        return tmdbApi.searchMovies(str, str2, str3, i10, num, num2, dVar);
    }

    public static /* synthetic */ Object q(TmdbApi tmdbApi, String str, String str2, String str3, int i10, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: searchMulti");
        }
        if ((i11 & 4) != 0) {
            str3 = null;
        }
        String str4 = str3;
        if ((i11 & 8) != 0) {
            i10 = 1;
        }
        return tmdbApi.searchMulti(str, str2, str4, i10, dVar);
    }

    public static /* synthetic */ Object r(TmdbApi tmdbApi, String str, String str2, String str3, int i10, Integer num, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: searchTv");
        }
        if ((i11 & 4) != 0) {
            str3 = null;
        }
        if ((i11 & 8) != 0) {
            i10 = 1;
        }
        if ((i11 & 16) != 0) {
            num = null;
        }
        return tmdbApi.searchTv(str, str2, str3, i10, num, dVar);
    }
}
