package na;

/* JADX INFO: loaded from: classes5.dex */
public abstract class y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c2.a f20689a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c2.a f20690b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c2.a f20691c;

    static {
        int i10 = 5;
        f20689a = new c2.a("NO_VALUE", i10);
        f20690b = new c2.a("NONE", i10);
        f20691c = new c2.a("PENDING", i10);
    }

    public static x0 a(int i10, int i11) {
        int i12 = (i11 & 1) != 0 ? 0 : 1;
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        int i13 = (i11 & 4) != 0 ? 1 : 2;
        if (i10 < 0) {
            throw new IllegalArgumentException(a0.c.i(i10, "extraBufferCapacity cannot be negative, but was ").toString());
        }
        if (i12 <= 0 && i10 <= 0 && i13 != 1) {
            throw new IllegalArgumentException("replay or extraBufferCapacity must be positive with non-default onBufferOverflow strategy ".concat(g8.b.v(i13)).toString());
        }
        int i14 = i10 + i12;
        if (i14 < 0) {
            i14 = Integer.MAX_VALUE;
        }
        return new x0(i12, i14, i13);
    }

    public static final j1 b(Object obj) {
        if (obj == null) {
            obj = oa.b.f20807b;
        }
        return new j1(obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object c(na.l1 r4, e.h r5, java.lang.Throwable r6, f7.c r7) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            boolean r0 = r7 instanceof na.o
            if (r0 == 0) goto L13
            r0 = r7
            na.o r0 = (na.o) r0
            int r1 = r0.f20630m
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f20630m = r1
            goto L18
        L13:
            na.o r0 = new na.o
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.f20629l
            int r1 = r0.f20630m
            x6.t0 r2 = x6.t0.f22605a
            r3 = 1
            if (r1 == 0) goto L33
            if (r1 != r3) goto L2b
            java.lang.Throwable r6 = r0.f20628i
            k2.c.G(r7)     // Catch: java.lang.Throwable -> L29
            goto L42
        L29:
            r4 = move-exception
            goto L43
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            k2.c.G(r7)
            r0.f20628i = r6     // Catch: java.lang.Throwable -> L29
            r0.f20630m = r3     // Catch: java.lang.Throwable -> L29
            r5.invoke(r4, r6, r0)     // Catch: java.lang.Throwable -> L29
            e7.a r4 = e7.a.f15033i
            if (r2 != r4) goto L42
            return r4
        L42:
            return r2
        L43:
            if (r6 == 0) goto L4a
            if (r6 == r4) goto L4a
            com.google.common.util.concurrent.r0.a(r4, r6)
        L4a:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: na.y0.c(na.l1, e.h, java.lang.Throwable, f7.c):java.lang.Object");
    }

    public static final void d(Object[] objArr, long j10, Object obj) {
        objArr[((int) j10) & (objArr.length - 1)] = obj;
    }

    public static final s0 e(j1 j1Var) {
        return new s0(j1Var, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.io.Serializable f(na.j r4, na.k r5, f7.c r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof na.t
            if (r0 == 0) goto L13
            r0 = r6
            na.t r0 = (na.t) r0
            int r1 = r0.f20651m
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f20651m = r1
            goto L18
        L13:
            na.t r0 = new na.t
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.f20650l
            int r1 = r0.f20651m
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            kotlin.jvm.internal.k0 r4 = r0.f20649i
            k2.c.G(r6)     // Catch: java.lang.Throwable -> L27
            goto L4c
        L27:
            r5 = move-exception
            goto L50
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            k2.c.G(r6)
            kotlin.jvm.internal.k0 r6 = new kotlin.jvm.internal.k0
            r6.<init>()
            androidx.work.impl.constraints.k r1 = new androidx.work.impl.constraints.k     // Catch: java.lang.Throwable -> L4e
            r3 = 1
            r1.<init>(r5, r6, r3)     // Catch: java.lang.Throwable -> L4e
            r0.f20649i = r6     // Catch: java.lang.Throwable -> L4e
            r0.f20651m = r2     // Catch: java.lang.Throwable -> L4e
            java.lang.Object r4 = r4.collect(r1, r0)     // Catch: java.lang.Throwable -> L4e
            e7.a r5 = e7.a.f15033i
            if (r4 != r5) goto L4c
            return r5
        L4c:
            r4 = 0
            return r4
        L4e:
            r5 = move-exception
            r4 = r6
        L50:
            java.lang.Object r4 = r4.f19746i
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            if (r4 == 0) goto L5c
            boolean r6 = r4.equals(r5)
            if (r6 != 0) goto L7e
        L5c:
            d7.j r6 = r0.getContext()
            ka.u1 r0 = ka.u1.f19642i
            d7.j$a r6 = r6.get(r0)
            ka.v1 r6 = (ka.v1) r6
            if (r6 == 0) goto L7f
            boolean r0 = r6.isCancelled()
            if (r0 != 0) goto L71
            goto L7f
        L71:
            java.util.concurrent.CancellationException r6 = r6.getCancellationException()
            if (r6 == 0) goto L7f
            boolean r6 = r6.equals(r5)
            if (r6 != 0) goto L7e
            goto L7f
        L7e:
            throw r5
        L7f:
            if (r4 != 0) goto L82
            return r5
        L82:
            boolean r6 = r5 instanceof java.util.concurrent.CancellationException
            if (r6 == 0) goto L8a
            com.google.common.util.concurrent.r0.a(r4, r5)
            throw r4
        L8a:
            com.google.common.util.concurrent.r0.a(r5, r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: na.y0.f(na.j, na.k, f7.c):java.io.Serializable");
    }

    public static final Object g(j jVar, r7.p pVar, d7.d dVar) {
        int i10 = c0.f20542a;
        oa.i iVarP = p(jVar, new b0(pVar, (d7.d) null));
        d7.k kVar = d7.k.f14688i;
        kotlin.jvm.internal.p.a(kVar, kVar);
        Object objCollect = iVarP.d(kVar, 0, 1).collect(oa.r.f20852i, dVar);
        x6.t0 t0Var = x6.t0.f22605a;
        e7.a aVar = e7.a.f15033i;
        if (objCollect != aVar) {
            objCollect = t0Var;
        }
        return objCollect == aVar ? objCollect : t0Var;
    }

    public static final j h(j jVar) {
        if (jVar instanceof h1) {
            return jVar;
        }
        boolean z = jVar instanceof h;
        n nVar = n.f20624i;
        if (z) {
            h hVar = (h) jVar;
            if (hVar.f20575l == nVar) {
                return hVar;
            }
        }
        return new h(jVar, nVar);
    }

    public static final v i(j jVar, int i10) {
        if (i10 >= 0) {
            return new v(jVar, i10);
        }
        throw new IllegalArgumentException(a0.c.i(i10, "Drop count should be non-negative, but had ").toString());
    }

    public static final Object j(k kVar, j jVar, f7.j jVar2) throws Throwable {
        if (kVar instanceof l1) {
            throw ((l1) kVar).f20612i;
        }
        Object objCollect = jVar.collect(kVar, jVar2);
        return objCollect == e7.a.f15033i ? objCollect : x6.t0.f22605a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0084, code lost:
    
        if (r1.emit(r9, r0) == r4) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0072 A[Catch: all -> 0x0034, TRY_LEAVE, TryCatch #1 {all -> 0x0034, blocks: (B:13:0x002e, B:25:0x0055, B:29:0x006a, B:31:0x0072, B:20:0x0046, B:24:0x0051), top: B:47:0x0020 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0084 -> B:14:0x0031). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object k(na.k r6, ma.w r7, boolean r8, d7.d r9) throws java.lang.Throwable {
        /*
            boolean r0 = r9 instanceof na.m
            if (r0 == 0) goto L13
            r0 = r9
            na.m r0 = (na.m) r0
            int r1 = r0.f20618p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f20618p = r1
            goto L18
        L13:
            na.m r0 = new na.m
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.f20617o
            int r1 = r0.f20618p
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L4a
            if (r1 == r3) goto L3e
            if (r1 != r2) goto L36
            boolean r8 = r0.f20616n
            ma.l r6 = r0.f20615m
            ma.w r7 = r0.f20614l
            na.k r1 = r0.f20613i
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L34
        L31:
            r9 = r6
            r6 = r1
            goto L55
        L34:
            r6 = move-exception
            goto L90
        L36:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3e:
            boolean r8 = r0.f20616n
            ma.l r6 = r0.f20615m
            ma.w r7 = r0.f20614l
            na.k r1 = r0.f20613i
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L34
            goto L6a
        L4a:
            k2.c.G(r9)
            boolean r9 = r6 instanceof na.l1
            if (r9 != 0) goto L98
            ma.l r9 = r7.iterator()     // Catch: java.lang.Throwable -> L34
        L55:
            r0.f20613i = r6     // Catch: java.lang.Throwable -> L34
            r0.f20614l = r7     // Catch: java.lang.Throwable -> L34
            r0.f20615m = r9     // Catch: java.lang.Throwable -> L34
            r0.f20616n = r8     // Catch: java.lang.Throwable -> L34
            r0.f20618p = r3     // Catch: java.lang.Throwable -> L34
            java.lang.Object r1 = r9.a(r0)     // Catch: java.lang.Throwable -> L34
            if (r1 != r4) goto L66
            goto L86
        L66:
            r5 = r1
            r1 = r6
            r6 = r9
            r9 = r5
        L6a:
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L34
            boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L34
            if (r9 == 0) goto L87
            java.lang.Object r9 = r6.next()     // Catch: java.lang.Throwable -> L34
            r0.f20613i = r1     // Catch: java.lang.Throwable -> L34
            r0.f20614l = r7     // Catch: java.lang.Throwable -> L34
            r0.f20615m = r6     // Catch: java.lang.Throwable -> L34
            r0.f20616n = r8     // Catch: java.lang.Throwable -> L34
            r0.f20618p = r2     // Catch: java.lang.Throwable -> L34
            java.lang.Object r9 = r1.emit(r9, r0)     // Catch: java.lang.Throwable -> L34
            if (r9 != r4) goto L31
        L86:
            return r4
        L87:
            if (r8 == 0) goto L8d
            r6 = 0
            r7.cancel(r6)
        L8d:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        L90:
            throw r6     // Catch: java.lang.Throwable -> L91
        L91:
            r9 = move-exception
            if (r8 == 0) goto L97
            qb.d.e(r7, r6)
        L97:
            throw r9
        L98:
            na.l1 r6 = (na.l1) r6
            java.lang.Throwable r6 = r6.f20612i
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: na.y0.k(na.k, ma.w, boolean, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object l(na.j r5, d7.d r6) {
        /*
            c2.a r0 = oa.b.f20807b
            boolean r1 = r6 instanceof na.g0
            if (r1 == 0) goto L15
            r1 = r6
            na.g0 r1 = (na.g0) r1
            int r2 = r1.f20573n
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.f20573n = r2
            goto L1a
        L15:
            na.g0 r1 = new na.g0
            r1.<init>(r6)
        L1a:
            java.lang.Object r6 = r1.f20572m
            int r2 = r1.f20573n
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            na.d0 r5 = r1.f20571l
            kotlin.jvm.internal.k0 r1 = r1.f20570i
            k2.c.G(r6)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L2b
            goto L5e
        L2b:
            r6 = move-exception
            goto L5a
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            k2.c.G(r6)
            kotlin.jvm.internal.k0 r6 = new kotlin.jvm.internal.k0
            r6.<init>()
            r6.f19746i = r0
            na.d0 r2 = new na.d0
            r4 = 0
            r2.<init>(r6, r4)
            r1.f20570i = r6     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L56
            r1.f20571l = r2     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L56
            r1.f20573n = r3     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L56
            java.lang.Object r5 = r5.collect(r2, r1)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L56
            e7.a r1 = e7.a.f15033i
            if (r5 != r1) goto L54
            return r1
        L54:
            r1 = r6
            goto L5e
        L56:
            r5 = move-exception
            r1 = r6
            r6 = r5
            r5 = r2
        L5a:
            na.k r2 = r6.f19977i
            if (r2 != r5) goto L6b
        L5e:
            java.lang.Object r5 = r1.f19746i
            if (r5 == r0) goto L63
            return r5
        L63:
            java.util.NoSuchElementException r5 = new java.util.NoSuchElementException
            java.lang.String r6 = "Expected at least one element"
            r5.<init>(r6)
            throw r5
        L6b:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: na.y0.l(na.j, d7.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v9, types: [r7.p] */
    /* JADX WARN: Type inference failed for: r5v0, types: [na.j] */
    /* JADX WARN: Type inference failed for: r6v0, types: [r7.p] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.StringBuilder] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m(na.j r5, r7.p r6, d7.d r7) {
        /*
            c2.a r0 = oa.b.f20807b
            boolean r1 = r7 instanceof na.h0
            if (r1 == 0) goto L15
            r1 = r7
            na.h0 r1 = (na.h0) r1
            int r2 = r1.f20580o
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.f20580o = r2
            goto L1a
        L15:
            na.h0 r1 = new na.h0
            r1.<init>(r7)
        L1a:
            java.lang.Object r7 = r1.f20579n
            int r2 = r1.f20580o
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            na.f0 r5 = r1.f20578m
            kotlin.jvm.internal.k0 r6 = r1.f20577l
            f7.j r1 = r1.f20576i
            r7.p r1 = (r7.p) r1
            k2.c.G(r7)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L2f
            goto L69
        L2f:
            r7 = move-exception
            goto L65
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            k2.c.G(r7)
            kotlin.jvm.internal.k0 r7 = new kotlin.jvm.internal.k0
            r7.<init>()
            r7.f19746i = r0
            na.f0 r2 = new na.f0
            r4 = 0
            r2.<init>(r6, r7, r4)
            r4 = r6
            f7.j r4 = (f7.j) r4     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L60
            r1.f20576i = r4     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L60
            r1.f20577l = r7     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L60
            r1.f20578m = r2     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L60
            r1.f20580o = r3     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L60
            java.lang.Object r5 = r5.collect(r2, r1)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L60
            e7.a r1 = e7.a.f15033i
            if (r5 != r1) goto L5d
            return r1
        L5d:
            r1 = r6
            r6 = r7
            goto L69
        L60:
            r5 = move-exception
            r1 = r6
            r6 = r7
            r7 = r5
            r5 = r2
        L65:
            na.k r2 = r7.f19977i
            if (r2 != r5) goto L82
        L69:
            java.lang.Object r5 = r6.f19746i
            if (r5 == r0) goto L6e
            return r5
        L6e:
            java.util.NoSuchElementException r5 = new java.util.NoSuchElementException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Expected at least one element matching the predicate "
            r6.<init>(r7)
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L82:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: na.y0.m(na.j, r7.p, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object n(na.j r4, r7.p r5, f7.c r6) {
        /*
            boolean r0 = r6 instanceof na.j0
            if (r0 == 0) goto L13
            r0 = r6
            na.j0 r0 = (na.j0) r0
            int r1 = r0.f20598n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f20598n = r1
            goto L18
        L13:
            na.j0 r0 = new na.j0
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.f20597m
            int r1 = r0.f20598n
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            na.f0 r4 = r0.f20596l
            kotlin.jvm.internal.k0 r5 = r0.f20595i
            k2.c.G(r6)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L29
            goto L5a
        L29:
            r6 = move-exception
            goto L56
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            k2.c.G(r6)
            kotlin.jvm.internal.k0 r6 = new kotlin.jvm.internal.k0
            r6.<init>()
            na.f0 r1 = new na.f0
            r3 = 1
            r1.<init>(r5, r6, r3)
            r0.f20595i = r6     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L52
            r0.f20596l = r1     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L52
            r0.f20598n = r2     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L52
            java.lang.Object r4 = r4.collect(r1, r0)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L52
            e7.a r5 = e7.a.f15033i
            if (r4 != r5) goto L50
            return r5
        L50:
            r5 = r6
            goto L5a
        L52:
            r4 = move-exception
            r5 = r6
            r6 = r4
            r4 = r1
        L56:
            na.k r0 = r6.f19977i
            if (r0 != r4) goto L5d
        L5a:
            java.lang.Object r4 = r5.f19746i
            return r4
        L5d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: na.y0.n(na.j, r7.p, f7.c):java.lang.Object");
    }

    public static final s0 o(t0 t0Var, pa.e eVar, g1 g1Var, Float f10) {
        ma.j.f20343f.getClass();
        ma.i iVar = ma.i.f20341a;
        fi.iki.elonen.f fVar = new fi.iki.elonen.f(t0Var, d7.k.f14688i);
        j1 j1VarB = b(f10);
        return new s0(j1VarB, ka.m0.o(g1Var.equals(b1.f20538a) ? 1 : 4, (d7.j) fVar.f15417l, eVar, new androidx.lifecycle.q0(g1Var, (j) fVar.f15416i, j1VarB, f10, null, 4)));
    }

    public static final oa.i p(j jVar, r7.q qVar) {
        int i10 = c0.f20542a;
        return new oa.i(qVar, jVar, d7.k.f14688i, -2, 1);
    }
}
