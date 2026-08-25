package androidx.compose.ui.input.pointer;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.LayoutCoordinates;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J&\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u000b¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u000b¢\u0006\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0018R\u001a\u0010\u001a\u001a\u00020\u00198\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"Landroidx/compose/ui/input/pointer/HitPathTracker;", "", "Landroidx/compose/ui/layout/LayoutCoordinates;", "rootCoordinates", "<init>", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "Landroidx/compose/ui/input/pointer/PointerId;", "pointerId", "", "Landroidx/compose/ui/Modifier$Node;", "pointerInputNodes", "Lx6/t0;", "addHitPath-KNwqfcY", "(JLjava/util/List;)V", "addHitPath", "Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "internalPointerEvent", "", "isInBounds", "dispatchChanges", "(Landroidx/compose/ui/input/pointer/InternalPointerEvent;Z)Z", "processCancel", "()V", "removeDetachedPointerInputFilters", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/input/pointer/NodeParent;", "root", "Landroidx/compose/ui/input/pointer/NodeParent;", "getRoot$ui_release", "()Landroidx/compose/ui/input/pointer/NodeParent;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HitPathTracker {
    public static final int $stable = MutableVector.$stable;
    private final NodeParent root = new NodeParent();
    private final LayoutCoordinates rootCoordinates;

    public HitPathTracker(LayoutCoordinates layoutCoordinates) {
        this.rootCoordinates = layoutCoordinates;
    }

    public static /* synthetic */ boolean dispatchChanges$default(HitPathTracker hitPathTracker, InternalPointerEvent internalPointerEvent, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        return hitPathTracker.dispatchChanges(internalPointerEvent, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /* JADX INFO: renamed from: addHitPath-KNwqfcY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m4446addHitPathKNwqfcY(long r12, java.util.List<? extends androidx.compose.ui.Modifier.Node> r14) {
        /*
            r11 = this;
            androidx.compose.ui.input.pointer.NodeParent r0 = r11.root
            int r1 = r14.size()
            r2 = 1
            r3 = 0
            r4 = r3
        L9:
            if (r4 >= r1) goto L5f
            java.lang.Object r5 = r14.get(r4)
            androidx.compose.ui.Modifier$Node r5 = (androidx.compose.ui.Modifier.Node) r5
            if (r2 == 0) goto L48
            androidx.compose.runtime.collection.MutableVector r6 = r0.getChildren()
            int r7 = r6.getSize()
            if (r7 <= 0) goto L36
            java.lang.Object[] r6 = r6.getContent()
            r8 = r3
        L22:
            r9 = r6[r8]
            r10 = r9
            androidx.compose.ui.input.pointer.Node r10 = (androidx.compose.ui.input.pointer.Node) r10
            androidx.compose.ui.Modifier$Node r10 = r10.getModifierNode()
            boolean r10 = kotlin.jvm.internal.p.a(r10, r5)
            if (r10 == 0) goto L32
            goto L37
        L32:
            int r8 = r8 + 1
            if (r8 < r7) goto L22
        L36:
            r9 = 0
        L37:
            androidx.compose.ui.input.pointer.Node r9 = (androidx.compose.ui.input.pointer.Node) r9
            if (r9 == 0) goto L47
            r9.markIsIn()
            androidx.compose.ui.input.pointer.util.PointerIdArray r0 = r9.getPointerIds()
            r0.m4598add0FcD4WY(r12)
            r0 = r9
            goto L5c
        L47:
            r2 = r3
        L48:
            androidx.compose.ui.input.pointer.Node r6 = new androidx.compose.ui.input.pointer.Node
            r6.<init>(r5)
            androidx.compose.ui.input.pointer.util.PointerIdArray r5 = r6.getPointerIds()
            r5.m4598add0FcD4WY(r12)
            androidx.compose.runtime.collection.MutableVector r0 = r0.getChildren()
            r0.add(r6)
            r0 = r6
        L5c:
            int r4 = r4 + 1
            goto L9
        L5f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.HitPathTracker.m4446addHitPathKNwqfcY(long, java.util.List):void");
    }

    public final boolean dispatchChanges(InternalPointerEvent internalPointerEvent, boolean isInBounds) {
        if (this.root.buildCache(internalPointerEvent.getChanges(), this.rootCoordinates, internalPointerEvent, isInBounds)) {
            return this.root.dispatchFinalEventPass(internalPointerEvent) || this.root.dispatchMainEventPass(internalPointerEvent.getChanges(), this.rootCoordinates, internalPointerEvent, isInBounds);
        }
        return false;
    }

    /* JADX INFO: renamed from: getRoot$ui_release, reason: from getter */
    public final NodeParent getRoot() {
        return this.root;
    }

    public final void processCancel() {
        this.root.dispatchCancel();
        this.root.clear();
    }

    public final void removeDetachedPointerInputFilters() {
        this.root.removeDetachedPointerInputFilters();
    }
}
