package androidx.compose.runtime.snapshots;

import androidx.compose.material3.d;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J%\u0010\r\u001a\u00020\f2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ;\u0010\u0010\u001a\u00020\u00012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0014\u001a\u00020\nH\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\fH\u0010¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\fH\u0010¢\u0006\u0004\b\u001a\u0010\u0018J\u000f\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\nH\u0016¢\u0006\u0004\b\u001f\u0010\u0013¨\u0006 "}, d2 = {"Landroidx/compose/runtime/snapshots/GlobalSnapshot;", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "", TtmlNode.ATTR_ID, "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "invalid", "<init>", "(ILandroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "Lkotlin/Function1;", "", "Lx6/t0;", "readObserver", "Landroidx/compose/runtime/snapshots/Snapshot;", "takeNestedSnapshot", "(Lr7/l;)Landroidx/compose/runtime/snapshots/Snapshot;", "writeObserver", "takeNestedMutableSnapshot", "(Lr7/l;Lr7/l;)Landroidx/compose/runtime/snapshots/MutableSnapshot;", "notifyObjectsInitialized$runtime_release", "()V", "notifyObjectsInitialized", "snapshot", "", "nestedDeactivated$runtime_release", "(Landroidx/compose/runtime/snapshots/Snapshot;)Ljava/lang/Void;", "nestedDeactivated", "nestedActivated$runtime_release", "nestedActivated", "Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "apply", "()Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "dispose", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class GlobalSnapshot extends MutableSnapshot {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.GlobalSnapshot$takeNestedMutableSnapshot$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<SnapshotIdSet, MutableSnapshot> {
        final /* synthetic */ l<Object, t0> $readObserver;
        final /* synthetic */ l<Object, t0> $writeObserver;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(l<Object, t0> lVar, l<Object, t0> lVar2) {
            super(1);
            this.$readObserver = lVar;
            this.$writeObserver = lVar2;
        }

        @Override // r7.l
        public final MutableSnapshot invoke(SnapshotIdSet snapshotIdSet) {
            int i10;
            synchronized (SnapshotKt.getLock()) {
                i10 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i10 + 1;
            }
            return new MutableSnapshot(i10, snapshotIdSet, this.$readObserver, this.$writeObserver);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.GlobalSnapshot$takeNestedSnapshot$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/snapshots/ReadonlySnapshot;", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06021 extends r implements l<SnapshotIdSet, ReadonlySnapshot> {
        final /* synthetic */ l<Object, t0> $readObserver;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06021(l<Object, t0> lVar) {
            super(1);
            this.$readObserver = lVar;
        }

        @Override // r7.l
        public final ReadonlySnapshot invoke(SnapshotIdSet snapshotIdSet) {
            int i10;
            synchronized (SnapshotKt.getLock()) {
                i10 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i10 + 1;
            }
            return new ReadonlySnapshot(i10, snapshotIdSet, this.$readObserver);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public GlobalSnapshot(int i10, SnapshotIdSet snapshotIdSet) {
        l globalSnapshot$1$1$1;
        synchronized (SnapshotKt.getLock()) {
            List list = SnapshotKt.globalWriteObservers;
            globalSnapshot$1$1$1 = (l) x.T0(list);
            globalSnapshot$1$1$1 = globalSnapshot$1$1$1 == null ? new GlobalSnapshot$1$1$1(list) : globalSnapshot$1$1$1;
        }
        super(i10, snapshotIdSet, null, globalSnapshot$1$1$1);
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    public SnapshotApplyResult apply() {
        throw new IllegalStateException("Cannot apply the global snapshot directly. Call Snapshot.advanceGlobalSnapshot");
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        synchronized (SnapshotKt.getLock()) {
            releasePinnedSnapshotLocked$runtime_release();
        }
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
        SnapshotKt.advanceGlobalSnapshot();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    public MutableSnapshot takeNestedMutableSnapshot(l<Object, t0> readObserver, l<Object, t0> writeObserver) {
        return (MutableSnapshot) SnapshotKt.takeNewSnapshot(new AnonymousClass1(readObserver, writeObserver));
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(l<Object, t0> readObserver) {
        return SnapshotKt.takeNewSnapshot(new C06021(readObserver));
    }
}
