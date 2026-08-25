package ma;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes5.dex */
public final class p extends pa.w {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final f f20348o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final AtomicReferenceArray f20349p;

    public p(long j10, p pVar, f fVar, int i10) {
        super(j10, pVar, i10);
        this.f20348o = fVar;
        this.f20349p = new AtomicReferenceArray(h.f20324b * 2);
    }

    @Override // pa.w
    public final int f() {
        return h.f20324b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0059, code lost:
    
        m(r5, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x005c, code lost:
    
        if (r0 == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x005e, code lost:
    
        r2.getClass();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0061, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
    
        return;
     */
    @Override // pa.w
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(int r5, d7.j r6) {
        /*
            r4 = this;
            int r6 = ma.h.f20324b
            if (r5 < r6) goto L6
            r0 = 1
            goto L7
        L6:
            r0 = 0
        L7:
            if (r0 == 0) goto La
            int r5 = r5 - r6
        La:
            int r6 = r5 * 2
            java.util.concurrent.atomic.AtomicReferenceArray r1 = r4.f20349p
            r1.get(r6)
        L11:
            java.lang.Object r6 = r4.k(r5)
            boolean r1 = r6 instanceof ka.y2
            ma.f r2 = r4.f20348o
            r3 = 0
            if (r1 != 0) goto L62
            boolean r1 = r6 instanceof ma.y
            if (r1 == 0) goto L21
            goto L62
        L21:
            c2.a r1 = ma.h.f20332j
            if (r6 == r1) goto L59
            c2.a r1 = ma.h.k
            if (r6 != r1) goto L2a
            goto L59
        L2a:
            c2.a r1 = ma.h.f20329g
            if (r6 == r1) goto L11
            c2.a r1 = ma.h.f20328f
            if (r6 != r1) goto L33
            goto L11
        L33:
            c2.a r5 = ma.h.f20331i
            if (r6 == r5) goto L7c
            c2.a r5 = ma.h.f20326d
            if (r6 != r5) goto L3c
            goto L7c
        L3c:
            c2.a r5 = ma.h.f20333l
            if (r6 != r5) goto L41
            goto L7c
        L41:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "unexpected state: "
            r0.<init>(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L59:
            r4.m(r5, r3)
            if (r0 == 0) goto L7c
            r2.getClass()
            return
        L62:
            if (r0 == 0) goto L67
            c2.a r1 = ma.h.f20332j
            goto L69
        L67:
            c2.a r1 = ma.h.k
        L69:
            boolean r6 = r4.j(r5, r6, r1)
            if (r6 == 0) goto L11
            r4.m(r5, r3)
            r6 = r0 ^ 1
            r4.l(r5, r6)
            if (r0 == 0) goto L7c
            r2.getClass()
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ma.p.g(int, d7.j):void");
    }

    public final boolean j(int i10, Object obj, Object obj2) {
        AtomicReferenceArray atomicReferenceArray;
        int i11 = (i10 * 2) + 1;
        do {
            atomicReferenceArray = this.f20349p;
            if (atomicReferenceArray.compareAndSet(i11, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceArray.get(i11) == obj);
        return false;
    }

    public final Object k(int i10) {
        return this.f20349p.get((i10 * 2) + 1);
    }

    public final void l(int i10, boolean z) {
        if (z) {
            this.f20348o.D((this.f21298m * ((long) h.f20324b)) + ((long) i10));
        }
        h();
    }

    public final void m(int i10, Object obj) {
        this.f20349p.lazySet(i10 * 2, obj);
    }

    public final void n(int i10, Object obj) {
        this.f20349p.set((i10 * 2) + 1, obj);
    }
}
