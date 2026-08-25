package ua;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import ka.m0;
import q7.w;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends i implements a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f22097h = AtomicReferenceFieldUpdater.newUpdater(d.class, Object.class, "owner");

    @w
    private volatile Object owner;

    public d(boolean z) {
        super(1, z ? 1 : 0);
        this.owner = z ? null : e.f22098a;
    }

    @Override // ua.a
    public final void b(Object obj) {
        while (e()) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f22097h;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            c2.a aVar = e.f22098a;
            if (obj2 != aVar) {
                if (obj2 == obj || obj == null) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, aVar)) {
                        if (atomicReferenceFieldUpdater.get(this) != obj2) {
                            break;
                        }
                    }
                    release();
                    return;
                }
                throw new IllegalStateException(("This mutex is locked by " + obj2 + ", but " + obj + " is expected").toString());
            }
        }
        throw new IllegalStateException("This mutex is not locked");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0022, code lost:
    
        r0.d(r1, r4.f22107b);
     */
    @Override // ua.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object c(d7.d r5) {
        /*
            r4 = this;
            boolean r0 = r4.tryLock()
            x6.t0 r1 = x6.t0.f22605a
            if (r0 == 0) goto L9
            goto L3b
        L9:
            d7.d r5 = t7.a.A(r5)
            ka.l r5 = ka.m0.l(r5)
            ua.c r0 = new ua.c     // Catch: java.lang.Throwable -> L3c
            r0.<init>(r4, r5)     // Catch: java.lang.Throwable -> L3c
        L16:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = ua.i.f22105g     // Catch: java.lang.Throwable -> L3c
            int r2 = r2.getAndDecrement(r4)     // Catch: java.lang.Throwable -> L3c
            int r3 = r4.f22106a     // Catch: java.lang.Throwable -> L3c
            if (r2 > r3) goto L16
            if (r2 <= 0) goto L28
            s8.d r2 = r4.f22107b     // Catch: java.lang.Throwable -> L3c
            r0.d(r1, r2)     // Catch: java.lang.Throwable -> L3c
            goto L2e
        L28:
            boolean r2 = r4.d(r0)     // Catch: java.lang.Throwable -> L3c
            if (r2 == 0) goto L16
        L2e:
            java.lang.Object r5 = r5.r()
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L37
            goto L38
        L37:
            r5 = r1
        L38:
            if (r5 != r0) goto L3b
            return r5
        L3b:
            return r1
        L3c:
            r0 = move-exception
            r5.y()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ua.d.c(d7.d):java.lang.Object");
    }

    public final boolean e() {
        return Math.max(i.f22105g.get(this), 0) == 0;
    }

    public final String toString() {
        return "Mutex@" + m0.j(this) + "[isLocked=" + e() + ",owner=" + f22097h.get(this) + ']';
    }

    @Override // ua.a
    public final boolean tryLock() {
        int i10;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = i.f22105g;
            int i11 = atomicIntegerFieldUpdater.get(this);
            int i12 = this.f22106a;
            if (i11 > i12) {
                do {
                    i10 = atomicIntegerFieldUpdater.get(this);
                    if (i10 > i12) {
                    }
                } while (!atomicIntegerFieldUpdater.compareAndSet(this, i10, i12));
            } else {
                if (i11 <= 0) {
                    return false;
                }
                if (atomicIntegerFieldUpdater.compareAndSet(this, i11, i11 - 1)) {
                    f22097h.set(this, null);
                    return true;
                }
            }
        }
    }
}
