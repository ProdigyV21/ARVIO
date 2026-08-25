package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bµ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000bHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J·\u0001\u00102\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00106\u001a\u000207HÖ\u0081\u0004J\n\u00108\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0015R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015¨\u00069"}, d2 = {"Lcom/arflix/tv/data/api/StremioMetaPreview;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Type, ContentDisposition.Parameters.Name, "poster", "background", "logo", MediaTrack.ROLE_DESCRIPTION, "genres", "", "released", "releaseInfo", "year", "imdbId", "tmdbId", "moviedbId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getType", "getName", "getPoster", "getBackground", "getLogo", "getDescription", "getGenres", "()Ljava/util/List;", "getReleased", "getReleaseInfo", "getYear", "getImdbId", "getTmdbId", "getMoviedbId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StremioMetaPreview {
    public static final int $stable = 0;
    private final String background;
    private final String description;
    private final List<String> genres;
    private final String id;

    @SerializedName("imdb_id")
    private final String imdbId;
    private final String logo;

    @SerializedName("moviedb_id")
    private final String moviedbId;
    private final String name;
    private final String poster;
    private final String releaseInfo;
    private final String released;

    @SerializedName("tmdb_id")
    private final String tmdbId;
    private final String type;
    private final String year;

    public StremioMetaPreview() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getReleaseInfo() {
        return this.releaseInfo;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getYear() {
        return this.year;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getImdbId() {
        return this.imdbId;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getTmdbId() {
        return this.tmdbId;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getMoviedbId() {
        return this.moviedbId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPoster() {
        return this.poster;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getBackground() {
        return this.background;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getLogo() {
        return this.logo;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final List<String> component8() {
        return this.genres;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getReleased() {
        return this.released;
    }

    public final StremioMetaPreview copy(String id, String type, String name, String poster, String background, String logo, String description, List<String> genres, String released, String releaseInfo, String year, String imdbId, String tmdbId, String moviedbId) {
        return new StremioMetaPreview(id, type, name, poster, background, logo, description, genres, released, releaseInfo, year, imdbId, tmdbId, moviedbId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StremioMetaPreview)) {
            return false;
        }
        StremioMetaPreview stremioMetaPreview = (StremioMetaPreview) other;
        return p.a(this.id, stremioMetaPreview.id) && p.a(this.type, stremioMetaPreview.type) && p.a(this.name, stremioMetaPreview.name) && p.a(this.poster, stremioMetaPreview.poster) && p.a(this.background, stremioMetaPreview.background) && p.a(this.logo, stremioMetaPreview.logo) && p.a(this.description, stremioMetaPreview.description) && p.a(this.genres, stremioMetaPreview.genres) && p.a(this.released, stremioMetaPreview.released) && p.a(this.releaseInfo, stremioMetaPreview.releaseInfo) && p.a(this.year, stremioMetaPreview.year) && p.a(this.imdbId, stremioMetaPreview.imdbId) && p.a(this.tmdbId, stremioMetaPreview.tmdbId) && p.a(this.moviedbId, stremioMetaPreview.moviedbId);
    }

    public final String getBackground() {
        return this.background;
    }

    public final String getDescription() {
        return this.description;
    }

    public final List<String> getGenres() {
        return this.genres;
    }

    public final String getId() {
        return this.id;
    }

    public final String getImdbId() {
        return this.imdbId;
    }

    public final String getLogo() {
        return this.logo;
    }

    public final String getMoviedbId() {
        return this.moviedbId;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPoster() {
        return this.poster;
    }

    public final String getReleaseInfo() {
        return this.releaseInfo;
    }

    public final String getReleased() {
        return this.released;
    }

    public final String getTmdbId() {
        return this.tmdbId;
    }

    public final String getType() {
        return this.type;
    }

    public final String getYear() {
        return this.year;
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.type;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.name;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.poster;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.background;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.logo;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.description;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        List<String> list = this.genres;
        int iHashCode8 = (iHashCode7 + (list == null ? 0 : list.hashCode())) * 31;
        String str8 = this.released;
        int iHashCode9 = (iHashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.releaseInfo;
        int iHashCode10 = (iHashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.year;
        int iHashCode11 = (iHashCode10 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.imdbId;
        int iHashCode12 = (iHashCode11 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.tmdbId;
        int iHashCode13 = (iHashCode12 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.moviedbId;
        return iHashCode13 + (str13 != null ? str13.hashCode() : 0);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.type;
        String str3 = this.name;
        String str4 = this.poster;
        String str5 = this.background;
        String str6 = this.logo;
        String str7 = this.description;
        List<String> list = this.genres;
        String str8 = this.released;
        String str9 = this.releaseInfo;
        String str10 = this.year;
        String str11 = this.imdbId;
        String str12 = this.tmdbId;
        String str13 = this.moviedbId;
        StringBuilder sbR = a2.r("StremioMetaPreview(id=", str, ", type=", str2, ", name=");
        y.a.i(sbR, str3, ", poster=", str4, ", background=");
        y.a.i(sbR, str5, ", logo=", str6, ", description=");
        sbR.append(str7);
        sbR.append(", genres=");
        sbR.append(list);
        sbR.append(", released=");
        y.a.i(sbR, str8, ", releaseInfo=", str9, ", year=");
        y.a.i(sbR, str10, ", imdbId=", str11, ", tmdbId=");
        return a2.n(sbR, str12, ", moviedbId=", str13, ")");
    }

    public StremioMetaPreview(String str, String str2, String str3, String str4, String str5, String str6, String str7, List<String> list, String str8, String str9, String str10, String str11, String str12, String str13) {
        this.id = str;
        this.type = str2;
        this.name = str3;
        this.poster = str4;
        this.background = str5;
        this.logo = str6;
        this.description = str7;
        this.genres = list;
        this.released = str8;
        this.releaseInfo = str9;
        this.year = str10;
        this.imdbId = str11;
        this.tmdbId = str12;
        this.moviedbId = str13;
    }

    public /* synthetic */ StremioMetaPreview(String str, String str2, String str3, String str4, String str5, String str6, String str7, List list, String str8, String str9, String str10, String str11, String str12, String str13, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : str4, (i10 & 16) != 0 ? null : str5, (i10 & 32) != 0 ? null : str6, (i10 & 64) != 0 ? null : str7, (i10 & 128) != 0 ? null : list, (i10 & 256) != 0 ? null : str8, (i10 & 512) != 0 ? null : str9, (i10 & 1024) != 0 ? null : str10, (i10 & 2048) != 0 ? null : str11, (i10 & 4096) != 0 ? null : str12, (i10 & 8192) != 0 ? null : str13);
    }
}
