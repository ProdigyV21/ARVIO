package androidx.media3.exoplayer;

import androidx.media3.common.C;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class LoadingInfo {
    public final long lastRebufferRealtimeMs;
    public final long playbackPositionUs;
    public final float playbackSpeed;

    public static final class Builder {
        private long lastRebufferRealtimeMs;
        private long playbackPositionUs;
        private float playbackSpeed;

        public LoadingInfo build() {
            return new LoadingInfo(this);
        }

        public Builder setLastRebufferRealtimeMs(long j10) {
            ac.b.j(j10 >= 0 || j10 == C.TIME_UNSET);
            this.lastRebufferRealtimeMs = j10;
            return this;
        }

        public Builder setPlaybackPositionUs(long j10) {
            this.playbackPositionUs = j10;
            return this;
        }

        public Builder setPlaybackSpeed(float f10) {
            ac.b.j(f10 > 0.0f || f10 == -3.4028235E38f);
            this.playbackSpeed = f10;
            return this;
        }

        public Builder() {
            this.playbackPositionUs = C.TIME_UNSET;
            this.playbackSpeed = -3.4028235E38f;
            this.lastRebufferRealtimeMs = C.TIME_UNSET;
        }

        private Builder(LoadingInfo loadingInfo) {
            this.playbackPositionUs = loadingInfo.playbackPositionUs;
            this.playbackSpeed = loadingInfo.playbackSpeed;
            this.lastRebufferRealtimeMs = loadingInfo.lastRebufferRealtimeMs;
        }
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LoadingInfo)) {
            return false;
        }
        LoadingInfo loadingInfo = (LoadingInfo) obj;
        return this.playbackPositionUs == loadingInfo.playbackPositionUs && this.playbackSpeed == loadingInfo.playbackSpeed && this.lastRebufferRealtimeMs == loadingInfo.lastRebufferRealtimeMs;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.playbackPositionUs), Float.valueOf(this.playbackSpeed), Long.valueOf(this.lastRebufferRealtimeMs));
    }

    public boolean rebufferedSince(long j10) {
        long j11 = this.lastRebufferRealtimeMs;
        return (j11 == C.TIME_UNSET || j10 == C.TIME_UNSET || j11 < j10) ? false : true;
    }

    private LoadingInfo(Builder builder) {
        this.playbackPositionUs = builder.playbackPositionUs;
        this.playbackSpeed = builder.playbackSpeed;
        this.lastRebufferRealtimeMs = builder.lastRebufferRealtimeMs;
    }
}
