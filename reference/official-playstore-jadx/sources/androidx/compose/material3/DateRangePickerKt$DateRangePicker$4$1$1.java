package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/material3/DisplayMode;", "displayMode", "Lx6/t0;", "invoke-vCnGnXg", "(I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DateRangePickerKt$DateRangePicker$4$1$1 extends r implements l<DisplayMode, t0> {
    final /* synthetic */ DateRangePickerState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangePickerKt$DateRangePicker$4$1$1(DateRangePickerState dateRangePickerState) {
        super(1);
        this.$state = dateRangePickerState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m1510invokevCnGnXg(((DisplayMode) obj).getValue());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-vCnGnXg, reason: not valid java name */
    public final void m1510invokevCnGnXg(int i10) {
        this.$state.mo1514setDisplayModevCnGnXg(i10);
    }
}
