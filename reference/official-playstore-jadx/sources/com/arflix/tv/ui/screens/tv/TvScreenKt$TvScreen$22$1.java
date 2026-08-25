package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.State;
import com.arflix.tv.data.model.IptvChannel;
import com.google.android.gms.cast.MediaError;
import java.util.List;
import ka.s0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$22$1", f = "TvScreen.kt", l = {542}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$22$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ List<IptvChannel> $channels;
    final /* synthetic */ State<String> $latestEpgAnchorChannelId$delegate;
    final /* synthetic */ String $selectedGroup;
    final /* synthetic */ List<String> $selectedGroupChannelIds;
    final /* synthetic */ TvViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$22$1(String str, List<IptvChannel> list, TvViewModel tvViewModel, List<String> list2, State<String> state, d7.d<? super TvScreenKt$TvScreen$22$1> dVar) {
        super(2, dVar);
        this.$selectedGroup = str;
        this.$channels = list;
        this.$viewModel = tvViewModel;
        this.$selectedGroupChannelIds = list2;
        this.$latestEpgAnchorChannelId$delegate = state;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$TvScreen$22$1(this.$selectedGroup, this.$channels, this.$viewModel, this.$selectedGroupChannelIds, this.$latestEpgAnchorChannelId$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int size;
        int i10;
        int size2;
        int i11;
        int i12 = this.label;
        t0 t0Var = t0.f22605a;
        if (i12 == 0) {
            k2.c.G(obj);
            if (!kotlin.text.o.h0(this.$selectedGroup) && !this.$channels.isEmpty()) {
                this.label = 1;
                Object objA = s0.a(260L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            }
            return t0Var;
        }
        if (i12 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        TvViewModel tvViewModel = this.$viewModel;
        List<String> list = this.$selectedGroupChannelIds;
        String strTvScreen$lambda$71 = TvScreenKt.TvScreen$lambda$71(this.$latestEpgAnchorChannelId$delegate);
        if (TvScreenKt.isPriorityGuideGroup(this.$selectedGroup)) {
            size = this.$channels.size();
            i10 = 480;
        } else {
            size = this.$channels.size();
            i10 = 140;
        }
        int iMin = Math.min(size, i10);
        if (TvScreenKt.isPriorityGuideGroup(this.$selectedGroup)) {
            size2 = this.$channels.size();
            i11 = 1200;
        } else {
            size2 = this.$channels.size();
            i11 = MediaError.DetailedErrorCode.DASH_MANIFEST_UNKNOWN;
        }
        TvViewModel.prefetchVisibleCategoryEpg$default(tvViewModel, list, strTvScreen$lambda$71, iMin, Math.min(size2, i11), false, 16, null);
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$22$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
