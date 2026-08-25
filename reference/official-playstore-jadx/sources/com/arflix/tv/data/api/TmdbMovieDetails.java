package com.arflix.tv.data.api;

import androidx.compose.ui.layout.LayoutKt;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b0\b\u0087\b\u0018\u00002\u00020\u0001Bµ\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0005HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010;\u001a\u00020\rHÆ\u0003J\u0010\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010(J\t\u0010=\u001a\u00020\u0010HÆ\u0003J\u000f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010@\u001a\u00020\u0016HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0018HÆ\u0003J¼\u0001\u0010B\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÆ\u0001¢\u0006\u0002\u0010CJ\u0014\u0010D\u001a\u00020\u00162\b\u0010E\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010F\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010G\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010)\u001a\u0004\b'\u0010(R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001eR\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00188\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102¨\u0006H"}, d2 = {"Lcom/arflix/tv/data/api/TmdbMovieDetails;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Title, "", "originalTitle", "overview", "releaseDate", "posterPath", "backdropPath", "originalLanguage", "voteAverage", "", "runtime", "budget", "", "genres", "", "Lcom/arflix/tv/data/api/TmdbGenre;", "status", "adult", "", "belongsToCollection", "Lcom/arflix/tv/data/api/TmdbCollectionRef;", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FLjava/lang/Integer;JLjava/util/List;Ljava/lang/String;ZLcom/arflix/tv/data/api/TmdbCollectionRef;)V", "getId", "()I", "getTitle", "()Ljava/lang/String;", "getOriginalTitle", "getOverview", "getReleaseDate", "getPosterPath", "getBackdropPath", "getOriginalLanguage", "getVoteAverage", "()F", "getRuntime", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getBudget", "()J", "getGenres", "()Ljava/util/List;", "getStatus", "getAdult", "()Z", "getBelongsToCollection", "()Lcom/arflix/tv/data/api/TmdbCollectionRef;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FLjava/lang/Integer;JLjava/util/List;Ljava/lang/String;ZLcom/arflix/tv/data/api/TmdbCollectionRef;)Lcom/arflix/tv/data/api/TmdbMovieDetails;", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbMovieDetails {
    public static final int $stable = 0;
    private final boolean adult;

    @SerializedName("backdrop_path")
    private final String backdropPath;

    @SerializedName("belongs_to_collection")
    private final TmdbCollectionRef belongsToCollection;
    private final long budget;
    private final List<TmdbGenre> genres;
    private final int id;

    @SerializedName("original_language")
    private final String originalLanguage;

    @SerializedName("original_title")
    private final String originalTitle;
    private final String overview;

    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("release_date")
    private final String releaseDate;
    private final Integer runtime;
    private final String status;
    private final String title;

    @SerializedName("vote_average")
    private final float voteAverage;

    public TmdbMovieDetails() {
        this(0, null, null, null, null, null, null, null, 0.0f, null, 0L, null, null, false, null, LayoutKt.LargeDimension, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Integer getRuntime() {
        return this.runtime;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getBudget() {
        return this.budget;
    }

    public final List<TmdbGenre> component12() {
        return this.genres;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final boolean getAdult() {
        return this.adult;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final TmdbCollectionRef getBelongsToCollection() {
        return this.belongsToCollection;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getOriginalTitle() {
        return this.originalTitle;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getOverview() {
        return this.overview;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getReleaseDate() {
        return this.releaseDate;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getPosterPath() {
        return this.posterPath;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getBackdropPath() {
        return this.backdropPath;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getOriginalLanguage() {
        return this.originalLanguage;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final float getVoteAverage() {
        return this.voteAverage;
    }

    public final TmdbMovieDetails copy(int id, String title, String originalTitle, String overview, String releaseDate, String posterPath, String backdropPath, String originalLanguage, float voteAverage, Integer runtime, long budget, List<TmdbGenre> genres, String status, boolean adult, TmdbCollectionRef belongsToCollection) {
        return new TmdbMovieDetails(id, title, originalTitle, overview, releaseDate, posterPath, backdropPath, originalLanguage, voteAverage, runtime, budget, genres, status, adult, belongsToCollection);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbMovieDetails)) {
            return false;
        }
        TmdbMovieDetails tmdbMovieDetails = (TmdbMovieDetails) other;
        return this.id == tmdbMovieDetails.id && p.a(this.title, tmdbMovieDetails.title) && p.a(this.originalTitle, tmdbMovieDetails.originalTitle) && p.a(this.overview, tmdbMovieDetails.overview) && p.a(this.releaseDate, tmdbMovieDetails.releaseDate) && p.a(this.posterPath, tmdbMovieDetails.posterPath) && p.a(this.backdropPath, tmdbMovieDetails.backdropPath) && p.a(this.originalLanguage, tmdbMovieDetails.originalLanguage) && Float.compare(this.voteAverage, tmdbMovieDetails.voteAverage) == 0 && p.a(this.runtime, tmdbMovieDetails.runtime) && this.budget == tmdbMovieDetails.budget && p.a(this.genres, tmdbMovieDetails.genres) && p.a(this.status, tmdbMovieDetails.status) && this.adult == tmdbMovieDetails.adult && p.a(this.belongsToCollection, tmdbMovieDetails.belongsToCollection);
    }

    public final boolean getAdult() {
        return this.adult;
    }

    public final String getBackdropPath() {
        return this.backdropPath;
    }

    public final TmdbCollectionRef getBelongsToCollection() {
        return this.belongsToCollection;
    }

    public final long getBudget() {
        return this.budget;
    }

    public final List<TmdbGenre> getGenres() {
        return this.genres;
    }

    public final int getId() {
        return this.id;
    }

    public final String getOriginalLanguage() {
        return this.originalLanguage;
    }

    public final String getOriginalTitle() {
        return this.originalTitle;
    }

    public final String getOverview() {
        return this.overview;
    }

    public final String getPosterPath() {
        return this.posterPath;
    }

    public final String getReleaseDate() {
        return this.releaseDate;
    }

    public final Integer getRuntime() {
        return this.runtime;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getTitle() {
        return this.title;
    }

    public final float getVoteAverage() {
        return this.voteAverage;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.id * 31, 31, this.title);
        String str = this.originalTitle;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.overview;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.releaseDate;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.posterPath;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.backdropPath;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.originalLanguage;
        int iB = a0.c.b(this.voteAverage, (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31, 31);
        Integer num = this.runtime;
        int iHashCode6 = num == null ? 0 : num.hashCode();
        long j10 = this.budget;
        int iE = androidx.compose.material3.d.e(this.genres, (((iB + iHashCode6) * 31) + ((int) (j10 ^ (j10 >>> 32)))) * 31, 31);
        String str7 = this.status;
        int iHashCode7 = (((iE + (str7 == null ? 0 : str7.hashCode())) * 31) + (this.adult ? 1231 : 1237)) * 31;
        TmdbCollectionRef tmdbCollectionRef = this.belongsToCollection;
        return iHashCode7 + (tmdbCollectionRef != null ? tmdbCollectionRef.hashCode() : 0);
    }

    public String toString() {
        int i10 = this.id;
        String str = this.title;
        String str2 = this.originalTitle;
        String str3 = this.overview;
        String str4 = this.releaseDate;
        String str5 = this.posterPath;
        String str6 = this.backdropPath;
        String str7 = this.originalLanguage;
        float f10 = this.voteAverage;
        Integer num = this.runtime;
        long j10 = this.budget;
        List<TmdbGenre> list = this.genres;
        String str8 = this.status;
        boolean z = this.adult;
        TmdbCollectionRef tmdbCollectionRef = this.belongsToCollection;
        StringBuilder sbO = a2.o("TmdbMovieDetails(id=", i10, ", title=", str, ", originalTitle=");
        y.a.i(sbO, str2, ", overview=", str3, ", releaseDate=");
        y.a.i(sbO, str4, ", posterPath=", str5, ", backdropPath=");
        y.a.i(sbO, str6, ", originalLanguage=", str7, ", voteAverage=");
        sbO.append(f10);
        sbO.append(", runtime=");
        sbO.append(num);
        sbO.append(", budget=");
        sbO.append(j10);
        sbO.append(", genres=");
        sbO.append(list);
        sbO.append(", status=");
        sbO.append(str8);
        sbO.append(", adult=");
        sbO.append(z);
        sbO.append(", belongsToCollection=");
        sbO.append(tmdbCollectionRef);
        sbO.append(")");
        return sbO.toString();
    }

    public TmdbMovieDetails(int i10, String str, String str2, String str3, String str4, String str5, String str6, String str7, float f10, Integer num, long j10, List<TmdbGenre> list, String str8, boolean z, TmdbCollectionRef tmdbCollectionRef) {
        this.id = i10;
        this.title = str;
        this.originalTitle = str2;
        this.overview = str3;
        this.releaseDate = str4;
        this.posterPath = str5;
        this.backdropPath = str6;
        this.originalLanguage = str7;
        this.voteAverage = f10;
        this.runtime = num;
        this.budget = j10;
        this.genres = list;
        this.status = str8;
        this.adult = z;
        this.belongsToCollection = tmdbCollectionRef;
    }

    public /* synthetic */ TmdbMovieDetails(int i10, String str, String str2, String str3, String str4, String str5, String str6, String str7, float f10, Integer num, long j10, List list, String str8, boolean z, TmdbCollectionRef tmdbCollectionRef, int i11, kotlin.jvm.internal.h hVar) {
        this((i11 & 1) != 0 ? 0 : i10, (i11 & 2) != 0 ? "" : str, (i11 & 4) != 0 ? null : str2, (i11 & 8) != 0 ? null : str3, (i11 & 16) != 0 ? null : str4, (i11 & 32) != 0 ? null : str5, (i11 & 64) != 0 ? null : str6, (i11 & 128) != 0 ? null : str7, (i11 & 256) != 0 ? 0.0f : f10, (i11 & 512) != 0 ? null : num, (i11 & 1024) != 0 ? 0L : j10, (i11 & 2048) != 0 ? z.f19728i : list, (i11 & 4096) != 0 ? null : str8, (i11 & 8192) != 0 ? false : z, (i11 & 16384) != 0 ? null : tmdbCollectionRef);
    }
}
