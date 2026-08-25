package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0089\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0012\u0010\u0013J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0005HÆ\u0003J¥\u0001\u00101\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00105\u001a\u000206HÖ\u0081\u0004J\n\u00107\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017¨\u00068"}, d2 = {"Lcom/arflix/tv/data/api/TraktLastActivities;", "", TtmlNode.COMBINE_ALL, "", "movies", "Lcom/arflix/tv/data/api/TraktActivityTimestamps;", "episodes", "shows", "Lcom/arflix/tv/data/api/TraktShowActivityTimestamps;", "seasons", "comments", "lists", "watchlist", "favorites", "recommendations", "collaborations", "account", "savedFilters", "<init>", "(Ljava/lang/String;Lcom/arflix/tv/data/api/TraktActivityTimestamps;Lcom/arflix/tv/data/api/TraktActivityTimestamps;Lcom/arflix/tv/data/api/TraktShowActivityTimestamps;Lcom/arflix/tv/data/api/TraktActivityTimestamps;Lcom/arflix/tv/data/api/TraktActivityTimestamps;Lcom/arflix/tv/data/api/TraktActivityTimestamps;Lcom/arflix/tv/data/api/TraktActivityTimestamps;Lcom/arflix/tv/data/api/TraktActivityTimestamps;Lcom/arflix/tv/data/api/TraktActivityTimestamps;Lcom/arflix/tv/data/api/TraktActivityTimestamps;Lcom/arflix/tv/data/api/TraktActivityTimestamps;Lcom/arflix/tv/data/api/TraktActivityTimestamps;)V", "getAll", "()Ljava/lang/String;", "getMovies", "()Lcom/arflix/tv/data/api/TraktActivityTimestamps;", "getEpisodes", "getShows", "()Lcom/arflix/tv/data/api/TraktShowActivityTimestamps;", "getSeasons", "getComments", "getLists", "getWatchlist", "getFavorites", "getRecommendations", "getCollaborations", "getAccount", "getSavedFilters", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktLastActivities {
    public static final int $stable = 0;
    private final TraktActivityTimestamps account;
    private final String all;
    private final TraktActivityTimestamps collaborations;
    private final TraktActivityTimestamps comments;
    private final TraktActivityTimestamps episodes;
    private final TraktActivityTimestamps favorites;
    private final TraktActivityTimestamps lists;
    private final TraktActivityTimestamps movies;
    private final TraktActivityTimestamps recommendations;

    @SerializedName("saved_filters")
    private final TraktActivityTimestamps savedFilters;
    private final TraktActivityTimestamps seasons;
    private final TraktShowActivityTimestamps shows;
    private final TraktActivityTimestamps watchlist;

    public TraktLastActivities(String str, TraktActivityTimestamps traktActivityTimestamps, TraktActivityTimestamps traktActivityTimestamps2, TraktShowActivityTimestamps traktShowActivityTimestamps, TraktActivityTimestamps traktActivityTimestamps3, TraktActivityTimestamps traktActivityTimestamps4, TraktActivityTimestamps traktActivityTimestamps5, TraktActivityTimestamps traktActivityTimestamps6, TraktActivityTimestamps traktActivityTimestamps7, TraktActivityTimestamps traktActivityTimestamps8, TraktActivityTimestamps traktActivityTimestamps9, TraktActivityTimestamps traktActivityTimestamps10, TraktActivityTimestamps traktActivityTimestamps11) {
        this.all = str;
        this.movies = traktActivityTimestamps;
        this.episodes = traktActivityTimestamps2;
        this.shows = traktShowActivityTimestamps;
        this.seasons = traktActivityTimestamps3;
        this.comments = traktActivityTimestamps4;
        this.lists = traktActivityTimestamps5;
        this.watchlist = traktActivityTimestamps6;
        this.favorites = traktActivityTimestamps7;
        this.recommendations = traktActivityTimestamps8;
        this.collaborations = traktActivityTimestamps9;
        this.account = traktActivityTimestamps10;
        this.savedFilters = traktActivityTimestamps11;
    }

    public static /* synthetic */ TraktLastActivities copy$default(TraktLastActivities traktLastActivities, String str, TraktActivityTimestamps traktActivityTimestamps, TraktActivityTimestamps traktActivityTimestamps2, TraktShowActivityTimestamps traktShowActivityTimestamps, TraktActivityTimestamps traktActivityTimestamps3, TraktActivityTimestamps traktActivityTimestamps4, TraktActivityTimestamps traktActivityTimestamps5, TraktActivityTimestamps traktActivityTimestamps6, TraktActivityTimestamps traktActivityTimestamps7, TraktActivityTimestamps traktActivityTimestamps8, TraktActivityTimestamps traktActivityTimestamps9, TraktActivityTimestamps traktActivityTimestamps10, TraktActivityTimestamps traktActivityTimestamps11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = traktLastActivities.all;
        }
        return traktLastActivities.copy(str, (i10 & 2) != 0 ? traktLastActivities.movies : traktActivityTimestamps, (i10 & 4) != 0 ? traktLastActivities.episodes : traktActivityTimestamps2, (i10 & 8) != 0 ? traktLastActivities.shows : traktShowActivityTimestamps, (i10 & 16) != 0 ? traktLastActivities.seasons : traktActivityTimestamps3, (i10 & 32) != 0 ? traktLastActivities.comments : traktActivityTimestamps4, (i10 & 64) != 0 ? traktLastActivities.lists : traktActivityTimestamps5, (i10 & 128) != 0 ? traktLastActivities.watchlist : traktActivityTimestamps6, (i10 & 256) != 0 ? traktLastActivities.favorites : traktActivityTimestamps7, (i10 & 512) != 0 ? traktLastActivities.recommendations : traktActivityTimestamps8, (i10 & 1024) != 0 ? traktLastActivities.collaborations : traktActivityTimestamps9, (i10 & 2048) != 0 ? traktLastActivities.account : traktActivityTimestamps10, (i10 & 4096) != 0 ? traktLastActivities.savedFilters : traktActivityTimestamps11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAll() {
        return this.all;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final TraktActivityTimestamps getRecommendations() {
        return this.recommendations;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final TraktActivityTimestamps getCollaborations() {
        return this.collaborations;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final TraktActivityTimestamps getAccount() {
        return this.account;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final TraktActivityTimestamps getSavedFilters() {
        return this.savedFilters;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final TraktActivityTimestamps getMovies() {
        return this.movies;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TraktActivityTimestamps getEpisodes() {
        return this.episodes;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final TraktShowActivityTimestamps getShows() {
        return this.shows;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final TraktActivityTimestamps getSeasons() {
        return this.seasons;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final TraktActivityTimestamps getComments() {
        return this.comments;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final TraktActivityTimestamps getLists() {
        return this.lists;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final TraktActivityTimestamps getWatchlist() {
        return this.watchlist;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final TraktActivityTimestamps getFavorites() {
        return this.favorites;
    }

    public final TraktLastActivities copy(String all, TraktActivityTimestamps movies, TraktActivityTimestamps episodes, TraktShowActivityTimestamps shows, TraktActivityTimestamps seasons, TraktActivityTimestamps comments, TraktActivityTimestamps lists, TraktActivityTimestamps watchlist, TraktActivityTimestamps favorites, TraktActivityTimestamps recommendations, TraktActivityTimestamps collaborations, TraktActivityTimestamps account, TraktActivityTimestamps savedFilters) {
        return new TraktLastActivities(all, movies, episodes, shows, seasons, comments, lists, watchlist, favorites, recommendations, collaborations, account, savedFilters);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktLastActivities)) {
            return false;
        }
        TraktLastActivities traktLastActivities = (TraktLastActivities) other;
        return p.a(this.all, traktLastActivities.all) && p.a(this.movies, traktLastActivities.movies) && p.a(this.episodes, traktLastActivities.episodes) && p.a(this.shows, traktLastActivities.shows) && p.a(this.seasons, traktLastActivities.seasons) && p.a(this.comments, traktLastActivities.comments) && p.a(this.lists, traktLastActivities.lists) && p.a(this.watchlist, traktLastActivities.watchlist) && p.a(this.favorites, traktLastActivities.favorites) && p.a(this.recommendations, traktLastActivities.recommendations) && p.a(this.collaborations, traktLastActivities.collaborations) && p.a(this.account, traktLastActivities.account) && p.a(this.savedFilters, traktLastActivities.savedFilters);
    }

    public final TraktActivityTimestamps getAccount() {
        return this.account;
    }

    public final String getAll() {
        return this.all;
    }

    public final TraktActivityTimestamps getCollaborations() {
        return this.collaborations;
    }

    public final TraktActivityTimestamps getComments() {
        return this.comments;
    }

    public final TraktActivityTimestamps getEpisodes() {
        return this.episodes;
    }

    public final TraktActivityTimestamps getFavorites() {
        return this.favorites;
    }

    public final TraktActivityTimestamps getLists() {
        return this.lists;
    }

    public final TraktActivityTimestamps getMovies() {
        return this.movies;
    }

    public final TraktActivityTimestamps getRecommendations() {
        return this.recommendations;
    }

    public final TraktActivityTimestamps getSavedFilters() {
        return this.savedFilters;
    }

    public final TraktActivityTimestamps getSeasons() {
        return this.seasons;
    }

    public final TraktShowActivityTimestamps getShows() {
        return this.shows;
    }

    public final TraktActivityTimestamps getWatchlist() {
        return this.watchlist;
    }

    public int hashCode() {
        String str = this.all;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        TraktActivityTimestamps traktActivityTimestamps = this.movies;
        int iHashCode2 = (iHashCode + (traktActivityTimestamps == null ? 0 : traktActivityTimestamps.hashCode())) * 31;
        TraktActivityTimestamps traktActivityTimestamps2 = this.episodes;
        int iHashCode3 = (iHashCode2 + (traktActivityTimestamps2 == null ? 0 : traktActivityTimestamps2.hashCode())) * 31;
        TraktShowActivityTimestamps traktShowActivityTimestamps = this.shows;
        int iHashCode4 = (iHashCode3 + (traktShowActivityTimestamps == null ? 0 : traktShowActivityTimestamps.hashCode())) * 31;
        TraktActivityTimestamps traktActivityTimestamps3 = this.seasons;
        int iHashCode5 = (iHashCode4 + (traktActivityTimestamps3 == null ? 0 : traktActivityTimestamps3.hashCode())) * 31;
        TraktActivityTimestamps traktActivityTimestamps4 = this.comments;
        int iHashCode6 = (iHashCode5 + (traktActivityTimestamps4 == null ? 0 : traktActivityTimestamps4.hashCode())) * 31;
        TraktActivityTimestamps traktActivityTimestamps5 = this.lists;
        int iHashCode7 = (iHashCode6 + (traktActivityTimestamps5 == null ? 0 : traktActivityTimestamps5.hashCode())) * 31;
        TraktActivityTimestamps traktActivityTimestamps6 = this.watchlist;
        int iHashCode8 = (iHashCode7 + (traktActivityTimestamps6 == null ? 0 : traktActivityTimestamps6.hashCode())) * 31;
        TraktActivityTimestamps traktActivityTimestamps7 = this.favorites;
        int iHashCode9 = (iHashCode8 + (traktActivityTimestamps7 == null ? 0 : traktActivityTimestamps7.hashCode())) * 31;
        TraktActivityTimestamps traktActivityTimestamps8 = this.recommendations;
        int iHashCode10 = (iHashCode9 + (traktActivityTimestamps8 == null ? 0 : traktActivityTimestamps8.hashCode())) * 31;
        TraktActivityTimestamps traktActivityTimestamps9 = this.collaborations;
        int iHashCode11 = (iHashCode10 + (traktActivityTimestamps9 == null ? 0 : traktActivityTimestamps9.hashCode())) * 31;
        TraktActivityTimestamps traktActivityTimestamps10 = this.account;
        int iHashCode12 = (iHashCode11 + (traktActivityTimestamps10 == null ? 0 : traktActivityTimestamps10.hashCode())) * 31;
        TraktActivityTimestamps traktActivityTimestamps11 = this.savedFilters;
        return iHashCode12 + (traktActivityTimestamps11 != null ? traktActivityTimestamps11.hashCode() : 0);
    }

    public String toString() {
        return "TraktLastActivities(all=" + this.all + ", movies=" + this.movies + ", episodes=" + this.episodes + ", shows=" + this.shows + ", seasons=" + this.seasons + ", comments=" + this.comments + ", lists=" + this.lists + ", watchlist=" + this.watchlist + ", favorites=" + this.favorites + ", recommendations=" + this.recommendations + ", collaborations=" + this.collaborations + ", account=" + this.account + ", savedFilters=" + this.savedFilters + ")";
    }
}
