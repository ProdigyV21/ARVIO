package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import ka.m0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B)\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ&\u0010\u001f\u001a\u00020 *\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0016ø\u0001\u0000¢\u0006\u0004\b&\u0010'R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u0011X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R&\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006("}, d2 = {"Landroidx/compose/material3/TabIndicatorOffsetNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "tabPositionsState", "Landroidx/compose/runtime/State;", "", "Landroidx/compose/material3/TabPosition;", "selectedTabIndex", "", "followContentSize", "", "(Landroidx/compose/runtime/State;IZ)V", "getFollowContentSize", "()Z", "setFollowContentSize", "(Z)V", "initialOffset", "Landroidx/compose/ui/unit/Dp;", "initialWidth", "offsetAnimatable", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "getSelectedTabIndex", "()I", "setSelectedTabIndex", "(I)V", "getTabPositionsState", "()Landroidx/compose/runtime/State;", "setTabPositionsState", "(Landroidx/compose/runtime/State;)V", "widthAnimatable", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TabIndicatorOffsetNode extends Modifier.Node implements LayoutModifierNode {
    public static final int $stable = 8;
    private boolean followContentSize;
    private Dp initialOffset;
    private Dp initialWidth;
    private Animatable<Dp, AnimationVector1D> offsetAnimatable;
    private int selectedTabIndex;
    private State<? extends List<TabPosition>> tabPositionsState;
    private Animatable<Dp, AnimationVector1D> widthAnimatable;

    public TabIndicatorOffsetNode(State<? extends List<TabPosition>> state, int i10, boolean z) {
        this.tabPositionsState = state;
        this.selectedTabIndex = i10;
        this.followContentSize = z;
    }

    public final boolean getFollowContentSize() {
        return this.followContentSize;
    }

    public final int getSelectedTabIndex() {
        return this.selectedTabIndex;
    }

    public final State<List<TabPosition>> getTabPositionsState() {
        return this.tabPositionsState;
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
        long jM5637copyZbe2FdA$default;
        if (this.tabPositionsState.getValue().isEmpty()) {
            return MeasureScope.CC.q(measureScope, 0, 0, null, TabIndicatorOffsetNode$measure$1.INSTANCE, 4, null);
        }
        float contentWidth = this.tabPositionsState.getValue().get(this.selectedTabIndex).getContentWidth();
        if (this.followContentSize) {
            Dp dp = this.initialWidth;
            if (dp != null) {
                Animatable<Dp, AnimationVector1D> animatable = this.widthAnimatable;
                if (animatable == null) {
                    Animatable<Dp, AnimationVector1D> animatable2 = new Animatable<>(dp, VectorConvertersKt.getVectorConverter(Dp.INSTANCE), null, null, 12, null);
                    this.widthAnimatable = animatable2;
                    animatable = animatable2;
                }
                if (!Dp.m5683equalsimpl0(contentWidth, animatable.getTargetValue().m5692unboximpl())) {
                    m0.p(getCoroutineScope(), null, 0, new TabIndicatorOffsetNode$measure$2(animatable, contentWidth, null), 3);
                }
            } else {
                this.initialWidth = Dp.m5676boximpl(contentWidth);
            }
        }
        float left = this.tabPositionsState.getValue().get(this.selectedTabIndex).getLeft();
        Dp dp2 = this.initialOffset;
        if (dp2 != null) {
            Animatable<Dp, AnimationVector1D> animatable3 = this.offsetAnimatable;
            if (animatable3 == null) {
                animatable3 = new Animatable<>(dp2, VectorConvertersKt.getVectorConverter(Dp.INSTANCE), null, null, 12, null);
                this.offsetAnimatable = animatable3;
            }
            if (!Dp.m5683equalsimpl0(left, animatable3.getTargetValue().m5692unboximpl())) {
                m0.p(getCoroutineScope(), null, 0, new TabIndicatorOffsetNode$measure$3(animatable3, left, null), 3);
            }
        } else {
            this.initialOffset = Dp.m5676boximpl(left);
        }
        Animatable<Dp, AnimationVector1D> animatable4 = this.offsetAnimatable;
        if (animatable4 != null) {
            left = animatable4.getValue().m5692unboximpl();
        }
        float f10 = left;
        if (this.followContentSize) {
            Animatable<Dp, AnimationVector1D> animatable5 = this.widthAnimatable;
            if (animatable5 != null) {
                contentWidth = animatable5.getValue().m5692unboximpl();
            }
            jM5637copyZbe2FdA$default = Constraints.m5637copyZbe2FdA$default(j10, measureScope.mo279roundToPx0680j_4(contentWidth), measureScope.mo279roundToPx0680j_4(contentWidth), 0, 0, 12, null);
        } else {
            jM5637copyZbe2FdA$default = j10;
        }
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(jM5637copyZbe2FdA$default);
        return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), Constraints.m5645getMaxHeightimpl(j10), null, new TabIndicatorOffsetNode$measure$4(placeableMo4631measureBRTryo0, measureScope, f10, j10), 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.c(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.d(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    public final void setFollowContentSize(boolean z) {
        this.followContentSize = z;
    }

    public final void setSelectedTabIndex(int i10) {
        this.selectedTabIndex = i10;
    }

    public final void setTabPositionsState(State<? extends List<TabPosition>> state) {
        this.tabPositionsState = state;
    }
}
