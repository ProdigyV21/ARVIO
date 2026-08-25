package androidx.compose.foundation.gestures;

import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0014\u001a\u00020\u0011*\u00020\u0010ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0014\u0010\u0017\u001a\u00020\u0011*\u00020\u0011ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0014\u0010\u001a\u001a\u00020\u0010*\u00020\u0011ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u0010*\u00020\u001bø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u0019J\u0014\u0010\u001e\u001a\u00020\u001b*\u00020\u001bø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u0016J\u001c\u0010\"\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u0010ø\u0001\u0001¢\u0006\u0004\b \u0010!J\u0011\u0010#\u001a\u00020\u0010*\u00020\u0010¢\u0006\u0004\b#\u0010$J\u0014\u0010#\u001a\u00020\u0011*\u00020\u0011ø\u0001\u0001¢\u0006\u0004\b%\u0010\u0016J$\u0010,\u001a\u00020\u0011*\u00020&2\u0006\u0010'\u001a\u00020\u00112\u0006\u0010)\u001a\u00020(ø\u0001\u0001¢\u0006\u0004\b*\u0010+J\u0018\u0010/\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011ø\u0001\u0001¢\u0006\u0004\b.\u0010\u0016J\u001b\u00104\u001a\u0002012\u0006\u00100\u001a\u00020\u001bH\u0086@ø\u0001\u0001¢\u0006\u0004\b2\u00103J\u001b\u00107\u001a\u00020\u001b2\u0006\u00105\u001a\u00020\u001bH\u0086@ø\u0001\u0001¢\u0006\u0004\b6\u00103J\r\u00108\u001a\u00020\b¢\u0006\u0004\b8\u00109J\u0015\u0010;\u001a\u0002012\u0006\u0010:\u001a\u00020\b¢\u0006\u0004\b;\u0010<J?\u0010\"\u001a\u0002012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\"\u0010\u000fR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010BR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010CR\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010DR\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010ER\u0016\u0010\r\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010FR\u001a\u0010H\u001a\b\u0012\u0004\u0012\u00020\b0G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010K\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bJ\u00109\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006L"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollingLogic;", "", "Landroidx/compose/foundation/gestures/ScrollableState;", "scrollableState", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "Landroidx/compose/foundation/OverscrollEffect;", "overscrollEffect", "", "reverseDirection", "Landroidx/compose/foundation/gestures/FlingBehavior;", "flingBehavior", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "nestedScrollDispatcher", "<init>", "(Landroidx/compose/foundation/gestures/ScrollableState;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/OverscrollEffect;ZLandroidx/compose/foundation/gestures/FlingBehavior;Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;)V", "", "Landroidx/compose/ui/geometry/Offset;", "toOffset-tuRUvjQ", "(F)J", "toOffset", "singleAxisOffset-MK-Hz9U", "(J)J", "singleAxisOffset", "toFloat-k-4lQ0M", "(J)F", "toFloat", "Landroidx/compose/ui/unit/Velocity;", "toFloat-TH1AsA0", "singleAxisVelocity-AH228Gc", "singleAxisVelocity", "newValue", "update-QWom1Mo", "(JF)J", "update", "reverseIfNeeded", "(F)F", "reverseIfNeeded-MK-Hz9U", "Landroidx/compose/foundation/gestures/ScrollScope;", "initialAvailableDelta", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "source", "dispatchScroll-3eAAhYA", "(Landroidx/compose/foundation/gestures/ScrollScope;JI)J", "dispatchScroll", "scroll", "performRawScroll-MK-Hz9U", "performRawScroll", "initialVelocity", "Lx6/t0;", "onDragStopped-sF-c-tU", "(JLd7/d;)Ljava/lang/Object;", "onDragStopped", "available", "doFlingAnimation-QWom1Mo", "doFlingAnimation", "shouldScrollImmediately", "()Z", "isFlinging", "registerNestedFling", "(Z)V", "Landroidx/compose/foundation/gestures/ScrollableState;", "getScrollableState", "()Landroidx/compose/foundation/gestures/ScrollableState;", "setScrollableState", "(Landroidx/compose/foundation/gestures/ScrollableState;)V", "Landroidx/compose/foundation/gestures/Orientation;", "Landroidx/compose/foundation/OverscrollEffect;", "Z", "Landroidx/compose/foundation/gestures/FlingBehavior;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "Landroidx/compose/runtime/MutableState;", "isNestedFlinging", "Landroidx/compose/runtime/MutableState;", "getShouldDispatchOverscroll", "shouldDispatchOverscroll", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ScrollingLogic {
    private FlingBehavior flingBehavior;
    private final MutableState<Boolean> isNestedFlinging = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
    private NestedScrollDispatcher nestedScrollDispatcher;
    private Orientation orientation;
    private OverscrollEffect overscrollEffect;
    private boolean reverseDirection;
    private ScrollableState scrollableState;

    public ScrollingLogic(ScrollableState scrollableState, Orientation orientation, OverscrollEffect overscrollEffect, boolean z, FlingBehavior flingBehavior, NestedScrollDispatcher nestedScrollDispatcher) {
        this.scrollableState = scrollableState;
        this.orientation = orientation;
        this.overscrollEffect = overscrollEffect;
        this.reverseDirection = z;
        this.flingBehavior = flingBehavior;
        this.nestedScrollDispatcher = nestedScrollDispatcher;
    }

    private final boolean getShouldDispatchOverscroll() {
        return this.scrollableState.getCanScrollForward() || this.scrollableState.getCanScrollBackward();
    }

    /* JADX INFO: renamed from: dispatchScroll-3eAAhYA, reason: not valid java name */
    public final long m373dispatchScroll3eAAhYA(ScrollScope scrollScope, long j10, int i10) {
        ScrollingLogic$dispatchScroll$performScroll$1 scrollingLogic$dispatchScroll$performScroll$1 = new ScrollingLogic$dispatchScroll$performScroll$1(this, i10, scrollScope);
        OverscrollEffect overscrollEffect = this.overscrollEffect;
        return NestedScrollSource.m4420equalsimpl0(i10, NestedScrollSource.INSTANCE.m4428getWheelWNlRxjI()) ? ((Offset) scrollingLogic$dispatchScroll$performScroll$1.invoke(Offset.m3225boximpl(j10))).getPackedValue() : (overscrollEffect == null || !getShouldDispatchOverscroll()) ? ((Offset) scrollingLogic$dispatchScroll$performScroll$1.invoke(Offset.m3225boximpl(j10))).getPackedValue() : overscrollEffect.mo160applyToScrollRhakbz0(j10, i10, scrollingLogic$dispatchScroll$performScroll$1);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /* JADX INFO: renamed from: doFlingAnimation-QWom1Mo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m374doFlingAnimationQWom1Mo(long r12, d7.d<? super androidx.compose.ui.unit.Velocity> r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1
            if (r0 == 0) goto L14
            r0 = r14
            androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1 r0 = (androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r4 = r0
            goto L1a
        L14:
            androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1 r0 = new androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1
            r0.<init>(r11, r14)
            goto L12
        L1a:
            java.lang.Object r14 = r4.result
            int r0 = r4.label
            r1 = 1
            if (r0 == 0) goto L33
            if (r0 != r1) goto L2b
            java.lang.Object r12 = r4.L$0
            kotlin.jvm.internal.j0 r12 = (kotlin.jvm.internal.j0) r12
            k2.c.G(r14)
            goto L5a
        L2b:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L33:
            k2.c.G(r14)
            kotlin.jvm.internal.j0 r7 = new kotlin.jvm.internal.j0
            r7.<init>()
            r7.f19745i = r12
            r14 = r1
            androidx.compose.foundation.gestures.ScrollableState r1 = r11.scrollableState
            androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2 r3 = new androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2
            r10 = 0
            r6 = r11
            r8 = r12
            r5 = r3
            r5.<init>(r6, r7, r8, r10)
            r4.L$0 = r7
            r4.label = r14
            r2 = 0
            r5 = 1
            r6 = 0
            java.lang.Object r12 = androidx.compose.foundation.gestures.f.e(r1, r2, r3, r4, r5, r6)
            e7.a r13 = e7.a.f15033i
            if (r12 != r13) goto L59
            return r13
        L59:
            r12 = r7
        L5a:
            long r12 = r12.f19745i
            androidx.compose.ui.unit.Velocity r12 = androidx.compose.ui.unit.Velocity.m5900boximpl(r12)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollingLogic.m374doFlingAnimationQWom1Mo(long, d7.d):java.lang.Object");
    }

    public final ScrollableState getScrollableState() {
        return this.scrollableState;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0059, code lost:
    
        if (r1.mo159applyToFlingBMRW4eQ(r7, r9, r0) == r4) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005c, code lost:
    
        r7 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006a, code lost:
    
        if (r9.invoke(r7, r0) == r4) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006c, code lost:
    
        return r4;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: onDragStopped-sF-c-tU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m375onDragStoppedsFctU(long r7, d7.d<? super x6.t0> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$1
            if (r0 == 0) goto L13
            r0 = r9
            androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$1 r0 = (androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$1 r0 = new androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$1
            r0.<init>(r6, r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L35
            if (r1 == r3) goto L2d
            if (r1 != r2) goto L25
            goto L2d
        L25:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2d:
            java.lang.Object r7 = r0.L$0
            androidx.compose.foundation.gestures.ScrollingLogic r7 = (androidx.compose.foundation.gestures.ScrollingLogic) r7
            k2.c.G(r9)
            goto L6d
        L35:
            k2.c.G(r9)
            r6.registerNestedFling(r3)
            long r7 = r6.m379singleAxisVelocityAH228Gc(r7)
            androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$performFling$1 r9 = new androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$performFling$1
            r1 = 0
            r9.<init>(r6, r1)
            androidx.compose.foundation.OverscrollEffect r1 = r6.overscrollEffect
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L5e
            boolean r5 = r6.getShouldDispatchOverscroll()
            if (r5 == 0) goto L5e
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r7 = r1.mo159applyToFlingBMRW4eQ(r7, r9, r0)
            if (r7 != r4) goto L5c
            goto L6c
        L5c:
            r7 = r6
            goto L6d
        L5e:
            androidx.compose.ui.unit.Velocity r7 = androidx.compose.ui.unit.Velocity.m5900boximpl(r7)
            r0.L$0 = r6
            r0.label = r2
            java.lang.Object r7 = r9.invoke(r7, r0)
            if (r7 != r4) goto L5c
        L6c:
            return r4
        L6d:
            r8 = 0
            r7.registerNestedFling(r8)
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollingLogic.m375onDragStoppedsFctU(long, d7.d):java.lang.Object");
    }

    /* JADX INFO: renamed from: performRawScroll-MK-Hz9U, reason: not valid java name */
    public final long m376performRawScrollMKHz9U(long scroll) {
        return this.scrollableState.isScrollInProgress() ? Offset.INSTANCE.m3252getZeroF1C5BW0() : m382toOffsettuRUvjQ(reverseIfNeeded(this.scrollableState.dispatchRawDelta(reverseIfNeeded(m381toFloatk4lQ0M(scroll)))));
    }

    public final void registerNestedFling(boolean isFlinging) {
        this.isNestedFlinging.setValue(Boolean.valueOf(isFlinging));
    }

    public final float reverseIfNeeded(float f10) {
        return this.reverseDirection ? f10 * (-1) : f10;
    }

    /* JADX INFO: renamed from: reverseIfNeeded-MK-Hz9U, reason: not valid java name */
    public final long m377reverseIfNeededMKHz9U(long j10) {
        return this.reverseDirection ? Offset.m3243timestuRUvjQ(j10, -1.0f) : j10;
    }

    public final void setScrollableState(ScrollableState scrollableState) {
        this.scrollableState = scrollableState;
    }

    public final boolean shouldScrollImmediately() {
        if (this.scrollableState.isScrollInProgress() || this.isNestedFlinging.getValue().booleanValue()) {
            return true;
        }
        OverscrollEffect overscrollEffect = this.overscrollEffect;
        return overscrollEffect != null ? overscrollEffect.isInProgress() : false;
    }

    /* JADX INFO: renamed from: singleAxisOffset-MK-Hz9U, reason: not valid java name */
    public final long m378singleAxisOffsetMKHz9U(long j10) {
        return this.orientation == Orientation.Horizontal ? Offset.m3230copydBAh8RU$default(j10, 0.0f, 0.0f, 1, null) : Offset.m3230copydBAh8RU$default(j10, 0.0f, 0.0f, 2, null);
    }

    /* JADX INFO: renamed from: singleAxisVelocity-AH228Gc, reason: not valid java name */
    public final long m379singleAxisVelocityAH228Gc(long j10) {
        return this.orientation == Orientation.Horizontal ? Velocity.m5905copyOhffZ5M$default(j10, 0.0f, 0.0f, 1, null) : Velocity.m5905copyOhffZ5M$default(j10, 0.0f, 0.0f, 2, null);
    }

    /* JADX INFO: renamed from: toFloat-TH1AsA0, reason: not valid java name */
    public final float m380toFloatTH1AsA0(long j10) {
        return this.orientation == Orientation.Horizontal ? Velocity.m5909getXimpl(j10) : Velocity.m5910getYimpl(j10);
    }

    /* JADX INFO: renamed from: toFloat-k-4lQ0M, reason: not valid java name */
    public final float m381toFloatk4lQ0M(long j10) {
        return this.orientation == Orientation.Horizontal ? Offset.m3236getXimpl(j10) : Offset.m3237getYimpl(j10);
    }

    /* JADX INFO: renamed from: toOffset-tuRUvjQ, reason: not valid java name */
    public final long m382toOffsettuRUvjQ(float f10) {
        return f10 == 0.0f ? Offset.INSTANCE.m3252getZeroF1C5BW0() : this.orientation == Orientation.Horizontal ? OffsetKt.Offset(f10, 0.0f) : OffsetKt.Offset(0.0f, f10);
    }

    public final void update(ScrollableState scrollableState, Orientation orientation, OverscrollEffect overscrollEffect, boolean reverseDirection, FlingBehavior flingBehavior, NestedScrollDispatcher nestedScrollDispatcher) {
        this.scrollableState = scrollableState;
        this.orientation = orientation;
        this.overscrollEffect = overscrollEffect;
        this.reverseDirection = reverseDirection;
        this.flingBehavior = flingBehavior;
        this.nestedScrollDispatcher = nestedScrollDispatcher;
    }

    /* JADX INFO: renamed from: update-QWom1Mo, reason: not valid java name */
    public final long m383updateQWom1Mo(long j10, float f10) {
        return this.orientation == Orientation.Horizontal ? Velocity.m5905copyOhffZ5M$default(j10, f10, 0.0f, 2, null) : Velocity.m5905copyOhffZ5M$default(j10, 0.0f, f10, 1, null);
    }
}
