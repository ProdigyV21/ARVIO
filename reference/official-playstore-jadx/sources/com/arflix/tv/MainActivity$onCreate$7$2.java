package com.arflix.tv;

import com.google.android.gms.cast.MediaError;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.MainActivity$onCreate$7$2", f = "MainActivity.kt", l = {MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, 401, 402}, m = "invokeSuspend", v = 2)
public final class MainActivity$onCreate$7$2 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ MainActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$onCreate$7$2(MainActivity mainActivity, d7.d<? super MainActivity$onCreate$7$2> dVar) {
        super(2, dVar);
        this.this$0 = mainActivity;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        MainActivity$onCreate$7$2 mainActivity$onCreate$7$2 = new MainActivity$onCreate$7$2(this.this$0, dVar);
        mainActivity$onCreate$7$2.L$0 = obj;
        return mainActivity$onCreate$7$2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x005b, code lost:
    
        if (r9 == r7) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x007f, code lost:
    
        if (r0.prefetchFreshStartupData(r8) != r7) goto L31;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.arflix.tv.data.repository.IptvRepository] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v5 */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.L$0
            ka.k0 r0 = (ka.k0) r0
            int r1 = r8.label
            r2 = 0
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            e7.a r7 = e7.a.f15033i
            if (r1 == 0) goto L3d
            if (r1 == r5) goto L31
            if (r1 == r4) goto L29
            if (r1 != r3) goto L21
            java.lang.Object r0 = r8.L$2
            ka.k0 r0 = (ka.k0) r0
            java.lang.Object r0 = r8.L$1
            com.arflix.tv.data.repository.IptvRepository r0 = (com.arflix.tv.data.repository.IptvRepository) r0
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L82
            goto L82
        L21:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L29:
            java.lang.Object r0 = r8.L$1
            com.arflix.tv.data.repository.IptvRepository r0 = (com.arflix.tv.data.repository.IptvRepository) r0
            k2.c.G(r9)
            goto L71
        L31:
            java.lang.Object r1 = r8.L$2
            ka.k0 r1 = (ka.k0) r1
            java.lang.Object r1 = r8.L$1
            com.arflix.tv.data.repository.IptvRepository r1 = (com.arflix.tv.data.repository.IptvRepository) r1
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L5e
            goto L5e
        L3d:
            k2.c.G(r9)
            com.arflix.tv.MainActivity r9 = r8.this$0
            dagger.Lazy r9 = r9.getIptvRepository()
            java.lang.Object r9 = r9.get()
            r1 = r9
            com.arflix.tv.data.repository.IptvRepository r1 = (com.arflix.tv.data.repository.IptvRepository) r1
            r8.L$0 = r0     // Catch: java.lang.Throwable -> L5e
            r8.L$1 = r1     // Catch: java.lang.Throwable -> L5e
            r8.L$2 = r6     // Catch: java.lang.Throwable -> L5e
            r8.I$0 = r2     // Catch: java.lang.Throwable -> L5e
            r8.label = r5     // Catch: java.lang.Throwable -> L5e
            java.lang.Object r9 = r1.warmupFromCacheOnly(r8)     // Catch: java.lang.Throwable -> L5e
            if (r9 != r7) goto L5e
            goto L81
        L5e:
            r8.L$0 = r0
            r8.L$1 = r1
            r8.L$2 = r6
            r8.label = r4
            r4 = 60000(0xea60, double:2.9644E-319)
            java.lang.Object r9 = ka.s0.a(r4, r8)
            if (r9 != r7) goto L70
            goto L81
        L70:
            r0 = r1
        L71:
            r8.L$0 = r6     // Catch: java.lang.Throwable -> L82
            r8.L$1 = r6     // Catch: java.lang.Throwable -> L82
            r8.L$2 = r6     // Catch: java.lang.Throwable -> L82
            r8.I$0 = r2     // Catch: java.lang.Throwable -> L82
            r8.label = r3     // Catch: java.lang.Throwable -> L82
            java.lang.Object r9 = r0.prefetchFreshStartupData(r8)     // Catch: java.lang.Throwable -> L82
            if (r9 != r7) goto L82
        L81:
            return r7
        L82:
            x6.t0 r9 = x6.t0.f22605a
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.MainActivity$onCreate$7$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((MainActivity$onCreate$7$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
