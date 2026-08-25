package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.data.model.IptvChannel;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$17$1", f = "TvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$17$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableIntState $channelIndex$delegate;
    final /* synthetic */ List<IptvChannel> $channels;
    final /* synthetic */ MutableState<String> $selectedChannelId$delegate;
    final /* synthetic */ State<TvUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$17$1(List<IptvChannel> list, MutableIntState mutableIntState, MutableState<String> mutableState, State<TvUiState> state, d7.d<? super TvScreenKt$TvScreen$17$1> dVar) {
        super(2, dVar);
        this.$channels = list;
        this.$channelIndex$delegate = mutableIntState;
        this.$selectedChannelId$delegate = mutableState;
        this.$uiState$delegate = state;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$TvScreen$17$1(this.$channels, this.$channelIndex$delegate, this.$selectedChannelId$delegate, this.$uiState$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (this.$channelIndex$delegate.getIntValue() >= this.$channels.size()) {
            this.$channelIndex$delegate.setIntValue(0);
        }
        if (TvScreenKt.TvScreen$lambda$20(this.$selectedChannelId$delegate) != null) {
            List<IptvChannel> channels = TvScreenKt.TvScreen$lambda$6(this.$uiState$delegate).getSnapshot().getChannels();
            MutableState<String> mutableState = this.$selectedChannelId$delegate;
            if (channels == null || !channels.isEmpty()) {
                Iterator<T> it = channels.iterator();
                while (it.hasNext()) {
                    if (kotlin.jvm.internal.p.a(((IptvChannel) it.next()).getId(), TvScreenKt.TvScreen$lambda$20(mutableState))) {
                        break;
                    }
                }
                this.$selectedChannelId$delegate.setValue(null);
            } else {
                this.$selectedChannelId$delegate.setValue(null);
            }
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$17$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
