package androidx.media3.common.util;

import androidx.media3.common.C;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes3.dex */
public final class TimestampAdjuster {
    private static final long MAX_PTS_PLUS_ONE = 8589934592L;
    public static final long MODE_NO_OFFSET = Long.MAX_VALUE;
    public static final long MODE_SHARED = 9223372036854775806L;
    private long firstSampleTimestampUs;
    private long lastUnadjustedTimestampUs;
    private final ThreadLocal<Long> nextSampleTimestampUs = new ThreadLocal<>();
    private long timestampOffsetUs;

    public TimestampAdjuster(long j10) {
        reset(j10);
    }

    public static long ptsToUs(long j10) {
        return Util.scaleLargeTimestamp(j10, 1000000L, 90000L);
    }

    public static long usToNonWrappedPts(long j10) {
        return Util.scaleLargeTimestamp(j10, 90000L, 1000000L);
    }

    public static long usToWrappedPts(long j10) {
        return usToNonWrappedPts(j10) % MAX_PTS_PLUS_ONE;
    }

    public synchronized long adjustSampleTimestamp(long j10) {
        if (j10 == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        try {
            if (!isInitialized()) {
                long jLongValue = this.firstSampleTimestampUs;
                if (jLongValue == MODE_SHARED) {
                    Long l10 = this.nextSampleTimestampUs.get();
                    l10.getClass();
                    jLongValue = l10.longValue();
                }
                this.timestampOffsetUs = jLongValue - j10;
                notifyAll();
            }
            this.lastUnadjustedTimestampUs = j10;
            return j10 + this.timestampOffsetUs;
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized long adjustTsTimestamp(long j10) {
        if (j10 == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        try {
            long j11 = this.lastUnadjustedTimestampUs;
            if (j11 != C.TIME_UNSET) {
                long jUsToNonWrappedPts = usToNonWrappedPts(j11);
                long j12 = (4294967296L + jUsToNonWrappedPts) / MAX_PTS_PLUS_ONE;
                long j13 = ((j12 - 1) * MAX_PTS_PLUS_ONE) + j10;
                long j14 = (j12 * MAX_PTS_PLUS_ONE) + j10;
                j10 = Math.abs(j13 - jUsToNonWrappedPts) < Math.abs(j14 - jUsToNonWrappedPts) ? j13 : j14;
            }
            return adjustSampleTimestamp(ptsToUs(j10));
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized long adjustTsTimestampGreaterThanPreviousTimestamp(long j10) {
        if (j10 == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        long j11 = this.lastUnadjustedTimestampUs;
        if (j11 != C.TIME_UNSET) {
            long jUsToNonWrappedPts = usToNonWrappedPts(j11);
            long j12 = jUsToNonWrappedPts / MAX_PTS_PLUS_ONE;
            Long.signum(j12);
            long j13 = (j12 * MAX_PTS_PLUS_ONE) + j10;
            j10 = j13 >= jUsToNonWrappedPts ? j13 : ((j12 + 1) * MAX_PTS_PLUS_ONE) + j10;
        }
        return adjustSampleTimestamp(ptsToUs(j10));
    }

    public synchronized long getFirstSampleTimestampUs() {
        long j10;
        j10 = this.firstSampleTimestampUs;
        if (j10 == Long.MAX_VALUE || j10 == MODE_SHARED) {
            j10 = C.TIME_UNSET;
        }
        return j10;
    }

    public synchronized long getLastAdjustedTimestampUs() {
        long j10;
        try {
            j10 = this.lastUnadjustedTimestampUs;
        } catch (Throwable th) {
            throw th;
        }
        return j10 != C.TIME_UNSET ? j10 + this.timestampOffsetUs : getFirstSampleTimestampUs();
    }

    public synchronized long getTimestampOffsetUs() {
        return this.timestampOffsetUs;
    }

    public synchronized boolean isInitialized() {
        return this.timestampOffsetUs != C.TIME_UNSET;
    }

    public synchronized void reset(long j10) {
        this.firstSampleTimestampUs = j10;
        this.timestampOffsetUs = j10 == Long.MAX_VALUE ? 0L : -9223372036854775807L;
        this.lastUnadjustedTimestampUs = C.TIME_UNSET;
    }

    public synchronized void sharedInitializeOrWait(boolean z, long j10, long j11) throws InterruptedException, TimeoutException {
        try {
            ac.b.s(this.firstSampleTimestampUs == MODE_SHARED);
            if (isInitialized()) {
                return;
            }
            if (z) {
                this.nextSampleTimestampUs.set(Long.valueOf(j10));
            } else {
                long jElapsedRealtime = 0;
                long j12 = j11;
                while (!isInitialized()) {
                    if (j11 == 0) {
                        wait();
                    } else {
                        ac.b.s(j12 > 0);
                        long jElapsedRealtime2 = android.os.SystemClock.elapsedRealtime();
                        wait(j12);
                        jElapsedRealtime += android.os.SystemClock.elapsedRealtime() - jElapsedRealtime2;
                        if (jElapsedRealtime >= j11 && !isInitialized()) {
                            throw new TimeoutException("TimestampAdjuster failed to initialize in " + j11 + " milliseconds");
                        }
                        j12 = j11 - jElapsedRealtime;
                    }
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
