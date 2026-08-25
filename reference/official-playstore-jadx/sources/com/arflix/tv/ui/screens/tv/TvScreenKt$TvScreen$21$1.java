package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.MutableIntState;
import com.arflix.tv.data.model.IptvChannel;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$21$1", f = "TvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$21$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ List<IptvChannel> $channels;
    final /* synthetic */ MutableIntState $groupIndex$delegate;
    final /* synthetic */ List<String> $groups;
    final /* synthetic */ String $selectedGroup;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$21$1(String str, List<IptvChannel> list, List<String> list2, MutableIntState mutableIntState, d7.d<? super TvScreenKt$TvScreen$21$1> dVar) {
        super(2, dVar);
        this.$selectedGroup = str;
        this.$channels = list;
        this.$groups = list2;
        this.$groupIndex$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$TvScreen$21$1(this.$selectedGroup, this.$channels, this.$groups, this.$groupIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (kotlin.jvm.internal.p.a(this.$selectedGroup, TvViewModelKt.FAVORITES_GROUP_NAME) && this.$channels.isEmpty() && this.$groups.size() > 1 && this.$groupIndex$delegate.getIntValue() == 0) {
            this.$groupIndex$delegate.setIntValue(1);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$21$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
