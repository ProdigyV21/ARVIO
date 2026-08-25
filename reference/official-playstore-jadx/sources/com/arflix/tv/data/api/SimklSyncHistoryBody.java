package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001BO\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0003HÆ\u0003JQ\u0010\u0015\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u001e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001d"}, d2 = {"Lcom/arflix/tv/data/api/SimklSyncHistoryBody;", "", "movies", "", "Lcom/arflix/tv/data/api/SimklMovieRef;", "shows", "Lcom/arflix/tv/data/api/SimklShowRef;", "anime", "episodes", "Lcom/arflix/tv/data/api/SimklEpisodeRef;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getMovies", "()Ljava/util/List;", "getShows", "getAnime", "getEpisodes", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklSyncHistoryBody {
    public static final int $stable = 0;

    @SerializedName("anime")
    private final List<SimklShowRef> anime;

    @SerializedName("episodes")
    private final List<SimklEpisodeRef> episodes;

    @SerializedName("movies")
    private final List<SimklMovieRef> movies;

    @SerializedName("shows")
    private final List<SimklShowRef> shows;

    public SimklSyncHistoryBody() {
        this(null, null, null, null, 15, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SimklSyncHistoryBody copy$default(SimklSyncHistoryBody simklSyncHistoryBody, List list, List list2, List list3, List list4, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = simklSyncHistoryBody.movies;
        }
        if ((i10 & 2) != 0) {
            list2 = simklSyncHistoryBody.shows;
        }
        if ((i10 & 4) != 0) {
            list3 = simklSyncHistoryBody.anime;
        }
        if ((i10 & 8) != 0) {
            list4 = simklSyncHistoryBody.episodes;
        }
        return simklSyncHistoryBody.copy(list, list2, list3, list4);
    }

    public final List<SimklMovieRef> component1() {
        return this.movies;
    }

    public final List<SimklShowRef> component2() {
        return this.shows;
    }

    public final List<SimklShowRef> component3() {
        return this.anime;
    }

    public final List<SimklEpisodeRef> component4() {
        return this.episodes;
    }

    public final SimklSyncHistoryBody copy(List<SimklMovieRef> movies, List<SimklShowRef> shows, List<SimklShowRef> anime, List<SimklEpisodeRef> episodes) {
        return new SimklSyncHistoryBody(movies, shows, anime, episodes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklSyncHistoryBody)) {
            return false;
        }
        SimklSyncHistoryBody simklSyncHistoryBody = (SimklSyncHistoryBody) other;
        return p.a(this.movies, simklSyncHistoryBody.movies) && p.a(this.shows, simklSyncHistoryBody.shows) && p.a(this.anime, simklSyncHistoryBody.anime) && p.a(this.episodes, simklSyncHistoryBody.episodes);
    }

    public final List<SimklShowRef> getAnime() {
        return this.anime;
    }

    public final List<SimklEpisodeRef> getEpisodes() {
        return this.episodes;
    }

    public final List<SimklMovieRef> getMovies() {
        return this.movies;
    }

    public final List<SimklShowRef> getShows() {
        return this.shows;
    }

    public int hashCode() {
        List<SimklMovieRef> list = this.movies;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<SimklShowRef> list2 = this.shows;
        int iHashCode2 = (iHashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<SimklShowRef> list3 = this.anime;
        int iHashCode3 = (iHashCode2 + (list3 == null ? 0 : list3.hashCode())) * 31;
        List<SimklEpisodeRef> list4 = this.episodes;
        return iHashCode3 + (list4 != null ? list4.hashCode() : 0);
    }

    public String toString() {
        return "SimklSyncHistoryBody(movies=" + this.movies + ", shows=" + this.shows + ", anime=" + this.anime + ", episodes=" + this.episodes + ")";
    }

    public SimklSyncHistoryBody(List<SimklMovieRef> list, List<SimklShowRef> list2, List<SimklShowRef> list3, List<SimklEpisodeRef> list4) {
        this.movies = list;
        this.shows = list2;
        this.anime = list3;
        this.episodes = list4;
    }

    public /* synthetic */ SimklSyncHistoryBody(List list, List list2, List list3, List list4, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : list, (i10 & 2) != 0 ? null : list2, (i10 & 4) != 0 ? null : list3, (i10 & 8) != 0 ? null : list4);
    }
}
