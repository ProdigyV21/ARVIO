package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bi\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010$\u001a\u00020\u000bHÆ\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u000b\u0010&\u001a\u0004\u0018\u00010\u0007HÆ\u0003Jp\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010(J\u0014\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010,\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010-\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0018\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015¨\u0006."}, d2 = {"Lcom/arflix/tv/data/api/TmdbEpisode;", "", TtmlNode.ATTR_ID, "", "episodeNumber", "seasonNumber", ContentDisposition.Parameters.Name, "", "overview", "stillPath", "voteAverage", "", "runtime", "airDate", "<init>", "(IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;FLjava/lang/Integer;Ljava/lang/String;)V", "getId", "()I", "getEpisodeNumber", "getSeasonNumber", "getName", "()Ljava/lang/String;", "getOverview", "getStillPath", "getVoteAverage", "()F", "getRuntime", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getAirDate", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;FLjava/lang/Integer;Ljava/lang/String;)Lcom/arflix/tv/data/api/TmdbEpisode;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbEpisode {
    public static final int $stable = 0;

    @SerializedName("air_date")
    private final String airDate;

    @SerializedName("episode_number")
    private final int episodeNumber;
    private final int id;
    private final String name;
    private final String overview;
    private final Integer runtime;

    @SerializedName("season_number")
    private final int seasonNumber;

    @SerializedName("still_path")
    private final String stillPath;

    @SerializedName("vote_average")
    private final float voteAverage;

    public TmdbEpisode() {
        this(0, 0, 0, null, null, null, 0.0f, null, null, 511, null);
    }

    public static /* synthetic */ TmdbEpisode copy$default(TmdbEpisode tmdbEpisode, int i10, int i11, int i12, String str, String str2, String str3, float f10, Integer num, String str4, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            i10 = tmdbEpisode.id;
        }
        if ((i13 & 2) != 0) {
            i11 = tmdbEpisode.episodeNumber;
        }
        if ((i13 & 4) != 0) {
            i12 = tmdbEpisode.seasonNumber;
        }
        if ((i13 & 8) != 0) {
            str = tmdbEpisode.name;
        }
        if ((i13 & 16) != 0) {
            str2 = tmdbEpisode.overview;
        }
        if ((i13 & 32) != 0) {
            str3 = tmdbEpisode.stillPath;
        }
        if ((i13 & 64) != 0) {
            f10 = tmdbEpisode.voteAverage;
        }
        if ((i13 & 128) != 0) {
            num = tmdbEpisode.runtime;
        }
        if ((i13 & 256) != 0) {
            str4 = tmdbEpisode.airDate;
        }
        Integer num2 = num;
        String str5 = str4;
        String str6 = str3;
        float f11 = f10;
        String str7 = str2;
        int i14 = i12;
        return tmdbEpisode.copy(i10, i11, i14, str, str7, str6, f11, num2, str5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
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
    public final Integer getRuntime() {
        return this.runtime;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getAirDate() {
        return this.airDate;
    }

    public final TmdbEpisode copy(int id, int episodeNumber, int seasonNumber, String name, String overview, String stillPath, float voteAverage, Integer runtime, String airDate) {
        return new TmdbEpisode(id, episodeNumber, seasonNumber, name, overview, stillPath, voteAverage, runtime, airDate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbEpisode)) {
            return false;
        }
        TmdbEpisode tmdbEpisode = (TmdbEpisode) other;
        return this.id == tmdbEpisode.id && this.episodeNumber == tmdbEpisode.episodeNumber && this.seasonNumber == tmdbEpisode.seasonNumber && p.a(this.name, tmdbEpisode.name) && p.a(this.overview, tmdbEpisode.overview) && p.a(this.stillPath, tmdbEpisode.stillPath) && Float.compare(this.voteAverage, tmdbEpisode.voteAverage) == 0 && p.a(this.runtime, tmdbEpisode.runtime) && p.a(this.airDate, tmdbEpisode.airDate);
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

    public final String getName() {
        return this.name;
    }

    public final String getOverview() {
        return this.overview;
    }

    public final Integer getRuntime() {
        return this.runtime;
    }

    public final int getSeasonNumber() {
        return this.seasonNumber;
    }

    public final String getStillPath() {
        return this.stillPath;
    }

    public final float getVoteAverage() {
        return this.voteAverage;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(((((this.id * 31) + this.episodeNumber) * 31) + this.seasonNumber) * 31, 31, this.name);
        String str = this.overview;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.stillPath;
        int iB = a0.c.b(this.voteAverage, (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31, 31);
        Integer num = this.runtime;
        int iHashCode2 = (iB + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.airDate;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        int i10 = this.id;
        int i11 = this.episodeNumber;
        int i12 = this.seasonNumber;
        String str = this.name;
        String str2 = this.overview;
        String str3 = this.stillPath;
        float f10 = this.voteAverage;
        Integer num = this.runtime;
        String str4 = this.airDate;
        StringBuilder sbV = androidx.compose.foundation.c.v("TmdbEpisode(id=", i10, ", episodeNumber=", i11, ", seasonNumber=");
        sbV.append(i12);
        sbV.append(", name=");
        sbV.append(str);
        sbV.append(", overview=");
        y.a.i(sbV, str2, ", stillPath=", str3, ", voteAverage=");
        sbV.append(f10);
        sbV.append(", runtime=");
        sbV.append(num);
        sbV.append(", airDate=");
        return a0.c.p(sbV, str4, ")");
    }

    public TmdbEpisode(int i10, int i11, int i12, String str, String str2, String str3, float f10, Integer num, String str4) {
        this.id = i10;
        this.episodeNumber = i11;
        this.seasonNumber = i12;
        this.name = str;
        this.overview = str2;
        this.stillPath = str3;
        this.voteAverage = f10;
        this.runtime = num;
        this.airDate = str4;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public /* synthetic */ TmdbEpisode(int r2, int r3, int r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, float r8, java.lang.Integer r9, java.lang.String r10, int r11, kotlin.jvm.internal.h r12) {
        /*
            r1 = this;
            r12 = r11 & 1
            if (r12 == 0) goto L5
            r2 = 0
        L5:
            r12 = r11 & 2
            r0 = 1
            if (r12 == 0) goto Lb
            r3 = r0
        Lb:
            r12 = r11 & 4
            if (r12 == 0) goto L10
            r4 = r0
        L10:
            r12 = r11 & 8
            if (r12 == 0) goto L16
            java.lang.String r5 = ""
        L16:
            r12 = r11 & 16
            r0 = 0
            if (r12 == 0) goto L1c
            r6 = r0
        L1c:
            r12 = r11 & 32
            if (r12 == 0) goto L21
            r7 = r0
        L21:
            r12 = r11 & 64
            if (r12 == 0) goto L26
            r8 = 0
        L26:
            r12 = r11 & 128(0x80, float:1.8E-43)
            if (r12 == 0) goto L2b
            r9 = r0
        L2b:
            r11 = r11 & 256(0x100, float:3.59E-43)
            if (r11 == 0) goto L3a
            r12 = r0
            r10 = r8
            r11 = r9
            r8 = r6
            r9 = r7
            r6 = r4
            r7 = r5
            r4 = r2
            r5 = r3
            r3 = r1
            goto L44
        L3a:
            r12 = r10
            r11 = r9
            r9 = r7
            r10 = r8
            r7 = r5
            r8 = r6
            r5 = r3
            r6 = r4
            r3 = r1
            r4 = r2
        L44:
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.api.TmdbEpisode.<init>(int, int, int, java.lang.String, java.lang.String, java.lang.String, float, java.lang.Integer, java.lang.String, int, kotlin.jvm.internal.h):void");
    }
}
