package t;

import x6.t0;

/* JADX INFO: loaded from: classes.dex */
public final class a0 extends f7.h implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public b0 f21778i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public d0 f21779l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long[] f21780m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f21781n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f21782o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f21783p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f21784q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f21785r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f21786s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public /* synthetic */ Object f21787t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ d0 f21788u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ b0 f21789v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a0(d0 d0Var, b0 b0Var, d7.d dVar) {
        super(2, dVar);
        this.f21788u = d0Var;
        this.f21789v = b0Var;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        a0 a0Var = new a0(this.f21788u, this.f21789v, dVar);
        a0Var.f21787t = obj;
        return a0Var;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((a0) create((ga.o) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x004e -> B:22:0x009c). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0050 -> B:14:0x0063). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x006c -> B:19:0x0091). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            r20 = this;
            r0 = r20
            int r1 = r0.f21786s
            r2 = 0
            r3 = 8
            r4 = 1
            if (r1 == 0) goto L2d
            if (r1 != r4) goto L25
            int r1 = r0.f21784q
            int r5 = r0.f21783p
            long r6 = r0.f21785r
            int r8 = r0.f21782o
            int r9 = r0.f21781n
            long[] r10 = r0.f21780m
            t.d0 r11 = r0.f21779l
            t.b0 r12 = r0.f21778i
            java.lang.Object r13 = r0.f21787t
            ga.o r13 = (ga.o) r13
            k2.c.G(r21)
            goto L91
        L25:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L2d:
            k2.c.G(r21)
            java.lang.Object r1 = r0.f21787t
            ga.o r1 = (ga.o) r1
            t.d0 r5 = r0.f21788u
            long[] r6 = r5.f21835a
            int r7 = r6.length
            int r7 = r7 + (-2)
            if (r7 < 0) goto La1
            t.b0 r8 = r0.f21789v
            r9 = r2
        L40:
            r10 = r6[r9]
            long r12 = ~r10
            r14 = 7
            long r12 = r12 << r14
            long r12 = r12 & r10
            r14 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r12 = r12 & r14
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 == 0) goto L9c
            int r12 = r9 - r7
            int r12 = ~r12
            int r12 = r12 >>> 31
            int r12 = 8 - r12
            r13 = r1
            r1 = r2
            r18 = r10
            r11 = r5
            r10 = r6
            r5 = r12
            r12 = r8
            r8 = r9
            r9 = r7
            r6 = r18
        L63:
            if (r1 >= r5) goto L94
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r6
            r16 = 128(0x80, double:6.3E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L91
            int r2 = r8 << 3
            int r2 = r2 + r1
            r12.f21791i = r2
            java.lang.Object[] r3 = r11.f21836b
            r2 = r3[r2]
            r0.f21787t = r13
            r0.f21778i = r12
            r0.f21779l = r11
            r0.f21780m = r10
            r0.f21781n = r9
            r0.f21782o = r8
            r0.f21785r = r6
            r0.f21783p = r5
            r0.f21784q = r1
            r0.f21786s = r4
            r13.b(r0, r2)
            e7.a r1 = e7.a.f15033i
            return r1
        L91:
            long r6 = r6 >> r3
            int r1 = r1 + r4
            goto L63
        L94:
            if (r5 != r3) goto La1
            r7 = r9
            r6 = r10
            r5 = r11
            r1 = r13
            r9 = r8
            r8 = r12
        L9c:
            if (r9 == r7) goto La1
            int r9 = r9 + 1
            goto L40
        La1:
            x6.t0 r1 = x6.t0.f22605a
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: t.a0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
