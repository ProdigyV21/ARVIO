package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u000f\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\u0010\u001a\u00020\r*\u00020\b2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0015\u001a\u00020\u0013*\u00020\u00112\u0006\u0010\n\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J#\u0010\u0017\u001a\u00020\u0013*\u00020\u00112\u0006\u0010\n\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0017\u0010\u0016J#\u0010\u0019\u001a\u00020\u0013*\u00020\u00112\u0006\u0010\n\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0019\u0010\u0016J#\u0010\u001a\u001a\u00020\u0013*\u00020\u00112\u0006\u0010\n\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u001a\u0010\u0016R(\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR(\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b \u0010\u001d\"\u0004\b!\u0010\u001f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\""}, d2 = {"Landroidx/compose/foundation/layout/UnspecifiedConstraintsNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/unit/Dp;", "minWidth", "minHeight", "<init>", "(FFLkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measurable", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "", "height", "minIntrinsicWidth", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Landroidx/compose/ui/layout/IntrinsicMeasurable;I)I", "maxIntrinsicWidth", "width", "minIntrinsicHeight", "maxIntrinsicHeight", "F", "getMinWidth-D9Ej5fM", "()F", "setMinWidth-0680j_4", "(F)V", "getMinHeight-D9Ej5fM", "setMinHeight-0680j_4", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class UnspecifiedConstraintsNode extends Modifier.Node implements LayoutModifierNode {
    private float minHeight;
    private float minWidth;

    public /* synthetic */ UnspecifiedConstraintsNode(float f10, float f11, h hVar) {
        this(f10, f11);
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMinHeight() {
        return this.minHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMinWidth() {
        return this.minWidth;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        int iMaxIntrinsicHeight = intrinsicMeasurable.maxIntrinsicHeight(i10);
        int iMo279roundToPx0680j_4 = !Dp.m5683equalsimpl0(this.minHeight, Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM()) ? intrinsicMeasureScope.mo279roundToPx0680j_4(this.minHeight) : 0;
        return iMaxIntrinsicHeight < iMo279roundToPx0680j_4 ? iMo279roundToPx0680j_4 : iMaxIntrinsicHeight;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        int iMaxIntrinsicWidth = intrinsicMeasurable.maxIntrinsicWidth(i10);
        int iMo279roundToPx0680j_4 = !Dp.m5683equalsimpl0(this.minWidth, Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM()) ? intrinsicMeasureScope.mo279roundToPx0680j_4(this.minWidth) : 0;
        return iMaxIntrinsicWidth < iMo279roundToPx0680j_4 ? iMo279roundToPx0680j_4 : iMaxIntrinsicWidth;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        int iM5648getMinWidthimpl;
        float f10 = this.minWidth;
        Dp.Companion companion = Dp.INSTANCE;
        int iM5647getMinHeightimpl = 0;
        if (Dp.m5683equalsimpl0(f10, companion.m5698getUnspecifiedD9Ej5fM()) || Constraints.m5648getMinWidthimpl(j10) != 0) {
            iM5648getMinWidthimpl = Constraints.m5648getMinWidthimpl(j10);
        } else {
            iM5648getMinWidthimpl = measureScope.mo279roundToPx0680j_4(this.minWidth);
            int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j10);
            if (iM5648getMinWidthimpl > iM5646getMaxWidthimpl) {
                iM5648getMinWidthimpl = iM5646getMaxWidthimpl;
            }
            if (iM5648getMinWidthimpl < 0) {
                iM5648getMinWidthimpl = 0;
            }
        }
        int iM5646getMaxWidthimpl2 = Constraints.m5646getMaxWidthimpl(j10);
        if (Dp.m5683equalsimpl0(this.minHeight, companion.m5698getUnspecifiedD9Ej5fM()) || Constraints.m5647getMinHeightimpl(j10) != 0) {
            iM5647getMinHeightimpl = Constraints.m5647getMinHeightimpl(j10);
        } else {
            int iMo279roundToPx0680j_4 = measureScope.mo279roundToPx0680j_4(this.minHeight);
            int iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(j10);
            if (iMo279roundToPx0680j_4 > iM5645getMaxHeightimpl) {
                iMo279roundToPx0680j_4 = iM5645getMaxHeightimpl;
            }
            if (iMo279roundToPx0680j_4 >= 0) {
                iM5647getMinHeightimpl = iMo279roundToPx0680j_4;
            }
        }
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(ConstraintsKt.Constraints(iM5648getMinWidthimpl, iM5646getMaxWidthimpl2, iM5647getMinHeightimpl, Constraints.m5645getMaxHeightimpl(j10)));
        return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new UnspecifiedConstraintsNode$measure$1(placeableMo4631measureBRTryo0), 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        int iMinIntrinsicHeight = intrinsicMeasurable.minIntrinsicHeight(i10);
        int iMo279roundToPx0680j_4 = !Dp.m5683equalsimpl0(this.minHeight, Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM()) ? intrinsicMeasureScope.mo279roundToPx0680j_4(this.minHeight) : 0;
        return iMinIntrinsicHeight < iMo279roundToPx0680j_4 ? iMo279roundToPx0680j_4 : iMinIntrinsicHeight;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        int iMinIntrinsicWidth = intrinsicMeasurable.minIntrinsicWidth(i10);
        int iMo279roundToPx0680j_4 = !Dp.m5683equalsimpl0(this.minWidth, Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM()) ? intrinsicMeasureScope.mo279roundToPx0680j_4(this.minWidth) : 0;
        return iMinIntrinsicWidth < iMo279roundToPx0680j_4 ? iMo279roundToPx0680j_4 : iMinIntrinsicWidth;
    }

    /* JADX INFO: renamed from: setMinHeight-0680j_4, reason: not valid java name */
    public final void m599setMinHeight0680j_4(float f10) {
        this.minHeight = f10;
    }

    /* JADX INFO: renamed from: setMinWidth-0680j_4, reason: not valid java name */
    public final void m600setMinWidth0680j_4(float f10) {
        this.minWidth = f10;
    }

    public /* synthetic */ UnspecifiedConstraintsNode(float f10, float f11, int i10, h hVar) {
        this((i10 & 1) != 0 ? Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM() : f10, (i10 & 2) != 0 ? Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM() : f11, null);
    }

    private UnspecifiedConstraintsNode(float f10, float f11) {
        this.minWidth = f10;
        this.minHeight = f11;
    }
}
