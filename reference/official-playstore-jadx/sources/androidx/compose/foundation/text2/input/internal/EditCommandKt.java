package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.c;
import androidx.compose.foundation.text.StringHelpers_androidKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import qb.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a#\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a#\u0010\n\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a#\u0010\f\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\f\u0010\u0007\u001a#\u0010\u000f\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u000f\u0010\u000b\u001a#\u0010\u0010\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0010\u0010\u000b\u001a\u0013\u0010\u0011\u001a\u00020\u0005*\u00020\u0000H\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0013\u0010\u0013\u001a\u00020\u0005*\u00020\u0000H\u0000¢\u0006\u0004\b\u0013\u0010\u0012\u001a\u001b\u0010\u0015\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0013\u0010\u0017\u001a\u00020\u0005*\u00020\u0000H\u0000¢\u0006\u0004\b\u0017\u0010\u0012\u001a\u001f\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0018H\u0002¢\u0006\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "", "text", "", "newCursorPosition", "Lx6/t0;", "commitText", "(Landroidx/compose/foundation/text2/input/internal/EditingBuffer;Ljava/lang/String;I)V", TtmlNode.START, TtmlNode.END, "setComposingRegion", "(Landroidx/compose/foundation/text2/input/internal/EditingBuffer;II)V", "setComposingText", "lengthBeforeCursor", "lengthAfterCursor", "deleteSurroundingText", "deleteSurroundingTextInCodePoints", "finishComposingText", "(Landroidx/compose/foundation/text2/input/internal/EditingBuffer;)V", "backspace", "amount", "moveCursor", "(Landroidx/compose/foundation/text2/input/internal/EditingBuffer;I)V", "deleteAll", "", "high", "low", "", "isSurrogatePair", "(CC)Z", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EditCommandKt {
    public static final void backspace(EditingBuffer editingBuffer) {
        if (editingBuffer.hasComposition()) {
            editingBuffer.delete(editingBuffer.getCompositionStart(), editingBuffer.getCompositionEnd());
            return;
        }
        if (editingBuffer.getCursor() != -1) {
            if (editingBuffer.getCursor() != 0) {
                editingBuffer.delete(StringHelpers_androidKt.findPrecedingBreak(editingBuffer.toString(), editingBuffer.getCursor()), editingBuffer.getCursor());
            }
        } else {
            int selectionStart = editingBuffer.getSelectionStart();
            int selectionEnd = editingBuffer.getSelectionEnd();
            editingBuffer.setCursor(editingBuffer.getSelectionStart());
            editingBuffer.delete(selectionStart, selectionEnd);
        }
    }

    public static final void commitText(EditingBuffer editingBuffer, String str, int i10) {
        if (editingBuffer.hasComposition()) {
            editingBuffer.replace(editingBuffer.getCompositionStart(), editingBuffer.getCompositionEnd(), str);
        } else {
            editingBuffer.replace(editingBuffer.getSelectionStart(), editingBuffer.getSelectionEnd(), str);
        }
        editingBuffer.setCursor(d.n(i10 > 0 ? (r0 + i10) - 1 : (editingBuffer.getCursor() + i10) - str.length(), 0, editingBuffer.getLength()));
    }

    public static final void deleteAll(EditingBuffer editingBuffer) {
        editingBuffer.replace(0, editingBuffer.getLength(), "");
    }

    public static final void deleteSurroundingText(EditingBuffer editingBuffer, int i10, int i11) {
        if (i10 < 0 || i11 < 0) {
            throw new IllegalArgumentException(c.s("Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were ", i10, " and ", i11, " respectively.").toString());
        }
        int selectionEnd = editingBuffer.getSelectionEnd();
        int length = selectionEnd + i11;
        if (((i11 ^ length) & (selectionEnd ^ length)) < 0) {
            length = editingBuffer.getLength();
        }
        editingBuffer.delete(editingBuffer.getSelectionEnd(), Math.min(length, editingBuffer.getLength()));
        int selectionStart = editingBuffer.getSelectionStart();
        int i12 = selectionStart - i10;
        if (((i10 ^ selectionStart) & (selectionStart ^ i12)) < 0) {
            i12 = 0;
        }
        editingBuffer.delete(Math.max(0, i12), editingBuffer.getSelectionStart());
    }

    public static final void deleteSurroundingTextInCodePoints(EditingBuffer editingBuffer, int i10, int i11) {
        if (i10 < 0 || i11 < 0) {
            throw new IllegalArgumentException(c.s("Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were ", i10, " and ", i11, " respectively.").toString());
        }
        int i12 = 0;
        for (int i13 = 0; i13 < i10; i13++) {
            int i14 = i12 + 1;
            i12 = (editingBuffer.getSelectionStart() <= i14 || !isSurrogatePair(editingBuffer.get((editingBuffer.getSelectionStart() - i14) + (-1)), editingBuffer.get(editingBuffer.getSelectionStart() - i14))) ? i14 : i12 + 2;
            if (i12 == editingBuffer.getSelectionStart()) {
                break;
            }
        }
        int i15 = 0;
        for (int i16 = 0; i16 < i11; i16++) {
            int i17 = i15 + 1;
            i15 = (editingBuffer.getSelectionEnd() + i17 >= editingBuffer.getLength() || !isSurrogatePair(editingBuffer.get((editingBuffer.getSelectionEnd() + i17) + (-1)), editingBuffer.get(editingBuffer.getSelectionEnd() + i17))) ? i17 : i15 + 2;
            if (editingBuffer.getSelectionEnd() + i15 == editingBuffer.getLength()) {
                break;
            }
        }
        editingBuffer.delete(editingBuffer.getSelectionEnd(), editingBuffer.getSelectionEnd() + i15);
        editingBuffer.delete(editingBuffer.getSelectionStart() - i12, editingBuffer.getSelectionStart());
    }

    public static final void finishComposingText(EditingBuffer editingBuffer) {
        editingBuffer.commitComposition();
    }

    private static final boolean isSurrogatePair(char c10, char c11) {
        return Character.isHighSurrogate(c10) && Character.isLowSurrogate(c11);
    }

    public static final void moveCursor(EditingBuffer editingBuffer, int i10) {
        if (editingBuffer.getCursor() == -1) {
            editingBuffer.setCursor(editingBuffer.getSelectionStart());
        }
        int selectionStart = editingBuffer.getSelectionStart();
        String string = editingBuffer.toString();
        int i11 = 0;
        if (i10 <= 0) {
            int i12 = -i10;
            while (i11 < i12) {
                int iFindPrecedingBreak = StringHelpers_androidKt.findPrecedingBreak(string, selectionStart);
                if (iFindPrecedingBreak == -1) {
                    break;
                }
                i11++;
                selectionStart = iFindPrecedingBreak;
            }
        } else {
            while (i11 < i10) {
                int iFindFollowingBreak = StringHelpers_androidKt.findFollowingBreak(string, selectionStart);
                if (iFindFollowingBreak == -1) {
                    break;
                }
                i11++;
                selectionStart = iFindFollowingBreak;
            }
        }
        editingBuffer.setCursor(selectionStart);
    }

    public static final void setComposingRegion(EditingBuffer editingBuffer, int i10, int i11) {
        if (editingBuffer.hasComposition()) {
            editingBuffer.commitComposition();
        }
        int iN = d.n(i10, 0, editingBuffer.getLength());
        int iN2 = d.n(i11, 0, editingBuffer.getLength());
        if (iN != iN2) {
            if (iN < iN2) {
                editingBuffer.setComposition(iN, iN2);
            } else {
                editingBuffer.setComposition(iN2, iN);
            }
        }
    }

    public static final void setComposingText(EditingBuffer editingBuffer, String str, int i10) {
        if (editingBuffer.hasComposition()) {
            int compositionStart = editingBuffer.getCompositionStart();
            editingBuffer.replace(compositionStart, editingBuffer.getCompositionEnd(), str);
            if (str.length() > 0) {
                editingBuffer.setComposition(compositionStart, str.length() + compositionStart);
            }
        } else {
            int selectionStart = editingBuffer.getSelectionStart();
            editingBuffer.replace(selectionStart, editingBuffer.getSelectionEnd(), str);
            if (str.length() > 0) {
                editingBuffer.setComposition(selectionStart, str.length() + selectionStart);
            }
        }
        editingBuffer.setCursor(d.n(i10 > 0 ? (r0 + i10) - 1 : (editingBuffer.getCursor() + i10) - str.length(), 0, editingBuffer.getLength()));
    }
}
