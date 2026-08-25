package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$12$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$12$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ List<TvProviderFilter> $providerFilters;
    final /* synthetic */ MutableState<String> $selectedProviderId$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$12$1(List<TvProviderFilter> list, MutableState<String> mutableState, d7.d<? super LiveTvScreenKt$LiveTvScreen$12$1> dVar) {
        super(2, dVar);
        this.$providerFilters = list;
        this.$selectedProviderId$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$12$1(this.$providerFilters, this.$selectedProviderId$delegate, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r4) {
        /*
            r3 = this;
            int r0 = r3.label
            if (r0 != 0) goto L45
            k2.c.G(r4)
            java.util.List<com.arflix.tv.ui.screens.tv.live.TvProviderFilter> r4 = r3.$providerFilters
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L3b
            java.util.List<com.arflix.tv.ui.screens.tv.live.TvProviderFilter> r4 = r3.$providerFilters
            androidx.compose.runtime.MutableState<java.lang.String> r0 = r3.$selectedProviderId$delegate
            if (r4 == 0) goto L1c
            boolean r1 = r4.isEmpty()
            if (r1 == 0) goto L1c
            goto L3b
        L1c:
            java.util.Iterator r4 = r4.iterator()
        L20:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L3b
            java.lang.Object r1 = r4.next()
            com.arflix.tv.ui.screens.tv.live.TvProviderFilter r1 = (com.arflix.tv.ui.screens.tv.live.TvProviderFilter) r1
            java.lang.String r1 = r1.getId()
            java.lang.String r2 = com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.access$LiveTvScreen$lambda$16(r0)
            boolean r1 = kotlin.jvm.internal.p.a(r1, r2)
            if (r1 == 0) goto L20
            goto L42
        L3b:
            androidx.compose.runtime.MutableState<java.lang.String> r4 = r3.$selectedProviderId$delegate
            java.lang.String r0 = "all"
            com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.access$LiveTvScreen$lambda$17(r4, r0)
        L42:
            x6.t0 r4 = x6.t0.f22605a
            return r4
        L45:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$12$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$12$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
