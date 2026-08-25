package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.input.ImeAction;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\f"}, d2 = {"androidx/compose/foundation/text2/input/internal/TextFieldDecoratorModifierNode$keyboardActionScope$1", "Landroidx/compose/foundation/text/KeyboardActionScope;", "Landroidx/compose/ui/text/input/ImeAction;", "imeAction", "Lx6/t0;", "defaultKeyboardAction-KlQnJC8", "(I)V", "defaultKeyboardAction", "Landroidx/compose/ui/focus/FocusManager;", "getFocusManager", "()Landroidx/compose/ui/focus/FocusManager;", "focusManager", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldDecoratorModifierNode$keyboardActionScope$1 implements KeyboardActionScope {
    final /* synthetic */ TextFieldDecoratorModifierNode this$0;

    public TextFieldDecoratorModifierNode$keyboardActionScope$1(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode) {
        this.this$0 = textFieldDecoratorModifierNode;
    }

    private final FocusManager getFocusManager() {
        return (FocusManager) CompositionLocalConsumerModifierNodeKt.currentValueOf(this.this$0, CompositionLocalsKt.getLocalFocusManager());
    }

    @Override // androidx.compose.foundation.text.KeyboardActionScope
    /* JADX INFO: renamed from: defaultKeyboardAction-KlQnJC8 */
    public void mo828defaultKeyboardActionKlQnJC8(int imeAction) {
        ImeAction.Companion companion = ImeAction.INSTANCE;
        if (ImeAction.m5355equalsimpl0(imeAction, companion.m5370getNexteUduSuo())) {
            getFocusManager().mo3168moveFocus3ESFkO8(FocusDirection.INSTANCE.m3163getNextdhqQ8s());
            return;
        }
        if (ImeAction.m5355equalsimpl0(imeAction, companion.m5372getPreviouseUduSuo())) {
            getFocusManager().mo3168moveFocus3ESFkO8(FocusDirection.INSTANCE.m3164getPreviousdhqQ8s());
        } else {
            if (ImeAction.m5355equalsimpl0(imeAction, companion.m5368getDoneeUduSuo())) {
                this.this$0.requireKeyboardController().hide();
                return;
            }
            if (ImeAction.m5355equalsimpl0(imeAction, companion.m5369getGoeUduSuo()) ? true : ImeAction.m5355equalsimpl0(imeAction, companion.m5373getSearcheUduSuo()) ? true : ImeAction.m5355equalsimpl0(imeAction, companion.m5374getSendeUduSuo()) ? true : ImeAction.m5355equalsimpl0(imeAction, companion.m5367getDefaulteUduSuo())) {
                return;
            }
            ImeAction.m5355equalsimpl0(imeAction, companion.m5371getNoneeUduSuo());
        }
    }
}
