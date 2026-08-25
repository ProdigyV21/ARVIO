package androidx.compose.animation;

import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/GraphicsLayerScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class EnterExitTransitionKt$createGraphicsLayerBlock$1$block$1 extends r implements l<GraphicsLayerScope, t0> {
    final /* synthetic */ State<Float> $alpha;
    final /* synthetic */ State<Float> $scale;
    final /* synthetic */ State<TransformOrigin> $transformOrigin;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnterExitTransitionKt$createGraphicsLayerBlock$1$block$1(State<Float> state, State<Float> state2, State<TransformOrigin> state3) {
        super(1);
        this.$alpha = state;
        this.$scale = state2;
        this.$transformOrigin = state3;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((GraphicsLayerScope) obj);
        return t0.f22605a;
    }

    public final void invoke(GraphicsLayerScope graphicsLayerScope) {
        State<Float> state = this.$alpha;
        graphicsLayerScope.setAlpha(state != null ? state.getValue().floatValue() : 1.0f);
        State<Float> state2 = this.$scale;
        graphicsLayerScope.setScaleX(state2 != null ? state2.getValue().floatValue() : 1.0f);
        State<Float> state3 = this.$scale;
        graphicsLayerScope.setScaleY(state3 != null ? state3.getValue().floatValue() : 1.0f);
        State<TransformOrigin> state4 = this.$transformOrigin;
        graphicsLayerScope.mo3646setTransformOrigin__ExYCQ(state4 != null ? state4.getValue().getPackedValue() : TransformOrigin.INSTANCE.m3841getCenterSzJe1aQ());
    }
}
