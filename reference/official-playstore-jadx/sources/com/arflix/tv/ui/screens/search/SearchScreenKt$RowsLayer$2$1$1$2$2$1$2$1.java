package com.arflix.tv.ui.screens.search;

import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.unit.Density;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.arflix.tv.data.model.Category;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchScreenKt$RowsLayer$2$1$1$2$2$1$2$1", f = "SearchScreen.kt", l = {AnalyticsListener.EVENT_VIDEO_DECODER_RELEASED, AnalyticsListener.EVENT_AUDIO_TRACK_INITIALIZED, AnalyticsListener.EVENT_RENDERER_READY_CHANGED, 1035}, m = "invokeSuspend", v = 2)
public final class SearchScreenKt$RowsLayer$2$1$1$2$2$1$2$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Category $category;
    final /* synthetic */ int $currentItemIndex;
    final /* synthetic */ Density $density;
    final /* synthetic */ long $fastScrollThresholdMs;
    final /* synthetic */ boolean $isCurrentRow;
    final /* synthetic */ float $itemWidth;
    final /* synthetic */ long $lastNavEventTime;
    final /* synthetic */ MutableIntState $lastScrollIndex$delegate;
    final /* synthetic */ MutableIntState $lastScrollOffset$delegate;
    final /* synthetic */ LazyListState $rowState;
    int I$0;
    int I$1;
    int I$10;
    int I$2;
    int I$3;
    int I$4;
    int I$5;
    int I$6;
    int I$7;
    int I$8;
    int I$9;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchScreenKt$RowsLayer$2$1$1$2$2$1$2$1(boolean z, int i10, Category category, LazyListState lazyListState, Density density, long j10, long j11, float f10, MutableIntState mutableIntState, MutableIntState mutableIntState2, d7.d<? super SearchScreenKt$RowsLayer$2$1$1$2$2$1$2$1> dVar) {
        super(2, dVar);
        this.$isCurrentRow = z;
        this.$currentItemIndex = i10;
        this.$category = category;
        this.$rowState = lazyListState;
        this.$density = density;
        this.$lastNavEventTime = j10;
        this.$fastScrollThresholdMs = j11;
        this.$itemWidth = f10;
        this.$lastScrollIndex$delegate = mutableIntState;
        this.$lastScrollOffset$delegate = mutableIntState2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SearchScreenKt$RowsLayer$2$1$1$2$2$1$2$1(this.$isCurrentRow, this.$currentItemIndex, this.$category, this.$rowState, this.$density, this.$lastNavEventTime, this.$fastScrollThresholdMs, this.$itemWidth, this.$lastScrollIndex$delegate, this.$lastScrollOffset$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x0170, code lost:
    
        if (r5.scrollToItem(r1, r8, r18) == r2) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0173, code lost:
    
        r2 = r1;
        r1 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01a0, code lost:
    
        if (r5.scrollToItem(r1, r8, r18) == r2) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01c6, code lost:
    
        if (r5.animateScrollToItem(r1, r8, r18) == r2) goto L82;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0141 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0142  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 468
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.search.SearchScreenKt$RowsLayer$2$1$1$2$2$1$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((SearchScreenKt$RowsLayer$2$1$1$2$2$1$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
