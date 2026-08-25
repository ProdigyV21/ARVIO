package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class ChipKt$ChipContent$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ p<Composer, Integer, t0> $avatar;
    final /* synthetic */ p<Composer, Integer, t0> $label;
    final /* synthetic */ long $labelColor;
    final /* synthetic */ TextStyle $labelTextStyle;
    final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
    final /* synthetic */ long $leadingIconColor;
    final /* synthetic */ float $minHeight;
    final /* synthetic */ PaddingValues $paddingValues;
    final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
    final /* synthetic */ long $trailingIconColor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ChipKt$ChipContent$2(p<? super Composer, ? super Integer, t0> pVar, TextStyle textStyle, long j10, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, long j11, long j12, float f10, PaddingValues paddingValues, int i10) {
        super(2);
        this.$label = pVar;
        this.$labelTextStyle = textStyle;
        this.$labelColor = j10;
        this.$leadingIcon = pVar2;
        this.$avatar = pVar3;
        this.$trailingIcon = pVar4;
        this.$leadingIconColor = j11;
        this.$trailingIconColor = j12;
        this.$minHeight = f10;
        this.$paddingValues = paddingValues;
        this.$$changed = i10;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        ChipKt.m1342ChipContentfe0OD_I(this.$label, this.$labelTextStyle, this.$labelColor, this.$leadingIcon, this.$avatar, this.$trailingIcon, this.$leadingIconColor, this.$trailingIconColor, this.$minHeight, this.$paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
    }
}
