package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$GuidePanel$2$1", f = "TvScreen.kt", l = {1857}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$GuidePanel$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableState<Long> $now$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$GuidePanel$2$1(MutableState<Long> mutableState, d7.d<? super TvScreenKt$GuidePanel$2$1> dVar) {
        super(2, dVar);
        this.$now$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$GuidePanel$2$1(this.$now$delegate, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0022 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0020 -> B:12:0x0023). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            int r0 = r4.label
            r1 = 1
            if (r0 == 0) goto L13
            if (r0 != r1) goto Lb
            k2.c.G(r5)
            goto L23
        Lb:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L13:
            k2.c.G(r5)
        L16:
            r4.label = r1
            r2 = 30000(0x7530, double:1.4822E-319)
            java.lang.Object r5 = ka.s0.a(r2, r4)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L23
            return r0
        L23:
            androidx.compose.runtime.MutableState<java.lang.Long> r5 = r4.$now$delegate
            long r2 = java.lang.System.currentTimeMillis()
            com.arflix.tv.ui.screens.tv.TvScreenKt.access$GuidePanel$lambda$3(r5, r2)
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt$GuidePanel$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$GuidePanel$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
