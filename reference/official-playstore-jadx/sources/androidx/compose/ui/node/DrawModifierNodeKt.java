package androidx.compose.ui.node;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/node/DrawModifierNode;", "Lx6/t0;", "invalidateDraw", "(Landroidx/compose/ui/node/DrawModifierNode;)V", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DrawModifierNodeKt {
    public static final void invalidateDraw(DrawModifierNode drawModifierNode) {
        if (drawModifierNode.getNode().getIsAttached()) {
            DelegatableNodeKt.m4726requireCoordinator64DMado(drawModifierNode, NodeKind.m4843constructorimpl(1)).invalidateLayer();
        }
    }
}
