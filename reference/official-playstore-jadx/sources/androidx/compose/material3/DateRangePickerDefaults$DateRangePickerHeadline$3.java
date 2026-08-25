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
public final class DateRangePickerDefaults$DateRangePickerHeadline$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ DatePickerFormatter $dateFormatter;
    final /* synthetic */ int $displayMode;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ Long $selectedEndDateMillis;
    final /* synthetic */ Long $selectedStartDateMillis;
    final /* synthetic */ DateRangePickerDefaults $tmp0_rcvr;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangePickerDefaults$DateRangePickerHeadline$3(DateRangePickerDefaults dateRangePickerDefaults, Long l10, Long l11, int i10, DatePickerFormatter datePickerFormatter, Modifier modifier, int i11, int i12) {
        super(2);
        this.$tmp0_rcvr = dateRangePickerDefaults;
        this.$selectedStartDateMillis = l10;
        this.$selectedEndDateMillis = l11;
        this.$displayMode = i10;
        this.$dateFormatter = datePickerFormatter;
        this.$modifier = modifier;
        this.$$changed = i11;
        this.$$default = i12;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        this.$tmp0_rcvr.m1502DateRangePickerHeadlinev84Udv0(this.$selectedStartDateMillis, this.$selectedEndDateMillis, this.$displayMode, this.$dateFormatter, this.$modifier, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
