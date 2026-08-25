package androidx.compose.ui.modifier;

import androidx.compose.material3.d;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class b {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10 */
    public static Object a(ModifierLocalModifierNode modifierLocalModifierNode, ModifierLocal modifierLocal) {
        NodeChain nodes;
        if (!modifierLocalModifierNode.getNode().getIsAttached()) {
            throw new IllegalArgumentException("ModifierLocal accessed from an unattached node");
        }
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(32);
        if (!modifierLocalModifierNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = modifierLocalModifierNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(modifierLocalModifierNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((d.d(layoutNodeRequireLayoutNode) & iM4843constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM4843constructorimpl) != 0) {
                        Modifier.Node nodePop = parent;
                        MutableVector mutableVector = null;
                        while (nodePop != 0) {
                            if (nodePop instanceof ModifierLocalModifierNode) {
                                ModifierLocalModifierNode modifierLocalModifierNode2 = (ModifierLocalModifierNode) nodePop;
                                if (modifierLocalModifierNode2.getProvidedValues().contains$ui_release(modifierLocal)) {
                                    return modifierLocalModifierNode2.getProvidedValues().get$ui_release(modifierLocal);
                                }
                            } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                int i10 = 0;
                                nodePop = nodePop;
                                while (delegate != null) {
                                    if ((delegate.getKindSet() & iM4843constructorimpl) != 0) {
                                        i10++;
                                        if (i10 == 1) {
                                            nodePop = delegate;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                mutableVector.add(nodePop);
                                                nodePop = 0;
                                            }
                                            mutableVector.add(delegate);
                                        }
                                    }
                                    delegate = delegate.getChild();
                                    nodePop = nodePop;
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
        return modifierLocal.getDefaultFactory$ui_release().invoke();
    }

    public static ModifierLocalMap b(ModifierLocalModifierNode modifierLocalModifierNode) {
        return EmptyMap.INSTANCE;
    }

    public static void c(ModifierLocalModifierNode modifierLocalModifierNode, ModifierLocal modifierLocal, Object obj) {
        if (modifierLocalModifierNode.getProvidedValues() == EmptyMap.INSTANCE) {
            throw new IllegalArgumentException("In order to provide locals you must override providedValues: ModifierLocalMap");
        }
        if (modifierLocalModifierNode.getProvidedValues().contains$ui_release(modifierLocal)) {
            modifierLocalModifierNode.getProvidedValues().mo4706set$ui_release(modifierLocal, obj);
            return;
        }
        throw new IllegalArgumentException(("Any provided key must be initially provided in the overridden providedValues: ModifierLocalMap property. Key " + modifierLocal + " was not found.").toString());
    }
}
