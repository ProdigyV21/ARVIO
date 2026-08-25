package androidx.compose.foundation.gestures;

import androidx.compose.ui.unit.Velocity;
import f7.j;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/Velocity;", "velocity"}, k = 3, mv = {1, 8, 0}, xi = 48)
@f7.e(c = "androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$performFling$1", f = "Scrollable.kt", l = {804, 807, 810}, m = "invokeSuspend")
public final class ScrollingLogic$onDragStopped$performFling$1 extends j implements p<Velocity, d7.d<? super Velocity>, Object> {
    /* synthetic */ long J$0;
    long J$1;
    int label;
    final /* synthetic */ ScrollingLogic this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollingLogic$onDragStopped$performFling$1(ScrollingLogic scrollingLogic, d7.d<? super ScrollingLogic$onDragStopped$performFling$1> dVar) {
        super(2, dVar);
        this.this$0 = scrollingLogic;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        ScrollingLogic$onDragStopped$performFling$1 scrollingLogic$onDragStopped$performFling$1 = new ScrollingLogic$onDragStopped$performFling$1(this.this$0, dVar);
        scrollingLogic$onDragStopped$performFling$1.J$0 = ((Velocity) obj).getPackedValue();
        return scrollingLogic$onDragStopped$performFling$1;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m386invokesFctU(((Velocity) obj).getPackedValue(), (d7.d) obj2);
    }

    /* JADX INFO: renamed from: invoke-sF-c-tU, reason: not valid java name */
    public final Object m386invokesFctU(long j10, d7.d<? super Velocity> dVar) {
        return ((ScrollingLogic$onDragStopped$performFling$1) create(Velocity.m5900boximpl(j10), dVar)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x007b  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            int r0 = r13.label
            r1 = 3
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r0 == 0) goto L2d
            if (r0 == r3) goto L27
            if (r0 == r2) goto L1f
            if (r0 != r1) goto L17
            long r0 = r13.J$1
            long r2 = r13.J$0
            k2.c.G(r14)
            goto L7d
        L17:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L1f:
            long r2 = r13.J$1
            long r5 = r13.J$0
            k2.c.G(r14)
            goto L5d
        L27:
            long r5 = r13.J$0
            k2.c.G(r14)
            goto L43
        L2d:
            k2.c.G(r14)
            long r5 = r13.J$0
            androidx.compose.foundation.gestures.ScrollingLogic r14 = r13.this$0
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher r14 = androidx.compose.foundation.gestures.ScrollingLogic.access$getNestedScrollDispatcher$p(r14)
            r13.J$0 = r5
            r13.label = r3
            java.lang.Object r14 = r14.m4415dispatchPreFlingQWom1Mo(r5, r13)
            if (r14 != r4) goto L43
            goto L7a
        L43:
            androidx.compose.ui.unit.Velocity r14 = (androidx.compose.ui.unit.Velocity) r14
            long r7 = r14.getPackedValue()
            long r7 = androidx.compose.ui.unit.Velocity.m5912minusAH228Gc(r5, r7)
            androidx.compose.foundation.gestures.ScrollingLogic r14 = r13.this$0
            r13.J$0 = r5
            r13.J$1 = r7
            r13.label = r2
            java.lang.Object r14 = r14.m374doFlingAnimationQWom1Mo(r7, r13)
            if (r14 != r4) goto L5c
            goto L7a
        L5c:
            r2 = r7
        L5d:
            androidx.compose.ui.unit.Velocity r14 = (androidx.compose.ui.unit.Velocity) r14
            long r10 = r14.getPackedValue()
            androidx.compose.foundation.gestures.ScrollingLogic r14 = r13.this$0
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher r7 = androidx.compose.foundation.gestures.ScrollingLogic.access$getNestedScrollDispatcher$p(r14)
            long r8 = androidx.compose.ui.unit.Velocity.m5912minusAH228Gc(r2, r10)
            r13.J$0 = r5
            r13.J$1 = r10
            r13.label = r1
            r12 = r13
            java.lang.Object r14 = r7.m4413dispatchPostFlingRZ2iAVY(r8, r10, r12)
            if (r14 != r4) goto L7b
        L7a:
            return r4
        L7b:
            r2 = r5
            r0 = r10
        L7d:
            androidx.compose.ui.unit.Velocity r14 = (androidx.compose.ui.unit.Velocity) r14
            long r4 = r14.getPackedValue()
            long r0 = androidx.compose.ui.unit.Velocity.m5912minusAH228Gc(r0, r4)
            long r0 = androidx.compose.ui.unit.Velocity.m5912minusAH228Gc(r2, r0)
            androidx.compose.ui.unit.Velocity r14 = androidx.compose.ui.unit.Velocity.m5900boximpl(r0)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$performFling$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
