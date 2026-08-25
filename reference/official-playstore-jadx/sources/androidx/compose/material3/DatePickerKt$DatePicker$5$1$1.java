package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "dateInMillis", "Lx6/t0;", "invoke", "(Ljava/lang/Long;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DatePickerKt$DatePicker$5$1$1 extends r implements l<Long, t0> {
    final /* synthetic */ DatePickerState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DatePickerKt$DatePicker$5$1$1(DatePickerState datePickerState) {
        super(1);
        this.$state = datePickerState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Long) obj);
        return t0.f22605a;
    }

    public final void invoke(Long l10) {
        this.$state.setSelectedDateMillis(l10);
    }
}
