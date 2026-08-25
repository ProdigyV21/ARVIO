package androidx.media3.common;

import android.os.SystemClock;
import androidx.media3.common.SimpleBasePlayer;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class b0 {
    static {
        SimpleBasePlayer.PositionSupplier positionSupplier = SimpleBasePlayer.PositionSupplier.ZERO;
    }

    public static SimpleBasePlayer.PositionSupplier a(long j10) {
        return new a0(j10);
    }

    public static SimpleBasePlayer.PositionSupplier b(final long j10, final float f10) {
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        return new SimpleBasePlayer.PositionSupplier() { // from class: androidx.media3.common.z
            @Override // androidx.media3.common.SimpleBasePlayer.PositionSupplier
            public final long get() {
                return b0.d(j10, jElapsedRealtime, f10);
            }
        };
    }

    public static /* synthetic */ long d(long j10, long j11, float f10) {
        return j10 + ((long) ((SystemClock.elapsedRealtime() - j11) * f10));
    }

    public static /* synthetic */ long c(long j10) {
        return j10;
    }
}
