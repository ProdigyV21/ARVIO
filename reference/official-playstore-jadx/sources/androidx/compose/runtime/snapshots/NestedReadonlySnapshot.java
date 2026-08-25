package androidx.compose.runtime.snapshots;

import androidx.compose.material3.d;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import r7.l;
import t.d0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006\u0012\u0006\u0010\n\u001a\u00020\u0001¢\u0006\u0004\b\u000b\u0010\fJ%\u0010\r\u001a\u00020\u00002\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0011\u001a\u00020\bH\u0010¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0015\u0010\u0010J\u0017\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0010¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0001H\u0010¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010!\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0001H\u0010¢\u0006\u0004\b \u0010\u001eR(\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00068\u0010X\u0090\u0004¢\u0006\f\n\u0004\b\t\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\n\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\n\u0010%\u001a\u0004\b&\u0010'R\u0014\u0010)\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0014R\u0014\u0010+\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010'R\u001c\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010,8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.R\"\u00101\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00068PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b0\u0010$¨\u00062"}, d2 = {"Landroidx/compose/runtime/snapshots/NestedReadonlySnapshot;", "Landroidx/compose/runtime/snapshots/Snapshot;", "", TtmlNode.ATTR_ID, "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "invalid", "Lkotlin/Function1;", "", "Lx6/t0;", "readObserver", "parent", "<init>", "(ILandroidx/compose/runtime/snapshots/SnapshotIdSet;Lr7/l;Landroidx/compose/runtime/snapshots/Snapshot;)V", "takeNestedSnapshot", "(Lr7/l;)Landroidx/compose/runtime/snapshots/NestedReadonlySnapshot;", "notifyObjectsInitialized$runtime_release", "()V", "notifyObjectsInitialized", "", "hasPendingChanges", "()Z", "dispose", "Landroidx/compose/runtime/snapshots/StateObject;", "state", "", "recordModified$runtime_release", "(Landroidx/compose/runtime/snapshots/StateObject;)Ljava/lang/Void;", "recordModified", "snapshot", "nestedDeactivated$runtime_release", "(Landroidx/compose/runtime/snapshots/Snapshot;)Ljava/lang/Void;", "nestedDeactivated", "nestedActivated$runtime_release", "nestedActivated", "Lr7/l;", "getReadObserver$runtime_release", "()Lr7/l;", "Landroidx/compose/runtime/snapshots/Snapshot;", "getParent", "()Landroidx/compose/runtime/snapshots/Snapshot;", "getReadOnly", "readOnly", "getRoot", "root", "Lt/d0;", "getModified$runtime_release", "()Lt/d0;", "modified", "getWriteObserver$runtime_release", "writeObserver", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NestedReadonlySnapshot extends Snapshot {
    public static final int $stable = 8;
    private final Snapshot parent;
    private final l<Object, t0> readObserver;

    public NestedReadonlySnapshot(int i10, SnapshotIdSet snapshotIdSet, l<Object, t0> lVar, Snapshot snapshot) {
        super(i10, snapshotIdSet, null);
        this.readObserver = lVar;
        this.parent = snapshot;
        snapshot.mo3086nestedActivated$runtime_release(this);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (getDisposed()) {
            return;
        }
        if (getId() != this.parent.getId()) {
            closeAndReleasePinning$runtime_release();
        }
        this.parent.mo3087nestedDeactivated$runtime_release(this);
        super.dispose();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getModified$runtime_release */
    public d0 getModified() {
        return null;
    }

    public final Snapshot getParent() {
        return this.parent;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getReadObserver$runtime_release, reason: merged with bridge method [inline-methods] */
    public l<Object, t0> getReadObserver() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return true;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot getRoot() {
        return this.parent.getRoot();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public l<Object, t0> getWriteObserver$runtime_release() {
        return null;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean hasPendingChanges() {
        return false;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedActivated$runtime_release, reason: merged with bridge method [inline-methods] */
    public Void mo3086nestedActivated$runtime_release(Snapshot snapshot) {
        throw d.u();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedDeactivated$runtime_release, reason: merged with bridge method [inline-methods] */
    public Void mo3087nestedDeactivated$runtime_release(Snapshot snapshot) {
        throw d.u();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime_release() {
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public /* bridge */ /* synthetic */ Snapshot takeNestedSnapshot(l lVar) {
        return takeNestedSnapshot((l<Object, t0>) lVar);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: recordModified$runtime_release, reason: merged with bridge method [inline-methods] */
    public Void mo3089recordModified$runtime_release(StateObject state) {
        SnapshotKt.reportReadonlySnapshotWrite();
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public NestedReadonlySnapshot takeNestedSnapshot(l<Object, t0> readObserver) {
        return new NestedReadonlySnapshot(getId(), getInvalid(), SnapshotKt.mergedReadObserver$default(readObserver, getReadObserver(), false, 4, null), this.parent);
    }
}
