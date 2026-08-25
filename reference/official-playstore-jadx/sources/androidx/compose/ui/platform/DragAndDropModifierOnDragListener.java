package androidx.compose.ui.platform;

import android.view.DragEvent;
import android.view.View;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draganddrop.DragAndDropEvent;
import androidx.compose.ui.draganddrop.DragAndDropManager;
import androidx.compose.ui.draganddrop.DragAndDropModifierNode;
import androidx.compose.ui.draganddrop.DragAndDropNode;
import androidx.compose.ui.draganddrop.DragAndDropTransferData;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.ModifierNodeElement;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002Be\u0012\\\u0010\u0010\u001aX\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012$\u0012\"\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J;\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b \u0010!Rj\u0010\u0010\u001aX\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012$\u0012\"\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u001a\u0010*\u001a\u00020)8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006."}, d2 = {"Landroidx/compose/ui/platform/DragAndDropModifierOnDragListener;", "Landroid/view/View$OnDragListener;", "Landroidx/compose/ui/draganddrop/DragAndDropManager;", "Lkotlin/Function3;", "Landroidx/compose/ui/draganddrop/DragAndDropTransferData;", "Lx6/y;", ContentDisposition.Parameters.Name, "transferData", "Landroidx/compose/ui/geometry/Size;", "decorationSize", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "Lx6/n;", "drawDragDecoration", "", "startDrag", "<init>", "(Lr7/q;)V", "Landroid/view/View;", "view", "Landroid/view/DragEvent;", "event", "onDrag", "(Landroid/view/View;Landroid/view/DragEvent;)Z", "drag-12SF9DM", "(Landroidx/compose/ui/draganddrop/DragAndDropTransferData;JLr7/l;)Z", "drag", "Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;", "node", "registerNodeInterest", "(Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;)V", "isInterestedNode", "(Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;)Z", "Lr7/q;", "Landroidx/compose/ui/draganddrop/DragAndDropNode;", "rootDragAndDropNode", "Landroidx/compose/ui/draganddrop/DragAndDropNode;", "Lt/f;", "interestedNodes", "Lt/f;", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DragAndDropModifierOnDragListener implements View.OnDragListener, DragAndDropManager {
    private final r7.q<DragAndDropTransferData, Size, r7.l<? super DrawScope, t0>, Boolean> startDrag;
    private final DragAndDropNode rootDragAndDropNode = new DragAndDropNode(DragAndDropModifierOnDragListener$rootDragAndDropNode$1.INSTANCE);
    private final t.f interestedNodes = new t.f(0);
    private final Modifier modifier = new ModifierNodeElement<DragAndDropNode>() { // from class: androidx.compose.ui.platform.DragAndDropModifierOnDragListener$modifier$1
        @Override // androidx.compose.ui.node.ModifierNodeElement
        public boolean equals(Object other) {
            return other == this;
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public int hashCode() {
            return this.this$0.rootDragAndDropNode.hashCode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void inspectableProperties(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("RootDragAndDropNode");
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(DragAndDropNode node) {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public DragAndDropNode create() {
            return this.this$0.rootDragAndDropNode;
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    public DragAndDropModifierOnDragListener(r7.q<? super DragAndDropTransferData, ? super Size, ? super r7.l<? super DrawScope, t0>, Boolean> qVar) {
        this.startDrag = qVar;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropManager
    /* JADX INFO: renamed from: drag-12SF9DM */
    public boolean mo3121drag12SF9DM(DragAndDropTransferData transferData, long decorationSize, r7.l<? super DrawScope, t0> drawDragDecoration) {
        return ((Boolean) this.startDrag.invoke(transferData, Size.m3293boximpl(decorationSize), drawDragDecoration)).booleanValue();
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropManager
    public Modifier getModifier() {
        return this.modifier;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropManager
    public boolean isInterestedNode(DragAndDropModifierNode node) {
        return this.interestedNodes.contains(node);
    }

    @Override // android.view.View.OnDragListener
    public boolean onDrag(View view, DragEvent event) {
        DragAndDropEvent dragAndDropEvent = new DragAndDropEvent(event);
        switch (event.getAction()) {
            case 1:
                boolean zAcceptDragAndDropTransfer = this.rootDragAndDropNode.acceptDragAndDropTransfer(dragAndDropEvent);
                t.f fVar = this.interestedNodes;
                fVar.getClass();
                t.a aVar = new t.a(fVar);
                while (aVar.hasNext()) {
                    ((DragAndDropModifierNode) aVar.next()).onStarted(dragAndDropEvent);
                }
                break;
            case 2:
                this.rootDragAndDropNode.onMoved(dragAndDropEvent);
                break;
            case 4:
                this.rootDragAndDropNode.onEnded(dragAndDropEvent);
                break;
            case 5:
                this.rootDragAndDropNode.onEntered(dragAndDropEvent);
                break;
            case 6:
                this.rootDragAndDropNode.onExited(dragAndDropEvent);
                break;
        }
        return false;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropManager
    public void registerNodeInterest(DragAndDropModifierNode node) {
        this.interestedNodes.add(node);
    }
}
