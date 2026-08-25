package androidx.media3.exoplayer.video;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.view.Choreographer;
import android.view.Choreographer$VsyncCallback;
import android.view.Display;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes3.dex */
public final class VideoFrameReleaseHelper {
    private static final long MAX_ALLOWED_ADJUSTMENT_NS = 20000000;
    private static final int MINIMUM_FRAMES_WITHOUT_SYNC_TO_CLEAR_SURFACE_FRAME_RATE = 30;
    private static final long MINIMUM_MATCHING_FRAME_DURATION_FOR_HIGH_CONFIDENCE_NS = 5000000000L;
    private static final float MINIMUM_MEDIA_FRAME_RATE_CHANGE_FOR_UPDATE_HIGH_CONFIDENCE = 0.1f;
    private static final float MINIMUM_MEDIA_FRAME_RATE_CHANGE_FOR_UPDATE_LOW_CONFIDENCE = 1.0f;
    private static final String TAG = "VideoFrameReleaseHelper";
    private static final long VSYNC_OFFSET_PERCENTAGE = 80;
    public static final long VSYNC_SAMPLE_UPDATE_PERIOD_MS = 500;
    private final Context context;
    private long frameIndex;
    private long lastAdjustedFrameIndex;
    private long lastAdjustedPresentationTimeUs;
    private long lastAdjustedReleaseTimeNs;
    private long lastVsyncHysteresisOffsetNs;
    private long pendingLastAdjustedFrameIndex;
    private long pendingLastAdjustedReleaseTimeNs;
    private long pendingLastPresentationTimeUs;
    private long pendingVsyncHysteresisOffsetNs;
    private boolean started;
    private Surface surface;
    private float surfaceMediaFrameRate;
    private float surfacePlaybackFrameRate;
    private boolean vsyncSampleBuilt;
    private VSyncSampler vsyncSampler;
    private final FixedFrameRateEstimator frameRateEstimator = new FixedFrameRateEstimator();
    private float formatFrameRate = -1.0f;
    private float playbackSpeed = 1.0f;
    private int changeFrameRateStrategy = 0;

    public static final class Api30 {
        private Api30() {
        }

        public static void setSurfaceFrameRate(Surface surface, float f10) {
            try {
                surface.setFrameRate(f10, f10 == 0.0f ? 0 : 1);
            } catch (IllegalStateException e5) {
                Log.e(VideoFrameReleaseHelper.TAG, "Failed to call Surface.setFrameRate", e5);
            }
        }
    }

    public static abstract class VSyncSampler implements DisplayManager.DisplayListener {
        final Choreographer choreographer;
        final DisplayManager displayManager;
        volatile long sampledVsyncTimeNs;
        volatile long vsyncDurationNs;

