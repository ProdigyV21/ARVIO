package com.arflix.tv.data.api;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\bHÆ\u0003J9\u0010\u0013\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/data/api/MdbWatchedResponse;", "", "movies", "", "Lcom/arflix/tv/data/api/MdbWatchedMovieRow;", "episodes", "Lcom/arflix/tv/data/api/MdbWatchedEpisodeRow;", "pagination", "Lcom/arflix/tv/data/api/MdbPagination;", "<init>", "(Ljava/util/List;Ljava/util/List;Lcom/arflix/tv/data/api/MdbPagination;)V", "getMovies", "()Ljava/util/List;", "getEpisodes", "getPagination", "()Lcom/arflix/tv/data/api/MdbPagination;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbWatchedResponse {
    public static final int $stable = 0;
    private final List<MdbWatchedEpisodeRow> episodes;
    private final List<MdbWatchedMovieRow> movies;
    private final MdbPagination pagination;

    public MdbWatchedResponse() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MdbWatchedResponse copy$default(MdbWatchedResponse mdbWatchedResponse, List list, List list2, MdbPagination mdbPagination, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = mdbWatchedResponse.movies;
        }
        if ((i10 & 2) != 0) {
            list2 = mdbWatchedResponse.episodes;
        }
        if ((i10 & 4) != 0) {
            mdbPagination = mdbWatchedResponse.pagination;
        }
        return mdbWatchedResponse.copy(list, list2, mdbPagination);
    }

    public final List<MdbWatchedMovieRow> component1() {
        return this.movies;
    }

    public final List<MdbWatchedEpisodeRow> component2() {
        return this.episodes;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final MdbPagination getPagination() {
        return this.pagination;
    }

    public final MdbWatchedResponse copy(List<MdbWatchedMovieRow> movies, List<MdbWatchedEpisodeRow> episodes, MdbPagination pagination) {
        return new MdbWatchedResponse(movies, episodes, pagination);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbWatchedResponse)) {
            return false;
        }
        MdbWatchedResponse mdbWatchedResponse = (MdbWatchedResponse) other;
        return p.a(this.movies, mdbWatchedResponse.movies) && p.a(this.episodes, mdbWatchedResponse.episodes) && p.a(this.pagination, mdbWatchedResponse.pagination);
    }

    public final List<MdbWatchedEpisodeRow> getEpisodes() {
        return this.episodes;
    }

    public final List<MdbWatchedMovieRow> getMovies() {
        return this.movies;
    }

    public final MdbPagination getPagination() {
        return this.pagination;
    }

    public int hashCode() {
        List<MdbWatchedMovieRow> list = this.movies;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<MdbWatchedEpisodeRow> list2 = this.episodes;
        int iHashCode2 = (iHashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        MdbPagination mdbPagination = this.pagination;
        return iHashCode2 + (mdbPagination != null ? mdbPagination.hashCode() : 0);
    }

    public String toString() {
        return "MdbWatchedResponse(movies=" + this.movies + ", episodes=" + this.episodes + ", pagination=" + this.pagination + ")";
    }

    public MdbWatchedResponse(List<MdbWatchedMovieRow> list, List<MdbWatchedEpisodeRow> list2, MdbPagination mdbPagination) {
        this.movies = list;
        this.episodes = list2;
        this.pagination = mdbPagination;
    }

    public /* synthetic */ MdbWatchedResponse(List list, List list2, MdbPagination mdbPagination, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : list, (i10 & 2) != 0 ? null : list2, (i10 & 4) != 0 ? null : mdbPagination);
    }
}
