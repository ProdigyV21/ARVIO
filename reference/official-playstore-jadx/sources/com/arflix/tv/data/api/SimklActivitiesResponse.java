package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J9\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/data/api/SimklActivitiesResponse;", "", TtmlNode.COMBINE_ALL, "", "movies", "Lcom/arflix/tv/data/api/SimklActivityGroup;", "shows", "anime", "<init>", "(Ljava/lang/String;Lcom/arflix/tv/data/api/SimklActivityGroup;Lcom/arflix/tv/data/api/SimklActivityGroup;Lcom/arflix/tv/data/api/SimklActivityGroup;)V", "getAll", "()Ljava/lang/String;", "getMovies", "()Lcom/arflix/tv/data/api/SimklActivityGroup;", "getShows", "getAnime", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklActivitiesResponse {
    public static final int $stable = 0;

    @SerializedName(TtmlNode.COMBINE_ALL)
    private final String all;

    @SerializedName("anime")
    private final SimklActivityGroup anime;

    @SerializedName("movies")
    private final SimklActivityGroup movies;

    @SerializedName(alternate = {"shows"}, value = "tv_shows")
    private final SimklActivityGroup shows;

    public SimklActivitiesResponse() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ SimklActivitiesResponse copy$default(SimklActivitiesResponse simklActivitiesResponse, String str, SimklActivityGroup simklActivityGroup, SimklActivityGroup simklActivityGroup2, SimklActivityGroup simklActivityGroup3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = simklActivitiesResponse.all;
        }
        if ((i10 & 2) != 0) {
            simklActivityGroup = simklActivitiesResponse.movies;
        }
        if ((i10 & 4) != 0) {
            simklActivityGroup2 = simklActivitiesResponse.shows;
        }
        if ((i10 & 8) != 0) {
            simklActivityGroup3 = simklActivitiesResponse.anime;
        }
        return simklActivitiesResponse.copy(str, simklActivityGroup, simklActivityGroup2, simklActivityGroup3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAll() {
        return this.all;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final SimklActivityGroup getMovies() {
        return this.movies;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final SimklActivityGroup getShows() {
        return this.shows;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final SimklActivityGroup getAnime() {
        return this.anime;
    }

    public final SimklActivitiesResponse copy(String all, SimklActivityGroup movies, SimklActivityGroup shows, SimklActivityGroup anime) {
        return new SimklActivitiesResponse(all, movies, shows, anime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklActivitiesResponse)) {
            return false;
        }
        SimklActivitiesResponse simklActivitiesResponse = (SimklActivitiesResponse) other;
        return p.a(this.all, simklActivitiesResponse.all) && p.a(this.movies, simklActivitiesResponse.movies) && p.a(this.shows, simklActivitiesResponse.shows) && p.a(this.anime, simklActivitiesResponse.anime);
    }

    public final String getAll() {
        return this.all;
    }

    public final SimklActivityGroup getAnime() {
        return this.anime;
    }

    public final SimklActivityGroup getMovies() {
        return this.movies;
    }

    public final SimklActivityGroup getShows() {
        return this.shows;
    }

    public int hashCode() {
        String str = this.all;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        SimklActivityGroup simklActivityGroup = this.movies;
        int iHashCode2 = (iHashCode + (simklActivityGroup == null ? 0 : simklActivityGroup.hashCode())) * 31;
        SimklActivityGroup simklActivityGroup2 = this.shows;
        int iHashCode3 = (iHashCode2 + (simklActivityGroup2 == null ? 0 : simklActivityGroup2.hashCode())) * 31;
        SimklActivityGroup simklActivityGroup3 = this.anime;
        return iHashCode3 + (simklActivityGroup3 != null ? simklActivityGroup3.hashCode() : 0);
    }

    public String toString() {
        return "SimklActivitiesResponse(all=" + this.all + ", movies=" + this.movies + ", shows=" + this.shows + ", anime=" + this.anime + ")";
    }

    public SimklActivitiesResponse(String str, SimklActivityGroup simklActivityGroup, SimklActivityGroup simklActivityGroup2, SimklActivityGroup simklActivityGroup3) {
        this.all = str;
        this.movies = simklActivityGroup;
        this.shows = simklActivityGroup2;
        this.anime = simklActivityGroup3;
    }

    public /* synthetic */ SimklActivitiesResponse(String str, SimklActivityGroup simklActivityGroup, SimklActivityGroup simklActivityGroup2, SimklActivityGroup simklActivityGroup3, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : simklActivityGroup, (i10 & 4) != 0 ? null : simklActivityGroup2, (i10 & 8) != 0 ? null : simklActivityGroup3);
    }
}
