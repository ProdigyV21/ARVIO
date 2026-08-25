package androidx.compose.material3;

import androidx.compose.foundation.text.KeyboardActionScope;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text/KeyboardActionScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text/KeyboardActionScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TimePickerKt$TimeInputImpl$1$1$6$1 extends r implements l<KeyboardActionScope, t0> {
    final /* synthetic */ TimePickerState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimePickerKt$TimeInputImpl$1$1$6$1(TimePickerState timePickerState) {
        super(1);
        this.$state = timePickerState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((KeyboardActionScope) obj);
        return t0.f22605a;
    }

    public final void invoke(KeyboardActionScope keyboardActionScope) {
        this.$state.m2207setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1870getMinuteJiIwxys());
    }
}
