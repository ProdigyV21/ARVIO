package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\"\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ*\u0010\u0012\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J#\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001f"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollableNestedScrollConnection;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "scrollingLogic", "", "enabled", "<init>", "(Landroidx/compose/foundation/gestures/ScrollingLogic;Z)V", "Landroidx/compose/ui/geometry/Offset;", "available", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "source", "onPreScroll-OzD1aCk", "(JI)J", "onPreScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPostScroll", "Landroidx/compose/ui/unit/Velocity;", "onPostFling-RZ2iAVY", "(JJLd7/d;)Ljava/lang/Object;", "onPostFling", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "getScrollingLogic", "()Landroidx/compose/foundation/gestures/ScrollingLogic;", "Z", "getEnabled", "()Z", "setEnabled", "(Z)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ScrollableNestedScrollConnection implements NestedScrollConnection {
    private boolean enabled;
    private final ScrollingLogic scrollingLogic;

    public ScrollableNestedScrollConnection(ScrollingLogic scrollingLogic, boolean z) {
        this.scrollingLogic = scrollingLogic;
        this.enabled = z;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final ScrollingLogic getScrollingLogic() {
        return this.scrollingLogic;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo368onPostFlingRZ2iAVY(long r3, long r5, d7.d<? super androidx.compose.ui.unit.Velocity> r7) {
        /*
            r2 = this;
            boolean r3 = r7 instanceof androidx.compose.foundation.gestures.ScrollableNestedScrollConnection$onPostFling$1
            if (r3 == 0) goto L13
            r3 = r7
            androidx.compose.foundation.gestures.ScrollableNestedScrollConnection$onPostFling$1 r3 = (androidx.compose.foundation.gestures.ScrollableNestedScrollConnection$onPostFling$1) r3
            int r4 = r3.label
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r4 & r0
            if (r1 == 0) goto L13
            int r4 = r4 - r0
            r3.label = r4
            goto L18
        L13:
            androidx.compose.foundation.gestures.ScrollableNestedScrollConnection$onPostFling$1 r3 = new androidx.compose.foundation.gestures.ScrollableNestedScrollConnection$onPostFling$1
            r3.<init>(r2, r7)
        L18:
            java.lang.Object r4 = r3.result
            int r7 = r3.label
            r0 = 1
            if (r7 == 0) goto L33
            if (r7 != r0) goto L2b
            long r5 = r3.J$0
            java.lang.Object r3 = r3.L$0
            androidx.compose.foundation.gestures.ScrollableNestedScrollConnection r3 = (androidx.compose.foundation.gestures.ScrollableNestedScrollConnection) r3
            k2.c.G(r4)
            goto L4c
        L2b:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r4)
            throw r3
        L33:
            k2.c.G(r4)
            boolean r4 = r2.enabled
            if (r4 == 0) goto L57
            androidx.compose.foundation.gestures.ScrollingLogic r4 = r2.scrollingLogic
            r3.L$0 = r2
            r3.J$0 = r5
            r3.label = r0
            java.lang.Object r4 = r4.m374doFlingAnimationQWom1Mo(r5, r3)
            e7.a r3 = e7.a.f15033i
            if (r4 != r3) goto L4b
            return r3
        L4b:
            r3 = r2
        L4c:
            androidx.compose.ui.unit.Velocity r4 = (androidx.compose.ui.unit.Velocity) r4
            long r0 = r4.getPackedValue()
            long r4 = androidx.compose.ui.unit.Velocity.m5912minusAH228Gc(r5, r0)
            goto L5e
        L57:
            androidx.compose.ui.unit.Velocity$Companion r3 = androidx.compose.ui.unit.Velocity.INSTANCE
            long r4 = r3.m5920getZero9UxMQ8M()
            r3 = r2
        L5e:
            androidx.compose.ui.unit.Velocity r4 = androidx.compose.ui.unit.Velocity.m5900boximpl(r4)
            r4.getPackedValue()
            androidx.compose.foundation.gestures.ScrollingLogic r3 = r3.scrollingLogic
            r5 = 0
            r3.registerNestedFling(r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollableNestedScrollConnection.mo368onPostFlingRZ2iAVY(long, long, d7.d):java.lang.Object");
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M, reason: not valid java name */
    public long mo369onPostScrollDzOQY0M(long consumed, long available, int source) {
        return this.enabled ? this.scrollingLogic.m376performRawScrollMKHz9U(available) : Offset.INSTANCE.m3252getZeroF1C5BW0();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo, reason: not valid java name */
    public final /* synthetic */ Object mo370onPreFlingQWom1Mo(long j10, d7.d dVar) {
        return androidx.compose.ui.input.nestedscroll.a.c(this, j10, dVar);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk, reason: not valid java name */
    public long mo371onPreScrollOzD1aCk(long available, int source) {
        if (NestedScrollSource.m4420equalsimpl0(source, NestedScrollSource.INSTANCE.m4426getFlingWNlRxjI())) {
            this.scrollingLogic.registerNestedFling(true);
        }
        return Offset.INSTANCE.m3252getZeroF1C5BW0();
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }
}
