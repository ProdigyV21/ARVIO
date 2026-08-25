package com.arflix.tv.data.api;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J%\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/data/api/MdbWatchedShowRef;", "", "ids", "Lcom/arflix/tv/data/api/MdbIds;", "seasons", "", "Lcom/arflix/tv/data/api/MdbWatchedSeasonRef;", "<init>", "(Lcom/arflix/tv/data/api/MdbIds;Ljava/util/List;)V", "getIds", "()Lcom/arflix/tv/data/api/MdbIds;", "getSeasons", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbWatchedShowRef {
    public static final int $stable = 0;
    private final MdbIds ids;
    private final List<MdbWatchedSeasonRef> seasons;

    public MdbWatchedShowRef(MdbIds mdbIds, List<MdbWatchedSeasonRef> list) {
        this.ids = mdbIds;
        this.seasons = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MdbWatchedShowRef copy$default(MdbWatchedShowRef mdbWatchedShowRef, MdbIds mdbIds, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            mdbIds = mdbWatchedShowRef.ids;
        }
        if ((i10 & 2) != 0) {
            list = mdbWatchedShowRef.seasons;
        }
        return mdbWatchedShowRef.copy(mdbIds, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final MdbIds getIds() {
        return this.ids;
    }

    public final List<MdbWatchedSeasonRef> component2() {
        return this.seasons;
    }

    public final MdbWatchedShowRef copy(MdbIds ids, List<MdbWatchedSeasonRef> seasons) {
        return new MdbWatchedShowRef(ids, seasons);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbWatchedShowRef)) {
            return false;
        }
        MdbWatchedShowRef mdbWatchedShowRef = (MdbWatchedShowRef) other;
        return p.a(this.ids, mdbWatchedShowRef.ids) && p.a(this.seasons, mdbWatchedShowRef.seasons);
    }

    public final MdbIds getIds() {
        return this.ids;
    }

    public final List<MdbWatchedSeasonRef> getSeasons() {
        return this.seasons;
    }

    public int hashCode() {
        int iHashCode = this.ids.hashCode() * 31;
        List<MdbWatchedSeasonRef> list = this.seasons;
        return iHashCode + (list == null ? 0 : list.hashCode());
    }

    public String toString() {
        return "MdbWatchedShowRef(ids=" + this.ids + ", seasons=" + this.seasons + ")";
    }

    public /* synthetic */ MdbWatchedShowRef(MdbIds mdbIds, List list, int i10, kotlin.jvm.internal.h hVar) {
        this(mdbIds, (i10 & 2) != 0 ? null : list);
    }
}
