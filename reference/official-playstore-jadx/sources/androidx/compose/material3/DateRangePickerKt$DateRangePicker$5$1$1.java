package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "startDateMillis", "endDateMillis", "Lx6/t0;", "invoke", "(Ljava/lang/Long;Ljava/lang/Long;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DateRangePickerKt$DateRangePicker$5$1$1 extends r implements p<Long, Long, t0> {
    final /* synthetic */ DateRangePickerState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangePickerKt$DateRangePicker$5$1$1(DateRangePickerState dateRangePickerState) {
        super(2);
        this.$state = dateRangePickerState;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Long) obj, (Long) obj2);
        return t0.f22605a;
    }

    public final void invoke(Long l10, Long l11) {
        try {
            this.$state.setSelection(l10, l11);
        } catch (IllegalArgumentException unused) {
        }
    }
}
