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
public final class Tooltip_androidKt$PlainTooltip$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ CaretProperties $caretProperties;
    final /* synthetic */ long $containerColor;
    final /* synthetic */ p<Composer, Integer, t0> $content;
    final /* synthetic */ long $contentColor;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ float $shadowElevation;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ CaretScope $this_PlainTooltip;
    final /* synthetic */ float $tonalElevation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public Tooltip_androidKt$PlainTooltip$2(CaretScope caretScope, Modifier modifier, CaretProperties caretProperties, Shape shape, long j10, long j11, float f10, float f11, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
        super(2);
        this.$this_PlainTooltip = caretScope;
        this.$modifier = modifier;
        this.$caretProperties = caretProperties;
        this.$shape = shape;
        this.$contentColor = j10;
        this.$containerColor = j11;
        this.$tonalElevation = f10;
        this.$shadowElevation = f11;
        this.$content = pVar;
        this.$$changed = i10;
        this.$$default = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        Tooltip_androidKt.m2300PlainTooltipFg7CxbU(this.$this_PlainTooltip, this.$modifier, this.$caretProperties, this.$shape, this.$contentColor, this.$containerColor, this.$tonalElevation, this.$shadowElevation, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
