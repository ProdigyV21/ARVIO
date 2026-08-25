package androidx.compose.foundation.text2.input.internal.selection;

import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldHandleState;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TextFieldSelectionState$cursorHandle$2 extends r implements a<TextFieldHandleState> {
    final /* synthetic */ TextFieldSelectionState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldSelectionState$cursorHandle$2(TextFieldSelectionState textFieldSelectionState) {
        super(0);
        this.this$0 = textFieldSelectionState;
    }

    @Override // r7.a
    public final TextFieldHandleState invoke() {
        TextFieldCharSequence text = this.this$0.textFieldState.getText();
        return (this.this$0.getShowCursorHandle() && TextRange.m5186getCollapsedimpl(text.getSelectionInChars()) && text.length() > 0 && (this.this$0.getDraggingHandle() == Handle.Cursor || this.this$0.getCursorHandleInBounds())) ? new TextFieldHandleState(true, this.this$0.getCursorRect().m3263getBottomCenterF1C5BW0(), ResolvedTextDirection.Ltr, false, null) : TextFieldHandleState.INSTANCE.getHidden();
    }
}
