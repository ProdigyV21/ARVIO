package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import t.d0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0001¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u000b\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001d\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Landroidx/compose/runtime/snapshots/NestedMutableSnapshot;", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "", TtmlNode.ATTR_ID, "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "invalid", "Lkotlin/Function1;", "", "Lx6/t0;", "readObserver", "writeObserver", "parent", "<init>", "(ILandroidx/compose/runtime/snapshots/SnapshotIdSet;Lr7/l;Lr7/l;Landroidx/compose/runtime/snapshots/MutableSnapshot;)V", "deactivate", "()V", "dispose", "Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "apply", "()Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "getParent", "()Landroidx/compose/runtime/snapshots/MutableSnapshot;", "", "deactivated", "Z", "Landroidx/compose/runtime/snapshots/Snapshot;", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "root", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NestedMutableSnapshot extends MutableSnapshot {
    public static final int $stable = 8;
    private boolean deactivated;
    private final MutableSnapshot parent;

    public NestedMutableSnapshot(int i10, SnapshotIdSet snapshotIdSet, l<Object, t0> lVar, l<Object, t0> lVar2, MutableSnapshot mutableSnapshot) {
        super(i10, snapshotIdSet, lVar, lVar2);
        this.parent = mutableSnapshot;
        mutableSnapshot.mo3086nestedActivated$runtime_release(this);
    }

    private final void deactivate() {
        if (this.deactivated) {
            return;
        }
        this.deactivated = true;
        this.parent.mo3087nestedDeactivated$runtime_release(this);
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    public SnapshotApplyResult apply() {
        Map<StateRecord, ? extends StateRecord> mapOptimisticMerges;
        if (this.parent.getApplied() || this.parent.getDisposed()) {
            return new SnapshotApplyResult.Failure(this);
        }
        d0 modified = getModified();
        int id = getId();
        if (modified != null) {
            MutableSnapshot mutableSnapshot = this.parent;
            mapOptimisticMerges = SnapshotKt.optimisticMerges(mutableSnapshot, this, mutableSnapshot.getInvalid());
        } else {
            mapOptimisticMerges = null;
        }
        synchronized (SnapshotKt.getLock()) {
            try {
                SnapshotKt.validateOpen(this);
                if (modified == null || modified.f21838d == 0) {
                    closeAndReleasePinning$runtime_release();
                } else {
                    SnapshotApplyResult snapshotApplyResultInnerApplyLocked$runtime_release = innerApplyLocked$runtime_release(this.parent.getId(), mapOptimisticMerges, this.parent.getInvalid());
                    if (!p.a(snapshotApplyResultInnerApplyLocked$runtime_release, SnapshotApplyResult.Success.INSTANCE)) {
                        return snapshotApplyResultInnerApplyLocked$runtime_release;
                    }
                    d0 modified2 = this.parent.getModified();
                    if (modified2 != null) {
                        modified2.i(modified);
                    } else {
                        this.parent.setModified(modified);
                        setModified(null);
                    }
                }
                if (this.parent.getId() < id) {
                    this.parent.advance$runtime_release();
                }
                MutableSnapshot mutableSnapshot2 = this.parent;
                mutableSnapshot2.setInvalid$runtime_release(mutableSnapshot2.getInvalid().clear(id).andNot(getPreviousIds()));
                this.parent.recordPrevious$runtime_release(id);
                this.parent.recordPreviousPinnedSnapshot$runtime_release(takeoverPinnedSnapshot$runtime_release());
                this.parent.recordPreviousList$runtime_release(getPreviousIds());
                this.parent.recordPreviousPinnedSnapshots$runtime_release(getPreviousPinnedSnapshots());
                setApplied$runtime_release(true);
                deactivate();
                return SnapshotApplyResult.Success.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (getDisposed()) {
            return;
        }
        super.dispose();
        deactivate();
    }

    public final MutableSnapshot getParent() {
        return this.parent;
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public Snapshot getRoot() {
        return this.parent.getRoot();
    }
}
