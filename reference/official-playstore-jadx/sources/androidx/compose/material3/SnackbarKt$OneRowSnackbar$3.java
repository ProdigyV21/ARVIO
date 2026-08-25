package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class SnackbarKt$OneRowSnackbar$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ p<Composer, Integer, t0> $action;
    final /* synthetic */ long $actionTextColor;
    final /* synthetic */ TextStyle $actionTextStyle;
    final /* synthetic */ p<Composer, Integer, t0> $dismissAction;
    final /* synthetic */ long $dismissActionColor;
    final /* synthetic */ p<Composer, Integer, t0> $text;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SnackbarKt$OneRowSnackbar$3(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, TextStyle textStyle, long j10, long j11, int i10) {
        super(2);
        this.$text = pVar;
        this.$action = pVar2;
        this.$dismissAction = pVar3;
        this.$actionTextStyle = textStyle;
        this.$actionTextColor = j10;
        this.$dismissActionColor = j11;
        this.$$changed = i10;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        SnackbarKt.m1917OneRowSnackbarkKq0p4A(this.$text, this.$action, this.$dismissAction, this.$actionTextStyle, this.$actionTextColor, this.$dismissActionColor, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
    }
}
