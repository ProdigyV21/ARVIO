package ma;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import ka.m0;
import ka.y2;
import kotlin.jvm.internal.q0;
import kotlinx.coroutines.channels.ClosedReceiveChannelException;
import kotlinx.coroutines.channels.ClosedSendChannelException;
import x6.c0;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public class f implements j {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f20312l = AtomicLongFieldUpdater.newUpdater(f.class, "sendersAndCloseStatus");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f20313m = AtomicLongFieldUpdater.newUpdater(f.class, "receivers");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f20314n = AtomicLongFieldUpdater.newUpdater(f.class, "bufferEnd");

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f20315o = AtomicLongFieldUpdater.newUpdater(f.class, "completedExpandBuffersAndPauseFlag");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f20316p = AtomicReferenceFieldUpdater.newUpdater(f.class, Object.class, "sendSegment");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f20317q = AtomicReferenceFieldUpdater.newUpdater(f.class, Object.class, "receiveSegment");

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f20318r = AtomicReferenceFieldUpdater.newUpdater(f.class, Object.class, "bufferEndSegment");

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f20319s = AtomicReferenceFieldUpdater.newUpdater(f.class, Object.class, "_closeCause");

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f20320t = AtomicReferenceFieldUpdater.newUpdater(f.class, Object.class, "closeHandler");

    @q7.w
    private volatile Object _closeCause;

    @q7.w
    private volatile long bufferEnd;

    @q7.w
    private volatile Object bufferEndSegment;

    @q7.w
    private volatile Object closeHandler;

    @q7.w
    private volatile long completedExpandBuffersAndPauseFlag;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f20321i;

    @q7.w
    private volatile Object receiveSegment;

    @q7.w
    private volatile long receivers;

    @q7.w
    private volatile Object sendSegment;

    @q7.w
    private volatile long sendersAndCloseStatus;

    public f(int i10) {
        this.f20321i = i10;
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Invalid channel capacity: ", ", should be >=0").toString());
        }
        p pVar = h.f20323a;
        this.bufferEnd = i10 != 0 ? i10 != Integer.MAX_VALUE ? i10 : Long.MAX_VALUE : 0L;
        this.completedExpandBuffersAndPauseFlag = f20314n.get(this);
        p pVar2 = new p(0L, null, this, 3);
        this.sendSegment = pVar2;
        this.receiveSegment = pVar2;
        this.bufferEndSegment = t() ? h.f20323a : pVar2;
        this._closeCause = h.f20340s;
    }

    public static boolean A(Object obj) {
        if (obj instanceof ka.k) {
            return h.a((ka.k) obj, t0.f22605a, null);
        }
        if (obj instanceof sa.f) {
            throw new ClassCastException();
        }
        throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
    }

    public static final p a(f fVar, long j10, p pVar) {
        Object objB;
        f fVar2;
        p pVar2 = h.f20323a;
        g gVar = g.f20322i;
        loop0: while (true) {
            objB = pa.a.b(pVar, j10, gVar);
            if (!pa.a.e(objB)) {
                pa.w wVarC = pa.a.c(objB);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f20316p;
                    pa.w wVar = (pa.w) atomicReferenceFieldUpdater.get(fVar);
                    if (wVar.f21298m >= wVarC.f21298m) {
                        break loop0;
                    }
                    if (!wVarC.i()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(fVar, wVar, wVarC)) {
                        if (atomicReferenceFieldUpdater.get(fVar) != wVar) {
                            if (wVarC.e()) {
                                wVarC.d();
                            }
                        }
                    }
                    if (wVar.e()) {
                        wVar.d();
                    }
                }
            } else {
                break;
            }
        }
        boolean zE = pa.a.e(objB);
        AtomicLongFieldUpdater atomicLongFieldUpdater = f20313m;
        if (zE) {
            fVar.isClosedForSend();
            if (pVar.f21298m * ((long) h.f20324b) < atomicLongFieldUpdater.get(fVar)) {
                pVar.a();
                return null;
            }
        } else {
            p pVar3 = (p) pa.a.c(objB);
            long j11 = pVar3.f21298m;
            if (j11 <= j10) {
                return pVar3;
            }
            long j12 = ((long) h.f20324b) * j11;
            while (true) {
                long j13 = f20312l.get(fVar);
                long j14 = 1152921504606846975L & j13;
                if (j14 >= j12) {
                    fVar2 = fVar;
                    break;
                }
                fVar2 = fVar;
                if (f20312l.compareAndSet(fVar2, j13, (((long) ((int) (j13 >> 60))) << 60) + j14)) {
                    break;
                }
                fVar = fVar2;
            }
            if (j11 * ((long) h.f20324b) < atomicLongFieldUpdater.get(fVar2)) {
                pVar3.a();
            }
        }
        return null;
    }

    public static final void c(f fVar, Object obj, ka.l lVar) {
        lVar.resumeWith(new c0(fVar.n()));
    }

    public static final int d(f fVar, p pVar, int i10, Object obj, long j10, Object obj2, boolean z) {
        pVar.m(i10, obj);
        if (z) {
            return fVar.C(pVar, i10, obj, j10, obj2, z);
        }
        Object objK = pVar.k(i10);
        if (objK == null) {
            if (fVar.f(j10)) {
                if (pVar.j(i10, null, h.f20326d)) {
                    return 1;
                }
            } else {
                if (obj2 == null) {
                    return 3;
                }
                if (pVar.j(i10, null, obj2)) {
                    return 2;
                }
            }
        } else if (objK instanceof y2) {
            pVar.m(i10, null);
            if (fVar.z(objK, obj)) {
                pVar.n(i10, h.f20331i);
                return 0;
            }
            c2.a aVar = h.k;
            if (pVar.f20349p.getAndSet((i10 * 2) + 1, aVar) == aVar) {
                return 5;
            }
            pVar.l(i10, true);
            return 5;
        }
        return fVar.C(pVar, i10, obj, j10, obj2, z);
    }

    public static void p(f fVar) {
        AtomicLongFieldUpdater atomicLongFieldUpdater = f20315o;
        if ((atomicLongFieldUpdater.addAndGet(fVar, 1L) & 4611686018427387904L) != 0) {
            while ((atomicLongFieldUpdater.get(fVar) & 4611686018427387904L) != 0) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object w(ma.f r13, f7.c r14) {
        /*
            boolean r0 = r14 instanceof ma.d
            if (r0 == 0) goto L14
            r0 = r14
            ma.d r0 = (ma.d) r0
            int r1 = r0.f20308m
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.f20308m = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            ma.d r0 = new ma.d
            r0.<init>(r13, r14)
            goto L12
        L1a:
            java.lang.Object r14 = r6.f20306i
            int r0 = r6.f20308m
            r1 = 1
            if (r0 == 0) goto L33
            if (r0 != r1) goto L2b
            k2.c.G(r14)
            ma.o r14 = (ma.o) r14
            java.lang.Object r13 = r14.f20347a
            return r13
        L2b:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L33:
            k2.c.G(r14)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r14 = ma.f.f20317q
            java.lang.Object r14 = r14.get(r13)
            ma.p r14 = (ma.p) r14
        L3e:
            boolean r0 = r13.r()
            if (r0 == 0) goto L4e
            java.lang.Throwable r13 = r13.l()
            ma.m r14 = new ma.m
            r14.<init>(r13)
            return r14
        L4e:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = ma.f.f20313m
            long r4 = r0.getAndIncrement(r13)
            int r0 = ma.h.f20324b
            long r2 = (long) r0
            long r7 = r4 / r2
            long r2 = r4 % r2
            int r3 = (int) r2
            long r9 = r14.f21298m
            int r0 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r0 == 0) goto L6b
            ma.p r0 = r13.k(r7, r14)
            if (r0 != 0) goto L69
            goto L3e
        L69:
            r2 = r0
            goto L6c
        L6b:
            r2 = r14
        L6c:
            r12 = 0
            r7 = r13
            r8 = r2
            r9 = r3
            r10 = r4
            java.lang.Object r13 = r7.B(r8, r9, r10, r12)
            c2.a r14 = ma.h.f20334m
            if (r13 == r14) goto La0
            c2.a r14 = ma.h.f20336o
            if (r13 != r14) goto L8b
            long r13 = r7.o()
            int r13 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r13 >= 0) goto L88
            r2.a()
        L88:
            r14 = r2
            r13 = r7
            goto L3e
        L8b:
            c2.a r14 = ma.h.f20335n
            if (r13 != r14) goto L9c
            r6.f20308m = r1
            r1 = r7
            java.lang.Object r13 = r1.x(r2, r3, r4, r6)
            e7.a r14 = e7.a.f15033i
            if (r13 != r14) goto L9b
            return r14
        L9b:
            return r13
        L9c:
            r2.a()
            return r13
        La0:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "unexpected"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: ma.f.w(ma.f, f7.c):java.lang.Object");
    }

    public final Object B(p pVar, int i10, long j10, Object obj) {
        Object objK = pVar.k(i10);
        AtomicReferenceArray atomicReferenceArray = pVar.f20349p;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f20312l;
        if (objK == null) {
            if (j10 >= (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                if (obj == null) {
                    return h.f20335n;
                }
                if (pVar.j(i10, objK, obj)) {
                    j();
                    return h.f20334m;
                }
            }
        } else if (objK == h.f20326d && pVar.j(i10, objK, h.f20331i)) {
            j();
            Object obj2 = atomicReferenceArray.get(i10 * 2);
            pVar.m(i10, null);
            return obj2;
        }
        while (true) {
            Object objK2 = pVar.k(i10);
            if (objK2 == null || objK2 == h.f20327e) {
                if (j10 < (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                    if (pVar.j(i10, objK2, h.f20330h)) {
                        j();
                        return h.f20336o;
                    }
                } else {
                    if (obj == null) {
                        return h.f20335n;
                    }
                    if (pVar.j(i10, objK2, obj)) {
                        j();
                        return h.f20334m;
                    }
                }
            } else if (objK2 != h.f20326d) {
                c2.a aVar = h.f20332j;
                if (objK2 == aVar) {
                    return h.f20336o;
                }
                if (objK2 == h.f20330h) {
                    return h.f20336o;
                }
                if (objK2 == h.f20333l) {
                    j();
                    return h.f20336o;
                }
                if (objK2 != h.f20329g && pVar.j(i10, objK2, h.f20328f)) {
                    boolean z = objK2 instanceof y;
                    if (z) {
                        objK2 = ((y) objK2).f20356a;
                    }
                    if (A(objK2)) {
                        pVar.n(i10, h.f20331i);
                        j();
                        Object obj3 = atomicReferenceArray.get(i10 * 2);
                        pVar.m(i10, null);
                        return obj3;
                    }
                    pVar.n(i10, aVar);
                    pVar.h();
                    if (z) {
                        j();
                    }
                    return h.f20336o;
                }
            } else if (pVar.j(i10, objK2, h.f20331i)) {
                j();
                Object obj4 = atomicReferenceArray.get(i10 * 2);
                pVar.m(i10, null);
                return obj4;
            }
        }
    }

    public final int C(p pVar, int i10, Object obj, long j10, Object obj2, boolean z) {
        while (true) {
            Object objK = pVar.k(i10);
            if (objK == null) {
                if (!f(j10) || z) {
                    if (z) {
                        if (pVar.j(i10, null, h.f20332j)) {
                            pVar.h();
                            return 4;
                        }
                    } else {
                        if (obj2 == null) {
                            return 3;
                        }
                        if (pVar.j(i10, null, obj2)) {
                            return 2;
                        }
                    }
                } else if (pVar.j(i10, null, h.f20326d)) {
                    break;
                }
            } else {
                if (objK != h.f20327e) {
                    c2.a aVar = h.k;
                    if (objK == aVar) {
                        pVar.m(i10, null);
                        return 5;
                    }
                    if (objK == h.f20330h) {
                        pVar.m(i10, null);
                        return 5;
                    }
                    if (objK == h.f20333l) {
                        pVar.m(i10, null);
                        isClosedForSend();
                        return 4;
                    }
                    pVar.m(i10, null);
                    if (objK instanceof y) {
                        objK = ((y) objK).f20356a;
                    }
                    if (z(objK, obj)) {
                        pVar.n(i10, h.f20331i);
                        return 0;
                    }
                    if (pVar.f20349p.getAndSet((i10 * 2) + 1, aVar) != aVar) {
                        pVar.l(i10, true);
                    }
                    return 5;
                }
                if (pVar.j(i10, objK, h.f20326d)) {
                    break;
                }
            }
        }
        return 1;
    }

    public final void D(long j10) {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        f fVar = this;
        if (fVar.t()) {
            return;
        }
        while (true) {
            atomicLongFieldUpdater = f20314n;
            if (atomicLongFieldUpdater.get(fVar) > j10) {
                break;
            } else {
                fVar = this;
            }
        }
        int i10 = h.f20325c;
        int i11 = 0;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater2 = f20315o;
            if (i11 < i10) {
                long j11 = atomicLongFieldUpdater.get(fVar);
                if (j11 == (4611686018427387903L & atomicLongFieldUpdater2.get(fVar)) && j11 == atomicLongFieldUpdater.get(fVar)) {
                    return;
                } else {
                    i11++;
                }
            } else {
                while (true) {
                    long j12 = atomicLongFieldUpdater2.get(fVar);
                    if (atomicLongFieldUpdater2.compareAndSet(fVar, j12, (j12 & 4611686018427387903L) + 4611686018427387904L)) {
                        break;
                    } else {
                        fVar = this;
                    }
                }
                while (true) {
                    long j13 = atomicLongFieldUpdater.get(fVar);
                    long j14 = atomicLongFieldUpdater2.get(fVar);
                    long j15 = j14 & 4611686018427387903L;
                    boolean z = (j14 & 4611686018427387904L) != 0;
                    if (j13 == j15 && j13 == atomicLongFieldUpdater.get(fVar)) {
                        break;
                    }
                    if (z) {
                        fVar = this;
                    } else {
                        fVar = this;
                        atomicLongFieldUpdater2.compareAndSet(fVar, j14, 4611686018427387904L + j15);
                    }
                }
                while (true) {
                    long j16 = atomicLongFieldUpdater2.get(fVar);
                    if (atomicLongFieldUpdater2.compareAndSet(fVar, j16, j16 & 4611686018427387903L)) {
                        return;
                    } else {
                        fVar = this;
                    }
                }
            }
        }
    }

    @Override // ma.w
    public final Object b() {
        p pVar;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f20313m;
        long j10 = atomicLongFieldUpdater.get(this);
        long j11 = f20312l.get(this);
        if (q(j11, true)) {
            return new m(l());
        }
        long j12 = j11 & 1152921504606846975L;
        n nVar = o.f20346b;
        if (j10 >= j12) {
            return nVar;
        }
        Object obj = h.k;
        p pVar2 = (p) f20317q.get(this);
        while (!r()) {
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(this);
            long j13 = h.f20324b;
            long j14 = andIncrement / j13;
            int i10 = (int) (andIncrement % j13);
            if (pVar2.f21298m != j14) {
                p pVarK = k(j14, pVar2);
                if (pVarK == null) {
                    continue;
                } else {
                    pVar = pVarK;
                }
            } else {
                pVar = pVar2;
            }
            Object objB = B(pVar, i10, andIncrement, obj);
            p pVar3 = pVar;
            if (objB == h.f20334m) {
                y2 y2Var = obj instanceof y2 ? (y2) obj : null;
                if (y2Var != null) {
                    y2Var.e(pVar3, i10);
                }
                D(andIncrement);
                pVar3.h();
                return nVar;
            }
            if (objB != h.f20336o) {
                if (objB == h.f20335n) {
                    throw new IllegalStateException("unexpected");
                }
                pVar3.a();
                return objB;
            }
            if (andIncrement < o()) {
                pVar3.a();
            }
            pVar2 = pVar3;
        }
        return new m(l());
    }

    @Override // ma.w
    public final void cancel(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new CancellationException("Channel was cancelled");
        }
        g(cancellationException, true);
    }

    @Override // ma.x
    public final boolean close(Throwable th) {
        return g(th, false);
    }

    @Override // ma.w
    public final Object e(f7.j jVar) {
        return w(this, jVar);
    }

    public final boolean f(long j10) {
        return j10 < f20314n.get(this) || j10 < f20313m.get(this) + ((long) this.f20321i);
    }

    public final boolean g(Throwable th, boolean z) {
        f fVar;
        boolean z5;
        long j10;
        long j11;
        long j12;
        Object obj;
        long j13;
        long j14;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f20312l;
        if (!z) {
            fVar = this;
            break;
        }
        do {
            j14 = atomicLongFieldUpdater.get(this);
            if (((int) (j14 >> 60)) != 0) {
                fVar = this;
                break;
            }
            p pVar = h.f20323a;
            fVar = this;
        } while (!atomicLongFieldUpdater.compareAndSet(fVar, j14, (j14 & 1152921504606846975L) + (((long) 1) << 60)));
        c2.a aVar = h.f20340s;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f20319s;
            if (atomicReferenceFieldUpdater.compareAndSet(this, aVar, th)) {
                z5 = true;
                break;
            }
            if (atomicReferenceFieldUpdater.get(this) != aVar) {
                z5 = false;
                break;
            }
        }
        if (z) {
            do {
                j13 = atomicLongFieldUpdater.get(this);
            } while (!atomicLongFieldUpdater.compareAndSet(fVar, j13, (((long) 3) << 60) + (j13 & 1152921504606846975L)));
        } else {
            do {
                j10 = atomicLongFieldUpdater.get(this);
                int i10 = (int) (j10 >> 60);
                if (i10 == 0) {
                    j11 = j10 & 1152921504606846975L;
                    j12 = 2;
                } else {
                    if (i10 != 1) {
                        break;
                    }
                    j11 = j10 & 1152921504606846975L;
                    j12 = 3;
                }
            } while (!atomicLongFieldUpdater.compareAndSet(fVar, j10, (j12 << 60) + j11));
        }
        isClosedForSend();
        if (z5) {
            loop3: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f20320t;
                obj = atomicReferenceFieldUpdater2.get(this);
                c2.a aVar2 = obj == null ? h.f20338q : h.f20339r;
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, obj, aVar2)) {
                    if (atomicReferenceFieldUpdater2.get(this) != obj) {
                        break;
                    }
                }
            }
            if (obj != null) {
                q0.e(1, obj);
                ((r7.l) obj).invoke(l());
                return z5;
            }
        }
        return z5;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x008d, code lost:
    
        r1 = (ma.p) ((pa.d) pa.d.f21259l.get(r1));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final ma.p h(long r13) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ma.f.h(long):ma.p");
    }

    public final void i(long j10) {
        p pVar = (p) f20317q.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f20313m;
            long j11 = atomicLongFieldUpdater.get(this);
            if (j10 < Math.max(((long) this.f20321i) + j11, f20314n.get(this))) {
                return;
            }
            if (atomicLongFieldUpdater.compareAndSet(this, j11, 1 + j11)) {
                long j12 = h.f20324b;
                long j13 = j11 / j12;
                int i10 = (int) (j11 % j12);
                if (pVar.f21298m != j13) {
                    p pVarK = k(j13, pVar);
                    if (pVarK != null) {
                        pVar = pVarK;
                    }
                }
                p pVar2 = pVar;
                if (B(pVar2, i10, j11, null) != h.f20336o || j11 < o()) {
                    pVar2.a();
                }
                pVar = pVar2;
            }
        }
    }

    @Override // ma.x
    public final void invokeOnClose(r7.l lVar) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = f20320t;
            if (atomicReferenceFieldUpdater.compareAndSet(this, null, lVar)) {
                return;
            }
        } while (atomicReferenceFieldUpdater.get(this) == null);
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            c2.a aVar = h.f20338q;
            if (obj != aVar) {
                if (obj == h.f20339r) {
                    throw new IllegalStateException("Another handler was already registered and successfully invoked");
                }
                throw new IllegalStateException(("Another handler is already registered: " + obj).toString());
            }
            c2.a aVar2 = h.f20339r;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, aVar, aVar2)) {
                if (atomicReferenceFieldUpdater.get(this) != aVar) {
                    break;
                }
            }
            lVar.invoke(l());
            return;
        }
    }

    @Override // ma.x
    public final boolean isClosedForSend() {
        return q(f20312l.get(this), false);
    }

    @Override // ma.w
    public final l iterator() {
        return new c(this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x018e, code lost:
    
        p(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0191, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j() {
        /*
            Method dump skipped, instruction units count: 402
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ma.f.j():void");
    }

    public final p k(long j10, p pVar) {
        Object objB;
        long j11;
        p pVar2 = h.f20323a;
        g gVar = g.f20322i;
        loop0: while (true) {
            objB = pa.a.b(pVar, j10, gVar);
            if (!pa.a.e(objB)) {
                pa.w wVarC = pa.a.c(objB);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f20317q;
                    pa.w wVar = (pa.w) atomicReferenceFieldUpdater.get(this);
                    if (wVar.f21298m >= wVarC.f21298m) {
                        break loop0;
                    }
                    if (!wVarC.i()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, wVar, wVarC)) {
                        if (atomicReferenceFieldUpdater.get(this) != wVar) {
                            if (wVarC.e()) {
                                wVarC.d();
                            }
                        }
                    }
                    if (wVar.e()) {
                        wVar.d();
                    }
                }
            } else {
                break;
            }
        }
        if (pa.a.e(objB)) {
            isClosedForSend();
            if (pVar.f21298m * ((long) h.f20324b) < o()) {
                pVar.a();
                return null;
            }
        } else {
            p pVar3 = (p) pa.a.c(objB);
            long j12 = pVar3.f21298m;
            if (!t() && j10 <= f20314n.get(this) / ((long) h.f20324b)) {
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f20318r;
                    pa.w wVar2 = (pa.w) atomicReferenceFieldUpdater2.get(this);
                    if (wVar2.f21298m >= j12 || !pVar3.i()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater2.compareAndSet(this, wVar2, pVar3)) {
                        if (atomicReferenceFieldUpdater2.get(this) != wVar2) {
                            if (pVar3.e()) {
                                pVar3.d();
                            }
                        }
                    }
                    if (wVar2.e()) {
                        wVar2.d();
                    }
                }
            }
            if (j12 <= j10) {
                return pVar3;
            }
            long j13 = j12 * ((long) h.f20324b);
            do {
                j11 = f20313m.get(this);
                if (j11 >= j13) {
                    break;
                }
            } while (!f20313m.compareAndSet(this, j11, j13));
            if (j12 * ((long) h.f20324b) < o()) {
                pVar3.a();
            }
        }
        return null;
    }

    public final Throwable l() {
        return (Throwable) f20319s.get(this);
    }

    public final Throwable m() {
        Throwable thL = l();
        return thL == null ? new ClosedReceiveChannelException("Channel was closed") : thL;
    }

    public final Throwable n() {
        Throwable thL = l();
        return thL == null ? new ClosedSendChannelException("Channel was closed") : thL;
    }

    public final long o() {
        return f20312l.get(this) & 1152921504606846975L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x00a2, code lost:
    
        r0 = (ma.p) ((pa.d) pa.d.f21259l.get(r0));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean q(long r14, boolean r16) {
        /*
            Method dump skipped, instruction units count: 368
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ma.f.q(long, boolean):boolean");
    }

    public final boolean r() {
        return q(f20312l.get(this), true);
    }

    @Override // ma.w
    public final Object receive(d7.d dVar) throws Throwable {
        p pVar;
        Throwable th;
        p pVar2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f20317q;
        p pVar3 = (p) atomicReferenceFieldUpdater.get(this);
        while (!r()) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f20313m;
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(this);
            long j10 = h.f20324b;
            long j11 = andIncrement / j10;
            int i10 = (int) (andIncrement % j10);
            if (pVar3.f21298m != j11) {
                p pVarK = k(j11, pVar3);
                if (pVarK == null) {
                    continue;
                } else {
                    pVar = pVarK;
                }
            } else {
                pVar = pVar3;
            }
            Object objB = B(pVar, i10, andIncrement, null);
            c2.a aVar = h.f20334m;
            if (objB == aVar) {
                throw new IllegalStateException("unexpected");
            }
            c2.a aVar2 = h.f20336o;
            if (objB == aVar2) {
                if (andIncrement < o()) {
                    pVar.a();
                }
                pVar3 = pVar;
            } else {
                if (objB != h.f20335n) {
                    pVar.a();
                    return objB;
                }
                ka.l lVarL = m0.l(t7.a.A(dVar));
                f fVar = this;
                try {
                    Object objB2 = fVar.B(pVar, i10, andIncrement, lVarL);
                    if (objB2 == aVar) {
                        lVarL.e(pVar, i10);
                    } else {
                        if (objB2 == aVar2) {
                            if (andIncrement < o()) {
                                pVar.a();
                            }
                            p pVar4 = (p) atomicReferenceFieldUpdater.get(this);
                            while (true) {
                                if (r()) {
                                    lVarL.resumeWith(new c0(m()));
                                    break;
                                }
                                ka.l lVar = lVarL;
                                try {
                                    long andIncrement2 = atomicLongFieldUpdater.getAndIncrement(this);
                                    long j12 = h.f20324b;
                                    long j13 = andIncrement2 / j12;
                                    int i11 = (int) (andIncrement2 % j12);
                                    if (pVar4.f21298m != j13) {
                                        try {
                                            p pVarK2 = k(j13, pVar4);
                                            if (pVarK2 == null) {
                                                lVarL = lVar;
                                            } else {
                                                pVar2 = pVarK2;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            lVarL = lVar;
                                            lVarL.y();
                                            throw th;
                                        }
                                    } else {
                                        pVar2 = pVar4;
                                    }
                                    objB2 = fVar.B(pVar2, i11, andIncrement2, lVar);
                                    p pVar5 = pVar2;
                                    lVarL = lVar;
                                    if (objB2 == h.f20334m) {
                                        lVarL.e(pVar5, i11);
                                        break;
                                    }
                                    if (objB2 == h.f20336o) {
                                        if (andIncrement2 < o()) {
                                            pVar5.a();
                                        }
                                        fVar = this;
                                        pVar4 = pVar5;
                                    } else {
                                        if (objB2 == h.f20335n) {
                                            throw new IllegalStateException("unexpected");
                                        }
                                        pVar5.a();
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    lVarL = lVar;
                                    th = th;
                                    lVarL.y();
                                    throw th;
                                }
                            }
                        } else {
                            pVar.a();
                        }
                        lVarL.d(objB2, null);
                    }
                    return lVarL.r();
                } catch (Throwable th4) {
                    th = th4;
                }
            }
        }
        Throwable thM = m();
        int i12 = pa.x.f21299a;
        throw thM;
    }

    public boolean s() {
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x0176, code lost:
    
        return r13;
     */
    /* JADX WARN: Removed duplicated region for block: B:132:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0164 A[RETURN] */
    @Override // ma.x
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object send(java.lang.Object r24, d7.d r25) {
        /*
            Method dump skipped, instruction units count: 380
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ma.f.send(java.lang.Object, d7.d):java.lang.Object");
    }

    public final boolean t() {
        long j10 = f20314n.get(this);
        return j10 == 0 || j10 == Long.MAX_VALUE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:90:0x01cc, code lost:
    
        r16 = r7;
        r3 = (ma.p) r3.b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01d5, code lost:
    
        if (r3 != null) goto L97;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String toString() {
        /*
            Method dump skipped, instruction units count: 507
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ma.f.toString():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00be A[SYNTHETIC] */
    @Override // ma.x
    /* JADX INFO: renamed from: trySend-JP2dKIU */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo6685trySendJP2dKIU(java.lang.Object r16) {
        /*
            r15 = this;
            java.util.concurrent.atomic.AtomicLongFieldUpdater r8 = ma.f.f20312l
            long r1 = r8.get(r15)
            r9 = 0
            boolean r3 = r15.q(r1, r9)
            r10 = 1
            r11 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            if (r3 == 0) goto L15
            r1 = r9
            goto L1b
        L15:
            long r1 = r1 & r11
            boolean r1 = r15.f(r1)
            r1 = r1 ^ r10
        L1b:
            ma.n r13 = ma.o.f20346b
            if (r1 == 0) goto L20
            return r13
        L20:
            c2.a r6 = ma.h.f20332j
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = ma.f.f20316p
            java.lang.Object r1 = r1.get(r15)
            ma.p r1 = (ma.p) r1
        L2a:
            long r2 = r8.getAndIncrement(r15)
            long r4 = r2 & r11
            boolean r7 = r15.q(r2, r9)
            int r14 = ma.h.f20324b
            long r2 = (long) r14
            long r11 = r4 / r2
            long r2 = r4 % r2
            int r2 = (int) r2
            long r9 = r1.f21298m
            int r3 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r3 == 0) goto L5d
            ma.p r3 = a(r15, r11, r1)
            if (r3 != 0) goto L5c
            if (r7 == 0) goto L54
            java.lang.Throwable r1 = r15.n()
            ma.m r2 = new ma.m
            r2.<init>(r1)
            return r2
        L54:
            r9 = 0
            r10 = 1
        L56:
            r11 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            goto L2a
        L5c:
            r1 = r3
        L5d:
            r0 = r15
            r3 = r16
            int r9 = d(r0, r1, r2, r3, r4, r6, r7)
            x6.t0 r3 = x6.t0.f22605a
            if (r9 == 0) goto Lbe
            r10 = 1
            if (r9 == r10) goto Lbd
            r3 = 2
            if (r9 == r3) goto L9c
            r2 = 3
            if (r9 == r2) goto L94
            r2 = 4
            if (r9 == r2) goto L7d
            r2 = 5
            if (r9 == r2) goto L78
            goto L7b
        L78:
            r1.a()
        L7b:
            r9 = 0
            goto L56
        L7d:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = ma.f.f20313m
            long r2 = r2.get(r15)
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 >= 0) goto L8a
            r1.a()
        L8a:
            java.lang.Throwable r1 = r15.n()
            ma.m r2 = new ma.m
            r2.<init>(r1)
            return r2
        L94:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "unexpected"
            r1.<init>(r2)
            throw r1
        L9c:
            if (r7 == 0) goto Lab
            r1.h()
            java.lang.Throwable r1 = r15.n()
            ma.m r2 = new ma.m
            r2.<init>(r1)
            return r2
        Lab:
            boolean r3 = r6 instanceof ka.y2
            if (r3 == 0) goto Lb2
            ka.y2 r6 = (ka.y2) r6
            goto Lb3
        Lb2:
            r6 = 0
        Lb3:
            if (r6 == 0) goto Lb9
            int r2 = r2 + r14
            r6.e(r1, r2)
        Lb9:
            r1.h()
            return r13
        Lbd:
            return r3
        Lbe:
            r1.a()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: ma.f.mo6685trySendJP2dKIU(java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0011, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void u(long r5, ma.p r7) {
        /*
            r4 = this;
        L0:
            long r0 = r7.f21298m
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L11
            pa.d r0 = r7.b()
            ma.p r0 = (ma.p) r0
            if (r0 != 0) goto Lf
            goto L11
        Lf:
            r7 = r0
            goto L0
        L11:
            boolean r5 = r7.c()
            if (r5 == 0) goto L22
            pa.d r5 = r7.b()
            ma.p r5 = (ma.p) r5
            if (r5 != 0) goto L20
            goto L22
        L20:
            r7 = r5
            goto L11
        L22:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = ma.f.f20318r
            java.lang.Object r6 = r5.get(r4)
            pa.w r6 = (pa.w) r6
            long r0 = r6.f21298m
            long r2 = r7.f21298m
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L33
            goto L49
        L33:
            boolean r0 = r7.i()
            if (r0 != 0) goto L3a
            goto L11
        L3a:
            boolean r0 = r5.compareAndSet(r4, r6, r7)
            if (r0 == 0) goto L4a
            boolean r5 = r6.e()
            if (r5 == 0) goto L49
            r6.d()
        L49:
            return
        L4a:
            java.lang.Object r0 = r5.get(r4)
            if (r0 == r6) goto L3a
            boolean r5 = r7.e()
            if (r5 == 0) goto L22
            r7.d()
            goto L22
        */
        throw new UnsupportedOperationException("Method not decompiled: ma.f.u(long, ma.p):void");
    }

    public final Object v(d7.d dVar, Object obj) {
        ka.l lVar = new ka.l(1, t7.a.A(dVar));
        lVar.s();
        lVar.resumeWith(new c0(n()));
        Object objR = lVar.r();
        return objR == e7.a.f15033i ? objR : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object x(ma.p r14, int r15, long r16, f7.c r18) {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ma.f.x(ma.p, int, long, f7.c):java.lang.Object");
    }

    public final void y(y2 y2Var, boolean z) {
        if (y2Var instanceof ka.k) {
            ((d7.d) y2Var).resumeWith(new c0(z ? m() : n()));
            return;
        }
        if (y2Var instanceof v) {
            ((v) y2Var).f20355i.resumeWith(new o(new m(l())));
            return;
        }
        if (!(y2Var instanceof c)) {
            if (y2Var instanceof sa.f) {
                p pVar = h.f20323a;
                ((sa.f) y2Var).b();
                return;
            } else {
                throw new IllegalStateException(("Unexpected waiter: " + y2Var).toString());
            }
        }
        c cVar = (c) y2Var;
        ka.l lVar = cVar.f20304l;
        cVar.f20304l = null;
        cVar.f20303i = h.f20333l;
        Throwable thL = cVar.f20305m.l();
        if (thL == null) {
            lVar.resumeWith(Boolean.FALSE);
        } else {
            lVar.resumeWith(new c0(thL));
        }
    }

    public final boolean z(Object obj, Object obj2) {
        if (obj instanceof sa.f) {
            return ((sa.f) obj).b();
        }
        if (obj instanceof v) {
            return h.a(((v) obj).f20355i, new o(obj2), null);
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            ka.l lVar = cVar.f20304l;
            cVar.f20304l = null;
            cVar.f20303i = obj2;
            return h.a(lVar, Boolean.TRUE, null);
        }
        if (obj instanceof ka.k) {
            return h.a((ka.k) obj, obj2, null);
        }
        throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
    }
}
