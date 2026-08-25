package androidx.tv.material3;

import androidx.compose.runtime.State;

/* JADX INFO: loaded from: classes3.dex */
public final class b0 extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5816i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f5817l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ l0 f5818m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ State f5819n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b0(long j10, l0 l0Var, State state, d7.d dVar) {
        super(2, dVar);
        this.f5817l = j10;
        this.f5818m = l0Var;
        this.f5819n = state;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        return new b0(this.f5817l, this.f5818m, this.f5819n, dVar);
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((b0) create((ka.k0) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
        return e7.a.f15033i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x005b, code lost:
    
        if (na.y0.m(r9, r0, r8) == r5) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0044  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0042 -> B:24:0x005e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x005b -> B:24:0x005e). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            int r0 = r8.f5816i
            r1 = 3
            r2 = 1
            androidx.tv.material3.l0 r3 = r8.f5818m
            r4 = 2
            e7.a r5 = e7.a.f15033i
            if (r0 == 0) goto L25
            if (r0 == r2) goto L21
            if (r0 == r4) goto L1d
            if (r0 != r1) goto L15
            k2.c.G(r9)
            goto L5e
        L15:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L1d:
            k2.c.G(r9)
            goto L3c
        L21:
            k2.c.G(r9)
            goto L31
        L25:
            k2.c.G(r9)
        L28:
            r8.f5816i = r2
            java.lang.Object r9 = ka.m0.B(r8)
            if (r9 != r5) goto L31
            goto L5d
        L31:
            r8.f5816i = r4
            long r6 = r8.f5817l
            java.lang.Object r9 = ka.s0.a(r6, r8)
            if (r9 != r5) goto L3c
            goto L5d
        L3c:
            androidx.compose.runtime.MutableIntState r9 = r3.f6184a
            int r9 = r9.getIntValue()
            if (r9 <= 0) goto L5e
            androidx.tv.material3.z r9 = new androidx.tv.material3.z
            r0 = 0
            r9.<init>(r0, r3)
            na.j r9 = androidx.compose.runtime.SnapshotStateKt.snapshotFlow(r9)
            androidx.tv.material3.a0 r0 = new androidx.tv.material3.a0
            r6 = 0
            r7 = 0
            r0.<init>(r4, r7, r6)
            r8.f5816i = r1
            java.lang.Object r9 = na.y0.m(r9, r0, r8)
            if (r9 != r5) goto L5e
        L5d:
            return r5
        L5e:
            androidx.compose.runtime.State r9 = r8.f5819n
            java.lang.Object r9 = r9.getValue()
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            r3.a(r9)
            goto L28
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.b0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
