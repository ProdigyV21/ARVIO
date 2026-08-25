package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$commitFocusedChannel$1", f = "LiveTvScreen.kt", l = {AnalyticsListener.EVENT_AUDIO_CODEC_ERROR}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$commitFocusedChannel$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<String> $epgPrefetchAnchorId$delegate;
    final /* synthetic */ MutableState<String> $focusedChannelId$delegate;
    final /* synthetic */ x6.x[] $pendingFocusCommit;
    final /* synthetic */ Map<String, String> $rememberedChannelByCategory;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$commitFocusedChannel$1(x6.x[] xVarArr, Map<String, String> map, MutableState<String> mutableState, MutableState<String> mutableState2, d7.d<? super LiveTvScreenKt$LiveTvScreen$commitFocusedChannel$1> dVar) {
        super(2, dVar);
        this.$pendingFocusCommit = xVarArr;
        this.$rememberedChannelByCategory = map;
        this.$focusedChannelId$delegate = mutableState;
        this.$epgPrefetchAnchorId$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$commitFocusedChannel$1(this.$pendingFocusCommit, this.$rememberedChannelByCategory, this.$focusedChannelId$delegate, this.$epgPrefetchAnchorId$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            this.label = 1;
            Object objA = ka.s0.a(450L, this);
            e7.a aVar = e7.a.f15033i;
            if (objA == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        x6.x xVar = this.$pendingFocusCommit[0];
        x6.t0 t0Var = x6.t0.f22605a;
        if (xVar == null) {
            return t0Var;
        }
        String str = (String) xVar.f22608i;
        String str2 = (String) xVar.f22609l;
        if (!kotlin.jvm.internal.p.a(LiveTvScreenKt.LiveTvScreen$lambda$68(this.$focusedChannelId$delegate), str)) {
            this.$focusedChannelId$delegate.setValue(str);
        }
        this.$epgPrefetchAnchorId$delegate.setValue(str);
        this.$rememberedChannelByCategory.put(str2, str);
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$commitFocusedChannel$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
