package androidx.compose.material3.pulltorefresh;

import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.a;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b!\b\u0001\u0018\u0000 <2\u00020\u0001:\u0001<B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\fJ\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b\u0017\u0010\u0015J\r\u0010\u0018\u001a\u00020\u0004¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u0019R\"\u0010\u001d\u001a\u00020\u001c8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R+\u0010)\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00048@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010\u0019\"\u0004\b'\u0010(R+\u0010-\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00048B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010%\u001a\u0004\b+\u0010\u0019\"\u0004\b,\u0010(R+\u00104\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\u00028B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0014\u00106\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b5\u0010\u0019R\u0014\u00108\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u0010\u0019R\u0014\u0010:\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010\u0019R\u0014\u0010;\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u00101\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006="}, d2 = {"Landroidx/compose/material3/pulltorefresh/PullToRefreshStateImpl;", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "", "initialRefreshing", "", "positionalThreshold", "Lkotlin/Function0;", "enabled", "<init>", "(ZFLr7/a;)V", "Lx6/t0;", "startRefresh", "()V", "endRefresh", "Landroidx/compose/ui/geometry/Offset;", "available", "consumeAvailableOffset-MK-Hz9U", "(J)J", "consumeAvailableOffset", "velocity", "onRelease", "(FLd7/d;)Ljava/lang/Object;", "offset", "animateTo", "calculateVerticalOffset", "()F", "F", "getPositionalThreshold", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "nestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "getNestedScrollConnection", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "setNestedScrollConnection", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;)V", "<set-?>", "distancePulled$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getDistancePulled$material3_release", "setDistancePulled$material3_release", "(F)V", "distancePulled", "_verticalOffset$delegate", "get_verticalOffset", "set_verticalOffset", "_verticalOffset", "_refreshing$delegate", "Landroidx/compose/runtime/MutableState;", "get_refreshing", "()Z", "set_refreshing", "(Z)V", "_refreshing", "getAdjustedDistancePulled", "adjustedDistancePulled", "getProgress", "progress", "getVerticalOffset", "verticalOffset", "isRefreshing", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PullToRefreshStateImpl implements PullToRefreshState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: _refreshing$delegate, reason: from kotlin metadata */
    private final MutableState _refreshing;
    private NestedScrollConnection nestedScrollConnection;
    private final float positionalThreshold;

    /* JADX INFO: renamed from: distancePulled$delegate, reason: from kotlin metadata */
    private final MutableFloatState distancePulled = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);

    /* JADX INFO: renamed from: _verticalOffset$delegate, reason: from kotlin metadata */
    private final MutableFloatState _verticalOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/material3/pulltorefresh/PullToRefreshStateImpl$Companion;", "", "<init>", "()V", "", "positionalThreshold", "Lkotlin/Function0;", "", "enabled", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "Saver", "(FLr7/a;)Landroidx/compose/runtime/saveable/Saver;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final Saver<PullToRefreshState, Boolean> Saver(float positionalThreshold, a<Boolean> enabled) {
            return SaverKt.Saver(PullToRefreshStateImpl$Companion$Saver$1.INSTANCE, new PullToRefreshStateImpl$Companion$Saver$2(positionalThreshold, enabled));
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl$animateTo$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "value", "<anonymous parameter 1>", "Lx6/t0;", "invoke", "(FF)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements p<Float, Float, t0> {
        public AnonymousClass2() {
            super(2);
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke(((Number) obj).floatValue(), ((Number) obj2).floatValue());
            return t0.f22605a;
        }

        public final void invoke(float f10, float f11) {
            PullToRefreshStateImpl.this.set_verticalOffset(f10);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl$onRelease$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl", f = "PullToRefresh.kt", l = {364}, m = "onRelease")
    public static final class AnonymousClass1 extends c {
        float F$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PullToRefreshStateImpl.this.onRelease(0.0f, this);
        }
    }

    public PullToRefreshStateImpl(boolean z, float f10, a<Boolean> aVar) {
        this.positionalThreshold = f10;
        this.nestedScrollConnection = new PullToRefreshStateImpl$nestedScrollConnection$1(aVar, this);
        this._refreshing = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), null, 2, null);
    }

    private final float getAdjustedDistancePulled() {
        return getDistancePulled$material3_release() * 0.5f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean get_refreshing() {
        return ((Boolean) this._refreshing.getValue()).booleanValue();
    }

    private final float get_verticalOffset() {
        return this._verticalOffset.getFloatValue();
    }

    private final void set_refreshing(boolean z) {
        this._refreshing.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void set_verticalOffset(float f10) {
        this._verticalOffset.setFloatValue(f10);
    }

    public final Object animateTo(float f10, d<? super t0> dVar) {
        Object objAnimate$default = SuspendAnimationKt.animate$default(get_verticalOffset(), f10, 0.0f, null, new AnonymousClass2(), dVar, 12, null);
        return objAnimate$default == e7.a.f15033i ? objAnimate$default : t0.f22605a;
    }

    public final float calculateVerticalOffset() {
        if (getAdjustedDistancePulled() <= getPositionalThreshold()) {
            return getAdjustedDistancePulled();
        }
        float fM = qb.d.m(Math.abs(getProgress()) - 1.0f, 0.0f, 2.0f);
        return getPositionalThreshold() + (getPositionalThreshold() * (fM - (((float) Math.pow(fM, 2)) / 4)));
    }

    /* JADX INFO: renamed from: consumeAvailableOffset-MK-Hz9U, reason: not valid java name */
    public final long m2334consumeAvailableOffsetMKHz9U(long available) {
        float distancePulled$material3_release;
        if (isRefreshing()) {
            distancePulled$material3_release = 0.0f;
        } else {
            float fM3237getYimpl = Offset.m3237getYimpl(available) + getDistancePulled$material3_release();
            if (fM3237getYimpl < 0.0f) {
                fM3237getYimpl = 0.0f;
            }
            distancePulled$material3_release = fM3237getYimpl - getDistancePulled$material3_release();
            setDistancePulled$material3_release(fM3237getYimpl);
            set_verticalOffset(calculateVerticalOffset());
        }
        return OffsetKt.Offset(0.0f, distancePulled$material3_release);
    }

    @Override // androidx.compose.material3.pulltorefresh.PullToRefreshState
    public void endRefresh() {
        set_verticalOffset(0.0f);
        set_refreshing(false);
    }

    public final float getDistancePulled$material3_release() {
        return this.distancePulled.getFloatValue();
    }

    @Override // androidx.compose.material3.pulltorefresh.PullToRefreshState
    public NestedScrollConnection getNestedScrollConnection() {
        return this.nestedScrollConnection;
    }

    @Override // androidx.compose.material3.pulltorefresh.PullToRefreshState
    public float getPositionalThreshold() {
        return this.positionalThreshold;
    }

    @Override // androidx.compose.material3.pulltorefresh.PullToRefreshState
    public float getProgress() {
        return getAdjustedDistancePulled() / getPositionalThreshold();
    }

    @Override // androidx.compose.material3.pulltorefresh.PullToRefreshState
    public float getVerticalOffset() {
        return get_verticalOffset();
    }

    @Override // androidx.compose.material3.pulltorefresh.PullToRefreshState
    public boolean isRefreshing() {
        return get_refreshing();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object onRelease(float r5, d7.d<? super java.lang.Float> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl$onRelease$1 r0 = (androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl$onRelease$1 r0 = new androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl$onRelease$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L34
            if (r1 != r2) goto L2c
            float r5 = r0.F$0
            java.lang.Object r0 = r0.L$0
            androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl r0 = (androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl) r0
            k2.c.G(r6)
            goto L63
        L2c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L34:
            k2.c.G(r6)
            boolean r6 = r4.isRefreshing()
            if (r6 == 0) goto L43
            java.lang.Float r5 = new java.lang.Float
            r5.<init>(r3)
            return r5
        L43:
            float r6 = r4.getAdjustedDistancePulled()
            float r1 = r4.getPositionalThreshold()
            int r6 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r6 <= 0) goto L54
            r4.startRefresh()
        L52:
            r0 = r4
            goto L63
        L54:
            r0.L$0 = r4
            r0.F$0 = r5
            r0.label = r2
            java.lang.Object r6 = r4.animateTo(r3, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L52
            return r0
        L63:
            float r6 = r0.getDistancePulled$material3_release()
            int r6 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r6 != 0) goto L6d
        L6b:
            r5 = r3
            goto L72
        L6d:
            int r6 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r6 >= 0) goto L72
            goto L6b
        L72:
            r0.setDistancePulled$material3_release(r3)
            java.lang.Float r6 = new java.lang.Float
            r6.<init>(r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl.onRelease(float, d7.d):java.lang.Object");
    }

    public final void setDistancePulled$material3_release(float f10) {
        this.distancePulled.setFloatValue(f10);
    }

    @Override // androidx.compose.material3.pulltorefresh.PullToRefreshState
    public void setNestedScrollConnection(NestedScrollConnection nestedScrollConnection) {
        this.nestedScrollConnection = nestedScrollConnection;
    }

    @Override // androidx.compose.material3.pulltorefresh.PullToRefreshState
    public void startRefresh() {
        set_refreshing(true);
        set_verticalOffset(getPositionalThreshold());
    }
}
