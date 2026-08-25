package androidx.compose.material3;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/input/pointer/PointerInputChange;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SliderKt$rangeSliderPressDragModifier$1$1$1$finishInteraction$success$1 extends r implements l<PointerInputChange, t0> {
    final /* synthetic */ f0 $draggingStart;
    final /* synthetic */ RangeSliderState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SliderKt$rangeSliderPressDragModifier$1$1$1$finishInteraction$success$1(RangeSliderState rangeSliderState, f0 f0Var) {
        super(1);
        this.$state = rangeSliderState;
        this.$draggingStart = f0Var;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PointerInputChange) obj);
        return t0.f22605a;
    }

    public final void invoke(PointerInputChange pointerInputChange) {
        float fM3236getXimpl = Offset.m3236getXimpl(PointerEventKt.positionChange(pointerInputChange));
        RangeSliderState rangeSliderState = this.$state;
        boolean z = this.$draggingStart.f19738i;
        if (rangeSliderState.isRtl$material3_release()) {
            fM3236getXimpl = -fM3236getXimpl;
        }
        rangeSliderState.onDrag$material3_release(z, fM3236getXimpl);
    }
}
