package ra;

import androidx.fragment.app.a2;
import h.f0;
import io.ktor.util.date.GMTDateParser;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import ka.m0;
import kotlin.jvm.internal.p;
import pa.u;
import q7.w;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements Executor, Closeable {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f21413r = AtomicLongFieldUpdater.newUpdater(b.class, "parkedWorkersStack");

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f21414s = AtomicLongFieldUpdater.newUpdater(b.class, "controlState");

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f21415t = AtomicIntegerFieldUpdater.newUpdater(b.class, "_isTerminated");

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final c2.a f21416u = new c2.a("NOT_IN_STACK", 5);

    @w
    private volatile int _isTerminated;

    @w
    private volatile long controlState;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f21417i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f21418l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f21419m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f21420n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final e f21421o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final e f21422p;

    @w
    private volatile long parkedWorkersStack;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final u f21423q;

    public b(long j10, int i10, String str, int i11) {
        this.f21417i = i10;
        this.f21418l = i11;
        this.f21419m = j10;
        this.f21420n = str;
        if (i10 < 1) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Core pool size ", " should be at least 1").toString());
        }
        if (i11 < i10) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(i11, i10, "Max pool size ", " should be greater than or equals to core pool size ").toString());
        }
        if (i11 > 2097150) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i11, "Max pool size ", " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (j10 <= 0) {
            throw new IllegalArgumentException(androidx.compose.material3.d.k(j10, "Idle worker keep alive time ", " must be positive").toString());
        }
        this.f21421o = new e();
        this.f21422p = new e();
        this.f21423q = new u((i10 + 1) * 2);
        this.controlState = ((long) i10) << 42;
        this._isTerminated = 0;
    }

    public static /* synthetic */ void l(b bVar, Runnable runnable, int i10) {
        bVar.k(runnable, k.f21438g, (i10 & 4) == 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0085  */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void close() throws java.lang.InterruptedException {
        /*
            r8 = this;
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = ra.b.f21415t
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r8, r1, r2)
            if (r0 != 0) goto Lb
            return
        Lb:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r1 = r0 instanceof ra.a
            r3 = 0
            if (r1 == 0) goto L17
            ra.a r0 = (ra.a) r0
            goto L18
        L17:
            r0 = r3
        L18:
            if (r0 == 0) goto L23
            ra.b r1 = r0.f21412r
            boolean r1 = kotlin.jvm.internal.p.a(r1, r8)
            if (r1 == 0) goto L23
            goto L24
        L23:
            r0 = r3
        L24:
            pa.u r1 = r8.f21423q
            monitor-enter(r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r4 = ra.b.f21414s     // Catch: java.lang.Throwable -> Lbd
            long r4 = r4.get(r8)     // Catch: java.lang.Throwable -> Lbd
            r6 = 2097151(0x1fffff, double:1.0361303E-317)
            long r4 = r4 & r6
            int r4 = (int) r4
            monitor-exit(r1)
            if (r2 > r4) goto L73
            r1 = r2
        L36:
            pa.u r5 = r8.f21423q
            java.lang.Object r5 = r5.b(r1)
            ra.a r5 = (ra.a) r5
            if (r5 == r0) goto L6e
        L40:
            boolean r6 = r5.isAlive()
            if (r6 == 0) goto L4f
            java.util.concurrent.locks.LockSupport.unpark(r5)
            r6 = 10000(0x2710, double:4.9407E-320)
            r5.join(r6)
            goto L40
        L4f:
            ra.m r5 = r5.f21405i
            ra.e r6 = r8.f21422p
            r5.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = ra.m.f21441b
            java.lang.Object r7 = r7.getAndSet(r5, r3)
            ra.h r7 = (ra.h) r7
            if (r7 == 0) goto L63
            r6.a(r7)
        L63:
            ra.h r7 = r5.b()
            if (r7 != 0) goto L6a
            goto L6e
        L6a:
            r6.a(r7)
            goto L63
        L6e:
            if (r1 == r4) goto L73
            int r1 = r1 + 1
            goto L36
        L73:
            ra.e r1 = r8.f21422p
            r1.b()
            ra.e r1 = r8.f21421o
            r1.b()
        L7d:
            if (r0 == 0) goto L85
            ra.h r1 = r0.a(r2)
            if (r1 != 0) goto Lac
        L85:
            ra.e r1 = r8.f21421o
            java.lang.Object r1 = r1.d()
            ra.h r1 = (ra.h) r1
            if (r1 != 0) goto Lac
            ra.e r1 = r8.f21422p
            java.lang.Object r1 = r1.d()
            ra.h r1 = (ra.h) r1
            if (r1 != 0) goto Lac
            if (r0 == 0) goto L9f
            r1 = 5
            r0.h(r1)
        L9f:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = ra.b.f21413r
            r1 = 0
            r0.set(r8, r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = ra.b.f21414s
            r0.set(r8, r1)
            return
        Lac:
            r1.run()     // Catch: java.lang.Throwable -> Lb0
            goto L7d
        Lb0:
            r1 = move-exception
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            java.lang.Thread$UncaughtExceptionHandler r4 = r3.getUncaughtExceptionHandler()
            r4.uncaughtException(r3, r1)
            goto L7d
        Lbd:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ra.b.close():void");
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        l(this, runnable, 6);
    }

    public final int i() {
        synchronized (this.f21423q) {
            try {
                if (f21415t.get(this) != 0) {
                    return -1;
                }
                AtomicLongFieldUpdater atomicLongFieldUpdater = f21414s;
                long j10 = atomicLongFieldUpdater.get(this);
                int i10 = (int) (j10 & 2097151);
                int i11 = i10 - ((int) ((j10 & 4398044413952L) >> 21));
                if (i11 < 0) {
                    i11 = 0;
                }
                if (i11 >= this.f21417i) {
                    return 0;
                }
                if (i10 >= this.f21418l) {
                    return 0;
                }
                int i12 = ((int) (atomicLongFieldUpdater.get(this) & 2097151)) + 1;
                if (i12 <= 0 || this.f21423q.b(i12) != null) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                a aVar = new a(this, i12);
                this.f21423q.c(i12, aVar);
                if (i12 != ((int) (2097151 & atomicLongFieldUpdater.incrementAndGet(this)))) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                int i13 = i11 + 1;
                aVar.start();
                return i13;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void k(Runnable runnable, i iVar, boolean z) {
        h jVar;
        k.f21437f.getClass();
        long jNanoTime = System.nanoTime();
        if (runnable instanceof h) {
            jVar = (h) runnable;
            jVar.f21429i = jNanoTime;
            jVar.f21430l = iVar;
        } else {
            jVar = new j(runnable, jNanoTime, iVar);
        }
        boolean z5 = false;
        boolean z10 = jVar.f21430l.a() == 1;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f21414s;
        long jAddAndGet = z10 ? atomicLongFieldUpdater.addAndGet(this, 2097152L) : 0L;
        Thread threadCurrentThread = Thread.currentThread();
        a aVar = threadCurrentThread instanceof a ? (a) threadCurrentThread : null;
        if (aVar == null || !p.a(aVar.f21412r, this)) {
            aVar = null;
        }
        if (aVar != null && aVar.f21407m != 5 && (jVar.f21430l.a() != 0 || aVar.f21407m != 2)) {
            aVar.f21411q = true;
            m mVar = aVar.f21405i;
            if (z) {
                jVar = mVar.a(jVar);
            } else {
                mVar.getClass();
                h hVar = (h) m.f21441b.getAndSet(mVar, jVar);
                jVar = hVar == null ? null : mVar.a(hVar);
            }
        }
        if (jVar != null) {
            if (!(jVar.f21430l.a() == 1 ? this.f21422p.a(jVar) : this.f21421o.a(jVar))) {
                throw new RejectedExecutionException(a0.c.p(new StringBuilder(), this.f21420n, " was terminated"));
            }
        }
        if (z && aVar != null) {
            z5 = true;
        }
        if (z10) {
            if (z5 || y() || x(jAddAndGet)) {
                return;
            }
            y();
            return;
        }
        if (z5 || y() || x(atomicLongFieldUpdater.get(this))) {
            return;
        }
        y();
    }

    public final void o(a aVar, int i10, int i11) {
        while (true) {
            long j10 = f21413r.get(this);
            int i12 = (int) (2097151 & j10);
            long j11 = (2097152 + j10) & (-2097152);
            if (i12 == i10) {
                if (i11 == 0) {
                    Object objC = aVar.c();
                    while (true) {
                        if (objC == f21416u) {
                            i12 = -1;
                            break;
                        }
                        if (objC == null) {
                            i12 = 0;
                            break;
                        }
                        a aVar2 = (a) objC;
                        int iB = aVar2.b();
                        if (iB != 0) {
                            i12 = iB;
                            break;
                        }
                        objC = aVar2.c();
                    }
                } else {
                    i12 = i11;
                }
            }
            if (i12 >= 0) {
                if (f21413r.compareAndSet(this, j10, ((long) i12) | j11)) {
                    return;
                }
            }
        }
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        u uVar = this.f21423q;
        int iA = uVar.a();
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        for (int i15 = 1; i15 < iA; i15++) {
            a aVar = (a) uVar.b(i15);
            if (aVar != null) {
                m mVar = aVar.f21405i;
                mVar.getClass();
                int i16 = m.f21441b.get(mVar) != null ? (m.f21442c.get(mVar) - m.f21443d.get(mVar)) + 1 : m.f21442c.get(mVar) - m.f21443d.get(mVar);
                int iC = f0.c(aVar.f21407m);
                if (iC == 0) {
                    i10++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i16);
                    sb2.append('c');
                    arrayList.add(sb2.toString());
                } else if (iC == 1) {
                    i11++;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(i16);
                    sb3.append('b');
                    arrayList.add(sb3.toString());
                } else if (iC == 2) {
                    i12++;
                } else if (iC == 3) {
                    i13++;
                    if (i16 > 0) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(i16);
                        sb4.append(GMTDateParser.DAY_OF_MONTH);
                        arrayList.add(sb4.toString());
                    }
                } else if (iC == 4) {
                    i14++;
                }
            }
        }
        long j10 = f21414s.get(this);
        StringBuilder sb5 = new StringBuilder();
        sb5.append(this.f21420n);
        sb5.append('@');
        sb5.append(m0.j(this));
        sb5.append("[Pool Size {core = ");
        int i17 = this.f21417i;
        sb5.append(i17);
        sb5.append(", max = ");
        a2.y(sb5, this.f21418l, "}, Worker States {CPU = ", i10, ", blocking = ");
        a2.y(sb5, i11, ", parked = ", i12, ", dormant = ");
        a2.y(sb5, i13, ", terminated = ", i14, "}, running workers queues = ");
        sb5.append(arrayList);
        sb5.append(", global CPU queue size = ");
        sb5.append(this.f21421o.c());
        sb5.append(", global blocking queue size = ");
        sb5.append(this.f21422p.c());
        sb5.append(", Control State {created workers= ");
        sb5.append((int) (2097151 & j10));
        sb5.append(", blocking tasks = ");
        sb5.append((int) ((4398044413952L & j10) >> 21));
        sb5.append(", CPUs acquired = ");
        sb5.append(i17 - ((int) ((j10 & 9223367638808264704L) >> 42)));
        sb5.append("}]");
        return sb5.toString();
    }

    public final boolean x(long j10) {
        int i10 = ((int) (2097151 & j10)) - ((int) ((j10 & 4398044413952L) >> 21));
        if (i10 < 0) {
            i10 = 0;
        }
        int i11 = this.f21417i;
        if (i10 < i11) {
            int i12 = i();
            if (i12 == 1 && i11 > 1) {
                i();
            }
            if (i12 > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean y() {
        c2.a aVar;
        int iB;
        while (true) {
            long j10 = f21413r.get(this);
            a aVar2 = (a) this.f21423q.b((int) (2097151 & j10));
            if (aVar2 == null) {
                aVar2 = null;
            } else {
                long j11 = (2097152 + j10) & (-2097152);
                Object objC = aVar2.c();
                while (true) {
                    aVar = f21416u;
                    if (objC == aVar) {
                        iB = -1;
                        break;
                    }
                    if (objC == null) {
                        iB = 0;
                        break;
                    }
                    a aVar3 = (a) objC;
                    iB = aVar3.b();
                    if (iB != 0) {
                        break;
                    }
                    objC = aVar3.c();
                }
                if (iB >= 0) {
                    if (f21413r.compareAndSet(this, j10, ((long) iB) | j11)) {
                        aVar2.g(aVar);
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            if (aVar2 == null) {
                return false;
            }
            if (a.f21404s.compareAndSet(aVar2, -1, 0)) {
                LockSupport.unpark(aVar2);
                return true;
            }
        }
    }
}
