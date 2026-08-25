package com.arflix.tv.ui.screens.tv.live;

import com.arflix.tv.data.model.IptvChannel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$10$1$enriched$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$10$1$enriched$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends EnrichedChannel>>, Object> {
    final /* synthetic */ List<IptvChannel> $snapshot;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$10$1$enriched$1(List<IptvChannel> list, d7.d<? super LiveTvScreenKt$LiveTvScreen$10$1$enriched$1> dVar) {
        super(2, dVar);
        this.$snapshot = list;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$10$1$enriched$1(this.$snapshot, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        List<IptvChannel> list = this.$snapshot;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
        int i10 = 0;
        for (Object obj2 : list) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            arrayList.add(LiveCategoryKt.enrich((IptvChannel) obj2, i11));
            i10 = i11;
        }
        return arrayList;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<EnrichedChannel>> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$10$1$enriched$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
