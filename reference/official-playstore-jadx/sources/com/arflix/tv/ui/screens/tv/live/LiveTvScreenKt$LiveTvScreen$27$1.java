package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.ui.screens.tv.TvUiState;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$27$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$27$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ String $epgAnchorChannelId;
    final /* synthetic */ List<String> $epgPrefetchIds;
    final /* synthetic */ MutableState<String> $focusedChannelId$delegate;
    final /* synthetic */ MutableState<String> $playingChannelId$delegate;
    final /* synthetic */ MutableState<String> $selectedCategoryId$delegate;
    final /* synthetic */ int $selectedCategoryTotalCount;
    final /* synthetic */ String $selectedDisplayChannelId;
    final /* synthetic */ MutableState<Boolean> $startupChannelApplied$delegate;
    final /* synthetic */ State<TvUiState> $state$delegate;
    final /* synthetic */ TvViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$27$1(List<String> list, String str, String str2, TvViewModel tvViewModel, int i10, State<TvUiState> state, MutableState<Boolean> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, d7.d<? super LiveTvScreenKt$LiveTvScreen$27$1> dVar) {
        super(2, dVar);
        this.$epgPrefetchIds = list;
        this.$epgAnchorChannelId = str;
        this.$selectedDisplayChannelId = str2;
        this.$viewModel = tvViewModel;
        this.$selectedCategoryTotalCount = i10;
        this.$state$delegate = state;
        this.$startupChannelApplied$delegate = mutableState;
        this.$focusedChannelId$delegate = mutableState2;
        this.$playingChannelId$delegate = mutableState3;
        this.$selectedCategoryId$delegate = mutableState4;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$27$1(this.$epgPrefetchIds, this.$epgAnchorChannelId, this.$selectedDisplayChannelId, this.$viewModel, this.$selectedCategoryTotalCount, this.$state$delegate, this.$startupChannelApplied$delegate, this.$focusedChannelId$delegate, this.$playingChannelId$delegate, this.$selectedCategoryId$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (LiveTvScreenKt.LiveTvScreen$lambda$7(this.$state$delegate).getIptvPreferencesLoaded() && LiveTvScreenKt.LiveTvScreen$lambda$7(this.$state$delegate).getTvSessionLoaded() && LiveTvScreenKt.LiveTvScreen$lambda$74(this.$startupChannelApplied$delegate) && !this.$epgPrefetchIds.isEmpty()) {
            String strLiveTvScreen$lambda$68 = this.$epgAnchorChannelId;
            if (strLiveTvScreen$lambda$68 == null && (strLiveTvScreen$lambda$68 = this.$selectedDisplayChannelId) == null && (strLiveTvScreen$lambda$68 = LiveTvScreenKt.LiveTvScreen$lambda$68(this.$focusedChannelId$delegate)) == null && (strLiveTvScreen$lambda$68 = LiveTvScreenKt.LiveTvScreen$lambda$63(this.$playingChannelId$delegate)) == null) {
                strLiveTvScreen$lambda$68 = (String) kotlin.collections.x.o0(this.$epgPrefetchIds);
            }
            this.$viewModel.prefetchVisibleCategoryEpg(this.$epgPrefetchIds, strLiveTvScreen$lambda$68, this.$selectedCategoryTotalCount > 10000 ? 8 : kotlin.jvm.internal.p.a(LiveTvScreenKt.LiveTvScreen$lambda$13(this.$selectedCategoryId$delegate), TtmlNode.COMBINE_ALL) ? 12 : 24, this.$selectedCategoryTotalCount <= 10000 ? kotlin.jvm.internal.p.a(LiveTvScreenKt.LiveTvScreen$lambda$13(this.$selectedCategoryId$delegate), TtmlNode.COMBINE_ALL) ? 48 : 96 : 24, true);
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$27$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
