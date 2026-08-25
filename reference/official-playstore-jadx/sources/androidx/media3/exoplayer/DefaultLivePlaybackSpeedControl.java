package androidx.media3.exoplayer;

import android.os.SystemClock;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultLivePlaybackSpeedControl implements LivePlaybackSpeedControl {
    public static final float DEFAULT_FALLBACK_MAX_PLAYBACK_SPEED = 1.03f;
    public static final float DEFAULT_FALLBACK_MIN_PLAYBACK_SPEED = 0.97f;
    public static final long DEFAULT_MAX_LIVE_OFFSET_ERROR_MS_FOR_UNIT_SPEED = 20;
    public static final float DEFAULT_MIN_POSSIBLE_LIVE_OFFSET_SMOOTHING_FACTOR = 0.999f;
    public static final long DEFAULT_MIN_UPDATE_INTERVAL_MS = 1000;
    public static final float DEFAULT_PROPORTIONAL_CONTROL_FACTOR = 0.1f;
    public static final long DEFAULT_TARGET_LIVE_OFFSET_INCREMENT_ON_REBUFFER_MS = 500;
    private float adjustedPlaybackSpeed;
    private long currentTargetLiveOffsetUs;
    private final float fallbackMaxPlaybackSpeed;
    private final float fallbackMinPlaybackSpeed;
    private long idealTargetLiveOffsetUs;
    private long lastPlaybackSpeedUpdateMs;
    private final long maxLiveOffsetErrorUsForUnitSpeed;
    private float maxPlaybackSpeed;
    private long maxTargetLiveOffsetUs;
    private long mediaConfigurationTargetLiveOffsetUs;
    private float minPlaybackSpeed;
    private final float minPossibleLiveOffsetSmoothingFactor;
    private long minTargetLiveOffsetUs;
    private final long minUpdateIntervalMs;
    private final float proportionalControlFactor;
    private long smoothedMinPossibleLiveOffsetDeviationUs;
    private long smoothedMinPossibleLiveOffsetUs;
    private long targetLiveOffsetOverrideUs;
    private final long targetLiveOffsetRebufferDeltaUs;

    public static final class Builder {
        private float fallbackMinPlaybackSpeed = 0.97f;
        private float fallbackMaxPlaybackSpeed = 1.03f;
        private long minUpdateIntervalMs = 1000;
        private float proportionalControlFactorUs = 1.0E-7f;
        private long maxLiveOffsetErrorUsForUnitSpeed = Util.msToUs(20);
        private long targetLiveOffsetIncrementOnRebufferUs = Util.msToUs(500);
        private float minPossibleLiveOffsetSmoothingFactor = 0.999f;

        public DefaultLivePlaybackSpeedControl build() {
            return new DefaultLivePlaybackSpeedControl(this.fallbackMinPlaybackSpeed, this.fallbackMaxPlaybackSpeed, this.minUpdateIntervalMs, this.proportionalControlFactorUs, this.maxLiveOffsetErrorUsForUnitSpeed, this.targetLiveOffsetIncrementOnRebufferUs, this.minPossibleLiveOffsetSmoothingFactor);
        }

        public Builder setFallbackMaxPlaybackSpeed(float f10) {
            ac.b.j(f10 >= 1.0f);
            this.fallbackMaxPlaybackSpeed = f10;
            return this;
        }

        public Builder setFallbackMinPlaybackSpeed(float f10) {
            ac.b.j(0.0f < f10 && f10 <= 1.0f);
            this.fallbackMinPlaybackSpeed = f10;
            return this;
        }

        public Builder setMaxLiveOffsetErrorMsForUnitSpeed(long j10) {
            ac.b.j(j10 > 0);
            this.maxLiveOffsetErrorUsForUnitSpeed = Util.msToUs(j10);
            return this;
        }

        public Builder setMinPossibleLiveOffsetSmoothingFactor(float f10) {
            ac.b.j(f10 >= 0.0f && f10 < 1.0f);
            this.minPossibleLiveOffsetSmoothingFactor = f10;
            return this;
        }

        public Builder setMinUpdateIntervalMs(long j10) {
            ac.b.j(j10 > 0);
            this.minUpdateIntervalMs = j10;
            return this;
        }

        public Builder setProportionalControlFactor(float f10) {
            ac.b.j(f10 > 0.0f);
            this.proportionalControlFactorUs = f10 / 1000000.0f;
            return this;
        }

        public Builder setTargetLiveOffsetIncrementOnRebufferMs(long j10) {
            ac.b.j(j10 >= 0);
            this.targetLiveOffsetIncrementOnRebufferUs = Util.msToUs(j10);
            return this;
        }
    }

    private void adjustTargetLiveOffsetUs(long j10) {
        long j11 = (this.smoothedMinPossibleLiveOffsetDeviationUs * 3) + this.smoothedMinPossibleLiveOffsetUs;
        if (this.currentTargetLiveOffsetUs <= j11) {
            long jConstrainValue = Util.constrainValue(j10 - ((long) (Math.max(0.0f, this.adjustedPlaybackSpeed - 1.0f) / this.proportionalControlFactor)), this.currentTargetLiveOffsetUs, j11);
            this.currentTargetLiveOffsetUs = jConstrainValue;
            long j12 = this.maxTargetLiveOffsetUs;
            if (j12 == C.TIME_UNSET || jConstrainValue <= j12) {
                return;
            }
            this.currentTargetLiveOffsetUs = j12;
            return;
        }
        float fMsToUs = Util.msToUs(this.minUpdateIntervalMs);
        long[] jArr = {j11, this.idealTargetLiveOffsetUs, this.currentTargetLiveOffsetUs - (((long) ((this.adjustedPlaybackSpeed - 1.0f) * fMsToUs)) + ((long) ((this.maxPlaybackSpeed - 1.0f) * fMsToUs)))};
        long j13 = jArr[0];
        for (int i10 = 1; i10 < 3; i10++) {
            long j14 = jArr[i10];
            if (j14 > j13) {
                j13 = j14;
            }
        }
        this.currentTargetLiveOffsetUs = j13;
    }

    private void maybeResetTargetLiveOffsetUs() {
        long j10;
        long j11 = this.mediaConfigurationTargetLiveOffsetUs;
        if (j11 != C.TIME_UNSET) {
            j10 = this.targetLiveOffsetOverrideUs;
            if (j10 == C.TIME_UNSET) {
                long j12 = this.minTargetLiveOffsetUs;
                if (j12 != C.TIME_UNSET && j11 < j12) {
                    j11 = j12;
                }
                j10 = this.maxTargetLiveOffsetUs;
                if (j10 == C.TIME_UNSET || j11 <= j10) {
                    j10 = j11;
                }
            }
        } else {
            j10 = -9223372036854775807L;
        }
        if (this.idealTargetLiveOffsetUs == j10) {
            return;
        }
        this.idealTargetLiveOffsetUs = j10;
        this.currentTargetLiveOffsetUs = j10;
        this.smoothedMinPossibleLiveOffsetUs = C.TIME_UNSET;
        this.smoothedMinPossibleLiveOffsetDeviationUs = C.TIME_UNSET;
        this.lastPlaybackSpeedUpdateMs = C.TIME_UNSET;
    }

    private static long smooth(long j10, long j11, float f10) {
        return (long) (((1.0f - f10) * j11) + (j10 * f10));
    }

    private void updateSmoothedMinPossibleLiveOffsetUs(long j10, long j11) {
        long j12 = j10 - j11;
        long j13 = this.smoothedMinPossibleLiveOffsetUs;
        if (j13 == C.TIME_UNSET) {
            this.smoothedMinPossibleLiveOffsetUs = j12;
            this.smoothedMinPossibleLiveOffsetDeviationUs = 0L;
        } else {
            long jMax = Math.max(j12, smooth(j13, j12, this.minPossibleLiveOffsetSmoothingFactor));
            this.smoothedMinPossibleLiveOffsetUs = jMax;
            this.smoothedMinPossibleLiveOffsetDeviationUs = smooth(this.smoothedMinPossibleLiveOffsetDeviationUs, Math.abs(j12 - jMax), this.minPossibleLiveOffsetSmoothingFactor);
        }
    }

    @Override // androidx.media3.exoplayer.LivePlaybackSpeedControl
    public float getAdjustedPlaybackSpeed(long j10, long j11) {
        if (this.mediaConfigurationTargetLiveOffsetUs == C.TIME_UNSET) {
            return 1.0f;
        }
        updateSmoothedMinPossibleLiveOffsetUs(j10, j11);
        if (this.lastPlaybackSpeedUpdateMs != C.TIME_UNSET && SystemClock.elapsedRealtime() - this.lastPlaybackSpeedUpdateMs < this.minUpdateIntervalMs) {
            return this.adjustedPlaybackSpeed;
        }
        this.lastPlaybackSpeedUpdateMs = SystemClock.elapsedRealtime();
        adjustTargetLiveOffsetUs(j10);
        long j12 = j10 - this.currentTargetLiveOffsetUs;
        if (Math.abs(j12) < this.maxLiveOffsetErrorUsForUnitSpeed) {
            this.adjustedPlaybackSpeed = 1.0f;
        } else {
            this.adjustedPlaybackSpeed = Util.constrainValue((this.proportionalControlFactor * j12) + 1.0f, this.minPlaybackSpeed, this.maxPlaybackSpeed);
        }
        return this.adjustedPlaybackSpeed;
    }

    @Override // androidx.media3.exoplayer.LivePlaybackSpeedControl
    public long getTargetLiveOffsetUs() {
        return this.currentTargetLiveOffsetUs;
    }

    @Override // androidx.media3.exoplayer.LivePlaybackSpeedControl
    public void notifyRebuffer() {
        long j10 = this.currentTargetLiveOffsetUs;
        if (j10 == C.TIME_UNSET) {
            return;
        }
        long j11 = j10 + this.targetLiveOffsetRebufferDeltaUs;
        this.currentTargetLiveOffsetUs = j11;
        long j12 = this.maxTargetLiveOffsetUs;
        if (j12 != C.TIME_UNSET && j11 > j12) {
            this.currentTargetLiveOffsetUs = j12;
        }
        this.lastPlaybackSpeedUpdateMs = C.TIME_UNSET;
    }

    @Override // androidx.media3.exoplayer.LivePlaybackSpeedControl
    public void setLiveConfiguration(MediaItem.LiveConfiguration liveConfiguration) {
        this.mediaConfigurationTargetLiveOffsetUs = Util.msToUs(liveConfiguration.targetOffsetMs);
        this.minTargetLiveOffsetUs = Util.msToUs(liveConfiguration.minOffsetMs);
        this.maxTargetLiveOffsetUs = Util.msToUs(liveConfiguration.maxOffsetMs);
        float f10 = liveConfiguration.minPlaybackSpeed;
        if (f10 == -3.4028235E38f) {
            f10 = this.fallbackMinPlaybackSpeed;
        }
        this.minPlaybackSpeed = f10;
        float f11 = liveConfiguration.maxPlaybackSpeed;
        if (f11 == -3.4028235E38f) {
            f11 = this.fallbackMaxPlaybackSpeed;
        }
        this.maxPlaybackSpeed = f11;
        if (f10 == 1.0f && f11 == 1.0f) {
            this.mediaConfigurationTargetLiveOffsetUs = C.TIME_UNSET;
        }
        maybeResetTargetLiveOffsetUs();
    }

    @Override // androidx.media3.exoplayer.LivePlaybackSpeedControl
    public void setTargetLiveOffsetOverrideUs(long j10) {
        this.targetLiveOffsetOverrideUs = j10;
        maybeResetTargetLiveOffsetUs();
    }

    private DefaultLivePlaybackSpeedControl(float f10, float f11, long j10, float f12, long j11, long j12, float f13) {
        this.fallbackMinPlaybackSpeed = f10;
        this.fallbackMaxPlaybackSpeed = f11;
        this.minUpdateIntervalMs = j10;
        this.proportionalControlFactor = f12;
        this.maxLiveOffsetErrorUsForUnitSpeed = j11;
        this.targetLiveOffsetRebufferDeltaUs = j12;
        this.minPossibleLiveOffsetSmoothingFactor = f13;
        this.mediaConfigurationTargetLiveOffsetUs = C.TIME_UNSET;
        this.targetLiveOffsetOverrideUs = C.TIME_UNSET;
        this.minTargetLiveOffsetUs = C.TIME_UNSET;
        this.maxTargetLiveOffsetUs = C.TIME_UNSET;
        this.minPlaybackSpeed = f10;
        this.maxPlaybackSpeed = f11;
        this.adjustedPlaybackSpeed = 1.0f;
        this.lastPlaybackSpeedUpdateMs = C.TIME_UNSET;
        this.idealTargetLiveOffsetUs = C.TIME_UNSET;
        this.currentTargetLiveOffsetUs = C.TIME_UNSET;
        this.smoothedMinPossibleLiveOffsetUs = C.TIME_UNSET;
        this.smoothedMinPossibleLiveOffsetDeviationUs = C.TIME_UNSET;
    }
}
