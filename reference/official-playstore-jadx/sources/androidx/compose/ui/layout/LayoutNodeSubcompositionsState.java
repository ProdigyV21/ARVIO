package androidx.compose.ui.layout;

import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ReusableComposition;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.layout.SubcomposeSlotReusePolicy;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeKt;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.platform.Wrapper_androidKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001:\u0003{|}B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\nJ\u000f\u0010\f\u001a\u00020\bH\u0016¢\u0006\u0004\b\f\u0010\nJ0\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u0010¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\b¢\u0006\u0004\b\u001a\u0010\nJ,\u0010\"\u001a\u00020!2\u001d\u0010 \u001a\u0019\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e0\u001b¢\u0006\u0002\b\u001f¢\u0006\u0004\b\"\u0010#J*\u0010%\u001a\u00020$2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u0010¢\u0006\u0004\b%\u0010&J\r\u0010'\u001a\u00020\b¢\u0006\u0004\b'\u0010\nJ4\u0010\u0014\u001a\u00020\b2\u0006\u0010(\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u0010H\u0002¢\u0006\u0004\b\u0014\u0010)J\u001f\u0010\u0014\u001a\u00020\b2\u0006\u0010(\u001a\u00020\u00022\u0006\u0010+\u001a\u00020*H\u0002¢\u0006\u0004\b\u0014\u0010,JD\u00105\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010-2\u0006\u0010/\u001a\u00020\u00022\u0006\u00101\u001a\u0002002\u0006\u00103\u001a\u0002022\u0011\u00104\u001a\r\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u0010H\u0002¢\u0006\u0004\b5\u00106J\u0019\u00108\u001a\u0004\u0018\u00010\r2\u0006\u00107\u001a\u00020\u0016H\u0002¢\u0006\u0004\b8\u00109J\u0017\u0010;\u001a\u00020\b2\u0006\u0010:\u001a\u000200H\u0002¢\u0006\u0004\b;\u0010<J\u000f\u0010=\u001a\u00020\bH\u0002¢\u0006\u0004\b=\u0010\nJ\u0013\u0010>\u001a\u00020\b*\u00020\u0002H\u0002¢\u0006\u0004\b>\u0010?J\u001b\u0010@\u001a\u0004\u0018\u00010\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0004\b@\u0010AJ\u000f\u0010B\u001a\u00020\bH\u0002¢\u0006\u0004\bB\u0010\nJ(\u0010E\u001a\u00020\u001e2\u0006\u0010C\u001a\u00020\u001e2\u000e\b\u0004\u0010D\u001a\b\u0012\u0004\u0012\u00020\b0\u000fH\u0082\b¢\u0006\u0004\bE\u0010FJ\u0017\u0010G\u001a\u00020\u00022\u0006\u00107\u001a\u00020\u0016H\u0002¢\u0006\u0004\bG\u0010HJ)\u0010L\u001a\u00020\b2\u0006\u0010I\u001a\u00020\u00162\u0006\u0010J\u001a\u00020\u00162\b\b\u0002\u0010K\u001a\u00020\u0016H\u0002¢\u0006\u0004\bL\u0010MJ\u001e\u0010N\u001a\u00020\b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\b0\u000fH\u0082\b¢\u0006\u0004\bN\u0010OJ2\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u0010H\u0002¢\u0006\u0004\bP\u0010\u0015R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010QR$\u0010R\u001a\u0004\u0018\u0001028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bR\u0010S\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR*\u0010\u0005\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u00048\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010Y\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u0016\u0010^\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b^\u0010_R\u0016\u0010`\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b`\u0010_R0\u0010c\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020*0aj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020*`b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bc\u0010dR4\u0010e\u001a\"\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u00020aj\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u0002`b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010dR\u0018\u0010g\u001a\u00060fR\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bg\u0010hR\u0018\u0010j\u001a\u00060iR\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bj\u0010kR4\u0010l\u001a\"\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u00020aj\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u0002`b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bl\u0010dR\u0014\u0010n\u001a\u00020m8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bn\u0010oR\"\u0010q\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020$0p8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bq\u0010rR\u001c\u0010t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0s8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bt\u0010uR\u0016\u0010v\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bv\u0010_R\u0016\u0010w\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bw\u0010_R\u0014\u0010y\u001a\u00020x8\u0002X\u0082D¢\u0006\u0006\n\u0004\by\u0010z¨\u0006~"}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;", "Landroidx/compose/runtime/ComposeNodeLifecycleCallback;", "Landroidx/compose/ui/node/LayoutNode;", "root", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "slotReusePolicy", "<init>", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;)V", "Lx6/t0;", "onReuse", "()V", "onDeactivate", "onRelease", "", "slotId", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "content", "", "Landroidx/compose/ui/layout/Measurable;", "subcompose", "(Ljava/lang/Object;Lr7/p;)Ljava/util/List;", "", "startIndex", "disposeOrReuseStartingFromIndex", "(I)V", "makeSureStateIsConsistent", "Lkotlin/Function2;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lx6/n;", "block", "Landroidx/compose/ui/layout/MeasurePolicy;", "createMeasurePolicy", "(Lr7/p;)Landroidx/compose/ui/layout/MeasurePolicy;", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "precompose", "(Ljava/lang/Object;Lr7/p;)Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "forceRecomposeChildren", "node", "(Landroidx/compose/ui/node/LayoutNode;Ljava/lang/Object;Lr7/p;)V", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$NodeState;", "nodeState", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$NodeState;)V", "Landroidx/compose/runtime/ReusableComposition;", "existing", TtmlNode.RUBY_CONTAINER, "", "reuseContent", "Landroidx/compose/runtime/CompositionContext;", "parent", "composable", "subcomposeInto", "(Landroidx/compose/runtime/ReusableComposition;Landroidx/compose/ui/node/LayoutNode;ZLandroidx/compose/runtime/CompositionContext;Lr7/p;)Landroidx/compose/runtime/ReusableComposition;", "index", "getSlotIdAtIndex", "(I)Ljava/lang/Object;", "deactivate", "markActiveNodesAsReused", "(Z)V", "disposeCurrentNodes", "resetLayoutState", "(Landroidx/compose/ui/node/LayoutNode;)V", "takeNodeFromReusables", "(Ljava/lang/Object;)Landroidx/compose/ui/node/LayoutNode;", "disposeUnusedSlotsInPostLookahead", "result", "placeChildrenBlock", "createMeasureResult", "(Landroidx/compose/ui/layout/MeasureResult;Lr7/a;)Landroidx/compose/ui/layout/MeasureResult;", "createNodeAt", "(I)Landroidx/compose/ui/node/LayoutNode;", "from", "to", "count", "move", "(III)V", "ignoreRemeasureRequests", "(Lr7/a;)V", "postLookaheadSubcompose", "Landroidx/compose/ui/node/LayoutNode;", "compositionContext", "Landroidx/compose/runtime/CompositionContext;", "getCompositionContext", "()Landroidx/compose/runtime/CompositionContext;", "setCompositionContext", "(Landroidx/compose/runtime/CompositionContext;)V", "value", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "getSlotReusePolicy", "()Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "setSlotReusePolicy", "(Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;)V", "currentIndex", "I", "currentPostLookaheadIndex", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "nodeToNodeState", "Ljava/util/HashMap;", "slotIdToNode", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$Scope;", "scope", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$Scope;", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$PostLookaheadMeasureScopeImpl;", "postLookaheadMeasureScope", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$PostLookaheadMeasureScopeImpl;", "precomposeMap", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy$SlotIdsSet;", "reusableSlotIdsSet", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy$SlotIdsSet;", "", "postLookaheadPrecomposeSlotHandleMap", "Ljava/util/Map;", "Landroidx/compose/runtime/collection/MutableVector;", "postLookaheadComposedSlotIds", "Landroidx/compose/runtime/collection/MutableVector;", "reusableCount", "precomposedCount", "", "NoIntrinsicsMessage", "Ljava/lang/String;", "NodeState", "PostLookaheadMeasureScopeImpl", "Scope", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LayoutNodeSubcompositionsState implements ComposeNodeLifecycleCallback {
    public static final int $stable = 8;
    private CompositionContext compositionContext;
    private int currentIndex;
    private int currentPostLookaheadIndex;
    private int precomposedCount;
    private int reusableCount;
    private final LayoutNode root;
    private SubcomposeSlotReusePolicy slotReusePolicy;
    private final HashMap<LayoutNode, NodeState> nodeToNodeState = new HashMap<>();
    private final HashMap<Object, LayoutNode> slotIdToNode = new HashMap<>();
    private final Scope scope = new Scope();
    private final PostLookaheadMeasureScopeImpl postLookaheadMeasureScope = new PostLookaheadMeasureScopeImpl();
    private final HashMap<Object, LayoutNode> precomposeMap = new HashMap<>();
    private final SubcomposeSlotReusePolicy.SlotIdsSet reusableSlotIdsSet = new SubcomposeSlotReusePolicy.SlotIdsSet(null, 1, 0 == true ? 1 : 0);
    private final Map<Object, SubcomposeLayoutState.PrecomposedSlotHandle> postLookaheadPrecomposeSlotHandleMap = new LinkedHashMap();
    private final MutableVector<Object> postLookaheadComposedSlotIds = new MutableVector<>(new Object[16], 0);
    private final String NoIntrinsicsMessage = "Asking for intrinsic measurements of SubcomposeLayout layouts is not supported. This includes components that are built on top of SubcomposeLayout, such as lazy lists, BoxWithConstraints, TabRow, etc. To mitigate this:\n- if intrinsic measurements are used to achieve 'match parent' sizing, consider replacing the parent of the component with a custom layout which controls the order in which children are measured, making intrinsic measurement not needed\n- adding a size modifier to the component, in order to fast return the queried intrinsic measurement.";

    @Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004JO\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\b2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0096\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0016\u001a\u00020\u0005*\u00020\u0013H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\u0005*\u00020\u0017H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001c\u001a\u00020\u0013*\u00020\u0017H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001c\u001a\u00020\u0013*\u00020\u001dH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001a\u0010\u001c\u001a\u00020\u0013*\u00020\u0005H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010 J\u0017\u0010%\u001a\u00020\"*\u00020!H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010$J\u0017\u0010'\u001a\u00020\u001d*\u00020\u0013H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b&\u0010\u001fJ\u0017\u0010'\u001a\u00020\u001d*\u00020\u0017H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010\u001bJ\u0014\u0010+\u001a\u00020**\u00020)H\u0097\u0001¢\u0006\u0004\b+\u0010,J\u0017\u0010.\u001a\u00020!*\u00020\"H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b-\u0010$J\u0017\u00101\u001a\u00020\u0017*\u00020\u0013H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b/\u00100J\u001a\u00101\u001a\u00020\u0017*\u00020\u001dH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b2\u00100J\u001a\u00101\u001a\u00020\u0017*\u00020\u0005H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b2\u00103J2\u0010;\u001a\b\u0012\u0004\u0012\u00020:092\b\u00105\u001a\u0004\u0018\u0001042\u0011\u00108\u001a\r\u0012\u0004\u0012\u00020\r06¢\u0006\u0002\b7H\u0016¢\u0006\u0004\b;\u0010<R\u0014\u0010?\u001a\u00020\u001d8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0014\u0010A\u001a\u00020\u001d8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b@\u0010>R\u0014\u0010C\u001a\u00020B8VX\u0096\u0005¢\u0006\u0006\u001a\u0004\bC\u0010DR\u0014\u0010H\u001a\u00020E8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\bF\u0010G\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006I"}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$PostLookaheadMeasureScopeImpl;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/layout/MeasureScope;", "<init>", "(Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;)V", "", "width", "height", "", "Landroidx/compose/ui/layout/AlignmentLine;", "alignmentLines", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "Lx6/n;", "placementBlock", "Landroidx/compose/ui/layout/MeasureResult;", TtmlNode.TAG_LAYOUT, "(IILjava/util/Map;Lr7/l;)Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "roundToPx", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp-GaN1DYA", "(J)F", "toDp", "", "toDp-u2uoSUM", "(F)F", "(I)F", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/unit/DpSize;", "toDpSize-k-rfVVM", "(J)J", "toDpSize", "toPx-0680j_4", "toPx", "toPx--R2X_6o", "Landroidx/compose/ui/unit/DpRect;", "Landroidx/compose/ui/geometry/Rect;", "toRect", "(Landroidx/compose/ui/unit/DpRect;)Landroidx/compose/ui/geometry/Rect;", "toSize-XkaWNTQ", "toSize", "toSp-0xMU5do", "(F)J", "toSp", "toSp-kPz2Gy4", "(I)J", "", "slotId", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "content", "", "Landroidx/compose/ui/layout/Measurable;", "subcompose", "(Ljava/lang/Object;Lr7/p;)Ljava/util/List;", "getDensity", "()F", "density", "getFontScale", "fontScale", "", "isLookingAhead", "()Z", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class PostLookaheadMeasureScopeImpl implements SubcomposeMeasureScope, MeasureScope {
        private final /* synthetic */ Scope $$delegate_0;

        public PostLookaheadMeasureScopeImpl() {
            this.$$delegate_0 = LayoutNodeSubcompositionsState.this.scope;
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
            return this.$$delegate_0.getLayoutDirection();
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public boolean isLookingAhead() {
            return this.$$delegate_0.isLookingAhead();
        }

        @Override // androidx.compose.ui.layout.MeasureScope
        public MeasureResult layout(int width, int height, Map<AlignmentLine, Integer> alignmentLines, r7.l<? super Placeable.PlacementScope, t0> placementBlock) {
            return this.$$delegate_0.layout(width, height, alignmentLines, placementBlock);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx--R2X_6o */
        public int mo278roundToPxR2X_6o(long j10) {
            Scope scope = this.$$delegate_0;
            scope.getClass();
            return androidx.compose.ui.unit.a.a(scope, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx-0680j_4 */
        public int mo279roundToPx0680j_4(float f10) {
            Scope scope = this.$$delegate_0;
            scope.getClass();
            return androidx.compose.ui.unit.a.b(scope, f10);
        }

        @Override // androidx.compose.ui.layout.SubcomposeMeasureScope
        public List<Measurable> subcompose(Object slotId, p<? super Composer, ? super Integer, t0> content) {
            LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.slotIdToNode.get(slotId);
            List<Measurable> childMeasurables$ui_release = layoutNode != null ? layoutNode.getChildMeasurables$ui_release() : null;
            return childMeasurables$ui_release != null ? childMeasurables$ui_release : LayoutNodeSubcompositionsState.this.postLookaheadSubcompose(slotId, content);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toDp-GaN1DYA */
        public float mo280toDpGaN1DYA(long j10) {
            Scope scope = this.$$delegate_0;
            scope.getClass();
            return androidx.compose.ui.unit.b.a(scope, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public float mo281toDpu2uoSUM(float f10) {
            Scope scope = this.$$delegate_0;
            scope.getClass();
            return androidx.compose.ui.unit.a.c(scope, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDpSize-k-rfVVM */
        public long mo283toDpSizekrfVVM(long j10) {
            Scope scope = this.$$delegate_0;
            scope.getClass();
            return androidx.compose.ui.unit.a.e(scope, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx--R2X_6o */
        public float mo284toPxR2X_6o(long j10) {
            Scope scope = this.$$delegate_0;
            scope.getClass();
            return androidx.compose.ui.unit.a.f(scope, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx-0680j_4 */
        public float mo285toPx0680j_4(float f10) {
            Scope scope = this.$$delegate_0;
            scope.getClass();
            return androidx.compose.ui.unit.a.g(scope, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        public Rect toRect(DpRect dpRect) {
            Scope scope = this.$$delegate_0;
            scope.getClass();
            return androidx.compose.ui.unit.a.h(scope, dpRect);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSize-XkaWNTQ */
        public long mo286toSizeXkaWNTQ(long j10) {
            Scope scope = this.$$delegate_0;
            scope.getClass();
            return androidx.compose.ui.unit.a.i(scope, j10);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toSp-0xMU5do */
        public long mo287toSp0xMU5do(float f10) {
            Scope scope = this.$$delegate_0;
            scope.getClass();
            return androidx.compose.ui.unit.b.b(scope, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public long mo288toSpkPz2Gy4(float f10) {
            Scope scope = this.$$delegate_0;
            scope.getClass();
            return androidx.compose.ui.unit.a.j(scope, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public float mo282toDpu2uoSUM(int i10) {
            Scope scope = this.$$delegate_0;
            scope.getClass();
            return androidx.compose.ui.unit.a.d(scope, i10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public long mo289toSpkPz2Gy4(int i10) {
            Scope scope = this.$$delegate_0;
            scope.getClass();
            return androidx.compose.ui.unit.a.k(scope, i10);
        }
    }

    @Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0016¢\u0006\u0004\b\f\u0010\rJL\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e0\u00112\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00070\u0014¢\u0006\u0002\b\u0016H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\"\u0010\u001c\u001a\u00020\u001b8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\"\u0010#\u001a\u00020\"8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\"\u0010)\u001a\u00020\"8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b)\u0010$\u001a\u0004\b*\u0010&\"\u0004\b+\u0010(R\u0014\u0010-\u001a\u00020,8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.¨\u0006/"}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$Scope;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "<init>", "(Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;)V", "", "slotId", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "", "Landroidx/compose/ui/layout/Measurable;", "subcompose", "(Ljava/lang/Object;Lr7/p;)Ljava/util/List;", "", "width", "height", "", "Landroidx/compose/ui/layout/AlignmentLine;", "alignmentLines", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/n;", "placementBlock", "Landroidx/compose/ui/layout/MeasureResult;", TtmlNode.TAG_LAYOUT, "(IILjava/util/Map;Lr7/l;)Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "", "density", "F", "getDensity", "()F", "setDensity", "(F)V", "fontScale", "getFontScale", "setFontScale", "", "isLookingAhead", "()Z", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class Scope implements SubcomposeMeasureScope {
        private float density;
        private float fontScale;
        private LayoutDirection layoutDirection = LayoutDirection.Rtl;

        public Scope() {
        }

        @Override // androidx.compose.ui.unit.Density
        public float getDensity() {
            return this.density;
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public float getFontScale() {
            return this.fontScale;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public LayoutDirection getLayoutDirection() {
            return this.layoutDirection;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public boolean isLookingAhead() {
            return LayoutNodeSubcompositionsState.this.root.getLayoutState$ui_release() == LayoutNode.LayoutState.LookaheadLayingOut || LayoutNodeSubcompositionsState.this.root.getLayoutState$ui_release() == LayoutNode.LayoutState.LookaheadMeasuring;
        }

        @Override // androidx.compose.ui.layout.MeasureScope
        public MeasureResult layout(final int width, final int height, final Map<AlignmentLine, Integer> alignmentLines, final r7.l<? super Placeable.PlacementScope, t0> placementBlock) {
            if ((width & (-16777216)) != 0 || ((-16777216) & height) != 0) {
                throw new IllegalStateException(androidx.compose.foundation.c.s("Size(", width, " x ", height, ") is out of range. Each dimension must be between 0 and 16777215.").toString());
            }
            final LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
            return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$Scope$layout$1
                @Override // androidx.compose.ui.layout.MeasureResult
                public Map<AlignmentLine, Integer> getAlignmentLines() {
                    return alignmentLines;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                /* JADX INFO: renamed from: getHeight, reason: from getter */
                public int get$height() {
                    return height;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                /* JADX INFO: renamed from: getWidth, reason: from getter */
                public int get$width() {
                    return width;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public void placeChildren() {
                    LookaheadDelegate lookaheadDelegate;
                    if (!this.isLookingAhead() || (lookaheadDelegate = layoutNodeSubcompositionsState.root.getInnerCoordinator$ui_release().getLookaheadDelegate()) == null) {
                        placementBlock.invoke(layoutNodeSubcompositionsState.root.getInnerCoordinator$ui_release().getPlacementScope());
                    } else {
                        placementBlock.invoke(lookaheadDelegate.getPlacementScope());
                    }
                }
            };
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

        public void setDensity(float f10) {
            this.density = f10;
        }

        public void setFontScale(float f10) {
            this.fontScale = f10;
        }

        public void setLayoutDirection(LayoutDirection layoutDirection) {
            this.layoutDirection = layoutDirection;
        }

        @Override // androidx.compose.ui.layout.SubcomposeMeasureScope
        public List<Measurable> subcompose(Object slotId, p<? super Composer, ? super Integer, t0> content) {
            return LayoutNodeSubcompositionsState.this.subcompose(slotId, content);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toDp-GaN1DYA */
        public final /* synthetic */ float mo280toDpGaN1DYA(long j10) {
            return androidx.compose.ui.unit.b.a(this, j10);
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
            return androidx.compose.ui.unit.b.b(this, f10);
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
    }

    /* JADX INFO: renamed from: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$disposeUnusedSlotsInPostLookahead$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010'\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "<name for destructuring parameter 0>", "", "", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "invoke", "(Ljava/util/Map$Entry;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06111 extends r implements r7.l<Map.Entry<Object, SubcomposeLayoutState.PrecomposedSlotHandle>, Boolean> {
        public C06111() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(Map.Entry<Object, SubcomposeLayoutState.PrecomposedSlotHandle> entry) {
            boolean z;
            Object key = entry.getKey();
            SubcomposeLayoutState.PrecomposedSlotHandle value = entry.getValue();
            int iIndexOf = LayoutNodeSubcompositionsState.this.postLookaheadComposedSlotIds.indexOf(key);
            if (iIndexOf < 0 || iIndexOf >= LayoutNodeSubcompositionsState.this.currentPostLookaheadIndex) {
                value.dispose();
                z = true;
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LayoutNodeSubcompositionsState(LayoutNode layoutNode, SubcomposeSlotReusePolicy subcomposeSlotReusePolicy) {
        this.root = layoutNode;
        this.slotReusePolicy = subcomposeSlotReusePolicy;
    }

    private final MeasureResult createMeasureResult(final MeasureResult result, final r7.a<t0> placeChildrenBlock) {
        return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.createMeasureResult.1
            @Override // androidx.compose.ui.layout.MeasureResult
            public Map<AlignmentLine, Integer> getAlignmentLines() {
                return result.getAlignmentLines();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            /* JADX INFO: renamed from: getHeight */
            public int get$height() {
                return result.get$height();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            /* JADX INFO: renamed from: getWidth */
            public int get$width() {
                return result.get$width();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public void placeChildren() {
                placeChildrenBlock.invoke();
            }
        };
    }

    private final LayoutNode createNodeAt(int index) {
        LayoutNode layoutNode = new LayoutNode(true, 0, 2, null);
        LayoutNode layoutNode2 = this.root;
        layoutNode2.ignoreRemeasureRequests = true;
        this.root.insertAt$ui_release(index, layoutNode);
        layoutNode2.ignoreRemeasureRequests = false;
        return layoutNode;
    }

    private final void disposeCurrentNodes() {
        LayoutNode layoutNode = this.root;
        layoutNode.ignoreRemeasureRequests = true;
        Iterator<T> it = this.nodeToNodeState.values().iterator();
        while (it.hasNext()) {
            ReusableComposition composition = ((NodeState) it.next()).getComposition();
            if (composition != null) {
                composition.dispose();
            }
        }
        this.root.removeAll$ui_release();
        layoutNode.ignoreRemeasureRequests = false;
        this.nodeToNodeState.clear();
        this.slotIdToNode.clear();
        this.precomposedCount = 0;
        this.reusableCount = 0;
        this.precomposeMap.clear();
        makeSureStateIsConsistent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disposeUnusedSlotsInPostLookahead() {
        x.j0(this.postLookaheadPrecomposeSlotHandleMap.entrySet(), new C06111(), true);
    }

    private final Object getSlotIdAtIndex(int index) {
        return this.nodeToNodeState.get(this.root.getFoldedChildren$ui_release().get(index)).getSlotId();
    }

    private final void ignoreRemeasureRequests(r7.a<t0> block) {
        LayoutNode layoutNode = this.root;
        layoutNode.ignoreRemeasureRequests = true;
        block.invoke();
        layoutNode.ignoreRemeasureRequests = false;
    }

    private final void markActiveNodesAsReused(boolean deactivate) {
        this.precomposedCount = 0;
        this.precomposeMap.clear();
        int size = this.root.getFoldedChildren$ui_release().size();
        if (this.reusableCount != size) {
            this.reusableCount = size;
            Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
            try {
                Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
                for (int i10 = 0; i10 < size; i10++) {
                    try {
                        LayoutNode layoutNode = this.root.getFoldedChildren$ui_release().get(i10);
                        NodeState nodeState = this.nodeToNodeState.get(layoutNode);
                        if (nodeState != null && nodeState.getActive()) {
                            resetLayoutState(layoutNode);
                            if (deactivate) {
                                ReusableComposition composition = nodeState.getComposition();
                                if (composition != null) {
                                    composition.deactivate();
                                }
                                nodeState.setActiveState(SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null));
                            } else {
                                nodeState.setActive(false);
                            }
                            nodeState.setSlotId(SubcomposeLayoutKt.ReusedSlotId);
                        }
                    } catch (Throwable th) {
                        snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                        throw th;
                    }
                }
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                snapshotCreateNonObservableSnapshot.dispose();
                this.slotIdToNode.clear();
            } catch (Throwable th2) {
                snapshotCreateNonObservableSnapshot.dispose();
                throw th2;
            }
        }
        makeSureStateIsConsistent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void move(int from, int to, int count) {
        LayoutNode layoutNode = this.root;
        layoutNode.ignoreRemeasureRequests = true;
        this.root.move$ui_release(from, to, count);
        layoutNode.ignoreRemeasureRequests = false;
    }

    public static /* synthetic */ void move$default(LayoutNodeSubcompositionsState layoutNodeSubcompositionsState, int i10, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i12 = 1;
        }
        layoutNodeSubcompositionsState.move(i10, i11, i12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Measurable> postLookaheadSubcompose(Object slotId, p<? super Composer, ? super Integer, t0> content) {
        if (this.postLookaheadComposedSlotIds.getSize() < this.currentPostLookaheadIndex) {
            throw new IllegalArgumentException("Error: currentPostLookaheadIndex cannot be greater than the size of thepostLookaheadComposedSlotIds list.");
        }
        int size = this.postLookaheadComposedSlotIds.getSize();
        int i10 = this.currentPostLookaheadIndex;
        if (size == i10) {
            this.postLookaheadComposedSlotIds.add(slotId);
        } else {
            this.postLookaheadComposedSlotIds.set(i10, slotId);
        }
        this.currentPostLookaheadIndex++;
        if (!this.precomposeMap.containsKey(slotId)) {
            this.postLookaheadPrecomposeSlotHandleMap.put(slotId, precompose(slotId, content));
            if (this.root.getLayoutState$ui_release() == LayoutNode.LayoutState.LayingOut) {
                this.root.requestLookaheadRelayout$ui_release(true);
            } else {
                LayoutNode.requestLookaheadRemeasure$ui_release$default(this.root, true, false, 2, null);
            }
        }
        LayoutNode layoutNode = this.precomposeMap.get(slotId);
        if (layoutNode == null) {
            return z.f19728i;
        }
        List<LayoutNodeLayoutDelegate.MeasurePassDelegate> childDelegates$ui_release = layoutNode.getMeasurePassDelegate$ui_release().getChildDelegates$ui_release();
        int size2 = childDelegates$ui_release.size();
        for (int i11 = 0; i11 < size2; i11++) {
            childDelegates$ui_release.get(i11).markDetachedFromParentLookaheadPass$ui_release();
        }
        return childDelegates$ui_release;
    }

    private final void resetLayoutState(LayoutNode layoutNode) {
        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate$ui_release = layoutNode.getMeasurePassDelegate$ui_release();
        LayoutNode.UsageByParent usageByParent = LayoutNode.UsageByParent.NotUsed;
        measurePassDelegate$ui_release.setMeasuredByParent$ui_release(usageByParent);
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate$ui_release = layoutNode.getLookaheadPassDelegate$ui_release();
        if (lookaheadPassDelegate$ui_release != null) {
            lookaheadPassDelegate$ui_release.setMeasuredByParent$ui_release(usageByParent);
        }
    }

    private final ReusableComposition subcomposeInto(ReusableComposition existing, LayoutNode container, boolean reuseContent, CompositionContext parent, p<? super Composer, ? super Integer, t0> composable) {
        if (existing == null || existing.getDisposed()) {
            existing = Wrapper_androidKt.createSubcomposition(container, parent);
        }
        if (reuseContent) {
            existing.setContentWithReuse(composable);
            return existing;
        }
        existing.setContent(composable);
        return existing;
    }

    private final LayoutNode takeNodeFromReusables(Object slotId) {
        int i10;
        if (this.reusableCount == 0) {
            return null;
        }
        int size = this.root.getFoldedChildren$ui_release().size() - this.precomposedCount;
        int i11 = size - this.reusableCount;
        int i12 = size - 1;
        int i13 = i12;
        while (true) {
            if (i13 < i11) {
                i10 = -1;
                break;
            }
            if (kotlin.jvm.internal.p.a(getSlotIdAtIndex(i13), slotId)) {
                i10 = i13;
                break;
            }
            i13--;
        }
        if (i10 == -1) {
            while (i12 >= i11) {
                NodeState nodeState = this.nodeToNodeState.get(this.root.getFoldedChildren$ui_release().get(i12));
                if (nodeState.getSlotId() == SubcomposeLayoutKt.ReusedSlotId || this.slotReusePolicy.areCompatible(slotId, nodeState.getSlotId())) {
                    nodeState.setSlotId(slotId);
                    i13 = i12;
                    i10 = i13;
                    break;
                }
                i12--;
            }
            i13 = i12;
        }
        if (i10 == -1) {
            return null;
        }
        if (i13 != i11) {
            move(i13, i11, 1);
        }
        this.reusableCount--;
        LayoutNode layoutNode = this.root.getFoldedChildren$ui_release().get(i11);
        NodeState nodeState2 = this.nodeToNodeState.get(layoutNode);
        nodeState2.setActiveState(SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null));
        nodeState2.setForceReuse(true);
        nodeState2.setForceRecompose(true);
        return layoutNode;
    }

    public final MeasurePolicy createMeasurePolicy(final p<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> block) {
        return new LayoutNode.NoIntrinsicsMeasurePolicy(this.NoIntrinsicsMessage) { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.createMeasurePolicy.1
            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* JADX INFO: renamed from: measure-3p2s80s */
            public MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
                LayoutNodeSubcompositionsState.this.scope.setLayoutDirection(measureScope.getLayoutDirection());
                LayoutNodeSubcompositionsState.this.scope.setDensity(measureScope.getDensity());
                LayoutNodeSubcompositionsState.this.scope.setFontScale(measureScope.getFontScale());
                if (measureScope.isLookingAhead() || LayoutNodeSubcompositionsState.this.root.getLookaheadRoot() == null) {
                    LayoutNodeSubcompositionsState.this.currentIndex = 0;
                    final MeasureResult measureResult = (MeasureResult) block.invoke(LayoutNodeSubcompositionsState.this.scope, Constraints.m5634boximpl(j10));
                    final int i10 = LayoutNodeSubcompositionsState.this.currentIndex;
                    final LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
                    return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$createMeasurePolicy$1$measure-3p2s80s$$inlined$createMeasureResult$2
                        @Override // androidx.compose.ui.layout.MeasureResult
                        public Map<AlignmentLine, Integer> getAlignmentLines() {
                            return measureResult.getAlignmentLines();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        /* JADX INFO: renamed from: getHeight */
                        public int get$height() {
                            return measureResult.get$height();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        /* JADX INFO: renamed from: getWidth */
                        public int get$width() {
                            return measureResult.get$width();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        public void placeChildren() {
                            layoutNodeSubcompositionsState.currentIndex = i10;
                            measureResult.placeChildren();
                            LayoutNodeSubcompositionsState layoutNodeSubcompositionsState2 = layoutNodeSubcompositionsState;
                            layoutNodeSubcompositionsState2.disposeOrReuseStartingFromIndex(layoutNodeSubcompositionsState2.currentIndex);
                        }
                    };
                }
                LayoutNodeSubcompositionsState.this.currentPostLookaheadIndex = 0;
                final MeasureResult measureResult2 = (MeasureResult) block.invoke(LayoutNodeSubcompositionsState.this.postLookaheadMeasureScope, Constraints.m5634boximpl(j10));
                final int i11 = LayoutNodeSubcompositionsState.this.currentPostLookaheadIndex;
                final LayoutNodeSubcompositionsState layoutNodeSubcompositionsState2 = LayoutNodeSubcompositionsState.this;
                return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$createMeasurePolicy$1$measure-3p2s80s$$inlined$createMeasureResult$1
                    @Override // androidx.compose.ui.layout.MeasureResult
                    public Map<AlignmentLine, Integer> getAlignmentLines() {
                        return measureResult2.getAlignmentLines();
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    /* JADX INFO: renamed from: getHeight */
                    public int get$height() {
                        return measureResult2.get$height();
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    /* JADX INFO: renamed from: getWidth */
                    public int get$width() {
                        return measureResult2.get$width();
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    public void placeChildren() {
                        layoutNodeSubcompositionsState2.currentPostLookaheadIndex = i11;
                        measureResult2.placeChildren();
                        layoutNodeSubcompositionsState2.disposeUnusedSlotsInPostLookahead();
                    }
                };
            }
        };
    }

    public final void disposeOrReuseStartingFromIndex(int startIndex) {
        boolean z = false;
        this.reusableCount = 0;
        int size = (this.root.getFoldedChildren$ui_release().size() - this.precomposedCount) - 1;
        if (startIndex <= size) {
            this.reusableSlotIdsSet.clear();
            if (startIndex <= size) {
                int i10 = startIndex;
                while (true) {
                    this.reusableSlotIdsSet.add(getSlotIdAtIndex(i10));
                    if (i10 == size) {
                        break;
                    } else {
                        i10++;
                    }
                }
            }
            this.slotReusePolicy.getSlotsToRetain(this.reusableSlotIdsSet);
            Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
            try {
                Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
                boolean z5 = false;
                while (size >= startIndex) {
                    try {
                        LayoutNode layoutNode = this.root.getFoldedChildren$ui_release().get(size);
                        NodeState nodeState = this.nodeToNodeState.get(layoutNode);
                        Object slotId = nodeState.getSlotId();
                        if (this.reusableSlotIdsSet.contains(slotId)) {
                            this.reusableCount++;
                            if (nodeState.getActive()) {
                                resetLayoutState(layoutNode);
                                nodeState.setActive(false);
                                z5 = true;
                            }
                        } else {
                            LayoutNode layoutNode2 = this.root;
                            layoutNode2.ignoreRemeasureRequests = true;
                            this.nodeToNodeState.remove(layoutNode);
                            ReusableComposition composition = nodeState.getComposition();
                            if (composition != null) {
                                composition.dispose();
                            }
                            this.root.removeAt$ui_release(size, 1);
                            layoutNode2.ignoreRemeasureRequests = false;
                        }
                        this.slotIdToNode.remove(slotId);
                        size--;
                    } catch (Throwable th) {
                        snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                        throw th;
                    }
                }
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                snapshotCreateNonObservableSnapshot.dispose();
                z = z5;
            } catch (Throwable th2) {
                snapshotCreateNonObservableSnapshot.dispose();
                throw th2;
            }
        }
        if (z) {
            Snapshot.INSTANCE.sendApplyNotifications();
        }
        makeSureStateIsConsistent();
    }

    public final void forceRecomposeChildren() {
        if (this.reusableCount != this.root.getFoldedChildren$ui_release().size()) {
            Iterator<Map.Entry<LayoutNode, NodeState>> it = this.nodeToNodeState.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().setForceRecompose(true);
            }
            if (this.root.getMeasurePending$ui_release()) {
                return;
            }
            LayoutNode.requestRemeasure$ui_release$default(this.root, false, false, 3, null);
        }
    }

    public final CompositionContext getCompositionContext() {
        return this.compositionContext;
    }

    public final SubcomposeSlotReusePolicy getSlotReusePolicy() {
        return this.slotReusePolicy;
    }

    public final void makeSureStateIsConsistent() {
        int size = this.root.getFoldedChildren$ui_release().size();
        if (this.nodeToNodeState.size() != size) {
            throw new IllegalArgumentException(("Inconsistency between the count of nodes tracked by the state (" + this.nodeToNodeState.size() + ") and the children count on the SubcomposeLayout (" + size + "). Are you trying to use the state of the disposed SubcomposeLayout?").toString());
        }
        if ((size - this.reusableCount) - this.precomposedCount < 0) {
            StringBuilder sbS = a0.c.s(size, "Incorrect state. Total children ", ". Reusable children ");
            sbS.append(this.reusableCount);
            sbS.append(". Precomposed children ");
            sbS.append(this.precomposedCount);
            throw new IllegalArgumentException(sbS.toString().toString());
        }
        if (this.precomposeMap.size() == this.precomposedCount) {
            return;
        }
        throw new IllegalArgumentException(("Incorrect state. Precomposed children " + this.precomposedCount + ". Map size " + this.precomposeMap.size()).toString());
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onDeactivate() {
        markActiveNodesAsReused(true);
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onRelease() {
        disposeCurrentNodes();
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onReuse() {
        markActiveNodesAsReused(false);
    }

    public final SubcomposeLayoutState.PrecomposedSlotHandle precompose(final Object slotId, p<? super Composer, ? super Integer, t0> content) {
        if (!this.root.isAttached()) {
            return new SubcomposeLayoutState.PrecomposedSlotHandle() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.precompose.1
                @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
                public void dispose() {
                }

                @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
                public final /* synthetic */ int getPlaceablesCount() {
                    return o.a(this);
                }

                @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
                /* JADX INFO: renamed from: premeasure-0kLqBqw, reason: not valid java name */
                public final /* synthetic */ void mo4647premeasure0kLqBqw(int i10, long j10) {
                    o.b(this, i10, j10);
                }
            };
        }
        makeSureStateIsConsistent();
        if (!this.slotIdToNode.containsKey(slotId)) {
            this.postLookaheadPrecomposeSlotHandleMap.remove(slotId);
            HashMap<Object, LayoutNode> map = this.precomposeMap;
            LayoutNode layoutNodeTakeNodeFromReusables = map.get(slotId);
            if (layoutNodeTakeNodeFromReusables == null) {
                layoutNodeTakeNodeFromReusables = takeNodeFromReusables(slotId);
                if (layoutNodeTakeNodeFromReusables != null) {
                    move(this.root.getFoldedChildren$ui_release().indexOf(layoutNodeTakeNodeFromReusables), this.root.getFoldedChildren$ui_release().size(), 1);
                    this.precomposedCount++;
                } else {
                    layoutNodeTakeNodeFromReusables = createNodeAt(this.root.getFoldedChildren$ui_release().size());
                    this.precomposedCount++;
                }
                map.put(slotId, layoutNodeTakeNodeFromReusables);
            }
            subcompose(layoutNodeTakeNodeFromReusables, slotId, content);
        }
        return new SubcomposeLayoutState.PrecomposedSlotHandle() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.precompose.2
            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            public void dispose() {
                LayoutNodeSubcompositionsState.this.makeSureStateIsConsistent();
                LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.remove(slotId);
                if (layoutNode != null) {
                    if (LayoutNodeSubcompositionsState.this.precomposedCount <= 0) {
                        throw new IllegalStateException("No pre-composed items to dispose");
                    }
                    int iIndexOf = LayoutNodeSubcompositionsState.this.root.getFoldedChildren$ui_release().indexOf(layoutNode);
                    if (iIndexOf < LayoutNodeSubcompositionsState.this.root.getFoldedChildren$ui_release().size() - LayoutNodeSubcompositionsState.this.precomposedCount) {
                        throw new IllegalStateException("Item is not in pre-composed item range");
                    }
                    LayoutNodeSubcompositionsState.this.reusableCount++;
                    LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
                    layoutNodeSubcompositionsState.precomposedCount--;
                    int size = (LayoutNodeSubcompositionsState.this.root.getFoldedChildren$ui_release().size() - LayoutNodeSubcompositionsState.this.precomposedCount) - LayoutNodeSubcompositionsState.this.reusableCount;
                    LayoutNodeSubcompositionsState.this.move(iIndexOf, size, 1);
                    LayoutNodeSubcompositionsState.this.disposeOrReuseStartingFromIndex(size);
                }
            }

            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            public int getPlaceablesCount() {
                List<LayoutNode> children$ui_release;
                LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(slotId);
                if (layoutNode == null || (children$ui_release = layoutNode.getChildren$ui_release()) == null) {
                    return 0;
                }
                return children$ui_release.size();
            }

            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            /* JADX INFO: renamed from: premeasure-0kLqBqw */
            public void mo4647premeasure0kLqBqw(int index, long constraints) {
                LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(slotId);
                if (layoutNode == null || !layoutNode.isAttached()) {
                    return;
                }
                int size = layoutNode.getChildren$ui_release().size();
                if (index < 0 || index >= size) {
                    throw new IndexOutOfBoundsException(androidx.compose.foundation.c.r("Index (", index, ") is out of bound of [0, ", size, ')'));
                }
                if (layoutNode.isPlaced()) {
                    throw new IllegalArgumentException("Pre-measure called on node that is not placed");
                }
                LayoutNode layoutNode2 = LayoutNodeSubcompositionsState.this.root;
                layoutNode2.ignoreRemeasureRequests = true;
                LayoutNodeKt.requireOwner(layoutNode).mo4899measureAndLayout0kLqBqw(layoutNode.getChildren$ui_release().get(index), constraints);
                layoutNode2.ignoreRemeasureRequests = false;
            }
        };
    }

    public final void setCompositionContext(CompositionContext compositionContext) {
        this.compositionContext = compositionContext;
    }

    public final void setSlotReusePolicy(SubcomposeSlotReusePolicy subcomposeSlotReusePolicy) {
        if (this.slotReusePolicy != subcomposeSlotReusePolicy) {
            this.slotReusePolicy = subcomposeSlotReusePolicy;
            markActiveNodesAsReused(false);
            LayoutNode.requestRemeasure$ui_release$default(this.root, false, false, 3, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<androidx.compose.ui.layout.Measurable> subcompose(java.lang.Object r11, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r12) {
        /*
            r10 = this;
            r10.makeSureStateIsConsistent()
            androidx.compose.ui.node.LayoutNode r0 = r10.root
            androidx.compose.ui.node.LayoutNode$LayoutState r0 = r0.getLayoutState$ui_release()
            androidx.compose.ui.node.LayoutNode$LayoutState r1 = androidx.compose.ui.node.LayoutNode.LayoutState.Measuring
            if (r0 == r1) goto L23
            androidx.compose.ui.node.LayoutNode$LayoutState r2 = androidx.compose.ui.node.LayoutNode.LayoutState.LayingOut
            if (r0 == r2) goto L23
            androidx.compose.ui.node.LayoutNode$LayoutState r2 = androidx.compose.ui.node.LayoutNode.LayoutState.LookaheadMeasuring
            if (r0 == r2) goto L23
            androidx.compose.ui.node.LayoutNode$LayoutState r2 = androidx.compose.ui.node.LayoutNode.LayoutState.LookaheadLayingOut
            if (r0 != r2) goto L1a
            goto L23
        L1a:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "subcompose can only be used inside the measure or layout blocks"
            r11.<init>(r12)
            throw r11
        L23:
            java.util.HashMap<java.lang.Object, androidx.compose.ui.node.LayoutNode> r2 = r10.slotIdToNode
            java.lang.Object r3 = r2.get(r11)
            if (r3 != 0) goto L55
            java.util.HashMap<java.lang.Object, androidx.compose.ui.node.LayoutNode> r3 = r10.precomposeMap
            java.lang.Object r3 = r3.remove(r11)
            androidx.compose.ui.node.LayoutNode r3 = (androidx.compose.ui.node.LayoutNode) r3
            if (r3 == 0) goto L46
            int r4 = r10.precomposedCount
            if (r4 <= 0) goto L3e
            int r4 = r4 + (-1)
            r10.precomposedCount = r4
            goto L52
        L3e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "Check failed."
            r11.<init>(r12)
            throw r11
        L46:
            androidx.compose.ui.node.LayoutNode r3 = r10.takeNodeFromReusables(r11)
            if (r3 != 0) goto L52
            int r3 = r10.currentIndex
            androidx.compose.ui.node.LayoutNode r3 = r10.createNodeAt(r3)
        L52:
            r2.put(r11, r3)
        L55:
            androidx.compose.ui.node.LayoutNode r3 = (androidx.compose.ui.node.LayoutNode) r3
            androidx.compose.ui.node.LayoutNode r2 = r10.root
            java.util.List r2 = r2.getFoldedChildren$ui_release()
            int r4 = r10.currentIndex
            java.lang.Object r2 = kotlin.collections.x.p0(r4, r2)
            if (r2 == r3) goto L7d
            androidx.compose.ui.node.LayoutNode r2 = r10.root
            java.util.List r2 = r2.getFoldedChildren$ui_release()
            int r5 = r2.indexOf(r3)
            int r6 = r10.currentIndex
            if (r5 < r6) goto L7f
            if (r6 == r5) goto L7d
            r8 = 4
            r9 = 0
            r7 = 0
            r4 = r10
            move$default(r4, r5, r6, r7, r8, r9)
            goto L9d
        L7d:
            r4 = r10
            goto L9d
        L7f:
            r4 = r10
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r0 = "Key \""
            r12.<init>(r0)
            r12.append(r11)
            java.lang.String r11 = "\" was already used. If you are using LazyColumn/Row please make sure you provide a unique key for each item."
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r11 = r11.toString()
            r12.<init>(r11)
            throw r12
        L9d:
            int r2 = r4.currentIndex
            int r2 = r2 + 1
            r4.currentIndex = r2
            r10.subcompose(r3, r11, r12)
            if (r0 == r1) goto Lb2
            androidx.compose.ui.node.LayoutNode$LayoutState r11 = androidx.compose.ui.node.LayoutNode.LayoutState.LayingOut
            if (r0 != r11) goto Lad
            goto Lb2
        Lad:
            java.util.List r11 = r3.getChildLookaheadMeasurables$ui_release()
            return r11
        Lb2:
            java.util.List r11 = r3.getChildMeasurables$ui_release()
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.subcompose(java.lang.Object, r7.p):java.util.List");
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B0\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nR$\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0002\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR-\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u001b\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010!\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\u001c\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R(\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001a0$8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R$\u0010.\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\u001a8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b,\u0010\u001e\"\u0004\b-\u0010 ¨\u0006/"}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$NodeState;", "", "slotId", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "Landroidx/compose/runtime/ReusableComposition;", "composition", "<init>", "(Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/ReusableComposition;)V", "Ljava/lang/Object;", "getSlotId", "()Ljava/lang/Object;", "setSlotId", "(Ljava/lang/Object;)V", "Lr7/p;", "getContent", "()Lr7/p;", "setContent", "(Lr7/p;)V", "Landroidx/compose/runtime/ReusableComposition;", "getComposition", "()Landroidx/compose/runtime/ReusableComposition;", "setComposition", "(Landroidx/compose/runtime/ReusableComposition;)V", "", "forceRecompose", "Z", "getForceRecompose", "()Z", "setForceRecompose", "(Z)V", "forceReuse", "getForceReuse", "setForceReuse", "Landroidx/compose/runtime/MutableState;", "activeState", "Landroidx/compose/runtime/MutableState;", "getActiveState", "()Landroidx/compose/runtime/MutableState;", "setActiveState", "(Landroidx/compose/runtime/MutableState;)V", "value", "getActive", "setActive", "active", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class NodeState {
        private MutableState<Boolean> activeState;
        private ReusableComposition composition;
        private p<? super Composer, ? super Integer, t0> content;
        private boolean forceRecompose;
        private boolean forceReuse;
        private Object slotId;

        public NodeState(Object obj, p<? super Composer, ? super Integer, t0> pVar, ReusableComposition reusableComposition) {
            this.slotId = obj;
            this.content = pVar;
            this.composition = reusableComposition;
            this.activeState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null);
        }

        public final boolean getActive() {
            return this.activeState.getValue().booleanValue();
        }

        public final MutableState<Boolean> getActiveState() {
            return this.activeState;
        }

        public final ReusableComposition getComposition() {
            return this.composition;
        }

        public final p<Composer, Integer, t0> getContent() {
            return this.content;
        }

        public final boolean getForceRecompose() {
            return this.forceRecompose;
        }

        public final boolean getForceReuse() {
            return this.forceReuse;
        }

        public final Object getSlotId() {
            return this.slotId;
        }

        public final void setActive(boolean z) {
            this.activeState.setValue(Boolean.valueOf(z));
        }

        public final void setActiveState(MutableState<Boolean> mutableState) {
            this.activeState = mutableState;
        }

        public final void setComposition(ReusableComposition reusableComposition) {
            this.composition = reusableComposition;
        }

        public final void setContent(p<? super Composer, ? super Integer, t0> pVar) {
            this.content = pVar;
        }

        public final void setForceRecompose(boolean z) {
            this.forceRecompose = z;
        }

        public final void setForceReuse(boolean z) {
            this.forceReuse = z;
        }

        public final void setSlotId(Object obj) {
            this.slotId = obj;
        }

        public /* synthetic */ NodeState(Object obj, p pVar, ReusableComposition reusableComposition, int i10, kotlin.jvm.internal.h hVar) {
            this(obj, pVar, (i10 & 4) != 0 ? null : reusableComposition);
        }
    }

    private final void subcompose(LayoutNode node, Object slotId, p<? super Composer, ? super Integer, t0> content) {
        HashMap<LayoutNode, NodeState> map = this.nodeToNodeState;
        NodeState nodeState = map.get(node);
        if (nodeState == null) {
            NodeState nodeState2 = new NodeState(slotId, ComposableSingletons$SubcomposeLayoutKt.INSTANCE.m4621getLambda1$ui_release(), null, 4, null);
            map.put(node, nodeState2);
            nodeState = nodeState2;
        }
        NodeState nodeState3 = nodeState;
        ReusableComposition composition = nodeState3.getComposition();
        boolean hasInvalidations = composition != null ? composition.getHasInvalidations() : true;
        if (nodeState3.getContent() != content || hasInvalidations || nodeState3.getForceRecompose()) {
            nodeState3.setContent(content);
            subcompose(node, nodeState3);
            nodeState3.setForceRecompose(false);
        }
    }

    private final void subcompose(LayoutNode node, NodeState nodeState) {
        Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                LayoutNode layoutNode = this.root;
                layoutNode.ignoreRemeasureRequests = true;
                p<Composer, Integer, t0> content = nodeState.getContent();
                ReusableComposition composition = nodeState.getComposition();
                CompositionContext compositionContext = this.compositionContext;
                if (compositionContext != null) {
                    nodeState.setComposition(subcomposeInto(composition, node, nodeState.getForceReuse(), compositionContext, ComposableLambdaKt.composableLambdaInstance(-1750409193, true, new LayoutNodeSubcompositionsState$subcompose$3$1$1(nodeState, content))));
                    nodeState.setForceReuse(false);
                    layoutNode.ignoreRemeasureRequests = false;
                    snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    snapshotCreateNonObservableSnapshot.dispose();
                    return;
                }
                throw new IllegalStateException("parent composition reference not set");
            } catch (Throwable th) {
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                throw th;
            }
        } catch (Throwable th2) {
            snapshotCreateNonObservableSnapshot.dispose();
            throw th2;
        }
    }
}
