package androidx.compose.material3;

import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.MutableState;
import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "year", "Lx6/t0;", "invoke", "(I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DatePickerKt$DatePickerContent$1$4$2$2$1$1 extends r implements l<Integer, t0> {
    final /* synthetic */ k0 $coroutineScope;
    final /* synthetic */ CalendarMonth $displayedMonth;
    final /* synthetic */ LazyListState $monthsListState;
    final /* synthetic */ MutableState<Boolean> $yearPickerVisible$delegate;
    final /* synthetic */ i $yearRange;

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$1$4$2$2$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$1$4$2$2$1$1$1", f = "DatePicker.kt", l = {1568}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ CalendarMonth $displayedMonth;
        final /* synthetic */ LazyListState $monthsListState;
        final /* synthetic */ int $year;
        final /* synthetic */ i $yearRange;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LazyListState lazyListState, int i10, i iVar, CalendarMonth calendarMonth, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$monthsListState = lazyListState;
            this.$year = i10;
            this.$yearRange = iVar;
            this.$displayedMonth = calendarMonth;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.$monthsListState, this.$year, this.$yearRange, this.$displayedMonth, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                LazyListState lazyListState = this.$monthsListState;
                int month = (this.$displayedMonth.getMonth() + ((this.$year - this.$yearRange.f22619i) * 12)) - 1;
                this.label = 1;
                Object objScrollToItem$default = LazyListState.scrollToItem$default(lazyListState, month, 0, this, 2, null);
                e7.a aVar = e7.a.f15033i;
                if (objScrollToItem$default == aVar) {
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
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DatePickerKt$DatePickerContent$1$4$2$2$1$1(k0 k0Var, MutableState<Boolean> mutableState, LazyListState lazyListState, i iVar, CalendarMonth calendarMonth) {
        super(1);
        this.$coroutineScope = k0Var;
        this.$yearPickerVisible$delegate = mutableState;
        this.$monthsListState = lazyListState;
        this.$yearRange = iVar;
        this.$displayedMonth = calendarMonth;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).intValue());
        return t0.f22605a;
    }

    public final void invoke(int i10) {
        DatePickerKt.DatePickerContent$lambda$9(this.$yearPickerVisible$delegate, !DatePickerKt.DatePickerContent$lambda$8(r0));
        m0.p(this.$coroutineScope, null, 0, new AnonymousClass1(this.$monthsListState, i10, this.$yearRange, this.$displayedMonth, null), 3);
    }
}
