package androidx.compose.material3;

import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TimePickerKt$TimeSelector$2$1 extends r implements r7.a<t0> {
    final /* synthetic */ k0 $scope;
    final /* synthetic */ int $selection;
    final /* synthetic */ TimePickerState $state;

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$TimeSelector$2$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.TimePickerKt$TimeSelector$2$1$1", f = "TimePicker.kt", l = {1218}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ TimePickerState $state;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(TimePickerState timePickerState, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$state = timePickerState;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.$state, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                TimePickerState timePickerState = this.$state;
                this.label = 1;
                Object objAnimateToCurrent$material3_release = timePickerState.animateToCurrent$material3_release(this);
                e7.a aVar = e7.a.f15033i;
                if (objAnimateToCurrent$material3_release == aVar) {
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
    public TimePickerKt$TimeSelector$2$1(int i10, TimePickerState timePickerState, k0 k0Var) {
        super(0);
        this.$selection = i10;
        this.$state = timePickerState;
        this.$scope = k0Var;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m2193invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m2193invoke() {
        if (Selection.m1865equalsimpl0(this.$selection, this.$state.m2204getSelectionJiIwxys$material3_release())) {
            return;
        }
        this.$state.m2207setSelectioniHAOin8$material3_release(this.$selection);
        m0.p(this.$scope, null, 0, new AnonymousClass1(this.$state, null), 3);
    }
}
