package androidx.compose.material3;

import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SliderDefaults$Track$3$1 extends r implements l<DrawScope, t0> {
    final /* synthetic */ long $activeTickColor;
    final /* synthetic */ long $activeTrackColor;
    final /* synthetic */ long $inactiveTickColor;
    final /* synthetic */ long $inactiveTrackColor;
    final /* synthetic */ SliderState $sliderState;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SliderDefaults$Track$3$1(SliderState sliderState, long j10, long j11, long j12, long j13) {
        super(1);
        this.$sliderState = sliderState;
        this.$inactiveTrackColor = j10;
        this.$activeTrackColor = j11;
        this.$inactiveTickColor = j12;
        this.$activeTickColor = j13;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DrawScope) obj);
        return t0.f22605a;
    }

    public final void invoke(DrawScope drawScope) {
        SliderDefaults.INSTANCE.m1887drawTrackLUBghH0(drawScope, this.$sliderState.getTickFractions(), 0.0f, this.$sliderState.getCoercedValueAsFraction$material3_release(), this.$inactiveTrackColor, this.$activeTrackColor, this.$inactiveTickColor, this.$activeTickColor);
    }
}
