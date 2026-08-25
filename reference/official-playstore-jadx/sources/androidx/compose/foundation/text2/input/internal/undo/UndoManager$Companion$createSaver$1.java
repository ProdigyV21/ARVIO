package androidx.compose.foundation.text2.input.internal.undo;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import java.util.List;
import kotlin.Metadata;
import t7.a;
import z6.c;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u0016J\u001a\u0010\u0006\u001a\u00020\u0003*\u00020\u00072\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0016¨\u0006\b"}, d2 = {"androidx/compose/foundation/text2/input/internal/undo/UndoManager$Companion$createSaver$1", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/text2/input/internal/undo/UndoManager;", "", "restore", "value", "save", "Landroidx/compose/runtime/saveable/SaverScope;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 176)
public final class UndoManager$Companion$createSaver$1<T> implements Saver<UndoManager<T>, Object> {
    final /* synthetic */ Saver<T, Object> $itemSaver;

    public UndoManager$Companion$createSaver$1(Saver<T, Object> saver) {
        this.$itemSaver = saver;
    }

    @Override // androidx.compose.runtime.saveable.Saver
    public UndoManager<T> restore(Object value) {
        List list = (List) value;
        int iIntValue = ((Number) list.get(0)).intValue();
        int iIntValue2 = ((Number) list.get(1)).intValue();
        int iIntValue3 = ((Number) list.get(2)).intValue();
        Saver<T, Object> saver = this.$itemSaver;
        c cVarN = a.n();
        int i10 = 3;
        while (i10 < iIntValue2 + 3) {
            cVarN.add(saver.restore(list.get(i10)));
            i10++;
        }
        c cVarE = a.e(cVarN);
        Saver<T, Object> saver2 = this.$itemSaver;
        c cVarN2 = a.n();
        while (i10 < iIntValue2 + iIntValue3 + 3) {
            cVarN2.add(saver2.restore(list.get(i10)));
            i10++;
        }
        return new UndoManager<>(cVarE, a.e(cVarN2), iIntValue);
    }

    @Override // androidx.compose.runtime.saveable.Saver
    public Object save(SaverScope saverScope, UndoManager<T> undoManager) {
        Saver<T, Object> saver = this.$itemSaver;
        c cVarN = a.n();
        cVarN.add(Integer.valueOf(((UndoManager) undoManager).capacity));
        cVarN.add(Integer.valueOf(((UndoManager) undoManager).undoStack.size()));
        cVarN.add(Integer.valueOf(((UndoManager) undoManager).redoStack.size()));
        SnapshotStateList snapshotStateList = ((UndoManager) undoManager).undoStack;
        int size = snapshotStateList.size();
        for (int i10 = 0; i10 < size; i10++) {
            cVarN.add(saver.save(saverScope, snapshotStateList.get(i10)));
        }
        SnapshotStateList snapshotStateList2 = ((UndoManager) undoManager).redoStack;
        int size2 = snapshotStateList2.size();
        for (int i11 = 0; i11 < size2; i11++) {
            cVarN.add(saver.save(saverScope, snapshotStateList2.get(i11)));
        }
        return a.e(cVarN);
    }
}
