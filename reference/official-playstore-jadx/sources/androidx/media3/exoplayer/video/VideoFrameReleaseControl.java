package androidx.media3.exoplayer.video;

import android.content.Context;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlaybackException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes3.dex */
public final class VideoFrameReleaseControl {
    public static final int FRAME_RELEASE_DROP = 2;
    public static final int FRAME_RELEASE_IGNORE = 4;
    public static final int FRAME_RELEASE_IMMEDIATELY = 0;
    public static final int FRAME_RELEASE_SCHEDULED = 1;
    public static final int FRAME_RELEASE_SKIP = 3;
    public static final int FRAME_RELEASE_TRY_AGAIN_LATER = 5;
    private static final long MAX_EARLY_US_THRESHOLD = 50000;
    private final long allowedJoiningTimeMs;
    private boolean disableAdvancingTimestampChecks;
    private boolean frameReadyWithoutSurface;
    private final VideoFrameReleaseHelper frameReleaseHelper;
    private final FrameTimingEvaluator frameTimingEvaluator;
    private boolean hasOutputSurface;
    private boolean joiningRenderNextFrameImmediately;
    private long lastReleaseRealtimeUs;
    private boolean started;
    private int firstFrameState = 0;
    private long initialPositionUs = C.TIME_UNSET;
    private long lastPresentationTimeUs = C.TIME_UNSET;
    private long joiningDeadlineMs = C.TIME_UNSET;
    private float playbackSpeed = 1.0f;
    private Clock clock = Clock.DEFAULT;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface FrameReleaseAction {
    }

    public static class FrameReleaseInfo {
        private long earlyUs = C.TIME_UNSET;
        private long releaseTimeNs = C.TIME_UNSET;

        /* JADX INFO: Access modifiers changed from: private */
        public void reset() {
            this.earlyUs = C.TIME_UNSET;
            this.releaseTimeNs = C.TIME_UNSET;
        }

        public long getEarlyUs() {
            return this.earlyUs;
        }

        public long getReleaseTimeNs() {
            return this.releaseTimeNs;
        }
    }

    public interface FrameTimingEvaluator {
        boolean shouldDropFrame(long j10, long j11, boolean z);

        boolean shouldForceReleaseFrame(long j10, long j11);

        boolean shouldIgnoreFrame(long j10, long j11, long j12, boolean z, boolean z5) throws ExoPlaybackException;
    }

    public VideoFrameReleaseControl(Context context, FrameTimingEvaluator frameTimingEvaluator, long j10) {
        this.frameTimingEvaluator = frameTimingEvaluator;
        this.allowedJoiningTimeMs = j10;
        this.frameReleaseHelper = new VideoFrameReleaseHelper(context);
    }

    private long calculateEarlyTimeUs(long j10, long j11, long j12) {
        long j13 = (long) ((j12 - j10) / ((double) this.playbackSpeed));
        return this.started ? j13 - (Util.msToUs(this.clock.elapsedRealtime()) - j11) : j13;
    }

