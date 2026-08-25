package androidx.compose.ui.node;

import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LookaheadLayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\b \u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\u0010\u001a\u00020\rH\u0010¢\u0006\u0004\b\u000e\u0010\u000fJ=\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0019\u0010\u0018\u001a\u0015\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\r\u0018\u00010\u0015¢\u0006\u0002\b\u0017H\u0004ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0011H\u0000ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\rH\u0014¢\u0006\u0004\b\u001f\u0010\u000fJ)\u0010(\u001a\u00020%2\u0006\u0010!\u001a\u00020 2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0086\bø\u0001\u0000¢\u0006\u0004\b&\u0010'J\u0017\u0010*\u001a\u00020\t2\u0006\u0010)\u001a\u00020\tH\u0016¢\u0006\u0004\b*\u0010+J\u0017\u0010,\u001a\u00020\t2\u0006\u0010)\u001a\u00020\tH\u0016¢\u0006\u0004\b,\u0010+J\u0017\u0010.\u001a\u00020\t2\u0006\u0010-\u001a\u00020\tH\u0016¢\u0006\u0004\b.\u0010+J\u0017\u0010/\u001a\u00020\t2\u0006\u0010-\u001a\u00020\tH\u0016¢\u0006\u0004\b/\u0010+J\u001d\u00103\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u0000H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u00102J\u001a\u00105\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002ø\u0001\u0000¢\u0006\u0004\b4\u0010\u001dR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u00106\u001a\u0004\b7\u00108R(\u0010\u0012\u001a\u00020\u00118\u0016@\u0016X\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0012\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010\u001dR$\u0010>\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t\u0018\u00010=8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010?R\u0017\u0010A\u001a\u00020@8\u0006¢\u0006\f\n\u0004\bA\u0010B\u001a\u0004\bC\u0010DR(\u0010F\u001a\u0004\u0018\u00010#2\b\u0010E\u001a\u0004\u0018\u00010#8\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\bF\u0010G\"\u0004\bH\u0010IR&\u0010J\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t0=8\u0004X\u0084\u0004¢\u0006\f\n\u0004\bJ\u0010?\u001a\u0004\bK\u0010LR\u0016\u0010O\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bM\u0010NR\u0014\u0010S\u001a\u00020P8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u0014\u0010V\u001a\u00020#8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\bT\u0010UR\u0014\u0010W\u001a\u00020P8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bW\u0010RR\u0014\u0010[\u001a\u00020X8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bY\u0010ZR\u0014\u0010^\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\\\u0010]R\u0014\u0010`\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b_\u0010]R\u0016\u0010b\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\ba\u0010NR\u0014\u0010f\u001a\u00020c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bd\u0010eR\u0014\u0010j\u001a\u00020g8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bh\u0010iR\u0014\u0010n\u001a\u00020k8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bl\u0010mR\u0016\u0010r\u001a\u0004\u0018\u00010o8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bp\u0010q\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006s"}, d2 = {"Landroidx/compose/ui/node/LookaheadDelegate;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "Landroidx/compose/ui/node/NodeCoordinator;", "coordinator", "<init>", "(Landroidx/compose/ui/node/NodeCoordinator;)V", "Landroidx/compose/ui/layout/AlignmentLine;", "alignmentLine", "", "getCachedAlignmentLine$ui_release", "(Landroidx/compose/ui/layout/AlignmentLine;)I", "getCachedAlignmentLine", "Lx6/t0;", "replace$ui_release", "()V", "replace", "Landroidx/compose/ui/unit/IntOffset;", "position", "", "zIndex", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lx6/n;", "layerBlock", "placeAt-f8xVGno", "(JFLr7/l;)V", "placeAt", "placeSelfApparentToRealOffset--gyyYBs$ui_release", "(J)V", "placeSelfApparentToRealOffset", "placeChildren", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Lkotlin/Function0;", "Landroidx/compose/ui/layout/MeasureResult;", "block", "Landroidx/compose/ui/layout/Placeable;", "performingMeasure-K40F9xA", "(JLr7/a;)Landroidx/compose/ui/layout/Placeable;", "performingMeasure", "height", "minIntrinsicWidth", "(I)I", "maxIntrinsicWidth", "width", "minIntrinsicHeight", "maxIntrinsicHeight", "ancestor", "positionIn-Bjo55l4$ui_release", "(Landroidx/compose/ui/node/LookaheadDelegate;)J", "positionIn", "placeSelf--gyyYBs", "placeSelf", "Landroidx/compose/ui/node/NodeCoordinator;", "getCoordinator", "()Landroidx/compose/ui/node/NodeCoordinator;", "J", "getPosition-nOcc-ac", "()J", "setPosition--gyyYBs", "", "oldAlignmentLines", "Ljava/util/Map;", "Landroidx/compose/ui/layout/LookaheadLayoutCoordinates;", "lookaheadLayoutCoordinates", "Landroidx/compose/ui/layout/LookaheadLayoutCoordinates;", "getLookaheadLayoutCoordinates", "()Landroidx/compose/ui/layout/LookaheadLayoutCoordinates;", "result", "_measureResult", "Landroidx/compose/ui/layout/MeasureResult;", "set_measureResult", "(Landroidx/compose/ui/layout/MeasureResult;)V", "cachedAlignmentLinesMap", "getCachedAlignmentLinesMap", "()Ljava/util/Map;", "getChild", "()Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "child", "", "getHasMeasureResult", "()Z", "hasMeasureResult", "getMeasureResult$ui_release", "()Landroidx/compose/ui/layout/MeasureResult;", "measureResult", "isLookingAhead", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "getDensity", "()F", "density", "getFontScale", "fontScale", "getParent", "parent", "Landroidx/compose/ui/node/LayoutNode;", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "layoutNode", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "coordinates", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "getAlignmentLinesOwner", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "alignmentLinesOwner", "", "getParentData", "()Ljava/lang/Object;", "parentData", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class LookaheadDelegate extends LookaheadCapablePlaceable implements Measurable {
    public static final int $stable = 0;
    private MeasureResult _measureResult;
    private final NodeCoordinator coordinator;
    private Map<AlignmentLine, Integer> oldAlignmentLines;
    private long position = IntOffset.INSTANCE.m5812getZeronOccac();
    private final LookaheadLayoutCoordinates lookaheadLayoutCoordinates = new LookaheadLayoutCoordinates(this);
    private final Map<AlignmentLine, Integer> cachedAlignmentLinesMap = new LinkedHashMap();

    public LookaheadDelegate(NodeCoordinator nodeCoordinator) {
        this.coordinator = nodeCoordinator;
    }

    /* JADX INFO: renamed from: placeSelf--gyyYBs, reason: not valid java name */
    private final void m4786placeSelfgyyYBs(long position) {
        if (IntOffset.m5801equalsimpl0(getPosition(), position)) {
            return;
        }
        m4790setPositiongyyYBs(position);
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate$ui_release = getLayoutNode().getLayoutDelegate().getLookaheadPassDelegate();
        if (lookaheadPassDelegate$ui_release != null) {
            lookaheadPassDelegate$ui_release.notifyChildrenUsingCoordinatesWhilePlacing();
        }
        invalidateAlignmentLinesFromPositionChange(this.coordinator);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void set_measureResult(MeasureResult measureResult) {
        t0 t0Var;
        Map<AlignmentLine, Integer> map;
        if (measureResult != null) {
            m4667setMeasuredSizeozmzZPI(IntSizeKt.IntSize(measureResult.getWidth(), measureResult.getHeight()));
            t0Var = t0.f22605a;
        } else {
            t0Var = null;
        }
        if (t0Var == null) {
            m4667setMeasuredSizeozmzZPI(IntSize.INSTANCE.m5849getZeroYbymL2g());
        }
        if (!p.a(this._measureResult, measureResult) && measureResult != null && ((((map = this.oldAlignmentLines) != null && !map.isEmpty()) || !measureResult.getAlignmentLines().isEmpty()) && !p.a(measureResult.getAlignmentLines(), this.oldAlignmentLines))) {
            getAlignmentLinesOwner().getAlignmentLines().onAlignmentsChanged();
            Map linkedHashMap = this.oldAlignmentLines;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap();
                this.oldAlignmentLines = linkedHashMap;
            }
            linkedHashMap.clear();
            linkedHashMap.putAll(measureResult.getAlignmentLines());
        }
        this._measureResult = measureResult;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public AlignmentLinesOwner getAlignmentLinesOwner() {
        return this.coordinator.getLayoutNode().getLayoutDelegate().getLookaheadAlignmentLinesOwner$ui_release();
    }

    public final int getCachedAlignmentLine$ui_release(AlignmentLine alignmentLine) {
        Integer num = this.cachedAlignmentLinesMap.get(alignmentLine);
        if (num != null) {
            return num.intValue();
        }
        return Integer.MIN_VALUE;
    }

    public final Map<AlignmentLine, Integer> getCachedAlignmentLinesMap() {
        return this.cachedAlignmentLinesMap;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LookaheadCapablePlaceable getChild() {
        NodeCoordinator wrapped = this.coordinator.getWrapped();
        if (wrapped != null) {
            return wrapped.getLookaheadDelegate();
        }
        return null;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LayoutCoordinates getCoordinates() {
        return this.lookaheadLayoutCoordinates;
    }

    public final NodeCoordinator getCoordinator() {
        return this.coordinator;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.coordinator.getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.coordinator.getFontScale();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public boolean getHasMeasureResult() {
        return this._measureResult != null;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public LayoutDirection getLayoutDirection() {
        return this.coordinator.getLayoutDirection();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable, androidx.compose.ui.node.MeasureScopeWithLayoutNode
    public LayoutNode getLayoutNode() {
        return this.coordinator.getLayoutNode();
    }

    public final LookaheadLayoutCoordinates getLookaheadLayoutCoordinates() {
        return this.lookaheadLayoutCoordinates;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public MeasureResult getMeasureResult$ui_release() {
        MeasureResult measureResult = this._measureResult;
        if (measureResult != null) {
            return measureResult;
        }
        throw new IllegalStateException("LookaheadDelegate has not been measured yet when measureResult is requested.");
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LookaheadCapablePlaceable getParent() {
        NodeCoordinator wrappedBy = this.coordinator.getWrappedBy();
        if (wrappedBy != null) {
            return wrappedBy.getLookaheadDelegate();
        }
        return null;
    }

    @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
    public Object getParentData() {
        return this.coordinator.getParentData();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    /* JADX INFO: renamed from: getPosition-nOcc-ac, reason: from getter */
    public long getPosition() {
        return this.position;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable, androidx.compose.ui.layout.IntrinsicMeasureScope
    public boolean isLookingAhead() {
        return true;
    }

    public int maxIntrinsicHeight(int width) {
        return this.coordinator.getWrapped().getLookaheadDelegate().maxIntrinsicHeight(width);
    }

    public int maxIntrinsicWidth(int height) {
        return this.coordinator.getWrapped().getLookaheadDelegate().maxIntrinsicWidth(height);
    }

    public int minIntrinsicHeight(int width) {
        return this.coordinator.getWrapped().getLookaheadDelegate().minIntrinsicHeight(width);
    }

    public int minIntrinsicWidth(int height) {
        return this.coordinator.getWrapped().getLookaheadDelegate().minIntrinsicWidth(height);
    }

    /* JADX INFO: renamed from: performingMeasure-K40F9xA, reason: not valid java name */
    public final Placeable m4787performingMeasureK40F9xA(long constraints, r7.a<? extends MeasureResult> block) {
        m4668setMeasurementConstraintsBRTryo0(constraints);
        set_measureResult((MeasureResult) block.invoke());
        return this;
    }

    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public final void mo4632placeAtf8xVGno(long position, float zIndex, l<? super GraphicsLayerScope, t0> layerBlock) {
        m4786placeSelfgyyYBs(position);
        if (getIsShallowPlacing()) {
            return;
        }
        placeChildren();
    }

    public void placeChildren() {
        getMeasureResult$ui_release().placeChildren();
    }

    /* JADX INFO: renamed from: placeSelfApparentToRealOffset--gyyYBs$ui_release, reason: not valid java name */
    public final void m4788placeSelfApparentToRealOffsetgyyYBs$ui_release(long position) {
        long apparentToRealOffset = getApparentToRealOffset();
        m4786placeSelfgyyYBs(a0.c.c(IntOffset.m5803getYimpl(position), IntOffset.m5802getXimpl(apparentToRealOffset) + IntOffset.m5802getXimpl(position), apparentToRealOffset));
    }

    /* JADX INFO: renamed from: positionIn-Bjo55l4$ui_release, reason: not valid java name */
    public final long m4789positionInBjo55l4$ui_release(LookaheadDelegate ancestor) {
        long jM5812getZeronOccac = IntOffset.INSTANCE.m5812getZeronOccac();
        for (LookaheadDelegate lookaheadDelegate = this; !lookaheadDelegate.equals(ancestor); lookaheadDelegate = lookaheadDelegate.coordinator.getWrappedBy().getLookaheadDelegate()) {
            long position = lookaheadDelegate.getPosition();
            jM5812getZeronOccac = a0.c.c(IntOffset.m5803getYimpl(jM5812getZeronOccac), IntOffset.m5802getXimpl(position) + IntOffset.m5802getXimpl(jM5812getZeronOccac), position);
        }
        return jM5812getZeronOccac;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public void replace$ui_release() {
        mo4632placeAtf8xVGno(getPosition(), 0.0f, null);
    }

    /* JADX INFO: renamed from: setPosition--gyyYBs, reason: not valid java name */
    public void m4790setPositiongyyYBs(long j10) {
        this.position = j10;
    }
}
