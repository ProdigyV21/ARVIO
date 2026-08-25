package androidx.compose.foundation.text2;

import androidx.compose.ui.focus.FocusState;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/focus/FocusState;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/focus/FocusState;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SecureTextFieldController$focusChangeModifier$1 extends r implements l<FocusState, t0> {
    final /* synthetic */ SecureTextFieldController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SecureTextFieldController$focusChangeModifier$1(SecureTextFieldController secureTextFieldController) {
        super(1);
        this.this$0 = secureTextFieldController;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((FocusState) obj);
        return t0.f22605a;
    }

    public final void invoke(FocusState focusState) {
        if (focusState.isFocused()) {
            return;
        }
        this.this$0.getPasswordRevealFilter().hide();
    }
}
