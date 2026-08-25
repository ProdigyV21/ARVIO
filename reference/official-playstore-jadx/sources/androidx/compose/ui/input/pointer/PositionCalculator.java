package androidx.compose.ui.input.pointer;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u001a\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H&ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H&ø\u0001\u0000¢\u0006\u0004\b\b\u0010\u0005J\u001a\u0010\t\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH&ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/input/pointer/PositionCalculator;", "", "Landroidx/compose/ui/geometry/Offset;", "positionOnScreen", "screenToLocal-MK-Hz9U", "(J)J", "screenToLocal", "localPosition", "localToScreen-MK-Hz9U", "localToScreen", "Landroidx/compose/ui/graphics/Matrix;", "localTransform", "Lx6/t0;", "localToScreen-58bKbWc", "([F)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface PositionCalculator {
    /* JADX INFO: renamed from: localToScreen-58bKbWc, reason: not valid java name */
    void mo4586localToScreen58bKbWc(float[] localTransform);

    /* JADX INFO: renamed from: localToScreen-MK-Hz9U, reason: not valid java name */
    long mo4587localToScreenMKHz9U(long localPosition);

    /* JADX INFO: renamed from: screenToLocal-MK-Hz9U, reason: not valid java name */
    long mo4588screenToLocalMKHz9U(long positionOnScreen);
}
