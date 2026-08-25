package androidx.compose.ui.text.android.selection;

import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/compose/ui/text/android/selection/WordBoundary;", "", "locale", "Ljava/util/Locale;", "text", "", "(Ljava/util/Locale;Ljava/lang/CharSequence;)V", "wordIterator", "Landroidx/compose/ui/text/android/selection/WordIterator;", "getWordEnd", "", "offset", "getWordStart", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WordBoundary {
    public static final int $stable = 8;
    private final WordIterator wordIterator;

    public WordBoundary(Locale locale, CharSequence charSequence) {
        this.wordIterator = new WordIterator(charSequence, 0, charSequence.length(), locale);
    }

    public final int getWordEnd(int offset) {
        int punctuationEnd = this.wordIterator.isAfterPunctuation(this.wordIterator.nextBoundary(offset)) ? this.wordIterator.getPunctuationEnd(offset) : this.wordIterator.getNextWordEndOnTwoWordBoundary(offset);
        return punctuationEnd == -1 ? offset : punctuationEnd;
    }

    public final int getWordStart(int offset) {
        int punctuationBeginning = this.wordIterator.isOnPunctuation(this.wordIterator.prevBoundary(offset)) ? this.wordIterator.getPunctuationBeginning(offset) : this.wordIterator.getPrevWordBeginningOnTwoWordsBoundary(offset);
        return punctuationBeginning == -1 ? offset : punctuationBeginning;
    }
}
