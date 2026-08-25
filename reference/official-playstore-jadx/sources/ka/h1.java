package ka;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes5.dex */
public abstract class h1 extends i1 implements r0 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f19595o = AtomicReferenceFieldUpdater.newUpdater(h1.class, Object.class, "_queue");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f19596p = AtomicReferenceFieldUpdater.newUpdater(h1.class, Object.class, "_delayed");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f19597q = AtomicIntegerFieldUpdater.newUpdater(h1.class, "_isCompleted");

    @q7.w
    private volatile Object _delayed;

    @q7.w
    private volatile int _isCompleted = 0;

    @q7.w
    private volatile Object _queue;

    @Override // ka.c1
    public final long D0() {
        f1 f1Var;
        pa.e0 e0VarD;
        if (!J0()) {
            g1 g1Var = (g1) f19596p.get(this);
            Runnable runnable = null;
            if (g1Var != null && pa.d0.f21260b.get(g1Var) != 0) {
                long jNanoTime = System.nanoTime();
                do {
                    synchronized (g1Var) {
                        try {
                            pa.e0[] e0VarArr = g1Var.f21261a;
                            pa.e0 e0Var = e0VarArr != null ? e0VarArr[0] : null;
                            if (e0Var == null) {
                                e0VarD = null;
                            } else {
                                f1 f1Var2 = (f1) e0Var;
                                e0VarD = ((jNanoTime - f1Var2.f19585i) > 0L ? 1 : ((jNanoTime - f1Var2.f19585i) == 0L ? 0 : -1)) >= 0 ? O0(f1Var2) : false ? g1Var.d(0) : null;
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                } while (((f1) e0VarD) != null);
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19595o;
            loop1: while (true) {
                Object obj = atomicReferenceFieldUpdater.get(this);
                if (obj == null) {
                    break;
                }
                if (!(obj instanceof pa.p)) {
                    if (obj != m0.f19613c) {
                        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, null)) {
                            if (atomicReferenceFieldUpdater.get(this) != obj) {
                                break;
                            }
                        }
                        runnable = (Runnable) obj;
                        break loop1;
                    }
                    break;
                }
                pa.p pVar = (pa.p) obj;
                Object objD = pVar.d();
                if (objD != pa.p.f21289g) {
                    runnable = (Runnable) objD;
                    break;
                }
                pa.p pVarC = pVar.c();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, pVarC) && atomicReferenceFieldUpdater.get(this) == obj) {
                }
            }
            if (runnable != null) {
                runnable.run();
                return 0L;
            }
            kotlin.collections.p pVar2 = this.f19566m;
            if (((pVar2 == null || pVar2.isEmpty()) ? Long.MAX_VALUE : 0L) != 0) {
                Object obj2 = f19595o.get(this);
                if (obj2 != null) {
                    if (obj2 instanceof pa.p) {
                        long j10 = pa.p.f21288f.get((pa.p) obj2);
                        if (((int) (1073741823 & j10)) == ((int) ((j10 & 1152921503533105152L) >> 30))) {
                        }
                    } else if (obj2 == m0.f19613c) {
                        return Long.MAX_VALUE;
                    }
                }
                g1 g1Var2 = (g1) f19596p.get(this);
                if (g1Var2 != null && (f1Var = (f1) g1Var2.b()) != null) {
                    long jNanoTime2 = f1Var.f19585i - System.nanoTime();
                    if (jNanoTime2 >= 0) {
                        return jNanoTime2;
                    }
                }
                return Long.MAX_VALUE;
            }
        }
        return 0L;
    }

    public void N0(Runnable runnable) {
        if (!O0(runnable)) {
            n0.f19622r.N0(runnable);
            return;
        }
        Thread threadK0 = K0();
        if (Thread.currentThread() != threadK0) {
            LockSupport.unpark(threadK0);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x004b, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean O0(java.lang.Runnable r7) {
        /*
            r6 = this;
        L0:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = ka.h1.f19595o
            java.lang.Object r1 = r0.get(r6)
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = ka.h1.f19597q
            int r2 = r2.get(r6)
            r3 = 0
            if (r2 == 0) goto L10
            return r3
        L10:
            r2 = 1
            if (r1 != 0) goto L22
        L13:
            r1 = 0
            boolean r1 = r0.compareAndSet(r6, r1, r7)
            if (r1 == 0) goto L1b
            goto L62
        L1b:
            java.lang.Object r1 = r0.get(r6)
            if (r1 == 0) goto L13
            goto L0
        L22:
            boolean r4 = r1 instanceof pa.p
            if (r4 == 0) goto L47
            r4 = r1
            pa.p r4 = (pa.p) r4
            int r5 = r4.a(r7)
            if (r5 == 0) goto L62
            if (r5 == r2) goto L35
            r0 = 2
            if (r5 == r0) goto L4b
            goto L0
        L35:
            pa.p r3 = r4.c()
        L39:
            boolean r2 = r0.compareAndSet(r6, r1, r3)
            if (r2 == 0) goto L40
            goto L0
        L40:
            java.lang.Object r2 = r0.get(r6)
            if (r2 == r1) goto L39
            goto L0
        L47:
            c2.a r4 = ka.m0.f19613c
            if (r1 != r4) goto L4c
        L4b:
            return r3
        L4c:
            pa.p r3 = new pa.p
            r4 = 8
            r3.<init>(r4, r2)
            r4 = r1
            java.lang.Runnable r4 = (java.lang.Runnable) r4
            r3.a(r4)
            r3.a(r7)
        L5c:
            boolean r4 = r0.compareAndSet(r6, r1, r3)
            if (r4 == 0) goto L63
        L62:
            return r2
        L63:
            java.lang.Object r4 = r0.get(r6)
            if (r4 == r1) goto L5c
            goto L0
        */
        throw new UnsupportedOperationException("Method not decompiled: ka.h1.O0(java.lang.Runnable):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean P0() {
        /*
            r7 = this;
            kotlin.collections.p r0 = r7.f19566m
            r1 = 1
            if (r0 == 0) goto La
            boolean r0 = r0.isEmpty()
            goto Lb
        La:
            r0 = r1
        Lb:
            r2 = 0
            if (r0 != 0) goto Lf
            goto L54
        Lf:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = ka.h1.f19596p
            java.lang.Object r0 = r0.get(r7)
            ka.g1 r0 = (ka.g1) r0
            if (r0 == 0) goto L27
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r3 = pa.d0.f21260b
            int r0 = r3.get(r0)
            if (r0 != 0) goto L23
            r0 = r1
            goto L24
        L23:
            r0 = r2
        L24:
            if (r0 != 0) goto L27
            goto L54
        L27:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = ka.h1.f19595o
            java.lang.Object r0 = r0.get(r7)
            if (r0 != 0) goto L30
            goto L53
        L30:
            boolean r3 = r0 instanceof pa.p
            if (r3 == 0) goto L4f
            pa.p r0 = (pa.p) r0
            java.util.concurrent.atomic.AtomicLongFieldUpdater r3 = pa.p.f21288f
            long r3 = r3.get(r0)
            r5 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r5 = r5 & r3
            int r0 = (int) r5
            r5 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r3 = r3 & r5
            r5 = 30
            long r3 = r3 >> r5
            int r3 = (int) r3
            if (r0 != r3) goto L4e
            return r1
        L4e:
            return r2
        L4f:
            c2.a r3 = ka.m0.f19613c
            if (r0 != r3) goto L54
        L53:
            return r1
        L54:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: ka.h1.P0():boolean");
    }

    public final void Q0(long j10, f1 f1Var) {
        int iF;
        Thread threadK0;
        int i10 = f19597q.get(this);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19596p;
        if (i10 != 0) {
            iF = 1;
        } else {
            g1 g1Var = (g1) atomicReferenceFieldUpdater.get(this);
            if (g1Var == null) {
                g1 g1Var2 = new g1();
                g1Var2.f19592c = j10;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, g1Var2) && atomicReferenceFieldUpdater.get(this) == null) {
                }
                g1Var = (g1) atomicReferenceFieldUpdater.get(this);
            }
            iF = f1Var.f(j10, g1Var, this);
        }
        if (iF != 0) {
            if (iF == 1) {
                M0(j10, f1Var);
                return;
            } else {
                if (iF != 2) {
                    throw new IllegalStateException("unexpected result");
                }
                return;
            }
        }
        g1 g1Var3 = (g1) atomicReferenceFieldUpdater.get(this);
        if ((g1Var3 != null ? (f1) g1Var3.b() : null) != f1Var || Thread.currentThread() == (threadK0 = K0())) {
            return;
        }
        LockSupport.unpark(threadK0);
    }

    @Override // ka.f0
    public final void dispatch(d7.j jVar, Runnable runnable) {
        N0(runnable);
    }

    @Override // ka.r0
    public z0 l(long j10, t2 t2Var, d7.j jVar) {
        return o0.f19625a.l(j10, t2Var, jVar);
    }

    @Override // ka.c1
    public void shutdown() {
        pa.e0 e0VarD;
        r2.f19629a.set(null);
        f19597q.set(this, 1);
        c2.a aVar = m0.f19613c;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19595o;
        loop0: while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj != null) {
                if (!(obj instanceof pa.p)) {
                    if (obj != aVar) {
                        pa.p pVar = new pa.p(8, true);
                        pVar.a((Runnable) obj);
                        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, pVar)) {
                            if (atomicReferenceFieldUpdater.get(this) != obj) {
                                break;
                            }
                        }
                        break loop0;
                    }
                    break;
                }
                ((pa.p) obj).b();
                break;
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, aVar)) {
                if (atomicReferenceFieldUpdater.get(this) != null) {
                    break;
                }
            }
            break loop0;
        }
        while (D0() <= 0) {
        }
        long jNanoTime = System.nanoTime();
        while (true) {
            g1 g1Var = (g1) f19596p.get(this);
            if (g1Var == null) {
                return;
            }
            synchronized (g1Var) {
                e0VarD = pa.d0.f21260b.get(g1Var) > 0 ? g1Var.d(0) : null;
            }
            f1 f1Var = (f1) e0VarD;
            if (f1Var == null) {
                return;
            } else {
                M0(jNanoTime, f1Var);
            }
        }
    }

    @Override // ka.r0
    public final void y(long j10, l lVar) {
        long j11 = j10 > 0 ? j10 >= 9223372036854L ? Long.MAX_VALUE : 1000000 * j10 : 0L;
        if (j11 < 4611686018427387903L) {
            long jNanoTime = System.nanoTime();
            d1 d1Var = new d1(this, j11 + jNanoTime, lVar);
            Q0(jNanoTime, d1Var);
            lVar.c(new i(d1Var, 1));
        }
    }
}
