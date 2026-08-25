package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class ChipKt$SelectableChip$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ p<Composer, Integer, t0> $avatar;
    final /* synthetic */ BorderStroke $border;
    final /* synthetic */ SelectableChipColors $colors;
    final /* synthetic */ SelectableChipElevation $elevation;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ p<Composer, Integer, t0> $label;
    final /* synthetic */ TextStyle $labelTextStyle;
    final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
    final /* synthetic */ float $minHeight;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ r7.a<t0> $onClick;
    final /* synthetic */ PaddingValues $paddingValues;
    final /* synthetic */ boolean $selected;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ChipKt$SelectableChip$3(boolean z, Modifier modifier, r7.a<t0> aVar, boolean z5, p<? super Composer, ? super Integer, t0> pVar, TextStyle textStyle, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, Shape shape, SelectableChipColors selectableChipColors, SelectableChipElevation selectableChipElevation, BorderStroke borderStroke, float f10, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, int i10, int i11) {
        super(2);
        this.$selected = z;
        this.$modifier = modifier;
        this.$onClick = aVar;
        this.$enabled = z5;
        this.$label = pVar;
        this.$labelTextStyle = textStyle;
        this.$leadingIcon = pVar2;
        this.$avatar = pVar3;
        this.$trailingIcon = pVar4;
        this.$shape = shape;
        this.$colors = selectableChipColors;
        this.$elevation = selectableChipElevation;
        this.$border = borderStroke;
        this.$minHeight = f10;
        this.$paddingValues = paddingValues;
        this.$interactionSource = mutableInteractionSource;
        this.$$changed = i10;
        this.$$changed1 = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        ChipKt.m1343SelectableChipu0RnIRE(this.$selected, this.$modifier, this.$onClick, this.$enabled, this.$label, this.$labelTextStyle, this.$leadingIcon, this.$avatar, this.$trailingIcon, this.$shape, this.$colors, this.$elevation, this.$border, this.$minHeight, this.$paddingValues, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1));
    }
}
