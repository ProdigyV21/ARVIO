package com.arflix.tv.data.api;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003HÆ\u0003J?\u0010\u0012\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/api/TraktRatingBody;", "", "movies", "", "Lcom/arflix/tv/data/api/TraktRatingMovieItem;", "shows", "Lcom/arflix/tv/data/api/TraktRatingShowItem;", "episodes", "Lcom/arflix/tv/data/api/TraktRatingEpisodeItem;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getMovies", "()Ljava/util/List;", "getShows", "getEpisodes", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktRatingBody {
    public static final int $stable = 0;
    private final List<TraktRatingEpisodeItem> episodes;
    private final List<TraktRatingMovieItem> movies;
    private final List<TraktRatingShowItem> shows;

    public TraktRatingBody() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TraktRatingBody copy$default(TraktRatingBody traktRatingBody, List list, List list2, List list3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = traktRatingBody.movies;
        }
        if ((i10 & 2) != 0) {
            list2 = traktRatingBody.shows;
        }
        if ((i10 & 4) != 0) {
            list3 = traktRatingBody.episodes;
        }
        return traktRatingBody.copy(list, list2, list3);
    }

    public final List<TraktRatingMovieItem> component1() {
        return this.movies;
    }

    public final List<TraktRatingShowItem> component2() {
        return this.shows;
    }

    public final List<TraktRatingEpisodeItem> component3() {
        return this.episodes;
    }

    public final TraktRatingBody copy(List<TraktRatingMovieItem> movies, List<TraktRatingShowItem> shows, List<TraktRatingEpisodeItem> episodes) {
        return new TraktRatingBody(movies, shows, episodes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktRatingBody)) {
            return false;
        }
        TraktRatingBody traktRatingBody = (TraktRatingBody) other;
        return p.a(this.movies, traktRatingBody.movies) && p.a(this.shows, traktRatingBody.shows) && p.a(this.episodes, traktRatingBody.episodes);
    }

    public final List<TraktRatingEpisodeItem> getEpisodes() {
        return this.episodes;
    }

    public final List<TraktRatingMovieItem> getMovies() {
        return this.movies;
    }

    public final List<TraktRatingShowItem> getShows() {
        return this.shows;
    }

    public int hashCode() {
        List<TraktRatingMovieItem> list = this.movies;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<TraktRatingShowItem> list2 = this.shows;
        int iHashCode2 = (iHashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<TraktRatingEpisodeItem> list3 = this.episodes;
        return iHashCode2 + (list3 != null ? list3.hashCode() : 0);
    }

    public String toString() {
        List<TraktRatingMovieItem> list = this.movies;
        List<TraktRatingShowItem> list2 = this.shows;
        List<TraktRatingEpisodeItem> list3 = this.episodes;
        StringBuilder sb2 = new StringBuilder("TraktRatingBody(movies=");
        sb2.append(list);
        sb2.append(", shows=");
        sb2.append(list2);
        sb2.append(", episodes=");
        return a0.c.q(sb2, list3, ")");
    }

    public TraktRatingBody(List<TraktRatingMovieItem> list, List<TraktRatingShowItem> list2, List<TraktRatingEpisodeItem> list3) {
        this.movies = list;
        this.shows = list2;
        this.episodes = list3;
    }

    public /* synthetic */ TraktRatingBody(List list, List list2, List list3, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : list, (i10 & 2) != 0 ? null : list2, (i10 & 4) != 0 ? null : list3);
    }
}
