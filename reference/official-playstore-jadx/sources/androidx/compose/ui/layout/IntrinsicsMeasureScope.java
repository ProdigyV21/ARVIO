package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\f\u001a\u00020\t*\u00020\bH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\t*\u00020\rH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\b*\u00020\rH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0012\u001a\u00020\b*\u00020\u0013H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0012\u001a\u00020\b*\u00020\tH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0016J\u0017\u0010\u001b\u001a\u00020\u0018*\u00020\u0017H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001d\u001a\u00020\u0013*\u00020\bH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u0015J\u0017\u0010\u001d\u001a\u00020\u0013*\u00020\rH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0011J\u0014\u0010!\u001a\u00020 *\u00020\u001fH\u0097\u0001¢\u0006\u0004\b!\u0010\"J\u0017\u0010$\u001a\u00020\u0017*\u00020\u0018H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010\u001aJ\u0017\u0010'\u001a\u00020\r*\u00020\bH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b%\u0010&J\u001a\u0010'\u001a\u00020\r*\u00020\u0013H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010&J\u001a\u0010'\u001a\u00020\r*\u00020\tH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010)JL\u00105\u001a\u0002042\u0006\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020\t2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\t0,2\u0017\u00103\u001a\u0013\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u0002010/¢\u0006\u0002\b2H\u0016¢\u0006\u0004\b5\u00106R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u00107\u001a\u0004\b8\u00109R\u0014\u0010<\u001a\u00020\u00138\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0014\u0010>\u001a\u00020\u00138\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b=\u0010;R\u0014\u0010@\u001a\u00020?8VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b@\u0010A\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006B"}, d2 = {"Landroidx/compose/ui/layout/IntrinsicsMeasureScope;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "intrinsicMeasureScope", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "<init>", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Landroidx/compose/ui/unit/LayoutDirection;)V", "Landroidx/compose/ui/unit/Dp;", "", "roundToPx-0680j_4", "(F)I", "roundToPx", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp-GaN1DYA", "(J)F", "toDp", "", "toDp-u2uoSUM", "(F)F", "(I)F", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/unit/DpSize;", "toDpSize-k-rfVVM", "(J)J", "toDpSize", "toPx-0680j_4", "toPx", "toPx--R2X_6o", "Landroidx/compose/ui/unit/DpRect;", "Landroidx/compose/ui/geometry/Rect;", "toRect", "(Landroidx/compose/ui/unit/DpRect;)Landroidx/compose/ui/geometry/Rect;", "toSize-XkaWNTQ", "toSize", "toSp-0xMU5do", "(F)J", "toSp", "toSp-kPz2Gy4", "(I)J", "width", "height", "", "Landroidx/compose/ui/layout/AlignmentLine;", "alignmentLines", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "Lx6/n;", "placementBlock", "Landroidx/compose/ui/layout/MeasureResult;", TtmlNode.TAG_LAYOUT, "(IILjava/util/Map;Lr7/l;)Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "getDensity", "()F", "density", "getFontScale", "fontScale", "", "isLookingAhead", "()Z", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class IntrinsicsMeasureScope implements MeasureScope, IntrinsicMeasureScope {
    public static final int $stable = 0;
    private final /* synthetic */ IntrinsicMeasureScope $$delegate_0;
    private final LayoutDirection layoutDirection;

    public IntrinsicsMeasureScope(IntrinsicMeasureScope intrinsicMeasureScope, LayoutDirection layoutDirection) {
        this.layoutDirection = layoutDirection;
        this.$$delegate_0 = intrinsicMeasureScope;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.$$delegate_0.getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.$$delegate_0.getFontScale();
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public LayoutDirection getLayoutDirection() {
        return this.layoutDirection;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public boolean isLookingAhead() {
        return this.$$delegate_0.isLookingAhead();
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public MeasureResult layout(final int width, final int height, final Map<AlignmentLine, Integer> alignmentLines, r7.l<? super Placeable.PlacementScope, t0> placementBlock) {
        if (width < 0) {
            width = 0;
        }
        if (height < 0) {
            height = 0;
        }
        if ((width & (-16777216)) == 0 && ((-16777216) & height) == 0) {
            return new MeasureResult() { // from class: androidx.compose.ui.layout.IntrinsicsMeasureScope.layout.1
                @Override // androidx.compose.ui.layout.MeasureResult
                public Map<AlignmentLine, Integer> getAlignmentLines() {
                    return alignmentLines;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                /* JADX INFO: renamed from: getHeight, reason: from getter */
                public int get$h() {
                    return height;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                /* JADX INFO: renamed from: getWidth, reason: from getter */
                public int get$w() {
                    return width;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public void placeChildren() {
                }
            };
        }
        throw new IllegalStateException(androidx.compose.foundation.c.s("Size(", width, " x ", height, ") is out of range. Each dimension must be between 0 and 16777215.").toString());
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    public int mo278roundToPxR2X_6o(long j10) {
        return this.$$delegate_0.mo278roundToPxR2X_6o(j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public int mo279roundToPx0680j_4(float f10) {
        return this.$$delegate_0.mo279roundToPx0680j_4(f10);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public float mo280toDpGaN1DYA(long j10) {
        return this.$$delegate_0.mo280toDpGaN1DYA(j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public float mo281toDpu2uoSUM(float f10) {
        return this.$$delegate_0.mo281toDpu2uoSUM(f10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public long mo283toDpSizekrfVVM(long j10) {
        return this.$$delegate_0.mo283toDpSizekrfVVM(j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public float mo284toPxR2X_6o(long j10) {
        return this.$$delegate_0.mo284toPxR2X_6o(j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public float mo285toPx0680j_4(float f10) {
        return this.$$delegate_0.mo285toPx0680j_4(f10);
    }

    @Override // androidx.compose.ui.unit.Density
    public Rect toRect(DpRect dpRect) {
        return this.$$delegate_0.toRect(dpRect);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public long mo286toSizeXkaWNTQ(long j10) {
        return this.$$delegate_0.mo286toSizeXkaWNTQ(j10);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public long mo287toSp0xMU5do(float f10) {
        return this.$$delegate_0.mo287toSp0xMU5do(f10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public long mo288toSpkPz2Gy4(float f10) {
        return this.$$delegate_0.mo288toSpkPz2Gy4(f10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public float mo282toDpu2uoSUM(int i10) {
        return this.$$delegate_0.mo282toDpu2uoSUM(i10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public long mo289toSpkPz2Gy4(int i10) {
        return this.$$delegate_0.mo289toSpkPz2Gy4(i10);
    }
}
