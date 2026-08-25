package androidx.compose.foundation.text2.input;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\bJ\r\u0010\n\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000bR\u0017\u0010\u0010\u001a\u00020\f8F¢\u0006\f\u0012\u0004\b\u000f\u0010\b\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0013\u001a\u00020\f8F¢\u0006\f\u0012\u0004\b\u0012\u0010\b\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/text2/input/UndoState;", "", "Landroidx/compose/foundation/text2/input/TextFieldState;", "state", "<init>", "(Landroidx/compose/foundation/text2/input/TextFieldState;)V", "Lx6/t0;", "undo", "()V", "redo", "clearHistory", "Landroidx/compose/foundation/text2/input/TextFieldState;", "", "getCanUndo", "()Z", "getCanUndo$annotations", "canUndo", "getCanRedo", "getCanRedo$annotations", "canRedo", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UndoState {
    public static final int $stable = 0;
    private final TextFieldState state;

    public UndoState(TextFieldState textFieldState) {
        this.state = textFieldState;
    }

    public static /* synthetic */ void getCanRedo$annotations() {
    }

    public static /* synthetic */ void getCanUndo$annotations() {
    }

    public final void clearHistory() {
        this.state.getTextUndoManager().clearHistory();
    }

    public final boolean getCanRedo() {
        return this.state.getTextUndoManager().getCanRedo();
    }

    public final boolean getCanUndo() {
        return this.state.getTextUndoManager().getCanUndo();
    }

    public final void redo() {
        this.state.getTextUndoManager().redo(this.state);
    }

    public final void undo() {
        this.state.getTextUndoManager().undo(this.state);
    }
}
