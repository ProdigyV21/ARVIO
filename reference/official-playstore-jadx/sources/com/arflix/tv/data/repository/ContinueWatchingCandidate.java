package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/data/repository/ContinueWatchingCandidate;", "", "item", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "lastActivityAt", "", "<init>", "(Lcom/arflix/tv/data/repository/ContinueWatchingItem;Ljava/lang/String;)V", "getItem", "()Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "getLastActivityAt", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class ContinueWatchingCandidate {
    private final ContinueWatchingItem item;
    private final String lastActivityAt;

    public ContinueWatchingCandidate(ContinueWatchingItem continueWatchingItem, String str) {
        this.item = continueWatchingItem;
        this.lastActivityAt = str;
    }

    public static /* synthetic */ ContinueWatchingCandidate copy$default(ContinueWatchingCandidate continueWatchingCandidate, ContinueWatchingItem continueWatchingItem, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            continueWatchingItem = continueWatchingCandidate.item;
        }
        if ((i10 & 2) != 0) {
            str = continueWatchingCandidate.lastActivityAt;
        }
        return continueWatchingCandidate.copy(continueWatchingItem, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ContinueWatchingItem getItem() {
        return this.item;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLastActivityAt() {
        return this.lastActivityAt;
    }

    public final ContinueWatchingCandidate copy(ContinueWatchingItem item, String lastActivityAt) {
        return new ContinueWatchingCandidate(item, lastActivityAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContinueWatchingCandidate)) {
            return false;
        }
        ContinueWatchingCandidate continueWatchingCandidate = (ContinueWatchingCandidate) other;
        return kotlin.jvm.internal.p.a(this.item, continueWatchingCandidate.item) && kotlin.jvm.internal.p.a(this.lastActivityAt, continueWatchingCandidate.lastActivityAt);
    }

    public final ContinueWatchingItem getItem() {
        return this.item;
    }

    public final String getLastActivityAt() {
        return this.lastActivityAt;
    }

    public int hashCode() {
        return this.lastActivityAt.hashCode() + (this.item.hashCode() * 31);
    }

    public String toString() {
        return "ContinueWatchingCandidate(item=" + this.item + ", lastActivityAt=" + this.lastActivityAt + ")";
    }
}
