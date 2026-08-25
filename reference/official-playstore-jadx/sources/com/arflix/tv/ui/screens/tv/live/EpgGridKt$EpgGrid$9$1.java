package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.MutableState;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$9$1", f = "EpgGrid.kt", l = {178}, m = "invokeSuspend", v = 2)
public final class EpgGridKt$EpgGrid$9$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<String> $activeChannelFocusId$delegate;
    final /* synthetic */ HashMap<String, Integer> $channelIndexById;
    final /* synthetic */ LazyListState $channelListState;
    final /* synthetic */ List<EnrichedChannel> $channels;
    final /* synthetic */ MutableState<Boolean> $didPositionInitialSelection$delegate;
    final /* synthetic */ MutableState<String> $pendingChannelFocusId$delegate;
    final /* synthetic */ String $selectedChannelId;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EpgGridKt$EpgGrid$9$1(List<EnrichedChannel> list, LazyListState lazyListState, String str, HashMap<String, Integer> map, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<Boolean> mutableState3, d7.d<? super EpgGridKt$EpgGrid$9$1> dVar) {
        super(2, dVar);
        this.$channels = list;
        this.$channelListState = lazyListState;
        this.$selectedChannelId = str;
        this.$channelIndexById = map;
        this.$activeChannelFocusId$delegate = mutableState;
        this.$pendingChannelFocusId$delegate = mutableState2;
        this.$didPositionInitialSelection$delegate = mutableState3;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new EpgGridKt$EpgGrid$9$1(this.$channels, this.$channelListState, this.$selectedChannelId, this.$channelIndexById, this.$activeChannelFocusId$delegate, this.$pendingChannelFocusId$delegate, this.$didPositionInitialSelection$delegate, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0048  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            int r0 = r9.label
            x6.t0 r1 = x6.t0.f22605a
            r2 = 1
            if (r0 == 0) goto L16
            if (r0 != r2) goto Le
            k2.c.G(r10)
            r6 = r9
            goto L34
        Le:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L16:
            k2.c.G(r10)
            java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> r10 = r9.$channels
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L22
            return r1
        L22:
            androidx.compose.foundation.lazy.LazyListState r3 = r9.$channelListState
            r9.label = r2
            r4 = 0
            r5 = 0
            r7 = 2
            r8 = 0
            r6 = r9
            java.lang.Object r10 = androidx.compose.foundation.lazy.LazyListState.scrollToItem$default(r3, r4, r5, r6, r7, r8)
            e7.a r0 = e7.a.f15033i
            if (r10 != r0) goto L34
            return r0
        L34:
            androidx.compose.runtime.MutableState<java.lang.String> r10 = r6.$activeChannelFocusId$delegate
            java.lang.String r0 = r6.$selectedChannelId
            r3 = 0
            if (r0 == 0) goto L48
            java.util.HashMap<java.lang.String, java.lang.Integer> r4 = r6.$channelIndexById
            boolean r4 = r4.containsKey(r0)
            if (r4 == 0) goto L44
            goto L45
        L44:
            r0 = r3
        L45:
            if (r0 == 0) goto L48
            goto L58
        L48:
            java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> r0 = r6.$channels
            java.lang.Object r0 = kotlin.collections.x.o0(r0)
            com.arflix.tv.ui.screens.tv.live.EnrichedChannel r0 = (com.arflix.tv.ui.screens.tv.live.EnrichedChannel) r0
            if (r0 == 0) goto L57
            java.lang.String r0 = r0.getId()
            goto L58
        L57:
            r0 = r3
        L58:
            com.arflix.tv.ui.screens.tv.live.EpgGridKt.access$EpgGrid$lambda$25(r10, r0)
            androidx.compose.runtime.MutableState<java.lang.String> r10 = r6.$pendingChannelFocusId$delegate
            com.arflix.tv.ui.screens.tv.live.EpgGridKt.access$EpgGrid$lambda$28(r10, r3)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r10 = r6.$didPositionInitialSelection$delegate
            com.arflix.tv.ui.screens.tv.live.EpgGridKt.access$EpgGrid$lambda$22(r10, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$9$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((EpgGridKt$EpgGrid$9$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
