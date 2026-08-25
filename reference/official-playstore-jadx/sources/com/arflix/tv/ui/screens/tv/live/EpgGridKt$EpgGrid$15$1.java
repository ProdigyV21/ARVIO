package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyListItemInfo;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.SnapshotStateKt;
import com.google.android.gms.cast.MediaError;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$15$1", f = "EpgGrid.kt", l = {MediaError.DetailedErrorCode.SMOOTH_NO_MEDIA_DATA}, m = "invokeSuspend", v = 2)
public final class EpgGridKt$EpgGrid$15$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ LazyListState $channelListState;
    final /* synthetic */ int $channelWindowOffset;
    final /* synthetic */ List<EnrichedChannel> $channels;
    final /* synthetic */ r7.a<x6.t0> $onRequestNextChannels;
    final /* synthetic */ r7.a<x6.t0> $onRequestPreviousChannels;
    final /* synthetic */ int $safeTotalChannelCount;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EpgGridKt$EpgGrid$15$1(LazyListState lazyListState, int i10, r7.a<x6.t0> aVar, List<EnrichedChannel> list, int i11, r7.a<x6.t0> aVar2, d7.d<? super EpgGridKt$EpgGrid$15$1> dVar) {
        super(2, dVar);
        this.$channelListState = lazyListState;
        this.$channelWindowOffset = i10;
        this.$onRequestPreviousChannels = aVar;
        this.$channels = list;
        this.$safeTotalChannelCount = i11;
        this.$onRequestNextChannels = aVar2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.x invokeSuspend$lambda$0(LazyListState lazyListState) {
        List<LazyListItemInfo> visibleItemsInfo = lazyListState.getLayoutInfo().getVisibleItemsInfo();
        LazyListItemInfo lazyListItemInfo = (LazyListItemInfo) kotlin.collections.x.o0(visibleItemsInfo);
        int index = lazyListItemInfo != null ? lazyListItemInfo.getIndex() : 0;
        LazyListItemInfo lazyListItemInfo2 = (LazyListItemInfo) kotlin.collections.x.y0(visibleItemsInfo);
        return new x6.x(Integer.valueOf(index), Integer.valueOf(lazyListItemInfo2 != null ? lazyListItemInfo2.getIndex() : 0));
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new EpgGridKt$EpgGrid$15$1(this.$channelListState, this.$channelWindowOffset, this.$onRequestPreviousChannels, this.$channels, this.$safeTotalChannelCount, this.$onRequestNextChannels, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            na.j jVarH = na.y0.h(SnapshotStateKt.snapshotFlow(new u(this.$channelListState, 3)));
            final int i11 = this.$channelWindowOffset;
            final r7.a<x6.t0> aVar = this.$onRequestPreviousChannels;
            final List<EnrichedChannel> list = this.$channels;
            final int i12 = this.$safeTotalChannelCount;
            final r7.a<x6.t0> aVar2 = this.$onRequestNextChannels;
            na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$15$1.2
                public final Object emit(x6.x xVar, d7.d<? super x6.t0> dVar) {
                    int iIntValue = ((Number) xVar.f22608i).intValue();
                    int iIntValue2 = ((Number) xVar.f22609l).intValue();
                    if (iIntValue <= 10 && i11 > 0) {
                        aVar.invoke();
                    }
                    if (!list.isEmpty() && t7.a.w(list) - iIntValue2 <= 10 && i11 + iIntValue2 < i12 - 1) {
                        aVar2.invoke();
                    }
                    return x6.t0.f22605a;
                }

                @Override // na.k
                public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                    return emit((x6.x) obj2, (d7.d<? super x6.t0>) dVar);
                }
            };
            this.label = 1;
            Object objCollect = jVarH.collect(kVar, this);
            e7.a aVar3 = e7.a.f15033i;
            if (objCollect == aVar3) {
                return aVar3;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((EpgGridKt$EpgGrid$15$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
