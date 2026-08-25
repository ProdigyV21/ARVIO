package androidx.work.impl.constraints;

/* JADX INFO: loaded from: classes3.dex */
public final class i implements na.j {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6810i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f6811l;

    public /* synthetic */ i(Object obj, int i10) {
        this.f6810i = i10;
        this.f6811l = obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003e  */
    @Override // na.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object collect(na.k r8, d7.d r9) {
        /*
            r7 = this;
            int r0 = r7.f6810i
            switch(r0) {
                case 0: goto L87;
                case 1: goto L2b;
                case 2: goto L1b;
                default: goto L5;
            }
        L5:
            java.lang.Object r0 = r7.f6811l
            na.j r0 = (na.j) r0
            na.l0 r1 = new na.l0
            r2 = 0
            r1.<init>(r8, r2)
            java.lang.Object r8 = r0.collect(r1, r9)
            e7.a r9 = e7.a.f15033i
            if (r8 != r9) goto L18
            goto L1a
        L18:
            x6.t0 r8 = x6.t0.f22605a
        L1a:
            return r8
        L1b:
            java.lang.Object r0 = r7.f6811l
            java.io.Serializable r0 = (java.io.Serializable) r0
            java.lang.Object r8 = r8.emit(r0, r9)
            e7.a r9 = e7.a.f15033i
            if (r8 != r9) goto L28
            goto L2a
        L28:
            x6.t0 r8 = x6.t0.f22605a
        L2a:
            return r8
        L2b:
            boolean r0 = r9 instanceof na.l
            if (r0 == 0) goto L3e
            r0 = r9
            na.l r0 = (na.l) r0
            int r1 = r0.f20606l
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L3e
            int r1 = r1 - r2
            r0.f20606l = r1
            goto L43
        L3e:
            na.l r0 = new na.l
            r0.<init>(r7, r9)
        L43:
            java.lang.Object r9 = r0.f20605i
            int r1 = r0.f20606l
            r2 = 1
            if (r1 == 0) goto L5d
            if (r1 != r2) goto L55
            java.util.Iterator r8 = r0.f20609o
            na.k r1 = r0.f20608n
            k2.c.G(r9)
            r9 = r1
            goto L6b
        L55:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L5d:
            k2.c.G(r9)
            java.lang.Object r9 = r7.f6811l
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.Iterator r9 = r9.iterator()
            r6 = r9
            r9 = r8
            r8 = r6
        L6b:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L84
            java.lang.Object r1 = r8.next()
            r0.f20608n = r9
            r0.f20609o = r8
            r0.f20606l = r2
            java.lang.Object r1 = r9.emit(r1, r0)
            e7.a r3 = e7.a.f15033i
            if (r1 != r3) goto L6b
            goto L86
        L84:
            x6.t0 r3 = x6.t0.f22605a
        L86:
            return r3
        L87:
            java.lang.Object r0 = r7.f6811l
            na.j[] r0 = (na.j[]) r0
            a8.g0 r1 = new a8.g0
            r2 = 12
            r1.<init>(r0, r2)
            androidx.work.impl.constraints.h r2 = new androidx.work.impl.constraints.h
            r3 = 3
            r4 = 0
            r5 = 0
            r2.<init>(r3, r4, r5)
            java.lang.Object r8 = oa.b.a(r9, r8, r1, r2, r0)
            e7.a r9 = e7.a.f15033i
            if (r8 != r9) goto La3
            goto La5
        La3:
            x6.t0 r8 = x6.t0.f22605a
        La5:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.constraints.i.collect(na.k, d7.d):java.lang.Object");
    }
}
