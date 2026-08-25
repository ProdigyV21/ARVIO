package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J?\u0010\u0011\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/data/api/SimklAddToListBody;", "", "movies", "", "Lcom/arflix/tv/data/api/SimklAddToListMovie;", "shows", "Lcom/arflix/tv/data/api/SimklAddToListShow;", "anime", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getMovies", "()Ljava/util/List;", "getShows", "getAnime", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklAddToListBody {
    public static final int $stable = 0;

    @SerializedName("anime")
    private final List<SimklAddToListShow> anime;

    @SerializedName("movies")
    private final List<SimklAddToListMovie> movies;

    @SerializedName("shows")
    private final List<SimklAddToListShow> shows;

    public SimklAddToListBody() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SimklAddToListBody copy$default(SimklAddToListBody simklAddToListBody, List list, List list2, List list3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = simklAddToListBody.movies;
        }
        if ((i10 & 2) != 0) {
            list2 = simklAddToListBody.shows;
        }
        if ((i10 & 4) != 0) {
            list3 = simklAddToListBody.anime;
        }
        return simklAddToListBody.copy(list, list2, list3);
    }

    public final List<SimklAddToListMovie> component1() {
        return this.movies;
    }

    public final List<SimklAddToListShow> component2() {
        return this.shows;
    }

    public final List<SimklAddToListShow> component3() {
        return this.anime;
    }

    public final SimklAddToListBody copy(List<SimklAddToListMovie> movies, List<SimklAddToListShow> shows, List<SimklAddToListShow> anime) {
        return new SimklAddToListBody(movies, shows, anime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklAddToListBody)) {
            return false;
        }
        SimklAddToListBody simklAddToListBody = (SimklAddToListBody) other;
        return p.a(this.movies, simklAddToListBody.movies) && p.a(this.shows, simklAddToListBody.shows) && p.a(this.anime, simklAddToListBody.anime);
    }

    public final List<SimklAddToListShow> getAnime() {
        return this.anime;
    }

    public final List<SimklAddToListMovie> getMovies() {
        return this.movies;
    }

    public final List<SimklAddToListShow> getShows() {
        return this.shows;
    }

    public int hashCode() {
        List<SimklAddToListMovie> list = this.movies;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<SimklAddToListShow> list2 = this.shows;
        int iHashCode2 = (iHashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<SimklAddToListShow> list3 = this.anime;
        return iHashCode2 + (list3 != null ? list3.hashCode() : 0);
    }

    public String toString() {
        List<SimklAddToListMovie> list = this.movies;
        List<SimklAddToListShow> list2 = this.shows;
        List<SimklAddToListShow> list3 = this.anime;
        StringBuilder sb2 = new StringBuilder("SimklAddToListBody(movies=");
        sb2.append(list);
        sb2.append(", shows=");
        sb2.append(list2);
        sb2.append(", anime=");
        return a0.c.q(sb2, list3, ")");
    }

    public SimklAddToListBody(List<SimklAddToListMovie> list, List<SimklAddToListShow> list2, List<SimklAddToListShow> list3) {
        this.movies = list;
        this.shows = list2;
        this.anime = list3;
    }

    public /* synthetic */ SimklAddToListBody(List list, List list2, List list3, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : list, (i10 & 2) != 0 ? null : list2, (i10 & 4) != 0 ? null : list3);
    }
}
