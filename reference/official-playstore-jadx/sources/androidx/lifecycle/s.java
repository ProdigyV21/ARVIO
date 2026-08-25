package androidx.lifecycle;

/* JADX INFO: loaded from: classes3.dex */
public final class s extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3285i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ Object f3286l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3287m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ s(Object obj, d7.d dVar, int i10) {
        super(2, dVar);
        this.f3285i = i10;
        this.f3287m = obj;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [kotlin.jvm.internal.r, r7.a] */
    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f3285i) {
            case 0:
                s sVar = new s((t) this.f3287m, dVar, 0);
                sVar.f3286l = obj;
                return sVar;
            case 1:
                s sVar2 = new s((kotlin.jvm.internal.r) this.f3287m, dVar);
                sVar2.f3286l = obj;
                return sVar2;
            case 2:
                s sVar3 = new s((String) this.f3287m, dVar, 2);
                sVar3.f3286l = obj;
                return sVar3;
            default:
                s sVar4 = new s((z0.b0) this.f3287m, dVar, 3);
                sVar4.f3286l = obj;
                return sVar4;
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        switch (this.f3285i) {
            case 0:
                s sVar = (s) create((ka.k0) obj, (d7.d) obj2);
                x6.t0 t0Var = x6.t0.f22605a;
                sVar.invokeSuspend(t0Var);
                return t0Var;
            case 1:
                return ((s) create((ka.k0) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
            case 2:
                s sVar2 = (s) create((c1.b) obj, (d7.d) obj2);
                x6.t0 t0Var2 = x6.t0.f22605a;
                sVar2.invokeSuspend(t0Var2);
                return t0Var2;
            default:
                return ((s) create((z0.b0) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0076, code lost:
    
        return r0.invoke();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0079, code lost:
    
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x007a, code lost:
    
        r1.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007d, code lost:
    
        throw r5;
     */
    /* JADX WARN: Type inference failed for: r0v5, types: [kotlin.jvm.internal.r, r7.a] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) throws java.lang.Throwable {
        /*
            r4 = this;
            int r0 = r4.f3285i
            switch(r0) {
                case 0: goto L8a;
                case 1: goto L35;
                case 2: goto L22;
                default: goto L5;
            }
        L5:
            k2.c.G(r5)
            java.lang.Object r5 = r4.f3286l
            z0.b0 r5 = (z0.b0) r5
            java.lang.Object r0 = r4.f3287m
            z0.b0 r0 = (z0.b0) r0
            boolean r1 = r0 instanceof z0.b
            r2 = 0
            if (r1 != 0) goto L1d
            boolean r1 = r0 instanceof z0.h
            if (r1 == 0) goto L1a
            goto L1d
        L1a:
            if (r5 != r0) goto L1d
            r2 = 1
        L1d:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
            return r5
        L22:
            k2.c.G(r5)
            java.lang.Object r5 = r4.f3286l
            c1.b r5 = (c1.b) r5
            java.lang.Object r0 = r4.f3287m
            java.lang.String r0 = (java.lang.String) r0
            c1.e r1 = w5.v.f22488a
            r5.d(r1, r0)
            x6.t0 r5 = x6.t0.f22605a
            return r5
        L35:
            k2.c.G(r5)
            java.lang.Object r5 = r4.f3286l
            ka.k0 r5 = (ka.k0) r5
            d7.j r5 = r5.getCoroutineContext()
            java.lang.Object r0 = r4.f3287m
            kotlin.jvm.internal.r r0 = (kotlin.jvm.internal.r) r0
            ka.s2 r1 = new ka.s2     // Catch: java.lang.InterruptedException -> L77
            ka.v1 r5 = ka.m0.k(r5)     // Catch: java.lang.InterruptedException -> L77
            r1.<init>(r5)     // Catch: java.lang.InterruptedException -> L77
            r2 = 1
            ka.z0 r5 = r5.invokeOnCompletion(r2, r2, r1)     // Catch: java.lang.InterruptedException -> L77
            r1.f19635m = r5     // Catch: java.lang.InterruptedException -> L77
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r5 = ka.s2.f19632n     // Catch: java.lang.InterruptedException -> L77
        L56:
            int r2 = r5.get(r1)     // Catch: java.lang.InterruptedException -> L77
            if (r2 == 0) goto L68
            r5 = 2
            if (r2 == r5) goto L6f
            r5 = 3
            if (r2 != r5) goto L63
            goto L6f
        L63:
            ka.s2.b(r2)     // Catch: java.lang.InterruptedException -> L77
            r5 = 0
            throw r5     // Catch: java.lang.InterruptedException -> L77
        L68:
            r3 = 0
            boolean r2 = r5.compareAndSet(r1, r2, r3)     // Catch: java.lang.InterruptedException -> L77
            if (r2 == 0) goto L56
        L6f:
            java.lang.Object r5 = r0.invoke()     // Catch: java.lang.Throwable -> L79
            r1.a()     // Catch: java.lang.InterruptedException -> L77
            return r5
        L77:
            r5 = move-exception
            goto L7e
        L79:
            r5 = move-exception
            r1.a()     // Catch: java.lang.InterruptedException -> L77
            throw r5     // Catch: java.lang.InterruptedException -> L77
        L7e:
            java.util.concurrent.CancellationException r0 = new java.util.concurrent.CancellationException
            java.lang.String r1 = "Blocking call was interrupted due to parent cancellation"
            r0.<init>(r1)
            java.lang.Throwable r5 = r0.initCause(r5)
            throw r5
        L8a:
            k2.c.G(r5)
            java.lang.Object r5 = r4.f3286l
            ka.k0 r5 = (ka.k0) r5
            java.lang.Object r0 = r4.f3287m
            androidx.lifecycle.t r0 = (androidx.lifecycle.t) r0
            androidx.lifecycle.r r1 = r0.f3296i
            androidx.lifecycle.q r2 = r1.getCurrentState()
            androidx.lifecycle.q r3 = androidx.lifecycle.q.f3267l
            int r2 = r2.compareTo(r3)
            if (r2 < 0) goto La7
            r1.addObserver(r0)
            goto Laf
        La7:
            d7.j r5 = r5.getCoroutineContext()
            r0 = 0
            ka.m0.g(r5, r0)
        Laf:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.s.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public s(r7.a aVar, d7.d dVar) {
        super(2, dVar);
        this.f3285i = 1;
        this.f3287m = (kotlin.jvm.internal.r) aVar;
    }
}
