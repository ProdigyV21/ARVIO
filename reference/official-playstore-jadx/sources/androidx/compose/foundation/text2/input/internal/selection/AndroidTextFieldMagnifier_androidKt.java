package androidx.compose.foundation.text2.input.internal.selection;

import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.text2.input.internal.TextLayoutState;
import androidx.compose.foundation.text2.input.internal.TransformedTextFieldState;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0001¨\u0006\n"}, d2 = {"textFieldMagnifierNode", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldMagnifierNode;", "textFieldState", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "textFieldSelectionState", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "textLayoutState", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "isFocused", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidTextFieldMagnifier_androidKt {
    public static final TextFieldMagnifierNode textFieldMagnifierNode(TransformedTextFieldState transformedTextFieldState, TextFieldSelectionState textFieldSelectionState, TextLayoutState textLayoutState, boolean z) {
        return Magnifier_androidKt.isPlatformMagnifierSupported$default(0, 1, null) ? new TextFieldMagnifierNodeImpl28(transformedTextFieldState, textFieldSelectionState, textLayoutState, z) : new TextFieldMagnifierNode() { // from class: androidx.compose.foundation.text2.input.internal.selection.AndroidTextFieldMagnifier_androidKt.textFieldMagnifierNode.1
            @Override // androidx.compose.foundation.text2.input.internal.selection.TextFieldMagnifierNode
            public void update(TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState2, TextLayoutState textLayoutState2, boolean isFocused) {
            }
        };
    }
}
