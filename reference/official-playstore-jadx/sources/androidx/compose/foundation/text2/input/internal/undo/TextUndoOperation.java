package androidx.compose.foundation.text2.input.internal.undo;

import androidx.compose.foundation.text.UndoManager_jvmKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import t7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 (2\u00020\u0001:\u0001(BC\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0013\u001a\u0004\b\u0016\u0010\u0015R\u001d\u0010\b\u001a\u00020\u00078\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\t\u001a\u00020\u00078\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\t\u0010\u0017\u001a\u0004\b\u001a\u0010\u0019R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0017\u001a\u0004\b\u001b\u0010\u0019R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010 \u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0011\u0010'\u001a\u00020$8F¢\u0006\u0006\u001a\u0004\b%\u0010&\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006)"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation;", "", "", "index", "", "preText", "postText", "Landroidx/compose/ui/text/TextRange;", "preSelection", "postSelection", "", "timeInMillis", "", "canMerge", "<init>", "(ILjava/lang/String;Ljava/lang/String;JJJZLkotlin/jvm/internal/h;)V", "I", "getIndex", "()I", "Ljava/lang/String;", "getPreText", "()Ljava/lang/String;", "getPostText", "J", "getPreSelection-d9O1mEE", "()J", "getPostSelection-d9O1mEE", "getTimeInMillis", "Z", "getCanMerge", "()Z", "Landroidx/compose/foundation/text2/input/internal/undo/TextEditType;", "textEditType", "Landroidx/compose/foundation/text2/input/internal/undo/TextEditType;", "getTextEditType", "()Landroidx/compose/foundation/text2/input/internal/undo/TextEditType;", "Landroidx/compose/foundation/text2/input/internal/undo/TextDeleteType;", "getDeletionType", "()Landroidx/compose/foundation/text2/input/internal/undo/TextDeleteType;", "deletionType", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextUndoOperation {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Saver<TextUndoOperation, Object> Saver = new Saver<TextUndoOperation, Object>() { // from class: androidx.compose.foundation.text2.input.internal.undo.TextUndoOperation$Companion$Saver$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.compose.runtime.saveable.Saver
        public TextUndoOperation restore(Object value) {
            List list = (List) value;
            return new TextUndoOperation(((Integer) list.get(0)).intValue(), (String) list.get(1), (String) list.get(2), TextRangeKt.TextRange(((Integer) list.get(3)).intValue(), ((Integer) list.get(4)).intValue()), TextRangeKt.TextRange(((Integer) list.get(5)).intValue(), ((Integer) list.get(6)).intValue()), ((Long) list.get(7)).longValue(), false, 64, null);
        }

        @Override // androidx.compose.runtime.saveable.Saver
        public Object save(SaverScope saverScope, TextUndoOperation textUndoOperation) {
            return a.E(Integer.valueOf(textUndoOperation.getIndex()), textUndoOperation.getPreText(), textUndoOperation.getPostText(), Integer.valueOf(TextRange.m5192getStartimpl(textUndoOperation.getPreSelection())), Integer.valueOf(TextRange.m5187getEndimpl(textUndoOperation.getPreSelection())), Integer.valueOf(TextRange.m5192getStartimpl(textUndoOperation.getPostSelection())), Integer.valueOf(TextRange.m5187getEndimpl(textUndoOperation.getPostSelection())), Long.valueOf(textUndoOperation.getTimeInMillis()));
        }
    };
    private final boolean canMerge;
    private final int index;
    private final long postSelection;
    private final String postText;
    private final long preSelection;
    private final String preText;
    private final TextEditType textEditType;
    private final long timeInMillis;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation;", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final Saver<TextUndoOperation, Object> getSaver() {
            return TextUndoOperation.Saver;
        }

        private Companion() {
        }
    }

    public /* synthetic */ TextUndoOperation(int i10, String str, String str2, long j10, long j11, long j12, boolean z, h hVar) {
        this(i10, str, str2, j10, j11, j12, z);
    }

    public final boolean getCanMerge() {
        return this.canMerge;
    }

    public final TextDeleteType getDeletionType() {
        return this.textEditType != TextEditType.Delete ? TextDeleteType.NotByUser : !TextRange.m5186getCollapsedimpl(this.postSelection) ? TextDeleteType.NotByUser : TextRange.m5186getCollapsedimpl(this.preSelection) ? TextRange.m5192getStartimpl(this.preSelection) > TextRange.m5192getStartimpl(this.postSelection) ? TextDeleteType.Start : TextDeleteType.End : (TextRange.m5192getStartimpl(this.preSelection) == TextRange.m5192getStartimpl(this.postSelection) && TextRange.m5192getStartimpl(this.preSelection) == this.index) ? TextDeleteType.Inner : TextDeleteType.NotByUser;
    }

    public final int getIndex() {
        return this.index;
    }

    /* JADX INFO: renamed from: getPostSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getPostSelection() {
        return this.postSelection;
    }

    public final String getPostText() {
        return this.postText;
    }

    /* JADX INFO: renamed from: getPreSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getPreSelection() {
        return this.preSelection;
    }

    public final String getPreText() {
        return this.preText;
    }

    public final TextEditType getTextEditType() {
        return this.textEditType;
    }

    public final long getTimeInMillis() {
        return this.timeInMillis;
    }

    private TextUndoOperation(int i10, String str, String str2, long j10, long j11, long j12, boolean z) {
        this.index = i10;
        this.preText = str;
        this.postText = str2;
        this.preSelection = j10;
        this.postSelection = j11;
        this.timeInMillis = j12;
        this.canMerge = z;
        if (str.length() == 0 && str2.length() == 0) {
            throw new IllegalArgumentException("Either pre or post text must not be empty");
        }
        this.textEditType = (str.length() != 0 || str2.length() <= 0) ? (str.length() <= 0 || str2.length() != 0) ? TextEditType.Replace : TextEditType.Delete : TextEditType.Insert;
    }

    public /* synthetic */ TextUndoOperation(int i10, String str, String str2, long j10, long j11, long j12, boolean z, int i11, h hVar) {
        this(i10, str, str2, j10, j11, (i11 & 32) != 0 ? UndoManager_jvmKt.timeNowMillis() : j12, (i11 & 64) != 0 ? true : z, null);
    }
}
