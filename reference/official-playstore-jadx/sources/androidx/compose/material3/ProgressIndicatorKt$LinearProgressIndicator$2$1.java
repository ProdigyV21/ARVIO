package androidx.compose.material3;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ProgressIndicatorKt$LinearProgressIndicator$2$1 extends r implements l<DrawScope, t0> {
    final /* synthetic */ r7.a<Float> $coercedProgress;
    final /* synthetic */ long $color;
    final /* synthetic */ int $strokeCap;
    final /* synthetic */ long $trackColor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProgressIndicatorKt$LinearProgressIndicator$2$1(long j10, int i10, r7.a<Float> aVar, long j11) {
        super(1);
        this.$trackColor = j10;
        this.$strokeCap = i10;
        this.$coercedProgress = aVar;
        this.$color = j11;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DrawScope) obj);
        return t0.f22605a;
    }

    public final void invoke(DrawScope drawScope) {
        float fM3302getHeightimpl = Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc());
        ProgressIndicatorKt.m1780drawLinearIndicatorTrackAZGd3zU(drawScope, this.$trackColor, fM3302getHeightimpl, this.$strokeCap);
        ProgressIndicatorKt.m1779drawLinearIndicatorqYKTg0g(drawScope, 0.0f, ((Number) this.$coercedProgress.invoke()).floatValue(), this.$color, fM3302getHeightimpl, this.$strokeCap);
    }
}
