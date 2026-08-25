package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.grid.LazyGridSpanLayoutProvider;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffsetKt;
import java.util.ArrayList;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "containerConstraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-0kLqBqw", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;J)Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyGridKt$rememberLazyGridMeasurePolicy$1$1 extends r implements p<LazyLayoutMeasureScope, Constraints, LazyGridMeasureResult> {
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ k0 $coroutineScope;
    final /* synthetic */ Arrangement.Horizontal $horizontalArrangement;
    final /* synthetic */ boolean $isVertical;
    final /* synthetic */ r7.a<LazyGridItemProvider> $itemProviderLambda;
    final /* synthetic */ boolean $reverseLayout;
    final /* synthetic */ LazyGridSlotsProvider $slots;
    final /* synthetic */ LazyGridState $state;
    final /* synthetic */ Arrangement.Vertical $verticalArrangement;

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u00030\u0002j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003`\u00052\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "line", "Ljava/util/ArrayList;", "Lx6/x;", "Landroidx/compose/ui/unit/Constraints;", "Lkotlin/collections/ArrayList;", "invoke", "(I)Ljava/util/ArrayList;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Integer, ArrayList<x>> {
        final /* synthetic */ LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1 $measuredLineProvider;
        final /* synthetic */ LazyGridSpanLayoutProvider $spanLayoutProvider;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LazyGridSpanLayoutProvider lazyGridSpanLayoutProvider, LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1 lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1) {
            super(1);
            this.$spanLayoutProvider = lazyGridSpanLayoutProvider;
            this.$measuredLineProvider = lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final ArrayList<x> invoke(int i10) {
            LazyGridSpanLayoutProvider.LineConfiguration lineConfiguration = this.$spanLayoutProvider.getLineConfiguration(i10);
            int firstItemIndex = lineConfiguration.getFirstItemIndex();
            ArrayList<x> arrayList = new ArrayList<>(lineConfiguration.getSpans().size());
            List<GridItemSpan> spans = lineConfiguration.getSpans();
            LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1 lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1 = this.$measuredLineProvider;
            int size = spans.size();
            int i11 = 0;
            for (int i12 = 0; i12 < size; i12++) {
                int iM648getCurrentLineSpanimpl = GridItemSpan.m648getCurrentLineSpanimpl(spans.get(i12).getPackedValue());
                arrayList.add(new x(Integer.valueOf(firstItemIndex), Constraints.m5634boximpl(lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1.m670childConstraintsJhjzzOo$foundation_release(i11, iM648getCurrentLineSpanimpl))));
                firstItemIndex++;
                i11 += iM648getCurrentLineSpanimpl;
            }
            return arrayList;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$3, reason: invalid class name */
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
    public LazyGridKt$rememberLazyGridMeasurePolicy$1$1(boolean z, PaddingValues paddingValues, boolean z5, r7.a<? extends LazyGridItemProvider> aVar, LazyGridSlotsProvider lazyGridSlotsProvider, LazyGridState lazyGridState, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, k0 k0Var) {
        super(2);
        this.$isVertical = z;
        this.$contentPadding = paddingValues;
        this.$reverseLayout = z5;
        this.$itemProviderLambda = aVar;
        this.$slots = lazyGridSlotsProvider;
        this.$state = lazyGridState;
        this.$verticalArrangement = vertical;
        this.$horizontalArrangement = horizontal;
        this.$coroutineScope = k0Var;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m663invoke0kLqBqw((LazyLayoutMeasureScope) obj, ((Constraints) obj2).getValue());
    }

    /* JADX WARN: Type inference failed for: r18v0, types: [androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1, androidx.compose.foundation.lazy.grid.LazyGridMeasuredLineProvider] */
    /* JADX WARN: Type inference failed for: r4v4, types: [androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1, androidx.compose.foundation.lazy.grid.LazyGridMeasuredItemProvider] */
    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
    public final LazyGridMeasureResult m663invoke0kLqBqw(final LazyLayoutMeasureScope lazyLayoutMeasureScope, long j10) {
        float spacing;
        long jIntOffset;
        int lineIndexOfItem;
        int i10;
        int firstVisibleItemScrollOffset;
        CheckScrollableContainerConstraintsKt.m208checkScrollableContainerConstraintsK40F9xA(j10, this.$isVertical ? Orientation.Vertical : Orientation.Horizontal);
        int iMo279roundToPx0680j_4 = this.$isVertical ? lazyLayoutMeasureScope.mo279roundToPx0680j_4(this.$contentPadding.mo480calculateLeftPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection())) : lazyLayoutMeasureScope.mo279roundToPx0680j_4(PaddingKt.calculateStartPadding(this.$contentPadding, lazyLayoutMeasureScope.getLayoutDirection()));
        int iMo279roundToPx0680j_42 = this.$isVertical ? lazyLayoutMeasureScope.mo279roundToPx0680j_4(this.$contentPadding.mo481calculateRightPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection())) : lazyLayoutMeasureScope.mo279roundToPx0680j_4(PaddingKt.calculateEndPadding(this.$contentPadding, lazyLayoutMeasureScope.getLayoutDirection()));
        int iMo279roundToPx0680j_43 = lazyLayoutMeasureScope.mo279roundToPx0680j_4(this.$contentPadding.getTop());
        int iMo279roundToPx0680j_44 = lazyLayoutMeasureScope.mo279roundToPx0680j_4(this.$contentPadding.getBottom());
        int i11 = iMo279roundToPx0680j_43 + iMo279roundToPx0680j_44;
        int i12 = iMo279roundToPx0680j_4 + iMo279roundToPx0680j_42;
        boolean z = this.$isVertical;
        int i13 = z ? i11 : i12;
        final int i14 = (!z || this.$reverseLayout) ? (z && this.$reverseLayout) ? iMo279roundToPx0680j_44 : (z || this.$reverseLayout) ? iMo279roundToPx0680j_42 : iMo279roundToPx0680j_4 : iMo279roundToPx0680j_43;
        final int i15 = i13 - i14;
        long jM5662offsetNN6EwU = ConstraintsKt.m5662offsetNN6EwU(j10, -i12, -i11);
        final LazyGridItemProvider lazyGridItemProvider = (LazyGridItemProvider) this.$itemProviderLambda.invoke();
        final LazyGridSpanLayoutProvider spanLayoutProvider = lazyGridItemProvider.getSpanLayoutProvider();
        final LazyGridSlots lazyGridSlotsMo652invoke0kLqBqw = this.$slots.mo652invoke0kLqBqw(lazyLayoutMeasureScope, j10);
        int length = lazyGridSlotsMo652invoke0kLqBqw.getSizes().length;
        spanLayoutProvider.setSlotsPerLine(length);
        this.$state.setDensity$foundation_release(lazyLayoutMeasureScope);
        this.$state.setSlotsPerLine$foundation_release(length);
        if (this.$isVertical) {
            Arrangement.Vertical vertical = this.$verticalArrangement;
            if (vertical == null) {
                throw new IllegalArgumentException("null verticalArrangement when isVertical == true");
            }
            spacing = vertical.getSpacing();
        } else {
            Arrangement.Horizontal horizontal = this.$horizontalArrangement;
            if (horizontal == null) {
                throw new IllegalArgumentException("null horizontalArrangement when isVertical == false");
            }
            spacing = horizontal.getSpacing();
        }
        final int iMo279roundToPx0680j_45 = lazyLayoutMeasureScope.mo279roundToPx0680j_4(spacing);
        final int itemCount = lazyGridItemProvider.getItemCount();
        int iM5645getMaxHeightimpl = this.$isVertical ? Constraints.m5645getMaxHeightimpl(j10) - i11 : Constraints.m5646getMaxWidthimpl(j10) - i12;
        if (!this.$reverseLayout || iM5645getMaxHeightimpl > 0) {
            jIntOffset = IntOffsetKt.IntOffset(iMo279roundToPx0680j_4, iMo279roundToPx0680j_43);
        } else {
            boolean z5 = this.$isVertical;
            if (!z5) {
                iMo279roundToPx0680j_4 += iM5645getMaxHeightimpl;
            }
            if (z5) {
                iMo279roundToPx0680j_43 += iM5645getMaxHeightimpl;
            }
            jIntOffset = IntOffsetKt.IntOffset(iMo279roundToPx0680j_4, iMo279roundToPx0680j_43);
        }
        final long j11 = jIntOffset;
        final LazyGridState lazyGridState = this.$state;
        final boolean z10 = this.$isVertical;
        final boolean z11 = this.$reverseLayout;
        final ?? r4 = new LazyGridMeasuredItemProvider(lazyGridItemProvider, lazyLayoutMeasureScope, iMo279roundToPx0680j_45, lazyGridState, z10, z11, i14, i15, j11) { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1
            final /* synthetic */ int $afterContentPadding;
            final /* synthetic */ int $beforeContentPadding;
            final /* synthetic */ boolean $isVertical;
            final /* synthetic */ boolean $reverseLayout;
            final /* synthetic */ LazyGridState $state;
            final /* synthetic */ LazyLayoutMeasureScope $this_null;
            final /* synthetic */ long $visualItemOffset;

            {
                this.$this_null = lazyLayoutMeasureScope;
                this.$state = lazyGridState;
                this.$isVertical = z10;
                this.$reverseLayout = z11;
                this.$beforeContentPadding = i14;
                this.$afterContentPadding = i15;
                this.$visualItemOffset = j11;
            }

            @Override // androidx.compose.foundation.lazy.grid.LazyGridMeasuredItemProvider
            public LazyGridMeasuredItem createItem(int index, Object key, Object contentType, int crossAxisSize, int mainAxisSpacing, List<? extends Placeable> placeables) {
                return new LazyGridMeasuredItem(index, key, this.$isVertical, crossAxisSize, mainAxisSpacing, this.$reverseLayout, this.$this_null.getLayoutDirection(), this.$beforeContentPadding, this.$afterContentPadding, placeables, this.$visualItemOffset, contentType, this.$state.getPlacementAnimator(), null);
            }
        };
        final boolean z12 = this.$isVertical;
        ?? r18 = new LazyGridMeasuredLineProvider(z12, lazyGridSlotsMo652invoke0kLqBqw, itemCount, iMo279roundToPx0680j_45, r4, spanLayoutProvider) { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1
            final /* synthetic */ boolean $isVertical;
            final /* synthetic */ LazyGridSlots $resolvedSlots;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z12, lazyGridSlotsMo652invoke0kLqBqw, itemCount, iMo279roundToPx0680j_45, r4, spanLayoutProvider);
                this.$isVertical = z12;
                this.$resolvedSlots = lazyGridSlotsMo652invoke0kLqBqw;
            }

            @Override // androidx.compose.foundation.lazy.grid.LazyGridMeasuredLineProvider
            public LazyGridMeasuredLine createLine(int index, LazyGridMeasuredItem[] items, List<GridItemSpan> spans, int mainAxisSpacing) {
                return new LazyGridMeasuredLine(index, items, this.$resolvedSlots, spans, this.$isVertical, mainAxisSpacing);
            }
        };
        this.$state.setPrefetchInfoRetriever$foundation_release(new AnonymousClass1(spanLayoutProvider, r18));
        Snapshot.Companion companion = Snapshot.INSTANCE;
        LazyGridState lazyGridState2 = this.$state;
        Snapshot snapshotCreateNonObservableSnapshot = companion.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                int iUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release = lazyGridState2.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(lazyGridItemProvider, lazyGridState2.getFirstVisibleItemIndex());
                if (iUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release < itemCount || itemCount <= 0) {
                    lineIndexOfItem = spanLayoutProvider.getLineIndexOfItem(iUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release);
                    i10 = i15;
                    firstVisibleItemScrollOffset = lazyGridState2.getFirstVisibleItemScrollOffset();
                } else {
                    lineIndexOfItem = spanLayoutProvider.getLineIndexOfItem(itemCount - 1);
                    i10 = i15;
                    firstVisibleItemScrollOffset = 0;
                }
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                snapshotCreateNonObservableSnapshot.dispose();
                LazyGridMeasureResult lazyGridMeasureResultM665measureLazyGridW2FL7xs = LazyGridMeasureKt.m665measureLazyGridW2FL7xs(itemCount, r18, r4, iM5645getMaxHeightimpl, i14, i10, iMo279roundToPx0680j_45, lineIndexOfItem, firstVisibleItemScrollOffset, this.$state.getScrollToBeConsumed(), jM5662offsetNN6EwU, this.$isVertical, this.$verticalArrangement, this.$horizontalArrangement, this.$reverseLayout, lazyLayoutMeasureScope, this.$state.getPlacementAnimator(), spanLayoutProvider, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(lazyGridItemProvider, this.$state.getPinnedItems(), this.$state.getBeyondBoundsInfo()), this.$coroutineScope, this.$state.m672getPlacementScopeInvalidatorzYiylxw$foundation_release(), new AnonymousClass3(lazyLayoutMeasureScope, j10, i12, i11));
                LazyGridState.applyMeasureResult$foundation_release$default(this.$state, lazyGridMeasureResultM665measureLazyGridW2FL7xs, false, 2, null);
                return lazyGridMeasureResultM665measureLazyGridW2FL7xs;
            } catch (Throwable th) {
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                throw th;
            }
        } catch (Throwable th2) {
            snapshotCreateNonObservableSnapshot.dispose();
            throw th2;
        }
    }
}
