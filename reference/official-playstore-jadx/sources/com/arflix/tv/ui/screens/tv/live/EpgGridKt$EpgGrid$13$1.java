package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$13$1", f = "EpgGrid.kt", l = {306}, m = "invokeSuspend", v = 2)
public final class EpgGridKt$EpgGrid$13$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<String> $activeChannelFocusId$delegate;
    final /* synthetic */ LinkedHashMap<String, FocusRequester> $channelFocusRequesters;
    final /* synthetic */ HashMap<String, Integer> $channelIndexById;
    final /* synthetic */ LazyListState $channelListState;
    final /* synthetic */ int $channelWindowOffset;
    final /* synthetic */ List<EnrichedChannel> $channels;
    final /* synthetic */ long $clockTickMillis;
    final /* synthetic */ FocusRequester $firstChannelFocusRequester;
    final /* synthetic */ int $focusEpgSignal;
    final /* synthetic */ MutableIntState $handledEpgFocusSignal$delegate;
    final /* synthetic */ r7.l<EnrichedChannel, x6.t0> $onChannelFocused;
    final /* synthetic */ r7.a<x6.t0> $onRequestNextChannels;
    final /* synthetic */ r7.a<x6.t0> $onRequestPreviousChannels;
    final /* synthetic */ MutableState<String> $pendingChannelFocusId$delegate;
    final /* synthetic */ LinkedHashMap<String, List<FocusRequester>> $programFocusRequesters;
    final /* synthetic */ LinkedHashMap<String, List<ProgramFocusTarget>> $programFocusTargets;
    final /* synthetic */ int $safeTotalChannelCount;
    final /* synthetic */ ka.k0 $scope;
    final /* synthetic */ FocusRequester $selectedChannelFocusRequester;
    final /* synthetic */ String $selectedChannelId;
    final /* synthetic */ long $windowStartMillis;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    int I$5;
    int I$6;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public EpgGridKt$EpgGrid$13$1(int i10, String str, HashMap<String, Integer> map, long j10, long j11, MutableIntState mutableIntState, List<EnrichedChannel> list, LinkedHashMap<String, List<ProgramFocusTarget>> linkedHashMap, LinkedHashMap<String, List<FocusRequester>> linkedHashMap2, ka.k0 k0Var, LazyListState lazyListState, r7.l<? super EnrichedChannel, x6.t0> lVar, LinkedHashMap<String, FocusRequester> linkedHashMap3, int i11, r7.a<x6.t0> aVar, int i12, r7.a<x6.t0> aVar2, MutableState<String> mutableState, MutableState<String> mutableState2, FocusRequester focusRequester, FocusRequester focusRequester2, d7.d<? super EpgGridKt$EpgGrid$13$1> dVar) {
        super(2, dVar);
        this.$focusEpgSignal = i10;
        this.$selectedChannelId = str;
        this.$channelIndexById = map;
        this.$clockTickMillis = j10;
        this.$windowStartMillis = j11;
        this.$handledEpgFocusSignal$delegate = mutableIntState;
        this.$channels = list;
        this.$programFocusTargets = linkedHashMap;
        this.$programFocusRequesters = linkedHashMap2;
        this.$scope = k0Var;
        this.$channelListState = lazyListState;
        this.$onChannelFocused = lVar;
        this.$channelFocusRequesters = linkedHashMap3;
        this.$channelWindowOffset = i11;
        this.$onRequestPreviousChannels = aVar;
        this.$safeTotalChannelCount = i12;
        this.$onRequestNextChannels = aVar2;
        this.$activeChannelFocusId$delegate = mutableState;
        this.$pendingChannelFocusId$delegate = mutableState2;
        this.$firstChannelFocusRequester = focusRequester;
        this.$selectedChannelFocusRequester = focusRequester2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new EpgGridKt$EpgGrid$13$1(this.$focusEpgSignal, this.$selectedChannelId, this.$channelIndexById, this.$clockTickMillis, this.$windowStartMillis, this.$handledEpgFocusSignal$delegate, this.$channels, this.$programFocusTargets, this.$programFocusRequesters, this.$scope, this.$channelListState, this.$onChannelFocused, this.$channelFocusRequesters, this.$channelWindowOffset, this.$onRequestPreviousChannels, this.$safeTotalChannelCount, this.$onRequestNextChannels, this.$activeChannelFocusId$delegate, this.$pendingChannelFocusId$delegate, this.$firstChannelFocusRequester, this.$selectedChannelFocusRequester, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00dc  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00d4 -> B:29:0x00d6). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            Method dump skipped, instruction units count: 267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$13$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((EpgGridKt$EpgGrid$13$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
