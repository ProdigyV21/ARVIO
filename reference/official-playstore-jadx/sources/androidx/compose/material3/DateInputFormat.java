package androidx.compose.material3;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.u;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\f\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0016"}, d2 = {"Landroidx/compose/material3/DateInputFormat;", "", "patternWithDelimiters", "", TtmlNode.RUBY_DELIMITER, "", "(Ljava/lang/String;C)V", "getDelimiter", "()C", "getPatternWithDelimiters", "()Ljava/lang/String;", "patternWithoutDelimiters", "getPatternWithoutDelimiters", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class DateInputFormat {
    public static final int $stable = 0;
    private final char delimiter;
    private final String patternWithDelimiters;
    private final String patternWithoutDelimiters;

    public DateInputFormat(String str, char c10) {
        this.patternWithDelimiters = str;
        this.delimiter = c10;
        this.patternWithoutDelimiters = u.O(str, String.valueOf(c10), "", false);
    }

    public static /* synthetic */ DateInputFormat copy$default(DateInputFormat dateInputFormat, String str, char c10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = dateInputFormat.patternWithDelimiters;
        }
        if ((i10 & 2) != 0) {
            c10 = dateInputFormat.delimiter;
        }
        return dateInputFormat.copy(str, c10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPatternWithDelimiters() {
        return this.patternWithDelimiters;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final char getDelimiter() {
        return this.delimiter;
    }

    public final DateInputFormat copy(String patternWithDelimiters, char delimiter) {
        return new DateInputFormat(patternWithDelimiters, delimiter);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DateInputFormat)) {
            return false;
        }
        DateInputFormat dateInputFormat = (DateInputFormat) other;
        return p.a(this.patternWithDelimiters, dateInputFormat.patternWithDelimiters) && this.delimiter == dateInputFormat.delimiter;
    }

    public final char getDelimiter() {
        return this.delimiter;
    }

    public final String getPatternWithDelimiters() {
        return this.patternWithDelimiters;
    }

    public final String getPatternWithoutDelimiters() {
        return this.patternWithoutDelimiters;
    }

    public int hashCode() {
        return (this.patternWithDelimiters.hashCode() * 31) + this.delimiter;
    }

    public String toString() {
        return "DateInputFormat(patternWithDelimiters=" + this.patternWithDelimiters + ", delimiter=" + this.delimiter + ')';
    }
}
