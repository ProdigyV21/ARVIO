package androidx.media3.exoplayer.video;

import android.util.Range;
import androidx.media3.common.C;

/* JADX INFO: loaded from: classes3.dex */
class VideoFrameReleaseEarlyTimeForecaster {
    private static final float SMOOTHING_FACTOR = 0.2f;
    private double derivativeOfEarlyTime;
    private Range<Double> derivativeOfEarlyTimeRange;
    private long lastFrameEarlyUs;
    private long lastFramePresentationTimeUs;

    public VideoFrameReleaseEarlyTimeForecaster(float f10) {
        ac.b.j(f10 > 0.0f);
        Range<Double> range = new Range<>(Double.valueOf(0.0d), Double.valueOf(1.0d / ((double) f10)));
        this.derivativeOfEarlyTimeRange = range;
        this.derivativeOfEarlyTime = ((Double) range.getUpper()).doubleValue();
        this.lastFramePresentationTimeUs = C.TIME_UNSET;
        this.lastFrameEarlyUs = C.TIME_UNSET;
    }

    private double calculateDerivativeFromLastFrame(long j10, long j11) {
        long j12 = this.lastFramePresentationTimeUs;
        if (j12 != C.TIME_UNSET) {
            if (this.lastFrameEarlyUs != C.TIME_UNSET && j10 != j12) {
                return (j11 - r4) / (j10 - j12);
            }
        }
        return ((Double) this.derivativeOfEarlyTimeRange.getUpper()).doubleValue();
    }

    private void updateDerivativeWithExponentialMovingAverage(double d4) {
        this.derivativeOfEarlyTime = (d4 * 0.20000000298023224d) + (this.derivativeOfEarlyTime * 0.800000011920929d);
    }

    public void onVideoFrameProcessed(long j10, long j11) {
        ac.b.j(j10 != C.TIME_UNSET);
        ac.b.j(j11 != C.TIME_UNSET);
        updateDerivativeWithExponentialMovingAverage(((Double) this.derivativeOfEarlyTimeRange.clamp(Double.valueOf(calculateDerivativeFromLastFrame(j10, j11)))).doubleValue());
        this.lastFramePresentationTimeUs = j10;
        this.lastFrameEarlyUs = j11;
    }

    public long predictEarlyUs(long j10) {
        if (this.lastFramePresentationTimeUs == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        return (long) (((j10 - r0) * this.derivativeOfEarlyTime) + this.lastFrameEarlyUs);
    }

    public void reset() {
        this.derivativeOfEarlyTime = ((Double) this.derivativeOfEarlyTimeRange.getUpper()).doubleValue();
        this.lastFramePresentationTimeUs = C.TIME_UNSET;
        this.lastFrameEarlyUs = C.TIME_UNSET;
    }

    public void setPlaybackSpeed(float f10) {
        ac.b.j(f10 > 0.0f);
        this.derivativeOfEarlyTimeRange = new Range<>(Double.valueOf(0.0d), Double.valueOf(1.0d / ((double) f10)));
        reset();
    }
}
