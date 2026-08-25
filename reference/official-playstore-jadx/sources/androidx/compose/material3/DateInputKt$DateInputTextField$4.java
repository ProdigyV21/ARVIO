package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class DateInputKt$DateInputTextField$4 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ CalendarModel $calendarModel;
    final /* synthetic */ DatePickerColors $colors;
    final /* synthetic */ DateInputFormat $dateInputFormat;
    final /* synthetic */ DateInputValidator $dateInputValidator;
    final /* synthetic */ Long $initialDateMillis;
    final /* synthetic */ int $inputIdentifier;
    final /* synthetic */ p<Composer, Integer, t0> $label;
    final /* synthetic */ Locale $locale;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ l<Long, t0> $onDateSelectionChange;
    final /* synthetic */ p<Composer, Integer, t0> $placeholder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DateInputKt$DateInputTextField$4(Modifier modifier, Long l10, l<? super Long, t0> lVar, CalendarModel calendarModel, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, int i10, DateInputValidator dateInputValidator, DateInputFormat dateInputFormat, Locale locale, DatePickerColors datePickerColors, int i11, int i12) {
        super(2);
        this.$modifier = modifier;
        this.$initialDateMillis = l10;
        this.$onDateSelectionChange = lVar;
        this.$calendarModel = calendarModel;
        this.$label = pVar;
        this.$placeholder = pVar2;
        this.$inputIdentifier = i10;
        this.$dateInputValidator = dateInputValidator;
        this.$dateInputFormat = dateInputFormat;
        this.$locale = locale;
        this.$colors = datePickerColors;
        this.$$changed = i11;
        this.$$changed1 = i12;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        DateInputKt.m1447DateInputTextFieldtQNruF0(this.$modifier, this.$initialDateMillis, this.$onDateSelectionChange, this.$calendarModel, this.$label, this.$placeholder, this.$inputIdentifier, this.$dateInputValidator, this.$dateInputFormat, this.$locale, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1));
    }
}
