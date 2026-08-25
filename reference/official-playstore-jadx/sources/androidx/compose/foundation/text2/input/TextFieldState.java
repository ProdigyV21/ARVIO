package androidx.compose.foundation.text2.input;

import androidx.compose.foundation.text2.input.TextFieldBuffer;
import androidx.compose.foundation.text2.input.TextUndoManager;
import androidx.compose.foundation.text2.input.internal.EditingBuffer;
import androidx.compose.foundation.text2.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0002XYB!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tB\u001d\b\u0016\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\nJ1\u0010\u0014\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J/\u0010\u0019\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ)\u0010#\u001a\u00020\u00132\u0017\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00130\u001f¢\u0006\u0002\b!H\u0086\b¢\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\u0002H\u0016¢\u0006\u0004\b%\u0010&J\u0017\u0010(\u001a\u00020 2\u0006\u0010'\u001a\u00020\u000bH\u0001¢\u0006\u0004\b(\u0010)J\u0017\u0010*\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020 H\u0001¢\u0006\u0004\b*\u0010+JG\u0010/\u001a\u00020\u00132\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u0017\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u00130\u001f¢\u0006\u0002\b!H\u0080\b¢\u0006\u0004\b-\u0010.J)\u00101\u001a\u00020\u00132\u0017\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u00130\u001f¢\u0006\u0002\b!H\u0080\b¢\u0006\u0004\b0\u0010$J\u0017\u00106\u001a\u00020\u00132\u0006\u00103\u001a\u000202H\u0000¢\u0006\u0004\b4\u00105J\u0017\u00108\u001a\u00020\u00132\u0006\u00103\u001a\u000202H\u0000¢\u0006\u0004\b7\u00105J\u0017\u0010;\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u000bH\u0001¢\u0006\u0004\b9\u0010:R\u001a\u0010<\u001a\u00020\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?R(\u0010@\u001a\u00020,8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\b@\u0010A\u0012\u0004\bF\u0010G\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER+\u0010N\u001a\u00020\u000b2\u0006\u0010H\u001a\u00020\u000b8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bI\u0010J\u001a\u0004\bK\u0010L\"\u0004\bM\u0010:R \u0010P\u001a\u00020O8GX\u0087\u0004¢\u0006\u0012\n\u0004\bP\u0010Q\u0012\u0004\bT\u0010G\u001a\u0004\bR\u0010SR\u001a\u0010V\u001a\b\u0012\u0004\u0012\u0002020U8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010W¨\u0006Z"}, d2 = {"Landroidx/compose/foundation/text2/input/TextFieldState;", "", "", "initialText", "Landroidx/compose/ui/text/TextRange;", "initialSelectionInChars", "Landroidx/compose/foundation/text2/input/TextUndoManager;", "initialTextUndoManager", "<init>", "(Ljava/lang/String;JLandroidx/compose/foundation/text2/input/TextUndoManager;Lkotlin/jvm/internal/h;)V", "(Ljava/lang/String;JLkotlin/jvm/internal/h;)V", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "previousValue", "Landroidx/compose/foundation/text2/input/InputTransformation;", "inputTransformation", "", "notifyImeOfChanges", "Landroidx/compose/foundation/text2/input/internal/undo/TextFieldEditUndoBehavior;", "undoBehavior", "Lx6/t0;", "commitEditAsUser", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/InputTransformation;ZLandroidx/compose/foundation/text2/input/internal/undo/TextFieldEditUndoBehavior;)V", "postValue", "Landroidx/compose/foundation/text2/input/TextFieldBuffer$ChangeList;", "changes", "recordEditForUndo", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/TextFieldBuffer$ChangeList;Landroidx/compose/foundation/text2/input/internal/undo/TextFieldEditUndoBehavior;)V", "oldValue", "newValue", "notifyIme", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/TextFieldCharSequence;)V", "Lkotlin/Function1;", "Landroidx/compose/foundation/text2/input/TextFieldBuffer;", "Lx6/n;", "block", "edit", "(Lr7/l;)V", "toString", "()Ljava/lang/String;", "value", "startEdit", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;)Landroidx/compose/foundation/text2/input/TextFieldBuffer;", "commitEdit", "(Landroidx/compose/foundation/text2/input/TextFieldBuffer;)V", "Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "editAsUser$foundation_release", "(Landroidx/compose/foundation/text2/input/InputTransformation;ZLandroidx/compose/foundation/text2/input/internal/undo/TextFieldEditUndoBehavior;Lr7/l;)V", "editAsUser", "editWithNoSideEffects$foundation_release", "editWithNoSideEffects", "Landroidx/compose/foundation/text2/input/TextFieldState$NotifyImeListener;", "notifyImeListener", "addNotifyImeListener$foundation_release", "(Landroidx/compose/foundation/text2/input/TextFieldState$NotifyImeListener;)V", "addNotifyImeListener", "removeNotifyImeListener$foundation_release", "removeNotifyImeListener", "resetStateAndNotifyIme$foundation_release", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;)V", "resetStateAndNotifyIme", "textUndoManager", "Landroidx/compose/foundation/text2/input/TextUndoManager;", "getTextUndoManager$foundation_release", "()Landroidx/compose/foundation/text2/input/TextUndoManager;", "mainBuffer", "Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "getMainBuffer$foundation_release", "()Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "setMainBuffer$foundation_release", "(Landroidx/compose/foundation/text2/input/internal/EditingBuffer;)V", "getMainBuffer$foundation_release$annotations", "()V", "<set-?>", "text$delegate", "Landroidx/compose/runtime/MutableState;", "getText", "()Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "setText", "text", "Landroidx/compose/foundation/text2/input/UndoState;", "undoState", "Landroidx/compose/foundation/text2/input/UndoState;", "getUndoState", "()Landroidx/compose/foundation/text2/input/UndoState;", "getUndoState$annotations", "Landroidx/compose/runtime/collection/MutableVector;", "notifyImeListeners", "Landroidx/compose/runtime/collection/MutableVector;", "NotifyImeListener", "Saver", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldState {
    public static final int $stable = 0;
    private EditingBuffer mainBuffer;
    private final MutableVector<NotifyImeListener> notifyImeListeners;

    /* JADX INFO: renamed from: text$delegate, reason: from kotlin metadata */
    private final MutableState text;
    private final TextUndoManager textUndoManager;
    private final UndoState undoState;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bà\u0080\u0001\u0018\u00002\u00020\u0001J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&¢\u0006\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text2/input/TextFieldState$NotifyImeListener;", "", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "oldValue", "newValue", "Lx6/t0;", "onChange", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/TextFieldCharSequence;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface NotifyImeListener {
        void onChange(TextFieldCharSequence oldValue, TextFieldCharSequence newValue);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0003*\u00020\b2\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Landroidx/compose/foundation/text2/input/TextFieldState$Saver;", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/text2/input/TextFieldState;", "", "()V", "restore", "value", "save", "Landroidx/compose/runtime/saveable/SaverScope;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Saver implements androidx.compose.runtime.saveable.Saver<TextFieldState, Object> {
        public static final int $stable = 0;
        public static final Saver INSTANCE = new Saver();

        private Saver() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.compose.runtime.saveable.Saver
        public TextFieldState restore(Object value) {
            List list = (List) value;
            return new TextFieldState((String) list.get(0), TextRangeKt.TextRange(((Integer) list.get(1)).intValue(), ((Integer) list.get(2)).intValue()), TextUndoManager.Companion.Saver.INSTANCE.restore(list.get(3)), (h) null);
        }

        @Override // androidx.compose.runtime.saveable.Saver
        public Object save(SaverScope saverScope, TextFieldState textFieldState) {
            return t7.a.E(textFieldState.getText().toString(), Integer.valueOf(TextRange.m5192getStartimpl(textFieldState.getText().getSelectionInChars())), Integer.valueOf(TextRange.m5187getEndimpl(textFieldState.getText().getSelectionInChars())), TextUndoManager.Companion.Saver.INSTANCE.save(saverScope, textFieldState.getTextUndoManager()));
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TextFieldEditUndoBehavior.values().length];
            try {
                iArr[TextFieldEditUndoBehavior.ClearHistory.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TextFieldEditUndoBehavior.MergeIfPossible.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TextFieldEditUndoBehavior.NeverMerge.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ TextFieldState(String str, long j10, TextUndoManager textUndoManager, h hVar) {
        this(str, j10, textUndoManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void commitEditAsUser(TextFieldCharSequence previousValue, InputTransformation inputTransformation, boolean notifyImeOfChanges, TextFieldEditUndoBehavior undoBehavior) {
        TextFieldCharSequence textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ = TextFieldCharSequenceKt.m1066TextFieldCharSequence3r_uNRQ(this.mainBuffer.toString(), this.mainBuffer.m1084getSelectiond9O1mEE(), this.mainBuffer.m1083getCompositionMzsxiRA());
        if (inputTransformation == null) {
            TextFieldCharSequence text = getText();
            setText(textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ);
            if (notifyImeOfChanges) {
                notifyIme(text, textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ);
            }
            recordEditForUndo(previousValue, getText(), this.mainBuffer.getChangeTracker(), undoBehavior);
            return;
        }
        TextFieldCharSequence text2 = getText();
        if (textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ.contentEquals(text2) && TextRange.m5185equalsimpl0(textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ.getSelectionInChars(), text2.getSelectionInChars())) {
            setText(textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ);
            if (notifyImeOfChanges) {
                notifyIme(text2, textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ);
                return;
            }
            return;
        }
        TextFieldBuffer textFieldBuffer = new TextFieldBuffer(textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ, this.mainBuffer.getChangeTracker(), text2);
        inputTransformation.transformInput(text2, textFieldBuffer);
        TextFieldCharSequence textFieldCharSequenceM1063toTextFieldCharSequenceOEnZFl4$foundation_release = textFieldBuffer.m1063toTextFieldCharSequenceOEnZFl4$foundation_release(textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ.getCompositionInChars());
        if (p.a(textFieldCharSequenceM1063toTextFieldCharSequenceOEnZFl4$foundation_release, textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ)) {
            setText(textFieldCharSequenceM1063toTextFieldCharSequenceOEnZFl4$foundation_release);
            if (notifyImeOfChanges) {
                notifyIme(text2, textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ);
            }
        } else {
            resetStateAndNotifyIme$foundation_release(textFieldCharSequenceM1063toTextFieldCharSequenceOEnZFl4$foundation_release);
        }
        recordEditForUndo(previousValue, getText(), textFieldBuffer.getChanges(), undoBehavior);
    }

    public static /* synthetic */ void editAsUser$foundation_release$default(TextFieldState textFieldState, InputTransformation inputTransformation, boolean z, TextFieldEditUndoBehavior textFieldEditUndoBehavior, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        if ((i10 & 4) != 0) {
            textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        lVar.invoke(textFieldState.getMainBuffer());
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5185equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1084getSelectiond9O1mEE()) && p.a(text.getCompositionInChars(), textFieldState.getMainBuffer().m1083getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, z, textFieldEditUndoBehavior);
    }

    public static /* synthetic */ void getMainBuffer$foundation_release$annotations() {
    }

    public static /* synthetic */ void getUndoState$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyIme(TextFieldCharSequence oldValue, TextFieldCharSequence newValue) {
        MutableVector<NotifyImeListener> mutableVector = this.notifyImeListeners;
        int size = mutableVector.getSize();
        if (size > 0) {
            NotifyImeListener[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                content[i10].onChange(oldValue, newValue);
                i10++;
            } while (i10 < size);
        }
    }

    private final void recordEditForUndo(TextFieldCharSequence previousValue, TextFieldCharSequence postValue, TextFieldBuffer.ChangeList changes, TextFieldEditUndoBehavior undoBehavior) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[undoBehavior.ordinal()];
        if (i10 == 1) {
            this.textUndoManager.clearHistory();
        } else if (i10 == 2) {
            TextUndoManagerKt.recordChanges(this.textUndoManager, previousValue, postValue, changes, true);
        } else {
            if (i10 != 3) {
                return;
            }
            TextUndoManagerKt.recordChanges(this.textUndoManager, previousValue, postValue, changes, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setText(TextFieldCharSequence textFieldCharSequence) {
        this.text.setValue(textFieldCharSequence);
    }

    public final void addNotifyImeListener$foundation_release(NotifyImeListener notifyImeListener) {
        this.notifyImeListeners.add(notifyImeListener);
    }

    public final void commitEdit(TextFieldBuffer newValue) {
        boolean z = newValue.getChanges().getChangeCount() > 0;
        boolean zM5185equalsimpl0 = TextRange.m5185equalsimpl0(newValue.getSelectionInChars(), this.mainBuffer.m1084getSelectiond9O1mEE());
        if (z || !zM5185equalsimpl0) {
            resetStateAndNotifyIme$foundation_release(TextFieldBuffer.m1058toTextFieldCharSequenceOEnZFl4$foundation_release$default(newValue, null, 1, null));
        }
        this.textUndoManager.clearHistory();
    }

    public final void edit(l<? super TextFieldBuffer, t0> block) {
        TextFieldBuffer textFieldBufferStartEdit = startEdit(getText());
        block.invoke(textFieldBufferStartEdit);
        commitEdit(textFieldBufferStartEdit);
    }

    public final void editAsUser$foundation_release(InputTransformation inputTransformation, boolean notifyImeOfChanges, TextFieldEditUndoBehavior undoBehavior, l<? super EditingBuffer, t0> block) {
        TextFieldCharSequence text = getText();
        getMainBuffer().getChangeTracker().clearChanges();
        block.invoke(getMainBuffer());
        if (getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5185equalsimpl0(text.getSelectionInChars(), getMainBuffer().m1084getSelectiond9O1mEE()) && p.a(text.getCompositionInChars(), getMainBuffer().m1083getCompositionMzsxiRA())) {
            return;
        }
        commitEditAsUser(text, inputTransformation, notifyImeOfChanges, undoBehavior);
    }

    public final void editWithNoSideEffects$foundation_release(l<? super EditingBuffer, t0> block) {
        TextFieldCharSequence text = getText();
        getMainBuffer().getChangeTracker().clearChanges();
        block.invoke(getMainBuffer());
        TextFieldCharSequence textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ = TextFieldCharSequenceKt.m1066TextFieldCharSequence3r_uNRQ(getMainBuffer().toString(), getMainBuffer().m1084getSelectiond9O1mEE(), getMainBuffer().m1083getCompositionMzsxiRA());
        setText(textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ);
        notifyIme(text, textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ);
    }

    /* JADX INFO: renamed from: getMainBuffer$foundation_release, reason: from getter */
    public final EditingBuffer getMainBuffer() {
        return this.mainBuffer;
    }

    public final TextFieldCharSequence getText() {
        return (TextFieldCharSequence) this.text.getValue();
    }

    /* JADX INFO: renamed from: getTextUndoManager$foundation_release, reason: from getter */
    public final TextUndoManager getTextUndoManager() {
        return this.textUndoManager;
    }

    public final UndoState getUndoState() {
        return this.undoState;
    }

    public final void removeNotifyImeListener$foundation_release(NotifyImeListener notifyImeListener) {
        this.notifyImeListeners.remove(notifyImeListener);
    }

    public final void resetStateAndNotifyIme$foundation_release(TextFieldCharSequence newValue) {
        TextFieldCharSequence textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ = TextFieldCharSequenceKt.m1066TextFieldCharSequence3r_uNRQ(this.mainBuffer.toString(), this.mainBuffer.m1084getSelectiond9O1mEE(), this.mainBuffer.m1083getCompositionMzsxiRA());
        boolean zA = p.a(newValue.getCompositionInChars(), this.mainBuffer.m1083getCompositionMzsxiRA());
        boolean z = true;
        boolean z5 = false;
        if (!textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ.contentEquals(newValue)) {
            this.mainBuffer = new EditingBuffer(newValue.toString(), newValue.getSelectionInChars(), (h) null);
        } else if (TextRange.m5185equalsimpl0(textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ.getSelectionInChars(), newValue.getSelectionInChars())) {
            z = false;
        } else {
            this.mainBuffer.setSelection(TextRange.m5192getStartimpl(newValue.getSelectionInChars()), TextRange.m5187getEndimpl(newValue.getSelectionInChars()));
            z5 = true;
            z = false;
        }
        TextRange textRangeMo1064getCompositionInCharsMzsxiRA = newValue.getCompositionInChars();
        if (textRangeMo1064getCompositionInCharsMzsxiRA == null || TextRange.m5186getCollapsedimpl(textRangeMo1064getCompositionInCharsMzsxiRA.getPackedValue())) {
            this.mainBuffer.commitComposition();
        } else {
            this.mainBuffer.setComposition(TextRange.m5190getMinimpl(textRangeMo1064getCompositionInCharsMzsxiRA.getPackedValue()), TextRange.m5189getMaximpl(textRangeMo1064getCompositionInCharsMzsxiRA.getPackedValue()));
        }
        if (z || (!z5 && !zA)) {
            this.mainBuffer.commitComposition();
        }
        if (!z) {
            newValue = textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ;
        }
        TextFieldCharSequence textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ2 = TextFieldCharSequenceKt.m1066TextFieldCharSequence3r_uNRQ(newValue, this.mainBuffer.m1084getSelectiond9O1mEE(), this.mainBuffer.m1083getCompositionMzsxiRA());
        setText(textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ2);
        notifyIme(textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ, textFieldCharSequenceM1066TextFieldCharSequence3r_uNRQ2);
    }

    public final void setMainBuffer$foundation_release(EditingBuffer editingBuffer) {
        this.mainBuffer = editingBuffer;
    }

    public final TextFieldBuffer startEdit(TextFieldCharSequence value) {
        return new TextFieldBuffer(value, null, null, 6, null);
    }

    public String toString() {
        return "TextFieldState(selectionInChars=" + ((Object) TextRange.m5195toStringimpl(getText().getSelectionInChars())) + ", text=\"" + ((Object) getText()) + "\")";
    }

    public /* synthetic */ TextFieldState(String str, long j10, h hVar) {
        this(str, j10);
    }

    private TextFieldState(String str, long j10, TextUndoManager textUndoManager) {
        this.textUndoManager = textUndoManager;
        this.mainBuffer = new EditingBuffer(str, TextRangeKt.m5198coerceIn8ffj60Q(j10, 0, str.length()), (h) null);
        this.text = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TextFieldCharSequenceKt.m1068TextFieldCharSequenceFDrldGo(str, j10), null, 2, null);
        this.undoState = new UndoState(this);
        this.notifyImeListeners = new MutableVector<>(new NotifyImeListener[16], 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TextFieldState(String str, long j10, int i10, h hVar) {
        str = (i10 & 1) != 0 ? "" : str;
        this(str, (i10 & 2) != 0 ? TextRangeKt.TextRange(str.length()) : j10, (h) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TextFieldState(String str, long j10) {
        this(str, j10, new TextUndoManager(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0), (h) null);
    }
}
