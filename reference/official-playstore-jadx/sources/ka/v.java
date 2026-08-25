package ka;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public class v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f19646b = AtomicIntegerFieldUpdater.newUpdater(v.class, "_handled");

    @q7.w
    private volatile int _handled;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Throwable f19647a;

    public v(Throwable th, boolean z) {
        this.f19647a = th;
        this._handled = z ? 1 : 0;
    }

    public final String toString() {
        return getClass().getSimpleName() + '[' + this.f19647a + ']';
    }
}
