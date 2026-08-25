package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayout;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0099\u0001\u0010\u001f\u001a\u0019\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0018¢\u0006\u0002\b\u001c2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0016\u001a\u00020\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0000H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001e\u0010#\u001a\u00020\"2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u0000H\u0082\b¢\u0006\u0004\b#\u0010$\u001a\u001b\u0010&\u001a\u00020\u000b*\u00020\u00032\u0006\u0010%\u001a\u00020\u000bH\u0000¢\u0006\u0004\b&\u0010'\"\u0014\u0010(\u001a\u00020\u00078\u0002X\u0082T¢\u0006\u0006\n\u0004\b(\u0010)\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006*"}, d2 = {"Lkotlin/Function0;", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "itemProviderLambda", "Landroidx/compose/foundation/pager/PagerState;", "state", "Landroidx/compose/foundation/layout/PaddingValues;", "contentPadding", "", "reverseLayout", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "", "beyondBoundsPageCount", "Landroidx/compose/ui/unit/Dp;", "pageSpacing", "Landroidx/compose/foundation/pager/PageSize;", "pageSize", "Landroidx/compose/ui/Alignment$Horizontal;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "verticalAlignment", "Landroidx/compose/foundation/gestures/snapping/SnapPositionInLayout;", "snapPositionInLayout", "pageCount", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lx6/n;", "rememberPagerMeasurePolicy-121YqSk", "(Lr7/a;Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;IFLandroidx/compose/foundation/pager/PageSize;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/snapping/SnapPositionInLayout;Lr7/a;Landroidx/compose/runtime/Composer;II)Lr7/p;", "rememberPagerMeasurePolicy", "", "generateMsg", "Lx6/t0;", "debugLog", "(Lr7/a;)V", "pageSizeWithSpacing", "calculateCurrentPageLayoutOffset", "(Landroidx/compose/foundation/pager/PagerState;I)I", "DEBUG", "Z", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PagerMeasurePolicyKt {
    private static final boolean DEBUG = false;

    public static final int calculateCurrentPageLayoutOffset(PagerState pagerState, int i10) {
        PageInfo pageInfo;
        List<PageInfo> visiblePagesInfo = pagerState.getLayoutInfo().getVisiblePagesInfo();
        int size = visiblePagesInfo.size();
        int i11 = 0;
        while (true) {
            if (i11 >= size) {
                pageInfo = null;
                break;
            }
            pageInfo = visiblePagesInfo.get(i11);
            if (pageInfo.getIndex() == pagerState.getCurrentPage()) {
                break;
            }
            i11++;
        }
        PageInfo pageInfo2 = pageInfo;
        int offset = pageInfo2 != null ? pageInfo2.getOffset() : 0;
        return -t7.a.M(((pagerState.getCurrentPageOffsetFraction() - (i10 == 0 ? pagerState.getCurrentPageOffsetFraction() : (-offset) / i10)) * i10) - offset);
    }

    /* JADX INFO: renamed from: rememberPagerMeasurePolicy-121YqSk, reason: not valid java name */
    public static final p<LazyLayoutMeasureScope, Constraints, MeasureResult> m761rememberPagerMeasurePolicy121YqSk(r7.a<PagerLazyLayoutItemProvider> aVar, PagerState pagerState, PaddingValues paddingValues, boolean z, Orientation orientation, int i10, float f10, PageSize pageSize, Alignment.Horizontal horizontal, Alignment.Vertical vertical, SnapPositionInLayout snapPositionInLayout, r7.a<Integer> aVar2, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(-1615726010);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1615726010, i11, i12, "androidx.compose.foundation.pager.rememberPagerMeasurePolicy (PagerMeasurePolicy.kt:56)");
        }
        Object[] objArr = {pagerState, paddingValues, Boolean.valueOf(z), orientation, horizontal, vertical, Dp.m5676boximpl(f10), pageSize, snapPositionInLayout, aVar2};
        composer.startReplaceableGroup(-568225417);
        boolean zChanged = false;
        for (int i13 = 0; i13 < 10; i13++) {
            zChanged |= composer.changed(objArr[i13]);
        }
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 = new PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(orientation, paddingValues, z, pagerState, f10, pageSize, aVar, aVar2, vertical, horizontal, i10, snapPositionInLayout);
            composer.updateRememberedValue(pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1);
            objRememberedValue = pagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1;
        }
        composer.endReplaceableGroup();
        p<LazyLayoutMeasureScope, Constraints, MeasureResult> pVar = (p) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return pVar;
    }

    private static final void debugLog(r7.a<String> aVar) {
    }
}
