package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsScreenKt$DetailsEpisodeRatingsRail$2$1", f = "DetailsScreen.kt", l = {2623, 2627}, m = "invokeSuspend", v = 2)
public final class DetailsScreenKt$DetailsEpisodeRatingsRail$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $leftChevronBump$delegate;
    final /* synthetic */ MutableIntState $previousRatingsIndex$delegate;
    final /* synthetic */ int $ratingsIndex;
    final /* synthetic */ MutableState<Boolean> $rightChevronBump$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsScreenKt$DetailsEpisodeRatingsRail$2$1(int i10, MutableIntState mutableIntState, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, d7.d<? super DetailsScreenKt$DetailsEpisodeRatingsRail$2$1> dVar) {
        super(2, dVar);
        this.$ratingsIndex = i10;
        this.$previousRatingsIndex$delegate = mutableIntState;
        this.$leftChevronBump$delegate = mutableState;
        this.$rightChevronBump$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new DetailsScreenKt$DetailsEpisodeRatingsRail$2$1(this.$ratingsIndex, this.$previousRatingsIndex$delegate, this.$leftChevronBump$delegate, this.$rightChevronBump$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0037, code lost:
    
        if (ka.s0.a(100, r7) == r6) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0055, code lost:
    
        if (ka.s0.a(100, r7) == r6) goto L20;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            int r0 = r7.label
            r1 = 0
            r2 = 2
            r3 = 1
            if (r0 == 0) goto L1b
            if (r0 == r3) goto L17
            if (r0 != r2) goto Lf
            k2.c.G(r8)
            goto L58
        Lf:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L17:
            k2.c.G(r8)
            goto L3a
        L1b:
            k2.c.G(r8)
            int r8 = r7.$ratingsIndex
            androidx.compose.runtime.MutableIntState r0 = r7.$previousRatingsIndex$delegate
            int r0 = com.arflix.tv.ui.screens.details.DetailsScreenKt.access$DetailsEpisodeRatingsRail_HXNGIdc$lambda$2(r0)
            r4 = 100
            e7.a r6 = e7.a.f15033i
            if (r8 >= r0) goto L40
            androidx.compose.runtime.MutableState<java.lang.Boolean> r8 = r7.$leftChevronBump$delegate
            com.arflix.tv.ui.screens.details.DetailsScreenKt.access$DetailsEpisodeRatingsRail_HXNGIdc$lambda$6(r8, r3)
            r7.label = r3
            java.lang.Object r8 = ka.s0.a(r4, r7)
            if (r8 != r6) goto L3a
            goto L57
        L3a:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r8 = r7.$leftChevronBump$delegate
            com.arflix.tv.ui.screens.details.DetailsScreenKt.access$DetailsEpisodeRatingsRail_HXNGIdc$lambda$6(r8, r1)
            goto L5d
        L40:
            int r8 = r7.$ratingsIndex
            androidx.compose.runtime.MutableIntState r0 = r7.$previousRatingsIndex$delegate
            int r0 = com.arflix.tv.ui.screens.details.DetailsScreenKt.access$DetailsEpisodeRatingsRail_HXNGIdc$lambda$2(r0)
            if (r8 <= r0) goto L5d
            androidx.compose.runtime.MutableState<java.lang.Boolean> r8 = r7.$rightChevronBump$delegate
            com.arflix.tv.ui.screens.details.DetailsScreenKt.access$DetailsEpisodeRatingsRail_HXNGIdc$lambda$9(r8, r3)
            r7.label = r2
            java.lang.Object r8 = ka.s0.a(r4, r7)
            if (r8 != r6) goto L58
        L57:
            return r6
        L58:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r8 = r7.$rightChevronBump$delegate
            com.arflix.tv.ui.screens.details.DetailsScreenKt.access$DetailsEpisodeRatingsRail_HXNGIdc$lambda$9(r8, r1)
        L5d:
            androidx.compose.runtime.MutableIntState r8 = r7.$previousRatingsIndex$delegate
            int r0 = r7.$ratingsIndex
            com.arflix.tv.ui.screens.details.DetailsScreenKt.access$DetailsEpisodeRatingsRail_HXNGIdc$lambda$3(r8, r0)
            x6.t0 r8 = x6.t0.f22605a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt$DetailsEpisodeRatingsRail$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((DetailsScreenKt$DetailsEpisodeRatingsRail$2$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
