package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public class TimestampAligner {
    private volatile long nativeTimestampAligner = nativeCreateTimestampAligner();

    private void checkNativeAlignerExists() {
        if (this.nativeTimestampAligner == 0) {
            throw new IllegalStateException("TimestampAligner has been disposed.");
        }
    }

    public static long getRtcTimeNanos() {
        return nativeRtcTimeNanos();
    }

    private static native long nativeCreateTimestampAligner();

    private static native void nativeReleaseTimestampAligner(long j10);

    private static native long nativeRtcTimeNanos();

    private static native long nativeTranslateTimestamp(long j10, long j11);

    public void dispose() {
        checkNativeAlignerExists();
        nativeReleaseTimestampAligner(this.nativeTimestampAligner);
        this.nativeTimestampAligner = 0L;
    }

    public long translateTimestamp(long j10) {
        checkNativeAlignerExists();
        return nativeTranslateTimestamp(this.nativeTimestampAligner, j10);
    }
}
