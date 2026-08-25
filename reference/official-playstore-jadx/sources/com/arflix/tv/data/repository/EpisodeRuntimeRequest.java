package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bi\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0017J\u0010\u0010$\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0017J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0082\u0001\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010+J\u0014\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010/\u001a\u00020\u0005HÖ\u0081\u0004J\n\u00100\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0019\u0010\u0017R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0012¨\u00061"}, d2 = {"Lcom/arflix/tv/data/repository/EpisodeRuntimeRequest;", "", "imdbId", "", "season", "", "episode", "tmdbId", "tvdbId", "genreIds", "", "originalLanguage", LinkHeader.Parameters.Title, "airDate", "animeQueryOverride", "<init>", "(Ljava/lang/String;IILjava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getImdbId", "()Ljava/lang/String;", "getSeason", "()I", "getEpisode", "getTmdbId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTvdbId", "getGenreIds", "()Ljava/util/List;", "getOriginalLanguage", "getTitle", "getAirDate", "getAnimeQueryOverride", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/String;IILjava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/repository/EpisodeRuntimeRequest;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class EpisodeRuntimeRequest {
    public static final int $stable = 0;
    private final String airDate;
    private final String animeQueryOverride;
    private final int episode;
    private final List<Integer> genreIds;
    private final String imdbId;
    private final String originalLanguage;
    private final int season;
    private final String title;
    private final Integer tmdbId;
    private final Integer tvdbId;

    public EpisodeRuntimeRequest(String str, int i10, int i11, Integer num, Integer num2, List<Integer> list, String str2, String str3, String str4, String str5) {
        this.imdbId = str;
        this.season = i10;
        this.episode = i11;
        this.tmdbId = num;
        this.tvdbId = num2;
        this.genreIds = list;
        this.originalLanguage = str2;
        this.title = str3;
        this.airDate = str4;
        this.animeQueryOverride = str5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EpisodeRuntimeRequest copy$default(EpisodeRuntimeRequest episodeRuntimeRequest, String str, int i10, int i11, Integer num, Integer num2, List list, String str2, String str3, String str4, String str5, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            str = episodeRuntimeRequest.imdbId;
        }
        if ((i12 & 2) != 0) {
            i10 = episodeRuntimeRequest.season;
        }
        if ((i12 & 4) != 0) {
            i11 = episodeRuntimeRequest.episode;
        }
        if ((i12 & 8) != 0) {
            num = episodeRuntimeRequest.tmdbId;
        }
        if ((i12 & 16) != 0) {
            num2 = episodeRuntimeRequest.tvdbId;
        }
        if ((i12 & 32) != 0) {
            list = episodeRuntimeRequest.genreIds;
        }
        if ((i12 & 64) != 0) {
            str2 = episodeRuntimeRequest.originalLanguage;
        }
        if ((i12 & 128) != 0) {
            str3 = episodeRuntimeRequest.title;
        }
        if ((i12 & 256) != 0) {
            str4 = episodeRuntimeRequest.airDate;
        }
        if ((i12 & 512) != 0) {
            str5 = episodeRuntimeRequest.animeQueryOverride;
        }
        String str6 = str4;
        String str7 = str5;
        String str8 = str2;
        String str9 = str3;
        Integer num3 = num2;
        List list2 = list;
        return episodeRuntimeRequest.copy(str, i10, i11, num, num3, list2, str8, str9, str6, str7);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getImdbId() {
        return this.imdbId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getAnimeQueryOverride() {
        return this.animeQueryOverride;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getTmdbId() {
        return this.tmdbId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getTvdbId() {
        return this.tvdbId;
    }

    public final List<Integer> component6() {
        return this.genreIds;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getOriginalLanguage() {
        return this.originalLanguage;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getAirDate() {
        return this.airDate;
    }

    public final EpisodeRuntimeRequest copy(String imdbId, int season, int episode, Integer tmdbId, Integer tvdbId, List<Integer> genreIds, String originalLanguage, String title, String airDate, String animeQueryOverride) {
        return new EpisodeRuntimeRequest(imdbId, season, episode, tmdbId, tvdbId, genreIds, originalLanguage, title, airDate, animeQueryOverride);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EpisodeRuntimeRequest)) {
            return false;
        }
        EpisodeRuntimeRequest episodeRuntimeRequest = (EpisodeRuntimeRequest) other;
        return kotlin.jvm.internal.p.a(this.imdbId, episodeRuntimeRequest.imdbId) && this.season == episodeRuntimeRequest.season && this.episode == episodeRuntimeRequest.episode && kotlin.jvm.internal.p.a(this.tmdbId, episodeRuntimeRequest.tmdbId) && kotlin.jvm.internal.p.a(this.tvdbId, episodeRuntimeRequest.tvdbId) && kotlin.jvm.internal.p.a(this.genreIds, episodeRuntimeRequest.genreIds) && kotlin.jvm.internal.p.a(this.originalLanguage, episodeRuntimeRequest.originalLanguage) && kotlin.jvm.internal.p.a(this.title, episodeRuntimeRequest.title) && kotlin.jvm.internal.p.a(this.airDate, episodeRuntimeRequest.airDate) && kotlin.jvm.internal.p.a(this.animeQueryOverride, episodeRuntimeRequest.animeQueryOverride);
    }

    public final String getAirDate() {
        return this.airDate;
    }

    public final String getAnimeQueryOverride() {
        return this.animeQueryOverride;
    }

    public final int getEpisode() {
        return this.episode;
    }

    public final List<Integer> getGenreIds() {
        return this.genreIds;
    }

    public final String getImdbId() {
        return this.imdbId;
    }

    public final String getOriginalLanguage() {
        return this.originalLanguage;
    }

    public final int getSeason() {
        return this.season;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Integer getTmdbId() {
        return this.tmdbId;
    }

    public final Integer getTvdbId() {
        return this.tvdbId;
    }

    public int hashCode() {
        int iHashCode = ((((this.imdbId.hashCode() * 31) + this.season) * 31) + this.episode) * 31;
        Integer num = this.tmdbId;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.tvdbId;
        int iE = androidx.compose.material3.d.e(this.genreIds, (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31, 31);
        String str = this.originalLanguage;
        int iC = androidx.compose.foundation.c.c((iE + (str == null ? 0 : str.hashCode())) * 31, 31, this.title);
        String str2 = this.airDate;
        int iHashCode3 = (iC + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.animeQueryOverride;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        String str = this.imdbId;
        int i10 = this.season;
        int i11 = this.episode;
        Integer num = this.tmdbId;
        Integer num2 = this.tvdbId;
        List<Integer> list = this.genreIds;
        String str2 = this.originalLanguage;
        String str3 = this.title;
        String str4 = this.airDate;
        String str5 = this.animeQueryOverride;
        StringBuilder sbQ = a2.q("EpisodeRuntimeRequest(imdbId=", str, ", season=", i10, ", episode=");
        sbQ.append(i11);
        sbQ.append(", tmdbId=");
        sbQ.append(num);
        sbQ.append(", tvdbId=");
        sbQ.append(num2);
        sbQ.append(", genreIds=");
        sbQ.append(list);
        sbQ.append(", originalLanguage=");
        y.a.i(sbQ, str2, ", title=", str3, ", airDate=");
        return a2.n(sbQ, str4, ", animeQueryOverride=", str5, ")");
    }

    public /* synthetic */ EpisodeRuntimeRequest(String str, int i10, int i11, Integer num, Integer num2, List list, String str2, String str3, String str4, String str5, int i12, kotlin.jvm.internal.h hVar) {
        this(str, i10, i11, num, num2, list, str2, str3, str4, (i12 & 512) != 0 ? null : str5);
    }
}
