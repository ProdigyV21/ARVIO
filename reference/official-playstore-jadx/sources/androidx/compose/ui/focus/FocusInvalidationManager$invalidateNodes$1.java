package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class FocusInvalidationManager$invalidateNodes$1 extends r implements r7.a<t0> {
    final /* synthetic */ FocusInvalidationManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FocusInvalidationManager$invalidateNodes$1(FocusInvalidationManager focusInvalidationManager) {
        super(0);
        this.this$0 = focusInvalidationManager;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m3167invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m3167invoke() {
        int i10;
        int i11;
        FocusState focusState;
        int i12;
        int i13;
        Set set = this.this$0.focusPropertiesNodes;
        FocusInvalidationManager focusInvalidationManager = this.this$0;
        Iterator it = set.iterator();
        while (true) {
            int i14 = 1024;
            int i15 = 16;
            int i16 = 0;
            int i17 = 1;
            if (!it.hasNext()) {
                this.this$0.focusPropertiesNodes.clear();
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                Set<FocusEventModifierNode> set2 = this.this$0.focusEventNodes;
                FocusInvalidationManager focusInvalidationManager2 = this.this$0;
                for (FocusEventModifierNode focusEventModifierNode : set2) {
                    if (focusEventModifierNode.getNode().getIsAttached()) {
                        int iM4843constructorimpl = NodeKind.m4843constructorimpl(i14);
                        Modifier.Node node = focusEventModifierNode.getNode();
                        int i18 = i16;
                        int i19 = i17;
                        FocusTargetNode focusTargetNode = null;
                        MutableVector mutableVector = null;
                        while (node != null) {
                            if (node instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode2 = (FocusTargetNode) node;
                                if (focusTargetNode != null) {
                                    i18 = i17;
                                }
                                if (focusInvalidationManager2.focusTargetNodes.contains(focusTargetNode2)) {
                                    linkedHashSet.add(focusTargetNode2);
                                    i19 = i16;
                                }
                                focusTargetNode = focusTargetNode2;
                            } else if ((node.getKindSet() & iM4843constructorimpl) != 0 && (node instanceof DelegatingNode)) {
                                Modifier.Node delegate = ((DelegatingNode) node).getDelegate();
                                int i20 = i16;
                                while (delegate != null) {
                                    if ((delegate.getKindSet() & iM4843constructorimpl) != 0) {
                                        i20++;
                                        if (i20 == i17) {
                                            node = delegate;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[i15], i16);
                                            }
                                            if (node != null) {
                                                mutableVector.add(node);
                                                node = null;
                                            }
                                            mutableVector.add(delegate);
                                        }
                                    }
                                    delegate = delegate.getChild();
                                    i17 = 1;
                                }
                                int i21 = i17;
                                if (i20 == i21) {
                                    i17 = i21;
                                }
                            }
                            node = DelegatableNodeKt.pop(mutableVector);
                            i17 = 1;
                        }
                        if (!focusEventModifierNode.getNode().getIsAttached()) {
                            throw new IllegalStateException("visitChildren called on an unattached node");
                        }
                        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[i15], i16);
                        Modifier.Node child = focusEventModifierNode.getNode().getChild();
                        if (child == null) {
                            DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, focusEventModifierNode.getNode());
                        } else {
                            mutableVector2.add(child);
                        }
                        while (mutableVector2.isNotEmpty()) {
                            Modifier.Node nodePop = (Modifier.Node) androidx.compose.material3.d.h(mutableVector2, 1);
                            if ((nodePop.getAggregateChildKindSet() & iM4843constructorimpl) == 0) {
                                DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, nodePop);
                            } else {
                                while (nodePop != null) {
                                    if ((nodePop.getKindSet() & iM4843constructorimpl) != 0) {
                                        MutableVector mutableVector3 = null;
                                        while (nodePop != null) {
                                            if (nodePop instanceof FocusTargetNode) {
                                                FocusTargetNode focusTargetNode3 = (FocusTargetNode) nodePop;
                                                if (focusTargetNode != null) {
                                                    i18 = 1;
                                                }
                                                if (focusInvalidationManager2.focusTargetNodes.contains(focusTargetNode3)) {
                                                    linkedHashSet.add(focusTargetNode3);
                                                    i19 = i16;
                                                }
                                                i12 = i16;
                                                focusTargetNode = focusTargetNode3;
                                            } else if ((nodePop.getKindSet() & iM4843constructorimpl) == 0 || !(nodePop instanceof DelegatingNode)) {
                                                i12 = i16;
                                            } else {
                                                Modifier.Node delegate2 = ((DelegatingNode) nodePop).getDelegate();
                                                int i22 = i16;
                                                while (delegate2 != null) {
                                                    if ((delegate2.getKindSet() & iM4843constructorimpl) != 0) {
                                                        i22++;
                                                        if (i22 == 1) {
                                                            nodePop = delegate2;
                                                            i13 = 0;
                                                        } else {
                                                            if (mutableVector3 == null) {
                                                                Modifier.Node[] nodeArr = new Modifier.Node[i15];
                                                                i13 = 0;
                                                                mutableVector3 = new MutableVector(nodeArr, 0);
                                                            } else {
                                                                i13 = 0;
                                                            }
                                                            if (nodePop != null) {
                                                                mutableVector3.add(nodePop);
                                                                nodePop = null;
                                                            }
                                                            mutableVector3.add(delegate2);
                                                        }
                                                    } else {
                                                        i13 = i16;
                                                    }
                                                    delegate2 = delegate2.getChild();
                                                    i16 = i13;
                                                    i15 = 16;
                                                }
                                                i12 = i16;
                                                if (i22 != 1) {
                                                    nodePop = DelegatableNodeKt.pop(mutableVector3);
                                                }
                                                i16 = i12;
                                                i15 = 16;
                                            }
                                            nodePop = DelegatableNodeKt.pop(mutableVector3);
                                            i16 = i12;
                                            i15 = 16;
                                        }
                                    } else {
                                        nodePop = nodePop.getChild();
                                        i16 = i16;
                                        i15 = 16;
                                    }
                                }
                            }
                            i16 = i16;
                            i15 = 16;
                        }
                        i10 = i16;
                        i11 = 1;
                        if (i19 != 0) {
                            if (i18 != 0) {
                                focusState = FocusEventModifierNodeKt.getFocusState(focusEventModifierNode);
                            } else if (focusTargetNode == null || (focusState = focusTargetNode.getFocusState()) == null) {
                                focusState = FocusStateImpl.Inactive;
                            }
                            focusEventModifierNode.onFocusEvent(focusState);
                        }
                    } else {
                        focusEventModifierNode.onFocusEvent(FocusStateImpl.Inactive);
                        i10 = i16;
                        i11 = i17;
                    }
                    i17 = i11;
                    i14 = 1024;
                    i16 = i10;
                    i15 = 16;
                }
                this.this$0.focusEventNodes.clear();
                for (FocusTargetNode focusTargetNode4 : this.this$0.focusTargetNodes) {
                    if (focusTargetNode4.getIsAttached()) {
                        FocusStateImpl focusState2 = focusTargetNode4.getFocusState();
                        focusTargetNode4.invalidateFocus$ui_release();
                        if (focusState2 != focusTargetNode4.getFocusState() || linkedHashSet.contains(focusTargetNode4)) {
                            FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode4);
                        }
                    }
                }
                this.this$0.focusTargetNodes.clear();
                linkedHashSet.clear();
                if (!this.this$0.focusPropertiesNodes.isEmpty()) {
                    throw new IllegalStateException("Unprocessed FocusProperties nodes");
                }
                if (!this.this$0.focusEventNodes.isEmpty()) {
                    throw new IllegalStateException("Unprocessed FocusEvent nodes");
                }
                if (!this.this$0.focusTargetNodes.isEmpty()) {
                    throw new IllegalStateException("Unprocessed FocusTarget nodes");
                }
                return;
            }
            FocusPropertiesModifierNode focusPropertiesModifierNode = (FocusPropertiesModifierNode) it.next();
            if (focusPropertiesModifierNode.getNode().getIsAttached()) {
                int iM4843constructorimpl2 = NodeKind.m4843constructorimpl(1024);
                Modifier.Node node2 = focusPropertiesModifierNode.getNode();
                MutableVector mutableVector4 = null;
                while (node2 != null) {
                    if (node2 instanceof FocusTargetNode) {
                        focusInvalidationManager.focusTargetNodes.add((FocusTargetNode) node2);
                    } else if ((node2.getKindSet() & iM4843constructorimpl2) != 0 && (node2 instanceof DelegatingNode)) {
                        int i23 = 0;
                        for (Modifier.Node delegate3 = ((DelegatingNode) node2).getDelegate(); delegate3 != null; delegate3 = delegate3.getChild()) {
                            if ((delegate3.getKindSet() & iM4843constructorimpl2) != 0) {
                                i23++;
                                if (i23 == 1) {
                                    node2 = delegate3;
                                } else {
                                    if (mutableVector4 == null) {
                                        mutableVector4 = new MutableVector(new Modifier.Node[16], 0);
                                    }
                                    if (node2 != null) {
                                        mutableVector4.add(node2);
                                        node2 = null;
                                    }
                                    mutableVector4.add(delegate3);
                                }
                            }
                        }
                        if (i23 == 1) {
                        }
                    }
                    node2 = DelegatableNodeKt.pop(mutableVector4);
                }
                if (!focusPropertiesModifierNode.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitChildren called on an unattached node");
                }
                MutableVector mutableVector5 = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child2 = focusPropertiesModifierNode.getNode().getChild();
                if (child2 == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(mutableVector5, focusPropertiesModifierNode.getNode());
                } else {
                    mutableVector5.add(child2);
                }
                while (mutableVector5.isNotEmpty()) {
                    Modifier.Node nodePop2 = (Modifier.Node) androidx.compose.material3.d.h(mutableVector5, 1);
                    if ((nodePop2.getAggregateChildKindSet() & iM4843constructorimpl2) == 0) {
                        DelegatableNodeKt.addLayoutNodeChildren(mutableVector5, nodePop2);
                    } else {
                        while (true) {
                            if (nodePop2 == null) {
                                break;
                            }
                            if ((nodePop2.getKindSet() & iM4843constructorimpl2) != 0) {
                                MutableVector mutableVector6 = null;
                                while (nodePop2 != null) {
                                    if (nodePop2 instanceof FocusTargetNode) {
                                        focusInvalidationManager.focusTargetNodes.add((FocusTargetNode) nodePop2);
                                    } else if ((nodePop2.getKindSet() & iM4843constructorimpl2) != 0 && (nodePop2 instanceof DelegatingNode)) {
                                        int i24 = 0;
                                        for (Modifier.Node delegate4 = ((DelegatingNode) nodePop2).getDelegate(); delegate4 != null; delegate4 = delegate4.getChild()) {
                                            if ((delegate4.getKindSet() & iM4843constructorimpl2) != 0) {
                                                i24++;
                                                if (i24 == 1) {
                                                    nodePop2 = delegate4;
                                                } else {
                                                    if (mutableVector6 == null) {
                                                        mutableVector6 = new MutableVector(new Modifier.Node[16], 0);
                                                    }
                                                    if (nodePop2 != null) {
                                                        mutableVector6.add(nodePop2);
                                                        nodePop2 = null;
                                                    }
                                                    mutableVector6.add(delegate4);
                                                }
                                            }
                                        }
                                        if (i24 == 1) {
                                        }
                                    }
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector6);
                                }
                            } else {
                                nodePop2 = nodePop2.getChild();
                            }
                        }
                    }
                }
            }
        }
    }
}
