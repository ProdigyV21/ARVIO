package androidx.compose.foundation.text;

import androidx.compose.ui.text.input.ImeAction;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/text/input/ImeAction;", "imeAction", "Lx6/t0;", "invoke-KlQnJC8", "(I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TextFieldState$onImeActionPerformed$1 extends r implements l<ImeAction, t0> {
    final /* synthetic */ TextFieldState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldState$onImeActionPerformed$1(TextFieldState textFieldState) {
        super(1);
        this.this$0 = textFieldState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m905invokeKlQnJC8(((ImeAction) obj).getValue());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-KlQnJC8, reason: not valid java name */
    public final void m905invokeKlQnJC8(int i10) {
        this.this$0.keyboardActionRunner.m829runActionKlQnJC8(i10);
    }
}
