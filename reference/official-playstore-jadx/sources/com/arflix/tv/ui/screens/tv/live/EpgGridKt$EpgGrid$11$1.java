package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.focus.FocusRequester;
import java.util.HashMap;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$11$1", f = "EpgGrid.kt", l = {280}, m = "invokeSuspend", v = 2)
public final class EpgGridKt$EpgGrid$11$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ HashMap<String, Integer> $channelIndexById;
    final /* synthetic */ LazyListState $channelListState;
    final /* synthetic */ int $focusSelectedChannelSignal;
    final /* synthetic */ MutableIntState $handledSelectedFocusSignal$delegate;
    final /* synthetic */ FocusRequester $selectedChannelFocusRequester;
    final /* synthetic */ String $selectedChannelId;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EpgGridKt$EpgGrid$11$1(int i10, String str, HashMap<String, Integer> map, LazyListState lazyListState, MutableIntState mutableIntState, FocusRequester focusRequester, d7.d<? super EpgGridKt$EpgGrid$11$1> dVar) {
        super(2, dVar);
        this.$focusSelectedChannelSignal = i10;
        this.$selectedChannelId = str;
        this.$channelIndexById = map;
        this.$channelListState = lazyListState;
        this.$handledSelectedFocusSignal$delegate = mutableIntState;
        this.$selectedChannelFocusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        EpgGridKt$EpgGrid$11$1 epgGridKt$EpgGrid$11$1 = new EpgGridKt$EpgGrid$11$1(this.$focusSelectedChannelSignal, this.$selectedChannelId, this.$channelIndexById, this.$channelListState, this.$handledSelectedFocusSignal$delegate, this.$selectedChannelFocusRequester, dVar);
        epgGridKt$EpgGrid$11$1.L$0 = obj;
        return epgGridKt$EpgGrid$11$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String str;
        EpgGridKt$EpgGrid$11$1 epgGridKt$EpgGrid$11$1;
        ka.k0 k0Var = (ka.k0) this.L$0;
        int i10 = this.label;
        x6.t0 t0Var = x6.t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            if (this.$focusSelectedChannelSignal == 0 || this.$handledSelectedFocusSignal$delegate.getIntValue() == this.$focusSelectedChannelSignal || (str = this.$selectedChannelId) == null) {
                return t0Var;
            }
            Integer num = this.$channelIndexById.get(str);
            if (num == null) {
                return t0Var;
            }
            int iIntValue = num.intValue();
            LazyListState lazyListState = this.$channelListState;
            this.L$0 = k0Var;
            this.L$1 = null;
            this.I$0 = iIntValue;
            this.label = 1;
            epgGridKt$EpgGrid$11$1 = this;
            Object objScrollToItem$default = LazyListState.scrollToItem$default(lazyListState, iIntValue, 0, epgGridKt$EpgGrid$11$1, 2, null);
            e7.a aVar = e7.a.f15033i;
            if (objScrollToItem$default == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            epgGridKt$EpgGrid$11$1 = this;
        }
        try {
            epgGridKt$EpgGrid$11$1.$selectedChannelFocusRequester.requestFocus();
        } catch (Throwable unused) {
        }
        epgGridKt$EpgGrid$11$1.$handledSelectedFocusSignal$delegate.setIntValue(epgGridKt$EpgGrid$11$1.$focusSelectedChannelSignal);
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((EpgGridKt$EpgGrid$11$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
