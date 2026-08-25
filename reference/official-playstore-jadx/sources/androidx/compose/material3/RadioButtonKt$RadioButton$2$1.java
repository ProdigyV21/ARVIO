package androidx.compose.material3;

import androidx.compose.material3.tokens.RadioButtonTokens;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class RadioButtonKt$RadioButton$2$1 extends r implements l<DrawScope, t0> {
    final /* synthetic */ State<Dp> $dotRadius;
    final /* synthetic */ State<Color> $radioColor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RadioButtonKt$RadioButton$2$1(State<Color> state, State<Dp> state2) {
        super(1);
        this.$radioColor = state;
        this.$dotRadius = state2;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DrawScope) obj);
        return t0.f22605a;
    }

    public final void invoke(DrawScope drawScope) {
        float fMo285toPx0680j_4 = drawScope.mo285toPx0680j_4(RadioButtonKt.RadioStrokeWidth);
        float f10 = 2;
        float f11 = fMo285toPx0680j_4 / f10;
        androidx.compose.ui.graphics.drawscope.c.x(drawScope, this.$radioColor.getValue().m3482unboximpl(), drawScope.mo285toPx0680j_4(Dp.m5678constructorimpl(RadioButtonTokens.INSTANCE.m2802getIconSizeD9Ej5fM() / f10)) - f11, 0L, 0.0f, new Stroke(fMo285toPx0680j_4, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
        if (Dp.m5677compareTo0680j_4(this.$dotRadius.getValue().m5692unboximpl(), Dp.m5678constructorimpl(0)) > 0) {
            androidx.compose.ui.graphics.drawscope.c.x(drawScope, this.$radioColor.getValue().m3482unboximpl(), drawScope.mo285toPx0680j_4(this.$dotRadius.getValue().m5692unboximpl()) - f11, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
        }
    }
}
