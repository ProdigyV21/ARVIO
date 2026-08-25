package com.arflix.tv.data.repository.sync;

import androidx.compose.material3.d;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\bHÆ\u0003J/\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/repository/sync/RemoteWatchlistResult;", "", "connected", "", "items", "", "Lcom/arflix/tv/data/model/MediaItem;", "rawCount", "", "<init>", "(ZLjava/util/List;I)V", "getConnected", "()Z", "getItems", "()Ljava/util/List;", "getRawCount", "()I", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class RemoteWatchlistResult {
    public static final int $stable = 0;
    private final boolean connected;
    private final List<MediaItem> items;
    private final int rawCount;

    public RemoteWatchlistResult(boolean z, List<MediaItem> list, int i10) {
        this.connected = z;
        this.items = list;
        this.rawCount = i10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RemoteWatchlistResult copy$default(RemoteWatchlistResult remoteWatchlistResult, boolean z, List list, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            z = remoteWatchlistResult.connected;
        }
        if ((i11 & 2) != 0) {
            list = remoteWatchlistResult.items;
        }
        if ((i11 & 4) != 0) {
            i10 = remoteWatchlistResult.rawCount;
        }
        return remoteWatchlistResult.copy(z, list, i10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getConnected() {
        return this.connected;
    }

    public final List<MediaItem> component2() {
        return this.items;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getRawCount() {
        return this.rawCount;
    }

    public final RemoteWatchlistResult copy(boolean connected, List<MediaItem> items, int rawCount) {
        return new RemoteWatchlistResult(connected, items, rawCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RemoteWatchlistResult)) {
            return false;
        }
        RemoteWatchlistResult remoteWatchlistResult = (RemoteWatchlistResult) other;
        return this.connected == remoteWatchlistResult.connected && p.a(this.items, remoteWatchlistResult.items) && this.rawCount == remoteWatchlistResult.rawCount;
    }

    public final boolean getConnected() {
        return this.connected;
    }

    public final List<MediaItem> getItems() {
        return this.items;
    }

    public final int getRawCount() {
        return this.rawCount;
    }

    public int hashCode() {
        int i10 = (this.connected ? 1231 : 1237) * 31;
        List<MediaItem> list = this.items;
        return ((i10 + (list == null ? 0 : list.hashCode())) * 31) + this.rawCount;
    }

    public String toString() {
        boolean z = this.connected;
        List<MediaItem> list = this.items;
        int i10 = this.rawCount;
        StringBuilder sb2 = new StringBuilder("RemoteWatchlistResult(connected=");
        sb2.append(z);
        sb2.append(", items=");
        sb2.append(list);
        sb2.append(", rawCount=");
        return d.j(i10, ")", sb2);
    }
}
