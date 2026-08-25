package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001:\u0002ghB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\t\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\u000b\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\n\u0010\bJ\u000f\u0010\r\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\f\u0010\bJ\u000f\u0010\u000f\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u000e\u0010\bJ\r\u0010\u0010\u001a\u00020\u0006¢\u0006\u0004\b\u0010\u0010\bJ\u000f\u0010\u0012\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0011\u0010\bJ\r\u0010\u0013\u001a\u00020\u0006¢\u0006\u0004\b\u0013\u0010\bJ\r\u0010\u0014\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\bJ\r\u0010\u0015\u001a\u00020\u0006¢\u0006\u0004\b\u0015\u0010\bJ\r\u0010\u0016\u001a\u00020\u0006¢\u0006\u0004\b\u0016\u0010\bJ\u001a\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0017H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0017H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001aR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001eR$\u0010!\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R$\u0010&\u001a\u00020%2\u0006\u0010 \u001a\u00020%8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R$\u0010*\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b*\u0010\"\u001a\u0004\b+\u0010$R$\u0010,\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b,\u0010\"\u001a\u0004\b-\u0010$R\u0016\u0010.\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010\"R$\u0010/\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b/\u0010\"\u001a\u0004\b0\u0010$R$\u00101\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b1\u0010\"\u001a\u0004\b2\u0010$R\u0016\u00103\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u0010\"R\u0016\u00105\u001a\u0002048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u00106R\u0016\u00107\u001a\u0002048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u00106R*\u00109\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u001f8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b9\u0010\"\u001a\u0004\b:\u0010$\"\u0004\b;\u0010<R*\u0010=\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u001f8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b=\u0010\"\u001a\u0004\b>\u0010$\"\u0004\b?\u0010<R*\u0010@\u001a\u0002042\u0006\u00108\u001a\u0002048\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b@\u00106\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001e\u0010F\u001a\u00060ER\u00020\u00008\u0000X\u0080\u0004¢\u0006\f\n\u0004\bF\u0010G\u001a\u0004\bH\u0010IR0\u0010K\u001a\b\u0018\u00010JR\u00020\u00002\f\u0010 \u001a\b\u0018\u00010JR\u00020\u00008\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\bK\u0010L\u001a\u0004\bM\u0010NR\u001c\u0010O\u001a\u00020\u00178\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bO\u0010PR\u001a\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00060Q8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR\u0011\u0010W\u001a\u00020T8F¢\u0006\u0006\u001a\u0004\bU\u0010VR\u0019\u0010Z\u001a\u0004\u0018\u00010\u00178Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bX\u0010YR\u0019\u0010\\\u001a\u0004\u0018\u00010\u00178Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b[\u0010YR\u0014\u0010^\u001a\u0002048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b]\u0010BR\u0014\u0010`\u001a\u0002048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b_\u0010BR\u0014\u0010d\u001a\u00020a8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bb\u0010cR\u0016\u0010f\u001a\u0004\u0018\u00010a8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\be\u0010c\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006i"}, d2 = {"Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;", "", "Landroidx/compose/ui/node/LayoutNode;", "layoutNode", "<init>", "(Landroidx/compose/ui/node/LayoutNode;)V", "Lx6/t0;", "markLayoutPending$ui_release", "()V", "markLayoutPending", "markMeasurePending$ui_release", "markMeasurePending", "markLookaheadLayoutPending$ui_release", "markLookaheadLayoutPending", "markLookaheadMeasurePending$ui_release", "markLookaheadMeasurePending", "onCoordinatesUsed", "ensureLookaheadDelegateCreated$ui_release", "ensureLookaheadDelegateCreated", "updateParentData", "invalidateParentData", "resetAlignmentLines", "markChildrenDirty", "Landroidx/compose/ui/unit/Constraints;", "constraints", "performMeasure-BRTryo0", "(J)V", "performMeasure", "performLookaheadMeasure-BRTryo0", "performLookaheadMeasure", "Landroidx/compose/ui/node/LayoutNode;", "", "<set-?>", "detachedFromParentLookaheadPass", "Z", "getDetachedFromParentLookaheadPass$ui_release", "()Z", "Landroidx/compose/ui/node/LayoutNode$LayoutState;", "layoutState", "Landroidx/compose/ui/node/LayoutNode$LayoutState;", "getLayoutState$ui_release", "()Landroidx/compose/ui/node/LayoutNode$LayoutState;", "measurePending", "getMeasurePending$ui_release", "layoutPending", "getLayoutPending$ui_release", "layoutPendingForAlignment", "lookaheadMeasurePending", "getLookaheadMeasurePending$ui_release", "lookaheadLayoutPending", "getLookaheadLayoutPending$ui_release", "lookaheadLayoutPendingForAlignment", "", "nextChildLookaheadPlaceOrder", "I", "nextChildPlaceOrder", "value", "coordinatesAccessedDuringPlacement", "getCoordinatesAccessedDuringPlacement", "setCoordinatesAccessedDuringPlacement", "(Z)V", "coordinatesAccessedDuringModifierPlacement", "getCoordinatesAccessedDuringModifierPlacement", "setCoordinatesAccessedDuringModifierPlacement", "childrenAccessingCoordinatesDuringPlacement", "getChildrenAccessingCoordinatesDuringPlacement", "()I", "setChildrenAccessingCoordinatesDuringPlacement", "(I)V", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate$MeasurePassDelegate;", "measurePassDelegate", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate$MeasurePassDelegate;", "getMeasurePassDelegate$ui_release", "()Landroidx/compose/ui/node/LayoutNodeLayoutDelegate$MeasurePassDelegate;", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate$LookaheadPassDelegate;", "lookaheadPassDelegate", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate$LookaheadPassDelegate;", "getLookaheadPassDelegate$ui_release", "()Landroidx/compose/ui/node/LayoutNodeLayoutDelegate$LookaheadPassDelegate;", "performMeasureConstraints", "J", "Lkotlin/Function0;", "performMeasureBlock", "Lr7/a;", "Landroidx/compose/ui/node/NodeCoordinator;", "getOuterCoordinator", "()Landroidx/compose/ui/node/NodeCoordinator;", "outerCoordinator", "getLastConstraints-DWUhwKw", "()Landroidx/compose/ui/unit/Constraints;", "lastConstraints", "getLastLookaheadConstraints-DWUhwKw", "lastLookaheadConstraints", "getHeight$ui_release", "height", "getWidth$ui_release", "width", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "getAlignmentLinesOwner$ui_release", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "alignmentLinesOwner", "getLookaheadAlignmentLinesOwner$ui_release", "lookaheadAlignmentLinesOwner", "LookaheadPassDelegate", "MeasurePassDelegate", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LayoutNodeLayoutDelegate {
    public static final int $stable = 8;
    private int childrenAccessingCoordinatesDuringPlacement;
    private boolean coordinatesAccessedDuringModifierPlacement;
    private boolean coordinatesAccessedDuringPlacement;
    private boolean detachedFromParentLookaheadPass;
    private final LayoutNode layoutNode;
    private boolean layoutPending;
    private boolean layoutPendingForAlignment;
    private boolean lookaheadLayoutPending;
    private boolean lookaheadLayoutPendingForAlignment;
    private boolean lookaheadMeasurePending;
    private LookaheadPassDelegate lookaheadPassDelegate;
    private boolean measurePending;
    private int nextChildLookaheadPlaceOrder;
    private int nextChildPlaceOrder;
    private LayoutNode.LayoutState layoutState = LayoutNode.LayoutState.Idle;
    private final MeasurePassDelegate measurePassDelegate = new MeasurePassDelegate();
    private long performMeasureConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null);
    private final r7.a<t0> performMeasureBlock = new LayoutNodeLayoutDelegate$performMeasureBlock$1(this);

    @Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tH\u0016¢\u0006\u0004\b\f\u0010\rJ#\u0010\u0010\u001a\u00020\u00062\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0012\u0010\bJ\u000f\u0010\u0013\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0013\u0010\bJ\r\u0010\u0014\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\bJ\u001a\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0015H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0018\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0015ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ=\u0010'\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 2\u0019\u0010$\u001a\u0015\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000e¢\u0006\u0002\b#H\u0014ø\u0001\u0000¢\u0006\u0004\b%\u0010&J\u0018\u0010)\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\nH\u0096\u0002¢\u0006\u0004\b)\u0010*J\u0017\u0010,\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u000bH\u0016¢\u0006\u0004\b,\u0010-J\u0017\u0010.\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u000bH\u0016¢\u0006\u0004\b.\u0010-J\u0017\u00100\u001a\u00020\u000b2\u0006\u0010/\u001a\u00020\u000bH\u0016¢\u0006\u0004\b0\u0010-J\u0017\u00101\u001a\u00020\u000b2\u0006\u0010/\u001a\u00020\u000bH\u0016¢\u0006\u0004\b1\u0010-J\u0015\u00103\u001a\u00020\u00062\u0006\u00102\u001a\u00020\u001a¢\u0006\u0004\b3\u00104J\r\u00105\u001a\u00020\u0006¢\u0006\u0004\b5\u0010\bJ\r\u00106\u001a\u00020\u001a¢\u0006\u0004\b6\u00107J\u000f\u00109\u001a\u00020\u0006H\u0000¢\u0006\u0004\b8\u0010\bJ\r\u0010:\u001a\u00020\u0006¢\u0006\u0004\b:\u0010\bJ\r\u0010;\u001a\u00020\u0006¢\u0006\u0004\b;\u0010\bJ(\u0010=\u001a\u00020\u00062\u0016\u0010\u000f\u001a\u0012\u0012\b\u0012\u00060\u0000R\u00020<\u0012\u0004\u0012\u00020\u00060\u000eH\u0082\b¢\u0006\u0004\b=\u0010\u0011J\u000f\u0010>\u001a\u00020\u0006H\u0002¢\u0006\u0004\b>\u0010\bJ\u000f\u0010?\u001a\u00020\u0006H\u0002¢\u0006\u0004\b?\u0010\bJ\u0017\u0010B\u001a\u00020\u00062\u0006\u0010A\u001a\u00020@H\u0002¢\u0006\u0004\bB\u0010CJ\u000f\u0010D\u001a\u00020\u0006H\u0002¢\u0006\u0004\bD\u0010\bJ\u000f\u0010E\u001a\u00020\u0006H\u0002¢\u0006\u0004\bE\u0010\bJ\u000f\u0010F\u001a\u00020\u0006H\u0002¢\u0006\u0004\bF\u0010\bJ\u000f\u0010G\u001a\u00020\u0006H\u0002¢\u0006\u0004\bG\u0010\bR\u0016\u0010H\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010IR\u001e\u0010K\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u000b8\u0002@BX\u0082\u000e¢\u0006\u0006\n\u0004\bK\u0010LR\"\u0010M\u001a\u00020\u000b8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bM\u0010L\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\"\u0010S\u001a\u00020R8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bS\u0010T\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR\"\u0010Y\u001a\u00020\u001a8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bY\u0010I\u001a\u0004\bZ\u00107\"\u0004\b[\u00104R\"\u0010\\\u001a\u00020\u001a8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\\\u0010I\u001a\u0004\b]\u00107\"\u0004\b^\u00104R\u0016\u0010_\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b_\u0010IR\u001e\u0010`\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b`\u0010aR*\u0010b\u001a\u00020\u001e2\u0006\u0010J\u001a\u00020\u001e8\u0000@BX\u0080\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\bb\u0010c\u001a\u0004\bd\u0010eR$\u0010f\u001a\u00020 2\u0006\u0010J\u001a\u00020 8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\bf\u0010g\u001a\u0004\bh\u0010iRJ\u0010j\u001a\u0015\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000e¢\u0006\u0002\b#2\u0019\u0010J\u001a\u0015\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000e¢\u0006\u0002\b#8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\bj\u0010k\u001a\u0004\bl\u0010mR\"\u0010n\u001a\u00020\u001a8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bn\u0010I\u001a\u0004\bn\u00107\"\u0004\bo\u00104R\u001a\u0010q\u001a\u00020p8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bq\u0010r\u001a\u0004\bs\u0010tR\u001e\u0010v\u001a\f\u0012\b\u0012\u00060\u0000R\u00020<0u8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bv\u0010wR\"\u0010x\u001a\u00020\u001a8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bx\u0010I\u001a\u0004\by\u00107\"\u0004\bz\u00104R$\u0010{\u001a\u00020\u001a2\u0006\u0010J\u001a\u00020\u001a8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b{\u0010I\u001a\u0004\b|\u00107R\u0016\u0010}\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b}\u0010IR+\u0010\u007f\u001a\u0004\u0018\u00010~2\b\u0010J\u001a\u0004\u0018\u00010~8\u0016@RX\u0096\u000e¢\u0006\u000f\n\u0005\b\u007f\u0010\u0080\u0001\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001R\u0018\u0010\u0083\u0001\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0083\u0001\u0010IR\u001c\u0010\u0087\u0001\u001a\u00070\u0084\u0001R\u00020<8@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001R\u001c\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u00158Fø\u0001\u0000ø\u0001\u0001¢\u0006\b\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001R\u0018\u0010\u008e\u0001\u001a\u00030\u008b\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R\"\u0010\u0092\u0001\u001a\r\u0012\b\u0012\u00060\u0000R\u00020<0\u008f\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0019\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0016\u0010\u0097\u0001\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0096\u0001\u0010OR\u0016\u0010\u0099\u0001\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0098\u0001\u0010O\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u009a\u0001"}, d2 = {"Landroidx/compose/ui/node/LayoutNodeLayoutDelegate$LookaheadPassDelegate;", "Landroidx/compose/ui/layout/Placeable;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "<init>", "(Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;)V", "Lx6/t0;", "layoutChildren", "()V", "", "Landroidx/compose/ui/layout/AlignmentLine;", "", "calculateAlignmentLines", "()Ljava/util/Map;", "Lkotlin/Function1;", "block", "forEachChildAlignmentLinesOwner", "(Lr7/l;)V", "requestLayout", "requestMeasure", "notifyChildrenUsingCoordinatesWhilePlacing", "Landroidx/compose/ui/unit/Constraints;", "constraints", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "measure", "", "remeasure-BRTryo0", "(J)Z", "remeasure", "Landroidx/compose/ui/unit/IntOffset;", "position", "", "zIndex", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lx6/n;", "layerBlock", "placeAt-f8xVGno", "(JFLr7/l;)V", "placeAt", "alignmentLine", "get", "(Landroidx/compose/ui/layout/AlignmentLine;)I", "height", "minIntrinsicWidth", "(I)I", "maxIntrinsicWidth", "width", "minIntrinsicHeight", "maxIntrinsicHeight", "forceRequest", "invalidateIntrinsicsParent", "(Z)V", "invalidateParentData", "updateParentData", "()Z", "onNodePlaced$ui_release", "onNodePlaced", "replace", "onNodeDetached", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;", "forEachChildDelegate", "checkChildrenPlaceOrderForUpdates", "markSubtreeAsNotPlaced", "Landroidx/compose/ui/node/LayoutNode;", "node", "trackLookaheadMeasurementByParent", "(Landroidx/compose/ui/node/LayoutNode;)V", "onIntrinsicsQueried", "clearPlaceOrder", "markNodeAndSubtreeAsPlaced", "onBeforeLayoutChildren", "relayoutWithoutParentInProgress", "Z", "<set-?>", "previousPlaceOrder", "I", "placeOrder", "getPlaceOrder$ui_release", "()I", "setPlaceOrder$ui_release", "(I)V", "Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "measuredByParent", "Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "getMeasuredByParent$ui_release", "()Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "setMeasuredByParent$ui_release", "(Landroidx/compose/ui/node/LayoutNode$UsageByParent;)V", "duringAlignmentLinesQuery", "getDuringAlignmentLinesQuery$ui_release", "setDuringAlignmentLinesQuery$ui_release", "placedOnce", "getPlacedOnce$ui_release", "setPlacedOnce$ui_release", "measuredOnce", "lookaheadConstraints", "Landroidx/compose/ui/unit/Constraints;", "lastPosition", "J", "getLastPosition-nOcc-ac$ui_release", "()J", "lastZIndex", "F", "getLastZIndex$ui_release", "()F", "lastLayerBlock", "Lr7/l;", "getLastLayerBlock$ui_release", "()Lr7/l;", "isPlaced", "setPlaced", "Landroidx/compose/ui/node/AlignmentLines;", "alignmentLines", "Landroidx/compose/ui/node/AlignmentLines;", "getAlignmentLines", "()Landroidx/compose/ui/node/AlignmentLines;", "Landroidx/compose/runtime/collection/MutableVector;", "_childDelegates", "Landroidx/compose/runtime/collection/MutableVector;", "childDelegatesDirty", "getChildDelegatesDirty$ui_release", "setChildDelegatesDirty$ui_release", "layingOutChildren", "getLayingOutChildren", "parentDataDirty", "", "parentData", "Ljava/lang/Object;", "getParentData", "()Ljava/lang/Object;", "onNodePlacedCalled", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate$MeasurePassDelegate;", "getMeasurePassDelegate$ui_release", "()Landroidx/compose/ui/node/LayoutNodeLayoutDelegate$MeasurePassDelegate;", "measurePassDelegate", "getLastConstraints-DWUhwKw", "()Landroidx/compose/ui/unit/Constraints;", "lastConstraints", "Landroidx/compose/ui/node/NodeCoordinator;", "getInnerCoordinator", "()Landroidx/compose/ui/node/NodeCoordinator;", "innerCoordinator", "", "getChildDelegates$ui_release", "()Ljava/util/List;", "childDelegates", "getParentAlignmentLinesOwner", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "parentAlignmentLinesOwner", "getMeasuredWidth", "measuredWidth", "getMeasuredHeight", "measuredHeight", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class LookaheadPassDelegate extends Placeable implements Measurable, AlignmentLinesOwner {
        private boolean duringAlignmentLinesQuery;
        private boolean isPlaced;
        private l<? super GraphicsLayerScope, t0> lastLayerBlock;
        private float lastZIndex;
        private boolean layingOutChildren;
        private Constraints lookaheadConstraints;
        private boolean measuredOnce;
        private boolean onNodePlacedCalled;
        private boolean placedOnce;
        private boolean relayoutWithoutParentInProgress;
        private int previousPlaceOrder = Integer.MAX_VALUE;
        private int placeOrder = Integer.MAX_VALUE;
        private LayoutNode.UsageByParent measuredByParent = LayoutNode.UsageByParent.NotUsed;
        private long lastPosition = IntOffset.INSTANCE.m5812getZeronOccac();
        private final AlignmentLines alignmentLines = new LookaheadAlignmentLines(this);
        private final MutableVector<LookaheadPassDelegate> _childDelegates = new MutableVector<>(new LookaheadPassDelegate[16], 0);
        private boolean childDelegatesDirty = true;
        private boolean parentDataDirty = true;
        private Object parentData = getMeasurePassDelegate$ui_release().getParentData();

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                int[] iArr = new int[LayoutNode.LayoutState.values().length];
                try {
                    iArr[LayoutNode.LayoutState.LookaheadMeasuring.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[LayoutNode.LayoutState.Measuring.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[LayoutNode.LayoutState.LayingOut.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[LayoutNode.LayoutState.LookaheadLayingOut.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                $EnumSwitchMapping$0 = iArr;
                int[] iArr2 = new int[LayoutNode.UsageByParent.values().length];
                try {
                    iArr2[LayoutNode.UsageByParent.InMeasureBlock.ordinal()] = 1;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr2[LayoutNode.UsageByParent.InLayoutBlock.ordinal()] = 2;
                } catch (NoSuchFieldError unused6) {
                }
                $EnumSwitchMapping$1 = iArr2;
            }
        }

        public LookaheadPassDelegate() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void checkChildrenPlaceOrderForUpdates() {
            MutableVector<LayoutNode> mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
            int size = mutableVector.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector.getContent();
                int i10 = 0;
                do {
                    LookaheadPassDelegate lookaheadPassDelegate = content[i10].getLayoutDelegate().getLookaheadPassDelegate();
                    int i11 = lookaheadPassDelegate.previousPlaceOrder;
                    int i12 = lookaheadPassDelegate.placeOrder;
                    if (i11 != i12 && i12 == Integer.MAX_VALUE) {
                        lookaheadPassDelegate.markSubtreeAsNotPlaced();
                    }
                    i10++;
                } while (i10 < size);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void clearPlaceOrder() {
            int i10 = 0;
            LayoutNodeLayoutDelegate.this.nextChildLookaheadPlaceOrder = 0;
            MutableVector<LayoutNode> mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
            int size = mutableVector.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector.getContent();
                do {
                    LookaheadPassDelegate lookaheadPassDelegate = content[i10].getLayoutDelegate().getLookaheadPassDelegate();
                    lookaheadPassDelegate.previousPlaceOrder = lookaheadPassDelegate.placeOrder;
                    lookaheadPassDelegate.placeOrder = Integer.MAX_VALUE;
                    if (lookaheadPassDelegate.measuredByParent == LayoutNode.UsageByParent.InLayoutBlock) {
                        lookaheadPassDelegate.measuredByParent = LayoutNode.UsageByParent.NotUsed;
                    }
                    i10++;
                } while (i10 < size);
            }
        }

        private final void forEachChildDelegate(l<? super LookaheadPassDelegate, t0> block) {
            MutableVector<LayoutNode> mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
            int size = mutableVector.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector.getContent();
                int i10 = 0;
                do {
                    block.invoke(content[i10].getLayoutDelegate().getLookaheadPassDelegate());
                    i10++;
                } while (i10 < size);
            }
        }

        private final void markNodeAndSubtreeAsPlaced() {
            boolean isPlaced = getIsPlaced();
            setPlaced(true);
            int i10 = 0;
            if (!isPlaced && LayoutNodeLayoutDelegate.this.getLookaheadMeasurePending()) {
                LayoutNode.requestLookaheadRemeasure$ui_release$default(LayoutNodeLayoutDelegate.this.layoutNode, true, false, 2, null);
            }
            MutableVector<LayoutNode> mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
            int size = mutableVector.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector.getContent();
                do {
                    LayoutNode layoutNode = content[i10];
                    if (layoutNode.getPlaceOrder$ui_release() != Integer.MAX_VALUE) {
                        layoutNode.getLookaheadPassDelegate$ui_release().markNodeAndSubtreeAsPlaced();
                        layoutNode.rescheduleRemeasureOrRelayout$ui_release(layoutNode);
                    }
                    i10++;
                } while (i10 < size);
            }
        }

        private final void markSubtreeAsNotPlaced() {
            if (getIsPlaced()) {
                int i10 = 0;
                setPlaced(false);
                MutableVector<LayoutNode> mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
                int size = mutableVector.getSize();
                if (size > 0) {
                    LayoutNode[] content = mutableVector.getContent();
                    do {
                        content[i10].getLayoutDelegate().getLookaheadPassDelegate().markSubtreeAsNotPlaced();
                        i10++;
                    } while (i10 < size);
                }
            }
        }

        private final void onBeforeLayoutChildren() {
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui_release();
            int size = mutableVector.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector.getContent();
                int i10 = 0;
                do {
                    LayoutNode layoutNode2 = content[i10];
                    if (layoutNode2.getLookaheadMeasurePending$ui_release() && layoutNode2.getMeasuredByParentInLookahead$ui_release() == LayoutNode.UsageByParent.InMeasureBlock && layoutNode2.getLayoutDelegate().getLookaheadPassDelegate().m4774remeasureBRTryo0(layoutNode2.getLayoutDelegate().m4771getLastLookaheadConstraintsDWUhwKw().getValue())) {
                        LayoutNode.requestLookaheadRemeasure$ui_release$default(layoutNodeLayoutDelegate.layoutNode, false, false, 3, null);
                    }
                    i10++;
                } while (i10 < size);
            }
        }

        private final void onIntrinsicsQueried() {
            LayoutNode.requestLookaheadRemeasure$ui_release$default(LayoutNodeLayoutDelegate.this.layoutNode, false, false, 3, null);
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if (parent$ui_release == null || LayoutNodeLayoutDelegate.this.layoutNode.getIntrinsicsUsageByParent() != LayoutNode.UsageByParent.NotUsed) {
                return;
            }
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            int i10 = WhenMappings.$EnumSwitchMapping$0[parent$ui_release.getLayoutState$ui_release().ordinal()];
            layoutNode.setIntrinsicsUsageByParent$ui_release(i10 != 2 ? i10 != 3 ? parent$ui_release.getIntrinsicsUsageByParent() : LayoutNode.UsageByParent.InLayoutBlock : LayoutNode.UsageByParent.InMeasureBlock);
        }

        private final void trackLookaheadMeasurementByParent(LayoutNode node) {
            LayoutNode.UsageByParent usageByParent;
            LayoutNode parent$ui_release = node.getParent$ui_release();
            if (parent$ui_release == null) {
                this.measuredByParent = LayoutNode.UsageByParent.NotUsed;
                return;
            }
            if (this.measuredByParent != LayoutNode.UsageByParent.NotUsed && !node.getCanMultiMeasure()) {
                throw new IllegalStateException("measure() may not be called multiple times on the same Measurable. If you want to get the content size of the Measurable before calculating the final constraints, please use methods like minIntrinsicWidth()/maxIntrinsicWidth() and minIntrinsicHeight()/maxIntrinsicHeight()");
            }
            int i10 = WhenMappings.$EnumSwitchMapping$0[parent$ui_release.getLayoutState$ui_release().ordinal()];
            if (i10 == 1 || i10 == 2) {
                usageByParent = LayoutNode.UsageByParent.InMeasureBlock;
            } else {
                if (i10 != 3 && i10 != 4) {
                    throw new IllegalStateException("Measurable could be only measured from the parent's measure or layout block. Parents state is " + parent$ui_release.getLayoutState$ui_release());
                }
                usageByParent = LayoutNode.UsageByParent.InLayoutBlock;
            }
            this.measuredByParent = usageByParent;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public Map<AlignmentLine, Integer> calculateAlignmentLines() {
            if (!this.duringAlignmentLinesQuery) {
                if (LayoutNodeLayoutDelegate.this.getLayoutState() == LayoutNode.LayoutState.LookaheadMeasuring) {
                    getAlignmentLines().setUsedByModifierMeasurement$ui_release(true);
                    if (getAlignmentLines().getDirty()) {
                        LayoutNodeLayoutDelegate.this.markLookaheadLayoutPending$ui_release();
                    }
                } else {
                    getAlignmentLines().setUsedByModifierLayout$ui_release(true);
                }
            }
            LookaheadDelegate lookaheadDelegate = getInnerCoordinator().getLookaheadDelegate();
            if (lookaheadDelegate != null) {
                lookaheadDelegate.setPlacingForAlignment$ui_release(true);
            }
            layoutChildren();
            LookaheadDelegate lookaheadDelegate2 = getInnerCoordinator().getLookaheadDelegate();
            if (lookaheadDelegate2 != null) {
                lookaheadDelegate2.setPlacingForAlignment$ui_release(false);
            }
            return getAlignmentLines().getLastCalculation();
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public void forEachChildAlignmentLinesOwner(l<? super AlignmentLinesOwner, t0> block) {
            MutableVector<LayoutNode> mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
            int size = mutableVector.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector.getContent();
                int i10 = 0;
                do {
                    block.invoke(content[i10].getLayoutDelegate().getLookaheadAlignmentLinesOwner$ui_release());
                    i10++;
                } while (i10 < size);
            }
        }

        @Override // androidx.compose.ui.layout.Measured
        public int get(AlignmentLine alignmentLine) {
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if ((parent$ui_release != null ? parent$ui_release.getLayoutState$ui_release() : null) == LayoutNode.LayoutState.LookaheadMeasuring) {
                getAlignmentLines().setUsedDuringParentMeasurement$ui_release(true);
            } else {
                LayoutNode parent$ui_release2 = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
                if ((parent$ui_release2 != null ? parent$ui_release2.getLayoutState$ui_release() : null) == LayoutNode.LayoutState.LookaheadLayingOut) {
                    getAlignmentLines().setUsedDuringParentLayout$ui_release(true);
                }
            }
            this.duringAlignmentLinesQuery = true;
            int i10 = LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate().get(alignmentLine);
            this.duringAlignmentLinesQuery = false;
            return i10;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public AlignmentLines getAlignmentLines() {
            return this.alignmentLines;
        }

        public final List<LookaheadPassDelegate> getChildDelegates$ui_release() {
            LayoutNodeLayoutDelegate.this.layoutNode.getChildren$ui_release();
            if (!this.childDelegatesDirty) {
                return this._childDelegates.asMutableList();
            }
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            MutableVector<LookaheadPassDelegate> mutableVector = this._childDelegates;
            MutableVector<LayoutNode> mutableVector2 = layoutNode.get_children$ui_release();
            int size = mutableVector2.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector2.getContent();
                int i10 = 0;
                do {
                    LayoutNode layoutNode2 = content[i10];
                    if (mutableVector.getSize() <= i10) {
                        mutableVector.add(layoutNode2.getLayoutDelegate().getLookaheadPassDelegate());
                    } else {
                        mutableVector.set(i10, layoutNode2.getLayoutDelegate().getLookaheadPassDelegate());
                    }
                    i10++;
                } while (i10 < size);
            }
            mutableVector.removeRange(layoutNode.getChildren$ui_release().size(), mutableVector.getSize());
            this.childDelegatesDirty = false;
            return this._childDelegates.asMutableList();
        }

        /* JADX INFO: renamed from: getChildDelegatesDirty$ui_release, reason: from getter */
        public final boolean getChildDelegatesDirty() {
            return this.childDelegatesDirty;
        }

        /* JADX INFO: renamed from: getDuringAlignmentLinesQuery$ui_release, reason: from getter */
        public final boolean getDuringAlignmentLinesQuery() {
            return this.duringAlignmentLinesQuery;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public NodeCoordinator getInnerCoordinator() {
            return LayoutNodeLayoutDelegate.this.layoutNode.getInnerCoordinator$ui_release();
        }

        /* JADX INFO: renamed from: getLastConstraints-DWUhwKw, reason: not valid java name and from getter */
        public final Constraints getLookaheadConstraints() {
            return this.lookaheadConstraints;
        }

        public final l<GraphicsLayerScope, t0> getLastLayerBlock$ui_release() {
            return this.lastLayerBlock;
        }

        /* JADX INFO: renamed from: getLastPosition-nOcc-ac$ui_release, reason: not valid java name and from getter */
        public final long getLastPosition() {
            return this.lastPosition;
        }

        /* JADX INFO: renamed from: getLastZIndex$ui_release, reason: from getter */
        public final float getLastZIndex() {
            return this.lastZIndex;
        }

        public final boolean getLayingOutChildren() {
            return this.layingOutChildren;
        }

        public final MeasurePassDelegate getMeasurePassDelegate$ui_release() {
            return LayoutNodeLayoutDelegate.this.getMeasurePassDelegate();
        }

        /* JADX INFO: renamed from: getMeasuredByParent$ui_release, reason: from getter */
        public final LayoutNode.UsageByParent getMeasuredByParent() {
            return this.measuredByParent;
        }

        @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured
        public int getMeasuredHeight() {
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate().getMeasuredHeight();
        }

        @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured
        public int getMeasuredWidth() {
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate().getMeasuredWidth();
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public AlignmentLinesOwner getParentAlignmentLinesOwner() {
            LayoutNodeLayoutDelegate layoutDelegate;
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if (parent$ui_release == null || (layoutDelegate = parent$ui_release.getLayoutDelegate()) == null) {
                return null;
            }
            return layoutDelegate.getLookaheadAlignmentLinesOwner$ui_release();
        }

        @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
        public Object getParentData() {
            return this.parentData;
        }

        /* JADX INFO: renamed from: getPlaceOrder$ui_release, reason: from getter */
        public final int getPlaceOrder() {
            return this.placeOrder;
        }

        /* JADX INFO: renamed from: getPlacedOnce$ui_release, reason: from getter */
        public final boolean getPlacedOnce() {
            return this.placedOnce;
        }

        public final void invalidateIntrinsicsParent(boolean forceRequest) {
            LayoutNode parent$ui_release;
            LayoutNode parent$ui_release2 = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            LayoutNode.UsageByParent intrinsicsUsageByParent = LayoutNodeLayoutDelegate.this.layoutNode.getIntrinsicsUsageByParent();
            if (parent$ui_release2 == null || intrinsicsUsageByParent == LayoutNode.UsageByParent.NotUsed) {
                return;
            }
            while (parent$ui_release2.getIntrinsicsUsageByParent() == intrinsicsUsageByParent && (parent$ui_release = parent$ui_release2.getParent$ui_release()) != null) {
                parent$ui_release2 = parent$ui_release;
            }
            int i10 = WhenMappings.$EnumSwitchMapping$1[intrinsicsUsageByParent.ordinal()];
            if (i10 == 1) {
                if (parent$ui_release2.getLookaheadRoot() != null) {
                    LayoutNode.requestLookaheadRemeasure$ui_release$default(parent$ui_release2, forceRequest, false, 2, null);
                    return;
                } else {
                    LayoutNode.requestRemeasure$ui_release$default(parent$ui_release2, forceRequest, false, 2, null);
                    return;
                }
            }
            if (i10 != 2) {
                throw new IllegalStateException("Intrinsics isn't used by the parent");
            }
            if (parent$ui_release2.getLookaheadRoot() != null) {
                parent$ui_release2.requestLookaheadRelayout$ui_release(forceRequest);
            } else {
                parent$ui_release2.requestRelayout$ui_release(forceRequest);
            }
        }

        public final void invalidateParentData() {
            this.parentDataDirty = true;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        /* JADX INFO: renamed from: isPlaced, reason: from getter */
        public boolean getIsPlaced() {
            return this.isPlaced;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public void layoutChildren() {
            this.layingOutChildren = true;
            getAlignmentLines().recalculateQueryOwner();
            if (LayoutNodeLayoutDelegate.this.getLookaheadLayoutPending()) {
                onBeforeLayoutChildren();
            }
            LookaheadDelegate lookaheadDelegate = getInnerCoordinator().getLookaheadDelegate();
            if (LayoutNodeLayoutDelegate.this.lookaheadLayoutPendingForAlignment || (!this.duringAlignmentLinesQuery && !lookaheadDelegate.getIsPlacingForAlignment() && LayoutNodeLayoutDelegate.this.getLookaheadLayoutPending())) {
                LayoutNodeLayoutDelegate.this.lookaheadLayoutPending = false;
                LayoutNode.LayoutState layoutState = LayoutNodeLayoutDelegate.this.getLayoutState();
                LayoutNodeLayoutDelegate.this.layoutState = LayoutNode.LayoutState.LookaheadLayingOut;
                Owner ownerRequireOwner = LayoutNodeKt.requireOwner(LayoutNodeLayoutDelegate.this.layoutNode);
                LayoutNodeLayoutDelegate.this.setCoordinatesAccessedDuringPlacement(false);
                OwnerSnapshotObserver.observeLayoutSnapshotReads$ui_release$default(ownerRequireOwner.getSnapshotObserver(), LayoutNodeLayoutDelegate.this.layoutNode, false, new LayoutNodeLayoutDelegate$LookaheadPassDelegate$layoutChildren$1(this, lookaheadDelegate, LayoutNodeLayoutDelegate.this), 2, null);
                LayoutNodeLayoutDelegate.this.layoutState = layoutState;
                if (LayoutNodeLayoutDelegate.this.getCoordinatesAccessedDuringPlacement() && lookaheadDelegate.getIsPlacingForAlignment()) {
                    requestLayout();
                }
                LayoutNodeLayoutDelegate.this.lookaheadLayoutPendingForAlignment = false;
            }
            if (getAlignmentLines().getUsedDuringParentLayout()) {
                getAlignmentLines().setPreviousUsedDuringParentLayout$ui_release(true);
            }
            if (getAlignmentLines().getDirty() && getAlignmentLines().getRequired$ui_release()) {
                getAlignmentLines().recalculate();
            }
            this.layingOutChildren = false;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicHeight(int width) {
            onIntrinsicsQueried();
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate().maxIntrinsicHeight(width);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicWidth(int height) {
            onIntrinsicsQueried();
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate().maxIntrinsicWidth(height);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x002b  */
        @Override // androidx.compose.ui.layout.Measurable
        /* JADX INFO: renamed from: measure-BRTryo0 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public androidx.compose.ui.layout.Placeable mo4631measureBRTryo0(long r4) {
            /*
                r3 = this;
                androidx.compose.ui.node.LayoutNodeLayoutDelegate r0 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.this
                androidx.compose.ui.node.LayoutNode r0 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.access$getLayoutNode$p(r0)
                androidx.compose.ui.node.LayoutNode r0 = r0.getParent$ui_release()
                r1 = 0
                if (r0 == 0) goto L12
                androidx.compose.ui.node.LayoutNode$LayoutState r0 = r0.getLayoutState$ui_release()
                goto L13
            L12:
                r0 = r1
            L13:
                androidx.compose.ui.node.LayoutNode$LayoutState r2 = androidx.compose.ui.node.LayoutNode.LayoutState.LookaheadMeasuring
                if (r0 == r2) goto L2b
                androidx.compose.ui.node.LayoutNodeLayoutDelegate r0 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.this
                androidx.compose.ui.node.LayoutNode r0 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.access$getLayoutNode$p(r0)
                androidx.compose.ui.node.LayoutNode r0 = r0.getParent$ui_release()
                if (r0 == 0) goto L27
                androidx.compose.ui.node.LayoutNode$LayoutState r1 = r0.getLayoutState$ui_release()
            L27:
                androidx.compose.ui.node.LayoutNode$LayoutState r0 = androidx.compose.ui.node.LayoutNode.LayoutState.LookaheadLayingOut
                if (r1 != r0) goto L31
            L2b:
                androidx.compose.ui.node.LayoutNodeLayoutDelegate r0 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.this
                r1 = 0
                androidx.compose.ui.node.LayoutNodeLayoutDelegate.access$setDetachedFromParentLookaheadPass$p(r0, r1)
            L31:
                androidx.compose.ui.node.LayoutNodeLayoutDelegate r0 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.this
                androidx.compose.ui.node.LayoutNode r0 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.access$getLayoutNode$p(r0)
                r3.trackLookaheadMeasurementByParent(r0)
                androidx.compose.ui.node.LayoutNodeLayoutDelegate r0 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.this
                androidx.compose.ui.node.LayoutNode r0 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.access$getLayoutNode$p(r0)
                androidx.compose.ui.node.LayoutNode$UsageByParent r0 = r0.getIntrinsicsUsageByParent()
                androidx.compose.ui.node.LayoutNode$UsageByParent r1 = androidx.compose.ui.node.LayoutNode.UsageByParent.NotUsed
                if (r0 != r1) goto L51
                androidx.compose.ui.node.LayoutNodeLayoutDelegate r0 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.this
                androidx.compose.ui.node.LayoutNode r0 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.access$getLayoutNode$p(r0)
                r0.clearSubtreeIntrinsicsUsage$ui_release()
            L51:
                r3.m4774remeasureBRTryo0(r4)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LayoutNodeLayoutDelegate.LookaheadPassDelegate.mo4631measureBRTryo0(long):androidx.compose.ui.layout.Placeable");
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicHeight(int width) {
            onIntrinsicsQueried();
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate().minIntrinsicHeight(width);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicWidth(int height) {
            onIntrinsicsQueried();
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate().minIntrinsicWidth(height);
        }

        public final void notifyChildrenUsingCoordinatesWhilePlacing() {
            MutableVector<LayoutNode> mutableVector;
            int size;
            if (LayoutNodeLayoutDelegate.this.getChildrenAccessingCoordinatesDuringPlacement() <= 0 || (size = (mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release()).getSize()) <= 0) {
                return;
            }
            LayoutNode[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                LayoutNode layoutNode = content[i10];
                LayoutNodeLayoutDelegate layoutDelegate = layoutNode.getLayoutDelegate();
                if ((layoutDelegate.getCoordinatesAccessedDuringPlacement() || layoutDelegate.getCoordinatesAccessedDuringModifierPlacement()) && !layoutDelegate.getLayoutPending()) {
                    LayoutNode.requestLookaheadRelayout$ui_release$default(layoutNode, false, 1, null);
                }
                LookaheadPassDelegate lookaheadPassDelegate = layoutDelegate.getLookaheadPassDelegate();
                if (lookaheadPassDelegate != null) {
                    lookaheadPassDelegate.notifyChildrenUsingCoordinatesWhilePlacing();
                }
                i10++;
            } while (i10 < size);
        }

        public final void onNodeDetached() {
            this.placeOrder = Integer.MAX_VALUE;
            this.previousPlaceOrder = Integer.MAX_VALUE;
            setPlaced(false);
        }

        public final void onNodePlaced$ui_release() {
            this.onNodePlacedCalled = true;
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if (!getIsPlaced()) {
                markNodeAndSubtreeAsPlaced();
                if (this.relayoutWithoutParentInProgress && parent$ui_release != null) {
                    LayoutNode.requestLookaheadRelayout$ui_release$default(parent$ui_release, false, 1, null);
                }
            }
            if (parent$ui_release == null) {
                this.placeOrder = 0;
            } else if (!this.relayoutWithoutParentInProgress && (parent$ui_release.getLayoutState$ui_release() == LayoutNode.LayoutState.LayingOut || parent$ui_release.getLayoutState$ui_release() == LayoutNode.LayoutState.LookaheadLayingOut)) {
                if (this.placeOrder != Integer.MAX_VALUE) {
                    throw new IllegalStateException("Place was called on a node which was placed already");
                }
                this.placeOrder = parent$ui_release.getLayoutDelegate().nextChildLookaheadPlaceOrder;
                parent$ui_release.getLayoutDelegate().nextChildLookaheadPlaceOrder++;
            }
            layoutChildren();
        }

        @Override // androidx.compose.ui.layout.Placeable
        /* JADX INFO: renamed from: placeAt-f8xVGno */
        public void mo4632placeAtf8xVGno(long position, float zIndex, l<? super GraphicsLayerScope, t0> layerBlock) {
            if (LayoutNodeLayoutDelegate.this.layoutNode.getIsDeactivated()) {
                throw new IllegalArgumentException("place is called on a deactivated node");
            }
            LayoutNodeLayoutDelegate.this.layoutState = LayoutNode.LayoutState.LookaheadLayingOut;
            this.placedOnce = true;
            this.onNodePlacedCalled = false;
            if (!IntOffset.m5801equalsimpl0(position, this.lastPosition)) {
                if (LayoutNodeLayoutDelegate.this.getCoordinatesAccessedDuringModifierPlacement() || LayoutNodeLayoutDelegate.this.getCoordinatesAccessedDuringPlacement()) {
                    LayoutNodeLayoutDelegate.this.lookaheadLayoutPending = true;
                }
                notifyChildrenUsingCoordinatesWhilePlacing();
            }
            Owner ownerRequireOwner = LayoutNodeKt.requireOwner(LayoutNodeLayoutDelegate.this.layoutNode);
            if (LayoutNodeLayoutDelegate.this.getLookaheadLayoutPending() || !getIsPlaced()) {
                LayoutNodeLayoutDelegate.this.setCoordinatesAccessedDuringModifierPlacement(false);
                getAlignmentLines().setUsedByModifierLayout$ui_release(false);
                OwnerSnapshotObserver.observeLayoutModifierSnapshotReads$ui_release$default(ownerRequireOwner.getSnapshotObserver(), LayoutNodeLayoutDelegate.this.layoutNode, false, new LayoutNodeLayoutDelegate$LookaheadPassDelegate$placeAt$2(LayoutNodeLayoutDelegate.this, ownerRequireOwner, position), 2, null);
            } else {
                LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate().m4788placeSelfApparentToRealOffsetgyyYBs$ui_release(position);
                onNodePlaced$ui_release();
            }
            this.lastPosition = position;
            this.lastZIndex = zIndex;
            this.lastLayerBlock = layerBlock;
            LayoutNodeLayoutDelegate.this.layoutState = LayoutNode.LayoutState.Idle;
        }

        /* JADX INFO: renamed from: remeasure-BRTryo0, reason: not valid java name */
        public final boolean m4774remeasureBRTryo0(long constraints) {
            if (LayoutNodeLayoutDelegate.this.layoutNode.getIsDeactivated()) {
                throw new IllegalArgumentException("measure is called on a deactivated node");
            }
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            LayoutNodeLayoutDelegate.this.layoutNode.setCanMultiMeasure$ui_release(LayoutNodeLayoutDelegate.this.layoutNode.getCanMultiMeasure() || (parent$ui_release != null && parent$ui_release.getCanMultiMeasure()));
            if (!LayoutNodeLayoutDelegate.this.layoutNode.getLookaheadMeasurePending$ui_release()) {
                Constraints constraints2 = this.lookaheadConstraints;
                if (constraints2 == null ? false : Constraints.m5639equalsimpl0(constraints2.getValue(), constraints)) {
                    Owner owner = LayoutNodeLayoutDelegate.this.layoutNode.getOwner();
                    if (owner != null) {
                        owner.forceMeasureTheSubtree(LayoutNodeLayoutDelegate.this.layoutNode, true);
                    }
                    LayoutNodeLayoutDelegate.this.layoutNode.resetSubtreeIntrinsicsUsage$ui_release();
                    return false;
                }
            }
            this.lookaheadConstraints = Constraints.m5634boximpl(constraints);
            m4668setMeasurementConstraintsBRTryo0(constraints);
            getAlignmentLines().setUsedByModifierMeasurement$ui_release(false);
            forEachChildAlignmentLinesOwner(LayoutNodeLayoutDelegate$LookaheadPassDelegate$remeasure$2.INSTANCE);
            long measuredSize = this.measuredOnce ? getMeasuredSize() : IntSizeKt.IntSize(Integer.MIN_VALUE, Integer.MIN_VALUE);
            this.measuredOnce = true;
            LookaheadDelegate lookaheadDelegate = LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate();
            if (!(lookaheadDelegate != null)) {
                throw new IllegalStateException("Lookahead result from lookaheadRemeasure cannot be null");
            }
            LayoutNodeLayoutDelegate.this.m4768performLookaheadMeasureBRTryo0(constraints);
            m4667setMeasuredSizeozmzZPI(IntSizeKt.IntSize(lookaheadDelegate.getWidth(), lookaheadDelegate.getHeight()));
            return (IntSize.m5844getWidthimpl(measuredSize) == lookaheadDelegate.getWidth() && IntSize.m5843getHeightimpl(measuredSize) == lookaheadDelegate.getHeight()) ? false : true;
        }

        public final void replace() {
            LayoutNode parent$ui_release;
            try {
                this.relayoutWithoutParentInProgress = true;
                if (!this.placedOnce) {
                    throw new IllegalStateException("replace() called on item that was not placed");
                }
                this.onNodePlacedCalled = false;
                boolean isPlaced = getIsPlaced();
                mo4632placeAtf8xVGno(this.lastPosition, 0.0f, null);
                if (isPlaced && !this.onNodePlacedCalled && (parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release()) != null) {
                    LayoutNode.requestLookaheadRelayout$ui_release$default(parent$ui_release, false, 1, null);
                }
            } finally {
                this.relayoutWithoutParentInProgress = false;
            }
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public void requestLayout() {
            LayoutNode.requestLookaheadRelayout$ui_release$default(LayoutNodeLayoutDelegate.this.layoutNode, false, 1, null);
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public void requestMeasure() {
            LayoutNode.requestLookaheadRemeasure$ui_release$default(LayoutNodeLayoutDelegate.this.layoutNode, false, false, 3, null);
        }

        public final void setChildDelegatesDirty$ui_release(boolean z) {
            this.childDelegatesDirty = z;
        }

        public final void setDuringAlignmentLinesQuery$ui_release(boolean z) {
            this.duringAlignmentLinesQuery = z;
        }

        public final void setMeasuredByParent$ui_release(LayoutNode.UsageByParent usageByParent) {
            this.measuredByParent = usageByParent;
        }

        public final void setPlaceOrder$ui_release(int i10) {
            this.placeOrder = i10;
        }

        public void setPlaced(boolean z) {
            this.isPlaced = z;
        }

        public final void setPlacedOnce$ui_release(boolean z) {
            this.placedOnce = z;
        }

        public final boolean updateParentData() {
            if ((getParentData() == null && LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate().getParentData() == null) || !this.parentDataDirty) {
                return false;
            }
            this.parentDataDirty = false;
            this.parentData = LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate().getParentData();
            return true;
        }
    }

    @Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010$\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\t\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\n\u0010\bJ\u000f\u0010\f\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u000b\u0010\bJ\u001a\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\rø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0096\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ=\u0010%\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0019\u0010\"\u001a\u0015\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001f¢\u0006\u0002\b!H\u0014ø\u0001\u0000¢\u0006\u0004\b#\u0010$J\r\u0010&\u001a\u00020\u0006¢\u0006\u0004\b&\u0010\bJ\u0017\u0010(\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\u0018H\u0016¢\u0006\u0004\b(\u0010)J\u0017\u0010*\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\u0018H\u0016¢\u0006\u0004\b*\u0010)J\u0017\u0010,\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\u0018H\u0016¢\u0006\u0004\b,\u0010)J\u0017\u0010-\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\u0018H\u0016¢\u0006\u0004\b-\u0010)J\r\u0010.\u001a\u00020\u0006¢\u0006\u0004\b.\u0010\bJ\r\u0010/\u001a\u00020\u0012¢\u0006\u0004\b/\u00100J\u001b\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u001801H\u0016¢\u0006\u0004\b2\u00103J#\u00105\u001a\u00020\u00062\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u001fH\u0016¢\u0006\u0004\b5\u00106J\u000f\u00107\u001a\u00020\u0006H\u0016¢\u0006\u0004\b7\u0010\bJ\u000f\u00108\u001a\u00020\u0006H\u0016¢\u0006\u0004\b8\u0010\bJ\r\u00109\u001a\u00020\u0006¢\u0006\u0004\b9\u0010\bJ\u0015\u0010;\u001a\u00020\u00062\u0006\u0010:\u001a\u00020\u0012¢\u0006\u0004\b;\u0010<J\r\u0010=\u001a\u00020\u0006¢\u0006\u0004\b=\u0010\bJ\r\u0010>\u001a\u00020\u0006¢\u0006\u0004\b>\u0010\bJ\r\u0010?\u001a\u00020\u0006¢\u0006\u0004\b?\u0010\bJ\u000f\u0010@\u001a\u00020\u0006H\u0002¢\u0006\u0004\b@\u0010\bJ\u000f\u0010A\u001a\u00020\u0006H\u0002¢\u0006\u0004\bA\u0010\bJ\u000f\u0010B\u001a\u00020\u0006H\u0002¢\u0006\u0004\bB\u0010\bJ\u000f\u0010C\u001a\u00020\u0006H\u0002¢\u0006\u0004\bC\u0010\bJ(\u0010E\u001a\u00020\u00062\u0016\u00104\u001a\u0012\u0012\b\u0012\u00060\u0000R\u00020D\u0012\u0004\u0012\u00020\u00060\u001fH\u0082\b¢\u0006\u0004\bE\u00106J\u0017\u0010H\u001a\u00020\u00062\u0006\u0010G\u001a\u00020FH\u0002¢\u0006\u0004\bH\u0010IJ=\u0010K\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0019\u0010\"\u001a\u0015\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001f¢\u0006\u0002\b!H\u0002ø\u0001\u0000¢\u0006\u0004\bJ\u0010$J\u000f\u0010L\u001a\u00020\u0006H\u0002¢\u0006\u0004\bL\u0010\bJ\u000f\u0010M\u001a\u00020\u0006H\u0002¢\u0006\u0004\bM\u0010\bR\u0016\u0010N\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bN\u0010OR$\u0010Q\u001a\u00020\u00182\u0006\u0010P\u001a\u00020\u00188\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010TR$\u0010U\u001a\u00020\u00182\u0006\u0010P\u001a\u00020\u00188\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\bU\u0010R\u001a\u0004\bV\u0010TR\u0016\u0010W\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bW\u0010OR\u0016\u0010X\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bX\u0010OR\"\u0010Z\u001a\u00020Y8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bZ\u0010[\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R\"\u0010`\u001a\u00020\u00128\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b`\u0010O\u001a\u0004\ba\u00100\"\u0004\bb\u0010<R\u001c\u0010c\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bc\u0010dR)\u0010e\u001a\u0015\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001f¢\u0006\u0002\b!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\be\u0010fR\u0016\u0010g\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bg\u0010hR\u0016\u0010i\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bi\u0010OR(\u0010k\u001a\u0004\u0018\u00010j2\b\u0010P\u001a\u0004\u0018\u00010j8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\bk\u0010l\u001a\u0004\bm\u0010nR*\u0010o\u001a\u00020\u00122\u0006\u0010P\u001a\u00020\u00128\u0016@PX\u0096\u000e¢\u0006\u0012\n\u0004\bo\u0010O\u001a\u0004\bo\u00100\"\u0004\bp\u0010<R*\u0010q\u001a\u00020\u00122\u0006\u0010P\u001a\u00020\u00128\u0006@@X\u0086\u000e¢\u0006\u0012\n\u0004\bq\u0010O\u001a\u0004\bq\u00100\"\u0004\br\u0010<R\u001a\u0010t\u001a\u00020s8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bt\u0010u\u001a\u0004\bv\u0010wR\u001e\u0010y\u001a\f\u0012\b\u0012\u00060\u0000R\u00020D0x8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\by\u0010zR\"\u0010{\u001a\u00020\u00128\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b{\u0010O\u001a\u0004\b|\u00100\"\u0004\b}\u0010<R$\u0010~\u001a\u00020\u00122\u0006\u0010P\u001a\u00020\u00128\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b~\u0010O\u001a\u0004\b\u007f\u00100R\u001e\u0010\u0081\u0001\u001a\t\u0012\u0004\u0012\u00020\u00060\u0080\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0081\u0001\u0010\u0082\u0001R&\u0010\u001e\u001a\u00020\u001d2\u0006\u0010P\u001a\u00020\u001d8\u0000@BX\u0080\u000e¢\u0006\u000e\n\u0004\b\u001e\u0010h\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0018\u0010\u0085\u0001\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0085\u0001\u0010OR+\u0010\u0086\u0001\u001a\u0015\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001f¢\u0006\u0002\b!8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0086\u0001\u0010fR\u001e\u0010\u0087\u0001\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0007\n\u0005\b\u0087\u0001\u0010dR\u0018\u0010\u0088\u0001\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0088\u0001\u0010hR\u001e\u0010\u0089\u0001\u001a\t\u0012\u0004\u0012\u00020\u00060\u0080\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0089\u0001\u0010\u0082\u0001R\u001c\u0010\u008c\u0001\u001a\u0004\u0018\u00010\r8Fø\u0001\u0000ø\u0001\u0001¢\u0006\b\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0018\u0010\u0090\u0001\u001a\u00030\u008d\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001R\"\u0010\u0094\u0001\u001a\r\u0012\b\u0012\u00060\u0000R\u00020D0\u0091\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001R\u0016\u0010\u0096\u0001\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0095\u0001\u0010TR\u0016\u0010\u0098\u0001\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0097\u0001\u0010TR\u0019\u0010\u009b\u0001\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0099\u0001\u0010\u009a\u0001\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u009c\u0001"}, d2 = {"Landroidx/compose/ui/node/LayoutNodeLayoutDelegate$MeasurePassDelegate;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/layout/Placeable;", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "<init>", "(Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;)V", "Lx6/t0;", "markDetachedFromParentLookaheadPass$ui_release", "()V", "markDetachedFromParentLookaheadPass", "layoutChildren", "onNodePlaced$ui_release", "onNodePlaced", "Landroidx/compose/ui/unit/Constraints;", "constraints", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "measure", "", "remeasure-BRTryo0", "(J)Z", "remeasure", "Landroidx/compose/ui/layout/AlignmentLine;", "alignmentLine", "", "get", "(Landroidx/compose/ui/layout/AlignmentLine;)I", "Landroidx/compose/ui/unit/IntOffset;", "position", "", "zIndex", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lx6/n;", "layerBlock", "placeAt-f8xVGno", "(JFLr7/l;)V", "placeAt", "replace", "height", "minIntrinsicWidth", "(I)I", "maxIntrinsicWidth", "width", "minIntrinsicHeight", "maxIntrinsicHeight", "invalidateParentData", "updateParentData", "()Z", "", "calculateAlignmentLines", "()Ljava/util/Map;", "block", "forEachChildAlignmentLinesOwner", "(Lr7/l;)V", "requestLayout", "requestMeasure", "notifyChildrenUsingCoordinatesWhilePlacing", "forceRequest", "invalidateIntrinsicsParent", "(Z)V", "onNodeDetached", "measureBasedOnLookahead", "placeBasedOnLookahead", "checkChildrenPlaceOrderForUpdates", "markSubtreeAsNotPlaced", "markNodeAndSubtreeAsPlaced", "clearPlaceOrder", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;", "forEachChildDelegate", "Landroidx/compose/ui/node/LayoutNode;", "node", "trackMeasurementByParent", "(Landroidx/compose/ui/node/LayoutNode;)V", "placeOuterCoordinator-f8xVGno", "placeOuterCoordinator", "onIntrinsicsQueried", "onBeforeLayoutChildren", "relayoutWithoutParentInProgress", "Z", "<set-?>", "previousPlaceOrder", "I", "getPreviousPlaceOrder$ui_release", "()I", "placeOrder", "getPlaceOrder$ui_release", "measuredOnce", "placedOnce", "Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "measuredByParent", "Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "getMeasuredByParent$ui_release", "()Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "setMeasuredByParent$ui_release", "(Landroidx/compose/ui/node/LayoutNode$UsageByParent;)V", "duringAlignmentLinesQuery", "getDuringAlignmentLinesQuery$ui_release", "setDuringAlignmentLinesQuery$ui_release", "lastPosition", "J", "lastLayerBlock", "Lr7/l;", "lastZIndex", "F", "parentDataDirty", "", "parentData", "Ljava/lang/Object;", "getParentData", "()Ljava/lang/Object;", "isPlaced", "setPlaced$ui_release", "isPlacedByParent", "setPlacedByParent$ui_release", "Landroidx/compose/ui/node/AlignmentLines;", "alignmentLines", "Landroidx/compose/ui/node/AlignmentLines;", "getAlignmentLines", "()Landroidx/compose/ui/node/AlignmentLines;", "Landroidx/compose/runtime/collection/MutableVector;", "_childDelegates", "Landroidx/compose/runtime/collection/MutableVector;", "childDelegatesDirty", "getChildDelegatesDirty$ui_release", "setChildDelegatesDirty$ui_release", "layingOutChildren", "getLayingOutChildren", "Lkotlin/Function0;", "layoutChildrenBlock", "Lr7/a;", "getZIndex$ui_release", "()F", "onNodePlacedCalled", "placeOuterCoordinatorLayerBlock", "placeOuterCoordinatorPosition", "placeOuterCoordinatorZIndex", "placeOuterCoordinatorBlock", "getLastConstraints-DWUhwKw", "()Landroidx/compose/ui/unit/Constraints;", "lastConstraints", "Landroidx/compose/ui/node/NodeCoordinator;", "getInnerCoordinator", "()Landroidx/compose/ui/node/NodeCoordinator;", "innerCoordinator", "", "getChildDelegates$ui_release", "()Ljava/util/List;", "childDelegates", "getMeasuredWidth", "measuredWidth", "getMeasuredHeight", "measuredHeight", "getParentAlignmentLinesOwner", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "parentAlignmentLinesOwner", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class MeasurePassDelegate extends Placeable implements Measurable, AlignmentLinesOwner {
        private final MutableVector<MeasurePassDelegate> _childDelegates;
        private final AlignmentLines alignmentLines;
        private boolean childDelegatesDirty;
        private boolean duringAlignmentLinesQuery;
        private boolean isPlaced;
        private boolean isPlacedByParent;
        private l<? super GraphicsLayerScope, t0> lastLayerBlock;
        private long lastPosition;
        private float lastZIndex;
        private boolean layingOutChildren;
        private final r7.a<t0> layoutChildrenBlock;
        private boolean measuredOnce;
        private boolean onNodePlacedCalled;
        private Object parentData;
        private boolean parentDataDirty;
        private final r7.a<t0> placeOuterCoordinatorBlock;
        private l<? super GraphicsLayerScope, t0> placeOuterCoordinatorLayerBlock;
        private long placeOuterCoordinatorPosition;
        private float placeOuterCoordinatorZIndex;
        private boolean placedOnce;
        private boolean relayoutWithoutParentInProgress;
        private float zIndex;
        private int previousPlaceOrder = Integer.MAX_VALUE;
        private int placeOrder = Integer.MAX_VALUE;
        private LayoutNode.UsageByParent measuredByParent = LayoutNode.UsageByParent.NotUsed;

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                int[] iArr = new int[LayoutNode.LayoutState.values().length];
                try {
                    iArr[LayoutNode.LayoutState.Measuring.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[LayoutNode.LayoutState.LayingOut.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
                int[] iArr2 = new int[LayoutNode.UsageByParent.values().length];
                try {
                    iArr2[LayoutNode.UsageByParent.InMeasureBlock.ordinal()] = 1;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr2[LayoutNode.UsageByParent.InLayoutBlock.ordinal()] = 2;
                } catch (NoSuchFieldError unused4) {
                }
                $EnumSwitchMapping$1 = iArr2;
            }
        }

        public MeasurePassDelegate() {
            IntOffset.Companion companion = IntOffset.INSTANCE;
            this.lastPosition = companion.m5812getZeronOccac();
            this.parentDataDirty = true;
            this.alignmentLines = new LayoutNodeAlignmentLines(this);
            this._childDelegates = new MutableVector<>(new MeasurePassDelegate[16], 0);
            this.childDelegatesDirty = true;
            this.layoutChildrenBlock = new LayoutNodeLayoutDelegate$MeasurePassDelegate$layoutChildrenBlock$1(this);
            this.placeOuterCoordinatorPosition = companion.m5812getZeronOccac();
            this.placeOuterCoordinatorBlock = new LayoutNodeLayoutDelegate$MeasurePassDelegate$placeOuterCoordinatorBlock$1(LayoutNodeLayoutDelegate.this, this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void checkChildrenPlaceOrderForUpdates() {
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui_release();
            int size = mutableVector.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector.getContent();
                int i10 = 0;
                do {
                    LayoutNode layoutNode2 = content[i10];
                    if (layoutNode2.getMeasurePassDelegate$ui_release().previousPlaceOrder != layoutNode2.getPlaceOrder$ui_release()) {
                        layoutNode.onZSortedChildrenInvalidated$ui_release();
                        layoutNode.invalidateLayer$ui_release();
                        if (layoutNode2.getPlaceOrder$ui_release() == Integer.MAX_VALUE) {
                            layoutNode2.getMeasurePassDelegate$ui_release().markSubtreeAsNotPlaced();
                        }
                    }
                    i10++;
                } while (i10 < size);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void clearPlaceOrder() {
            LayoutNodeLayoutDelegate.this.nextChildPlaceOrder = 0;
            MutableVector<LayoutNode> mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
            int size = mutableVector.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector.getContent();
                int i10 = 0;
                do {
                    MeasurePassDelegate measurePassDelegate$ui_release = content[i10].getMeasurePassDelegate$ui_release();
                    measurePassDelegate$ui_release.previousPlaceOrder = measurePassDelegate$ui_release.placeOrder;
                    measurePassDelegate$ui_release.placeOrder = Integer.MAX_VALUE;
                    measurePassDelegate$ui_release.isPlacedByParent = false;
                    if (measurePassDelegate$ui_release.measuredByParent == LayoutNode.UsageByParent.InLayoutBlock) {
                        measurePassDelegate$ui_release.measuredByParent = LayoutNode.UsageByParent.NotUsed;
                    }
                    i10++;
                } while (i10 < size);
            }
        }

        private final void forEachChildDelegate(l<? super MeasurePassDelegate, t0> block) {
            MutableVector<LayoutNode> mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
            int size = mutableVector.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector.getContent();
                int i10 = 0;
                do {
                    block.invoke(content[i10].getMeasurePassDelegate$ui_release());
                    i10++;
                } while (i10 < size);
            }
        }

        private final void markNodeAndSubtreeAsPlaced() {
            boolean isPlaced = getIsPlaced();
            setPlaced$ui_release(true);
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            int i10 = 0;
            if (!isPlaced) {
                if (layoutNode.getMeasurePending$ui_release()) {
                    LayoutNode.requestRemeasure$ui_release$default(layoutNode, true, false, 2, null);
                } else if (layoutNode.getLookaheadMeasurePending$ui_release()) {
                    LayoutNode.requestLookaheadRemeasure$ui_release$default(layoutNode, true, false, 2, null);
                }
            }
            NodeCoordinator wrapped = layoutNode.getInnerCoordinator$ui_release().getWrapped();
            for (NodeCoordinator outerCoordinator$ui_release = layoutNode.getOuterCoordinator$ui_release(); !p.a(outerCoordinator$ui_release, wrapped) && outerCoordinator$ui_release != null; outerCoordinator$ui_release = outerCoordinator$ui_release.getWrapped()) {
                if (outerCoordinator$ui_release.getLastLayerDrawingWasSkipped()) {
                    outerCoordinator$ui_release.invalidateLayer();
                }
            }
            MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui_release();
            int size = mutableVector.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector.getContent();
                do {
                    LayoutNode layoutNode2 = content[i10];
                    if (layoutNode2.getPlaceOrder$ui_release() != Integer.MAX_VALUE) {
                        layoutNode2.getMeasurePassDelegate$ui_release().markNodeAndSubtreeAsPlaced();
                        layoutNode.rescheduleRemeasureOrRelayout$ui_release(layoutNode2);
                    }
                    i10++;
                } while (i10 < size);
            }
        }

        private final void markSubtreeAsNotPlaced() {
            if (getIsPlaced()) {
                int i10 = 0;
                setPlaced$ui_release(false);
                MutableVector<LayoutNode> mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
                int size = mutableVector.getSize();
                if (size > 0) {
                    LayoutNode[] content = mutableVector.getContent();
                    do {
                        content[i10].getMeasurePassDelegate$ui_release().markSubtreeAsNotPlaced();
                        i10++;
                    } while (i10 < size);
                }
            }
        }

        private final void onBeforeLayoutChildren() {
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui_release();
            int size = mutableVector.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector.getContent();
                int i10 = 0;
                do {
                    LayoutNode layoutNode2 = content[i10];
                    if (layoutNode2.getMeasurePending$ui_release() && layoutNode2.getMeasuredByParent$ui_release() == LayoutNode.UsageByParent.InMeasureBlock && LayoutNode.m4751remeasure_Sx5XlM$ui_release$default(layoutNode2, null, 1, null)) {
                        LayoutNode.requestRemeasure$ui_release$default(layoutNodeLayoutDelegate.layoutNode, false, false, 3, null);
                    }
                    i10++;
                } while (i10 < size);
            }
        }

        private final void onIntrinsicsQueried() {
            LayoutNode.requestRemeasure$ui_release$default(LayoutNodeLayoutDelegate.this.layoutNode, false, false, 3, null);
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if (parent$ui_release == null || LayoutNodeLayoutDelegate.this.layoutNode.getIntrinsicsUsageByParent() != LayoutNode.UsageByParent.NotUsed) {
                return;
            }
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            int i10 = WhenMappings.$EnumSwitchMapping$0[parent$ui_release.getLayoutState$ui_release().ordinal()];
            layoutNode.setIntrinsicsUsageByParent$ui_release(i10 != 1 ? i10 != 2 ? parent$ui_release.getIntrinsicsUsageByParent() : LayoutNode.UsageByParent.InLayoutBlock : LayoutNode.UsageByParent.InMeasureBlock);
        }

        /* JADX INFO: renamed from: placeOuterCoordinator-f8xVGno, reason: not valid java name */
        private final void m4777placeOuterCoordinatorf8xVGno(long position, float zIndex, l<? super GraphicsLayerScope, t0> layerBlock) {
            if (LayoutNodeLayoutDelegate.this.layoutNode.getIsDeactivated()) {
                throw new IllegalArgumentException("place is called on a deactivated node");
            }
            LayoutNodeLayoutDelegate.this.layoutState = LayoutNode.LayoutState.LayingOut;
            this.lastPosition = position;
            this.lastZIndex = zIndex;
            this.lastLayerBlock = layerBlock;
            this.placedOnce = true;
            this.onNodePlacedCalled = false;
            Owner ownerRequireOwner = LayoutNodeKt.requireOwner(LayoutNodeLayoutDelegate.this.layoutNode);
            if (LayoutNodeLayoutDelegate.this.getLayoutPending() || !getIsPlaced()) {
                getAlignmentLines().setUsedByModifierLayout$ui_release(false);
                LayoutNodeLayoutDelegate.this.setCoordinatesAccessedDuringModifierPlacement(false);
                this.placeOuterCoordinatorLayerBlock = layerBlock;
                this.placeOuterCoordinatorPosition = position;
                this.placeOuterCoordinatorZIndex = zIndex;
                ownerRequireOwner.getSnapshotObserver().observeLayoutModifierSnapshotReads$ui_release(LayoutNodeLayoutDelegate.this.layoutNode, false, this.placeOuterCoordinatorBlock);
                this.placeOuterCoordinatorLayerBlock = null;
            } else {
                LayoutNodeLayoutDelegate.this.getOuterCoordinator().m4827placeSelfApparentToRealOffsetf8xVGno(position, zIndex, layerBlock);
                onNodePlaced$ui_release();
            }
            LayoutNodeLayoutDelegate.this.layoutState = LayoutNode.LayoutState.Idle;
        }

        private final void trackMeasurementByParent(LayoutNode node) {
            LayoutNode.UsageByParent usageByParent;
            LayoutNode parent$ui_release = node.getParent$ui_release();
            if (parent$ui_release == null) {
                this.measuredByParent = LayoutNode.UsageByParent.NotUsed;
                return;
            }
            if (this.measuredByParent != LayoutNode.UsageByParent.NotUsed && !node.getCanMultiMeasure()) {
                throw new IllegalStateException("measure() may not be called multiple times on the same Measurable. If you want to get the content size of the Measurable before calculating the final constraints, please use methods like minIntrinsicWidth()/maxIntrinsicWidth() and minIntrinsicHeight()/maxIntrinsicHeight()");
            }
            int i10 = WhenMappings.$EnumSwitchMapping$0[parent$ui_release.getLayoutState$ui_release().ordinal()];
            if (i10 == 1) {
                usageByParent = LayoutNode.UsageByParent.InMeasureBlock;
            } else {
                if (i10 != 2) {
                    throw new IllegalStateException("Measurable could be only measured from the parent's measure or layout block. Parents state is " + parent$ui_release.getLayoutState$ui_release());
                }
                usageByParent = LayoutNode.UsageByParent.InLayoutBlock;
            }
            this.measuredByParent = usageByParent;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public Map<AlignmentLine, Integer> calculateAlignmentLines() {
            if (!this.duringAlignmentLinesQuery) {
                if (LayoutNodeLayoutDelegate.this.getLayoutState() == LayoutNode.LayoutState.Measuring) {
                    getAlignmentLines().setUsedByModifierMeasurement$ui_release(true);
                    if (getAlignmentLines().getDirty()) {
                        LayoutNodeLayoutDelegate.this.markLayoutPending$ui_release();
                    }
                } else {
                    getAlignmentLines().setUsedByModifierLayout$ui_release(true);
                }
            }
            getInnerCoordinator().setPlacingForAlignment$ui_release(true);
            layoutChildren();
            getInnerCoordinator().setPlacingForAlignment$ui_release(false);
            return getAlignmentLines().getLastCalculation();
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public void forEachChildAlignmentLinesOwner(l<? super AlignmentLinesOwner, t0> block) {
            MutableVector<LayoutNode> mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
            int size = mutableVector.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector.getContent();
                int i10 = 0;
                do {
                    block.invoke(content[i10].getLayoutDelegate().getAlignmentLinesOwner$ui_release());
                    i10++;
                } while (i10 < size);
            }
        }

        @Override // androidx.compose.ui.layout.Measured
        public int get(AlignmentLine alignmentLine) {
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if ((parent$ui_release != null ? parent$ui_release.getLayoutState$ui_release() : null) == LayoutNode.LayoutState.Measuring) {
                getAlignmentLines().setUsedDuringParentMeasurement$ui_release(true);
            } else {
                LayoutNode parent$ui_release2 = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
                if ((parent$ui_release2 != null ? parent$ui_release2.getLayoutState$ui_release() : null) == LayoutNode.LayoutState.LayingOut) {
                    getAlignmentLines().setUsedDuringParentLayout$ui_release(true);
                }
            }
            this.duringAlignmentLinesQuery = true;
            int i10 = LayoutNodeLayoutDelegate.this.getOuterCoordinator().get(alignmentLine);
            this.duringAlignmentLinesQuery = false;
            return i10;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public AlignmentLines getAlignmentLines() {
            return this.alignmentLines;
        }

        public final List<MeasurePassDelegate> getChildDelegates$ui_release() {
            LayoutNodeLayoutDelegate.this.layoutNode.updateChildrenIfDirty$ui_release();
            if (!this.childDelegatesDirty) {
                return this._childDelegates.asMutableList();
            }
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            MutableVector<MeasurePassDelegate> mutableVector = this._childDelegates;
            MutableVector<LayoutNode> mutableVector2 = layoutNode.get_children$ui_release();
            int size = mutableVector2.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector2.getContent();
                int i10 = 0;
                do {
                    LayoutNode layoutNode2 = content[i10];
                    if (mutableVector.getSize() <= i10) {
                        mutableVector.add(layoutNode2.getLayoutDelegate().getMeasurePassDelegate());
                    } else {
                        mutableVector.set(i10, layoutNode2.getLayoutDelegate().getMeasurePassDelegate());
                    }
                    i10++;
                } while (i10 < size);
            }
            mutableVector.removeRange(layoutNode.getChildren$ui_release().size(), mutableVector.getSize());
            this.childDelegatesDirty = false;
            return this._childDelegates.asMutableList();
        }

        /* JADX INFO: renamed from: getChildDelegatesDirty$ui_release, reason: from getter */
        public final boolean getChildDelegatesDirty() {
            return this.childDelegatesDirty;
        }

        /* JADX INFO: renamed from: getDuringAlignmentLinesQuery$ui_release, reason: from getter */
        public final boolean getDuringAlignmentLinesQuery() {
            return this.duringAlignmentLinesQuery;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public NodeCoordinator getInnerCoordinator() {
            return LayoutNodeLayoutDelegate.this.layoutNode.getInnerCoordinator$ui_release();
        }

        /* JADX INFO: renamed from: getLastConstraints-DWUhwKw, reason: not valid java name */
        public final Constraints m4778getLastConstraintsDWUhwKw() {
            if (this.measuredOnce) {
                return Constraints.m5634boximpl(getMeasurementConstraints());
            }
            return null;
        }

        public final boolean getLayingOutChildren() {
            return this.layingOutChildren;
        }

        /* JADX INFO: renamed from: getMeasuredByParent$ui_release, reason: from getter */
        public final LayoutNode.UsageByParent getMeasuredByParent() {
            return this.measuredByParent;
        }

        @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured
        public int getMeasuredHeight() {
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().getMeasuredHeight();
        }

        @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured
        public int getMeasuredWidth() {
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().getMeasuredWidth();
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public AlignmentLinesOwner getParentAlignmentLinesOwner() {
            LayoutNodeLayoutDelegate layoutDelegate;
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if (parent$ui_release == null || (layoutDelegate = parent$ui_release.getLayoutDelegate()) == null) {
                return null;
            }
            return layoutDelegate.getAlignmentLinesOwner$ui_release();
        }

        @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
        public Object getParentData() {
            return this.parentData;
        }

        /* JADX INFO: renamed from: getPlaceOrder$ui_release, reason: from getter */
        public final int getPlaceOrder() {
            return this.placeOrder;
        }

        /* JADX INFO: renamed from: getPreviousPlaceOrder$ui_release, reason: from getter */
        public final int getPreviousPlaceOrder() {
            return this.previousPlaceOrder;
        }

        /* JADX INFO: renamed from: getZIndex$ui_release, reason: from getter */
        public final float getZIndex() {
            return this.zIndex;
        }

        public final void invalidateIntrinsicsParent(boolean forceRequest) {
            LayoutNode parent$ui_release;
            LayoutNode parent$ui_release2 = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            LayoutNode.UsageByParent intrinsicsUsageByParent = LayoutNodeLayoutDelegate.this.layoutNode.getIntrinsicsUsageByParent();
            if (parent$ui_release2 == null || intrinsicsUsageByParent == LayoutNode.UsageByParent.NotUsed) {
                return;
            }
            while (parent$ui_release2.getIntrinsicsUsageByParent() == intrinsicsUsageByParent && (parent$ui_release = parent$ui_release2.getParent$ui_release()) != null) {
                parent$ui_release2 = parent$ui_release;
            }
            int i10 = WhenMappings.$EnumSwitchMapping$1[intrinsicsUsageByParent.ordinal()];
            if (i10 == 1) {
                LayoutNode.requestRemeasure$ui_release$default(parent$ui_release2, forceRequest, false, 2, null);
            } else {
                if (i10 != 2) {
                    throw new IllegalStateException("Intrinsics isn't used by the parent");
                }
                parent$ui_release2.requestRelayout$ui_release(forceRequest);
            }
        }

        public final void invalidateParentData() {
            this.parentDataDirty = true;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        /* JADX INFO: renamed from: isPlaced, reason: from getter */
        public boolean getIsPlaced() {
            return this.isPlaced;
        }

        /* JADX INFO: renamed from: isPlacedByParent, reason: from getter */
        public final boolean getIsPlacedByParent() {
            return this.isPlacedByParent;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public void layoutChildren() {
            this.layingOutChildren = true;
            getAlignmentLines().recalculateQueryOwner();
            if (LayoutNodeLayoutDelegate.this.getLayoutPending()) {
                onBeforeLayoutChildren();
            }
            if (LayoutNodeLayoutDelegate.this.layoutPendingForAlignment || (!this.duringAlignmentLinesQuery && !getInnerCoordinator().getIsPlacingForAlignment() && LayoutNodeLayoutDelegate.this.getLayoutPending())) {
                LayoutNodeLayoutDelegate.this.layoutPending = false;
                LayoutNode.LayoutState layoutState = LayoutNodeLayoutDelegate.this.getLayoutState();
                LayoutNodeLayoutDelegate.this.layoutState = LayoutNode.LayoutState.LayingOut;
                LayoutNodeLayoutDelegate.this.setCoordinatesAccessedDuringPlacement(false);
                LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
                LayoutNodeKt.requireOwner(layoutNode).getSnapshotObserver().observeLayoutSnapshotReads$ui_release(layoutNode, false, this.layoutChildrenBlock);
                LayoutNodeLayoutDelegate.this.layoutState = layoutState;
                if (getInnerCoordinator().getIsPlacingForAlignment() && LayoutNodeLayoutDelegate.this.getCoordinatesAccessedDuringPlacement()) {
                    requestLayout();
                }
                LayoutNodeLayoutDelegate.this.layoutPendingForAlignment = false;
            }
            if (getAlignmentLines().getUsedDuringParentLayout()) {
                getAlignmentLines().setPreviousUsedDuringParentLayout$ui_release(true);
            }
            if (getAlignmentLines().getDirty() && getAlignmentLines().getRequired$ui_release()) {
                getAlignmentLines().recalculate();
            }
            this.layingOutChildren = false;
        }

        public final void markDetachedFromParentLookaheadPass$ui_release() {
            LayoutNodeLayoutDelegate.this.detachedFromParentLookaheadPass = true;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicHeight(int width) {
            onIntrinsicsQueried();
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().maxIntrinsicHeight(width);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicWidth(int height) {
            onIntrinsicsQueried();
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().maxIntrinsicWidth(height);
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* JADX INFO: renamed from: measure-BRTryo0 */
        public Placeable mo4631measureBRTryo0(long constraints) {
            LayoutNode.UsageByParent intrinsicsUsageByParent = LayoutNodeLayoutDelegate.this.layoutNode.getIntrinsicsUsageByParent();
            LayoutNode.UsageByParent usageByParent = LayoutNode.UsageByParent.NotUsed;
            if (intrinsicsUsageByParent == usageByParent) {
                LayoutNodeLayoutDelegate.this.layoutNode.clearSubtreeIntrinsicsUsage$ui_release();
            }
            if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(LayoutNodeLayoutDelegate.this.layoutNode)) {
                LookaheadPassDelegate lookaheadPassDelegate = LayoutNodeLayoutDelegate.this.getLookaheadPassDelegate();
                lookaheadPassDelegate.setMeasuredByParent$ui_release(usageByParent);
                lookaheadPassDelegate.mo4631measureBRTryo0(constraints);
            }
            trackMeasurementByParent(LayoutNodeLayoutDelegate.this.layoutNode);
            m4779remeasureBRTryo0(constraints);
            return this;
        }

        public final void measureBasedOnLookahead() {
            LookaheadPassDelegate lookaheadPassDelegate = LayoutNodeLayoutDelegate.this.getLookaheadPassDelegate();
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if (parent$ui_release == null) {
                throw new IllegalStateException("layoutNode parent is not set");
            }
            if (lookaheadPassDelegate == null) {
                throw new IllegalStateException("invalid lookaheadDelegate");
            }
            if (lookaheadPassDelegate.getMeasuredByParent() == LayoutNode.UsageByParent.InMeasureBlock && parent$ui_release.getLayoutState$ui_release() == LayoutNode.LayoutState.Measuring) {
                mo4631measureBRTryo0(lookaheadPassDelegate.getLookaheadConstraints().getValue());
            } else if (lookaheadPassDelegate.getMeasuredByParent() == LayoutNode.UsageByParent.InLayoutBlock && parent$ui_release.getLayoutState$ui_release() == LayoutNode.LayoutState.LayingOut) {
                mo4631measureBRTryo0(lookaheadPassDelegate.getLookaheadConstraints().getValue());
            }
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicHeight(int width) {
            onIntrinsicsQueried();
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().minIntrinsicHeight(width);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicWidth(int height) {
            onIntrinsicsQueried();
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().minIntrinsicWidth(height);
        }

        public final void notifyChildrenUsingCoordinatesWhilePlacing() {
            MutableVector<LayoutNode> mutableVector;
            int size;
            if (LayoutNodeLayoutDelegate.this.getChildrenAccessingCoordinatesDuringPlacement() <= 0 || (size = (mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release()).getSize()) <= 0) {
                return;
            }
            LayoutNode[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                LayoutNode layoutNode = content[i10];
                LayoutNodeLayoutDelegate layoutDelegate = layoutNode.getLayoutDelegate();
                if ((layoutDelegate.getCoordinatesAccessedDuringPlacement() || layoutDelegate.getCoordinatesAccessedDuringModifierPlacement()) && !layoutDelegate.getLayoutPending()) {
                    LayoutNode.requestRelayout$ui_release$default(layoutNode, false, 1, null);
                }
                layoutDelegate.getMeasurePassDelegate().notifyChildrenUsingCoordinatesWhilePlacing();
                i10++;
            } while (i10 < size);
        }

        public final void onNodeDetached() {
            this.placeOrder = Integer.MAX_VALUE;
            this.previousPlaceOrder = Integer.MAX_VALUE;
            setPlaced$ui_release(false);
        }

        public final void onNodePlaced$ui_release() {
            this.onNodePlacedCalled = true;
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            float zIndex = getInnerCoordinator().getZIndex();
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            NodeCoordinator outerCoordinator$ui_release = layoutNode.getOuterCoordinator$ui_release();
            NodeCoordinator innerCoordinator$ui_release = layoutNode.getInnerCoordinator$ui_release();
            while (outerCoordinator$ui_release != innerCoordinator$ui_release) {
                LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = (LayoutModifierNodeCoordinator) outerCoordinator$ui_release;
                zIndex += layoutModifierNodeCoordinator.getZIndex();
                outerCoordinator$ui_release = layoutModifierNodeCoordinator.getWrapped();
            }
            if (zIndex != this.zIndex) {
                this.zIndex = zIndex;
                if (parent$ui_release != null) {
                    parent$ui_release.onZSortedChildrenInvalidated$ui_release();
                }
                if (parent$ui_release != null) {
                    parent$ui_release.invalidateLayer$ui_release();
                }
            }
            if (!getIsPlaced()) {
                if (parent$ui_release != null) {
                    parent$ui_release.invalidateLayer$ui_release();
                }
                markNodeAndSubtreeAsPlaced();
                if (this.relayoutWithoutParentInProgress && parent$ui_release != null) {
                    LayoutNode.requestRelayout$ui_release$default(parent$ui_release, false, 1, null);
                }
            }
            if (parent$ui_release == null) {
                this.placeOrder = 0;
            } else if (!this.relayoutWithoutParentInProgress && parent$ui_release.getLayoutState$ui_release() == LayoutNode.LayoutState.LayingOut) {
                if (this.placeOrder != Integer.MAX_VALUE) {
                    throw new IllegalStateException("Place was called on a node which was placed already");
                }
                this.placeOrder = parent$ui_release.getLayoutDelegate().nextChildPlaceOrder;
                parent$ui_release.getLayoutDelegate().nextChildPlaceOrder++;
            }
            layoutChildren();
        }

        @Override // androidx.compose.ui.layout.Placeable
        /* JADX INFO: renamed from: placeAt-f8xVGno */
        public void mo4632placeAtf8xVGno(long position, float zIndex, l<? super GraphicsLayerScope, t0> layerBlock) {
            Placeable.PlacementScope placementScope;
            this.isPlacedByParent = true;
            if (!IntOffset.m5801equalsimpl0(position, this.lastPosition)) {
                if (LayoutNodeLayoutDelegate.this.getCoordinatesAccessedDuringModifierPlacement() || LayoutNodeLayoutDelegate.this.getCoordinatesAccessedDuringPlacement()) {
                    LayoutNodeLayoutDelegate.this.layoutPending = true;
                }
                notifyChildrenUsingCoordinatesWhilePlacing();
            }
            if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(LayoutNodeLayoutDelegate.this.layoutNode)) {
                NodeCoordinator wrappedBy = LayoutNodeLayoutDelegate.this.getOuterCoordinator().getWrappedBy();
                if (wrappedBy == null || (placementScope = wrappedBy.getPlacementScope()) == null) {
                    placementScope = LayoutNodeKt.requireOwner(LayoutNodeLayoutDelegate.this.layoutNode).getPlacementScope();
                }
                Placeable.PlacementScope placementScope2 = placementScope;
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
                LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.getLookaheadPassDelegate();
                LayoutNode parent$ui_release = layoutNodeLayoutDelegate.layoutNode.getParent$ui_release();
                if (parent$ui_release != null) {
                    parent$ui_release.getLayoutDelegate().nextChildLookaheadPlaceOrder = 0;
                }
                lookaheadPassDelegate.setPlaceOrder$ui_release(Integer.MAX_VALUE);
                Placeable.PlacementScope.place$default(placementScope2, lookaheadPassDelegate, IntOffset.m5802getXimpl(position), IntOffset.m5803getYimpl(position), 0.0f, 4, null);
            }
            LookaheadPassDelegate lookaheadPassDelegate2 = LayoutNodeLayoutDelegate.this.getLookaheadPassDelegate();
            if ((lookaheadPassDelegate2 == null || lookaheadPassDelegate2.getPlacedOnce()) ? false : true) {
                throw new IllegalArgumentException("Error: Placement happened before lookahead.");
            }
            m4777placeOuterCoordinatorf8xVGno(position, zIndex, layerBlock);
        }

        public final void placeBasedOnLookahead() {
            LookaheadPassDelegate lookaheadPassDelegate = LayoutNodeLayoutDelegate.this.getLookaheadPassDelegate();
            if (lookaheadPassDelegate == null) {
                throw new IllegalStateException("invalid lookaheadDelegate");
            }
            mo4632placeAtf8xVGno(lookaheadPassDelegate.getLastPosition(), lookaheadPassDelegate.getLastZIndex(), lookaheadPassDelegate.getLastLayerBlock$ui_release());
        }

        /* JADX INFO: renamed from: remeasure-BRTryo0, reason: not valid java name */
        public final boolean m4779remeasureBRTryo0(long constraints) {
            if (LayoutNodeLayoutDelegate.this.layoutNode.getIsDeactivated()) {
                throw new IllegalArgumentException("measure is called on a deactivated node");
            }
            Owner ownerRequireOwner = LayoutNodeKt.requireOwner(LayoutNodeLayoutDelegate.this.layoutNode);
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            boolean z = true;
            LayoutNodeLayoutDelegate.this.layoutNode.setCanMultiMeasure$ui_release(LayoutNodeLayoutDelegate.this.layoutNode.getCanMultiMeasure() || (parent$ui_release != null && parent$ui_release.getCanMultiMeasure()));
            if (!LayoutNodeLayoutDelegate.this.layoutNode.getMeasurePending$ui_release() && Constraints.m5639equalsimpl0(getMeasurementConstraints(), constraints)) {
                e.b(ownerRequireOwner, LayoutNodeLayoutDelegate.this.layoutNode, false, 2, null);
                LayoutNodeLayoutDelegate.this.layoutNode.resetSubtreeIntrinsicsUsage$ui_release();
                return false;
            }
            getAlignmentLines().setUsedByModifierMeasurement$ui_release(false);
            forEachChildAlignmentLinesOwner(LayoutNodeLayoutDelegate$MeasurePassDelegate$remeasure$2.INSTANCE);
            this.measuredOnce = true;
            long jMo4638getSizeYbymL2g = LayoutNodeLayoutDelegate.this.getOuterCoordinator().mo4638getSizeYbymL2g();
            m4668setMeasurementConstraintsBRTryo0(constraints);
            LayoutNodeLayoutDelegate.this.m4769performMeasureBRTryo0(constraints);
            if (IntSize.m5842equalsimpl0(LayoutNodeLayoutDelegate.this.getOuterCoordinator().mo4638getSizeYbymL2g(), jMo4638getSizeYbymL2g) && LayoutNodeLayoutDelegate.this.getOuterCoordinator().getWidth() == getWidth() && LayoutNodeLayoutDelegate.this.getOuterCoordinator().getHeight() == getHeight()) {
                z = false;
            }
            m4667setMeasuredSizeozmzZPI(IntSizeKt.IntSize(LayoutNodeLayoutDelegate.this.getOuterCoordinator().getWidth(), LayoutNodeLayoutDelegate.this.getOuterCoordinator().getHeight()));
            return z;
        }

        public final void replace() {
            LayoutNode parent$ui_release;
            try {
                this.relayoutWithoutParentInProgress = true;
                if (!this.placedOnce) {
                    throw new IllegalStateException("replace called on unplaced item");
                }
                boolean isPlaced = getIsPlaced();
                m4777placeOuterCoordinatorf8xVGno(this.lastPosition, this.lastZIndex, this.lastLayerBlock);
                if (isPlaced && !this.onNodePlacedCalled && (parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release()) != null) {
                    LayoutNode.requestRelayout$ui_release$default(parent$ui_release, false, 1, null);
                }
            } finally {
                this.relayoutWithoutParentInProgress = false;
            }
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public void requestLayout() {
            LayoutNode.requestRelayout$ui_release$default(LayoutNodeLayoutDelegate.this.layoutNode, false, 1, null);
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public void requestMeasure() {
            LayoutNode.requestRemeasure$ui_release$default(LayoutNodeLayoutDelegate.this.layoutNode, false, false, 3, null);
        }

        public final void setChildDelegatesDirty$ui_release(boolean z) {
            this.childDelegatesDirty = z;
        }

        public final void setDuringAlignmentLinesQuery$ui_release(boolean z) {
            this.duringAlignmentLinesQuery = z;
        }

        public final void setMeasuredByParent$ui_release(LayoutNode.UsageByParent usageByParent) {
            this.measuredByParent = usageByParent;
        }

        public void setPlaced$ui_release(boolean z) {
            this.isPlaced = z;
        }

        public final void setPlacedByParent$ui_release(boolean z) {
            this.isPlacedByParent = z;
        }

        public final boolean updateParentData() {
            if ((getParentData() == null && LayoutNodeLayoutDelegate.this.getOuterCoordinator().getParentData() == null) || !this.parentDataDirty) {
                return false;
            }
            this.parentDataDirty = false;
            this.parentData = LayoutNodeLayoutDelegate.this.getOuterCoordinator().getParentData();
            return true;
        }
    }

    public LayoutNodeLayoutDelegate(LayoutNode layoutNode) {
        this.layoutNode = layoutNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: performLookaheadMeasure-BRTryo0, reason: not valid java name */
    public final void m4768performLookaheadMeasureBRTryo0(long constraints) {
        this.layoutState = LayoutNode.LayoutState.LookaheadMeasuring;
        this.lookaheadMeasurePending = false;
        OwnerSnapshotObserver.observeMeasureSnapshotReads$ui_release$default(LayoutNodeKt.requireOwner(this.layoutNode).getSnapshotObserver(), this.layoutNode, false, new LayoutNodeLayoutDelegate$performLookaheadMeasure$1(this, constraints), 2, null);
        markLookaheadLayoutPending$ui_release();
        if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(this.layoutNode)) {
            markLayoutPending$ui_release();
        } else {
            markMeasurePending$ui_release();
        }
        this.layoutState = LayoutNode.LayoutState.Idle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: performMeasure-BRTryo0, reason: not valid java name */
    public final void m4769performMeasureBRTryo0(long constraints) {
        LayoutNode.LayoutState layoutState = this.layoutState;
        LayoutNode.LayoutState layoutState2 = LayoutNode.LayoutState.Idle;
        if (layoutState != layoutState2) {
            throw new IllegalStateException("layout state is not idle before measure starts");
        }
        LayoutNode.LayoutState layoutState3 = LayoutNode.LayoutState.Measuring;
        this.layoutState = layoutState3;
        this.measurePending = false;
        this.performMeasureConstraints = constraints;
        LayoutNodeKt.requireOwner(this.layoutNode).getSnapshotObserver().observeMeasureSnapshotReads$ui_release(this.layoutNode, false, this.performMeasureBlock);
        if (this.layoutState == layoutState3) {
            markLayoutPending$ui_release();
            this.layoutState = layoutState2;
        }
    }

    public final void ensureLookaheadDelegateCreated$ui_release() {
        if (this.lookaheadPassDelegate == null) {
            this.lookaheadPassDelegate = new LookaheadPassDelegate();
        }
    }

    public final AlignmentLinesOwner getAlignmentLinesOwner$ui_release() {
        return this.measurePassDelegate;
    }

    public final int getChildrenAccessingCoordinatesDuringPlacement() {
        return this.childrenAccessingCoordinatesDuringPlacement;
    }

    public final boolean getCoordinatesAccessedDuringModifierPlacement() {
        return this.coordinatesAccessedDuringModifierPlacement;
    }

    public final boolean getCoordinatesAccessedDuringPlacement() {
        return this.coordinatesAccessedDuringPlacement;
    }

    /* JADX INFO: renamed from: getDetachedFromParentLookaheadPass$ui_release, reason: from getter */
    public final boolean getDetachedFromParentLookaheadPass() {
        return this.detachedFromParentLookaheadPass;
    }

    public final int getHeight$ui_release() {
        return this.measurePassDelegate.getHeight();
    }

    /* JADX INFO: renamed from: getLastConstraints-DWUhwKw, reason: not valid java name */
    public final Constraints m4770getLastConstraintsDWUhwKw() {
        return this.measurePassDelegate.m4778getLastConstraintsDWUhwKw();
    }

    /* JADX INFO: renamed from: getLastLookaheadConstraints-DWUhwKw, reason: not valid java name */
    public final Constraints m4771getLastLookaheadConstraintsDWUhwKw() {
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            return lookaheadPassDelegate.getLookaheadConstraints();
        }
        return null;
    }

    /* JADX INFO: renamed from: getLayoutPending$ui_release, reason: from getter */
    public final boolean getLayoutPending() {
        return this.layoutPending;
    }

    /* JADX INFO: renamed from: getLayoutState$ui_release, reason: from getter */
    public final LayoutNode.LayoutState getLayoutState() {
        return this.layoutState;
    }

    public final AlignmentLinesOwner getLookaheadAlignmentLinesOwner$ui_release() {
        return this.lookaheadPassDelegate;
    }

    /* JADX INFO: renamed from: getLookaheadLayoutPending$ui_release, reason: from getter */
    public final boolean getLookaheadLayoutPending() {
        return this.lookaheadLayoutPending;
    }

    /* JADX INFO: renamed from: getLookaheadMeasurePending$ui_release, reason: from getter */
    public final boolean getLookaheadMeasurePending() {
        return this.lookaheadMeasurePending;
    }

    /* JADX INFO: renamed from: getLookaheadPassDelegate$ui_release, reason: from getter */
    public final LookaheadPassDelegate getLookaheadPassDelegate() {
        return this.lookaheadPassDelegate;
    }

    /* JADX INFO: renamed from: getMeasurePassDelegate$ui_release, reason: from getter */
    public final MeasurePassDelegate getMeasurePassDelegate() {
        return this.measurePassDelegate;
    }

    /* JADX INFO: renamed from: getMeasurePending$ui_release, reason: from getter */
    public final boolean getMeasurePending() {
        return this.measurePending;
    }

    public final NodeCoordinator getOuterCoordinator() {
        return this.layoutNode.getNodes().getOuterCoordinator();
    }

    public final int getWidth$ui_release() {
        return this.measurePassDelegate.getWidth();
    }

    public final void invalidateParentData() {
        this.measurePassDelegate.invalidateParentData();
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            lookaheadPassDelegate.invalidateParentData();
        }
    }

    public final void markChildrenDirty() {
        this.measurePassDelegate.setChildDelegatesDirty$ui_release(true);
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            lookaheadPassDelegate.setChildDelegatesDirty$ui_release(true);
        }
    }

    public final void markLayoutPending$ui_release() {
        this.layoutPending = true;
        this.layoutPendingForAlignment = true;
    }

    public final void markLookaheadLayoutPending$ui_release() {
        this.lookaheadLayoutPending = true;
        this.lookaheadLayoutPendingForAlignment = true;
    }

    public final void markLookaheadMeasurePending$ui_release() {
        this.lookaheadMeasurePending = true;
    }

    public final void markMeasurePending$ui_release() {
        this.measurePending = true;
    }

    public final void onCoordinatesUsed() {
        LayoutNode.LayoutState layoutState$ui_release = this.layoutNode.getLayoutState$ui_release();
        if (layoutState$ui_release == LayoutNode.LayoutState.LayingOut || layoutState$ui_release == LayoutNode.LayoutState.LookaheadLayingOut) {
            if (this.measurePassDelegate.getLayingOutChildren()) {
                setCoordinatesAccessedDuringPlacement(true);
            } else {
                setCoordinatesAccessedDuringModifierPlacement(true);
            }
        }
        if (layoutState$ui_release == LayoutNode.LayoutState.LookaheadLayingOut) {
            LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
            if (lookaheadPassDelegate == null || !lookaheadPassDelegate.getLayingOutChildren()) {
                setCoordinatesAccessedDuringModifierPlacement(true);
            } else {
                setCoordinatesAccessedDuringPlacement(true);
            }
        }
    }

    public final void resetAlignmentLines() {
        AlignmentLines alignmentLines;
        this.measurePassDelegate.getAlignmentLines().reset$ui_release();
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        if (lookaheadPassDelegate == null || (alignmentLines = lookaheadPassDelegate.getAlignmentLines()) == null) {
            return;
        }
        alignmentLines.reset$ui_release();
    }

    public final void setChildrenAccessingCoordinatesDuringPlacement(int i10) {
        int i11 = this.childrenAccessingCoordinatesDuringPlacement;
        this.childrenAccessingCoordinatesDuringPlacement = i10;
        if ((i11 == 0) != (i10 == 0)) {
            LayoutNode parent$ui_release = this.layoutNode.getParent$ui_release();
            LayoutNodeLayoutDelegate layoutDelegate = parent$ui_release != null ? parent$ui_release.getLayoutDelegate() : null;
            if (layoutDelegate != null) {
                if (i10 == 0) {
                    layoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(layoutDelegate.childrenAccessingCoordinatesDuringPlacement - 1);
                } else {
                    layoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(layoutDelegate.childrenAccessingCoordinatesDuringPlacement + 1);
                }
            }
        }
    }

    public final void setCoordinatesAccessedDuringModifierPlacement(boolean z) {
        if (this.coordinatesAccessedDuringModifierPlacement != z) {
            this.coordinatesAccessedDuringModifierPlacement = z;
            if (z && !this.coordinatesAccessedDuringPlacement) {
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement + 1);
            } else {
                if (z || this.coordinatesAccessedDuringPlacement) {
                    return;
                }
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement - 1);
            }
        }
    }

    public final void setCoordinatesAccessedDuringPlacement(boolean z) {
        if (this.coordinatesAccessedDuringPlacement != z) {
            this.coordinatesAccessedDuringPlacement = z;
            if (z && !this.coordinatesAccessedDuringModifierPlacement) {
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement + 1);
            } else {
                if (z || this.coordinatesAccessedDuringModifierPlacement) {
                    return;
                }
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement - 1);
            }
        }
    }

    public final void updateParentData() {
        LayoutNode parent$ui_release;
        if (this.measurePassDelegate.updateParentData() && (parent$ui_release = this.layoutNode.getParent$ui_release()) != null) {
            LayoutNode.requestRemeasure$ui_release$default(parent$ui_release, false, false, 3, null);
        }
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        if (lookaheadPassDelegate == null || !lookaheadPassDelegate.updateParentData()) {
            return;
        }
        if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(this.layoutNode)) {
            LayoutNode parent$ui_release2 = this.layoutNode.getParent$ui_release();
            if (parent$ui_release2 != null) {
                LayoutNode.requestRemeasure$ui_release$default(parent$ui_release2, false, false, 3, null);
                return;
            }
            return;
        }
        LayoutNode parent$ui_release3 = this.layoutNode.getParent$ui_release();
        if (parent$ui_release3 != null) {
            LayoutNode.requestLookaheadRemeasure$ui_release$default(parent$ui_release3, false, false, 3, null);
        }
    }
}
