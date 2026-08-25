package androidx.compose.ui.draganddrop;

import androidx.compose.ui.node.TraversableNode;
import kotlin.Metadata;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/node/TraversableNode$Companion$TraverseDescendantsAction;", "child", "Landroidx/compose/ui/node/TraversableNode;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class DragAndDropNode$Companion$firstChildOrNull$1 extends r implements l<TraversableNode, TraversableNode.Companion.TraverseDescendantsAction> {
    final /* synthetic */ k0 $match;
    final /* synthetic */ l<DragAndDropModifierNode, Boolean> $predicate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DragAndDropNode$Companion$firstChildOrNull$1(l<? super DragAndDropModifierNode, Boolean> lVar, k0 k0Var) {
        super(1);
        this.$predicate = lVar;
        this.$match = k0Var;
    }

    @Override // r7.l
    public final TraversableNode.Companion.TraverseDescendantsAction invoke(TraversableNode traversableNode) {
        if (!(traversableNode instanceof DragAndDropModifierNode) || !((Boolean) this.$predicate.invoke(traversableNode)).booleanValue()) {
            return TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
        }
        this.$match.f19746i = traversableNode;
        return TraversableNode.Companion.TraverseDescendantsAction.CancelTraversal;
    }
}
