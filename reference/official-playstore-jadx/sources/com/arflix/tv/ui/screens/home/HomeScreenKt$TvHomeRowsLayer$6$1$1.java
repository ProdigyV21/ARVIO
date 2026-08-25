package com.arflix.tv.ui.screens.home;

import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$TvHomeRowsLayer$6$1$1", f = "HomeScreen.kt", l = {3110, 3130, 3138, 3141}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$TvHomeRowsLayer$6$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ List<Float> $categoryHeightsPx;
    final /* synthetic */ Density $density;
    final /* synthetic */ long $fastScrollThresholdMs;
    final /* synthetic */ HomeFocusState $focusState;
    final /* synthetic */ MutableIntState $lastAppliedTargetIndex$delegate;
    final /* synthetic */ LazyListState $listState;
    final /* synthetic */ boolean $smoothScrolling;
    final /* synthetic */ int $targetIndex;
    float F$0;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$TvHomeRowsLayer$6$1$1(LazyListState lazyListState, int i10, HomeFocusState homeFocusState, long j10, boolean z, MutableIntState mutableIntState, List<Float> list, Density density, d7.d<? super HomeScreenKt$TvHomeRowsLayer$6$1$1> dVar) {
        super(2, dVar);
        this.$listState = lazyListState;
        this.$targetIndex = i10;
        this.$focusState = homeFocusState;
        this.$fastScrollThresholdMs = j10;
        this.$smoothScrolling = z;
        this.$lastAppliedTargetIndex$delegate = mutableIntState;
        this.$categoryHeightsPx = list;
        this.$density = density;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$TvHomeRowsLayer$6$1$1(this.$listState, this.$targetIndex, this.$focusState, this.$fastScrollThresholdMs, this.$smoothScrolling, this.$lastAppliedTargetIndex$delegate, this.$categoryHeightsPx, this.$density, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:85:0x01c7, code lost:
    
        if (r7.scrollToItem(r8, 0, r22) != r2) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01e3, code lost:
    
        if (r0.animateScrollToItem(r3, 0, r22) == r2) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01fb, code lost:
    
        if (r0.scrollToItem(r1, 0, r22) != r2) goto L93;
     */
    /* JADX WARN: Removed duplicated region for block: B:73:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0191  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r23) {
        /*
            Method dump skipped, instruction units count: 518
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt$TvHomeRowsLayer$6$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$TvHomeRowsLayer$6$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
