package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "destination", "Landroidx/compose/ui/focus/FocusTargetNode;", "invoke", "(Landroidx/compose/ui/focus/FocusTargetNode;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class FocusOwnerImpl$moveFocus$foundNextItem$1 extends r implements l<FocusTargetNode, Boolean> {
    final /* synthetic */ int $focusDirection;
    final /* synthetic */ f0 $isCancelled;
    final /* synthetic */ FocusTargetNode $source;
    final /* synthetic */ FocusOwnerImpl this$0;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CustomDestinationResult.values().length];
            try {
                iArr[CustomDestinationResult.Redirected.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CustomDestinationResult.Cancelled.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CustomDestinationResult.None.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FocusOwnerImpl$moveFocus$foundNextItem$1(FocusTargetNode focusTargetNode, FocusOwnerImpl focusOwnerImpl, int i10, f0 f0Var) {
        super(1);
        this.$source = focusTargetNode;
        this.this$0 = focusOwnerImpl;
        this.$focusDirection = i10;
        this.$isCancelled = f0Var;
    }

    @Override // r7.l
    public final Boolean invoke(FocusTargetNode focusTargetNode) {
        Modifier.Node node;
        boolean zPerformRequestFocus;
        NodeChain nodes;
        if (p.a(focusTargetNode, this.$source)) {
            return Boolean.FALSE;
        }
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(1024);
        if (!focusTargetNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = focusTargetNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
        loop0: while (true) {
            node = null;
            zPerformRequestFocus = true;
            if (layoutNodeRequireLayoutNode == null) {
                break;
            }
            if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & iM4843constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM4843constructorimpl) != 0) {
                        Modifier.Node nodePop = parent;
                        MutableVector mutableVector = null;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                node = nodePop;
                                break loop0;
                            }
                            if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
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
        if (node == null) {
            throw new IllegalStateException("Focus search landed at the root.");
        }
        FocusTransactionManager focusTransactionManager = this.this$0.getFocusTransactionManager();
        int i11 = this.$focusDirection;
        f0 f0Var = this.$isCancelled;
        try {
            if (focusTransactionManager.ongoingTransaction) {
                focusTransactionManager.cancelTransaction();
            }
            focusTransactionManager.beginTransaction();
            int i12 = WhenMappings.$EnumSwitchMapping$0[FocusTransactionsKt.m3186performCustomRequestFocusMxy_nc0(focusTargetNode, i11).ordinal()];
            if (i12 != 1) {
                if (i12 == 2 || i12 == 3) {
                    f0Var.f19738i = true;
                } else {
                    if (i12 != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    zPerformRequestFocus = FocusTransactionsKt.performRequestFocus(focusTargetNode);
                }
            }
            Boolean boolValueOf = Boolean.valueOf(zPerformRequestFocus);
            focusTransactionManager.commitTransaction();
            return boolValueOf;
        } catch (Throwable th) {
            focusTransactionManager.commitTransaction();
            throw th;
        }
    }
}
