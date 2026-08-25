package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.compose.ui.input.pointer.util.VelocityTrackerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import ma.x;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "event", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/input/pointer/PointerInputChange;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DraggableKt$awaitDrag$2 extends r implements l<PointerInputChange, t0> {
    final /* synthetic */ x<DragEvent> $channel;
    final /* synthetic */ boolean $reverseDirection;
    final /* synthetic */ VelocityTracker $velocityTracker;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DraggableKt$awaitDrag$2(VelocityTracker velocityTracker, x<? super DragEvent> xVar, boolean z) {
        super(1);
        this.$velocityTracker = velocityTracker;
        this.$channel = xVar;
        this.$reverseDirection = z;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PointerInputChange) obj);
        return t0.f22605a;
    }

    public final void invoke(PointerInputChange pointerInputChange) {
        VelocityTrackerKt.addPointerInputChange(this.$velocityTracker, pointerInputChange);
        if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
            return;
        }
        long jPositionChange = PointerEventKt.positionChange(pointerInputChange);
        pointerInputChange.consume();
        x<DragEvent> xVar = this.$channel;
        if (this.$reverseDirection) {
            jPositionChange = Offset.m3243timestuRUvjQ(jPositionChange, -1.0f);
        }
        xVar.mo6685trySendJP2dKIU(new DragEvent.DragDelta(jPositionChange, null));
    }
}
