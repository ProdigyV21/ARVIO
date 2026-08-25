package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.focus.FocusEventModifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusEventModifierNodeKt;
import androidx.compose.ui.focus.FocusOrderModifier;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusPropertiesModifierNodeKt;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.focus.FocusTargetNodeKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.key.SoftKeyboardInterceptionModifierNode;
import androidx.compose.ui.input.pointer.PointerInputModifier;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.layout.IntermediateLayoutModifierNode;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.OnGloballyPositionedModifier;
import androidx.compose.ui.layout.OnPlacedModifier;
import androidx.compose.ui.layout.OnRemeasuredModifier;
import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.modifier.ModifierLocalConsumer;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.modifier.ModifierLocalProvider;
import androidx.compose.ui.semantics.SemanticsModifier;
import androidx.media3.session.MediaUtils;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0011\u001a#\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0080\fø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a#\u0010\n\u001a\u00020\u0007*\u00020\u00002\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0080\nø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u0017\u0010\r\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u0017\u0010\r\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\r\u0010\u0011\u001a\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0017\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0015\u0010\u0014\u001a\u0017\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0016\u0010\u0014\u001a'\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a'\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u001c\u0010\u001a\u001a\u0013\u0010\u001e\u001a\u00020\u0012*\u00020\u001dH\u0002¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u0013\u0010 \u001a\u00020\u0007*\u00020\u001dH\u0002¢\u0006\u0004\b \u0010!\u001a\u0017\u0010\"\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\"\u0010\u0011\"\u001a\u0010#\u001a\u00020\u00008\u0002X\u0082T¢\u0006\f\n\u0004\b#\u0010$\u0012\u0004\b%\u0010&\"\u001a\u0010'\u001a\u00020\u00008\u0002X\u0082T¢\u0006\f\n\u0004\b'\u0010$\u0012\u0004\b(\u0010&\"\u001a\u0010)\u001a\u00020\u00008\u0002X\u0082T¢\u0006\f\n\u0004\b)\u0010$\u0012\u0004\b*\u0010&\"\u001c\u0010-\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006."}, d2 = {"", "Landroidx/compose/ui/node/NodeKind;", "other", "or-64DMado", "(II)I", "or", "value", "", "contains-64DMado", "(II)Z", "contains", "Landroidx/compose/ui/Modifier$Element;", "element", "calculateNodeKindSetFrom", "(Landroidx/compose/ui/Modifier$Element;)I", "Landroidx/compose/ui/Modifier$Node;", "node", "(Landroidx/compose/ui/Modifier$Node;)I", "Lx6/t0;", "autoInvalidateRemovedNode", "(Landroidx/compose/ui/Modifier$Node;)V", "autoInvalidateInsertedNode", "autoInvalidateUpdatedNode", "remainingSet", "phase", "autoInvalidateNodeIncludingDelegates", "(Landroidx/compose/ui/Modifier$Node;II)V", "selfKindSet", "autoInvalidateNodeSelf", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "scheduleInvalidationOfAssociatedFocusTargets", "(Landroidx/compose/ui/focus/FocusPropertiesModifierNode;)V", "specifiesCanFocusProperty", "(Landroidx/compose/ui/focus/FocusPropertiesModifierNode;)Z", "calculateNodeKindSetFromIncludingDelegates", "Updated", "I", "getUpdated$annotations", "()V", "Inserted", "getInserted$annotations", "Removed", "getRemoved$annotations", "getIncludeSelfInTraversal-H91voCI", "(I)Z", "includeSelfInTraversal", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NodeKindKt {
    private static final int Inserted = 1;
    private static final int Removed = 2;
    private static final int Updated = 0;

    public static final void autoInvalidateInsertedNode(Modifier.Node node) {
        if (!node.getIsAttached()) {
            throw new IllegalStateException("autoInvalidateInsertedNode called on unattached node");
        }
        autoInvalidateNodeIncludingDelegates(node, -1, 1);
    }

    public static final void autoInvalidateNodeIncludingDelegates(Modifier.Node node, int i10, int i11) {
        if (!(node instanceof DelegatingNode)) {
            autoInvalidateNodeSelf(node, i10 & node.getKindSet(), i11);
            return;
        }
        DelegatingNode delegatingNode = (DelegatingNode) node;
        autoInvalidateNodeSelf(node, delegatingNode.getSelfKindSet() & i10, i11);
        int i12 = (~delegatingNode.getSelfKindSet()) & i10;
        for (Modifier.Node delegate = delegatingNode.getDelegate(); delegate != null; delegate = delegate.getChild()) {
            autoInvalidateNodeIncludingDelegates(delegate, i12, i11);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void autoInvalidateNodeSelf(Modifier.Node node, int i10, int i11) {
        if (i11 != 0 || node.getShouldAutoInvalidate()) {
            if ((NodeKind.m4843constructorimpl(2) & i10) != 0 && (node instanceof LayoutModifierNode)) {
                LayoutModifierNodeKt.invalidateMeasurement((LayoutModifierNode) node);
                if (i11 == 2) {
                    DelegatableNodeKt.m4726requireCoordinator64DMado(node, NodeKind.m4843constructorimpl(2)).onRelease();
                }
            }
            if ((NodeKind.m4843constructorimpl(256) & i10) != 0 && (node instanceof GlobalPositionAwareModifierNode)) {
                DelegatableNodeKt.requireLayoutNode(node).invalidateMeasurements$ui_release();
            }
            if ((NodeKind.m4843constructorimpl(4) & i10) != 0 && (node instanceof DrawModifierNode)) {
                DrawModifierNodeKt.invalidateDraw((DrawModifierNode) node);
            }
            if ((NodeKind.m4843constructorimpl(8) & i10) != 0 && (node instanceof SemanticsModifierNode)) {
                SemanticsModifierNodeKt.invalidateSemantics((SemanticsModifierNode) node);
            }
            if ((NodeKind.m4843constructorimpl(64) & i10) != 0 && (node instanceof ParentDataModifierNode)) {
                ParentDataModifierNodeKt.invalidateParentData((ParentDataModifierNode) node);
            }
            if ((NodeKind.m4843constructorimpl(1024) & i10) != 0 && (node instanceof FocusTargetNode)) {
                if (i11 == 2) {
                    node.onReset();
                } else {
                    DelegatableNodeKt.requireOwner(node).getFocusOwner().scheduleInvalidation((FocusTargetNode) node);
                }
            }
            if ((NodeKind.m4843constructorimpl(2048) & i10) != 0 && (node instanceof FocusPropertiesModifierNode)) {
                FocusPropertiesModifierNode focusPropertiesModifierNode = (FocusPropertiesModifierNode) node;
                if (specifiesCanFocusProperty(focusPropertiesModifierNode)) {
                    if (i11 == 2) {
                        scheduleInvalidationOfAssociatedFocusTargets(focusPropertiesModifierNode);
                    } else {
                        FocusPropertiesModifierNodeKt.invalidateFocusProperties(focusPropertiesModifierNode);
                    }
                }
            }
            if ((i10 & NodeKind.m4843constructorimpl(4096)) == 0 || !(node instanceof FocusEventModifierNode)) {
                return;
            }
            FocusEventModifierNodeKt.invalidateFocusEvent((FocusEventModifierNode) node);
        }
    }

    public static final void autoInvalidateRemovedNode(Modifier.Node node) {
        if (!node.getIsAttached()) {
            throw new IllegalStateException("autoInvalidateRemovedNode called on unattached node");
        }
        autoInvalidateNodeIncludingDelegates(node, -1, 2);
    }

    public static final void autoInvalidateUpdatedNode(Modifier.Node node) {
        if (!node.getIsAttached()) {
            throw new IllegalStateException("autoInvalidateUpdatedNode called on unattached node");
        }
        autoInvalidateNodeIncludingDelegates(node, -1, 0);
    }

    public static final int calculateNodeKindSetFrom(Modifier.Node node) {
        if (node.getKindSet() != 0) {
            return node.getKindSet();
        }
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(1);
        if (node instanceof LayoutModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(2);
        }
        if (node instanceof DrawModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(4);
        }
        if (node instanceof SemanticsModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(8);
        }
        if (node instanceof PointerInputModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(16);
        }
        if (node instanceof ModifierLocalModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(32);
        }
        if (node instanceof ParentDataModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(64);
        }
        if (node instanceof LayoutAwareModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(128);
        }
        if (node instanceof GlobalPositionAwareModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(256);
        }
        if (node instanceof IntermediateLayoutModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(512);
        }
        if (node instanceof FocusTargetNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(1024);
        }
        if (node instanceof FocusPropertiesModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(2048);
        }
        if (node instanceof FocusEventModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(4096);
        }
        if (node instanceof KeyInputModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(8192);
        }
        if (node instanceof RotaryInputModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(16384);
        }
        if (node instanceof CompositionLocalConsumerModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(32768);
        }
        if (node instanceof SoftKeyboardInterceptionModifierNode) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(131072);
        }
        return node instanceof TraversableNode ? NodeKind.m4843constructorimpl(MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) | iM4843constructorimpl : iM4843constructorimpl;
    }

    public static final int calculateNodeKindSetFromIncludingDelegates(Modifier.Node node) {
        if (!(node instanceof DelegatingNode)) {
            return calculateNodeKindSetFrom(node);
        }
        DelegatingNode delegatingNode = (DelegatingNode) node;
        int selfKindSet = delegatingNode.getSelfKindSet();
        for (Modifier.Node delegate = delegatingNode.getDelegate(); delegate != null; delegate = delegate.getChild()) {
            selfKindSet |= calculateNodeKindSetFromIncludingDelegates(delegate);
        }
        return selfKindSet;
    }

    /* JADX INFO: renamed from: contains-64DMado, reason: not valid java name */
    public static final boolean m4851contains64DMado(int i10, int i11) {
        return (i10 & i11) != 0;
    }

    /* JADX INFO: renamed from: getIncludeSelfInTraversal-H91voCI, reason: not valid java name */
    public static final boolean m4852getIncludeSelfInTraversalH91voCI(int i10) {
        return (i10 & NodeKind.m4843constructorimpl(128)) != 0;
    }

    private static /* synthetic */ void getInserted$annotations() {
    }

    private static /* synthetic */ void getRemoved$annotations() {
    }

    private static /* synthetic */ void getUpdated$annotations() {
    }

    /* JADX INFO: renamed from: or-64DMado, reason: not valid java name */
    public static final int m4853or64DMado(int i10, int i11) {
        return i10 | i11;
    }

    private static final void scheduleInvalidationOfAssociatedFocusTargets(FocusPropertiesModifierNode focusPropertiesModifierNode) {
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(1024);
        if (!focusPropertiesModifierNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = focusPropertiesModifierNode.getNode().getChild();
        if (child == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector, focusPropertiesModifierNode.getNode());
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.isNotEmpty()) {
            Modifier.Node nodePop = (Modifier.Node) androidx.compose.material3.d.h(mutableVector, 1);
            if ((nodePop.getAggregateChildKindSet() & iM4843constructorimpl) == 0) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector, nodePop);
            } else {
                while (true) {
                    if (nodePop == null) {
                        break;
                    }
                    if ((nodePop.getKindSet() & iM4843constructorimpl) != 0) {
                        MutableVector mutableVector2 = null;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNodeKt.invalidateFocusTarget((FocusTargetNode) nodePop);
                            } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i10 = 0;
                                for (Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate(); delegate != null; delegate = delegate.getChild()) {
                                    if ((delegate.getKindSet() & iM4843constructorimpl) != 0) {
                                        i10++;
                                        if (i10 == 1) {
                                            nodePop = delegate;
                                        } else {
                                            if (mutableVector2 == null) {
                                                mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                mutableVector2.add(nodePop);
                                                nodePop = null;
                                            }
                                            mutableVector2.add(delegate);
                                        }
                                    }
                                }
                                if (i10 == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                        }
                    } else {
                        nodePop = nodePop.getChild();
                    }
                }
            }
        }
    }

    private static final boolean specifiesCanFocusProperty(FocusPropertiesModifierNode focusPropertiesModifierNode) {
        CanFocusChecker canFocusChecker = CanFocusChecker.INSTANCE;
        canFocusChecker.reset();
        focusPropertiesModifierNode.applyFocusProperties(canFocusChecker);
        return canFocusChecker.isCanFocusSet();
    }

    public static final int calculateNodeKindSetFrom(Modifier.Element element) {
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(1);
        if (element instanceof LayoutModifier) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(2);
        }
        if (element instanceof DrawModifier) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(4);
        }
        if (element instanceof SemanticsModifier) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(8);
        }
        if (element instanceof PointerInputModifier) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(16);
        }
        if ((element instanceof ModifierLocalConsumer) || (element instanceof ModifierLocalProvider)) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(32);
        }
        if (element instanceof FocusEventModifier) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(4096);
        }
        if (element instanceof FocusOrderModifier) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(2048);
        }
        if (element instanceof OnGloballyPositionedModifier) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(256);
        }
        if (element instanceof ParentDataModifier) {
            iM4843constructorimpl |= NodeKind.m4843constructorimpl(64);
        }
        return ((element instanceof OnPlacedModifier) || (element instanceof OnRemeasuredModifier)) ? NodeKind.m4843constructorimpl(128) | iM4843constructorimpl : iM4843constructorimpl;
    }
}
