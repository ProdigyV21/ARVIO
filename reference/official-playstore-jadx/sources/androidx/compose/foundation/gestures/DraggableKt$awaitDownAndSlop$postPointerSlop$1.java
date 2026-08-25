package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.compose.ui.input.pointer.util.VelocityTrackerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.j0;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "event", "Landroidx/compose/ui/geometry/Offset;", "offset", "Lx6/t0;", "invoke-Uv8p0NA", "(Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DraggableKt$awaitDownAndSlop$postPointerSlop$1 extends r implements p<PointerInputChange, Offset, t0> {
    final /* synthetic */ j0 $initialDelta;
    final /* synthetic */ VelocityTracker $velocityTracker;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DraggableKt$awaitDownAndSlop$postPointerSlop$1(VelocityTracker velocityTracker, j0 j0Var) {
        super(2);
        this.$velocityTracker = velocityTracker;
        this.$initialDelta = j0Var;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        m351invokeUv8p0NA((PointerInputChange) obj, ((Offset) obj2).getPackedValue());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
    public final void m351invokeUv8p0NA(PointerInputChange pointerInputChange, long j10) {
        VelocityTrackerKt.addPointerInputChange(this.$velocityTracker, pointerInputChange);
        pointerInputChange.consume();
        this.$initialDelta.f19745i = j10;
    }
}
