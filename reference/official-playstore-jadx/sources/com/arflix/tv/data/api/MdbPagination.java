package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/data/api/MdbPagination;", "", "offset", "", "limit", "hasMore", "", "<init>", "(IIZ)V", "getOffset", "()I", "getLimit", "getHasMore", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbPagination {
    public static final int $stable = 0;

    @SerializedName("has_more")
    private final boolean hasMore;
    private final int limit;
    private final int offset;

    public MdbPagination() {
        this(0, 0, false, 7, null);
    }

    public static /* synthetic */ MdbPagination copy$default(MdbPagination mdbPagination, int i10, int i11, boolean z, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = mdbPagination.offset;
        }
        if ((i12 & 2) != 0) {
            i11 = mdbPagination.limit;
        }
        if ((i12 & 4) != 0) {
            z = mdbPagination.hasMore;
        }
        return mdbPagination.copy(i10, i11, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getOffset() {
        return this.offset;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getLimit() {
        return this.limit;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getHasMore() {
        return this.hasMore;
    }

    public final MdbPagination copy(int offset, int limit, boolean hasMore) {
        return new MdbPagination(offset, limit, hasMore);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbPagination)) {
            return false;
        }
        MdbPagination mdbPagination = (MdbPagination) other;
        return this.offset == mdbPagination.offset && this.limit == mdbPagination.limit && this.hasMore == mdbPagination.hasMore;
    }

    public final boolean getHasMore() {
        return this.hasMore;
    }

    public final int getLimit() {
        return this.limit;
    }

    public final int getOffset() {
        return this.offset;
    }

    public int hashCode() {
        return (((this.offset * 31) + this.limit) * 31) + (this.hasMore ? 1231 : 1237);
    }

    public String toString() {
        return a0.c.m(")", this.hasMore, androidx.compose.foundation.c.v("MdbPagination(offset=", this.offset, ", limit=", this.limit, ", hasMore="));
    }

    public MdbPagination(int i10, int i11, boolean z) {
        this.offset = i10;
        this.limit = i11;
        this.hasMore = z;
    }

    public /* synthetic */ MdbPagination(int i10, int i11, boolean z, int i12, kotlin.jvm.internal.h hVar) {
        this((i12 & 1) != 0 ? 0 : i10, (i12 & 2) != 0 ? 0 : i11, (i12 & 4) != 0 ? false : z);
    }
}
