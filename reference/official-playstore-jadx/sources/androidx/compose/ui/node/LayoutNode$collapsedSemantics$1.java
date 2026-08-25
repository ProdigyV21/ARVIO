package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import kotlin.Metadata;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class LayoutNode$collapsedSemantics$1 extends r implements r7.a<t0> {
    final /* synthetic */ k0 $config;
    final /* synthetic */ LayoutNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LayoutNode$collapsedSemantics$1(LayoutNode layoutNode, k0 k0Var) {
        super(0);
        this.this$0 = layoutNode;
        this.$config = k0Var;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m4763invoke();
        return t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m4763invoke() {
        NodeChain nodes = this.this$0.getNodes();
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(8);
        k0 k0Var = this.$config;
        if ((nodes.getAggregateChildKindSet() & iM4843constructorimpl) != 0) {
            for (Modifier.Node tail = nodes.getTail(); tail != null; tail = tail.getParent()) {
                if ((tail.getKindSet() & iM4843constructorimpl) != 0) {
                    Modifier.Node nodePop = tail;
                    MutableVector mutableVector = null;
                    while (nodePop != 0) {
                        if (nodePop instanceof SemanticsModifierNode) {
                            SemanticsModifierNode semanticsModifierNode = (SemanticsModifierNode) nodePop;
                            if (semanticsModifierNode.getIsClearingSemantics()) {
                                SemanticsConfiguration semanticsConfiguration = new SemanticsConfiguration();
                                k0Var.f19746i = semanticsConfiguration;
                                semanticsConfiguration.setClearingSemantics(true);
                            }
                            if (semanticsModifierNode.getShouldMergeDescendantSemantics()) {
                                ((SemanticsConfiguration) k0Var.f19746i).setMergingSemanticsOfDescendants(true);
                            }
                            semanticsModifierNode.applySemantics((SemanticsConfiguration) k0Var.f19746i);
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
            }
        }
    }
}
