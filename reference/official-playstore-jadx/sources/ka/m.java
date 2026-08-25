package ka;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final class m extends v {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f19610c = AtomicIntegerFieldUpdater.newUpdater(m.class, "_resumed");

    @q7.w
    private volatile int _resumed;

    public m(l lVar, Throwable th, boolean z) {
        if (th == null) {
            th = new CancellationException("Continuation " + lVar + " was cancelled normally");
        }
        super(th, z);
        this._resumed = 0;
    }
}
