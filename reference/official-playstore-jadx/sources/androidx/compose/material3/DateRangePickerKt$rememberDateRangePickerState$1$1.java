package androidx.compose.material3;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/material3/DateRangePickerStateImpl;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class DateRangePickerKt$rememberDateRangePickerState$1$1 extends r implements r7.a<DateRangePickerStateImpl> {
    final /* synthetic */ int $initialDisplayMode;
    final /* synthetic */ Long $initialDisplayedMonthMillis;
    final /* synthetic */ Long $initialSelectedEndDateMillis;
    final /* synthetic */ Long $initialSelectedStartDateMillis;
    final /* synthetic */ Locale $locale;
    final /* synthetic */ SelectableDates $selectableDates;
    final /* synthetic */ i $yearRange;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangePickerKt$rememberDateRangePickerState$1$1(Long l10, Long l11, Long l12, i iVar, int i10, SelectableDates selectableDates, Locale locale) {
        super(0);
        this.$initialSelectedStartDateMillis = l10;
        this.$initialSelectedEndDateMillis = l11;
        this.$initialDisplayedMonthMillis = l12;
        this.$yearRange = iVar;
        this.$initialDisplayMode = i10;
        this.$selectableDates = selectableDates;
        this.$locale = locale;
    }

    @Override // r7.a
    public final DateRangePickerStateImpl invoke() {
        return new DateRangePickerStateImpl(this.$initialSelectedStartDateMillis, this.$initialSelectedEndDateMillis, this.$initialDisplayedMonthMillis, this.$yearRange, this.$initialDisplayMode, this.$selectableDates, this.$locale, null);
    }
}
