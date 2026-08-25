package ka;

/* JADX INFO: loaded from: classes5.dex */
public final class d2 extends f7.h implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public pa.j f19573i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public p f19574l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19575m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f19576n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ f2 f19577o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d2(d7.d dVar, f2 f2Var) {
        super(2, dVar);
        this.f19577o = f2Var;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        d2 d2Var = new d2(dVar, this.f19577o);
        d2Var.f19576n = obj;
        return d2Var;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((d2) create((ga.o) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x005d -> B:25:0x006f). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            int r0 = r5.f19575m
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L24
            if (r0 == r2) goto L20
            if (r0 != r1) goto L18
            ka.p r0 = r5.f19574l
            pa.j r2 = r5.f19573i
            java.lang.Object r4 = r5.f19576n
            ga.o r4 = (ga.o) r4
            k2.c.G(r6)
            goto L6f
        L18:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L20:
            k2.c.G(r6)
            goto L74
        L24:
            k2.c.G(r6)
            java.lang.Object r6 = r5.f19576n
            ga.o r6 = (ga.o) r6
            ka.f2 r0 = r5.f19577o
            java.lang.Object r0 = r0.y()
            boolean r4 = r0 instanceof ka.p
            if (r4 == 0) goto L3f
            ka.p r0 = (ka.p) r0
            ka.q r0 = r0.f19627l
            r5.f19575m = r2
            r6.b(r5, r0)
            return r3
        L3f:
            boolean r2 = r0 instanceof ka.p1
            if (r2 == 0) goto L74
            ka.p1 r0 = (ka.p1) r0
            ka.i2 r0 = r0.getList()
            if (r0 == 0) goto L74
            java.lang.Object r2 = r0.getNext()
            pa.m r2 = (pa.m) r2
            r4 = r2
            r2 = r0
            r0 = r4
            r4 = r6
        L55:
            boolean r6 = kotlin.jvm.internal.p.a(r0, r2)
            if (r6 != 0) goto L74
            boolean r6 = r0 instanceof ka.p
            if (r6 == 0) goto L6f
            ka.p r0 = (ka.p) r0
            ka.q r6 = r0.f19627l
            r5.f19576n = r4
            r5.f19573i = r2
            r5.f19574l = r0
            r5.f19575m = r1
            r4.b(r5, r6)
            return r3
        L6f:
            pa.m r0 = r0.getNextNode()
            goto L55
        L74:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: ka.d2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
