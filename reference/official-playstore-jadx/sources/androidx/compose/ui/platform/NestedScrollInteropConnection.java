package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.unit.Velocity;
import androidx.core.view.b2;
import androidx.core.view.k1;
import androidx.core.view.m0;
import java.util.WeakHashMap;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\"\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ*\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0017\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u0014H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J#\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u0014H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\""}, d2 = {"Landroidx/compose/ui/platform/NestedScrollInteropConnection;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroid/view/View;", "view", "<init>", "(Landroid/view/View;)V", "Lx6/t0;", "interruptOngoingScrolls", "()V", "Landroidx/compose/ui/geometry/Offset;", "available", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "source", "onPreScroll-OzD1aCk", "(JI)J", "onPreScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPostScroll", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLd7/d;)Ljava/lang/Object;", "onPreFling", "onPostFling-RZ2iAVY", "(JJLd7/d;)Ljava/lang/Object;", "onPostFling", "Landroid/view/View;", "Landroidx/core/view/m0;", "nestedScrollChildHelper", "Landroidx/core/view/m0;", "", "consumedScrollCache", "[I", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NestedScrollInteropConnection implements NestedScrollConnection {
    public static final int $stable = 8;
    private final int[] consumedScrollCache;
    private final m0 nestedScrollChildHelper;
    private final View view;

    public NestedScrollInteropConnection(View view) {
        this.view = view;
        m0 m0Var = new m0(view);
        if (m0Var.f2286d) {
            WeakHashMap weakHashMap = b2.f2200a;
            k1.z(view);
        }
        m0Var.f2286d = true;
        this.nestedScrollChildHelper = m0Var;
        this.consumedScrollCache = new int[2];
        WeakHashMap weakHashMap2 = b2.f2200a;
        k1.t(view, true);
    }

    private final void interruptOngoingScrolls() {
        if (this.nestedScrollChildHelper.f(0)) {
            this.nestedScrollChildHelper.h(0);
        }
        if (this.nestedScrollChildHelper.f(1)) {
            this.nestedScrollChildHelper.h(1);
        }
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    public Object mo368onPostFlingRZ2iAVY(long j10, long j11, d7.d<? super Velocity> dVar) {
        if (!this.nestedScrollChildHelper.a(NestedScrollInteropConnectionKt.toViewVelocity(Velocity.m5909getXimpl(j11)), NestedScrollInteropConnectionKt.toViewVelocity(Velocity.m5910getYimpl(j11)), true)) {
            j11 = Velocity.INSTANCE.m5920getZero9UxMQ8M();
        }
        interruptOngoingScrolls();
        return Velocity.m5900boximpl(j11);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo369onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!this.nestedScrollChildHelper.g(NestedScrollInteropConnectionKt.m4990getScrollAxesk4lQ0M(available), NestedScrollInteropConnectionKt.m4992toViewTypeGyEprt8(source))) {
            return Offset.INSTANCE.m3252getZeroF1C5BW0();
        }
        kotlin.collections.r.U(this.consumedScrollCache, 0, 0, 6);
        this.nestedScrollChildHelper.d(NestedScrollInteropConnectionKt.composeToViewOffset(Offset.m3236getXimpl(consumed)), NestedScrollInteropConnectionKt.composeToViewOffset(Offset.m3237getYimpl(consumed)), NestedScrollInteropConnectionKt.composeToViewOffset(Offset.m3236getXimpl(available)), NestedScrollInteropConnectionKt.composeToViewOffset(Offset.m3237getYimpl(available)), null, NestedScrollInteropConnectionKt.m4992toViewTypeGyEprt8(source), this.consumedScrollCache);
        return NestedScrollInteropConnectionKt.m4991toOffsetUv8p0NA(this.consumedScrollCache, available);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    public Object mo370onPreFlingQWom1Mo(long j10, d7.d<? super Velocity> dVar) {
        if (!this.nestedScrollChildHelper.b(NestedScrollInteropConnectionKt.toViewVelocity(Velocity.m5909getXimpl(j10)), NestedScrollInteropConnectionKt.toViewVelocity(Velocity.m5910getYimpl(j10)))) {
            j10 = Velocity.INSTANCE.m5920getZero9UxMQ8M();
        }
        interruptOngoingScrolls();
        return Velocity.m5900boximpl(j10);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public long mo371onPreScrollOzD1aCk(long available, int source) {
        if (!this.nestedScrollChildHelper.g(NestedScrollInteropConnectionKt.m4990getScrollAxesk4lQ0M(available), NestedScrollInteropConnectionKt.m4992toViewTypeGyEprt8(source))) {
            return Offset.INSTANCE.m3252getZeroF1C5BW0();
        }
        kotlin.collections.r.U(this.consumedScrollCache, 0, 0, 6);
        this.nestedScrollChildHelper.c(NestedScrollInteropConnectionKt.composeToViewOffset(Offset.m3236getXimpl(available)), NestedScrollInteropConnectionKt.composeToViewOffset(Offset.m3237getYimpl(available)), NestedScrollInteropConnectionKt.m4992toViewTypeGyEprt8(source), this.consumedScrollCache, null);
        return NestedScrollInteropConnectionKt.m4991toOffsetUv8p0NA(this.consumedScrollCache, available);
    }
}
