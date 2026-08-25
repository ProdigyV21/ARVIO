package androidx.compose.foundation.text2.input.internal.undo;

import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.foundation.text2.input.TextFieldCharSequenceKt;
import androidx.compose.foundation.text2.input.TextFieldState;
import androidx.compose.foundation.text2.input.internal.EditingBuffer;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001b\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0006\u0010\u0005¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/text2/input/TextFieldState;", "Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation;", "op", "Lx6/t0;", "undo", "(Landroidx/compose/foundation/text2/input/TextFieldState;Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation;)V", "redo", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextUndoOperationKt {
    public static final void redo(TextFieldState textFieldState, TextUndoOperation textUndoOperation) {
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.replace(textUndoOperation.getIndex(), textUndoOperation.getPreText().length() + textUndoOperation.getIndex(), textUndoOperation.getPostText());
        mainBuffer.setSelection(TextRange.m5192getStartimpl(textUndoOperation.getPostSelection()), TextRange.m5187getEndimpl(textUndoOperation.getPostSelection()));
        TextFieldCharSequence textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ = TextFieldCharSequenceKt.m1066TextFieldCharSequence3r_uNRQ(textFieldState.getMainBuffer().toString(), textFieldState.getMainBuffer().m1084getSelectiond9O1mEE(), textFieldState.getMainBuffer().m1083getCompositionMzsxiRA());
        textFieldState.setText(textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ);
        textFieldState.notifyIme(text, textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ);
    }

    public static final void undo(TextFieldState textFieldState, TextUndoOperation textUndoOperation) {
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.replace(textUndoOperation.getIndex(), textUndoOperation.getPostText().length() + textUndoOperation.getIndex(), textUndoOperation.getPreText());
        mainBuffer.setSelection(TextRange.m5192getStartimpl(textUndoOperation.getPreSelection()), TextRange.m5187getEndimpl(textUndoOperation.getPreSelection()));
        TextFieldCharSequence textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ = TextFieldCharSequenceKt.m1066TextFieldCharSequence3r_uNRQ(textFieldState.getMainBuffer().toString(), textFieldState.getMainBuffer().m1084getSelectiond9O1mEE(), textFieldState.getMainBuffer().m1083getCompositionMzsxiRA());
        textFieldState.setText(textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ);
        textFieldState.notifyIme(text, textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ);
    }
}
