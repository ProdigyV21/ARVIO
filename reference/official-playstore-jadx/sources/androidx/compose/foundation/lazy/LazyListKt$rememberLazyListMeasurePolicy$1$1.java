package androidx.compose.foundation.lazy;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffsetKt;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "containerConstraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-0kLqBqw", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;J)Landroidx/compose/foundation/lazy/LazyListMeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyListKt$rememberLazyListMeasurePolicy$1$1 extends r implements p<LazyLayoutMeasureScope, Constraints, LazyListMeasureResult> {
    final /* synthetic */ int $beyondBoundsItemCount;
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
    final /* synthetic */ Arrangement.Horizontal $horizontalArrangement;
    final /* synthetic */ boolean $isVertical;
    final /* synthetic */ r7.a<LazyListItemProvider> $itemProviderLambda;
    final /* synthetic */ boolean $reverseLayout;
    final /* synthetic */ LazyListState $state;
    final /* synthetic */ Alignment.Vertical $verticalAlignment;
    final /* synthetic */ Arrangement.Vertical $verticalArrangement;

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyListKt$rememberLazyListMeasurePolicy$1$1$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\n¢\u0006\u0004\b\t\u0010\n"}, d2 = {"", "width", "height", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "Lx6/n;", "placement", "Landroidx/compose/ui/layout/MeasureResult;", "invoke", "(IILr7/l;)Landroidx/compose/ui/layout/MeasureResult;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements q<Integer, Integer, l<? super Placeable.PlacementScope, ? extends t0>, MeasureResult> {
        final /* synthetic */ long $containerConstraints;
        final /* synthetic */ LazyLayoutMeasureScope $this_null;
        final /* synthetic */ int $totalHorizontalPadding;
        final /* synthetic */ int $totalVerticalPadding;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j10, int i10, int i11) {
            super(3);
            this.$this_null = lazyLayoutMeasureScope;
            this.$containerConstraints = j10;
            this.$totalHorizontalPadding = i10;
            this.$totalVerticalPadding = i11;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke(((Number) obj).intValue(), ((Number) obj2).intValue(), (l<? super Placeable.PlacementScope, t0>) obj3);
        }

        public final MeasureResult invoke(int i10, int i11, l<? super Placeable.PlacementScope, t0> lVar) {
            return this.$this_null.layout(ConstraintsKt.m5660constrainWidthK40F9xA(this.$containerConstraints, i10 + this.$totalHorizontalPadding), ConstraintsKt.m5659constrainHeightK40F9xA(this.$containerConstraints, i11 + this.$totalVerticalPadding), a0.f19683i, lVar);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LazyListKt$rememberLazyListMeasurePolicy$1$1(LazyListState lazyListState, boolean z, PaddingValues paddingValues, boolean z5, r7.a<? extends LazyListItemProvider> aVar, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, int i10, Alignment.Horizontal horizontal2, Alignment.Vertical vertical2) {
        super(2);
        this.$state = lazyListState;
        this.$isVertical = z;
        this.$contentPadding = paddingValues;
        this.$reverseLayout = z5;
        this.$itemProviderLambda = aVar;
        this.$verticalArrangement = vertical;
        this.$horizontalArrangement = horizontal;
        this.$beyondBoundsItemCount = i10;
        this.$horizontalAlignment = horizontal2;
        this.$verticalAlignment = vertical2;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m633invoke0kLqBqw((LazyLayoutMeasureScope) obj, ((Constraints) obj2).getValue());
    }

    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
    public final LazyListMeasureResult m633invoke0kLqBqw(final LazyLayoutMeasureScope lazyLayoutMeasureScope, long j10) {
        float spacing;
        final long jIntOffset;
        boolean z = this.$state.getHasLookaheadPassOccurred() || lazyLayoutMeasureScope.isLookingAhead();
        CheckScrollableContainerConstraintsKt.m208checkScrollableContainerConstraintsK40F9xA(j10, this.$isVertical ? Orientation.Vertical : Orientation.Horizontal);
        int iMo279roundToPx0680j_4 = this.$isVertical ? lazyLayoutMeasureScope.mo279roundToPx0680j_4(this.$contentPadding.mo480calculateLeftPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection())) : lazyLayoutMeasureScope.mo279roundToPx0680j_4(PaddingKt.calculateStartPadding(this.$contentPadding, lazyLayoutMeasureScope.getLayoutDirection()));
        int iMo279roundToPx0680j_42 = this.$isVertical ? lazyLayoutMeasureScope.mo279roundToPx0680j_4(this.$contentPadding.mo481calculateRightPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection())) : lazyLayoutMeasureScope.mo279roundToPx0680j_4(PaddingKt.calculateEndPadding(this.$contentPadding, lazyLayoutMeasureScope.getLayoutDirection()));
        int iMo279roundToPx0680j_43 = lazyLayoutMeasureScope.mo279roundToPx0680j_4(this.$contentPadding.getTop());
        int iMo279roundToPx0680j_44 = lazyLayoutMeasureScope.mo279roundToPx0680j_4(this.$contentPadding.getBottom());
        int i10 = iMo279roundToPx0680j_43 + iMo279roundToPx0680j_44;
        int i11 = iMo279roundToPx0680j_4 + iMo279roundToPx0680j_42;
        boolean z5 = this.$isVertical;
        int i12 = z5 ? i10 : i11;
        final int i13 = (!z5 || this.$reverseLayout) ? (z5 && this.$reverseLayout) ? iMo279roundToPx0680j_44 : (z5 || this.$reverseLayout) ? iMo279roundToPx0680j_42 : iMo279roundToPx0680j_4 : iMo279roundToPx0680j_43;
        final int i14 = i12 - i13;
        final long jM5662offsetNN6EwU = ConstraintsKt.m5662offsetNN6EwU(j10, -i11, -i10);
        this.$state.setDensity$foundation_release(lazyLayoutMeasureScope);
        final LazyListItemProvider lazyListItemProvider = (LazyListItemProvider) this.$itemProviderLambda.invoke();
        lazyListItemProvider.getItemScope().setMaxSize(Constraints.m5646getMaxWidthimpl(jM5662offsetNN6EwU), Constraints.m5645getMaxHeightimpl(jM5662offsetNN6EwU));
        if (this.$isVertical) {
            Arrangement.Vertical vertical = this.$verticalArrangement;
            if (vertical == null) {
                throw new IllegalArgumentException("null verticalArrangement when isVertical == true");
            }
            spacing = vertical.getSpacing();
        } else {
            Arrangement.Horizontal horizontal = this.$horizontalArrangement;
            if (horizontal == null) {
                throw new IllegalArgumentException("null horizontalAlignment when isVertical == false");
            }
            spacing = horizontal.getSpacing();
        }
        final int iMo279roundToPx0680j_45 = lazyLayoutMeasureScope.mo279roundToPx0680j_4(spacing);
        final int itemCount = lazyListItemProvider.getItemCount();
        int iM5645getMaxHeightimpl = this.$isVertical ? Constraints.m5645getMaxHeightimpl(j10) - i10 : Constraints.m5646getMaxWidthimpl(j10) - i11;
        if (!this.$reverseLayout || iM5645getMaxHeightimpl > 0) {
            jIntOffset = IntOffsetKt.IntOffset(iMo279roundToPx0680j_4, iMo279roundToPx0680j_43);
        } else {
            boolean z10 = this.$isVertical;
            if (!z10) {
                iMo279roundToPx0680j_4 += iM5645getMaxHeightimpl;
            }
            if (z10) {
                iMo279roundToPx0680j_43 += iM5645getMaxHeightimpl;
            }
            jIntOffset = IntOffsetKt.IntOffset(iMo279roundToPx0680j_4, iMo279roundToPx0680j_43);
        }
        final boolean z11 = this.$isVertical;
        final Alignment.Horizontal horizontal2 = this.$horizontalAlignment;
        final Alignment.Vertical vertical2 = this.$verticalAlignment;
        final boolean z12 = this.$reverseLayout;
        final LazyListState lazyListState = this.$state;
        LazyListMeasuredItemProvider lazyListMeasuredItemProvider = new LazyListMeasuredItemProvider(jM5662offsetNN6EwU, z11, lazyListItemProvider, lazyLayoutMeasureScope, itemCount, iMo279roundToPx0680j_45, horizontal2, vertical2, z12, i13, i14, jIntOffset, lazyListState) { // from class: androidx.compose.foundation.lazy.LazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1
            final /* synthetic */ int $afterContentPadding;
            final /* synthetic */ int $beforeContentPadding;
            final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
            final /* synthetic */ boolean $isVertical;
            final /* synthetic */ int $itemsCount;
            final /* synthetic */ boolean $reverseLayout;
            final /* synthetic */ int $spaceBetweenItems;
            final /* synthetic */ LazyListState $state;
            final /* synthetic */ LazyLayoutMeasureScope $this_null;
            final /* synthetic */ Alignment.Vertical $verticalAlignment;
            final /* synthetic */ long $visualItemOffset;

            {
                this.$isVertical = z11;
                this.$this_null = lazyLayoutMeasureScope;
                this.$itemsCount = itemCount;
                this.$spaceBetweenItems = iMo279roundToPx0680j_45;
                this.$horizontalAlignment = horizontal2;
                this.$verticalAlignment = vertical2;
                this.$reverseLayout = z12;
                this.$beforeContentPadding = i13;
                this.$afterContentPadding = i14;
                this.$visualItemOffset = jIntOffset;
                this.$state = lazyListState;
            }

            @Override // androidx.compose.foundation.lazy.LazyListMeasuredItemProvider
            public LazyListMeasuredItem createItem(int index, Object key, Object contentType, List<? extends Placeable> placeables) {
                return new LazyListMeasuredItem(index, placeables, this.$isVertical, this.$horizontalAlignment, this.$verticalAlignment, this.$this_null.getLayoutDirection(), this.$reverseLayout, this.$beforeContentPadding, this.$afterContentPadding, index == this.$itemsCount + (-1) ? 0 : this.$spaceBetweenItems, this.$visualItemOffset, key, contentType, this.$state.getItemAnimator(), null);
            }
        };
        this.$state.m643setPremeasureConstraintsBRTryo0$foundation_release(lazyListMeasuredItemProvider.getChildConstraints());
        Snapshot.Companion companion = Snapshot.INSTANCE;
        LazyListState lazyListState2 = this.$state;
        Snapshot snapshotCreateNonObservableSnapshot = companion.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                int iUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release = lazyListState2.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(lazyListItemProvider, lazyListState2.getFirstVisibleItemIndex());
                int firstVisibleItemScrollOffset = lazyListState2.getFirstVisibleItemScrollOffset();
                snapshotCreateNonObservableSnapshot.dispose();
                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(lazyListItemProvider, this.$state.getPinnedItems(), this.$state.getBeyondBoundsInfo());
                float scrollToBeConsumed = (lazyLayoutMeasureScope.isLookingAhead() || !z) ? this.$state.getScrollToBeConsumed() : this.$state.getScrollDeltaBetweenPasses$foundation_release();
                int i15 = i13;
                boolean z13 = this.$isVertical;
                List<Integer> headerIndexes = lazyListItemProvider.getHeaderIndexes();
                Arrangement.Vertical vertical3 = this.$verticalArrangement;
                Arrangement.Horizontal horizontal3 = this.$horizontalArrangement;
                boolean z14 = this.$reverseLayout;
                LazyListItemAnimator itemAnimator = this.$state.getItemAnimator();
                int i16 = this.$beyondBoundsItemCount;
                boolean zIsLookingAhead = lazyLayoutMeasureScope.isLookingAhead();
                LazyListMeasureResult postLookaheadLayoutInfo = this.$state.getPostLookaheadLayoutInfo();
                k0 coroutineScope = this.$state.getCoroutineScope();
                if (coroutineScope == null) {
                    throw new IllegalArgumentException("coroutineScope should be not null");
                }
                LazyListMeasureResult lazyListMeasureResultM636measureLazyList5IMabDg = LazyListMeasureKt.m636measureLazyList5IMabDg(itemCount, lazyListMeasuredItemProvider, iM5645getMaxHeightimpl, i15, i14, iMo279roundToPx0680j_45, iUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release, firstVisibleItemScrollOffset, scrollToBeConsumed, jM5662offsetNN6EwU, z13, headerIndexes, vertical3, horizontal3, z14, lazyLayoutMeasureScope, itemAnimator, i16, listCalculateLazyLayoutPinnedIndices, z, zIsLookingAhead, postLookaheadLayoutInfo, coroutineScope, this.$state.m641getPlacementScopeInvalidatorzYiylxw$foundation_release(), new AnonymousClass3(lazyLayoutMeasureScope, j10, i11, i10));
                LazyListState.applyMeasureResult$foundation_release$default(this.$state, lazyListMeasureResultM636measureLazyList5IMabDg, lazyLayoutMeasureScope.isLookingAhead(), false, 4, null);
                return lazyListMeasureResultM636measureLazyList5IMabDg;
            } finally {
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
            }
        } catch (Throwable th) {
            snapshotCreateNonObservableSnapshot.dispose();
            throw th;
        }
    }
}
