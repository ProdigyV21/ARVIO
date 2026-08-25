package ra;

import androidx.leanback.widget.r;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import java.util.concurrent.TimeUnit;
import pa.y;

/* JADX INFO: loaded from: classes5.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f21432a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f21433b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f21434c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f21435d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f21436e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final f f21437f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final r f21438g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final r f21439h;

    static {
        String property;
        int i10 = y.f21300a;
        try {
            property = System.getProperty("kotlinx.coroutines.scheduler.default.name");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            property = "DefaultDispatcher";
        }
        f21432a = property;
        f21433b = pa.a.i("kotlinx.coroutines.scheduler.resolution.ns", SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US, 1L, Long.MAX_VALUE);
        int i11 = y.f21300a;
        if (i11 < 2) {
            i11 = 2;
        }
        f21434c = pa.a.j(i11, 8, "kotlinx.coroutines.scheduler.core.pool.size");
        f21435d = pa.a.j(2097150, 4, "kotlinx.coroutines.scheduler.max.pool.size");
        f21436e = TimeUnit.SECONDS.toNanos(pa.a.i("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 1L, Long.MAX_VALUE));
        f21437f = f.f21427a;
        f21438g = new r(0);
        f21439h = new r(1);
    }
}
