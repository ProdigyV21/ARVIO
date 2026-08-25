package com.arflix.tv.ui.screens.tv.live;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003JE\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveTvBufferProfile;", "", "minBufferMs", "", "maxBufferMs", "bufferForPlaybackMs", "bufferForPlaybackAfterRebufferMs", "targetBufferBytes", "backBufferMs", "<init>", "(IIIIII)V", "getMinBufferMs", "()I", "getMaxBufferMs", "getBufferForPlaybackMs", "getBufferForPlaybackAfterRebufferMs", "getTargetBufferBytes", "getBackBufferMs", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class LiveTvBufferProfile {
    private final int backBufferMs;
    private final int bufferForPlaybackAfterRebufferMs;
    private final int bufferForPlaybackMs;
    private final int maxBufferMs;
    private final int minBufferMs;
    private final int targetBufferBytes;

    public LiveTvBufferProfile(int i10, int i11, int i12, int i13, int i14, int i15) {
        this.minBufferMs = i10;
        this.maxBufferMs = i11;
        this.bufferForPlaybackMs = i12;
        this.bufferForPlaybackAfterRebufferMs = i13;
        this.targetBufferBytes = i14;
        this.backBufferMs = i15;
    }

    public static /* synthetic */ LiveTvBufferProfile copy$default(LiveTvBufferProfile liveTvBufferProfile, int i10, int i11, int i12, int i13, int i14, int i15, int i16, Object obj) {
        if ((i16 & 1) != 0) {
            i10 = liveTvBufferProfile.minBufferMs;
        }
        if ((i16 & 2) != 0) {
            i11 = liveTvBufferProfile.maxBufferMs;
        }
        if ((i16 & 4) != 0) {
            i12 = liveTvBufferProfile.bufferForPlaybackMs;
        }
        if ((i16 & 8) != 0) {
            i13 = liveTvBufferProfile.bufferForPlaybackAfterRebufferMs;
        }
        if ((i16 & 16) != 0) {
            i14 = liveTvBufferProfile.targetBufferBytes;
        }
        if ((i16 & 32) != 0) {
            i15 = liveTvBufferProfile.backBufferMs;
        }
        int i17 = i14;
        int i18 = i15;
        return liveTvBufferProfile.copy(i10, i11, i12, i13, i17, i18);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getMinBufferMs() {
        return this.minBufferMs;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getMaxBufferMs() {
        return this.maxBufferMs;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getBufferForPlaybackMs() {
        return this.bufferForPlaybackMs;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getBufferForPlaybackAfterRebufferMs() {
        return this.bufferForPlaybackAfterRebufferMs;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getTargetBufferBytes() {
        return this.targetBufferBytes;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getBackBufferMs() {
        return this.backBufferMs;
    }

    public final LiveTvBufferProfile copy(int minBufferMs, int maxBufferMs, int bufferForPlaybackMs, int bufferForPlaybackAfterRebufferMs, int targetBufferBytes, int backBufferMs) {
        return new LiveTvBufferProfile(minBufferMs, maxBufferMs, bufferForPlaybackMs, bufferForPlaybackAfterRebufferMs, targetBufferBytes, backBufferMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LiveTvBufferProfile)) {
            return false;
        }
        LiveTvBufferProfile liveTvBufferProfile = (LiveTvBufferProfile) other;
        return this.minBufferMs == liveTvBufferProfile.minBufferMs && this.maxBufferMs == liveTvBufferProfile.maxBufferMs && this.bufferForPlaybackMs == liveTvBufferProfile.bufferForPlaybackMs && this.bufferForPlaybackAfterRebufferMs == liveTvBufferProfile.bufferForPlaybackAfterRebufferMs && this.targetBufferBytes == liveTvBufferProfile.targetBufferBytes && this.backBufferMs == liveTvBufferProfile.backBufferMs;
    }

    public final int getBackBufferMs() {
        return this.backBufferMs;
    }

    public final int getBufferForPlaybackAfterRebufferMs() {
        return this.bufferForPlaybackAfterRebufferMs;
    }

    public final int getBufferForPlaybackMs() {
        return this.bufferForPlaybackMs;
    }

    public final int getMaxBufferMs() {
        return this.maxBufferMs;
    }

    public final int getMinBufferMs() {
        return this.minBufferMs;
    }

    public final int getTargetBufferBytes() {
        return this.targetBufferBytes;
    }

    public int hashCode() {
        return (((((((((this.minBufferMs * 31) + this.maxBufferMs) * 31) + this.bufferForPlaybackMs) * 31) + this.bufferForPlaybackAfterRebufferMs) * 31) + this.targetBufferBytes) * 31) + this.backBufferMs;
    }

    public String toString() {
        int i10 = this.minBufferMs;
        int i11 = this.maxBufferMs;
        int i12 = this.bufferForPlaybackMs;
        int i13 = this.bufferForPlaybackAfterRebufferMs;
        int i14 = this.targetBufferBytes;
        int i15 = this.backBufferMs;
        StringBuilder sbV = androidx.compose.foundation.c.v("LiveTvBufferProfile(minBufferMs=", i10, ", maxBufferMs=", i11, ", bufferForPlaybackMs=");
        androidx.fragment.app.a2.y(sbV, i12, ", bufferForPlaybackAfterRebufferMs=", i13, ", targetBufferBytes=");
        sbV.append(i14);
        sbV.append(", backBufferMs=");
        sbV.append(i15);
        sbV.append(")");
        return sbV.toString();
    }
}
