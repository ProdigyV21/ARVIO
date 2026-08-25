package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0000\u0018\u00002\u00020\u0001:\u0001aB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000f\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0011\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0011\u0010\u0010J\u001f\u0010\u0012\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0010J\u001f\u0010\u0013\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0013\u0010\u0010J\u0015\u0010\u0014\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0002¢\u0006\u0004\b\u0014\u0010\u0005J\u001f\u0010\u0017\u001a\u00020\r2\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\b¢\u0006\u0004\b\u0019\u0010\u001aJ \u0010\u0017\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001f\u0010 J\u001d\u0010\"\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\r¢\u0006\u0004\b\"\u0010#J\u0017\u0010%\u001a\u00020\b2\b\b\u0002\u0010$\u001a\u00020\r¢\u0006\u0004\b%\u0010&J\u0015\u0010(\u001a\u00020\b2\u0006\u0010'\u001a\u00020\u0002¢\u0006\u0004\b(\u0010\u0005J$\u0010+\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*J$\u0010-\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b,\u0010*J\u0017\u0010.\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0002H\u0002¢\u0006\u0004\b.\u0010\u0005J\u001e\u00100\u001a\u00020\b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\b0\u0015H\u0082\b¢\u0006\u0004\b0\u00101J\u000f\u00102\u001a\u00020\bH\u0002¢\u0006\u0004\b2\u0010\u001aJ+\u00104\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010!\u001a\u00020\r2\b\b\u0002\u00103\u001a\u00020\rH\u0002¢\u0006\u0004\b4\u00105J\u001f\u00106\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\rH\u0002¢\u0006\u0004\b6\u0010#J\u001f\u00107\u001a\u00020\b2\u0006\u0010'\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\rH\u0002¢\u0006\u0004\b7\u0010#J\u001f\u00108\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\rH\u0002¢\u0006\u0004\b8\u0010#J\u001b\u00109\u001a\u00020\r*\u00020\u00022\u0006\u0010!\u001a\u00020\rH\u0002¢\u0006\u0004\b9\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010:R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u0016\u0010>\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010A\u001a\u00020@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010BR\u001a\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001d0C8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010ER$\u0010H\u001a\u00020F2\u0006\u0010G\u001a\u00020F8F@BX\u0086\u000e¢\u0006\f\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010KR\u001a\u0010M\u001a\b\u0012\u0004\u0012\u00020L0C8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bM\u0010ER\u001e\u0010N\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bN\u0010OR\u0016\u0010Q\u001a\u0004\u0018\u00010P8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bQ\u0010RR\u0011\u0010U\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0011\u0010W\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\bV\u0010TR\u0018\u0010Z\u001a\u00020\r*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bX\u0010YR\u0018\u0010\\\u001a\u00020\r*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b[\u0010YR\u0018\u0010^\u001a\u00020\r*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b]\u0010YR\u0018\u0010`\u001a\u00020\r*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b_\u0010Y\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006b"}, d2 = {"Landroidx/compose/ui/node/MeasureAndLayoutDelegate;", "", "Landroidx/compose/ui/node/LayoutNode;", "root", "<init>", "(Landroidx/compose/ui/node/LayoutNode;)V", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Lx6/t0;", "updateRootConstraints-BRTryo0", "(J)V", "updateRootConstraints", "layoutNode", "", "forced", "requestLookaheadRemeasure", "(Landroidx/compose/ui/node/LayoutNode;Z)Z", "requestRemeasure", "requestLookaheadRelayout", "requestRelayout", "requestOnPositionedCallback", "Lkotlin/Function0;", "onLayout", "measureAndLayout", "(Lr7/a;)Z", "measureOnly", "()V", "measureAndLayout-0kLqBqw", "(Landroidx/compose/ui/node/LayoutNode;J)V", "Landroidx/compose/ui/node/Owner$OnLayoutCompletedListener;", "listener", "registerOnLayoutCompletedListener", "(Landroidx/compose/ui/node/Owner$OnLayoutCompletedListener;)V", "affectsLookahead", "forceMeasureTheSubtree", "(Landroidx/compose/ui/node/LayoutNode;Z)V", "forceDispatch", "dispatchOnPositionedCallbacks", "(Z)V", "node", "onNodeDetached", "doLookaheadRemeasure-sdFAvZA", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/unit/Constraints;)Z", "doLookaheadRemeasure", "doRemeasure-sdFAvZA", "doRemeasure", "remeasureLookaheadRootsInSubtree", "block", "performMeasureAndLayout", "(Lr7/a;)V", "callOnLayoutCompletedListeners", "relayoutNeeded", "remeasureAndRelayoutIfNeeded", "(Landroidx/compose/ui/node/LayoutNode;ZZ)Z", "remeasureOnly", "onlyRemeasureIfScheduled", "forceMeasureTheSubtreeInternal", "measurePending", "Landroidx/compose/ui/node/LayoutNode;", "Landroidx/compose/ui/node/DepthSortedSetsForDifferentPasses;", "relayoutNodes", "Landroidx/compose/ui/node/DepthSortedSetsForDifferentPasses;", "duringMeasureLayout", "Z", "Landroidx/compose/ui/node/OnPositionedDispatcher;", "onPositionedDispatcher", "Landroidx/compose/ui/node/OnPositionedDispatcher;", "Landroidx/compose/runtime/collection/MutableVector;", "onLayoutCompletedListeners", "Landroidx/compose/runtime/collection/MutableVector;", "", "<set-?>", "measureIteration", "J", "getMeasureIteration", "()J", "Landroidx/compose/ui/node/MeasureAndLayoutDelegate$PostponedRequest;", "postponedMeasureRequests", "rootConstraints", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/node/LayoutTreeConsistencyChecker;", "consistencyChecker", "Landroidx/compose/ui/node/LayoutTreeConsistencyChecker;", "getHasPendingMeasureOrLayout", "()Z", "hasPendingMeasureOrLayout", "getHasPendingOnPositionedCallbacks", "hasPendingOnPositionedCallbacks", "getMeasureAffectsParent", "(Landroidx/compose/ui/node/LayoutNode;)Z", "measureAffectsParent", "getCanAffectParent", "canAffectParent", "getCanAffectParentInLookahead", "canAffectParentInLookahead", "getMeasureAffectsParentLookahead", "measureAffectsParentLookahead", "PostponedRequest", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MeasureAndLayoutDelegate {
    public static final int $stable = 8;
    private final LayoutTreeConsistencyChecker consistencyChecker;
    private boolean duringMeasureLayout;
    private long measureIteration;
    private final MutableVector<Owner.OnLayoutCompletedListener> onLayoutCompletedListeners;
    private final OnPositionedDispatcher onPositionedDispatcher;
    private final MutableVector<PostponedRequest> postponedMeasureRequests;
    private final DepthSortedSetsForDifferentPasses relayoutNodes;
    private final LayoutNode root;
    private Constraints rootConstraints;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/node/MeasureAndLayoutDelegate$PostponedRequest;", "", "node", "Landroidx/compose/ui/node/LayoutNode;", "isLookahead", "", "isForced", "(Landroidx/compose/ui/node/LayoutNode;ZZ)V", "()Z", "getNode", "()Landroidx/compose/ui/node/LayoutNode;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class PostponedRequest {
        public static final int $stable = 8;
        private final boolean isForced;
        private final boolean isLookahead;
        private final LayoutNode node;

        public PostponedRequest(LayoutNode layoutNode, boolean z, boolean z5) {
            this.node = layoutNode;
            this.isLookahead = z;
            this.isForced = z5;
        }

        public final LayoutNode getNode() {
            return this.node;
        }

        /* JADX INFO: renamed from: isForced, reason: from getter */
        public final boolean getIsForced() {
            return this.isForced;
        }

        /* JADX INFO: renamed from: isLookahead, reason: from getter */
        public final boolean getIsLookahead() {
            return this.isLookahead;
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

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
                iArr[LayoutNode.LayoutState.LookaheadLayingOut.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[LayoutNode.LayoutState.LayingOut.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[LayoutNode.LayoutState.Idle.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MeasureAndLayoutDelegate(LayoutNode layoutNode) {
        this.root = layoutNode;
        Owner.Companion companion = Owner.INSTANCE;
        DepthSortedSetsForDifferentPasses depthSortedSetsForDifferentPasses = new DepthSortedSetsForDifferentPasses(companion.getEnableExtraAssertions());
        this.relayoutNodes = depthSortedSetsForDifferentPasses;
        this.onPositionedDispatcher = new OnPositionedDispatcher();
        this.onLayoutCompletedListeners = new MutableVector<>(new Owner.OnLayoutCompletedListener[16], 0);
        this.measureIteration = 1L;
        MutableVector<PostponedRequest> mutableVector = new MutableVector<>(new PostponedRequest[16], 0);
        this.postponedMeasureRequests = mutableVector;
        this.consistencyChecker = companion.getEnableExtraAssertions() ? new LayoutTreeConsistencyChecker(layoutNode, depthSortedSetsForDifferentPasses, mutableVector.asMutableList()) : null;
    }

    private final void callOnLayoutCompletedListeners() {
        MutableVector<Owner.OnLayoutCompletedListener> mutableVector = this.onLayoutCompletedListeners;
        int size = mutableVector.getSize();
        if (size > 0) {
            Owner.OnLayoutCompletedListener[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                content[i10].onLayoutComplete();
                i10++;
            } while (i10 < size);
        }
        this.onLayoutCompletedListeners.clear();
    }

    public static /* synthetic */ void dispatchOnPositionedCallbacks$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        measureAndLayoutDelegate.dispatchOnPositionedCallbacks(z);
    }

    /* JADX INFO: renamed from: doLookaheadRemeasure-sdFAvZA, reason: not valid java name */
    private final boolean m4791doLookaheadRemeasuresdFAvZA(LayoutNode layoutNode, Constraints constraints) {
        if (layoutNode.getLookaheadRoot() == null) {
            return false;
        }
        boolean zM4754lookaheadRemeasure_Sx5XlM$ui_release = constraints != null ? layoutNode.m4754lookaheadRemeasure_Sx5XlM$ui_release(constraints) : LayoutNode.m4750lookaheadRemeasure_Sx5XlM$ui_release$default(layoutNode, null, 1, null);
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        if (zM4754lookaheadRemeasure_Sx5XlM$ui_release && parent$ui_release != null) {
            if (parent$ui_release.getLookaheadRoot() == null) {
                requestRemeasure$default(this, parent$ui_release, false, 2, null);
                return zM4754lookaheadRemeasure_Sx5XlM$ui_release;
            }
            if (layoutNode.getMeasuredByParentInLookahead$ui_release() == LayoutNode.UsageByParent.InMeasureBlock) {
                requestLookaheadRemeasure$default(this, parent$ui_release, false, 2, null);
                return zM4754lookaheadRemeasure_Sx5XlM$ui_release;
            }
            if (layoutNode.getMeasuredByParentInLookahead$ui_release() == LayoutNode.UsageByParent.InLayoutBlock) {
                requestLookaheadRelayout$default(this, parent$ui_release, false, 2, null);
            }
        }
        return zM4754lookaheadRemeasure_Sx5XlM$ui_release;
    }

    /* JADX INFO: renamed from: doRemeasure-sdFAvZA, reason: not valid java name */
    private final boolean m4792doRemeasuresdFAvZA(LayoutNode layoutNode, Constraints constraints) {
        boolean zM4755remeasure_Sx5XlM$ui_release = constraints != null ? layoutNode.m4755remeasure_Sx5XlM$ui_release(constraints) : LayoutNode.m4751remeasure_Sx5XlM$ui_release$default(layoutNode, null, 1, null);
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        if (zM4755remeasure_Sx5XlM$ui_release && parent$ui_release != null) {
            if (layoutNode.getMeasuredByParent$ui_release() == LayoutNode.UsageByParent.InMeasureBlock) {
                requestRemeasure$default(this, parent$ui_release, false, 2, null);
                return zM4755remeasure_Sx5XlM$ui_release;
            }
            if (layoutNode.getMeasuredByParent$ui_release() == LayoutNode.UsageByParent.InLayoutBlock) {
                requestRelayout$default(this, parent$ui_release, false, 2, null);
            }
        }
        return zM4755remeasure_Sx5XlM$ui_release;
    }

    private final void forceMeasureTheSubtreeInternal(LayoutNode layoutNode, boolean affectsLookahead) {
        MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                LayoutNode layoutNode2 = content[i10];
                if ((!affectsLookahead && getMeasureAffectsParent(layoutNode2)) || (affectsLookahead && getMeasureAffectsParentLookahead(layoutNode2))) {
                    if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(layoutNode2) && !affectsLookahead) {
                        if (layoutNode2.getLookaheadMeasurePending$ui_release() && this.relayoutNodes.contains(layoutNode2, true)) {
                            remeasureAndRelayoutIfNeeded(layoutNode2, true, false);
                        } else {
                            forceMeasureTheSubtree(layoutNode2, true);
                        }
                    }
                    onlyRemeasureIfScheduled(layoutNode2, affectsLookahead);
                    if (!measurePending(layoutNode2, affectsLookahead)) {
                        forceMeasureTheSubtreeInternal(layoutNode2, affectsLookahead);
                    }
                }
                i10++;
            } while (i10 < size);
        }
        onlyRemeasureIfScheduled(layoutNode, affectsLookahead);
    }

    private final boolean getCanAffectParent(LayoutNode layoutNode) {
        return layoutNode.getMeasurePending$ui_release() && getMeasureAffectsParent(layoutNode);
    }

    private final boolean getCanAffectParentInLookahead(LayoutNode layoutNode) {
        return layoutNode.getLookaheadMeasurePending$ui_release() && getMeasureAffectsParentLookahead(layoutNode);
    }

    private final boolean getMeasureAffectsParent(LayoutNode layoutNode) {
        return layoutNode.getMeasuredByParent$ui_release() == LayoutNode.UsageByParent.InMeasureBlock || layoutNode.getLayoutDelegate().getAlignmentLinesOwner$ui_release().getAlignmentLines().getRequired$ui_release();
    }

    private final boolean getMeasureAffectsParentLookahead(LayoutNode layoutNode) {
        AlignmentLinesOwner lookaheadAlignmentLinesOwner$ui_release;
        AlignmentLines alignmentLines;
        return layoutNode.getMeasuredByParentInLookahead$ui_release() == LayoutNode.UsageByParent.InMeasureBlock || !((lookaheadAlignmentLinesOwner$ui_release = layoutNode.getLayoutDelegate().getLookaheadAlignmentLinesOwner$ui_release()) == null || (alignmentLines = lookaheadAlignmentLinesOwner$ui_release.getAlignmentLines()) == null || !alignmentLines.getRequired$ui_release());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean measureAndLayout$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            aVar = null;
        }
        return measureAndLayoutDelegate.measureAndLayout(aVar);
    }

    private final boolean measurePending(LayoutNode layoutNode, boolean z) {
        return z ? layoutNode.getLookaheadMeasurePending$ui_release() : layoutNode.getMeasurePending$ui_release();
    }

    private final void onlyRemeasureIfScheduled(LayoutNode node, boolean affectsLookahead) {
        if (measurePending(node, affectsLookahead) && this.relayoutNodes.contains(node, affectsLookahead)) {
            remeasureAndRelayoutIfNeeded(node, affectsLookahead, false);
        }
    }

    private final void performMeasureAndLayout(r7.a<t0> block) {
        if (!this.root.isAttached()) {
            throw new IllegalArgumentException("performMeasureAndLayout called with unattached root");
        }
        if (!this.root.isPlaced()) {
            throw new IllegalArgumentException("performMeasureAndLayout called with unplaced root");
        }
        if (this.duringMeasureLayout) {
            throw new IllegalArgumentException("performMeasureAndLayout called during measure layout");
        }
        if (this.rootConstraints != null) {
            this.duringMeasureLayout = true;
            try {
                block.invoke();
                this.duringMeasureLayout = false;
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker != null) {
                    layoutTreeConsistencyChecker.assertConsistent();
                }
            } catch (Throwable th) {
                this.duringMeasureLayout = false;
                throw th;
            }
        }
    }

    private final boolean remeasureAndRelayoutIfNeeded(LayoutNode layoutNode, boolean affectsLookahead, boolean relayoutNeeded) {
        boolean zM4791doLookaheadRemeasuresdFAvZA;
        boolean zM4792doRemeasuresdFAvZA;
        LayoutNode parent$ui_release;
        int i10 = 0;
        if (layoutNode.getIsDeactivated()) {
            return false;
        }
        if (!layoutNode.isPlaced() && !layoutNode.isPlacedByParent() && !getCanAffectParent(layoutNode) && !p.a(layoutNode.isPlacedInLookahead(), Boolean.TRUE) && !getCanAffectParentInLookahead(layoutNode) && !layoutNode.getAlignmentLinesRequired$ui_release()) {
            return false;
        }
        if (layoutNode.getLookaheadMeasurePending$ui_release() || layoutNode.getMeasurePending$ui_release()) {
            Constraints constraints = layoutNode == this.root ? this.rootConstraints : null;
            zM4791doLookaheadRemeasuresdFAvZA = (layoutNode.getLookaheadMeasurePending$ui_release() && affectsLookahead) ? m4791doLookaheadRemeasuresdFAvZA(layoutNode, constraints) : false;
            zM4792doRemeasuresdFAvZA = m4792doRemeasuresdFAvZA(layoutNode, constraints);
        } else {
            zM4792doRemeasuresdFAvZA = false;
            zM4791doLookaheadRemeasuresdFAvZA = false;
        }
        if (relayoutNeeded) {
            if ((zM4791doLookaheadRemeasuresdFAvZA || layoutNode.getLookaheadLayoutPending$ui_release()) && p.a(layoutNode.isPlacedInLookahead(), Boolean.TRUE) && affectsLookahead) {
                layoutNode.lookaheadReplace$ui_release();
            }
            if (layoutNode.getLayoutPending$ui_release() && (layoutNode == this.root || ((parent$ui_release = layoutNode.getParent$ui_release()) != null && parent$ui_release.isPlaced() && layoutNode.isPlacedByParent()))) {
                if (layoutNode == this.root) {
                    layoutNode.place$ui_release(0, 0);
                } else {
                    layoutNode.replace$ui_release();
                }
                this.onPositionedDispatcher.onNodePositioned(layoutNode);
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker != null) {
                    layoutTreeConsistencyChecker.assertConsistent();
                }
            }
        }
        if (this.postponedMeasureRequests.isNotEmpty()) {
            MutableVector<PostponedRequest> mutableVector = this.postponedMeasureRequests;
            int size = mutableVector.getSize();
            if (size > 0) {
                PostponedRequest[] content = mutableVector.getContent();
                do {
                    PostponedRequest postponedRequest = content[i10];
                    if (postponedRequest.getNode().isAttached()) {
                        if (postponedRequest.getIsLookahead()) {
                            requestLookaheadRemeasure(postponedRequest.getNode(), postponedRequest.getIsForced());
                        } else {
                            requestRemeasure(postponedRequest.getNode(), postponedRequest.getIsForced());
                        }
                    }
                    i10++;
                } while (i10 < size);
            }
            this.postponedMeasureRequests.clear();
        }
        return zM4792doRemeasuresdFAvZA;
    }

    public static /* synthetic */ boolean remeasureAndRelayoutIfNeeded$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, boolean z5, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        if ((i10 & 4) != 0) {
            z5 = true;
        }
        return measureAndLayoutDelegate.remeasureAndRelayoutIfNeeded(layoutNode, z, z5);
    }

    private final void remeasureLookaheadRootsInSubtree(LayoutNode layoutNode) {
        MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                LayoutNode layoutNode2 = content[i10];
                if (getMeasureAffectsParent(layoutNode2)) {
                    if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(layoutNode2)) {
                        remeasureOnly(layoutNode2, true);
                    } else {
                        remeasureLookaheadRootsInSubtree(layoutNode2);
                    }
                }
                i10++;
            } while (i10 < size);
        }
    }

    private final void remeasureOnly(LayoutNode layoutNode, boolean affectsLookahead) {
        Constraints constraints = layoutNode == this.root ? this.rootConstraints : null;
        if (affectsLookahead) {
            m4791doLookaheadRemeasuresdFAvZA(layoutNode, constraints);
        } else {
            m4792doRemeasuresdFAvZA(layoutNode, constraints);
        }
    }

    public static /* synthetic */ boolean requestLookaheadRelayout$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestLookaheadRelayout(layoutNode, z);
    }

    public static /* synthetic */ boolean requestLookaheadRemeasure$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestLookaheadRemeasure(layoutNode, z);
    }

    public static /* synthetic */ boolean requestRelayout$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestRelayout(layoutNode, z);
    }

    public static /* synthetic */ boolean requestRemeasure$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestRemeasure(layoutNode, z);
    }

    public final void dispatchOnPositionedCallbacks(boolean forceDispatch) {
        if (forceDispatch) {
            this.onPositionedDispatcher.onRootNodePositioned(this.root);
        }
        this.onPositionedDispatcher.dispatch();
    }

    public final void forceMeasureTheSubtree(LayoutNode layoutNode, boolean affectsLookahead) {
        if (this.relayoutNodes.isEmpty(affectsLookahead)) {
            return;
        }
        if (!this.duringMeasureLayout) {
            throw new IllegalStateException("forceMeasureTheSubtree should be executed during the measureAndLayout pass");
        }
        if (measurePending(layoutNode, affectsLookahead)) {
            throw new IllegalArgumentException("node not yet measured");
        }
        forceMeasureTheSubtreeInternal(layoutNode, affectsLookahead);
    }

    public final boolean getHasPendingMeasureOrLayout() {
        return this.relayoutNodes.isNotEmpty();
    }

    public final boolean getHasPendingOnPositionedCallbacks() {
        return this.onPositionedDispatcher.isNotEmpty();
    }

    public final long getMeasureIteration() {
        if (this.duringMeasureLayout) {
            return this.measureIteration;
        }
        throw new IllegalArgumentException("measureIteration should be only used during the measure/layout pass");
    }

    public final boolean measureAndLayout(r7.a<t0> onLayout) throws Throwable {
        MeasureAndLayoutDelegate measureAndLayoutDelegate;
        Throwable th;
        boolean z;
        LayoutNode layoutNodePop;
        if (!this.root.isAttached()) {
            throw new IllegalArgumentException("performMeasureAndLayout called with unattached root");
        }
        if (!this.root.isPlaced()) {
            throw new IllegalArgumentException("performMeasureAndLayout called with unplaced root");
        }
        if (this.duringMeasureLayout) {
            throw new IllegalArgumentException("performMeasureAndLayout called during measure layout");
        }
        boolean z5 = false;
        if (this.rootConstraints != null) {
            this.duringMeasureLayout = true;
            try {
                if (this.relayoutNodes.isNotEmpty()) {
                    DepthSortedSetsForDifferentPasses depthSortedSetsForDifferentPasses = this.relayoutNodes;
                    z = false;
                    while (depthSortedSetsForDifferentPasses.isNotEmpty()) {
                        boolean zIsEmpty = depthSortedSetsForDifferentPasses.lookaheadSet.isEmpty();
                        boolean z10 = !zIsEmpty;
                        if (zIsEmpty) {
                            layoutNodePop = depthSortedSetsForDifferentPasses.set.pop();
                        } else {
                            try {
                                layoutNodePop = depthSortedSetsForDifferentPasses.lookaheadSet.pop();
                            } catch (Throwable th2) {
                                th = th2;
                                measureAndLayoutDelegate = this;
                                measureAndLayoutDelegate.duringMeasureLayout = false;
                                throw th;
                            }
                        }
                        LayoutNode layoutNode = layoutNodePop;
                        measureAndLayoutDelegate = this;
                        try {
                            boolean zRemeasureAndRelayoutIfNeeded$default = remeasureAndRelayoutIfNeeded$default(measureAndLayoutDelegate, layoutNode, z10, false, 4, null);
                            if (layoutNode == this.root && zRemeasureAndRelayoutIfNeeded$default) {
                                z = true;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            th = th;
                            measureAndLayoutDelegate.duringMeasureLayout = false;
                            throw th;
                        }
                    }
                    measureAndLayoutDelegate = this;
                    if (onLayout != null) {
                        onLayout.invoke();
                    }
                } else {
                    measureAndLayoutDelegate = this;
                    z = false;
                }
                measureAndLayoutDelegate.duringMeasureLayout = false;
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = measureAndLayoutDelegate.consistencyChecker;
                if (layoutTreeConsistencyChecker != null) {
                    layoutTreeConsistencyChecker.assertConsistent();
                }
                z5 = z;
            } catch (Throwable th4) {
                th = th4;
                measureAndLayoutDelegate = this;
            }
        }
        callOnLayoutCompletedListeners();
        return z5;
    }

    /* JADX INFO: renamed from: measureAndLayout-0kLqBqw, reason: not valid java name */
    public final void m4793measureAndLayout0kLqBqw(LayoutNode layoutNode, long constraints) {
        if (layoutNode.getIsDeactivated()) {
            return;
        }
        if (layoutNode.equals(this.root)) {
            throw new IllegalArgumentException("measureAndLayout called on root");
        }
        if (!this.root.isAttached()) {
            throw new IllegalArgumentException("performMeasureAndLayout called with unattached root");
        }
        if (!this.root.isPlaced()) {
            throw new IllegalArgumentException("performMeasureAndLayout called with unplaced root");
        }
        if (this.duringMeasureLayout) {
            throw new IllegalArgumentException("performMeasureAndLayout called during measure layout");
        }
        if (this.rootConstraints != null) {
            this.duringMeasureLayout = true;
            try {
                this.relayoutNodes.remove(layoutNode);
                boolean zM4791doLookaheadRemeasuresdFAvZA = m4791doLookaheadRemeasuresdFAvZA(layoutNode, Constraints.m5634boximpl(constraints));
                m4792doRemeasuresdFAvZA(layoutNode, Constraints.m5634boximpl(constraints));
                if (zM4791doLookaheadRemeasuresdFAvZA || layoutNode.getLookaheadLayoutPending$ui_release()) {
                    if (p.a(layoutNode.isPlacedInLookahead(), Boolean.TRUE)) {
                        layoutNode.lookaheadReplace$ui_release();
                    }
                }
                if (layoutNode.getLayoutPending$ui_release() && layoutNode.isPlaced()) {
                    layoutNode.replace$ui_release();
                    this.onPositionedDispatcher.onNodePositioned(layoutNode);
                }
                this.duringMeasureLayout = false;
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker != null) {
                    layoutTreeConsistencyChecker.assertConsistent();
                }
            } catch (Throwable th) {
                this.duringMeasureLayout = false;
                throw th;
            }
        }
        callOnLayoutCompletedListeners();
    }

    public final void measureOnly() {
        if (this.relayoutNodes.isNotEmpty()) {
            if (!this.root.isAttached()) {
                throw new IllegalArgumentException("performMeasureAndLayout called with unattached root");
            }
            if (!this.root.isPlaced()) {
                throw new IllegalArgumentException("performMeasureAndLayout called with unplaced root");
            }
            if (this.duringMeasureLayout) {
                throw new IllegalArgumentException("performMeasureAndLayout called during measure layout");
            }
            if (this.rootConstraints != null) {
                this.duringMeasureLayout = true;
                try {
                    if (!this.relayoutNodes.isEmpty(true)) {
                        if (this.root.getLookaheadRoot() != null) {
                            remeasureOnly(this.root, true);
                        } else {
                            remeasureLookaheadRootsInSubtree(this.root);
                        }
                    }
                    remeasureOnly(this.root, false);
                    this.duringMeasureLayout = false;
                    LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                    if (layoutTreeConsistencyChecker != null) {
                        layoutTreeConsistencyChecker.assertConsistent();
                    }
                } catch (Throwable th) {
                    this.duringMeasureLayout = false;
                    throw th;
                }
            }
        }
    }

    public final void onNodeDetached(LayoutNode node) {
        this.relayoutNodes.remove(node);
    }

    public final void registerOnLayoutCompletedListener(Owner.OnLayoutCompletedListener listener) {
        this.onLayoutCompletedListeners.add(listener);
    }

    public final boolean requestLookaheadRelayout(LayoutNode layoutNode, boolean forced) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[layoutNode.getLayoutState$ui_release().ordinal()];
        if (i10 != 1) {
            if (i10 != 2) {
                if (i10 != 3) {
                    if (i10 != 4 && i10 != 5) {
                        throw new NoWhenBranchMatchedException();
                    }
                }
            }
            if ((layoutNode.getLookaheadMeasurePending$ui_release() || layoutNode.getLookaheadLayoutPending$ui_release()) && !forced) {
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker != null) {
                    layoutTreeConsistencyChecker.assertConsistent();
                }
                return false;
            }
            layoutNode.markLookaheadLayoutPending$ui_release();
            layoutNode.markLayoutPending$ui_release();
            if (layoutNode.getIsDeactivated()) {
                return false;
            }
            LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
            if (p.a(layoutNode.isPlacedInLookahead(), Boolean.TRUE) && ((parent$ui_release == null || !parent$ui_release.getLookaheadMeasurePending$ui_release()) && (parent$ui_release == null || !parent$ui_release.getLookaheadLayoutPending$ui_release()))) {
                this.relayoutNodes.add(layoutNode, true);
            } else if (layoutNode.isPlaced() && ((parent$ui_release == null || !parent$ui_release.getLayoutPending$ui_release()) && (parent$ui_release == null || !parent$ui_release.getMeasurePending$ui_release()))) {
                this.relayoutNodes.add(layoutNode, false);
            }
            return !this.duringMeasureLayout;
        }
        LayoutTreeConsistencyChecker layoutTreeConsistencyChecker2 = this.consistencyChecker;
        if (layoutTreeConsistencyChecker2 != null) {
            layoutTreeConsistencyChecker2.assertConsistent();
        }
        return false;
    }

    public final boolean requestLookaheadRemeasure(LayoutNode layoutNode, boolean forced) {
        LayoutNode parent$ui_release;
        LayoutNode parent$ui_release2;
        if (layoutNode.getLookaheadRoot() == null) {
            throw new IllegalStateException("Error: requestLookaheadRemeasure cannot be called on a node outside LookaheadScope");
        }
        int i10 = WhenMappings.$EnumSwitchMapping$0[layoutNode.getLayoutState$ui_release().ordinal()];
        if (i10 != 1) {
            if (i10 != 2 && i10 != 3 && i10 != 4) {
                if (i10 != 5) {
                    throw new NoWhenBranchMatchedException();
                }
                if (layoutNode.getLookaheadMeasurePending$ui_release() && !forced) {
                    return false;
                }
                layoutNode.markLookaheadMeasurePending$ui_release();
                layoutNode.markMeasurePending$ui_release();
                if (layoutNode.getIsDeactivated()) {
                    return false;
                }
                if ((p.a(layoutNode.isPlacedInLookahead(), Boolean.TRUE) || getCanAffectParentInLookahead(layoutNode)) && ((parent$ui_release = layoutNode.getParent$ui_release()) == null || !parent$ui_release.getLookaheadMeasurePending$ui_release())) {
                    this.relayoutNodes.add(layoutNode, true);
                } else if ((layoutNode.isPlaced() || getCanAffectParent(layoutNode)) && ((parent$ui_release2 = layoutNode.getParent$ui_release()) == null || !parent$ui_release2.getMeasurePending$ui_release())) {
                    this.relayoutNodes.add(layoutNode, false);
                }
                return !this.duringMeasureLayout;
            }
            this.postponedMeasureRequests.add(new PostponedRequest(layoutNode, true, forced));
            LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
            if (layoutTreeConsistencyChecker != null) {
                layoutTreeConsistencyChecker.assertConsistent();
            }
        }
        return false;
    }

    public final void requestOnPositionedCallback(LayoutNode layoutNode) {
        this.onPositionedDispatcher.onNodePositioned(layoutNode);
    }

    public final boolean requestRelayout(LayoutNode layoutNode, boolean forced) {
        LayoutNode parent$ui_release;
        int i10 = WhenMappings.$EnumSwitchMapping$0[layoutNode.getLayoutState$ui_release().ordinal()];
        if (i10 == 1 || i10 == 2 || i10 == 3 || i10 == 4) {
            LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
            if (layoutTreeConsistencyChecker != null) {
                layoutTreeConsistencyChecker.assertConsistent();
            }
            return false;
        }
        if (i10 != 5) {
            throw new NoWhenBranchMatchedException();
        }
        if (!forced && layoutNode.isPlaced() == layoutNode.isPlacedByParent() && (layoutNode.getMeasurePending$ui_release() || layoutNode.getLayoutPending$ui_release())) {
            LayoutTreeConsistencyChecker layoutTreeConsistencyChecker2 = this.consistencyChecker;
            if (layoutTreeConsistencyChecker2 != null) {
                layoutTreeConsistencyChecker2.assertConsistent();
            }
            return false;
        }
        layoutNode.markLayoutPending$ui_release();
        if (layoutNode.getIsDeactivated()) {
            return false;
        }
        if (layoutNode.isPlacedByParent() && (((parent$ui_release = layoutNode.getParent$ui_release()) == null || !parent$ui_release.getLayoutPending$ui_release()) && (parent$ui_release == null || !parent$ui_release.getMeasurePending$ui_release()))) {
            this.relayoutNodes.add(layoutNode, false);
        }
        return !this.duringMeasureLayout;
    }

    public final boolean requestRemeasure(LayoutNode layoutNode, boolean forced) {
        LayoutNode parent$ui_release;
        int i10 = WhenMappings.$EnumSwitchMapping$0[layoutNode.getLayoutState$ui_release().ordinal()];
        if (i10 != 1 && i10 != 2) {
            if (i10 != 3 && i10 != 4) {
                if (i10 != 5) {
                    throw new NoWhenBranchMatchedException();
                }
                if (layoutNode.getMeasurePending$ui_release() && !forced) {
                    return false;
                }
                layoutNode.markMeasurePending$ui_release();
                if (layoutNode.getIsDeactivated()) {
                    return false;
                }
                if ((layoutNode.isPlaced() || getCanAffectParent(layoutNode)) && ((parent$ui_release = layoutNode.getParent$ui_release()) == null || !parent$ui_release.getMeasurePending$ui_release())) {
                    this.relayoutNodes.add(layoutNode, false);
                }
                return !this.duringMeasureLayout;
            }
            this.postponedMeasureRequests.add(new PostponedRequest(layoutNode, false, forced));
            LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
            if (layoutTreeConsistencyChecker != null) {
                layoutTreeConsistencyChecker.assertConsistent();
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: updateRootConstraints-BRTryo0, reason: not valid java name */
    public final void m4794updateRootConstraintsBRTryo0(long constraints) {
        Constraints constraints2 = this.rootConstraints;
        if (constraints2 == null ? false : Constraints.m5639equalsimpl0(constraints2.getValue(), constraints)) {
            return;
        }
        if (this.duringMeasureLayout) {
            throw new IllegalArgumentException("updateRootConstraints called while measuring");
        }
        this.rootConstraints = Constraints.m5634boximpl(constraints);
        if (this.root.getLookaheadRoot() != null) {
            this.root.markLookaheadMeasurePending$ui_release();
        }
        this.root.markMeasurePending$ui_release();
        DepthSortedSetsForDifferentPasses depthSortedSetsForDifferentPasses = this.relayoutNodes;
        LayoutNode layoutNode = this.root;
        depthSortedSetsForDifferentPasses.add(layoutNode, layoutNode.getLookaheadRoot() != null);
    }
}
