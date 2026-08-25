package ka;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final class s2 implements r7.l {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f19632n = AtomicIntegerFieldUpdater.newUpdater(s2.class, "_state");

    @q7.w
    private volatile int _state;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final v1 f19633i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Thread f19634l = Thread.currentThread();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public z0 f19635m;

    public s2(v1 v1Var) {
        this.f19633i = v1Var;
    }

    public static void b(int i10) {
        throw new IllegalStateException(("Illegal state " + i10).toString());
    }

    public final void a() {
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f19632n;
            int i10 = atomicIntegerFieldUpdater.get(this);
            if (i10 != 0) {
                if (i10 != 2) {
                    if (i10 == 3) {
                        Thread.interrupted();
                        return;
                    } else {
                        b(i10);
                        throw null;
                    }
                }
            } else if (atomicIntegerFieldUpdater.compareAndSet(this, i10, 1)) {
                z0 z0Var = this.f19635m;
                if (z0Var != null) {
                    z0Var.dispose();
                    return;
                }
                return;
            }
        }
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f19632n;
            int i10 = atomicIntegerFieldUpdater.get(this);
            if (i10 != 0) {
                if (i10 != 1 && i10 != 2 && i10 != 3) {
                    b(i10);
                    throw null;
                }
            } else if (atomicIntegerFieldUpdater.compareAndSet(this, i10, 2)) {
                this.f19634l.interrupt();
                atomicIntegerFieldUpdater.set(this, 3);
                break;
            }
        }
        return x6.t0.f22605a;
    }
}
