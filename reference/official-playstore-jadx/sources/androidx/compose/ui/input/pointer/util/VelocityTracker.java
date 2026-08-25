package androidx.compose.ui.input.pointer.util;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000f\u001a\u00020\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\b¢\u0006\u0004\b\u0013\u0010\u0003R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016R(\u0010\u0018\u001a\u00020\u00068\u0000@\u0000X\u0080\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u000e\"\u0004\b\u001b\u0010\u001cR\"\u0010\u001d\u001a\u00020\u00048\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u0019\u001a\u0004\b\u001e\u0010\u000e\"\u0004\b\u001f\u0010\u001c\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006 "}, d2 = {"Landroidx/compose/ui/input/pointer/util/VelocityTracker;", "", "<init>", "()V", "", "timeMillis", "Landroidx/compose/ui/geometry/Offset;", "position", "Lx6/t0;", "addPosition-Uv8p0NA", "(JJ)V", "addPosition", "Landroidx/compose/ui/unit/Velocity;", "calculateVelocity-9UxMQ8M", "()J", "calculateVelocity", "maximumVelocity", "calculateVelocity-AH228Gc", "(J)J", "resetTracking", "Landroidx/compose/ui/input/pointer/util/VelocityTracker1D;", "xVelocityTracker", "Landroidx/compose/ui/input/pointer/util/VelocityTracker1D;", "yVelocityTracker", "currentPointerPositionAccumulator", "J", "getCurrentPointerPositionAccumulator-F1C5BW0$ui_release", "setCurrentPointerPositionAccumulator-k-4lQ0M$ui_release", "(J)V", "lastMoveEventTimeStamp", "getLastMoveEventTimeStamp$ui_release", "setLastMoveEventTimeStamp$ui_release", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class VelocityTracker {
    public static final int $stable = 8;
    private long lastMoveEventTimeStamp;
    private final VelocityTracker1D xVelocityTracker = new VelocityTracker1D(false, null, 3, null);
    private final VelocityTracker1D yVelocityTracker = new VelocityTracker1D(false, null, 3, null);
    private long currentPointerPositionAccumulator = Offset.INSTANCE.m3252getZeroF1C5BW0();

    /* JADX INFO: renamed from: addPosition-Uv8p0NA, reason: not valid java name */
    public final void m4603addPositionUv8p0NA(long timeMillis, long position) {
        this.xVelocityTracker.addDataPoint(timeMillis, Offset.m3236getXimpl(position));
        this.yVelocityTracker.addDataPoint(timeMillis, Offset.m3237getYimpl(position));
    }

    /* JADX INFO: renamed from: calculateVelocity-9UxMQ8M, reason: not valid java name */
    public final long m4604calculateVelocity9UxMQ8M() {
        return m4605calculateVelocityAH228Gc(VelocityKt.Velocity(Float.MAX_VALUE, Float.MAX_VALUE));
    }

    /* JADX INFO: renamed from: calculateVelocity-AH228Gc, reason: not valid java name */
    public final long m4605calculateVelocityAH228Gc(long maximumVelocity) {
        if (Velocity.m5909getXimpl(maximumVelocity) > 0.0f && Velocity.m5910getYimpl(maximumVelocity) > 0.0f) {
            return VelocityKt.Velocity(this.xVelocityTracker.calculateVelocity(Velocity.m5909getXimpl(maximumVelocity)), this.yVelocityTracker.calculateVelocity(Velocity.m5910getYimpl(maximumVelocity)));
        }
        throw new IllegalStateException(("maximumVelocity should be a positive value. You specified=" + ((Object) Velocity.m5916toStringimpl(maximumVelocity))).toString());
    }

    /* JADX INFO: renamed from: getCurrentPointerPositionAccumulator-F1C5BW0$ui_release, reason: not valid java name and from getter */
    public final long getCurrentPointerPositionAccumulator() {
        return this.currentPointerPositionAccumulator;
    }

    /* JADX INFO: renamed from: getLastMoveEventTimeStamp$ui_release, reason: from getter */
    public final long getLastMoveEventTimeStamp() {
        return this.lastMoveEventTimeStamp;
    }

    public final void resetTracking() {
        this.xVelocityTracker.resetTracking();
        this.yVelocityTracker.resetTracking();
        this.lastMoveEventTimeStamp = 0L;
    }

    /* JADX INFO: renamed from: setCurrentPointerPositionAccumulator-k-4lQ0M$ui_release, reason: not valid java name */
    public final void m4607setCurrentPointerPositionAccumulatork4lQ0M$ui_release(long j10) {
        this.currentPointerPositionAccumulator = j10;
    }

    public final void setLastMoveEventTimeStamp$ui_release(long j10) {
        this.lastMoveEventTimeStamp = j10;
    }
}
