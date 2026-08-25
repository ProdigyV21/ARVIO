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
public final class DatePickerKt$SwitchableDateEntryContent$4 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ CalendarModel $calendarModel;
    final /* synthetic */ DatePickerColors $colors;
    final /* synthetic */ DatePickerFormatter $dateFormatter;
    final /* synthetic */ int $displayMode;
    final /* synthetic */ long $displayedMonthMillis;
    final /* synthetic */ l<Long, t0> $onDateSelectionChange;
    final /* synthetic */ l<Long, t0> $onDisplayedMonthChange;
    final /* synthetic */ SelectableDates $selectableDates;
    final /* synthetic */ Long $selectedDateMillis;
    final /* synthetic */ i $yearRange;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DatePickerKt$SwitchableDateEntryContent$4(Long l10, long j10, int i10, l<? super Long, t0> lVar, l<? super Long, t0> lVar2, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i11) {
        super(2);
        this.$selectedDateMillis = l10;
        this.$displayedMonthMillis = j10;
        this.$displayMode = i10;
        this.$onDateSelectionChange = lVar;
        this.$onDisplayedMonthChange = lVar2;
        this.$calendarModel = calendarModel;
        this.$yearRange = iVar;
        this.$dateFormatter = datePickerFormatter;
        this.$selectableDates = selectableDates;
        this.$colors = datePickerColors;
        this.$$changed = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        DatePickerKt.m1485SwitchableDateEntryContentd7iavvg(this.$selectedDateMillis, this.$displayedMonthMillis, this.$displayMode, this.$onDateSelectionChange, this.$onDisplayedMonthChange, this.$calendarModel, this.$yearRange, this.$dateFormatter, this.$selectableDates, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
    }
}
