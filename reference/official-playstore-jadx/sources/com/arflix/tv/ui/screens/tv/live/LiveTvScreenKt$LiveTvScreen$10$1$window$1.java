package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/IptvChannel;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$10$1$window$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$10$1$window$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends IptvChannel>>, Object> {
    final /* synthetic */ MutableState<EnrichedChannels> $enrichedState;
    final /* synthetic */ Set<String> $favSet;
    final /* synthetic */ List<x6.j0> $groupCounts;
    final /* synthetic */ MutableState<List<x6.j0>> $lastKnownPlaylistGroupCounts$delegate;
    final /* synthetic */ MutableIntState $pagedLoadedLimit$delegate;
    final /* synthetic */ kotlin.jvm.internal.i0 $pagedTotal;
    final /* synthetic */ List<EnrichedChannel> $previousFavoriteRows;
    final /* synthetic */ MutableState<LinkedHashSet<String>> $recents;
    final /* synthetic */ MutableState<String> $selectedCategoryId$delegate;
    final /* synthetic */ TvViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$10$1$window$1(TvViewModel tvViewModel, Set<String> set, List<EnrichedChannel> list, MutableState<LinkedHashSet<String>> mutableState, MutableState<EnrichedChannels> mutableState2, kotlin.jvm.internal.i0 i0Var, List<x6.j0> list2, MutableState<String> mutableState3, MutableIntState mutableIntState, MutableState<List<x6.j0>> mutableState4, d7.d<? super LiveTvScreenKt$LiveTvScreen$10$1$window$1> dVar) {
        super(2, dVar);
        this.$viewModel = tvViewModel;
        this.$favSet = set;
        this.$previousFavoriteRows = list;
        this.$recents = mutableState;
        this.$enrichedState = mutableState2;
        this.$pagedTotal = i0Var;
        this.$groupCounts = list2;
        this.$selectedCategoryId$delegate = mutableState3;
        this.$pagedLoadedLimit$delegate = mutableIntState;
        this.$lastKnownPlaylistGroupCounts$delegate = mutableState4;
    }

    private static final List<IptvChannel> invokeSuspend$scanCategoryWindow(int i10, kotlin.jvm.internal.i0 i0Var, TvViewModel tvViewModel, String str, String str2) throws IOException {
        if (!kotlin.text.u.P(str, "grp:", false)) {
            return kotlin.collections.z.f19728i;
        }
        String strLooseIptvGroupKey = LiveTvScreenKt.looseIptvGroupKey(str2);
        String strCompactIptvGroupKey = LiveTvScreenKt.compactIptvGroupKey(str2);
        ArrayList arrayList = new ArrayList(i10);
        int size = 0;
        loop0: while (arrayList.size() < i10 && size < i0Var.f19744i) {
            List<IptvChannel> listPagedChannelWindow = tvViewModel.getIptvRepository().pagedChannelWindow(null, null, size, 1000);
            if (listPagedChannelWindow.isEmpty()) {
                break;
            }
            for (IptvChannel iptvChannel : listPagedChannelWindow) {
                String id = iptvChannel.getId();
                boolean zA = kotlin.jvm.internal.p.a(LiveCategoryKt.playlistGroupCategoryId(kotlin.text.o.G0(':', id, id), iptvChannel.getGroup()), str);
                boolean z = !kotlin.text.o.h0(strLooseIptvGroupKey) && kotlin.jvm.internal.p.a(LiveTvScreenKt.looseIptvGroupKey(iptvChannel.getGroup()), strLooseIptvGroupKey);
                boolean z5 = !kotlin.text.o.h0(strCompactIptvGroupKey) && kotlin.jvm.internal.p.a(LiveTvScreenKt.compactIptvGroupKey(iptvChannel.getGroup()), strCompactIptvGroupKey);
                if (zA || z || z5) {
                    arrayList.add(iptvChannel);
                    if (arrayList.size() >= i10) {
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
        return new LiveTvScreenKt$LiveTvScreen$10$1$window$1(this.$viewModel, this.$favSet, this.$previousFavoriteRows, this.$recents, this.$enrichedState, this.$pagedTotal, this.$groupCounts, this.$selectedCategoryId$delegate, this.$pagedLoadedLimit$delegate, this.$lastKnownPlaylistGroupCounts$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0115, code lost:
    
        if (r2.equals("fav") == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x013c, code lost:
    
        if (r2.equals("recent") != false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x014a, code lost:
    
        return com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.selectPagedChannelsInProviderOrder(com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.LiveTvScreen$lambda$13(r16.$selectedCategoryId$delegate), kotlin.collections.z.f19728i, r3, r4, r1);
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            Method dump skipped, instruction units count: 602
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$10$1$window$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<IptvChannel>> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$10$1$window$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
