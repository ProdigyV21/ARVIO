package com.arflix.tv.data.api;

import androidx.compose.ui.layout.LayoutKt;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B½\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0011\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0011¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00104\u001a\u00020\rHÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\u000f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00140\u0011HÆ\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00160\u0011HÆ\u0003J¿\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00112\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0011HÆ\u0001J\u0014\u0010<\u001a\u00020=2\b\u0010>\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010?\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010@\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u0016\u0010\u000f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u00118\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001cR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0011¢\u0006\b\n\u0000\u001a\u0004\b*\u0010(R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0011¢\u0006\b\n\u0000\u001a\u0004\b+\u0010(¨\u0006A"}, d2 = {"Lcom/arflix/tv/data/api/TmdbTvDetails;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "", "originalName", "overview", "firstAirDate", "posterPath", "backdropPath", "originalLanguage", "voteAverage", "", "numberOfSeasons", "numberOfEpisodes", "episodeRunTime", "", "status", "genres", "Lcom/arflix/tv/data/api/TmdbGenre;", "seasons", "Lcom/arflix/tv/data/api/TmdbTvSeason;", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FIILjava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getOriginalName", "getOverview", "getFirstAirDate", "getPosterPath", "getBackdropPath", "getOriginalLanguage", "getVoteAverage", "()F", "getNumberOfSeasons", "getNumberOfEpisodes", "getEpisodeRunTime", "()Ljava/util/List;", "getStatus", "getGenres", "getSeasons", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbTvDetails {
    public static final int $stable = 0;

    @SerializedName("backdrop_path")
    private final String backdropPath;

    @SerializedName("episode_run_time")
    private final List<Integer> episodeRunTime;

    @SerializedName("first_air_date")
    private final String firstAirDate;
    private final List<TmdbGenre> genres;
    private final int id;
    private final String name;

    @SerializedName("number_of_episodes")
    private final int numberOfEpisodes;

    @SerializedName("number_of_seasons")
    private final int numberOfSeasons;

    @SerializedName("original_language")
    private final String originalLanguage;

    @SerializedName("original_name")
    private final String originalName;
    private final String overview;

    @SerializedName("poster_path")
    private final String posterPath;
    private final List<TmdbTvSeason> seasons;
    private final String status;

    @SerializedName("vote_average")
    private final float voteAverage;

    public TmdbTvDetails() {
        this(0, null, null, null, null, null, null, null, 0.0f, 0, 0, null, null, null, null, LayoutKt.LargeDimension, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getNumberOfSeasons() {
        return this.numberOfSeasons;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getNumberOfEpisodes() {
        return this.numberOfEpisodes;
    }

    public final List<Integer> component12() {
        return this.episodeRunTime;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    public final List<TmdbGenre> component14() {
        return this.genres;
    }

    public final List<TmdbTvSeason> component15() {
        return this.seasons;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getOriginalName() {
        return this.originalName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getOverview() {
        return this.overview;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getFirstAirDate() {
        return this.firstAirDate;
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

    public final TmdbTvDetails copy(int id, String name, String originalName, String overview, String firstAirDate, String posterPath, String backdropPath, String originalLanguage, float voteAverage, int numberOfSeasons, int numberOfEpisodes, List<Integer> episodeRunTime, String status, List<TmdbGenre> genres, List<TmdbTvSeason> seasons) {
        return new TmdbTvDetails(id, name, originalName, overview, firstAirDate, posterPath, backdropPath, originalLanguage, voteAverage, numberOfSeasons, numberOfEpisodes, episodeRunTime, status, genres, seasons);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbTvDetails)) {
            return false;
        }
        TmdbTvDetails tmdbTvDetails = (TmdbTvDetails) other;
        return this.id == tmdbTvDetails.id && p.a(this.name, tmdbTvDetails.name) && p.a(this.originalName, tmdbTvDetails.originalName) && p.a(this.overview, tmdbTvDetails.overview) && p.a(this.firstAirDate, tmdbTvDetails.firstAirDate) && p.a(this.posterPath, tmdbTvDetails.posterPath) && p.a(this.backdropPath, tmdbTvDetails.backdropPath) && p.a(this.originalLanguage, tmdbTvDetails.originalLanguage) && Float.compare(this.voteAverage, tmdbTvDetails.voteAverage) == 0 && this.numberOfSeasons == tmdbTvDetails.numberOfSeasons && this.numberOfEpisodes == tmdbTvDetails.numberOfEpisodes && p.a(this.episodeRunTime, tmdbTvDetails.episodeRunTime) && p.a(this.status, tmdbTvDetails.status) && p.a(this.genres, tmdbTvDetails.genres) && p.a(this.seasons, tmdbTvDetails.seasons);
    }

    public final String getBackdropPath() {
        return this.backdropPath;
    }

    public final List<Integer> getEpisodeRunTime() {
        return this.episodeRunTime;
    }

    public final String getFirstAirDate() {
        return this.firstAirDate;
    }

    public final List<TmdbGenre> getGenres() {
        return this.genres;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final int getNumberOfEpisodes() {
        return this.numberOfEpisodes;
    }

    public final int getNumberOfSeasons() {
        return this.numberOfSeasons;
    }

    public final String getOriginalLanguage() {
        return this.originalLanguage;
    }

    public final String getOriginalName() {
        return this.originalName;
    }

    public final String getOverview() {
        return this.overview;
    }

    public final String getPosterPath() {
        return this.posterPath;
    }

    public final List<TmdbTvSeason> getSeasons() {
        return this.seasons;
    }

    public final String getStatus() {
        return this.status;
    }

    public final float getVoteAverage() {
        return this.voteAverage;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.id * 31, 31, this.name);
        String str = this.originalName;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.overview;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.firstAirDate;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.posterPath;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.backdropPath;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.originalLanguage;
        int iE = androidx.compose.material3.d.e(this.episodeRunTime, (((a0.c.b(this.voteAverage, (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31, 31) + this.numberOfSeasons) * 31) + this.numberOfEpisodes) * 31, 31);
        String str7 = this.status;
        return this.seasons.hashCode() + androidx.compose.material3.d.e(this.genres, (iE + (str7 != null ? str7.hashCode() : 0)) * 31, 31);
    }

    public String toString() {
        int i10 = this.id;
        String str = this.name;
        String str2 = this.originalName;
        String str3 = this.overview;
        String str4 = this.firstAirDate;
        String str5 = this.posterPath;
        String str6 = this.backdropPath;
        String str7 = this.originalLanguage;
        float f10 = this.voteAverage;
        int i11 = this.numberOfSeasons;
        int i12 = this.numberOfEpisodes;
        List<Integer> list = this.episodeRunTime;
        String str8 = this.status;
        List<TmdbGenre> list2 = this.genres;
        List<TmdbTvSeason> list3 = this.seasons;
        StringBuilder sbO = a2.o("TmdbTvDetails(id=", i10, ", name=", str, ", originalName=");
        y.a.i(sbO, str2, ", overview=", str3, ", firstAirDate=");
        y.a.i(sbO, str4, ", posterPath=", str5, ", backdropPath=");
        y.a.i(sbO, str6, ", originalLanguage=", str7, ", voteAverage=");
        sbO.append(f10);
        sbO.append(", numberOfSeasons=");
        sbO.append(i11);
        sbO.append(", numberOfEpisodes=");
        sbO.append(i12);
        sbO.append(", episodeRunTime=");
        sbO.append(list);
        sbO.append(", status=");
        sbO.append(str8);
        sbO.append(", genres=");
        sbO.append(list2);
        sbO.append(", seasons=");
        return a0.c.q(sbO, list3, ")");
    }

    public TmdbTvDetails(int i10, String str, String str2, String str3, String str4, String str5, String str6, String str7, float f10, int i11, int i12, List<Integer> list, String str8, List<TmdbGenre> list2, List<TmdbTvSeason> list3) {
        this.id = i10;
        this.name = str;
        this.originalName = str2;
        this.overview = str3;
        this.firstAirDate = str4;
        this.posterPath = str5;
        this.backdropPath = str6;
        this.originalLanguage = str7;
        this.voteAverage = f10;
        this.numberOfSeasons = i11;
        this.numberOfEpisodes = i12;
        this.episodeRunTime = list;
        this.status = str8;
        this.genres = list2;
        this.seasons = list3;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TmdbTvDetails(int i10, String str, String str2, String str3, String str4, String str5, String str6, String str7, float f10, int i11, int i12, List list, String str8, List list2, List list3, int i13, kotlin.jvm.internal.h hVar) {
        int i14 = (i13 & 1) != 0 ? 0 : i10;
        String str9 = (i13 & 2) != 0 ? "" : str;
        String str10 = (i13 & 4) != 0 ? null : str2;
        String str11 = (i13 & 8) != 0 ? null : str3;
        String str12 = (i13 & 16) != 0 ? null : str4;
        String str13 = (i13 & 32) != 0 ? null : str5;
        String str14 = (i13 & 64) != 0 ? null : str6;
        String str15 = (i13 & 128) != 0 ? null : str7;
        float f11 = (i13 & 256) != 0 ? 0.0f : f10;
        int i15 = (i13 & 512) != 0 ? 1 : i11;
        int i16 = (i13 & 1024) == 0 ? i12 : 0;
        int i17 = i13 & 2048;
        z zVar = z.f19728i;
        this(i14, str9, str10, str11, str12, str13, str14, str15, f11, i15, i16, i17 != 0 ? zVar : list, (i13 & 4096) == 0 ? str8 : null, (i13 & 8192) != 0 ? zVar : list2, (i13 & 16384) != 0 ? zVar : list3);
    }
}
