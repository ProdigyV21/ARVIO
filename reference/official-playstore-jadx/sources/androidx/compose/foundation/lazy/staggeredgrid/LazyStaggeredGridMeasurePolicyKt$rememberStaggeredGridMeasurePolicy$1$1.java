package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffsetKt;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-0kLqBqw", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;J)Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1 extends r implements p<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> {
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ k0 $coroutineScope;
    final /* synthetic */ r7.a<LazyStaggeredGridItemProvider> $itemProviderLambda;
    final /* synthetic */ float $mainAxisSpacing;
    final /* synthetic */ Orientation $orientation;
    final /* synthetic */ boolean $reverseLayout;
    final /* synthetic */ LazyGridStaggeredGridSlotsProvider $slots;
    final /* synthetic */ LazyStaggeredGridState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1(Orientation orientation, LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider, r7.a<? extends LazyStaggeredGridItemProvider> aVar, LazyStaggeredGridState lazyStaggeredGridState, PaddingValues paddingValues, boolean z, float f10, k0 k0Var) {
        super(2);
        this.$orientation = orientation;
        this.$slots = lazyGridStaggeredGridSlotsProvider;
        this.$itemProviderLambda = aVar;
        this.$state = lazyStaggeredGridState;
        this.$contentPadding = paddingValues;
        this.$reverseLayout = z;
        this.$mainAxisSpacing = f10;
        this.$coroutineScope = k0Var;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m730invoke0kLqBqw((LazyLayoutMeasureScope) obj, ((Constraints) obj2).getValue());
    }

    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
    public final LazyStaggeredGridMeasureResult m730invoke0kLqBqw(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j10) {
        CheckScrollableContainerConstraintsKt.m208checkScrollableContainerConstraintsK40F9xA(j10, this.$orientation);
        LazyStaggeredGridSlots lazyStaggeredGridSlotsMo712invoke0kLqBqw = this.$slots.mo712invoke0kLqBqw(lazyLayoutMeasureScope, j10);
        boolean z = this.$orientation == Orientation.Vertical;
        LazyStaggeredGridItemProvider lazyStaggeredGridItemProvider = (LazyStaggeredGridItemProvider) this.$itemProviderLambda.invoke();
        this.$state.setSlots$foundation_release(lazyStaggeredGridSlotsMo712invoke0kLqBqw);
        this.$state.setVertical$foundation_release(z);
        this.$state.setSpanProvider$foundation_release(lazyStaggeredGridItemProvider.getSpanProvider());
        int iMo279roundToPx0680j_4 = lazyLayoutMeasureScope.mo279roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(this.$contentPadding, this.$orientation, this.$reverseLayout, lazyLayoutMeasureScope.getLayoutDirection()));
        int iMo279roundToPx0680j_42 = lazyLayoutMeasureScope.mo279roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(this.$contentPadding, this.$orientation, this.$reverseLayout, lazyLayoutMeasureScope.getLayoutDirection()));
        int iMo279roundToPx0680j_43 = lazyLayoutMeasureScope.mo279roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(this.$contentPadding, this.$orientation, lazyLayoutMeasureScope.getLayoutDirection()));
        int iM5645getMaxHeightimpl = ((z ? Constraints.m5645getMaxHeightimpl(j10) : Constraints.m5646getMaxWidthimpl(j10)) - iMo279roundToPx0680j_4) - iMo279roundToPx0680j_42;
        long jIntOffset = z ? IntOffsetKt.IntOffset(iMo279roundToPx0680j_43, iMo279roundToPx0680j_4) : IntOffsetKt.IntOffset(iMo279roundToPx0680j_4, iMo279roundToPx0680j_43);
        PaddingValues paddingValues = this.$contentPadding;
        int iMo279roundToPx0680j_44 = lazyLayoutMeasureScope.mo279roundToPx0680j_4(Dp.m5678constructorimpl(PaddingKt.calculateEndPadding(paddingValues, lazyLayoutMeasureScope.getLayoutDirection()) + PaddingKt.calculateStartPadding(paddingValues, lazyLayoutMeasureScope.getLayoutDirection())));
        PaddingValues paddingValues2 = this.$contentPadding;
        boolean z5 = z;
        LazyStaggeredGridMeasureResult lazyStaggeredGridMeasureResultM728measureStaggeredGridsdzDtKU = LazyStaggeredGridMeasureKt.m728measureStaggeredGridsdzDtKU(lazyLayoutMeasureScope, this.$state, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(lazyStaggeredGridItemProvider, this.$state.getPinnedItems(), this.$state.getBeyondBoundsInfo()), lazyStaggeredGridItemProvider, lazyStaggeredGridSlotsMo712invoke0kLqBqw, Constraints.m5637copyZbe2FdA$default(j10, ConstraintsKt.m5660constrainWidthK40F9xA(j10, iMo279roundToPx0680j_44), 0, ConstraintsKt.m5659constrainHeightK40F9xA(j10, lazyLayoutMeasureScope.mo279roundToPx0680j_4(Dp.m5678constructorimpl(paddingValues2.getBottom() + paddingValues2.getTop()))), 0, 10, null), z5, this.$reverseLayout, jIntOffset, iM5645getMaxHeightimpl, lazyLayoutMeasureScope.mo279roundToPx0680j_4(this.$mainAxisSpacing), iMo279roundToPx0680j_4, iMo279roundToPx0680j_42, this.$coroutineScope);
        LazyStaggeredGridState.applyMeasureResult$foundation_release$default(this.$state, lazyStaggeredGridMeasureResultM728measureStaggeredGridsdzDtKU, false, 2, null);
        return lazyStaggeredGridMeasureResultM728measureStaggeredGridsdzDtKU;
    }
}
