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
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B7\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ&\u0010\u0014\u001a\u00020\u0011*\u00020\f2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J#\u0010\u0019\u001a\u00020\u0017*\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ#\u0010\u001c\u001a\u00020\u0017*\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001c\u0010\u001aJ#\u0010\u001d\u001a\u00020\u0017*\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001d\u0010\u001aJ#\u0010\u001e\u001a\u00020\u0017*\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001e\u0010\u001aR(\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0004\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R(\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R(\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R(\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b(\u0010!\"\u0004\b)\u0010#R\"\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001e\u00102\u001a\u00020\u000f*\u00020/8BX\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b0\u00101\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00063"}, d2 = {"Landroidx/compose/foundation/layout/SizeNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/unit/Dp;", "minWidth", "minHeight", "maxWidth", "maxHeight", "", "enforceIncoming", "<init>", "(FFFFZLkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measurable", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "", "height", "minIntrinsicWidth", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Landroidx/compose/ui/layout/IntrinsicMeasurable;I)I", "width", "minIntrinsicHeight", "maxIntrinsicWidth", "maxIntrinsicHeight", "F", "getMinWidth-D9Ej5fM", "()F", "setMinWidth-0680j_4", "(F)V", "getMinHeight-D9Ej5fM", "setMinHeight-0680j_4", "getMaxWidth-D9Ej5fM", "setMaxWidth-0680j_4", "getMaxHeight-D9Ej5fM", "setMaxHeight-0680j_4", "Z", "getEnforceIncoming", "()Z", "setEnforceIncoming", "(Z)V", "Landroidx/compose/ui/unit/Density;", "getTargetConstraints-OenEA2s", "(Landroidx/compose/ui/unit/Density;)J", "targetConstraints", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SizeNode extends Modifier.Node implements LayoutModifierNode {
    private boolean enforceIncoming;
    private float maxHeight;
    private float maxWidth;
    private float minHeight;
    private float minWidth;

    public /* synthetic */ SizeNode(float f10, float f11, float f12, float f13, boolean z, h hVar) {
        this(f10, f11, f12, f13, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004f  */
    /* JADX INFO: renamed from: getTargetConstraints-OenEA2s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final long m586getTargetConstraintsOenEA2s(androidx.compose.ui.unit.Density r8) {
        /*
            r7 = this;
            float r0 = r7.maxWidth
            androidx.compose.ui.unit.Dp$Companion r1 = androidx.compose.ui.unit.Dp.INSTANCE
            float r2 = r1.m5698getUnspecifiedD9Ej5fM()
            boolean r0 = androidx.compose.ui.unit.Dp.m5683equalsimpl0(r0, r2)
            r2 = 2147483647(0x7fffffff, float:NaN)
            r3 = 0
            if (r0 != 0) goto L1c
            float r0 = r7.maxWidth
            int r0 = r8.mo279roundToPx0680j_4(r0)
            if (r0 >= 0) goto L1d
            r0 = r3
            goto L1d
        L1c:
            r0 = r2
        L1d:
            float r4 = r7.maxHeight
            float r5 = r1.m5698getUnspecifiedD9Ej5fM()
            boolean r4 = androidx.compose.ui.unit.Dp.m5683equalsimpl0(r4, r5)
            if (r4 != 0) goto L33
            float r4 = r7.maxHeight
            int r4 = r8.mo279roundToPx0680j_4(r4)
            if (r4 >= 0) goto L34
            r4 = r3
            goto L34
        L33:
            r4 = r2
        L34:
            float r5 = r7.minWidth
            float r6 = r1.m5698getUnspecifiedD9Ej5fM()
            boolean r5 = androidx.compose.ui.unit.Dp.m5683equalsimpl0(r5, r6)
            if (r5 != 0) goto L4f
            float r5 = r7.minWidth
            int r5 = r8.mo279roundToPx0680j_4(r5)
            if (r5 <= r0) goto L49
            r5 = r0
        L49:
            if (r5 >= 0) goto L4c
            r5 = r3
        L4c:
            if (r5 == r2) goto L4f
            goto L50
        L4f:
            r5 = r3
        L50:
            float r6 = r7.minHeight
            float r1 = r1.m5698getUnspecifiedD9Ej5fM()
            boolean r1 = androidx.compose.ui.unit.Dp.m5683equalsimpl0(r6, r1)
            if (r1 != 0) goto L6b
            float r1 = r7.minHeight
            int r8 = r8.mo279roundToPx0680j_4(r1)
            if (r8 <= r4) goto L65
            r8 = r4
        L65:
            if (r8 >= 0) goto L68
            r8 = r3
        L68:
            if (r8 == r2) goto L6b
            r3 = r8
        L6b:
            long r0 = androidx.compose.ui.unit.ConstraintsKt.Constraints(r5, r0, r3, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.SizeNode.m586getTargetConstraintsOenEA2s(androidx.compose.ui.unit.Density):long");
    }

    public final boolean getEnforceIncoming() {
        return this.enforceIncoming;
    }

    /* JADX INFO: renamed from: getMaxHeight-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMaxHeight() {
        return this.maxHeight;
    }

    /* JADX INFO: renamed from: getMaxWidth-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMaxWidth() {
        return this.maxWidth;
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
        long jM586getTargetConstraintsOenEA2s = m586getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        return Constraints.m5643getHasFixedHeightimpl(jM586getTargetConstraintsOenEA2s) ? Constraints.m5645getMaxHeightimpl(jM586getTargetConstraintsOenEA2s) : ConstraintsKt.m5659constrainHeightK40F9xA(jM586getTargetConstraintsOenEA2s, intrinsicMeasurable.maxIntrinsicHeight(i10));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        long jM586getTargetConstraintsOenEA2s = m586getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        return Constraints.m5644getHasFixedWidthimpl(jM586getTargetConstraintsOenEA2s) ? Constraints.m5646getMaxWidthimpl(jM586getTargetConstraintsOenEA2s) : ConstraintsKt.m5660constrainWidthK40F9xA(jM586getTargetConstraintsOenEA2s, intrinsicMeasurable.maxIntrinsicWidth(i10));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        int iM5648getMinWidthimpl;
        int iM5646getMaxWidthimpl;
        int iM5647getMinHeightimpl;
        int iM5645getMaxHeightimpl;
        long jConstraints;
        long jM586getTargetConstraintsOenEA2s = m586getTargetConstraintsOenEA2s(measureScope);
        if (this.enforceIncoming) {
            jConstraints = ConstraintsKt.m5658constrainN9IONVI(j10, jM586getTargetConstraintsOenEA2s);
        } else {
            float f10 = this.minWidth;
            Dp.Companion companion = Dp.INSTANCE;
            if (Dp.m5683equalsimpl0(f10, companion.m5698getUnspecifiedD9Ej5fM())) {
                iM5648getMinWidthimpl = Constraints.m5648getMinWidthimpl(j10);
                int iM5646getMaxWidthimpl2 = Constraints.m5646getMaxWidthimpl(jM586getTargetConstraintsOenEA2s);
                if (iM5648getMinWidthimpl > iM5646getMaxWidthimpl2) {
                    iM5648getMinWidthimpl = iM5646getMaxWidthimpl2;
                }
            } else {
                iM5648getMinWidthimpl = Constraints.m5648getMinWidthimpl(jM586getTargetConstraintsOenEA2s);
            }
            if (Dp.m5683equalsimpl0(this.maxWidth, companion.m5698getUnspecifiedD9Ej5fM())) {
                iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j10);
                int iM5648getMinWidthimpl2 = Constraints.m5648getMinWidthimpl(jM586getTargetConstraintsOenEA2s);
                if (iM5646getMaxWidthimpl < iM5648getMinWidthimpl2) {
                    iM5646getMaxWidthimpl = iM5648getMinWidthimpl2;
                }
            } else {
                iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(jM586getTargetConstraintsOenEA2s);
            }
            if (Dp.m5683equalsimpl0(this.minHeight, companion.m5698getUnspecifiedD9Ej5fM())) {
                iM5647getMinHeightimpl = Constraints.m5647getMinHeightimpl(j10);
                int iM5645getMaxHeightimpl2 = Constraints.m5645getMaxHeightimpl(jM586getTargetConstraintsOenEA2s);
                if (iM5647getMinHeightimpl > iM5645getMaxHeightimpl2) {
                    iM5647getMinHeightimpl = iM5645getMaxHeightimpl2;
                }
            } else {
                iM5647getMinHeightimpl = Constraints.m5647getMinHeightimpl(jM586getTargetConstraintsOenEA2s);
            }
            if (Dp.m5683equalsimpl0(this.maxHeight, companion.m5698getUnspecifiedD9Ej5fM())) {
                iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(j10);
                int iM5647getMinHeightimpl2 = Constraints.m5647getMinHeightimpl(jM586getTargetConstraintsOenEA2s);
                if (iM5645getMaxHeightimpl < iM5647getMinHeightimpl2) {
                    iM5645getMaxHeightimpl = iM5647getMinHeightimpl2;
                }
            } else {
                iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(jM586getTargetConstraintsOenEA2s);
            }
            jConstraints = ConstraintsKt.Constraints(iM5648getMinWidthimpl, iM5646getMaxWidthimpl, iM5647getMinHeightimpl, iM5645getMaxHeightimpl);
        }
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(jConstraints);
        return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new SizeNode$measure$1(placeableMo4631measureBRTryo0), 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        long jM586getTargetConstraintsOenEA2s = m586getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        return Constraints.m5643getHasFixedHeightimpl(jM586getTargetConstraintsOenEA2s) ? Constraints.m5645getMaxHeightimpl(jM586getTargetConstraintsOenEA2s) : ConstraintsKt.m5659constrainHeightK40F9xA(jM586getTargetConstraintsOenEA2s, intrinsicMeasurable.minIntrinsicHeight(i10));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        long jM586getTargetConstraintsOenEA2s = m586getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        return Constraints.m5644getHasFixedWidthimpl(jM586getTargetConstraintsOenEA2s) ? Constraints.m5646getMaxWidthimpl(jM586getTargetConstraintsOenEA2s) : ConstraintsKt.m5660constrainWidthK40F9xA(jM586getTargetConstraintsOenEA2s, intrinsicMeasurable.minIntrinsicWidth(i10));
    }

    public final void setEnforceIncoming(boolean z) {
        this.enforceIncoming = z;
    }

    /* JADX INFO: renamed from: setMaxHeight-0680j_4, reason: not valid java name */
    public final void m591setMaxHeight0680j_4(float f10) {
        this.maxHeight = f10;
    }

    /* JADX INFO: renamed from: setMaxWidth-0680j_4, reason: not valid java name */
    public final void m592setMaxWidth0680j_4(float f10) {
        this.maxWidth = f10;
    }

    /* JADX INFO: renamed from: setMinHeight-0680j_4, reason: not valid java name */
    public final void m593setMinHeight0680j_4(float f10) {
        this.minHeight = f10;
    }

    /* JADX INFO: renamed from: setMinWidth-0680j_4, reason: not valid java name */
    public final void m594setMinWidth0680j_4(float f10) {
        this.minWidth = f10;
    }

    public /* synthetic */ SizeNode(float f10, float f11, float f12, float f13, boolean z, int i10, h hVar) {
        this((i10 & 1) != 0 ? Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM() : f10, (i10 & 2) != 0 ? Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM() : f11, (i10 & 4) != 0 ? Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM() : f12, (i10 & 8) != 0 ? Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM() : f13, z, null);
    }

    private SizeNode(float f10, float f11, float f12, float f13, boolean z) {
        this.minWidth = f10;
        this.minHeight = f11;
        this.maxWidth = f12;
        this.maxHeight = f13;
        this.enforceIncoming = z;
    }
}
