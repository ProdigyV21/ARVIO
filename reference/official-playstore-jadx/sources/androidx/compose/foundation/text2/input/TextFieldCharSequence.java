package androidx.compose.foundation.text2.input;

import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\bw\u0018\u00002\u00020\u0001J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001H&J\u0013\u0010\f\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\rH¦\u0002J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\u00020\u0003X¦\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0001\u0012ø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "", "compositionInChars", "Landroidx/compose/ui/text/TextRange;", "getCompositionInChars-MzsxiRA", "()Landroidx/compose/ui/text/TextRange;", "selectionInChars", "getSelectionInChars-d9O1mEE", "()J", "contentEquals", "", "other", "equals", "", "hashCode", "", "toString", "", "Landroidx/compose/foundation/text2/input/TextFieldCharSequenceWrapper;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface TextFieldCharSequence extends CharSequence {
    boolean contentEquals(CharSequence other);

    boolean equals(Object other);

    /* JADX INFO: renamed from: getCompositionInChars-MzsxiRA, reason: not valid java name */
    TextRange mo1064getCompositionInCharsMzsxiRA();

    /* JADX INFO: renamed from: getSelectionInChars-d9O1mEE, reason: not valid java name */
    long mo1065getSelectionInCharsd9O1mEE();

    int hashCode();

    @Override // java.lang.CharSequence
    String toString();
}
