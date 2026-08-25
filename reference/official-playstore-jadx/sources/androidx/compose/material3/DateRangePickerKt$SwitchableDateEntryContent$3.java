package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class DateRangePickerKt$SwitchableDateEntryContent$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ CalendarModel $calendarModel;
    final /* synthetic */ DatePickerColors $colors;
    final /* synthetic */ DatePickerFormatter $dateFormatter;
    final /* synthetic */ int $displayMode;
    final /* synthetic */ long $displayedMonthMillis;
    final /* synthetic */ p<Long, Long, t0> $onDatesSelectionChange;
    final /* synthetic */ l<Long, t0> $onDisplayedMonthChange;
    final /* synthetic */ SelectableDates $selectableDates;
    final /* synthetic */ Long $selectedEndDateMillis;
    final /* synthetic */ Long $selectedStartDateMillis;
    final /* synthetic */ i $yearRange;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DateRangePickerKt$SwitchableDateEntryContent$3(Long l10, Long l11, long j10, int i10, p<? super Long, ? super Long, t0> pVar, l<? super Long, t0> lVar, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i11, int i12) {
        super(2);
        this.$selectedStartDateMillis = l10;
        this.$selectedEndDateMillis = l11;
        this.$displayedMonthMillis = j10;
        this.$displayMode = i10;
        this.$onDatesSelectionChange = pVar;
        this.$onDisplayedMonthChange = lVar;
        this.$calendarModel = calendarModel;
        this.$yearRange = iVar;
        this.$dateFormatter = datePickerFormatter;
        this.$selectableDates = selectableDates;
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
        DateRangePickerKt.m1506SwitchableDateEntryContentRN2D1Q(this.$selectedStartDateMillis, this.$selectedEndDateMillis, this.$displayedMonthMillis, this.$displayMode, this.$onDatesSelectionChange, this.$onDisplayedMonthChange, this.$calendarModel, this.$yearRange, this.$dateFormatter, this.$selectableDates, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1));
    }
}
