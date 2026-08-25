package pa;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import ka.l2;

/* JADX INFO: loaded from: classes5.dex */
public abstract class w extends d implements l2 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f21297n = AtomicIntegerFieldUpdater.newUpdater(w.class, "cleanedAndPointers");

    @q7.w
    private volatile int cleanedAndPointers;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f21298m;

    public w(long j10, w wVar, int i10) {
        super(wVar);
        this.f21298m = j10;
        this.cleanedAndPointers = i10 << 16;
    }

    @Override // pa.d
    public final boolean c() {
        return f21297n.get(this) == f() && b() != null;
    }

    public final boolean e() {
        return f21297n.addAndGet(this, -65536) == f() && b() != null;
    }

    public abstract int f();

    public abstract void g(int i10, d7.j jVar);

    public final void h() {
        if (f21297n.incrementAndGet(this) == f()) {
            d();
        }
    }

    public final boolean i() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i10;
        do {
            atomicIntegerFieldUpdater = f21297n;
            i10 = atomicIntegerFieldUpdater.get(this);
            if (i10 == f() && b() != null) {
                return false;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i10, 65536 + i10));
        return true;
    }
}
