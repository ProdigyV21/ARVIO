package na;

/* JADX INFO: loaded from: classes5.dex */
public final class q implements j {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ e f20637i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ e.h f20638l;

    public q(e eVar, e.h hVar) {
        this.f20637i = eVar;
        this.f20638l = hVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // na.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object collect(na.k r10, d7.d r11) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r11 instanceof na.p
            if (r0 == 0) goto L13
            r0 = r11
            na.p r0 = (na.p) r0
            int r1 = r0.f20633l
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f20633l = r1
            goto L18
        L13:
            na.p r0 = new na.p
            r0.<init>(r9, r11)
        L18:
            java.lang.Object r11 = r0.f20632i
            int r1 = r0.f20633l
            x6.t0 r2 = x6.t0.f22605a
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            e7.a r7 = e7.a.f15033i
            if (r1 == 0) goto L52
            if (r1 == r5) goto L46
            if (r1 == r4) goto L3e
            if (r1 != r3) goto L36
            java.lang.Object r10 = r0.f20635n
            oa.t r10 = (oa.t) r10
            k2.c.G(r11)     // Catch: java.lang.Throwable -> L34
            goto L7d
        L34:
            r11 = move-exception
            goto L85
        L36:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L3e:
            java.lang.Object r10 = r0.f20635n
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            k2.c.G(r11)
            goto L9f
        L46:
            na.k r10 = r0.f20636o
            java.lang.Object r1 = r0.f20635n
            na.q r1 = (na.q) r1
            k2.c.G(r11)     // Catch: java.lang.Throwable -> L50
            goto L65
        L50:
            r10 = move-exception
            goto L8b
        L52:
            k2.c.G(r11)
            na.e r11 = r9.f20637i     // Catch: java.lang.Throwable -> L89
            r0.f20635n = r9     // Catch: java.lang.Throwable -> L89
            r0.f20636o = r10     // Catch: java.lang.Throwable -> L89
            r0.f20633l = r5     // Catch: java.lang.Throwable -> L89
            java.lang.Object r11 = r11.collect(r10, r0)     // Catch: java.lang.Throwable -> L89
            if (r11 != r7) goto L64
            goto L9e
        L64:
            r1 = r9
        L65:
            oa.t r11 = new oa.t
            d7.j r4 = r0.getContext()
            r11.<init>(r10, r4)
            e.h r10 = r1.f20638l     // Catch: java.lang.Throwable -> L81
            r0.f20635n = r11     // Catch: java.lang.Throwable -> L81
            r0.f20636o = r6     // Catch: java.lang.Throwable -> L81
            r0.f20633l = r3     // Catch: java.lang.Throwable -> L81
            r10.invoke(r11, r6, r0)     // Catch: java.lang.Throwable -> L81
            if (r2 != r7) goto L7c
            goto L9e
        L7c:
            r10 = r11
        L7d:
            r10.releaseIntercepted()
            return r2
        L81:
            r10 = move-exception
            r8 = r11
            r11 = r10
            r10 = r8
        L85:
            r10.releaseIntercepted()
            throw r11
        L89:
            r10 = move-exception
            r1 = r9
        L8b:
            na.l1 r11 = new na.l1
            r11.<init>(r10)
            e.h r1 = r1.f20638l
            r0.f20635n = r10
            r0.f20636o = r6
            r0.f20633l = r4
            java.lang.Object r11 = na.y0.c(r11, r1, r10, r0)
            if (r11 != r7) goto L9f
        L9e:
            return r7
        L9f:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: na.q.collect(na.k, d7.d):java.lang.Object");
    }
}
