package androidx.compose.ui.draw;

import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/GraphicsLayerScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ShadowKt$shadow$2$1 extends r implements l<GraphicsLayerScope, t0> {
    final /* synthetic */ long $ambientColor;
    final /* synthetic */ boolean $clip;
    final /* synthetic */ float $elevation;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ long $spotColor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShadowKt$shadow$2$1(float f10, Shape shape, boolean z, long j10, long j11) {
        super(1);
        this.$elevation = f10;
        this.$shape = shape;
        this.$clip = z;
        this.$ambientColor = j10;
        this.$spotColor = j11;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((GraphicsLayerScope) obj);
        return t0.f22605a;
    }

    public final void invoke(GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setShadowElevation(graphicsLayerScope.mo285toPx0680j_4(this.$elevation));
        graphicsLayerScope.setShape(this.$shape);
        graphicsLayerScope.setClip(this.$clip);
        graphicsLayerScope.mo3643setAmbientShadowColor8_81llA(this.$ambientColor);
        graphicsLayerScope.mo3645setSpotShadowColor8_81llA(this.$spotColor);
    }
}
