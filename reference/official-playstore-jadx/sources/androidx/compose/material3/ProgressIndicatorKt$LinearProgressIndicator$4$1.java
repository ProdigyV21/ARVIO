package androidx.compose.material3;

import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ProgressIndicatorKt$LinearProgressIndicator$4$1 extends r implements l<DrawScope, t0> {
    final /* synthetic */ long $color;
    final /* synthetic */ State<Float> $firstLineHead;
    final /* synthetic */ State<Float> $firstLineTail;
    final /* synthetic */ State<Float> $secondLineHead;
    final /* synthetic */ State<Float> $secondLineTail;
    final /* synthetic */ int $strokeCap;
    final /* synthetic */ long $trackColor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProgressIndicatorKt$LinearProgressIndicator$4$1(long j10, int i10, State<Float> state, State<Float> state2, long j11, State<Float> state3, State<Float> state4) {
        super(1);
        this.$trackColor = j10;
        this.$strokeCap = i10;
        this.$firstLineHead = state;
        this.$firstLineTail = state2;
        this.$color = j11;
        this.$secondLineHead = state3;
        this.$secondLineTail = state4;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DrawScope) obj);
        return t0.f22605a;
    }

    public final void invoke(DrawScope drawScope) {
        DrawScope drawScope2;
        float fM3302getHeightimpl = Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc());
        ProgressIndicatorKt.m1780drawLinearIndicatorTrackAZGd3zU(drawScope, this.$trackColor, fM3302getHeightimpl, this.$strokeCap);
        if (this.$firstLineHead.getValue().floatValue() - this.$firstLineTail.getValue().floatValue() > 0.0f) {
            drawScope2 = drawScope;
            ProgressIndicatorKt.m1779drawLinearIndicatorqYKTg0g(drawScope2, this.$firstLineHead.getValue().floatValue(), this.$firstLineTail.getValue().floatValue(), this.$color, fM3302getHeightimpl, this.$strokeCap);
        } else {
            drawScope2 = drawScope;
        }
        if (this.$secondLineHead.getValue().floatValue() - this.$secondLineTail.getValue().floatValue() > 0.0f) {
            ProgressIndicatorKt.m1779drawLinearIndicatorqYKTg0g(drawScope2, this.$secondLineHead.getValue().floatValue(), this.$secondLineTail.getValue().floatValue(), this.$color, fM3302getHeightimpl, this.$strokeCap);
        }
    }
}
