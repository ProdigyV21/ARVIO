package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "dateInMillis", "Lx6/t0;", "invoke", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DateRangePickerKt$VerticalMonthsList$1$onDateSelectionChange$1$1 extends r implements l<Long, t0> {
    final /* synthetic */ p<Long, Long, t0> $onDatesSelectionChange;
    final /* synthetic */ Long $selectedEndDateMillis;
    final /* synthetic */ Long $selectedStartDateMillis;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DateRangePickerKt$VerticalMonthsList$1$onDateSelectionChange$1$1(Long l10, Long l11, p<? super Long, ? super Long, t0> pVar) {
        super(1);
        this.$selectedStartDateMillis = l10;
        this.$selectedEndDateMillis = l11;
        this.$onDatesSelectionChange = pVar;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).longValue());
        return t0.f22605a;
    }

    public final void invoke(long j10) {
        DateRangePickerKt.updateDateSelection(j10, this.$selectedStartDateMillis, this.$selectedEndDateMillis, this.$onDatesSelectionChange);
    }
}
