package androidx.media3.common.util;

import androidx.media3.common.C;
import androidx.media3.common.audio.SpeedProvider;

/* JADX INFO: loaded from: classes3.dex */
public class SpeedProviderUtil {
    private SpeedProviderUtil() {
    }

    public static long getDurationAfterSpeedProviderApplied(SpeedProvider speedProvider, long j10) {
        long j11 = 0;
        double dMin = 0.0d;
        while (j11 < j10) {
            long nextSpeedChangeTimeUs = speedProvider.getNextSpeedChangeTimeUs(j11);
            if (nextSpeedChangeTimeUs == C.TIME_UNSET) {
                nextSpeedChangeTimeUs = Long.MAX_VALUE;
            }
            ac.b.s(nextSpeedChangeTimeUs > j11);
            dMin += (Math.min(nextSpeedChangeTimeUs, j10) - j11) / ((double) speedProvider.getSpeed(j11));
            j11 = nextSpeedChangeTimeUs;
        }
        return (long) Math.floor(dMin);
    }

    public static long getNextSpeedChangeSamplePosition(SpeedProvider speedProvider, long j10, int i10) {
        ac.b.j(j10 >= 0);
        ac.b.j(i10 > 0);
        long nextSpeedChangeTimeUs = speedProvider.getNextSpeedChangeTimeUs(Util.sampleCountToDurationUs(j10, i10));
        if (nextSpeedChangeTimeUs == C.TIME_UNSET) {
            return -1L;
        }
        return Util.durationUsToSampleCount(nextSpeedChangeTimeUs, i10);
    }

    public static float getSampleAlignedSpeed(SpeedProvider speedProvider, long j10, int i10) {
        ac.b.j(j10 >= 0);
        ac.b.j(i10 > 0);
        return speedProvider.getSpeed(Util.sampleCountToDurationUs(j10, i10));
    }
}
