package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.State;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsScreenKt$HomeStyleRowAutoScroll$2$1", f = "DetailsScreen.kt", l = {3328, 3336, 3347, 3350, 3358}, m = "invokeSuspend", v = 2)
public final class DetailsScreenKt$HomeStyleRowAutoScroll$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ int $focusedItemIndex;
    final /* synthetic */ boolean $isCurrentRow;
    final /* synthetic */ float $itemSpanPx;
    final /* synthetic */ MutableIntState $lastScrollIndex$delegate;
    final /* synthetic */ MutableIntState $lastScrollOffset$delegate;
    final /* synthetic */ androidx.tv.foundation.lazy.list.m0 $rowState;
    final /* synthetic */ State<Integer> $scrollTargetIndex$delegate;
    float F$0;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    int I$5;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsScreenKt$HomeStyleRowAutoScroll$2$1(boolean z, int i10, androidx.tv.foundation.lazy.list.m0 m0Var, float f10, State<Integer> state, MutableIntState mutableIntState, MutableIntState mutableIntState2, d7.d<? super DetailsScreenKt$HomeStyleRowAutoScroll$2$1> dVar) {
        super(2, dVar);
        this.$isCurrentRow = z;
        this.$focusedItemIndex = i10;
        this.$rowState = m0Var;
        this.$itemSpanPx = f10;
        this.$scrollTargetIndex$delegate = state;
        this.$lastScrollIndex$delegate = mutableIntState;
        this.$lastScrollOffset$delegate = mutableIntState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new DetailsScreenKt$HomeStyleRowAutoScroll$2$1(this.$isCurrentRow, this.$focusedItemIndex, this.$rowState, this.$itemSpanPx, this.$scrollTargetIndex$delegate, this.$lastScrollIndex$delegate, this.$lastScrollOffset$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0076, code lost:
    
        if (r14.d(0, 0, r13) == r8) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b6, code lost:
    
        if (r14.d(r0, 0, r13) == r8) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x011f, code lost:
    
        if (r1.d(r2, 0, r13) == r8) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0199, code lost:
    
        if (r9.d(r10, r7, r13) != r8) goto L75;
     */
    /* JADX WARN: Removed duplicated region for block: B:72:0x017d  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 429
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt$HomeStyleRowAutoScroll$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((DetailsScreenKt$HomeStyleRowAutoScroll$2$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
