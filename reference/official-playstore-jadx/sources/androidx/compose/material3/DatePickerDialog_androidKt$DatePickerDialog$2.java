package androidx.compose.material3;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.window.DialogProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class DatePickerDialog_androidKt$DatePickerDialog$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ DatePickerColors $colors;
    final /* synthetic */ p<Composer, Integer, t0> $confirmButton;
    final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;
    final /* synthetic */ p<Composer, Integer, t0> $dismissButton;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ r7.a<t0> $onDismissRequest;
    final /* synthetic */ DialogProperties $properties;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ float $tonalElevation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DatePickerDialog_androidKt$DatePickerDialog$2(r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar2, Shape shape, float f10, DatePickerColors datePickerColors, DialogProperties dialogProperties, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
        super(2);
        this.$onDismissRequest = aVar;
        this.$confirmButton = pVar;
        this.$modifier = modifier;
        this.$dismissButton = pVar2;
        this.$shape = shape;
        this.$tonalElevation = f10;
        this.$colors = datePickerColors;
        this.$properties = dialogProperties;
        this.$content = qVar;
        this.$$changed = i10;
        this.$$default = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        DatePickerDialog_androidKt.m1479DatePickerDialogGmEhDVc(this.$onDismissRequest, this.$confirmButton, this.$modifier, this.$dismissButton, this.$shape, this.$tonalElevation, this.$colors, this.$properties, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
