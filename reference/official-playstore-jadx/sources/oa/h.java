package oa;

import kotlin.jvm.internal.k0;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements na.k {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ k0 f20824i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ka.k0 f20825l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ i f20826m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ na.k f20827n;

    public h(k0 k0Var, ka.k0 k0Var2, i iVar, na.k kVar) {
        this.f20824i = k0Var;
        this.f20825l = k0Var2;
        this.f20826m = iVar;
        this.f20827n = kVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // na.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r7, d7.d r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof oa.g
            if (r0 == 0) goto L13
            r0 = r8
            oa.g r0 = (oa.g) r0
            int r1 = r0.f20823p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f20823p = r1
            goto L18
        L13:
            oa.g r0 = new oa.g
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.f20821n
            int r1 = r0.f20823p
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r7 = r0.f20819l
            oa.h r0 = r0.f20818i
            k2.c.G(r8)
            goto L56
        L29:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L31:
            k2.c.G(r8)
            kotlin.jvm.internal.k0 r8 = r6.f20824i
            java.lang.Object r8 = r8.f19746i
            ka.v1 r8 = (ka.v1) r8
            if (r8 == 0) goto L55
            kotlinx.coroutines.flow.internal.ChildCancelledException r1 = new kotlinx.coroutines.flow.internal.ChildCancelledException
            r1.<init>()
            r8.cancel(r1)
            r0.f20818i = r6
            r0.f20819l = r7
            r0.f20820m = r8
            r0.f20823p = r2
            java.lang.Object r8 = r8.join(r0)
            e7.a r0 = e7.a.f15033i
            if (r8 != r0) goto L55
            return r0
        L55:
            r0 = r6
        L56:
            kotlin.jvm.internal.k0 r8 = r0.f20824i
            ka.k0 r1 = r0.f20825l
            oa.f r3 = new oa.f
            oa.i r4 = r0.f20826m
            na.k r0 = r0.f20827n
            r5 = 0
            r3.<init>(r4, r0, r7, r5)
            r7 = 4
            ka.o2 r7 = ka.m0.p(r1, r5, r7, r3, r2)
            r8.f19746i = r7
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: oa.h.emit(java.lang.Object, d7.d):java.lang.Object");
    }
}
