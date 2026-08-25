package com.arflix.tv.ui.components;

import androidx.compose.material3.MenuKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.NextEpisodeOverlayKt$NextEpisodeOverlay$2$1", f = "NextEpisodeOverlay.kt", l = {MenuKt.InTransitionDuration}, m = "invokeSuspend", v = 2)
public final class NextEpisodeOverlayKt$NextEpisodeOverlay$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $actionTaken$delegate;
    final /* synthetic */ MutableIntState $countdown$delegate;
    final /* synthetic */ int $countdownSeconds;
    final /* synthetic */ boolean $isVisible;
    final /* synthetic */ r7.a<x6.t0> $onPlayNext;
    final /* synthetic */ MutableFloatState $progress$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NextEpisodeOverlayKt$NextEpisodeOverlay$2$1(boolean z, int i10, MutableIntState mutableIntState, MutableState<Boolean> mutableState, MutableFloatState mutableFloatState, r7.a<x6.t0> aVar, d7.d<? super NextEpisodeOverlayKt$NextEpisodeOverlay$2$1> dVar) {
        super(2, dVar);
        this.$isVisible = z;
        this.$countdownSeconds = i10;
        this.$countdown$delegate = mutableIntState;
        this.$actionTaken$delegate = mutableState;
        this.$progress$delegate = mutableFloatState;
        this.$onPlayNext = aVar;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new NextEpisodeOverlayKt$NextEpisodeOverlay$2$1(this.$isVisible, this.$countdownSeconds, this.$countdown$delegate, this.$actionTaken$delegate, this.$progress$delegate, this.$onPlayNext, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0064  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x003b -> B:18:0x003e). Please report as a decompilation issue!!! */
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
            goto L3e
        Lb:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L13:
            k2.c.G(r5)
            boolean r5 = r4.$isVisible
            if (r5 == 0) goto L73
            androidx.compose.runtime.MutableIntState r5 = r4.$countdown$delegate
            int r0 = r4.$countdownSeconds
            com.arflix.tv.ui.components.NextEpisodeOverlayKt.access$NextEpisodeOverlay$lambda$5(r5, r0)
        L21:
            androidx.compose.runtime.MutableIntState r5 = r4.$countdown$delegate
            int r5 = com.arflix.tv.ui.components.NextEpisodeOverlayKt.access$NextEpisodeOverlay$lambda$4(r5)
            if (r5 <= 0) goto L5c
            androidx.compose.runtime.MutableState<java.lang.Boolean> r5 = r4.$actionTaken$delegate
            boolean r5 = com.arflix.tv.ui.components.NextEpisodeOverlayKt.access$NextEpisodeOverlay$lambda$10(r5)
            if (r5 != 0) goto L5c
            r4.label = r1
            r2 = 1000(0x3e8, double:4.94E-321)
            java.lang.Object r5 = ka.s0.a(r2, r4)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3e
            return r0
        L3e:
            androidx.compose.runtime.MutableIntState r5 = r4.$countdown$delegate
            int r5 = com.arflix.tv.ui.components.NextEpisodeOverlayKt.access$NextEpisodeOverlay$lambda$4(r5)
            androidx.compose.runtime.MutableIntState r0 = r4.$countdown$delegate
            int r5 = r5 + (-1)
            com.arflix.tv.ui.components.NextEpisodeOverlayKt.access$NextEpisodeOverlay$lambda$5(r0, r5)
            androidx.compose.runtime.MutableFloatState r5 = r4.$progress$delegate
            androidx.compose.runtime.MutableIntState r0 = r4.$countdown$delegate
            int r0 = com.arflix.tv.ui.components.NextEpisodeOverlayKt.access$NextEpisodeOverlay$lambda$4(r0)
            float r0 = (float) r0
            int r2 = r4.$countdownSeconds
            float r2 = (float) r2
            float r0 = r0 / r2
            com.arflix.tv.ui.components.NextEpisodeOverlayKt.access$NextEpisodeOverlay$lambda$8(r5, r0)
            goto L21
        L5c:
            androidx.compose.runtime.MutableIntState r5 = r4.$countdown$delegate
            int r5 = com.arflix.tv.ui.components.NextEpisodeOverlayKt.access$NextEpisodeOverlay$lambda$4(r5)
            if (r5 != 0) goto L73
            androidx.compose.runtime.MutableState<java.lang.Boolean> r5 = r4.$actionTaken$delegate
            boolean r5 = com.arflix.tv.ui.components.NextEpisodeOverlayKt.access$NextEpisodeOverlay$lambda$10(r5)
            if (r5 != 0) goto L73
            r7.a<x6.t0> r5 = r4.$onPlayNext
            androidx.compose.runtime.MutableState<java.lang.Boolean> r0 = r4.$actionTaken$delegate
            com.arflix.tv.ui.components.NextEpisodeOverlayKt.access$NextEpisodeOverlay$playNextOnce(r5, r0)
        L73:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.NextEpisodeOverlayKt$NextEpisodeOverlay$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((NextEpisodeOverlayKt$NextEpisodeOverlay$2$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
