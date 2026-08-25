package androidx.compose.foundation.pager;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayout;
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
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/pager/PagerMeasureResult;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "containerConstraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-0kLqBqw", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;J)Landroidx/compose/foundation/pager/PagerMeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 extends r implements p<LazyLayoutMeasureScope, Constraints, PagerMeasureResult> {
    final /* synthetic */ int $beyondBoundsPageCount;
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
    final /* synthetic */ r7.a<PagerLazyLayoutItemProvider> $itemProviderLambda;
    final /* synthetic */ Orientation $orientation;
    final /* synthetic */ r7.a<Integer> $pageCount;
    final /* synthetic */ PageSize $pageSize;
    final /* synthetic */ float $pageSpacing;
    final /* synthetic */ boolean $reverseLayout;
    final /* synthetic */ SnapPositionInLayout $snapPositionInLayout;
    final /* synthetic */ PagerState $state;
    final /* synthetic */ Alignment.Vertical $verticalAlignment;

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\n¢\u0006\u0004\b\t\u0010\n"}, d2 = {"", "width", "height", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "Lx6/n;", "placement", "Landroidx/compose/ui/layout/MeasureResult;", "invoke", "(IILr7/l;)Landroidx/compose/ui/layout/MeasureResult;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements q<Integer, Integer, l<? super Placeable.PlacementScope, ? extends t0>, MeasureResult> {
        final /* synthetic */ long $containerConstraints;
        final /* synthetic */ LazyLayoutMeasureScope $this_null;
        final /* synthetic */ int $totalHorizontalPadding;
        final /* synthetic */ int $totalVerticalPadding;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j10, int i10, int i11) {
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
    public PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(Orientation orientation, PaddingValues paddingValues, boolean z, PagerState pagerState, float f10, PageSize pageSize, r7.a<PagerLazyLayoutItemProvider> aVar, r7.a<Integer> aVar2, Alignment.Vertical vertical, Alignment.Horizontal horizontal, int i10, SnapPositionInLayout snapPositionInLayout) {
        super(2);
        this.$orientation = orientation;
        this.$contentPadding = paddingValues;
        this.$reverseLayout = z;
        this.$state = pagerState;
        this.$pageSpacing = f10;
        this.$pageSize = pageSize;
        this.$itemProviderLambda = aVar;
        this.$pageCount = aVar2;
        this.$verticalAlignment = vertical;
        this.$horizontalAlignment = horizontal;
        this.$beyondBoundsPageCount = i10;
        this.$snapPositionInLayout = snapPositionInLayout;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m762invoke0kLqBqw((LazyLayoutMeasureScope) obj, ((Constraints) obj2).getValue());
    }

    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
    public final PagerMeasureResult m762invoke0kLqBqw(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j10) {
        long jIntOffset;
        Orientation orientation = this.$orientation;
        Orientation orientation2 = Orientation.Vertical;
        boolean z = orientation == orientation2;
        CheckScrollableContainerConstraintsKt.m208checkScrollableContainerConstraintsK40F9xA(j10, z ? orientation2 : Orientation.Horizontal);
        int iMo279roundToPx0680j_4 = z ? lazyLayoutMeasureScope.mo279roundToPx0680j_4(this.$contentPadding.mo480calculateLeftPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection())) : lazyLayoutMeasureScope.mo279roundToPx0680j_4(PaddingKt.calculateStartPadding(this.$contentPadding, lazyLayoutMeasureScope.getLayoutDirection()));
        int iMo279roundToPx0680j_42 = z ? lazyLayoutMeasureScope.mo279roundToPx0680j_4(this.$contentPadding.mo481calculateRightPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection())) : lazyLayoutMeasureScope.mo279roundToPx0680j_4(PaddingKt.calculateEndPadding(this.$contentPadding, lazyLayoutMeasureScope.getLayoutDirection()));
        int iMo279roundToPx0680j_43 = lazyLayoutMeasureScope.mo279roundToPx0680j_4(this.$contentPadding.getTop());
        int iMo279roundToPx0680j_44 = lazyLayoutMeasureScope.mo279roundToPx0680j_4(this.$contentPadding.getBottom());
        int i10 = iMo279roundToPx0680j_42;
        int i11 = iMo279roundToPx0680j_43 + iMo279roundToPx0680j_44;
        int i12 = iMo279roundToPx0680j_4 + i10;
        int i13 = z ? i11 : i12;
        if (z && !this.$reverseLayout) {
            iMo279roundToPx0680j_44 = iMo279roundToPx0680j_43;
        } else if (!z || !this.$reverseLayout) {
            iMo279roundToPx0680j_44 = (z || this.$reverseLayout) ? i10 : iMo279roundToPx0680j_4;
        }
        int i14 = i13 - iMo279roundToPx0680j_44;
        long jM5662offsetNN6EwU = ConstraintsKt.m5662offsetNN6EwU(j10, -i12, -i11);
        this.$state.setDensity$foundation_release(lazyLayoutMeasureScope);
        int iMo279roundToPx0680j_45 = lazyLayoutMeasureScope.mo279roundToPx0680j_4(this.$pageSpacing);
        int iM5645getMaxHeightimpl = z ? Constraints.m5645getMaxHeightimpl(j10) - i11 : Constraints.m5646getMaxWidthimpl(j10) - i12;
        boolean z5 = z;
        if (!this.$reverseLayout || iM5645getMaxHeightimpl > 0) {
            jIntOffset = IntOffsetKt.IntOffset(iMo279roundToPx0680j_4, iMo279roundToPx0680j_43);
        } else {
            if (!z5) {
                iMo279roundToPx0680j_4 += iM5645getMaxHeightimpl;
            }
            if (z5) {
                iMo279roundToPx0680j_43 += iM5645getMaxHeightimpl;
            }
            jIntOffset = IntOffsetKt.IntOffset(iMo279roundToPx0680j_4, iMo279roundToPx0680j_43);
        }
        int iCalculateMainAxisPageSize = this.$pageSize.calculateMainAxisPageSize(lazyLayoutMeasureScope, iM5645getMaxHeightimpl, iMo279roundToPx0680j_45);
        this.$state.m766setPremeasureConstraintsBRTryo0$foundation_release(ConstraintsKt.Constraints$default(0, this.$orientation == orientation2 ? Constraints.m5646getMaxWidthimpl(jM5662offsetNN6EwU) : iCalculateMainAxisPageSize, 0, this.$orientation != orientation2 ? Constraints.m5645getMaxHeightimpl(jM5662offsetNN6EwU) : iCalculateMainAxisPageSize, 5, null));
        PagerLazyLayoutItemProvider pagerLazyLayoutItemProvider = (PagerLazyLayoutItemProvider) this.$itemProviderLambda.invoke();
        int i15 = iCalculateMainAxisPageSize + iMo279roundToPx0680j_45;
        Snapshot.Companion companion = Snapshot.INSTANCE;
        PagerState pagerState = this.$state;
        Snapshot snapshotCreateNonObservableSnapshot = companion.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                int iMatchScrollPositionWithKey$foundation_release = pagerState.matchScrollPositionWithKey$foundation_release(pagerLazyLayoutItemProvider, pagerState.getCurrentPage());
                int iCalculateCurrentPageLayoutOffset = PagerMeasurePolicyKt.calculateCurrentPageLayoutOffset(pagerState, i15);
                snapshotCreateNonObservableSnapshot.dispose();
                int i16 = iMo279roundToPx0680j_44;
                long j11 = jIntOffset;
                PagerMeasureResult pagerMeasureResultM760measurePager_JDW0YA = PagerMeasureKt.m760measurePager_JDW0YA(lazyLayoutMeasureScope, ((Number) this.$pageCount.invoke()).intValue(), pagerLazyLayoutItemProvider, iM5645getMaxHeightimpl, i16, i14, iMo279roundToPx0680j_45, iMatchScrollPositionWithKey$foundation_release, iCalculateCurrentPageLayoutOffset, jM5662offsetNN6EwU, this.$orientation, this.$verticalAlignment, this.$horizontalAlignment, this.$reverseLayout, j11, iCalculateMainAxisPageSize, this.$beyondBoundsPageCount, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(pagerLazyLayoutItemProvider, this.$state.getPinnedPages(), this.$state.getBeyondBoundsInfo()), this.$snapPositionInLayout, this.$state.m763getPlacementScopeInvalidatorzYiylxw$foundation_release(), new AnonymousClass2(lazyLayoutMeasureScope, j10, i12, i11));
                PagerState.applyMeasureResult$foundation_release$default(this.$state, pagerMeasureResultM760measurePager_JDW0YA, false, 2, null);
                return pagerMeasureResultM760measurePager_JDW0YA;
            } finally {
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
            }
        } catch (Throwable th) {
            snapshotCreateNonObservableSnapshot.dispose();
            throw th;
        }
    }
}
