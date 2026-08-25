package com.arflix.tv.data.api;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\r\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/data/api/MdbWatchlistModifyBody;", "", "movies", "", "Lcom/arflix/tv/data/api/MdbTmdbRef;", "shows", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getMovies", "()Ljava/util/List;", "getShows", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbWatchlistModifyBody {
    public static final int $stable = 0;
    private final List<MdbTmdbRef> movies;
    private final List<MdbTmdbRef> shows;

    /* JADX WARN: Multi-variable type inference failed */
    public MdbWatchlistModifyBody() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MdbWatchlistModifyBody copy$default(MdbWatchlistModifyBody mdbWatchlistModifyBody, List list, List list2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = mdbWatchlistModifyBody.movies;
        }
        if ((i10 & 2) != 0) {
            list2 = mdbWatchlistModifyBody.shows;
        }
        return mdbWatchlistModifyBody.copy(list, list2);
    }

    public final List<MdbTmdbRef> component1() {
        return this.movies;
    }

    public final List<MdbTmdbRef> component2() {
        return this.shows;
    }

    public final MdbWatchlistModifyBody copy(List<MdbTmdbRef> movies, List<MdbTmdbRef> shows) {
        return new MdbWatchlistModifyBody(movies, shows);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbWatchlistModifyBody)) {
            return false;
        }
        MdbWatchlistModifyBody mdbWatchlistModifyBody = (MdbWatchlistModifyBody) other;
        return p.a(this.movies, mdbWatchlistModifyBody.movies) && p.a(this.shows, mdbWatchlistModifyBody.shows);
    }

    public final List<MdbTmdbRef> getMovies() {
        return this.movies;
    }

    public final List<MdbTmdbRef> getShows() {
        return this.shows;
    }

    public int hashCode() {
        List<MdbTmdbRef> list = this.movies;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<MdbTmdbRef> list2 = this.shows;
        return iHashCode + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "MdbWatchlistModifyBody(movies=" + this.movies + ", shows=" + this.shows + ")";
    }

    public MdbWatchlistModifyBody(List<MdbTmdbRef> list, List<MdbTmdbRef> list2) {
        this.movies = list;
        this.shows = list2;
    }

    public /* synthetic */ MdbWatchlistModifyBody(List list, List list2, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : list, (i10 & 2) != 0 ? null : list2);
    }
}
