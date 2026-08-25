package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.NodeCoordinator;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class NodeCoordinator$hit$1 extends r implements r7.a<t0> {
    final /* synthetic */ HitTestResult $hitTestResult;
    final /* synthetic */ NodeCoordinator.HitTestSource $hitTestSource;
    final /* synthetic */ boolean $isInLayer;
    final /* synthetic */ boolean $isTouchEvent;
    final /* synthetic */ long $pointerPosition;
    final /* synthetic */ Modifier.Node $this_hit;
    final /* synthetic */ NodeCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NodeCoordinator$hit$1(NodeCoordinator nodeCoordinator, Modifier.Node node, NodeCoordinator.HitTestSource hitTestSource, long j10, HitTestResult hitTestResult, boolean z, boolean z5) {
        super(0);
        this.this$0 = nodeCoordinator;
        this.$this_hit = node;
        this.$hitTestSource = hitTestSource;
        this.$pointerPosition = j10;
        this.$hitTestResult = hitTestResult;
        this.$isTouchEvent = z;
        this.$isInLayer = z5;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m4835invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m4835invoke() {
        this.this$0.m4811hit1hIXUjU(NodeCoordinatorKt.m4841nextUntilhw7D004(this.$this_hit, this.$hitTestSource.mo4833entityTypeOLwlOKw(), NodeKind.m4843constructorimpl(2)), this.$hitTestSource, this.$pointerPosition, this.$hitTestResult, this.$isTouchEvent, this.$isInLayer);
    }
}
