package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J9\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/data/api/TraktSyncResponse;", "", "added", "Lcom/arflix/tv/data/api/TraktSyncCounts;", "deleted", "existing", "notFound", "Lcom/arflix/tv/data/api/TraktSyncNotFound;", "<init>", "(Lcom/arflix/tv/data/api/TraktSyncCounts;Lcom/arflix/tv/data/api/TraktSyncCounts;Lcom/arflix/tv/data/api/TraktSyncCounts;Lcom/arflix/tv/data/api/TraktSyncNotFound;)V", "getAdded", "()Lcom/arflix/tv/data/api/TraktSyncCounts;", "getDeleted", "getExisting", "getNotFound", "()Lcom/arflix/tv/data/api/TraktSyncNotFound;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktSyncResponse {
    public static final int $stable = 0;
    private final TraktSyncCounts added;
    private final TraktSyncCounts deleted;
    private final TraktSyncCounts existing;

    @SerializedName("not_found")
    private final TraktSyncNotFound notFound;

    public TraktSyncResponse(TraktSyncCounts traktSyncCounts, TraktSyncCounts traktSyncCounts2, TraktSyncCounts traktSyncCounts3, TraktSyncNotFound traktSyncNotFound) {
        this.added = traktSyncCounts;
        this.deleted = traktSyncCounts2;
        this.existing = traktSyncCounts3;
        this.notFound = traktSyncNotFound;
    }

    public static /* synthetic */ TraktSyncResponse copy$default(TraktSyncResponse traktSyncResponse, TraktSyncCounts traktSyncCounts, TraktSyncCounts traktSyncCounts2, TraktSyncCounts traktSyncCounts3, TraktSyncNotFound traktSyncNotFound, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            traktSyncCounts = traktSyncResponse.added;
        }
        if ((i10 & 2) != 0) {
            traktSyncCounts2 = traktSyncResponse.deleted;
        }
        if ((i10 & 4) != 0) {
            traktSyncCounts3 = traktSyncResponse.existing;
        }
        if ((i10 & 8) != 0) {
            traktSyncNotFound = traktSyncResponse.notFound;
        }
        return traktSyncResponse.copy(traktSyncCounts, traktSyncCounts2, traktSyncCounts3, traktSyncNotFound);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final TraktSyncCounts getAdded() {
        return this.added;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final TraktSyncCounts getDeleted() {
        return this.deleted;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TraktSyncCounts getExisting() {
        return this.existing;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final TraktSyncNotFound getNotFound() {
        return this.notFound;
    }

    public final TraktSyncResponse copy(TraktSyncCounts added, TraktSyncCounts deleted, TraktSyncCounts existing, TraktSyncNotFound notFound) {
        return new TraktSyncResponse(added, deleted, existing, notFound);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktSyncResponse)) {
            return false;
        }
        TraktSyncResponse traktSyncResponse = (TraktSyncResponse) other;
        return p.a(this.added, traktSyncResponse.added) && p.a(this.deleted, traktSyncResponse.deleted) && p.a(this.existing, traktSyncResponse.existing) && p.a(this.notFound, traktSyncResponse.notFound);
    }

    public final TraktSyncCounts getAdded() {
        return this.added;
    }

    public final TraktSyncCounts getDeleted() {
        return this.deleted;
    }

    public final TraktSyncCounts getExisting() {
        return this.existing;
    }

    public final TraktSyncNotFound getNotFound() {
        return this.notFound;
    }

    public int hashCode() {
        TraktSyncCounts traktSyncCounts = this.added;
        int iHashCode = (traktSyncCounts == null ? 0 : traktSyncCounts.hashCode()) * 31;
        TraktSyncCounts traktSyncCounts2 = this.deleted;
        int iHashCode2 = (iHashCode + (traktSyncCounts2 == null ? 0 : traktSyncCounts2.hashCode())) * 31;
        TraktSyncCounts traktSyncCounts3 = this.existing;
        int iHashCode3 = (iHashCode2 + (traktSyncCounts3 == null ? 0 : traktSyncCounts3.hashCode())) * 31;
        TraktSyncNotFound traktSyncNotFound = this.notFound;
        return iHashCode3 + (traktSyncNotFound != null ? traktSyncNotFound.hashCode() : 0);
    }

    public String toString() {
        return "TraktSyncResponse(added=" + this.added + ", deleted=" + this.deleted + ", existing=" + this.existing + ", notFound=" + this.notFound + ")";
    }
}
