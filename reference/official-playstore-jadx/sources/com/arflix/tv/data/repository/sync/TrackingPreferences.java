package com.arflix.tv.data.repository.sync;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0010JD\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010¨\u0006 "}, d2 = {"Lcom/arflix/tv/data/repository/sync/TrackingPreferences;", "", "watchlistReadMode", "Lcom/arflix/tv/data/repository/sync/TrackingReadMode;", "continueWatchingReadMode", "watchedReadMode", "writeToTrakt", "", "writeToSimkl", "<init>", "(Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getWatchlistReadMode", "()Lcom/arflix/tv/data/repository/sync/TrackingReadMode;", "getContinueWatchingReadMode", "getWatchedReadMode", "getWriteToTrakt", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getWriteToSimkl", "component1", "component2", "component3", "component4", "component5", "copy", "(Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/arflix/tv/data/repository/sync/TrackingPreferences;", "equals", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TrackingPreferences {
    public static final int $stable = 0;
    private final TrackingReadMode continueWatchingReadMode;
    private final TrackingReadMode watchedReadMode;
    private final TrackingReadMode watchlistReadMode;
    private final Boolean writeToSimkl;
    private final Boolean writeToTrakt;

    public TrackingPreferences() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ TrackingPreferences copy$default(TrackingPreferences trackingPreferences, TrackingReadMode trackingReadMode, TrackingReadMode trackingReadMode2, TrackingReadMode trackingReadMode3, Boolean bool, Boolean bool2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            trackingReadMode = trackingPreferences.watchlistReadMode;
        }
        if ((i10 & 2) != 0) {
            trackingReadMode2 = trackingPreferences.continueWatchingReadMode;
        }
        if ((i10 & 4) != 0) {
            trackingReadMode3 = trackingPreferences.watchedReadMode;
        }
        if ((i10 & 8) != 0) {
            bool = trackingPreferences.writeToTrakt;
        }
        if ((i10 & 16) != 0) {
            bool2 = trackingPreferences.writeToSimkl;
        }
        Boolean bool3 = bool2;
        TrackingReadMode trackingReadMode4 = trackingReadMode3;
        return trackingPreferences.copy(trackingReadMode, trackingReadMode2, trackingReadMode4, bool, bool3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final TrackingReadMode getWatchlistReadMode() {
        return this.watchlistReadMode;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final TrackingReadMode getContinueWatchingReadMode() {
        return this.continueWatchingReadMode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TrackingReadMode getWatchedReadMode() {
        return this.watchedReadMode;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Boolean getWriteToTrakt() {
        return this.writeToTrakt;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Boolean getWriteToSimkl() {
        return this.writeToSimkl;
    }

    public final TrackingPreferences copy(TrackingReadMode watchlistReadMode, TrackingReadMode continueWatchingReadMode, TrackingReadMode watchedReadMode, Boolean writeToTrakt, Boolean writeToSimkl) {
        return new TrackingPreferences(watchlistReadMode, continueWatchingReadMode, watchedReadMode, writeToTrakt, writeToSimkl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrackingPreferences)) {
            return false;
        }
        TrackingPreferences trackingPreferences = (TrackingPreferences) other;
        return this.watchlistReadMode == trackingPreferences.watchlistReadMode && this.continueWatchingReadMode == trackingPreferences.continueWatchingReadMode && this.watchedReadMode == trackingPreferences.watchedReadMode && p.a(this.writeToTrakt, trackingPreferences.writeToTrakt) && p.a(this.writeToSimkl, trackingPreferences.writeToSimkl);
    }

    public final TrackingReadMode getContinueWatchingReadMode() {
        return this.continueWatchingReadMode;
    }

    public final TrackingReadMode getWatchedReadMode() {
        return this.watchedReadMode;
    }

    public final TrackingReadMode getWatchlistReadMode() {
        return this.watchlistReadMode;
    }

    public final Boolean getWriteToSimkl() {
        return this.writeToSimkl;
    }

    public final Boolean getWriteToTrakt() {
        return this.writeToTrakt;
    }

    public int hashCode() {
        int iHashCode = (this.watchedReadMode.hashCode() + ((this.continueWatchingReadMode.hashCode() + (this.watchlistReadMode.hashCode() * 31)) * 31)) * 31;
        Boolean bool = this.writeToTrakt;
        int iHashCode2 = (iHashCode + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.writeToSimkl;
        return iHashCode2 + (bool2 != null ? bool2.hashCode() : 0);
    }

    public String toString() {
        return "TrackingPreferences(watchlistReadMode=" + this.watchlistReadMode + ", continueWatchingReadMode=" + this.continueWatchingReadMode + ", watchedReadMode=" + this.watchedReadMode + ", writeToTrakt=" + this.writeToTrakt + ", writeToSimkl=" + this.writeToSimkl + ")";
    }

    public TrackingPreferences(TrackingReadMode trackingReadMode, TrackingReadMode trackingReadMode2, TrackingReadMode trackingReadMode3, Boolean bool, Boolean bool2) {
        this.watchlistReadMode = trackingReadMode;
        this.continueWatchingReadMode = trackingReadMode2;
        this.watchedReadMode = trackingReadMode3;
        this.writeToTrakt = bool;
        this.writeToSimkl = bool2;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public /* synthetic */ TrackingPreferences(com.arflix.tv.data.repository.sync.TrackingReadMode r2, com.arflix.tv.data.repository.sync.TrackingReadMode r3, com.arflix.tv.data.repository.sync.TrackingReadMode r4, java.lang.Boolean r5, java.lang.Boolean r6, int r7, kotlin.jvm.internal.h r8) {
        /*
            r1 = this;
            r8 = r7 & 1
            if (r8 == 0) goto L6
            com.arflix.tv.data.repository.sync.TrackingReadMode r2 = com.arflix.tv.data.repository.sync.TrackingReadMode.AUTO
        L6:
            r8 = r7 & 2
            if (r8 == 0) goto Lc
            com.arflix.tv.data.repository.sync.TrackingReadMode r3 = com.arflix.tv.data.repository.sync.TrackingReadMode.AUTO
        Lc:
            r8 = r7 & 4
            if (r8 == 0) goto L12
            com.arflix.tv.data.repository.sync.TrackingReadMode r4 = com.arflix.tv.data.repository.sync.TrackingReadMode.AUTO
        L12:
            r8 = r7 & 8
            r0 = 0
            if (r8 == 0) goto L18
            r5 = r0
        L18:
            r7 = r7 & 16
            if (r7 == 0) goto L23
            r8 = r0
            r6 = r4
            r7 = r5
            r4 = r2
            r5 = r3
            r3 = r1
            goto L29
        L23:
            r8 = r6
            r7 = r5
            r5 = r3
            r6 = r4
            r3 = r1
            r4 = r2
        L29:
            r3.<init>(r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.TrackingPreferences.<init>(com.arflix.tv.data.repository.sync.TrackingReadMode, com.arflix.tv.data.repository.sync.TrackingReadMode, com.arflix.tv.data.repository.sync.TrackingReadMode, java.lang.Boolean, java.lang.Boolean, int, kotlin.jvm.internal.h):void");
    }
}
