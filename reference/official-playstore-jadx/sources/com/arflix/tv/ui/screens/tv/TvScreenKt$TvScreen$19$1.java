package com.arflix.tv.ui.screens.tv;

import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.IptvChannel;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$19$1", f = "TvScreen.kt", l = {527}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$19$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ List<IptvChannel> $channels;
    final /* synthetic */ LazyListState $channelsListState;
    final /* synthetic */ MutableState<TvFocusZone> $focusZone$delegate;
    final /* synthetic */ int $safeChannelIndex;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$19$1(List<IptvChannel> list, LazyListState lazyListState, int i10, MutableState<TvFocusZone> mutableState, d7.d<? super TvScreenKt$TvScreen$19$1> dVar) {
        super(2, dVar);
        this.$channels = list;
        this.$channelsListState = lazyListState;
        this.$safeChannelIndex = i10;
        this.$focusZone$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$TvScreen$19$1(this.$channels, this.$channelsListState, this.$safeChannelIndex, this.$focusZone$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            if (TvScreenKt.TvScreen$lambda$8(this.$focusZone$delegate) == TvFocusZone.GUIDE && !this.$channels.isEmpty()) {
                LazyListState lazyListState = this.$channelsListState;
                int i11 = this.$safeChannelIndex;
                this.label = 1;
                Object objSmoothScrollTo = TvScreenKt.smoothScrollTo(lazyListState, i11, this);
                e7.a aVar = e7.a.f15033i;
                if (objSmoothScrollTo == aVar) {
                    return aVar;
                }
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$19$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
