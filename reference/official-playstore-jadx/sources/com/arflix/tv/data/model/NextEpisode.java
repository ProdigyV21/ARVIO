package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/data/model/NextEpisode;", "Ljava/io/Serializable;", TtmlNode.ATTR_ID, "", "seasonNumber", "episodeNumber", ContentDisposition.Parameters.Name, "", "overview", "<init>", "(IIILjava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getSeasonNumber", "getEpisodeNumber", "getName", "()Ljava/lang/String;", "getOverview", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class NextEpisode implements Serializable {
    public static final int $stable = 0;
    private final int episodeNumber;
    private final int id;
    private final String name;
    private final String overview;
    private final int seasonNumber;

    public NextEpisode(int i10, int i11, int i12, String str, String str2) {
        this.id = i10;
        this.seasonNumber = i11;
        this.episodeNumber = i12;
        this.name = str;
        this.overview = str2;
    }

    public static /* synthetic */ NextEpisode copy$default(NextEpisode nextEpisode, int i10, int i11, int i12, String str, String str2, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            i10 = nextEpisode.id;
        }
        if ((i13 & 2) != 0) {
            i11 = nextEpisode.seasonNumber;
        }
        if ((i13 & 4) != 0) {
            i12 = nextEpisode.episodeNumber;
        }
        if ((i13 & 8) != 0) {
            str = nextEpisode.name;
        }
        if ((i13 & 16) != 0) {
            str2 = nextEpisode.overview;
        }
        String str3 = str2;
        int i14 = i12;
        return nextEpisode.copy(i10, i11, i14, str, str3);
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
    public final int getEpisodeNumber() {
        return this.episodeNumber;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getOverview() {
        return this.overview;
    }

    public final NextEpisode copy(int id, int seasonNumber, int episodeNumber, String name, String overview) {
        return new NextEpisode(id, seasonNumber, episodeNumber, name, overview);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NextEpisode)) {
            return false;
        }
        NextEpisode nextEpisode = (NextEpisode) other;
        return this.id == nextEpisode.id && this.seasonNumber == nextEpisode.seasonNumber && this.episodeNumber == nextEpisode.episodeNumber && p.a(this.name, nextEpisode.name) && p.a(this.overview, nextEpisode.overview);
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

    public final int getSeasonNumber() {
        return this.seasonNumber;
    }

    public int hashCode() {
        return this.overview.hashCode() + c.c(((((this.id * 31) + this.seasonNumber) * 31) + this.episodeNumber) * 31, 31, this.name);
    }

    public String toString() {
        int i10 = this.id;
        int i11 = this.seasonNumber;
        int i12 = this.episodeNumber;
        String str = this.name;
        String str2 = this.overview;
        StringBuilder sbV = c.v("NextEpisode(id=", i10, ", seasonNumber=", i11, ", episodeNumber=");
        sbV.append(i12);
        sbV.append(", name=");
        sbV.append(str);
        sbV.append(", overview=");
        return a0.c.p(sbV, str2, ")");
    }

    public /* synthetic */ NextEpisode(int i10, int i11, int i12, String str, String str2, int i13, h hVar) {
        this(i10, i11, i12, str, (i13 & 16) != 0 ? "" : str2);
    }
}
