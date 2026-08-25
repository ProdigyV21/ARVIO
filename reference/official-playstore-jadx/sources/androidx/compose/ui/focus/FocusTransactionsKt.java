package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.Owner;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a \u0010\u0003\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\u0002\u001a\u001e\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u001e\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u001e\u0010\u000f\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000e\u001a\u001e\u0010\u0011\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u000e\u001a\u001e\u0010\u0013\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u000e\u001a\f\u0010\u0015\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0016\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0014\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0002H\u0002\u001a\f\u0010\u0019\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u001a\u001a\u00020\u0002*\u00020\u0002H\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"captureFocus", "", "Landroidx/compose/ui/focus/FocusTargetNode;", "clearChildFocus", "forced", "refreshFocusEvents", "clearFocus", "freeFocus", "grantFocus", "performCustomClearFocus", "Landroidx/compose/ui/focus/CustomDestinationResult;", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "performCustomClearFocus-Mxy_nc0", "(Landroidx/compose/ui/focus/FocusTargetNode;I)Landroidx/compose/ui/focus/CustomDestinationResult;", "performCustomEnter", "performCustomEnter-Mxy_nc0", "performCustomExit", "performCustomExit-Mxy_nc0", "performCustomRequestFocus", "performCustomRequestFocus-Mxy_nc0", "performRequestFocus", "requestFocus", "requestFocusForChild", "childNode", "requestFocusForOwner", "requireActiveChild", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FocusTransactionsKt {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CustomDestinationResult.values().length];
            try {
                iArr[CustomDestinationResult.None.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CustomDestinationResult.Redirected.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CustomDestinationResult.Cancelled.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FocusStateImpl.values().length];
            try {
                iArr2[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[FocusStateImpl.Captured.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[FocusStateImpl.ActiveParent.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.focus.FocusTransactionsKt$grantFocus$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.a<t0> {
        final /* synthetic */ FocusTargetNode $this_grantFocus;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(FocusTargetNode focusTargetNode) {
            super(0);
            this.$this_grantFocus = focusTargetNode;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m3187invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m3187invoke() {
            this.$this_grantFocus.fetchFocusProperties$ui_release();
        }
    }

    public static final boolean captureFocus(FocusTargetNode focusTargetNode) {
        FocusTransactionManager focusTransactionManagerRequireTransactionManager = FocusTargetNodeKt.requireTransactionManager(focusTargetNode);
        try {
            if (focusTransactionManagerRequireTransactionManager.ongoingTransaction) {
                focusTransactionManagerRequireTransactionManager.cancelTransaction();
            }
            focusTransactionManagerRequireTransactionManager.beginTransaction();
            int i10 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
            boolean z = true;
            if (i10 == 1) {
                focusTargetNode.setFocusState(FocusStateImpl.Captured);
                FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
            } else if (i10 != 2) {
                if (i10 != 3 && i10 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                z = false;
            }
            return z;
        } finally {
            focusTransactionManagerRequireTransactionManager.commitTransaction();
        }
    }

    private static final boolean clearChildFocus(FocusTargetNode focusTargetNode, boolean z, boolean z5) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
        if (activeChild != null) {
            return clearFocus(activeChild, z, z5);
        }
        return true;
    }

    public static /* synthetic */ boolean clearChildFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        if ((i10 & 2) != 0) {
            z5 = true;
        }
        return clearChildFocus(focusTargetNode, z, z5);
    }

    public static final boolean clearFocus(FocusTargetNode focusTargetNode, boolean z, boolean z5) {
        int i10 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i10 == 1) {
            focusTargetNode.setFocusState(FocusStateImpl.Inactive);
            if (z5) {
                FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
            }
            return true;
        }
        if (i10 == 2) {
            if (z) {
                focusTargetNode.setFocusState(FocusStateImpl.Inactive);
                if (z5) {
                    FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
                }
            }
            return z;
        }
        if (i10 != 3) {
            if (i10 == 4) {
                return true;
            }
            throw new NoWhenBranchMatchedException();
        }
        if (!clearChildFocus(focusTargetNode, z, z5)) {
            return false;
        }
        focusTargetNode.setFocusState(FocusStateImpl.Inactive);
        if (z5) {
            FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
        }
        return true;
    }

    public static /* synthetic */ boolean clearFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        return clearFocus(focusTargetNode, z, z5);
    }

    public static final boolean freeFocus(FocusTargetNode focusTargetNode) {
        FocusTransactionManager focusTransactionManagerRequireTransactionManager = FocusTargetNodeKt.requireTransactionManager(focusTargetNode);
        try {
            if (focusTransactionManagerRequireTransactionManager.ongoingTransaction) {
                focusTransactionManagerRequireTransactionManager.cancelTransaction();
            }
            focusTransactionManagerRequireTransactionManager.beginTransaction();
            int i10 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
            boolean z = true;
            if (i10 != 1) {
                if (i10 != 2) {
                    if (i10 != 3 && i10 != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    z = false;
                } else {
                    focusTargetNode.setFocusState(FocusStateImpl.Active);
                    FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
                }
            }
            return z;
        } finally {
            focusTransactionManagerRequireTransactionManager.commitTransaction();
        }
    }

    private static final boolean grantFocus(FocusTargetNode focusTargetNode) {
        ObserverModifierNodeKt.observeReads(focusTargetNode, new AnonymousClass1(focusTargetNode));
        int i10 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i10 != 3 && i10 != 4) {
            return true;
        }
        focusTargetNode.setFocusState(FocusStateImpl.Active);
        return true;
    }

    /* JADX INFO: renamed from: performCustomClearFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m3183performCustomClearFocusMxy_nc0(FocusTargetNode focusTargetNode, int i10) {
        int i11 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i11 != 1) {
            if (i11 == 2) {
                return CustomDestinationResult.Cancelled;
            }
            if (i11 == 3) {
                CustomDestinationResult customDestinationResultM3183performCustomClearFocusMxy_nc0 = m3183performCustomClearFocusMxy_nc0(requireActiveChild(focusTargetNode), i10);
                if (customDestinationResultM3183performCustomClearFocusMxy_nc0 == CustomDestinationResult.None) {
                    customDestinationResultM3183performCustomClearFocusMxy_nc0 = null;
                }
                return customDestinationResultM3183performCustomClearFocusMxy_nc0 == null ? m3185performCustomExitMxy_nc0(focusTargetNode, i10) : customDestinationResultM3183performCustomClearFocusMxy_nc0;
            }
            if (i11 != 4) {
                throw new NoWhenBranchMatchedException();
            }
        }
        return CustomDestinationResult.None;
    }

    /* JADX INFO: renamed from: performCustomEnter-Mxy_nc0, reason: not valid java name */
    private static final CustomDestinationResult m3184performCustomEnterMxy_nc0(FocusTargetNode focusTargetNode, int i10) {
        if (!focusTargetNode.isProcessingCustomEnter) {
            focusTargetNode.isProcessingCustomEnter = true;
            try {
                FocusRequester focusRequester = (FocusRequester) focusTargetNode.fetchFocusProperties$ui_release().getEnter().invoke(FocusDirection.m3150boximpl(i10));
                FocusRequester.Companion companion = FocusRequester.INSTANCE;
                if (focusRequester != companion.getDefault()) {
                    if (focusRequester == companion.getCancel()) {
                        return CustomDestinationResult.Cancelled;
                    }
                    return focusRequester.focus$ui_release() ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                }
            } finally {
                focusTargetNode.isProcessingCustomEnter = false;
            }
        }
        return CustomDestinationResult.None;
    }

    /* JADX INFO: renamed from: performCustomExit-Mxy_nc0, reason: not valid java name */
    private static final CustomDestinationResult m3185performCustomExitMxy_nc0(FocusTargetNode focusTargetNode, int i10) {
        if (!focusTargetNode.isProcessingCustomExit) {
            focusTargetNode.isProcessingCustomExit = true;
            try {
                FocusRequester focusRequester = (FocusRequester) focusTargetNode.fetchFocusProperties$ui_release().getExit().invoke(FocusDirection.m3150boximpl(i10));
                FocusRequester.Companion companion = FocusRequester.INSTANCE;
                if (focusRequester != companion.getDefault()) {
                    if (focusRequester == companion.getCancel()) {
                        return CustomDestinationResult.Cancelled;
                    }
                    return focusRequester.focus$ui_release() ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                }
            } finally {
                focusTargetNode.isProcessingCustomExit = false;
            }
        }
        return CustomDestinationResult.None;
    }

    /* JADX INFO: renamed from: performCustomRequestFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m3186performCustomRequestFocusMxy_nc0(FocusTargetNode focusTargetNode, int i10) {
        Modifier.Node nodePop;
        NodeChain nodes;
        int i11 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i11 == 1 || i11 == 2) {
            return CustomDestinationResult.None;
        }
        if (i11 == 3) {
            return m3183performCustomClearFocusMxy_nc0(requireActiveChild(focusTargetNode), i10);
        }
        if (i11 != 4) {
            throw new NoWhenBranchMatchedException();
        }
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
                                int i12 = 0;
                                for (Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate(); delegate$ui_release != null; delegate$ui_release = delegate$ui_release.getChild()) {
                                    if ((delegate$ui_release.getKindSet() & iM4843constructorimpl) != 0) {
                                        i12++;
                                        if (i12 == 1) {
                                            nodePop = delegate$ui_release;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                mutableVector.add(nodePop);
                                                nodePop = null;
                                            }
                                            mutableVector.add(delegate$ui_release);
                                        }
                                    }
                                }
                                if (i12 == 1) {
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
        if (focusTargetNode2 == null) {
            return CustomDestinationResult.None;
        }
        int i13 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode2.getFocusState().ordinal()];
        if (i13 == 1) {
            return m3184performCustomEnterMxy_nc0(focusTargetNode2, i10);
        }
        if (i13 == 2) {
            return CustomDestinationResult.Cancelled;
        }
        if (i13 == 3) {
            return m3186performCustomRequestFocusMxy_nc0(focusTargetNode2, i10);
        }
        if (i13 != 4) {
            throw new NoWhenBranchMatchedException();
        }
        CustomDestinationResult customDestinationResultM3186performCustomRequestFocusMxy_nc0 = m3186performCustomRequestFocusMxy_nc0(focusTargetNode2, i10);
        CustomDestinationResult customDestinationResult = customDestinationResultM3186performCustomRequestFocusMxy_nc0 != CustomDestinationResult.None ? customDestinationResultM3186performCustomRequestFocusMxy_nc0 : null;
        return customDestinationResult == null ? m3184performCustomEnterMxy_nc0(focusTargetNode2, i10) : customDestinationResult;
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean performRequestFocus(androidx.compose.ui.focus.FocusTargetNode r11) {
        /*
            Method dump skipped, instruction units count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTransactionsKt.performRequestFocus(androidx.compose.ui.focus.FocusTargetNode):boolean");
    }

    public static final boolean requestFocus(FocusTargetNode focusTargetNode) {
        FocusTransactionManager focusTransactionManagerRequireTransactionManager = FocusTargetNodeKt.requireTransactionManager(focusTargetNode);
        try {
            if (focusTransactionManagerRequireTransactionManager.ongoingTransaction) {
                focusTransactionManagerRequireTransactionManager.cancelTransaction();
            }
            focusTransactionManagerRequireTransactionManager.beginTransaction();
            int i10 = WhenMappings.$EnumSwitchMapping$0[m3186performCustomRequestFocusMxy_nc0(focusTargetNode, FocusDirection.INSTANCE.m3160getEnterdhqQ8s()).ordinal()];
            boolean zPerformRequestFocus = true;
            if (i10 == 1) {
                zPerformRequestFocus = performRequestFocus(focusTargetNode);
            } else if (i10 != 2) {
                if (i10 != 3 && i10 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                zPerformRequestFocus = false;
            }
            return zPerformRequestFocus;
        } finally {
            focusTransactionManagerRequireTransactionManager.commitTransaction();
        }
    }

    private static final boolean requestFocusForChild(FocusTargetNode focusTargetNode, FocusTargetNode focusTargetNode2) {
        Modifier.Node node;
        Modifier.Node nodePop;
        NodeChain nodes;
        NodeChain nodes2;
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(1024);
        if (!focusTargetNode2.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = focusTargetNode2.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode2);
        loop0: while (true) {
            node = null;
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
                                int i10 = 0;
                                for (Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate(); delegate$ui_release != null; delegate$ui_release = delegate$ui_release.getChild()) {
                                    if ((delegate$ui_release.getKindSet() & iM4843constructorimpl) != 0) {
                                        i10++;
                                        if (i10 == 1) {
                                            nodePop = delegate$ui_release;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                mutableVector.add(nodePop);
                                                nodePop = null;
                                            }
                                            mutableVector.add(delegate$ui_release);
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
            parent = (layoutNodeRequireLayoutNode == null || (nodes2 = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes2.getTail();
        }
        if (!p.a(nodePop, focusTargetNode)) {
            throw new IllegalStateException("Non child node cannot request focus.");
        }
        int i11 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i11 == 1) {
            boolean zGrantFocus = grantFocus(focusTargetNode2);
            if (zGrantFocus) {
                focusTargetNode.setFocusState(FocusStateImpl.ActiveParent);
            }
            return zGrantFocus;
        }
        if (i11 != 2) {
            if (i11 != 3) {
                if (i11 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                int iM4843constructorimpl2 = NodeKind.m4843constructorimpl(1024);
                if (!focusTargetNode.getNode().getIsAttached()) {
                    throw new IllegalStateException("visitAncestors called on an unattached node");
                }
                Modifier.Node parent2 = focusTargetNode.getNode().getParent();
                LayoutNode layoutNodeRequireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
                loop4: while (true) {
                    if (layoutNodeRequireLayoutNode2 == null) {
                        break;
                    }
                    if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode2) & iM4843constructorimpl2) != 0) {
                        while (parent2 != null) {
                            if ((parent2.getKindSet() & iM4843constructorimpl2) != 0) {
                                Modifier.Node nodePop2 = parent2;
                                MutableVector mutableVector2 = null;
                                while (nodePop2 != null) {
                                    if (nodePop2 instanceof FocusTargetNode) {
                                        node = nodePop2;
                                        break loop4;
                                    }
                                    if ((nodePop2.getKindSet() & iM4843constructorimpl2) != 0 && (nodePop2 instanceof DelegatingNode)) {
                                        int i12 = 0;
                                        for (Modifier.Node delegate$ui_release2 = ((DelegatingNode) nodePop2).getDelegate(); delegate$ui_release2 != null; delegate$ui_release2 = delegate$ui_release2.getChild()) {
                                            if ((delegate$ui_release2.getKindSet() & iM4843constructorimpl2) != 0) {
                                                i12++;
                                                if (i12 == 1) {
                                                    nodePop2 = delegate$ui_release2;
                                                } else {
                                                    if (mutableVector2 == null) {
                                                        mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                    }
                                                    if (nodePop2 != null) {
                                                        mutableVector2.add(nodePop2);
                                                        nodePop2 = null;
                                                    }
                                                    mutableVector2.add(delegate$ui_release2);
                                                }
                                            }
                                        }
                                        if (i12 == 1) {
                                        }
                                    }
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector2);
                                }
                            }
                            parent2 = parent2.getParent();
                        }
                    }
                    layoutNodeRequireLayoutNode2 = layoutNodeRequireLayoutNode2.getParent$ui_release();
                    parent2 = (layoutNodeRequireLayoutNode2 == null || (nodes = layoutNodeRequireLayoutNode2.getNodes()) == null) ? null : nodes.getTail();
                }
                FocusTargetNode focusTargetNode3 = (FocusTargetNode) node;
                if (focusTargetNode3 == null && requestFocusForOwner(focusTargetNode)) {
                    focusTargetNode.setFocusState(FocusStateImpl.Active);
                    return requestFocusForChild(focusTargetNode, focusTargetNode2);
                }
                if (focusTargetNode3 == null || !requestFocusForChild(focusTargetNode3, focusTargetNode)) {
                    return false;
                }
                boolean zRequestFocusForChild = requestFocusForChild(focusTargetNode, focusTargetNode2);
                if (focusTargetNode.getFocusState() != FocusStateImpl.ActiveParent) {
                    throw new IllegalStateException("Deactivated node is focused");
                }
                if (zRequestFocusForChild) {
                    FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode3);
                }
                return zRequestFocusForChild;
            }
            requireActiveChild(focusTargetNode);
            if (clearChildFocus$default(focusTargetNode, false, false, 3, null) && grantFocus(focusTargetNode2)) {
                return true;
            }
        }
        return false;
    }

    private static final boolean requestFocusForOwner(FocusTargetNode focusTargetNode) {
        LayoutNode layoutNode;
        Owner owner;
        NodeCoordinator coordinator = focusTargetNode.getCoordinator();
        if (coordinator == null || (layoutNode = coordinator.getLayoutNode()) == null || (owner = layoutNode.getOwner()) == null) {
            throw new IllegalStateException("Owner not initialized.");
        }
        return owner.requestFocus();
    }

    private static final FocusTargetNode requireActiveChild(FocusTargetNode focusTargetNode) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
        if (activeChild != null) {
            return activeChild;
        }
        throw new IllegalArgumentException("ActiveParent with no focused child");
    }
}
