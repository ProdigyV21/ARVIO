package androidx.compose.runtime;

import androidx.compose.runtime.snapshots.Snapshot;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\t\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"T", "", "", "changed", "Landroidx/compose/runtime/snapshots/Snapshot;", "<anonymous parameter 1>", "Lx6/t0;", "invoke", "(Ljava/util/Set;Landroidx/compose/runtime/snapshots/Snapshot;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1$unregisterApplyObserver$1 extends r implements p<Set<? extends Object>, Snapshot, t0> {
    final /* synthetic */ ma.j<Set<Object>> $appliedChanges;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1$unregisterApplyObserver$1(ma.j<Set<Object>> jVar) {
        super(2);
        this.$appliedChanges = jVar;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Set<? extends Object>) obj, (Snapshot) obj2);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void invoke(java.util.Set<? extends java.lang.Object> r17, androidx.compose.runtime.snapshots.Snapshot r18) {
        /*
            r16 = this;
            r0 = r17
            boolean r1 = r0 instanceof androidx.compose.runtime.collection.ScatterSetWrapper
            r2 = 4
            if (r1 == 0) goto L65
            r1 = r0
            androidx.compose.runtime.collection.ScatterSetWrapper r1 = (androidx.compose.runtime.collection.ScatterSetWrapper) r1
            t.j0 r1 = r1.getSet()
            java.lang.Object[] r3 = r1.f21836b
            long[] r1 = r1.f21835a
            int r4 = r1.length
            int r4 = r4 + (-2)
            if (r4 < 0) goto L5d
            r5 = 0
            r6 = r5
        L19:
            r7 = r1[r6]
            long r9 = ~r7
            r11 = 7
            long r9 = r9 << r11
            long r9 = r9 & r7
            r11 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r9 = r9 & r11
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 == 0) goto L60
            int r9 = r6 - r4
            int r9 = ~r9
            int r9 = r9 >>> 31
            r10 = 8
            int r9 = 8 - r9
            r11 = r5
        L33:
            if (r11 >= r9) goto L5a
            r12 = 255(0xff, double:1.26E-321)
            long r12 = r12 & r7
            r14 = 128(0x80, double:6.3E-322)
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 >= 0) goto L56
            int r12 = r6 << 3
            int r12 = r12 + r11
            r12 = r3[r12]
            boolean r13 = r12 instanceof androidx.compose.runtime.snapshots.StateObjectImpl
            if (r13 == 0) goto L53
            androidx.compose.runtime.snapshots.StateObjectImpl r12 = (androidx.compose.runtime.snapshots.StateObjectImpl) r12
            int r13 = androidx.compose.runtime.snapshots.ReaderKind.m3091constructorimpl(r2)
            boolean r12 = r12.m3107isReadInh_f27i8$runtime_release(r13)
            if (r12 == 0) goto L56
        L53:
            r1 = r16
            goto L95
        L56:
            long r7 = r7 >> r10
            int r11 = r11 + 1
            goto L33
        L5a:
            if (r9 != r10) goto L5d
            goto L60
        L5d:
            r1 = r16
            goto L9a
        L60:
            if (r6 == r4) goto L5d
            int r6 = r6 + 1
            goto L19
        L65:
            r1 = r0
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            boolean r3 = r1 instanceof java.util.Collection
            if (r3 == 0) goto L76
            r3 = r1
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L76
            goto L5d
        L76:
            java.util.Iterator r1 = r1.iterator()
        L7a:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L5d
            java.lang.Object r3 = r1.next()
            boolean r4 = r3 instanceof androidx.compose.runtime.snapshots.StateObjectImpl
            if (r4 == 0) goto L53
            androidx.compose.runtime.snapshots.StateObjectImpl r3 = (androidx.compose.runtime.snapshots.StateObjectImpl) r3
            int r4 = androidx.compose.runtime.snapshots.ReaderKind.m3091constructorimpl(r2)
            boolean r3 = r3.m3107isReadInh_f27i8$runtime_release(r4)
            if (r3 == 0) goto L7a
            goto L53
        L95:
            ma.j<java.util.Set<java.lang.Object>> r2 = r1.$appliedChanges
            r2.mo6685trySendJP2dKIU(r0)
        L9a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1$unregisterApplyObserver$1.invoke(java.util.Set, androidx.compose.runtime.snapshots.Snapshot):void");
    }
}
