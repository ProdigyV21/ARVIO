package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b6\b\u0087\b\u0018\u00002\u00020\u0001Bé\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u0012¢\u0006\u0004\b\u001a\u0010\u001bJ\t\u00104\u001a\u00020\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010>\u001a\u00020\u000fHÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\u000f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010C\u001a\u00020\u0016HÆ\u0003J\t\u0010D\u001a\u00020\u000fHÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00000\u0012HÆ\u0003Jë\u0001\u0010G\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00032\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u000f2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u0012HÆ\u0001J\u0014\u0010H\u001a\u00020\u00162\b\u0010I\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010J\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010K\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001fR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR\u0018\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001fR\u0016\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0016\u0010\u0010\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001dR\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00128\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001fR\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u0017\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b1\u0010)R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001fR\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u00128\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010,¨\u0006L"}, d2 = {"Lcom/arflix/tv/data/api/TmdbMediaItem;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Title, "", ContentDisposition.Parameters.Name, "originalTitle", "originalName", "overview", "releaseDate", "firstAirDate", "posterPath", "backdropPath", "voteAverage", "", "voteCount", "genreIds", "", "originalLanguage", "mediaType", "adult", "", "popularity", "character", "knownFor", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FILjava/util/List;Ljava/lang/String;Ljava/lang/String;ZFLjava/lang/String;Ljava/util/List;)V", "getId", "()I", "getTitle", "()Ljava/lang/String;", "getName", "getOriginalTitle", "getOriginalName", "getOverview", "getReleaseDate", "getFirstAirDate", "getPosterPath", "getBackdropPath", "getVoteAverage", "()F", "getVoteCount", "getGenreIds", "()Ljava/util/List;", "getOriginalLanguage", "getMediaType", "getAdult", "()Z", "getPopularity", "getCharacter", "getKnownFor", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbMediaItem {
    public static final int $stable = 8;
    private final boolean adult;

    @SerializedName("backdrop_path")
    private final String backdropPath;
    private final String character;

    @SerializedName("first_air_date")
    private final String firstAirDate;

    @SerializedName("genre_ids")
    private final List<Integer> genreIds;
    private final int id;

    @SerializedName("known_for")
    private final List<TmdbMediaItem> knownFor;

    @SerializedName("media_type")
    private final String mediaType;
    private final String name;

    @SerializedName("original_language")
    private final String originalLanguage;

    @SerializedName("original_name")
    private final String originalName;

    @SerializedName("original_title")
    private final String originalTitle;
    private final String overview;
    private final float popularity;

    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("release_date")
    private final String releaseDate;
    private final String title;

    @SerializedName("vote_average")
    private final float voteAverage;

    @SerializedName("vote_count")
    private final int voteCount;

    public TmdbMediaItem() {
        this(0, null, null, null, null, null, null, null, null, null, 0.0f, 0, null, null, null, false, 0.0f, null, null, 524287, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TmdbMediaItem copy$default(TmdbMediaItem tmdbMediaItem, int i10, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, float f10, int i11, List list, String str10, String str11, boolean z, float f11, String str12, List list2, int i12, Object obj) {
        List list3;
        String str13;
        int i13 = (i12 & 1) != 0 ? tmdbMediaItem.id : i10;
        String str14 = (i12 & 2) != 0 ? tmdbMediaItem.title : str;
        String str15 = (i12 & 4) != 0 ? tmdbMediaItem.name : str2;
        String str16 = (i12 & 8) != 0 ? tmdbMediaItem.originalTitle : str3;
        String str17 = (i12 & 16) != 0 ? tmdbMediaItem.originalName : str4;
        String str18 = (i12 & 32) != 0 ? tmdbMediaItem.overview : str5;
        String str19 = (i12 & 64) != 0 ? tmdbMediaItem.releaseDate : str6;
        String str20 = (i12 & 128) != 0 ? tmdbMediaItem.firstAirDate : str7;
        String str21 = (i12 & 256) != 0 ? tmdbMediaItem.posterPath : str8;
        String str22 = (i12 & 512) != 0 ? tmdbMediaItem.backdropPath : str9;
        float f12 = (i12 & 1024) != 0 ? tmdbMediaItem.voteAverage : f10;
        int i14 = (i12 & 2048) != 0 ? tmdbMediaItem.voteCount : i11;
        List list4 = (i12 & 4096) != 0 ? tmdbMediaItem.genreIds : list;
        String str23 = (i12 & 8192) != 0 ? tmdbMediaItem.originalLanguage : str10;
        int i15 = i13;
        String str24 = (i12 & 16384) != 0 ? tmdbMediaItem.mediaType : str11;
        boolean z5 = (i12 & 32768) != 0 ? tmdbMediaItem.adult : z;
        float f13 = (i12 & 65536) != 0 ? tmdbMediaItem.popularity : f11;
        String str25 = (i12 & 131072) != 0 ? tmdbMediaItem.character : str12;
        if ((i12 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0) {
            str13 = str25;
            list3 = tmdbMediaItem.knownFor;
        } else {
            list3 = list2;
            str13 = str25;
        }
        return tmdbMediaItem.copy(i15, str14, str15, str16, str17, str18, str19, str20, str21, str22, f12, i14, list4, str23, str24, z5, f13, str13, list3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getBackdropPath() {
        return this.backdropPath;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final float getVoteAverage() {
        return this.voteAverage;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getVoteCount() {
        return this.voteCount;
    }

    public final List<Integer> component13() {
        return this.genreIds;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getOriginalLanguage() {
        return this.originalLanguage;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getMediaType() {
        return this.mediaType;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final boolean getAdult() {
        return this.adult;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final float getPopularity() {
        return this.popularity;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getCharacter() {
        return this.character;
    }

    public final List<TmdbMediaItem> component19() {
        return this.knownFor;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getOriginalTitle() {
        return this.originalTitle;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getOriginalName() {
        return this.originalName;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getOverview() {
        return this.overview;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getReleaseDate() {
        return this.releaseDate;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getFirstAirDate() {
        return this.firstAirDate;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getPosterPath() {
        return this.posterPath;
    }

    public final TmdbMediaItem copy(int id, String title, String name, String originalTitle, String originalName, String overview, String releaseDate, String firstAirDate, String posterPath, String backdropPath, float voteAverage, int voteCount, List<Integer> genreIds, String originalLanguage, String mediaType, boolean adult, float popularity, String character, List<TmdbMediaItem> knownFor) {
        return new TmdbMediaItem(id, title, name, originalTitle, originalName, overview, releaseDate, firstAirDate, posterPath, backdropPath, voteAverage, voteCount, genreIds, originalLanguage, mediaType, adult, popularity, character, knownFor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbMediaItem)) {
            return false;
        }
        TmdbMediaItem tmdbMediaItem = (TmdbMediaItem) other;
        return this.id == tmdbMediaItem.id && p.a(this.title, tmdbMediaItem.title) && p.a(this.name, tmdbMediaItem.name) && p.a(this.originalTitle, tmdbMediaItem.originalTitle) && p.a(this.originalName, tmdbMediaItem.originalName) && p.a(this.overview, tmdbMediaItem.overview) && p.a(this.releaseDate, tmdbMediaItem.releaseDate) && p.a(this.firstAirDate, tmdbMediaItem.firstAirDate) && p.a(this.posterPath, tmdbMediaItem.posterPath) && p.a(this.backdropPath, tmdbMediaItem.backdropPath) && Float.compare(this.voteAverage, tmdbMediaItem.voteAverage) == 0 && this.voteCount == tmdbMediaItem.voteCount && p.a(this.genreIds, tmdbMediaItem.genreIds) && p.a(this.originalLanguage, tmdbMediaItem.originalLanguage) && p.a(this.mediaType, tmdbMediaItem.mediaType) && this.adult == tmdbMediaItem.adult && Float.compare(this.popularity, tmdbMediaItem.popularity) == 0 && p.a(this.character, tmdbMediaItem.character) && p.a(this.knownFor, tmdbMediaItem.knownFor);
    }

    public final boolean getAdult() {
        return this.adult;
    }

    public final String getBackdropPath() {
        return this.backdropPath;
    }

    public final String getCharacter() {
        return this.character;
    }

    public final String getFirstAirDate() {
        return this.firstAirDate;
    }

    public final List<Integer> getGenreIds() {
        return this.genreIds;
    }

    public final int getId() {
        return this.id;
    }

    public final List<TmdbMediaItem> getKnownFor() {
        return this.knownFor;
    }

    public final String getMediaType() {
        return this.mediaType;
    }

    public final String getName() {
        return this.name;
    }

    public final String getOriginalLanguage() {
        return this.originalLanguage;
    }

    public final String getOriginalName() {
        return this.originalName;
    }

    public final String getOriginalTitle() {
        return this.originalTitle;
    }

    public final String getOverview() {
        return this.overview;
    }

    public final float getPopularity() {
        return this.popularity;
    }

    public final String getPosterPath() {
        return this.posterPath;
    }

    public final String getReleaseDate() {
        return this.releaseDate;
    }

    public final String getTitle() {
        return this.title;
    }

    public final float getVoteAverage() {
        return this.voteAverage;
    }

    public final int getVoteCount() {
        return this.voteCount;
    }

    public int hashCode() {
        int i10 = this.id * 31;
        String str = this.title;
        int iHashCode = (i10 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.originalTitle;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.originalName;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.overview;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.releaseDate;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.firstAirDate;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.posterPath;
        int iHashCode8 = (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.backdropPath;
        int iE = androidx.compose.material3.d.e(this.genreIds, (a0.c.b(this.voteAverage, (iHashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31, 31) + this.voteCount) * 31, 31);
        String str10 = this.originalLanguage;
        int iHashCode9 = (iE + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.mediaType;
        int iB = a0.c.b(this.popularity, (((iHashCode9 + (str11 == null ? 0 : str11.hashCode())) * 31) + (this.adult ? 1231 : 1237)) * 31, 31);
        String str12 = this.character;
        return this.knownFor.hashCode() + ((iB + (str12 != null ? str12.hashCode() : 0)) * 31);
    }

    public String toString() {
        int i10 = this.id;
        String str = this.title;
        String str2 = this.name;
        String str3 = this.originalTitle;
        String str4 = this.originalName;
        String str5 = this.overview;
        String str6 = this.releaseDate;
        String str7 = this.firstAirDate;
        String str8 = this.posterPath;
        String str9 = this.backdropPath;
        float f10 = this.voteAverage;
        int i11 = this.voteCount;
        List<Integer> list = this.genreIds;
        String str10 = this.originalLanguage;
        String str11 = this.mediaType;
        boolean z = this.adult;
        float f11 = this.popularity;
        String str12 = this.character;
        List<TmdbMediaItem> list2 = this.knownFor;
        StringBuilder sbO = a2.o("TmdbMediaItem(id=", i10, ", title=", str, ", name=");
        y.a.i(sbO, str2, ", originalTitle=", str3, ", originalName=");
        y.a.i(sbO, str4, ", overview=", str5, ", releaseDate=");
        y.a.i(sbO, str6, ", firstAirDate=", str7, ", posterPath=");
        y.a.i(sbO, str8, ", backdropPath=", str9, ", voteAverage=");
        sbO.append(f10);
        sbO.append(", voteCount=");
        sbO.append(i11);
        sbO.append(", genreIds=");
        sbO.append(list);
        sbO.append(", originalLanguage=");
        sbO.append(str10);
        sbO.append(", mediaType=");
        sbO.append(str11);
        sbO.append(", adult=");
        sbO.append(z);
        sbO.append(", popularity=");
        sbO.append(f11);
        sbO.append(", character=");
        sbO.append(str12);
        sbO.append(", knownFor=");
        return a0.c.q(sbO, list2, ")");
    }

    public TmdbMediaItem(int i10, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, float f10, int i11, List<Integer> list, String str10, String str11, boolean z, float f11, String str12, List<TmdbMediaItem> list2) {
        this.id = i10;
        this.title = str;
        this.name = str2;
        this.originalTitle = str3;
        this.originalName = str4;
        this.overview = str5;
        this.releaseDate = str6;
        this.firstAirDate = str7;
        this.posterPath = str8;
        this.backdropPath = str9;
        this.voteAverage = f10;
        this.voteCount = i11;
        this.genreIds = list;
        this.originalLanguage = str10;
        this.mediaType = str11;
        this.adult = z;
        this.popularity = f11;
        this.character = str12;
        this.knownFor = list2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TmdbMediaItem(int i10, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, float f10, int i11, List list, String str10, String str11, boolean z, float f11, String str12, List list2, int i12, kotlin.jvm.internal.h hVar) {
        int i13 = (i12 & 1) != 0 ? 0 : i10;
        String str13 = (i12 & 2) != 0 ? null : str;
        String str14 = (i12 & 4) != 0 ? null : str2;
        String str15 = (i12 & 8) != 0 ? null : str3;
        String str16 = (i12 & 16) != 0 ? null : str4;
        String str17 = (i12 & 32) != 0 ? null : str5;
        String str18 = (i12 & 64) != 0 ? null : str6;
        String str19 = (i12 & 128) != 0 ? null : str7;
        String str20 = (i12 & 256) != 0 ? null : str8;
        String str21 = (i12 & 512) != 0 ? null : str9;
        float f12 = (i12 & 1024) != 0 ? 0.0f : f10;
        int i14 = (i12 & 2048) != 0 ? 0 : i11;
        int i15 = i12 & 4096;
        z zVar = z.f19728i;
        this(i13, str13, str14, str15, str16, str17, str18, str19, str20, str21, f12, i14, i15 != 0 ? zVar : list, (i12 & 8192) != 0 ? null : str10, (i12 & 16384) != 0 ? null : str11, (i12 & 32768) != 0 ? false : z, (i12 & 65536) != 0 ? 0.0f : f11, (i12 & 131072) != 0 ? null : str12, (i12 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? zVar : list2);
    }
}
