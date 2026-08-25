package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0001)BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012JZ\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\"J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&HÖ\u0083\u0004J\n\u0010'\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010(\u001a\u00020\u0017HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0014\u0010\u0012R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0015\u0010\u0012R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006*"}, d2 = {"Lcom/arflix/tv/data/model/EpisodeIdentity;", "Ljava/io/Serializable;", "displaySeason", "", "displayEpisode", "tmdbSeason", "tmdbEpisode", "kitsuId", "kitsuEpisode", "armEpisodeId", "<init>", "(IIIILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getDisplaySeason", "()I", "getDisplayEpisode", "getTmdbSeason", "getTmdbEpisode", "getKitsuId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getKitsuEpisode", "getArmEpisodeId", "kitsuQuery", "", "getKitsuQuery", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(IIIILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/arflix/tv/data/model/EpisodeIdentity;", "equals", "", "other", "", "hashCode", "toString", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class EpisodeIdentity implements Serializable {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Integer armEpisodeId;
    private final int displayEpisode;
    private final int displaySeason;
    private final Integer kitsuEpisode;
    private final Integer kitsuId;
    private final int tmdbEpisode;
    private final int tmdbSeason;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/arflix/tv/data/model/EpisodeIdentity$Companion;", "", "<init>", "()V", "canonical", "Lcom/arflix/tv/data/model/EpisodeIdentity;", "season", "", "episode", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final EpisodeIdentity canonical(int season, int episode) {
            return new EpisodeIdentity(season, episode, season, episode, null, null, null, 112, null);
        }

        private Companion() {
        }
    }

    public EpisodeIdentity(int i10, int i11, int i12, int i13, Integer num, Integer num2, Integer num3) {
        this.displaySeason = i10;
        this.displayEpisode = i11;
        this.tmdbSeason = i12;
        this.tmdbEpisode = i13;
        this.kitsuId = num;
        this.kitsuEpisode = num2;
        this.armEpisodeId = num3;
    }

    public static /* synthetic */ EpisodeIdentity copy$default(EpisodeIdentity episodeIdentity, int i10, int i11, int i12, int i13, Integer num, Integer num2, Integer num3, int i14, Object obj) {
        if ((i14 & 1) != 0) {
            i10 = episodeIdentity.displaySeason;
        }
        if ((i14 & 2) != 0) {
            i11 = episodeIdentity.displayEpisode;
        }
        if ((i14 & 4) != 0) {
            i12 = episodeIdentity.tmdbSeason;
        }
        if ((i14 & 8) != 0) {
            i13 = episodeIdentity.tmdbEpisode;
        }
        if ((i14 & 16) != 0) {
            num = episodeIdentity.kitsuId;
        }
        if ((i14 & 32) != 0) {
            num2 = episodeIdentity.kitsuEpisode;
        }
        if ((i14 & 64) != 0) {
            num3 = episodeIdentity.armEpisodeId;
        }
        Integer num4 = num2;
        Integer num5 = num3;
        Integer num6 = num;
        int i15 = i12;
        return episodeIdentity.copy(i10, i11, i15, i13, num6, num4, num5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getDisplaySeason() {
        return this.displaySeason;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getDisplayEpisode() {
        return this.displayEpisode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getTmdbSeason() {
        return this.tmdbSeason;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getTmdbEpisode() {
        return this.tmdbEpisode;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getKitsuId() {
        return this.kitsuId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getKitsuEpisode() {
        return this.kitsuEpisode;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getArmEpisodeId() {
        return this.armEpisodeId;
    }

    public final EpisodeIdentity copy(int displaySeason, int displayEpisode, int tmdbSeason, int tmdbEpisode, Integer kitsuId, Integer kitsuEpisode, Integer armEpisodeId) {
        return new EpisodeIdentity(displaySeason, displayEpisode, tmdbSeason, tmdbEpisode, kitsuId, kitsuEpisode, armEpisodeId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EpisodeIdentity)) {
            return false;
        }
        EpisodeIdentity episodeIdentity = (EpisodeIdentity) other;
        return this.displaySeason == episodeIdentity.displaySeason && this.displayEpisode == episodeIdentity.displayEpisode && this.tmdbSeason == episodeIdentity.tmdbSeason && this.tmdbEpisode == episodeIdentity.tmdbEpisode && p.a(this.kitsuId, episodeIdentity.kitsuId) && p.a(this.kitsuEpisode, episodeIdentity.kitsuEpisode) && p.a(this.armEpisodeId, episodeIdentity.armEpisodeId);
    }

    public final Integer getArmEpisodeId() {
        return this.armEpisodeId;
    }

    public final int getDisplayEpisode() {
        return this.displayEpisode;
    }

    public final int getDisplaySeason() {
        return this.displaySeason;
    }

    public final Integer getKitsuEpisode() {
        return this.kitsuEpisode;
    }

    public final Integer getKitsuId() {
        return this.kitsuId;
    }

    public final String getKitsuQuery() {
        Integer num = this.kitsuId;
        if (num != null) {
            int iIntValue = num.intValue();
            Integer num2 = this.kitsuEpisode;
            if (num2 != null) {
                return c.n(iIntValue, num2.intValue(), "kitsu:", ":");
            }
        }
        return null;
    }

    public final int getTmdbEpisode() {
        return this.tmdbEpisode;
    }

    public final int getTmdbSeason() {
        return this.tmdbSeason;
    }

    public int hashCode() {
        int i10 = ((((((this.displaySeason * 31) + this.displayEpisode) * 31) + this.tmdbSeason) * 31) + this.tmdbEpisode) * 31;
        Integer num = this.kitsuId;
        int iHashCode = (i10 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.kitsuEpisode;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.armEpisodeId;
        return iHashCode2 + (num3 != null ? num3.hashCode() : 0);
    }

    public String toString() {
        int i10 = this.displaySeason;
        int i11 = this.displayEpisode;
        int i12 = this.tmdbSeason;
        int i13 = this.tmdbEpisode;
        Integer num = this.kitsuId;
        Integer num2 = this.kitsuEpisode;
        Integer num3 = this.armEpisodeId;
        StringBuilder sbV = c.v("EpisodeIdentity(displaySeason=", i10, ", displayEpisode=", i11, ", tmdbSeason=");
        a2.y(sbV, i12, ", tmdbEpisode=", i13, ", kitsuId=");
        a2.z(sbV, num, ", kitsuEpisode=", num2, ", armEpisodeId=");
        sbV.append(num3);
        sbV.append(")");
        return sbV.toString();
    }

    public /* synthetic */ EpisodeIdentity(int i10, int i11, int i12, int i13, Integer num, Integer num2, Integer num3, int i14, h hVar) {
        this(i10, i11, i12, i13, (i14 & 16) != 0 ? null : num, (i14 & 32) != 0 ? null : num2, (i14 & 64) != 0 ? null : num3);
    }
}
