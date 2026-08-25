package na;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class x0 extends oa.a implements p0, d, oa.p {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f20676o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f20677p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f20678q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Object[] f20679r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f20680s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f20681t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f20682u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f20683v;

    public x0(int i10, int i11, int i12) {
        this.f20676o = i10;
        this.f20677p = i11;
        this.f20678q = i12;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:0|2|(2:4|(1:6)(1:7))(0)|8|(1:53)|(2:10|(1:(1:(7:14|15|16|31|59|(5:32|33|(10:57|(2:42|43)|44|(1:61)|16|31|59|32|33|(0)(1:35))(0)|49|50)|46)(2:19|20))(5:21|22|59|(5:32|33|(0)(0)|49|50)|46))(4:24|55|25|26))(1:29)|51|30|31|59|(5:32|33|(0)(0)|49|50)|46) */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b1, code lost:
    
        r10 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b2, code lost:
    
        r4 = r8;
        r8 = r10;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0081 A[Catch: all -> 0x0036, TRY_ENTER, TryCatch #1 {all -> 0x0036, blocks: (B:15:0x002f, B:32:0x0077, B:35:0x0081, B:39:0x0094, B:42:0x009b, B:43:0x009f, B:44:0x00a0, B:22:0x0049), top: B:53:0x001e }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0092 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v4, types: [na.k] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r4v1, types: [oa.a] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v4, types: [na.x0] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r9v0, types: [na.k] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v2, types: [oa.c] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5, types: [na.z0] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8, types: [na.z0] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00ae -> B:16:0x0032). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void j(na.x0 r8, na.k r9, d7.d r10) throws java.lang.Throwable {
        /*
            boolean r0 = r10 instanceof na.w0
            if (r0 == 0) goto L13
            r0 = r10
            na.w0 r0 = (na.w0) r0
            int r1 = r0.f20672q
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f20672q = r1
            goto L18
        L13:
            na.w0 r0 = new na.w0
            r0.<init>(r8, r10)
        L18:
            java.lang.Object r10 = r0.f20670o
            int r1 = r0.f20672q
            r2 = 3
            r3 = 2
            if (r1 == 0) goto L5c
            r8 = 1
            if (r1 == r8) goto L4d
            if (r1 == r3) goto L41
            if (r1 != r2) goto L39
            ka.v1 r8 = r0.f20669n
            na.z0 r9 = r0.f20668m
            na.k r1 = r0.f20667l
            na.x0 r4 = r0.f20666i
            k2.c.G(r10)     // Catch: java.lang.Throwable -> L36
        L32:
            r10 = r1
            r1 = r8
            r8 = r4
            goto L74
        L36:
            r8 = move-exception
            goto Lb4
        L39:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L41:
            ka.v1 r8 = r0.f20669n
            na.z0 r9 = r0.f20668m
            na.k r1 = r0.f20667l
            na.x0 r4 = r0.f20666i
            k2.c.G(r10)     // Catch: java.lang.Throwable -> L36
            goto L77
        L4d:
            na.z0 r9 = r0.f20668m
            na.k r8 = r0.f20667l
            na.x0 r1 = r0.f20666i
            k2.c.G(r10)     // Catch: java.lang.Throwable -> L59
            r10 = r8
            r8 = r1
            goto L68
        L59:
            r8 = move-exception
            r4 = r1
            goto Lb4
        L5c:
            k2.c.G(r10)
            oa.c r10 = r8.a()
            na.z0 r10 = (na.z0) r10
            r7 = r10
            r10 = r9
            r9 = r7
        L68:
            d7.j r1 = r0.getContext()     // Catch: java.lang.Throwable -> Lb1
            ka.u1 r4 = ka.u1.f19642i     // Catch: java.lang.Throwable -> Lb1
            d7.j$a r1 = r1.get(r4)     // Catch: java.lang.Throwable -> Lb1
            ka.v1 r1 = (ka.v1) r1     // Catch: java.lang.Throwable -> Lb1
        L74:
            r4 = r8
            r8 = r1
            r1 = r10
        L77:
            java.lang.Object r10 = r4.r(r9)     // Catch: java.lang.Throwable -> L36
            c2.a r5 = na.y0.f20689a     // Catch: java.lang.Throwable -> L36
            e7.a r6 = e7.a.f15033i
            if (r10 != r5) goto L92
            r0.f20666i = r4     // Catch: java.lang.Throwable -> L36
            r0.f20667l = r1     // Catch: java.lang.Throwable -> L36
            r0.f20668m = r9     // Catch: java.lang.Throwable -> L36
            r0.f20669n = r8     // Catch: java.lang.Throwable -> L36
            r0.f20672q = r3     // Catch: java.lang.Throwable -> L36
            java.lang.Object r10 = r4.h(r9, r0)     // Catch: java.lang.Throwable -> L36
            if (r10 != r6) goto L77
            goto Lb0
        L92:
            if (r8 == 0) goto La0
            boolean r5 = r8.isActive()     // Catch: java.lang.Throwable -> L36
            if (r5 == 0) goto L9b
            goto La0
        L9b:
            java.util.concurrent.CancellationException r8 = r8.getCancellationException()     // Catch: java.lang.Throwable -> L36
            throw r8     // Catch: java.lang.Throwable -> L36
        La0:
            r0.f20666i = r4     // Catch: java.lang.Throwable -> L36
            r0.f20667l = r1     // Catch: java.lang.Throwable -> L36
            r0.f20668m = r9     // Catch: java.lang.Throwable -> L36
            r0.f20669n = r8     // Catch: java.lang.Throwable -> L36
            r0.f20672q = r2     // Catch: java.lang.Throwable -> L36
            java.lang.Object r10 = r1.emit(r10, r0)     // Catch: java.lang.Throwable -> L36
            if (r10 != r6) goto L32
        Lb0:
            return
        Lb1:
            r10 = move-exception
            r4 = r8
            r8 = r10
        Lb4:
            r4.f(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: na.x0.j(na.x0, na.k, d7.d):void");
    }

    @Override // na.p0
    public final boolean b(Object obj) {
        int i10;
        boolean z;
        d7.d[] dVarArrM = oa.b.f20806a;
        synchronized (this) {
            if (p(obj)) {
                dVarArrM = m(dVarArrM);
                z = true;
            } else {
                z = false;
            }
        }
        for (d7.d dVar : dVarArrM) {
            if (dVar != null) {
                dVar.resumeWith(x6.t0.f22605a);
            }
        }
        return z;
    }

    @Override // oa.a
    public final oa.c c() {
        z0 z0Var = new z0();
        z0Var.f20696a = -1L;
        return z0Var;
    }

    @Override // na.j
    public final Object collect(k kVar, d7.d dVar) throws Throwable {
        j(this, kVar, dVar);
        return e7.a.f15033i;
    }

    @Override // oa.a
    public final oa.c[] d() {
        return new z0[2];
    }

    @Override // na.p0, na.k
    public final Object emit(Object obj, d7.d dVar) throws Throwable {
        Throwable th;
        d7.d[] dVarArrM;
        v0 v0Var;
        if (b(obj)) {
            return x6.t0.f22605a;
        }
        ka.l lVar = new ka.l(1, t7.a.A(dVar));
        lVar.s();
        d7.d[] dVarArrM2 = oa.b.f20806a;
        synchronized (this) {
            try {
                if (p(obj)) {
                    try {
                        lVar.resumeWith(x6.t0.f22605a);
                        dVarArrM = m(dVarArrM2);
                        v0Var = null;
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                } else {
                    try {
                        v0 v0Var2 = new v0(this, n() + ((long) (this.f20682u + this.f20683v)), obj, lVar);
                        l(v0Var2);
                        this.f20683v++;
                        if (this.f20677p == 0) {
                            dVarArrM2 = m(dVarArrM2);
                        }
                        dVarArrM = dVarArrM2;
                        v0Var = v0Var2;
                    } catch (Throwable th3) {
                        th = th3;
                        th = th;
                        throw th;
                    }
                }
                if (v0Var != null) {
                    lVar.c(new ka.i(v0Var, 1));
                }
                for (d7.d dVar2 : dVarArrM) {
                    if (dVar2 != null) {
                        dVar2.resumeWith(x6.t0.f22605a);
                    }
                }
                Object objR = lVar.r();
                e7.a aVar = e7.a.f15033i;
                if (objR != aVar) {
                    objR = x6.t0.f22605a;
                }
                return objR == aVar ? objR : x6.t0.f22605a;
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    public final Object h(z0 z0Var, w0 w0Var) {
        ka.l lVar = new ka.l(1, t7.a.A(w0Var));
        lVar.s();
        synchronized (this) {
            try {
                if (q(z0Var) < 0) {
                    z0Var.f20697b = lVar;
                } else {
                    lVar.resumeWith(x6.t0.f22605a);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        Object objR = lVar.r();
        return objR == e7.a.f15033i ? objR : x6.t0.f22605a;
    }

    public final void i() {
        if (this.f20677p != 0 || this.f20683v > 1) {
            Object[] objArr = this.f20679r;
            while (this.f20683v > 0) {
                long jN = n();
                int i10 = this.f20682u;
                int i11 = this.f20683v;
                if (objArr[((int) ((jN + ((long) (i10 + i11))) - 1)) & (objArr.length - 1)] != y0.f20689a) {
                    return;
                }
                this.f20683v = i11 - 1;
                y0.d(objArr, n() + ((long) (this.f20682u + this.f20683v)), null);
            }
        }
    }

    public final void k() {
        oa.c[] cVarArr;
        y0.d(this.f20679r, n(), null);
        this.f20682u--;
        long jN = n() + 1;
        if (this.f20680s < jN) {
            this.f20680s = jN;
        }
        if (this.f20681t < jN) {
            if (this.f20803l != 0 && (cVarArr = this.f20802i) != null) {
                for (oa.c cVar : cVarArr) {
                    if (cVar != null) {
                        z0 z0Var = (z0) cVar;
                        long j10 = z0Var.f20696a;
                        if (j10 >= 0 && j10 < jN) {
                            z0Var.f20696a = jN;
                        }
                    }
                }
            }
            this.f20681t = jN;
        }
    }

    public final void l(Object obj) {
        int i10 = this.f20682u + this.f20683v;
        Object[] objArrO = this.f20679r;
        if (objArrO == null) {
            objArrO = o(null, 0, 2);
        } else if (i10 >= objArrO.length) {
            objArrO = o(objArrO, i10, objArrO.length * 2);
        }
        y0.d(objArrO, n() + ((long) i10), obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [d7.d[]] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r6v3 */
    public final d7.d[] m(d7.d[] dVarArr) {
        oa.c[] cVarArr;
        z0 z0Var;
        ka.l lVar;
        int length = dVarArr.length;
        if (this.f20803l != 0 && (cVarArr = this.f20802i) != null) {
            int length2 = cVarArr.length;
            int i10 = 0;
            dVarArr = dVarArr;
            while (i10 < length2) {
                oa.c cVar = cVarArr[i10];
                if (cVar != null && (lVar = (z0Var = (z0) cVar).f20697b) != null && q(z0Var) >= 0) {
                    int length3 = dVarArr.length;
                    dVarArr = dVarArr;
                    if (length >= length3) {
                        dVarArr = Arrays.copyOf((Object[]) dVarArr, Math.max(2, dVarArr.length * 2));
                    }
                    ((d7.d[]) dVarArr)[length] = lVar;
                    z0Var.f20697b = null;
                    length++;
                }
                i10++;
                dVarArr = dVarArr;
            }
        }
        return (d7.d[]) dVarArr;
    }

    public final long n() {
        return Math.min(this.f20681t, this.f20680s);
    }

    public final Object[] o(Object[] objArr, int i10, int i11) {
        if (i11 <= 0) {
            throw new IllegalStateException("Buffer size overflow");
        }
        Object[] objArr2 = new Object[i11];
        this.f20679r = objArr2;
        if (objArr != null) {
            long jN = n();
            for (int i12 = 0; i12 < i10; i12++) {
                long j10 = ((long) i12) + jN;
                y0.d(objArr2, j10, objArr[((int) j10) & (objArr.length - 1)]);
            }
        }
        return objArr2;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean p(java.lang.Object r13) {
        /*
            r12 = this;
            int r1 = r12.f20803l
            int r2 = r12.f20676o
            r9 = 1
            if (r1 != 0) goto L22
            if (r2 != 0) goto La
            goto L76
        La:
            r12.l(r13)
            int r1 = r12.f20682u
            int r1 = r1 + r9
            r12.f20682u = r1
            if (r1 <= r2) goto L17
            r12.k()
        L17:
            long r1 = r12.n()
            int r3 = r12.f20682u
            long r3 = (long) r3
            long r1 = r1 + r3
            r12.f20681t = r1
            return r9
        L22:
            int r1 = r12.f20682u
            int r3 = r12.f20677p
            if (r1 < r3) goto L3e
            long r4 = r12.f20681t
            long r6 = r12.f20680s
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 > 0) goto L3e
            int r1 = r12.f20678q
            int r1 = h.f0.c(r1)
            if (r1 == 0) goto L3c
            r4 = 2
            if (r1 == r4) goto L76
            goto L3e
        L3c:
            r1 = 0
            return r1
        L3e:
            r12.l(r13)
            int r1 = r12.f20682u
            int r1 = r1 + r9
            r12.f20682u = r1
            if (r1 <= r3) goto L4b
            r12.k()
        L4b:
            long r3 = r12.n()
            int r1 = r12.f20682u
            long r5 = (long) r1
            long r3 = r3 + r5
            long r5 = r12.f20680s
            long r3 = r3 - r5
            int r1 = (int) r3
            if (r1 <= r2) goto L76
            r1 = 1
            long r1 = r1 + r5
            long r3 = r12.f20681t
            long r5 = r12.n()
            int r7 = r12.f20682u
            long r7 = (long) r7
            long r5 = r5 + r7
            long r7 = r12.n()
            int r10 = r12.f20682u
            long r10 = (long) r10
            long r7 = r7 + r10
            int r10 = r12.f20683v
            long r10 = (long) r10
            long r7 = r7 + r10
            r0 = r12
            r0.s(r1, r3, r5, r7)
        L76:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: na.x0.p(java.lang.Object):boolean");
    }

    public final long q(z0 z0Var) {
        long j10 = z0Var.f20696a;
        if (j10 < n() + ((long) this.f20682u)) {
            return j10;
        }
        if (this.f20677p <= 0 && j10 <= n() && this.f20683v != 0) {
            return j10;
        }
        return -1L;
    }

    public final Object r(z0 z0Var) {
        Object obj;
        d7.d[] dVarArrT = oa.b.f20806a;
        synchronized (this) {
            try {
                long jQ = q(z0Var);
                if (jQ < 0) {
                    obj = y0.f20689a;
                } else {
                    long j10 = z0Var.f20696a;
                    Object obj2 = this.f20679r[((int) jQ) & (r0.length - 1)];
                    if (obj2 instanceof v0) {
                        obj2 = ((v0) obj2).f20661m;
                    }
                    z0Var.f20696a = jQ + 1;
                    Object obj3 = obj2;
                    dVarArrT = t(j10);
                    obj = obj3;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        for (d7.d dVar : dVarArrT) {
            if (dVar != null) {
                dVar.resumeWith(x6.t0.f22605a);
            }
        }
        return obj;
    }

    public final void s(long j10, long j11, long j12, long j13) {
        long jMin = Math.min(j11, j10);
        for (long jN = n(); jN < jMin; jN++) {
            y0.d(this.f20679r, jN, null);
        }
        this.f20680s = j10;
        this.f20681t = j11;
        this.f20682u = (int) (j12 - jMin);
        this.f20683v = (int) (j13 - j12);
    }

    public final d7.d[] t(long j10) {
        long j11;
        long j12;
        long j13;
        d7.d[] dVarArr;
        d7.d[] dVarArr2;
        oa.c[] cVarArr;
        c2.a aVar = y0.f20689a;
        d7.d[] dVarArr3 = oa.b.f20806a;
        if (j10 <= this.f20681t) {
            long jN = n();
            long j14 = ((long) this.f20682u) + jN;
            int i10 = this.f20677p;
            if (i10 == 0 && this.f20683v > 0) {
                j14++;
            }
            int i11 = 0;
            if (this.f20803l != 0 && (cVarArr = this.f20802i) != null) {
                for (oa.c cVar : cVarArr) {
                    if (cVar != null) {
                        long j15 = ((z0) cVar).f20696a;
                        if (j15 >= 0 && j15 < j14) {
                            j14 = j15;
                        }
                    }
                }
            }
            if (j14 > this.f20681t) {
                long jN2 = n() + ((long) this.f20682u);
                int iMin = this.f20803l > 0 ? Math.min(this.f20683v, i10 - ((int) (jN2 - j14))) : this.f20683v;
                long j16 = ((long) this.f20683v) + jN2;
                if (iMin > 0) {
                    j13 = 1;
                    Object[] objArr = this.f20679r;
                    j11 = jN;
                    d7.d[] dVarArr4 = new d7.d[iMin];
                    long j17 = jN2;
                    while (true) {
                        if (jN2 >= j16) {
                            dVarArr2 = dVarArr4;
                            j12 = j14;
                            break;
                        }
                        dVarArr2 = dVarArr4;
                        Object obj = objArr[(objArr.length - 1) & ((int) jN2)];
                        if (obj != aVar) {
                            v0 v0Var = (v0) obj;
                            int i12 = i11 + 1;
                            j12 = j14;
                            dVarArr2[i11] = v0Var.f20662n;
                            y0.d(objArr, jN2, aVar);
                            y0.d(objArr, j17, v0Var.f20661m);
                            j17++;
                            if (i12 >= iMin) {
                                break;
                            }
                            i11 = i12;
                        } else {
                            j12 = j14;
                        }
                        jN2++;
                        dVarArr4 = dVarArr2;
                        j14 = j12;
                    }
                    jN2 = j17;
                    dVarArr = dVarArr2;
                } else {
                    j11 = jN;
                    j12 = j14;
                    j13 = 1;
                    dVarArr = dVarArr3;
                }
                int i13 = (int) (jN2 - j11);
                long j18 = this.f20803l == 0 ? jN2 : j12;
                long jMax = Math.max(this.f20680s, jN2 - ((long) Math.min(this.f20676o, i13)));
                if (i10 == 0 && jMax < j16) {
                    if (kotlin.jvm.internal.p.a(this.f20679r[((int) jMax) & (r2.length - 1)], aVar)) {
                        jN2 += j13;
                        jMax += j13;
                    }
                }
                s(jMax, j18, jN2, j16);
                i();
                return dVarArr.length == 0 ? dVarArr : m(dVarArr);
            }
        }
        return dVarArr3;
    }
}
