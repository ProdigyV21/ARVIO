package androidx.compose.foundation.gestures.snapping;

import androidx.compose.animation.SplineBasedDecayKt;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.grid.LazyGridItemInfo;
import androidx.compose.foundation.lazy.grid.LazyGridLayoutInfo;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a\u0014\u0010\u000b\u001a\u00020\u0001*\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0010"}, d2 = {"singleAxisViewportSize", "", "Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;", "getSingleAxisViewportSize", "(Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;)I", "SnapLayoutInfoProvider", "Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "lazyGridState", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "positionInLayout", "Landroidx/compose/foundation/gestures/snapping/SnapPositionInLayout;", "offsetOnMainAxis", "Landroidx/compose/foundation/lazy/grid/LazyGridItemInfo;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "sizeOnMainAxis", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyGridSnapLayoutInfoProviderKt {
    public static final SnapLayoutInfoProvider SnapLayoutInfoProvider(final LazyGridState lazyGridState, final SnapPositionInLayout snapPositionInLayout) {
        return new SnapLayoutInfoProvider() { // from class: androidx.compose.foundation.gestures.snapping.LazyGridSnapLayoutInfoProviderKt.SnapLayoutInfoProvider.1
            private final LazyGridLayoutInfo getLayoutInfo() {
                return lazyGridState.getLayoutInfo();
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x003c  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            private final java.util.List<androidx.compose.foundation.lazy.grid.LazyGridItemInfo> singleAxisItems() {
                /*
                    r9 = this;
                    androidx.compose.foundation.lazy.grid.LazyGridState r0 = r1
                    androidx.compose.foundation.lazy.grid.LazyGridLayoutInfo r0 = r0.getLayoutInfo()
                    java.util.List r0 = r0.getVisibleItemsInfo()
                    androidx.compose.foundation.lazy.grid.LazyGridState r1 = r1
                    java.util.ArrayList r2 = new java.util.ArrayList
                    int r3 = r0.size()
                    r2.<init>(r3)
                    int r3 = r0.size()
                    r4 = 0
                L1a:
                    if (r4 >= r3) goto L42
                    java.lang.Object r5 = r0.get(r4)
                    r6 = r5
                    androidx.compose.foundation.lazy.grid.LazyGridItemInfo r6 = (androidx.compose.foundation.lazy.grid.LazyGridItemInfo) r6
                    androidx.compose.foundation.lazy.grid.LazyGridLayoutInfo r7 = r1.getLayoutInfo()
                    androidx.compose.foundation.gestures.Orientation r7 = r7.getOrientation()
                    androidx.compose.foundation.gestures.Orientation r8 = androidx.compose.foundation.gestures.Orientation.Horizontal
                    if (r7 != r8) goto L36
                    int r6 = r6.getRow()
                    if (r6 != 0) goto L3f
                    goto L3c
                L36:
                    int r6 = r6.getColumn()
                    if (r6 != 0) goto L3f
                L3c:
                    r2.add(r5)
                L3f:
                    int r4 = r4 + 1
                    goto L1a
                L42:
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.snapping.LazyGridSnapLayoutInfoProviderKt.AnonymousClass1.singleAxisItems():java.util.List");
            }

            public final float averageItemSize() {
                int iM5844getWidthimpl;
                List<LazyGridItemInfo> listSingleAxisItems = singleAxisItems();
                if (listSingleAxisItems.isEmpty()) {
                    return 0.0f;
                }
                int i10 = 0;
                if (getLayoutInfo().getOrientation() == Orientation.Vertical) {
                    int size = listSingleAxisItems.size();
                    iM5844getWidthimpl = 0;
                    while (i10 < size) {
                        iM5844getWidthimpl += IntSize.m5843getHeightimpl(listSingleAxisItems.get(i10).getSize());
                        i10++;
                    }
                } else {
                    int size2 = listSingleAxisItems.size();
                    iM5844getWidthimpl = 0;
                    while (i10 < size2) {
                        iM5844getWidthimpl += IntSize.m5844getWidthimpl(listSingleAxisItems.get(i10).getSize());
                        i10++;
                    }
                }
                return iM5844getWidthimpl / listSingleAxisItems.size();
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateApproachOffset(float initialVelocity) {
                float fAverageItemSize = (averageItemSize() * ((float) Math.floor(Math.abs(Math.abs(DecayAnimationSpecKt.calculateTargetValue(SplineBasedDecayKt.splineBasedDecay(lazyGridState.getDensity()), 0.0f, initialVelocity))) / averageItemSize()))) - averageItemSize();
                if (fAverageItemSize < 0.0f) {
                    fAverageItemSize = 0.0f;
                }
                return fAverageItemSize == 0.0f ? fAverageItemSize : Math.signum(initialVelocity) * fAverageItemSize;
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateSnappingOffset(float currentVelocity) {
                List<LazyGridItemInfo> visibleItemsInfo = getLayoutInfo().getVisibleItemsInfo();
                SnapPositionInLayout snapPositionInLayout2 = snapPositionInLayout;
                int size = visibleItemsInfo.size();
                float f10 = Float.NEGATIVE_INFINITY;
                float f11 = Float.POSITIVE_INFINITY;
                for (int i10 = 0; i10 < size; i10++) {
                    LazyGridItemInfo lazyGridItemInfo = visibleItemsInfo.get(i10);
                    float fCalculateDistanceToDesiredSnapPosition = SnapPositionInLayoutKt.calculateDistanceToDesiredSnapPosition(LazyGridSnapLayoutInfoProviderKt.getSingleAxisViewportSize(getLayoutInfo()), getLayoutInfo().getBeforeContentPadding(), getLayoutInfo().getAfterContentPadding(), LazyGridSnapLayoutInfoProviderKt.sizeOnMainAxis(lazyGridItemInfo, getLayoutInfo().getOrientation()), LazyGridSnapLayoutInfoProviderKt.offsetOnMainAxis(lazyGridItemInfo, getLayoutInfo().getOrientation()), lazyGridItemInfo.getIndex(), snapPositionInLayout2);
                    if (fCalculateDistanceToDesiredSnapPosition <= 0.0f && fCalculateDistanceToDesiredSnapPosition > f10) {
                        f10 = fCalculateDistanceToDesiredSnapPosition;
                    }
                    if (fCalculateDistanceToDesiredSnapPosition >= 0.0f && fCalculateDistanceToDesiredSnapPosition < f11) {
                        f11 = fCalculateDistanceToDesiredSnapPosition;
                    }
                }
                return SnapFlingBehaviorKt.m407calculateFinalOffsetFhqu1e0(LazyListSnapLayoutInfoProviderKt.calculateFinalSnappingItem(lazyGridState.getDensity(), currentVelocity), f10, f11);
            }
        };
    }

    public static /* synthetic */ SnapLayoutInfoProvider SnapLayoutInfoProvider$default(LazyGridState lazyGridState, SnapPositionInLayout snapPositionInLayout, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            snapPositionInLayout = SnapPositionInLayout.INSTANCE.getCenterToCenter();
        }
        return SnapLayoutInfoProvider(lazyGridState, snapPositionInLayout);
    }

    public static final int getSingleAxisViewportSize(LazyGridLayoutInfo lazyGridLayoutInfo) {
        return lazyGridLayoutInfo.getOrientation() == Orientation.Vertical ? IntSize.m5843getHeightimpl(lazyGridLayoutInfo.mo664getViewportSizeYbymL2g()) : IntSize.m5844getWidthimpl(lazyGridLayoutInfo.mo664getViewportSizeYbymL2g());
    }

    public static final int offsetOnMainAxis(LazyGridItemInfo lazyGridItemInfo, Orientation orientation) {
        return orientation == Orientation.Vertical ? IntOffset.m5803getYimpl(lazyGridItemInfo.getOffset()) : IntOffset.m5802getXimpl(lazyGridItemInfo.getOffset());
    }

    public static final int sizeOnMainAxis(LazyGridItemInfo lazyGridItemInfo, Orientation orientation) {
        return orientation == Orientation.Vertical ? IntSize.m5843getHeightimpl(lazyGridItemInfo.getSize()) : IntSize.m5844getWidthimpl(lazyGridItemInfo.getSize());
    }
}
