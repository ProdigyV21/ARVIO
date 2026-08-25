package androidx.compose.material3;

import androidx.compose.ui.focus.FocusRequester;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.TimePickerKt$TimePickerTextField$2$1", f = "TimePicker.kt", l = {}, m = "invokeSuspend")
public final class TimePickerKt$TimePickerTextField$2$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ FocusRequester $focusRequester;
    final /* synthetic */ int $selection;
    final /* synthetic */ TimePickerState $state;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimePickerKt$TimePickerTextField$2$1(TimePickerState timePickerState, int i10, FocusRequester focusRequester, d7.d<? super TimePickerKt$TimePickerTextField$2$1> dVar) {
        super(2, dVar);
        this.$state = timePickerState;
        this.$selection = i10;
        this.$focusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TimePickerKt$TimePickerTextField$2$1(this.$state, this.$selection, this.$focusRequester, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (Selection.m1865equalsimpl0(this.$state.m2204getSelectionJiIwxys$material3_release(), this.$selection)) {
            this.$focusRequester.requestFocus();
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((TimePickerKt$TimePickerTextField$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
