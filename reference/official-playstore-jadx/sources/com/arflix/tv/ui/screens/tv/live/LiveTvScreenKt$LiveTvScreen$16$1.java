package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$16$1", f = "LiveTvScreen.kt", l = {951, 952}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$16$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<List<EnrichedChannel>> $allDisplayChannelsState;
    final /* synthetic */ int $variantCollapseLimit;
    final /* synthetic */ MutableState<Map<String, List<EnrichedChannel>>> $variantGroupsState;
    final /* synthetic */ List<EnrichedChannel> $visibleChannels;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$16$1(List<EnrichedChannel> list, MutableState<Map<String, List<EnrichedChannel>>> mutableState, MutableState<List<EnrichedChannel>> mutableState2, int i10, d7.d<? super LiveTvScreenKt$LiveTvScreen$16$1> dVar) {
        super(2, dVar);
        this.$visibleChannels = list;
        this.$variantGroupsState = mutableState;
        this.$allDisplayChannelsState = mutableState2;
        this.$variantCollapseLimit = i10;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$16$1(this.$visibleChannels, this.$variantGroupsState, this.$allDisplayChannelsState, this.$variantCollapseLimit, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x007a, code lost:
    
        if (r8 == r5) goto L23;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
        /*
            r7 = this;
            int r0 = r7.label
            r1 = 0
            r2 = 2
            r3 = 1
            x6.t0 r4 = x6.t0.f22605a
            e7.a r5 = e7.a.f15033i
            if (r0 == 0) goto L23
            if (r0 == r3) goto L1f
            if (r0 != r2) goto L17
            java.lang.Object r0 = r7.L$0
            java.util.Map r0 = (java.util.Map) r0
            k2.c.G(r8)
            goto L7d
        L17:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L1f:
            k2.c.G(r8)
            goto L66
        L23:
            k2.c.G(r8)
            java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> r8 = r7.$visibleChannels
            boolean r8 = r8.isEmpty()
            kotlin.collections.a0 r0 = kotlin.collections.a0.f19683i
            if (r8 == 0) goto L3d
            androidx.compose.runtime.MutableState<java.util.Map<java.lang.String, java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel>>> r8 = r7.$variantGroupsState
            r8.setValue(r0)
            androidx.compose.runtime.MutableState<java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel>> r8 = r7.$allDisplayChannelsState
            kotlin.collections.z r0 = kotlin.collections.z.f19728i
            r8.setValue(r0)
            return r4
        L3d:
            java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> r8 = r7.$visibleChannels
            int r8 = r8.size()
            int r6 = r7.$variantCollapseLimit
            if (r8 <= r6) goto L54
            androidx.compose.runtime.MutableState<java.util.Map<java.lang.String, java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel>>> r8 = r7.$variantGroupsState
            r8.setValue(r0)
            androidx.compose.runtime.MutableState<java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel>> r8 = r7.$allDisplayChannelsState
            java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> r0 = r7.$visibleChannels
            r8.setValue(r0)
            return r4
        L54:
            ra.d r8 = ka.x0.f19653b
            com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$16$1$groups$1 r0 = new com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$16$1$groups$1
            java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> r6 = r7.$visibleChannels
            r0.<init>(r6, r1)
            r7.label = r3
            java.lang.Object r8 = ka.m0.y(r8, r0, r7)
            if (r8 != r5) goto L66
            goto L7c
        L66:
            r0 = r8
            java.util.Map r0 = (java.util.Map) r0
            ra.d r8 = ka.x0.f19653b
            com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$16$1$collapsed$1 r3 = new com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$16$1$collapsed$1
            java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> r6 = r7.$visibleChannels
            r3.<init>(r6, r0, r1)
            r7.L$0 = r0
            r7.label = r2
            java.lang.Object r8 = ka.m0.y(r8, r3, r7)
            if (r8 != r5) goto L7d
        L7c:
            return r5
        L7d:
            java.util.List r8 = (java.util.List) r8
            androidx.compose.runtime.MutableState<java.util.Map<java.lang.String, java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel>>> r1 = r7.$variantGroupsState
            r1.setValue(r0)
            androidx.compose.runtime.MutableState<java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel>> r0 = r7.$allDisplayChannelsState
            r0.setValue(r8)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$16$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$16$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
