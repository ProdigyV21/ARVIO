package androidx.compose.runtime.snapshots;

import androidx.compose.material3.d;
import androidx.compose.runtime.ActualJvm_jvmKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import r7.l;
import t.d0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001BM\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0014H\u0010¢\u0006\u0004\b\u0016\u0010\u0017J%\u0010\u001b\u001a\u00020\u001a2\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ;\u0010\u001e\u001a\u00020\u00012\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00032\u0014\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010!\u001a\u00020\u0005H\u0010¢\u0006\u0004\b \u0010\u000eJ\u0017\u0010&\u001a\u00020#2\u0006\u0010\"\u001a\u00020\u001aH\u0010¢\u0006\u0004\b$\u0010%J\u0017\u0010(\u001a\u00020#2\u0006\u0010\"\u001a\u00020\u001aH\u0010¢\u0006\u0004\b'\u0010%R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010)R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010*R\u0014\u0010\n\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010*RF\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00032\u0014\u0010+\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0010@VX\u0090\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100RF\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00032\u0014\u0010+\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038\u0010@VX\u0090\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010,\u001a\u0004\b1\u0010.\"\u0004\b2\u00100R\u001a\u00104\u001a\u0002038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R\u0014\u0010:\u001a\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R$\u0010A\u001a\u00020;2\u0006\u0010<\u001a\u00020;8V@PX\u0096\u000e¢\u0006\f\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R$\u0010G\u001a\u00020B2\u0006\u0010<\u001a\u00020B8P@PX\u0090\u000e¢\u0006\f\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR4\u0010M\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010H2\u000e\u0010<\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010H8P@VX\u0090\u000e¢\u0006\f\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR$\u0010P\u001a\u00020;2\u0006\u0010<\u001a\u00020;8P@PX\u0090\u000e¢\u0006\f\u001a\u0004\bN\u0010>\"\u0004\bO\u0010@R\u0014\u0010R\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010\u0010¨\u0006S"}, d2 = {"Landroidx/compose/runtime/snapshots/TransparentObserverMutableSnapshot;", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "parentSnapshot", "Lkotlin/Function1;", "", "Lx6/t0;", "specifiedReadObserver", "specifiedWriteObserver", "", "mergeParentObservers", "ownsParentSnapshot", "<init>", "(Landroidx/compose/runtime/snapshots/MutableSnapshot;Lr7/l;Lr7/l;ZZ)V", "dispose", "()V", "hasPendingChanges", "()Z", "Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "apply", "()Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "Landroidx/compose/runtime/snapshots/StateObject;", "state", "recordModified$runtime_release", "(Landroidx/compose/runtime/snapshots/StateObject;)V", "recordModified", "readObserver", "Landroidx/compose/runtime/snapshots/Snapshot;", "takeNestedSnapshot", "(Lr7/l;)Landroidx/compose/runtime/snapshots/Snapshot;", "writeObserver", "takeNestedMutableSnapshot", "(Lr7/l;Lr7/l;)Landroidx/compose/runtime/snapshots/MutableSnapshot;", "notifyObjectsInitialized$runtime_release", "notifyObjectsInitialized", "snapshot", "", "nestedActivated$runtime_release", "(Landroidx/compose/runtime/snapshots/Snapshot;)Ljava/lang/Void;", "nestedActivated", "nestedDeactivated$runtime_release", "nestedDeactivated", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "Z", "<set-?>", "Lr7/l;", "getReadObserver$runtime_release", "()Lr7/l;", "setReadObserver", "(Lr7/l;)V", "getWriteObserver$runtime_release", "setWriteObserver", "", "threadId", "J", "getThreadId$runtime_release", "()J", "getCurrentSnapshot", "()Landroidx/compose/runtime/snapshots/MutableSnapshot;", "currentSnapshot", "", "value", "getId", "()I", "setId$runtime_release", "(I)V", TtmlNode.ATTR_ID, "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "getInvalid$runtime_release", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "setInvalid$runtime_release", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "invalid", "Lt/d0;", "getModified$runtime_release", "()Lt/d0;", "setModified", "(Lt/d0;)V", "modified", "getWriteCount$runtime_release", "setWriteCount$runtime_release", "writeCount", "getReadOnly", "readOnly", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TransparentObserverMutableSnapshot extends MutableSnapshot {
    public static final int $stable = 8;
    private final boolean mergeParentObservers;
    private final boolean ownsParentSnapshot;
    private final MutableSnapshot parentSnapshot;
    private l<Object, t0> readObserver;
    private final long threadId;
    private l<Object, t0> writeObserver;

    public TransparentObserverMutableSnapshot(MutableSnapshot mutableSnapshot, l<Object, t0> lVar, l<Object, t0> lVar2, boolean z, boolean z5) {
        l<Object, t0> writeObserver$runtime_release;
        l<Object, t0> readObserver;
        super(0, SnapshotIdSet.INSTANCE.getEMPTY(), SnapshotKt.mergedReadObserver(lVar, (mutableSnapshot == null || (readObserver = mutableSnapshot.getReadObserver()) == null) ? ((GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get()).getReadObserver() : readObserver, z), SnapshotKt.mergedWriteObserver(lVar2, (mutableSnapshot == null || (writeObserver$runtime_release = mutableSnapshot.getWriteObserver$runtime_release()) == null) ? ((GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get()).getWriteObserver$runtime_release() : writeObserver$runtime_release));
        this.parentSnapshot = mutableSnapshot;
        this.mergeParentObservers = z;
        this.ownsParentSnapshot = z5;
        this.readObserver = super.getReadObserver();
        this.writeObserver = super.getWriteObserver$runtime_release();
        this.threadId = ActualJvm_jvmKt.currentThreadId();
    }

    private final MutableSnapshot getCurrentSnapshot() {
        MutableSnapshot mutableSnapshot = this.parentSnapshot;
        return mutableSnapshot == null ? (MutableSnapshot) SnapshotKt.currentGlobalSnapshot.get() : mutableSnapshot;
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    public SnapshotApplyResult apply() {
        return getCurrentSnapshot().apply();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        MutableSnapshot mutableSnapshot;
        setDisposed$runtime_release(true);
        if (!this.ownsParentSnapshot || (mutableSnapshot = this.parentSnapshot) == null) {
            return;
        }
        mutableSnapshot.dispose();
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

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getModified$runtime_release */
    public d0 getModified() {
        return getCurrentSnapshot().getModified();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getReadObserver$runtime_release, reason: merged with bridge method [inline-methods] */
    public l<Object, t0> getReadObserver() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return getCurrentSnapshot().getReadOnly();
    }

    /* JADX INFO: renamed from: getThreadId$runtime_release, reason: from getter */
    public final long getThreadId() {
        return this.threadId;
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getWriteCount$runtime_release */
    public int getWriteCount() {
        return getCurrentSnapshot().getWriteCount();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public l<Object, t0> getWriteObserver$runtime_release() {
        return this.writeObserver;
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public boolean hasPendingChanges() {
        return getCurrentSnapshot().hasPendingChanges();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedActivated$runtime_release, reason: merged with bridge method [inline-methods] */
    public Void mo3086nestedActivated$runtime_release(Snapshot snapshot) {
        throw d.u();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedDeactivated$runtime_release, reason: merged with bridge method [inline-methods] */
    public Void mo3087nestedDeactivated$runtime_release(Snapshot snapshot) {
        throw d.u();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime_release() {
        getCurrentSnapshot().notifyObjectsInitialized$runtime_release();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
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

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    public void setModified(d0 d0Var) {
        throw d.u();
    }

    public void setReadObserver(l<Object, t0> lVar) {
        this.readObserver = lVar;
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public void setWriteCount$runtime_release(int i10) {
        getCurrentSnapshot().setWriteCount$runtime_release(i10);
    }

    public void setWriteObserver(l<Object, t0> lVar) {
        this.writeObserver = lVar;
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    public MutableSnapshot takeNestedMutableSnapshot(l<Object, t0> readObserver, l<Object, t0> writeObserver) {
        l<Object, t0> lVarMergedReadObserver$default = SnapshotKt.mergedReadObserver$default(readObserver, getReadObserver(), false, 4, null);
        l<Object, t0> lVarMergedWriteObserver = SnapshotKt.mergedWriteObserver(writeObserver, getWriteObserver$runtime_release());
        return !this.mergeParentObservers ? new TransparentObserverMutableSnapshot(getCurrentSnapshot().takeNestedMutableSnapshot(null, lVarMergedWriteObserver), lVarMergedReadObserver$default, lVarMergedWriteObserver, false, true) : getCurrentSnapshot().takeNestedMutableSnapshot(lVarMergedReadObserver$default, lVarMergedWriteObserver);
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(l<Object, t0> readObserver) {
        l<Object, t0> lVarMergedReadObserver$default = SnapshotKt.mergedReadObserver$default(readObserver, getReadObserver(), false, 4, null);
        return !this.mergeParentObservers ? SnapshotKt.createTransparentSnapshotWithNoParentReadObserver(getCurrentSnapshot().takeNestedSnapshot(null), lVarMergedReadObserver$default, true) : getCurrentSnapshot().takeNestedSnapshot(lVarMergedReadObserver$default);
    }
}
