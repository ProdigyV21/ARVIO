package com.arflix.tv.ui.screens.search;

import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.MutableIntState;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchScreenKt$RowsLayer$1$1", f = "SearchScreen.kt", l = {939, 941}, m = "invokeSuspend", v = 2)
public final class SearchScreenKt$RowsLayer$1$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ long $fastScrollThresholdMs;
    final /* synthetic */ MutableIntState $lastAppliedTargetIndex$delegate;
    final /* synthetic */ long $lastNavEventTime;
    final /* synthetic */ LazyListState $listState;
    final /* synthetic */ int $targetIndex;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchScreenKt$RowsLayer$1$1(LazyListState lazyListState, int i10, long j10, long j11, MutableIntState mutableIntState, d7.d<? super SearchScreenKt$RowsLayer$1$1> dVar) {
        super(2, dVar);
        this.$listState = lazyListState;
        this.$targetIndex = i10;
        this.$lastNavEventTime = j10;
        this.$fastScrollThresholdMs = j11;
        this.$lastAppliedTargetIndex$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SearchScreenKt$RowsLayer$1$1(this.$listState, this.$targetIndex, this.$lastNavEventTime, this.$fastScrollThresholdMs, this.$lastAppliedTargetIndex$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x007a, code lost:
    
        if (androidx.compose.foundation.lazy.LazyListState.animateScrollToItem$default(r8, r9, 0, r14, 2, null) == r6) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0093, code lost:
    
        if (androidx.compose.foundation.lazy.LazyListState.scrollToItem$default(r7, r8, 0, r10, 2, null) == r6) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0095, code lost:
    
        return r6;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            int r0 = r14.label
            r1 = 2
            x6.t0 r2 = x6.t0.f22605a
            r3 = 1
            if (r0 == 0) goto L1b
            if (r0 == r3) goto L15
            if (r0 != r1) goto Ld
            goto L15
        Ld:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L15:
            k2.c.G(r15)
            r10 = r14
            goto L96
        L1b:
            k2.c.G(r15)
            androidx.compose.foundation.lazy.LazyListState r15 = r14.$listState
            int r15 = r15.getFirstVisibleItemIndex()
            androidx.compose.runtime.MutableIntState r0 = r14.$lastAppliedTargetIndex$delegate
            int r0 = com.arflix.tv.ui.screens.search.SearchScreenKt.access$RowsLayer$lambda$1(r0)
            r4 = 0
            if (r0 >= 0) goto L2f
            r0 = r3
            goto L30
        L2f:
            r0 = r4
        L30:
            int r5 = r14.$targetIndex
            if (r15 != r5) goto L3a
            androidx.compose.runtime.MutableIntState r15 = r14.$lastAppliedTargetIndex$delegate
            com.arflix.tv.ui.screens.search.SearchScreenKt.access$RowsLayer$lambda$2(r15, r5)
            return r2
        L3a:
            long r5 = r14.$lastNavEventTime
            r7 = 0
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 <= 0) goto L50
            long r5 = android.os.SystemClock.elapsedRealtime()
            long r7 = r14.$lastNavEventTime
            long r5 = r5 - r7
            long r7 = r14.$fastScrollThresholdMs
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 > 0) goto L50
            r4 = r3
        L50:
            if (r0 != 0) goto L55
            if (r4 != 0) goto L55
            return r2
        L55:
            int r5 = r14.$targetIndex
            int r5 = r5 - r15
            int r5 = java.lang.Math.abs(r5)
            e7.a r6 = e7.a.f15033i
            if (r0 != 0) goto L7d
            r7 = 5
            if (r5 > r7) goto L7d
            androidx.compose.foundation.lazy.LazyListState r8 = r14.$listState
            int r9 = r14.$targetIndex
            r14.I$0 = r15
            r14.I$1 = r0
            r14.I$2 = r4
            r14.I$3 = r5
            r14.label = r3
            r10 = 0
            r12 = 2
            r13 = 0
            r11 = r14
            java.lang.Object r15 = androidx.compose.foundation.lazy.LazyListState.animateScrollToItem$default(r8, r9, r10, r11, r12, r13)
            r10 = r11
            if (r15 != r6) goto L96
            goto L95
        L7d:
            r10 = r14
            androidx.compose.foundation.lazy.LazyListState r7 = r10.$listState
            int r8 = r10.$targetIndex
            r10.I$0 = r15
            r10.I$1 = r0
            r10.I$2 = r4
            r10.I$3 = r5
            r10.label = r1
            r9 = 0
            r11 = 2
            r12 = 0
            java.lang.Object r15 = androidx.compose.foundation.lazy.LazyListState.scrollToItem$default(r7, r8, r9, r10, r11, r12)
            if (r15 != r6) goto L96
        L95:
            return r6
        L96:
            androidx.compose.runtime.MutableIntState r15 = r10.$lastAppliedTargetIndex$delegate
            int r0 = r10.$targetIndex
            com.arflix.tv.ui.screens.search.SearchScreenKt.access$RowsLayer$lambda$2(r15, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.search.SearchScreenKt$RowsLayer$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((SearchScreenKt$RowsLayer$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
