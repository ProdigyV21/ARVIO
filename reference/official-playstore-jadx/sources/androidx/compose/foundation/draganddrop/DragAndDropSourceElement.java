package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import d7.d;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0083\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BI\u0012\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006\u0012'\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\u0002\b\u0006¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0015\u001a\u00020\u0005*\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J!\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J1\u0010\u0019\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\u0002\b\u0006HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJV\u0010\u001b\u001a\u00020\u00002\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u00062)\b\u0002\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\u0002\b\u0006HÆ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001e\u001a\u00020\u001dHÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010!\u001a\u00020 HÖ\u0001¢\u0006\u0004\b!\u0010\"J\u001a\u0010%\u001a\u00020$2\b\u0010#\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b%\u0010&R(\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010'\u001a\u0004\b(\u0010\u0018R8\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\u0002\b\u00068\u0006¢\u0006\f\n\u0004\b\f\u0010)\u001a\u0004\b*\u0010\u001a¨\u0006+"}, d2 = {"Landroidx/compose/foundation/draganddrop/DragAndDropSourceElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/draganddrop/DragAndDropSourceNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "Lx6/n;", "drawDragDecoration", "Lkotlin/Function2;", "Landroidx/compose/foundation/draganddrop/DragAndDropSourceScope;", "Ld7/d;", "", "dragAndDropSourceHandler", "<init>", "(Lr7/l;Lr7/p;)V", "create", "()Landroidx/compose/foundation/draganddrop/DragAndDropSourceNode;", "node", "update", "(Landroidx/compose/foundation/draganddrop/DragAndDropSourceNode;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "component1", "()Lr7/l;", "component2", "()Lr7/p;", "copy", "(Lr7/l;Lr7/p;)Landroidx/compose/foundation/draganddrop/DragAndDropSourceElement;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lr7/l;", "getDrawDragDecoration", "Lr7/p;", "getDragAndDropSourceHandler", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class DragAndDropSourceElement extends ModifierNodeElement<DragAndDropSourceNode> {
    private final p<DragAndDropSourceScope, d<? super t0>, Object> dragAndDropSourceHandler;
    private final l<DrawScope, t0> drawDragDecoration;

    /* JADX WARN: Multi-variable type inference failed */
    public DragAndDropSourceElement(l<? super DrawScope, t0> lVar, p<? super DragAndDropSourceScope, ? super d<? super t0>, ? extends Object> pVar) {
        this.drawDragDecoration = lVar;
        this.dragAndDropSourceHandler = pVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DragAndDropSourceElement copy$default(DragAndDropSourceElement dragAndDropSourceElement, l lVar, p pVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = dragAndDropSourceElement.drawDragDecoration;
        }
        if ((i10 & 2) != 0) {
            pVar = dragAndDropSourceElement.dragAndDropSourceHandler;
        }
        return dragAndDropSourceElement.copy(lVar, pVar);
    }

    public final l<DrawScope, t0> component1() {
        return this.drawDragDecoration;
    }

    public final p<DragAndDropSourceScope, d<? super t0>, Object> component2() {
        return this.dragAndDropSourceHandler;
    }

    public final DragAndDropSourceElement copy(l<? super DrawScope, t0> drawDragDecoration, p<? super DragAndDropSourceScope, ? super d<? super t0>, ? extends Object> dragAndDropSourceHandler) {
        return new DragAndDropSourceElement(drawDragDecoration, dragAndDropSourceHandler);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DragAndDropSourceElement)) {
            return false;
        }
        DragAndDropSourceElement dragAndDropSourceElement = (DragAndDropSourceElement) other;
        return kotlin.jvm.internal.p.a(this.drawDragDecoration, dragAndDropSourceElement.drawDragDecoration) && kotlin.jvm.internal.p.a(this.dragAndDropSourceHandler, dragAndDropSourceElement.dragAndDropSourceHandler);
    }

    public final p<DragAndDropSourceScope, d<? super t0>, Object> getDragAndDropSourceHandler() {
        return this.dragAndDropSourceHandler;
    }

    public final l<DrawScope, t0> getDrawDragDecoration() {
        return this.drawDragDecoration;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return this.dragAndDropSourceHandler.hashCode() + (this.drawDragDecoration.hashCode() * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("dragSource");
        inspectorInfo.getProperties().set("drawDragDecoration", this.drawDragDecoration);
        inspectorInfo.getProperties().set("dragAndDropSourceHandler", this.dragAndDropSourceHandler);
    }

    public String toString() {
        return "DragAndDropSourceElement(drawDragDecoration=" + this.drawDragDecoration + ", dragAndDropSourceHandler=" + this.dragAndDropSourceHandler + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public DragAndDropSourceNode create() {
        return new DragAndDropSourceNode(this.drawDragDecoration, this.dragAndDropSourceHandler);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(DragAndDropSourceNode node) {
        node.setDrawDragDecoration(this.drawDragDecoration);
        node.setDragAndDropSourceHandler(this.dragAndDropSourceHandler);
    }
}
