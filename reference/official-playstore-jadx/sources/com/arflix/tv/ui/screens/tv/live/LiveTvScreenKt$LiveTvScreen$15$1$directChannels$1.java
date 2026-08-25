package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/IptvChannel;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$15$1$directChannels$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$15$1$directChannels$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends IptvChannel>>, Object> {
    final /* synthetic */ MutableIntState $pagedLoadedLimit$delegate;
    final /* synthetic */ x6.x $resolvedGroup;
    final /* synthetic */ MutableState<String> $selectedCategoryId$delegate;
    final /* synthetic */ LiveCategoryTree $tree;
    final /* synthetic */ TvViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$15$1$directChannels$1(x6.x xVar, TvViewModel tvViewModel, LiveCategoryTree liveCategoryTree, MutableIntState mutableIntState, MutableState<String> mutableState, d7.d<? super LiveTvScreenKt$LiveTvScreen$15$1$directChannels$1> dVar) {
        super(2, dVar);
        this.$resolvedGroup = xVar;
        this.$viewModel = tvViewModel;
        this.$tree = liveCategoryTree;
        this.$pagedLoadedLimit$delegate = mutableIntState;
        this.$selectedCategoryId$delegate = mutableState;
    }

    private static final List<IptvChannel> invokeSuspend$scanCategoryWindow(TvViewModel tvViewModel, MutableState<String> mutableState, MutableIntState mutableIntState, String str) throws IOException {
        if (!kotlin.text.u.P(LiveTvScreenKt.LiveTvScreen$lambda$13(mutableState), "grp:", false)) {
            return kotlin.collections.z.f19728i;
        }
        String strLooseIptvGroupKey = LiveTvScreenKt.looseIptvGroupKey(str);
        String strCompactIptvGroupKey = LiveTvScreenKt.compactIptvGroupKey(str);
        ArrayList arrayList = new ArrayList(mutableIntState.getIntValue());
        int size = 0;
        loop0: while (arrayList.size() < mutableIntState.getIntValue()) {
            List<IptvChannel> listPagedChannelWindow = tvViewModel.getIptvRepository().pagedChannelWindow(null, null, size, 1000);
            if (listPagedChannelWindow.isEmpty()) {
                break;
            }
            for (IptvChannel iptvChannel : listPagedChannelWindow) {
                String id = iptvChannel.getId();
                boolean zA = kotlin.jvm.internal.p.a(LiveCategoryKt.playlistGroupCategoryId(kotlin.text.o.G0(':', id, id), iptvChannel.getGroup()), LiveTvScreenKt.LiveTvScreen$lambda$13(mutableState));
                boolean z = !kotlin.text.o.h0(strLooseIptvGroupKey) && kotlin.jvm.internal.p.a(LiveTvScreenKt.looseIptvGroupKey(iptvChannel.getGroup()), strLooseIptvGroupKey);
                boolean z5 = !kotlin.text.o.h0(strCompactIptvGroupKey) && kotlin.jvm.internal.p.a(LiveTvScreenKt.compactIptvGroupKey(iptvChannel.getGroup()), strCompactIptvGroupKey);
                if (zA || z || z5) {
                    arrayList.add(iptvChannel);
                    if (arrayList.size() >= mutableIntState.getIntValue()) {
                        break loop0;
                    }
                }
            }
            size += listPagedChannelWindow.size();
        }
        return arrayList;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$15$1$directChannels$1(this.$resolvedGroup, this.$viewModel, this.$tree, this.$pagedLoadedLimit$delegate, this.$selectedCategoryId$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        x6.x xVar = this.$resolvedGroup;
        String str = xVar != null ? (String) xVar.f22608i : null;
        String str2 = xVar != null ? (String) xVar.f22609l : null;
        if (str == null || str2 == null) {
            TvViewModel tvViewModel = this.$viewModel;
            MutableState<String> mutableState = this.$selectedCategoryId$delegate;
            MutableIntState mutableIntState = this.$pagedLoadedLimit$delegate;
            LiveCategory liveCategoryById = this.$tree.byId(LiveTvScreenKt.LiveTvScreen$lambda$13(mutableState));
            return invokeSuspend$scanCategoryWindow(tvViewModel, mutableState, mutableIntState, liveCategoryById != null ? liveCategoryById.getPlaylistGroupName() : null);
        }
        List<IptvChannel> listPagedChannelWindow = this.$viewModel.getIptvRepository().pagedChannelWindow(str, str2, 0, this.$pagedLoadedLimit$delegate.getIntValue());
        if (!listPagedChannelWindow.isEmpty()) {
            return listPagedChannelWindow;
        }
        List<IptvChannel> listPagedChannelWindow2 = this.$viewModel.getIptvRepository().pagedChannelWindow(null, str2, 0, this.$pagedLoadedLimit$delegate.getIntValue());
        return !listPagedChannelWindow2.isEmpty() ? listPagedChannelWindow2 : invokeSuspend$scanCategoryWindow(this.$viewModel, this.$selectedCategoryId$delegate, this.$pagedLoadedLimit$delegate, str2);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<IptvChannel>> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$15$1$directChannels$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
