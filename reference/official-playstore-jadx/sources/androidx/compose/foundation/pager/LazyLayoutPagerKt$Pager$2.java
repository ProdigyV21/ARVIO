package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutPagerKt$Pager$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ int $$default;
    final /* synthetic */ int $beyondBoundsPageCount;
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ SnapFlingBehavior $flingBehavior;
    final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
    final /* synthetic */ l<Integer, Object> $key;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ Orientation $orientation;
    final /* synthetic */ r7.r<PagerScope, Integer, Composer, Integer, t0> $pageContent;
    final /* synthetic */ NestedScrollConnection $pageNestedScrollConnection;
    final /* synthetic */ PageSize $pageSize;
    final /* synthetic */ float $pageSpacing;
    final /* synthetic */ boolean $reverseLayout;
    final /* synthetic */ PagerState $state;
    final /* synthetic */ boolean $userScrollEnabled;
    final /* synthetic */ Alignment.Vertical $verticalAlignment;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LazyLayoutPagerKt$Pager$2(Modifier modifier, PagerState pagerState, PaddingValues paddingValues, boolean z, Orientation orientation, SnapFlingBehavior snapFlingBehavior, boolean z5, int i10, float f10, PageSize pageSize, NestedScrollConnection nestedScrollConnection, l<? super Integer, ? extends Object> lVar, Alignment.Horizontal horizontal, Alignment.Vertical vertical, r7.r<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, t0> rVar, int i11, int i12, int i13) {
        super(2);
        this.$modifier = modifier;
        this.$state = pagerState;
        this.$contentPadding = paddingValues;
        this.$reverseLayout = z;
        this.$orientation = orientation;
        this.$flingBehavior = snapFlingBehavior;
        this.$userScrollEnabled = z5;
        this.$beyondBoundsPageCount = i10;
        this.$pageSpacing = f10;
        this.$pageSize = pageSize;
        this.$pageNestedScrollConnection = nestedScrollConnection;
        this.$key = lVar;
        this.$horizontalAlignment = horizontal;
        this.$verticalAlignment = vertical;
        this.$pageContent = rVar;
        this.$$changed = i11;
        this.$$changed1 = i12;
        this.$$default = i13;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        LazyLayoutPagerKt.m750Pagerfs30GE4(this.$modifier, this.$state, this.$contentPadding, this.$reverseLayout, this.$orientation, this.$flingBehavior, this.$userScrollEnabled, this.$beyondBoundsPageCount, this.$pageSpacing, this.$pageSize, this.$pageNestedScrollConnection, this.$key, this.$horizontalAlignment, this.$verticalAlignment, this.$pageContent, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
    }
}
