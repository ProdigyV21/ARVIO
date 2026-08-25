package androidx.compose.material3;

import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ProgressIndicatorKt$CircularProgressIndicator$4$1 extends r implements l<DrawScope, t0> {
    final /* synthetic */ State<Float> $baseRotation;
    final /* synthetic */ long $color;
    final /* synthetic */ State<Integer> $currentRotation;
    final /* synthetic */ State<Float> $endAngle;
    final /* synthetic */ State<Float> $startAngle;
    final /* synthetic */ Stroke $stroke;
    final /* synthetic */ float $strokeWidth;
    final /* synthetic */ long $trackColor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProgressIndicatorKt$CircularProgressIndicator$4$1(long j10, Stroke stroke, State<Integer> state, State<Float> state2, State<Float> state3, State<Float> state4, float f10, long j11) {
        super(1);
        this.$trackColor = j10;
        this.$stroke = stroke;
        this.$currentRotation = state;
        this.$endAngle = state2;
        this.$startAngle = state3;
        this.$baseRotation = state4;
        this.$strokeWidth = f10;
        this.$color = j11;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DrawScope) obj);
        return t0.f22605a;
    }

    public final void invoke(DrawScope drawScope) {
        ProgressIndicatorKt.m1776drawCircularIndicatorTrackbw27NRU(drawScope, this.$trackColor, this.$stroke);
        ProgressIndicatorKt.m1778drawIndeterminateCircularIndicatorhrjfTZI(drawScope, this.$startAngle.getValue().floatValue() + this.$baseRotation.getValue().floatValue() + (((this.$currentRotation.getValue().floatValue() * 216.0f) % 360.0f) - 90.0f), this.$strokeWidth, Math.abs(this.$endAngle.getValue().floatValue() - this.$startAngle.getValue().floatValue()), this.$color, this.$stroke);
    }
}
