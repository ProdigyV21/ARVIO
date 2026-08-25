package com.arflix.tv.ui.screens.player;

import androidx.fragment.app.a2;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\u000e\n\u0000\b\u0083\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003JO\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0014\u0010\u001e\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006#"}, d2 = {"Lcom/arflix/tv/ui/screens/player/PlaybackBufferProfile;", "", "minBufferMs", "", "maxBufferMs", "bufferForPlaybackMs", "bufferForPlaybackAfterRebufferMs", "targetBufferBytes", "backBufferMs", "prioritizeTimeOverSizeThresholds", "", "<init>", "(IIIIIIZ)V", "getMinBufferMs", "()I", "getMaxBufferMs", "getBufferForPlaybackMs", "getBufferForPlaybackAfterRebufferMs", "getTargetBufferBytes", "getBackBufferMs", "getPrioritizeTimeOverSizeThresholds", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class PlaybackBufferProfile {
    private final int backBufferMs;
    private final int bufferForPlaybackAfterRebufferMs;
    private final int bufferForPlaybackMs;
    private final int maxBufferMs;
    private final int minBufferMs;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final int targetBufferBytes;

    public PlaybackBufferProfile(int i10, int i11, int i12, int i13, int i14, int i15, boolean z) {
        this.minBufferMs = i10;
        this.maxBufferMs = i11;
        this.bufferForPlaybackMs = i12;
        this.bufferForPlaybackAfterRebufferMs = i13;
        this.targetBufferBytes = i14;
        this.backBufferMs = i15;
        this.prioritizeTimeOverSizeThresholds = z;
    }

    public static /* synthetic */ PlaybackBufferProfile copy$default(PlaybackBufferProfile playbackBufferProfile, int i10, int i11, int i12, int i13, int i14, int i15, boolean z, int i16, Object obj) {
        if ((i16 & 1) != 0) {
            i10 = playbackBufferProfile.minBufferMs;
        }
        if ((i16 & 2) != 0) {
            i11 = playbackBufferProfile.maxBufferMs;
        }
        if ((i16 & 4) != 0) {
            i12 = playbackBufferProfile.bufferForPlaybackMs;
        }
        if ((i16 & 8) != 0) {
            i13 = playbackBufferProfile.bufferForPlaybackAfterRebufferMs;
        }
        if ((i16 & 16) != 0) {
            i14 = playbackBufferProfile.targetBufferBytes;
        }
        if ((i16 & 32) != 0) {
            i15 = playbackBufferProfile.backBufferMs;
        }
        if ((i16 & 64) != 0) {
            z = playbackBufferProfile.prioritizeTimeOverSizeThresholds;
        }
        int i17 = i15;
        boolean z5 = z;
        int i18 = i14;
        int i19 = i12;
        return playbackBufferProfile.copy(i10, i11, i19, i13, i18, i17, z5);
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

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getPrioritizeTimeOverSizeThresholds() {
        return this.prioritizeTimeOverSizeThresholds;
    }

    public final PlaybackBufferProfile copy(int minBufferMs, int maxBufferMs, int bufferForPlaybackMs, int bufferForPlaybackAfterRebufferMs, int targetBufferBytes, int backBufferMs, boolean prioritizeTimeOverSizeThresholds) {
        return new PlaybackBufferProfile(minBufferMs, maxBufferMs, bufferForPlaybackMs, bufferForPlaybackAfterRebufferMs, targetBufferBytes, backBufferMs, prioritizeTimeOverSizeThresholds);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaybackBufferProfile)) {
            return false;
        }
        PlaybackBufferProfile playbackBufferProfile = (PlaybackBufferProfile) other;
        return this.minBufferMs == playbackBufferProfile.minBufferMs && this.maxBufferMs == playbackBufferProfile.maxBufferMs && this.bufferForPlaybackMs == playbackBufferProfile.bufferForPlaybackMs && this.bufferForPlaybackAfterRebufferMs == playbackBufferProfile.bufferForPlaybackAfterRebufferMs && this.targetBufferBytes == playbackBufferProfile.targetBufferBytes && this.backBufferMs == playbackBufferProfile.backBufferMs && this.prioritizeTimeOverSizeThresholds == playbackBufferProfile.prioritizeTimeOverSizeThresholds;
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

    public final boolean getPrioritizeTimeOverSizeThresholds() {
        return this.prioritizeTimeOverSizeThresholds;
    }

    public final int getTargetBufferBytes() {
        return this.targetBufferBytes;
    }

    public int hashCode() {
        return (((((((((((this.minBufferMs * 31) + this.maxBufferMs) * 31) + this.bufferForPlaybackMs) * 31) + this.bufferForPlaybackAfterRebufferMs) * 31) + this.targetBufferBytes) * 31) + this.backBufferMs) * 31) + (this.prioritizeTimeOverSizeThresholds ? 1231 : 1237);
    }

    public String toString() {
        int i10 = this.minBufferMs;
        int i11 = this.maxBufferMs;
        int i12 = this.bufferForPlaybackMs;
        int i13 = this.bufferForPlaybackAfterRebufferMs;
        int i14 = this.targetBufferBytes;
        int i15 = this.backBufferMs;
        boolean z = this.prioritizeTimeOverSizeThresholds;
        StringBuilder sbV = androidx.compose.foundation.c.v("PlaybackBufferProfile(minBufferMs=", i10, ", maxBufferMs=", i11, ", bufferForPlaybackMs=");
        a2.y(sbV, i12, ", bufferForPlaybackAfterRebufferMs=", i13, ", targetBufferBytes=");
        a2.y(sbV, i14, ", backBufferMs=", i15, ", prioritizeTimeOverSizeThresholds=");
        return a0.c.m(")", z, sbV);
    }
}
