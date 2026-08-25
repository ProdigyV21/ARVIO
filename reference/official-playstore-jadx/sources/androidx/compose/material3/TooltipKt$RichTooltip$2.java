package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class TooltipKt$RichTooltip$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ p<Composer, Integer, t0> $action;
    final /* synthetic */ RichTooltipColors $colors;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ float $shadowElevation;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ p<Composer, Integer, t0> $text;
    final /* synthetic */ p<Composer, Integer, t0> $title;
    final /* synthetic */ float $tonalElevation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TooltipKt$RichTooltip$2(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, Shape shape, RichTooltipColors richTooltipColors, float f10, float f11, p<? super Composer, ? super Integer, t0> pVar3, int i10, int i11) {
        super(2);
        this.$modifier = modifier;
        this.$title = pVar;
        this.$action = pVar2;
        this.$shape = shape;
        this.$colors = richTooltipColors;
        this.$tonalElevation = f10;
        this.$shadowElevation = f11;
        this.$text = pVar3;
        this.$$changed = i10;
        this.$$default = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        TooltipKt.m2299RichTooltip1tP8Re8(this.$modifier, this.$title, this.$action, this.$shape, this.$colors, this.$tonalElevation, this.$shadowElevation, this.$text, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
