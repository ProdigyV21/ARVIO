package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.IptvChannel;
import java.util.Iterator;
import java.util.List;
import ka.s0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$23$1", f = "TvScreen.kt", l = {556}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$23$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ List<IptvChannel> $channels;
    final /* synthetic */ MutableState<TvFocusZone> $focusZone$delegate;
    final /* synthetic */ MutableState<Boolean> $isFastNavigating$delegate;
    final /* synthetic */ MutableState<Boolean> $isFullScreen$delegate;
    final /* synthetic */ MutableState<String> $playingChannelId$delegate;
    final /* synthetic */ MutableState<String> $selectedChannelId$delegate;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$23$1(List<IptvChannel> list, MutableState<Boolean> mutableState, MutableState<TvFocusZone> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<Boolean> mutableState5, d7.d<? super TvScreenKt$TvScreen$23$1> dVar) {
        super(2, dVar);
        this.$channels = list;
        this.$isFullScreen$delegate = mutableState;
        this.$focusZone$delegate = mutableState2;
        this.$selectedChannelId$delegate = mutableState3;
        this.$playingChannelId$delegate = mutableState4;
        this.$isFastNavigating$delegate = mutableState5;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$TvScreen$23$1(this.$channels, this.$isFullScreen$delegate, this.$focusZone$delegate, this.$selectedChannelId$delegate, this.$playingChannelId$delegate, this.$isFastNavigating$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String strTvScreen$lambda$20;
        List<IptvChannel> list;
        List<IptvChannel> list2;
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            if (!TvScreenKt.TvScreen$lambda$29(this.$isFullScreen$delegate) && TvScreenKt.TvScreen$lambda$8(this.$focusZone$delegate) == TvFocusZone.GROUPS && (strTvScreen$lambda$20 = TvScreenKt.TvScreen$lambda$20(this.$selectedChannelId$delegate)) != null && !strTvScreen$lambda$20.equals(TvScreenKt.TvScreen$lambda$23(this.$playingChannelId$delegate)) && ((list = this.$channels) == null || !list.isEmpty())) {
                Iterator<T> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (kotlin.jvm.internal.p.a(((IptvChannel) it.next()).getId(), strTvScreen$lambda$20)) {
                        if (!TvScreenKt.TvScreen$lambda$50(this.$isFastNavigating$delegate)) {
                            this.L$0 = strTvScreen$lambda$20;
                            this.label = 1;
                            Object objA = s0.a(120L, this);
                            e7.a aVar = e7.a.f15033i;
                            if (objA == aVar) {
                                return aVar;
                            }
                        }
                    }
                }
            }
            return t0Var;
        }
        if (i10 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        strTvScreen$lambda$20 = (String) this.L$0;
        k2.c.G(obj);
        if (!TvScreenKt.TvScreen$lambda$29(this.$isFullScreen$delegate) && TvScreenKt.TvScreen$lambda$8(this.$focusZone$delegate) == TvFocusZone.GROUPS && !TvScreenKt.TvScreen$lambda$50(this.$isFastNavigating$delegate) && ((list2 = this.$channels) == null || !list2.isEmpty())) {
            Iterator<T> it2 = list2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                if (kotlin.jvm.internal.p.a(((IptvChannel) it2.next()).getId(), strTvScreen$lambda$20)) {
                    if (kotlin.jvm.internal.p.a(TvScreenKt.TvScreen$lambda$20(this.$selectedChannelId$delegate), strTvScreen$lambda$20) && !kotlin.jvm.internal.p.a(TvScreenKt.TvScreen$lambda$23(this.$playingChannelId$delegate), strTvScreen$lambda$20)) {
                        this.$playingChannelId$delegate.setValue(strTvScreen$lambda$20);
                    }
                }
            }
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$23$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
