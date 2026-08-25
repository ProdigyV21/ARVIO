package com.arflix.tv.ui.components;

import androidx.compose.runtime.MutableIntState;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.StreamSelectorKt$StreamSelector$4$1", f = "StreamSelector.kt", l = {251}, m = "invokeSuspend", v = 2)
public final class StreamSelectorKt$StreamSelector$4$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableIntState $elapsedSeconds$delegate;
    final /* synthetic */ long $streamSearchStartTime;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamSelectorKt$StreamSelector$4$1(long j10, MutableIntState mutableIntState, d7.d<? super StreamSelectorKt$StreamSelector$4$1> dVar) {
        super(2, dVar);
        this.$streamSearchStartTime = j10;
        this.$elapsedSeconds$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new StreamSelectorKt$StreamSelector$4$1(this.$streamSearchStartTime, this.$elapsedSeconds$delegate, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0030 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x002e -> B:14:0x0031). Please report as a decompilation issue!!! */
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
            goto L31
        Lb:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L13:
            k2.c.G(r7)
            long r2 = r6.$streamSearchStartTime
            r4 = 0
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 <= 0) goto L43
            androidx.compose.runtime.MutableIntState r7 = r6.$elapsedSeconds$delegate
            r0 = 0
            com.arflix.tv.ui.components.StreamSelectorKt.access$StreamSelector$lambda$24(r7, r0)
        L24:
            r6.label = r1
            r2 = 1000(0x3e8, double:4.94E-321)
            java.lang.Object r7 = ka.s0.a(r2, r6)
            e7.a r0 = e7.a.f15033i
            if (r7 != r0) goto L31
            return r0
        L31:
            androidx.compose.runtime.MutableIntState r7 = r6.$elapsedSeconds$delegate
            long r2 = java.lang.System.currentTimeMillis()
            long r4 = r6.$streamSearchStartTime
            long r2 = r2 - r4
            r0 = 1000(0x3e8, float:1.401E-42)
            long r4 = (long) r0
            long r2 = r2 / r4
            int r0 = (int) r2
            com.arflix.tv.ui.components.StreamSelectorKt.access$StreamSelector$lambda$24(r7, r0)
            goto L24
        L43:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt$StreamSelector$4$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((StreamSelectorKt$StreamSelector$4$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
