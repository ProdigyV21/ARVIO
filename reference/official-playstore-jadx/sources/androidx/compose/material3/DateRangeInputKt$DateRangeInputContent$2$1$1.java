package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "startDateMillis", "Lx6/t0;", "invoke", "(Ljava/lang/Long;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DateRangeInputKt$DateRangeInputContent$2$1$1 extends r implements l<Long, t0> {
    final /* synthetic */ p<Long, Long, t0> $onDatesSelectionChange;
    final /* synthetic */ Long $selectedEndDateMillis;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DateRangeInputKt$DateRangeInputContent$2$1$1(p<? super Long, ? super Long, t0> pVar, Long l10) {
        super(1);
        this.$onDatesSelectionChange = pVar;
        this.$selectedEndDateMillis = l10;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Long) obj);
        return t0.f22605a;
    }

    public final void invoke(Long l10) {
        this.$onDatesSelectionChange.invoke(l10, this.$selectedEndDateMillis);
    }
}
