package ua;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import ka.y2;
import q7.w;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public class i implements f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f22101c = AtomicReferenceFieldUpdater.newUpdater(i.class, Object.class, TtmlNode.TAG_HEAD);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f22102d = AtomicLongFieldUpdater.newUpdater(i.class, "deqIdx");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f22103e = AtomicReferenceFieldUpdater.newUpdater(i.class, Object.class, "tail");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f22104f = AtomicLongFieldUpdater.newUpdater(i.class, "enqIdx");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f22105g = AtomicIntegerFieldUpdater.newUpdater(i.class, "_availablePermits");

    @w
    private volatile int _availablePermits;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f22106a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s8.d f22107b;

    @w
    private volatile long deqIdx;

    @w
    private volatile long enqIdx;

    @w
    private volatile Object head;

    @w
    private volatile Object tail;

    public i(int i10, int i11) {
        this.f22106a = i10;
        if (i10 <= 0) {
            throw new IllegalArgumentException(a0.c.i(i10, "Semaphore should have at least 1 permit, but had ").toString());
        }
        if (i11 < 0 || i11 > i10) {
            throw new IllegalArgumentException(a0.c.i(i10, "The number of acquired permits should be in 0..").toString());
        }
        k kVar = new k(0L, null, 2);
        this.head = kVar;
        this.tail = kVar;
        this._availablePermits = i10 - i11;
        this.f22107b = new s8.d(this, 8);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
    
        r5.d(r3, r4.f22107b);
     */
    @Override // ua.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(d7.d r5) {
        /*
            r4 = this;
        L0:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = ua.i.f22105g
            int r1 = r0.getAndDecrement(r4)
            int r2 = r4.f22106a
            if (r1 > r2) goto L0
            x6.t0 r3 = x6.t0.f22605a
            if (r1 <= 0) goto Lf
            goto L3e
        Lf:
            d7.d r5 = t7.a.A(r5)
            ka.l r5 = ka.m0.l(r5)
            boolean r1 = r4.d(r5)     // Catch: java.lang.Throwable -> L3f
            if (r1 != 0) goto L31
        L1d:
            int r1 = r0.getAndDecrement(r4)     // Catch: java.lang.Throwable -> L3f
            if (r1 > r2) goto L1d
            if (r1 <= 0) goto L2b
            s8.d r0 = r4.f22107b     // Catch: java.lang.Throwable -> L3f
            r5.d(r3, r0)     // Catch: java.lang.Throwable -> L3f
            goto L31
        L2b:
            boolean r1 = r4.d(r5)     // Catch: java.lang.Throwable -> L3f
            if (r1 == 0) goto L1d
        L31:
            java.lang.Object r5 = r5.r()
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3a
            goto L3b
        L3a:
            r5 = r3
        L3b:
            if (r5 != r0) goto L3e
            return r5
        L3e:
            return r3
        L3f:
            r0 = move-exception
            r5.y()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ua.i.a(d7.d):java.lang.Object");
    }

    public final boolean d(y2 y2Var) {
        Object objB;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f22103e;
        k kVar = (k) atomicReferenceFieldUpdater.get(this);
        long andIncrement = f22104f.getAndIncrement(this);
        g gVar = g.f22099i;
        long j10 = andIncrement / ((long) j.f22113f);
        loop0: while (true) {
            objB = pa.a.b(kVar, j10, gVar);
            if (!pa.a.e(objB)) {
                pa.w wVarC = pa.a.c(objB);
                while (true) {
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
        k kVar2 = (k) pa.a.c(objB);
        AtomicReferenceArray atomicReferenceArray = kVar2.f22114o;
        int i10 = (int) (andIncrement % ((long) j.f22113f));
        while (!atomicReferenceArray.compareAndSet(i10, null, y2Var)) {
            if (atomicReferenceArray.get(i10) != null) {
                c2.a aVar = j.f22109b;
                c2.a aVar2 = j.f22110c;
                while (!atomicReferenceArray.compareAndSet(i10, aVar, aVar2)) {
                    if (atomicReferenceArray.get(i10) != aVar) {
                        return false;
                    }
                }
                ((ka.k) y2Var).d(t0.f22605a, this.f22107b);
                return true;
            }
        }
        y2Var.e(kVar2, i10);
        return true;
    }

    @Override // ua.f
    public final void release() {
        int i10;
        Object objB;
        boolean zB;
        do {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f22105g;
            int andIncrement = atomicIntegerFieldUpdater.getAndIncrement(this);
            int i11 = this.f22106a;
            if (andIncrement >= i11) {
                do {
                    i10 = atomicIntegerFieldUpdater.get(this);
                    if (i10 <= i11) {
                        break;
                    }
                } while (!atomicIntegerFieldUpdater.compareAndSet(this, i10, i11));
                throw new IllegalStateException(("The number of released permits cannot be greater than " + i11).toString());
            }
            if (andIncrement >= 0) {
                return;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f22101c;
            k kVar = (k) atomicReferenceFieldUpdater.get(this);
            long andIncrement2 = f22102d.getAndIncrement(this);
            long j10 = andIncrement2 / ((long) j.f22113f);
            h hVar = h.f22100i;
            while (true) {
                objB = pa.a.b(kVar, j10, hVar);
                if (pa.a.e(objB)) {
                    break;
                }
                pa.w wVarC = pa.a.c(objB);
                while (true) {
                    pa.w wVar = (pa.w) atomicReferenceFieldUpdater.get(this);
                    if (wVar.f21298m >= wVarC.f21298m) {
                        break;
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
            }
            k kVar2 = (k) pa.a.c(objB);
            AtomicReferenceArray atomicReferenceArray = kVar2.f22114o;
            kVar2.a();
            zB = false;
            if (kVar2.f21298m <= j10) {
                int i12 = (int) (andIncrement2 % ((long) j.f22113f));
                Object andSet = atomicReferenceArray.getAndSet(i12, j.f22109b);
                if (andSet == null) {
                    int i13 = j.f22108a;
                    for (int i14 = 0; i14 < i13; i14++) {
                        if (atomicReferenceArray.get(i12) == j.f22110c) {
                            zB = true;
                            break;
                        }
                    }
                    c2.a aVar = j.f22109b;
                    c2.a aVar2 = j.f22111d;
                    while (true) {
                        if (!atomicReferenceArray.compareAndSet(i12, aVar, aVar2)) {
                            if (atomicReferenceArray.get(i12) != aVar) {
                                break;
                            }
                        } else {
                            zB = true;
                            break;
                        }
                    }
                    zB = !zB;
                } else if (andSet != j.f22112e) {
                    if (andSet instanceof ka.k) {
                        ka.k kVar3 = (ka.k) andSet;
                        c2.a aVarA = kVar3.a(t0.f22605a, this.f22107b);
                        if (aVarA != null) {
                            kVar3.f(aVarA);
                            zB = true;
                            break;
                            break;
                        }
                    } else {
                        if (!(andSet instanceof sa.f)) {
                            throw new IllegalStateException(("unexpected: " + andSet).toString());
                        }
                        zB = ((sa.f) andSet).b();
                    }
                }
            }
        } while (!zB);
    }
}
