package com.arflix.tv.ui.components;

import androidx.compose.runtime.MutableState;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.ScreensaverKt$ScreensaverHost$1$1", f = "Screensaver.kt", l = {161}, m = "invokeSuspend", v = 2)
public final class ScreensaverKt$ScreensaverHost$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ long $idleTimeoutMs;
    final /* synthetic */ MutableState<Boolean> $isScreensaverActive$delegate;
    final /* synthetic */ MutableState<Long> $lastActivityTime$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreensaverKt$ScreensaverHost$1$1(long j10, MutableState<Long> mutableState, MutableState<Boolean> mutableState2, d7.d<? super ScreensaverKt$ScreensaverHost$1$1> dVar) {
        super(2, dVar);
        this.$idleTimeoutMs = j10;
        this.$lastActivityTime$delegate = mutableState;
        this.$isScreensaverActive$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new ScreensaverKt$ScreensaverHost$1$1(this.$idleTimeoutMs, this.$lastActivityTime$delegate, this.$isScreensaverActive$delegate, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0022 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
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
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            int r0 = r6.label
            r1 = 1
            if (r0 == 0) goto L13
            if (r0 != r1) goto Lb
            k2.c.G(r7)
            goto L23
        Lb:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L13:
            k2.c.G(r7)
        L16:
            r6.label = r1
            r2 = 1000(0x3e8, double:4.94E-321)
            java.lang.Object r7 = ka.s0.a(r2, r6)
            e7.a r0 = e7.a.f15033i
            if (r7 != r0) goto L23
            return r0
        L23:
            long r2 = java.lang.System.currentTimeMillis()
            androidx.compose.runtime.MutableState<java.lang.Long> r7 = r6.$lastActivityTime$delegate
            long r4 = com.arflix.tv.ui.components.ScreensaverKt.access$ScreensaverHost$lambda$4(r7)
            long r2 = r2 - r4
            long r4 = r6.$idleTimeoutMs
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 < 0) goto L16
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r6.$isScreensaverActive$delegate
            boolean r7 = com.arflix.tv.ui.components.ScreensaverKt.access$ScreensaverHost$lambda$1(r7)
            if (r7 != 0) goto L16
            androidx.compose.runtime.MutableState<java.lang.Boolean> r7 = r6.$isScreensaverActive$delegate
            com.arflix.tv.ui.components.ScreensaverKt.access$ScreensaverHost$lambda$2(r7, r1)
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.ScreensaverKt$ScreensaverHost$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((ScreensaverKt$ScreensaverHost$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
