package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import t.d0;
import t.k0;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u001c\b\u0017\u0018\u0000 v2\u00020\u0001:\u0001vBE\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ?\u0010\u0010\u001a\u00020\u00002\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00062\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0015\u0010\u0016J%\u0010\u0017\u001a\u00020\u00012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0010¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0010¢\u0006\u0004\b\u001d\u0010\u001bJ\u000f\u0010 \u001a\u00020\bH\u0010¢\u0006\u0004\b\u001f\u0010\u0016J\u000f\u0010\"\u001a\u00020\bH\u0010¢\u0006\u0004\b!\u0010\u0016J\u000f\u0010$\u001a\u00020\bH\u0010¢\u0006\u0004\b#\u0010\u0016J5\u0010,\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u00022\u0014\u0010(\u001a\u0010\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020'\u0018\u00010&2\u0006\u0010)\u001a\u00020\u0004H\u0000¢\u0006\u0004\b*\u0010+J$\u00102\u001a\u00028\u0000\"\u0004\b\u0000\u0010-2\f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u00000.H\u0080\b¢\u0006\u0004\b0\u00101J\u000f\u00102\u001a\u00020\bH\u0000¢\u0006\u0004\b0\u0010\u0016J\u0017\u00105\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b3\u00104J\u0017\u00107\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b6\u00104J\u0017\u0010<\u001a\u00020\b2\u0006\u00109\u001a\u000208H\u0000¢\u0006\u0004\b:\u0010;J\u0017\u0010@\u001a\u00020\b2\u0006\u0010=\u001a\u00020\u0004H\u0000¢\u0006\u0004\b>\u0010?J\u0017\u0010E\u001a\u00020\b2\u0006\u0010B\u001a\u00020AH\u0010¢\u0006\u0004\bC\u0010DJ\u000f\u0010F\u001a\u00020\bH\u0002¢\u0006\u0004\bF\u0010\u0016J\u000f\u0010G\u001a\u00020\bH\u0002¢\u0006\u0004\bG\u0010\u0016J\u000f\u0010H\u001a\u00020\bH\u0002¢\u0006\u0004\bH\u0010\u0016J\u000f\u0010I\u001a\u00020\bH\u0002¢\u0006\u0004\bI\u0010\u0016R(\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00068\u0010X\u0090\u0004¢\u0006\f\n\u0004\b\t\u0010J\u001a\u0004\bK\u0010LR(\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00068\u0010X\u0090\u0004¢\u0006\f\n\u0004\b\n\u0010J\u001a\u0004\bM\u0010LR\"\u0010N\u001a\u00020\u00028\u0010@\u0010X\u0090\u000e¢\u0006\u0012\n\u0004\bN\u0010O\u001a\u0004\bP\u0010Q\"\u0004\bR\u00104R:\u0010U\u001a\n\u0012\u0004\u0012\u00020A\u0018\u00010S2\u000e\u0010T\u001a\n\u0012\u0004\u0012\u00020A\u0018\u00010S8\u0010@VX\u0090\u000e¢\u0006\u0012\n\u0004\bU\u0010V\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR*\u0010\\\u001a\n\u0012\u0004\u0012\u00020A\u0018\u00010[8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\\\u0010]\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR\"\u0010b\u001a\u00020\u00048\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bb\u0010c\u001a\u0004\bd\u0010e\"\u0004\bf\u0010?R\"\u0010g\u001a\u0002088\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bg\u0010h\u001a\u0004\bi\u0010j\"\u0004\bk\u0010;R\u0016\u0010=\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u0010OR\"\u0010l\u001a\u00020\r8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bl\u0010m\u001a\u0004\bn\u0010\u000f\"\u0004\bo\u0010pR\u0014\u0010r\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bq\u0010\u000fR\u0014\u0010u\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bs\u0010t¨\u0006w"}, d2 = {"Landroidx/compose/runtime/snapshots/MutableSnapshot;", "Landroidx/compose/runtime/snapshots/Snapshot;", "", TtmlNode.ATTR_ID, "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "invalid", "Lkotlin/Function1;", "", "Lx6/t0;", "readObserver", "writeObserver", "<init>", "(ILandroidx/compose/runtime/snapshots/SnapshotIdSet;Lr7/l;Lr7/l;)V", "", "hasPendingChanges", "()Z", "takeNestedMutableSnapshot", "(Lr7/l;Lr7/l;)Landroidx/compose/runtime/snapshots/MutableSnapshot;", "Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "apply", "()Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "dispose", "()V", "takeNestedSnapshot", "(Lr7/l;)Landroidx/compose/runtime/snapshots/Snapshot;", "snapshot", "nestedActivated$runtime_release", "(Landroidx/compose/runtime/snapshots/Snapshot;)V", "nestedActivated", "nestedDeactivated$runtime_release", "nestedDeactivated", "notifyObjectsInitialized$runtime_release", "notifyObjectsInitialized", "closeLocked$runtime_release", "closeLocked", "releasePinnedSnapshotsForCloseLocked$runtime_release", "releasePinnedSnapshotsForCloseLocked", "snapshotId", "", "Landroidx/compose/runtime/snapshots/StateRecord;", "optimisticMerges", "invalidSnapshots", "innerApplyLocked$runtime_release", "(ILjava/util/Map;Landroidx/compose/runtime/snapshots/SnapshotIdSet;)Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "innerApplyLocked", "T", "Lkotlin/Function0;", "block", "advance$runtime_release", "(Lr7/a;)Ljava/lang/Object;", "advance", "recordPrevious$runtime_release", "(I)V", "recordPrevious", "recordPreviousPinnedSnapshot$runtime_release", "recordPreviousPinnedSnapshot", "", "handles", "recordPreviousPinnedSnapshots$runtime_release", "([I)V", "recordPreviousPinnedSnapshots", "snapshots", "recordPreviousList$runtime_release", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "recordPreviousList", "Landroidx/compose/runtime/snapshots/StateObject;", "state", "recordModified$runtime_release", "(Landroidx/compose/runtime/snapshots/StateObject;)V", "recordModified", "validateNotApplied", "validateNotAppliedOrPinned", "abandon", "releasePreviouslyPinnedSnapshotsLocked", "Lr7/l;", "getReadObserver$runtime_release", "()Lr7/l;", "getWriteObserver$runtime_release", "writeCount", "I", "getWriteCount$runtime_release", "()I", "setWriteCount$runtime_release", "Lt/d0;", "<set-?>", "modified", "Lt/d0;", "getModified$runtime_release", "()Lt/d0;", "setModified", "(Lt/d0;)V", "", "merged", "Ljava/util/List;", "getMerged$runtime_release", "()Ljava/util/List;", "setMerged$runtime_release", "(Ljava/util/List;)V", "previousIds", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "getPreviousIds$runtime_release", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "setPreviousIds$runtime_release", "previousPinnedSnapshots", "[I", "getPreviousPinnedSnapshots$runtime_release", "()[I", "setPreviousPinnedSnapshots$runtime_release", "applied", "Z", "getApplied$runtime_release", "setApplied$runtime_release", "(Z)V", "getReadOnly", "readOnly", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "root", "Companion", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class MutableSnapshot extends Snapshot {
    private boolean applied;
    private List<? extends StateObject> merged;
    private d0 modified;
    private SnapshotIdSet previousIds;
    private int[] previousPinnedSnapshots;
    private final l<Object, t0> readObserver;
    private int snapshots;
    private int writeCount;
    private final l<Object, t0> writeObserver;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final int[] EmptyIntArray = new int[0];

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/compose/runtime/snapshots/MutableSnapshot$Companion;", "", "()V", "EmptyIntArray", "", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private Companion() {
        }
    }

    public MutableSnapshot(int i10, SnapshotIdSet snapshotIdSet, l<Object, t0> lVar, l<Object, t0> lVar2) {
        super(i10, snapshotIdSet, null);
        this.readObserver = lVar;
        this.writeObserver = lVar2;
        this.previousIds = SnapshotIdSet.INSTANCE.getEMPTY();
        this.previousPinnedSnapshots = EmptyIntArray;
        this.snapshots = 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void abandon() {
        /*
            r15 = this;
            t.d0 r0 = r15.getModified()
            if (r0 == 0) goto L77
            r15.validateNotApplied()
            r1 = 0
            r15.setModified(r1)
            int r1 = r15.getId()
            java.lang.Object[] r2 = r0.f21836b
            long[] r0 = r0.f21835a
            int r3 = r0.length
            int r3 = r3 + (-2)
            if (r3 < 0) goto L77
            r4 = 0
            r5 = r4
        L1c:
            r6 = r0[r5]
            long r8 = ~r6
            r10 = 7
            long r8 = r8 << r10
            long r8 = r8 & r6
            r10 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r10
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L72
            int r8 = r5 - r3
            int r8 = ~r8
            int r8 = r8 >>> 31
            r9 = 8
            int r8 = 8 - r8
            r10 = r4
        L36:
            if (r10 >= r8) goto L70
            r11 = 255(0xff, double:1.26E-321)
            long r11 = r11 & r6
            r13 = 128(0x80, double:6.3E-322)
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 >= 0) goto L6c
            int r11 = r5 << 3
            int r11 = r11 + r10
            r11 = r2[r11]
            androidx.compose.runtime.snapshots.StateObject r11 = (androidx.compose.runtime.snapshots.StateObject) r11
            androidx.compose.runtime.snapshots.StateRecord r11 = r11.getFirstStateRecord()
        L4c:
            if (r11 == 0) goto L6c
            int r12 = r11.getSnapshotId()
            if (r12 == r1) goto L64
            androidx.compose.runtime.snapshots.SnapshotIdSet r12 = r15.previousIds
            int r13 = r11.getSnapshotId()
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            boolean r12 = kotlin.collections.x.f0(r12, r13)
            if (r12 == 0) goto L67
        L64:
            r11.setSnapshotId$runtime_release(r4)
        L67:
            androidx.compose.runtime.snapshots.StateRecord r11 = r11.getNext()
            goto L4c
        L6c:
            long r6 = r6 >> r9
            int r10 = r10 + 1
            goto L36
        L70:
            if (r8 != r9) goto L77
        L72:
            if (r5 == r3) goto L77
            int r5 = r5 + 1
            goto L1c
        L77:
            r15.closeAndReleasePinning$runtime_release()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.MutableSnapshot.abandon():void");
    }

    private final void releasePreviouslyPinnedSnapshotsLocked() {
        int length = this.previousPinnedSnapshots.length;
        for (int i10 = 0; i10 < length; i10++) {
            SnapshotKt.releasePinningLocked(this.previousPinnedSnapshots[i10]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MutableSnapshot takeNestedMutableSnapshot$default(MutableSnapshot mutableSnapshot, l lVar, l lVar2, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: takeNestedMutableSnapshot");
        }
        if ((i10 & 1) != 0) {
            lVar = null;
        }
        if ((i10 & 2) != 0) {
            lVar2 = null;
        }
        return mutableSnapshot.takeNestedMutableSnapshot(lVar, lVar2);
    }

    private final void validateNotApplied() {
        if (this.applied) {
            PreconditionsKt.throwIllegalStateException("Unsupported operation on a snapshot that has been applied");
        }
    }

    private final void validateNotAppliedOrPinned() {
        if (!this.applied || ((Snapshot) this).pinningTrackingHandle >= 0) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("Unsupported operation on a disposed or applied snapshot");
    }

    public final <T> T advance$runtime_release(r7.a<? extends T> block) {
        recordPrevious$runtime_release(getId());
        T t2 = (T) block.invoke();
        if (getApplied() || getDisposed()) {
            return t2;
        }
        int id = getId();
        synchronized (SnapshotKt.getLock()) {
            int i10 = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i10 + 1;
            setId$runtime_release(i10);
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
        }
        setInvalid$runtime_release(SnapshotKt.addRange(getInvalid(), id + 1, getId()));
        return t2;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x017f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.compose.runtime.snapshots.SnapshotApplyResult apply() {
        /*
            Method dump skipped, instruction units count: 421
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.MutableSnapshot.apply():androidx.compose.runtime.snapshots.SnapshotApplyResult");
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void closeLocked$runtime_release() {
        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getId()).andNot(this.previousIds);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (getDisposed()) {
            return;
        }
        super.dispose();
        mo3087nestedDeactivated$runtime_release(this);
    }

    /* JADX INFO: renamed from: getApplied$runtime_release, reason: from getter */
    public final boolean getApplied() {
        return this.applied;
    }

    public final List<StateObject> getMerged$runtime_release() {
        return this.merged;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getModified$runtime_release, reason: from getter */
    public d0 getModified() {
        return this.modified;
    }

    /* JADX INFO: renamed from: getPreviousIds$runtime_release, reason: from getter */
    public final SnapshotIdSet getPreviousIds() {
        return this.previousIds;
    }

    /* JADX INFO: renamed from: getPreviousPinnedSnapshots$runtime_release, reason: from getter */
    public final int[] getPreviousPinnedSnapshots() {
        return this.previousPinnedSnapshots;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getReadObserver$runtime_release */
    public l<Object, t0> getReadObserver() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return false;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot getRoot() {
        return this;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getWriteCount$runtime_release, reason: from getter */
    public int getWriteCount() {
        return this.writeCount;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public l<Object, t0> getWriteObserver$runtime_release() {
        return this.writeObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean hasPendingChanges() {
        d0 modified = getModified();
        return modified != null && modified.c();
    }

    public final SnapshotApplyResult innerApplyLocked$runtime_release(int snapshotId, Map<StateRecord, ? extends StateRecord> optimisticMerges, SnapshotIdSet invalidSnapshots) {
        SnapshotIdSet snapshotIdSet;
        Object[] objArr;
        long[] jArr;
        SnapshotIdSet snapshotIdSet2;
        Object[] objArr2;
        long[] jArr2;
        int i10;
        StateRecord stateRecord;
        StateRecord stateRecordMergeRecords;
        SnapshotIdSet snapshotIdSetOr = getInvalid().set(getId()).or(this.previousIds);
        d0 modified = getModified();
        Object[] objArr3 = modified.f21836b;
        long[] jArr3 = modified.f21835a;
        int length = jArr3.length - 2;
        ArrayList arrayList = null;
        ArrayList arrayListI0 = null;
        if (length >= 0) {
            int i11 = 0;
            while (true) {
                long j10 = jArr3[i11];
                if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i12 = 8;
                    int i13 = 8 - ((~(i11 - length)) >>> 31);
                    int i14 = 0;
                    while (i14 < i13) {
                        if ((j10 & 255) < 128) {
                            StateObject stateObject = (StateObject) objArr3[(i11 << 3) + i14];
                            i10 = i12;
                            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
                            objArr2 = objArr3;
                            jArr2 = jArr3;
                            ArrayList arrayList2 = arrayList;
                            StateRecord stateRecord2 = SnapshotKt.readable(firstStateRecord, snapshotId, invalidSnapshots);
                            if (stateRecord2 == null || (stateRecord = SnapshotKt.readable(firstStateRecord, getId(), snapshotIdSetOr)) == null) {
                                snapshotIdSet2 = snapshotIdSetOr;
                            } else {
                                snapshotIdSet2 = snapshotIdSetOr;
                                if (stateRecord.getSnapshotId() != 1 && !stateRecord2.equals(stateRecord)) {
                                    StateRecord stateRecord3 = SnapshotKt.readable(firstStateRecord, getId(), getInvalid());
                                    if (stateRecord3 == null) {
                                        SnapshotKt.readError();
                                        throw new KotlinNothingValueException();
                                    }
                                    if (optimisticMerges == null || (stateRecordMergeRecords = optimisticMerges.get(stateRecord2)) == null) {
                                        stateRecordMergeRecords = stateObject.mergeRecords(stateRecord, stateRecord2, stateRecord3);
                                    }
                                    if (stateRecordMergeRecords == null) {
                                        return new SnapshotApplyResult.Failure(this);
                                    }
                                    if (!stateRecordMergeRecords.equals(stateRecord3)) {
                                        if (stateRecordMergeRecords.equals(stateRecord2)) {
                                            ArrayList arrayList3 = arrayList2 == null ? new ArrayList() : arrayList2;
                                            arrayList3.add(new x(stateObject, stateRecord2.create()));
                                            if (arrayListI0 == null) {
                                                arrayListI0 = new ArrayList();
                                            }
                                            arrayListI0.add(stateObject);
                                            arrayList = arrayList3;
                                        } else {
                                            arrayList = arrayList2 == null ? new ArrayList() : arrayList2;
                                            arrayList.add(!stateRecordMergeRecords.equals(stateRecord) ? new x(stateObject, stateRecordMergeRecords) : new x(stateObject, stateRecord.create()));
                                        }
                                    }
                                }
                            }
                            arrayList = arrayList2;
                        } else {
                            snapshotIdSet2 = snapshotIdSetOr;
                            objArr2 = objArr3;
                            jArr2 = jArr3;
                            i10 = i12;
                        }
                        j10 >>= i10;
                        i14++;
                        i12 = i10;
                        objArr3 = objArr2;
                        jArr3 = jArr2;
                        snapshotIdSetOr = snapshotIdSet2;
                    }
                    snapshotIdSet = snapshotIdSetOr;
                    objArr = objArr3;
                    jArr = jArr3;
                    if (i13 != i12) {
                        break;
                    }
                } else {
                    snapshotIdSet = snapshotIdSetOr;
                    objArr = objArr3;
                    jArr = jArr3;
                }
                if (i11 == length) {
                    break;
                }
                i11++;
                objArr3 = objArr;
                jArr3 = jArr;
                snapshotIdSetOr = snapshotIdSet;
            }
        }
        if (arrayList != null) {
            advance$runtime_release();
            int size = arrayList.size();
            for (int i15 = 0; i15 < size; i15++) {
                x xVar = (x) arrayList.get(i15);
                StateObject stateObject2 = (StateObject) xVar.f22608i;
                StateRecord stateRecord4 = (StateRecord) xVar.f22609l;
                stateRecord4.setSnapshotId$runtime_release(getId());
                synchronized (SnapshotKt.getLock()) {
                    stateRecord4.setNext$runtime_release(stateObject2.getFirstStateRecord());
                    stateObject2.prependStateRecord(stateRecord4);
                }
            }
        }
        if (arrayListI0 != null) {
            int size2 = arrayListI0.size();
            for (int i16 = 0; i16 < size2; i16++) {
                modified.j((StateObject) arrayListI0.get(i16));
            }
            List<? extends StateObject> list = this.merged;
            if (list != null) {
                arrayListI0 = kotlin.collections.x.I0(list, arrayListI0);
            }
            this.merged = arrayListI0;
        }
        return SnapshotApplyResult.Success.INSTANCE;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedActivated$runtime_release */
    public void mo3086nestedActivated$runtime_release(Snapshot snapshot) {
        this.snapshots++;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedDeactivated$runtime_release */
    public void mo3087nestedDeactivated$runtime_release(Snapshot snapshot) {
        if (!(this.snapshots > 0)) {
            PreconditionsKt.throwIllegalArgumentException("no pending nested snapshots");
        }
        int i10 = this.snapshots - 1;
        this.snapshots = i10;
        if (i10 != 0 || this.applied) {
            return;
        }
        abandon();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime_release() {
        if (this.applied || getDisposed()) {
            return;
        }
        advance$runtime_release();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: recordModified$runtime_release */
    public void mo3089recordModified$runtime_release(StateObject state) {
        d0 modified = getModified();
        if (modified == null) {
            int i10 = k0.f21841a;
            modified = new d0();
            setModified(modified);
        }
        modified.d(state);
    }

    public final void recordPrevious$runtime_release(int id) {
        synchronized (SnapshotKt.getLock()) {
            this.previousIds = this.previousIds.set(id);
        }
    }

    public final void recordPreviousList$runtime_release(SnapshotIdSet snapshots) {
        synchronized (SnapshotKt.getLock()) {
            this.previousIds = this.previousIds.or(snapshots);
        }
    }

    public final void recordPreviousPinnedSnapshot$runtime_release(int id) {
        if (id >= 0) {
            int[] iArr = this.previousPinnedSnapshots;
            int length = iArr.length;
            int[] iArrCopyOf = Arrays.copyOf(iArr, length + 1);
            iArrCopyOf[length] = id;
            this.previousPinnedSnapshots = iArrCopyOf;
        }
    }

    public final void recordPreviousPinnedSnapshots$runtime_release(int[] handles) {
        if (handles.length == 0) {
            return;
        }
        int[] iArr = this.previousPinnedSnapshots;
        if (iArr.length != 0) {
            int length = iArr.length;
            int length2 = handles.length;
            int[] iArrCopyOf = Arrays.copyOf(iArr, length + length2);
            System.arraycopy(handles, 0, iArrCopyOf, length, length2);
            handles = iArrCopyOf;
        }
        this.previousPinnedSnapshots = handles;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void releasePinnedSnapshotsForCloseLocked$runtime_release() {
        releasePreviouslyPinnedSnapshotsLocked();
        super.releasePinnedSnapshotsForCloseLocked$runtime_release();
    }

    public final void setApplied$runtime_release(boolean z) {
        this.applied = z;
    }

    public final void setMerged$runtime_release(List<? extends StateObject> list) {
        this.merged = list;
    }

    public void setModified(d0 d0Var) {
        this.modified = d0Var;
    }

    public final void setPreviousIds$runtime_release(SnapshotIdSet snapshotIdSet) {
        this.previousIds = snapshotIdSet;
    }

    public final void setPreviousPinnedSnapshots$runtime_release(int[] iArr) {
        this.previousPinnedSnapshots = iArr;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void setWriteCount$runtime_release(int i10) {
        this.writeCount = i10;
    }

    public MutableSnapshot takeNestedMutableSnapshot(l<Object, t0> readObserver, l<Object, t0> writeObserver) throws Throwable {
        int i10;
        SnapshotIdSet invalid;
        validateNotDisposed$runtime_release();
        validateNotAppliedOrPinned();
        recordPrevious$runtime_release(getId());
        synchronized (SnapshotKt.getLock()) {
            try {
                i10 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i10 + 1;
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(i10);
                invalid = getInvalid();
                setInvalid$runtime_release(invalid.set(i10));
            } catch (Throwable th) {
                th = th;
            }
            try {
                NestedMutableSnapshot nestedMutableSnapshot = new NestedMutableSnapshot(i10, SnapshotKt.addRange(invalid, getId() + 1, i10), SnapshotKt.mergedReadObserver$default(readObserver, getReadObserver(), false, 4, null), SnapshotKt.mergedWriteObserver(writeObserver, getWriteObserver$runtime_release()), this);
                if (getApplied() || getDisposed()) {
                    return nestedMutableSnapshot;
                }
                int id = getId();
                synchronized (SnapshotKt.getLock()) {
                    int i11 = SnapshotKt.nextSnapshotId;
                    SnapshotKt.nextSnapshotId = i11 + 1;
                    setId$runtime_release(i11);
                    SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
                }
                setInvalid$runtime_release(SnapshotKt.addRange(getInvalid(), id + 1, getId()));
                return nestedMutableSnapshot;
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(l<Object, t0> readObserver) {
        NestedReadonlySnapshot nestedReadonlySnapshot;
        validateNotDisposed$runtime_release();
        validateNotAppliedOrPinned();
        int id = getId();
        recordPrevious$runtime_release(getId());
        synchronized (SnapshotKt.getLock()) {
            int i10 = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i10 + 1;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(i10);
            nestedReadonlySnapshot = new NestedReadonlySnapshot(i10, SnapshotKt.addRange(getInvalid(), id + 1, i10), SnapshotKt.mergedReadObserver$default(readObserver, getReadObserver(), false, 4, null), this);
        }
        if (getApplied() || getDisposed()) {
            return nestedReadonlySnapshot;
        }
        int id2 = getId();
        synchronized (SnapshotKt.getLock()) {
            int i11 = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i11 + 1;
            setId$runtime_release(i11);
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
        }
        setInvalid$runtime_release(SnapshotKt.addRange(getInvalid(), id2 + 1, getId()));
        return nestedReadonlySnapshot;
    }

    public final void advance$runtime_release() {
        recordPrevious$runtime_release(getId());
        if (getApplied() || getDisposed()) {
            return;
        }
        int id = getId();
        synchronized (SnapshotKt.getLock()) {
            int i10 = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i10 + 1;
            setId$runtime_release(i10);
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
        }
        setInvalid$runtime_release(SnapshotKt.addRange(getInvalid(), id + 1, getId()));
    }
}
