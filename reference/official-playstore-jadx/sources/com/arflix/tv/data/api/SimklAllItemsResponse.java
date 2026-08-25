package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J?\u0010\u0011\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/data/api/SimklAllItemsResponse;", "", "movies", "", "Lcom/arflix/tv/data/api/SimklHistoryMovieItem;", "shows", "Lcom/arflix/tv/data/api/SimklHistoryShowItem;", "anime", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getMovies", "()Ljava/util/List;", "getShows", "getAnime", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklAllItemsResponse {
    public static final int $stable = 0;

    @SerializedName("anime")
    private final List<SimklHistoryShowItem> anime;

    @SerializedName("movies")
    private final List<SimklHistoryMovieItem> movies;

    @SerializedName("shows")
    private final List<SimklHistoryShowItem> shows;

    public SimklAllItemsResponse() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SimklAllItemsResponse copy$default(SimklAllItemsResponse simklAllItemsResponse, List list, List list2, List list3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = simklAllItemsResponse.movies;
        }
        if ((i10 & 2) != 0) {
            list2 = simklAllItemsResponse.shows;
        }
        if ((i10 & 4) != 0) {
            list3 = simklAllItemsResponse.anime;
        }
        return simklAllItemsResponse.copy(list, list2, list3);
    }

    public final List<SimklHistoryMovieItem> component1() {
        return this.movies;
    }

    public final List<SimklHistoryShowItem> component2() {
        return this.shows;
    }

    public final List<SimklHistoryShowItem> component3() {
        return this.anime;
    }

    public final SimklAllItemsResponse copy(List<SimklHistoryMovieItem> movies, List<SimklHistoryShowItem> shows, List<SimklHistoryShowItem> anime) {
        return new SimklAllItemsResponse(movies, shows, anime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklAllItemsResponse)) {
            return false;
        }
        SimklAllItemsResponse simklAllItemsResponse = (SimklAllItemsResponse) other;
        return p.a(this.movies, simklAllItemsResponse.movies) && p.a(this.shows, simklAllItemsResponse.shows) && p.a(this.anime, simklAllItemsResponse.anime);
    }

    public final List<SimklHistoryShowItem> getAnime() {
        return this.anime;
    }

    public final List<SimklHistoryMovieItem> getMovies() {
        return this.movies;
    }

    public final List<SimklHistoryShowItem> getShows() {
        return this.shows;
    }

    public int hashCode() {
        List<SimklHistoryMovieItem> list = this.movies;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<SimklHistoryShowItem> list2 = this.shows;
        int iHashCode2 = (iHashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<SimklHistoryShowItem> list3 = this.anime;
        return iHashCode2 + (list3 != null ? list3.hashCode() : 0);
    }

    public String toString() {
        List<SimklHistoryMovieItem> list = this.movies;
        List<SimklHistoryShowItem> list2 = this.shows;
        List<SimklHistoryShowItem> list3 = this.anime;
        StringBuilder sb2 = new StringBuilder("SimklAllItemsResponse(movies=");
        sb2.append(list);
        sb2.append(", shows=");
        sb2.append(list2);
        sb2.append(", anime=");
        return a0.c.q(sb2, list3, ")");
    }

    public SimklAllItemsResponse(List<SimklHistoryMovieItem> list, List<SimklHistoryShowItem> list2, List<SimklHistoryShowItem> list3) {
        this.movies = list;
        this.shows = list2;
        this.anime = list3;
    }

    public /* synthetic */ SimklAllItemsResponse(List list, List list2, List list3, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : list, (i10 & 2) != 0 ? null : list2, (i10 & 4) != 0 ? null : list3);
    }
}
