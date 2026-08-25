package com.arflix.tv.ui.screens.details;

import com.arflix.tv.data.model.EpisodeIdentity;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J0\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/ui/screens/details/PendingAutoPlayRequest;", "", "identity", "Lcom/arflix/tv/data/model/EpisodeIdentity;", "startPositionMs", "", "requestedAtMs", "<init>", "(Lcom/arflix/tv/data/model/EpisodeIdentity;Ljava/lang/Long;J)V", "getIdentity", "()Lcom/arflix/tv/data/model/EpisodeIdentity;", "getStartPositionMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRequestedAtMs", "()J", "component1", "component2", "component3", "copy", "(Lcom/arflix/tv/data/model/EpisodeIdentity;Ljava/lang/Long;J)Lcom/arflix/tv/ui/screens/details/PendingAutoPlayRequest;", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class PendingAutoPlayRequest {
    private final EpisodeIdentity identity;
    private final long requestedAtMs;
    private final Long startPositionMs;

    public PendingAutoPlayRequest(EpisodeIdentity episodeIdentity, Long l10, long j10) {
        this.identity = episodeIdentity;
        this.startPositionMs = l10;
        this.requestedAtMs = j10;
    }

    public static /* synthetic */ PendingAutoPlayRequest copy$default(PendingAutoPlayRequest pendingAutoPlayRequest, EpisodeIdentity episodeIdentity, Long l10, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            episodeIdentity = pendingAutoPlayRequest.identity;
        }
        if ((i10 & 2) != 0) {
            l10 = pendingAutoPlayRequest.startPositionMs;
        }
        if ((i10 & 4) != 0) {
            j10 = pendingAutoPlayRequest.requestedAtMs;
        }
        return pendingAutoPlayRequest.copy(episodeIdentity, l10, j10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final EpisodeIdentity getIdentity() {
        return this.identity;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Long getStartPositionMs() {
        return this.startPositionMs;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getRequestedAtMs() {
        return this.requestedAtMs;
    }

    public final PendingAutoPlayRequest copy(EpisodeIdentity identity, Long startPositionMs, long requestedAtMs) {
        return new PendingAutoPlayRequest(identity, startPositionMs, requestedAtMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PendingAutoPlayRequest)) {
            return false;
        }
        PendingAutoPlayRequest pendingAutoPlayRequest = (PendingAutoPlayRequest) other;
        return kotlin.jvm.internal.p.a(this.identity, pendingAutoPlayRequest.identity) && kotlin.jvm.internal.p.a(this.startPositionMs, pendingAutoPlayRequest.startPositionMs) && this.requestedAtMs == pendingAutoPlayRequest.requestedAtMs;
    }

    public final EpisodeIdentity getIdentity() {
        return this.identity;
    }

    public final long getRequestedAtMs() {
        return this.requestedAtMs;
    }

    public final Long getStartPositionMs() {
        return this.startPositionMs;
    }

    public int hashCode() {
        EpisodeIdentity episodeIdentity = this.identity;
        int iHashCode = (episodeIdentity == null ? 0 : episodeIdentity.hashCode()) * 31;
        Long l10 = this.startPositionMs;
        int iHashCode2 = (iHashCode + (l10 != null ? l10.hashCode() : 0)) * 31;
        long j10 = this.requestedAtMs;
        return iHashCode2 + ((int) (j10 ^ (j10 >>> 32)));
    }

    public String toString() {
        EpisodeIdentity episodeIdentity = this.identity;
        Long l10 = this.startPositionMs;
        long j10 = this.requestedAtMs;
        StringBuilder sb2 = new StringBuilder("PendingAutoPlayRequest(identity=");
        sb2.append(episodeIdentity);
        sb2.append(", startPositionMs=");
        sb2.append(l10);
        sb2.append(", requestedAtMs=");
        return a0.c.j(j10, ")", sb2);
    }
}
