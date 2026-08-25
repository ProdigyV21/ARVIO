package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.Velocity;
import d7.d;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\f\u001a\u00020\t*\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001c\u0010\u0010\u001a\u00020\r*\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001c\u0010\u0010\u001a\u00020\b*\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u000fJ\"\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J*\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ#\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010!R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b#\u0010$\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006%"}, d2 = {"Landroidx/compose/foundation/pager/DefaultPagerNestedScrollConnection;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroidx/compose/foundation/pager/PagerState;", "state", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "<init>", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/gestures/Orientation;)V", "Landroidx/compose/ui/geometry/Offset;", "", "mainAxis-k-4lQ0M", "(J)F", "mainAxis", "Landroidx/compose/ui/unit/Velocity;", "consumeOnOrientation-QWom1Mo", "(JLandroidx/compose/foundation/gestures/Orientation;)J", "consumeOnOrientation", "consumeOnOrientation-8S9VItk", "available", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "source", "onPreScroll-OzD1aCk", "(JI)J", "onPreScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPostScroll", "onPostFling-RZ2iAVY", "(JJLd7/d;)Ljava/lang/Object;", "onPostFling", "Landroidx/compose/foundation/pager/PagerState;", "getState", "()Landroidx/compose/foundation/pager/PagerState;", "Landroidx/compose/foundation/gestures/Orientation;", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DefaultPagerNestedScrollConnection implements NestedScrollConnection {
    private final Orientation orientation;
    private final PagerState state;

    public DefaultPagerNestedScrollConnection(PagerState pagerState, Orientation orientation) {
        this.state = pagerState;
        this.orientation = orientation;
    }

    /* JADX INFO: renamed from: mainAxis-k-4lQ0M, reason: not valid java name */
    private final float m747mainAxisk4lQ0M(long j10) {
        return this.orientation == Orientation.Horizontal ? Offset.m3236getXimpl(j10) : Offset.m3237getYimpl(j10);
    }

    /* JADX INFO: renamed from: consumeOnOrientation-8S9VItk, reason: not valid java name */
    public final long m748consumeOnOrientation8S9VItk(long j10, Orientation orientation) {
        return orientation == Orientation.Vertical ? Offset.m3230copydBAh8RU$default(j10, 0.0f, 0.0f, 2, null) : Offset.m3230copydBAh8RU$default(j10, 0.0f, 0.0f, 1, null);
    }

    /* JADX INFO: renamed from: consumeOnOrientation-QWom1Mo, reason: not valid java name */
    public final long m749consumeOnOrientationQWom1Mo(long j10, Orientation orientation) {
        return orientation == Orientation.Vertical ? Velocity.m5905copyOhffZ5M$default(j10, 0.0f, 0.0f, 2, null) : Velocity.m5905copyOhffZ5M$default(j10, 0.0f, 0.0f, 1, null);
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final PagerState getState() {
        return this.state;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    public Object mo368onPostFlingRZ2iAVY(long j10, long j11, d<? super Velocity> dVar) {
        return Velocity.m5900boximpl(m749consumeOnOrientationQWom1Mo(j11, this.orientation));
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo369onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!NestedScrollSource.m4420equalsimpl0(source, NestedScrollSource.INSTANCE.m4426getFlingWNlRxjI()) || m747mainAxisk4lQ0M(available) == 0.0f) {
            return Offset.INSTANCE.m3252getZeroF1C5BW0();
        }
        throw new CancellationException();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    public final /* synthetic */ Object mo370onPreFlingQWom1Mo(long j10, d dVar) {
        return androidx.compose.ui.input.nestedscroll.a.c(this, j10, dVar);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public long mo371onPreScrollOzD1aCk(long available, int source) {
        if (!NestedScrollSource.m4420equalsimpl0(source, NestedScrollSource.INSTANCE.m4425getDragWNlRxjI()) || Math.abs(this.state.getCurrentPageOffsetFraction()) <= 0.0d) {
            return Offset.INSTANCE.m3252getZeroF1C5BW0();
        }
        float currentPageOffsetFraction = this.state.getCurrentPageOffsetFraction() * this.state.getPageSize$foundation_release();
        float pageSpacing = ((this.state.getLayoutInfo().getPageSpacing() + this.state.getLayoutInfo().getPageSize()) * (-Math.signum(this.state.getCurrentPageOffsetFraction()))) + currentPageOffsetFraction;
        if (this.state.getCurrentPageOffsetFraction() > 0.0f) {
            pageSpacing = currentPageOffsetFraction;
            currentPageOffsetFraction = pageSpacing;
        }
        Orientation orientation = this.orientation;
        Orientation orientation2 = Orientation.Horizontal;
        float fM3237getYimpl = -this.state.dispatchRawDelta(-qb.d.m(orientation == orientation2 ? Offset.m3236getXimpl(available) : Offset.m3237getYimpl(available), currentPageOffsetFraction, pageSpacing));
        float fM3236getXimpl = this.orientation == orientation2 ? fM3237getYimpl : Offset.m3236getXimpl(available);
        if (this.orientation != Orientation.Vertical) {
            fM3237getYimpl = Offset.m3237getYimpl(available);
        }
        return Offset.m3229copydBAh8RU(available, fM3236getXimpl, fM3237getYimpl);
    }
}
