package androidx.compose.ui.layout;

import androidx.compose.ui.node.LayoutNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/node/LayoutNode;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/node/LayoutNode;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TestModifierUpdaterKt$TestModifierUpdaterLayout$1$1 extends r implements r7.l<LayoutNode, t0> {
    final /* synthetic */ r7.l<TestModifierUpdater, t0> $onAttached;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TestModifierUpdaterKt$TestModifierUpdaterLayout$1$1(r7.l<? super TestModifierUpdater, t0> lVar) {
        super(1);
        this.$onAttached = lVar;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((LayoutNode) obj);
        return t0.f22605a;
    }

    public final void invoke(LayoutNode layoutNode) {
        this.$onAttached.invoke(new TestModifierUpdater(layoutNode));
    }
}
