package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/data/api/SimklSyncResponse;", "", "added", "Lcom/arflix/tv/data/api/SimklSyncCount;", "deleted", "notFound", "<init>", "(Lcom/arflix/tv/data/api/SimklSyncCount;Lcom/arflix/tv/data/api/SimklSyncCount;Lcom/arflix/tv/data/api/SimklSyncCount;)V", "getAdded", "()Lcom/arflix/tv/data/api/SimklSyncCount;", "getDeleted", "getNotFound", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklSyncResponse {
    public static final int $stable = 0;

    @SerializedName("added")
    private final SimklSyncCount added;

    @SerializedName("deleted")
    private final SimklSyncCount deleted;

    @SerializedName("not_found")
    private final SimklSyncCount notFound;

    public SimklSyncResponse() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ SimklSyncResponse copy$default(SimklSyncResponse simklSyncResponse, SimklSyncCount simklSyncCount, SimklSyncCount simklSyncCount2, SimklSyncCount simklSyncCount3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            simklSyncCount = simklSyncResponse.added;
        }
        if ((i10 & 2) != 0) {
            simklSyncCount2 = simklSyncResponse.deleted;
        }
        if ((i10 & 4) != 0) {
            simklSyncCount3 = simklSyncResponse.notFound;
        }
        return simklSyncResponse.copy(simklSyncCount, simklSyncCount2, simklSyncCount3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SimklSyncCount getAdded() {
        return this.added;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final SimklSyncCount getDeleted() {
        return this.deleted;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final SimklSyncCount getNotFound() {
        return this.notFound;
    }

    public final SimklSyncResponse copy(SimklSyncCount added, SimklSyncCount deleted, SimklSyncCount notFound) {
        return new SimklSyncResponse(added, deleted, notFound);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklSyncResponse)) {
            return false;
        }
        SimklSyncResponse simklSyncResponse = (SimklSyncResponse) other;
        return p.a(this.added, simklSyncResponse.added) && p.a(this.deleted, simklSyncResponse.deleted) && p.a(this.notFound, simklSyncResponse.notFound);
    }

    public final SimklSyncCount getAdded() {
        return this.added;
    }

    public final SimklSyncCount getDeleted() {
        return this.deleted;
    }

    public final SimklSyncCount getNotFound() {
        return this.notFound;
    }

    public int hashCode() {
        SimklSyncCount simklSyncCount = this.added;
        int iHashCode = (simklSyncCount == null ? 0 : simklSyncCount.hashCode()) * 31;
        SimklSyncCount simklSyncCount2 = this.deleted;
        int iHashCode2 = (iHashCode + (simklSyncCount2 == null ? 0 : simklSyncCount2.hashCode())) * 31;
        SimklSyncCount simklSyncCount3 = this.notFound;
        return iHashCode2 + (simklSyncCount3 != null ? simklSyncCount3.hashCode() : 0);
    }

    public String toString() {
        return "SimklSyncResponse(added=" + this.added + ", deleted=" + this.deleted + ", notFound=" + this.notFound + ")";
    }

    public SimklSyncResponse(SimklSyncCount simklSyncCount, SimklSyncCount simklSyncCount2, SimklSyncCount simklSyncCount3) {
        this.added = simklSyncCount;
        this.deleted = simklSyncCount2;
        this.notFound = simklSyncCount3;
    }

    public /* synthetic */ SimklSyncResponse(SimklSyncCount simklSyncCount, SimklSyncCount simklSyncCount2, SimklSyncCount simklSyncCount3, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : simklSyncCount, (i10 & 2) != 0 ? null : simklSyncCount2, (i10 & 4) != 0 ? null : simklSyncCount3);
    }
}
