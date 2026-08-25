package com.arflix.tv.data.api;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/api/MdbCountResponse;", "", "added", "Lcom/arflix/tv/data/api/MdbCounts;", "removed", "updated", "existing", "<init>", "(Lcom/arflix/tv/data/api/MdbCounts;Lcom/arflix/tv/data/api/MdbCounts;Lcom/arflix/tv/data/api/MdbCounts;Lcom/arflix/tv/data/api/MdbCounts;)V", "getAdded", "()Lcom/arflix/tv/data/api/MdbCounts;", "getRemoved", "getUpdated", "getExisting", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbCountResponse {
    public static final int $stable = 0;
    private final MdbCounts added;
    private final MdbCounts existing;
    private final MdbCounts removed;
    private final MdbCounts updated;

    public MdbCountResponse() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ MdbCountResponse copy$default(MdbCountResponse mdbCountResponse, MdbCounts mdbCounts, MdbCounts mdbCounts2, MdbCounts mdbCounts3, MdbCounts mdbCounts4, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            mdbCounts = mdbCountResponse.added;
        }
        if ((i10 & 2) != 0) {
            mdbCounts2 = mdbCountResponse.removed;
        }
        if ((i10 & 4) != 0) {
            mdbCounts3 = mdbCountResponse.updated;
        }
        if ((i10 & 8) != 0) {
            mdbCounts4 = mdbCountResponse.existing;
        }
        return mdbCountResponse.copy(mdbCounts, mdbCounts2, mdbCounts3, mdbCounts4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final MdbCounts getAdded() {
        return this.added;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final MdbCounts getRemoved() {
        return this.removed;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final MdbCounts getUpdated() {
        return this.updated;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final MdbCounts getExisting() {
        return this.existing;
    }

    public final MdbCountResponse copy(MdbCounts added, MdbCounts removed, MdbCounts updated, MdbCounts existing) {
        return new MdbCountResponse(added, removed, updated, existing);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbCountResponse)) {
            return false;
        }
        MdbCountResponse mdbCountResponse = (MdbCountResponse) other;
        return p.a(this.added, mdbCountResponse.added) && p.a(this.removed, mdbCountResponse.removed) && p.a(this.updated, mdbCountResponse.updated) && p.a(this.existing, mdbCountResponse.existing);
    }

    public final MdbCounts getAdded() {
        return this.added;
    }

    public final MdbCounts getExisting() {
        return this.existing;
    }

    public final MdbCounts getRemoved() {
        return this.removed;
    }

    public final MdbCounts getUpdated() {
        return this.updated;
    }

    public int hashCode() {
        MdbCounts mdbCounts = this.added;
        int iHashCode = (mdbCounts == null ? 0 : mdbCounts.hashCode()) * 31;
        MdbCounts mdbCounts2 = this.removed;
        int iHashCode2 = (iHashCode + (mdbCounts2 == null ? 0 : mdbCounts2.hashCode())) * 31;
        MdbCounts mdbCounts3 = this.updated;
        int iHashCode3 = (iHashCode2 + (mdbCounts3 == null ? 0 : mdbCounts3.hashCode())) * 31;
        MdbCounts mdbCounts4 = this.existing;
        return iHashCode3 + (mdbCounts4 != null ? mdbCounts4.hashCode() : 0);
    }

    public String toString() {
        return "MdbCountResponse(added=" + this.added + ", removed=" + this.removed + ", updated=" + this.updated + ", existing=" + this.existing + ")";
    }

    public MdbCountResponse(MdbCounts mdbCounts, MdbCounts mdbCounts2, MdbCounts mdbCounts3, MdbCounts mdbCounts4) {
        this.added = mdbCounts;
        this.removed = mdbCounts2;
        this.updated = mdbCounts3;
        this.existing = mdbCounts4;
    }

    public /* synthetic */ MdbCountResponse(MdbCounts mdbCounts, MdbCounts mdbCounts2, MdbCounts mdbCounts3, MdbCounts mdbCounts4, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : mdbCounts, (i10 & 2) != 0 ? null : mdbCounts2, (i10 & 4) != 0 ? null : mdbCounts3, (i10 & 8) != 0 ? null : mdbCounts4);
    }
}
