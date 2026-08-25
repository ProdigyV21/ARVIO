package androidx.compose.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
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
public final class FloatingActionButtonKt$FloatingActionButton$4 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ long $containerColor;
    final /* synthetic */ p<Composer, Integer, t0> $content;
    final /* synthetic */ long $contentColor;
    final /* synthetic */ FloatingActionButtonElevation $elevation;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ r7.a<t0> $onClick;
    final /* synthetic */ Shape $shape;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FloatingActionButtonKt$FloatingActionButton$4(r7.a<t0> aVar, Modifier modifier, Shape shape, long j10, long j11, FloatingActionButtonElevation floatingActionButtonElevation, MutableInteractionSource mutableInteractionSource, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
        super(2);
        this.$onClick = aVar;
        this.$modifier = modifier;
        this.$shape = shape;
        this.$containerColor = j10;
        this.$contentColor = j11;
        this.$elevation = floatingActionButtonElevation;
        this.$interactionSource = mutableInteractionSource;
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
        FloatingActionButtonKt.m1589FloatingActionButtonXz6DiA(this.$onClick, this.$modifier, this.$shape, this.$containerColor, this.$contentColor, this.$elevation, this.$interactionSource, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
