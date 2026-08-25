package androidx.compose.runtime;

import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotContextElement;
import d7.j;
import ka.q2;
import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\t\u001a\u0004\u0018\u00010\u00032\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\t\u0010\nJ!\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u000fR\u0018\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Landroidx/compose/runtime/SnapshotContextElementImpl;", "Landroidx/compose/runtime/snapshots/SnapshotContextElement;", "Lka/q2;", "Landroidx/compose/runtime/snapshots/Snapshot;", "snapshot", "<init>", "(Landroidx/compose/runtime/snapshots/Snapshot;)V", "Ld7/j;", "context", "updateThreadContext", "(Ld7/j;)Landroidx/compose/runtime/snapshots/Snapshot;", "oldState", "Lx6/t0;", "restoreThreadContext", "(Ld7/j;Landroidx/compose/runtime/snapshots/Snapshot;)V", "Landroidx/compose/runtime/snapshots/Snapshot;", "Ld7/j$b;", "getKey", "()Ld7/j$b;", "key", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SnapshotContextElementImpl implements SnapshotContextElement, q2<Snapshot> {
    public static final int $stable = 8;
    private final Snapshot snapshot;

    public SnapshotContextElementImpl(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    @Override // androidx.compose.runtime.snapshots.SnapshotContextElement, d7.j
    public <R> R fold(R r4, p<? super R, ? super j.a, ? extends R> pVar) {
        return (R) SnapshotContextElement.DefaultImpls.fold(this, r4, pVar);
    }

    @Override // androidx.compose.runtime.snapshots.SnapshotContextElement, d7.j
    public <E extends j.a> E get(j.b<E> bVar) {
        return (E) SnapshotContextElement.DefaultImpls.get(this, bVar);
    }

    @Override // androidx.compose.runtime.snapshots.SnapshotContextElement, d7.j.a
    public j.b<?> getKey() {
        return SnapshotContextElement.INSTANCE;
    }

    @Override // androidx.compose.runtime.snapshots.SnapshotContextElement, d7.j
    public d7.j minusKey(j.b<?> bVar) {
        return SnapshotContextElement.DefaultImpls.minusKey(this, bVar);
    }

    @Override // androidx.compose.runtime.snapshots.SnapshotContextElement, d7.j
    public d7.j plus(d7.j jVar) {
        return SnapshotContextElement.DefaultImpls.plus(this, jVar);
    }

    @Override // ka.q2
    public void restoreThreadContext(d7.j context, Snapshot oldState) {
        this.snapshot.unsafeLeave(oldState);
    }

    @Override // ka.q2
    public Snapshot updateThreadContext(d7.j context) {
        return this.snapshot.unsafeEnter();
    }
}
