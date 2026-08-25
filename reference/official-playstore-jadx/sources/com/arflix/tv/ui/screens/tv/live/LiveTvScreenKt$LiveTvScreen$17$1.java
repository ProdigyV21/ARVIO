package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$17$1", f = "LiveTvScreen.kt", l = {967, 969}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$17$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<Map<String, Integer>> $filteredChannelIndexState;
    final /* synthetic */ MutableState<List<EnrichedChannel>> $filteredChannelsCollapsedState;
    final /* synthetic */ MutableState<List<EnrichedChannel>> $filteredChannelsState;
    final /* synthetic */ Map<String, List<EnrichedChannel>> $variantGroups;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LiveTvScreenKt$LiveTvScreen$17$1(MutableState<List<EnrichedChannel>> mutableState, Map<String, ? extends List<EnrichedChannel>> map, MutableState<List<EnrichedChannel>> mutableState2, MutableState<Map<String, Integer>> mutableState3, d7.d<? super LiveTvScreenKt$LiveTvScreen$17$1> dVar) {
        super(2, dVar);
        this.$filteredChannelsState = mutableState;
        this.$variantGroups = map;
        this.$filteredChannelsCollapsedState = mutableState2;
        this.$filteredChannelIndexState = mutableState3;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$17$1(this.$filteredChannelsState, this.$variantGroups, this.$filteredChannelsCollapsedState, this.$filteredChannelIndexState, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x004f, code lost:
    
        if (r8 == r4) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0066, code lost:
    
        if (r8 != r4) goto L20;
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
            r1 = 2
            r2 = 1
            r3 = 0
            e7.a r4 = e7.a.f15033i
            if (r0 == 0) goto L29
            if (r0 == r2) goto L21
            if (r0 != r1) goto L19
            java.lang.Object r0 = r7.L$1
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r1 = r7.L$0
            java.util.List r1 = (java.util.List) r1
            k2.c.G(r8)
            goto L69
        L19:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L21:
            java.lang.Object r0 = r7.L$0
            java.util.List r0 = (java.util.List) r0
            k2.c.G(r8)
            goto L52
        L29:
            k2.c.G(r8)
            androidx.compose.runtime.MutableState<java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel>> r8 = r7.$filteredChannelsState
            java.lang.Object r8 = r8.getValue()
            java.util.List r8 = (java.util.List) r8
            java.util.Map<java.lang.String, java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel>> r0 = r7.$variantGroups
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L3e
        L3c:
            r0 = r8
            goto L55
        L3e:
            ra.d r0 = ka.x0.f19653b
            com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$17$1$collapsed$1 r5 = new com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$17$1$collapsed$1
            java.util.Map<java.lang.String, java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel>> r6 = r7.$variantGroups
            r5.<init>(r8, r6, r3)
            r7.L$0 = r3
            r7.label = r2
            java.lang.Object r8 = ka.m0.y(r0, r5, r7)
            if (r8 != r4) goto L52
            goto L68
        L52:
            java.util.List r8 = (java.util.List) r8
            goto L3c
        L55:
            ra.d r8 = ka.x0.f19653b
            com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$17$1$index$1 r2 = new com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$17$1$index$1
            r2.<init>(r0, r3)
            r7.L$0 = r3
            r7.L$1 = r0
            r7.label = r1
            java.lang.Object r8 = ka.m0.y(r8, r2, r7)
            if (r8 != r4) goto L69
        L68:
            return r4
        L69:
            java.util.HashMap r8 = (java.util.HashMap) r8
            androidx.compose.runtime.MutableState<java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel>> r1 = r7.$filteredChannelsCollapsedState
            r1.setValue(r0)
            androidx.compose.runtime.MutableState<java.util.Map<java.lang.String, java.lang.Integer>> r0 = r7.$filteredChannelIndexState
            r0.setValue(r8)
            x6.t0 r8 = x6.t0.f22605a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$17$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$17$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
