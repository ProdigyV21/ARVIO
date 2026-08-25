package com.arflix.tv.ui.screens.tv.live;

import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.PlaylistGroupKey;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "query", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$49$14$1$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$49$14$1$1 extends f7.j implements r7.p<String, d7.d<? super List<? extends EnrichedChannel>>, Object> {
    final /* synthetic */ Set<String> $hiddenGroupSet;
    final /* synthetic */ TvViewModel $viewModel;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$49$14$1$1(TvViewModel tvViewModel, Set<String> set, d7.d<? super LiveTvScreenKt$LiveTvScreen$49$14$1$1> dVar) {
        super(2, dVar);
        this.$viewModel = tvViewModel;
        this.$hiddenGroupSet = set;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean invokeSuspend$lambda$0(IptvChannel iptvChannel) {
        return LiveCategoryKt.isAdultGroup(iptvChannel.getGroup(), iptvChannel.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean invokeSuspend$lambda$1(Set set, IptvChannel iptvChannel) {
        String id = iptvChannel.getId();
        String strG0 = kotlin.text.o.G0(':', id, id);
        PlaylistGroupKey.Companion companion = PlaylistGroupKey.INSTANCE;
        String group = iptvChannel.getGroup();
        if (kotlin.text.o.h0(group)) {
            group = "Ungrouped";
        }
        return set.contains(companion.build(strG0, group));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EnrichedChannel invokeSuspend$lambda$2(int i10, IptvChannel iptvChannel) {
        return LiveCategoryKt.enrichForFastStartup(iptvChannel, i10 + 1);
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        LiveTvScreenKt$LiveTvScreen$49$14$1$1 liveTvScreenKt$LiveTvScreen$49$14$1$1 = new LiveTvScreenKt$LiveTvScreen$49$14$1$1(this.$viewModel, this.$hiddenGroupSet, dVar);
        liveTvScreenKt$LiveTvScreen$49$14$1$1.L$0 = obj;
        return liveTvScreenKt$LiveTvScreen$49$14$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String str = (String) this.L$0;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return ga.r.U(new ga.l(ga.r.J(ga.r.J(new ga.p(this.$viewModel.getIptvRepository().pagedSearchChannels(str, 200), 3), new n2(2)), new o2(this.$hiddenGroupSet, 0)), new l0(1), 2));
    }

    @Override // r7.p
    public final Object invoke(String str, d7.d<? super List<EnrichedChannel>> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$49$14$1$1) create(str, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
