package androidx.compose.ui.viewinterop;

import androidx.compose.ui.node.LayoutNode;
import b2.g;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"Landroid/view/View;", "T", "Landroidx/compose/ui/node/LayoutNode;", "Lb2/g;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/node/LayoutNode;Lb2/g;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AndroidView_androidKt$updateViewHolderParams$4 extends r implements p<LayoutNode, g, t0> {
    public static final AndroidView_androidKt$updateViewHolderParams$4 INSTANCE = new AndroidView_androidKt$updateViewHolderParams$4();

    public AndroidView_androidKt$updateViewHolderParams$4() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((LayoutNode) obj, (g) obj2);
        return t0.f22605a;
    }

    public final void invoke(LayoutNode layoutNode, g gVar) {
        AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setSavedStateRegistryOwner(gVar);
    }
}
