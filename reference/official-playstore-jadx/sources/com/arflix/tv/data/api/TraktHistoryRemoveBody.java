package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001Ba\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0003HÆ\u0003Jc\u0010\u001a\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00032\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00032\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004J\n\u0010 \u001a\u00020!HÖ\u0081\u0004R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/api/TraktHistoryRemoveBody;", "", "ids", "", "", "movies", "Lcom/arflix/tv/data/api/TraktMovieId;", "shows", "Lcom/arflix/tv/data/api/TraktShowId;", "episodes", "Lcom/arflix/tv/data/api/TraktEpisodeId;", "seasons", "Lcom/arflix/tv/data/api/TraktSeasonId;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getIds", "()Ljava/util/List;", "getMovies", "getShows", "getEpisodes", "getSeasons", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktHistoryRemoveBody {
    public static final int $stable = 0;
    private final List<TraktEpisodeId> episodes;
    private final List<Long> ids;
    private final List<TraktMovieId> movies;
    private final List<TraktSeasonId> seasons;
    private final List<TraktShowId> shows;

    public TraktHistoryRemoveBody() {
        this(null, null, null, null, null, 31, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TraktHistoryRemoveBody copy$default(TraktHistoryRemoveBody traktHistoryRemoveBody, List list, List list2, List list3, List list4, List list5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = traktHistoryRemoveBody.ids;
        }
        if ((i10 & 2) != 0) {
            list2 = traktHistoryRemoveBody.movies;
        }
        if ((i10 & 4) != 0) {
            list3 = traktHistoryRemoveBody.shows;
        }
        if ((i10 & 8) != 0) {
            list4 = traktHistoryRemoveBody.episodes;
        }
        if ((i10 & 16) != 0) {
            list5 = traktHistoryRemoveBody.seasons;
        }
        List list6 = list5;
        List list7 = list3;
        return traktHistoryRemoveBody.copy(list, list2, list7, list4, list6);
    }

    public final List<Long> component1() {
        return this.ids;
    }

    public final List<TraktMovieId> component2() {
        return this.movies;
    }

    public final List<TraktShowId> component3() {
        return this.shows;
    }

    public final List<TraktEpisodeId> component4() {
        return this.episodes;
    }

    public final List<TraktSeasonId> component5() {
        return this.seasons;
    }

    public final TraktHistoryRemoveBody copy(List<Long> ids, List<TraktMovieId> movies, List<TraktShowId> shows, List<TraktEpisodeId> episodes, List<TraktSeasonId> seasons) {
        return new TraktHistoryRemoveBody(ids, movies, shows, episodes, seasons);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktHistoryRemoveBody)) {
            return false;
        }
        TraktHistoryRemoveBody traktHistoryRemoveBody = (TraktHistoryRemoveBody) other;
        return p.a(this.ids, traktHistoryRemoveBody.ids) && p.a(this.movies, traktHistoryRemoveBody.movies) && p.a(this.shows, traktHistoryRemoveBody.shows) && p.a(this.episodes, traktHistoryRemoveBody.episodes) && p.a(this.seasons, traktHistoryRemoveBody.seasons);
    }

    public final List<TraktEpisodeId> getEpisodes() {
        return this.episodes;
    }

    public final List<Long> getIds() {
        return this.ids;
    }

    public final List<TraktMovieId> getMovies() {
        return this.movies;
    }

    public final List<TraktSeasonId> getSeasons() {
        return this.seasons;
    }

    public final List<TraktShowId> getShows() {
        return this.shows;
    }

    public int hashCode() {
        List<Long> list = this.ids;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<TraktMovieId> list2 = this.movies;
        int iHashCode2 = (iHashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<TraktShowId> list3 = this.shows;
        int iHashCode3 = (iHashCode2 + (list3 == null ? 0 : list3.hashCode())) * 31;
        List<TraktEpisodeId> list4 = this.episodes;
        int iHashCode4 = (iHashCode3 + (list4 == null ? 0 : list4.hashCode())) * 31;
        List<TraktSeasonId> list5 = this.seasons;
        return iHashCode4 + (list5 != null ? list5.hashCode() : 0);
    }

    public String toString() {
        List<Long> list = this.ids;
        List<TraktMovieId> list2 = this.movies;
        List<TraktShowId> list3 = this.shows;
        List<TraktEpisodeId> list4 = this.episodes;
        List<TraktSeasonId> list5 = this.seasons;
        StringBuilder sb2 = new StringBuilder("TraktHistoryRemoveBody(ids=");
        sb2.append(list);
        sb2.append(", movies=");
        sb2.append(list2);
        sb2.append(", shows=");
        a2.A(sb2, list3, ", episodes=", list4, ", seasons=");
        return a0.c.q(sb2, list5, ")");
    }

    public TraktHistoryRemoveBody(List<Long> list, List<TraktMovieId> list2, List<TraktShowId> list3, List<TraktEpisodeId> list4, List<TraktSeasonId> list5) {
        this.ids = list;
        this.movies = list2;
        this.shows = list3;
        this.episodes = list4;
        this.seasons = list5;
    }

    public /* synthetic */ TraktHistoryRemoveBody(List list, List list2, List list3, List list4, List list5, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : list, (i10 & 2) != 0 ? null : list2, (i10 & 4) != 0 ? null : list3, (i10 & 8) != 0 ? null : list4, (i10 & 16) != 0 ? null : list5);
    }
}
