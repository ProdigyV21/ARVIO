package androidx.compose.runtime.snapshots;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0000H&¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0000H&¢\u0006\u0004\b\b\u0010\tR\"\u0010\u000b\u001a\u00020\n8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0011\u001a\u0004\u0018\u00010\u00008\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u0007¨\u0006\u0015"}, d2 = {"Landroidx/compose/runtime/snapshots/StateRecord;", "", "<init>", "()V", "value", "Lx6/t0;", "assign", "(Landroidx/compose/runtime/snapshots/StateRecord;)V", "create", "()Landroidx/compose/runtime/snapshots/StateRecord;", "", "snapshotId", "I", "getSnapshotId$runtime_release", "()I", "setSnapshotId$runtime_release", "(I)V", LinkHeader.Rel.Next, "Landroidx/compose/runtime/snapshots/StateRecord;", "getNext$runtime_release", "setNext$runtime_release", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class StateRecord {
    public static final int $stable = 8;
    private StateRecord next;
    private int snapshotId = SnapshotKt.currentSnapshot().getId();

    public abstract void assign(StateRecord value);

    public abstract StateRecord create();

    /* JADX INFO: renamed from: getNext$runtime_release, reason: from getter */
    public final StateRecord getNext() {
        return this.next;
    }

    /* JADX INFO: renamed from: getSnapshotId$runtime_release, reason: from getter */
    public final int getSnapshotId() {
        return this.snapshotId;
    }

    public final void setNext$runtime_release(StateRecord stateRecord) {
        this.next = stateRecord;
    }

    public final void setSnapshotId$runtime_release(int i10) {
        this.snapshotId = i10;
    }
}
