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
public final class DateRangePickerDefaults$DateRangePickerHeadline$6 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ DatePickerFormatter $dateFormatter;
    final /* synthetic */ p<Composer, Integer, t0> $datesDelimiter;
    final /* synthetic */ int $displayMode;
    final /* synthetic */ p<Composer, Integer, t0> $endDatePlaceholder;
    final /* synthetic */ String $endDateText;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ Long $selectedEndDateMillis;
    final /* synthetic */ Long $selectedStartDateMillis;
    final /* synthetic */ p<Composer, Integer, t0> $startDatePlaceholder;
    final /* synthetic */ String $startDateText;
    final /* synthetic */ DateRangePickerDefaults $tmp7_rcvr;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DateRangePickerDefaults$DateRangePickerHeadline$6(DateRangePickerDefaults dateRangePickerDefaults, Long l10, Long l11, int i10, DatePickerFormatter datePickerFormatter, Modifier modifier, String str, String str2, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, int i11, int i12) {
        super(2);
        this.$tmp7_rcvr = dateRangePickerDefaults;
        this.$selectedStartDateMillis = l10;
        this.$selectedEndDateMillis = l11;
        this.$displayMode = i10;
        this.$dateFormatter = datePickerFormatter;
        this.$modifier = modifier;
        this.$startDateText = str;
        this.$endDateText = str2;
        this.$startDatePlaceholder = pVar;
        this.$endDatePlaceholder = pVar2;
        this.$datesDelimiter = pVar3;
        this.$$changed = i11;
        this.$$changed1 = i12;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        this.$tmp7_rcvr.m1500DateRangePickerHeadline0YIUgSQ(this.$selectedStartDateMillis, this.$selectedEndDateMillis, this.$displayMode, this.$dateFormatter, this.$modifier, this.$startDateText, this.$endDateText, this.$startDatePlaceholder, this.$endDatePlaceholder, this.$datesDelimiter, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1));
    }
}
