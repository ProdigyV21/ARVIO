package j$.time;

import androidx.media3.common.C;

/* JADX INFO: loaded from: classes2.dex */
public class TimeConversions {
    public static Duration convert(java.time.Duration duration) {
        if (duration == null) {
            return null;
        }
        long seconds = duration.getSeconds();
        long nano = duration.getNano();
        Duration duration2 = Duration.f18095c;
        return Duration.m(j$.com.android.tools.r8.a.L(seconds, j$.com.android.tools.r8.a.Q(nano, C.NANOS_PER_SECOND)), (int) j$.com.android.tools.r8.a.P(nano, C.NANOS_PER_SECOND));
    }

    public static java.time.Duration convert(Duration duration) {
        if (duration == null) {
            return null;
        }
        return java.time.Duration.ofSeconds(duration.f18096a, duration.f18097b);
    }
}
