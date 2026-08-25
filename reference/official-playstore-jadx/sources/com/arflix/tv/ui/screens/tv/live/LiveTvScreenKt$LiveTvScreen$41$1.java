package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.tv.live.LiveTvStartup;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$41$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$41$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableIntState $focusCategoryRailSignal$delegate;
    final /* synthetic */ MutableState<LiveTvFocusZone> $focusZone$delegate;
    final /* synthetic */ boolean $isTouchDevice;
    final /* synthetic */ MutableLongState $lastGuideUserNavigationAt$delegate;
    final /* synthetic */ MutableState<EnrichedChannels> $visibleEnrichedState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$41$1(boolean z, MutableState<EnrichedChannels> mutableState, MutableLongState mutableLongState, MutableState<LiveTvFocusZone> mutableState2, MutableIntState mutableIntState, d7.d<? super LiveTvScreenKt$LiveTvScreen$41$1> dVar) {
        super(2, dVar);
        this.$isTouchDevice = z;
        this.$visibleEnrichedState = mutableState;
        this.$lastGuideUserNavigationAt$delegate = mutableLongState;
        this.$focusZone$delegate = mutableState2;
        this.$focusCategoryRailSignal$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$41$1(this.$isTouchDevice, this.$visibleEnrichedState, this.$lastGuideUserNavigationAt$delegate, this.$focusZone$delegate, this.$focusCategoryRailSignal$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (LiveTvStartup.INSTANCE.entryFocus(this.$isTouchDevice, this.$visibleEnrichedState.getValue() != EnrichedChannels.INSTANCE.getEmpty()) == LiveTvStartup.EntryFocus.CATEGORY_LIST) {
            LiveTvScreenKt.LiveTvScreen$noteGuideUserNavigation(this.$lastGuideUserNavigationAt$delegate);
            this.$focusZone$delegate.setValue(LiveTvFocusZone.CATEGORY_LIST);
            MutableIntState mutableIntState = this.$focusCategoryRailSignal$delegate;
            mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$41$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
