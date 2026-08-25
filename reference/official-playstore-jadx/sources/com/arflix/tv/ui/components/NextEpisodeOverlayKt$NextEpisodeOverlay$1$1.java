package com.arflix.tv.ui.components;

import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.NextEpisodeOverlayKt$NextEpisodeOverlay$1$1", f = "NextEpisodeOverlay.kt", l = {}, m = "invokeSuspend", v = 2)
public final class NextEpisodeOverlayKt$NextEpisodeOverlay$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ boolean $isVisible;
    final /* synthetic */ FocusRequester $overlayFocusRequester;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NextEpisodeOverlayKt$NextEpisodeOverlay$1$1(boolean z, FocusRequester focusRequester, d7.d<? super NextEpisodeOverlayKt$NextEpisodeOverlay$1$1> dVar) {
        super(2, dVar);
        this.$isVisible = z;
        this.$overlayFocusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        NextEpisodeOverlayKt$NextEpisodeOverlay$1$1 nextEpisodeOverlayKt$NextEpisodeOverlay$1$1 = new NextEpisodeOverlayKt$NextEpisodeOverlay$1$1(this.$isVisible, this.$overlayFocusRequester, dVar);
        nextEpisodeOverlayKt$NextEpisodeOverlay$1$1.L$0 = obj;
        return nextEpisodeOverlayKt$NextEpisodeOverlay$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (this.$isVisible) {
            try {
                this.$overlayFocusRequester.requestFocus();
            } catch (Throwable unused) {
            }
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((NextEpisodeOverlayKt$NextEpisodeOverlay$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
