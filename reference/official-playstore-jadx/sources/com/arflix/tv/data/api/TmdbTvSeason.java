package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0007HÆ\u0003JW\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012¨\u0006#"}, d2 = {"Lcom/arflix/tv/data/api/TmdbTvSeason;", "", TtmlNode.ATTR_ID, "", "seasonNumber", "episodeCount", ContentDisposition.Parameters.Name, "", "overview", "posterPath", "airDate", "<init>", "(IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getSeasonNumber", "getEpisodeCount", "getName", "()Ljava/lang/String;", "getOverview", "getPosterPath", "getAirDate", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbTvSeason {
    public static final int $stable = 0;

    @SerializedName("air_date")
    private final String airDate;

    @SerializedName("episode_count")
    private final int episodeCount;
    private final int id;
    private final String name;
    private final String overview;

    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("season_number")
    private final int seasonNumber;

    public TmdbTvSeason() {
        this(0, 0, 0, null, null, null, null, 127, null);
    }

    public static /* synthetic */ TmdbTvSeason copy$default(TmdbTvSeason tmdbTvSeason, int i10, int i11, int i12, String str, String str2, String str3, String str4, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            i10 = tmdbTvSeason.id;
        }
        if ((i13 & 2) != 0) {
            i11 = tmdbTvSeason.seasonNumber;
        }
        if ((i13 & 4) != 0) {
            i12 = tmdbTvSeason.episodeCount;
        }
        if ((i13 & 8) != 0) {
            str = tmdbTvSeason.name;
        }
        if ((i13 & 16) != 0) {
            str2 = tmdbTvSeason.overview;
        }
        if ((i13 & 32) != 0) {
            str3 = tmdbTvSeason.posterPath;
        }
        if ((i13 & 64) != 0) {
            str4 = tmdbTvSeason.airDate;
        }
        String str5 = str3;
        String str6 = str4;
        String str7 = str2;
        int i14 = i12;
        return tmdbTvSeason.copy(i10, i11, i14, str, str7, str5, str6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getSeasonNumber() {
        return this.seasonNumber;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getEpisodeCount() {
        return this.episodeCount;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getOverview() {
        return this.overview;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getPosterPath() {
        return this.posterPath;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getAirDate() {
        return this.airDate;
    }

    public final TmdbTvSeason copy(int id, int seasonNumber, int episodeCount, String name, String overview, String posterPath, String airDate) {
        return new TmdbTvSeason(id, seasonNumber, episodeCount, name, overview, posterPath, airDate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbTvSeason)) {
            return false;
        }
        TmdbTvSeason tmdbTvSeason = (TmdbTvSeason) other;
        return this.id == tmdbTvSeason.id && this.seasonNumber == tmdbTvSeason.seasonNumber && this.episodeCount == tmdbTvSeason.episodeCount && p.a(this.name, tmdbTvSeason.name) && p.a(this.overview, tmdbTvSeason.overview) && p.a(this.posterPath, tmdbTvSeason.posterPath) && p.a(this.airDate, tmdbTvSeason.airDate);
    }

    public final String getAirDate() {
        return this.airDate;
    }

    public final int getEpisodeCount() {
        return this.episodeCount;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getOverview() {
        return this.overview;
    }

    public final String getPosterPath() {
        return this.posterPath;
    }

    public final int getSeasonNumber() {
        return this.seasonNumber;
    }

    public int hashCode() {
        int i10 = ((((this.id * 31) + this.seasonNumber) * 31) + this.episodeCount) * 31;
        String str = this.name;
        int iHashCode = (i10 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.overview;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.posterPath;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.airDate;
        return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        int i10 = this.id;
        int i11 = this.seasonNumber;
        int i12 = this.episodeCount;
        String str = this.name;
        String str2 = this.overview;
        String str3 = this.posterPath;
        String str4 = this.airDate;
        StringBuilder sbV = androidx.compose.foundation.c.v("TmdbTvSeason(id=", i10, ", seasonNumber=", i11, ", episodeCount=");
        sbV.append(i12);
        sbV.append(", name=");
        sbV.append(str);
        sbV.append(", overview=");
        y.a.i(sbV, str2, ", posterPath=", str3, ", airDate=");
        return a0.c.p(sbV, str4, ")");
    }

    public TmdbTvSeason(int i10, int i11, int i12, String str, String str2, String str3, String str4) {
        this.id = i10;
        this.seasonNumber = i11;
        this.episodeCount = i12;
        this.name = str;
        this.overview = str2;
        this.posterPath = str3;
        this.airDate = str4;
    }

    public /* synthetic */ TmdbTvSeason(int i10, int i11, int i12, String str, String str2, String str3, String str4, int i13, kotlin.jvm.internal.h hVar) {
        this((i13 & 1) != 0 ? 0 : i10, (i13 & 2) != 0 ? 1 : i11, (i13 & 4) != 0 ? 0 : i12, (i13 & 8) != 0 ? null : str, (i13 & 16) != 0 ? null : str2, (i13 & 32) != 0 ? null : str3, (i13 & 64) != 0 ? null : str4);
    }
}
