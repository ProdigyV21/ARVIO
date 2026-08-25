package androidx.compose.material3;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.material3.DisplayMode;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/AnimatedContentScope;", "Landroidx/compose/material3/DisplayMode;", "mode", "Lx6/t0;", "invoke-fYndouo", "(Landroidx/compose/animation/AnimatedContentScope;ILandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DatePickerKt$SwitchableDateEntryContent$3 extends r implements r7.r<AnimatedContentScope, DisplayMode, Composer, Integer, t0> {
    final /* synthetic */ CalendarModel $calendarModel;
    final /* synthetic */ DatePickerColors $colors;
    final /* synthetic */ DatePickerFormatter $dateFormatter;
    final /* synthetic */ long $displayedMonthMillis;
    final /* synthetic */ l<Long, t0> $onDateSelectionChange;
    final /* synthetic */ l<Long, t0> $onDisplayedMonthChange;
    final /* synthetic */ SelectableDates $selectableDates;
    final /* synthetic */ Long $selectedDateMillis;
    final /* synthetic */ i $yearRange;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DatePickerKt$SwitchableDateEntryContent$3(Long l10, long j10, l<? super Long, t0> lVar, l<? super Long, t0> lVar2, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors) {
        super(4);
        this.$selectedDateMillis = l10;
        this.$displayedMonthMillis = j10;
        this.$onDateSelectionChange = lVar;
        this.$onDisplayedMonthChange = lVar2;
        this.$calendarModel = calendarModel;
        this.$yearRange = iVar;
        this.$dateFormatter = datePickerFormatter;
        this.$selectableDates = selectableDates;
        this.$colors = datePickerColors;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        m1496invokefYndouo((AnimatedContentScope) obj, ((DisplayMode) obj2).getValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-fYndouo, reason: not valid java name */
    public final void m1496invokefYndouo(AnimatedContentScope animatedContentScope, int i10, Composer composer, int i11) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-459778869, i11, -1, "androidx.compose.material3.SwitchableDateEntryContent.<anonymous> (DatePicker.kt:1439)");
        }
        DisplayMode.Companion companion = DisplayMode.INSTANCE;
        if (DisplayMode.m1526equalsimpl0(i10, companion.m1531getPickerjFl4v0())) {
            composer.startReplaceableGroup(-1168710170);
            DatePickerKt.DatePickerContent(this.$selectedDateMillis, this.$displayedMonthMillis, this.$onDateSelectionChange, this.$onDisplayedMonthChange, this.$calendarModel, this.$yearRange, this.$dateFormatter, this.$selectableDates, this.$colors, composer, 0);
            composer.endReplaceableGroup();
        } else if (DisplayMode.m1526equalsimpl0(i10, companion.m1530getInputjFl4v0())) {
            composer.startReplaceableGroup(-1168709641);
            DateInputKt.DateInputContent(this.$selectedDateMillis, this.$onDateSelectionChange, this.$calendarModel, this.$yearRange, this.$dateFormatter, this.$selectableDates, this.$colors, composer, 0);
            composer.endReplaceableGroup();
        } else {
            composer.startReplaceableGroup(-1168709264);
            composer.endReplaceableGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
