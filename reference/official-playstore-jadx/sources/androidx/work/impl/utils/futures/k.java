package androidx.work.impl.utils.futures;

/* JADX INFO: loaded from: classes3.dex */
public final class k extends i {
    public final boolean h(Object obj) {
        if (obj == null) {
            obj = i.f7036q;
        }
        if (!i.f7035p.e(this, null, obj)) {
            return false;
        }
        i.b(this);
        return true;
    }

    public final boolean i(Throwable th) {
        if (!i.f7035p.e(this, null, new c(th))) {
            return false;
        }
        i.b(this);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean j(com.google.common.util.concurrent.d1 r6) {
        /*
            r5 = this;
            r6.getClass()
            java.lang.Object r0 = r5.f7037i
            r1 = 0
            if (r0 != 0) goto L44
            boolean r0 = r6.isDone()
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L20
            java.lang.Object r6 = androidx.work.impl.utils.futures.i.e(r6)
            com.google.common.util.concurrent.p0 r0 = androidx.work.impl.utils.futures.i.f7035p
            boolean r6 = r0.e(r5, r3, r6)
            if (r6 == 0) goto L4f
            androidx.work.impl.utils.futures.i.b(r5)
            return r2
        L20:
            androidx.work.impl.utils.futures.f r0 = new androidx.work.impl.utils.futures.f
            r0.<init>(r5, r6)
            com.google.common.util.concurrent.p0 r4 = androidx.work.impl.utils.futures.i.f7035p
            boolean r3 = r4.e(r5, r3, r0)
            if (r3 == 0) goto L42
            androidx.work.impl.utils.futures.j r1 = androidx.work.impl.utils.futures.j.f7040i     // Catch: java.lang.Throwable -> L33
            r6.addListener(r0, r1)     // Catch: java.lang.Throwable -> L33
            return r2
        L33:
            r6 = move-exception
            androidx.work.impl.utils.futures.c r1 = new androidx.work.impl.utils.futures.c     // Catch: java.lang.Throwable -> L3a
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L3a
            goto L3c
        L3a:
            androidx.work.impl.utils.futures.c r1 = androidx.work.impl.utils.futures.c.f7017b
        L3c:
            com.google.common.util.concurrent.p0 r6 = androidx.work.impl.utils.futures.i.f7035p
            r6.e(r5, r0, r1)
            return r2
        L42:
            java.lang.Object r0 = r5.f7037i
        L44:
            boolean r2 = r0 instanceof androidx.work.impl.utils.futures.a
            if (r2 == 0) goto L4f
            androidx.work.impl.utils.futures.a r0 = (androidx.work.impl.utils.futures.a) r0
            boolean r0 = r0.f7014a
            r6.cancel(r0)
        L4f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.utils.futures.k.j(com.google.common.util.concurrent.d1):boolean");
    }
}
