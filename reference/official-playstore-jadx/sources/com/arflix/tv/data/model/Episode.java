package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0007HÆ\u0003J\t\u00102\u001a\u00020\u0007HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u00104\u001a\u00020\u000bHÆ\u0003J\t\u00105\u001a\u00020\u0007HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0007HÆ\u0003J\t\u00108\u001a\u00020\u0010HÆ\u0003J\t\u00109\u001a\u00020\u0012HÆ\u0003J\u0083\u0001\u0010:\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012HÆ\u0001J\u0014\u0010;\u001a\u00020\u00102\b\u0010<\u001a\u0004\u0018\u00010=HÖ\u0083\u0004J\n\u0010>\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010?\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\"R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b&\u0010\u0016R\u0011\u0010'\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b(\u0010\u0016R\u0013\u0010)\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0013\u0010,\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b-\u0010+¨\u0006@"}, d2 = {"Lcom/arflix/tv/data/model/Episode;", "Ljava/io/Serializable;", TtmlNode.ATTR_ID, "", "episodeNumber", "seasonNumber", ContentDisposition.Parameters.Name, "", "overview", "stillPath", "voteAverage", "", "imdbRating", "runtime", "airDate", "isWatched", "", "identity", "Lcom/arflix/tv/data/model/EpisodeIdentity;", "<init>", "(IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;FLjava/lang/String;ILjava/lang/String;ZLcom/arflix/tv/data/model/EpisodeIdentity;)V", "getId", "()I", "getEpisodeNumber", "getSeasonNumber", "getName", "()Ljava/lang/String;", "getOverview", "getStillPath", "getVoteAverage", "()F", "getImdbRating", "getRuntime", "getAirDate", "()Z", "getIdentity", "()Lcom/arflix/tv/data/model/EpisodeIdentity;", "tmdbSeasonNumber", "getTmdbSeasonNumber", "tmdbEpisodeNumber", "getTmdbEpisodeNumber", "kitsuId", "getKitsuId", "()Ljava/lang/Integer;", "kitsuEpisodeNumber", "getKitsuEpisodeNumber", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "other", "", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Episode implements Serializable {
    public static final int $stable = 0;
    private final String airDate;
    private final int episodeNumber;
    private final int id;
    private final EpisodeIdentity identity;
    private final String imdbRating;
    private final boolean isWatched;
    private final String name;
    private final String overview;
    private final int runtime;
    private final int seasonNumber;
    private final String stillPath;
    private final float voteAverage;

    public Episode(int i10, int i11, int i12, String str, String str2, String str3, float f10, String str4, int i13, String str5, boolean z, EpisodeIdentity episodeIdentity) {
        this.id = i10;
        this.episodeNumber = i11;
        this.seasonNumber = i12;
        this.name = str;
        this.overview = str2;
        this.stillPath = str3;
        this.voteAverage = f10;
        this.imdbRating = str4;
        this.runtime = i13;
        this.airDate = str5;
        this.isWatched = z;
        this.identity = episodeIdentity;
    }

    public static /* synthetic */ Episode copy$default(Episode episode, int i10, int i11, int i12, String str, String str2, String str3, float f10, String str4, int i13, String str5, boolean z, EpisodeIdentity episodeIdentity, int i14, Object obj) {
        if ((i14 & 1) != 0) {
            i10 = episode.id;
        }
        if ((i14 & 2) != 0) {
            i11 = episode.episodeNumber;
        }
        if ((i14 & 4) != 0) {
            i12 = episode.seasonNumber;
        }
        if ((i14 & 8) != 0) {
            str = episode.name;
        }
        if ((i14 & 16) != 0) {
            str2 = episode.overview;
        }
        if ((i14 & 32) != 0) {
            str3 = episode.stillPath;
        }
        if ((i14 & 64) != 0) {
            f10 = episode.voteAverage;
        }
        if ((i14 & 128) != 0) {
            str4 = episode.imdbRating;
        }
        if ((i14 & 256) != 0) {
            i13 = episode.runtime;
        }
        if ((i14 & 512) != 0) {
            str5 = episode.airDate;
        }
        if ((i14 & 1024) != 0) {
            z = episode.isWatched;
        }
        if ((i14 & 2048) != 0) {
            episodeIdentity = episode.identity;
        }
        boolean z5 = z;
        EpisodeIdentity episodeIdentity2 = episodeIdentity;
        int i15 = i13;
        String str6 = str5;
        float f11 = f10;
        String str7 = str4;
        String str8 = str2;
        String str9 = str3;
        return episode.copy(i10, i11, i12, str, str8, str9, f11, str7, i15, str6, z5, episodeIdentity2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getAirDate() {
        return this.airDate;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getIsWatched() {
        return this.isWatched;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final EpisodeIdentity getIdentity() {
        return this.identity;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getEpisodeNumber() {
        return this.episodeNumber;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getSeasonNumber() {
        return this.seasonNumber;
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
    public final String getStillPath() {
        return this.stillPath;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final float getVoteAverage() {
        return this.voteAverage;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getImdbRating() {
        return this.imdbRating;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getRuntime() {
        return this.runtime;
    }

    public final Episode copy(int id, int episodeNumber, int seasonNumber, String name, String overview, String stillPath, float voteAverage, String imdbRating, int runtime, String airDate, boolean isWatched, EpisodeIdentity identity) {
        return new Episode(id, episodeNumber, seasonNumber, name, overview, stillPath, voteAverage, imdbRating, runtime, airDate, isWatched, identity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Episode)) {
            return false;
        }
        Episode episode = (Episode) other;
        return this.id == episode.id && this.episodeNumber == episode.episodeNumber && this.seasonNumber == episode.seasonNumber && p.a(this.name, episode.name) && p.a(this.overview, episode.overview) && p.a(this.stillPath, episode.stillPath) && Float.compare(this.voteAverage, episode.voteAverage) == 0 && p.a(this.imdbRating, episode.imdbRating) && this.runtime == episode.runtime && p.a(this.airDate, episode.airDate) && this.isWatched == episode.isWatched && p.a(this.identity, episode.identity);
    }

    public final String getAirDate() {
        return this.airDate;
    }

    public final int getEpisodeNumber() {
        return this.episodeNumber;
    }

    public final int getId() {
        return this.id;
    }

    public final EpisodeIdentity getIdentity() {
        return this.identity;
    }

    public final String getImdbRating() {
        return this.imdbRating;
    }

    public final Integer getKitsuEpisodeNumber() {
        return this.identity.getKitsuEpisode();
    }

    public final Integer getKitsuId() {
        return this.identity.getKitsuId();
    }

    public final String getName() {
        return this.name;
    }

    public final String getOverview() {
        return this.overview;
    }

    public final int getRuntime() {
        return this.runtime;
    }

    public final int getSeasonNumber() {
        return this.seasonNumber;
    }

    public final String getStillPath() {
        return this.stillPath;
    }

    public final int getTmdbEpisodeNumber() {
        return this.identity.getTmdbEpisode();
    }

    public final int getTmdbSeasonNumber() {
        return this.identity.getTmdbSeason();
    }

    public final float getVoteAverage() {
        return this.voteAverage;
    }

    public int hashCode() {
        int iC = c.c(c.c(((((this.id * 31) + this.episodeNumber) * 31) + this.seasonNumber) * 31, 31, this.name), 31, this.overview);
        String str = this.stillPath;
        return this.identity.hashCode() + ((c.c((c.c(a0.c.b(this.voteAverage, (iC + (str == null ? 0 : str.hashCode())) * 31, 31), 31, this.imdbRating) + this.runtime) * 31, 31, this.airDate) + (this.isWatched ? 1231 : 1237)) * 31);
    }

    public final boolean isWatched() {
        return this.isWatched;
    }

    public String toString() {
        int i10 = this.id;
        int i11 = this.episodeNumber;
        int i12 = this.seasonNumber;
        String str = this.name;
        String str2 = this.overview;
        String str3 = this.stillPath;
        float f10 = this.voteAverage;
        String str4 = this.imdbRating;
        int i13 = this.runtime;
        String str5 = this.airDate;
        boolean z = this.isWatched;
        EpisodeIdentity episodeIdentity = this.identity;
        StringBuilder sbV = c.v("Episode(id=", i10, ", episodeNumber=", i11, ", seasonNumber=");
        sbV.append(i12);
        sbV.append(", name=");
        sbV.append(str);
        sbV.append(", overview=");
        a.i(sbV, str2, ", stillPath=", str3, ", voteAverage=");
        sbV.append(f10);
        sbV.append(", imdbRating=");
        sbV.append(str4);
        sbV.append(", runtime=");
        sbV.append(i13);
        sbV.append(", airDate=");
        sbV.append(str5);
        sbV.append(", isWatched=");
        sbV.append(z);
        sbV.append(", identity=");
        sbV.append(episodeIdentity);
        sbV.append(")");
        return sbV.toString();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ Episode(int i10, int i11, int i12, String str, String str2, String str3, float f10, String str4, int i13, String str5, boolean z, EpisodeIdentity episodeIdentity, int i14, h hVar) {
        int i15;
        int i16;
        EpisodeIdentity episodeIdentityCanonical;
        String str6 = (i14 & 16) != 0 ? "" : str2;
        String str7 = (i14 & 32) != 0 ? null : str3;
        float f11 = (i14 & 64) != 0 ? 0.0f : f10;
        String str8 = (i14 & 128) != 0 ? "" : str4;
        int i17 = (i14 & 256) != 0 ? 0 : i13;
        String str9 = (i14 & 512) != 0 ? "" : str5;
        boolean z5 = (i14 & 1024) != 0 ? false : z;
        if ((i14 & 2048) != 0) {
            i15 = i11;
            i16 = i12;
            episodeIdentityCanonical = EpisodeIdentity.INSTANCE.canonical(i16, i15);
        } else {
            i15 = i11;
            i16 = i12;
            episodeIdentityCanonical = episodeIdentity;
        }
        this(i10, i15, i16, str, str6, str7, f11, str8, i17, str9, z5, episodeIdentityCanonical);
    }
}
