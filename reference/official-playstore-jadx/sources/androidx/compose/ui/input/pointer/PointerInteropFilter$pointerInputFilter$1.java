package androidx.compose.ui.input.pointer;

import android.os.SystemClock;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInteropFilter;
import androidx.compose.ui.layout.LayoutCoordinates;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0007\u0010\bJ*\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0004R\u0016\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0017\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0018"}, d2 = {"androidx/compose/ui/input/pointer/PointerInteropFilter$pointerInputFilter$1", "Landroidx/compose/ui/input/pointer/PointerInputFilter;", "Lx6/t0;", "reset", "()V", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pointerEvent", "dispatchToView", "(Landroidx/compose/ui/input/pointer/PointerEvent;)V", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "pass", "Landroidx/compose/ui/unit/IntSize;", "bounds", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onPointerEvent", "onCancel", "Landroidx/compose/ui/input/pointer/PointerInteropFilter$DispatchToViewState;", "state", "Landroidx/compose/ui/input/pointer/PointerInteropFilter$DispatchToViewState;", "", "getShareWithSiblings", "()Z", "shareWithSiblings", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PointerInteropFilter$pointerInputFilter$1 extends PointerInputFilter {
    private PointerInteropFilter.DispatchToViewState state = PointerInteropFilter.DispatchToViewState.Unknown;
    final /* synthetic */ PointerInteropFilter this$0;

    public PointerInteropFilter$pointerInputFilter$1(PointerInteropFilter pointerInteropFilter) {
        this.this$0 = pointerInteropFilter;
    }

    private final void dispatchToView(PointerEvent pointerEvent) {
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int size = changes.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (changes.get(i10).isConsumed()) {
                if (this.state == PointerInteropFilter.DispatchToViewState.Dispatching) {
                    LayoutCoordinates layoutCoordinates = getLayoutCoordinates();
                    if (layoutCoordinates == null) {
                        throw new IllegalStateException("layoutCoordinates not set");
                    }
                    PointerInteropUtils_androidKt.m4564toCancelMotionEventScoped4ec7I(pointerEvent, layoutCoordinates.mo4640localToRootMKHz9U(Offset.INSTANCE.m3252getZeroF1C5BW0()), new PointerInteropFilter$pointerInputFilter$1$dispatchToView$2(this.this$0));
                }
                this.state = PointerInteropFilter.DispatchToViewState.NotDispatching;
                return;
            }
        }
        LayoutCoordinates layoutCoordinates2 = getLayoutCoordinates();
        if (layoutCoordinates2 == null) {
            throw new IllegalStateException("layoutCoordinates not set");
        }
        PointerInteropUtils_androidKt.m4565toMotionEventScoped4ec7I(pointerEvent, layoutCoordinates2.mo4640localToRootMKHz9U(Offset.INSTANCE.m3252getZeroF1C5BW0()), new PointerInteropFilter$pointerInputFilter$1$dispatchToView$3(this, this.this$0));
        if (this.state == PointerInteropFilter.DispatchToViewState.Dispatching) {
            int size2 = changes.size();
            for (int i11 = 0; i11 < size2; i11++) {
                changes.get(i11).consume();
            }
            InternalPointerEvent internalPointerEvent = pointerEvent.getInternalPointerEvent();
            if (internalPointerEvent == null) {
                return;
            }
            internalPointerEvent.setSuppressMovementConsumption(!this.this$0.getDisallowIntercept());
        }
    }

    private final void reset() {
        this.state = PointerInteropFilter.DispatchToViewState.Unknown;
        this.this$0.setDisallowIntercept$ui_release(false);
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputFilter
    public boolean getShareWithSiblings() {
        return true;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputFilter
    public void onCancel() {
        if (this.state == PointerInteropFilter.DispatchToViewState.Dispatching) {
            PointerInteropUtils_androidKt.emptyCancelMotionEventScope(SystemClock.uptimeMillis(), new PointerInteropFilter$pointerInputFilter$1$onCancel$1(this.this$0));
            reset();
        }
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputFilter
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo4542onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        boolean z;
        List<PointerInputChange> changes = pointerEvent.getChanges();
        if (this.this$0.getDisallowIntercept()) {
            z = true;
            break;
        }
        int size = changes.size();
        for (int i10 = 0; i10 < size; i10++) {
            PointerInputChange pointerInputChange = changes.get(i10);
            if (PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange) || PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
                z = true;
                break;
            }
        }
        z = false;
        if (this.state != PointerInteropFilter.DispatchToViewState.NotDispatching) {
            if (pass == PointerEventPass.Initial && z) {
                dispatchToView(pointerEvent);
            }
            if (pass == PointerEventPass.Final && !z) {
                dispatchToView(pointerEvent);
            }
        }
        if (pass == PointerEventPass.Final) {
            int size2 = changes.size();
            for (int i11 = 0; i11 < size2; i11++) {
                if (!PointerEventKt.changedToUpIgnoreConsumed(changes.get(i11))) {
                    return;
                }
            }
            reset();
        }
    }
}
