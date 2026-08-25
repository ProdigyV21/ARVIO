package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.ui.focus.FocusRequester;
import java.util.LinkedHashMap;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$keepChannelFocus$2", f = "EpgGrid.kt", l = {226, 227, 237}, m = "invokeSuspend", v = 2)
public final class EpgGridKt$EpgGrid$keepChannelFocus$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ EnrichedChannel $channel;
    final /* synthetic */ LinkedHashMap<String, FocusRequester> $channelFocusRequesters;
    final /* synthetic */ LazyListState $channelListState;
    final /* synthetic */ FocusRequester $firstChannelFocusRequester;
    final /* synthetic */ int $rowIdx;
    final /* synthetic */ FocusRequester $selectedChannelFocusRequester;
    final /* synthetic */ String $selectedChannelId;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EpgGridKt$EpgGrid$keepChannelFocus$2(LazyListState lazyListState, int i10, LinkedHashMap<String, FocusRequester> linkedHashMap, EnrichedChannel enrichedChannel, FocusRequester focusRequester, String str, FocusRequester focusRequester2, d7.d<? super EpgGridKt$EpgGrid$keepChannelFocus$2> dVar) {
        super(2, dVar);
        this.$channelListState = lazyListState;
        this.$rowIdx = i10;
        this.$channelFocusRequesters = linkedHashMap;
        this.$channel = enrichedChannel;
        this.$firstChannelFocusRequester = focusRequester;
        this.$selectedChannelId = str;
        this.$selectedChannelFocusRequester = focusRequester2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        EpgGridKt$EpgGrid$keepChannelFocus$2 epgGridKt$EpgGrid$keepChannelFocus$2 = new EpgGridKt$EpgGrid$keepChannelFocus$2(this.$channelListState, this.$rowIdx, this.$channelFocusRequesters, this.$channel, this.$firstChannelFocusRequester, this.$selectedChannelId, this.$selectedChannelFocusRequester, dVar);
        epgGridKt$EpgGrid$keepChannelFocus$2.L$0 = obj;
        return epgGridKt$EpgGrid$keepChannelFocus$2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x006c, code lost:
    
        if (ka.s0.a(16, r18) == r13) goto L40;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ef A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00df -> B:42:0x00e4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00ea -> B:44:0x00ec). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$keepChannelFocus$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((EpgGridKt$EpgGrid$keepChannelFocus$2) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
