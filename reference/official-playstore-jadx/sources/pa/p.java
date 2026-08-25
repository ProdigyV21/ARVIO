package pa;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final class p {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f21287e = AtomicReferenceFieldUpdater.newUpdater(p.class, Object.class, "_next");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f21288f = AtomicLongFieldUpdater.newUpdater(p.class, "_state");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c2.a f21289g = new c2.a("REMOVE_FROZEN", 5);

    @q7.w
    private volatile Object _next;

    @q7.w
    private volatile long _state;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f21290a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f21291b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f21292c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AtomicReferenceArray f21293d;

    public p(int i10, boolean z) {
        this.f21290a = i10;
        this.f21291b = z;
        int i11 = i10 - 1;
        this.f21292c = i11;
        this.f21293d = new AtomicReferenceArray(i10);
        if (i11 > 1073741823) {
            throw new IllegalStateException("Check failed.");
        }
        if ((i10 & i11) != 0) {
            throw new IllegalStateException("Check failed.");
        }
    }

    public final int a(Object obj) {
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f21288f;
            long j10 = atomicLongFieldUpdater.get(this);
            if ((3458764513820540928L & j10) != 0) {
                return (2305843009213693952L & j10) != 0 ? 2 : 1;
            }
            int i10 = (int) (1073741823 & j10);
            int i11 = (int) ((1152921503533105152L & j10) >> 30);
            int i12 = this.f21292c;
            if (((i11 + 2) & i12) == (i10 & i12)) {
                return 1;
            }
            boolean z = this.f21291b;
            AtomicReferenceArray atomicReferenceArray = this.f21293d;
            if (z || atomicReferenceArray.get(i11 & i12) == null) {
                if (f21288f.compareAndSet(this, j10, ((-1152921503533105153L) & j10) | (((long) ((i11 + 1) & 1073741823)) << 30))) {
                    atomicReferenceArray.set(i11 & i12, obj);
                    p pVarC = this;
                    while ((atomicLongFieldUpdater.get(pVarC) & 1152921504606846976L) != 0) {
                        pVarC = pVarC.c();
                        AtomicReferenceArray atomicReferenceArray2 = pVarC.f21293d;
                        int i13 = pVarC.f21292c & i11;
                        Object obj2 = atomicReferenceArray2.get(i13);
                        if ((obj2 instanceof o) && ((o) obj2).f21286a == i11) {
                            atomicReferenceArray2.set(i13, obj);
                        } else {
                            pVarC = null;
                        }
                        if (pVarC == null) {
                            return 0;
                        }
                    }
                    return 0;
                }
            } else {
                int i14 = this.f21290a;
                if (i14 < 1024 || ((i11 - i10) & 1073741823) > (i14 >> 1)) {
                    return 1;
                }
            }
        }
    }

    public final boolean b() {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j10;
        do {
            atomicLongFieldUpdater = f21288f;
            j10 = atomicLongFieldUpdater.get(this);
            if ((j10 & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j10) != 0) {
                return false;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j10, 2305843009213693952L | j10));
        return true;
    }

    public final p c() {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j10;
        p pVar;
        while (true) {
            atomicLongFieldUpdater = f21288f;
            j10 = atomicLongFieldUpdater.get(this);
            if ((j10 & 1152921504606846976L) != 0) {
                pVar = this;
                break;
            }
            long j11 = 1152921504606846976L | j10;
            pVar = this;
            if (atomicLongFieldUpdater.compareAndSet(pVar, j10, j11)) {
                j10 = j11;
                break;
            }
        }
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f21287e;
            p pVar2 = (p) atomicReferenceFieldUpdater.get(this);
            if (pVar2 != null) {
                return pVar2;
            }
            p pVar3 = new p(pVar.f21290a * 2, pVar.f21291b);
            int i10 = (int) (1073741823 & j10);
            int i11 = (int) ((1152921503533105152L & j10) >> 30);
            while (true) {
                int i12 = pVar.f21292c;
                int i13 = i10 & i12;
                if (i13 == (i12 & i11)) {
                    break;
                }
                Object oVar = pVar.f21293d.get(i13);
                if (oVar == null) {
                    oVar = new o(i10);
                }
                pVar3.f21293d.set(pVar3.f21292c & i10, oVar);
                i10++;
            }
            atomicLongFieldUpdater.set(pVar3, (-1152921504606846977L) & j10);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, pVar3) && atomicReferenceFieldUpdater.get(this) == null) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0041, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object d() {
        /*
            r30 = this;
            r1 = r30
        L2:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r6 = pa.p.f21288f
            long r2 = r6.get(r1)
            r7 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r4 = r2 & r7
            r9 = 0
            int r0 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r0 == 0) goto L15
            c2.a r0 = pa.p.f21289g
            return r0
        L15:
            r11 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r4 = r2 & r11
            int r0 = (int) r4
            r4 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r4 = r4 & r2
            r13 = 30
            long r4 = r4 >> r13
            int r4 = (int) r4
            int r5 = r1.f21292c
            r4 = r4 & r5
            r13 = r0 & r5
            r14 = 0
            if (r4 != r13) goto L2e
            goto L41
        L2e:
            java.util.concurrent.atomic.AtomicReferenceArray r15 = r1.f21293d
            java.lang.Object r4 = r15.get(r13)
            boolean r5 = r1.f21291b
            if (r4 != 0) goto L3b
            if (r5 == 0) goto L2
            goto L41
        L3b:
            r16 = r7
            boolean r7 = r4 instanceof pa.o
            if (r7 == 0) goto L42
        L41:
            return r14
        L42:
            int r0 = r0 + 1
            r7 = 1073741823(0x3fffffff, float:1.9999999)
            r0 = r0 & r7
            r7 = -1073741824(0xffffffffc0000000, double:NaN)
            long r18 = r2 & r7
            r20 = r7
            long r7 = (long) r0
            long r18 = r18 | r7
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = pa.p.f21288f
            r28 = r18
            r18 = r4
            r19 = r5
            r4 = r28
            boolean r0 = r0.compareAndSet(r1, r2, r4)
            if (r0 == 0) goto L66
            r15.set(r13, r14)
            return r18
        L66:
            r1 = r30
            if (r19 == 0) goto L2
        L6a:
            long r24 = r6.get(r1)
            long r2 = r24 & r11
            int r0 = (int) r2
            long r2 = r24 & r16
            int r2 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r2 == 0) goto L7d
            pa.p r0 = r1.c()
            r1 = r0
            goto L96
        L7d:
            long r2 = r24 & r20
            long r26 = r2 | r7
            java.util.concurrent.atomic.AtomicLongFieldUpdater r22 = pa.p.f21288f
            r23 = r1
            boolean r1 = r22.compareAndSet(r23, r24, r26)
            r2 = r23
            if (r1 == 0) goto L99
            java.util.concurrent.atomic.AtomicReferenceArray r1 = r2.f21293d
            int r2 = r2.f21292c
            r0 = r0 & r2
            r1.set(r0, r14)
            r1 = r14
        L96:
            if (r1 != 0) goto L6a
            return r18
        L99:
            r1 = r2
            goto L6a
        */
        throw new UnsupportedOperationException("Method not decompiled: pa.p.d():java.lang.Object");
    }
}
