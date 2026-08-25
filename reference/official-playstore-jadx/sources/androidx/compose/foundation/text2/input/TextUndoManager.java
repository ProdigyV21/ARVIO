package androidx.compose.foundation.text2.input;

import androidx.compose.foundation.text2.input.internal.undo.TextUndoOperation;
import androidx.compose.foundation.text2.input.internal.undo.TextUndoOperationKt;
import androidx.compose.foundation.text2.input.internal.undo.UndoManager;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.snapshots.Snapshot;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u0000 \"2\u00020\u0001:\u0001\"B#\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000f\u0010\u000eJ\u0015\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\b¢\u0006\u0004\b\u0013\u0010\nR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0014R/\u0010\u001b\u001a\u0004\u0018\u00010\u00022\b\u0010\u0015\u001a\u0004\u0018\u00010\u00028B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u0012R\u0011\u0010\u001f\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010!\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b \u0010\u001e¨\u0006#"}, d2 = {"Landroidx/compose/foundation/text2/input/TextUndoManager;", "", "Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation;", "initialStagingUndo", "Landroidx/compose/foundation/text2/input/internal/undo/UndoManager;", "undoManager", "<init>", "(Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation;Landroidx/compose/foundation/text2/input/internal/undo/UndoManager;)V", "Lx6/t0;", "flush", "()V", "Landroidx/compose/foundation/text2/input/TextFieldState;", "state", "undo", "(Landroidx/compose/foundation/text2/input/TextFieldState;)V", "redo", "op", "record", "(Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation;)V", "clearHistory", "Landroidx/compose/foundation/text2/input/internal/undo/UndoManager;", "<set-?>", "stagingUndo$delegate", "Landroidx/compose/runtime/MutableState;", "getStagingUndo", "()Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation;", "setStagingUndo", "stagingUndo", "", "getCanUndo", "()Z", "canUndo", "getCanRedo", "canRedo", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextUndoManager {

    /* JADX INFO: renamed from: stagingUndo$delegate, reason: from kotlin metadata */
    private final MutableState stagingUndo;
    private final UndoManager<TextUndoOperation> undoManager;
    public static final int $stable = 8;

    /* JADX WARN: Multi-variable type inference failed */
    public TextUndoManager() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    private final void flush() {
        Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                TextUndoOperation stagingUndo = getStagingUndo();
                if (stagingUndo != null) {
                    this.undoManager.record(stagingUndo);
                }
                setStagingUndo(null);
            } finally {
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
            }
        } finally {
            snapshotCreateNonObservableSnapshot.dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final TextUndoOperation getStagingUndo() {
        return (TextUndoOperation) this.stagingUndo.getValue();
    }

    private final void setStagingUndo(TextUndoOperation textUndoOperation) {
        this.stagingUndo.setValue(textUndoOperation);
    }

    public final void clearHistory() {
        setStagingUndo(null);
        this.undoManager.clearHistory();
    }

    public final boolean getCanRedo() {
        return this.undoManager.getCanRedo$foundation_release() && getStagingUndo() == null;
    }

    public final boolean getCanUndo() {
        return this.undoManager.getCanUndo$foundation_release() || getStagingUndo() != null;
    }

    public final void record(TextUndoOperation op) {
        Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                TextUndoOperation stagingUndo = getStagingUndo();
                if (stagingUndo == null) {
                    setStagingUndo(op);
                    return;
                }
                TextUndoOperation textUndoOperationMerge = TextUndoManagerKt.merge(stagingUndo, op);
                if (textUndoOperationMerge != null) {
                    setStagingUndo(textUndoOperationMerge);
                } else {
                    flush();
                    setStagingUndo(op);
                }
            } finally {
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
            }
        } finally {
            snapshotCreateNonObservableSnapshot.dispose();
        }
    }

    public final void redo(TextFieldState state) {
        if (getCanRedo()) {
            TextUndoOperationKt.redo(state, this.undoManager.redo());
        }
    }

    public final void undo(TextFieldState state) {
        if (getCanUndo()) {
            flush();
            TextUndoOperationKt.undo(state, this.undoManager.undo());
        }
    }

    public TextUndoManager(TextUndoOperation textUndoOperation, UndoManager<TextUndoOperation> undoManager) {
        this.undoManager = undoManager;
        this.stagingUndo = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(textUndoOperation, null, 2, null);
    }

    public /* synthetic */ TextUndoManager(TextUndoOperation textUndoOperation, UndoManager undoManager, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : textUndoOperation, (i10 & 2) != 0 ? new UndoManager(null, null, 100, 3, null) : undoManager);
    }
}
