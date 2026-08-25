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
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$12$1", f = "TvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$12$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableIntState $channelIndex$delegate;
    final /* synthetic */ MutableIntState $groupIndex$delegate;
    final /* synthetic */ List<String> $groups;
    final /* synthetic */ String $initialChannelId;
    final /* synthetic */ MutableState<Boolean> $isFullScreen$delegate;
    final /* synthetic */ MutableState<String> $playingChannelId$delegate;
    final /* synthetic */ MutableState<String> $selectedChannelId$delegate;
    final /* synthetic */ State<TvUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$12$1(String str, List<String> list, State<TvUiState> state, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<Boolean> mutableState3, d7.d<? super TvScreenKt$TvScreen$12$1> dVar) {
        super(2, dVar);
        this.$initialChannelId = str;
        this.$groups = list;
        this.$uiState$delegate = state;
        this.$groupIndex$delegate = mutableIntState;
        this.$channelIndex$delegate = mutableIntState2;
        this.$selectedChannelId$delegate = mutableState;
        this.$playingChannelId$delegate = mutableState2;
        this.$isFullScreen$delegate = mutableState3;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$TvScreen$12$1(this.$initialChannelId, this.$groups, this.$uiState$delegate, this.$groupIndex$delegate, this.$channelIndex$delegate, this.$selectedChannelId$delegate, this.$playingChannelId$delegate, this.$isFullScreen$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        IptvChannel iptvChannel;
        int i10;
        List<IptvChannel> list;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (this.$initialChannelId != null && !TvScreenKt.TvScreen$lambda$6(this.$uiState$delegate).getSnapshot().getChannels().isEmpty() && (iptvChannel = TvScreenKt.TvScreen$lambda$6(this.$uiState$delegate).getChannelLookup().get(this.$initialChannelId)) != null) {
            List<String> list2 = this.$groups;
            State<TvUiState> state = this.$uiState$delegate;
            Iterator<String> it = list2.iterator();
            int i11 = 0;
            loop0: while (true) {
                boolean zHasNext = it.hasNext();
                i10 = -1;
                list = kotlin.collections.z.f19728i;
                if (!zHasNext) {
                    i11 = -1;
                    break;
                }
                List<IptvChannel> list3 = TvScreenKt.TvScreen$lambda$6(state).getChannelsByGroup().get(it.next());
                if (list3 == null) {
                    list3 = list;
                }
                if (!list3.isEmpty()) {
                    Iterator<T> it2 = list3.iterator();
                    while (it2.hasNext()) {
                        if (kotlin.jvm.internal.p.a(((IptvChannel) it2.next()).getId(), iptvChannel.getId())) {
                            break loop0;
                        }
                    }
                }
                i11++;
            }
            if (i11 >= 0) {
                this.$groupIndex$delegate.setIntValue(i11);
                List<IptvChannel> list4 = TvScreenKt.TvScreen$lambda$6(this.$uiState$delegate).getChannelsByGroup().get(this.$groups.get(i11));
                if (list4 != null) {
                    list = list4;
                }
                MutableIntState mutableIntState = this.$channelIndex$delegate;
                Iterator<IptvChannel> it3 = list.iterator();
                int i12 = 0;
                while (true) {
                    if (!it3.hasNext()) {
                        break;
                    }
                    if (kotlin.jvm.internal.p.a(it3.next().getId(), iptvChannel.getId())) {
                        i10 = i12;
                        break;
                    }
                    i12++;
                }
                mutableIntState.setIntValue(i10 >= 0 ? i10 : 0);
            }
            this.$selectedChannelId$delegate.setValue(iptvChannel.getId());
            if (!kotlin.jvm.internal.p.a(TvScreenKt.TvScreen$lambda$23(this.$playingChannelId$delegate), iptvChannel.getId())) {
                this.$playingChannelId$delegate.setValue(iptvChannel.getId());
            }
            TvScreenKt.TvScreen$lambda$30(this.$isFullScreen$delegate, true);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$12$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
