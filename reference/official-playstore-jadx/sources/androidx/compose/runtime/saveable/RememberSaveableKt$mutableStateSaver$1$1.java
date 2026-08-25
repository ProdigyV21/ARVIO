package androidx.compose.runtime.saveable;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/MutableState;", "", "T", "Landroidx/compose/runtime/saveable/SaverScope;", "state", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class RememberSaveableKt$mutableStateSaver$1$1<T> extends r implements p<SaverScope, MutableState<T>, MutableState<Object>> {
    final /* synthetic */ Saver<T, Object> $this_with;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RememberSaveableKt$mutableStateSaver$1$1(Saver<T, Object> saver) {
        super(2);
        this.$this_with = saver;
    }

    @Override // r7.p
    public final MutableState<Object> invoke(SaverScope saverScope, MutableState<T> mutableState) {
        if (!(mutableState instanceof SnapshotMutableState)) {
            throw new IllegalArgumentException("If you use a custom MutableState implementation you have to write a custom Saver and pass it as a saver param to rememberSaveable()");
        }
        Object objSave = this.$this_with.save(saverScope, mutableState.getValue());
        if (objSave != null) {
            return SnapshotStateKt.mutableStateOf(objSave, ((SnapshotMutableState) mutableState).getPolicy());
        }
        return null;
    }
}
