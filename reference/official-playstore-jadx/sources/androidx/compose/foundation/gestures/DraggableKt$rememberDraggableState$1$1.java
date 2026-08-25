package androidx.compose.foundation.gestures;

import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(F)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DraggableKt$rememberDraggableState$1$1 extends r implements l<Float, t0> {
    final /* synthetic */ State<l<Float, t0>> $onDeltaState;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DraggableKt$rememberDraggableState$1$1(State<? extends l<? super Float, t0>> state) {
        super(1);
        this.$onDeltaState = state;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).floatValue());
        return t0.f22605a;
    }

    public final void invoke(float f10) {
        this.$onDeltaState.getValue().invoke(Float.valueOf(f10));
    }
}