    private void lowerFirstFrameState(int i10) {
        this.firstFrameState = Math.min(this.firstFrameState, i10);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean shouldForceRelease(long r8, long r10, long r12) {
        /*
            r7 = this;
            long r0 = r7.joiningDeadlineMs
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 0
            if (r0 == 0) goto L11
            boolean r0 = r7.joiningRenderNextFrameImmediately
            if (r0 != 0) goto L11
            return r1
        L11:
            int r0 = r7.firstFrameState
            if (r0 == 0) goto L54
            r4 = 1
            if (r0 == r4) goto L53
            r5 = 2
            if (r0 == r5) goto L4d
            r12 = 3
            if (r0 != r12) goto L47
            androidx.media3.common.util.Clock r12 = r7.clock
            long r12 = r12.elapsedRealtime()
            long r12 = androidx.media3.common.util.Util.msToUs(r12)
            long r5 = r7.lastReleaseRealtimeUs
            long r12 = r12 - r5
            boolean r0 = r7.started
            if (r0 == 0) goto L46
            boolean r0 = r7.disableAdvancingTimestampChecks
            if (r0 != 0) goto L3d
            long r5 = r7.initialPositionUs
            int r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r0 == 0) goto L46
            int r8 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r8 == 0) goto L46
        L3d:
            androidx.media3.exoplayer.video.VideoFrameReleaseControl$FrameTimingEvaluator r8 = r7.frameTimingEvaluator
            boolean r8 = r8.shouldForceReleaseFrame(r10, r12)
            if (r8 == 0) goto L46
            return r4
        L46:
            return r1
        L47:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            r8.<init>()
            throw r8
        L4d:
            int r8 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r8 < 0) goto L52
            return r4
        L52:
            return r1
        L53:
            return r4
        L54:
            boolean r8 = r7.started
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.video.VideoFrameReleaseControl.shouldForceRelease(long, long, long):boolean");
    }

    public void allowReleaseFirstFrameBeforeStarted() {
        if (this.firstFrameState == 0) {
            this.firstFrameState = 1;
        }
    }

    public void experimentalDisableAdvancingTimestampChecks() {
        this.disableAdvancingTimestampChecks = true;
    }

    public int getFrameReleaseAction(long j10, long j11, long j12, long j13, boolean z, boolean z5, FrameReleaseInfo frameReleaseInfo) throws ExoPlaybackException {
        frameReleaseInfo.reset();
        if (this.started && this.initialPositionUs == C.TIME_UNSET) {
            this.initialPositionUs = j11;
        }
        if (this.lastPresentationTimeUs != j10) {
            this.frameReleaseHelper.onNextFrame(j10);
            this.lastPresentationTimeUs = j10;
        }
        frameReleaseInfo.earlyUs = calculateEarlyTimeUs(j11, j12, j10);
        if (z && !z5) {
            return 3;
        }
        if (!this.hasOutputSurface) {
            if (this.frameTimingEvaluator.shouldIgnoreFrame(frameReleaseInfo.earlyUs, j11, j12, z5, true)) {
                return 4;
            }
            if (this.started && frameReleaseInfo.earlyUs < 30000) {
                return 3;
            }
            this.frameReadyWithoutSurface = true;
            return 5;
        }
        if (shouldForceRelease(j11, frameReleaseInfo.earlyUs, j13)) {
            return 0;
        }
        if (!this.started || j11 == this.initialPositionUs) {
            return 5;
        }
        long jNanoTime = this.clock.nanoTime();
        frameReleaseInfo.releaseTimeNs = this.frameReleaseHelper.adjustReleaseTime((frameReleaseInfo.earlyUs * 1000) + jNanoTime, j10);
        frameReleaseInfo.earlyUs = (frameReleaseInfo.releaseTimeNs - jNanoTime) / 1000;
        boolean z10 = (this.joiningDeadlineMs == C.TIME_UNSET || this.joiningRenderNextFrameImmediately) ? false : true;
        if (this.frameTimingEvaluator.shouldIgnoreFrame(frameReleaseInfo.earlyUs, j11, j12, z5, z10)) {
            return 4;
        }
        return this.frameTimingEvaluator.shouldDropFrame(frameReleaseInfo.earlyUs, j12, z5) ? z10 ? 3 : 2 : frameReleaseInfo.earlyUs > MAX_EARLY_US_THRESHOLD ? 5 : 1;
    }

    public boolean isReady(boolean z) {
        if (z && (this.firstFrameState == 3 || (!this.hasOutputSurface && this.frameReadyWithoutSurface))) {
            this.joiningDeadlineMs = C.TIME_UNSET;
            return true;
        }
        if (this.joiningDeadlineMs == C.TIME_UNSET) {
            return false;
        }
        if (this.clock.elapsedRealtime() < this.joiningDeadlineMs) {
            return true;
        }
        this.joiningDeadlineMs = C.TIME_UNSET;
        return false;
    }

    public void join(boolean z) {
        this.joiningRenderNextFrameImmediately = z;
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? this.clock.elapsedRealtime() + this.allowedJoiningTimeMs : C.TIME_UNSET;
    }

    public boolean onFrameReleasedIsFirstFrame() {
        boolean z = this.firstFrameState != 3;
        this.firstFrameState = 3;
        this.lastReleaseRealtimeUs = Util.msToUs(this.clock.elapsedRealtime());
        return z;
    }

    public void onStarted() {
        this.started = true;
        this.lastReleaseRealtimeUs = Util.msToUs(this.clock.elapsedRealtime());
        this.frameReleaseHelper.onStarted();
    }

    public void onStopped() {
        this.started = false;
        this.joiningDeadlineMs = C.TIME_UNSET;
        this.frameReleaseHelper.onStopped();
    }

    public void onStreamChanged(int i10) {
        if (i10 == 0) {
            this.firstFrameState = 1;
        } else if (i10 == 1) {
            this.firstFrameState = 0;
        } else {
            if (i10 != 2) {
                throw new IllegalStateException();
            }
            lowerFirstFrameState(2);
        }
        this.frameReleaseHelper.onPositionReset();
    }

    public void reset() {
        this.frameReleaseHelper.onPositionReset();
        this.lastPresentationTimeUs = C.TIME_UNSET;
        this.initialPositionUs = C.TIME_UNSET;
        lowerFirstFrameState(1);
        this.joiningDeadlineMs = C.TIME_UNSET;
    }

    public void setChangeFrameRateStrategy(int i10) {
        this.frameReleaseHelper.setChangeFrameRateStrategy(i10);
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public void setFrameRate(float f10) {
        this.frameReleaseHelper.onFormatChanged(f10);
    }

    public void setOutputSurface(Surface surface) {
        this.hasOutputSurface = surface != null;
        this.frameReadyWithoutSurface = false;
        this.frameReleaseHelper.onSurfaceChanged(surface);
        lowerFirstFrameState(1);
    }

    public void setPlaybackSpeed(float f10) {
        ac.b.j(f10 > 0.0f);
        if (f10 == this.playbackSpeed) {
            return;
        }
        this.playbackSpeed = f10;
        this.frameReleaseHelper.onPlaybackSpeed(f10);
    }
}
