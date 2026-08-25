package androidx.compose.ui.node;

import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.node.NodeMeasuringIntrinsics;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u001c\u0010\b\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0003H\u0016J&\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0005\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u001c\u0010\u0012\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u001c\u0010\u0013\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0003H\u0016ø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DelegatableNode;", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface LayoutModifierNode extends DelegatableNode {

    /* JADX INFO: renamed from: androidx.compose.ui.node.LayoutModifierNode$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static int a(final LayoutModifierNode layoutModifierNode, IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
            return NodeMeasuringIntrinsics.INSTANCE.maxHeight$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.maxIntrinsicHeight.1
                @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo4637measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
                    return LayoutModifierNode.this.mo55measure3p2s80s(measureScope, measurable, j10);
                }
            }, intrinsicMeasureScope, intrinsicMeasurable, i10);
        }

        public static int b(final LayoutModifierNode layoutModifierNode, IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
            return NodeMeasuringIntrinsics.INSTANCE.maxWidth$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.maxIntrinsicWidth.1
                @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo4637measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
                    return LayoutModifierNode.this.mo55measure3p2s80s(measureScope, measurable, j10);
                }
            }, intrinsicMeasureScope, intrinsicMeasurable, i10);
        }

        public static int c(final LayoutModifierNode layoutModifierNode, IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
            return NodeMeasuringIntrinsics.INSTANCE.minHeight$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.minIntrinsicHeight.1
                @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo4637measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
                    return LayoutModifierNode.this.mo55measure3p2s80s(measureScope, measurable, j10);
                }
            }, intrinsicMeasureScope, intrinsicMeasurable, i10);
        }

        public static int d(final LayoutModifierNode layoutModifierNode, IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
            return NodeMeasuringIntrinsics.INSTANCE.minWidth$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.minIntrinsicWidth.1
                @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo4637measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
                    return LayoutModifierNode.this.mo55measure3p2s80s(measureScope, measurable, j10);
                }
            }, intrinsicMeasureScope, intrinsicMeasurable, i10);
        }
    }

    int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10);

    int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10);

    /* JADX INFO: renamed from: measure-3p2s80s */
    MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10);

    int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10);

    int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10);
}
