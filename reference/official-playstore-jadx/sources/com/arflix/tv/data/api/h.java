package com.arflix.tv.data.api;

import com.arflix.tv.worker.TraktSyncWorker;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class h {
    public static /* synthetic */ Object A(TraktApi traktApi, String str, String str2, String str3, String str4, String str5, String str6, int i10, int i11, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getUserListItems");
        }
        if ((i12 & 2) != 0) {
            str2 = "2";
        }
        return traktApi.getUserListItems(str, str2, str3, str4, str5, (i12 & 32) != 0 ? TraktSyncWorker.SYNC_MODE_FULL : str6, (i12 & 64) != 0 ? 1 : i10, (i12 & 128) != 0 ? 100 : i11, dVar);
    }

    public static /* synthetic */ Object B(TraktApi traktApi, String str, String str2, String str3, String str4, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getUserListSummary");
        }
        if ((i10 & 2) != 0) {
            str2 = "2";
        }
        return traktApi.getUserListSummary(str, str2, str3, str4, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object C(TraktApi traktApi, String str, String str2, String str3, Integer num, Integer num2, String str4, d7.d dVar, int i10, Object obj) {
        String str5;
        TraktApi traktApi2;
        String str6;
        String str7;
        d7.d dVar2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWatchedMovies");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        String str8 = str3;
        Integer num3 = (i10 & 8) != 0 ? null : num;
        Integer num4 = (i10 & 16) != 0 ? null : num2;
        if ((i10 & 32) != 0) {
            str5 = null;
            str6 = str;
            str7 = str2;
            dVar2 = dVar;
            traktApi2 = traktApi;
        } else {
            str5 = str4;
            traktApi2 = traktApi;
            str6 = str;
            str7 = str2;
            dVar2 = dVar;
        }
        return traktApi2.getWatchedMovies(str6, str7, str8, num3, num4, str5, dVar2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object D(TraktApi traktApi, String str, String str2, String str3, Integer num, Integer num2, String str4, d7.d dVar, int i10, Object obj) {
        String str5;
        TraktApi traktApi2;
        String str6;
        String str7;
        d7.d dVar2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWatchedShows");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        String str8 = str3;
        Integer num3 = (i10 & 8) != 0 ? null : num;
        Integer num4 = (i10 & 16) != 0 ? null : num2;
        if ((i10 & 32) != 0) {
            str5 = null;
            str6 = str;
            str7 = str2;
            dVar2 = dVar;
            traktApi2 = traktApi;
        } else {
            str5 = str4;
            traktApi2 = traktApi;
            str6 = str;
            str7 = str2;
            dVar2 = dVar;
        }
        return traktApi2.getWatchedShows(str6, str7, str8, num3, num4, str5, dVar2);
    }

    public static /* synthetic */ Object E(TraktApi traktApi, String str, String str2, String str3, String str4, String str5, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWatchlist");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        String str6 = str3;
        if ((i10 & 8) != 0) {
            str4 = null;
        }
        String str7 = str4;
        if ((i10 & 16) != 0) {
            str5 = TraktSyncWorker.SYNC_MODE_FULL;
        }
        return traktApi.getWatchlist(str, str2, str6, str7, str5, dVar);
    }

    public static /* synthetic */ Object F(TraktApi traktApi, String str, String str2, String str3, String str4, String str5, int i10, int i11, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWatchlistAddedPage");
        }
        if ((i12 & 4) != 0) {
            str3 = "2";
        }
        String str6 = str3;
        if ((i12 & 16) != 0) {
            str5 = TraktSyncWorker.SYNC_MODE_FULL;
        }
        return traktApi.getWatchlistAddedPage(str, str2, str6, str4, str5, i10, i11, dVar);
    }

    public static /* synthetic */ Object G(TraktApi traktApi, String str, String str2, String str3, String str4, String str5, int i10, int i11, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWatchlistPage");
        }
        if ((i12 & 4) != 0) {
            str3 = "2";
        }
        String str6 = str3;
        if ((i12 & 8) != 0) {
            str4 = null;
        }
        String str7 = str4;
        if ((i12 & 16) != 0) {
            str5 = TraktSyncWorker.SYNC_MODE_FULL;
        }
        return traktApi.getWatchlistPage(str, str2, str6, str7, str5, i10, i11, dVar);
    }

    public static /* synthetic */ Object H(TraktApi traktApi, String str, String str2, String str3, TraktCollectionBody traktCollectionBody, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeFromCollection");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.removeFromCollection(str, str2, str3, traktCollectionBody, dVar);
    }

    public static /* synthetic */ Object I(TraktApi traktApi, String str, String str2, String str3, TraktHistoryBody traktHistoryBody, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeFromHistory");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.removeFromHistory(str, str2, str3, traktHistoryBody, dVar);
    }

    public static /* synthetic */ Object J(TraktApi traktApi, String str, String str2, String str3, TraktHistoryRemoveBody traktHistoryRemoveBody, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeFromHistoryByIds");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.removeFromHistoryByIds(str, str2, str3, traktHistoryRemoveBody, dVar);
    }

    public static /* synthetic */ Object K(TraktApi traktApi, String str, String str2, String str3, TraktWatchlistBody traktWatchlistBody, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeFromWatchlist");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.removeFromWatchlist(str, str2, str3, traktWatchlistBody, dVar);
    }

    public static /* synthetic */ Object L(TraktApi traktApi, String str, String str2, String str3, long j10, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removePlaybackItem");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.removePlaybackItem(str, str2, str3, j10, dVar);
    }

    public static /* synthetic */ Object M(TraktApi traktApi, String str, String str2, String str3, TraktRatingBody traktRatingBody, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeRating");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.removeRating(str, str2, str3, traktRatingBody, dVar);
    }

    public static /* synthetic */ Object N(TraktApi traktApi, String str, String str2, String str3, TraktScrobbleBody traktScrobbleBody, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scrobblePause");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.scrobblePause(str, str2, str3, traktScrobbleBody, dVar);
    }

    public static /* synthetic */ Object O(TraktApi traktApi, String str, String str2, String str3, TraktScrobbleBody traktScrobbleBody, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scrobbleStart");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.scrobbleStart(str, str2, str3, traktScrobbleBody, dVar);
    }

    public static /* synthetic */ Object P(TraktApi traktApi, String str, String str2, String str3, TraktScrobbleBody traktScrobbleBody, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scrobbleStop");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.scrobbleStop(str, str2, str3, traktScrobbleBody, dVar);
    }

    public static /* synthetic */ Object Q(TraktApi traktApi, String str, String str2, String str3, int i10, int i11, String str4, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: searchLists");
        }
        if ((i12 & 2) != 0) {
            str2 = "2";
        }
        String str5 = str2;
        if ((i12 & 8) != 0) {
            i10 = 1;
        }
        int i13 = i10;
        if ((i12 & 16) != 0) {
            i11 = 20;
        }
        int i14 = i11;
        if ((i12 & 32) != 0) {
            str4 = TraktSyncWorker.SYNC_MODE_FULL;
        }
        return traktApi.searchLists(str, str5, str3, i13, i14, str4, dVar);
    }

    public static /* synthetic */ Object a(TraktApi traktApi, String str, String str2, String str3, TraktRatingBody traktRatingBody, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addRating");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.addRating(str, str2, str3, traktRatingBody, dVar);
    }

    public static /* synthetic */ Object b(TraktApi traktApi, String str, String str2, String str3, TraktCollectionBody traktCollectionBody, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addToCollection");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.addToCollection(str, str2, str3, traktCollectionBody, dVar);
    }

    public static /* synthetic */ Object c(TraktApi traktApi, String str, String str2, String str3, TraktHistoryBody traktHistoryBody, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addToHistory");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.addToHistory(str, str2, str3, traktHistoryBody, dVar);
    }

    public static /* synthetic */ Object d(TraktApi traktApi, String str, String str2, String str3, TraktWatchlistBody traktWatchlistBody, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addToWatchlist");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.addToWatchlist(str, str2, str3, traktWatchlistBody, dVar);
    }

    public static /* synthetic */ Object e(TraktApi traktApi, String str, String str2, String str3, String str4, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCollectionMovies");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        String str5 = str3;
        if ((i10 & 8) != 0) {
            str4 = TraktSyncWorker.SYNC_MODE_FULL;
        }
        return traktApi.getCollectionMovies(str, str2, str5, str4, dVar);
    }

    public static /* synthetic */ Object f(TraktApi traktApi, String str, String str2, String str3, String str4, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCollectionShows");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        String str5 = str3;
        if ((i10 & 8) != 0) {
            str4 = TraktSyncWorker.SYNC_MODE_FULL;
        }
        return traktApi.getCollectionShows(str, str2, str5, str4, dVar);
    }

    public static /* synthetic */ Object g(TraktApi traktApi, String str, String str2, String str3, int i10, int i11, String str4, int i12, int i13, d7.d dVar, int i14, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getEpisodeComments");
        }
        if ((i14 & 2) != 0) {
            str2 = "2";
        }
        return traktApi.getEpisodeComments(str, str2, str3, i10, i11, (i14 & 32) != 0 ? "newest" : str4, (i14 & 64) != 0 ? 1 : i12, (i14 & 128) != 0 ? 10 : i13, dVar);
    }

    public static /* synthetic */ Object h(TraktApi traktApi, String str, String str2, String str3, String str4, int i10, Integer num, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getHiddenProgressResetShows");
        }
        if ((i11 & 4) != 0) {
            str3 = "2";
        }
        String str5 = str3;
        if ((i11 & 8) != 0) {
            str4 = "show";
        }
        String str6 = str4;
        if ((i11 & 16) != 0) {
            i10 = 100;
        }
        int i12 = i10;
        if ((i11 & 32) != 0) {
            num = null;
        }
        return traktApi.getHiddenProgressResetShows(str, str2, str5, str6, i12, num, dVar);
    }

    public static /* synthetic */ Object i(TraktApi traktApi, String str, String str2, String str3, String str4, int i10, Integer num, d7.d dVar, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getHiddenProgressShows");
        }
        if ((i11 & 4) != 0) {
            str3 = "2";
        }
        String str5 = str3;
        if ((i11 & 8) != 0) {
            str4 = "show";
        }
        String str6 = str4;
        if ((i11 & 16) != 0) {
            i10 = 100;
        }
        int i12 = i10;
        if ((i11 & 32) != 0) {
            num = null;
        }
        return traktApi.getHiddenProgressShows(str, str2, str5, str6, i12, num, dVar);
    }

    public static /* synthetic */ Object j(TraktApi traktApi, String str, String str2, String str3, int i10, int i11, String str4, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getHistoryEpisodes");
        }
        if ((i12 & 4) != 0) {
            str3 = "2";
        }
        String str5 = str3;
        if ((i12 & 8) != 0) {
            i10 = 1;
        }
        int i13 = i10;
        if ((i12 & 16) != 0) {
            i11 = 20;
        }
        int i14 = i11;
        if ((i12 & 32) != 0) {
            str4 = null;
        }
        return traktApi.getHistoryEpisodes(str, str2, str5, i13, i14, str4, dVar);
    }

    public static /* synthetic */ Object k(TraktApi traktApi, String str, String str2, String str3, int i10, int i11, String str4, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getHistoryMovies");
        }
        if ((i12 & 4) != 0) {
            str3 = "2";
        }
        String str5 = str3;
        if ((i12 & 8) != 0) {
            i10 = 1;
        }
        int i13 = i10;
        if ((i12 & 16) != 0) {
            i11 = 20;
        }
        int i14 = i11;
        if ((i12 & 32) != 0) {
            str4 = null;
        }
        return traktApi.getHistoryMovies(str, str2, str5, i13, i14, str4, dVar);
    }

    public static /* synthetic */ Object l(TraktApi traktApi, String str, String str2, String str3, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getLastActivities");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.getLastActivities(str, str2, str3, dVar);
    }

    public static /* synthetic */ Object m(TraktApi traktApi, String str, String str2, String str3, String str4, String str5, int i10, int i11, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getListItems");
        }
        if ((i12 & 2) != 0) {
            str2 = "2";
        }
        String str6 = str2;
        if ((i12 & 16) != 0) {
            str5 = TraktSyncWorker.SYNC_MODE_FULL;
        }
        return traktApi.getListItems(str, str6, str3, str4, str5, (i12 & 32) != 0 ? 1 : i10, (i12 & 64) != 0 ? 100 : i11, dVar);
    }

    public static /* synthetic */ Object n(TraktApi traktApi, String str, String str2, String str3, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getListSummary");
        }
        if ((i10 & 2) != 0) {
            str2 = "2";
        }
        return traktApi.getListSummary(str, str2, str3, dVar);
    }

    public static /* synthetic */ Object o(TraktApi traktApi, String str, String str2, String str3, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getMe");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.getMe(str, str2, str3, dVar);
    }

    public static /* synthetic */ Object p(TraktApi traktApi, String str, String str2, String str3, String str4, int i10, int i11, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getMovieComments");
        }
        if ((i12 & 2) != 0) {
            str2 = "2";
        }
        String str5 = str2;
        if ((i12 & 8) != 0) {
            str4 = "newest";
        }
        String str6 = str4;
        if ((i12 & 16) != 0) {
            i10 = 1;
        }
        int i13 = i10;
        if ((i12 & 32) != 0) {
            i11 = 10;
        }
        return traktApi.getMovieComments(str, str5, str3, str6, i13, i11, dVar);
    }

    public static /* synthetic */ Object q(TraktApi traktApi, String str, String str2, String str3, String str4, String str5, String str6, int i10, int i11, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getMyListItems");
        }
        if ((i12 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.getMyListItems(str, str2, str3, str4, str5, (i12 & 32) != 0 ? TraktSyncWorker.SYNC_MODE_FULL : str6, (i12 & 64) != 0 ? 1 : i10, (i12 & 128) != 0 ? 100 : i11, dVar);
    }

    public static /* synthetic */ Object r(TraktApi traktApi, String str, String str2, String str3, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getMyLists");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.getMyLists(str, str2, str3, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object s(TraktApi traktApi, String str, String str2, String str3, String str4, Integer num, Integer num2, d7.d dVar, int i10, Object obj) {
        Integer num3;
        TraktApi traktApi2;
        String str5;
        String str6;
        d7.d dVar2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPlaybackProgress");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        String str7 = str3;
        String str8 = (i10 & 8) != 0 ? null : str4;
        Integer num4 = (i10 & 16) != 0 ? null : num;
        if ((i10 & 32) != 0) {
            num3 = null;
            str5 = str;
            str6 = str2;
            dVar2 = dVar;
            traktApi2 = traktApi;
        } else {
            num3 = num2;
            traktApi2 = traktApi;
            str5 = str;
            str6 = str2;
            dVar2 = dVar;
        }
        return traktApi2.getPlaybackProgress(str5, str6, str7, str8, num4, num3, dVar2);
    }

    public static /* synthetic */ Object t(TraktApi traktApi, String str, String str2, String str3, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getRatingsEpisodes");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.getRatingsEpisodes(str, str2, str3, dVar);
    }

    public static /* synthetic */ Object u(TraktApi traktApi, String str, String str2, String str3, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getRatingsMovies");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.getRatingsMovies(str, str2, str3, dVar);
    }

    public static /* synthetic */ Object v(TraktApi traktApi, String str, String str2, String str3, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getRatingsShows");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        return traktApi.getRatingsShows(str, str2, str3, dVar);
    }

    public static /* synthetic */ Object w(TraktApi traktApi, String str, String str2, String str3, int i10, String str4, int i11, int i12, d7.d dVar, int i13, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getSeasonComments");
        }
        if ((i13 & 2) != 0) {
            str2 = "2";
        }
        String str5 = str2;
        if ((i13 & 16) != 0) {
            str4 = "newest";
        }
        return traktApi.getSeasonComments(str, str5, str3, i10, str4, (i13 & 32) != 0 ? 1 : i11, (i13 & 64) != 0 ? 10 : i12, dVar);
    }

    public static /* synthetic */ Object x(TraktApi traktApi, String str, String str2, String str3, String str4, int i10, int i11, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getShowComments");
        }
        if ((i12 & 2) != 0) {
            str2 = "2";
        }
        String str5 = str2;
        if ((i12 & 8) != 0) {
            str4 = "newest";
        }
        String str6 = str4;
        if ((i12 & 16) != 0) {
            i10 = 1;
        }
        int i13 = i10;
        if ((i12 & 32) != 0) {
            i11 = 10;
        }
        return traktApi.getShowComments(str, str5, str3, str6, i13, i11, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object y(TraktApi traktApi, String str, String str2, String str3, String str4, String str5, String str6, String str7, d7.d dVar, int i10, Object obj) {
        String str8;
        TraktApi traktApi2;
        String str9;
        String str10;
        String str11;
        d7.d dVar2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getShowProgress");
        }
        if ((i10 & 4) != 0) {
            str3 = "2";
        }
        String str12 = str3;
        String str13 = (i10 & 16) != 0 ? "false" : str5;
        String str14 = (i10 & 32) != 0 ? "false" : str6;
        if ((i10 & 64) != 0) {
            str8 = "false";
            str9 = str;
            str10 = str2;
            str11 = str4;
            dVar2 = dVar;
            traktApi2 = traktApi;
        } else {
            str8 = str7;
            traktApi2 = traktApi;
            str9 = str;
            str10 = str2;
            str11 = str4;
            dVar2 = dVar;
        }
        return traktApi2.getShowProgress(str9, str10, str12, str11, str13, str14, str8, dVar2);
    }

    public static /* synthetic */ Object z(TraktApi traktApi, String str, String str2, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTrendingAnime");
        }
        if ((i10 & 2) != 0) {
            str2 = "2";
        }
        return traktApi.getTrendingAnime(str, str2, dVar);
    }
}
