package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class DatePickerKt$DatePickerHeader$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ p<Composer, Integer, t0> $content;
    final /* synthetic */ long $headlineContentColor;
    final /* synthetic */ float $minHeight;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ p<Composer, Integer, t0> $title;
    final /* synthetic */ long $titleContentColor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DatePickerKt$DatePickerHeader$2(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, long j10, long j11, float f10, p<? super Composer, ? super Integer, t0> pVar2, int i10) {
        super(2);
        this.$modifier = modifier;
        this.$title = pVar;
        this.$titleContentColor = j10;
        this.$headlineContentColor = j11;
        this.$minHeight = f10;
        this.$content = pVar2;
        this.$$changed = i10;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        DatePickerKt.m1481DatePickerHeaderpc5RIQQ(this.$modifier, this.$title, this.$titleContentColor, this.$headlineContentColor, this.$minHeight, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
    }
}