        /* JADX INFO: Access modifiers changed from: private */
        public static VSyncSampler maybeBuildInstance(Context context) {
            DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
            if (displayManager == null) {
                return null;
            }
            try {
                Choreographer choreographer = Choreographer.getInstance();
                return Build.VERSION.SDK_INT >= 33 ? new VSyncSamplerV33(choreographer, displayManager) : new VSyncSamplerBase(choreographer, displayManager);
            } catch (RuntimeException e5) {
                Log.w(VideoFrameReleaseHelper.TAG, "Vsync sampling disabled due to platform error", e5);
                return null;
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayAdded(int i10) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayRemoved(int i10) {
        }

        public void register() {
            this.displayManager.registerDisplayListener(this, Util.createHandlerForCurrentLooper());
        }

        public void unregister() {
            this.displayManager.unregisterDisplayListener(this);
        }

        private VSyncSampler(Choreographer choreographer, DisplayManager displayManager) {
            this.choreographer = choreographer;
            this.displayManager = displayManager;
            this.sampledVsyncTimeNs = C.TIME_UNSET;
            this.vsyncDurationNs = C.TIME_UNSET;
        }
    }

    public static final class VSyncSamplerBase extends VSyncSampler implements Choreographer.FrameCallback {
        private static long getVsyncDurationNsFromDefaultDisplay(DisplayManager displayManager) {
            Display display = displayManager.getDisplay(0);
            if (display != null) {
                return (long) (1.0E9d / ((double) display.getRefreshRate()));
            }
            Log.w(VideoFrameReleaseHelper.TAG, "Unable to query display refresh rate");
            return C.TIME_UNSET;
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j10) {
            this.sampledVsyncTimeNs = j10;
            this.choreographer.postFrameCallbackDelayed(this, 500L);
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i10) {
            if (i10 == 0) {
                this.choreographer.postFrameCallback(this);
                this.vsyncDurationNs = getVsyncDurationNsFromDefaultDisplay(this.displayManager);
            }
        }

        @Override // androidx.media3.exoplayer.video.VideoFrameReleaseHelper.VSyncSampler
        public void register() {
            super.register();
            this.choreographer.postFrameCallback(this);
            this.vsyncDurationNs = getVsyncDurationNsFromDefaultDisplay(this.displayManager);
        }

        @Override // androidx.media3.exoplayer.video.VideoFrameReleaseHelper.VSyncSampler
        public void unregister() {
            super.unregister();
            this.choreographer.removeFrameCallback(this);
            this.sampledVsyncTimeNs = C.TIME_UNSET;
            this.vsyncDurationNs = C.TIME_UNSET;
        }

        private VSyncSamplerBase(Choreographer choreographer, DisplayManager displayManager) {
            super(choreographer, displayManager);
        }
    }

    public static final class VSyncSamplerV33 extends VSyncSampler implements Choreographer$VsyncCallback {
        private final Handler handler;

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onVsync$0() {
            this.choreographer.postVsyncCallback(this);
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i10) {
            if (i10 == 0) {
                this.choreographer.postVsyncCallback(this);
            }
        }

        public void onVsync(Choreographer.FrameData frameData) {
            this.sampledVsyncTimeNs = frameData.getFrameTimeNanos();
            Choreographer.FrameTimeline[] frameTimelines = frameData.getFrameTimelines();
            int length = frameTimelines.length;
            long j10 = C.TIME_UNSET;
            if (length >= 2) {
                long expectedPresentationTimeNanos = frameTimelines[1].getExpectedPresentationTimeNanos() - frameTimelines[0].getExpectedPresentationTimeNanos();
                if (expectedPresentationTimeNanos != 0) {
                    j10 = expectedPresentationTimeNanos;
                }
                this.vsyncDurationNs = j10;
            } else {
                this.vsyncDurationNs = C.TIME_UNSET;
            }
            this.handler.postDelayed(new c(this, 1), 500L);
        }

        @Override // androidx.media3.exoplayer.video.VideoFrameReleaseHelper.VSyncSampler
        public void register() {
            super.register();
            this.choreographer.postVsyncCallback(this);
        }

        @Override // androidx.media3.exoplayer.video.VideoFrameReleaseHelper.VSyncSampler
        public void unregister() {
            super.unregister();
            this.handler.removeCallbacksAndMessages(null);
            this.choreographer.removeVsyncCallback(this);
            this.sampledVsyncTimeNs = C.TIME_UNSET;
            this.vsyncDurationNs = C.TIME_UNSET;
        }

        private VSyncSamplerV33(Choreographer choreographer, DisplayManager displayManager) {
            super(choreographer, displayManager);
            this.handler = Util.createHandlerForCurrentLooper();
        }
    }

    public VideoFrameReleaseHelper(Context context) {
        this.context = context;
    }

    private static boolean adjustmentAllowed(long j10, long j11) {
        return Math.abs(j10 - j11) <= MAX_ALLOWED_ADJUSTMENT_NS;
    }

    private void clearSurfaceFrameRate() {
        Surface surface;
        if (Build.VERSION.SDK_INT < 30 || (surface = this.surface) == null || this.changeFrameRateStrategy == Integer.MIN_VALUE || this.surfacePlaybackFrameRate == 0.0f || !surface.isValid()) {
            return;
        }
        this.surfacePlaybackFrameRate = 0.0f;
        Api30.setSurfaceFrameRate(this.surface, 0.0f);
    }

    private long findClosestVsyncAndUpdateHysteresis(long j10, long j11, long j12) {
        long j13;
        long j14 = (((j10 - j11) / j12) * j12) + j11;
        if (j10 <= j14) {
            j13 = j14 - j12;
        } else {
            j13 = j14;
            j14 += j12;
        }
        long j15 = j14 - j10;
        long j16 = j10 - j13;
        long jAbs = Math.abs(j15 - j16);
        if (jAbs < j12 / 2) {
            long j17 = j12 / 4;
            if (jAbs < j17) {
                long j18 = this.lastVsyncHysteresisOffsetNs;
                if (j18 != 0) {
                    this.pendingVsyncHysteresisOffsetNs = j18;
                } else {
                    if (j15 < j16) {
                        j17 = -j17;
                    }
                    this.pendingVsyncHysteresisOffsetNs = j17;
                }
            } else {
                this.pendingVsyncHysteresisOffsetNs = 0L;
            }
        } else {
            this.pendingVsyncHysteresisOffsetNs = this.lastVsyncHysteresisOffsetNs;
        }
        return j15 + this.pendingVsyncHysteresisOffsetNs < j16 ? j14 : j13;
    }

    private void resetAdjustment() {
        this.frameIndex = 0L;
        this.lastAdjustedFrameIndex = -1L;
        this.pendingLastAdjustedFrameIndex = -1L;
        this.lastVsyncHysteresisOffsetNs = 0L;
        this.pendingVsyncHysteresisOffsetNs = 0L;
    }

    private void updateSurfaceMediaFrameRate() {
        if (Build.VERSION.SDK_INT < 30 || this.surface == null) {
            return;
        }
        float frameRate = this.frameRateEstimator.isSynced() ? this.frameRateEstimator.getFrameRate() : this.formatFrameRate;
        float f10 = this.surfaceMediaFrameRate;
        if (frameRate == f10) {
            return;
        }
        if (frameRate != -1.0f && f10 != -1.0f) {
            if (Math.abs(frameRate - this.surfaceMediaFrameRate) < ((!this.frameRateEstimator.isSynced() || this.frameRateEstimator.getMatchingFrameDurationSumNs() < MINIMUM_MATCHING_FRAME_DURATION_FOR_HIGH_CONFIDENCE_NS) ? 1.0f : 0.1f)) {
                return;
            }
        } else if (frameRate == -1.0f && this.frameRateEstimator.getFramesWithoutSyncCount() < 30) {
            return;
        }
        this.surfaceMediaFrameRate = frameRate;
        updateSurfacePlaybackFrameRate(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void updateSurfacePlaybackFrameRate(boolean r4) {
        /*
            r3 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 30
            if (r0 < r1) goto L38
            android.view.Surface r0 = r3.surface
            if (r0 == 0) goto L38
            int r1 = r3.changeFrameRateStrategy
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 == r2) goto L38
            boolean r0 = r0.isValid()
            if (r0 != 0) goto L17
            goto L38
        L17:
            boolean r0 = r3.started
            if (r0 == 0) goto L27
            float r0 = r3.surfaceMediaFrameRate
            r1 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 == 0) goto L27
            float r1 = r3.playbackSpeed
            float r0 = r0 * r1
            goto L28
        L27:
            r0 = 0
        L28:
            if (r4 != 0) goto L31
            float r4 = r3.surfacePlaybackFrameRate
            int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r4 != 0) goto L31
            goto L38
        L31:
            r3.surfacePlaybackFrameRate = r0
            android.view.Surface r4 = r3.surface
            androidx.media3.exoplayer.video.VideoFrameReleaseHelper.Api30.setSurfaceFrameRate(r4, r0)
        L38:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.video.VideoFrameReleaseHelper.updateSurfacePlaybackFrameRate(boolean):void");
    }

    public long adjustReleaseTime(long j10, long j11) {
        long j12;
        float frameDurationNs;
        float f10;
        if (this.lastAdjustedFrameIndex == -1) {
            j12 = j10;
        } else {
            if (this.frameRateEstimator.isSynced()) {
                frameDurationNs = (this.frameIndex - this.lastAdjustedFrameIndex) * this.frameRateEstimator.getFrameDurationNs();
                f10 = this.playbackSpeed;
            } else {
                frameDurationNs = (j11 - this.lastAdjustedPresentationTimeUs) * 1000;
                f10 = this.playbackSpeed;
            }
            long j13 = this.lastAdjustedReleaseTimeNs + ((long) (frameDurationNs / f10));
            if (adjustmentAllowed(j10, j13)) {
                j12 = j13;
            } else {
                resetAdjustment();
                j12 = j10;
            }
        }
        this.pendingLastAdjustedFrameIndex = this.frameIndex;
        this.pendingLastAdjustedReleaseTimeNs = j12;
        this.pendingLastPresentationTimeUs = j11;
        VSyncSampler vSyncSampler = this.vsyncSampler;
        if (vSyncSampler != null) {
            long j14 = vSyncSampler.sampledVsyncTimeNs;
            long j15 = this.vsyncSampler.vsyncDurationNs;
            if (j14 != C.TIME_UNSET && j15 != C.TIME_UNSET) {
                return findClosestVsyncAndUpdateHysteresis(j12, j14, j15) - ((j15 * 80) / 100);
            }
        }
        return j12;
    }

    public void onFormatChanged(float f10) {
        this.formatFrameRate = f10;
        this.frameRateEstimator.reset();
        updateSurfaceMediaFrameRate();
    }

    public void onNextFrame(long j10) {
        long j11 = this.pendingLastAdjustedFrameIndex;
        if (j11 != -1) {
            this.lastAdjustedFrameIndex = j11;
            this.lastAdjustedReleaseTimeNs = this.pendingLastAdjustedReleaseTimeNs;
            this.lastAdjustedPresentationTimeUs = this.pendingLastPresentationTimeUs;
            this.lastVsyncHysteresisOffsetNs = this.pendingVsyncHysteresisOffsetNs;
        }
        this.frameIndex++;
        this.frameRateEstimator.onNextFrame(j10 * 1000);
        updateSurfaceMediaFrameRate();
    }

    public void onPlaybackSpeed(float f10) {
        this.playbackSpeed = f10;
        updateSurfacePlaybackFrameRate(false);
    }

    public void onPositionReset() {
        resetAdjustment();
    }

    public void onStarted() {
        this.started = true;
        resetAdjustment();
        if (!this.vsyncSampleBuilt) {
            this.vsyncSampler = VSyncSampler.maybeBuildInstance(this.context);
        }
        VSyncSampler vSyncSampler = this.vsyncSampler;
        if (vSyncSampler != null) {
            vSyncSampler.register();
        }
        updateSurfacePlaybackFrameRate(false);
    }

    public void onStopped() {
        this.started = false;
        VSyncSampler vSyncSampler = this.vsyncSampler;
        if (vSyncSampler != null) {
            vSyncSampler.unregister();
        }
        clearSurfaceFrameRate();
    }

    public void onSurfaceChanged(Surface surface) {
        if (this.surface == surface) {
            return;
        }
        clearSurfaceFrameRate();
        this.surface = surface;
        updateSurfacePlaybackFrameRate(true);
    }

    public void setChangeFrameRateStrategy(int i10) {
        if (this.changeFrameRateStrategy == i10) {
            return;
        }
        this.changeFrameRateStrategy = i10;
        updateSurfacePlaybackFrameRate(true);
    }

    public void setVsyncData(long j10, long j11) {
        VSyncSampler vSyncSampler = this.vsyncSampler;
        vSyncSampler.getClass();
        vSyncSampler.sampledVsyncTimeNs = j10;
        this.vsyncSampler.vsyncDurationNs = j11;
    }
}
