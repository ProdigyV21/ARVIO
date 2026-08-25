package androidx.compose.ui.graphics.vector;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VNode;", "node", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/vector/VNode;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class GroupComponent$wrappedListener$1 extends r implements l<VNode, t0> {
    final /* synthetic */ GroupComponent this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GroupComponent$wrappedListener$1(GroupComponent groupComponent) {
        super(1);
        this.this$0 = groupComponent;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((VNode) obj);
        return t0.f22605a;
    }

    public final void invoke(VNode vNode) {
        this.this$0.markTintForVNode(vNode);
        l<VNode, t0> invalidateListener$ui_release = this.this$0.getInvalidateListener$ui_release();
        if (invalidateListener$ui_release != null) {
            invalidateListener$ui_release.invoke(vNode);
        }
    }
}
