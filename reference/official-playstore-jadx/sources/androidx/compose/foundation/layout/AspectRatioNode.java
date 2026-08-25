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
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\u0010\u001a\u00020\u0011*\u00020\u0012H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u001c\u0010\u0015\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0016H\u0016J\u001c\u0010\u001b\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0016H\u0016J&\u0010\u001d\u001a\u00020\u001e*\u00020\u001f2\u0006\u0010\u0018\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u001c\u0010$\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0016H\u0016J\u001c\u0010%\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0016H\u0016J \u0010&\u001a\u00020\u0011*\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b(\u0010)J \u0010*\u001a\u00020\u0011*\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010)J \u0010,\u001a\u00020\u0011*\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b-\u0010)J \u0010.\u001a\u00020\u0011*\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b/\u0010)R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00060"}, d2 = {"Landroidx/compose/foundation/layout/AspectRatioNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "aspectRatio", "", "matchHeightConstraintsFirst", "", "(FZ)V", "getAspectRatio", "()F", "setAspectRatio", "(F)V", "getMatchHeightConstraintsFirst", "()Z", "setMatchHeightConstraintsFirst", "(Z)V", "findSize", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/ui/unit/Constraints;", "findSize-ToXhtMw", "(J)J", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "tryMaxHeight", "enforceConstraints", "tryMaxHeight-JN-0ABg", "(JZ)J", "tryMaxWidth", "tryMaxWidth-JN-0ABg", "tryMinHeight", "tryMinHeight-JN-0ABg", "tryMinWidth", "tryMinWidth-JN-0ABg", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class AspectRatioNode extends Modifier.Node implements LayoutModifierNode {
    private float aspectRatio;
    private boolean matchHeightConstraintsFirst;

    public AspectRatioNode(float f10, boolean z) {
        this.aspectRatio = f10;
        this.matchHeightConstraintsFirst = z;
    }

    /* JADX INFO: renamed from: findSize-ToXhtMw, reason: not valid java name */
    private final long m455findSizeToXhtMw(long j10) {
        if (this.matchHeightConstraintsFirst) {
            long jM457tryMaxHeightJN0ABg$default = m457tryMaxHeightJN0ABg$default(this, j10, false, 1, null);
            IntSize.Companion companion = IntSize.INSTANCE;
            if (!IntSize.m5842equalsimpl0(jM457tryMaxHeightJN0ABg$default, companion.m5849getZeroYbymL2g())) {
                return jM457tryMaxHeightJN0ABg$default;
            }
            long jM459tryMaxWidthJN0ABg$default = m459tryMaxWidthJN0ABg$default(this, j10, false, 1, null);
            if (!IntSize.m5842equalsimpl0(jM459tryMaxWidthJN0ABg$default, companion.m5849getZeroYbymL2g())) {
                return jM459tryMaxWidthJN0ABg$default;
            }
            long jM461tryMinHeightJN0ABg$default = m461tryMinHeightJN0ABg$default(this, j10, false, 1, null);
            if (!IntSize.m5842equalsimpl0(jM461tryMinHeightJN0ABg$default, companion.m5849getZeroYbymL2g())) {
                return jM461tryMinHeightJN0ABg$default;
            }
            long jM463tryMinWidthJN0ABg$default = m463tryMinWidthJN0ABg$default(this, j10, false, 1, null);
            if (!IntSize.m5842equalsimpl0(jM463tryMinWidthJN0ABg$default, companion.m5849getZeroYbymL2g())) {
                return jM463tryMinWidthJN0ABg$default;
            }
            long jM456tryMaxHeightJN0ABg = m456tryMaxHeightJN0ABg(j10, false);
            if (!IntSize.m5842equalsimpl0(jM456tryMaxHeightJN0ABg, companion.m5849getZeroYbymL2g())) {
                return jM456tryMaxHeightJN0ABg;
            }
            long jM458tryMaxWidthJN0ABg = m458tryMaxWidthJN0ABg(j10, false);
            if (!IntSize.m5842equalsimpl0(jM458tryMaxWidthJN0ABg, companion.m5849getZeroYbymL2g())) {
                return jM458tryMaxWidthJN0ABg;
            }
            long jM460tryMinHeightJN0ABg = m460tryMinHeightJN0ABg(j10, false);
            if (!IntSize.m5842equalsimpl0(jM460tryMinHeightJN0ABg, companion.m5849getZeroYbymL2g())) {
                return jM460tryMinHeightJN0ABg;
            }
            long jM462tryMinWidthJN0ABg = m462tryMinWidthJN0ABg(j10, false);
            if (!IntSize.m5842equalsimpl0(jM462tryMinWidthJN0ABg, companion.m5849getZeroYbymL2g())) {
                return jM462tryMinWidthJN0ABg;
            }
        } else {
            long jM459tryMaxWidthJN0ABg$default2 = m459tryMaxWidthJN0ABg$default(this, j10, false, 1, null);
            IntSize.Companion companion2 = IntSize.INSTANCE;
            if (!IntSize.m5842equalsimpl0(jM459tryMaxWidthJN0ABg$default2, companion2.m5849getZeroYbymL2g())) {
                return jM459tryMaxWidthJN0ABg$default2;
            }
            long jM457tryMaxHeightJN0ABg$default2 = m457tryMaxHeightJN0ABg$default(this, j10, false, 1, null);
            if (!IntSize.m5842equalsimpl0(jM457tryMaxHeightJN0ABg$default2, companion2.m5849getZeroYbymL2g())) {
                return jM457tryMaxHeightJN0ABg$default2;
            }
            long jM463tryMinWidthJN0ABg$default2 = m463tryMinWidthJN0ABg$default(this, j10, false, 1, null);
            if (!IntSize.m5842equalsimpl0(jM463tryMinWidthJN0ABg$default2, companion2.m5849getZeroYbymL2g())) {
                return jM463tryMinWidthJN0ABg$default2;
            }
            long jM461tryMinHeightJN0ABg$default2 = m461tryMinHeightJN0ABg$default(this, j10, false, 1, null);
            if (!IntSize.m5842equalsimpl0(jM461tryMinHeightJN0ABg$default2, companion2.m5849getZeroYbymL2g())) {
                return jM461tryMinHeightJN0ABg$default2;
            }
            long jM458tryMaxWidthJN0ABg2 = m458tryMaxWidthJN0ABg(j10, false);
            if (!IntSize.m5842equalsimpl0(jM458tryMaxWidthJN0ABg2, companion2.m5849getZeroYbymL2g())) {
                return jM458tryMaxWidthJN0ABg2;
            }
            long jM456tryMaxHeightJN0ABg2 = m456tryMaxHeightJN0ABg(j10, false);
            if (!IntSize.m5842equalsimpl0(jM456tryMaxHeightJN0ABg2, companion2.m5849getZeroYbymL2g())) {
                return jM456tryMaxHeightJN0ABg2;
            }
            long jM462tryMinWidthJN0ABg2 = m462tryMinWidthJN0ABg(j10, false);
            if (!IntSize.m5842equalsimpl0(jM462tryMinWidthJN0ABg2, companion2.m5849getZeroYbymL2g())) {
                return jM462tryMinWidthJN0ABg2;
            }
            long jM460tryMinHeightJN0ABg2 = m460tryMinHeightJN0ABg(j10, false);
            if (!IntSize.m5842equalsimpl0(jM460tryMinHeightJN0ABg2, companion2.m5849getZeroYbymL2g())) {
                return jM460tryMinHeightJN0ABg2;
            }
        }
        return IntSize.INSTANCE.m5849getZeroYbymL2g();
    }

    /* JADX INFO: renamed from: tryMaxHeight-JN-0ABg, reason: not valid java name */
    private final long m456tryMaxHeightJN0ABg(long j10, boolean z) {
        int iM;
        int iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(j10);
        if (iM5645getMaxHeightimpl != Integer.MAX_VALUE && (iM = t7.a.M(iM5645getMaxHeightimpl * this.aspectRatio)) > 0) {
            long jIntSize = IntSizeKt.IntSize(iM, iM5645getMaxHeightimpl);
            if (!z || ConstraintsKt.m5661isSatisfiedBy4WqzIAM(j10, jIntSize)) {
                return jIntSize;
            }
        }
        return IntSize.INSTANCE.m5849getZeroYbymL2g();
    }

    /* JADX INFO: renamed from: tryMaxHeight-JN-0ABg$default, reason: not valid java name */
    public static /* synthetic */ long m457tryMaxHeightJN0ABg$default(AspectRatioNode aspectRatioNode, long j10, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        return aspectRatioNode.m456tryMaxHeightJN0ABg(j10, z);
    }

    /* JADX INFO: renamed from: tryMaxWidth-JN-0ABg, reason: not valid java name */
    private final long m458tryMaxWidthJN0ABg(long j10, boolean z) {
        int iM;
        int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j10);
        if (iM5646getMaxWidthimpl != Integer.MAX_VALUE && (iM = t7.a.M(iM5646getMaxWidthimpl / this.aspectRatio)) > 0) {
            long jIntSize = IntSizeKt.IntSize(iM5646getMaxWidthimpl, iM);
            if (!z || ConstraintsKt.m5661isSatisfiedBy4WqzIAM(j10, jIntSize)) {
                return jIntSize;
            }
        }
        return IntSize.INSTANCE.m5849getZeroYbymL2g();
    }

    /* JADX INFO: renamed from: tryMaxWidth-JN-0ABg$default, reason: not valid java name */
    public static /* synthetic */ long m459tryMaxWidthJN0ABg$default(AspectRatioNode aspectRatioNode, long j10, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        return aspectRatioNode.m458tryMaxWidthJN0ABg(j10, z);
    }

    /* JADX INFO: renamed from: tryMinHeight-JN-0ABg, reason: not valid java name */
    private final long m460tryMinHeightJN0ABg(long j10, boolean z) {
        int iM5647getMinHeightimpl = Constraints.m5647getMinHeightimpl(j10);
        int iM = t7.a.M(iM5647getMinHeightimpl * this.aspectRatio);
        if (iM > 0) {
            long jIntSize = IntSizeKt.IntSize(iM, iM5647getMinHeightimpl);
            if (!z || ConstraintsKt.m5661isSatisfiedBy4WqzIAM(j10, jIntSize)) {
                return jIntSize;
            }
        }
        return IntSize.INSTANCE.m5849getZeroYbymL2g();
    }

    /* JADX INFO: renamed from: tryMinHeight-JN-0ABg$default, reason: not valid java name */
    public static /* synthetic */ long m461tryMinHeightJN0ABg$default(AspectRatioNode aspectRatioNode, long j10, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        return aspectRatioNode.m460tryMinHeightJN0ABg(j10, z);
    }

    /* JADX INFO: renamed from: tryMinWidth-JN-0ABg, reason: not valid java name */
    private final long m462tryMinWidthJN0ABg(long j10, boolean z) {
        int iM5648getMinWidthimpl = Constraints.m5648getMinWidthimpl(j10);
        int iM = t7.a.M(iM5648getMinWidthimpl / this.aspectRatio);
        if (iM > 0) {
            long jIntSize = IntSizeKt.IntSize(iM5648getMinWidthimpl, iM);
            if (!z || ConstraintsKt.m5661isSatisfiedBy4WqzIAM(j10, jIntSize)) {
                return jIntSize;
            }
        }
        return IntSize.INSTANCE.m5849getZeroYbymL2g();
    }

    /* JADX INFO: renamed from: tryMinWidth-JN-0ABg$default, reason: not valid java name */
    public static /* synthetic */ long m463tryMinWidthJN0ABg$default(AspectRatioNode aspectRatioNode, long j10, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        return aspectRatioNode.m462tryMinWidthJN0ABg(j10, z);
    }

    public final float getAspectRatio() {
        return this.aspectRatio;
    }

    public final boolean getMatchHeightConstraintsFirst() {
        return this.matchHeightConstraintsFirst;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return i10 != Integer.MAX_VALUE ? t7.a.M(i10 / this.aspectRatio) : intrinsicMeasurable.maxIntrinsicHeight(i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return i10 != Integer.MAX_VALUE ? t7.a.M(i10 * this.aspectRatio) : intrinsicMeasurable.maxIntrinsicWidth(i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        long jM455findSizeToXhtMw = m455findSizeToXhtMw(j10);
        if (!IntSize.m5842equalsimpl0(jM455findSizeToXhtMw, IntSize.INSTANCE.m5849getZeroYbymL2g())) {
            j10 = Constraints.INSTANCE.m5654fixedJhjzzOo(IntSize.m5844getWidthimpl(jM455findSizeToXhtMw), IntSize.m5843getHeightimpl(jM455findSizeToXhtMw));
        }
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(j10);
        return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new AspectRatioNode$measure$1(placeableMo4631measureBRTryo0), 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return i10 != Integer.MAX_VALUE ? t7.a.M(i10 / this.aspectRatio) : intrinsicMeasurable.minIntrinsicHeight(i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return i10 != Integer.MAX_VALUE ? t7.a.M(i10 * this.aspectRatio) : intrinsicMeasurable.minIntrinsicWidth(i10);
    }

    public final void setAspectRatio(float f10) {
        this.aspectRatio = f10;
    }

    public final void setMatchHeightConstraintsFirst(boolean z) {
        this.matchHeightConstraintsFirst = z;
    }
}
