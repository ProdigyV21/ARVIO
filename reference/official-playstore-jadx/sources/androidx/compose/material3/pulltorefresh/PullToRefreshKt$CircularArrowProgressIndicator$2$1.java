package androidx.compose.material3.pulltorefresh;

import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class PullToRefreshKt$CircularArrowProgressIndicator$2$1 extends r implements l<DrawScope, t0> {
    final /* synthetic */ State<Float> $alphaState;
    final /* synthetic */ long $color;
    final /* synthetic */ Path $path;
    final /* synthetic */ a<Float> $progress;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PullToRefreshKt$CircularArrowProgressIndicator$2$1(a<Float> aVar, State<Float> state, long j10, Path path) {
        super(1);
        this.$progress = aVar;
        this.$alphaState = state;
        this.$color = j10;
        this.$path = path;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DrawScope) obj);
        return t0.f22605a;
    }

    public final void invoke(DrawScope drawScope) {
        ArrowValues ArrowValues = PullToRefreshKt.ArrowValues(((Number) this.$progress.invoke()).floatValue());
        float fFloatValue = this.$alphaState.getValue().floatValue();
        float rotation = ArrowValues.getRotation();
        long j10 = this.$color;
        Path path = this.$path;
        long jMo3915getCenterF1C5BW0 = drawScope.mo3915getCenterF1C5BW0();
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3928rotateUv8p0NA(rotation, jMo3915getCenterF1C5BW0);
        Rect rectM3275Rect3MmeM6k = RectKt.m3275Rect3MmeM6k(SizeKt.m3315getCenteruvyYCjk(drawScope.mo3916getSizeNHjbRc()), (drawScope.mo285toPx0680j_4(PullToRefreshKt.StrokeWidth) / 2.0f) + drawScope.mo285toPx0680j_4(PullToRefreshKt.ArcRadius));
        PullToRefreshKt.m2332drawCircularIndicatorKzyDr3Q(drawScope, j10, fFloatValue, ArrowValues, rectM3275Rect3MmeM6k, PullToRefreshKt.StrokeWidth);
        PullToRefreshKt.m2331drawArrowuDrxG_w(drawScope, path, rectM3275Rect3MmeM6k, j10, fFloatValue, ArrowValues, PullToRefreshKt.StrokeWidth);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }
}
