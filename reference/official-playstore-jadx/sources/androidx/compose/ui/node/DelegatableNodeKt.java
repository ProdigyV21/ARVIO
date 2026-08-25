package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u001a:\u0010\t\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0080\b¢\u0006\u0004\b\t\u0010\n\u001a\u001d\u0010\u000b\u001a\u0004\u0018\u00010\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a0\u0010\r\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0080\b¢\u0006\u0004\b\r\u0010\u000e\u001a!\u0010\u0011\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00060\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0011\u0010\u0012\u001a0\u0010\u0013\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0080\b¢\u0006\u0004\b\u0013\u0010\u000e\u001a0\u0010\u0014\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\u0005H\u0080\b¢\u0006\u0004\b\u0014\u0010\u000e\u001a0\u0010\u0015\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0080\b¢\u0006\u0004\b\u0015\u0010\u000e\u001a0\u0010\u0016\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0080\b¢\u0006\u0004\b\u0016\u0010\u000e\u001aA\u0010\u0015\u001a\u00020\u0007\"\u0006\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0005H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u000e\u001aA\u0010\u0016\u001a\u00020\u0007\"\u0006\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0005H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000e\u001aK\u0010\t\u001a\u00020\u0007\"\u0006\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0005H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\n\u001aM\u0010 \u001a\u00020\u0007\"\u0006\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u00182\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0005H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a5\u0010$\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010!\"\u0006\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0080\bø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a3\u0010\u000b\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010\u0017\u0018\u0001*\u00020%*\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0080\bø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001aA\u0010\r\u001a\u00020\u0007\"\u0006\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0005H\u0080\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u000e\u001aA\u0010\u0013\u001a\u00020\u0007\"\u0006\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0005H\u0080\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u000e\u001aA\u0010+\u001a\u00020\u0007\"\u0006\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0005H\u0080\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u000e\u001aA\u0010\u0014\u001a\u00020\u0007\"\u0006\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0005H\u0080\bø\u0001\u0000¢\u0006\u0004\b,\u0010\u000e\u001a\"\u0010/\u001a\u00020\u0003*\u00020\u00002\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u0018H\u0000ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001a\"\u00104\u001a\u000201*\u00020\u00002\n\u00100\u001a\u0006\u0012\u0002\b\u00030\u0018H\u0000ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001a\u0013\u00106\u001a\u000205*\u00020\u0000H\u0000¢\u0006\u0004\b6\u00107\u001a\u0013\u00109\u001a\u000208*\u00020\u0000H\u0000¢\u0006\u0004\b9\u0010:\u001a\u0011\u0010<\u001a\u00020;*\u00020\u0000¢\u0006\u0004\b<\u0010=\u001a\u0011\u0010?\u001a\u00020>*\u00020\u0000¢\u0006\u0004\b?\u0010@\u001a\u0011\u0010A\u001a\u00020\u0007*\u00020\u0000¢\u0006\u0004\bA\u0010B\u001a\u0015\u0010D\u001a\u0004\u0018\u00010C*\u00020\u0006H\u0000¢\u0006\u0004\bD\u0010E\u001aA\u0010H\u001a\u00020\u0007\"\u0006\b\u0000\u0010\u0017\u0018\u0001*\u00020\u00062\f\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0005H\u0080\bø\u0001\u0000¢\u0006\u0004\bF\u0010G\u001a\u001d\u0010I\u001a\u0004\u0018\u00010\u0006*\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000fH\u0002¢\u0006\u0004\bI\u0010J\"\u0018\u0010K\u001a\u00020\u0003*\u00020\u00008@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bK\u0010L\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006M"}, d2 = {"Landroidx/compose/ui/node/DelegatableNode;", "", "mask", "", "includeSelf", "Lkotlin/Function1;", "Landroidx/compose/ui/Modifier$Node;", "Lx6/t0;", "block", "visitAncestors", "(Landroidx/compose/ui/node/DelegatableNode;IZLr7/l;)V", "nearestAncestor", "(Landroidx/compose/ui/node/DelegatableNode;I)Landroidx/compose/ui/Modifier$Node;", "visitSubtree", "(Landroidx/compose/ui/node/DelegatableNode;ILr7/l;)V", "Landroidx/compose/runtime/collection/MutableVector;", "node", "addLayoutNodeChildren", "(Landroidx/compose/runtime/collection/MutableVector;Landroidx/compose/ui/Modifier$Node;)V", "visitChildren", "visitSubtreeIf", "visitLocalDescendants", "visitLocalAncestors", "T", "Landroidx/compose/ui/node/NodeKind;", LinkHeader.Parameters.Type, "visitLocalDescendants-6rFNWt0", "visitLocalAncestors-6rFNWt0", "visitAncestors-Y-YKmho", "untilType", "visitSelfAndAncestors-5BbP62I", "(Landroidx/compose/ui/node/DelegatableNode;IILr7/l;)V", "visitSelfAndAncestors", "", "ancestors-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Ljava/util/List;", "ancestors", "", "nearestAncestor-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Ljava/lang/Object;", "visitSubtree-6rFNWt0", "visitChildren-6rFNWt0", "visitSelfAndChildren-6rFNWt0", "visitSelfAndChildren", "visitSubtreeIf-6rFNWt0", "has-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Z", "has", "kind", "Landroidx/compose/ui/node/NodeCoordinator;", "requireCoordinator-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Landroidx/compose/ui/node/NodeCoordinator;", "requireCoordinator", "Landroidx/compose/ui/node/LayoutNode;", "requireLayoutNode", "(Landroidx/compose/ui/node/DelegatableNode;)Landroidx/compose/ui/node/LayoutNode;", "Landroidx/compose/ui/node/Owner;", "requireOwner", "(Landroidx/compose/ui/node/DelegatableNode;)Landroidx/compose/ui/node/Owner;", "Landroidx/compose/ui/unit/Density;", "requireDensity", "(Landroidx/compose/ui/node/DelegatableNode;)Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/LayoutDirection;", "requireLayoutDirection", "(Landroidx/compose/ui/node/DelegatableNode;)Landroidx/compose/ui/unit/LayoutDirection;", "invalidateSubtree", "(Landroidx/compose/ui/node/DelegatableNode;)V", "Landroidx/compose/ui/node/LayoutModifierNode;", "asLayoutModifierNode", "(Landroidx/compose/ui/Modifier$Node;)Landroidx/compose/ui/node/LayoutModifierNode;", "dispatchForKind-6rFNWt0", "(Landroidx/compose/ui/Modifier$Node;ILr7/l;)V", "dispatchForKind", "pop", "(Landroidx/compose/runtime/collection/MutableVector;)Landroidx/compose/ui/Modifier$Node;", "isDelegationRoot", "(Landroidx/compose/ui/node/DelegatableNode;)Z", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DelegatableNodeKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void addLayoutNodeChildren(MutableVector<Modifier.Node> mutableVector, Modifier.Node node) {
        MutableVector<LayoutNode> mutableVector2 = requireLayoutNode(node).get_children$ui_release();
        int size = mutableVector2.getSize();
        if (size > 0) {
            int i10 = size - 1;
            LayoutNode[] content = mutableVector2.getContent();
            do {
                mutableVector.add(content[i10].getNodes().getHead());
                i10--;
            } while (i10 >= 0);
        }
    }

    /* JADX INFO: renamed from: ancestors-64DMado, reason: not valid java name */
    public static final <T> List<T> m4722ancestors64DMado(DelegatableNode delegatableNode, int i10) {
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent$ui_release = delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & i10) != 0) {
                while (parent$ui_release != null) {
                    if ((parent$ui_release.getKindSet() & i10) != 0) {
                        p.h();
                        throw null;
                    }
                    parent$ui_release = parent$ui_release.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent$ui_release = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final LayoutModifierNode asLayoutModifierNode(Modifier.Node node) {
        if ((NodeKind.m4843constructorimpl(2) & node.getKindSet()) != 0) {
            if (node instanceof LayoutModifierNode) {
                return (LayoutModifierNode) node;
            }
            if (node instanceof DelegatingNode) {
                Modifier.Node delegate$ui_release = ((DelegatingNode) node).getDelegate();
                while (delegate$ui_release != 0) {
                    if (delegate$ui_release instanceof LayoutModifierNode) {
                        return (LayoutModifierNode) delegate$ui_release;
                    }
                    delegate$ui_release = (!(delegate$ui_release instanceof DelegatingNode) || (NodeKind.m4843constructorimpl(2) & delegate$ui_release.getKindSet()) == 0) ? delegate$ui_release.getChild() : ((DelegatingNode) delegate$ui_release).getDelegate();
                }
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: dispatchForKind-6rFNWt0, reason: not valid java name */
    public static final <T> void m4723dispatchForKind6rFNWt0(Modifier.Node node, int i10, l<? super T, t0> lVar) {
        if (node == null) {
            return;
        }
        p.h();
        throw null;
    }

    /* JADX INFO: renamed from: has-64DMado, reason: not valid java name */
    public static final boolean m4724has64DMado(DelegatableNode delegatableNode, int i10) {
        return (delegatableNode.getNode().getAggregateChildKindSet() & i10) != 0;
    }

    public static final void invalidateSubtree(DelegatableNode delegatableNode) {
        if (delegatableNode.getNode().getIsAttached()) {
            LayoutNode.invalidateSubtree$default(requireLayoutNode(delegatableNode), false, 1, null);
        }
    }

    public static final boolean isDelegationRoot(DelegatableNode delegatableNode) {
        return delegatableNode.getNode() == delegatableNode;
    }

    public static final Modifier.Node nearestAncestor(DelegatableNode delegatableNode, int i10) {
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("nearestAncestor called on an unattached node");
        }
        Modifier.Node parent$ui_release = delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & i10) != 0) {
                while (parent$ui_release != null) {
                    if ((parent$ui_release.getKindSet() & i10) != 0) {
                        return parent$ui_release;
                    }
                    parent$ui_release = parent$ui_release.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent$ui_release = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return null;
    }

    /* JADX INFO: renamed from: nearestAncestor-64DMado, reason: not valid java name */
    public static final <T> T m4725nearestAncestor64DMado(DelegatableNode delegatableNode, int i10) {
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent$ui_release = delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & i10) != 0) {
                while (parent$ui_release != null) {
                    if ((parent$ui_release.getKindSet() & i10) != 0) {
                        p.h();
                        throw null;
                    }
                    parent$ui_release = parent$ui_release.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent$ui_release = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier.Node pop(MutableVector<Modifier.Node> mutableVector) {
        if (mutableVector == null || mutableVector.isEmpty()) {
            return null;
        }
        return (Modifier.Node) androidx.compose.material3.d.h(mutableVector, 1);
    }

    /* JADX INFO: renamed from: requireCoordinator-64DMado, reason: not valid java name */
    public static final NodeCoordinator m4726requireCoordinator64DMado(DelegatableNode delegatableNode, int i10) {
        NodeCoordinator coordinator$ui_release = delegatableNode.getNode().getCoordinator();
        return (coordinator$ui_release.getTail() == delegatableNode && NodeKindKt.m4852getIncludeSelfInTraversalH91voCI(i10)) ? coordinator$ui_release.getWrapped() : coordinator$ui_release;
    }

    public static final Density requireDensity(DelegatableNode delegatableNode) {
        return requireLayoutNode(delegatableNode).getDensity();
    }

    public static final LayoutDirection requireLayoutDirection(DelegatableNode delegatableNode) {
        return requireLayoutNode(delegatableNode).getLayoutDirection();
    }

    public static final LayoutNode requireLayoutNode(DelegatableNode delegatableNode) {
        NodeCoordinator coordinator$ui_release = delegatableNode.getNode().getCoordinator();
        if (coordinator$ui_release != null) {
            return coordinator$ui_release.getLayoutNode();
        }
        throw new IllegalStateException("Cannot obtain node coordinator. Is the Modifier.Node attached?");
    }

    public static final Owner requireOwner(DelegatableNode delegatableNode) {
        Owner owner = requireLayoutNode(delegatableNode).getOwner();
        if (owner != null) {
            return owner;
        }
        throw new IllegalStateException("This node does not have an owner.");
    }

    public static final void visitAncestors(DelegatableNode delegatableNode, int i10, boolean z, l<? super Modifier.Node, t0> lVar) {
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = z ? delegatableNode.getNode() : delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & i10) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & i10) != 0) {
                        lVar.invoke(node);
                    }
                    node = node.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    public static /* synthetic */ void visitAncestors$default(DelegatableNode delegatableNode, int i10, boolean z, l lVar, int i11, Object obj) {
        NodeChain nodes;
        if ((i11 & 2) != 0) {
            z = false;
        }
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = z ? delegatableNode.getNode() : delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & i10) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & i10) != 0) {
                        lVar.invoke(node);
                    }
                    node = node.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    /* JADX INFO: renamed from: visitAncestors-Y-YKmho, reason: not valid java name */
    public static final <T> void m4727visitAncestorsYYKmho(DelegatableNode delegatableNode, int i10, boolean z, l<? super T, t0> lVar) {
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = z ? delegatableNode.getNode() : delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & i10) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & i10) != 0) {
                        p.h();
                        throw null;
                    }
                    node = node.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    /* JADX INFO: renamed from: visitAncestors-Y-YKmho$default, reason: not valid java name */
    public static void m4728visitAncestorsYYKmho$default(DelegatableNode delegatableNode, int i10, boolean z, l lVar, int i11, Object obj) {
        NodeChain nodes;
        if ((i11 & 2) != 0) {
            z = false;
        }
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = z ? delegatableNode.getNode() : delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & i10) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & i10) != 0) {
                        p.h();
                        throw null;
                    }
                    node = node.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    public static final void visitChildren(DelegatableNode delegatableNode, int i10, l<? super Modifier.Node, t0> lVar) {
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$ui_release = delegatableNode.getNode().getChild();
        if (child$ui_release == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode());
        } else {
            mutableVector.add(child$ui_release);
        }
        while (mutableVector.isNotEmpty()) {
            Modifier.Node child$ui_release2 = (Modifier.Node) androidx.compose.material3.d.h(mutableVector, 1);
            if ((child$ui_release2.getAggregateChildKindSet() & i10) == 0) {
                addLayoutNodeChildren(mutableVector, child$ui_release2);
            } else {
                while (true) {
                    if (child$ui_release2 == null) {
                        break;
                    }
                    if ((child$ui_release2.getKindSet() & i10) != 0) {
                        lVar.invoke(child$ui_release2);
                        break;
                    }
                    child$ui_release2 = child$ui_release2.getChild();
                }
            }
        }
    }

    /* JADX INFO: renamed from: visitChildren-6rFNWt0, reason: not valid java name */
    public static final <T> void m4729visitChildren6rFNWt0(DelegatableNode delegatableNode, int i10, l<? super T, t0> lVar) {
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$ui_release = delegatableNode.getNode().getChild();
        if (child$ui_release == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode());
        } else {
            mutableVector.add(child$ui_release);
        }
        while (mutableVector.isNotEmpty()) {
            Modifier.Node child$ui_release2 = (Modifier.Node) androidx.compose.material3.d.h(mutableVector, 1);
            if ((child$ui_release2.getAggregateChildKindSet() & i10) == 0) {
                addLayoutNodeChildren(mutableVector, child$ui_release2);
            } else {
                while (child$ui_release2 != null) {
                    if ((child$ui_release2.getKindSet() & i10) != 0) {
                        p.h();
                        throw null;
                    }
                    child$ui_release2 = child$ui_release2.getChild();
                }
            }
        }
    }

    public static final void visitLocalAncestors(DelegatableNode delegatableNode, int i10, l<? super Modifier.Node, t0> lVar) {
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitLocalAncestors called on an unattached node");
        }
        for (Modifier.Node parent$ui_release = delegatableNode.getNode().getParent(); parent$ui_release != null; parent$ui_release = parent$ui_release.getParent()) {
            if ((parent$ui_release.getKindSet() & i10) != 0) {
                lVar.invoke(parent$ui_release);
            }
        }
    }

    /* JADX INFO: renamed from: visitLocalAncestors-6rFNWt0, reason: not valid java name */
    public static final <T> void m4730visitLocalAncestors6rFNWt0(DelegatableNode delegatableNode, int i10, l<? super T, t0> lVar) {
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitLocalAncestors called on an unattached node");
        }
        for (Modifier.Node parent$ui_release = delegatableNode.getNode().getParent(); parent$ui_release != null; parent$ui_release = parent$ui_release.getParent()) {
            if ((parent$ui_release.getKindSet() & i10) != 0) {
                p.h();
                throw null;
            }
        }
    }

    public static final void visitLocalDescendants(DelegatableNode delegatableNode, int i10, l<? super Modifier.Node, t0> lVar) {
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node node = delegatableNode.getNode();
        if ((node.getAggregateChildKindSet() & i10) == 0) {
            return;
        }
        for (Modifier.Node child$ui_release = node.getChild(); child$ui_release != null; child$ui_release = child$ui_release.getChild()) {
            if ((child$ui_release.getKindSet() & i10) != 0) {
                lVar.invoke(child$ui_release);
            }
        }
    }

    /* JADX INFO: renamed from: visitLocalDescendants-6rFNWt0, reason: not valid java name */
    public static final <T> void m4731visitLocalDescendants6rFNWt0(DelegatableNode delegatableNode, int i10, l<? super T, t0> lVar) {
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node node = delegatableNode.getNode();
        if ((node.getAggregateChildKindSet() & i10) != 0) {
            for (Modifier.Node child$ui_release = node.getChild(); child$ui_release != null; child$ui_release = child$ui_release.getChild()) {
                if ((child$ui_release.getKindSet() & i10) != 0) {
                    p.h();
                    throw null;
                }
            }
        }
    }

    /* JADX INFO: renamed from: visitSelfAndAncestors-5BbP62I, reason: not valid java name */
    public static final <T> void m4732visitSelfAndAncestors5BbP62I(DelegatableNode delegatableNode, int i10, int i11, l<? super T, t0> lVar) {
        NodeChain nodes;
        Modifier.Node node = delegatableNode.getNode();
        int i12 = i10 | i11;
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node2 = delegatableNode.getNode();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & i12) != 0) {
                while (node2 != null) {
                    if ((node2.getKindSet() & i12) != 0) {
                        if (node2 != node && (node2.getKindSet() & i11) != 0) {
                            return;
                        }
                        if ((node2.getKindSet() & i10) != 0) {
                            p.h();
                            throw null;
                        }
                    }
                    node2 = node2.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            node2 = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    /* JADX INFO: renamed from: visitSelfAndChildren-6rFNWt0, reason: not valid java name */
    public static final <T> void m4733visitSelfAndChildren6rFNWt0(DelegatableNode delegatableNode, int i10, l<? super T, t0> lVar) {
        if (delegatableNode.getNode() != null) {
            p.h();
            throw null;
        }
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$ui_release = delegatableNode.getNode().getChild();
        if (child$ui_release == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode());
        } else {
            mutableVector.add(child$ui_release);
        }
        while (mutableVector.isNotEmpty()) {
            Modifier.Node child$ui_release2 = (Modifier.Node) androidx.compose.material3.d.h(mutableVector, 1);
            if ((child$ui_release2.getAggregateChildKindSet() & i10) == 0) {
                addLayoutNodeChildren(mutableVector, child$ui_release2);
            } else {
                while (child$ui_release2 != null) {
                    if ((child$ui_release2.getKindSet() & i10) != 0) {
                        p.h();
                        throw null;
                    }
                    child$ui_release2 = child$ui_release2.getChild();
                }
            }
        }
    }

    public static final void visitSubtree(DelegatableNode delegatableNode, int i10, l<? super Modifier.Node, t0> lVar) {
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitSubtree called on an unattached node");
        }
        Modifier.Node child$ui_release = delegatableNode.getNode().getChild();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        NestedVectorStack nestedVectorStack = new NestedVectorStack();
        while (layoutNodeRequireLayoutNode != null) {
            if (child$ui_release == null) {
                child$ui_release = layoutNodeRequireLayoutNode.getNodes().getHead();
            }
            if ((child$ui_release.getAggregateChildKindSet() & i10) != 0) {
                while (child$ui_release != null) {
                    if ((child$ui_release.getKindSet() & i10) != 0) {
                        lVar.invoke(child$ui_release);
                    }
                    child$ui_release = child$ui_release.getChild();
                }
            }
            nestedVectorStack.push(layoutNodeRequireLayoutNode.get_children$ui_release());
            child$ui_release = null;
            layoutNodeRequireLayoutNode = nestedVectorStack.isNotEmpty() ? (LayoutNode) nestedVectorStack.pop() : null;
        }
    }

    /* JADX INFO: renamed from: visitSubtree-6rFNWt0, reason: not valid java name */
    public static final <T> void m4734visitSubtree6rFNWt0(DelegatableNode delegatableNode, int i10, l<? super T, t0> lVar) {
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitSubtree called on an unattached node");
        }
        Modifier.Node child$ui_release = delegatableNode.getNode().getChild();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        NestedVectorStack nestedVectorStack = new NestedVectorStack();
        while (layoutNodeRequireLayoutNode != null) {
            if (child$ui_release == null) {
                child$ui_release = layoutNodeRequireLayoutNode.getNodes().getHead();
            }
            if ((child$ui_release.getAggregateChildKindSet() & i10) != 0) {
                while (child$ui_release != null) {
                    if ((child$ui_release.getKindSet() & i10) != 0) {
                        p.h();
                        throw null;
                    }
                    child$ui_release = child$ui_release.getChild();
                }
            }
            nestedVectorStack.push(layoutNodeRequireLayoutNode.get_children$ui_release());
            layoutNodeRequireLayoutNode = nestedVectorStack.isNotEmpty() ? (LayoutNode) nestedVectorStack.pop() : null;
            child$ui_release = null;
        }
    }

    public static final void visitSubtreeIf(DelegatableNode delegatableNode, int i10, l<? super Modifier.Node, Boolean> lVar) {
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$ui_release = delegatableNode.getNode().getChild();
        if (child$ui_release == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode());
        } else {
            mutableVector.add(child$ui_release);
        }
        while (mutableVector.isNotEmpty()) {
            Modifier.Node node = (Modifier.Node) androidx.compose.material3.d.h(mutableVector, 1);
            if ((node.getAggregateChildKindSet() & i10) != 0) {
                for (Modifier.Node child$ui_release2 = node; child$ui_release2 != null; child$ui_release2 = child$ui_release2.getChild()) {
                    if ((child$ui_release2.getKindSet() & i10) == 0 || ((Boolean) lVar.invoke(child$ui_release2)).booleanValue()) {
                    }
                }
            }
            addLayoutNodeChildren(mutableVector, node);
        }
    }

    /* JADX INFO: renamed from: visitSubtreeIf-6rFNWt0, reason: not valid java name */
    public static final <T> void m4735visitSubtreeIf6rFNWt0(DelegatableNode delegatableNode, int i10, l<? super T, Boolean> lVar) {
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$ui_release = delegatableNode.getNode().getChild();
        if (child$ui_release == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode());
        } else {
            mutableVector.add(child$ui_release);
        }
        while (mutableVector.isNotEmpty()) {
            Modifier.Node node = (Modifier.Node) androidx.compose.material3.d.h(mutableVector, 1);
            if ((node.getAggregateChildKindSet() & i10) != 0) {
                for (Modifier.Node child$ui_release2 = node; child$ui_release2 != null; child$ui_release2 = child$ui_release2.getChild()) {
                    if ((child$ui_release2.getKindSet() & i10) != 0) {
                        p.h();
                        throw null;
                    }
                }
            }
            addLayoutNodeChildren(mutableVector, node);
        }
    }
}
