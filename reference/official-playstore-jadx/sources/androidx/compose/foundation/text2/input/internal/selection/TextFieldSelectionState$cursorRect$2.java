package androidx.compose.foundation.text2.input.internal.selection;

import androidx.compose.foundation.text.TextFieldCursorKt;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/ui/geometry/Rect;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TextFieldSelectionState$cursorRect$2 extends r implements a<Rect> {
    final /* synthetic */ TextFieldSelectionState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldSelectionState$cursorRect$2(TextFieldSelectionState textFieldSelectionState) {
        super(0);
        this.this$0 = textFieldSelectionState;
    }

    @Override // r7.a
    public final Rect invoke() {
        float right;
        TextLayoutResult layoutResult = this.this$0.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Rect.INSTANCE.getZero();
        }
        TextFieldCharSequence text = this.this$0.textFieldState.getText();
        if (!TextRange.m5186getCollapsedimpl(text.getSelectionInChars())) {
            return Rect.INSTANCE.getZero();
        }
        Rect cursorRect = layoutResult.getCursorRect(TextRange.m5192getStartimpl(text.getSelectionInChars()));
        float fMo285toPx0680j_4 = this.this$0.density.mo285toPx0680j_4(TextFieldCursorKt.getDefaultCursorThickness());
        if (layoutResult.getLayoutInput().getLayoutDirection() == LayoutDirection.Ltr) {
            right = (fMo285toPx0680j_4 / 2) + cursorRect.getLeft();
        } else {
            right = cursorRect.getRight() - (fMo285toPx0680j_4 / 2);
        }
        float f10 = fMo285toPx0680j_4 / 2;
        float fM5844getWidthimpl = IntSize.m5844getWidthimpl(layoutResult.getSize()) - f10;
        if (right > fM5844getWidthimpl) {
            right = fM5844getWidthimpl;
        }
        if (right < f10) {
            right = f10;
        }
        return new Rect(right - f10, cursorRect.getTop(), right + f10, cursorRect.getBottom());
    }
}
