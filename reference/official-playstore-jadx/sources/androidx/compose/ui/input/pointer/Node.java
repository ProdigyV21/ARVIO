package androidx.compose.ui.input.pointer;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.util.PointerIdArray;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.PointerInputModifierNode;
import java.util.List;
import kotlin.Metadata;
import t.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\n\u001a\u00020\t2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001e\u0010\u0011\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u000fH\u0082\b¢\u0006\u0004\b\u0011\u0010\u0012J5\u0010\u001b\u001a\u00020\t2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ5\u0010\u001f\u001a\u00020\t2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001f\u0010\u001cJ\u000f\u0010 \u001a\u00020\fH\u0016¢\u0006\u0004\b \u0010\u000eJ\r\u0010!\u001a\u00020\f¢\u0006\u0004\b!\u0010\u000eJ\u0017\u0010\"\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\"\u0010#J\u000f\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b%\u0010&R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010+\u001a\u00020*8\u0006¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0018\u00101\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u0018\u00103\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u0016\u00105\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u00106R\u0016\u00107\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u00106R\u0016\u00108\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b8\u00106R\u001d\u0010;\u001a\b\u0012\u0004\u0012\u00020:098\u0006¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>¨\u0006?"}, d2 = {"Landroidx/compose/ui/input/pointer/Node;", "Landroidx/compose/ui/input/pointer/NodeParent;", "Landroidx/compose/ui/Modifier$Node;", "modifierNode", "<init>", "(Landroidx/compose/ui/Modifier$Node;)V", "Landroidx/compose/ui/input/pointer/PointerEvent;", "oldEvent", "newEvent", "", "hasPositionChanged", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEvent;)Z", "Lx6/t0;", "clearCache", "()V", "Lkotlin/Function0;", "block", "dispatchIfNeeded", "(Lr7/a;)Z", "Lt/p;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "changes", "Landroidx/compose/ui/layout/LayoutCoordinates;", "parentCoordinates", "Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "internalPointerEvent", "isInBounds", "dispatchMainEventPass", "(Lt/p;Landroidx/compose/ui/layout/LayoutCoordinates;Landroidx/compose/ui/input/pointer/InternalPointerEvent;Z)Z", "dispatchFinalEventPass", "(Landroidx/compose/ui/input/pointer/InternalPointerEvent;)Z", "buildCache", "dispatchCancel", "markIsIn", "cleanUpHits", "(Landroidx/compose/ui/input/pointer/InternalPointerEvent;)V", "", "toString", "()Ljava/lang/String;", "Landroidx/compose/ui/Modifier$Node;", "getModifierNode", "()Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/input/pointer/util/PointerIdArray;", "pointerIds", "Landroidx/compose/ui/input/pointer/util/PointerIdArray;", "getPointerIds", "()Landroidx/compose/ui/input/pointer/util/PointerIdArray;", "relevantChanges", "Lt/p;", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "wasIn", "Z", "isIn", "hasExited", "Landroidx/compose/runtime/collection/MutableVector;", "", "vec", "Landroidx/compose/runtime/collection/MutableVector;", "getVec", "()Landroidx/compose/runtime/collection/MutableVector;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Node extends NodeParent {
    public static final int $stable = 8;
    private LayoutCoordinates coordinates;
    private final Modifier.Node modifierNode;
    private PointerEvent pointerEvent;
    private boolean wasIn;
    private final PointerIdArray pointerIds = new PointerIdArray();
    private final p relevantChanges = new p(2);
    private boolean isIn = true;
    private boolean hasExited = true;
    private final MutableVector<Long> vec = new MutableVector<>(new Long[16], 0);

    public Node(Modifier.Node node) {
        this.modifierNode = node;
    }

    private final void clearCache() {
        this.relevantChanges.b();
        this.coordinates = null;
    }

    private final boolean dispatchIfNeeded(r7.a<t0> block) {
        if (this.relevantChanges.h() == 0 || !this.modifierNode.getIsAttached()) {
            return false;
        }
        block.invoke();
        return true;
    }

    private final boolean hasPositionChanged(PointerEvent oldEvent, PointerEvent newEvent) {
        if (oldEvent == null || oldEvent.getChanges().size() != newEvent.getChanges().size()) {
            return true;
        }
        int size = newEvent.getChanges().size();
        for (int i10 = 0; i10 < size; i10++) {
            if (!Offset.m3233equalsimpl0(oldEvent.getChanges().get(i10).getPosition(), newEvent.getChanges().get(i10).getPosition())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:98:0x022e  */
    /* JADX WARN: Type inference failed for: r5v24 */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean buildCache(t.p r38, androidx.compose.ui.layout.LayoutCoordinates r39, androidx.compose.ui.input.pointer.InternalPointerEvent r40, boolean r41) {
        /*
            Method dump skipped, instruction units count: 657
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.Node.buildCache(t.p, androidx.compose.ui.layout.LayoutCoordinates, androidx.compose.ui.input.pointer.InternalPointerEvent, boolean):boolean");
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void cleanUpHits(InternalPointerEvent internalPointerEvent) {
        super.cleanUpHits(internalPointerEvent);
        PointerEvent pointerEvent = this.pointerEvent;
        if (pointerEvent == null) {
            return;
        }
        this.wasIn = this.isIn;
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int size = changes.size();
        for (int i10 = 0; i10 < size; i10++) {
            PointerInputChange pointerInputChange = changes.get(i10);
            if (!pointerInputChange.getPressed() && (!internalPointerEvent.m4447issuesEnterExitEvent0FcD4WY(pointerInputChange.getId()) || !this.isIn)) {
                this.pointerIds.m4601remove0FcD4WY(pointerInputChange.getId());
            }
        }
        this.isIn = false;
        this.hasExited = PointerEventType.m4467equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m4472getExit7fucELk());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7 */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void dispatchCancel() {
        MutableVector<Node> children = getChildren();
        int size = children.getSize();
        if (size > 0) {
            Node[] content = children.getContent();
            int i10 = 0;
            do {
                content[i10].dispatchCancel();
                i10++;
            } while (i10 < size);
        }
        Modifier.Node nodePop = this.modifierNode;
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(16);
        MutableVector mutableVector = null;
        while (nodePop != 0) {
            if (nodePop instanceof PointerInputModifierNode) {
                ((PointerInputModifierNode) nodePop).onCancelPointerInput();
            } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                int i11 = 0;
                nodePop = nodePop;
                while (delegate != null) {
                    if ((delegate.getKindSet() & iM4843constructorimpl) != 0) {
                        i11++;
                        if (i11 == 1) {
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
                if (i11 == 1) {
                }
            }
            nodePop = DelegatableNodeKt.pop(mutableVector);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v6 */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean dispatchFinalEventPass(InternalPointerEvent internalPointerEvent) {
        MutableVector<Node> children;
        int size;
        boolean z = false;
        int i10 = 0;
        z = false;
        if (this.relevantChanges.h() != 0 && this.modifierNode.getIsAttached()) {
            PointerEvent pointerEvent = this.pointerEvent;
            long jMo4638getSizeYbymL2g = this.coordinates.mo4638getSizeYbymL2g();
            Modifier.Node nodePop = this.modifierNode;
            int iM4843constructorimpl = NodeKind.m4843constructorimpl(16);
            MutableVector mutableVector = null;
            while (nodePop != 0) {
                if (nodePop instanceof PointerInputModifierNode) {
                    ((PointerInputModifierNode) nodePop).mo148onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Final, jMo4638getSizeYbymL2g);
                } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                    Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                    int i11 = 0;
                    nodePop = nodePop;
                    while (delegate != null) {
                        if ((delegate.getKindSet() & iM4843constructorimpl) != 0) {
                            i11++;
                            if (i11 == 1) {
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
                    if (i11 == 1) {
                    }
                }
                nodePop = DelegatableNodeKt.pop(mutableVector);
            }
            if (this.modifierNode.getIsAttached() && (size = (children = getChildren()).getSize()) > 0) {
                Node[] content = children.getContent();
                do {
                    content[i10].dispatchFinalEventPass(internalPointerEvent);
                    i10++;
                } while (i10 < size);
            }
            z = true;
        }
        cleanUpHits(internalPointerEvent);
        clearCache();
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v9 */
    /* JADX WARN: Type inference failed for: r2v18 */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean dispatchMainEventPass(p changes, LayoutCoordinates parentCoordinates, InternalPointerEvent internalPointerEvent, boolean isInBounds) {
        MutableVector<Node> children;
        int size;
        if (this.relevantChanges.h() == 0 || !this.modifierNode.getIsAttached()) {
            return false;
        }
        PointerEvent pointerEvent = this.pointerEvent;
        long jMo4638getSizeYbymL2g = this.coordinates.mo4638getSizeYbymL2g();
        Modifier.Node nodePop = this.modifierNode;
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(16);
        MutableVector mutableVector = null;
        while (nodePop != 0) {
            if (nodePop instanceof PointerInputModifierNode) {
                ((PointerInputModifierNode) nodePop).mo148onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Initial, jMo4638getSizeYbymL2g);
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
        if (this.modifierNode.getIsAttached() && (size = (children = getChildren()).getSize()) > 0) {
            Node[] content = children.getContent();
            int i11 = 0;
            do {
                content[i11].dispatchMainEventPass(this.relevantChanges, this.coordinates, internalPointerEvent, isInBounds);
                i11++;
            } while (i11 < size);
        }
        if (this.modifierNode.getIsAttached()) {
            Modifier.Node nodePop2 = this.modifierNode;
            int iM4843constructorimpl2 = NodeKind.m4843constructorimpl(16);
            MutableVector mutableVector2 = null;
            while (nodePop2 != 0) {
                if (nodePop2 instanceof PointerInputModifierNode) {
                    ((PointerInputModifierNode) nodePop2).mo148onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Main, jMo4638getSizeYbymL2g);
                } else if ((nodePop2.getKindSet() & iM4843constructorimpl2) != 0 && (nodePop2 instanceof DelegatingNode)) {
                    Modifier.Node delegate2 = ((DelegatingNode) nodePop2).getDelegate();
                    int i12 = 0;
                    nodePop2 = nodePop2;
                    while (delegate2 != null) {
                        if ((delegate2.getKindSet() & iM4843constructorimpl2) != 0) {
                            i12++;
                            if (i12 == 1) {
                                nodePop2 = delegate2;
                            } else {
                                if (mutableVector2 == null) {
                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (nodePop2 != 0) {
                                    mutableVector2.add(nodePop2);
                                    nodePop2 = 0;
                                }
                                mutableVector2.add(delegate2);
                            }
                        }
                        delegate2 = delegate2.getChild();
                        nodePop2 = nodePop2;
                    }
                    if (i12 == 1) {
                    }
                }
                nodePop2 = DelegatableNodeKt.pop(mutableVector2);
            }
        }
        return true;
    }

    public final Modifier.Node getModifierNode() {
        return this.modifierNode;
    }

    public final PointerIdArray getPointerIds() {
        return this.pointerIds;
    }

    public final MutableVector<Long> getVec() {
        return this.vec;
    }

    public final void markIsIn() {
        this.isIn = true;
    }

    public String toString() {
        return "Node(pointerInputFilter=" + this.modifierNode + ", children=" + getChildren() + ", pointerIds=" + this.pointerIds + ')';
    }
}
