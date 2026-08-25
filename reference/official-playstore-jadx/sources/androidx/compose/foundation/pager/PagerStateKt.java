package androidx.compose.foundation.pager;

import androidx.appcompat.app.j0;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayout;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.b;
import d7.d;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.collections.z;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Y\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\"\u001a1\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a\u0014\u0010\n\u001a\u00020\t*\u00020\u0006H\u0080@¢\u0006\u0004\b\n\u0010\u000b\u001a\u0014\u0010\f\u001a\u00020\t*\u00020\u0006H\u0080@¢\u0006\u0004\b\f\u0010\u000b\u001a\u001e\u0010\u000f\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u0004H\u0082\b¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001b\u0010\u0012\u001a\u00020\u0000*\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\"\u001a\u0010\u0015\u001a\u00020\u00148\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0014\u0010\u0019\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a\"\u0014\u0010\u001b\u001a\u00020\u00008\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u001b\u0010\u001a\" \u0010\u001c\u001a\u00020\u00118\u0000X\u0080\u0004¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u0012\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001f\"\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$\" \u0010&\u001a\u00020%8\u0000X\u0080\u0004¢\u0006\u0012\n\u0004\b&\u0010'\u0012\u0004\b*\u0010!\u001a\u0004\b(\u0010)\"\u0014\u0010,\u001a\u00020+8\u0002X\u0082T¢\u0006\u0006\n\u0004\b,\u0010-\"\u001e\u00103\u001a\u00020\u0000*\u00020.8BX\u0082\u0004¢\u0006\f\u0012\u0004\b1\u00102\u001a\u0004\b/\u00100¨\u00064"}, d2 = {"", "initialPage", "", "initialPageOffsetFraction", "Lkotlin/Function0;", "pageCount", "Landroidx/compose/foundation/pager/PagerState;", "rememberPagerState", "(IFLr7/a;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/pager/PagerState;", "Lx6/t0;", "animateToNextPage", "(Landroidx/compose/foundation/pager/PagerState;Ld7/d;)Ljava/lang/Object;", "animateToPreviousPage", "", "generateMsg", "debugLog", "(Lr7/a;)V", "Landroidx/compose/foundation/pager/PagerMeasureResult;", "calculateNewMaxScrollOffset", "(Landroidx/compose/foundation/pager/PagerMeasureResult;I)I", "Landroidx/compose/ui/unit/Dp;", "DefaultPositionThreshold", "F", "getDefaultPositionThreshold", "()F", "MaxPagesForAnimateScroll", "I", "PagesToPrefetch", "EmptyLayoutInfo", "Landroidx/compose/foundation/pager/PagerMeasureResult;", "getEmptyLayoutInfo", "()Landroidx/compose/foundation/pager/PagerMeasureResult;", "getEmptyLayoutInfo$annotations", "()V", "androidx/compose/foundation/pager/PagerStateKt$UnitDensity$1", "UnitDensity", "Landroidx/compose/foundation/pager/PagerStateKt$UnitDensity$1;", "Landroidx/compose/foundation/gestures/snapping/SnapPositionInLayout;", "SnapAlignmentStartToStart", "Landroidx/compose/foundation/gestures/snapping/SnapPositionInLayout;", "getSnapAlignmentStartToStart", "()Landroidx/compose/foundation/gestures/snapping/SnapPositionInLayout;", "getSnapAlignmentStartToStart$annotations", "", "DEBUG", "Z", "Landroidx/compose/foundation/pager/PagerLayoutInfo;", "getSingleAxisViewPort", "(Landroidx/compose/foundation/pager/PagerLayoutInfo;)I", "getSingleAxisViewPort$annotations", "(Landroidx/compose/foundation/pager/PagerLayoutInfo;)V", "singleAxisViewPort", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PagerStateKt {
    private static final boolean DEBUG = false;
    private static final int MaxPagesForAnimateScroll = 3;
    public static final int PagesToPrefetch = 1;
    private static final float DefaultPositionThreshold = Dp.m5678constructorimpl(56);
    private static final PagerMeasureResult EmptyLayoutInfo = new PagerMeasureResult(z.f19728i, 0, 0, 0, Orientation.Horizontal, 0, 0, false, 0, null, null, 0.0f, 0, false, new MeasureResult() { // from class: androidx.compose.foundation.pager.PagerStateKt$EmptyLayoutInfo$1
        private final Map<AlignmentLine, Integer> alignmentLines = a0.f19683i;
        private final int height;
        private final int width;

        public static /* synthetic */ void getAlignmentLines$annotations() {
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public Map<AlignmentLine, Integer> getAlignmentLines() {
            return this.alignmentLines;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public int getHeight() {
            return this.height;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public int getWidth() {
            return this.width;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public void placeChildren() {
        }
    }, false);
    private static final PagerStateKt$UnitDensity$1 UnitDensity = new Density() { // from class: androidx.compose.foundation.pager.PagerStateKt$UnitDensity$1
        private final float density = 1.0f;
        private final float fontScale = 1.0f;

        @Override // androidx.compose.ui.unit.Density
        public float getDensity() {
            return this.density;
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public float getFontScale() {
            return this.fontScale;
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx--R2X_6o */
        public final /* synthetic */ int mo278roundToPxR2X_6o(long j10) {
            return androidx.compose.ui.unit.a.a(this, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx-0680j_4 */
        public final /* synthetic */ int mo279roundToPx0680j_4(float f10) {
            return androidx.compose.ui.unit.a.b(this, f10);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toDp-GaN1DYA */
        public final /* synthetic */ float mo280toDpGaN1DYA(long j10) {
            return b.a(this, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public final /* synthetic */ float mo281toDpu2uoSUM(float f10) {
            return androidx.compose.ui.unit.a.c(this, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDpSize-k-rfVVM */
        public final /* synthetic */ long mo283toDpSizekrfVVM(long j10) {
            return androidx.compose.ui.unit.a.e(this, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx--R2X_6o */
        public final /* synthetic */ float mo284toPxR2X_6o(long j10) {
            return androidx.compose.ui.unit.a.f(this, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx-0680j_4 */
        public final /* synthetic */ float mo285toPx0680j_4(float f10) {
            return androidx.compose.ui.unit.a.g(this, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        public final /* synthetic */ Rect toRect(DpRect dpRect) {
            return androidx.compose.ui.unit.a.h(this, dpRect);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSize-XkaWNTQ */
        public final /* synthetic */ long mo286toSizeXkaWNTQ(long j10) {
            return androidx.compose.ui.unit.a.i(this, j10);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toSp-0xMU5do */
        public final /* synthetic */ long mo287toSp0xMU5do(float f10) {
            return b.b(this, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public final /* synthetic */ long mo288toSpkPz2Gy4(float f10) {
            return androidx.compose.ui.unit.a.j(this, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public final /* synthetic */ float mo282toDpu2uoSUM(int i10) {
            return androidx.compose.ui.unit.a.d(this, i10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public final /* synthetic */ long mo289toSpkPz2Gy4(int i10) {
            return androidx.compose.ui.unit.a.k(this, i10);
        }
    };
    private static final SnapPositionInLayout SnapAlignmentStartToStart = new j0(10);

    /* JADX INFO: Access modifiers changed from: private */
    public static final int SnapAlignmentStartToStart$lambda$2(int i10, int i11, int i12, int i13, int i14) {
        return 0;
    }

    public static final Object animateToNextPage(PagerState pagerState, d<? super t0> dVar) {
        Object objAnimateScrollToPage$default;
        return (pagerState.getCurrentPage() + 1 >= pagerState.getPageCount() || (objAnimateScrollToPage$default = PagerState.animateScrollToPage$default(pagerState, pagerState.getCurrentPage() + 1, 0.0f, null, dVar, 6, null)) != e7.a.f15033i) ? t0.f22605a : objAnimateScrollToPage$default;
    }

    public static final Object animateToPreviousPage(PagerState pagerState, d<? super t0> dVar) {
        Object objAnimateScrollToPage$default;
        return (pagerState.getCurrentPage() + (-1) < 0 || (objAnimateScrollToPage$default = PagerState.animateScrollToPage$default(pagerState, pagerState.getCurrentPage() + (-1), 0.0f, null, dVar, 6, null)) != e7.a.f15033i) ? t0.f22605a : objAnimateScrollToPage$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int calculateNewMaxScrollOffset(PagerMeasureResult pagerMeasureResult, int i10) {
        int afterContentPadding = ((pagerMeasureResult.getAfterContentPadding() + (((pagerMeasureResult.getPageSize() + pagerMeasureResult.getPageSpacing()) * i10) + pagerMeasureResult.getBeforeContentPadding())) - pagerMeasureResult.getPageSpacing()) - getSingleAxisViewPort(pagerMeasureResult);
        if (afterContentPadding < 0) {
            return 0;
        }
        return afterContentPadding;
    }

    public static final float getDefaultPositionThreshold() {
        return DefaultPositionThreshold;
    }

    public static final PagerMeasureResult getEmptyLayoutInfo() {
        return EmptyLayoutInfo;
    }

    public static /* synthetic */ void getEmptyLayoutInfo$annotations() {
    }

    private static final int getSingleAxisViewPort(PagerLayoutInfo pagerLayoutInfo) {
        return pagerLayoutInfo.getOrientation() == Orientation.Vertical ? IntSize.m5843getHeightimpl(pagerLayoutInfo.mo757getViewportSizeYbymL2g()) : IntSize.m5844getWidthimpl(pagerLayoutInfo.mo757getViewportSizeYbymL2g());
    }

    private static /* synthetic */ void getSingleAxisViewPort$annotations(PagerLayoutInfo pagerLayoutInfo) {
    }

    public static final SnapPositionInLayout getSnapAlignmentStartToStart() {
        return SnapAlignmentStartToStart;
    }

    public static /* synthetic */ void getSnapAlignmentStartToStart$annotations() {
    }

    public static final PagerState rememberPagerState(int i10, float f10, r7.a<Integer> aVar, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(-1210768637);
        if ((i12 & 1) != 0) {
            i10 = 0;
        }
        if ((i12 & 2) != 0) {
            f10 = 0.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1210768637, i11, -1, "androidx.compose.foundation.pager.rememberPagerState (PagerState.kt:79)");
        }
        Object[] objArr = new Object[0];
        Saver<PagerStateImpl, ?> saver = PagerStateImpl.INSTANCE.getSaver();
        composer.startReplaceableGroup(-382513842);
        boolean zChanged = composer.changed(i10) | composer.changed(f10) | composer.changedInstance(aVar);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new PagerStateKt$rememberPagerState$1$1(i10, f10, aVar);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        PagerStateImpl pagerStateImpl = (PagerStateImpl) RememberSaveableKt.m3084rememberSaveable(objArr, (Saver) saver, (String) null, (r7.a) objRememberedValue, composer, 72, 4);
        pagerStateImpl.getPageCountState().setValue(aVar);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return pagerStateImpl;
    }

    private static final void debugLog(r7.a<String> aVar) {
    }
}
