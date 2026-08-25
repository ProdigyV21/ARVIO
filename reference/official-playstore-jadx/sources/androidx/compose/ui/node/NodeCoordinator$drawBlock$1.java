package androidx.compose.ui.node;

import androidx.compose.ui.graphics.Canvas;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/graphics/Canvas;", "canvas", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/Canvas;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class NodeCoordinator$drawBlock$1 extends r implements l<Canvas, t0> {
    final /* synthetic */ NodeCoordinator this$0;

    /* JADX INFO: renamed from: androidx.compose.ui.node.NodeCoordinator$drawBlock$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.a<t0> {
        final /* synthetic */ Canvas $canvas;
        final /* synthetic */ NodeCoordinator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(NodeCoordinator nodeCoordinator, Canvas canvas) {
            super(0);
            this.this$0 = nodeCoordinator;
            this.$canvas = canvas;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m4834invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m4834invoke() {
            this.this$0.drawContainedDrawModifiers(this.$canvas);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NodeCoordinator$drawBlock$1(NodeCoordinator nodeCoordinator) {
        super(1);
        this.this$0 = nodeCoordinator;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Canvas) obj);
        return t0.f22605a;
    }

    public final void invoke(Canvas canvas) {
        if (!this.this$0.getLayoutNode().isPlaced()) {
            this.this$0.lastLayerDrawingWasSkipped = true;
        } else {
            this.this$0.getSnapshotObserver().observeReads$ui_release(this.this$0, NodeCoordinator.onCommitAffectingLayer, new AnonymousClass1(this.this$0, canvas));
            this.this$0.lastLayerDrawingWasSkipped = false;
        }
    }
}
