package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import java.io.PrintStream;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$25$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$25$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ List<EnrichedChannel> $filteredChannels;
    final /* synthetic */ List<EnrichedChannel> $guideChannels;
    final /* synthetic */ int $normalizedGuideStart;
    final /* synthetic */ MutableState<String> $selectedCategoryId$delegate;
    final /* synthetic */ int $selectedCategoryTotalCount;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$25$1(List<EnrichedChannel> list, int i10, List<EnrichedChannel> list2, int i11, MutableState<String> mutableState, d7.d<? super LiveTvScreenKt$LiveTvScreen$25$1> dVar) {
        super(2, dVar);
        this.$filteredChannels = list;
        this.$selectedCategoryTotalCount = i10;
        this.$guideChannels = list2;
        this.$normalizedGuideStart = i11;
        this.$selectedCategoryId$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$25$1(this.$filteredChannels, this.$selectedCategoryTotalCount, this.$guideChannels, this.$normalizedGuideStart, this.$selectedCategoryId$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (!this.$filteredChannels.isEmpty()) {
            PrintStream printStream = System.err;
            String strLiveTvScreen$lambda$13 = LiveTvScreenKt.LiveTvScreen$lambda$13(this.$selectedCategoryId$delegate);
            int size = this.$filteredChannels.size();
            int i10 = this.$selectedCategoryTotalCount;
            int size2 = this.$guideChannels.size();
            int i11 = this.$normalizedGuideStart;
            StringBuilder sbQ = androidx.fragment.app.a2.q("[TV-Metrics] category=", strLiveTvScreen$lambda$13, " loaded=", size, DomExceptionUtils.SEPARATOR);
            androidx.fragment.app.a2.y(sbQ, i10, " guideWindow=", size2, " start=");
            sbQ.append(i11);
            printStream.println(sbQ.toString());
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$25$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
