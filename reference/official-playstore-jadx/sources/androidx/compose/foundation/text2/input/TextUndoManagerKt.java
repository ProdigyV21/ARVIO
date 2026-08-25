package androidx.compose.foundation.text2.input;

import androidx.compose.foundation.text.UndoManagerKt;
import androidx.compose.foundation.text2.input.TextFieldBuffer;
import androidx.compose.foundation.text2.input.internal.undo.TextDeleteType;
import androidx.compose.foundation.text2.input.internal.undo.TextEditType;
import androidx.compose.foundation.text2.input.internal.undo.TextUndoOperation;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u001a\u001d\u0010\u0002\u001a\u0004\u0018\u00010\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a5\u0010\r\u001a\u00020\f*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b\r\u0010\u000e\"\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011\"\u0018\u0010\u0012\u001a\u00020\n*\u00020\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation;", LinkHeader.Rel.Next, "merge", "(Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation;Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation;)Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation;", "Landroidx/compose/foundation/text2/input/TextUndoManager;", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "pre", "post", "Landroidx/compose/foundation/text2/input/TextFieldBuffer$ChangeList;", "changes", "", "allowMerge", "Lx6/t0;", "recordChanges", "(Landroidx/compose/foundation/text2/input/TextUndoManager;Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/TextFieldBuffer$ChangeList;Z)V", "", "TEXT_UNDO_CAPACITY", "I", "isNewLineInsert", "(Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation;)Z", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextUndoManagerKt {
    private static final int TEXT_UNDO_CAPACITY = 100;

    private static final boolean isNewLineInsert(TextUndoOperation textUndoOperation) {
        return p.a(textUndoOperation.getPostText(), "\n") || p.a(textUndoOperation.getPostText(), "\r\n");
    }

    public static final TextUndoOperation merge(TextUndoOperation textUndoOperation, TextUndoOperation textUndoOperation2) {
        if (!textUndoOperation.getCanMerge() || !textUndoOperation2.getCanMerge() || textUndoOperation2.getTimeInMillis() < textUndoOperation.getTimeInMillis() || textUndoOperation2.getTimeInMillis() - textUndoOperation.getTimeInMillis() >= UndoManagerKt.getSNAPSHOTS_INTERVAL_MILLIS() || isNewLineInsert(textUndoOperation) || isNewLineInsert(textUndoOperation2) || textUndoOperation.getTextEditType() != textUndoOperation2.getTextEditType()) {
            return null;
        }
        if (textUndoOperation.getTextEditType() == TextEditType.Insert) {
            if (textUndoOperation.getPostText().length() + textUndoOperation.getIndex() == textUndoOperation2.getIndex()) {
                return new TextUndoOperation(textUndoOperation.getIndex(), "", textUndoOperation.getPostText() + textUndoOperation2.getPostText(), textUndoOperation.getPreSelection(), textUndoOperation2.getPostSelection(), textUndoOperation.getTimeInMillis(), false, 64, null);
            }
        }
        if (textUndoOperation.getTextEditType() == TextEditType.Delete && textUndoOperation.getDeletionType() == textUndoOperation2.getDeletionType() && (textUndoOperation.getDeletionType() == TextDeleteType.Start || textUndoOperation.getDeletionType() == TextDeleteType.End)) {
            if (textUndoOperation.getIndex() == textUndoOperation2.getPreText().length() + textUndoOperation2.getIndex()) {
                return new TextUndoOperation(textUndoOperation2.getIndex(), textUndoOperation2.getPreText() + textUndoOperation.getPreText(), "", textUndoOperation.getPreSelection(), textUndoOperation2.getPostSelection(), textUndoOperation.getTimeInMillis(), false, 64, null);
            }
            if (textUndoOperation.getIndex() == textUndoOperation2.getIndex()) {
                return new TextUndoOperation(textUndoOperation.getIndex(), textUndoOperation.getPreText() + textUndoOperation2.getPreText(), "", textUndoOperation.getPreSelection(), textUndoOperation2.getPostSelection(), textUndoOperation.getTimeInMillis(), false, 64, null);
            }
        }
        return null;
    }

    public static final void recordChanges(TextUndoManager textUndoManager, TextFieldCharSequence textFieldCharSequence, TextFieldCharSequence textFieldCharSequence2, TextFieldBuffer.ChangeList changeList, boolean z) {
        if (changeList.getChangeCount() > 1) {
            textUndoManager.record(new TextUndoOperation(0, textFieldCharSequence.toString(), textFieldCharSequence2.toString(), textFieldCharSequence.getSelectionInChars(), textFieldCharSequence2.getSelectionInChars(), 0L, false, 32, null));
            return;
        }
        if (changeList.getChangeCount() == 1) {
            long jMo1053getOriginalRangejx7JFs = changeList.mo1053getOriginalRangejx7JFs(0);
            long jMo1054getRangejx7JFs = changeList.mo1054getRangejx7JFs(0);
            if (TextRange.m5186getCollapsedimpl(jMo1053getOriginalRangejx7JFs) && TextRange.m5186getCollapsedimpl(jMo1054getRangejx7JFs)) {
                return;
            }
            textUndoManager.record(new TextUndoOperation(TextRange.m5190getMinimpl(jMo1053getOriginalRangejx7JFs), TextRangeKt.m5199substringFDrldGo(textFieldCharSequence, jMo1053getOriginalRangejx7JFs), TextRangeKt.m5199substringFDrldGo(textFieldCharSequence2, jMo1054getRangejx7JFs), textFieldCharSequence.getSelectionInChars(), textFieldCharSequence2.getSelectionInChars(), 0L, z, 32, null));
        }
    }

    public static /* synthetic */ void recordChanges$default(TextUndoManager textUndoManager, TextFieldCharSequence textFieldCharSequence, TextFieldCharSequence textFieldCharSequence2, TextFieldBuffer.ChangeList changeList, boolean z, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            z = true;
        }
        recordChanges(textUndoManager, textFieldCharSequence, textFieldCharSequence2, changeList, z);
    }
}
