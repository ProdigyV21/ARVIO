package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class DatePickerKt$DateEntryContainer$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ DatePickerColors $colors;
    final /* synthetic */ p<Composer, Integer, t0> $content;
    final /* synthetic */ float $headerMinHeight;
    final /* synthetic */ p<Composer, Integer, t0> $headline;
    final /* synthetic */ TextStyle $headlineTextStyle;
    final /* synthetic */ p<Composer, Integer, t0> $modeToggleButton;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ p<Composer, Integer, t0> $title;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DatePickerKt$DateEntryContainer$3(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, DatePickerColors datePickerColors, TextStyle textStyle, float f10, p<? super Composer, ? super Integer, t0> pVar4, int i10) {
        super(2);
        this.$modifier = modifier;
        this.$title = pVar;
        this.$headline = pVar2;
        this.$modeToggleButton = pVar3;
        this.$colors = datePickerColors;
        this.$headlineTextStyle = textStyle;
        this.$headerMinHeight = f10;
        this.$content = pVar4;
        this.$$changed = i10;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        DatePickerKt.m1480DateEntryContainerau3_HiA(this.$modifier, this.$title, this.$headline, this.$modeToggleButton, this.$colors, this.$headlineTextStyle, this.$headerMinHeight, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
    }
}
