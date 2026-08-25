package androidx.compose.foundation.text2.input;

import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u001a&\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a.\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0000ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a3\u0010\u0013\u001a\u00020\u0012*\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001b\u0010\u0016\u001a\u00020\b*\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u001b\u0010\u0018\u001a\u00020\b*\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u0018\u0010\u0017\u001a\u0013\u0010\u0019\u001a\u00020\b*\u00020\u0004H\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"", "text", "Landroidx/compose/ui/text/TextRange;", "selection", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "TextFieldCharSequence-FDrldGo", "(Ljava/lang/String;J)Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "TextFieldCharSequence", "", "composition", "TextFieldCharSequence-3r_uNRQ", "(Ljava/lang/CharSequence;JLandroidx/compose/ui/text/TextRange;)Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "", "destination", "", "destinationOffset", "sourceStartIndex", "sourceEndIndex", "Lx6/t0;", "toCharArray", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;[CIII)V", "maxChars", "getTextBeforeSelection", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;I)Ljava/lang/CharSequence;", "getTextAfterSelection", "getSelectedText", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;)Ljava/lang/CharSequence;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldCharSequenceKt {
    /* JADX INFO: renamed from: TextFieldCharSequence-3r_uNRQ, reason: not valid java name */
    public static final TextFieldCharSequence m1066TextFieldCharSequence3r_uNRQ(CharSequence charSequence, long j10, TextRange textRange) {
        return new TextFieldCharSequenceWrapper(charSequence, j10, textRange, null);
    }

    /* JADX INFO: renamed from: TextFieldCharSequence-3r_uNRQ$default, reason: not valid java name */
    public static /* synthetic */ TextFieldCharSequence m1067TextFieldCharSequence3r_uNRQ$default(CharSequence charSequence, long j10, TextRange textRange, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            textRange = null;
        }
        return m1066TextFieldCharSequence3r_uNRQ(charSequence, j10, textRange);
    }

    /* JADX INFO: renamed from: TextFieldCharSequence-FDrldGo, reason: not valid java name */
    public static final TextFieldCharSequence m1068TextFieldCharSequenceFDrldGo(String str, long j10) {
        return new TextFieldCharSequenceWrapper(str, j10, null, null);
    }

    /* JADX INFO: renamed from: TextFieldCharSequence-FDrldGo$default, reason: not valid java name */
    public static /* synthetic */ TextFieldCharSequence m1069TextFieldCharSequenceFDrldGo$default(String str, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = "";
        }
        if ((i10 & 2) != 0) {
            j10 = TextRange.INSTANCE.m5197getZerod9O1mEE();
        }
        return m1068TextFieldCharSequenceFDrldGo(str, j10);
    }

    public static final CharSequence getSelectedText(TextFieldCharSequence textFieldCharSequence) {
        return textFieldCharSequence.subSequence(TextRange.m5190getMinimpl(textFieldCharSequence.getSelectionInChars()), TextRange.m5189getMaximpl(textFieldCharSequence.getSelectionInChars()));
    }

    public static final CharSequence getTextAfterSelection(TextFieldCharSequence textFieldCharSequence, int i10) {
        return textFieldCharSequence.subSequence(TextRange.m5189getMaximpl(textFieldCharSequence.getSelectionInChars()), Math.min(TextRange.m5189getMaximpl(textFieldCharSequence.getSelectionInChars()) + i10, textFieldCharSequence.length()));
    }

    public static final CharSequence getTextBeforeSelection(TextFieldCharSequence textFieldCharSequence, int i10) {
        return textFieldCharSequence.subSequence(Math.max(0, TextRange.m5190getMinimpl(textFieldCharSequence.getSelectionInChars()) - i10), TextRange.m5190getMinimpl(textFieldCharSequence.getSelectionInChars()));
    }

    public static final void toCharArray(TextFieldCharSequence textFieldCharSequence, char[] cArr, int i10, int i11, int i12) {
        ((TextFieldCharSequenceWrapper) textFieldCharSequence).toCharArray(cArr, i10, i11, i12);
    }
}
