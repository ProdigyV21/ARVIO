package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J&\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/layout/PaddingValuesModifier;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(Landroidx/compose/foundation/layout/PaddingValues;)V", "getPaddingValues", "()Landroidx/compose/foundation/layout/PaddingValues;", "setPaddingValues", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class PaddingValuesModifier extends Modifier.Node implements LayoutModifierNode {
    private PaddingValues paddingValues;

    public PaddingValuesModifier(PaddingValues paddingValues) {
        this.paddingValues = paddingValues;
    }

    public final PaddingValues getPaddingValues() {
        return this.paddingValues;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.a(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.b(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        float f10 = 0;
        if (Dp.m5677compareTo0680j_4(this.paddingValues.mo480calculateLeftPaddingu2uoSUM(measureScope.getLayoutDirection()), Dp.m5678constructorimpl(f10)) < 0 || Dp.m5677compareTo0680j_4(this.paddingValues.getTop(), Dp.m5678constructorimpl(f10)) < 0 || Dp.m5677compareTo0680j_4(this.paddingValues.mo481calculateRightPaddingu2uoSUM(measureScope.getLayoutDirection()), Dp.m5678constructorimpl(f10)) < 0 || Dp.m5677compareTo0680j_4(this.paddingValues.getBottom(), Dp.m5678constructorimpl(f10)) < 0) {
            throw new IllegalArgumentException("Padding must be non-negative");
        }
        int iMo279roundToPx0680j_4 = measureScope.mo279roundToPx0680j_4(this.paddingValues.mo481calculateRightPaddingu2uoSUM(measureScope.getLayoutDirection())) + measureScope.mo279roundToPx0680j_4(this.paddingValues.mo480calculateLeftPaddingu2uoSUM(measureScope.getLayoutDirection()));
        int iMo279roundToPx0680j_42 = measureScope.mo279roundToPx0680j_4(this.paddingValues.getBottom()) + measureScope.mo279roundToPx0680j_4(this.paddingValues.getTop());
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(ConstraintsKt.m5662offsetNN6EwU(j10, -iMo279roundToPx0680j_4, -iMo279roundToPx0680j_42));
        return MeasureScope.CC.q(measureScope, ConstraintsKt.m5660constrainWidthK40F9xA(j10, placeableMo4631measureBRTryo0.getWidth() + iMo279roundToPx0680j_4), ConstraintsKt.m5659constrainHeightK40F9xA(j10, placeableMo4631measureBRTryo0.getHeight() + iMo279roundToPx0680j_42), null, new PaddingValuesModifier$measure$2(placeableMo4631measureBRTryo0, measureScope, this), 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.c(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.d(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    public final void setPaddingValues(PaddingValues paddingValues) {
        this.paddingValues = paddingValues;
    }
}
