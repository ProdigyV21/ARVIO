package com.arflix.tv;

import androidx.compose.runtime.MutableState;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.MainActivity$onCreate$5$1$1", f = "MainActivity.kt", l = {280, 283, 285, 286, 288}, m = "invokeSuspend", v = 2)
public final class MainActivity$onCreate$5$1$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $skipProfileSelection$delegate;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ MainActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$onCreate$5$1$1(MainActivity mainActivity, MutableState<Boolean> mutableState, d7.d<? super MainActivity$onCreate$5$1$1> dVar) {
        super(2, dVar);
        this.this$0 = mainActivity;
        this.$skipProfileSelection$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new MainActivity$onCreate$5$1$1(this.this$0, this.$skipProfileSelection$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x012c, code lost:
    
        if (r10.setActiveProfile(r1, r17) == r9) goto L55;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0118 A[PHI: r1 r6 r10
      0x0118: PHI (r1v25 com.arflix.tv.data.model.Profile) = (r1v22 com.arflix.tv.data.model.Profile), (r1v30 com.arflix.tv.data.model.Profile) binds: [B:46:0x0103, B:51:0x0115] A[DONT_GENERATE, DONT_INLINE]
      0x0118: PHI (r6v5 ??) = (r6v13 ??), (r6v14 ??) binds: [B:46:0x0103, B:51:0x0115] A[DONT_GENERATE, DONT_INLINE]
      0x0118: PHI (r10v7 com.arflix.tv.data.repository.ProfileRepository) = (r10v6 com.arflix.tv.data.repository.ProfileRepository), (r10v8 com.arflix.tv.data.repository.ProfileRepository) binds: [B:46:0x0103, B:51:0x0115] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0134  */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12, types: [int] */
    /* JADX WARN: Type inference failed for: r1v14, types: [int] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v40, types: [int] */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r1v43 */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARN: Type inference failed for: r1v45 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5, types: [int] */
    /* JADX WARN: Type inference failed for: r6v6 */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            Method dump skipped, instruction units count: 319
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.MainActivity$onCreate$5$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((MainActivity$onCreate$5$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
