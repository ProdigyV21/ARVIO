package com.arflix.tv.ui.screens.watchlist;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/SourcePageState;", "", "hasMore", "", "isLoadingMore", "nextOffset", "", "<init>", "(ZZI)V", "getHasMore", "()Z", "getNextOffset", "()I", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class SourcePageState {
    private final boolean hasMore;
    private final boolean isLoadingMore;
    private final int nextOffset;

    public SourcePageState() {
        this(false, false, 0, 7, null);
    }

    public static /* synthetic */ SourcePageState copy$default(SourcePageState sourcePageState, boolean z, boolean z5, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            z = sourcePageState.hasMore;
        }
        if ((i11 & 2) != 0) {
            z5 = sourcePageState.isLoadingMore;
        }
        if ((i11 & 4) != 0) {
            i10 = sourcePageState.nextOffset;
        }
        return sourcePageState.copy(z, z5, i10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getHasMore() {
        return this.hasMore;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsLoadingMore() {
        return this.isLoadingMore;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getNextOffset() {
        return this.nextOffset;
    }

    public final SourcePageState copy(boolean hasMore, boolean isLoadingMore, int nextOffset) {
        return new SourcePageState(hasMore, isLoadingMore, nextOffset);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourcePageState)) {
            return false;
        }
        SourcePageState sourcePageState = (SourcePageState) other;
        return this.hasMore == sourcePageState.hasMore && this.isLoadingMore == sourcePageState.isLoadingMore && this.nextOffset == sourcePageState.nextOffset;
    }

    public final boolean getHasMore() {
        return this.hasMore;
    }

    public final int getNextOffset() {
        return this.nextOffset;
    }

    public int hashCode() {
        return ((((this.hasMore ? 1231 : 1237) * 31) + (this.isLoadingMore ? 1231 : 1237)) * 31) + this.nextOffset;
    }

    public final boolean isLoadingMore() {
        return this.isLoadingMore;
    }

    public String toString() {
        boolean z = this.hasMore;
        boolean z5 = this.isLoadingMore;
        int i10 = this.nextOffset;
        StringBuilder sb2 = new StringBuilder("SourcePageState(hasMore=");
        sb2.append(z);
        sb2.append(", isLoadingMore=");
        sb2.append(z5);
        sb2.append(", nextOffset=");
        return androidx.compose.material3.d.j(i10, ")", sb2);
    }

    public SourcePageState(boolean z, boolean z5, int i10) {
        this.hasMore = z;
        this.isLoadingMore = z5;
        this.nextOffset = i10;
    }

    public /* synthetic */ SourcePageState(boolean z, boolean z5, int i10, int i11, kotlin.jvm.internal.h hVar) {
        this((i11 & 1) != 0 ? false : z, (i11 & 2) != 0 ? false : z5, (i11 & 4) != 0 ? 0 : i10);
    }
}
