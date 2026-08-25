package androidx.compose.ui.viewinterop;

import androidx.compose.ui.node.LayoutNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00020\u00022\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"Landroid/view/View;", "T", "Landroidx/compose/ui/node/LayoutNode;", "Lkotlin/Function1;", "Lx6/t0;", "it", "invoke", "(Landroidx/compose/ui/node/LayoutNode;Lr7/l;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AndroidView_androidKt$AndroidView$2$1<T> extends r implements p<LayoutNode, l<? super T, ? extends t0>, t0> {
    public static final AndroidView_androidKt$AndroidView$2$1 INSTANCE = new AndroidView_androidKt$AndroidView$2$1();

    public AndroidView_androidKt$AndroidView$2$1() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((LayoutNode) obj, (l) obj2);
        return t0.f22605a;
    }

    public final void invoke(LayoutNode layoutNode, l<? super T, t0> lVar) {
        AndroidView_androidKt.requireViewFactoryHolder(layoutNode).setResetBlock(lVar);
    }
}
