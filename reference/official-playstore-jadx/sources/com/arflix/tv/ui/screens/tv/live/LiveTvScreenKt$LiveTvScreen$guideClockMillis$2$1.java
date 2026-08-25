package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.ProduceStateScope;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/runtime/ProduceStateScope;", "", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/runtime/ProduceStateScope;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$guideClockMillis$2$1", f = "LiveTvScreen.kt", l = {430}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$guideClockMillis$2$1 extends f7.j implements r7.p<ProduceStateScope<Long>, d7.d<? super x6.t0>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    public LiveTvScreenKt$LiveTvScreen$guideClockMillis$2$1(d7.d<? super LiveTvScreenKt$LiveTvScreen$guideClockMillis$2$1> dVar) {
        super(2, dVar);
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        LiveTvScreenKt$LiveTvScreen$guideClockMillis$2$1 liveTvScreenKt$LiveTvScreen$guideClockMillis$2$1 = new LiveTvScreenKt$LiveTvScreen$guideClockMillis$2$1(dVar);
        liveTvScreenKt$LiveTvScreen$guideClockMillis$2$1.L$0 = obj;
        return liveTvScreenKt$LiveTvScreen$guideClockMillis$2$1;
    }

    @Override // r7.p
    public final Object invoke(ProduceStateScope<Long> produceStateScope, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$guideClockMillis$2$1) create(produceStateScope, dVar)).invokeSuspend(x6.t0.f22605a);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0028 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0026 -> B:12:0x0029). Please report as a decompilation issue!!! */
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
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.L$0
            androidx.compose.runtime.ProduceStateScope r0 = (androidx.compose.runtime.ProduceStateScope) r0
            int r1 = r5.label
            r2 = 1
            if (r1 == 0) goto L17
            if (r1 != r2) goto Lf
            k2.c.G(r6)
            goto L29
        Lf:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L17:
            k2.c.G(r6)
        L1a:
            r5.L$0 = r0
            r5.label = r2
            r3 = 30000(0x7530, double:1.4822E-319)
            java.lang.Object r6 = ka.s0.a(r3, r5)
            e7.a r1 = e7.a.f15033i
            if (r6 != r1) goto L29
            return r1
        L29:
            long r3 = java.lang.System.currentTimeMillis()
            java.lang.Long r6 = new java.lang.Long
            r6.<init>(r3)
            r0.setValue(r6)
            goto L1a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$guideClockMillis$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
