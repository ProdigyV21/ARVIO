package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.ui.text.input.ImeAction;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/text/input/ImeAction;", "imeAction", "Lx6/t0;", "invoke-KlQnJC8", "(I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TextFieldDecoratorModifierNode$onImeActionPerformed$1 extends r implements l<ImeAction, t0> {
    final /* synthetic */ TextFieldDecoratorModifierNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldDecoratorModifierNode$onImeActionPerformed$1(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
        super(1);
        this.this$0 = textFieldDecoratorModifierNode;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m1109invokeKlQnJC8(((ImeAction) obj).getValue());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-KlQnJC8, reason: not valid java name */
    public final void m1109invokeKlQnJC8(int i10) {
        l<KeyboardActionScope, t0> onSend;
        ImeAction.Companion companion = ImeAction.INSTANCE;
        t0 t0Var = null;
        if (ImeAction.m5355equalsimpl0(i10, companion.m5368getDoneeUduSuo())) {
            onSend = this.this$0.getKeyboardActions().getOnDone();
        } else if (ImeAction.m5355equalsimpl0(i10, companion.m5369getGoeUduSuo())) {
            onSend = this.this$0.getKeyboardActions().getOnGo();
        } else if (ImeAction.m5355equalsimpl0(i10, companion.m5370getNexteUduSuo())) {
            onSend = this.this$0.getKeyboardActions().getOnNext();
        } else if (ImeAction.m5355equalsimpl0(i10, companion.m5372getPreviouseUduSuo())) {
            onSend = this.this$0.getKeyboardActions().getOnPrevious();
        } else if (ImeAction.m5355equalsimpl0(i10, companion.m5373getSearcheUduSuo())) {
            onSend = this.this$0.getKeyboardActions().getOnSearch();
        } else if (ImeAction.m5355equalsimpl0(i10, companion.m5374getSendeUduSuo())) {
            onSend = this.this$0.getKeyboardActions().getOnSend();
        } else {
            if (!(ImeAction.m5355equalsimpl0(i10, companion.m5367getDefaulteUduSuo()) ? true : ImeAction.m5355equalsimpl0(i10, companion.m5371getNoneeUduSuo()))) {
                throw new IllegalStateException("invalid ImeAction");
            }
            onSend = null;
        }
        if (onSend != null) {
            onSend.invoke(this.this$0.keyboardActionScope);
            t0Var = t0.f22605a;
        }
        if (t0Var == null) {
            this.this$0.keyboardActionScope.mo828defaultKeyboardActionKlQnJC8(i10);
        }
    }
}
