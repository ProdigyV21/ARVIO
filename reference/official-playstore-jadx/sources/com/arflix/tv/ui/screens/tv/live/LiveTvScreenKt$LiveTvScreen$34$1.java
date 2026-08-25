package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$34$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$34$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ boolean $channelsReady;
    final /* synthetic */ MutableState<LiveTvFocusZone> $focusZone$delegate;
    final /* synthetic */ boolean $isTouchDevice;
    final /* synthetic */ FocusRequester $sidebarFocus;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$34$1(boolean z, boolean z5, MutableState<LiveTvFocusZone> mutableState, FocusRequester focusRequester, d7.d<? super LiveTvScreenKt$LiveTvScreen$34$1> dVar) {
        super(2, dVar);
        this.$isTouchDevice = z;
        this.$channelsReady = z5;
        this.$focusZone$delegate = mutableState;
        this.$sidebarFocus = focusRequester;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        LiveTvScreenKt$LiveTvScreen$34$1 liveTvScreenKt$LiveTvScreen$34$1 = new LiveTvScreenKt$LiveTvScreen$34$1(this.$isTouchDevice, this.$channelsReady, this.$focusZone$delegate, this.$sidebarFocus, dVar);
        liveTvScreenKt$LiveTvScreen$34$1.L$0 = obj;
        return liveTvScreenKt$LiveTvScreen$34$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (LiveTvStartup.INSTANCE.shouldClaimSidebarFocus(this.$isTouchDevice, LiveTvScreenKt.LiveTvScreen$lambda$41(this.$focusZone$delegate) == LiveTvFocusZone.CATEGORY_LIST, this.$channelsReady)) {
            try {
                this.$sidebarFocus.requestFocus();
            } catch (Throwable unused) {
            }
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$34$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
