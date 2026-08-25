package com.arflix.tv.data.api;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ2\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/api/TraktEpisodeId;", "", "ids", "Lcom/arflix/tv/data/api/TraktIds;", "season", "", "number", "<init>", "(Lcom/arflix/tv/data/api/TraktIds;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getIds", "()Lcom/arflix/tv/data/api/TraktIds;", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getNumber", "component1", "component2", "component3", "copy", "(Lcom/arflix/tv/data/api/TraktIds;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/arflix/tv/data/api/TraktEpisodeId;", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktEpisodeId {
    public static final int $stable = 0;
    private final TraktIds ids;
    private final Integer number;
    private final Integer season;

    public TraktEpisodeId() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ TraktEpisodeId copy$default(TraktEpisodeId traktEpisodeId, TraktIds traktIds, Integer num, Integer num2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            traktIds = traktEpisodeId.ids;
        }
        if ((i10 & 2) != 0) {
            num = traktEpisodeId.season;
        }
        if ((i10 & 4) != 0) {
            num2 = traktEpisodeId.number;
        }
        return traktEpisodeId.copy(traktIds, num, num2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final TraktIds getIds() {
        return this.ids;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getNumber() {
        return this.number;
    }

    public final TraktEpisodeId copy(TraktIds ids, Integer season, Integer number) {
        return new TraktEpisodeId(ids, season, number);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktEpisodeId)) {
            return false;
        }
        TraktEpisodeId traktEpisodeId = (TraktEpisodeId) other;
        return p.a(this.ids, traktEpisodeId.ids) && p.a(this.season, traktEpisodeId.season) && p.a(this.number, traktEpisodeId.number);
    }

    public final TraktIds getIds() {
        return this.ids;
    }

    public final Integer getNumber() {
        return this.number;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public int hashCode() {
        TraktIds traktIds = this.ids;
        int iHashCode = (traktIds == null ? 0 : traktIds.hashCode()) * 31;
        Integer num = this.season;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.number;
        return iHashCode2 + (num2 != null ? num2.hashCode() : 0);
    }

    public String toString() {
        return "TraktEpisodeId(ids=" + this.ids + ", season=" + this.season + ", number=" + this.number + ")";
    }

    public TraktEpisodeId(TraktIds traktIds, Integer num, Integer num2) {
        this.ids = traktIds;
        this.season = num;
        this.number = num2;
    }

    public /* synthetic */ TraktEpisodeId(TraktIds traktIds, Integer num, Integer num2, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : traktIds, (i10 & 2) != 0 ? null : num, (i10 & 4) != 0 ? null : num2);
    }
}
