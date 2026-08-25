package androidx.compose.material3.pulltorefresh;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import d7.d;
import kotlin.Metadata;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\"\u0010\b\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J*\u0010\f\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0011"}, d2 = {"androidx/compose/material3/pulltorefresh/PullToRefreshStateImpl$nestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroidx/compose/ui/geometry/Offset;", "available", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "source", "onPreScroll-OzD1aCk", "(JI)J", "onPreScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPostScroll", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLd7/d;)Ljava/lang/Object;", "onPreFling", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PullToRefreshStateImpl$nestedScrollConnection$1 implements NestedScrollConnection {
    final /* synthetic */ a<Boolean> $enabled;
    final /* synthetic */ PullToRefreshStateImpl this$0;

    public PullToRefreshStateImpl$nestedScrollConnection$1(a<Boolean> aVar, PullToRefreshStateImpl pullToRefreshStateImpl) {
        this.$enabled = aVar;
        this.this$0 = pullToRefreshStateImpl;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    public final /* synthetic */ Object mo368onPostFlingRZ2iAVY(long j10, long j11, d dVar) {
        return androidx.compose.ui.input.nestedscroll.a.a(this, j10, j11, dVar);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo369onPostScrollDzOQY0M(long consumed, long available, int source) {
        return !((Boolean) this.$enabled.invoke()).booleanValue() ? Offset.INSTANCE.m3252getZeroF1C5BW0() : (!NestedScrollSource.m4420equalsimpl0(source, NestedScrollSource.INSTANCE.m4425getDragWNlRxjI()) || Offset.m3237getYimpl(available) <= 0.0f) ? Offset.INSTANCE.m3252getZeroF1C5BW0() : this.this$0.m2334consumeAvailableOffsetMKHz9U(available);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo370onPreFlingQWom1Mo(long r5, d7.d<? super androidx.compose.ui.unit.Velocity> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl$nestedScrollConnection$1$onPreFling$1
            if (r0 == 0) goto L13
            r0 = r7
            androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl$nestedScrollConnection$1$onPreFling$1 r0 = (androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl$nestedScrollConnection$1$onPreFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl$nestedScrollConnection$1$onPreFling$1 r0 = new androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl$nestedScrollConnection$1$onPreFling$1
            r0.<init>(r4, r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            float r5 = r0.F$0
            k2.c.G(r7)
            goto L47
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            k2.c.G(r7)
            androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl r7 = r4.this$0
            float r5 = androidx.compose.ui.unit.Velocity.m5910getYimpl(r5)
            r6 = 0
            r0.F$0 = r6
            r0.label = r2
            java.lang.Object r7 = r7.onRelease(r5, r0)
            e7.a r5 = e7.a.f15033i
            if (r7 != r5) goto L46
            return r5
        L46:
            r5 = r6
        L47:
            java.lang.Number r7 = (java.lang.Number) r7
            float r6 = r7.floatValue()
            long r5 = androidx.compose.ui.unit.VelocityKt.Velocity(r5, r6)
            androidx.compose.ui.unit.Velocity r5 = androidx.compose.ui.unit.Velocity.m5900boximpl(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl$nestedScrollConnection$1.mo370onPreFlingQWom1Mo(long, d7.d):java.lang.Object");
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public long mo371onPreScrollOzD1aCk(long available, int source) {
        return !((Boolean) this.$enabled.invoke()).booleanValue() ? Offset.INSTANCE.m3252getZeroF1C5BW0() : (!NestedScrollSource.m4420equalsimpl0(source, NestedScrollSource.INSTANCE.m4425getDragWNlRxjI()) || Offset.m3237getYimpl(available) >= 0.0f) ? Offset.INSTANCE.m3252getZeroF1C5BW0() : this.this$0.m2334consumeAvailableOffsetMKHz9U(available);
    }
}
