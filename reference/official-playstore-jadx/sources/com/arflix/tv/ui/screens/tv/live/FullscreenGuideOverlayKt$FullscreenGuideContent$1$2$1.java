package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.ui.focus.FocusRequester;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlayKt$FullscreenGuideContent$1$2$1", f = "FullscreenGuideOverlay.kt", l = {349, 350}, m = "invokeSuspend", v = 2)
public final class FullscreenGuideOverlayKt$FullscreenGuideContent$1$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ FocusRequester $anchorFocusRequester;
    final /* synthetic */ int $anchorIndex;
    final /* synthetic */ List<GuideProgramItem> $items;
    final /* synthetic */ LazyListState $listState;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullscreenGuideOverlayKt$FullscreenGuideContent$1$2$1(List<GuideProgramItem> list, LazyListState lazyListState, int i10, FocusRequester focusRequester, d7.d<? super FullscreenGuideOverlayKt$FullscreenGuideContent$1$2$1> dVar) {
        super(2, dVar);
        this.$items = list;
        this.$listState = lazyListState;
        this.$anchorIndex = i10;
        this.$anchorFocusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        FullscreenGuideOverlayKt$FullscreenGuideContent$1$2$1 fullscreenGuideOverlayKt$FullscreenGuideContent$1$2$1 = new FullscreenGuideOverlayKt$FullscreenGuideContent$1$2$1(this.$items, this.$listState, this.$anchorIndex, this.$anchorFocusRequester, dVar);
        fullscreenGuideOverlayKt$FullscreenGuideContent$1$2$1.L$0 = obj;
        return fullscreenGuideOverlayKt$FullscreenGuideContent$1$2$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004f, code lost:
    
        if (ka.s0.a(90, r11) == r4) goto L20;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = r11.L$0
            ka.k0 r0 = (ka.k0) r0
            int r1 = r11.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L22
            if (r1 == r3) goto L1d
            if (r1 != r2) goto L15
            k2.c.G(r12)
            r8 = r11
            goto L52
        L15:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L1d:
            k2.c.G(r12)
            r8 = r11
            goto L45
        L22:
            k2.c.G(r12)
            java.util.List<com.arflix.tv.ui.screens.tv.live.GuideProgramItem> r12 = r11.$items
            boolean r12 = r12.isEmpty()
            if (r12 != 0) goto L58
            androidx.compose.foundation.lazy.LazyListState r5 = r11.$listState
            int r12 = r11.$anchorIndex
            int r12 = r12 - r3
            if (r12 >= 0) goto L35
            r12 = 0
        L35:
            r6 = r12
            r11.L$0 = r0
            r11.label = r3
            r7 = 0
            r9 = 2
            r10 = 0
            r8 = r11
            java.lang.Object r12 = androidx.compose.foundation.lazy.LazyListState.scrollToItem$default(r5, r6, r7, r8, r9, r10)
            if (r12 != r4) goto L45
            goto L51
        L45:
            r8.L$0 = r0
            r8.label = r2
            r0 = 90
            java.lang.Object r12 = ka.s0.a(r0, r11)
            if (r12 != r4) goto L52
        L51:
            return r4
        L52:
            androidx.compose.ui.focus.FocusRequester r12 = r8.$anchorFocusRequester
            r12.requestFocus()     // Catch: java.lang.Throwable -> L59
            goto L59
        L58:
            r8 = r11
        L59:
            x6.t0 r12 = x6.t0.f22605a
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlayKt$FullscreenGuideContent$1$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((FullscreenGuideOverlayKt$FullscreenGuideContent$1$2$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
