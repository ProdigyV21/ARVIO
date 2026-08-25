package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0019\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\r\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0013\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0010¢\u0006\u0004\b\u0011\u0010\u0012J!\u0010\u0019\u001a\u00028\u0000\"\b\b\u0000\u0010\u0015*\u00020\u00142\u0006\u0010\u0016\u001a\u00028\u0000H\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0014H\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ!\u0010\u001e\u001a\u00028\u0000\"\b\b\u0000\u0010\u0015*\u00020\u00142\u0006\u0010\u0016\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u001e\u0010\u0018J\u0017\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0014H\u0004¢\u0006\u0004\b\u001f\u0010\u001cJ$\u0010$\u001a\u00020\u00072\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070 H\u0080\b¢\u0006\u0004\b\"\u0010#J\u000f\u0010&\u001a\u00020\u0007H\u0010¢\u0006\u0004\b%\u0010\u0003J\u000f\u0010(\u001a\u00020\u0007H\u0010¢\u0006\u0004\b'\u0010\u0003J\u000f\u0010*\u001a\u00020\u0007H\u0010¢\u0006\u0004\b)\u0010\u0003J\u000f\u0010,\u001a\u00020\u0007H\u0010¢\u0006\u0004\b+\u0010\u0003J\u000f\u0010.\u001a\u00020\u0007H\u0010¢\u0006\u0004\b-\u0010\u0003R \u0010/\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\u0012\n\u0004\b/\u00100\u0012\u0004\b3\u0010\u0003\u001a\u0004\b1\u00102R$\u0010\u001e\u001a\u0004\u0018\u00010\u00018\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u001e\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108¨\u00069"}, d2 = {"Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/Modifier$Node;", "<init>", "()V", "", "delegateKindSet", "delegateNode", "Lx6/t0;", "validateDelegateKindSet", "(ILandroidx/compose/ui/Modifier$Node;)V", "newKindSet", "", "recalculateOwner", "updateNodeKindSet", "(IZ)V", "Landroidx/compose/ui/node/NodeCoordinator;", "coordinator", "updateCoordinator$ui_release", "(Landroidx/compose/ui/node/NodeCoordinator;)V", "updateCoordinator", "Landroidx/compose/ui/node/DelegatableNode;", "T", "delegatableNode", "delegateUnprotected$ui_release", "(Landroidx/compose/ui/node/DelegatableNode;)Landroidx/compose/ui/node/DelegatableNode;", "delegateUnprotected", "instance", "undelegateUnprotected$ui_release", "(Landroidx/compose/ui/node/DelegatableNode;)V", "undelegateUnprotected", "delegate", "undelegate", "Lkotlin/Function1;", "block", "forEachImmediateDelegate$ui_release", "(Lr7/l;)V", "forEachImmediateDelegate", "markAsAttached$ui_release", "markAsAttached", "runAttachLifecycle$ui_release", "runAttachLifecycle", "runDetachLifecycle$ui_release", "runDetachLifecycle", "markAsDetached$ui_release", "markAsDetached", "reset$ui_release", "reset", "selfKindSet", "I", "getSelfKindSet$ui_release", "()I", "getSelfKindSet$ui_release$annotations", "Landroidx/compose/ui/Modifier$Node;", "getDelegate$ui_release", "()Landroidx/compose/ui/Modifier$Node;", "setDelegate$ui_release", "(Landroidx/compose/ui/Modifier$Node;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class DelegatingNode extends Modifier.Node {
    public static final int $stable = 8;
    private Modifier.Node delegate;
    private final int selfKindSet = NodeKindKt.calculateNodeKindSetFrom(this);

    public static /* synthetic */ void getSelfKindSet$ui_release$annotations() {
    }

    private final void updateNodeKindSet(int newKindSet, boolean recalculateOwner) {
        Modifier.Node child;
        int kindSet = getKindSet();
        setKindSet$ui_release(newKindSet);
        if (kindSet != newKindSet) {
            if (DelegatableNodeKt.isDelegationRoot(this)) {
                setAggregateChildKindSet$ui_release(newKindSet);
            }
            if (getIsAttached()) {
                Modifier.Node node = getNode();
                Modifier.Node parent = this;
                while (parent != null) {
                    newKindSet |= parent.getKindSet();
                    parent.setKindSet$ui_release(newKindSet);
                    if (parent == node) {
                        break;
                    } else {
                        parent = parent.getParent();
                    }
                }
                if (recalculateOwner && parent == node) {
                    newKindSet = NodeKindKt.calculateNodeKindSetFromIncludingDelegates(node);
                    node.setKindSet$ui_release(newKindSet);
                }
                int aggregateChildKindSet = newKindSet | ((parent == null || (child = parent.getChild()) == null) ? 0 : child.getAggregateChildKindSet());
                while (parent != null) {
                    aggregateChildKindSet |= parent.getKindSet();
                    parent.setAggregateChildKindSet$ui_release(aggregateChildKindSet);
                    parent = parent.getParent();
                }
            }
        }
    }

    private final void validateDelegateKindSet(int delegateKindSet, Modifier.Node delegateNode) {
        int kindSet = getKindSet();
        if ((delegateKindSet & NodeKind.m4843constructorimpl(2)) == 0 || (NodeKind.m4843constructorimpl(2) & kindSet) == 0 || (this instanceof LayoutModifierNode)) {
            return;
        }
        throw new IllegalStateException(("Delegating to multiple LayoutModifierNodes without the delegating node implementing LayoutModifierNode itself is not allowed.\nDelegating Node: " + this + "\nDelegate Node: " + delegateNode).toString());
    }

    public final <T extends DelegatableNode> T delegate(T delegatableNode) {
        Modifier.Node node = delegatableNode.getNode();
        if (node != delegatableNode) {
            Modifier.Node node2 = delegatableNode instanceof Modifier.Node ? (Modifier.Node) delegatableNode : null;
            Modifier.Node parent = node2 != null ? node2.getParent() : null;
            if (node == getNode() && p.a(parent, this)) {
                return delegatableNode;
            }
            throw new IllegalStateException("Cannot delegate to an already delegated node");
        }
        if (node.getIsAttached()) {
            throw new IllegalStateException("Cannot delegate to an already attached node");
        }
        node.setAsDelegateTo$ui_release(getNode());
        int kindSet = getKindSet();
        int iCalculateNodeKindSetFromIncludingDelegates = NodeKindKt.calculateNodeKindSetFromIncludingDelegates(node);
        node.setKindSet$ui_release(iCalculateNodeKindSetFromIncludingDelegates);
        validateDelegateKindSet(iCalculateNodeKindSetFromIncludingDelegates, node);
        node.setChild$ui_release(this.delegate);
        this.delegate = node;
        node.setParent$ui_release(this);
        updateNodeKindSet(getKindSet() | iCalculateNodeKindSetFromIncludingDelegates, false);
        if (getIsAttached()) {
            if ((iCalculateNodeKindSetFromIncludingDelegates & NodeKind.m4843constructorimpl(2)) == 0 || (kindSet & NodeKind.m4843constructorimpl(2)) != 0) {
                updateCoordinator$ui_release(getCoordinator());
            } else {
                NodeChain nodes = DelegatableNodeKt.requireLayoutNode(this).getNodes();
                getNode().updateCoordinator$ui_release(null);
                nodes.syncCoordinators();
            }
            node.markAsAttached$ui_release();
            node.runAttachLifecycle$ui_release();
            NodeKindKt.autoInvalidateInsertedNode(node);
        }
        return delegatableNode;
    }

    public final <T extends DelegatableNode> T delegateUnprotected$ui_release(T delegatableNode) {
        return (T) delegate(delegatableNode);
    }

    public final void forEachImmediateDelegate$ui_release(l<? super Modifier.Node, t0> block) {
        for (Modifier.Node delegate = getDelegate(); delegate != null; delegate = delegate.getChild()) {
            block.invoke(delegate);
        }
    }

    /* JADX INFO: renamed from: getDelegate$ui_release, reason: from getter */
    public final Modifier.Node getDelegate() {
        return this.delegate;
    }

    /* JADX INFO: renamed from: getSelfKindSet$ui_release, reason: from getter */
    public final int getSelfKindSet() {
        return this.selfKindSet;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void markAsAttached$ui_release() {
        super.markAsAttached$ui_release();
        for (Modifier.Node delegate = getDelegate(); delegate != null; delegate = delegate.getChild()) {
            delegate.updateCoordinator$ui_release(getCoordinator());
            if (!delegate.getIsAttached()) {
                delegate.markAsAttached$ui_release();
            }
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void markAsDetached$ui_release() {
        for (Modifier.Node delegate = getDelegate(); delegate != null; delegate = delegate.getChild()) {
            delegate.markAsDetached$ui_release();
        }
        super.markAsDetached$ui_release();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void reset$ui_release() {
        super.reset$ui_release();
        for (Modifier.Node delegate = getDelegate(); delegate != null; delegate = delegate.getChild()) {
            delegate.reset$ui_release();
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void runAttachLifecycle$ui_release() {
        for (Modifier.Node delegate = getDelegate(); delegate != null; delegate = delegate.getChild()) {
            delegate.runAttachLifecycle$ui_release();
        }
        super.runAttachLifecycle$ui_release();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void runDetachLifecycle$ui_release() {
        super.runDetachLifecycle$ui_release();
        for (Modifier.Node delegate = getDelegate(); delegate != null; delegate = delegate.getChild()) {
            delegate.runDetachLifecycle$ui_release();
        }
    }

    public final void setDelegate$ui_release(Modifier.Node node) {
        this.delegate = node;
    }

    public final void undelegate(DelegatableNode instance) {
        Modifier.Node node = null;
        for (Modifier.Node child = this.delegate; child != null; child = child.getChild()) {
            if (child == instance) {
                if (child.getIsAttached()) {
                    NodeKindKt.autoInvalidateRemovedNode(child);
                    child.runDetachLifecycle$ui_release();
                    child.markAsDetached$ui_release();
                }
                child.setAsDelegateTo$ui_release(child);
                child.setAggregateChildKindSet$ui_release(0);
                if (node == null) {
                    this.delegate = child.getChild();
                } else {
                    node.setChild$ui_release(child.getChild());
                }
                child.setChild$ui_release(null);
                child.setParent$ui_release(null);
                int kindSet = getKindSet();
                int iCalculateNodeKindSetFromIncludingDelegates = NodeKindKt.calculateNodeKindSetFromIncludingDelegates(this);
                updateNodeKindSet(iCalculateNodeKindSetFromIncludingDelegates, true);
                if (getIsAttached() && (kindSet & NodeKind.m4843constructorimpl(2)) != 0 && (NodeKind.m4843constructorimpl(2) & iCalculateNodeKindSetFromIncludingDelegates) == 0) {
                    NodeChain nodes = DelegatableNodeKt.requireLayoutNode(this).getNodes();
                    getNode().updateCoordinator$ui_release(null);
                    nodes.syncCoordinators();
                    return;
                }
                return;
            }
            node = child;
        }
        throw new IllegalStateException(("Could not find delegate: " + instance).toString());
    }

    public final void undelegateUnprotected$ui_release(DelegatableNode instance) {
        undelegate(instance);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void updateCoordinator$ui_release(NodeCoordinator coordinator) {
        super.updateCoordinator$ui_release(coordinator);
        for (Modifier.Node delegate = getDelegate(); delegate != null; delegate = delegate.getChild()) {
            delegate.updateCoordinator$ui_release(coordinator);
        }
    }
}
