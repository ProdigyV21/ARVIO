package androidx.compose.foundation.text2.input;

import androidx.compose.foundation.text2.input.internal.ToCharArray_androidKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.text.u;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0010\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J-\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\t¢\u0006\u0004\b\u001f\u0010 J\u001a\u0010\"\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010!H\u0096\u0002¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\tH\u0016¢\u0006\u0004\b$\u0010%R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010&R \u0010'\u001a\u00020\u00048\u0016X\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\"\u0010+\u001a\u0004\u0018\u00010\u00048\u0016X\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u0014\u00100\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u0010%\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00061"}, d2 = {"Landroidx/compose/foundation/text2/input/TextFieldCharSequenceWrapper;", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "", "text", "Landroidx/compose/ui/text/TextRange;", "selection", "composition", "<init>", "(Ljava/lang/CharSequence;JLandroidx/compose/ui/text/TextRange;Lkotlin/jvm/internal/h;)V", "", "index", "", "get", "(I)C", "startIndex", "endIndex", "subSequence", "(II)Ljava/lang/CharSequence;", "", "toString", "()Ljava/lang/String;", "other", "", "contentEquals", "(Ljava/lang/CharSequence;)Z", "", "destination", "destinationOffset", "sourceStartIndex", "sourceEndIndex", "Lx6/t0;", "toCharArray", "([CIII)V", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "Ljava/lang/CharSequence;", "selectionInChars", "J", "getSelectionInChars-d9O1mEE", "()J", "compositionInChars", "Landroidx/compose/ui/text/TextRange;", "getCompositionInChars-MzsxiRA", "()Landroidx/compose/ui/text/TextRange;", "getLength", "length", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class TextFieldCharSequenceWrapper implements TextFieldCharSequence {
    private final TextRange compositionInChars;
    private final long selectionInChars;
    private final CharSequence text;

    public /* synthetic */ TextFieldCharSequenceWrapper(CharSequence charSequence, long j10, TextRange textRange, h hVar) {
        this(charSequence, j10, textRange);
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ char charAt(int i10) {
        return get(i10);
    }

    @Override // androidx.compose.foundation.text2.input.TextFieldCharSequence
    public boolean contentEquals(CharSequence other) {
        return u.J(this.text, other);
    }

    @Override // androidx.compose.foundation.text2.input.TextFieldCharSequence
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || TextFieldCharSequenceWrapper.class != other.getClass()) {
            return false;
        }
        TextFieldCharSequenceWrapper textFieldCharSequenceWrapper = (TextFieldCharSequenceWrapper) other;
        return TextRange.m5185equalsimpl0(getSelectionInChars(), textFieldCharSequenceWrapper.getSelectionInChars()) && p.a(getCompositionInChars(), textFieldCharSequenceWrapper.getCompositionInChars()) && contentEquals(textFieldCharSequenceWrapper.text);
    }

    public char get(int index) {
        return this.text.charAt(index);
    }

    @Override // androidx.compose.foundation.text2.input.TextFieldCharSequence
    /* JADX INFO: renamed from: getCompositionInChars-MzsxiRA, reason: from getter */
    public TextRange getCompositionInChars() {
        return this.compositionInChars;
    }

    public int getLength() {
        return this.text.length();
    }

    @Override // androidx.compose.foundation.text2.input.TextFieldCharSequence
    /* JADX INFO: renamed from: getSelectionInChars-d9O1mEE, reason: from getter */
    public long getSelectionInChars() {
        return this.selectionInChars;
    }

    @Override // androidx.compose.foundation.text2.input.TextFieldCharSequence
    public int hashCode() {
        int iM5193hashCodeimpl = (TextRange.m5193hashCodeimpl(getSelectionInChars()) + (this.text.hashCode() * 31)) * 31;
        TextRange compositionInChars = getCompositionInChars();
        return iM5193hashCodeimpl + (compositionInChars != null ? TextRange.m5193hashCodeimpl(compositionInChars.getPackedValue()) : 0);
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ int length() {
        return getLength();
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int startIndex, int endIndex) {
        return this.text.subSequence(startIndex, endIndex);
    }

    public final void toCharArray(char[] destination, int destinationOffset, int sourceStartIndex, int sourceEndIndex) {
        ToCharArray_androidKt.toCharArray(this.text, destination, destinationOffset, sourceStartIndex, sourceEndIndex);
    }

    @Override // androidx.compose.foundation.text2.input.TextFieldCharSequence, java.lang.CharSequence
    public String toString() {
        return this.text.toString();
    }

    private TextFieldCharSequenceWrapper(CharSequence charSequence, long j10, TextRange textRange) {
        this.text = charSequence;
        this.selectionInChars = TextRangeKt.m5198coerceIn8ffj60Q(j10, 0, charSequence.length());
        this.compositionInChars = textRange != null ? TextRange.m5180boximpl(TextRangeKt.m5198coerceIn8ffj60Q(textRange.getPackedValue(), 0, charSequence.length())) : null;
    }
}
