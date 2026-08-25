package androidx.compose.foundation.text2.input;

import a0.c;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bw\u0018\u0000 \u00022\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0002\u0005\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text2/input/TextFieldLineLimits;", "", "Companion", "MultiLine", "SingleLine", "Landroidx/compose/foundation/text2/input/TextFieldLineLimits$MultiLine;", "Landroidx/compose/foundation/text2/input/TextFieldLineLimits$SingleLine;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface TextFieldLineLimits {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/text2/input/TextFieldLineLimits$Companion;", "", "()V", "Default", "Landroidx/compose/foundation/text2/input/TextFieldLineLimits;", "getDefault", "()Landroidx/compose/foundation/text2/input/TextFieldLineLimits;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final TextFieldLineLimits Default;

        static {
            int i10 = 0;
            Default = new MultiLine(i10, i10, 3, null);
        }

        private Companion() {
        }

        public final TextFieldLineLimits getDefault() {
            return Default;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0096\u0002J\b\u0010\r\u001a\u00020\u0003H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/text2/input/TextFieldLineLimits$MultiLine;", "Landroidx/compose/foundation/text2/input/TextFieldLineLimits;", "minHeightInLines", "", "maxHeightInLines", "(II)V", "getMaxHeightInLines", "()I", "getMinHeightInLines", "equals", "", "other", "", "hashCode", "toString", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class MultiLine implements TextFieldLineLimits {
        public static final int $stable = 0;
        private final int maxHeightInLines;
        private final int minHeightInLines;

        /* JADX WARN: Illegal instructions before constructor call */
        public MultiLine() {
            int i10 = 0;
            this(i10, i10, 3, null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || MultiLine.class != other.getClass()) {
                return false;
            }
            MultiLine multiLine = (MultiLine) other;
            return this.minHeightInLines == multiLine.minHeightInLines && this.maxHeightInLines == multiLine.maxHeightInLines;
        }

        public final int getMaxHeightInLines() {
            return this.maxHeightInLines;
        }

        public final int getMinHeightInLines() {
            return this.minHeightInLines;
        }

        public int hashCode() {
            return (this.minHeightInLines * 31) + this.maxHeightInLines;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("MultiLine(minHeightInLines=");
            sb2.append(this.minHeightInLines);
            sb2.append(", maxHeightInLines=");
            return c.o(sb2, this.maxHeightInLines, ')');
        }

        public MultiLine(int i10, int i11) {
            this.minHeightInLines = i10;
            this.maxHeightInLines = i11;
            if (1 > i10 || i10 > i11) {
                throw new IllegalArgumentException(androidx.compose.foundation.c.n(i10, i11, "Expected 1 ≤ minHeightInLines ≤ maxHeightInLines, were ", ", ").toString());
            }
        }

        public /* synthetic */ MultiLine(int i10, int i11, int i12, h hVar) {
            this((i12 & 1) != 0 ? 1 : i10, (i12 & 2) != 0 ? Integer.MAX_VALUE : i11);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/text2/input/TextFieldLineLimits$SingleLine;", "Landroidx/compose/foundation/text2/input/TextFieldLineLimits;", "()V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class SingleLine implements TextFieldLineLimits {
        public static final int $stable = 0;
        public static final SingleLine INSTANCE = new SingleLine();

        private SingleLine() {
        }
    }
}
