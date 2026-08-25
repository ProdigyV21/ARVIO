package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.pointer.PointerInputChange;
import kotlin.Metadata;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "change", "", "over", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/input/pointer/PointerInputChange;F)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DragGestureDetectorKt$detectHorizontalDragGestures$5$drag$1 extends r implements p<PointerInputChange, Float, t0> {
    final /* synthetic */ h0 $overSlop;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DragGestureDetectorKt$detectHorizontalDragGestures$5$drag$1(h0 h0Var) {
        super(2);
        this.$overSlop = h0Var;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((PointerInputChange) obj, ((Number) obj2).floatValue());
        return t0.f22605a;
    }

    public final void invoke(PointerInputChange pointerInputChange, float f10) {
        pointerInputChange.consume();
        this.$overSlop.f19742i = f10;
    }
}
