package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u001a&\u0010\b\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a:\u0010\u000e\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\n0\tH\u0000ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u0013\u0010\u0010\u001a\u00020\u000f*\u00020\u0000H\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0000*\u00020\u0000H\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u0000*\u00020\u0000H\u0002¢\u0006\u0004\b\u0014\u0010\u0013\"\u0018\u0010\u0015\u001a\u00020\n*\u00020\u00008@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\"\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u0000*\u00020\u00008@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/focus/FocusTargetNode;", "Landroidx/compose/ui/focus/FocusDirection;", "focusDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/ui/focus/FocusRequester;", "customFocusSearch--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILandroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/ui/focus/FocusRequester;", "customFocusSearch", "Lkotlin/Function1;", "", "onFound", "focusSearch-sMXa3k8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILandroidx/compose/ui/unit/LayoutDirection;Lr7/l;)Z", "focusSearch", "Landroidx/compose/ui/geometry/Rect;", "focusRect", "(Landroidx/compose/ui/focus/FocusTargetNode;)Landroidx/compose/ui/geometry/Rect;", "findActiveFocusNode", "(Landroidx/compose/ui/focus/FocusTargetNode;)Landroidx/compose/ui/focus/FocusTargetNode;", "findNonDeactivatedParent", "isEligibleForFocusSearch", "(Landroidx/compose/ui/focus/FocusTargetNode;)Z", "getActiveChild", "activeChild", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FocusTraversalKt {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FocusStateImpl.values().length];
            try {
                iArr2[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[FocusStateImpl.ActiveParent.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[FocusStateImpl.Captured.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: renamed from: customFocusSearch--OM-vw8, reason: not valid java name */
    public static final FocusRequester m3188customFocusSearchOMvw8(FocusTargetNode focusTargetNode, int i10, LayoutDirection layoutDirection) {
        FocusRequester end;
        FocusRequester focusRequester;
        FocusRequester start;
        FocusProperties focusPropertiesFetchFocusProperties$ui_release = focusTargetNode.fetchFocusProperties$ui_release();
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3163getNextdhqQ8s())) {
            return focusPropertiesFetchFocusProperties$ui_release.getNext();
        }
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3164getPreviousdhqQ8s())) {
            return focusPropertiesFetchFocusProperties$ui_release.getPrevious();
        }
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3166getUpdhqQ8s())) {
            return focusPropertiesFetchFocusProperties$ui_release.getUp();
        }
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3159getDowndhqQ8s())) {
            return focusPropertiesFetchFocusProperties$ui_release.getDown();
        }
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3162getLeftdhqQ8s())) {
            int i11 = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
            if (i11 == 1) {
                start = focusPropertiesFetchFocusProperties$ui_release.getStart();
            } else {
                if (i11 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                start = focusPropertiesFetchFocusProperties$ui_release.getEnd();
            }
            focusRequester = start != FocusRequester.INSTANCE.getDefault() ? start : null;
            return focusRequester == null ? focusPropertiesFetchFocusProperties$ui_release.getLeft() : focusRequester;
        }
        if (!FocusDirection.m3153equalsimpl0(i10, companion.m3165getRightdhqQ8s())) {
            if (FocusDirection.m3153equalsimpl0(i10, companion.m3160getEnterdhqQ8s())) {
                return (FocusRequester) focusPropertiesFetchFocusProperties$ui_release.getEnter().invoke(FocusDirection.m3150boximpl(i10));
            }
            if (FocusDirection.m3153equalsimpl0(i10, companion.m3161getExitdhqQ8s())) {
                return (FocusRequester) focusPropertiesFetchFocusProperties$ui_release.getExit().invoke(FocusDirection.m3150boximpl(i10));
            }
            throw new IllegalStateException("invalid FocusDirection");
        }
        int i12 = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
        if (i12 == 1) {
            end = focusPropertiesFetchFocusProperties$ui_release.getEnd();
        } else {
            if (i12 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            end = focusPropertiesFetchFocusProperties$ui_release.getStart();
        }
        focusRequester = end != FocusRequester.INSTANCE.getDefault() ? end : null;
        return focusRequester == null ? focusPropertiesFetchFocusProperties$ui_release.getRight() : focusRequester;
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x004f, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.focus.FocusTargetNode findActiveFocusNode(androidx.compose.ui.focus.FocusTargetNode r10) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTraversalKt.findActiveFocusNode(androidx.compose.ui.focus.FocusTargetNode):androidx.compose.ui.focus.FocusTargetNode");
    }

    private static final FocusTargetNode findNonDeactivatedParent(FocusTargetNode focusTargetNode) {
        NodeChain nodes;
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(1024);
        if (!focusTargetNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = focusTargetNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & iM4843constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM4843constructorimpl) != 0) {
                        Modifier.Node nodePop = parent;
                        MutableVector mutableVector = null;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode2 = (FocusTargetNode) nodePop;
                                if (focusTargetNode2.fetchFocusProperties$ui_release().getCanFocus()) {
                                    return focusTargetNode2;
                                }
                            } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i10 = 0;
                                for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                    if ((delegate.getKindSet() & iM4843constructorimpl) != 0) {
                                        i10++;
                                        if (i10 == 1) {
                                            nodePop = delegate;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                mutableVector.add(nodePop);
                                                nodePop = null;
                                            }
                                            mutableVector.add(delegate);
                                        }
                                    }
                                }
                                if (i10 == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return null;
    }

    public static final Rect focusRect(FocusTargetNode focusTargetNode) {
        Rect rectLocalBoundingBoxOf;
        NodeCoordinator coordinator = focusTargetNode.getCoordinator();
        return (coordinator == null || (rectLocalBoundingBoxOf = LayoutCoordinatesKt.findRootCoordinates(coordinator).localBoundingBoxOf(coordinator, false)) == null) ? Rect.INSTANCE.getZero() : rectLocalBoundingBoxOf;
    }

    /* JADX INFO: renamed from: focusSearch-sMXa3k8, reason: not valid java name */
    public static final boolean m3189focusSearchsMXa3k8(FocusTargetNode focusTargetNode, int i10, LayoutDirection layoutDirection, l<? super FocusTargetNode, Boolean> lVar) {
        int iM3165getRightdhqQ8s;
        Boolean boolM3201twoDimensionalFocusSearchOMvw8;
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3163getNextdhqQ8s()) ? true : FocusDirection.m3153equalsimpl0(i10, companion.m3164getPreviousdhqQ8s())) {
            return OneDimensionalFocusSearchKt.m3192oneDimensionalFocusSearchOMvw8(focusTargetNode, i10, lVar);
        }
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3162getLeftdhqQ8s()) ? true : FocusDirection.m3153equalsimpl0(i10, companion.m3165getRightdhqQ8s()) ? true : FocusDirection.m3153equalsimpl0(i10, companion.m3166getUpdhqQ8s()) ? true : FocusDirection.m3153equalsimpl0(i10, companion.m3159getDowndhqQ8s())) {
            Boolean boolM3201twoDimensionalFocusSearchOMvw82 = TwoDimensionalFocusSearchKt.m3201twoDimensionalFocusSearchOMvw8(focusTargetNode, i10, lVar);
            if (boolM3201twoDimensionalFocusSearchOMvw82 != null) {
                return boolM3201twoDimensionalFocusSearchOMvw82.booleanValue();
            }
            return false;
        }
        if (!FocusDirection.m3153equalsimpl0(i10, companion.m3160getEnterdhqQ8s())) {
            if (!FocusDirection.m3153equalsimpl0(i10, companion.m3161getExitdhqQ8s())) {
                throw new IllegalStateException(("Focus search invoked with invalid FocusDirection " + ((Object) FocusDirection.m3155toStringimpl(i10))).toString());
            }
            FocusTargetNode focusTargetNodeFindActiveFocusNode = findActiveFocusNode(focusTargetNode);
            FocusTargetNode focusTargetNodeFindNonDeactivatedParent = focusTargetNodeFindActiveFocusNode != null ? findNonDeactivatedParent(focusTargetNodeFindActiveFocusNode) : null;
            if (focusTargetNodeFindNonDeactivatedParent == null || focusTargetNodeFindNonDeactivatedParent.equals(focusTargetNode)) {
                return false;
            }
            return ((Boolean) lVar.invoke(focusTargetNodeFindNonDeactivatedParent)).booleanValue();
        }
        int i11 = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
        if (i11 == 1) {
            iM3165getRightdhqQ8s = companion.m3165getRightdhqQ8s();
        } else {
            if (i11 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            iM3165getRightdhqQ8s = companion.m3162getLeftdhqQ8s();
        }
        FocusTargetNode focusTargetNodeFindActiveFocusNode2 = findActiveFocusNode(focusTargetNode);
        if (focusTargetNodeFindActiveFocusNode2 == null || (boolM3201twoDimensionalFocusSearchOMvw8 = TwoDimensionalFocusSearchKt.m3201twoDimensionalFocusSearchOMvw8(focusTargetNodeFindActiveFocusNode2, iM3165getRightdhqQ8s, lVar)) == null) {
            return false;
        }
        return boolM3201twoDimensionalFocusSearchOMvw8.booleanValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x003b, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.focus.FocusTargetNode getActiveChild(androidx.compose.ui.focus.FocusTargetNode r10) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTraversalKt.getActiveChild(androidx.compose.ui.focus.FocusTargetNode):androidx.compose.ui.focus.FocusTargetNode");
    }

    public static final boolean isEligibleForFocusSearch(FocusTargetNode focusTargetNode) {
        LayoutNode layoutNode;
        NodeCoordinator coordinator;
        LayoutNode layoutNode2;
        NodeCoordinator coordinator2 = focusTargetNode.getCoordinator();
        return (coordinator2 == null || (layoutNode = coordinator2.getLayoutNode()) == null || !layoutNode.isPlaced() || (coordinator = focusTargetNode.getCoordinator()) == null || (layoutNode2 = coordinator.getLayoutNode()) == null || !layoutNode2.isAttached()) ? false : true;
    }
}
