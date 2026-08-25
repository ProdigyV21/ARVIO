package androidx.compose.material3;

import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SliderKt$RangeSlider$state$1$1 extends r implements r7.a<t0> {
    final /* synthetic */ State<r7.a<t0>> $onValueChangeFinishedState;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SliderKt$RangeSlider$state$1$1(State<? extends r7.a<t0>> state) {
        super(0);
        this.$onValueChangeFinishedState = state;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m1897invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m1897invoke() {
        r7.a<t0> value = this.$onValueChangeFinishedState.getValue();
        if (value != null) {
            value.invoke();
        }
    }
}
