package androidx.compose.ui.viewinterop;

import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"Landroid/view/View;", "T", "Landroidx/compose/ui/node/LayoutNode;", "Landroidx/compose/ui/unit/Density;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/unit/Density;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AndroidView_androidKt$updateViewHolderParams$2 extends r implements p<LayoutNode, Density, t0> {
    public static final AndroidView_androidKt$updateViewHolderParams$2 INSTANCE = new AndroidView_androidKt$updateViewHolderParams$2();

    public AndroidView_androidKt$updateViewHolderParams$2() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((LayoutNode) obj, (Density) obj2);
        return t0.f22605a;
    }

    public final void invoke(LayoutNode layoutNode, Density density) {
        AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setDensity(density);
    }
}
