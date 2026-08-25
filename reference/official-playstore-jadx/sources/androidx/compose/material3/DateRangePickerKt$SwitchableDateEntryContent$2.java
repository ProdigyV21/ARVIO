package androidx.compose.material3;

import androidx.compose.material3.DisplayMode;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/material3/DisplayMode;", "mode", "Lx6/t0;", "invoke-QujVXRc", "(ILandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DateRangePickerKt$SwitchableDateEntryContent$2 extends r implements q<DisplayMode, Composer, Integer, t0> {
    final /* synthetic */ CalendarModel $calendarModel;
    final /* synthetic */ DatePickerColors $colors;
    final /* synthetic */ DatePickerFormatter $dateFormatter;
    final /* synthetic */ long $displayedMonthMillis;
    final /* synthetic */ p<Long, Long, t0> $onDatesSelectionChange;
    final /* synthetic */ l<Long, t0> $onDisplayedMonthChange;
    final /* synthetic */ SelectableDates $selectableDates;
    final /* synthetic */ Long $selectedEndDateMillis;
    final /* synthetic */ Long $selectedStartDateMillis;
    final /* synthetic */ i $yearRange;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DateRangePickerKt$SwitchableDateEntryContent$2(Long l10, Long l11, long j10, p<? super Long, ? super Long, t0> pVar, l<? super Long, t0> lVar, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors) {
        super(3);
        this.$selectedStartDateMillis = l10;
        this.$selectedEndDateMillis = l11;
        this.$displayedMonthMillis = j10;
        this.$onDatesSelectionChange = pVar;
        this.$onDisplayedMonthChange = lVar;
        this.$calendarModel = calendarModel;
        this.$yearRange = iVar;
        this.$dateFormatter = datePickerFormatter;
        this.$selectableDates = selectableDates;
        this.$colors = datePickerColors;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        m1511invokeQujVXRc(((DisplayMode) obj).getValue(), (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-QujVXRc, reason: not valid java name */
    public final void m1511invokeQujVXRc(int i10, Composer composer, int i11) {
        int i12;
        if ((i11 & 6) == 0) {
            i12 = i11 | (composer.changed(i10) ? 4 : 2);
        } else {
            i12 = i11;
        }
        if ((i12 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1026642619, i12, -1, "androidx.compose.material3.SwitchableDateEntryContent.<anonymous> (DateRangePicker.kt:690)");
        }
        DisplayMode.Companion companion = DisplayMode.INSTANCE;
        if (DisplayMode.m1526equalsimpl0(i10, companion.m1531getPickerjFl4v0())) {
            composer.startReplaceableGroup(-1168744807);
            DateRangePickerKt.DateRangePickerContent(this.$selectedStartDateMillis, this.$selectedEndDateMillis, this.$displayedMonthMillis, this.$onDatesSelectionChange, this.$onDisplayedMonthChange, this.$calendarModel, this.$yearRange, this.$dateFormatter, this.$selectableDates, this.$colors, composer, 0);
            composer.endReplaceableGroup();
        } else if (DisplayMode.m1526equalsimpl0(i10, companion.m1530getInputjFl4v0())) {
            composer.startReplaceableGroup(-1168744198);
            DateRangeInputKt.DateRangeInputContent(this.$selectedStartDateMillis, this.$selectedEndDateMillis, this.$onDatesSelectionChange, this.$calendarModel, this.$yearRange, this.$dateFormatter, this.$selectableDates, this.$colors, composer, 0);
            composer.endReplaceableGroup();
        } else {
            composer.startReplaceableGroup(-1168743741);
            composer.endReplaceableGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
