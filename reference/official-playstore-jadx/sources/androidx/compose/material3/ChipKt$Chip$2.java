package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ChipKt$Chip$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ ChipColors $colors;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ p<Composer, Integer, t0> $label;
    final /* synthetic */ long $labelColor;
    final /* synthetic */ TextStyle $labelTextStyle;
    final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
    final /* synthetic */ float $minHeight;
    final /* synthetic */ PaddingValues $paddingValues;
    final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ChipKt$Chip$2(p<? super Composer, ? super Integer, t0> pVar, TextStyle textStyle, long j10, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, ChipColors chipColors, boolean z, float f10, PaddingValues paddingValues) {
        super(2);
        this.$label = pVar;
        this.$labelTextStyle = textStyle;
        this.$labelColor = j10;
        this.$leadingIcon = pVar2;
        this.$trailingIcon = pVar3;
        this.$colors = chipColors;
        this.$enabled = z;
        this.$minHeight = f10;
        this.$paddingValues = paddingValues;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1985962652, i10, -1, "androidx.compose.material3.Chip.<anonymous> (Chip.kt:1873)");
        }
        ChipKt.m1342ChipContentfe0OD_I(this.$label, this.$labelTextStyle, this.$labelColor, this.$leadingIcon, null, this.$trailingIcon, this.$colors.m1332leadingIconContentColorvNxB06k$material3_release(this.$enabled), this.$colors.m1333trailingIconContentColorvNxB06k$material3_release(this.$enabled), this.$minHeight, this.$paddingValues, composer, 24576);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
