package androidx.compose.material3;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\"\u0010\b\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J*\u0010\f\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ#\u0010\u0010\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0011"}, d2 = {"androidx/compose/material3/ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroidx/compose/ui/geometry/Offset;", "available", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "source", "onPreScroll-OzD1aCk", "(JI)J", "onPreScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPostScroll", "Landroidx/compose/ui/unit/Velocity;", "onPostFling-RZ2iAVY", "(JJLd7/d;)Ljava/lang/Object;", "onPostFling", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1 implements NestedScrollConnection {
    final /* synthetic */ ExitUntilCollapsedScrollBehavior this$0;

    public ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1(ExitUntilCollapsedScrollBehavior exitUntilCollapsedScrollBehavior) {
        this.this$0 = exitUntilCollapsedScrollBehavior;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo368onPostFlingRZ2iAVY(long r10, long r12, d7.d<? super androidx.compose.ui.unit.Velocity> r14) {
        /*
            r9 = this;
            boolean r0 = r14 instanceof androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1
            if (r0 == 0) goto L14
            r0 = r14
            androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 r0 = (androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 r0 = new androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1
            r0.<init>(r9, r14)
            goto L12
        L1a:
            java.lang.Object r14 = r6.result
            int r0 = r6.label
            r7 = 2
            r1 = 1
            e7.a r8 = e7.a.f15033i
            if (r0 == 0) goto L40
            if (r0 == r1) goto L36
            if (r0 != r7) goto L2e
            long r10 = r6.J$0
            k2.c.G(r14)
            goto L80
        L2e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L36:
            long r12 = r6.J$0
            java.lang.Object r10 = r6.L$0
            androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1 r10 = (androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1) r10
            k2.c.G(r14)
            goto L55
        L40:
            k2.c.G(r14)
            r6.L$0 = r9
            r6.J$0 = r12
            r6.label = r1
            r1 = r9
            r2 = r10
            r4 = r12
            java.lang.Object r14 = androidx.compose.ui.input.nestedscroll.a.a(r1, r2, r4, r6)
            if (r14 != r8) goto L53
            goto L7e
        L53:
            r10 = r9
            r12 = r4
        L55:
            androidx.compose.ui.unit.Velocity r14 = (androidx.compose.ui.unit.Velocity) r14
            long r0 = r14.getPackedValue()
            androidx.compose.material3.ExitUntilCollapsedScrollBehavior r11 = r10.this$0
            androidx.compose.material3.TopAppBarState r11 = r11.getState()
            float r12 = androidx.compose.ui.unit.Velocity.m5910getYimpl(r12)
            androidx.compose.material3.ExitUntilCollapsedScrollBehavior r13 = r10.this$0
            androidx.compose.animation.core.DecayAnimationSpec r13 = r13.getFlingAnimationSpec()
            androidx.compose.material3.ExitUntilCollapsedScrollBehavior r10 = r10.this$0
            androidx.compose.animation.core.AnimationSpec r10 = r10.getSnapAnimationSpec()
            r14 = 0
            r6.L$0 = r14
            r6.J$0 = r0
            r6.label = r7
            java.lang.Object r14 = androidx.compose.material3.AppBarKt.access$settleAppBar(r11, r12, r13, r10, r6)
            if (r14 != r8) goto L7f
        L7e:
            return r8
        L7f:
            r10 = r0
        L80:
            androidx.compose.ui.unit.Velocity r14 = (androidx.compose.ui.unit.Velocity) r14
            long r12 = r14.getPackedValue()
            long r10 = androidx.compose.ui.unit.Velocity.m5913plusAH228Gc(r10, r12)
            androidx.compose.ui.unit.Velocity r10 = androidx.compose.ui.unit.Velocity.m5900boximpl(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1.mo368onPostFlingRZ2iAVY(long, long, d7.d):java.lang.Object");
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo369onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!((Boolean) this.this$0.getCanScroll().invoke()).booleanValue()) {
            return Offset.INSTANCE.m3252getZeroF1C5BW0();
        }
        TopAppBarState state = this.this$0.getState();
        state.setContentOffset(Offset.m3237getYimpl(consumed) + state.getContentOffset());
        if (Offset.m3237getYimpl(available) < 0.0f || Offset.m3237getYimpl(consumed) < 0.0f) {
            float heightOffset = this.this$0.getState().getHeightOffset();
            this.this$0.getState().setHeightOffset(Offset.m3237getYimpl(consumed) + this.this$0.getState().getHeightOffset());
            return OffsetKt.Offset(0.0f, this.this$0.getState().getHeightOffset() - heightOffset);
        }
        if (Offset.m3237getYimpl(consumed) == 0.0f && Offset.m3237getYimpl(available) > 0.0f) {
            this.this$0.getState().setContentOffset(0.0f);
        }
        if (Offset.m3237getYimpl(available) <= 0.0f) {
            return Offset.INSTANCE.m3252getZeroF1C5BW0();
        }
        float heightOffset2 = this.this$0.getState().getHeightOffset();
        this.this$0.getState().setHeightOffset(Offset.m3237getYimpl(available) + this.this$0.getState().getHeightOffset());
        return OffsetKt.Offset(0.0f, this.this$0.getState().getHeightOffset() - heightOffset2);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    public final /* synthetic */ Object mo370onPreFlingQWom1Mo(long j10, d7.d dVar) {
        return androidx.compose.ui.input.nestedscroll.a.c(this, j10, dVar);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public long mo371onPreScrollOzD1aCk(long available, int source) {
        if (!((Boolean) this.this$0.getCanScroll().invoke()).booleanValue() || Offset.m3237getYimpl(available) > 0.0f) {
            return Offset.INSTANCE.m3252getZeroF1C5BW0();
        }
        float heightOffset = this.this$0.getState().getHeightOffset();
        this.this$0.getState().setHeightOffset(Offset.m3237getYimpl(available) + this.this$0.getState().getHeightOffset());
        return heightOffset == this.this$0.getState().getHeightOffset() ? Offset.INSTANCE.m3252getZeroF1C5BW0() : Offset.m3230copydBAh8RU$default(available, 0.0f, 0.0f, 2, null);
    }
}
