package androidx.compose.ui.text.android;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import java.text.CharacterIterator;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\f\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\u0006H\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\b\u0010\u0011\u001a\u00020\u0006H\u0016J\b\u0010\u0012\u001a\u00020\rH\u0016J\t\u0010\u0013\u001a\u00020\rH\u0096\u0002J\b\u0010\u0014\u001a\u00020\rH\u0016J\u0010\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0006H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/text/android/CharSequenceCharacterIterator;", "Ljava/lang/Object;", "Ljava/text/CharacterIterator;", "charSequence", "", TtmlNode.START, "", TtmlNode.END, "(Ljava/lang/CharSequence;II)V", "index", "clone", "", "current", "", "first", "getBeginIndex", "getEndIndex", "getIndex", "last", LinkHeader.Rel.Next, "previous", "setIndex", "position", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CharSequenceCharacterIterator implements CharacterIterator {
    public static final int $stable = 8;
    private final CharSequence charSequence;
    private final int end;
    private int index;
    private final int start;

    public CharSequenceCharacterIterator(CharSequence charSequence, int i10, int i11) {
        this.charSequence = charSequence;
        this.start = i10;
        this.end = i11;
        this.index = i10;
    }

    @Override // java.text.CharacterIterator
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    @Override // java.text.CharacterIterator
    public char current() {
        int i10 = this.index;
        if (i10 == this.end) {
            return (char) 65535;
        }
        return this.charSequence.charAt(i10);
    }

    @Override // java.text.CharacterIterator
    public char first() {
        this.index = this.start;
        return current();
    }

    @Override // java.text.CharacterIterator
    public int getBeginIndex() {
        return this.start;
    }

    @Override // java.text.CharacterIterator
    public int getEndIndex() {
        return this.end;
    }

    @Override // java.text.CharacterIterator
    public int getIndex() {
        return this.index;
    }

    @Override // java.text.CharacterIterator
    public char last() {
        int i10 = this.start;
        int i11 = this.end;
        if (i10 == i11) {
            this.index = i11;
            return (char) 65535;
        }
        int i12 = i11 - 1;
        this.index = i12;
        return this.charSequence.charAt(i12);
    }

    @Override // java.text.CharacterIterator
    public char next() {
        int i10 = this.index + 1;
        this.index = i10;
        int i11 = this.end;
        if (i10 < i11) {
            return this.charSequence.charAt(i10);
        }
        this.index = i11;
        return (char) 65535;
    }

    @Override // java.text.CharacterIterator
    public char previous() {
        int i10 = this.index;
        if (i10 <= this.start) {
            return (char) 65535;
        }
        int i11 = i10 - 1;
        this.index = i11;
        return this.charSequence.charAt(i11);
    }

    @Override // java.text.CharacterIterator
    public char setIndex(int position) {
        int i10 = this.start;
        if (position > this.end || i10 > position) {
            throw new IllegalArgumentException("invalid position");
        }
        this.index = position;
        return current();
    }
}
