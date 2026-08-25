package androidx.compose.ui.platform;

import android.graphics.Matrix;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Landroidx/compose/ui/platform/DeviceRenderNode;", "rn", "Landroid/graphics/Matrix;", "matrix", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/DeviceRenderNode;Landroid/graphics/Matrix;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class RenderNodeLayer$Companion$getMatrix$1 extends kotlin.jvm.internal.r implements r7.p<DeviceRenderNode, Matrix, t0> {
    public static final RenderNodeLayer$Companion$getMatrix$1 INSTANCE = new RenderNodeLayer$Companion$getMatrix$1();

    public RenderNodeLayer$Companion$getMatrix$1() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((DeviceRenderNode) obj, (Matrix) obj2);
        return t0.f22605a;
    }

    public final void invoke(DeviceRenderNode deviceRenderNode, Matrix matrix) {
        deviceRenderNode.getMatrix(matrix);
    }
}
