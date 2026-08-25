package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.modifier.ModifierLocal;
import androidx.compose.ui.modifier.ModifierLocalMap;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u00018B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\u0007J\u000f\u0010\n\u001a\u00020\bH\u0016¢\u0006\u0004\b\n\u0010\u0007J\u000f\u0010\u000e\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\f\u0010\rJ/\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\b0\u0011H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J/\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\b0\u0011H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0015J\u000f\u0010\u001a\u001a\u00020\bH\u0000¢\u0006\u0004\b\u0019\u0010\u0007J\u000f\u0010\u001c\u001a\u00020\bH\u0000¢\u0006\u0004\b\u001b\u0010\u0007J\u000f\u0010\u001e\u001a\u00020\bH\u0000¢\u0006\u0004\b\u001d\u0010\u0007R\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010\"\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010!R\u0016\u0010$\u001a\u00020#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\"\u0010'\u001a\u00020&8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R*\u00103\u001a\u00020#2\u0006\u0010-\u001a\u00020#8V@VX\u0096\u000e¢\u0006\u0012\u0012\u0004\b2\u0010\u0007\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0013\u00107\u001a\u0004\u0018\u0001048F¢\u0006\u0006\u001a\u0004\b5\u00106\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00069"}, d2 = {"Landroidx/compose/ui/focus/FocusTargetNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "<init>", "()V", "Lx6/t0;", "onObservedReadsChanged", "onReset", "Landroidx/compose/ui/focus/FocusProperties;", "fetchFocusProperties$ui_release", "()Landroidx/compose/ui/focus/FocusProperties;", "fetchFocusProperties", "Landroidx/compose/ui/focus/FocusDirection;", "focusDirection", "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusRequester;", "block", "fetchCustomEnter-aToIllA$ui_release", "(ILr7/l;)V", "fetchCustomEnter", "fetchCustomExit-aToIllA$ui_release", "fetchCustomExit", "commitFocusState$ui_release", "commitFocusState", "invalidateFocus$ui_release", "invalidateFocus", "scheduleInvalidationForFocusEvents$ui_release", "scheduleInvalidationForFocusEvents", "", "isProcessingCustomExit", "Z", "isProcessingCustomEnter", "Landroidx/compose/ui/focus/FocusStateImpl;", "committedFocusState", "Landroidx/compose/ui/focus/FocusStateImpl;", "", "previouslyFocusedChildHash", "I", "getPreviouslyFocusedChildHash", "()I", "setPreviouslyFocusedChildHash", "(I)V", "value", "getFocusState", "()Landroidx/compose/ui/focus/FocusStateImpl;", "setFocusState", "(Landroidx/compose/ui/focus/FocusStateImpl;)V", "getFocusState$annotations", "focusState", "Landroidx/compose/ui/layout/BeyondBoundsLayout;", "getBeyondBoundsLayoutParent", "()Landroidx/compose/ui/layout/BeyondBoundsLayout;", "beyondBoundsLayoutParent", "FocusTargetElement", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FocusTargetNode extends Modifier.Node implements CompositionLocalConsumerModifierNode, FocusTargetModifierNode, ObserverModifierNode, ModifierLocalModifierNode {
    public static final int $stable = 8;
    private FocusStateImpl committedFocusState = FocusStateImpl.Inactive;
    private boolean isProcessingCustomEnter;
    private boolean isProcessingCustomExit;
    private int previouslyFocusedChildHash;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\f\u001a\u00020\b*\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0096\u0002¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/focus/FocusTargetNode$FocusTargetElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/focus/FocusTargetNode;", "<init>", "()V", "create", "()Landroidx/compose/ui/focus/FocusTargetNode;", "node", "Lx6/t0;", "update", "(Landroidx/compose/ui/focus/FocusTargetNode;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class FocusTargetElement extends ModifierNodeElement<FocusTargetNode> {
        public static final int $stable = 0;
        public static final FocusTargetElement INSTANCE = new FocusTargetElement();

        private FocusTargetElement() {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public boolean equals(Object other) {
            return other == this;
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public int hashCode() {
            return 1739042953;
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void inspectableProperties(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("focusTarget");
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(FocusTargetNode node) {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public FocusTargetNode create() {
            return new FocusTargetNode();
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            try {
                iArr[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FocusStateImpl.Captured.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FocusStateImpl.ActiveParent.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ void getFocusState$annotations() {
    }

    public final void commitFocusState$ui_release() {
        FocusStateImpl uncommittedFocusState = FocusTargetNodeKt.requireTransactionManager(this).getUncommittedFocusState(this);
        if (uncommittedFocusState == null) {
            throw new IllegalStateException("committing a node that was not updated in the current transaction");
        }
        this.committedFocusState = uncommittedFocusState;
    }

    /* JADX INFO: renamed from: fetchCustomEnter-aToIllA$ui_release, reason: not valid java name */
    public final void m3180fetchCustomEnteraToIllA$ui_release(int focusDirection, l<? super FocusRequester, t0> block) {
        if (this.isProcessingCustomEnter) {
            return;
        }
        this.isProcessingCustomEnter = true;
        try {
            FocusRequester focusRequester = (FocusRequester) fetchFocusProperties$ui_release().getEnter().invoke(FocusDirection.m3150boximpl(focusDirection));
            if (focusRequester != FocusRequester.INSTANCE.getDefault()) {
                block.invoke(focusRequester);
            }
        } finally {
            this.isProcessingCustomEnter = false;
        }
    }

    /* JADX INFO: renamed from: fetchCustomExit-aToIllA$ui_release, reason: not valid java name */
    public final void m3181fetchCustomExitaToIllA$ui_release(int focusDirection, l<? super FocusRequester, t0> block) {
        if (this.isProcessingCustomExit) {
            return;
        }
        this.isProcessingCustomExit = true;
        try {
            FocusRequester focusRequester = (FocusRequester) fetchFocusProperties$ui_release().getExit().invoke(FocusDirection.m3150boximpl(focusDirection));
            if (focusRequester != FocusRequester.INSTANCE.getDefault()) {
                block.invoke(focusRequester);
            }
        } finally {
            this.isProcessingCustomExit = false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v12 */
    public final FocusProperties fetchFocusProperties$ui_release() {
        NodeChain nodes;
        FocusPropertiesImpl focusPropertiesImpl = new FocusPropertiesImpl();
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(2048);
        int iM4843constructorimpl2 = NodeKind.m4843constructorimpl(1024);
        Modifier.Node node = getNode();
        int i10 = iM4843constructorimpl | iM4843constructorimpl2;
        if (!getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node2 = getNode();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(this);
        while (layoutNodeRequireLayoutNode != null) {
            if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & i10) != 0) {
                while (node2 != null) {
                    if ((node2.getKindSet() & i10) != 0) {
                        if (node2 != node && (node2.getKindSet() & iM4843constructorimpl2) != 0) {
                            return focusPropertiesImpl;
                        }
                        if ((node2.getKindSet() & iM4843constructorimpl) != 0) {
                            Modifier.Node nodePop = node2;
                            MutableVector mutableVector = null;
                            while (nodePop != 0) {
                                if (nodePop instanceof FocusPropertiesModifierNode) {
                                    ((FocusPropertiesModifierNode) nodePop).applyFocusProperties(focusPropertiesImpl);
                                } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                    Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                                    int i11 = 0;
                                    nodePop = nodePop;
                                    while (delegate$ui_release != null) {
                                        if ((delegate$ui_release.getKindSet() & iM4843constructorimpl) != 0) {
                                            i11++;
                                            if (i11 == 1) {
                                                nodePop = delegate$ui_release;
                                            } else {
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != 0) {
                                                    mutableVector.add(nodePop);
                                                    nodePop = 0;
                                                }
                                                mutableVector.add(delegate$ui_release);
                                            }
                                        }
                                        delegate$ui_release = delegate$ui_release.getChild();
                                        nodePop = nodePop;
                                    }
                                    if (i11 == 1) {
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector);
                            }
                        }
                    }
                    node2 = node2.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            node2 = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return focusPropertiesImpl;
    }

    public final BeyondBoundsLayout getBeyondBoundsLayoutParent() {
        return (BeyondBoundsLayout) androidx.compose.ui.modifier.b.a(this, androidx.compose.ui.layout.BeyondBoundsLayoutKt.getModifierLocalBeyondBoundsLayout());
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode, androidx.compose.ui.modifier.ModifierLocalReadScope
    public final /* synthetic */ Object getCurrent(ModifierLocal modifierLocal) {
        return androidx.compose.ui.modifier.b.a(this, modifierLocal);
    }

    public final int getPreviouslyFocusedChildHash() {
        return this.previouslyFocusedChildHash;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode
    public final /* synthetic */ ModifierLocalMap getProvidedValues() {
        return androidx.compose.ui.modifier.b.b(this);
    }

    public final void invalidateFocus$ui_release() {
        int i10 = WhenMappings.$EnumSwitchMapping$0[getFocusState().ordinal()];
        if (i10 == 1 || i10 == 2) {
            k0 k0Var = new k0();
            ObserverModifierNodeKt.observeReads(this, new FocusTargetNode$invalidateFocus$1(k0Var, this));
            Object obj = k0Var.f19746i;
            if (obj == null) {
                p.i("focusProperties");
                throw null;
            }
            if (((FocusProperties) obj).getCanFocus()) {
                return;
            }
            DelegatableNodeKt.requireOwner(this).getFocusOwner().clearFocus(true);
        }
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        FocusStateImpl focusState = getFocusState();
        invalidateFocus$ui_release();
        if (focusState != getFocusState()) {
            FocusEventModifierNodeKt.refreshFocusEventNodes(this);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onReset() {
        int i10 = WhenMappings.$EnumSwitchMapping$0[getFocusState().ordinal()];
        if (i10 == 1 || i10 == 2) {
            DelegatableNodeKt.requireOwner(this).getFocusOwner().clearFocus(true);
            return;
        }
        if (i10 != 3) {
            if (i10 != 4) {
                return;
            }
            scheduleInvalidationForFocusEvents$ui_release();
            return;
        }
        scheduleInvalidationForFocusEvents$ui_release();
        FocusTransactionManager focusTransactionManagerRequireTransactionManager = FocusTargetNodeKt.requireTransactionManager(this);
        try {
            if (focusTransactionManagerRequireTransactionManager.ongoingTransaction) {
                focusTransactionManagerRequireTransactionManager.cancelTransaction();
            }
            focusTransactionManagerRequireTransactionManager.beginTransaction();
            setFocusState(FocusStateImpl.Inactive);
            focusTransactionManagerRequireTransactionManager.commitTransaction();
        } catch (Throwable th) {
            focusTransactionManagerRequireTransactionManager.commitTransaction();
            throw th;
        }
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode
    public final /* synthetic */ void provide(ModifierLocal modifierLocal, Object obj) {
        androidx.compose.ui.modifier.b.c(this, modifierLocal, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r10v7 */
    public final void scheduleInvalidationForFocusEvents$ui_release() {
        NodeChain nodes;
        Modifier.Node node = getNode();
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(4096);
        MutableVector mutableVector = null;
        while (node != 0) {
            if (node instanceof FocusEventModifierNode) {
                FocusEventModifierNodeKt.invalidateFocusEvent((FocusEventModifierNode) node);
            } else if ((node.getKindSet() & iM4843constructorimpl) != 0 && (node instanceof DelegatingNode)) {
                Modifier.Node delegate$ui_release = ((DelegatingNode) node).getDelegate();
                int i10 = 0;
                node = node;
                while (delegate$ui_release != null) {
                    if ((delegate$ui_release.getKindSet() & iM4843constructorimpl) != 0) {
                        i10++;
                        if (i10 == 1) {
                            node = delegate$ui_release;
                        } else {
                            if (mutableVector == null) {
                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                            }
                            if (node != 0) {
                                mutableVector.add(node);
                                node = 0;
                            }
                            mutableVector.add(delegate$ui_release);
                        }
                    }
                    delegate$ui_release = delegate$ui_release.getChild();
                    node = node;
                }
                if (i10 == 1) {
                }
            }
            node = DelegatableNodeKt.pop(mutableVector);
        }
        int iM4843constructorimpl2 = NodeKind.m4843constructorimpl(4096) | NodeKind.m4843constructorimpl(1024);
        if (!getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(this);
        while (layoutNodeRequireLayoutNode != null) {
            if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & iM4843constructorimpl2) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM4843constructorimpl2) != 0 && (NodeKind.m4843constructorimpl(1024) & parent.getKindSet()) == 0 && parent.getIsAttached()) {
                        int iM4843constructorimpl3 = NodeKind.m4843constructorimpl(4096);
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = parent;
                        while (nodePop != 0) {
                            if (nodePop instanceof FocusEventModifierNode) {
                                FocusEventModifierNodeKt.invalidateFocusEvent((FocusEventModifierNode) nodePop);
                            } else if ((nodePop.getKindSet() & iM4843constructorimpl3) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate$ui_release2 = ((DelegatingNode) nodePop).getDelegate();
                                int i11 = 0;
                                nodePop = nodePop;
                                while (delegate$ui_release2 != null) {
                                    if ((delegate$ui_release2.getKindSet() & iM4843constructorimpl3) != 0) {
                                        i11++;
                                        if (i11 == 1) {
                                            nodePop = delegate$ui_release2;
                                        } else {
                                            if (mutableVector2 == null) {
                                                mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                mutableVector2.add(nodePop);
                                                nodePop = 0;
                                            }
                                            mutableVector2.add(delegate$ui_release2);
                                        }
                                    }
                                    delegate$ui_release2 = delegate$ui_release2.getChild();
                                    nodePop = nodePop;
                                }
                                if (i11 == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    public void setFocusState(FocusStateImpl focusStateImpl) {
        FocusTargetNodeKt.requireTransactionManager(this).setUncommittedFocusState(this, focusStateImpl);
    }

    public final void setPreviouslyFocusedChildHash(int i10) {
        this.previouslyFocusedChildHash = i10;
    }

    @Override // androidx.compose.ui.focus.FocusTargetModifierNode
    public FocusStateImpl getFocusState() {
        FocusStateImpl uncommittedFocusState;
        FocusTransactionManager focusTransactionManager = FocusTargetNodeKt.getFocusTransactionManager(this);
        return (focusTransactionManager == null || (uncommittedFocusState = focusTransactionManager.getUncommittedFocusState(this)) == null) ? this.committedFocusState : uncommittedFocusState;
    }
}
