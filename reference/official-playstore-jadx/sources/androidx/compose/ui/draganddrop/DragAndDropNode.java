package androidx.compose.ui.draganddrop;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\n\b\u0000\u0018\u0000 02\u00020\u00012\u00020\u00022\u00020\u0003:\u00010B,\u0012#\u0010\n\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ;\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\u0002\b\u0015H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0005H\u0016¢\u0006\u0004\b \u0010\u001fJ\u0017\u0010!\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0005H\u0016¢\u0006\u0004\b!\u0010\u001fJ\u0017\u0010\"\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\"\u0010\u001fJ\u0017\u0010#\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0005H\u0016¢\u0006\u0004\b#\u0010\u001fJ\u0017\u0010$\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\u0005H\u0016¢\u0006\u0004\b$\u0010\u001dJ\u0017\u0010%\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0005H\u0016¢\u0006\u0004\b%\u0010\u001fR1\u0010\n\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010&R\u001a\u0010(\u001a\u00020'8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u0018\u0010,\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0018\u0010.\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00061"}, d2 = {"Landroidx/compose/ui/draganddrop/DragAndDropNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/draganddrop/DragAndDropEvent;", "Lx6/y;", ContentDisposition.Parameters.Name, "event", "Landroidx/compose/ui/draganddrop/DragAndDropTarget;", "onDragAndDropStart", "<init>", "(Lr7/l;)V", "Lx6/t0;", "onDetach", "()V", "Landroidx/compose/ui/draganddrop/DragAndDropTransferData;", "transferData", "Landroidx/compose/ui/geometry/Size;", "decorationSize", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/n;", "drawDragDecoration", "drag-12SF9DM", "(Landroidx/compose/ui/draganddrop/DragAndDropTransferData;JLr7/l;)V", "drag", "startEvent", "", "acceptDragAndDropTransfer", "(Landroidx/compose/ui/draganddrop/DragAndDropEvent;)Z", "onStarted", "(Landroidx/compose/ui/draganddrop/DragAndDropEvent;)V", "onEntered", "onMoved", "onChanged", "onExited", "onDrop", "onEnded", "Lr7/l;", "", "traverseKey", "Ljava/lang/Object;", "getTraverseKey", "()Ljava/lang/Object;", "lastChildDragAndDropModifierNode", "Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;", "thisDragAndDropTarget", "Landroidx/compose/ui/draganddrop/DragAndDropTarget;", "Companion", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DragAndDropNode extends Modifier.Node implements TraversableNode, DragAndDropModifierNode {
    private DragAndDropModifierNode lastChildDragAndDropModifierNode;
    private final l<DragAndDropEvent, DragAndDropTarget> onDragAndDropStart;
    private DragAndDropTarget thisDragAndDropTarget;
    private final Object traverseKey = Companion.DragAndDropTraversableKey.INSTANCE;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\nB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\b\u001a\u0004\u0018\u00010\u0004*\u00020\u00042\u0014\b\u0004\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0005H\u0082\b¢\u0006\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/draganddrop/DragAndDropNode$Companion;", "", "<init>", "()V", "Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;", "Lkotlin/Function1;", "", "predicate", "firstChildOrNull", "(Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;Lr7/l;)Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;", "DragAndDropTraversableKey", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/draganddrop/DragAndDropNode$Companion$DragAndDropTraversableKey;", "", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class DragAndDropTraversableKey {
            public static final DragAndDropTraversableKey INSTANCE = new DragAndDropTraversableKey();

            private DragAndDropTraversableKey() {
            }
        }

        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private final DragAndDropModifierNode firstChildOrNull(DragAndDropModifierNode dragAndDropModifierNode, l<? super DragAndDropModifierNode, Boolean> lVar) {
            if (!dragAndDropModifierNode.getNode().getIsAttached()) {
                return null;
            }
            k0 k0Var = new k0();
            TraversableNodeKt.traverseDescendants(dragAndDropModifierNode, DragAndDropTraversableKey.INSTANCE, new DragAndDropNode$Companion$firstChildOrNull$1(lVar, k0Var));
            return (DragAndDropModifierNode) k0Var.f19746i;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.draganddrop.DragAndDropNode$acceptDragAndDropTransfer$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "child", "Landroidx/compose/ui/draganddrop/DragAndDropNode;", "invoke", "(Landroidx/compose/ui/draganddrop/DragAndDropNode;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<DragAndDropNode, Boolean> {
        final /* synthetic */ f0 $handledByChild;
        final /* synthetic */ DragAndDropEvent $startEvent;
        final /* synthetic */ DragAndDropNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(f0 f0Var, DragAndDropEvent dragAndDropEvent, DragAndDropNode dragAndDropNode) {
            super(1);
            this.$handledByChild = f0Var;
            this.$startEvent = dragAndDropEvent;
            this.this$0 = dragAndDropNode;
        }

        @Override // r7.l
        public final Boolean invoke(DragAndDropNode dragAndDropNode) {
            f0 f0Var = this.$handledByChild;
            boolean z = f0Var.f19738i;
            boolean zAcceptDragAndDropTransfer = dragAndDropNode.acceptDragAndDropTransfer(this.$startEvent);
            DragAndDropNode dragAndDropNode2 = this.this$0;
            if (zAcceptDragAndDropTransfer) {
                DelegatableNodeKt.requireOwner(dragAndDropNode2).getDragAndDropManager().registerNodeInterest(dragAndDropNode);
            }
            f0Var.f19738i = z | zAcceptDragAndDropTransfer;
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.draganddrop.DragAndDropNode$onEnded$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "child", "Landroidx/compose/ui/draganddrop/DragAndDropNode;", "invoke", "(Landroidx/compose/ui/draganddrop/DragAndDropNode;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<DragAndDropNode, Boolean> {
        final /* synthetic */ DragAndDropEvent $event;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DragAndDropEvent dragAndDropEvent) {
            super(1);
            this.$event = dragAndDropEvent;
        }

        @Override // r7.l
        public final Boolean invoke(DragAndDropNode dragAndDropNode) {
            dragAndDropNode.onEnded(this.$event);
            return Boolean.TRUE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DragAndDropNode(l<? super DragAndDropEvent, ? extends DragAndDropTarget> lVar) {
        this.onDragAndDropStart = lVar;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropModifierNode
    public boolean acceptDragAndDropTransfer(DragAndDropEvent startEvent) {
        if (!getIsAttached()) {
            return false;
        }
        if (this.thisDragAndDropTarget != null) {
            throw new IllegalStateException("DragAndDropTarget self reference must be null at the start of a drag and drop session");
        }
        this.thisDragAndDropTarget = (DragAndDropTarget) this.onDragAndDropStart.invoke(startEvent);
        f0 f0Var = new f0();
        TraversableNodeKt.traverseChildren(this, new AnonymousClass2(f0Var, startEvent, this));
        return f0Var.f19738i || this.thisDragAndDropTarget != null;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropModifierNode
    /* JADX INFO: renamed from: drag-12SF9DM */
    public void mo3122drag12SF9DM(DragAndDropTransferData transferData, long decorationSize, l<? super DrawScope, t0> drawDragDecoration) {
        DelegatableNodeKt.requireOwner(this).getDragAndDropManager().mo3121drag12SF9DM(transferData, decorationSize, drawDragDecoration);
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public Object getTraverseKey() {
        return this.traverseKey;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public void onChanged(DragAndDropEvent event) {
        DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
        if (dragAndDropTarget != null) {
            dragAndDropTarget.onChanged(event);
            return;
        }
        DragAndDropModifierNode dragAndDropModifierNode = this.lastChildDragAndDropModifierNode;
        if (dragAndDropModifierNode != null) {
            dragAndDropModifierNode.onChanged(event);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.thisDragAndDropTarget = null;
        this.lastChildDragAndDropModifierNode = null;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public boolean onDrop(DragAndDropEvent event) {
        DragAndDropModifierNode dragAndDropModifierNode = this.lastChildDragAndDropModifierNode;
        if (dragAndDropModifierNode != null) {
            return dragAndDropModifierNode.onDrop(event);
        }
        DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
        if (dragAndDropTarget != null) {
            return dragAndDropTarget.onDrop(event);
        }
        return false;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public void onEnded(DragAndDropEvent event) {
        if (getNode().getIsAttached()) {
            TraversableNodeKt.traverseChildren(this, new AnonymousClass1(event));
            DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
            if (dragAndDropTarget != null) {
                dragAndDropTarget.onEnded(event);
            }
            this.thisDragAndDropTarget = null;
            this.lastChildDragAndDropModifierNode = null;
        }
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public void onEntered(DragAndDropEvent event) {
        DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
        if (dragAndDropTarget != null) {
            dragAndDropTarget.onEntered(event);
            return;
        }
        DragAndDropModifierNode dragAndDropModifierNode = this.lastChildDragAndDropModifierNode;
        if (dragAndDropModifierNode != null) {
            dragAndDropModifierNode.onEntered(event);
        }
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public void onExited(DragAndDropEvent event) {
        DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
        if (dragAndDropTarget != null) {
            dragAndDropTarget.onExited(event);
        }
        DragAndDropModifierNode dragAndDropModifierNode = this.lastChildDragAndDropModifierNode;
        if (dragAndDropModifierNode != null) {
            dragAndDropModifierNode.onExited(event);
        }
        this.lastChildDragAndDropModifierNode = null;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public void onMoved(DragAndDropEvent event) {
        DragAndDropModifierNode dragAndDropModifierNode;
        DragAndDropModifierNode dragAndDropModifierNode2 = this.lastChildDragAndDropModifierNode;
        if (dragAndDropModifierNode2 != null && DragAndDropNodeKt.m3124containsUv8p0NA(dragAndDropModifierNode2, DragAndDrop_androidKt.getPositionInRoot(event))) {
            dragAndDropModifierNode = dragAndDropModifierNode2;
        } else if (getNode().getIsAttached()) {
            k0 k0Var = new k0();
            TraversableNodeKt.traverseDescendants(this, Companion.DragAndDropTraversableKey.INSTANCE, new DragAndDropNode$onMoved$$inlined$firstChildOrNull$1(k0Var, this, event));
            dragAndDropModifierNode = (DragAndDropModifierNode) k0Var.f19746i;
        } else {
            dragAndDropModifierNode = null;
        }
        if (dragAndDropModifierNode != null && dragAndDropModifierNode2 == null) {
            DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
            if (dragAndDropTarget != null) {
                dragAndDropTarget.onExited(event);
            }
            DragAndDropNodeKt.dispatchEntered(dragAndDropModifierNode, event);
        } else if (dragAndDropModifierNode == null && dragAndDropModifierNode2 != null) {
            dragAndDropModifierNode2.onExited(event);
            DragAndDropTarget dragAndDropTarget2 = this.thisDragAndDropTarget;
            if (dragAndDropTarget2 != null) {
                DragAndDropNodeKt.dispatchEntered(dragAndDropTarget2, event);
            }
        } else if (!p.a(dragAndDropModifierNode, dragAndDropModifierNode2)) {
            if (dragAndDropModifierNode2 != null) {
                dragAndDropModifierNode2.onExited(event);
            }
            if (dragAndDropModifierNode != null) {
                DragAndDropNodeKt.dispatchEntered(dragAndDropModifierNode, event);
            }
        } else if (dragAndDropModifierNode != null) {
            dragAndDropModifierNode.onMoved(event);
        } else {
            DragAndDropTarget dragAndDropTarget3 = this.thisDragAndDropTarget;
            if (dragAndDropTarget3 != null) {
                dragAndDropTarget3.onMoved(event);
            }
        }
        this.lastChildDragAndDropModifierNode = dragAndDropModifierNode;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public void onStarted(DragAndDropEvent event) {
        DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
        if (dragAndDropTarget != null) {
            dragAndDropTarget.onStarted(event);
            return;
        }
        DragAndDropModifierNode dragAndDropModifierNode = this.lastChildDragAndDropModifierNode;
        if (dragAndDropModifierNode != null) {
            dragAndDropModifierNode.onStarted(event);
        }
    }
}
