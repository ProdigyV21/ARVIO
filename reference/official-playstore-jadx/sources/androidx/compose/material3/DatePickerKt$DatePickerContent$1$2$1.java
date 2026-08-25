package androidx.compose.material3;

import androidx.compose.foundation.lazy.LazyListState;
import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DatePickerKt$DatePickerContent$1$2$1 extends r implements r7.a<t0> {
    final /* synthetic */ k0 $coroutineScope;
    final /* synthetic */ LazyListState $monthsListState;

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$1$2$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.DatePickerKt$DatePickerContent$1$2$1$1", f = "DatePicker.kt", l = {1510}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ LazyListState $monthsListState;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LazyListState lazyListState, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$monthsListState = lazyListState;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.$monthsListState, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    LazyListState lazyListState = this.$monthsListState;
                    int firstVisibleItemIndex = lazyListState.getFirstVisibleItemIndex() - 1;
                    this.label = 1;
                    Object objAnimateScrollToItem$default = LazyListState.animateScrollToItem$default(lazyListState, firstVisibleItemIndex, 0, this, 2, null);
                    e7.a aVar = e7.a.f15033i;
                    if (objAnimateScrollToItem$default == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
            } catch (IllegalArgumentException unused) {
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DatePickerKt$DatePickerContent$1$2$1(k0 k0Var, LazyListState lazyListState) {
        super(0);
        this.$coroutineScope = k0Var;
        this.$monthsListState = lazyListState;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m1490invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m1490invoke() {
        m0.p(this.$coroutineScope, null, 0, new AnonymousClass1(this.$monthsListState, null), 3);
    }
}
