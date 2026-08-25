package androidx.compose.runtime.saveable;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/MutableState;", "T", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class RememberSaveableKt$mutableStateSaver$1$2<T> extends r implements l<MutableState<Object>, MutableState<T>> {
    final /* synthetic */ Saver<T, Object> $this_with;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RememberSaveableKt$mutableStateSaver$1$2(Saver<T, Object> saver) {
        super(1);
        this.$this_with = saver;
    }

    @Override // r7.l
    public final MutableState<T> invoke(MutableState<Object> mutableState) {
        if (mutableState instanceof SnapshotMutableState) {
            return SnapshotStateKt.mutableStateOf(mutableState.getValue() != null ? this.$this_with.restore(mutableState.getValue()) : null, ((SnapshotMutableState) mutableState).getPolicy());
        }
        throw new IllegalArgumentException("Failed requirement.");
    }
}
