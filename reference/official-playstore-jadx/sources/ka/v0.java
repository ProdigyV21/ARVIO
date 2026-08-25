package ka;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final class v0 extends pa.v {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f19648o = AtomicIntegerFieldUpdater.newUpdater(v0.class, "_decision");

    @q7.w
    private volatile int _decision;

    @Override // pa.v, ka.f2
    public final void g(Object obj) {
        h(obj);
    }

    @Override // pa.v, ka.f2
    public final void h(Object obj) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        do {
            atomicIntegerFieldUpdater = f19648o;
            int i10 = atomicIntegerFieldUpdater.get(this);
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("Already resumed");
                }
                pa.a.g(t7.a.A(this.f21296n), m0.q(obj));
                return;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, 0, 2));
    }
}
