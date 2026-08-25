package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J)\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/data/api/TmdbFindResponse;", "", "movieResults", "", "Lcom/arflix/tv/data/api/TmdbFindItem;", "tvResults", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getMovieResults", "()Ljava/util/List;", "getTvResults", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbFindResponse {
    public static final int $stable = 0;

    @SerializedName("movie_results")
    private final List<TmdbFindItem> movieResults;

    @SerializedName("tv_results")
    private final List<TmdbFindItem> tvResults;

    /* JADX WARN: Multi-variable type inference failed */
    public TmdbFindResponse() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TmdbFindResponse copy$default(TmdbFindResponse tmdbFindResponse, List list, List list2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = tmdbFindResponse.movieResults;
        }
        if ((i10 & 2) != 0) {
            list2 = tmdbFindResponse.tvResults;
        }
        return tmdbFindResponse.copy(list, list2);
    }

    public final List<TmdbFindItem> component1() {
        return this.movieResults;
    }

    public final List<TmdbFindItem> component2() {
        return this.tvResults;
    }

    public final TmdbFindResponse copy(List<TmdbFindItem> movieResults, List<TmdbFindItem> tvResults) {
        return new TmdbFindResponse(movieResults, tvResults);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbFindResponse)) {
            return false;
        }
        TmdbFindResponse tmdbFindResponse = (TmdbFindResponse) other;
        return p.a(this.movieResults, tmdbFindResponse.movieResults) && p.a(this.tvResults, tmdbFindResponse.tvResults);
    }

    public final List<TmdbFindItem> getMovieResults() {
        return this.movieResults;
    }

    public final List<TmdbFindItem> getTvResults() {
        return this.tvResults;
    }

    public int hashCode() {
        return this.tvResults.hashCode() + (this.movieResults.hashCode() * 31);
    }

    public String toString() {
        return "TmdbFindResponse(movieResults=" + this.movieResults + ", tvResults=" + this.tvResults + ")";
    }

    public TmdbFindResponse(List<TmdbFindItem> list, List<TmdbFindItem> list2) {
        this.movieResults = list;
        this.tvResults = list2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TmdbFindResponse(List list, List list2, int i10, kotlin.jvm.internal.h hVar) {
        int i11 = i10 & 1;
        z zVar = z.f19728i;
        this(i11 != 0 ? zVar : list, (i10 & 2) != 0 ? zVar : list2);
    }
}
