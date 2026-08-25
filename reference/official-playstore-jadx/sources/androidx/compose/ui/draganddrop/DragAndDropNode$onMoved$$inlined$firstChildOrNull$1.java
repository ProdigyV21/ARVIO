package androidx.compose.ui.draganddrop;

import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.TraversableNode;
import kotlin.Metadata;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/ui/node/TraversableNode$Companion$TraverseDescendantsAction;", "child", "Landroidx/compose/ui/node/TraversableNode;", "invoke", "androidx/compose/ui/draganddrop/DragAndDropNode$Companion$firstChildOrNull$1"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class DragAndDropNode$onMoved$$inlined$firstChildOrNull$1 extends r implements l<TraversableNode, TraversableNode.Companion.TraverseDescendantsAction> {
    final /* synthetic */ DragAndDropEvent $event$inlined;
    final /* synthetic */ k0 $match;
    final /* synthetic */ DragAndDropNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DragAndDropNode$onMoved$$inlined$firstChildOrNull$1(k0 k0Var, DragAndDropNode dragAndDropNode, DragAndDropEvent dragAndDropEvent) {
        super(1);
        this.$match = k0Var;
        this.this$0 = dragAndDropNode;
        this.$event$inlined = dragAndDropEvent;
    }

    @Override // r7.l
    public final TraversableNode.Companion.TraverseDescendantsAction invoke(TraversableNode traversableNode) {
        if (traversableNode instanceof DragAndDropModifierNode) {
            DragAndDropModifierNode dragAndDropModifierNode = (DragAndDropModifierNode) traversableNode;
            if (DelegatableNodeKt.requireOwner(this.this$0).getDragAndDropManager().isInterestedNode(dragAndDropModifierNode) && DragAndDropNodeKt.m3124containsUv8p0NA(dragAndDropModifierNode, DragAndDrop_androidKt.getPositionInRoot(this.$event$inlined))) {
                this.$match.f19746i = traversableNode;
                return TraversableNode.Companion.TraverseDescendantsAction.CancelTraversal;
            }
        }
        return TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
    }
}
