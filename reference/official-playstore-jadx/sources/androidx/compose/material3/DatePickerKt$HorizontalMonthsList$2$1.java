package androidx.compose.material3;

import androidx.compose.foundation.lazy.LazyListState;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.DatePickerKt$HorizontalMonthsList$2$1", f = "DatePicker.kt", l = {1689}, m = "invokeSuspend")
public final class DatePickerKt$HorizontalMonthsList$2$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ CalendarModel $calendarModel;
    final /* synthetic */ LazyListState $lazyListState;
    final /* synthetic */ l<Long, t0> $onDisplayedMonthChange;
    final /* synthetic */ i $yearRange;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DatePickerKt$HorizontalMonthsList$2$1(LazyListState lazyListState, l<? super Long, t0> lVar, CalendarModel calendarModel, i iVar, d7.d<? super DatePickerKt$HorizontalMonthsList$2$1> dVar) {
        super(2, dVar);
        this.$lazyListState = lazyListState;
        this.$onDisplayedMonthChange = lVar;
        this.$calendarModel = calendarModel;
        this.$yearRange = iVar;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new DatePickerKt$HorizontalMonthsList$2$1(this.$lazyListState, this.$onDisplayedMonthChange, this.$calendarModel, this.$yearRange, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            LazyListState lazyListState = this.$lazyListState;
            l<Long, t0> lVar = this.$onDisplayedMonthChange;
            CalendarModel calendarModel = this.$calendarModel;
            i iVar = this.$yearRange;
            this.label = 1;
            Object objUpdateDisplayedMonth = DatePickerKt.updateDisplayedMonth(lazyListState, lVar, calendarModel, iVar, this);
            e7.a aVar = e7.a.f15033i;
            if (objUpdateDisplayedMonth == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((DatePickerKt$HorizontalMonthsList$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
