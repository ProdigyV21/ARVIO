package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Z)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class RangeSliderState$gestureEndAction$1 extends r implements l<Boolean, t0> {
    final /* synthetic */ RangeSliderState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RangeSliderState$gestureEndAction$1(RangeSliderState rangeSliderState) {
        super(1);
        this.this$0 = rangeSliderState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Boolean) obj).booleanValue());
        return t0.f22605a;
    }

    public final void invoke(boolean z) {
        r7.a<t0> onValueChangeFinished = this.this$0.getOnValueChangeFinished();
        if (onValueChangeFinished != null) {
            onValueChangeFinished.invoke();
        }
    }
}
