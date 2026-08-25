package androidx.compose.ui.layout;

import androidx.compose.ui.node.LayoutNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/node/LayoutNode;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/node/LayoutNode;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class LayoutKt$MultiMeasureLayout$1$1 extends r implements r7.l<LayoutNode, t0> {
    public static final LayoutKt$MultiMeasureLayout$1$1 INSTANCE = new LayoutKt$MultiMeasureLayout$1$1();

    public LayoutKt$MultiMeasureLayout$1$1() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((LayoutNode) obj);
        return t0.f22605a;
    }

    public final void invoke(LayoutNode layoutNode) {
        layoutNode.setCanMultiMeasure$ui_release(true);
    }
}
