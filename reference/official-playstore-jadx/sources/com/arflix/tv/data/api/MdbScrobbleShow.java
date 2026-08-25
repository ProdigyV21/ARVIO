package com.arflix.tv.data.api;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/api/MdbScrobbleShow;", "", "ids", "Lcom/arflix/tv/data/api/MdbIds;", "season", "Lcom/arflix/tv/data/api/MdbScrobbleSeason;", "<init>", "(Lcom/arflix/tv/data/api/MdbIds;Lcom/arflix/tv/data/api/MdbScrobbleSeason;)V", "getIds", "()Lcom/arflix/tv/data/api/MdbIds;", "getSeason", "()Lcom/arflix/tv/data/api/MdbScrobbleSeason;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbScrobbleShow {
    public static final int $stable = 0;
    private final MdbIds ids;
    private final MdbScrobbleSeason season;

    public MdbScrobbleShow(MdbIds mdbIds, MdbScrobbleSeason mdbScrobbleSeason) {
        this.ids = mdbIds;
        this.season = mdbScrobbleSeason;
    }

    public static /* synthetic */ MdbScrobbleShow copy$default(MdbScrobbleShow mdbScrobbleShow, MdbIds mdbIds, MdbScrobbleSeason mdbScrobbleSeason, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            mdbIds = mdbScrobbleShow.ids;
        }
        if ((i10 & 2) != 0) {
            mdbScrobbleSeason = mdbScrobbleShow.season;
        }
        return mdbScrobbleShow.copy(mdbIds, mdbScrobbleSeason);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final MdbIds getIds() {
        return this.ids;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final MdbScrobbleSeason getSeason() {
        return this.season;
    }

    public final MdbScrobbleShow copy(MdbIds ids, MdbScrobbleSeason season) {
        return new MdbScrobbleShow(ids, season);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbScrobbleShow)) {
            return false;
        }
        MdbScrobbleShow mdbScrobbleShow = (MdbScrobbleShow) other;
        return p.a(this.ids, mdbScrobbleShow.ids) && p.a(this.season, mdbScrobbleShow.season);
    }

    public final MdbIds getIds() {
        return this.ids;
    }

    public final MdbScrobbleSeason getSeason() {
        return this.season;
    }

    public int hashCode() {
        return this.season.hashCode() + (this.ids.hashCode() * 31);
    }

    public String toString() {
        return "MdbScrobbleShow(ids=" + this.ids + ", season=" + this.season + ")";
    }
}
