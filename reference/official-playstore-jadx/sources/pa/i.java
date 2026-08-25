package pa;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import ka.o0;
import ka.r0;
import ka.t2;
import ka.z0;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends ka.f0 implements r0 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f21276p = AtomicIntegerFieldUpdater.newUpdater(i.class, "runningWorkers");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ka.f0 f21277i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f21278l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r0 f21279m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final n f21280n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Object f21281o;

    @q7.w
    private volatile int runningWorkers;

    /* JADX WARN: Multi-variable type inference failed */
    public i(ka.f0 f0Var, int i10) {
        this.f21277i = f0Var;
        this.f21278l = i10;
        r0 r0Var = f0Var instanceof r0 ? (r0) f0Var : null;
        this.f21279m = r0Var == null ? o0.f19625a : r0Var;
        this.f21280n = new n();
        this.f21281o = new Object();
    }

    @Override // ka.f0
    public final void dispatch(d7.j jVar, Runnable runnable) {
        Runnable runnableQ0;
        this.f21280n.a(runnable);
        if (f21276p.get(this) >= this.f21278l || !t0() || (runnableQ0 = q0()) == null) {
            return;
        }
        this.f21277i.dispatch(this, new h(this, runnableQ0));
    }

    @Override // ka.f0
    public final void dispatchYield(d7.j jVar, Runnable runnable) {
        Runnable runnableQ0;
        this.f21280n.a(runnable);
        if (f21276p.get(this) >= this.f21278l || !t0() || (runnableQ0 = q0()) == null) {
            return;
        }
        this.f21277i.dispatchYield(this, new h(this, runnableQ0));
    }

    @Override // ka.r0
    public final z0 l(long j10, t2 t2Var, d7.j jVar) {
        return this.f21279m.l(j10, t2Var, jVar);
    }

    @Override // ka.f0
    public final ka.f0 limitedParallelism(int i10) {
        a.a(i10);
        return i10 >= this.f21278l ? this : super.limitedParallelism(i10);
    }

    public final Runnable q0() {
        while (true) {
            Runnable runnable = (Runnable) this.f21280n.d();
            if (runnable != null) {
                return runnable;
            }
            synchronized (this.f21281o) {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f21276p;
                atomicIntegerFieldUpdater.decrementAndGet(this);
                if (this.f21280n.c() == 0) {
                    return null;
                }
                atomicIntegerFieldUpdater.incrementAndGet(this);
            }
        }
    }

    public final boolean t0() {
        synchronized (this.f21281o) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f21276p;
            if (atomicIntegerFieldUpdater.get(this) >= this.f21278l) {
                return false;
            }
            atomicIntegerFieldUpdater.incrementAndGet(this);
            return true;
        }
    }

    @Override // ka.r0
    public final void y(long j10, ka.l lVar) {
        this.f21279m.y(j10, lVar);
    }
}
