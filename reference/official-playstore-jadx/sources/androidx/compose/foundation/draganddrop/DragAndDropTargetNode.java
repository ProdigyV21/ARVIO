package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.draganddrop.DragAndDropEvent;
import androidx.compose.ui.draganddrop.DragAndDropNodeKt;
import androidx.compose.ui.draganddrop.DragAndDropTarget;
import androidx.compose.ui.node.DelegatingNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0003\u0018\u00002\u00020\u0001B2\u0012!\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0002\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fR=\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/draganddrop/DragAndDropTargetNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/draganddrop/DragAndDropEvent;", "Lx6/y;", ContentDisposition.Parameters.Name, "event", "", "shouldStartDragAndDrop", "Landroidx/compose/ui/draganddrop/DragAndDropTarget;", "target", "<init>", "(Lr7/l;Landroidx/compose/ui/draganddrop/DragAndDropTarget;)V", "Lr7/l;", "getShouldStartDragAndDrop", "()Lr7/l;", "setShouldStartDragAndDrop", "(Lr7/l;)V", "Landroidx/compose/ui/draganddrop/DragAndDropTarget;", "getTarget", "()Landroidx/compose/ui/draganddrop/DragAndDropTarget;", "setTarget", "(Landroidx/compose/ui/draganddrop/DragAndDropTarget;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DragAndDropTargetNode extends DelegatingNode {
    private l<? super DragAndDropEvent, Boolean> shouldStartDragAndDrop;
    private DragAndDropTarget target;

    public DragAndDropTargetNode(l<? super DragAndDropEvent, Boolean> lVar, DragAndDropTarget dragAndDropTarget) {
        this.shouldStartDragAndDrop = lVar;
        this.target = dragAndDropTarget;
        delegate(DragAndDropNodeKt.DragAndDropModifierNode(lVar, dragAndDropTarget));
    }

    public final l<DragAndDropEvent, Boolean> getShouldStartDragAndDrop() {
        return this.shouldStartDragAndDrop;
    }

    public final DragAndDropTarget getTarget() {
        return this.target;
    }

    public final void setShouldStartDragAndDrop(l<? super DragAndDropEvent, Boolean> lVar) {
        this.shouldStartDragAndDrop = lVar;
    }

    public final void setTarget(DragAndDropTarget dragAndDropTarget) {
        this.target = dragAndDropTarget;
    }
}
