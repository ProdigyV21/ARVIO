package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aA\u0010\n\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0019\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0004¢\u0006\u0002\b\u0006H\u0000ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000b"}, d2 = {"T", "Landroidx/compose/ui/focus/FocusTargetNode;", "Landroidx/compose/ui/focus/FocusDirection;", "direction", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/BeyondBoundsLayout$BeyondBoundsScope;", "Lx6/n;", "block", "searchBeyondBounds--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILr7/l;)Ljava/lang/Object;", "searchBeyondBounds", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BeyondBoundsLayoutKt {
    /* JADX INFO: renamed from: searchBeyondBounds--OM-vw8, reason: not valid java name */
    public static final <T> T m3149searchBeyondBoundsOMvw8(FocusTargetNode focusTargetNode, int i10, l<? super BeyondBoundsLayout.BeyondBoundsScope, ? extends T> lVar) {
        Modifier.Node nodePop;
        BeyondBoundsLayout beyondBoundsLayoutParent;
        int iM4617getBeforehoxUOeE;
        NodeChain nodes;
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(1024);
        if (!focusTargetNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = focusTargetNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
        loop0: while (true) {
            if (layoutNodeRequireLayoutNode == null) {
                nodePop = null;
                break;
            }
            if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & iM4843constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM4843constructorimpl) != 0) {
                        nodePop = parent;
                        MutableVector mutableVector = null;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                break loop0;
                            }
                            if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i11 = 0;
                                for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                    if ((delegate.getKindSet() & iM4843constructorimpl) != 0) {
                                        i11++;
                                        if (i11 == 1) {
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
                                if (i11 == 1) {
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
        FocusTargetNode focusTargetNode2 = (FocusTargetNode) nodePop;
        if ((focusTargetNode2 != null && p.a(focusTargetNode2.getBeyondBoundsLayoutParent(), focusTargetNode.getBeyondBoundsLayoutParent())) || (beyondBoundsLayoutParent = focusTargetNode.getBeyondBoundsLayoutParent()) == null) {
            return null;
        }
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3166getUpdhqQ8s())) {
            iM4617getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4615getAbovehoxUOeE();
        } else if (FocusDirection.m3153equalsimpl0(i10, companion.m3159getDowndhqQ8s())) {
            iM4617getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4618getBelowhoxUOeE();
        } else if (FocusDirection.m3153equalsimpl0(i10, companion.m3162getLeftdhqQ8s())) {
            iM4617getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4619getLefthoxUOeE();
        } else if (FocusDirection.m3153equalsimpl0(i10, companion.m3165getRightdhqQ8s())) {
            iM4617getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4620getRighthoxUOeE();
        } else if (FocusDirection.m3153equalsimpl0(i10, companion.m3163getNextdhqQ8s())) {
            iM4617getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4616getAfterhoxUOeE();
        } else {
            if (!FocusDirection.m3153equalsimpl0(i10, companion.m3164getPreviousdhqQ8s())) {
                throw new IllegalStateException("Unsupported direction for beyond bounds layout");
            }
            iM4617getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4617getBeforehoxUOeE();
        }
        return (T) beyondBoundsLayoutParent.mo687layouto7g1Pn8(iM4617getBeforehoxUOeE, lVar);
    }
}
