package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.IntSize;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0011\u0010\u0004\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0003\u001a\u0011\u0010\u0005\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0005\u0010\u0003\u001a\u0011\u0010\u0006\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0006\u0010\u0003\u001a\u0011\u0010\u0007\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0007\u0010\u0003\u001a\u0011\u0010\b\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\b\u0010\u0003\u001a\u0011\u0010\n\u001a\u00020\t*\u00020\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u0011\u0010\f\u001a\u00020\t*\u00020\u0000¢\u0006\u0004\b\f\u0010\u000b\u001a\u001d\u0010\u000e\u001a\u00020\t*\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0013\u0010\u0010\u001a\u00020\u0001*\u00020\u0000H\u0007¢\u0006\u0004\b\u0010\u0010\u0003\u001a\u0013\u0010\u0011\u001a\u00020\u0001*\u00020\u0000H\u0007¢\u0006\u0004\b\u0011\u0010\u0003\u001a\u0013\u0010\u0013\u001a\u00020\u0012*\u00020\u0000H\u0007¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0013\u0010\u0015\u001a\u00020\u0012*\u00020\u0000H\u0007¢\u0006\u0004\b\u0015\u0010\u0014\u001a\u0013\u0010\u0016\u001a\u00020\u0012*\u00020\u0000H\u0007¢\u0006\u0004\b\u0016\u0010\u0014\u001a\u001e\u0010\u001b\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a$\u0010\u001b\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001cø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006 "}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "", "changedToDown", "(Landroidx/compose/ui/input/pointer/PointerInputChange;)Z", "changedToDownIgnoreConsumed", "changedToUp", "changedToUpIgnoreConsumed", "positionChanged", "positionChangedIgnoreConsumed", "Landroidx/compose/ui/geometry/Offset;", "positionChange", "(Landroidx/compose/ui/input/pointer/PointerInputChange;)J", "positionChangeIgnoreConsumed", "ignoreConsumed", "positionChangeInternal", "(Landroidx/compose/ui/input/pointer/PointerInputChange;Z)J", "positionChangeConsumed", "anyChangeConsumed", "Lx6/t0;", "consumeDownChange", "(Landroidx/compose/ui/input/pointer/PointerInputChange;)V", "consumePositionChange", "consumeAllChanges", "Landroidx/compose/ui/unit/IntSize;", ContentDisposition.Parameters.Size, "isOutOfBounds-O0kMr_c", "(Landroidx/compose/ui/input/pointer/PointerInputChange;J)Z", "isOutOfBounds", "Landroidx/compose/ui/geometry/Size;", "extendedTouchPadding", "isOutOfBounds-jwHxaWs", "(Landroidx/compose/ui/input/pointer/PointerInputChange;JJ)Z", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PointerEventKt {
    @e
    public static final boolean anyChangeConsumed(PointerInputChange pointerInputChange) {
        return pointerInputChange.isConsumed();
    }

    public static final boolean changedToDown(PointerInputChange pointerInputChange) {
        return (pointerInputChange.isConsumed() || pointerInputChange.getPreviousPressed() || !pointerInputChange.getPressed()) ? false : true;
    }

    public static final boolean changedToDownIgnoreConsumed(PointerInputChange pointerInputChange) {
        return !pointerInputChange.getPreviousPressed() && pointerInputChange.getPressed();
    }

    public static final boolean changedToUp(PointerInputChange pointerInputChange) {
        return (pointerInputChange.isConsumed() || !pointerInputChange.getPreviousPressed() || pointerInputChange.getPressed()) ? false : true;
    }

    public static final boolean changedToUpIgnoreConsumed(PointerInputChange pointerInputChange) {
        return pointerInputChange.getPreviousPressed() && !pointerInputChange.getPressed();
    }

    @e
    public static final void consumeAllChanges(PointerInputChange pointerInputChange) {
        pointerInputChange.consume();
    }

    @e
    public static final void consumeDownChange(PointerInputChange pointerInputChange) {
        if (pointerInputChange.getPressed() != pointerInputChange.getPreviousPressed()) {
            pointerInputChange.consume();
        }
    }

    @e
    public static final void consumePositionChange(PointerInputChange pointerInputChange) {
        if (Offset.m3233equalsimpl0(positionChange(pointerInputChange), Offset.INSTANCE.m3252getZeroF1C5BW0())) {
            return;
        }
        pointerInputChange.consume();
    }

    @e
    /* JADX INFO: renamed from: isOutOfBounds-O0kMr_c, reason: not valid java name */
    public static final boolean m4462isOutOfBoundsO0kMr_c(PointerInputChange pointerInputChange, long j10) {
        long position = pointerInputChange.getPosition();
        float fM3236getXimpl = Offset.m3236getXimpl(position);
        float fM3237getYimpl = Offset.m3237getYimpl(position);
        return fM3236getXimpl < 0.0f || fM3236getXimpl > ((float) IntSize.m5844getWidthimpl(j10)) || fM3237getYimpl < 0.0f || fM3237getYimpl > ((float) IntSize.m5843getHeightimpl(j10));
    }

    /* JADX INFO: renamed from: isOutOfBounds-jwHxaWs, reason: not valid java name */
    public static final boolean m4463isOutOfBoundsjwHxaWs(PointerInputChange pointerInputChange, long j10, long j11) {
        if (!PointerType.m4577equalsimpl0(pointerInputChange.getType(), PointerType.INSTANCE.m4584getTouchT8wyACA())) {
            return m4462isOutOfBoundsO0kMr_c(pointerInputChange, j10);
        }
        long position = pointerInputChange.getPosition();
        float fM3236getXimpl = Offset.m3236getXimpl(position);
        float fM3237getYimpl = Offset.m3237getYimpl(position);
        return fM3236getXimpl < (-Size.m3305getWidthimpl(j11)) || fM3236getXimpl > Size.m3305getWidthimpl(j11) + ((float) IntSize.m5844getWidthimpl(j10)) || fM3237getYimpl < (-Size.m3302getHeightimpl(j11)) || fM3237getYimpl > Size.m3302getHeightimpl(j11) + ((float) IntSize.m5843getHeightimpl(j10));
    }

    public static final long positionChange(PointerInputChange pointerInputChange) {
        return positionChangeInternal(pointerInputChange, false);
    }

    @e
    public static final boolean positionChangeConsumed(PointerInputChange pointerInputChange) {
        return pointerInputChange.isConsumed();
    }

    public static final long positionChangeIgnoreConsumed(PointerInputChange pointerInputChange) {
        return positionChangeInternal(pointerInputChange, true);
    }

    private static final long positionChangeInternal(PointerInputChange pointerInputChange, boolean z) {
        return (z || !pointerInputChange.isConsumed()) ? Offset.m3240minusMKHz9U(pointerInputChange.getPosition(), pointerInputChange.getPreviousPosition()) : Offset.INSTANCE.m3252getZeroF1C5BW0();
    }

    public static /* synthetic */ long positionChangeInternal$default(PointerInputChange pointerInputChange, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        return positionChangeInternal(pointerInputChange, z);
    }

    public static final boolean positionChanged(PointerInputChange pointerInputChange) {
        return !Offset.m3233equalsimpl0(positionChangeInternal(pointerInputChange, false), Offset.INSTANCE.m3252getZeroF1C5BW0());
    }

    public static final boolean positionChangedIgnoreConsumed(PointerInputChange pointerInputChange) {
        return !Offset.m3233equalsimpl0(positionChangeInternal(pointerInputChange, true), Offset.INSTANCE.m3252getZeroF1C5BW0());
    }
}
