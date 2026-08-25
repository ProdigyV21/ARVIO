package androidx.compose.material3;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class SwitchKt$SwitchImpl$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ boolean $checked;
    final /* synthetic */ SwitchColors $colors;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ InteractionSource $interactionSource;
    final /* synthetic */ float $maxBound;
    final /* synthetic */ float $minBound;
    final /* synthetic */ BoxScope $this_SwitchImpl;
    final /* synthetic */ p<Composer, Integer, t0> $thumbContent;
    final /* synthetic */ Shape $thumbShape;
    final /* synthetic */ State<Float> $thumbValue;
    final /* synthetic */ float $uncheckedThumbDiameter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SwitchKt$SwitchImpl$2(BoxScope boxScope, boolean z, boolean z5, SwitchColors switchColors, State<Float> state, p<? super Composer, ? super Integer, t0> pVar, InteractionSource interactionSource, Shape shape, float f10, float f11, float f12, int i10, int i11) {
        super(2);
        this.$this_SwitchImpl = boxScope;
        this.$checked = z;
        this.$enabled = z5;
        this.$colors = switchColors;
        this.$thumbValue = state;
        this.$thumbContent = pVar;
        this.$interactionSource = interactionSource;
        this.$thumbShape = shape;
        this.$uncheckedThumbDiameter = f10;
        this.$minBound = f11;
        this.$maxBound = f12;
        this.$$changed = i10;
        this.$$changed1 = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        SwitchKt.m2037SwitchImpl0DmnUew(this.$this_SwitchImpl, this.$checked, this.$enabled, this.$colors, this.$thumbValue, this.$thumbContent, this.$interactionSource, this.$thumbShape, this.$uncheckedThumbDiameter, this.$minBound, this.$maxBound, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1));
    }
}
