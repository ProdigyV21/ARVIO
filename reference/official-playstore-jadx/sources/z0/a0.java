package z0;

import a8.g0;
import androidx.emoji2.text.e0;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import ka.m0;
import na.j1;
import na.t0;
import na.y0;
import x6.i0;

/* JADX INFO: loaded from: classes3.dex */
public final class a0 implements g {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final LinkedHashSet f23060i = new LinkedHashSet();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Object f23061j = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g0 f23062a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a1.a f23063b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final t0 f23064c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f23065d = ".tmp";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final i0 f23066e = new i0(new j8.t0(this, 17));

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final j1 f23067f = y0.b(c0.f23072a);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public List f23068g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e0 f23069h;

    public a0(g0 g0Var, List list, a1.a aVar, pa.e eVar) {
        this.f23062a = g0Var;
        this.f23063b = aVar;
        d7.d dVar = null;
        this.f23064c = new t0(new o(this, dVar, 1));
        this.f23068g = kotlin.collections.x.c1(list);
        this.f23069h = new e0(eVar, new s8.d(this, 18), new o(this, dVar, 0));
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a0, code lost:
    
        if (r8 != r5) goto L29;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r1v10, types: [f7.j, r7.p] */
    /* JADX WARN: Type inference failed for: r1v3, types: [f7.j, r7.p] */
    /* JADX WARN: Type inference failed for: r8v0, types: [z0.a0] */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7, types: [z0.a0] */
    /* JADX WARN: Type inference failed for: r8v9, types: [ka.r] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object b(z0.a0 r8, z0.m r9, f7.c r10) {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.a0.b(z0.a0, z0.m, f7.c):java.lang.Object");
    }

    @Override // z0.g
    public final Object a(r7.p pVar, d7.d dVar) throws Throwable {
        ka.s sVarB = m0.b();
        this.f23069h.x(new m(pVar, sVarB, (b0) this.f23067f.getValue(), dVar.getContext()));
        return sVarB.j(dVar);
    }

    public final File c() {
        return (File) this.f23066e.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object d(f7.c r13) throws androidx.datastore.core.CorruptionException, java.lang.IllegalAccessException, java.io.FileNotFoundException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.a0.d(f7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object e(f7.c r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof z0.u
            if (r0 == 0) goto L13
            r0 = r5
            z0.u r0 = (z0.u) r0
            int r1 = r0.f23130n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f23130n = r1
            goto L18
        L13:
            z0.u r0 = new z0.u
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.f23128l
            int r1 = r0.f23130n
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            z0.a0 r0 = r0.f23127i
            k2.c.G(r5)     // Catch: java.lang.Throwable -> L27
            goto L41
        L27:
            r5 = move-exception
            goto L46
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L31:
            k2.c.G(r5)
            r0.f23127i = r4     // Catch: java.lang.Throwable -> L44
            r0.f23130n = r2     // Catch: java.lang.Throwable -> L44
            java.lang.Object r5 = r4.d(r0)     // Catch: java.lang.Throwable -> L44
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L41
            return r0
        L41:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        L44:
            r5 = move-exception
            r0 = r4
        L46:
            na.j1 r0 = r0.f23067f
            z0.j r1 = new z0.j
            r1.<init>(r5)
            r0.getClass()
            r2 = 0
            r0.h(r2, r1)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.a0.e(f7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object f(f7.c r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof z0.v
            if (r0 == 0) goto L13
            r0 = r5
            z0.v r0 = (z0.v) r0
            int r1 = r0.f23134n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f23134n = r1
            goto L18
        L13:
            z0.v r0 = new z0.v
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.f23132l
            int r1 = r0.f23134n
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            z0.a0 r0 = r0.f23131i
            k2.c.G(r5)     // Catch: java.lang.Throwable -> L27
            goto L51
        L27:
            r5 = move-exception
            goto L43
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L31:
            k2.c.G(r5)
            r0.f23131i = r4     // Catch: java.lang.Throwable -> L41
            r0.f23134n = r2     // Catch: java.lang.Throwable -> L41
            java.lang.Object r5 = r4.d(r0)     // Catch: java.lang.Throwable -> L41
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L51
            return r0
        L41:
            r5 = move-exception
            r0 = r4
        L43:
            na.j1 r0 = r0.f23067f
            z0.j r1 = new z0.j
            r1.<init>(r5)
            r0.getClass()
            r5 = 0
            r0.h(r5, r1)
        L51:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.a0.f(f7.c):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v13, types: [z0.a0] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v2, types: [z0.w] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [z0.a0] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object g(f7.c r5) throws java.io.FileNotFoundException {
        /*
            r4 = this;
            boolean r0 = r5 instanceof z0.w
            if (r0 == 0) goto L13
            r0 = r5
            z0.w r0 = (z0.w) r0
            int r1 = r0.f23139o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f23139o = r1
            goto L18
        L13:
            z0.w r0 = new z0.w
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.f23137m
            int r1 = r0.f23139o
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            java.io.FileInputStream r1 = r0.f23136l
            z0.a0 r0 = r0.f23135i
            k2.c.G(r5)     // Catch: java.lang.Throwable -> L29
            goto L51
        L29:
            r5 = move-exception
            goto L5a
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L33:
            k2.c.G(r5)
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.io.FileNotFoundException -> L60
            java.io.File r5 = r4.c()     // Catch: java.io.FileNotFoundException -> L60
            r1.<init>(r5)     // Catch: java.io.FileNotFoundException -> L60
            c1.i r5 = c1.i.f7377a     // Catch: java.lang.Throwable -> L58
            r0.f23135i = r4     // Catch: java.lang.Throwable -> L58
            r0.f23136l = r1     // Catch: java.lang.Throwable -> L58
            r0.f23139o = r2     // Catch: java.lang.Throwable -> L58
            c1.b r5 = r5.a(r1)     // Catch: java.lang.Throwable -> L58
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L50
            return r0
        L50:
            r0 = r4
        L51:
            r3 = 0
            xc.d.L(r1, r3)     // Catch: java.io.FileNotFoundException -> L56
            return r5
        L56:
            r5 = move-exception
            goto L62
        L58:
            r5 = move-exception
            r0 = r4
        L5a:
            throw r5     // Catch: java.lang.Throwable -> L5b
        L5b:
            r3 = move-exception
            xc.d.L(r1, r5)     // Catch: java.io.FileNotFoundException -> L56
            throw r3     // Catch: java.io.FileNotFoundException -> L56
        L60:
            r5 = move-exception
            r0 = r4
        L62:
            java.io.File r0 = r0.c()
            boolean r0 = r0.exists()
            if (r0 != 0) goto L72
            c1.b r5 = new c1.b
            r5.<init>(r2)
            return r5
        L72:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.a0.g(f7.c):java.lang.Object");
    }

    @Override // z0.g
    public final na.j getData() {
        return this.f23064c;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0073 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object h(f7.c r6) throws androidx.datastore.core.CorruptionException, java.lang.IllegalAccessException, java.io.FileNotFoundException, java.lang.reflect.InvocationTargetException {
        /*
            r5 = this;
            boolean r0 = r6 instanceof z0.x
            if (r0 == 0) goto L13
            r0 = r6
            z0.x r0 = (z0.x) r0
            int r1 = r0.f23144o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f23144o = r1
            goto L18
        L13:
            z0.x r0 = new z0.x
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.f23142m
            int r1 = r0.f23144o
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L66
            if (r1 == r3) goto L5c
            r3 = 3
            if (r1 == r2) goto L3d
            if (r1 != r3) goto L35
            java.lang.Object r1 = r0.f23141l
            java.lang.Object r0 = r0.f23140i
            androidx.datastore.core.CorruptionException r0 = (androidx.datastore.core.CorruptionException) r0
            k2.c.G(r6)     // Catch: java.io.IOException -> L33
            return r1
        L33:
            r6 = move-exception
            goto L58
        L35:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L3d:
            java.lang.Object r1 = r0.f23141l
            androidx.datastore.core.CorruptionException r1 = (androidx.datastore.core.CorruptionException) r1
            java.lang.Object r2 = r0.f23140i
            z0.a0 r2 = (z0.a0) r2
            k2.c.G(r6)
            r0.f23140i = r1     // Catch: java.io.IOException -> L56
            r0.f23141l = r6     // Catch: java.io.IOException -> L56
            r0.f23144o = r3     // Catch: java.io.IOException -> L56
            java.lang.Object r0 = r2.j(r6, r0)     // Catch: java.io.IOException -> L56
            if (r0 != r4) goto L55
            goto L73
        L55:
            return r6
        L56:
            r6 = move-exception
            r0 = r1
        L58:
            com.google.common.util.concurrent.r0.a(r0, r6)
            throw r0
        L5c:
            java.lang.Object r1 = r0.f23140i
            z0.a0 r1 = (z0.a0) r1
            k2.c.G(r6)     // Catch: androidx.datastore.core.CorruptionException -> L64
            return r6
        L64:
            r6 = move-exception
            goto L77
        L66:
            k2.c.G(r6)
            r0.f23140i = r5     // Catch: androidx.datastore.core.CorruptionException -> L75
            r0.f23144o = r3     // Catch: androidx.datastore.core.CorruptionException -> L75
            java.lang.Object r6 = r5.g(r0)     // Catch: androidx.datastore.core.CorruptionException -> L75
            if (r6 != r4) goto L74
        L73:
            return r4
        L74:
            return r6
        L75:
            r6 = move-exception
            r1 = r5
        L77:
            a1.a r3 = r1.f23063b
            r0.f23140i = r1
            r0.f23141l = r6
            r0.f23144o = r2
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.a0.h(f7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object i(r7.p r10, d7.j r11, f7.c r12) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r12 instanceof z0.y
            if (r0 == 0) goto L13
            r0 = r12
            z0.y r0 = (z0.y) r0
            int r1 = r0.f23150p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f23150p = r1
            goto L18
        L13:
            z0.y r0 = new z0.y
            r0.<init>(r9, r12)
        L18:
            java.lang.Object r12 = r0.f23148n
            int r1 = r0.f23150p
            java.lang.String r2 = "Data in DataStore was mutated but DataStore is only compatible with Immutable types."
            r3 = 0
            r4 = 2
            r5 = 1
            r6 = 0
            e7.a r7 = e7.a.f15033i
            if (r1 == 0) goto L47
            if (r1 == r5) goto L3b
            if (r1 != r4) goto L33
            java.lang.Object r10 = r0.f23146l
            z0.a0 r11 = r0.f23145i
            k2.c.G(r12)
            goto La0
        L33:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L3b:
            java.lang.Object r10 = r0.f23147m
            java.lang.Object r11 = r0.f23146l
            z0.b r11 = (z0.b) r11
            z0.a0 r1 = r0.f23145i
            k2.c.G(r12)
            goto L7a
        L47:
            k2.c.G(r12)
            na.j1 r12 = r9.f23067f
            java.lang.Object r12 = r12.getValue()
            z0.b r12 = (z0.b) r12
            java.lang.Object r1 = r12.f23070a
            if (r1 == 0) goto L5b
            int r1 = r1.hashCode()
            goto L5c
        L5b:
            r1 = r3
        L5c:
            int r8 = r12.f23071b
            if (r1 != r8) goto Lba
            java.lang.Object r1 = r12.f23070a
            androidx.lifecycle.p0 r8 = new androidx.lifecycle.p0
            r8.<init>(r10, r1, r6)
            r0.f23145i = r9
            r0.f23146l = r12
            r0.f23147m = r1
            r0.f23150p = r5
            java.lang.Object r10 = ka.m0.y(r11, r8, r0)
            if (r10 != r7) goto L76
            goto L9d
        L76:
            r11 = r12
            r12 = r10
            r10 = r1
            r1 = r9
        L7a:
            java.lang.Object r5 = r11.f23070a
            if (r5 == 0) goto L83
            int r5 = r5.hashCode()
            goto L84
        L83:
            r5 = r3
        L84:
            int r11 = r11.f23071b
            if (r5 != r11) goto Lb4
            boolean r11 = kotlin.jvm.internal.p.a(r10, r12)
            if (r11 == 0) goto L8f
            return r10
        L8f:
            r0.f23145i = r1
            r0.f23146l = r12
            r0.f23147m = r6
            r0.f23150p = r4
            java.lang.Object r10 = r1.j(r12, r0)
            if (r10 != r7) goto L9e
        L9d:
            return r7
        L9e:
            r10 = r12
            r11 = r1
        La0:
            na.j1 r11 = r11.f23067f
            z0.b r12 = new z0.b
            if (r10 == 0) goto Laa
            int r3 = r10.hashCode()
        Laa:
            r12.<init>(r10, r3)
            r11.getClass()
            r11.h(r6, r12)
            return r10
        Lb4:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>(r2)
            throw r10
        Lba:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>(r2)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.a0.i(r7.p, d7.j, f7.c):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.io.File, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r9v8, types: [java.lang.StringBuilder] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object j(java.lang.Object r8, f7.c r9) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.a0.j(java.lang.Object, f7.c):java.lang.Object");
    }
}
