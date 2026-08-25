package androidx.compose.ui.draw;

import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.RenderEffectKt;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/GraphicsLayerScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BlurKt$blur$1 extends r implements l<GraphicsLayerScope, t0> {
    final /* synthetic */ boolean $clip;
    final /* synthetic */ Shape $edgeTreatment;
    final /* synthetic */ float $radiusX;
    final /* synthetic */ float $radiusY;
    final /* synthetic */ int $tileMode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BlurKt$blur$1(float f10, float f11, int i10, Shape shape, boolean z) {
        super(1);
        this.$radiusX = f10;
        this.$radiusY = f11;
        this.$tileMode = i10;
        this.$edgeTreatment = shape;
        this.$clip = z;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((GraphicsLayerScope) obj);
        return t0.f22605a;
    }

    public final void invoke(GraphicsLayerScope graphicsLayerScope) {
        float fMo285toPx0680j_4 = graphicsLayerScope.mo285toPx0680j_4(this.$radiusX);
        float fMo285toPx0680j_42 = graphicsLayerScope.mo285toPx0680j_4(this.$radiusY);
        graphicsLayerScope.setRenderEffect((fMo285toPx0680j_4 <= 0.0f || fMo285toPx0680j_42 <= 0.0f) ? null : RenderEffectKt.m3758BlurEffect3YTHUZs(fMo285toPx0680j_4, fMo285toPx0680j_42, this.$tileMode));
        Shape rectangleShape = this.$edgeTreatment;
        if (rectangleShape == null) {
            rectangleShape = RectangleShapeKt.getRectangleShape();
        }
        graphicsLayerScope.setShape(rectangleShape);
        graphicsLayerScope.setClip(this.$clip);
    }
}
