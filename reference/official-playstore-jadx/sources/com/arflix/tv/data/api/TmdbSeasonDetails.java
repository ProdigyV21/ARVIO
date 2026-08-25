package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BO\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003JQ\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006#"}, d2 = {"Lcom/arflix/tv/data/api/TmdbSeasonDetails;", "", TtmlNode.ATTR_ID, "", "seasonNumber", ContentDisposition.Parameters.Name, "", "overview", "posterPath", "episodes", "", "Lcom/arflix/tv/data/api/TmdbEpisode;", "<init>", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getId", "()I", "getSeasonNumber", "getName", "()Ljava/lang/String;", "getOverview", "getPosterPath", "getEpisodes", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbSeasonDetails {
    public static final int $stable = 0;
    private final List<TmdbEpisode> episodes;
    private final int id;
    private final String name;
    private final String overview;

    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("season_number")
    private final int seasonNumber;

    public TmdbSeasonDetails() {
        this(0, 0, null, null, null, null, 63, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TmdbSeasonDetails copy$default(TmdbSeasonDetails tmdbSeasonDetails, int i10, int i11, String str, String str2, String str3, List list, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = tmdbSeasonDetails.id;
        }
        if ((i12 & 2) != 0) {
            i11 = tmdbSeasonDetails.seasonNumber;
        }
        if ((i12 & 4) != 0) {
            str = tmdbSeasonDetails.name;
        }
        if ((i12 & 8) != 0) {
            str2 = tmdbSeasonDetails.overview;
        }
        if ((i12 & 16) != 0) {
            str3 = tmdbSeasonDetails.posterPath;
        }
        if ((i12 & 32) != 0) {
            list = tmdbSeasonDetails.episodes;
        }
        String str4 = str3;
        List list2 = list;
        return tmdbSeasonDetails.copy(i10, i11, str, str2, str4, list2);
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
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getOverview() {
        return this.overview;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPosterPath() {
        return this.posterPath;
    }

    public final List<TmdbEpisode> component6() {
        return this.episodes;
    }

    public final TmdbSeasonDetails copy(int id, int seasonNumber, String name, String overview, String posterPath, List<TmdbEpisode> episodes) {
        return new TmdbSeasonDetails(id, seasonNumber, name, overview, posterPath, episodes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbSeasonDetails)) {
            return false;
        }
        TmdbSeasonDetails tmdbSeasonDetails = (TmdbSeasonDetails) other;
        return this.id == tmdbSeasonDetails.id && this.seasonNumber == tmdbSeasonDetails.seasonNumber && p.a(this.name, tmdbSeasonDetails.name) && p.a(this.overview, tmdbSeasonDetails.overview) && p.a(this.posterPath, tmdbSeasonDetails.posterPath) && p.a(this.episodes, tmdbSeasonDetails.episodes);
    }

    public final List<TmdbEpisode> getEpisodes() {
        return this.episodes;
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
        int i10 = ((this.id * 31) + this.seasonNumber) * 31;
        String str = this.name;
        int iHashCode = (i10 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.overview;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.posterPath;
        return this.episodes.hashCode() + ((iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31);
    }

    public String toString() {
        int i10 = this.id;
        int i11 = this.seasonNumber;
        String str = this.name;
        String str2 = this.overview;
        String str3 = this.posterPath;
        List<TmdbEpisode> list = this.episodes;
        StringBuilder sbV = androidx.compose.foundation.c.v("TmdbSeasonDetails(id=", i10, ", seasonNumber=", i11, ", name=");
        y.a.i(sbV, str, ", overview=", str2, ", posterPath=");
        sbV.append(str3);
        sbV.append(", episodes=");
        sbV.append(list);
        sbV.append(")");
        return sbV.toString();
    }

    public TmdbSeasonDetails(int i10, int i11, String str, String str2, String str3, List<TmdbEpisode> list) {
        this.id = i10;
        this.seasonNumber = i11;
        this.name = str;
        this.overview = str2;
        this.posterPath = str3;
        this.episodes = list;
    }

    public /* synthetic */ TmdbSeasonDetails(int i10, int i11, String str, String str2, String str3, List list, int i12, kotlin.jvm.internal.h hVar) {
        this((i12 & 1) != 0 ? 0 : i10, (i12 & 2) != 0 ? 1 : i11, (i12 & 4) != 0 ? null : str, (i12 & 8) != 0 ? null : str2, (i12 & 16) != 0 ? null : str3, (i12 & 32) != 0 ? z.f19728i : list);
    }
}
