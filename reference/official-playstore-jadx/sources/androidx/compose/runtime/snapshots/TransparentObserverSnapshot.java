package androidx.compose.runtime.snapshots;

import androidx.compose.material3.d;
import androidx.compose.runtime.ActualJvm_jvmKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import r7.l;
import t.d0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0001\n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0010H\u0010¢\u0006\u0004\b\u0012\u0010\u0013J%\u0010\u0016\u001a\u00020\u00012\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0005H\u0010¢\u0006\u0004\b\u0018\u0010\rJ\u0017\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u0001H\u0010¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010 \u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u0001H\u0010¢\u0006\u0004\b\u001f\u0010\u001dR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010!R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\"R\u0014\u0010\t\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\"RF\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00032\u0014\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0010@VX\u0090\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R(\u0010)\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0010X\u0090\u0004¢\u0006\f\n\u0004\b)\u0010$\u001a\u0004\b*\u0010&R\u001a\u0010,\u001a\u00020+8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u001a\u00100\u001a\u00020\u00018\u0016X\u0096\u0004¢\u0006\f\n\u0004\b0\u0010!\u001a\u0004\b1\u00102R\u0014\u00104\u001a\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b3\u00102R$\u0010;\u001a\u0002052\u0006\u00106\u001a\u0002058V@PX\u0096\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R$\u0010A\u001a\u00020<2\u0006\u00106\u001a\u00020<8P@PX\u0090\u000e¢\u0006\f\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R4\u0010G\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010B2\u000e\u00106\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010B8P@VX\u0090\u000e¢\u0006\f\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u0014\u0010I\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bH\u0010\u000f¨\u0006J"}, d2 = {"Landroidx/compose/runtime/snapshots/TransparentObserverSnapshot;", "Landroidx/compose/runtime/snapshots/Snapshot;", "previousSnapshot", "Lkotlin/Function1;", "", "Lx6/t0;", "specifiedReadObserver", "", "mergeParentObservers", "ownsPreviousSnapshot", "<init>", "(Landroidx/compose/runtime/snapshots/Snapshot;Lr7/l;ZZ)V", "dispose", "()V", "hasPendingChanges", "()Z", "Landroidx/compose/runtime/snapshots/StateObject;", "state", "recordModified$runtime_release", "(Landroidx/compose/runtime/snapshots/StateObject;)V", "recordModified", "readObserver", "takeNestedSnapshot", "(Lr7/l;)Landroidx/compose/runtime/snapshots/Snapshot;", "notifyObjectsInitialized$runtime_release", "notifyObjectsInitialized", "snapshot", "", "nestedActivated$runtime_release", "(Landroidx/compose/runtime/snapshots/Snapshot;)Ljava/lang/Void;", "nestedActivated", "nestedDeactivated$runtime_release", "nestedDeactivated", "Landroidx/compose/runtime/snapshots/Snapshot;", "Z", "<set-?>", "Lr7/l;", "getReadObserver$runtime_release", "()Lr7/l;", "setReadObserver", "(Lr7/l;)V", "writeObserver", "getWriteObserver$runtime_release", "", "threadId", "J", "getThreadId$runtime_release", "()J", "root", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "getCurrentSnapshot", "currentSnapshot", "", "value", "getId", "()I", "setId$runtime_release", "(I)V", TtmlNode.ATTR_ID, "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "getInvalid$runtime_release", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "setInvalid$runtime_release", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "invalid", "Lt/d0;", "getModified$runtime_release", "()Lt/d0;", "setModified", "(Lt/d0;)V", "modified", "getReadOnly", "readOnly", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TransparentObserverSnapshot extends Snapshot {
    public static final int $stable = 8;
    private final boolean mergeParentObservers;
    private final boolean ownsPreviousSnapshot;
    private final Snapshot previousSnapshot;
    private l<Object, t0> readObserver;
    private final Snapshot root;
    private final long threadId;
    private final l<Object, t0> writeObserver;

    public TransparentObserverSnapshot(Snapshot snapshot, l<Object, t0> lVar, boolean z, boolean z5) {
        l<Object, t0> readObserver;
        super(0, SnapshotIdSet.INSTANCE.getEMPTY(), null);
        this.previousSnapshot = snapshot;
        this.mergeParentObservers = z;
        this.ownsPreviousSnapshot = z5;
        this.readObserver = SnapshotKt.mergedReadObserver(lVar, (snapshot == null || (readObserver = snapshot.getReadObserver()) == null) ? ((GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get()).getReadObserver() : readObserver, z);
        this.threadId = ActualJvm_jvmKt.currentThreadId();
        this.root = this;
    }

    private final Snapshot getCurrentSnapshot() {
        Snapshot snapshot = this.previousSnapshot;
        return snapshot == null ? (Snapshot) SnapshotKt.currentGlobalSnapshot.get() : snapshot;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        Snapshot snapshot;
        setDisposed$runtime_release(true);
        if (!this.ownsPreviousSnapshot || (snapshot = this.previousSnapshot) == null) {
            return;
        }
        snapshot.dispose();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public int getId() {
        return getCurrentSnapshot().getId();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getInvalid$runtime_release */
    public SnapshotIdSet getInvalid() {
        return getCurrentSnapshot().getInvalid();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getModified$runtime_release */
    public d0 getModified() {
        return getCurrentSnapshot().getModified();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getReadObserver$runtime_release, reason: merged with bridge method [inline-methods] */
    public l<Object, t0> getReadObserver() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return getCurrentSnapshot().getReadOnly();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot getRoot() {
        return this.root;
    }

    /* JADX INFO: renamed from: getThreadId$runtime_release, reason: from getter */
    public final long getThreadId() {
        return this.threadId;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public l<Object, t0> getWriteObserver$runtime_release() {
        return this.writeObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean hasPendingChanges() {
        return getCurrentSnapshot().hasPendingChanges();
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
        getCurrentSnapshot().notifyObjectsInitialized$runtime_release();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: recordModified$runtime_release */
    public void mo3089recordModified$runtime_release(StateObject state) {
        getCurrentSnapshot().mo3089recordModified$runtime_release(state);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void setId$runtime_release(int i10) {
        throw d.u();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void setInvalid$runtime_release(SnapshotIdSet snapshotIdSet) {
        throw d.u();
    }

    public void setModified(d0 d0Var) {
        throw d.u();
    }

    public void setReadObserver(l<Object, t0> lVar) {
        this.readObserver = lVar;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(l<Object, t0> readObserver) {
        l<Object, t0> lVarMergedReadObserver$default = SnapshotKt.mergedReadObserver$default(readObserver, getReadObserver(), false, 4, null);
        return !this.mergeParentObservers ? SnapshotKt.createTransparentSnapshotWithNoParentReadObserver(getCurrentSnapshot().takeNestedSnapshot(null), lVarMergedReadObserver$default, true) : getCurrentSnapshot().takeNestedSnapshot(lVarMergedReadObserver$default);
    }
}
